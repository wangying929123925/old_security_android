package com.example.ananops_android.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.adapter.ListCommonAdapter;
import com.example.ananops_android.adapter.ListViewHolder;
import com.example.ananops_android.db.ProjectListResponse;
import com.example.ananops_android.entity.ProjectInfo;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.SPUtils;
import com.example.ananops_android.view.EditTextWithDel;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ProjectListActivity extends BaseActivity {
    private ListView sortListView;
    private TextView title;
    private TextView noResult;
    private EditTextWithDel mEtSearchName;
    private ImageView imageBack;
    private List<ProjectInfo> projectInfos=new ArrayList<>();
    private ListCommonAdapter mAdapter;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_main);
        mContext =this;
      //  ActivityManager.getInstance().addActivity(this);
        //获取项目信息
        Net.instance.getProjectList(Long.valueOf(SPUtils.getInstance(mContext).getString("groupId", "1")), SPUtils.getInstance(mContext).getString("Token", " "))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ProjectListResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("ErrorGetProjectList", System.currentTimeMillis() + "");
                        e.printStackTrace();
                        Toast.makeText(mContext, "网络异常，请检查网络状态changeStatus", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(ProjectListResponse projectListResponse) {
                        if (TextUtils.equals(projectListResponse.getCode(), "200")) {
                            projectInfos.clear();
                            if (projectListResponse.getResult().size() > 0) {
                                projectInfos.addAll(projectListResponse.getResult());
                                initDatas();
                                initViews();
                                Log.v("项目列表1", projectListResponse.getResult().get(0).getId() + "");
                            } else {
                                Toast.makeText(mContext, "无项目列表！", Toast.LENGTH_LONG).show();
                                Log.v("项目列表0", projectListResponse.getResult().size() + "");
                            }
                        } else {
                            Toast.makeText(mContext, projectListResponse.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });




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
                BaseUtils.getInstence().intent(mContext, ProjectDetailActivity.class,bundle0);
            }
        });
        mAdapter.notifyDataSetInvalidated();
        sortListView.setAdapter(mAdapter);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
