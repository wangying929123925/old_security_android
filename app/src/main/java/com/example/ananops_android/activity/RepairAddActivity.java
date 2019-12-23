package com.example.ananops_android.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.services.help.Tip;
import com.example.ananops_android.Interface.ConfirmDialogInterface;
import com.example.ananops_android.R;
import com.example.ananops_android.adapter.GridAdapter;
import com.example.ananops_android.db.PostResponse;
import com.example.ananops_android.db.UserInfo;
import com.example.ananops_android.entity.RepairAddContent;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.photopicker.PhotoPickerActivity;
import com.example.ananops_android.photopicker.PhotoPreviewActivity;
import com.example.ananops_android.photopicker.SelectModel;
import com.example.ananops_android.photopicker.intent.PhotoPickerIntent;
import com.example.ananops_android.photopicker.intent.PhotoPreviewIntent;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.SPUtils;

import org.json.JSONArray;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class RepairAddActivity extends AppCompatActivity implements View.OnClickListener {
    final private RepairAddContent repairAddContent=new RepairAddContent();
    private TextView et_project_name;//项目名
    private TextView et_repair_person;//报修人
    private TextView repair_listid;//编号
    private EditText et_repair_tel;//电话
    private TextView repair_time;//时间
    private TextView fault_type;//类型
    private TextView fault_addr;//位置
    private TextView et_repair_address;//定位
    private EditText fault_description;//描述
    private TextView fault_name;//故障名称
    private TextView et_fault_degree;//等级
    private TextView et_emergency_degree;//紧急程度
    private TextView et_appointment_time;//预约时间
    private TextView choose_service;//选择服务商
    private TextView repair_add_confirm;//提交
    private ImageView basicinfo_back;
    private AlertDialog alertFaultAddr; //故障地点
    private AlertDialog alertFaultType; //故障类型
    private AlertDialog alertFaultName; //故障名称
    private GridView gridView;
    private GridAdapter gridAdapter;
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private ArrayList<String> imagePaths = new ArrayList<>();//图片
    private String tmp="";
    private String[] result = new String[4];
    private Context mContext;
    private static final int REQUEST_PLACE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_add);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mContext = this;
       // List<RepairAddContent.TaskItemDtoListBean>
        repairAddContent.setTaskItemDtoList(new ArrayList<>(Arrays.asList(new RepairAddContent.TaskItemDtoListBean())));
        initViews();
        initDatas();
        setOnListener();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void setOnListener() {
        fault_type.setOnClickListener(this);
        fault_addr.setOnClickListener(this);
        fault_name.setOnClickListener(this);
        basicinfo_back.setOnClickListener(this);
        et_appointment_time.setOnClickListener(this);
        et_repair_address.setOnClickListener(this);
        repair_add_confirm.setOnClickListener(this);
        et_emergency_degree.setOnClickListener(this);
        et_fault_degree.setOnClickListener(this);
         choose_service.setOnClickListener(this);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String imgs = (String) parent.getItemAtPosition(position);
                if ("paizhao".equals(imgs) ){
                    PhotoPickerIntent intent = new PhotoPickerIntent(RepairAddActivity.this);
                    intent.setSelectModel(SelectModel.MULTI);
                    intent.setShowCarema(true); // 是否显示拍照
                    intent.setMaxTotal(6); // 最多选择照片数量，默认为6
                    intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
                    startActivityForResult(intent, REQUEST_CAMERA_CODE);
                }else{
                    Toast.makeText(RepairAddActivity.this,"1"+position,Toast.LENGTH_SHORT).show();
                    PhotoPreviewIntent intent = new PhotoPreviewIntent(RepairAddActivity.this);
                    intent.setCurrentItem(position);
                    intent.setPhotoPaths(imagePaths);
                    startActivityForResult(intent, REQUEST_PREVIEW_CODE);
                }
            }
        });
    }

    private void initViews() {
        et_project_name=findViewById(R.id.et_project_name);//项目名
        et_repair_person=findViewById(R.id.et_repair_person);//报修人
        repair_listid=findViewById(R.id.et_repair_listid);
        et_repair_tel=findViewById(R.id.et_repair_tel);
        repair_time=findViewById(R.id.et_repair_time);
        fault_type=findViewById(R.id.et_fault_type);
        fault_addr=findViewById(R.id.et_fault_addr);
        et_repair_address=findViewById(R.id.et_repair_address);
        fault_description=findViewById(R.id.et_fault_description);
        fault_name=findViewById(R.id.et_fault_name);
        et_fault_degree=findViewById(R.id.et_fault_degree);
        et_emergency_degree=findViewById(R.id.et_emergency_degree);
        choose_service=findViewById(R.id. choose_service);
        et_appointment_time = findViewById(R.id.et_appointment_time);
        repair_add_confirm=findViewById(R.id.repair_add_confirm);
        basicinfo_back=findViewById(R.id.basicinfo_back);
        repair_time.setText(BaseUtils.getInstence().getTime());
        gridView = (GridView) findViewById(R.id.gridView_photo);
        int cols = getResources().getDisplayMetrics().widthPixels / getResources().getDisplayMetrics().densityDpi;
        cols = cols < 3 ? 3 : cols;
        gridView.setNumColumns(cols);//拍照图片
        imagePaths.add("paizhao");
        gridAdapter = new GridAdapter(this,imagePaths);
        gridView.setAdapter(gridAdapter);
      //  take_photo=findViewById(R.id.et_fault_photo);
    }
    private void initDatas() {
        et_repair_person.setText("111");
        et_repair_tel.setText("18801162442");
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case REQUEST_CAMERA_CODE:
                    ArrayList<String> list = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                    Log.d(RepairAddActivity.class.getSimpleName(), "数量："+list.size());
                    loadAdpater(list);
                    break;
                // 预览
                case REQUEST_PREVIEW_CODE:
                    ArrayList<String> ListExtra = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
                    loadAdpater(ListExtra);
                    break;
            }
        }
        if (requestCode == REQUEST_PLACE) {
            if (resultCode == AddressSearchActivity.RESULT_CODE_INPUTTIPS && data != null) {
                final Tip tip = data.getParcelableExtra("tip");
                if (tip.getName() != null) {
                    et_repair_address.setText(tip.getName());
                  //  repairAddContent.getTaskItemDto().setAddress_name(tip.getName());
                    repairAddContent.getTaskItemDtoList().get(0).setDeviceLongitude(tip.getPoint().getLongitude());
                    repairAddContent.getTaskItemDtoList().get(0).setDeviceLatitude(tip.getPoint().getLatitude());
                    Toast.makeText(RepairAddActivity.this,"经度="+tip.getPoint().getLongitude()+"纬度="+tip.getPoint().getLatitude(),Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_project_name:
                String[] item_project={"1", "2", "3"};
                showChooseDislog(v,et_project_name,item_project);
                break;
            case R.id.et_fault_type:
                showFaultTypeAlertDialog(v) ;
                break;
            case R.id.et_fault_addr:
                showFaultAddrAlertDialog(v);
                break;
            case R.id.et_fault_name:
                showFaultNameAlertDialog(v);
                break;
            case R.id.et_fault_degree:
                String[] item={"一级", "二级", "三级"};
                showChooseDislog(v,et_fault_degree,item);
                break;
            case R.id.et_emergency_degree:
                String[] item1={"1", "2", "3"};
                showChooseDislog(v,et_emergency_degree,item1);
                break;
            case R.id.choose_service:
                String[] item2={"1", "2", "3"};
                showChooseDislog(v,choose_service,item2);
            case R.id.basicinfo_back:
                showExitAlertDialog(v);
                break;
            case R.id.et_appointment_time:
                BaseUtils.showConfirmDialog(result,mContext,"请选择具体报修的时间", new ConfirmDialogInterface() {
                    @Override
                    public void onConfirmClickListener() {
//                        Toast.makeText(RepairAddActivity.this, "queding", Toast.LENGTH_SHORT).show();
                        et_appointment_time.setText(result[0]+"-"+result[1]+"-"+result[2]+"-"+result[3]);
                    }

                    @Override
                    public void onCancelClickListener() {
//                        Toast.makeText(RepairAddActivity.this, "quxiao", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.et_repair_address:
                Intent intent=new Intent(RepairAddActivity.this,AddressSearchActivity.class);
                startActivityForResult(intent, REQUEST_PLACE);
                break;
            case R.id.repair_add_confirm:
                addRepairSubmit();
            default:
                break;
        }
    }

    private void loadAdpater(ArrayList<String> paths){
        if (imagePaths!=null&& imagePaths.size()>0){
            imagePaths.clear();
        }
        if (paths.contains("paizhao")){
            paths.remove("paizhao");
        }
        paths.add("paizhao");
        imagePaths.addAll(paths);
        gridAdapter  = new GridAdapter(this,imagePaths);
        gridView.setAdapter(gridAdapter);
        try{
            JSONArray obj = new JSONArray(imagePaths);
        }catch (Exception e){
            e.printStackTrace();
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
        final String[] items = {"大门","大厅","现金柜台","非现金柜台","自助银行","办公区","网络机房","监控机房","其他","自助银行","办公区","网络机房","监控机房","其他"};
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
    public void showChooseDislog(View view, final TextView textView, final String[] item){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("请选择")
                .setSingleChoiceItems(item, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView.setText(item[which]);
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }
    public void showExitAlertDialog(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("未填完工单，确认退出吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                RepairAddActivity.this.finish();
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
    private void addRepairSubmit(){
//        repairAddContent.setProjectId(et_project_name.getText().toString().trim());
//        repairAddContent.setUserId(et_repair_person.getText().toString().trim());
//        repairAddContent.setCreator_call(et_repair_tel.getText().toString().trim());
//        repairAddContent.setCreated_time(repair_time.getText().toString().trim());
//        repairAddContent.setFacilitatorId(choose_service.getText().toString().trim());
//        repairAddContent.setAppoint_time(et_appointment_time.getText().toString().trim());
//        repairAddContent.setLevel(et_emergency_degree.getText().toString().trim());
//        repairAddContent.getTaskItemDtoList().get(0).setDeviceId(fault_name.getText().toString().trim());
//        repairAddContent.getTaskItemDtoList().get(0).setLevel(et_fault_degree.getText().toString().trim());
//        repairAddContent.getTaskItemDtoList().get(0).setDescription(fault_description.getText().toString().trim());
        repairAddContent.setAddressName("");
        repairAddContent.setAppoint_time("");
        repairAddContent.setContractId("");
        repairAddContent.setCreated_time("");
        repairAddContent.setCreator("");
        repairAddContent.setCreator_call("");
        repairAddContent.setFacilitatorId("");
        repairAddContent.setLevel("");
        repairAddContent.setPayMode(0);
        repairAddContent.setPrincipalId(2);
        repairAddContent.setProjectId("");
        repairAddContent.setStatus(0);
        repairAddContent.setTitle("");
        repairAddContent.setTotalCost(2);
        repairAddContent.setUserId("");
        repairAddContent.setUid("");
        repairAddContent.getTaskItemDtoList().get(0).setDescription("");
        repairAddContent.getTaskItemDtoList().get(0).setDeviceId("");
        repairAddContent.getTaskItemDtoList().get(0).setDeviceName("");
        repairAddContent.getTaskItemDtoList().get(0).setLevel("");
        repairAddContent.getTaskItemDtoList().get(0).setPhoto_url("");
        repairAddContent.getTaskItemDtoList().get(0).setTrouble_type("");
        repairAddContent.getTaskItemDtoList().get(0).setTaskId("");
       // Net.instance.repairAddPost(repairAddContent, SPUtils.getInstance().getString("Token",""))
        Net.instance1.repairAddPost(repairAddContent, UserInfo.TOKEN)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PostResponse>(){
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.v("LoginTime", System.currentTimeMillis() + "");
                e.printStackTrace();
                Toast.makeText(RepairAddActivity.this, "网络异常，请检查网络状态后登陆", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNext(PostResponse postResponse) {
                if(TextUtils.equals(postResponse.getCode(),"200")){
                Toast.makeText(RepairAddActivity.this,"提交成功！",Toast.LENGTH_SHORT).show();
                BaseUtils.getInstence().intent(RepairAddActivity.this,UserMainActivity.class);
                }
                else{
                    Toast.makeText(RepairAddActivity.this,"提交失败！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
