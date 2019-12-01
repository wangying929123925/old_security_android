package com.example.ananops_android.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ananops_android.R;


public class OrderDetailRepairFragment extends Fragment {
    private TextView tv_repair_start_time;
    private TextView tv_repair_end_time;
    private TextView tv_repair_money;
    private TextView tv_repair_status;
    private TextView tv_repair_time;
    private TextView tv_repair_degree;
    private TextView tv_emergency_degree;
    private TextView tv_repair_description;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_order_detail_repair,container,false);
        tv_repair_start_time=view.findViewById(R.id.tv_repair_start_time);
        tv_repair_end_time=view.findViewById(R.id.tv_repair_end_time);
       tv_repair_money=view.findViewById(R.id.tv_repair_money);
        tv_repair_status=view.findViewById(R.id.tv_repair_status);
        tv_repair_time=view.findViewById(R.id.tv_repair_time);
        tv_repair_degree=view.findViewById(R.id.tv_repair_degree);
       tv_emergency_degree=view.findViewById(R.id.tv_emergency_degree);
        tv_repair_description=view.findViewById(R.id.tv_repair_description);
        initdata();

        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initdata() {
        tv_repair_start_time.setText("test");
        tv_repair_end_time.setText("test");
        tv_repair_money.setText("test");
        tv_repair_status.setText("test");
        tv_repair_time.setText("test");
        tv_repair_degree.setText("test");
        tv_emergency_degree.setText("test");
        tv_repair_description.setText("test");
    }

    }
