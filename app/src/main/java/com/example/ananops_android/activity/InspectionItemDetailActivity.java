package com.example.ananops_android.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.adapter.FindTabAdapter;
import com.example.ananops_android.adapter.MyFragmentPagerAdapter;
import com.example.ananops_android.db.AcceptInspectionItemRequest;
import com.example.ananops_android.db.CodeMessageResponse;
import com.example.ananops_android.db.InspectionItemDetailResponse;
import com.example.ananops_android.db.InspectionPicRequest;
import com.example.ananops_android.db.InspectionPicResponse;
import com.example.ananops_android.fragment.InspectionItemFragment;
import com.example.ananops_android.fragment.InspectionItemTimeLineFragment;
import com.example.ananops_android.fragment.OrderDetailAppendix;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class InspectionItemDetailActivity extends BaseActivity {
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
    private static String inspectionItemId;
    private Context mContext;
    private static String statusDo; //有几个角色就设置几个不同的状态 对应不同fragment数据的显示
    private ArrayList<String> list_value1 = new ArrayList<>();//项目信息
    private ArrayList<String> list_value3 = new ArrayList<>();//其他信息
    private ArrayList<String> list_value4 = new ArrayList<>();//附件信息
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_item_detail);
        mContext=this;
      //  ActivityManager.getInstance().addActivity(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            inspectionItemId = bundle.getString("inspectionItemId","1");
            statusDo = bundle.getString("statusDo","1");
        }
        getInspectionItemInfo();
       // initFragment();
        defaultArrayList();
        initViews();
        initDatas();
    }

    private void initFragment() {
        if (list_title == null) {
            list_title = new ArrayList<>();
            list_title.add("项目信息");list_title.add("子项日志");
            list_title.add("其他信息");
            list_title.add("附件信息");
        }
        if (list_fragment == null) {
            list_fragment = new ArrayList<>();
        }
        ArrayList<String> list_item1 = new ArrayList<>();
        ArrayList<String> list_item2 = new ArrayList<>();
        ArrayList<String> list_item3 = new ArrayList<>();
//        ArrayList<String> list_item4 = new ArrayList<>();
//        ArrayList<String> list_value1 = new ArrayList<>();
//        ArrayList<String> list_value2 = new ArrayList<>();
//        ArrayList<String> list_value3 = new ArrayList<>();
//        ArrayList<String> list_value4 = new ArrayList<>();
        //首先把数据做全量填充
        //the first fragment
        list_item1.add("网点名称");list_item1.add("网点内容");
        list_item1.add("工程师");list_item1.add("周期");list_item1.add("持续时长");
//        TestResponse bean = new TestResponse();
//        TestResponse.ResultBean result = bean.getResult();
//        list_value1.add(String.valueOf(bean.getCode()));
//        list_value1.add(bean.getMessage());
//        list_value1.add(result.getCreator());
//        list_value1.add(result.getCreator());
//        list_value1.add(String.valueOf(result.getPageNum()));
//        list_value1.add(result.getCreator());
//        list_value1.add(result.getCreator());
//        list_value1.add(String.valueOf(result.getPageNum()));
        //the second fragment
        //the third fragment
        list_item3.add("故障程度");list_item3.add("故障等级");
        list_item3.add("故障类型");list_item3.add("故障描述");
//        list_value3.add(String.valueOf(bean.getCode()));
//        list_value3.add(String.valueOf(bean.getCode()));
//        list_value3.add(result.getCreator());
//        list_value3.add(result.getCreator());
        //the fourth fragment
        list_fragment.add(InspectionItemFragment.newIntance("1",list_item1,list_value1));
        list_fragment.add(InspectionItemTimeLineFragment.newIntance(inspectionItemId));
        list_fragment.add(InspectionItemFragment.newIntance("1",list_item3,list_value3));
        list_fragment.add(OrderDetailAppendix.newInstance(list_value4));
     //   list_fragment.add(InspectionItemFragment.newIntance("1",list_item4,list_value4));
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), list_fragment,list_title);
        //viewPager设置adapter
        vp_search_order_pager.setAdapter(myFragmentPagerAdapter);
        vp_search_order_pager.setOffscreenPageLimit(1);
        tab_search_order_title.setupWithViewPager(vp_search_order_pager);
    }

    private void initViews(){
        vp_search_order_pager = findViewById(R.id.vp_search_inspection_pager);
        tab_search_order_title = findViewById(R.id.search_inspection_tab);
    }
    private void initDatas(){
        title = findViewById(R.id.txt_title);//标题
        back_img=findViewById(R.id.img_back);
        title.setText("巡检子项详情");
        inspection_detail_button1=findViewById(R.id.inspection_detail_button1);
        inspection_detail_button2=findViewById(R.id.inspection_detail_button2);
        fragment_inspection_commit=findViewById(R.id.fragment_inspection_commit);
        switch (statusDo){
            case "2-2"://e:
                fragment_inspection_commit.setVisibility(View.VISIBLE);
                inspection_detail_button1.setVisibility(View.GONE);
                inspection_detail_button2.setVisibility(View.VISIBLE);
                inspection_detail_button2.setText("分配工程师");
                inspection_detail_button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //分配工程师
                        Bundle bundle = new Bundle();
                        bundle.putString("type","inspection");
                        bundle.putString("typeId",inspectionItemId);
                        bundle.putString("projectId","1");
                        BaseUtils.getInstence().intent(mContext, ContactActivity.class,bundle);
                         }
                });
                break;
            case "3-1"://工程师接单:
                fragment_inspection_commit.setVisibility(View.VISIBLE);
                inspection_detail_button1.setVisibility(View.GONE);
                inspection_detail_button2.setVisibility(View.VISIBLE);
                inspection_detail_button2.setText("接单");
                inspection_detail_button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //确认接单
                        AcceptInspectionItemRequest acceptInspectionItemRequest=new AcceptInspectionItemRequest();
                        acceptInspectionItemRequest.setItemId(Long.valueOf(inspectionItemId));
                    Net.instance.acceptItemByMaintainer(acceptInspectionItemRequest,SPUtils.getInstance(mContext).getString("Token", " "))
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Subscriber<CodeMessageResponse>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.v("ErrorAcceptInspection", System.currentTimeMillis() + "");
                                    e.printStackTrace();
                                    Toast.makeText(mContext, "服务器异常", Toast.LENGTH_SHORT).show();
                                    BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                                }

                                @Override
                                public void onNext(CodeMessageResponse codeMessageResponse) {
                                    if (TextUtils.equals(codeMessageResponse.getCode(), "200")) {
                                        Toast.makeText(mContext, "工程师接单成功！", Toast.LENGTH_LONG).show();
                                      //  InspectionUtils.getInstence().changeInspectionItemStatus(3,inspectionItemId,mContext);
                                        BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                                    } else {
                                        Toast.makeText(mContext, codeMessageResponse.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                    }
                });
                break;
            case "3-2"://完成改变状态
                fragment_inspection_commit.setVisibility(View.VISIBLE);
                inspection_detail_button1.setVisibility(View.GONE);
                inspection_detail_button2.setVisibility(View.VISIBLE);
                inspection_detail_button2.setText("子项单据");
                inspection_detail_button2.setOnClickListener(v -> {

                    BaseUtils.getInstence().intent(mContext,InspectionInvoiceListActivity.class,"inspectionItemId",inspectionItemId);
                    //改变状态
                    // InspectionUtils.getInstence().changeInspectionItemStatus(4,inspectionItemId,mContext);
                });
                break;
                default:
                    fragment_inspection_commit.setVisibility(View.GONE);
                    inspection_detail_button1.setVisibility(View.GONE);
                    inspection_detail_button2.setVisibility(View.GONE);
                    break;
        }
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getInspectionItemInfo() {
        Net.instance.getInspectionItemDetails(Long.valueOf(inspectionItemId), SPUtils.getInstance(mContext).getString("Token", " "))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<InspectionItemDetailResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("ErrorGetInspectionInfo", System.currentTimeMillis() + "");
                        e.printStackTrace();
                        getInspectionPicsUrl();
                    }

                    @Override
                    public void onNext(InspectionItemDetailResponse inspectionDetailResponse) {
                        if (TextUtils.equals(inspectionDetailResponse.getCode(), "200")) {
                            list_value1.clear();
                            list_value1.add(inspectionDetailResponse.getResult().getItemName());
                            list_value1.add(String.valueOf(inspectionDetailResponse.getResult().getDescription()));
                            list_value1.add(String.valueOf(inspectionDetailResponse.getResult().getMaintainerId()));
                            list_value1.add(inspectionDetailResponse.getResult().getFrequency()+"天");
                            list_value1.add(inspectionDetailResponse.getResult().getDays()+"天");
                            list_value3.clear();
                            list_value3.add(" ");
                            list_value3.add(" ");
                            list_value3.add(" ");
                            list_value3.add(" ");
                           // initFragment();
                            getInspectionPicsUrl();
                        } else {
                            getInspectionPicsUrl();
                         //   initFragment();
                            Toast.makeText(mContext, inspectionDetailResponse.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }
    private void getInspectionPicsUrl() {
        InspectionPicRequest inspectionPicRequest = new InspectionPicRequest();
        inspectionPicRequest.setItemId(Long.valueOf(inspectionItemId));
        inspectionPicRequest.setItemStatus(0);
        Net.instance.getInspectionPicsUrl(inspectionPicRequest,SPUtils.getInstance(mContext).getString("Token", " "))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<InspectionPicResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        initFragment();
                        Log.e("getInspectionUrls", System.currentTimeMillis() + "");
                    }

                    @Override
                    public void onNext(InspectionPicResponse inspectionPicResponse) {
                        if (TextUtils.equals(inspectionPicResponse.getCode(), "200")) {
                            initFragment();
                            int size = inspectionPicResponse.getResult().size();
                            if (size > 0) {
                                list_value4.clear();
                                for (int i = 0; i < size; i++) {
                                    list_value4.add(inspectionPicResponse.getResult().get(i).getUrl());
                                }
                            }
                        } else {
                            initFragment();
                        }
                    }
                });
    }

    private void defaultArrayList() {
        list_value1.clear();
        list_value1.add(" ");
        list_value1.add(" ");
        list_value1.add(" ");
        list_value1.add(" ");
        list_value1.add(" ");
        list_value3.clear();
        list_value3.add(" ");
        list_value3.add(" ");
        list_value3.add(" ");
        list_value3.add(" ");
        list_value4.clear();
    }
}
