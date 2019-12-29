package com.example.ananops_android.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ananops_android.R;
import com.example.ananops_android.fragment.TimeLineFragment;
import com.example.ananops_android.util.BaseUtils;

import java.util.List;

public class ProjectDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private TabLayout tab_search_order_title;                            //定义TabLayout
    private ViewPager vp_search_order_pager;  //内容
    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;
    private TimeLineFragment unDoFragment;
    private TextView title;
    //  private ImageView search_img;
    private ImageView back_img;
    private static String PROJECT_ID;
    private Button order_detail_button1;
    private Button order_detail_button2;
    private LinearLayout fragment_order_commit;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        mContext=this;
        initViews();
        setOnListener();
    }
    private void initViews() {
        tab_search_order_title = findViewById(R.id.search_order_tab);
        vp_search_order_pager = findViewById(R.id.vp_search_order_pager);
        title = findViewById(R.id.txt_title);//标题
        order_detail_button1=findViewById(R.id.order_detail_button1);
        order_detail_button2=findViewById(R.id.order_detail_button2);
        fragment_order_commit=findViewById(R.id.fragment_order_commit);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        if(bundle!=null){
            PROJECT_ID=bundle.getString("project_id");}
        fragment_order_commit.setVisibility(View.VISIBLE);
        order_detail_button1.setVisibility(View.GONE);
        order_detail_button2.setVisibility(View.VISIBLE);
        order_detail_button2.setText("查看全部巡检");
    }
    private void setOnListener() {
        // search_img.setOnClickListener(this);
        back_img.setOnClickListener(this);
        order_detail_button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                break;
            case R.id.order_detail_button2:
                Bundle bundle0=new Bundle();
                bundle0.putString("project_id",PROJECT_ID);
                BaseUtils.getInstence().intent(mContext,InspectionSerchListActivity.class,bundle0);
                break;
            default:
                break;
        }
    }
}
