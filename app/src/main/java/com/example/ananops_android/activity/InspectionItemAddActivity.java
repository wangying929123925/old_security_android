package com.example.ananops_android.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.annotation.ViewInject;
import com.example.ananops_android.db.CodeMessageResponse;
import com.example.ananops_android.db.InspectionDetailResponse;
import com.example.ananops_android.db.InspectionItemAddRequest;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.AnnotationUtils;
import com.example.ananops_android.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class InspectionItemAddActivity extends BaseActivity {
    @ViewInject(R.id.inspection_item_add_tv1)
    private TextView inspection_item_add_tv1;
    @ViewInject(R.id.inspection_item_add_tv2)
    private TextView inspection_item_add_tv2;
    @ViewInject(R.id.inspection_item_add_tv3)
    private TextView inspection_item_add_tv3;
    @ViewInject(R.id.inspection_item_add_tv4)
    private TextView inspection_item_add_tv4;
    @ViewInject(R.id.inspection_item_add_tv5)
    private TextView inspection_item_add_tv5;
    @ViewInject(R.id.inspection_item_add_tv6)
    private EditText inspection_item_add_tv6;
    @ViewInject(R.id.inspection_item_add_tv7)
    private EditText inspection_item_add_tv7;
    @ViewInject(R.id.inspection_item_add_tv8)
    private EditText inspection_item_add_tv8;
    @ViewInject(R.id.inspection_item_add_tv9)
    private EditText inspection_item_add_tv9;
    @ViewInject(R.id.invoice_submit_bt)
    private Button invoice_submit_bt;
    @ViewInject(R.id.inspection_item_back)
    private ImageView img_back;
    @ViewInject(R.id.inspection_item_add_pic)
    private TextView add_pic;
    private String inspectionId;
    private Context mContext;
    private int result;
    private static final int REQUEST_LOADPIC = 1;
    private List<Long> attachmentIds = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_item_add);
        AnnotationUtils.injectViews(this);
        mContext = this;
        initViews();
    }

    private void initViews() {
        Intent intent = getIntent();
        if (intent != null) {
            inspectionId = intent.getStringExtra("inspectionId");
            inspection_item_add_tv2.setText(inspectionId);
        }
       img_back.setOnClickListener(v -> {
         finish();
       });
        add_pic.setOnClickListener(v -> {
            Intent intent1 = new Intent(InspectionItemAddActivity.this, InspectionAddPicActivity.class);
            startActivityForResult(intent1, REQUEST_LOADPIC);
        });
        invoice_submit_bt.setOnClickListener(v ->{
            submitInspectionItem();
        });
     getInspectionInfo();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LOADPIC) {
            if (resultCode == InspectionAddPicActivity.RESULT_CODE_INSPICS && data != null) {
                ArrayList<String> strings = new ArrayList<>();
                strings = data.getStringArrayListExtra("attachmentIds");
                Log.v("获取到的strings：",strings+"");
                attachmentIds.clear();
                for (String s : strings) {
                    if(s!=null) {
                        Long id = Long.valueOf(s);
                        attachmentIds.add(id);
                    }
                }
            }
        }
    }

    private void getInspectionInfo() {
        Net.instance.getInspectionDetails(Long.valueOf(inspectionId), SPUtils.getInstance(mContext).getString("Token", " "))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<InspectionDetailResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("ErrorGetInspectionInfo", System.currentTimeMillis() + "");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(InspectionDetailResponse inspectionDetailResponse) {
                        if (TextUtils.equals(inspectionDetailResponse.getCode(), "200")) {
                         inspection_item_add_tv1.setText(inspectionDetailResponse.getResult().getTaskName());
                         inspection_item_add_tv3.setText(inspectionDetailResponse.getResult().getScheduledStartTime());
                         inspection_item_add_tv4.setText(inspectionDetailResponse.getResult().getFrequency()+"");
                         inspection_item_add_tv5.setText(inspectionDetailResponse.getResult().getDays()+"");
                         result=inspectionDetailResponse.getResult().getPointSum()-inspectionDetailResponse.getResult().getAlreadyPoint();
                         inspection_item_add_tv9.setText(String.valueOf(result));
                        } else {
                            Toast.makeText(mContext,"获取巡检信息失败:)", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void submitInspectionItem() {
        if (checkAdd()) {
            InspectionItemAddRequest inspectionItemAddRequest = new InspectionItemAddRequest();
            inspectionItemAddRequest.setInspectionTaskId(Long.valueOf(inspectionId));
            inspectionItemAddRequest.setScheduledStartTime(inspection_item_add_tv3.getText().toString().trim());
            inspectionItemAddRequest.setFrequency(Integer.parseInt(inspection_item_add_tv4.getText().toString().trim()));
            inspectionItemAddRequest.setDays(Integer.parseInt(inspection_item_add_tv5.getText().toString().trim()));
            inspectionItemAddRequest.setItemName(inspection_item_add_tv6.getText().toString().trim());
            inspectionItemAddRequest.setDescription(inspection_item_add_tv7.getText().toString().trim());
            inspectionItemAddRequest.setLocation(inspection_item_add_tv8.getText().toString().trim());
            inspectionItemAddRequest.setCount(Integer.parseInt(inspection_item_add_tv9.getText().toString().trim()));
            inspectionItemAddRequest.setUserId(Long.valueOf(SPUtils.getInstance(mContext).getString("user_id", "1")));
            inspectionItemAddRequest.setAttachmentIds(attachmentIds);
            Net.instance.inspectionItemAdd(inspectionItemAddRequest,SPUtils.getInstance(mContext).getString("Token", " "))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<CodeMessageResponse>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(mContext, "提交失败！", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNext(CodeMessageResponse codeMessageResponse) {
                            if (TextUtils.equals(codeMessageResponse.getCode(), "200")) {
                                Toast.makeText(mContext, "提交巡检子项成功！", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(mContext, "提交失败！", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
    private boolean checkAdd() {
        if (TextUtils.isEmpty(inspection_item_add_tv2.getText().toString().trim())) {
            Toast.makeText(this,"未获取到巡检信息！", Toast.LENGTH_SHORT).show();
            return false;
        }else if (TextUtils.isEmpty(inspection_item_add_tv6.getText().toString().trim())) {
            Toast.makeText(this,"请填写子项名称！", Toast.LENGTH_SHORT).show();
            return false;
        } else if(TextUtils.isEmpty(inspection_item_add_tv7.getText().toString().trim())) {
            Toast.makeText(this,"请填写任务描述！", Toast.LENGTH_SHORT).show();
            return false;
        }else if(TextUtils.isEmpty(inspection_item_add_tv8.getText().toString().trim())) {
            Toast.makeText(this,"请填写点位名称！", Toast.LENGTH_SHORT).show();
            return false;
        }else if(TextUtils.isEmpty(inspection_item_add_tv9.getText().toString().trim())) {
            Toast.makeText(this,"请填写点位数量！", Toast.LENGTH_SHORT).show();
            return false;
        } else if (Integer.parseInt(inspection_item_add_tv9.getText().toString().trim()) > result) {
            Toast.makeText(this,"点位数量不得超过未安排数量！", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
