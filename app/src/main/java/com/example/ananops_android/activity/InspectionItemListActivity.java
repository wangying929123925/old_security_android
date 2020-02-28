package com.example.ananops_android.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.adapter.ListCommonAdapter;
import com.example.ananops_android.adapter.ListViewHolder;
import com.example.ananops_android.db.AllAcceptedItemByMaintainerRequest;
import com.example.ananops_android.db.AllItemByTaskIdAndStatuRequest;
import com.example.ananops_android.db.InspectionItemListResponse;
import com.example.ananops_android.db.InspectionQueryByStatusAndIdRequest;
import com.example.ananops_android.entity.InspectionTaskItem;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.SPUtils;
import com.example.ananops_android.view.EditTextWithDel;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class InspectionItemListActivity extends AppCompatActivity {
    private ListView sortListView;
    private TextView title;
    private TextView noResult;
    private EditTextWithDel mEtSearchName;
    private ImageView imageBack;
    private List<InspectionTaskItem> inspectionTaskItems=new ArrayList<>();
    private ListCommonAdapter mAdapter;
    private static String statusDo;
    private String status;
    private String inspectionId;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext =this;
        setContentView(R.layout.activity_contacts_main);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            statusDo = bundle.getString("statusDo");
            inspectionId = bundle.getString("inspectionId");
            status = bundle.getString("status", "1");
            initDatas(statusDo);
        } else {
            initViews();
        }
    }
    private void initDatas(String statusDo){
        if (statusDo.equals("3-1")) {
            int status = 2;
            InspectionQueryByStatusAndIdRequest inspectionQueryByStatusAndIdRequest = new InspectionQueryByStatusAndIdRequest();
            inspectionQueryByStatusAndIdRequest.setStatus(status);
            inspectionQueryByStatusAndIdRequest.setUserId(1L);
            Net.instance.getInspectionItemByMaintainerIdAndStatus(inspectionQueryByStatusAndIdRequest, SPUtils.getInstance().getString("Token", " "))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<InspectionItemListResponse>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.v("InspectionListTime", System.currentTimeMillis() + "");
                            e.printStackTrace();
                        }

                        @Override
                        public void onNext(InspectionItemListResponse inspectionItemListResponse) {
                            if (TextUtils.equals(inspectionItemListResponse.getCode(), "200")) {
                                inspectionTaskItems.clear();
                                if (inspectionItemListResponse.getResult().size() > 0) {
                                    inspectionTaskItems.addAll(inspectionItemListResponse.getResult());
                                    Log.v("巡检子项列表1", inspectionItemListResponse.getResult().get(0).getId() + "");
                                    initViews();//
                                } else {
                                    Toast.makeText(mContext, "无巡检子项列表！", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(mContext, inspectionItemListResponse.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        } else if (statusDo.equals("3-2")) {
            AllAcceptedItemByMaintainerRequest allAcceptedItemByMaintainerRequest = new AllAcceptedItemByMaintainerRequest();
            allAcceptedItemByMaintainerRequest.setMaintainerId(Long.valueOf(SPUtils.getInstance().getString("user_id", "")));
            Net.instance.getAllAcceptedItemByMaintainer(allAcceptedItemByMaintainerRequest, SPUtils.getInstance().getString("Token", " "))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<InspectionItemListResponse>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.v("ErrorInspectionListTime", System.currentTimeMillis() + "");
                            e.printStackTrace();
                        }

                        @Override
                        public void onNext(InspectionItemListResponse inspectionItemListResponse) {
                            if (TextUtils.equals(inspectionItemListResponse.getCode(), "200")) {
                                inspectionTaskItems.clear();
                                if (inspectionItemListResponse.getResult().size() > 0) {
                                    inspectionTaskItems.addAll(inspectionItemListResponse.getResult());
                                    Log.v("巡检子项列表1", inspectionItemListResponse.getResult().get(0).getId() + "");
                                    initViews();//
                                } else {
                                    Toast.makeText(mContext, "无巡检子项列表！", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(mContext, inspectionItemListResponse.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }else {
            getInspectionListByTaskIdAndStatus();
        }
    }
    private void initViews() {
        sortListView=findViewById(R.id.lv_contact);
        mEtSearchName = (EditTextWithDel) findViewById(R.id.et_search_contact);
        title = findViewById(R.id.txt_title);
        noResult = findViewById(R.id.no_result_text);
        imageBack = findViewById(R.id.img_back);
        title.setText("巡检任务子项");
       // inspectionTaskItems= InspectionUtils.getInstence().getInspectionTaskItems(inspectionTaskItems,Long.valueOf(inspectionId),mContext);
        mAdapter = new ListCommonAdapter<InspectionTaskItem>(mContext, R.layout.item_project_list, inspectionTaskItems) {
            @Override
            protected void convert(ListViewHolder viewHolder, InspectionTaskItem inspectionTaskItem, int position) {
                viewHolder.setText(R.id.Plist_name, inspectionTaskItem.getItemName());//名称
                viewHolder.setText(R.id.Plist_id, String.valueOf(inspectionTaskItem.getId()));//id
                viewHolder.setText(R.id.Plist_type, inspectionTaskItem.getMaintainerName());//类型
                viewHolder.setText(R.id.Plist_price, inspectionTaskItem.getUpdateTime());//价格
            }
        };
        mAdapter.notifyDataSetInvalidated();
        sortListView.setAdapter(mAdapter);
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //进入任务子项
                Bundle bundle = new Bundle();
                bundle.putString("inspectionItemId",String.valueOf(inspectionTaskItems.get(position).getId()));
                bundle.putString("statusDo",statusDo);
               // bundle.putString("projectId","1");
                BaseUtils.getInstence().intent(mContext, InspectionItemDetailActivity.class,bundle);
//                if(inspectionTaskItems.get(position).getStatus()==2){
//                    Bundle bundle = new Bundle();
//                    bundle.putString("inspectionItemId",String.valueOf(inspectionTaskItems.get(position).getId()));
//                    bundle.putString("status","3-1");
//                    BaseUtils.getInstence().intent(mContext, InspectionItemDetailActivity.class,bundle);
//                }else if(inspectionTaskItems.get(position).getStatus()==3){
//                    Bundle bundle = new Bundle();
//                    bundle.putString("inspectionItemId",String.valueOf(inspectionTaskItems.get(position).getId()));
//                    bundle.putString("status","3-2");
//                    BaseUtils.getInstence().intent(mContext, InspectionItemDetailActivity.class,bundle);
//                }else if(inspectionTaskItems.get(position).getStatus()==1){
//                    Bundle bundle = new Bundle();
//                    bundle.putString("inspectionItemId",String.valueOf(inspectionTaskItems.get(position).getId()));
//                    bundle.putString("status","2-2");
//                    BaseUtils.getInstence().intent(mContext, InspectionItemDetailActivity.class,bundle);
//                }else {
//                    Bundle bundle = new Bundle();
//                    bundle.putString("inspectionItemId",String.valueOf(inspectionTaskItems.get(position).getId()));
//                    bundle.putString("status","no");
//                    BaseUtils.getInstence().intent(mContext, InspectionItemDetailActivity.class,bundle);
//                }

            }
        });
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void getInspectionListByTaskIdAndStatus(){
        AllItemByTaskIdAndStatuRequest allItemByTaskIdAndStatuRequest = new AllItemByTaskIdAndStatuRequest();
        allItemByTaskIdAndStatuRequest.setStatus(status);
        allItemByTaskIdAndStatuRequest.setTaskId(inspectionId);
        Net.instance.getAllItemByTaskIdAndStatus(allItemByTaskIdAndStatuRequest,SPUtils.getInstance().getString("Token", " "))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<InspectionItemListResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("ErrorInspectionListTime", System.currentTimeMillis() + "");
                        e.printStackTrace();
                        initViews();
                    }

                    @Override
                    public void onNext(InspectionItemListResponse inspectionItemListResponse) {
                        if (TextUtils.equals(inspectionItemListResponse.getCode(), "200")) {
                            inspectionTaskItems.clear();
                            if (inspectionItemListResponse.getResult().size() > 0) {
                                inspectionTaskItems.addAll(inspectionItemListResponse.getResult());
                                Log.v("巡检子项列表1", inspectionItemListResponse.getResult().get(0).getId() + "");
                                initViews();//
                            } else {
                                Toast.makeText(mContext, "无巡检子项列表！", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(mContext, inspectionItemListResponse.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
