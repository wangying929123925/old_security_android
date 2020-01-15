package com.example.ananops_android.activity;

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
import com.example.ananops_android.adapter.FindTabAdapter;
import com.example.ananops_android.adapter.MyFragmentPagerAdapter;
import com.example.ananops_android.db.TestResponse;
import com.example.ananops_android.fragment.InspectionItemFragment;

import java.util.ArrayList;
import java.util.List;

public class InspectionItemDetailActivity extends AppCompatActivity {
    private TabLayout tab_search_order_title;                            //定义TabLayout
    private ViewPager vp_search_order_pager;  //内容
    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;
    private FindTabAdapter findTabAdapter;
    private TextView title;
    private ImageView back_img;
    private static String INSPECTION_ID;
    private Button inspection_detail_button1;
    private Button inspection_detail_button2;
    private LinearLayout fragment_inspection_commit;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private String STATUS = "1"; //有几个角色就设置几个不同的状态 对应不同fragment数据的显示

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_item_detail);
        initFragment();
        initViews();
        initDatas();
        INSPECTION_ID = getIntent().getStringExtra("inspectionId");
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), list_fragment,list_title);
        //viewPager设置adapter
        vp_search_order_pager.setAdapter(myFragmentPagerAdapter);
        vp_search_order_pager.setOffscreenPageLimit(1);
        tab_search_order_title.setupWithViewPager(vp_search_order_pager);

    }

    private void initFragment() {

        if (list_title == null) {
            list_title = new ArrayList<>();
            list_title.add("项目信息");list_title.add("设备信息");
            list_title.add("故障信息");list_title.add("审核信息");
        }
        if (list_fragment == null) {
            list_fragment = new ArrayList<>();
        }
        ArrayList<String> list_item1 = new ArrayList<>();
        ArrayList<String> list_item2 = new ArrayList<>();
        ArrayList<String> list_item3 = new ArrayList<>();
        ArrayList<String> list_item4 = new ArrayList<>();
        ArrayList<String> list_value1 = new ArrayList<>();
        ArrayList<String> list_value2 = new ArrayList<>();
        ArrayList<String> list_value3 = new ArrayList<>();
        ArrayList<String> list_value4 = new ArrayList<>();
        //首先把数据做全量填充
        //the first fragment
        list_item1.add("项目编号");list_item1.add("项目名称");
        list_item1.add("甲方");list_item1.add("甲方负责人");list_item1.add("联系电话");
        list_item1.add("乙方");list_item1.add("乙方负责人");list_item1.add("联系电话");
        TestResponse bean = new TestResponse();
        TestResponse.ResultBean result = bean.getResult();
        list_value1.add(String.valueOf(bean.getCode()));
        list_value1.add(bean.getMessage());
        list_value1.add(result.getCreator());
        list_value1.add(result.getCreator());
        list_value1.add(String.valueOf(result.getPageNum()));
        list_value1.add(result.getCreator());
        list_value1.add(result.getCreator());
        list_value1.add(String.valueOf(result.getPageNum()));
        //the second fragment
        list_item2.add("设备编号");list_item2.add("设备名称");
        list_item2.add("设备位置");list_item2.add("报修人");list_item2.add("联系电话");
        list_value2.add(String.valueOf(bean.getCode()));
        list_value2.add(String.valueOf(bean.getCode()));
        list_value2.add(result.getCreator());
        list_value2.add(result.getCreator());
        list_value2.add(String.valueOf(result.getPageNum()));
        //the third fragment
        list_item3.add("故障程度");list_item3.add("故障等级");
        list_item3.add("故障类型");list_item3.add("故障描述");
        list_value3.add(String.valueOf(bean.getCode()));
        list_value3.add(String.valueOf(bean.getCode()));
        list_value3.add(result.getCreator());
        list_value3.add(result.getCreator());
        //the fourth fragment
        list_item4.add("单据状态");list_item4.add("报修单号");
        list_item4.add("审核人");list_item4.add("审核意见");list_item4.add("维修人员");
        list_item4.add("计划时间");list_item4.add("处理意见");
        list_value4.add(String.valueOf(bean.getCode()));
        list_value4.add(String.valueOf(bean.getCode()));
        list_value4.add("");
        list_value4.add(result.getCreator());
        list_value4.add("");
        list_value4.add(result.getCreator());
        list_value4.add("");
        switch (STATUS) {
            //根据不同的用户权限 做小量调整
            case "1":

                /*the first fragment*/
                    //去掉乙方相关信息
                List<String> subList =new ArrayList<>() ;
                for (String s:
                        list_item1.subList(0, 5)) {  //保留前五个
                    subList.add(s);
                }
                list_item1.clear();//清空原list
                for (String s:
                        subList) {
                    list_item1.add(s);//赋值给原list
                }
                List<String> list0 = new ArrayList<>();

                for (String s:
                        list_value1.subList(0, 5)) {
                    list0.add(s);
                }
                list_value1.clear();
                for (String s:
                        list0) {
                    list_value1.add(s);
                }
                /*the second fragment*/
//                list_item2.add("设备编号");list_item2.add("设备名称");
                /*the third fragment*/
//                list_item3.add("故障程度");list_item3.add("故障等级");
                /*the fourth fragment*/
//                list_item4.add("单据状态");list_item4.add("报修单号");

                break;
            case "2":

                break;
            default:
                break;

        }
        list_fragment.add(InspectionItemFragment.newIntance("1",list_item1,list_value1));
        list_fragment.add(InspectionItemFragment.newIntance("1",list_item2,list_value2));
        list_fragment.add(InspectionItemFragment.newIntance("1",list_item3,list_value3));
        list_fragment.add(InspectionItemFragment.newIntance("1",list_item4,list_value4));
    }

    private void initViews(){
        vp_search_order_pager = findViewById(R.id.vp_search_inspection_pager);
        tab_search_order_title = findViewById(R.id.search_inspection_tab);
    }
    private void initDatas(){
        title = findViewById(R.id.txt_title);//标题
        back_img=findViewById(R.id.img_back);
        title.setText("项目详情");
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
