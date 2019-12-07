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
import com.example.ananops_android.adapter.RepairAdapter;
import com.example.ananops_android.entity.RepairContent;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailContentFragment extends Fragment {
    private static String ORDER_ID;
   private TextView tv_project_name;
    private TextView tv_repair_listid;
    private TextView tv_repair_person;
    private TextView tv_repair_tel;
    private TextView tv_repair_time;
    private TextView tv_fault_type;
    private TextView tv_fault_addr;
    private TextView tv_repair_address;
    private TextView tv_fault_name;
    private TextView tv_fault_degree;
    private TextView tv_emergency_degree;
    private TextView tv_fault_description;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_order_detail_content,container,false);
        tv_project_name=view.findViewById(R.id.tv_project_name);
        tv_repair_listid=view.findViewById( R.id.tv_repair_listid);
       tv_repair_person=view.findViewById(R.id.tv_repair_person);
        tv_repair_tel=view.findViewById(R.id.tv_repair_tel);
        tv_repair_time=view.findViewById(R.id. tv_repair_time);
        tv_fault_type=view.findViewById(R.id.tv_fault_type);
        tv_fault_addr=view.findViewById(R.id.tv_fault_addr);
        tv_repair_address=view.findViewById(R.id.tv_repair_address);
        tv_fault_name=view.findViewById(R.id. tv_fault_name);
       tv_fault_degree=view.findViewById(R.id.tv_fault_degree);
        tv_emergency_degree=view.findViewById(R.id.tv_emergency_degree);
        tv_fault_description=view.findViewById(R.id.tv_fault_description);
        initdata();
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initdata() {
        if(!(getArguments()==null)){
            ORDER_ID=(String) getArguments().get("order_id");}
        tv_project_name.setText("测试");
        tv_repair_listid.setText("测试");
        tv_repair_person.setText("测试");
        tv_repair_tel.setText("测试");
        tv_repair_time.setText("测试");
        tv_fault_type.setText("测试");
        tv_fault_addr.setText("测试");
        tv_repair_address.setText("测试");
        tv_fault_name.setText("测试");
        tv_fault_degree.setText("测试");
        tv_emergency_degree.setText("测试");
        tv_fault_description.setText("测试");
    }
}
