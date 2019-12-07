package com.example.ananops_android.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.Interface.ConfirmDialogInterface;
import com.example.ananops_android.R;
import com.example.ananops_android.activity.MainActivity;
import com.example.ananops_android.entity.InspectionContent;
import com.example.ananops_android.entity.RepairContent;
import com.zyyoona7.picker.DatePickerView;
import com.zyyoona7.picker.base.BaseDatePickerView;
import com.zyyoona7.picker.listener.OnDateSelectedListener;
import com.zyyoona7.wheel.WheelView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public List<RepairContent> initRepairContent(List<RepairContent> repairContents){
        RepairContent repairContent=new RepairContent();
        repairContent.setRepair_id("2019140282");
        repairContent.setCheck_group("第一组");
        repairContent.setRepair_address("科研楼一楼");
        repairContent.setRepair_man("李民浩");
        repairContent.setRepair_time("2019-11-11 18:39");
        repairContent.setRepair_content("笔记本电脑");
        repairContent.setRepair_status("待填方案");

        RepairContent repairContent1=new RepairContent();
        repairContent1.setRepair_id("2019140282");
        repairContent1.setCheck_group("第一组");
        repairContent1.setRepair_address("科研楼一楼");
        repairContent1.setRepair_man("李民浩");
        repairContent1.setRepair_time("2019-11-11 18:39");
        repairContent1.setRepair_content("笔记本电脑");
        repairContent1.setRepair_status("维修中");
        RepairContent repairContent2=new RepairContent();
        repairContent2.setRepair_id("2019140282");
        repairContent2.setCheck_group("第一组");
        repairContent2.setRepair_address("科研楼一楼");
        repairContent2.setRepair_man("李民浩");
        repairContent1.setRepair_time("2019-11-11 18:39");
        repairContent2.setRepair_content("笔记本电脑");
        repairContent2.setRepair_status("计划中");
        RepairContent repairContent3=new RepairContent();
        repairContent3.setRepair_id("2019140282");
        repairContent3.setCheck_group("第一组");
        repairContent3.setRepair_address("科研楼一楼");
        repairContent3.setRepair_man("李民浩");
        repairContent3.setRepair_time("2019-11-11 18:39");
        repairContent3.setRepair_content("笔记本电脑");
        repairContent3.setRepair_status("维修工待接单");
        RepairContent repairContent7=new RepairContent();
        repairContent7.setRepair_id("2019140282");
        repairContent7.setCheck_group("第一组");
        repairContent7.setRepair_address("科研楼一楼");
        repairContent7.setRepair_man("李民浩");
        repairContent7.setRepair_time("2019-11-11 18:39");
        repairContent7.setRepair_content("笔记本电脑");
        repairContent7.setRepair_status("待接单");
        RepairContent repairContent8=new RepairContent();
        repairContent8.setRepair_id("2019140282");
        repairContent8.setCheck_group("第一组");
        repairContent8.setRepair_address("科研楼一楼");
        repairContent8.setRepair_man("李民浩");
        repairContent8.setRepair_time("2019-11-11 18:39");
        repairContent8.setRepair_content("笔记本电脑");
        repairContent8.setRepair_status("审核不通过");
        RepairContent repairContent4=new RepairContent();
        repairContent4.setRepair_id("2019140282");
        repairContent4.setCheck_group("第一组");
        repairContent4.setRepair_address("科研楼一楼");
        repairContent4.setRepair_man("李民浩");
        repairContent4.setRepair_time("2019-11-11 18:39");
        repairContent4.setRepair_content("笔记本电脑");
        repairContent4.setRepair_status("待验收");

        RepairContent repairContent5=new RepairContent();
        repairContent5.setRepair_id("2019140282");
        repairContent5.setCheck_group("第一组");
        repairContent5.setRepair_address("科研楼一楼");
        repairContent5.setRepair_man("李民浩");
        repairContent5.setRepair_time("2019-11-11 18:39");
        repairContent5.setRepair_content("笔记本电脑");
        repairContent5.setRepair_status("已完成");
        RepairContent repairContent6=new RepairContent();
        repairContent6.setRepair_id("2019140282");
        repairContent6.setCheck_group("第一组");
        repairContent6.setRepair_address("科研楼一楼");
        repairContent6.setRepair_man("李民浩");
        repairContent6.setRepair_time("2019-11-11 18:39");
        repairContent6.setRepair_content("笔记本电脑");
        repairContent6.setRepair_status("待评价");

        repairContents.add(repairContent);
        repairContents.add(repairContent);
        repairContents.add(repairContent);
        repairContents.add(repairContent);
        repairContents.add(repairContent1);
        repairContents.add(repairContent1);
        repairContents.add(repairContent1);
        repairContents.add(repairContent1);
        repairContents.add(repairContent1);
        repairContents.add(repairContent2);
        repairContents.add(repairContent2);
        repairContents.add(repairContent3);
        repairContents.add(repairContent3);
        repairContents.add(repairContent4);
        repairContents.add(repairContent4);
        repairContents.add(repairContent5);
        repairContents.add(repairContent5);
        repairContents.add(repairContent6);
        repairContents.add(repairContent6);
        repairContents.add(repairContent7);
        repairContents.add(repairContent7);
        repairContents.add(repairContent8);
        repairContents.add(repairContent8);
        return repairContents;
    }
    public List<InspectionContent> initInspectionContent(List<InspectionContent>inspectionContents){
        InspectionContent inspectionContent=new InspectionContent();
        inspectionContent.setInspection_id("000000001");
        inspectionContent.setInspection_name("邮政储蓄银行北京邮电大学支行巡检");
        inspectionContent.setDevice_name("ATM机");
        inspectionContent.setInspection_status("待执行");
        InspectionContent inspectionContent1=new InspectionContent();
        inspectionContent1.setInspection_id("000000001");
        inspectionContent1.setInspection_name("邮政储蓄银行北京邮电大学支行巡检");
        inspectionContent1.setDevice_name("ATM机");
        inspectionContent1.setInspection_status("已完成");
        inspectionContents.add(inspectionContent);
        inspectionContents.add(inspectionContent);
        inspectionContents.add(inspectionContent);
        inspectionContents.add(inspectionContent);
        inspectionContents.add(inspectionContent);
        inspectionContents.add(inspectionContent);
        inspectionContents.add(inspectionContent1);
        inspectionContents.add(inspectionContent1);
        inspectionContents.add(inspectionContent1);
        inspectionContents.add(inspectionContent1);
        inspectionContents.add(inspectionContent1);
        inspectionContents.add(inspectionContent1);
        inspectionContents.add(inspectionContent1);
        return inspectionContents;
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
        fromContext.startActivity(intent);
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
        result[3] = "上午";
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        //加载布局
        View view = View.inflate(mContext, R.layout.dialog_confirm, null);
        //获取组件实例
        TextView textTitle = view.findViewById(R.id.textTitle);
//        TextView textContent=view.findViewById(R.id.textContent);
        TextView textConfirm = view.findViewById(R.id.textConfirm);
        textConfirm.setText("确定报修");
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
        list.add("上午");
        list.add("下午");
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

