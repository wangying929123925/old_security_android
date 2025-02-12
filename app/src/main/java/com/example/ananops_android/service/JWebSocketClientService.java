package com.example.ananops_android.service;

import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.ananops_android.R;
import com.example.ananops_android.activity.UserMainActivity;
import com.example.ananops_android.entity.MessageEntity;
import com.example.ananops_android.entity.URL;
import com.example.ananops_android.net.JWebSocketClient;
import com.example.ananops_android.util.SPUtils;
import com.google.gson.Gson;

import org.java_websocket.handshake.ServerHandshake;

import java.io.IOException;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;
import ua.naiksoftware.stomp.dto.StompHeader;
import ua.naiksoftware.stomp.dto.StompMessage;


public class JWebSocketClientService extends Service {
    private JWebSocketClientBinder mBinder = new JWebSocketClientBinder();;
    public JWebSocketClient client;
    private StompClient mStompClient;
    private CompositeDisposable compositeDisposable;
    private static final String TAG = "------------->stomp";
    private final static int GRAY_SERVICE_ID = 1001;
    public static final String CHANNEL_ONE_ID = "com.example.ananops_android.channel";
    public static final String CHANNEL_ONE_NAME = "Channel_One";
    PowerManager.WakeLock wakeLock;//锁屏唤醒
    //获取电源锁，保持该服务在屏幕熄灭时仍然获取CPU时，保持运行
    @SuppressLint("InvalidWakeLockTag")
    private void acquireWakeLock()
    {
        if (null == wakeLock)
        {
            PowerManager pm = (PowerManager)this.getSystemService(Context.POWER_SERVICE);
            wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK|PowerManager.ON_AFTER_RELEASE, "PostLocationService");
            if (null != wakeLock)
            {
                wakeLock.acquire();
            }
        }
    }
    public JWebSocketClientService() {
    }
    //灰色保活手段
    public static class GrayInnerService extends Service {

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            //如果API在26以上即版本为O则调用startForefround()方法启动服务
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                //    startForegroundService()
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                if (manager != null) {
                    //进行渠道设置
                    //第三个参数值越小，该通知的重要性越低，因为我们只需要开启前台服务，不需要让用户知道
                    NotificationChannel channel = new NotificationChannel("channel","keep",NotificationManager.IMPORTANCE_MIN);
                    manager.createNotificationChannel(channel);
                    Notification notification = new NotificationCompat.Builder(this,"channel").build();
                    startForeground(GRAY_SERVICE_ID,notification);
                }

            } else {
                startForeground(GRAY_SERVICE_ID, new Notification());
            }
            stopForeground(true);
            stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    //用于Activity和service通讯
    public class JWebSocketClientBinder extends Binder {
        public JWebSocketClientService getService() {
            return JWebSocketClientService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("JWebSocketClientService", "启动服务");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        resetSubscriptions();
        initStompClient();
        //初始化websocket
        //initSocketClient();
       // mHandler.postDelayed(heartBeatRunnable, HEART_BEAT_RATE);//开启心跳检测
        //设置service为前台服务，提高优先级
        if (Build.VERSION.SDK_INT <Build.VERSION_CODES.JELLY_BEAN_MR2) {
            //Android4.3以下 ，隐藏Notification上的图标,直接发送空消息
            startForeground(GRAY_SERVICE_ID, new Notification());
        } else if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O){
            //Android4.3 - Android7.0，隐藏Notification上的图标
            //先发送通知，然后再开启一个服务，在里面发送另一个id一样的通知，最后将第二个前台服务停止并销毁自己,第一次发送的通知被取消掉
            Intent innerIntent = new Intent(this, GrayInnerService.class);
            startService(innerIntent);
            startForeground(GRAY_SERVICE_ID, new Notification());
        }else {
            //Android8.0以上app启动后通知栏会出现一条"正在运行"的通知
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (manager != null) {
                //进行渠道设置
                //第三个参数值越小，该通知的重要性越低，因为我们只需要开启前台服务，不需要让用户知道
                NotificationChannel channel = new NotificationChannel("channel","keep",NotificationManager.IMPORTANCE_MIN);
                manager.createNotificationChannel(channel);
                Notification notification = new NotificationCompat.Builder(this,"channel").build();
                startForeground(GRAY_SERVICE_ID,notification);
            }
           // startForeground(GRAY_SERVICE_ID, new Notification());
        }

        acquireWakeLock();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
       // closeConnect();
        mStompClient.disconnect();
        if (compositeDisposable != null) compositeDisposable.dispose();
        super.onDestroy();
    }

    /*
     *
     *初始化StompClient
     * */
    private void initStompClient() {
        mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, URL.ws);
        List<StompHeader> headers = new ArrayList<>();
      //  SPUtils.getInstance().init(this);
        headers.add(new StompHeader("userId", this.getSharedPreferences("GeneralStore",Context.MODE_PRIVATE).getString("user_id", "111")));
        resetSubscriptions();
        mStompClient.connect(headers);
        mStompClient.withClientHeartbeat(1000).withServerHeartbeat(1000);
        Disposable dispLifecycle = mStompClient.lifecycle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(lifecycleEvent -> {
                    switch (lifecycleEvent.getType()) {
                        case OPENED:
                            Log.e(TAG,"Stomp connection opened");
                            break;
                        case ERROR:
                            Log.e(TAG, "Stomp connection error", lifecycleEvent.getException());
                            break;
                        case CLOSED:
                            resetSubscriptions();
                            break;
                        case FAILED_SERVER_HEARTBEAT:
                            Log.e(TAG,"Stomp failed server heartbeat");
                            break;
                    }
                });
        compositeDisposable.add(dispLifecycle);
        // Receive greetings
        Disposable dispTopic = mStompClient.topic("/user/queue/chat")
                //Disposable dispTopic = mStompClient.topic("/topic")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((StompMessage topicMessage) -> {
                    Log.e(TAG, "Received " + topicMessage.getPayload());
                    Intent intent = new Intent();
                    intent.setAction("com.xch.servicecallback.content");
                    intent.putExtra("message", topicMessage.getPayload());
                    sendBroadcast(intent);
                    checkLockAndShowNotification( topicMessage.getPayload());
                    //DataModel response1 = mGson.fromJson(topicMessage.getPayload(), DataModel.class);
                    //添加你的数据逻辑

                }, throwable -> {
                    Log.e(TAG, "连接错误", throwable);
                });

        compositeDisposable.add(dispTopic);



    }
    private void resetSubscriptions() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
        compositeDisposable = new CompositeDisposable();
    }

    private void initSocketClient() {
        URI uri = URI.create(URL.ws);
        HashMap<String, String> map = new HashMap<>();
        map.put("userId", SPUtils.getInstance(this).getString("user_id", "111"));
        client = new JWebSocketClient(uri,map) {
            @Override
            public void onMessage(String message) {
                Log.e("JWebSocketClientService", "收到的消息：" + message);
                Intent intent = new Intent();
                intent.setAction("com.xch.servicecallback.content");
                intent.putExtra("message", message);
                sendBroadcast(intent);
                checkLockAndShowNotification(message);
            }
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                super.onOpen(handshakedata);
                Log.e("JWebSocketClientService", "websocket连接成功");
            }
            @Override
            public void onClose(int code, String reason, boolean remote) {
                Log.e("JWebSocketService", "websocket连接关闭"+reason);
            }

            @Override
            public void onError(Exception ex) {
                Log.e("连接失败", "onError()");
            }
        };
        connect();
    }
    /**
     * 连接websocket
     */
    private void connect() {
        new Thread() {
            @Override
            public void run() {
                try {
                    // wss需添加s
                    SSLContext sslContext = SSLContext.getInstance("TLS");
                    sslContext.init(null, new TrustManager[] { new X509TrustManager(){
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) {

                        }
                        @Override
                        public void checkServerTrusted(X509Certificate[] chain,String authType) {

                        }
                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }
                    }, new SecureRandom());
                    SSLSocketFactory factory = sslContext.getSocketFactory();
                    client.setSocket(factory.createSocket());
                   // client.connect();
                    //connectBlocking多出一个等待操作，会先连接再发送，否则未连接发送会报错
                    client.connectBlocking();
                   // sendMsg("111");
                }  catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (KeyManagementException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 发送消息
     *
     * @param msg
     */
    public void sendMsg(String msg) {
        if (null != client) {
            Log.e("JWebSocketClientService", "发送的消息：" + msg);
            client.send(msg);
        }
    }
    /**
     * 断开连接
     */
    private void closeConnect() {
        try {
            if (null != client) {
                client.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client = null;
        }
    }
    //    -----------------------------------消息通知--------------------------------------------------------
    /**
     * 检查锁屏状态，如果锁屏先点亮屏幕
     *
     * @param content
     */
    private void checkLockAndShowNotification(String content) {
        //管理锁屏的一个服务
        KeyguardManager km = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        if (km.inKeyguardRestrictedInputMode()) {//锁屏
            //获取电源管理器对象
            PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
            if (!pm.isScreenOn()) {
                @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP |
                        PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "bright");
                wl.acquire();  //点亮屏幕
                wl.release();  //任务结束后释放
            }
            sendNotification(content);
        } else {
            sendNotification(content);
        }
    }

    /**
     * 发送通知
     *
     * @param content
     */
    private void sendNotification(String content) {
        Gson gson = new Gson();
        MessageEntity messageEntity = gson.fromJson(content, MessageEntity.class);
        String messageContent = messageEntity.getContent().getMsgBodyDto().getStatusMsg();
        Intent intent = new Intent();
        intent.setClass(this, UserMainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel notificationChannel = null;
        Notification notification = null;
        //----------------  新增代码 --------------------------------------
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            //修改安卓8.1以上系统报错
            notificationChannel = new NotificationChannel(CHANNEL_ONE_ID, CHANNEL_ONE_NAME,NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.enableLights(true);//如果使用中的设备支持通知灯，则说明此通知通道是否应显示灯
            notificationChannel.setShowBadge(true);//是否显示角标
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_SECRET);
            //  NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notifyManager.createNotificationChannel(notificationChannel);
          //  notificationBuilder.setChannelId(CHANNEL_ONE_ID);
             notification = new Notification.Builder(this,CHANNEL_ONE_ID)
                    .setAutoCancel(true)
                     .setChannelId(CHANNEL_ONE_ID)
                    // 设置该通知优先级
                    .setPriority(Notification.PRIORITY_MAX)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("安安运维：您的工单有更新")
                    .setContentText(messageContent)
                    .setWhen(System.currentTimeMillis())
                    // 向通知添加声音、闪灯和振动效果
                    .setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_ALL | Notification.DEFAULT_SOUND)
                    .setContentIntent(pendingIntent)
                   .build();
        }else {
            notification = new Notification.Builder(this)
                    .setAutoCancel(true)
                    // 设置该通知优先级
                    .setPriority(Notification.PRIORITY_MAX)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("安安运维：您的工单有更新")
                    .setContentText(messageContent)
                    .setWhen(System.currentTimeMillis())
                    // 向通知添加声音、闪灯和振动效果
                    .setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_ALL | Notification.DEFAULT_SOUND)
                    .setContentIntent(pendingIntent)
                    .build();
        }
        //notificationBuilder.setChannelId(CHANNEL_ONE_ID);
     //   Notification notification = notificationBuilder.build();
        notification.defaults = Notification.DEFAULT_SOUND; //设置为默认的声音
        notification.flags |= Notification.FLAG_NO_CLEAR;
        notifyManager.notify(1, notification);//id要保证唯一
      //  startForeground(1,notification);
    }
    //    -------------------------------------websocket心跳检测------------------------------------------------
    private static final long HEART_BEAT_RATE = 10 * 1000;//每隔10秒进行一次对长连接的心跳检测
    private Handler mHandler = new Handler();
    private Runnable heartBeatRunnable = new Runnable() {
        @Override
        public void run() {
            Log.e("JWebSocketClientService", "心跳包检测websocket连接状态");
            if (client != null) {
                if (client.isClosed()) {
                    reconnectWs();
                }
            } else {
                //如果client已为空，重新初始化连接
                client = null;
                initSocketClient();
            }
            //每隔一定的时间，对长连接进行一次心跳检测
            mHandler.postDelayed(this, HEART_BEAT_RATE);
        }
    };
    //重新链接
    private void reconnectWs() {
        mHandler.removeCallbacks(heartBeatRunnable);
        new Thread() {
            @Override
            public void run() {
                try {
                    Log.e("JWebSocketClientService", "开启重连");
                    client.reconnectBlocking();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
