package com.example.ananops_android.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.adapter.RepairAdapter;
import com.example.ananops_android.db.OrderRequest;
import com.example.ananops_android.db.OrderResponse;
import com.example.ananops_android.entity.RepairContent;
import com.example.ananops_android.net.Net;
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

public class OrderSearchListActivity extends BaseActivity implements View.OnClickListener {
    private String searchType;
    private  String searchContent;
    private List<RepairContent> repairContents=new ArrayList<>();
    private RepairAdapter adapter;//适配器
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView title;
    private ImageView back_img;
    private EditText search_content;//搜索内容
    private TextView search_text;//
    private static String TITLE;
    private Context mContext;
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

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mContext=this;
      //  ActivityManager.getInstance().addActivity(this);
        setContentView(R.layout.activity_research_order_list);
       // initDatas();
        initViews();
        setOnListener();
    }
    private void initViews() {
        title = findViewById(R.id.txt_title);//标题
        //search_img=findViewById(R.id.img_search);
        back_img = findViewById(R.id.img_back);
        search_content = findViewById(R.id.text_search);
        search_text = findViewById(R.id.search_title_txt);
        searchType = "项目类型";
        noResult = findViewById(R.id.no_result_text);
        Intent intent = getIntent();
        TITLE = intent.getStringExtra("title");
        title.setText("维修列表");
       // repairContents = BaseUtils.getInstence().getRepairList(repairContents,orderRequest,mContext);
        adapter = new RepairAdapter(repairContents);
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView = findViewById(R.id.contact_recycler_view);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);
        getDatas(curPage);
        //设置refreshLayout的一些操作
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
                getDatas(curPage);
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
                 getDatas(curPage);
                    //  more
                } else {
                    Toast.makeText(mContext, "没有更多啦O(∩_∩)O", Toast.LENGTH_SHORT).show();
                }
             mRefreshLayout.finishLoadMore();
            }
        });
    }
    private void getDatas(int curPageNum) {
        int status;
      //  Log.v("Status",String.valueOf(status));
        OrderRequest orderRequest=new OrderRequest();
        orderRequest.setId(SPUtils.getInstance(mContext).getString("user_id",""));
        if (TITLE != null) {
            status = Integer.parseInt(TITLE);
            orderRequest.setStatus(status);
        }else {orderRequest.setStatus(null);}
        orderRequest.setPageNum(curPageNum);
        orderRequest.setPageSize(pageSize);
        orderRequest.setRoleCode(SPUtils.getInstance(mContext).getString("role_code",""));
        Net.instance.getRepairList(orderRequest, SPUtils.getInstance(mContext).getString("Token"," "))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OrderResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                       // Log.v("LoginTime", System.currentTimeMillis() + "");
                       // e.printStackTrace();
                        Toast.makeText(mContext, "获取维修列表失败：)", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(OrderResponse orderResponse) {
                        if(TextUtils.equals(orderResponse.getCode(),"200")){
                            List<RepairContent> repairContentAdds = new ArrayList<>();
                            repairContentAdds.addAll(orderResponse.getResult().getList());
                            totalPage = orderResponse.getResult().getPages();
                           showData(repairContentAdds);
                        }
                        else{
                            Toast.makeText(mContext, orderResponse.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                });

    }

    private void setOnListener() {
        back_img.setOnClickListener(this);
    //    search_text.setOnClickListener(this);
        search_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               // searchContent=search_content.getText().toString().trim();
                showSearchResult(searchType,s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void showData(List<RepairContent> repairContentAdds) {
        switch (curState) {
            case STATE_INIT:
                repairContents.clear();
                repairContents.addAll(repairContentAdds);
                adapter.notifyDataSetChanged();
                break;
            case STATE_LOAD_MORE:
                int lastPosition = repairContents.size();
                repairContents.addAll(lastPosition,repairContentAdds);
                adapter.notifyItemRangeInserted(lastPosition,repairContents.size());
                break;
            case STATE_REFRESH:
                repairContents.clear();
                repairContents.addAll(repairContentAdds);
                adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
        if (repairContents.size() == 0) {
            noResult.setVisibility(View.VISIBLE);
        } else {
            noResult.setVisibility(View.GONE);
        }
    }
    private void getData() {

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.search_title_txt:

                break;
        }
    }
    private void changeButton(Button button){

    }
    private void showSearchResult(String searchType,String searchContent){
        List<RepairContent> repairContentsFilterd=new ArrayList<>();
        if (TextUtils.isEmpty(searchContent)) {
            repairContentsFilterd = repairContents;
        }else {
            repairContentsFilterd.clear();
            for (RepairContent repairContent : repairContents) {
                String name = repairContent.getTitle();
                Pattern p = Pattern.compile(searchContent);
                Matcher m = p.matcher(name);
                if (m.find()) {
                    repairContentsFilterd.add(repairContent);
                }
            }
        }
            adapter.updateList(repairContentsFilterd);
            if (repairContentsFilterd.size() == 0) {
                noResult.setVisibility(View.VISIBLE);
            } else {
                noResult.setVisibility(View.GONE);
            }
        }


    @Override
    protected void onResume() {
        super.onResume();
        getDatas(1);
        curState = STATE_INIT;
    }
}
