package com.example.ananops_android.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ananops_android.R;

public class CheckAddActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView et_check_id;//id
    private EditText et_check_name;//巡检名
    private TextView et_device_name;//设备名
    private TextView et_device_type;//设备类型
    private TextView et_service_man;//服务商
    private TextView et_check_degree;//等级
    private TextView et_check_start_time;//开始时间
    private TextView et_check_end_time;//结束时间
    private EditText et_check_description;//描述
    private TextView choose_check_man;//选择巡检人
    private ImageView basicinfo_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_add);
        initViews();
        // initDatas();
        setOnListener();
    }
    private void initViews() {
        et_check_id=findViewById(R.id.et_check_id);//id
        et_check_name=findViewById(R.id.et_check_name);
        et_device_name=findViewById(R.id.et_device_name);
       et_device_type=findViewById(R.id.et_device_type);//设备类型
        et_service_man=findViewById(R.id.et_service_man);//服务商
       et_check_degree=findViewById(R.id. et_check_degree);//等级
        et_check_start_time=findViewById(R.id.et_check_start_time);//开始时间
         et_check_end_time=findViewById(R.id.et_check_end_time);//结束时间
         et_check_description=findViewById(R.id. et_check_description);//描述
        choose_check_man=findViewById(R.id.choose_check_man);//选择巡检人
        basicinfo_back=findViewById(R.id.basicinfo_back);
    }
    private void setOnListener() {
        et_device_name.setOnClickListener(this);
        et_check_degree.setOnClickListener(this);
        et_check_end_time.setOnClickListener(this);
        choose_check_man.setOnClickListener(this);
        basicinfo_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_device_name:
                break;
            case R.id.et_check_degree:
                break;
            case R.id. et_check_end_time:
                break;
            case R.id.choose_check_man:
                break;
            case R.id.basicinfo_back:
                showExitAlertDialog(v);
                break;
        }
    }

    public void showExitAlertDialog(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("未填完巡检计划，确认退出吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
               CheckAddActivity.this.finish();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }
}
