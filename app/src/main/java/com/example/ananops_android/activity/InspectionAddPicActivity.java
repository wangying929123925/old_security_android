package com.example.ananops_android.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.adapter.GridAdapter;
import com.example.ananops_android.db.UpLoadFilesResponse;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.photopicker.PhotoPickerActivity;
import com.example.ananops_android.photopicker.PhotoPreviewActivity;
import com.example.ananops_android.photopicker.SelectModel;
import com.example.ananops_android.photopicker.intent.PhotoPickerIntent;
import com.example.ananops_android.photopicker.intent.PhotoPreviewIntent;
import com.example.ananops_android.util.FileUtils;
import com.example.ananops_android.util.SPUtils;

import org.json.JSONArray;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

public class InspectionAddPicActivity extends BaseActivity {
    private ProgressDialog progressDialog;
    private GridView gridView;
    private GridAdapter gridAdapter;
    private ArrayList<String> imagePaths = new ArrayList<>();//图片
    private ArrayList<String> imagePathUp = new ArrayList<>();
    private ArrayList<String> attachmentIds = new ArrayList<>();//图片上传路径ID
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private static String[] PERMISSION_STORAGE_PHOTO ={
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };
    private TextView inspection_sub_num;
    private Context mContext;
    private Button inspection_submit_pics;
    private int num;
    private static int tmp=1;
    private int compressFlag = 0;
    public static final int RESULT_CODE_INSPICS=111;
    Long deviceId = new Date().getTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_add_pic);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,PERMISSION_STORAGE_PHOTO,1);
        }
        mContext = this;
      //  ActivityManager.getInstance().addActivity(this);
        num = getIntent().getIntExtra("num", 0);
        Log.v("InsAddPicActivity---", num + "");
        initView();
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

    private void initView() {
        inspection_sub_num = findViewById(R.id.inspection_sub_num);
        inspection_sub_num.setText("图片上传");
        gridView = (GridView) findViewById(R.id.gridView_photo);
        int cols = getResources().getDisplayMetrics().widthPixels / getResources().getDisplayMetrics().densityDpi;
        cols = cols < 3 ? 3 : cols;
        gridView.setNumColumns(cols);//拍照图片
        imagePaths.add("paizhao");
        gridAdapter = new GridAdapter(this,imagePaths);
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                    if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) {
                        String imgs = (String) parent.getItemAtPosition(position);
                        if ("paizhao".equals(imgs) ){
                            PhotoPickerIntent intent = new PhotoPickerIntent(InspectionAddPicActivity.this);
                            intent.setSelectModel(SelectModel.MULTI);
                            intent.setShowCarema(true); // 是否显示拍照
                            intent.setMaxTotal(6); // 最多选择照片数量，默认为6
                            intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
                            startActivityForResult(intent, REQUEST_CAMERA_CODE);
                        }else{
                            Toast.makeText(InspectionAddPicActivity.this,"1"+position,Toast.LENGTH_SHORT).show();
                            PhotoPreviewIntent intent = new PhotoPreviewIntent(InspectionAddPicActivity.this);
                            intent.setCurrentItem(position);
                            intent.setPhotoPaths(imagePaths);
                            startActivityForResult(intent, REQUEST_PREVIEW_CODE);
                        }
                    }
                }
            }
        });
        inspection_submit_pics = findViewById(R.id.inspection_submit_pics);
        inspection_submit_pics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subPics();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case REQUEST_CAMERA_CODE:
                    ArrayList<String> list = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                    Log.d(RepairAddActivity.class.getSimpleName(), "数量：" + list.size());
                    loadAdpater(list);
                    break;
                // 预览
                case REQUEST_PREVIEW_CODE:
                    ArrayList<String> ListExtra = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
                    loadAdpater(ListExtra);
                    break;
            }
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
      //  thread.start();
        gridAdapter  = new GridAdapter(this,imagePaths);
        gridView.setAdapter(gridAdapter);
        try{
            JSONArray obj = new JSONArray(imagePaths);
            Log.d("imagePaths", obj+"");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void checkCompress() {
        if (compressFlag != 1) {
            Toast.makeText(mContext, "请稍后再次点击图片提交按钮", Toast.LENGTH_SHORT).show();
            // hideProgressBar();
        } else {
            subPics();
        }
    }
    private void subPics() {
        attachmentIds.clear();
        if (imagePathUp.size() > 0) {
            showProgressBar();
            Log.d("imagePathUp上传", imagePathUp + "");
            for (int i = 0; i < imagePathUp.size(); i++) {
                int num1 = i+1;
                String string = imagePathUp.get(i);
                File file = new File(string);
                compressWithRx(file);
            }
        }
        else{
            Toast.makeText(mContext, "无图片上传！", Toast.LENGTH_SHORT).show();
        }
    }
    private void showProgressBar() {
        progressDialog=ProgressDialog.show(this,"提示","正在上传图片...",false);
        progressDialog.setCanceledOnTouchOutside(true);
    }
    private void hideProgressBar() {
        if (progressDialog != null) {
            progressDialog.dismiss();
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
        Net.instance.upLoadFiles1(type, "imcTaskAndroid", "ananops", body, SPUtils.getInstance(mContext).getString("Token", ""), deviceId)
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
                        attachmentIds.clear();
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
                            attachmentIds.add(String.valueOf(s));
                            Log.i("AttachmentLists", attachmentIds+"");
                            Log.i("tmp", tmp+"");
                            FileUtils.getInstance().deleteCacheFile(file);
                            if (tmp == imagePathUp.size()) {
                                hideProgressBar();
                                Intent intent = new Intent();
                                intent.putStringArrayListExtra("attachmentIds",attachmentIds );
                                setResult(RESULT_CODE_INSPICS, intent);
                                finish();
                                tmp=1;
                            }else {
                                tmp++;
                                Log.v("tmp+", tmp+"");
                            }
                        }

                    }
                });
    }
}
