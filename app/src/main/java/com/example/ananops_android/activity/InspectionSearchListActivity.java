package com.example.ananops_android.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.example.ananops_android.entity.InspectionInfo;
import com.example.ananops_android.entity.UserLogin;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.InspectionUtils;

import java.util.ArrayList;
import java.util.List;

public class InspectionSearchListActivity extends AppCompatActivity implements View.OnClickListener{
    private  String searchContent;
    private List<InspectionInfo> inspectionInfos=new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView title;
    private ImageView back_img;
    private EditText search_content;
    private TextView search_text;
    private static String TITLE;
    private static String PROJECT_ID;
    private InspectionAdapter inspectionAdapter;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_order_list);
        mContext=this;
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
        PROJECT_ID=intent.getStringExtra("project_id");
        title.setText("巡检列表");
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView=findViewById(R.id.contact_recycler_view);
        mRecyclerView.setLayoutManager(mLayoutManager);
        inspectionAdapter=new InspectionAdapter(inspectionInfos);
        inspectionAdapter.setOnRecyclerViewItemClickListener(new BaseRecyclerAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
             //   InspectionInfo inspectionInfo=inspectionInfos.get(position);
                Toast.makeText(getApplicationContext(), "巡检详情" + (position + 1), Toast.LENGTH_SHORT).show();
                Bundle bundle0=new Bundle();
                bundle0.putString("inspectionId",String.valueOf(inspectionInfos.get(position).getId()));
                BaseUtils.getInstence().intent(mContext,InspectionDetailActivity.class,bundle0);
            }
        });
        mRecyclerView.setAdapter(inspectionAdapter);
    }
    private void initDatas() {
   //  inspectionContents= BaseUtils.getInstence().initInspectionContent(inspectionContents);
        Bundle bundle = getIntent().getExtras();
        inspectionInfos = bundle.getParcelableArrayList("result");
        if (inspectionInfos == null) {
            inspectionInfos = new ArrayList<>();
        }
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
