package com.example.annanops.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.annanops.entity.RepairContent;
import com.example.annaops.R;
import com.example.annanops.adapter.RepairAdapter;

import java.util.ArrayList;
import java.util.List;

public class OrderResearchInputActivity extends AppCompatActivity implements View.OnClickListener {
    private String searchType;
    private  String searchContent;
    private Button[] buttons=new Button[4];
    private List<RepairContent> repairContents=new ArrayList<>();
    private RepairAdapter adapter;//适配器
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView title;
    private ImageView search_img;
    private ImageView back_img;
    private EditText search_content;
    private TextView search_text;
    private Button project_type;
    private Button order_type;
    private Button unit_type;
    private Button repair_date;
    private int index;
    private int currentTabIndex=0;// 当前fragment的index
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_order_input);
        initViews();
        initDatas();
        setOnListener();
    }
    private void initViews() {
        title=findViewById(R.id.txt_title);//标题
        //search_img=findViewById(R.id.img_search);
        back_img=findViewById(R.id.img_back);
        search_content=findViewById(R.id.text_search);
        search_text=findViewById(R.id.search_title_txt);
        project_type=findViewById(R.id.search_order_project_type);
        order_type=findViewById(R.id.search_order_type);
        unit_type=findViewById(R.id.search_order_unit_type);
        repair_date=findViewById(R.id.search_order_date);
        buttons[0]=project_type;
        buttons[1]=order_type;
        buttons[2]=unit_type;
        buttons[3]=repair_date;
        buttons[0].setSelected(true);
        searchType="项目类型";
        title.setText("工单查询");
        search_img.setVisibility(View.INVISIBLE);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView=findViewById(R.id.contact_recycler_view);
        mRecyclerView.setLayoutManager(mLayoutManager);
        adapter=new RepairAdapter(repairContents);
        mRecyclerView.setAdapter(adapter);
    }
    private void initDatas() {
        RepairContent repairContent=new RepairContent();
        repairContent.setRepair_id("2019140282");
        repairContent.setCheck_group("第一组");
        repairContent.setRepair_address("科研楼一楼");
        repairContent.setRepair_man("李民浩");
        repairContent.setRepair_time("2019-11-11 18:39");
        repairContent.setRepair_content("笔记本电脑");
        repairContent.setRepair_status("待维修");
        RepairContent repairContent1=new RepairContent();
        repairContent1.setRepair_id("2019140282");
        repairContent1.setCheck_group("第一组");
        repairContent1.setRepair_address("科研楼一楼");
        repairContent1.setRepair_man("李民浩");
        repairContent1.setRepair_time("2019-11-11 18:39");
        repairContent1.setRepair_content("笔记本电脑");
        repairContent1.setRepair_status("维修中");
        repairContents.add(repairContent);
        repairContents.add(repairContent);
        repairContents.add(repairContent);
        repairContents.add(repairContent);
        repairContents.add(repairContent1);
        repairContents.add(repairContent1);
        repairContents.add(repairContent1);
        repairContents.add(repairContent1);
        repairContents.add(repairContent1);
    }

    private void setOnListener() {
        back_img.setOnClickListener(this);
        search_text.setOnClickListener(this);
        project_type.setOnClickListener(this);
        order_type.setOnClickListener(this);
        unit_type.setOnClickListener(this);
        repair_date.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.search_title_txt:
                searchContent=search_content.getText().toString().trim();
                showSearchResult(searchType,searchContent);
                break;
            case R.id.search_order_project_type:
                searchType="项目类型";
                search_content.setHint("请输入项目类型");
                index=0;
                break;
            case R.id.search_order_type:
                searchType="工单类型";
                search_content.setHint("请输入工单类型");
                index=1;
                break;
            case R.id.search_order_unit_type:
                searchType="维修单位";
                search_content.setHint("请输入维修单位");
                index=2;
                break;
            case R.id.search_order_date:
                searchType="维修日期";
                search_content.setHint("请输入维修日期");
                index=3;
                break;
        }
        if(currentTabIndex!=index){
            buttons[currentTabIndex].setSelected(false);
            buttons[index].setSelected(true);
          buttons[currentTabIndex].setTextColor(getResources().getColor(R.color.black));
           buttons[index].setTextColor(getResources().getColor(R.color.state_pressed));
            currentTabIndex=index;
        }
    }
    private void changeButton(Button button){

    }
    private void showSearchResult(String searchType,String searchContent){

    }
}
