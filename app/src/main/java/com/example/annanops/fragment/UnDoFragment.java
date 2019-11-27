package com.example.annanops.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.annaops.R;
import com.example.annanops.adapter.TimeLineAdapter;
import com.example.annanops.entity.TimeLine;

import java.util.ArrayList;
import java.util.List;

public class UnDoFragment extends Fragment {
    private List<TimeLine> timeLines=new ArrayList<>();
    private TimeLineAdapter adapter;//适配器
    private ListView mListView;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.order_time_line,container,false);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mListView=view.findViewById(R.id.lv_time_line);
        adapter=new TimeLineAdapter(getContext(),timeLines);
        mListView.setAdapter(adapter);
        initdata();
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initdata() {
       TimeLine timeLine=new TimeLine();
       timeLine.setOrderDirector("试用用户");
       timeLine.setProcessStatus("执行维修");
       timeLine.setProcessTime("2019-11-25 15:05:02");
       timeLines.add(timeLine);
       TimeLine timeLine1=new TimeLine();
        timeLine1.setOrderDirector("试用用户");
        timeLine1.setProcessStatus("派工给试用用户，待维修");
        timeLine1.setProcessTime("2019-11-25 13:05:02");
        timeLines.add(timeLine1);
    }

                  }

