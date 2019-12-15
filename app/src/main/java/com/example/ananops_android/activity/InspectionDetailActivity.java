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
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_detail);
        initViews();
        initDatas();
        setOnListener();
    }
    private void initViews(){

    }
    private void initDatas(){

    }
    private void setOnListener(){

    }
}
