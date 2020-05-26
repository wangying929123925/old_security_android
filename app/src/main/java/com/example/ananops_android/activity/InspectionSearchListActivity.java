package com.example.ananops_android.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.adapter.BaseRecyclerAdapter;
import com.example.ananops_android.adapter.InspectionAdapter;
import com.example.ananops_android.db.AllUnDistributedWorkOrdersRequest;
import com.example.ananops_android.db.AllUnDistributedWorkOrdersResponse;
import com.example.ananops_android.db.GetAllUnConfirmedWorkOrdersRequset;
import com.example.ananops_android.db.GetAllUnConfirmedWorkOrdersResponse;
import com.example.ananops_android.db.InspectionListByProjectRequest;
import com.example.ananops_android.db.InspectionListByUserIdAndStatusRequest;
import com.example.ananops_android.db.InspectionListResponse;
import com.example.ananops_android.entity.InspectionInfo;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.SPUtils;
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

public class InspectionSearchListActivity extends BaseActivity{
    private  String searchContent;
    private List<InspectionInfo> inspectionInfos = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView title;
    private ImageView back_img;
    private EditText search_content;
    private TextView search_text;
    private static String statusDo;
    private static String projectId;
    private InspectionAdapter inspectionAdapter;
    private LinearLayout noResult;
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
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_order_list);
        mContext=this;
       // ActivityManager.getInstance().addActivity(this);
        Bundle bundle = getIntent().getExtras();
        //   inspectionInfos = bundle.getParcelableArrayList("result");
        statusDo = bundle.getString("statusDo");
        projectId = bundle.getString("projectId");
        initViews();
        initDatas();
        setOnListener();
    }
    private void initViews() {

        title = findViewById(R.id.txt_title);//标题
        //search_img=findViewById(R.id.img_search);
        back_img = findViewById(R.id.img_back);
        search_content = findViewById(R.id.text_search);
        search_text = findViewById(R.id.search_title_txt);
//        Intent intent=getIntent();
//        PROJECT_ID=intent.getStringExtra("project_id");
        noResult = findViewById(R.id.no_result_text);
        title.setText("巡检列表");
//        if (inspectionInfos.size() == 0) {
//            noResult.setVisibility(View.VISIBLE);
//        } else {
//            noResult.setVisibility(View.GONE);
//        }
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView = findViewById(R.id.contact_recycler_view);
        mRecyclerView.setLayoutManager(mLayoutManager);
        inspectionAdapter = new InspectionAdapter(inspectionInfos);
        inspectionAdapter.setOnRecyclerViewItemClickListener(new BaseRecyclerAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
             //   InspectionInfo inspectionInfo=inspectionInfos.get(position);
                  //  Toast.makeText(getApplicationContext(), "巡检详情" + (position + 1), Toast.LENGTH_SHORT).show();
                    Bundle bundle0 = new Bundle();
                    bundle0.putString("inspectionId", String.valueOf(inspectionInfos.get(position).getId()));
                    bundle0.putString("statusDo", statusDo);
                    BaseUtils.getInstence().intent(mContext, InspectionDetailActivity.class, bundle0, "title", "4-2");
                }

        });
        mRecyclerView.setAdapter(inspectionAdapter);
        //越界回弹
        mRefreshLayout = findViewById(R.id.refreshLayout);
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
                initDatas();
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
                    initDatas();
                    //  more
                } else {
                    Toast.makeText(mContext, "没有更多啦O(∩_∩)O", Toast.LENGTH_SHORT).show();
                }
                mRefreshLayout.finishLoadMore();
            }
        });

    }
    private void initDatas() {
   //  inspectionContents= BaseUtils.getInstence().initInspectionContent(inspectionContents);
        if (statusDo.equals("2-1")) {
            getDataUnConfirmed();
         //服务商待接单
        } else if (statusDo.equals("2-2")) {
            //服务商待分配工程师
            getDataUnDistributed();
        } else if (statusDo.equals("4-2")) {
            ////甲方待确认
            getDataByUserIdAndStatus(0);
        } else if (statusDo.equals("2-3")) {
            //服务商已完成
            getDatafinished();
        }else if (statusDo.equals("4-3")){
            //甲方巡检中确认任务完成
            getDataByUserIdAndStatus(3);
        }else if (statusDo.equals("4-4")){
            getDataByUserIdAndStatus(4);
            //甲方//甲方待确认
        } else if (statusDo.equals("4-5")) {
            //甲方待评价
            getDataByUserIdAndStatus(5);
        } else {
            getDataByProject();
            //项目
        }
    }
    private void setOnListener() {
        back_img.setOnClickListener(v -> finish());
     //   search_text.setOnClickListener(this);
        search_content.addTextChangedListener(new TextWatcher() {
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
    }

    private void getDataUnConfirmed() {
        GetAllUnConfirmedWorkOrdersRequset requset = new GetAllUnConfirmedWorkOrdersRequset();
        requset.setPageNum(curPage);
        requset.setPageSize(pageSize);
        requset.setUserId(Long.valueOf(SPUtils.getInstance(mContext).getString("user_id", "")));
        Net.instance.getAllUnConfirmedWorkOrders(requset, SPUtils.getInstance(mContext).getString("Token", " "))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetAllUnConfirmedWorkOrdersResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("ErrorGetUnauthorWork", System.currentTimeMillis() + "");
                        e.printStackTrace();
                     //   Toast.makeText(mContext, "获取项目信息失败！", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(GetAllUnConfirmedWorkOrdersResponse response) {
                        if (TextUtils.equals(response.getCode(), "200")) {
                            totalPage = response.getResult().getPages();
                            ArrayList<InspectionInfo> inspectionInfosAdd = new ArrayList<>();
                            inspectionInfosAdd.addAll(response.getResult().getList());
                            showData(inspectionInfosAdd);
                        } else {
                            Toast.makeText(mContext, "获取项目信息失败！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void getDataUnDistributed() {
        AllUnDistributedWorkOrdersRequest allUnDistributedWorkOrdersRequest = new AllUnDistributedWorkOrdersRequest();
        allUnDistributedWorkOrdersRequest.setUserId(Long.valueOf(SPUtils.getInstance(mContext).getString("user_id", "")));
        Net.instance.getAllUnDistributedWorkOrder(allUnDistributedWorkOrdersRequest, SPUtils.getInstance(mContext).getString("Token", " "))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AllUnDistributedWorkOrdersResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("ErrorGetUnauthorTask", System.currentTimeMillis() + "");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(AllUnDistributedWorkOrdersResponse response) {
                        if (TextUtils.equals(response.getCode(), "200")) {
                            totalPage = response.getResult().getPages();
                            ArrayList<InspectionInfo> inspectionInfosAdd = new ArrayList<>();
                            inspectionInfosAdd.addAll(response.getResult().getList());
                            showData(inspectionInfosAdd);
                        } else {
                            Toast.makeText(mContext, "获取项目信息失败！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void getDataByUserIdAndStatus(int status) {
        InspectionListByUserIdAndStatusRequest inspectionListByUserIdAndStatusRequest = new InspectionListByUserIdAndStatusRequest();
        inspectionListByUserIdAndStatusRequest.setRole(1);
        inspectionListByUserIdAndStatusRequest.setStatus(status);
        inspectionListByUserIdAndStatusRequest.setPageSize(pageSize);
        inspectionListByUserIdAndStatusRequest.setPageNum(curPage);
        inspectionListByUserIdAndStatusRequest.setUserId(Long.valueOf(SPUtils.getInstance(mContext).getString("user_id", "")));
        Net.instance.getInspectionTaskByUserIdAndStatus(inspectionListByUserIdAndStatusRequest, SPUtils.getInstance(mContext).getString("Token", " "))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AllUnDistributedWorkOrdersResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("ErrorGetInsTaskById", System.currentTimeMillis() + "");
                        e.printStackTrace();
                     //   Toast.makeText(mContext, "获取巡检信息失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(AllUnDistributedWorkOrdersResponse response) {
                        if (TextUtils.equals(response.getCode(), "200")) {
                            totalPage = response.getResult().getPages();
                            ArrayList<InspectionInfo> inspectionInfosAdd = new ArrayList<>();
                            inspectionInfosAdd.addAll(response.getResult().getList());
                            showData(inspectionInfosAdd);
                        } else {
                            Toast.makeText(mContext, "获取项目信息失败！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void getDatafinished() {
        GetAllUnConfirmedWorkOrdersRequset requset = new GetAllUnConfirmedWorkOrdersRequset();
        requset.setPageNum(curPage);
        requset.setPageSize(pageSize);
        requset.setUserId(Long.valueOf(SPUtils.getInstance(mContext).getString("user_id", "")));
        Net.instance.getAllFinishedWorkOrders(requset, SPUtils.getInstance(mContext).getString("Token", " "))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetAllUnConfirmedWorkOrdersResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("ErrorGetUnauthorWork", System.currentTimeMillis() + "");
                        e.printStackTrace();
                        Toast.makeText(mContext, "获取项目信息失败！", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNext(GetAllUnConfirmedWorkOrdersResponse response) {
                        if (TextUtils.equals(response.getCode(), "200")) {
                            totalPage = response.getResult().getPages();
                            ArrayList<InspectionInfo> inspectionInfosAdd = new ArrayList<>();
                            inspectionInfosAdd.addAll(response.getResult().getList());
                            showData(inspectionInfosAdd);
                        } else {
                            Toast.makeText(mContext, "获取项目信息失败！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void getDataByProject() {
        if (projectId != null) {
            InspectionListByProjectRequest inspectionListByProjectRequest=new InspectionListByProjectRequest();
            inspectionListByProjectRequest.setProjectId(Long.valueOf(projectId));
           // inspectionListByProjectRequest.setPageNum(curPage);
           // inspectionListByProjectRequest.setPageSize(pageSize);
            Net.instance.getInspectionListByProjectId(inspectionListByProjectRequest, SPUtils.getInstance(mContext).getString("Token", " "))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<InspectionListResponse>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.v("ErrorGetInspection_pro", System.currentTimeMillis() + "");
                            e.printStackTrace();
                        }

                        @Override
                        public void onNext(InspectionListResponse response) {
                            if (TextUtils.equals(response.getCode(), "200")) {
                              //  totalPage = response.getResult().getPages();
                                totalPage=1;
                                ArrayList<InspectionInfo> inspectionInfosAdd = new ArrayList<>();
                                inspectionInfosAdd.addAll(response.getResult());
                                showData(inspectionInfosAdd);
                            } else {
                                Toast.makeText(mContext, "获取项目信息失败！", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(mContext,"请选择项目！", Toast.LENGTH_LONG).show();
        }
    }

    private void showSearchResult(String searchContent){

            List<InspectionInfo> inspectionInfosFilterd=new ArrayList<>();
            if (TextUtils.isEmpty(searchContent)) {
                inspectionInfosFilterd = inspectionInfos;
            }else {
                inspectionInfosFilterd.clear();
                for (InspectionInfo repairContent : inspectionInfos) {
                    String name = repairContent.getTaskName();
                    Pattern p = Pattern.compile(searchContent);
                    Matcher m = p.matcher(name);
                    if (m.find()) {
                        inspectionInfosFilterd.add(repairContent);
                    }
                }
            }
            inspectionAdapter.updateList(inspectionInfosFilterd);
            if (inspectionInfosFilterd.size() == 0) {
                noResult.setVisibility(View.VISIBLE);
            } else {
                noResult.setVisibility(View.GONE);
            }

    }

    private void showData(List<InspectionInfo> inspectionInfosAdd) {
        switch (curState) {
            case STATE_INIT:
                inspectionInfos.clear();
                inspectionInfos.addAll(inspectionInfosAdd);
                inspectionAdapter.notifyDataSetChanged();
                break;
            case STATE_LOAD_MORE:
                int lastPosition = inspectionInfos.size();
                inspectionInfos.addAll(lastPosition,inspectionInfosAdd);
                inspectionAdapter.notifyItemRangeInserted(lastPosition,inspectionInfos.size());
                break;
            case STATE_REFRESH:
                inspectionInfos.clear();
                inspectionInfos.addAll(inspectionInfosAdd);
                inspectionAdapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
        if (inspectionInfos.size() == 0) {
            noResult.setVisibility(View.VISIBLE);
        } else {
            noResult.setVisibility(View.GONE);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();

    }
}
