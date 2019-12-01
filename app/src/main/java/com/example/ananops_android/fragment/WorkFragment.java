package com.example.ananops_android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.ananops_android.R;
import com.example.ananops_android.activity.ContactActivity;
import com.example.ananops_android.activity.RepairAddActivity;

public class WorkFragment extends Fragment implements View.OnClickListener {
    private RelativeLayout casual_inspection_1;
    private RelativeLayout new_check_1;
    private RelativeLayout check_manage_1;
    private RelativeLayout team_manage_1;
    private RelativeLayout statistic_analysis_1;
    private RelativeLayout repairman_manage_1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.work_navigation,container,false);
        casual_inspection_1=view.findViewById(R.id.casual_inspection_1);
        new_check_1=view.findViewById(R.id.new_check_1);
        check_manage_1=view.findViewById(R.id.check_manage_1);
        team_manage_1=view.findViewById(R.id.team_manage_1);
        statistic_analysis_1=view.findViewById(R.id.statistic_analysis_1);
        repairman_manage_1=view.findViewById(R.id.repairman_manage_1);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setOnListener();
    }

    private void setOnListener() {
        casual_inspection_1.setOnClickListener(this);
        new_check_1.setOnClickListener(this);
        check_manage_1.setOnClickListener(this);
        team_manage_1.setOnClickListener(this);
        statistic_analysis_1.setOnClickListener(this);
        repairman_manage_1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.casual_inspection_1:
                Intent intent=new Intent(getContext(), RepairAddActivity.class);
                startActivity(intent);
                break;
            case  R.id.new_check_1:
                break;
            case R.id.check_manage_1:
                break;
            case R.id.team_manage_1:
                Intent intent1=new Intent(getContext(), ContactActivity.class);
                startActivity(intent1);
                break;
            case R.id.statistic_analysis_1:
                break;
            case R.id.repairman_manage_1:
                break;
        }
    }

}
