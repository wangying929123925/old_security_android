package com.example.ananops_android.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ananops_android.R;
import com.example.ananops_android.adapter.FindTabAdapter;
import com.example.ananops_android.adapter.MyFragmentPagerAdapter;
import com.example.ananops_android.fragment.InspectionFragment;

import java.util.ArrayList;
import java.util.List;

public class InspectionDetailActivity extends AppCompatActivity {
    private TabLayout tab_search_order_title;                            //定义TabLayout
    private ViewPager vp_search_order_pager;  //内容
    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;
    private FindTabAdapter findTabAdapter;
    private TextView title;
    private ImageView back_img;
    private static String ACTIVITY_STATUS;
    private static String INSPECTION_ID;
    private Button inspection_detail_button1;
    private Button inspection_detail_button2;
    private LinearLayout fragment_inspection_commit;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_detail);

        initFragment();
        initViews();
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), list_fragment);
        vp_search_order_pager.setAdapter(myFragmentPagerAdapter);
        initDatas();
        setOnListener();
    }

    private void initFragment() {
        if (list_fragment == null) {
            list_fragment = new ArrayList<>();
            ArrayList<String> list_title1 = new ArrayList<>();
            list_title1.add("项目编号");list_title1.add("项目名称");
            list_title1.add("甲方");list_title1.add("甲方负责人");list_title1.add("联系电话");
            list_title1.add("乙方");list_title1.add("联系电话");
            list_fragment.add(InspectionFragment.newIntance(list_title1));
            ArrayList<String> list_title2 = new ArrayList<>();
            list_title2.add("设备编号");list_title2.add("设备名称");
            list_title2.add("设备位置");list_title2.add("报修人");list_title2.add("联系电话");
            list_fragment.add(InspectionFragment.newIntance(list_title2));
            ArrayList<String> list_title3 = new ArrayList<>();
            list_title3.add("故障程度");list_title3.add("故障等级");
            list_title3.add("故障类型");list_title3.add("故障描述");
            list_fragment.add(InspectionFragment.newIntance(list_title3));
            ArrayList<String> list_title4 = new ArrayList<>();
            list_title4.add("单据状态");list_title4.add("报修单号");
            list_title4.add("审核人");list_title4.add("审核意见");list_title4.add("维修人员");
            list_title4.add("计划时间");list_title4.add("处理意见");
            list_fragment.add(InspectionFragment.newIntance(list_title4));
        }
    }

    private void initViews(){
        vp_search_order_pager = findViewById(R.id.vp_search_inspection_pager);
    }
    private void initDatas(){

    }
    private void setOnListener(){

    }
}
