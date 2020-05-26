package com.example.ananops_android.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.adapter.ListCommonAdapter;
import com.example.ananops_android.adapter.ListViewHolder;
import com.example.ananops_android.db.AllAcceptedItemByMaintainerRequest;
import com.example.ananops_android.db.AllFinishedInspectionItemResponse;
import com.example.ananops_android.db.AllItemByTaskIdAndStatuRequest;
import com.example.ananops_android.db.InspectionQueryByStatusAndIdRequest;
import com.example.ananops_android.entity.InspectionTaskItem;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.SPUtils;
import com.example.ananops_android.view.EditTextWithDel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class InspectionItemListActivity extends BaseActivity {
    private ListView sortListView;
    private TextView title;
    private LinearLayout noResult;
    private EditTextWithDel mEtSearchName;
    private ImageView imageBack;
    private List<InspectionTaskItem> inspectionTaskItems = new ArrayList<>();
    private ListCommonAdapter mAdapter;
    private static String statusDo;
    private String status;
    private String inspectionId;
    private Context mContext;
    private SmartRefreshLayout mRefreshLayout;
    private int curPage = 1;//当前页
    private int pageSize = 10;
    //用于记录当前是何种状态，在请求完数据之后根据不同的状态进行不同的操作
    private static final int STATE_INIT = 0;
    private static final int STATE_REFRESH = 1;
    private static final int STATE_LOAD_MORE = 2;
    //用于记录当前的状态
    private int curState = STATE_INIT;
    //用于记录总页数，在上拉的时候判断还有没有更多数据
    private int totalPage = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext =this;
        setContentView(R.layout.activity_contacts_main);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            statusDo = bundle.getString("statusDo");
            inspectionId = bundle.getString("inspectionId","1");
            status = bundle.getString("status", "1");
            initDatas(statusDo,curPage);
        }
            initViews();

    }
    private void initDatas(String statusDo,int curPageNum){
        if (statusDo.equals("3-1")) {
            int status = 2;
            InspectionQueryByStatusAndIdRequest inspectionQueryByStatusAndIdRequest = new InspectionQueryByStatusAndIdRequest();
            inspectionQueryByStatusAndIdRequest.setStatus(status);
            inspectionQueryByStatusAndIdRequest.setPageNum(curPageNum);
            inspectionQueryByStatusAndIdRequest.setPageSize(pageSize);
            inspectionQueryByStatusAndIdRequest.setMaintainerId(Long.valueOf(SPUtils.getInstance(this).getString("user_id", "")));
            Net.instance.getInspectionItemByMaintainerIdAndStatus(inspectionQueryByStatusAndIdRequest, SPUtils.getInstance(mContext).getString("Token", " "))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<AllFinishedInspectionItemResponse>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.v("InspectionListTime", System.currentTimeMillis() + "");
                            e.printStackTrace();

                        }

                        @Override
                        public void onNext(AllFinishedInspectionItemResponse inspectionItemListResponse) {
                            if (TextUtils.equals(inspectionItemListResponse.getCode(), "200")) {
                                //inspectionTaskItems.clear();
                                totalPage = inspectionItemListResponse.getResult().getPages();
                                List<InspectionTaskItem> inspectionTaskItemsAdd = new ArrayList<>();
                                inspectionTaskItemsAdd.addAll(inspectionItemListResponse.getResult().getList());
                                showData(inspectionTaskItemsAdd);
                            } else {
                                Toast.makeText(mContext,"获取列表失败",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        } else if (statusDo.equals("3-2")) {
            AllAcceptedItemByMaintainerRequest allAcceptedItemByMaintainerRequest = new AllAcceptedItemByMaintainerRequest();
            allAcceptedItemByMaintainerRequest.setPageNum(curPageNum);
            allAcceptedItemByMaintainerRequest.setPageSize(pageSize);
            allAcceptedItemByMaintainerRequest.setMaintainerId(Long.valueOf(SPUtils.getInstance(mContext).getString("user_id", "")));
            Net.instance.getAllAcceptedItemByMaintainer(allAcceptedItemByMaintainerRequest, SPUtils.getInstance(mContext).getString("Token", " "))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<AllFinishedInspectionItemResponse>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                        //    Log.v("ErrorInspectionListTime", System.currentTimeMillis() + "");
                            e.printStackTrace();
                           // initViews();//
                        }

                        @Override
                        public void onNext(AllFinishedInspectionItemResponse inspectionItemListResponse) {
                            if (TextUtils.equals(inspectionItemListResponse.getCode(), "200")) {
                                    //inspectionTaskItems.clear();
                                    totalPage = inspectionItemListResponse.getResult().getPages();
                                    List<InspectionTaskItem> inspectionTaskItemsAdd = new ArrayList<>();
                                inspectionTaskItemsAdd.addAll(inspectionItemListResponse.getResult().getList());
                                    showData(inspectionTaskItemsAdd);
                                } else {
                                    Toast.makeText(mContext,"获取列表失败",Toast.LENGTH_SHORT).show();
                                }
                            }
                    });
        } else if (statusDo.equals("3-3")) {
            InspectionQueryByStatusAndIdRequest inspectionQueryByStatusAndIdRequest = new InspectionQueryByStatusAndIdRequest();
            inspectionQueryByStatusAndIdRequest.setStatus(4);
            inspectionQueryByStatusAndIdRequest.setPageNum(curPageNum);
            inspectionQueryByStatusAndIdRequest.setPageSize(pageSize);
            inspectionQueryByStatusAndIdRequest.setMaintainerId(Long.valueOf(SPUtils.getInstance(this).getString("user_id", "")));
            Net.instance.getAllFinishedItemByMaintainer(inspectionQueryByStatusAndIdRequest, SPUtils.getInstance(mContext).getString("Token", " "))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<AllFinishedInspectionItemResponse>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.v("ErrorInspectionListTime", System.currentTimeMillis() + "");
                            e.printStackTrace();
                            //initViews();//
                        }

                        @Override
                        public void onNext(AllFinishedInspectionItemResponse inspectionItemListResponse) {
                            if (TextUtils.equals(inspectionItemListResponse.getCode(), "200")) {
                                //inspectionTaskItems.clear();
                                totalPage = inspectionItemListResponse.getResult().getPages();
                                List<InspectionTaskItem> inspectionTaskItemsAdd = new ArrayList<>();
                                inspectionTaskItemsAdd.addAll(inspectionItemListResponse.getResult().getList());
                                showData(inspectionTaskItemsAdd);
                            } else {
                                Toast.makeText(mContext,"获取列表失败",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        } else {
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
        mRefreshLayout = findViewById(R.id.refreshLayout);
       // inspectionTaskItems= InspectionUtils.getInstence().getInspectionTaskItems(inspectionTaskItems,Long.valueOf(inspectionId),mContext);
        mAdapter = new ListCommonAdapter<InspectionTaskItem>(mContext, R.layout.item_project_list, inspectionTaskItems) {
            @Override
            protected void convert(ListViewHolder viewHolder, InspectionTaskItem inspectionTaskItem, int position) {
                if (inspectionTaskItem.getItemName() == null) {
                    viewHolder.setText(R.id.Plist_name, "--");
                }else {
                    viewHolder.setText(R.id.Plist_name, inspectionTaskItem.getItemName()); //名称}}
                }
                    viewHolder.setText(R.id.Plist_id, "子项编号："+inspectionTaskItem.getId());//id
                    viewHolder.setText(R.id.Plist_type, "点位数量："+inspectionTaskItem.getDescription());//类型
                    viewHolder.setText(R.id.Plist_price, "点位位置："+inspectionTaskItem.getUpdateTime());//价格
            }

        };
     //   mAdapter.notifyDataSetInvalidated();
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
        //越界回弹
        mRefreshLayout.setEnableOverScrollBounce(false);
        //在刷新或者加载的时候不允许操作视图
        mRefreshLayout.setDisableContentWhenRefresh(true);
        mRefreshLayout.setDisableContentWhenLoading(true);
        //监听列表在滚动到底部时触发加载事件（默认true）
        mRefreshLayout.setEnableAutoLoadMore(false);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                curPage = 1;
                curState = STATE_REFRESH;
                initDatas(statusDo,curPage);
                mRefreshLayout.finishRefresh();
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                curPage++;
                curState = STATE_LOAD_MORE;
                Log.i(TAG, "onLoadMore: curPage"+curPage);
                Log.i(TAG, "onLoadMore: totalPage"+totalPage);
                if (curPage <= totalPage) {
                    initDatas(statusDo,curPage);
                    //  more
                } else {
                    Toast.makeText(mContext, "没有更多啦O(∩_∩)O", Toast.LENGTH_SHORT).show();
                }
                mRefreshLayout.finishLoadMore();
            }
        });
        mEtSearchName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                     showSearchResult(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        imageBack.setOnClickListener(v -> finish());
    }
    private void getInspectionListByTaskIdAndStatus(){
        AllItemByTaskIdAndStatuRequest allItemByTaskIdAndStatuRequest = new AllItemByTaskIdAndStatuRequest();
        allItemByTaskIdAndStatuRequest.setStatus(status);
        allItemByTaskIdAndStatuRequest.setPageNum(curPage);
        allItemByTaskIdAndStatuRequest.setPageSize(pageSize);
        allItemByTaskIdAndStatuRequest.setTaskId(Long.valueOf(inspectionId));
        Net.instance.getAllItemByTaskIdAndStatus(allItemByTaskIdAndStatuRequest,SPUtils.getInstance(mContext).getString("Token", " "))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AllFinishedInspectionItemResponse>() {
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
                    public void onNext(AllFinishedInspectionItemResponse inspectionItemListResponse) {
                        if (TextUtils.equals(inspectionItemListResponse.getCode(), "200")) {
                            //inspectionTaskItems.clear();
                            totalPage = inspectionItemListResponse.getResult().getPages();
                            List<InspectionTaskItem> inspectionTaskItemsAdd = new ArrayList<>();
                            inspectionTaskItemsAdd.addAll(inspectionItemListResponse.getResult().getList());
                            showData(inspectionTaskItemsAdd);
                        } else {
                            Toast.makeText(mContext,"获取列表失败",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
    private void showData(List<InspectionTaskItem> inspectionTaskItemsAdd) {
        switch (curState) {
            case STATE_INIT:
                inspectionTaskItems.clear();
                inspectionTaskItems.addAll(inspectionTaskItemsAdd);
                mAdapter.notifyDataSetChanged();
                break;
            case STATE_LOAD_MORE:
                int lastPosition = inspectionTaskItems.size();
                inspectionTaskItems.addAll(lastPosition,inspectionTaskItemsAdd);
                mAdapter.notifyDataSetChanged();
               // mAdapter.updateList();
                break;
            case STATE_REFRESH:
                inspectionTaskItems.clear();
                inspectionTaskItems.addAll(inspectionTaskItemsAdd);
                mAdapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
        if (inspectionTaskItems.size() == 0) {
            noResult.setVisibility(View.VISIBLE);
        } else {
            noResult.setVisibility(View.GONE);
        }
    }
    private void showSearchResult(String searchContent){
        List<InspectionTaskItem> inspectionTaskItemsFilterd=new ArrayList<>();
        if (TextUtils.isEmpty(searchContent)) {
            inspectionTaskItemsFilterd = inspectionTaskItems;
        }else {
            inspectionTaskItemsFilterd.clear();
            for (InspectionTaskItem repairContent : inspectionTaskItems) {
                String name = repairContent.getItemName();
                Pattern p = Pattern.compile(searchContent);
                Matcher m = p.matcher(name);
                if (m.find()) {
                    inspectionTaskItemsFilterd.add(repairContent);
                }
            }
        }
        mAdapter.updateList(inspectionTaskItemsFilterd);
        if (inspectionTaskItemsFilterd.size() == 0) {
            noResult.setVisibility(View.VISIBLE);
        } else {
            noResult.setVisibility(View.GONE);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        initDatas(statusDo,1);
        curPage=1;
    }
}
