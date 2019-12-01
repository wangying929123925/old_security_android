package com.example.ananops_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.adapter.BaseRecyclerAdapter;
import com.example.ananops_android.adapter.BaseViewHolder;
import com.example.ananops_android.adapter.InspectionAdapter;
import com.example.ananops_android.adapter.RepairAdapter;
import com.example.ananops_android.entity.InspectionContent;

import java.util.ArrayList;
import java.util.List;

public class InspectionSerchListActivity extends AppCompatActivity implements View.OnClickListener{
    private  String searchContent;
    private List<InspectionContent> inspectionContents=new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView title;
    private ImageView back_img;
    private EditText search_content;
    private TextView search_text;
    private static String TITLE;
    private InspectionAdapter inspectionAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_order_list);
        initDatas();
        initViews();
        setOnListener();
    }
    private void initViews() {
        title=findViewById(R.id.txt_title);//标题
        //search_img=findViewById(R.id.img_search);
        back_img=findViewById(R.id.img_back);
        search_content=findViewById(R.id.text_search);
        search_text=findViewById(R.id.search_title_txt);
        Intent intent=getIntent();
        TITLE=intent.getStringExtra("title");
        title.setText(TITLE);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView=findViewById(R.id.contact_recycler_view);
        mRecyclerView.setLayoutManager(mLayoutManager);
        inspectionAdapter=new InspectionAdapter(inspectionContents);
        inspectionAdapter.setOnSubViewClickListener(new BaseRecyclerAdapter.OnSubViewClickListener() {
            @Override
            public void onSubViewClick(View v, int position) {
                InspectionContent inspectionContent=inspectionContents.get(position);
                switch (v.getId()){
                    case R.id.inspection_item_details:
                        Toast.makeText(getApplicationContext(), "工单详情" + (position + 1), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.inspection_item_button2:
                        Toast.makeText(getApplicationContext(), "你点击了去执行按钮", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        inspectionAdapter.setOnRecyclerViewItemClickListener(new BaseRecyclerAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                InspectionContent inspectionContent=inspectionContents.get(position);
                Toast.makeText(getApplicationContext(), "工单详情" + (position + 1), Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setAdapter(inspectionAdapter);
    }
    private void initDatas() {
        InspectionContent inspectionContent=new InspectionContent();
        inspectionContent.setInspection_id("000000001");
        inspectionContent.setInspection_name("邮政储蓄银行北京邮电大学支行巡检");
        inspectionContent.setDevice_name("ATM机");
        inspectionContent.setInspection_status("待执行");
        InspectionContent inspectionContent1=new InspectionContent();
        inspectionContent1.setInspection_id("000000001");
        inspectionContent1.setInspection_name("邮政储蓄银行北京邮电大学支行巡检");
        inspectionContent1.setDevice_name("ATM机");
        inspectionContent1.setInspection_status("已完成");
        inspectionContents.add(inspectionContent);
        inspectionContents.add(inspectionContent);
        inspectionContents.add(inspectionContent);
        inspectionContents.add(inspectionContent);
        inspectionContents.add(inspectionContent);
        inspectionContents.add(inspectionContent);
        inspectionContents.add(inspectionContent1);
        inspectionContents.add(inspectionContent1);
        inspectionContents.add(inspectionContent1);
        inspectionContents.add(inspectionContent1);
        inspectionContents.add(inspectionContent1);
        inspectionContents.add(inspectionContent1);
        inspectionContents.add(inspectionContent1);


    }
    private void setOnListener() {
        back_img.setOnClickListener(this);
        search_text.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.search_title_txt:
                searchContent=search_content.getText().toString().trim();
                showSearchResult(searchContent);
                break;
        }
    }
    private void showSearchResult(String searchContent){

    }
}
