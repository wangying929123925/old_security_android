package com.example.ananops_android.activity;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.adapter.MyFragmentPagerAdapter;
import com.example.ananops_android.db.AcceptImcTaskByPrincipalRequest;
import com.example.ananops_android.db.AllUnauthorizedTaskResponse;
import com.example.ananops_android.db.CodeMessageResponse;
import com.example.ananops_android.db.ConfirmWorkOrderRequest;
import com.example.ananops_android.db.TestResponse;
import com.example.ananops_android.entity.InspectionInfo;
import com.example.ananops_android.fragment.InspectionDetailFragment;
import com.example.ananops_android.fragment.InspectionItemFragment;
import com.example.ananops_android.fragment.InspectionTimeLineFragment;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.ActivityManager;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class InspectionDetailActivity extends AppCompatActivity {

    private TabLayout tab_search_order_title;                            //定义TabLayout
    private ViewPager vp_search_order_pager;  //内容
    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private String STATUS = "1"; //有几个角色就设置几个不同的状态 对应不同fragment数据的显示
    private static String inspectionId;
    private TextView title;
    private Button b1, b2;
    //  private ImageView search_img;
    private ImageView back_img;
    private Context mContext;
    private String SIGN; //上一个界面传过来的标志位用以区分Button的Text 和 点击效果

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_item_detail);
        mContext = this;
        ActivityManager.getInstance().addActivity(this);
        inspectionId = getIntent().getStringExtra("inspectionId");
        initFragment();
        initViews();
        initDatas();
        setOnListener();
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), list_fragment, list_title);
        //viewPager设置adapter
        vp_search_order_pager.setAdapter(myFragmentPagerAdapter);
        vp_search_order_pager.setOffscreenPageLimit(1);
        tab_search_order_title.setupWithViewPager(vp_search_order_pager);

    }

    private void initFragment() {

        if (list_title == null) {
            list_title = new ArrayList<>();
            list_title.add("巡检信息");
            list_title.add("进度条");
            list_title.add("网点");
            list_title.add("备品备件");
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
        list_item1.add("巡检名称");
        list_item1.add("巡检周期");
        list_item1.add("开始时间");
        list_item1.add("计划完成时间");
        list_item1.add("巡检内容");
        list_item1.add("巡检备注");
        list_item1.add("服务商");
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
        list_item2.add("设备编号");
        list_item2.add("设备名称");
        list_item2.add("设备位置");
        list_item2.add("报修人");
        list_item2.add("联系电话");
        list_value2.add(String.valueOf(bean.getCode()));
        list_value2.add(String.valueOf(bean.getCode()));
        list_value2.add(result.getCreator());
        list_value2.add(result.getCreator());
        list_value2.add(String.valueOf(result.getPageNum()));
        //the third fragment
        list_item3.add("故障程度");
        list_item3.add("故障等级");
        list_item3.add("故障类型");
        list_item3.add("故障描述");
        list_value3.add(String.valueOf(bean.getCode()));
        list_value3.add(String.valueOf(bean.getCode()));
        list_value3.add(result.getCreator());
        list_value3.add(result.getCreator());
        //the fourth fragment
        list_item4.add("单据状态");
        list_item4.add("报修单号");
        list_item4.add("审核人");
        list_item4.add("审核意见");
        list_item4.add("维修人员");
        list_item4.add("计划时间");
        list_item4.add("处理意见");
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
                List<String> subList = new ArrayList<>();
                for (String s :
                        list_item1.subList(0, 5)) {  //保留前五个
                    subList.add(s);
                }
                list_item1.clear();//清空原list
                for (String s :
                        subList) {
                    list_item1.add(s);//赋值给原list
                }
                List<String> list0 = new ArrayList<>();

                for (String s :
                        list_value1.subList(0, 5)) {
                    list0.add(s);
                }
                list_value1.clear();
                for (String s :
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
        list_fragment.add(InspectionItemFragment.newIntance(inspectionId, list_item1, list_value1));
        list_fragment.add(InspectionTimeLineFragment.newIntance(inspectionId));
        list_fragment.add(InspectionDetailFragment.newIntance(inspectionId));
        list_fragment.add(InspectionItemFragment.newIntance(inspectionId, list_item4, list_value4));
    }

    private void initViews() {
        vp_search_order_pager = findViewById(R.id.vp_search_inspection_pager);
        b1 = findViewById(R.id.inspection_detail_button1);
        b2 = findViewById(R.id.inspection_detail_button2);
        SIGN = getIntent().getStringExtra("title");
        if (!SIGN.equals("")) {
            switch (SIGN) {
                case "4-2"://甲方负责人审核通过or不通过:
                    b1.setText("通过");
                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AcceptImcTaskByPrincipalRequest acc = new AcceptImcTaskByPrincipalRequest();
                            acc.setTaskId(Long.parseLong(inspectionId));
                            Net.instance.acceptImcTaskByPrincipal(acc, SPUtils.getInstance().getString("Token", " "))
                                    .subscribeOn(Schedulers.newThread())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Subscriber<CodeMessageResponse>() {
                                        @Override
                                        public void onCompleted() {

                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            Log.v("ErrorAcceptImcTaskBy", System.currentTimeMillis() + "");
                                            Toast.makeText(mContext, "服务器异常", Toast.LENGTH_SHORT).show();
                                            e.printStackTrace();
                                        }

                                        @Override
                                        public void onNext(CodeMessageResponse codeMessageResponse) {
                                            if (TextUtils.equals(codeMessageResponse.getCode(), "200")) {
                                                Toast.makeText(mContext, "甲方负责人审核通过", Toast.LENGTH_SHORT).show();
                                                try {
                                                    Thread.sleep(500);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                                            }
                                        }
                                    });
                        }
                    });
                    b2.setText("不通过");
                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AcceptImcTaskByPrincipalRequest acc = new AcceptImcTaskByPrincipalRequest();
                            acc.setTaskId(Long.parseLong(inspectionId));
                            Net.instance.denyImcTaskByPrincipal(acc, SPUtils.getInstance().getString("Token", " "))
                                    .subscribeOn(Schedulers.newThread())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Subscriber<CodeMessageResponse>() {
                                        @Override
                                        public void onCompleted() {

                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            Log.v("ErrorDenyImcTaskBy", System.currentTimeMillis() + "");
                                            Toast.makeText(mContext, "服务器异常", Toast.LENGTH_SHORT).show();
                                            e.printStackTrace();
                                        }

                                        @Override
                                        public void onNext(CodeMessageResponse codeMessageResponse) {
                                            if (TextUtils.equals(codeMessageResponse.getCode(), "200")) {
                                                Toast.makeText(mContext, "甲方负责人审核拒绝", Toast.LENGTH_SHORT).show();
                                                try {
                                                    Thread.sleep(500);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                                            }
                                        }
                                    });
                        }
                    });
                    break;
                case "2-1"://服务商审核通过or不通过:
                    b1.setText("通过");
                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ConfirmWorkOrderRequest request = new ConfirmWorkOrderRequest();
                            request.setDecision(1);
                            ConfirmWorkOrderRequest.WorkOrderQueryDtoBean inner = new ConfirmWorkOrderRequest.WorkOrderQueryDtoBean();
                            inner.setId(Long.parseLong(inspectionId));
                            inner.setPageNum(0);
                            inner.setPageSize(0);
                            inner.setType("inspection");
                            request.setWorkOrderQueryDto(inner);
                            Net.instance.confirmWorkOrder(request, SPUtils.getInstance().getString("Token", " "))
                                    .subscribeOn(Schedulers.newThread())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Subscriber<CodeMessageResponse>() {
                                        @Override
                                        public void onCompleted() {

                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            Log.v("ErrorAcceptImcTaskBy", System.currentTimeMillis() + "");
                                            Toast.makeText(mContext, "服务器异常", Toast.LENGTH_SHORT).show();
                                            e.printStackTrace();
                                        }

                                        @Override
                                        public void onNext(CodeMessageResponse codeMessageResponse) {
                                            if (TextUtils.equals(codeMessageResponse.getCode(), "200")) {
                                                Toast.makeText(mContext, "甲方负责人审核通过", Toast.LENGTH_SHORT).show();
                                                try {
                                                    Thread.sleep(500);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                                            }
                                        }
                                    });
                        }
                    });
                    b2.setText("不通过");
                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mContext, "不通过", Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
                default:
                    break;
            }
        }
        tab_search_order_title = findViewById(R.id.search_inspection_tab);
    }

    private void initDatas() {
        title = findViewById(R.id.txt_title);//标题
        back_img = findViewById(R.id.img_back);
        title.setText("巡检详情");
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void setOnListener() {

    }

}
