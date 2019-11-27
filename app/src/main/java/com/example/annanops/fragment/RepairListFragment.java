package com.example.annanops.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.annaops.R;
import com.example.annanops.entity.RepairContent;
import com.example.annanops.adapter.RepairAdapter;

import java.util.ArrayList;
import java.util.List;

public class RepairListFragment extends Fragment implements View.OnClickListener {
    private List<RepairContent> repairContents=new ArrayList<>();
    private RepairAdapter adapter;//适配器
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RelativeLayout new_repair;
    private RelativeLayout un_repair;
    private RelativeLayout repaired;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_user_main,container,false);
        new_repair=view.findViewById(R.id.board_new_repair);
        un_repair=view.findViewById(R.id.board_un_repair);
        repaired=view.findViewById(R.id.board_repaired);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView=view.findViewById(R.id.recycler_view);
        //init
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
        setOnListener();
    }

    private void setOnListener() {
        new_repair.setOnClickListener(this);
        un_repair.setOnClickListener(this);
        repaired.setOnClickListener(this);
    }

    public void refresh() {

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.board_new_repair:
                repairContents=getNewRepair();
                adapter.notifyDataSetChanged();
                break;
            case R.id.board_un_repair:
                repairContents=getUnRepair();
                adapter.notifyDataSetChanged();
                break;
            case R.id.board_repaired:
                repairContents=getRepaired();
                adapter.notifyDataSetChanged();
                break;
        }
    }
    private List<RepairContent> getRepaired() {
        return repairContents ;
    }

    private List<RepairContent> getUnRepair() {
        return repairContents ;
    }

    private List<RepairContent> getNewRepair() {
        return repairContents ;
    }
    private void initdata() {
        RepairContent repairContent=new RepairContent();
        repairContent.setRepair_id("2019140282");
        repairContent.setCheck_group("第一组");
        repairContent.setRepair_address("科研楼一楼");
        repairContent.setRepair_man("李民浩");
        repairContent.setRepair_time("2019-11-11 18:39");
        repairContent.setRepair_content("笔记本电脑");
        repairContent.setRepair_status("待维修");
        RepairContent repairContent1=new RepairContent();
        repairContent1.setRepair_id("2019140282");
        repairContent1.setCheck_group("第一组");
        repairContent1.setRepair_address("科研楼一楼");
        repairContent1.setRepair_man("李民浩");
        repairContent1.setRepair_time("2019-11-11 18:39");
        repairContent1.setRepair_content("笔记本电脑");
        repairContent1.setRepair_status("维修中");
        repairContents.add(repairContent);
        repairContents.add(repairContent);
        repairContents.add(repairContent);
        repairContents.add(repairContent);
        repairContents.add(repairContent1);
        repairContents.add(repairContent1);
        repairContents.add(repairContent1);
        repairContents.add(repairContent1);
        repairContents.add(repairContent1);
    }
}
