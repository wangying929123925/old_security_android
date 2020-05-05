package com.example.ananops_android.activity;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ananops_android.R;
import com.example.ananops_android.fragment.UserMainFragment;
import com.example.ananops_android.fragment.UserMessageFragment;
import com.example.ananops_android.fragment.UserMineFragment;
import com.example.ananops_android.service.JWebSocketClientService;
import com.example.ananops_android.util.ActivityManager;
import com.example.ananops_android.util.BaseUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class UserMainActivity extends BaseActivity implements View.OnClickListener {
    private Fragment[] fragments;
    private ImageView[] imagebuttons;
    private TextView[] textviews;
    private int index;
    private int currentTabIndex=0;// 当前fragment的index
    private UserMainFragment boardFragment;
    private UserMessageFragment userTrainFragment;
    private UserMineFragment mineFragment;
    private Button main_nav_button;
    private DrawerLayout drawerLayout1;
    private JWebSocketClientService.JWebSocketClientBinder binder;
    private JWebSocketClientService jWebSClientService;
    private ChatMessageReceiver chatMessageReceiver;
    private Context mContext;
    private int fragmentIndexChange;
    //WebSocketClient client1;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder ibinder) {
            Log.e("MainActivity", "服务与活动成功绑定");
            binder = (JWebSocketClientService.JWebSocketClientBinder) ibinder;
            jWebSClientService = binder.getService();
           // client = jWebSClientService.client;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("MainActivity", "服务与活动成功断开");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        //启动服务
        startJWebSClientService();
        //绑定服务
        bindService();
        //注册广播
        doRegisterReceiver();
        checkNotification(this);
        setContentView(R.layout.activity_user_main);
      //  ActivityManager.getInstance().addActivity(this);
       // final Context mContext=this;
       // linkSocket("wss://www.ananops.com/wss/ws");
      //  imageBack=findViewById(R.id.img_back);
      //  imageRight=findViewById(R.id.img_right);
      //  title_text=findViewById(R.id.txt_title);//标题
        main_nav_button = findViewById(R.id.main_nav_button);
        drawerLayout1 = findViewById(R.id.drawer_layout_main);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.type_manage);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.logout:
                       drawerLayout1.closeDrawers();
                        BaseUtils.getInstence().intent(mContext, LoginActivity.class);
                        ActivityManager.getInstance().finishAllActivity();
                    break;
                    default:
                        drawerLayout1.closeDrawers();
                        break;
                }
                return true;
            }
        });
     //   initViews();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            index = bundle.getInt("fragmentIndex");
            Log.v("fragmentIndex", index + "");
        } else {
            index = 0;
        }
        initTabView();
    }
    @Override
    public void onClick(View v) {
    }

    /**
     * 绑定服务
     */
    private void bindService() {
        Intent bindIntent = new Intent(mContext, JWebSocketClientService.class);
        bindService(bindIntent, serviceConnection, BIND_AUTO_CREATE);
    }
    /**
     * 启动服务（websocket客户端服务）
     */
    private void startJWebSClientService() {
        Intent intent = new Intent(mContext, JWebSocketClientService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //android8.0以上通过startForegroundService启动service
            startForegroundService(intent);
        } else {
            startService(intent);
        }
        //startService(intent);
    }
    //广播接收类
    private class ChatMessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String message=intent.getStringExtra("message");

        }
    }
    /**
     * 动态注册广播
     */
    private void doRegisterReceiver() {
        chatMessageReceiver = new ChatMessageReceiver();
        IntentFilter filter = new IntentFilter("com.xch.servicecallback.content");
        registerReceiver(chatMessageReceiver, filter);
    }

    private void initViews() {
//    imageBack.setVisibility(View.GONE);//GONE与INVISIBLE区别，一个保留位置，一个不保留
    //imageRight.setVisibility(View.INVISIBLE);
        main_nav_button.setVisibility(View.VISIBLE);
        main_nav_button.setOnClickListener(new View.OnClickListener() {//展示导航栏
            @Override
            public void onClick(View v) {
                drawerLayout1.openDrawer(GravityCompat.START);
            }
        });
    }

    private void initTabView() {
        boardFragment=new UserMainFragment();
        userTrainFragment=new UserMessageFragment();
       // userDeviceFragment=new UserDeviceFragment();
        mineFragment=new UserMineFragment();
      // workFragment=new WorkFragment();
        fragments=new Fragment[]{boardFragment,userTrainFragment,mineFragment};
        imagebuttons=new ImageView[3];//图标
        imagebuttons[0]=findViewById(R.id.tab_main_img1);
        imagebuttons[1]=findViewById(R.id.tab_main_img2);
        imagebuttons[2]=findViewById(R.id.tab_main_img3);
        imagebuttons[0].setSelected(true);

        textviews=new TextView[3];//文字
        textviews[0]=findViewById(R.id.tab_main_text1);
        textviews[1]=findViewById(R.id.tab_main_text2);
        textviews[2]=findViewById(R.id.tab_main_text3);
        textviews[0].setTextColor(getResources().getColor(R.color.deep_blue));
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container,boardFragment)
                .add(R.id.fragment_container,userTrainFragment)
                .add(R.id.fragment_container,mineFragment)
                .hide(userTrainFragment).hide(mineFragment)
                .show(boardFragment).commit();
        changeFragment(index);
    }
    public void onTabClicked(View view) {
        switch (view.getId()){
            case R.id.tab_main_1:
                index = 0;
            //   title_text.setText("看板1");
                if(boardFragment!=null){
                    boardFragment.refresh();
                }
                break;
            case R.id.tab_main_2:
                index=1;
          //      title_text.setText("工作");
                break;
            case R.id.tab_main_3:
                index=2;
             //   title_text.setText("工作");
                break;
            default:
                break;
        }
        changeFragment(index);
    }

    private void changeFragment(int i) {
        if (currentTabIndex != i) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.hide(fragments[currentTabIndex]);
            if (!fragments[i].isAdded()) {
                fragmentTransaction.add(R.id.fragment_container, fragments[i]);
            }
            // fragmentTransaction..commit();
            fragmentTransaction.hide(fragments[currentTabIndex]).show(fragments[i]).commit();
        }
        imagebuttons[currentTabIndex].setSelected(false);
        imagebuttons[i].setSelected(true);
        textviews[currentTabIndex].setTextColor(0xFF999999);
        textviews[i].setTextColor(0xFF45C01A);
        currentTabIndex = i;
    }
    /**
     * 检测是否开启通知
     *
     * @param context
     */
    private void checkNotification(final Context context) {
        if (!isNotificationEnabled(context)) {
            new AlertDialog.Builder(context).setTitle("温馨提示")
                    .setMessage("你还未开启系统通知，将影响消息的接收，要去开启吗？")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setNotification(context);
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).show();
        }
    }
    /**
     * 如果没有开启通知，跳转至设置界面
     *
     * @param context
     */
    private void setNotification(Context context) {
        Intent localIntent = new Intent();
        //直接跳转到应用通知设置的代码：
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            localIntent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            localIntent.putExtra("app_package", context.getPackageName());
            localIntent.putExtra("app_uid", context.getApplicationInfo().uid);
        } else if (android.os.Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            localIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            localIntent.addCategory(Intent.CATEGORY_DEFAULT);
            localIntent.setData(Uri.parse("package:" + context.getPackageName()));
        } else {
            //4.4以下没有从app跳转到应用通知设置页面的Action，可考虑跳转到应用详情页面,
            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT >= 9) {
                localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
            } else if (Build.VERSION.SDK_INT <= 8) {
                localIntent.setAction(Intent.ACTION_VIEW);
                localIntent.setClassName("com.android.settings", "com.android.setting.InstalledAppDetails");
                localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
            }
        }
        context.startActivity(localIntent);
    }
    /**
     * 获取通知权限,监测是否开启了系统通知
     *
     * @param context
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private boolean isNotificationEnabled(Context context) {

        String CHECK_OP_NO_THROW = "checkOpNoThrow";
        String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";

        AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        ApplicationInfo appInfo = context.getApplicationInfo();
        String pkg = context.getApplicationContext().getPackageName();
        int uid = appInfo.uid;

        Class appOpsClass = null;
        try {
            appOpsClass = Class.forName(AppOpsManager.class.getName());
            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE,
                    String.class);
            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);

            int value = (Integer) opPostNotificationValue.get(Integer.class);
            return ((Integer) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(chatMessageReceiver);
        super.onDestroy();
    }
}
