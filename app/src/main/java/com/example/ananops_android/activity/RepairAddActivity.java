package com.example.ananops_android.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.services.help.Tip;
import com.example.ananops_android.R;
import com.example.ananops_android.adapter.GridAdapter;
import com.example.ananops_android.datePicker.CustomDatePicker;
import com.example.ananops_android.datePicker.DateFormatUtils;
import com.example.ananops_android.db.ProjectListResponse;
import com.example.ananops_android.db.TroubleTypeAndAddressListResponse;
import com.example.ananops_android.db.UpLoadFilesResponse;
import com.example.ananops_android.entity.ProjectInfo;
import com.example.ananops_android.entity.RepairAddContent;
import com.example.ananops_android.entity.TroubleAddEntity;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.photopicker.PhotoPickerActivity;
import com.example.ananops_android.photopicker.PhotoPreviewActivity;
import com.example.ananops_android.photopicker.SelectModel;
import com.example.ananops_android.photopicker.intent.PhotoPickerIntent;
import com.example.ananops_android.photopicker.intent.PhotoPreviewIntent;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.FileUtils;
import com.example.ananops_android.util.SPUtils;

import org.json.JSONArray;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.HttpException;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import top.zibin.luban.Luban;

public class RepairAddActivity extends BaseActivity implements View.OnClickListener {
    final private RepairAddContent repairAddContent=new RepairAddContent();
    private EditText et_repair_name;
    private TextView deadline_time;
    private EditText total_cost;
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
//    private AlertDialog alertFaultAddr; //故障地点
//    private AlertDialog alertFaultType; //故障类型
//    private AlertDialog alertFaultName; //故障名称
    private Button repair_submit_pics;//上传图片
    private ProgressDialog progressDialog;
    private GridView gridView;
    private GridAdapter gridAdapter;
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private ArrayList<String> imagePaths = new ArrayList<>();//图片
    ArrayList<String> imagePathUp = new ArrayList<>();//上传
    private int projectTmp=0;
    private volatile int tmp=1;
    private String[] result = new String[4];
    private List<ProjectInfo> projectInfos = new ArrayList<>();
    private String[] projectArray;
    private List<TroubleAddEntity> troubleAddresses = new ArrayList<>();
    private String[] addressArray;//故障地址
    private int addressTmp = 0;
    private List<TroubleAddEntity> emergencyLevels = new ArrayList<>();
    private String[] emergencyLevelArray;//紧急程度
    private int emergencyLevelTmp = 0;
    private List<TroubleAddEntity> troubleLevels = new ArrayList<>();
    private String[] troubleLevelArray;//故障等级
    private int troubleLevelTmp = 0;
    private List<TroubleAddEntity> troubleTypes = new ArrayList<>();
    private String[] troubleTypeArray;//故障类型
    private int troubleTypeTmp=0;
    private List<TroubleAddEntity> deviceTypes = new ArrayList<>();
    private String[] deviceTypeArray;//设备类型
    private int deviceTypeTmp=0;
    private  Long  deviceId = new Date().getTime();
    private Context mContext;
    private static String[] PERMISSION_STORAGE_PHOTO ={
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };
    private static final int REQUEST_PLACE = 1;
    private int compressFlag = 0;
    private CustomDatePicker mTimerPicker;//时间选择
    private List<Long> attachmentIdList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_add);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mContext = this;
       // ActivityManager.getInstance().addActivity(this);
       // List<RepairAddContent.TaskItemDtoListBean>
        repairAddContent.setMdmcAddTaskItemDtoList(new ArrayList<>(Arrays.asList(new RepairAddContent.MdmcAddTaskItemDtoListBean())));
        initViews();
        initDatas();
        setOnListener();
       // if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,PERMISSION_STORAGE_PHOTO,1);
            }
     //   }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mTimerPicker!=null) {
            mTimerPicker.onDestroy();
        }
       // alertFaultAddr = null;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        String s = "";
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                s += permissions[i];
                s += " ";
            }
            Toast.makeText(mContext, "成功授权"+s, Toast.LENGTH_SHORT).show();
        }
    }

    private void setOnListener() {
        et_project_name.setOnClickListener(this);
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
        repair_submit_pics.setOnClickListener(this);
        deadline_time.setOnClickListener(this);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                    if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) {
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
                }
            }
        });
    }

    private void initViews() {
        total_cost=findViewById(R.id.et_repair_cost);
        et_repair_name = findViewById(R.id.et_repair_name);
        deadline_time = findViewById(R.id.et_deadline_time);
        et_project_name = findViewById(R.id.et_project_name);//项目名
        et_repair_person = findViewById(R.id.et_repair_person);//报修人
        repair_listid = findViewById(R.id.et_repair_facname);
        et_repair_tel = findViewById(R.id.et_repair_tel);
        repair_time = findViewById(R.id.et_repair_time);
        fault_type = findViewById(R.id.et_fault_type);
        fault_addr = findViewById(R.id.et_fault_addr);
        et_repair_address = findViewById(R.id.et_repair_address);
        fault_description = findViewById(R.id.et_fault_description);
        fault_name = findViewById(R.id.et_fault_name);
        et_fault_degree = findViewById(R.id.et_fault_degree);
        et_emergency_degree = findViewById(R.id.et_emergency_degree);
        choose_service = findViewById(R.id.choose_service);
        et_appointment_time = findViewById(R.id.et_appointment_time);
        repair_add_confirm = findViewById(R.id.repair_add_confirm);
        basicinfo_back = findViewById(R.id.basicinfo_back);
        repair_submit_pics = findViewById(R.id.repair_submit_pics);
   //     progressBar01 = findViewById((R.id.progressBar01));
    //    progressBar01.setIndeterminate(false);//不确定模式
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
        et_repair_person.setText(SPUtils.getInstance(mContext).getString("user_id","111"));
        et_repair_tel.setText("18801162442");
        //获取项目信息
        Net.instance.getProjectList(Long.valueOf(SPUtils.getInstance(mContext).getString("groupId", "1")), SPUtils.getInstance(mContext).getString("Token", " "))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ProjectListResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("ErrorGetProjectList", System.currentTimeMillis() + "");
                        e.printStackTrace();
                        Toast.makeText(mContext, "网络异常，请检查网络状态changeStatus", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(ProjectListResponse projectListResponse) {
                        if (TextUtils.equals(projectListResponse.getCode(), "200")) {
                            projectInfos.clear();
                            if (projectListResponse.getResult().size() > 0) {
                                projectArray = new String[projectListResponse.getResult().size()];
                                for (int i = 0; i < projectListResponse.getResult().size(); i++) {
                                    projectInfos.add(projectListResponse.getResult().get(i));
                                    projectArray[i] = projectListResponse.getResult().get(i).getProjectName();
                                }
                                Log.v("项目列表1", projectListResponse.getResult().get(0).getId() + "");
                            } else {
                                Toast.makeText(mContext, "无项目列表！", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(mContext, projectListResponse.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
        //获取类型和位置列表
        Net.instance.getTroubleTypeListAndAddressList(SPUtils.getInstance(mContext).getString("Token","111"))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TroubleTypeAndAddressListResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("ErrorTroubleAndAddress", System.currentTimeMillis() + "");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(TroubleTypeAndAddressListResponse troubleTypeAndAddressListResponse) {
                        if (TextUtils.equals(troubleTypeAndAddressListResponse.getCode(), "200")) {
                            troubleTypes.clear();
                            troubleAddresses.clear();
                            emergencyLevels.clear();
                            troubleLevels.clear();
                            deviceTypes.clear();
                            //故障类型列表
                            if (troubleTypeAndAddressListResponse.getResult().getTroubleTypeList().size() > 0) {
                                troubleTypeArray = new String[troubleTypeAndAddressListResponse.getResult().getTroubleTypeList().size()];
                                for (int i = 0; i <troubleTypeAndAddressListResponse.getResult().getTroubleTypeList().size(); i++) {
                                    troubleTypes.add(troubleTypeAndAddressListResponse.getResult().getTroubleTypeList().get(i));
                                    troubleTypeArray[i] = troubleTypeAndAddressListResponse.getResult().getTroubleTypeList().get(i).getName();
                                }
                                Log.v("故障列表1", troubleTypeAndAddressListResponse.getResult().getTroubleTypeList().get(0) + "");
                            } else {
                                Toast.makeText(mContext, "无故障列表！", Toast.LENGTH_LONG).show();
                            }
                            //故障位置列表
                            if (troubleTypeAndAddressListResponse.getResult().getTroubleAddressList().size() > 0) {
                                addressArray = new String[troubleTypeAndAddressListResponse.getResult().getTroubleAddressList().size()];
                                for (int i = 0; i <troubleTypeAndAddressListResponse.getResult().getTroubleAddressList().size(); i++) {
                                    troubleAddresses.add(troubleTypeAndAddressListResponse.getResult().getTroubleAddressList().get(i));
                                    addressArray[i] = troubleTypeAndAddressListResponse.getResult().getTroubleAddressList().get(i).getName();
                                }
                               // Log.v("位置列表1", troubleTypeAndAddressListResponse.getResult().getTroubleAddressList().get(0).getTroubleAddress() + "");
                            } else {
                                Toast.makeText(mContext, "无位置列表！", Toast.LENGTH_LONG).show();
                            }
                            //故障等级列表
                            if (troubleTypeAndAddressListResponse.getResult().getTroubleLevelList().size() > 0) {
                                troubleLevelArray = new String[troubleTypeAndAddressListResponse.getResult().getTroubleLevelList().size()];
                                for (int i = 0; i <troubleTypeAndAddressListResponse.getResult().getTroubleLevelList().size(); i++) {
                                    troubleLevels.add(troubleTypeAndAddressListResponse.getResult().getTroubleLevelList().get(i));
                                    troubleLevelArray[i] = troubleTypeAndAddressListResponse.getResult().getTroubleLevelList().get(i).getName();
                                }
                            //    Log.v("位置列表1", troubleTypeAndAddressListResponse.getResult().getTroubleAddressList().get(0).getTroubleAddress() + "");
                            } else {
                                Toast.makeText(mContext, "无位置列表！", Toast.LENGTH_LONG).show();
                            }
                            //紧急程度列表
                            if (troubleTypeAndAddressListResponse.getResult().getEmergencyLevelList().size() > 0) {
                                emergencyLevelArray = new String[troubleTypeAndAddressListResponse.getResult().getEmergencyLevelList().size()];
                                for (int i = 0; i <troubleTypeAndAddressListResponse.getResult().getEmergencyLevelList().size(); i++) {
                                    emergencyLevels.add(troubleTypeAndAddressListResponse.getResult().getEmergencyLevelList().get(i));
                                    emergencyLevelArray[i] = troubleTypeAndAddressListResponse.getResult().getEmergencyLevelList().get(i).getName();
                                }
                            } else {
                                Toast.makeText(mContext, "无位置列表！", Toast.LENGTH_LONG).show();
                            }
                            //设备类型列表
                            if (troubleTypeAndAddressListResponse.getResult().getDeviceTypeList().size() > 0) {
                                deviceTypeArray = new String[troubleTypeAndAddressListResponse.getResult().getDeviceTypeList().size()];
                                for (int i = 0; i <troubleTypeAndAddressListResponse.getResult().getDeviceTypeList().size(); i++) {
                                    deviceTypes.add(troubleTypeAndAddressListResponse.getResult().getDeviceTypeList().get(i));
                                    deviceTypeArray[i] = troubleTypeAndAddressListResponse.getResult().getDeviceTypeList().get(i).getName();
                                }
                            } else {
                                Toast.makeText(mContext, "无位置列表！", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(mContext, troubleTypeAndAddressListResponse.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
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
//                    repairAddContent.getMdmcAddTaskItemDtoList().get(0).setDeviceLongitude(tip.getPoint().getLongitude());
//                    repairAddContent.getMdmcAddTaskItemDtoList().get(0).setDeviceLatitude(tip.getPoint().getLatitude());
                    repairAddContent.setRequestLatitude(tip.getPoint().getLatitude());
                    repairAddContent.setRequestLongitude(tip.getPoint().getLatitude());
                    Toast.makeText(RepairAddActivity.this,"经度="+tip.getPoint().getLongitude()+"纬度="+tip.getPoint().getLatitude(),Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_project_name:
               // String[] item_project={"1", "2", "3"};
                showChooseDislog1(v,projectArray,et_project_name);
                break;
            case R.id.et_fault_type://故障类型
                showFaultTypeAlertDialog(v,"请选择故障类型",troubleTypeArray,"troubleType",fault_type) ;
                break;
            case R.id.et_fault_addr://故障地点
                showFaultTypeAlertDialog(v,"请选择故障地点",addressArray,"troubleAddress",fault_addr) ;
                break;
            case R.id.et_fault_name://设备名称
                showFaultTypeAlertDialog(v,"请选择设备名称",deviceTypeArray,"deviceType",fault_name) ;
               // showFaultNameAlertDialog(v);
                break;
            case R.id.et_fault_degree://故障等级
               // String[] item = {"p0", "p1", "其他"};
               // showChooseDislog(v,et_fault_degree,item);
                showFaultTypeAlertDialog(v,"请选择故障等级",troubleLevelArray,"troubleLevel",et_fault_degree) ;
                break;
            case R.id.et_emergency_degree://紧急程度
//                String[] item1 = {"紧急", "中等", "一般"};
//                showChooseDislog(v,et_emergency_degree,item1);
                showFaultTypeAlertDialog(v,"请选择紧急程度",emergencyLevelArray,"emergencyLevel",et_emergency_degree) ;
                break;
            case R.id.choose_service:
//                String[] item2 = {"1", "2", "3"};
//                showChooseDislog(v,choose_service,item2);
                break;
            case R.id.basicinfo_back:
                showExitAlertDialog(v);
                break;
            case R.id.et_appointment_time:
                String endTime = "2025-12-31 23:59:00";
                String beginTime = BaseUtils.getInstence().getTime();
                mTimerPicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
                    @Override
                    public void onTimeSelected(long timestamp) {
                        et_appointment_time.setText(DateFormatUtils.long2Str(timestamp, true));
                    }
                }, beginTime, endTime);
                // 允许点击屏幕或物理返回键关闭
                mTimerPicker.setCancelable(true);
                // 显示时和分
                mTimerPicker.setCanShowPreciseTime(true);
                // 允许循环滚动
                mTimerPicker.setScrollLoop(true);
                // 允许滚动动画
                mTimerPicker.setCanShowAnim(true);
                mTimerPicker.show(beginTime);
                break;
            case R.id.et_deadline_time:
                String endTime1 = "2025-12-31 23:59:00";
                String beginTime1 = BaseUtils.getInstence().getTime();
                mTimerPicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
                    @Override
                    public void onTimeSelected(long timestamp) {
                        deadline_time.setText(DateFormatUtils.long2Str(timestamp, true));
                    }
                }, beginTime1, endTime1);
                // 允许点击屏幕或物理返回键关闭
                mTimerPicker.setCancelable(true);
                // 显示时和分
                mTimerPicker.setCanShowPreciseTime(true);
                // 允许循环滚动
                mTimerPicker.setScrollLoop(true);
                // 允许滚动动画
                mTimerPicker.setCanShowAnim(true);
                mTimerPicker.show(beginTime1);
                break;
            case R.id.et_repair_address:
                Intent intent = new Intent(RepairAddActivity.this, AddressSearchActivity.class);
                startActivityForResult(intent, REQUEST_PLACE);
                break;
            case R.id.repair_add_confirm:
                addRepairSubmit();
                break;
            case R.id.repair_submit_pics:
              // repairPicsSubmit();
                subPics();
                break;
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
        imagePaths.addAll(paths);
        imagePaths.add("paizhao");
        imagePathUp.clear();
        for (int i = 0; i < imagePaths.size(); i++) {
            String s = imagePaths.get(i);
            if (s.equals("paizhao")) {
                //continue;
            }else {
                imagePathUp.add(s);
            }
        }
       // thread.start();
        gridAdapter  = new GridAdapter(this,imagePaths);
        gridView.setAdapter(gridAdapter);
        try{
            JSONArray obj = new JSONArray(imagePaths);
            Log.d("imagePaths", obj+"");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void showFaultTypeAlertDialog(View view,String title,String[] strings,String tmpCode,TextView textView){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle(title)
                .setSingleChoiceItems(strings, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
             //  Toast.makeText(RepairAddActivity.this,troubleTypeArray[i],Toast.LENGTH_SHORT).show();
             //  tmp=troubleTypeArray[i];
                if(tmpCode.equals("troubleAddress")){//故障位置
                    addressTmp = i;
                } else if (tmpCode.equals("emergencyLevel")){//紧急程度
                    emergencyLevelTmp = i;
                } else if (tmpCode.equals("troubleLevel")) {///故障等级
                    troubleLevelTmp = i;
                } else if (tmpCode.equals("troubleType")) {
                    troubleTypeTmp = i;
                } else if (tmpCode.equals("deviceType")) {
                    deviceTypeTmp = i;
                }
                textView.setText(strings[i]);
               // alertFaultType.dismiss();
                dialogInterface.dismiss();
            }
        })
        .create()
        .show();
//        alertFaultType = alertBuilder.create();
//        alertFaultType.show();
    }
    public void showChooseDislog1(View view, final String[] item, final TextView textView) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择")
                .setSingleChoiceItems(item, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView.setText(item[which]);
                        Log.v("projectName", item[which]);
                        projectTmp = which;
                        if(projectInfos.size()>projectTmp){
                            repair_listid.setText(projectInfos.get(projectTmp).getPartyAName());
                            et_repair_person.setText(projectInfos.get(projectTmp).getAoneName());
                            et_repair_tel.setText(projectInfos.get(projectTmp).getAleaderTel());
                        }
                        else {
                            repair_listid.setText("");
                            et_repair_person.setText("");
                            et_repair_tel.setText("");
                        }
                        et_project_name.setText(projectInfos.get(projectTmp).getProjectName());
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
    private void showProgressBar(int tmp) {
        progressDialog=ProgressDialog.show(this,"提示","正在上传图片...",false);
        progressDialog.setCanceledOnTouchOutside(true);
    }
    private void hideProgressBar() {
        progressDialog.dismiss();
    }
    private void repairPicsSubmit() {
        if (compressFlag != 1) {
            Toast.makeText(mContext, "请稍后再次点击图片提交按钮", Toast.LENGTH_SHORT).show();
           // hideProgressBar();
        } else {
            subPics();
        }
    }

    private void compressWithRx(File file) {

        Luban.get(this)
                .load(file) //加载图片
                 .putGear(Luban.THIRD_GEAR)  //设置压缩等级
                .asObservable()     //返回一个Obsetvable观察者对象
                .subscribeOn(Schedulers.io())   //压缩指定IO线程
                .observeOn(AndroidSchedulers.mainThread())  //回调返回主线程
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {     //运行异常回调
                        throwable.printStackTrace();
                    }
                })
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends File>>() {
                    @Override
                    public Observable<? extends File> call(Throwable throwable) {   //异常处理
                        return Observable.empty();
                    }
                })
                .subscribe(new Action1<File>() {
                    @Override
                    public void call(File file) {
                        upLoadPicSingle(file);
                    }
                });
    }
    private void upLoadPicSingle(File file) {
        String string = file.getAbsolutePath();
        String type = string.substring(string.lastIndexOf(".") + 1);
        Log.d("imgType", type);
        //  客户端这样写 application/octet-stream，  服务器才认识，  规则
        //   RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), tempFile);
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/" + type), file);
        Log.d("RequestBody", requestFile+"");
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", string, requestFile);
        Net.instance.upLoadFiles(type, "mdmcTaskAndroid", "ananops", body, SPUtils.getInstance(mContext).getString("Token", ""), deviceId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<UpLoadFilesResponse>>() {
                    @Override
                    public void onCompleted() {
                        Log.v("上传","第"+tmp+"张照片");
                     //   hideProgressBar();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("UploadTime", System.currentTimeMillis() + "");
                        //  e.printStackTrace();
                        attachmentIdList.clear();
                        Toast.makeText(mContext, "提交失败,请重新上传图片", Toast.LENGTH_SHORT).show();
                        if (e instanceof HttpException) {
                            HttpException httpException = (HttpException) e;
                            try {
                                String error = httpException.response().errorBody().string();
                                Log.e("InspectionUpError", error);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                    @Override
                    public void onNext(List<UpLoadFilesResponse> upLoadFilesResponses) {
                        hideProgressBar();
                        Log.v("upLoadFilesResponses", upLoadFilesResponses + "");
                        if (upLoadFilesResponses.size() > 0) {
                            Toast.makeText(mContext, "提交照片成功！", Toast.LENGTH_SHORT).show();
                            // BaseUtils.getInstence().intent(mContext,UserMainActivity.class);
                            Long s = upLoadFilesResponses.get(0).getAttachmentId();
                            String url =upLoadFilesResponses.get(0).getAttachmentUrl().replace("\\","");
                            Log.i("AttachmentUrl",url);
                            //strings[i] = upLoadFilesResponse.getAttachmentId();
                            attachmentIdList.add(s);
                            Log.i("AttachmentLists", attachmentIdList+"");
                            Log.i("tmp", tmp+"");
                            FileUtils.getInstance().deleteCacheFile(file);
                            if (tmp == imagePathUp.size()) {
                                hideProgressBar();
                                tmp=1;
                            }else {
                                tmp++;
                                Log.v("tmp+", tmp+"");
                            }
                        }

                    }
                });
    }
    private String setLuBanPath() {
        String path = Environment.getExternalStorageDirectory() + "/MyLuBan/image/";
        File file = new File(path);
        if (file.mkdirs()) {
            return path;
        }
        return path;
    }

    //        imagePathUp.clear();
//        imagePathUp.addAll(imagePaths);
//        if (imagePathUp.contains("paizhao")) {
//            imagePathUp.remove("paizhao");
//            Log.d("imagePathUp", imagePathUp+"");
//        }
private void subPics(){
        attachmentIdList.clear();
        if (imagePathUp.size() > 0) {
            showProgressBar(tmp);
            Log.d("imagePathUp上传", imagePathUp + "");
            for (int i = 0; i < imagePathUp.size(); i++) {
                int num = i+1;
                Log.i("第num=",num+"");

                String string = imagePathUp.get(i);
                File file = new File(string);
                compressWithRx(file);
            }
        }
            else{
                Toast.makeText(mContext, "无图片上传！", Toast.LENGTH_SHORT).show();
            }
         //   hideProgressBar();
        }
    private void addRepairSubmit(){
       if(judgeRepair()) {
           repairAddContent.setAttachmentIdList(attachmentIdList);
           repairAddContent.setAppointTime(et_appointment_time.getText().toString().trim());//
           repairAddContent.setContractId(1L);//
           repairAddContent.setAddressName(et_repair_address.getText().toString().trim());//
           repairAddContent.setFacilitatorId(projectInfos.get(projectTmp).getBleaderId());//
           repairAddContent.setId(null);//
           repairAddContent.setCall(et_repair_tel.getText().toString().trim());
           repairAddContent.setObjectId(null);
           repairAddContent.setObjectType(1);
           repairAddContent.setLevel(emergencyLevels.get(emergencyLevelTmp).getCode());//
           repairAddContent.setPrincipalId(projectInfos.get(projectTmp).getAleaderId());//
           repairAddContent.setProjectId(projectInfos.get(projectTmp).getId());//
           repairAddContent.setStatus(0);//
           repairAddContent.setSuggestion("");//
           repairAddContent.setTitle(et_repair_name.getText().toString().trim());//
           if (!TextUtils.isEmpty(total_cost.getText().toString().trim())) {
               repairAddContent.setTotalCost(Integer.parseInt(total_cost.getText().toString().trim()));
           }
           repairAddContent.setUserId(Long.valueOf(SPUtils.getInstance(mContext).getString("user_id", "111")));//
           repairAddContent.getMdmcAddTaskItemDtoList().get(0).setDescription(fault_description.getText().toString().trim());
           repairAddContent.getMdmcAddTaskItemDtoList().get(0).setDeviceId(deviceTypes.get(deviceTypeTmp).getId());//
           repairAddContent.getMdmcAddTaskItemDtoList().get(0).setDeviceType(fault_type.getText().toString().trim());//设备类型
           repairAddContent.getMdmcAddTaskItemDtoList().get(0).setDeviceLatitude(troubleAddresses.get(addressTmp).getLatitude());
           repairAddContent.getMdmcAddTaskItemDtoList().get(0).setDeviceLongitude(troubleAddresses.get(addressTmp).getLongitude());
           repairAddContent.getMdmcAddTaskItemDtoList().get(0).setLevel(troubleLevels.get(troubleLevelTmp).getCode());
           repairAddContent.getMdmcAddTaskItemDtoList().get(0).setId(null);
           repairAddContent.getMdmcAddTaskItemDtoList().get(0).setTaskId(0L);
           repairAddContent.getMdmcAddTaskItemDtoList().get(0).setTroubleType(troubleTypes.get(troubleTypeTmp).getCode());//故障类型
           Log.v("repairAddContent", repairAddContent + "");
           BaseUtils.getInstence().repairAdd(repairAddContent, mContext);
//           BaseUtils.getInstence().intent(mContext,UserMainActivity.class);
//           Intent intent = new Intent(mContext,UserMainActivity.class);
//           finish();
//           mContext.startActivity(intent);
       }
    }
    private boolean judgeRepair(){
        if (TextUtils.isEmpty(et_appointment_time.getText().toString().trim())) {
            Toast.makeText(this,"请选择预约时间", Toast.LENGTH_SHORT).show();
            return false;
        }else if(TextUtils.isEmpty(deadline_time.getText().toString().trim())) {
            Toast.makeText(this,"请选择最晚维修时间", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(et_repair_tel.getText().toString().trim())) {
            Toast.makeText(this,"请填写联系方式", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(et_project_name.getText().toString().trim())) {
            Toast.makeText(this,"请选择项目", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(et_repair_address.getText().toString().trim())) {
            Toast.makeText(this,"请选择维修定位地点", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(et_emergency_degree.getText().toString().trim())) {
            Toast.makeText(this,"请选择紧急程度", Toast.LENGTH_SHORT).show();
            return false;
        }else if (TextUtils.isEmpty(fault_type.getText().toString().trim())) {
            Toast.makeText(this,"请选择故障类型", Toast.LENGTH_SHORT).show();
            return false;
        }else if (TextUtils.isEmpty(et_fault_degree.getText().toString().trim())) {
            Toast.makeText(this,"请选择故障等级", Toast.LENGTH_SHORT).show();
            return false;
        }else if (TextUtils.isEmpty(fault_addr.getText().toString().trim())) {
            Toast.makeText(this,"请选择故障位置", Toast.LENGTH_SHORT).show();
            return false;
        }else if(TextUtils.isEmpty(et_repair_name.getText().toString().trim())) {
            Toast.makeText(this,"请填写维修名称", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private int changeLevel(String string) {
        int level;
        if (string.equals("紧急")) {
            level = 0;
            return level;
        } else if (string.equals("中等")) {
            level = 1;
            return level;
        } else if (string.equals("一般")) {
            level = 2;
            return level;
        } else {
            level = 0;
            return level;
        }
    }
}
