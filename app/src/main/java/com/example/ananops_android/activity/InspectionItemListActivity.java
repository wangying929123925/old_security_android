package com.example.ananops_android.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ananops_android.R;
import com.example.ananops_android.adapter.ListCommonAdapter;
import com.example.ananops_android.adapter.ListViewHolder;
import com.example.ananops_android.entity.InspectionTaskItem;
import com.example.ananops_android.view.EditTextWithDel;

import java.util.List;

public class InspectionItemListActivity extends AppCompatActivity {
    private ListView sortListView;
    private TextView title;
    private TextView noResult;
    private EditTextWithDel mEtSearchName;
    private ImageView imageBack;
    private List<InspectionTaskItem> inspectionTaskItems;
    private ListCommonAdapter mAdapter;
    private static String inspectionId;
    private Context mComtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mComtext=this;
        setContentView(R.layout.activity_contacts_main);
        initViews();
    }
    private void initDatas() {
        Intent intent=getIntent();
        inspectionId=intent.getStringExtra("inspectionId");
        title.setText("巡检任务子项");
        mAdapter = new ListCommonAdapter<InspectionTaskItem>(mComtext, R.layout.item_chooese_replacement, inspectionTaskItems) {
            @Override
            protected void convert(ListViewHolder viewHolder, InspectionTaskItem inspectionTaskItem, int position) {
                viewHolder.setText(R.id.inspection_sub_name, inspectionTaskItem.getItemName());//名称
                viewHolder.setText(R.id.inspection_sub_id, String.valueOf(inspectionTaskItem.getId()));//id
                viewHolder.setText(R.id.inspection_sub_maintainer, inspectionTaskItem.getMaintainerName());//类型
                viewHolder.setText(R.id.inspection_sub_time, inspectionTaskItem.getUpdateTime());//价格
            }
        };
        sortListView.setAdapter(mAdapter);
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //进入任务子项

            }
        });
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void initViews() {
        sortListView=findViewById(R.id.lv_contact);
        mEtSearchName = (EditTextWithDel) findViewById(R.id.et_search_contact);
        title=findViewById(R.id.txt_title);
        noResult=findViewById(R.id.no_result_text);
        imageBack=findViewById(R.id.img_back);
        initDatas();
    }
}
