package com.example.ananops_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;


public class UserDeviceFragment extends Fragment implements View.OnClickListener {
    private TextView add_device;
    private TextView my_apply;
    private TextView my_bill;
    private TextView device_use;
    private TextView  device_recycle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_device, container, false);
        add_device=view.findViewById(R.id.apply_device);
        my_apply=view.findViewById(R.id.fra_my_apply);
        my_bill=view.findViewById(R.id.fra_my_bill);
        device_use=view.findViewById(R.id.fra_device_use);
        device_recycle=view.findViewById(R.id.fra_device_recycle);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setOnListener();
        inirDatas();
    }

    private void setOnListener() {
        add_device.setOnClickListener(this);
        my_apply.setOnClickListener(this);
        my_bill.setOnClickListener(this);
        device_use.setOnClickListener(this);
        device_recycle.setOnClickListener(this);
    }

    private void inirDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.apply_device:
                Toast.makeText(this.getContext(),"Ops,添加设备正在开发中",Toast.LENGTH_LONG).show();
                break;
            case R.id.fra_my_apply:
                    Toast.makeText(this.getContext(),"Ops,我的申请正在开发中",Toast.LENGTH_LONG).show();
                 break;
            case R.id.fra_my_bill:
                 Toast.makeText(this.getContext(),"Ops,我的挂账正在开发中",Toast.LENGTH_LONG).show();
                 break;
            case R.id.fra_device_use:
                 Toast.makeText(this.getContext(),"Ops,使用设备正在开发中",Toast.LENGTH_LONG).show();
                break;
             case R.id.fra_device_recycle:
                  Toast.makeText(this.getContext(),"Ops,设备回收正在开发中",Toast.LENGTH_LONG).show();
                 break;
        }
    }
}
