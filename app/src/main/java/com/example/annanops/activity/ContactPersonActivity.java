package com.example.annanops.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.annanops.adapter.FindTabAdapter;
import com.example.annanops.fragment.ContactDetailFragment;
import com.example.annanops.fragment.UnDoFragment;
import com.example.annaops.R;

import java.util.ArrayList;
import java.util.List;

public class ContactPersonActivity extends AppCompatActivity {
    public static final String CONTACT_ID="contact_id";
    public static final String CONTACT_NAME="contact_name";
    public static final String DEPART="depart";
    public static final String CONTACT_NUMBER="contact_number";
    private TabLayout tab_contact_title;                            //定义TabLayout
    private ViewPager vp_contact_pager;  //内容
    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;
    private ContactDetailFragment contactDetailFragment;
    private UnDoFragment contactDetailFragment1;
    private FindTabAdapter findTabAdapter;
    public String contact_id;
    private String contact_name;
    public String depart;
    public String contact_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_person);
        initViews();
        initDatas();
    }

    private void initViews() {
        Toolbar toolbar=findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar=findViewById(R.id.collapsing_toolbar);
        ImageView pandaImageView=findViewById(R.id.contact_image_view);
         Glide.with(this).load(R.drawable.haoyue).into(pandaImageView);//载入图片
        tab_contact_title=findViewById(R.id.contact_tab);
        vp_contact_pager=findViewById(R.id.vp_contact_pager);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
        contact_id=intent.getStringExtra(CONTACT_ID);
        contact_name=intent.getStringExtra(CONTACT_NAME);
        depart=intent.getStringExtra(DEPART);
        contact_phone=intent.getStringExtra(CONTACT_NUMBER);
        collapsingToolbar.setTitle(contact_name);
    }
    private void initDatas() {

        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        list_title = new ArrayList<>();
        list_title.add("详情");
        list_title.add("维修记录");
        //设置TabLayout的模式
      //  tab_contact_title.setTabMode(TabLayout.MODE_FIXED);
       // tab_contact_title.addTab(tab_contact_title.newTab().setText(list_title.get(0)));
        //tab_contact_title.addTab(tab_contact_title.newTab().setText(list_title.get(1)));
        list_fragment=new ArrayList<>();
        contactDetailFragment=new ContactDetailFragment();//基本信息
       // contactDetailFragment.ID.setText(contact_id);
       // contactDetailFragment.DEPART.setText(depart);
      //  contactDetailFragment.PHONE_NUMBER.setText(contact_phone);

        contactDetailFragment1=new UnDoFragment();//维修记录
        list_fragment.add(contactDetailFragment);
        list_fragment.add(contactDetailFragment1);
        findTabAdapter=new FindTabAdapter(getSupportFragmentManager(),list_fragment,list_title);
        //viewpager加载adapter
       vp_contact_pager.setAdapter(findTabAdapter);
        //TabLayout加载viewpager
        tab_contact_title.setupWithViewPager(vp_contact_pager);
       vp_contact_pager.setOffscreenPageLimit(2);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
