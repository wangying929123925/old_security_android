package com.example.weather.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weather.R;
import com.example.weather.entity.RepairContent;
import com.example.weather.util.RepairAdapter;

import java.util.ArrayList;
import java.util.List;

public class UnDoFragment extends Fragment {
    private List<RepairContent> repairContents=new ArrayList<>();
    private RepairAdapter adapter;//适配器
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private SwipeRefreshLayout swipe_refresh_order_search;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.research_order_display,container,false);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView=view.findViewById(R.id.contact_recycler_view);
        swipe_refresh_order_search=view.findViewById(R.id.swipe_refresh_order_search);
        swipe_refresh_order_search.setColorSchemeResources(R.color.colorPrimary);//下拉刷新进度条颜色
        swipe_refresh_order_search.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {//通常onRefresh是去网络上请求最新数据，这里将线程沉睡2秒
                refreshOrders();
            }
        });
        initdata();
        // mRecyclerView.setLayoutManager(new GridLayoutManager(this,4,VERTICAL,false));
        mRecyclerView.setLayoutManager(mLayoutManager);
        adapter=new RepairAdapter(repairContents);
        mRecyclerView.setAdapter(adapter);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initdata() {
        RepairContent repairContent=new RepairContent();
        repairContent.setRepair_id("2019140282");
        repairContent.setCheck_group("第一组");
        repairContent.setRepair_address("科研楼一楼");
        repairContent.setRepair_man("李民浩");
        repairContent.setRepair_time("2019-11-11 18:39");
        repairContent.setRepair_content("笔记本电脑");
        repairContent.setRepair_status("已报修");
        repairContents.add(repairContent);
        repairContents.add(repairContent);
        repairContents.add(repairContent);
        repairContents.add(repairContent);
        repairContents.add(repairContent);
        repairContents.add(repairContent);
        repairContents.add(repairContent);
        repairContents.add(repairContent);
        repairContents.add(repairContent);
    }
    private void refreshOrders() {

                      new Handler().postDelayed(new Runnable() {
                          @Override
                              public void run() {
                                    // TODO Auto-generated method stub
                              swipe_refresh_order_search.setRefreshing(false);
                          }
                                    }, 1000);

                  }

}
