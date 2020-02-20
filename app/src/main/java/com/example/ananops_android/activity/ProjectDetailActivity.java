package com.example.ananops_android.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.adapter.MyFragmentPagerAdapter;
import com.example.ananops_android.db.InspectionListByProjectRequest;
import com.example.ananops_android.db.InspectionListResponse;
import com.example.ananops_android.db.ProjectInfoResponse;
import com.example.ananops_android.entity.InspectionInfo;
import com.example.ananops_android.fragment.InspectionItemFragment;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.ActivityManager;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.InspectionUtils;
import com.example.ananops_android.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ProjectDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private TabLayout tab_search_order_title;                            //定义TabLayout
    private ViewPager vp_search_order_pager;  //内容
    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;
    private ArrayList<InspectionInfo> inspectionInfoList = new ArrayList<>(); //传递给下一个Activity的List
    private TextView title;
    //  private ImageView search_img;
    private ImageView back_img;
    private static String PROJECT_ID;
    private Button order_detail_button1;
    private Button order_detail_button2;
    private LinearLayout fragment_order_commit;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
        setContentView(R.layout.activity_order_detail);
        mContext=this;
        initViews();
        setOnListener();
        initFragment();
    }

    private void initFragment() {
        if (list_title == null) {
            list_title = new ArrayList<>();
            list_title.add("项目信息");list_title.add("合同信息");
        }
        if (list_fragment == null) {
            list_fragment = new ArrayList<>();
        }
        final ArrayList<String> list_item1 = new ArrayList<>();
        final ArrayList<String> list_item2 = new ArrayList<>();
        final ArrayList<String> list_value1 = new ArrayList<>();
        final ArrayList<String> list_value2 = new ArrayList<>();
        list_item1.add("项目名");list_item1.add("项目类型");
        list_item1.add("开始时间");list_item1.add("结束时间");
        list_item2.add("甲方");list_item2.add("甲方负责人1");list_item2.add("联系电话");
        list_item2.add("甲方负责人2");list_item2.add("联系电话");
        list_item2.add("甲方负责人2");list_item2.add("联系电话");
        list_item2.add("乙方");list_item2.add("乙方负责人");list_item2.add("联系电话");
        Net.instance.getProjectInfo(Long.valueOf(PROJECT_ID), SPUtils.getInstance().getString("Token", " "))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ProjectInfoResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("ErrorGetProjectInfo", System.currentTimeMillis() + "");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(ProjectInfoResponse projectInfoResponse) {
                        if (TextUtils.equals(projectInfoResponse.getCode(), "200")) {
                            list_value1.add(projectInfoResponse.getResult().getProjectName());
                            list_value1.add(projectInfoResponse.getResult().getProjectType());
                            list_value1.add(projectInfoResponse.getResult().getCreatedTime());
                            list_value1.add(projectInfoResponse.getResult().getUpdateTime());
                            list_value2.add(projectInfoResponse.getResult().getPartyAName());
                            list_value2.add(projectInfoResponse.getResult().getPartyAName());
                            list_value2.add(projectInfoResponse.getResult().getPartyAOne());
                            list_value2.add(projectInfoResponse.getResult().getAtwoName());
                            list_value2.add(projectInfoResponse.getResult().getPartyATwo());
                            list_value2.add(projectInfoResponse.getResult().getAthreeName());
                            list_value2.add(projectInfoResponse.getResult().getAthreeName());
                            list_value2.add(projectInfoResponse.getResult().getPartyBName());
                            list_value2.add(projectInfoResponse.getResult().getBleaderName());
                            list_value2.add(projectInfoResponse.getResult().getBleaderTel());
                            list_fragment.add(InspectionItemFragment.newIntance("1",list_item1,list_value1));
                            list_fragment.add(InspectionItemFragment.newIntance("1",list_item2,list_value2));
                            myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), list_fragment,list_title);
                            vp_search_order_pager.setAdapter(myFragmentPagerAdapter);
                            vp_search_order_pager.setOffscreenPageLimit(1);
                            tab_search_order_title.setupWithViewPager(vp_search_order_pager);
                        }
                        else {
                            Toast.makeText(mContext, projectInfoResponse.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    private void initViews() {
        tab_search_order_title = findViewById(R.id.search_order_tab);
        vp_search_order_pager = findViewById(R.id.vp_search_order_pager);
        title = findViewById(R.id.txt_title);//标题
        back_img=findViewById(R.id.img_back);
        order_detail_button1=findViewById(R.id.order_detail_button1);
        order_detail_button2=findViewById(R.id.order_detail_button2);
        fragment_order_commit=findViewById(R.id.fragment_order_commit);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        if(bundle!=null){
            PROJECT_ID=bundle.getString("project_id");}
        fragment_order_commit.setVisibility(View.VISIBLE);
        order_detail_button1.setVisibility(View.GONE);
        order_detail_button2.setVisibility(View.VISIBLE);
        order_detail_button2.setText("查看全部巡检");
        title.setText("项目详情");
    }
    private void setOnListener() {
        // search_img.setOnClickListener(this);
        back_img.setOnClickListener(this);
        order_detail_button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.order_detail_button2:
                Long projectId = Long.valueOf(PROJECT_ID);
                //查询 获取项目巡检列表
                if (projectId != null) {
                    InspectionListByProjectRequest inspectionListByProjectRequest=new InspectionListByProjectRequest();
                    inspectionListByProjectRequest.setProjectId(projectId);
                    Net.instance.getInspectionListByProjectId(inspectionListByProjectRequest, SPUtils.getInstance().getString("Token", " "))
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Subscriber<InspectionListResponse>() {
                                @Override
                                public void onCompleted() {
                                    Bundle bundle0=new Bundle();
                                    bundle0.putParcelableArrayList("result",inspectionInfoList);
                                    BaseUtils.getInstence().intent(mContext, InspectionSearchListActivity.class,bundle0);
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.v("ErrorGetInspection_pro", System.currentTimeMillis() + "");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onNext(InspectionListResponse inspectionListResponse) {
                                    if (TextUtils.equals(inspectionListResponse.getCode(), "200")) {
                                        inspectionInfoList.clear();
                                        if (inspectionListResponse.getResult().size() > 0) {
                                            inspectionInfoList.addAll(inspectionListResponse.getResult());

                                            Log.v("巡检列表1", inspectionListResponse.getResult().get(0).getId() + "");
                                        } else {
                                            Toast.makeText(mContext, "无巡检列表！", Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        Toast.makeText(mContext, inspectionListResponse.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(mContext,"请选择项目！", Toast.LENGTH_LONG).show();
                }

                break;
            default:
                break;
        }
    }
    //获取项目信息
}
