package com.example.ananops_android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.activity.CheckAddActivity;
import com.example.ananops_android.activity.ContactActivity;
import com.example.ananops_android.activity.InspectionSerchListActivity;
import com.example.ananops_android.activity.OrderSearchListActivity;
import com.example.ananops_android.activity.OrderDetailActivity;
import com.example.ananops_android.activity.RepairAddActivity;
import com.example.ananops_android.activity.UserOrderSearchActivitySpinner;
import com.example.ananops_android.entity.UserLogin;

public class UserMainFragment extends Fragment implements View.OnClickListener{
    private LinearLayout main_repair;
    private LinearLayout main_inspection;
    private LinearLayout main_data;
    private RelativeLayout main_repair_1;
    private RelativeLayout main_repair_2;
    private RelativeLayout main_repair_3;
    private RelativeLayout main_inspection_1;
    private RelativeLayout main_inspection_2;
    private RelativeLayout main_inspection_3;
    private RelativeLayout main_data_1;
    private RelativeLayout main_data_2;
    private RelativeLayout main_data_3;
    private ImageView main_repair_img1;
    private ImageView main_repair_img2;
    private ImageView main_repair_img3;
    private ImageView main_inspection_img1;
    private ImageView main_inspection_img2;
    private ImageView main_inspection_img3;
    private ImageView main_data_img1;
    private ImageView main_data_img2;
    private ImageView main_data_img3;
    private TextView main_repair_text1;
    private TextView main_repair_text2;
    private TextView main_repair_text3;
    private TextView main_inspection_text1;
    private TextView main_inspection_text2;
    private TextView main_inspection_text3;
    private TextView main_data_text1;
    private TextView main_data_text2;
    private TextView main_data_text3;
    private TextView user_Type;
    private TextView repair_all;
    private TextView inspection_all;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View view=inflater.inflate(R.layout.fragment_user_main,container,false);
        //mLayoutManager = new LinearLayoutManager(this.getActivity());
       // mRecyclerView=view.findViewById(R.id.contact_recycler_view);
     //   uer_message=view.findViewById(R.id.main_user_message);
        //init
        user_Type=view.findViewById(R.id.user_type);
        main_repair=view.findViewById(R.id.main_repair);
        main_inspection=view.findViewById(R.id.main_inspection);
        main_data=view.findViewById(R.id.main_data);
        main_repair_1=view.findViewById(R.id.main_repair_1);
        main_repair_2=view.findViewById(R.id.main_repair_2);
        main_repair_3=view.findViewById(R.id.main_repair_3);
        main_repair_img1=view.findViewById(R.id.main_repair_img1);
        main_repair_img2=view.findViewById(R.id.main_repair_img2);
        main_repair_img3=view.findViewById(R.id.main_repair_img3);
        main_repair_text1=view.findViewById(R.id.main_order_text1);
        main_repair_text2=view.findViewById(R.id.main_order_text2);
        main_repair_text3=view.findViewById(R.id.main_order_text3);
        main_inspection_1=view.findViewById(R.id.main_inspection_1);
        main_inspection_2=view.findViewById(R.id.main_inspection_2);
        main_inspection_3=view.findViewById(R.id.main_inspection_3);
        main_inspection_img1=view.findViewById(R.id.main_inspection_img1);
        main_inspection_img2=view.findViewById(R.id.main_inspection_img2);
        main_inspection_img3=view.findViewById(R.id.main_inspection_img3);
        main_inspection_text1=view.findViewById(R.id.main_inspection_text1);
        main_inspection_text2=view.findViewById(R.id.main_inspection_text2);
        main_inspection_text2=view.findViewById(R.id.main_inspection_text2);
        main_data_1=view.findViewById(R.id.main_data_1);
        main_data_2=view.findViewById(R.id.main_data_2);
        main_data_3=view.findViewById(R.id.main_data_3);
        main_data_img1=view.findViewById(R.id.main_data_img1);
        main_data_img2=view.findViewById(R.id.main_data_img2);
        main_data_img3=view.findViewById(R.id.main_data_img3);
        main_data_text1=view.findViewById(R.id.main_data_text1);
        main_data_text2=view.findViewById(R.id.main_data_text2);
        main_data_text3=view.findViewById(R.id.main_data_text3);
        repair_all=view.findViewById(R.id.main_repair_all);
        inspection_all=view.findViewById(R.id.main_inspection_all);
        initData();
       // mRecyclerView.setLayoutManager(new GridLayoutManager(this,4,VERTICAL,false));

        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setOnListener();
    }
    private void initData() {
//        UserLogin userLogin=new UserLogin();
//        userLogin.setUseCode(2);
       // UserLogin.useCode = 2;
        switch (UserLogin.useCode){
            case 1:
                initUserData();
                break;
            case 2:
                initServiceData();
                break;
            case 3:
                initWorkData();
                break;
        }
    }

    private void initWorkData() {
        user_Type.setText(getResources().getString(R.string.REPAIR_MAN));//维修工
        main_data.setVisibility(View.GONE);//数据分析不可见
        main_inspection_3.setVisibility(View.GONE);//2.3不可见
        main_repair_img1.setImageResource(R.drawable.ic_workorder);
        main_repair_img2.setImageResource(R.drawable.ic_workorder);
        main_repair_img3.setImageResource(R.drawable.ic_workorder);
        main_repair_text1.setText(getResources().getString(R.string.repair_un_receive));//待接单
        main_repair_text2.setText(getResources().getString(R.string.repair_un_repair));//待维修
        main_repair_text3.setText(getResources().getString(R.string.repair_repairing));//维修中
        main_inspection_img1.setImageResource(R.drawable.ic_workorder);
        main_inspection_img2.setImageResource(R.drawable.ic_workorder);
        main_inspection_text1.setText(getResources().getString(R.string.un_received_check));//待巡检
        main_inspection_text2.setText(getResources().getString(R.string.receiving_check));//巡检中

    }

    private void initServiceData() {
        user_Type.setText(getResources().getString(R.string.SERVICE_MAN));//服务商
        main_repair_3.setVisibility(View.GONE);//1.3为空
        main_inspection_3.setVisibility(View.GONE);//2.3为空

        main_repair_img1.setImageResource(R.drawable.ic_workorder);
        main_repair_img2.setImageResource(R.drawable.ic_workorder);
        main_repair_text1.setText(getResources().getString(R.string.repair_un_receive));//待接单
        main_repair_text2.setText(getResources().getString(R.string.repair_un_assign_service));//待分配

        main_inspection_img1.setImageResource(R.drawable.ic_workorder);
        main_inspection_img2.setImageResource(R.drawable.ic_workorder);
        main_inspection_text1.setText(getResources().getString(R.string.add_check));//新建巡检
        main_inspection_text2.setText(getResources().getString(R.string.un_accept_check));//验收

        main_data_img1.setImageResource(R.drawable.ic_workorder);
        main_data_img2.setImageResource(R.drawable.ic_workorder);
        main_data_img3.setImageResource(R.drawable.ic_workorder);
        main_data_text1.setText(getResources().getString(R.string.worker_manage_service));//人员
        main_data_text2.setText(getResources().getString(R.string.device_manage_service));//人员
        main_data_text3.setText(getResources().getString(R.string.damage_manage_service));//人员

    }

    private void initUserData() {
        user_Type.setText(getResources().getString(R.string.USER));//用户
        main_data.setVisibility(View.GONE);//3为空
        main_repair_img1.setImageResource(R.drawable.ic_workorder);
        main_repair_img2.setImageResource(R.drawable.ic_workorder);
        main_repair_img3.setImageResource(R.drawable.ic_workorder);
        main_repair_text1.setText(getResources().getString(R.string.add_repair));//添加
        main_repair_text2.setText(getResources().getString(R.string.un_accept));//验收
        main_repair_text3.setText(getResources().getString(R.string.un_evaluate));//评价
        main_inspection_img1.setImageResource(R.drawable.ic_workorder);
        main_inspection_text1.setText(getResources().getString(R.string.see_the_result));//查看巡检结果
        main_inspection_2.setVisibility(View.GONE);//2.2为空
        main_inspection_3.setVisibility(View.GONE);//2.3为空
    }

    private void setOnListener(){
     //my_repair.setOnClickListener(this);
        main_repair_1.setOnClickListener(this);
        main_repair_2.setOnClickListener(this);
        main_repair_3.setOnClickListener(this);
        main_inspection_1.setOnClickListener(this);
        main_inspection_2.setOnClickListener(this);
        main_inspection_3.setOnClickListener(this);
        main_data_1.setOnClickListener(this);
        main_data_2.setOnClickListener(this);
        main_data_3.setOnClickListener(this);
        repair_all.setOnClickListener(this);
        inspection_all.setOnClickListener(this);
    }

    public void refresh() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_repair_1:
                switch (UserLogin.useCode){
                    case 1:
                        Intent intent=new Intent(getContext(), RepairAddActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        Intent intent1=new Intent(getContext(),OrderSearchListActivity.class);
                        intent1.putExtra("title","待接单");
                        startActivity(intent1);
                        break;
                        //待接单
                    case 3:
                        Intent intent2=new Intent(getContext(),OrderSearchListActivity.class);
                        intent2.putExtra("title","待接单");
                        startActivity(intent2);
                        break;
                }
                break;
            case R.id.main_repair_2:
                switch (UserLogin.useCode){
                    case 1:
                        Intent intent=new Intent(getContext(), OrderSearchListActivity.class);
                        intent.putExtra("title","待验收");
                        startActivity(intent);
                        break;
                    case 2:
                        Intent intent1=new Intent(getContext(), OrderSearchListActivity.class);
                        intent1.putExtra("title","待分配");
                        startActivity(intent1);
                        break;
                    case 3:
                        Intent intent2=new Intent(getContext(), OrderSearchListActivity.class);
                        intent2.putExtra("title","待维修");
                        startActivity(intent2);
                        break;
                }
                break;
            case R.id.main_repair_3:
                switch (UserLogin.useCode){
                    case 1:
                        Intent intent=new Intent(getContext(), OrderSearchListActivity.class);
                        intent.putExtra("title","待评价");
                        startActivity(intent);
                        break;
                    case 2:
                        break;
                    case 3:
                        Intent intent2=new Intent(getContext(), OrderSearchListActivity.class);
                        intent2.putExtra("title","维修中");
                        startActivity(intent2);
                        break;
                }
                break;
            case R.id.main_repair_all:
                Intent intent2=new Intent(getContext(), UserOrderSearchActivitySpinner.class);
                startActivity(intent2);
                break;
            case R.id.main_inspection_1:
                switch (UserLogin.useCode){
                    case 1:
                        Toast.makeText(getContext(),"Ops,巡检查看正在开发中",Toast.LENGTH_LONG).show();
                        Intent intent1=new Intent(getContext(), InspectionSerchListActivity.class);
                        intent1.putExtra("title","待评价");
                        startActivity(intent1);

                        break;
                    case 2:
                        Intent intent=new Intent(getContext(), CheckAddActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        Toast.makeText(getContext(),"Ops,待巡检正在开发中",Toast.LENGTH_LONG).show();
                        Intent intent3=new Intent(getContext(), InspectionSerchListActivity.class);
                        intent3.putExtra("title","待执行");
                        startActivity(intent3);
                        break;
                }
                break;
            case R.id.main_inspection_2:
                switch (UserLogin.useCode){
                    case 1:
                        break;
                    case 2:
                        Toast.makeText(getContext(),"Ops,巡检待验收正在开发中",Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(getContext(),"Ops,巡检中正在开发中",Toast.LENGTH_LONG).show();
                        Intent intent1=new Intent(getContext(), InspectionSerchListActivity.class);
                        intent1.putExtra("title","已完成");
                        startActivity(intent1);
                        break;
                }
                break;
            case R.id.main_inspection_3:
                break;
            case R.id.main_inspection_all:
                Toast.makeText(getContext(),"Ops,查看巡检正在开发中",Toast.LENGTH_LONG).show();
                break;
            case R.id.main_data_1:
                Intent intent4=new Intent(getContext(), ContactActivity.class);
                startActivity(intent4);
                break;
            case R.id.main_data_2:
                Toast.makeText(getContext(),"Ops,设备管理正在开发中",Toast.LENGTH_LONG).show();
                break;
            case R.id.main_data_3:
                Toast.makeText(getContext(),"Ops,数据展示正在开发中",Toast.LENGTH_LONG).show();
                 break;
        }
    }
    }


