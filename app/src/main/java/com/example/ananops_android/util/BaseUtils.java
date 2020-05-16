package com.example.ananops_android.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.ananops_android.BuildConfig;
import com.example.ananops_android.activity.InspectionSearchListActivity;
import com.example.ananops_android.activity.UserMainActivity;
import com.example.ananops_android.db.AllUnDistributedWorkOrdersResponse;
import com.example.ananops_android.db.ChangeStatusDto;
import com.example.ananops_android.db.CodeMessageResponse;
import com.example.ananops_android.db.InspectionListByUserIdAndStatusRequest;
import com.example.ananops_android.db.OrderRequest;
import com.example.ananops_android.db.OrderResponse;
import com.example.ananops_android.entity.InspectionInfo;
import com.example.ananops_android.entity.RepairAddContent;
import com.example.ananops_android.entity.RepairContent;
import com.example.ananops_android.net.Net;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import retrofit2.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BaseUtils {
    public static BaseUtils instence;
    public static BaseUtils getInstence() {
        if (null == instence) {
            instence = new BaseUtils();
        }
        return instence;
    }

    private BaseUtils() {

    }
    public String statusNumConvertString(int status){
        final String statusString;
        switch (status){
            case 1:
                statusString = "工单取消";
                break;
            case 2:
                statusString = "甲方待审核";
                break;
            case 3:
                statusString = "服务商待接单";
                break;
            case 4:
                statusString = "服务商待分配工程师";
                break;
            case 5:
                statusString = "维修工待接单";
                break;
            case 6:
                statusString = "执行中";
                break;
            case 7:
                statusString = "服务商待审核备件";
                break;
            case 8:
                statusString = "管理员待审核备件";
                break;
            case 9:
                statusString = "维修中";
                break;
            case 10:
                statusString = "用户待验收";
                break;
            case 11:
                statusString = "管理员待支付";
                break;
            case 12:
                statusString = "待评价";
                break;
            case 13:
                statusString = "已完成";
                 break;
            case 14:
                statusString = "已完成";
                break;
           default:
               statusString = " ";
                break;
        }
        return statusString;
    }
    public int statusStringConvertNum(String statusString){
        final int statusInt;
        if (statusString != null) {
            switch (statusString) {
                case "值机员待确认":
                    statusInt = 1;
                    break;
                case "待审核":
                    statusInt = 2;
                    break;
                case "服务商待接单":
                    statusInt = 3;
                    break;
                case "维修工待接单":
                    statusInt = 4;
                    break;
                case "维修工已接单":
                    statusInt = 5;
                    break;
                case "待审核备件":
                    statusInt = 6;
                    break;
                case "维修中":
                    statusInt = 7;
                    break;
                case "服务商待验收":
                    statusInt = 8;
                    break;
                case "待审核账单":
                    statusInt = 9;
                    break;
                case "用户待验收":
                    statusInt = 10;
                    break;
                case "待评价":
                    statusInt = 11;
                    break;
                case "待支付":
                    statusInt = 12;
                    break;
                case "已完成":
                    statusInt = 13;
                    break;
                default:
                    statusInt = 0;
                    break;
            }
        } else {
            statusInt=1;
        }
        return statusInt;
    }
    public OkHttpClient.Builder getClient(){
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(15, TimeUnit.SECONDS);
        //add log record
        if (BuildConfig.DEBUG) {
            //打印网络请求日志
            LoggingInterceptor httpLoggingInterceptor = new LoggingInterceptor.Builder()
                    .loggable(BuildConfig.DEBUG)
                    .setLevel(Level.BASIC)
                    .log(Platform.INFO)
                    .request("请求")
                    .response("响应")
                    .build();
            httpClientBuilder.addInterceptor(httpLoggingInterceptor);
        }
        return httpClientBuilder;
    }
    public void roleStringConvertNum(String roleName,Context mContext){
        switch (roleName){
            case "用户负责人":
                SPUtils.getInstance(mContext).putInt("role_num",4);
                break;
            case "用户值机员":
                SPUtils.getInstance(mContext).putInt("role_num",1);
                break;
            case "维修工程师":
                SPUtils.getInstance(mContext).putInt("role_num",3);
                break;
            case "服务商负责人":
                SPUtils.getInstance(mContext).putInt("role_num",2);
                break;
                 default:
                 SPUtils.getInstance(mContext).putInt("role_num",1);

        }
    }
    public List<RepairContent> getRepairList(final List<RepairContent> repairContents, OrderRequest orderRequest, final Context mContext){
        Net.instance.getRepairList(orderRequest, SPUtils.getInstance(mContext).getString("Token"," "))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OrderResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("LoginTime", System.currentTimeMillis() + "");
                        e.printStackTrace();
                       Toast.makeText(mContext, "网络异常，请检查网络状态", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(OrderResponse orderResponse) {
                        if(TextUtils.equals(orderResponse.getCode(),"200")){
                            repairContents.clear();
                            for (int i = 0; i < orderResponse.getResult().size(); i++) {
                            repairContents.add(orderResponse.getResult().get(i));
                        }
                        Toast.makeText(mContext,"repairContents", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(mContext, orderResponse.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    }
                });
        return repairContents;
    }

    //维修add
    public void repairAdd(RepairAddContent repairAddContent, final Context mContext) {
        Net.instance.repairAddPost(repairAddContent,SPUtils.getInstance(mContext).getString("Token",""))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CodeMessageResponse>(){
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("LoginAddTime", System.currentTimeMillis() + "");
                        //  e.printStackTrace();
                        Toast.makeText(mContext, "提交失败", Toast.LENGTH_SHORT).show();
                        if (e instanceof HttpException) {
                            HttpException httpException = (HttpException) e;
                            try{
                                String error = httpException.response().errorBody().string();
                                Log.v("RepairAddError", error);
                            }catch(IOException e1) {
                                e1.printStackTrace();
                            }
                        }else {
                            //ToastUtil.showLongToast("请求失败");
                        }

                    }
                    @Override
                    public void onNext(CodeMessageResponse codeMessageResponse) {
                        if(TextUtils.equals(codeMessageResponse.getCode(),"200")){
                            Toast.makeText(mContext,"提交成功！",Toast.LENGTH_SHORT).show();
                           BaseUtils.getInstence().intent(mContext,UserMainActivity.class);

                        }
                        else{
                            Toast.makeText(mContext,"服务器故障！",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /*
   * 改变巡检状态
   * */
    public void changeStatus(int status,String orderId,String statusMsg ,final Context mContext){
        ChangeStatusDto changeStatusDto=new ChangeStatusDto();
        changeStatusDto.setStatus(status);
        changeStatusDto.setStatusMsg(statusMsg);
        changeStatusDto.setTaskId(orderId);
      Net.instance.changeStatus(changeStatusDto,orderId,SPUtils.getInstance(mContext).getString("Token"," "))
              .subscribeOn(Schedulers.newThread())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new Subscriber<CodeMessageResponse>() {
                  @Override
                  public void onCompleted() {

                  }

                  @Override
                  public void onError(Throwable e) {
                      Log.v("LoginTime", System.currentTimeMillis() + "");
                      e.printStackTrace();
                      Toast.makeText(mContext, "操作失败！", Toast.LENGTH_SHORT).show();
                     // BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                  }

                  @Override
                  public void onNext(CodeMessageResponse codeMessageResponse) {
                      if(TextUtils.equals(codeMessageResponse.getCode(),"200")){
                          Log.v("操作成功", System.currentTimeMillis() + "");
                          Toast.makeText(mContext,"操作成功！",Toast.LENGTH_SHORT).show();
                          BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                      }
                      else{
                          Toast.makeText(mContext,"操作失败！",Toast.LENGTH_SHORT).show();
                          Log.v("操作成功", codeMessageResponse.getMessage());
                      }
                  }
              });
    }

    /*
     根据巡检状态获取巡检列表
     */
    public void getAndPassInspectionList(int status, final String statusDo, final Context mContext) {
        InspectionListByUserIdAndStatusRequest inspectionListByUserIdAndStatusRequest = new InspectionListByUserIdAndStatusRequest();
        inspectionListByUserIdAndStatusRequest.setRole(1);
        inspectionListByUserIdAndStatusRequest.setStatus(status);
        inspectionListByUserIdAndStatusRequest.setUserId(Long.valueOf(SPUtils.getInstance(mContext).getString("user_id", "")));
        Net.instance.getInspectionTaskByUserIdAndStatus(inspectionListByUserIdAndStatusRequest, SPUtils.getInstance(mContext).getString("Token", " "))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AllUnDistributedWorkOrdersResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("ErrorGetInsTaskById", System.currentTimeMillis() + "");
                        e.printStackTrace();
                        Toast.makeText(mContext, "获取巡检信息失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(AllUnDistributedWorkOrdersResponse allUnauthorizedTaskResponse) {
                        if (TextUtils.equals(allUnauthorizedTaskResponse.getCode(),"200")) {
                            ArrayList<InspectionInfo> result = allUnauthorizedTaskResponse.getResult().getList();
                            if (result != null) {
                                Bundle bundle = new Bundle();
                                bundle.putParcelableArrayList("result", result);
                                bundle.putString("statusDo",statusDo);
                                BaseUtils.getInstence().intent(mContext, InspectionSearchListActivity.class,bundle,"title","4-2");
                            }
                        }
                    }
                });
    }
    /**
     * 不带参数的跳转
     *
     * @param fromContext
     * @param cls
     *            泛型
     */
    public void intent(Context fromContext, Class<?> cls) {
        Intent intent = new Intent(fromContext, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // ActivityManager.getInstance().finishActivity();
        fromContext.startActivity(intent);
      //  fromContext.finish
    }
    /**
     * 带一个参数的跳转
     *
     * @param fromContext
     * @param cls
     *            泛型
     */
    public void intent(Context fromContext, Class<?> cls,String dataName, String data) {
        Intent intent = new Intent(fromContext, cls);
        intent.putExtra(dataName,data);
        fromContext.startActivity(intent);
      //  ActivityManager.getInstance().finishActivity();
    }

    public void intent(Context fromContext, Class<?> cls,Bundle bundle,String dataName, String data) {
        Intent intent = new Intent(fromContext, cls);
        intent.putExtra(dataName,data);
        intent.putExtras(bundle);
        fromContext.startActivity(intent);
      //  ActivityManager.getInstance().finishActivity();
    }
    /**
     * 带参数的跳转
     *
     * @param fromContext
     * @param cls
     *            泛型
     */
    public void intent(Context fromContext, Class<?> cls,Bundle bb) {
        Intent intent = new Intent(fromContext, cls);
        intent.putExtras(bb);
        fromContext.startActivity(intent);
       // ActivityManager.getInstance().finishActivity();
    }
    /**
     * 封装 startActivityForResult 带参数传�?
     *
     * @param fromClass
     * @param toClass
     * @param requestCode
     * @param data
     *            参数传�?
     */
    public void startActivityForResult(Activity fromClass, Class<?> toClass, int requestCode, Bundle data) {
        Intent intent = new Intent();
        intent.setClass(fromClass, toClass);
        if (null != data) {
            intent.putExtras(data);
        }
        fromClass.startActivityForResult(intent, requestCode);
    }

    /**
     * 接收参数然后在返回数值
     * @param fromContext 当前的activity
     * @param bb
     * @param RESULT_OK
     */
    public void setResult(Activity fromContext,Bundle bb,int RESULT_OK){
        Intent intent=new Intent();
        intent.putExtras(bb);
        fromContext.setResult(RESULT_OK, intent);
        fromContext.finish();
    }

    public static Date stringToDate(String str) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date date = null;
        try {
            // Fri Feb 24 00:00:00 CST 2012
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 验证邮箱
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }

    /**
     * 验证手机号
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(17[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 验证是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        java.util.regex.Matcher match = pattern.matcher(str);
        if (match.matches() == false) {
            return false;
        } else {
            return true;
        }
    }
    public String getTime() {
        /* 获取当前系统时间 */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String time = sdf.format(curDate);
        return time;
    }

}

