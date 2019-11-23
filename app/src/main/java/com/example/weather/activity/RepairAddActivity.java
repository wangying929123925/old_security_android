package com.example.weather.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weather.R;


public class RepairAddActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText repair_listid;
    private EditText repair_time;
    private TextView fault_type;
    private TextView fault_addr;
    private EditText fault_description;
    private TextView fault_name;
    private ImageView take_photo;
    private ImageView choose_fault_type;
    private ImageView choose_fault_addr;
    private ImageView choose_fault_name;
    private AlertDialog alertFaultAddr; //故障地点
    private AlertDialog alertFaultType; //故障类型
    private AlertDialog alertFaultName; //故障名称
    private String tmp="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_add);
        initViews();
       // initDatas();
        setOnListener();

    }

    private void setOnListener() {
        fault_type.setOnClickListener(this);
        fault_addr.setOnClickListener(this);
        fault_name.setOnClickListener(this);
    }

    private void initViews() {
        repair_listid=findViewById(R.id.et_repair_listid);
        repair_time=findViewById(R.id.et_repair_time);
        fault_type=findViewById(R.id.et_fault_type);
        fault_addr=findViewById(R.id.et_fault_addr);
        fault_description=findViewById(R.id.et_fault_description);
        fault_name=findViewById(R.id.et_fault_name);
        take_photo=findViewById(R.id.et_fault_photo);

    }
    private void initDatas() {
        String repair_listid1 = repair_listid.getText().toString().trim();
        String repair_time1=repair_time.getText().toString().trim();
        String fualt_type1=fault_type.getText().toString().trim();
        String fualt_addr1=fault_addr.getText().toString().trim();
        String  fault_description1=fault_description.getText().toString().trim();
        String fault_name1=fault_name.getText().toString().trim();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_fault_type:
                showFaultTypeAlertDialog(v) ;
                break;
            case R.id.et_fault_addr:
                showFaultAddrAlertDialog(v);
                break;
            case R.id.et_fault_name:
                showFaultNameAlertDialog(v);
                break;
        }
    }
    public void showFaultTypeAlertDialog(View view){
        final String[] items = {"视频系统", "报警系统", "门禁系统"};
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("请选择故障类型");
        alertBuilder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               Toast.makeText(RepairAddActivity.this,items[i],Toast.LENGTH_SHORT).show();
               tmp=items[i];
                fault_type.setText(items[i]);
            }
        });

        alertBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                fault_type.setText(tmp);
                alertFaultType.dismiss();
            }
        });

        alertBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                fault_type.setText("");
                alertFaultType.dismiss();
            }
        });

        alertFaultType = alertBuilder.create();
        alertFaultType.show();
    }
    public void showFaultAddrAlertDialog(View view){
        final String[] items = {"大门","大厅","现金柜台","非现金柜台","自助银行","办公区","网络机房","监控机房","其他"};
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("请选择故障位置");
        alertBuilder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(RepairAddActivity.this,items[i],Toast.LENGTH_SHORT).show();
                tmp=items[i];
                fault_addr.setText(items[i]);
            }
        });

        alertBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               fault_addr.setText(tmp);
                alertFaultAddr.dismiss();
            }
        });

        alertBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                fault_addr.setText("");
                alertFaultAddr.dismiss();
            }
        });

        alertFaultAddr = alertBuilder.create();
        alertFaultAddr.show();
    }
    public void showFaultNameAlertDialog(View view){
        final String[] items = {"摄像机故障","监视器故障","硬盘录像机故障","拾音器故障","报警系统故障","门禁系统故障"};

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("请选择故障位置");
        alertBuilder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(RepairAddActivity.this,items[i],Toast.LENGTH_SHORT).show();
                fault_name.setText(items[i]);
                tmp=items[i];
            }
        });

        alertBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               fault_name.setText(tmp);
                alertFaultName.dismiss();
            }
        });

        alertBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                fault_name.setText("");
                alertFaultName.dismiss();
            }
        });

        alertFaultName = alertBuilder.create();
        alertFaultName.show();
    }
}
