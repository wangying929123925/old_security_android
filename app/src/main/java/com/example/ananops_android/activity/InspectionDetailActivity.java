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
import com.example.ananops_android.adapter.MyFragmentPagerAdapter;
import com.example.ananops_android.db.AcceptImcTaskByPrincipalRequest;
import com.example.ananops_android.db.ChangeInspectionStatusRequest;
import com.example.ananops_android.db.CodeMessageResponse;
import com.example.ananops_android.db.ConfirmWorkOrderRequest;
import com.example.ananops_android.db.InspectionDetailResponse;
import com.example.ananops_android.fragment.InspectionItemFragment;
import com.example.ananops_android.fragment.InspectionItemListFragment;
import com.example.ananops_android.fragment.InspectionTimeLineFragment;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.SPUtils;
import com.example.ananops_android.view.CommentWindow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class InspectionDetailActivity extends BaseActivity {

    private TabLayout tab_search_order_title;                            //定义TabLayout
    private ViewPager vp_search_order_pager;  //内容
    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private int userCode ; //有几个角色就设置几个不同的状态 对应不同fragment数据的显示
    private static String inspectionId;
    private TextView title;
    private Button b1, b2;
    private LinearLayout fragment_inspection_commit;
    //  private ImageView search_img;
    private ImageView back_img;
    private Context mContext;
    private ArrayList<String> list_value1= new ArrayList<>();
    private ArrayList<String> list_value4= new ArrayList<>();
    private static String statusDo; //StatusDo
    private CommentWindow commentWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_item_detail);
        mContext = this;
    //    ActivityManager.getInstance().addActivity(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            inspectionId = bundle.getString("inspectionId");
            statusDo = bundle.getString("statusDo");
        }
        getInspectionInfo();
     //   initFragment();
        initViews();
        initDatas();

    }

    private void initFragment() {
        userCode = SPUtils.getInstance(mContext).getInt("role_num", 1);
        if (list_title == null) {
            list_title = new ArrayList<>();
            list_title.add("巡检信息");
            list_title.add("进度条");
            list_title.add("巡检子项");
            list_title.add("其他信息");
        }
        if (list_fragment == null) {
            list_fragment = new ArrayList<>();
        }
        ArrayList<String> list_item1 = new ArrayList<>();
        ArrayList<String> list_item4 = new ArrayList<>();
       // ArrayList<String> list_value1 = new ArrayList<>();
       // ArrayList<String> list_value4 = new ArrayList<>();
        //首先把数据做全量填充
        //the first fragment
        list_item1.add("巡检名称");
        list_item1.add("巡检周期");
        list_item1.add("开始时间");
        list_item1.add("计划完成时间");
        list_item1.add("巡检内容");
        list_item1.add("巡检备注");
        list_item1.add("服务商");
//        TestResponse bean = new TestResponse();
//        TestResponse.ResultBean result = bean.getResult();
//        list_value1.add(String.valueOf(bean.getCode()));
//        list_value1.add(bean.getMessage());
//        list_value1.add(result.getCreator());
//        list_value1.add(result.getCreator());
//        list_value1.add(String.valueOf(result.getPageNum()));
//        list_value1.add(result.getCreator());
//       // list_value1.add(result.getCreator());
//        list_value1.add(String.valueOf(result.getPageNum()));
        //the third fragment
        //the fourth fragment
        list_item4.add("单据状态");
        list_item4.add("报修单号");
        list_item4.add("审核人");
        list_item4.add("审核意见");
        list_item4.add("维修人员");
        list_item4.add("计划时间");
        list_item4.add("处理意见");
//        list_value4.add(String.valueOf(bean.getCode()));
//        list_value4.add(String.valueOf(bean.getCode()));
//        list_value4.add("");
//        list_value4.add(result.getCreator());
//        list_value4.add("");
//        list_value4.add(result.getCreator());
//        list_value4.add("");
        list_fragment.add(InspectionItemFragment.newIntance("1", list_item1, list_value1));
        list_fragment.add(InspectionTimeLineFragment.newIntance(inspectionId));
        list_fragment.add(InspectionItemListFragment.newInstance(inspectionId,statusDo));
        list_fragment.add(InspectionItemFragment.newIntance("1", list_item4, list_value4));
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), list_fragment, list_title);
        //viewPager设置adapter
        vp_search_order_pager.setAdapter(myFragmentPagerAdapter);
        vp_search_order_pager.setOffscreenPageLimit(1);
        tab_search_order_title.setupWithViewPager(vp_search_order_pager);
    }

    private void initViews() {
        vp_search_order_pager = findViewById(R.id.vp_search_inspection_pager);
        b1 = findViewById(R.id.inspection_detail_button1);
        b2 = findViewById(R.id.inspection_detail_button2);
        fragment_inspection_commit = findViewById(R.id.fragment_inspection_commit);
       // statusDo = getIntent().getStringExtra("statusDo");
        if (!statusDo.equals("")) {
            switch (statusDo) {
                case "2-1"://服务商审核通过or不通过:
                    b1.setVisibility(View.VISIBLE);
                    b2.setVisibility(View.VISIBLE);
                    b1.setText("通过");
                    b1.setOnClickListener(v -> {
                        ConfirmWorkOrderRequest request = new ConfirmWorkOrderRequest();
                        request.setDecision(1);
                        ConfirmWorkOrderRequest.WorkOrderQueryDtoBean inner = new ConfirmWorkOrderRequest.WorkOrderQueryDtoBean();
                        inner.setId(Long.valueOf(inspectionId));
                        inner.setPageNum(0);
                        inner.setPageSize(0);
                        inner.setType("inspection");
                        request.setWorkOrderQueryDto(inner);
                        Net.instance.confirmWorkOrder(request, SPUtils.getInstance(mContext).getString("Token", " "))
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
                                        if (e instanceof HttpException) {
                                            HttpException httpException = (HttpException) e;
                                            try{
                                                String error = httpException.response().errorBody().string();
                                                Log.v("RepairAddError", error);
                                            }catch(IOException e1) {
                                                e1.printStackTrace();
                                            }
                                        }else {
                                            //ToastUtil.showLongToast("请求失败");
                                        }
                                    }

                                    @Override
                                    public void onNext(CodeMessageResponse codeMessageResponse) {
                                        if (TextUtils.equals(codeMessageResponse.getCode(), "200")) {
                                            Toast.makeText(mContext, "甲方负责人审核通过", Toast.LENGTH_SHORT).show();
                                            BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                                        }
                                    }
                                });
                    });
                    b2.setText("不通过");
                    b2.setOnClickListener(v -> Toast.makeText(mContext, "不通过", Toast.LENGTH_SHORT).show());
                    break;
                case "2-2":
                    b1.setVisibility(View.VISIBLE);
                    b2.setVisibility(View.GONE);
                    b1.setText("新建子项任务");
                    b1.setOnClickListener(v -> {
                        BaseUtils.getInstence().intent(mContext,InspectionItemAddActivity.class,"inspectionId",inspectionId);
                    });
                    break;
                case "2-4"://确认完成
                    b1.setVisibility(View.VISIBLE);
                    b2.setVisibility(View.GONE);
                    b1.setText("确认评价");
                    b1.setOnClickListener(v -> {
                        String userId = SPUtils.getInstance(mContext).getString("user_id", "1");
                        commentWindow = new CommentWindow(inspectionId,userId,2,mContext);
                        commentWindow.show();
                    });

                case "4-2"://甲方负责人审核通过or不通过:
                    b1.setVisibility(View.VISIBLE);
                    b2.setVisibility(View.VISIBLE);
                    b1.setText("通过");
                    b1.setOnClickListener(v -> {
                        AcceptImcTaskByPrincipalRequest acc = new AcceptImcTaskByPrincipalRequest();
                        acc.setTaskId(Long.parseLong(inspectionId));
                        Net.instance.acceptImcTaskByPrincipal(acc, SPUtils.getInstance(mContext).getString("Token", " "))
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
                                        //    Toast.makeText(mContext, "甲方负责人审核通过", Toast.LENGTH_SHORT).show();
                                            BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                                        }
                                    }
                                });
                    });
                    b2.setText("不通过");
                    b2.setOnClickListener(v -> {
                        AcceptImcTaskByPrincipalRequest acc = new AcceptImcTaskByPrincipalRequest();
                        acc.setTaskId(Long.parseLong(inspectionId));
                        Net.instance.denyImcTaskByPrincipal(acc, SPUtils.getInstance(mContext).getString("Token", " "))
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
                                        //    BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                                        }
                                    }
                                });
                    });
                    break;
                case "4-4":
                    b1.setVisibility(View.VISIBLE);
                    b2.setVisibility(View.GONE);
                    b1.setText("确认完成");
                    b1.setOnClickListener(v -> {
                        ChangeInspectionStatusRequest changeInspectionStatusRequest = new ChangeInspectionStatusRequest();
                        changeInspectionStatusRequest.setTaskId(Long.valueOf(inspectionId));
                        changeInspectionStatusRequest.setStatus(7);
                        changeInspectionStatusRequest.setStatusMsg("巡检完成");
                        Net.instance.modifyIspectionStatusByItemId(changeInspectionStatusRequest, SPUtils.getInstance(mContext).getString("Token", " "))
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Subscriber<CodeMessageResponse>() {
                                    @Override
                                    public void onCompleted() {

                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.v("ErrorModifyItemStatus", System.currentTimeMillis() + "");
                                        Toast.makeText(mContext, "操作失败", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onNext(CodeMessageResponse codeMessageResponse) {
                                        if (TextUtils.equals(codeMessageResponse.getCode(), "200")) {
                                            Toast.makeText(mContext, "操作成功！", Toast.LENGTH_LONG).show();
                                            BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                                        } else {
                                            Toast.makeText(mContext, "操作失败！", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                    });

                    break;
                default:
                    fragment_inspection_commit.setVisibility(View.GONE);
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

    private void getInspectionInfo() {
        Net.instance.getInspectionDetails(Long.valueOf(inspectionId), SPUtils.getInstance(mContext).getString("Token", " "))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<InspectionDetailResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("ErrorGetInspectionInfo", System.currentTimeMillis() + "");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(InspectionDetailResponse inspectionDetailResponse) {
                        if (TextUtils.equals(inspectionDetailResponse.getCode(), "200")) {
                            list_value1.clear();
                            list_value1.add(inspectionDetailResponse.getResult().getTaskName());
                            list_value1.add(inspectionDetailResponse.getResult().getFrequency()+"天");
                            list_value1.add(inspectionDetailResponse.getResult().getScheduledStartTime());
                            list_value1.add(" ");
                            list_value1.add(" ");
                            list_value1.add(" ");
                            list_value1.add(String.valueOf(inspectionDetailResponse.getResult().getFacilitatorId()));
                            list_value4 .clear();
                            list_value4.add(String.valueOf(inspectionDetailResponse.getResult().getStatus()));
                            list_value4.add(String.valueOf(inspectionDetailResponse.getResult().getId()));
                            list_value4.add(" ");
                            list_value4.add(" ");
                            list_value4.add(" ");
                            list_value4.add(" ");
                            list_value4.add(" ");
                            int result = inspectionDetailResponse.getResult().getPointSum() - inspectionDetailResponse.getResult().getAlreadyPoint();
                            if (result > 0) {
                                String text="新建子项"+"("+result+")";
                                b1.setText(text);
                            }
                            initFragment();
                        } else {
                            Toast.makeText(mContext, inspectionDetailResponse.getMessage(), Toast.LENGTH_LONG).show();
                            list_value1.clear();
                            list_value1.add(" ");
                            list_value1.add(" ");
                            list_value1.add(" ");
                            list_value1.add(" ");
                            list_value1.add(" ");
                            list_value1.add(" ");
                            list_value1.add(" ");
                            list_value4 .clear();
                            list_value4.add(" ");
                            list_value4.add(" ");
                            list_value4.add(" ");
                            list_value4.add(" ");
                            list_value4.add(" ");
                            list_value4.add(" ");
                            list_value4.add(" ");
                            initFragment();
                        }
                    }
                });
    }
}
