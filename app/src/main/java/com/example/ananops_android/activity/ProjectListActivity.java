package com.example.ananops_android.activity;

import android.content.Context;
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
import com.example.ananops_android.entity.ProjectInfo;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.InspectionUtils;
import com.example.ananops_android.view.EditTextWithDel;

import java.util.ArrayList;
import java.util.List;

public class ProjectListActivity extends AppCompatActivity {
    private ListView sortListView;
    private TextView title;
    private TextView noResult;
    private EditTextWithDel mEtSearchName;
    private ImageView imageBack;
    private List<ProjectInfo> projectInfos=new ArrayList<>();
    private ListCommonAdapter mAdapter;
    private Context mComtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_main);
        projectInfos= InspectionUtils.getInstence().getProjectList(projectInfos,4L,mComtext);
        mComtext=this;
        initDatas();
        initViews();

    }
    private void initViews() {

    }
    private void initDatas() {
        sortListView=findViewById(R.id.lv_contact);
        mEtSearchName = (EditTextWithDel) findViewById(R.id.et_search_contact);
        title=findViewById(R.id.txt_title);
        noResult=findViewById(R.id.no_result_text);
        imageBack=findViewById(R.id.img_back);
        title.setText("项目列表");

        mAdapter = new ListCommonAdapter<ProjectInfo>(getApplicationContext(), R.layout.item_project_list, projectInfos) {
            @Override
            protected void convert(ListViewHolder viewHolder,ProjectInfo projectInfo, int position) {
                viewHolder.setText(R.id.Plist_name, projectInfo.getProjectName());//名称
                viewHolder.setText(R.id.Plist_id, String.valueOf(projectInfo.getId()));//id
                viewHolder.setText(R.id.Plist_type, projectInfo.getCreator());//类型
                viewHolder.setText(R.id.Plist_price, projectInfo.getCreatedTime());//价格
            }
        };
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //进入任务子项
                Bundle bundle0=new Bundle();
                bundle0.putString("project_id",String.valueOf(projectInfos.get(position).getId()));
                BaseUtils.getInstence().intent(mComtext,InspectionSerchListActivity.class,bundle0);
            }
        });
        sortListView.setAdapter(mAdapter);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
