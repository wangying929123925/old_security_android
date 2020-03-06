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
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.services.help.Tip;
import com.example.ananops_android.Interface.ConfirmDialogInterface;
import com.example.ananops_android.R;
import com.example.ananops_android.adapter.GridAdapter;
import com.example.ananops_android.db.ProjectListResponse;
import com.example.ananops_android.db.TroubleTypeAndAddressListResponse;
import com.example.ananops_android.db.UpLoadFilesResponse;
import com.example.ananops_android.entity.ProjectInfo;
import com.example.ananops_android.entity.RepairAddContent;
import com.example.ananops_android.entity.TroubleAddress;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.photopicker.PhotoPickerActivity;
import com.example.ananops_android.photopicker.PhotoPreviewActivity;
import com.example.ananops_android.photopicker.SelectModel;
import com.example.ananops_android.photopicker.intent.PhotoPickerIntent;
import com.example.ananops_android.photopicker.intent.PhotoPreviewIntent;
import com.example.ananops_android.util.ActivityManager;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.FileUtils;
import com.example.ananops_android.util.SPUtils;

import org.json.JSONArray;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.HttpException;
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
    private Button repair_submit_pics;//上传图片
    private ProgressBar progressBar01;
    private ProgressDialog progressDialog;
    private GridView gridView;
    private GridAdapter gridAdapter;
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private ArrayList<String> imagePaths = new ArrayList<>();//图片
    ArrayList<String> imagePathUp = new ArrayList<>();//上传
    private int projectTmp=0;
    private String tmp="";
    private String[] result = new String[4];
    private List<ProjectInfo> projectInfos = new ArrayList<>();
    private String[] projectArray;
    private String[] addressArray;
    private List<TroubleAddress> troubleAddresses = new ArrayList<>();
    private int addressTmp=0;
    private String[] troubleTypeArray;
    private int troubleTypeTmp=0;
    private Context mContext;

    private static String[] PERMISSION_STORAGE_PHOTO ={
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    private static final int REQUEST_PLACE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_add);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mContext = this;
        ActivityManager.getInstance().addActivity(this);
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
        progressBar01 = findViewById((R.id.progressBar01));
        progressBar01.setIndeterminate(false);//不确定模式
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
        et_repair_person.setText(SPUtils.getInstance().getString("user_id","111"));
        et_repair_tel.setText("18801162442");
        //获取项目信息
        Net.instance.getProjectList(4L, SPUtils.getInstance().getString("Token", " "))
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
        Net.instance.getTroubleTypeListAndAddressList(Long.valueOf(SPUtils.getInstance().getString("user_id","111")),SPUtils.getInstance().getString("Token","111"))
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
                           //故障类型列表
                            troubleAddresses.clear();
                            if (troubleTypeAndAddressListResponse.getResult().getTroubleTypeList().size() > 0) {
                                troubleTypeArray = new String[troubleTypeAndAddressListResponse.getResult().getTroubleTypeList().size()];
                                for (int i = 0; i <troubleTypeAndAddressListResponse.getResult().getTroubleTypeList().size(); i++) {
                                    troubleTypeArray[i] = troubleTypeAndAddressListResponse.getResult().getTroubleTypeList().get(i);
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
                                    addressArray[i] = troubleTypeAndAddressListResponse.getResult().getTroubleAddressList().get(i).getTroubleAddress();
                                }
                                Log.v("位置列表1", troubleTypeAndAddressListResponse.getResult().getTroubleAddressList().get(0).getTroubleAddress() + "");
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
                String[] item = {"p0", "p1", "其他"};
                showChooseDislog(v,et_fault_degree,item);
                break;
            case R.id.et_emergency_degree:
                String[] item1 = {"紧急", "中等", "一般"};
                showChooseDislog(v,et_emergency_degree,item1);
                break;
            case R.id.choose_service:
                String[] item2 = {"1", "2", "3"};
                showChooseDislog(v,choose_service,item2);
                break;
            case R.id.basicinfo_back:
                showExitAlertDialog(v);
                break;
            case R.id.et_appointment_time:
                BaseUtils.showConfirmDialog(result,mContext,"请选择具体报修的时间", new ConfirmDialogInterface() {
                    @Override
                    public void onConfirmClickListener() {
//                        Toast.makeText(RepairAddActivity.this, "queding", Toast.LENGTH_SHORT).show();
                        et_appointment_time.setText(result[0] + "-" + result[1] + "-" + result[2] + " " + result[3]);
                    }

                    @Override
                    public void onCancelClickListener() {
//                        Toast.makeText(RepairAddActivity.this, "quxiao", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.et_repair_address:
                Intent intent = new Intent(RepairAddActivity.this, AddressSearchActivity.class);
                startActivityForResult(intent, REQUEST_PLACE);
                break;
            case R.id.repair_add_confirm:
                addRepairSubmit();
                break;
            case R.id.repair_submit_pics:
               repairPicsSubmit();
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
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        gridAdapter  = new GridAdapter(this,imagePaths);
        gridView.setAdapter(gridAdapter);
        try{
            JSONArray obj = new JSONArray(imagePaths);
            Log.d("imagePaths", obj+"");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void showFaultTypeAlertDialog(View view){
       // final String[] items = {"视频系统", "报警系统", "门禁系统"};
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("请选择故障类型");
        alertBuilder.setSingleChoiceItems(troubleTypeArray, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               Toast.makeText(RepairAddActivity.this,troubleTypeArray[i],Toast.LENGTH_SHORT).show();
               tmp=troubleTypeArray[i];
               troubleTypeTmp=i;
               fault_type.setText(troubleTypeArray[i]);
                alertFaultType.dismiss();
            }
        });

        alertFaultType = alertBuilder.create();
        alertFaultType.show();
    }
    public void showFaultAddrAlertDialog(View view){
        //final String[] items = {"大门","大厅","现金柜台","非现金柜台","自助银行","办公区","网络机房","监控机房","其他","自助银行","办公区","网络机房","监控机房","其他"};
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("请选择故障位置");
        alertBuilder.setSingleChoiceItems(addressArray, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(RepairAddActivity.this,addressArray[i],Toast.LENGTH_SHORT).show();
                addressTmp = i;
                fault_addr.setText(addressArray[i]);
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
    private void showProgressBar() {
        progressDialog=ProgressDialog.show(this,"提示","上传中",false);
    }
    private void hideProgressBar() {
        progressDialog.dismiss();
    }
    private void repairPicsSubmit() {
        showProgressBar();
        imagePathUp.clear();
        imagePathUp.addAll(imagePaths);
        if (imagePathUp.contains("paizhao")) {
            imagePathUp.remove("paizhao");
            Log.d("imagePathUp", imagePathUp+"");
        }
        Long deviceId = new Date().getTime();
        if (imagePathUp.size() > 0) {
            Log.d("imagePaths", imagePaths + "");
            for (int i = 0; i < imagePathUp.size(); i++) {
                String string = imagePathUp.get(i);
                File file = new File(string);
                long fileSize = FileUtils.getInstance().getFileSize(file);
                if (fileSize > 1048576L) {
                           string = FileUtils.getInstance().compressReSave(string, mContext, 100);
                           file = new File(string);
                       }
                String type = string.substring(string.lastIndexOf(".") + 1);
                Log.d("imgType", type);
                RequestBody requestFile = RequestBody.create(MediaType.parse("image/" + type), file);
                Log.d("RequestBody", requestFile+"");
                MultipartBody.Part body = MultipartBody.Part.createFormData("file", string, requestFile);
                Net.instance.upLoadFiles(type, "mdmcTaskAndroid", "ananops", body, SPUtils.getInstance().getString("Token", ""), deviceId)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<UpLoadFilesResponse>>() {
                            @Override
                            public void onCompleted() {
                                FileUtils.getInstance().deleteCacheFile();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.v("UploadTime", System.currentTimeMillis() + "");
                                //  e.printStackTrace();
                                Toast.makeText(mContext, "提交失败", Toast.LENGTH_SHORT).show();
                                if (e instanceof HttpException) {
                                    HttpException httpException = (HttpException) e;
                                    try {
                                        String error = httpException.response().errorBody().string();
                                        Log.e("RepairUpError", error);
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                } else {
                                    //ToastUtil.showLongToast("请求失败");
                                }
                            }

                            @Override
                            public void onNext(List<UpLoadFilesResponse> upLoadFilesResponses) {
                                Log.v("upLoadFilesResponses", upLoadFilesResponses + "");
                                if (upLoadFilesResponses.size() > 0) {
                                    Toast.makeText(mContext, "提交照片成功！", Toast.LENGTH_SHORT).show();
                                    // BaseUtils.getInstence().intent(mContext,UserMainActivity.class);
                                    String[] strings = new String[upLoadFilesResponses.size()];
                                    for (int i = 0; i < upLoadFilesResponses.size(); i++) {
                                        UpLoadFilesResponse upLoadFilesResponse = upLoadFilesResponses.get(i);
                                        strings[i] = upLoadFilesResponse.getAttachmentId();
                                        Log.v("AttachmentIds", strings[i]);
                                    }
                                } else {
                                    Toast.makeText(mContext, "无返回！", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        }
            else{
                Toast.makeText(mContext, "无图片上传！", Toast.LENGTH_SHORT).show();
            }
            hideProgressBar();
        }
    private void addRepairSubmit(){
       // repairAddContent.setAppointTime("1577160060000");
        repairAddContent.setAppointTime(et_appointment_time.getText().toString().trim());//
        repairAddContent.setContractId(1L);//
        repairAddContent.setAddressName(et_repair_address.getText().toString().trim());//
        repairAddContent.setFacilitatorId(projectInfos.get(projectTmp).getPartyAId());//
        repairAddContent.setId(null);//
        repairAddContent.setCall(et_repair_tel.getText().toString().trim());
        repairAddContent.setObjectId(null);
        repairAddContent.setObjectType(1);
        repairAddContent.setLevel(changeLevel(et_emergency_degree.getText().toString().trim()));//
        repairAddContent.setPrincipalId(projectInfos.get(projectTmp).getAleaderId());//
        repairAddContent.setProjectId(projectInfos.get(projectTmp).getId());//
        repairAddContent.setStatus(0);//
        repairAddContent.setSuggestion("");//
        repairAddContent.setTitle(addressArray[addressTmp]);//
        repairAddContent.setTotalCost(0);//
        repairAddContent.setUserId(Long.valueOf(SPUtils.getInstance().getString("user_id","111")));//
        repairAddContent.getMdmcAddTaskItemDtoList().get(0).setDescription(fault_description.getText().toString().trim());
        repairAddContent.getMdmcAddTaskItemDtoList().get(0).setDeviceId(0L);//
        repairAddContent.getMdmcAddTaskItemDtoList().get(0).setDeviceType(fault_type.getText().toString().trim());//设备类型
        repairAddContent.getMdmcAddTaskItemDtoList().get(0).setDeviceLatitude(troubleAddresses.get(troubleTypeTmp).getTroubleLatitude());
        repairAddContent.getMdmcAddTaskItemDtoList().get(0).setDeviceLongitude(troubleAddresses.get(troubleTypeTmp).getTroubleLongitude());
        repairAddContent.getMdmcAddTaskItemDtoList().get(0).setLevel(et_fault_degree.getText().toString().trim());
        repairAddContent.getMdmcAddTaskItemDtoList().get(0).setId(null);
        repairAddContent.getMdmcAddTaskItemDtoList().get(0).setTaskId(0L);
        repairAddContent.getMdmcAddTaskItemDtoList().get(0).setTroubleType(troubleTypeTmp);//故障类型
        Log.v("repairAddContent", repairAddContent + "");
       // Net.instance.repairAddPost(repairAddContent, SPUtils.getInstance().getString("Token",""))
        BaseUtils.getInstence().repairAdd(repairAddContent,mContext);
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
