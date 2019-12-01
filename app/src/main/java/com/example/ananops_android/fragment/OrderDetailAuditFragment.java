package com.example.ananops_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.ananops_android.R;
import com.example.ananops_android.adapter.RepairAdapter;
import com.example.ananops_android.entity.RepairContent;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailAuditFragment extends Fragment {
    private TextView tv_order_status;
    private TextView tv_audit_man;
    private TextView tv_audit_suggestion;
    private TextView tv_handle_suggestion;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_order_detail_audit,container,false);
        tv_order_status=view.findViewById(R.id.tv_order_status);
        tv_audit_man=view.findViewById(R.id.tv_audit_man);
        tv_audit_suggestion=view.findViewById(R.id.tv_audit_suggestion);
        tv_handle_suggestion=view.findViewById(R.id.tv_handle_suggestion);
        //init
        initdata();
        // mRecyclerView.setLayoutManager(new GridLayoutManager(this,4,VERTICAL,false));
        return view;
    }

    private void initdata() {
        tv_order_status.setText("no");
        tv_audit_man.setText("no");
        tv_audit_suggestion.setText("no");
        tv_handle_suggestion.setText("no");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


}
