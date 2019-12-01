package com.example.ananops_android.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.ananops_android.R;
import com.example.ananops_android.adapter.FindTabAdapter;
import com.example.ananops_android.fragment.OrderDetailAppendix;
import com.example.ananops_android.fragment.OrderDetailAuditFragment;
import com.example.ananops_android.fragment.OrderDetailContentFragment;
import com.example.ananops_android.fragment.OrderDetailRepairFragment;
import com.example.ananops_android.fragment.TimeLineFragment;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private TabLayout tab_search_order_title;                            //定义TabLayout
    private ViewPager vp_search_order_pager;  //内容
    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;
    private TimeLineFragment unDoFragment;
    private OrderDetailRepairFragment servicingOrderFragment;
    private OrderDetailContentFragment myOrderFragment;
    private OrderDetailAppendix orderDetailAppendix;
    private OrderDetailAuditFragment orderDetailAuditFragment;
    private FindTabAdapter findTabAdapter;
    private TextView title;
  //  private ImageView search_img;
    private ImageView back_img;

      @Override
    protected void onCreate(Bundle savedInstanceState){
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_order_detail);
          initViews();
          initDatas();
          setOnListener();
      }

    private void initViews() {
        tab_search_order_title=findViewById(R.id.search_order_tab);
        vp_search_order_pager=findViewById(R.id.vp_search_order_pager);
        //title=findViewById(R.id.txt_title);
        title=findViewById(R.id.txt_title);//标题
      //  search_img=findViewById(R.id.img_search);
        back_img=findViewById(R.id.img_back);
        title.setText("工单详情");
    }
    private void initDatas() {
        list_title = new ArrayList<>();
        list_title.add("处理进度");
        list_title.add("工单详情");
        list_title.add("维修详情");
        list_title.add("审核详情");
        list_title.add("附件信息");
        list_fragment=new ArrayList<>();
       unDoFragment=new TimeLineFragment();//维修记录
        servicingOrderFragment=new OrderDetailRepairFragment();
        myOrderFragment=new OrderDetailContentFragment();
        orderDetailAuditFragment=new OrderDetailAuditFragment();
        orderDetailAppendix=new OrderDetailAppendix();
        list_fragment.add(unDoFragment);
        list_fragment.add(myOrderFragment);
       list_fragment.add(servicingOrderFragment);
        list_fragment.add(orderDetailAuditFragment);
        list_fragment.add(orderDetailAppendix);
        findTabAdapter=new FindTabAdapter(getSupportFragmentManager(),list_fragment,list_title);
        vp_search_order_pager.setAdapter(findTabAdapter);
        tab_search_order_title.setupWithViewPager(vp_search_order_pager);
        vp_search_order_pager.setOffscreenPageLimit(5);
    }
    private void setOnListener() {
         // search_img.setOnClickListener(this);
          back_img.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
          switch (v.getId()){
              case R.id.img_back:
                  finish();
                  break;
          }

    }
}
