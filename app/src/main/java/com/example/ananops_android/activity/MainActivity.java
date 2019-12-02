package com.example.ananops_android.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.zyyoona7.picker.DatePickerView;
import com.zyyoona7.picker.base.BaseDatePickerView;
import com.zyyoona7.picker.ex.DayWheelView;
import com.zyyoona7.picker.ex.MonthWheelView;
import com.zyyoona7.picker.ex.YearWheelView;
import com.zyyoona7.picker.listener.OnDateSelectedListener;
import com.zyyoona7.wheel.WheelView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    protected Context mContext;
    private TextView textView;
    private String[] result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv_repair_time);

        mContext = this;



//        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
//        if(preferences.getString("weather",null)!=null){
//            Intent intent=new Intent(this,WeatherActivity.class);
//            startActivity(intent);
//            finish();//finish
//        }
    }

    public void select(View view) {
        showConfirmDialog("请选择具体保修的时间", new ConfirmDialogInterface() {
            @Override
            public void onConfirmClickListener() {
                Toast.makeText(MainActivity.this, "queding", Toast.LENGTH_SHORT).show();
                textView.setText(result[0]+"年"+result[1]+"月"+result[2]+"日"+result[3]);
            }

            @Override
            public void onCancelClickListener() {
                Toast.makeText(MainActivity.this, "quxiao", Toast.LENGTH_SHORT).show();
            }
        });

    }

    protected void showConfirmDialog(@Nullable String title, @NonNull final ConfirmDialogInterface confirmDialogInterface) {
        Calendar calendar = Calendar.getInstance();
        //获取系统的日期
        //年
        int year = calendar.get(Calendar.YEAR);
        //月
        int month = calendar.get(Calendar.MONTH)+1;
         //日
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        result = new String[4];
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
                Toast.makeText(MainActivity.this, "选中的日期：" + year + "-" + month + "-" + day, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(MainActivity.this, "选择了" + data, Toast.LENGTH_SHORT).show();
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
