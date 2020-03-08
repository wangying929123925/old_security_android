package com.example.ananops_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ananops_android.R;

import java.util.ArrayList;

public class OrderDetailContentFragment extends Fragment {
    private static String ORDER_ID;
   private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;
    private TextView tv8;
    private TextView tv9;
    private TextView tv10;
    private TextView phone;
    private TextView address;
    private ArrayList<String> contents;

    public static OrderDetailContentFragment newInstance(ArrayList<String> contents) {
        OrderDetailContentFragment orderDetailContentFragment = new OrderDetailContentFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("data",contents);
        orderDetailContentFragment.setArguments(bundle);
        return orderDetailContentFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_order_detail_content,container,false);
        tv1=view.findViewById(R.id.tv_project_name);
        tv2=view.findViewById( R.id.tv_company_name);
        tv3=view.findViewById(R.id.tv_repair_person);
        tv4=view.findViewById(R.id.tv_repair_tel);
        tv5=view.findViewById(R.id. tv_repair_time);
        tv6=view.findViewById(R.id.tv_repair_address);
        tv7=view.findViewById(R.id.tv_emergency_degree);
        tv8=view.findViewById(R.id.tv_fault_description);
        phone=view.findViewById(R.id.go_to_person);
        address=view.findViewById(R.id.go_to_address);
        initdata();
        setOnListener();
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initdata() {
        if(!(getArguments()==null)){
            contents=getArguments().getStringArrayList("data");
            tv1.setText(contents.get(0));
            tv2.setText(contents.get(1));
            tv3.setText(contents.get(2));
            tv4.setText(contents.get(3));
            tv5.setText(contents.get(4));
            tv6.setText(contents.get(5));
            tv7.setText(contents.get(6));
            tv8.setText(contents.get(7));
        }
    }

    private void setOnListener() {
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
