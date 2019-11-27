package com.example.annanops.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.annanops.adapter.FindTabAdapter;
import com.example.annanops.fragment.MyOrderFragment;
import com.example.annanops.fragment.ServicingOrderFragment;
import com.example.annanops.fragment.UnDoFragment;
import com.example.annaops.R;

import java.util.ArrayList;
import java.util.List;

public class OrderSearchActivity extends AppCompatActivity implements View.OnClickListener {
    private TabLayout tab_search_order_title;                            //定义TabLayout
    private ViewPager vp_search_order_pager;  //内容
    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;
    private UnDoFragment unDoFragment;
    private ServicingOrderFragment servicingOrderFragment;
    private MyOrderFragment myOrderFragment;
    private FindTabAdapter findTabAdapter;
    private TextView title;
  //  private ImageView search_img;
    private ImageView back_img;

      @Override
    protected void onCreate(Bundle savedInstanceState){
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_research_order);
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
       // list_title.add("备件信息");
        list_fragment=new ArrayList<>();
       unDoFragment=new UnDoFragment();//维修记录
        servicingOrderFragment=new ServicingOrderFragment();
        myOrderFragment=new MyOrderFragment();
        list_fragment.add(unDoFragment);
       list_fragment.add(servicingOrderFragment);
        list_fragment.add(myOrderFragment);
        findTabAdapter=new FindTabAdapter(getSupportFragmentManager(),list_fragment,list_title);
        vp_search_order_pager.setAdapter(findTabAdapter);
        tab_search_order_title.setupWithViewPager(vp_search_order_pager);
        vp_search_order_pager.setOffscreenPageLimit(3);
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
