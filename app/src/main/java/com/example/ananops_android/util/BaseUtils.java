package com.example.ananops_android.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.BuildConfig;
import com.example.ananops_android.Interface.ConfirmDialogInterface;
import com.example.ananops_android.R;
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
import com.zyyoona7.picker.DatePickerView;
import com.zyyoona7.picker.base.BaseDatePickerView;
import com.zyyoona7.picker.listener.OnDateSelectedListener;
import com.zyyoona7.wheel.WheelView;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
                statusString = "维修工已结单";
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
                statusString = "管理员待确认账单";
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
      Net.instance.changeStatus(changeStatusDto,SPUtils.getInstance(mContext).getString("Token"," "))
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

                      Toast.makeText(mContext, "网络异常，请检查网络状态changeStatus", Toast.LENGTH_SHORT).show();
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
    /**
     * 选择预约时间的dialog
     * @param title
     * @param confirmDialogInterface
     */
    public static void showConfirmDialog(final String[] result,final Context mContext, @Nullable String title, @NonNull final ConfirmDialogInterface confirmDialogInterface) {
        Calendar calendar = Calendar.getInstance();
        //获取系统的日期
        //年
        int year = calendar.get(Calendar.YEAR);
        //月
        int month = calendar.get(Calendar.MONTH)+1;
        //日
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        result[0] = year + "";
        result[1] = month + "";
        result[2] = day + "";
        result[3] = "01:00:00";
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        //加载布局
        View view = View.inflate(mContext, R.layout.dialog_confirm, null);
        //获取组件实例
        TextView textTitle = view.findViewById(R.id.textTitle);
//        TextView textContent=view.findViewById(R.id.textContent);
        TextView textConfirm = view.findViewById(R.id.textConfirm);
        textConfirm.setText("确定");
        TextView textCancel = view.findViewById(R.id.textCancel);
        textCancel.setText("取消");

        DatePickerView defaultDpv = view.findViewById(R.id.dpv_default);
        defaultDpv.setTextSize(24, true);
        defaultDpv.setLabelTextSize(20);
        defaultDpv.setMinDate(calendar);
        Calendar maxCalendar = Calendar.getInstance();
        maxCalendar.add(Calendar.MONTH, 3);
        defaultDpv.setMaxDate(maxCalendar);

//        //隐藏年月日
//        defaultDpv.setShowLabel(false);
//
//        //获取年月日 WheelView
//        YearWheelView yearWv3 = defaultDpv.getYearWv();
//        MonthWheelView monthWv3 = defaultDpv.getMonthWv();
//        DayWheelView dayWv3 = defaultDpv.getDayWv();
//        //注意：setIntegerNeedFormat(String integerFormat)方法 integerFormat 中必须包含并且只能包含一个格式说明符（format specifier）
//        //更多请查看该方法参数说明
//        yearWv3.setIntegerNeedFormat("%d年");
//        monthWv3.setIntegerNeedFormat("%d月");
//        dayWv3.setIntegerNeedFormat("%02d日");

        //选中回调
        defaultDpv.setOnDateSelectedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(BaseDatePickerView datePickerView, int year, int month, int day, @Nullable Date date) {
                Toast.makeText(mContext, "选中的日期：" + year + "-" + month + "-" + day, Toast.LENGTH_SHORT).show();
                result[0] = year + "";
                result[1] = month + "";
                result[2] = day + "";

            }
        });

        //初始化数据
        List<String> list = new ArrayList();
        list.add("01:00:00");
        list.add("02:00:00");
        list.add("03:00:00");
        list.add("04:00:00");
        list.add("05:00:00");
        list.add("06:00:00");
        list.add("07:00:00");
        list.add("08:00:00");
        list.add("09:00:00");
        list.add("10:00:00");
        list.add("11:00:00");
        list.add("12:00:00");
        list.add("13:00:00");
        list.add("14:00:00");
        list.add("15:00:00");
        list.add("16:00:00");
        list.add("17:00:00");
        list.add("18:00:00");
        list.add("19:00:00");
        list.add("20:00:00");
        list.add("21:00:00");
        list.add("22:00:00");
        list.add("23:00:00");
        list.add("24:00:00");
        //泛型为数据类型
        final WheelView<String> newWheelView = view.findViewById(R.id.newwheelview);

        //设置数据
        newWheelView.setData(list);
        newWheelView.setOnItemSelectedListener(new WheelView.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(WheelView<String> wheelView, String data, int position) {
//                Toast.makeText(mContext, "选择了" + data, Toast.LENGTH_SHORT).show();
                result[3] = data;
            }
        });
        //尽请使用各种方法
        newWheelView.setTextSize(24f, true);
        //设置标题
        textTitle.setText(title);
        //设置消息内容
//        textContent.setText(msg);
        //设置需要显示的view
        builder.setView(view);
        //赋值给其父类以获取dismiss方法
        final AlertDialog alertDialog = builder.create();
        //显示dialog
        alertDialog.show();
        //设置确定按钮内容
        textConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //确认键业务逻辑处理接口
                confirmDialogInterface.onConfirmClickListener();
                //业务逻辑处理完毕使dialog消失
                alertDialog.dismiss();
            }
        });
        //设置取消按钮内容
        textCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消键业务逻辑处理接口
                confirmDialogInterface.onCancelClickListener();
                //业务逻辑处理完毕使dialog消失
                alertDialog.dismiss();
            }
        });

    }
}

