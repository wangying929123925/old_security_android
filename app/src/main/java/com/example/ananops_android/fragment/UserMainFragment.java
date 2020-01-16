package com.example.ananops_android.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.activity.InspectionAddActivity;
import com.example.ananops_android.activity.InspectionItemListActivity;
import com.example.ananops_android.activity.InspectionSearchListActivity;
import com.example.ananops_android.activity.OrderSearchListActivity;
import com.example.ananops_android.activity.ProjectListActivity;
import com.example.ananops_android.activity.RepairAddActivity;
import com.example.ananops_android.activity.UserOrderSearchActivitySpinner;
import com.example.ananops_android.adapter.RepairAdapter;
import com.example.ananops_android.db.AllUnDistributedWorkOrdersRequest;
import com.example.ananops_android.db.AllUnDistributedWorkOrdersResponse;
import com.example.ananops_android.db.AllUnauthorizedTaskRequest;
import com.example.ananops_android.db.AllUnauthorizedTaskResponse;
import com.example.ananops_android.db.OrderRequest;
import com.example.ananops_android.entity.InspectionInfo;
import com.example.ananops_android.entity.RepairContent;
import com.example.ananops_android.entity.UnReadNum;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserMainFragment extends Fragment implements View.OnClickListener{
    private LinearLayout main_repair;
    private LinearLayout main_inspection;
    private RelativeLayout main_repair_1;
    private RelativeLayout main_repair_2;
    private RelativeLayout main_repair_3;
    private RelativeLayout main_repair_4;
    private RelativeLayout main_repair_5;
    private RelativeLayout main_inspection_1;
    private RelativeLayout main_inspection_2;
    private RelativeLayout main_inspection_3;
    private RelativeLayout main_inspection_4;
    private RelativeLayout main_inspection_5;
    private ImageView main_repair_img1;
    private ImageView main_repair_img2;
    private ImageView main_repair_img3;
    private ImageView main_repair_img4;
    private ImageView main_repair_img5;
    private ImageView main_inspection_img1;
    private ImageView main_inspection_img2;
    private ImageView main_inspection_img3;
    private ImageView main_inspection_img4;
    private ImageView main_inspection_img5;
    private TextView main_repair_text1;
    private TextView main_repair_text2;
    private TextView main_repair_text3;
    private TextView main_repair_text4;
    private TextView main_repair_text5;
    private TextView main_inspection_text1;
    private TextView main_inspection_text2;
    private TextView main_inspection_text3;
    private TextView main_inspection_text4;
    private TextView main_inspection_text5;
    private TextView user_Type;
    private TextView repair_all;
    private TextView inspection_all;
    private TextView main_repair_num1;
    private TextView main_repair_num2;
    private TextView main_repair_num3;
    private TextView main_repair_num4;
    private TextView main_repair_num5;
    private TextView main_inspection_num1;
    private TextView main_inspection_num2;
    private TextView main_inspection_num3;
    private TextView main_inspection_num4;
    private TextView main_inspection_num5;
    private RepairAdapter adapter;//适配器
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<RepairContent> repairContents=new ArrayList<>();
   private Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View view=inflater.inflate(R.layout.fragment_user_main,container,false);
        //mLayoutManager = new LinearLayoutManager(this.getActivity());
       // mRecyclerView=view.findViewById(R.id.contact_recycler_view);
     //   uer_message=view.findViewById(R.id.main_user_message);
        //init
        mContext=getContext();
        user_Type=view.findViewById(R.id.user_type);
        main_repair=view.findViewById(R.id.main_repair);
        main_inspection=view.findViewById(R.id.main_inspection);
        main_repair_1=view.findViewById(R.id.main_repair_1);
        main_repair_2=view.findViewById(R.id.main_repair_2);
        main_repair_3=view.findViewById(R.id.main_repair_3);
        main_repair_4=view.findViewById(R.id.main_repair_4);
        main_repair_5=view.findViewById(R.id.main_repair_5);
        main_repair_img1=view.findViewById(R.id.main_repair_img1);
        main_repair_img2=view.findViewById(R.id.main_repair_img2);
        main_repair_img3=view.findViewById(R.id.main_repair_img3);
        main_repair_img4=view.findViewById(R.id.main_repair_img4);
        main_repair_img5=view.findViewById(R.id.main_repair_img5);
        main_repair_text1=view.findViewById(R.id.main_order_text1);
        main_repair_text2=view.findViewById(R.id.main_order_text2);
        main_repair_text3=view.findViewById(R.id.main_order_text3);
        main_repair_text4=view.findViewById(R.id.main_order_text4);
        main_repair_text5=view.findViewById(R.id.main_order_text5);
        main_repair_num1=view.findViewById(R.id.main_order_num1);
        main_repair_num2=view.findViewById(R.id.main_order_num2);
        main_repair_num3=view.findViewById(R.id.main_order_num3);
        main_repair_num4=view.findViewById(R.id.main_order_num4);
        main_repair_num5=view.findViewById(R.id.main_order_num5);
        main_inspection_1=view.findViewById(R.id.main_inspection_1);
        main_inspection_2=view.findViewById(R.id.main_inspection_2);
        main_inspection_3=view.findViewById(R.id.main_inspection_3);
        main_inspection_4=view.findViewById(R.id.main_inspection_4);
        main_inspection_5=view.findViewById(R.id.main_inspection_5);
        main_inspection_img1=view.findViewById(R.id.main_inspection_img1);
        main_inspection_img2=view.findViewById(R.id.main_inspection_img2);
        main_inspection_img3=view.findViewById(R.id.main_inspection_img3);
        main_inspection_img4=view.findViewById(R.id.main_inspection_img4);
        main_inspection_img5=view.findViewById(R.id.main_inspection_img5);
        main_inspection_text1=view.findViewById(R.id.main_inspection_text1);
        main_inspection_text2=view.findViewById(R.id.main_inspection_text2);
        main_inspection_text3=view.findViewById(R.id.main_inspection_text3);
        main_inspection_text4=view.findViewById(R.id.main_inspection_text4);
        main_inspection_text5=view.findViewById(R.id.main_inspection_text5);
        main_inspection_num1=view.findViewById(R.id.main_inspection_num1);
        main_inspection_num2=view.findViewById(R.id.main_inspection_num2);
        main_inspection_num3=view.findViewById(R.id.main_inspection_num3);
        main_inspection_num4=view.findViewById(R.id.main_inspection_num4);
        main_inspection_num5=view.findViewById(R.id.main_inspection_num5);
        repair_all=view.findViewById(R.id.main_repair_all);
        inspection_all=view.findViewById(R.id.main_inspection_all);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView=view.findViewById(R.id.contact_recycler_view);
        mRecyclerView.setLayoutManager(mLayoutManager);
        OrderRequest orderRequest=new OrderRequest();
        orderRequest.setId(SPUtils.getInstance().getString("user_id",""));
        orderRequest.setStatus(null);
        orderRequest.setRoleCode(SPUtils.getInstance().getString("role_code",""));
        repairContents=BaseUtils.getInstence().getRepairList(repairContents,orderRequest,mContext);
        adapter=new RepairAdapter(repairContents);
        mRecyclerView.setAdapter(adapter);
        initData();
       // mRecyclerView.setLayoutManager(new GridLayoutManager(this,4,VERTICAL,false));
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setOnListener();
    }
    private void initData() {
//        UserLogin userLogin=new UserLogin();
//        userLogin.setUseCode(2);
       // UserLogin.useCode = 2;
        //switch (SPUtils.getInstance().getInt("role_code",1)){
        switch (SPUtils.getInstance().getInt("role_num",1)){
            case 1:
                initUserData();
                break;
            case 2:
                initServiceData();
                break;
            case 3:
                initWorkData();
                break;
            case 4:
                initUserManagerData();
                break;
        }
    //    repairContents=BaseUtils.getInstence().initRepairContent(repairContents);

    }

    private void initWorkData() {
        user_Type.setText(getResources().getString(R.string.REPAIR_MAN));//维修工
        main_repair_4.setVisibility(View.GONE);//1.3不可见
        main_repair_5.setVisibility(View.GONE);//1.3不可见
        main_inspection_4.setVisibility(View.GONE);//2.3不可见
        main_inspection_5.setVisibility(View.GONE);//2.3不可见
        main_repair_img1.setImageResource(R.drawable.ic_workorder);
        main_repair_img2.setImageResource(R.drawable.ic_workorder);
        main_repair_img3.setImageResource(R.drawable.ic_workorder);
        main_repair_num1.setText(String.valueOf(UnReadNum.main_repair_num1));
        main_repair_num2.setText(String.valueOf(UnReadNum.main_repair_num2));
        main_repair_num2.setText(String.valueOf(UnReadNum.main_repair_num3));
        main_repair_text1.setText(getResources().getString(R.string.worker_main_repair1));//待确认
        main_repair_text2.setText(getResources().getString(R.string.worker_main_repair2));//维修中
        main_repair_text3.setText(getResources().getString(R.string.worker_main_repair3));//维修中
        main_inspection_img1.setImageResource(R.drawable.ic_workorder);
        main_inspection_img2.setImageResource(R.drawable.ic_workorder);
        main_inspection_img3.setImageResource(R.drawable.ic_workorder);
        main_inspection_num1.setText(String.valueOf(UnReadNum.main_inspection_num1));
        main_inspection_num2.setText(String.valueOf(UnReadNum.main_inspection_num2));
        main_inspection_num3.setText(String.valueOf(UnReadNum.main_inspection_num3));
        main_inspection_text1.setText(getResources().getString(R.string.worker_main_inspection1));//待巡检
        main_inspection_text2.setText(getResources().getString(R.string.worker_main_inspection2));//巡检中
        main_inspection_text3.setText(getResources().getString(R.string.worker_main_inspection3));//巡检中
    }

    private void initServiceData() {
        user_Type.setText(getResources().getString(R.string.SERVICE_MAN));//服务商
        main_repair_3.setVisibility(View.GONE);//1.3为空
        main_repair_4.setVisibility(View.GONE);//1.3为空
        main_repair_5.setVisibility(View.GONE);//1.3为空
        main_inspection_4.setVisibility(View.GONE);//2.3为空
        main_inspection_5.setVisibility(View.GONE);//2.3为空
        main_repair_img1.setImageResource(R.drawable.ic_workorder);
        main_repair_img2.setImageResource(R.drawable.ic_workorder);
        main_repair_text1.setText(getResources().getString(R.string.service_main_repair1));//待接单
        main_repair_text2.setText(getResources().getString(R.string.service_main_repair2));//审核备件
        main_repair_num1.setText(String.valueOf(UnReadNum.main_repair_num1));
        main_repair_num2.setText(String.valueOf(UnReadNum.main_repair_num2));
        main_inspection_img1.setImageResource(R.drawable.ic_workorder);
        main_inspection_img2.setImageResource(R.drawable.ic_workorder);
        main_inspection_img3.setImageResource(R.drawable.ic_workorder);
        main_inspection_text1.setText(getResources().getString(R.string.service_main_inspection1));//新建巡检
        main_inspection_text2.setText(getResources().getString(R.string.service_main_inspection2));//验收
        main_inspection_text3.setText(getResources().getString(R.string.service_main_inspection3));//验收
        main_inspection_num1.setText(String.valueOf(UnReadNum.main_inspection_num1));
        main_inspection_num2.setText(String.valueOf(UnReadNum.main_inspection_num2));
        main_inspection_num3.setText(String.valueOf(UnReadNum.main_inspection_num3));
    }

    private void initUserData() {
        user_Type.setText(getResources().getString(R.string.USER));//用户
        main_inspection.setVisibility(View.GONE);
        main_repair_img1.setImageResource(R.drawable.ic_workorder);
        main_repair_img2.setImageResource(R.drawable.ic_workorder);
        main_repair_img3.setImageResource(R.drawable.ic_workorder);
        main_repair_img4.setImageResource(R.drawable.ic_workorder);
        main_repair_img5.setImageResource(R.drawable.ic_workorder);
        main_repair_text1.setText(getResources().getString(R.string.user_main_repair1));//添加
        main_repair_text2.setText(getResources().getString(R.string.user_main_repair2));//验收
        main_repair_text3.setText(getResources().getString(R.string.user_main_repair3));//评价
        main_repair_text4.setText(getResources().getString(R.string.user_main_repair4));//评价
        main_repair_text5.setText(getResources().getString(R.string.user_main_repair5));//评价
        main_repair_num1.setText(String.valueOf(UnReadNum.main_repair_num1));
        main_repair_num2.setText(String.valueOf(UnReadNum.main_repair_num2));
        main_repair_num3.setText(String.valueOf(UnReadNum.main_repair_num3));
        main_repair_num4.setText(String.valueOf(UnReadNum.main_repair_num4));
        main_repair_num5.setText(String.valueOf(UnReadNum.main_repair_num5));
    }
private void initUserManagerData(){
    user_Type.setText(getResources().getString(R.string.USER_MANAGER));//用户管理员
    main_repair_3.setVisibility(View.GONE);//1.3为空
    main_repair_4.setVisibility(View.GONE);//1.3为空
    main_repair_5.setVisibility(View.GONE);//1.3为空
    main_repair_img1.setImageResource(R.drawable.ic_workorder);
    main_repair_img2.setImageResource(R.drawable.ic_workorder);
    main_repair_text1.setText(getResources().getString(R.string.userManager_main_repair1));//添加
    main_repair_text2.setText(getResources().getString(R.string.userManager_main_repair2));//验收
    main_repair_num1.setText(String.valueOf(UnReadNum.main_repair_num1));
    main_repair_num2.setText(String.valueOf(UnReadNum.main_repair_num2));
    main_inspection_img1.setImageResource(R.drawable.ic_workorder);
    main_inspection_img2.setImageResource(R.drawable.ic_workorder);
    main_inspection_img3.setImageResource(R.drawable.ic_workorder);
    main_inspection_img4.setImageResource(R.drawable.ic_workorder);
    main_inspection_img5.setImageResource(R.drawable.ic_workorder);
    main_inspection_text1.setText(getResources().getString(R.string.userManager_main_inspection1));//查看巡检结果
    main_inspection_text2.setText(getResources().getString(R.string.userManager_main_inspection2));//查看巡检结果
    main_inspection_text3.setText(getResources().getString(R.string.userManager_main_inspection3));//查看巡检结果
    main_inspection_text4.setText(getResources().getString(R.string.userManager_main_inspection4));//查看巡检结果
    main_inspection_text5.setText(getResources().getString(R.string.userManager_main_inspection5));//查看巡检结果
    main_inspection_num1.setText(String.valueOf(UnReadNum.main_repair_num1));
    main_inspection_num2.setText(String.valueOf(UnReadNum.main_repair_num2));
    main_inspection_num3.setText(String.valueOf(UnReadNum.main_repair_num3));
    main_inspection_num4.setText(String.valueOf(UnReadNum.main_repair_num4));
    main_inspection_num5.setText(String.valueOf(UnReadNum.main_repair_num5));
}
    private void setOnListener(){
     //my_repair.setOnClickListener(this);
        main_repair_1.setOnClickListener(this);
        main_repair_2.setOnClickListener(this);
        main_repair_3.setOnClickListener(this);
        main_repair_4.setOnClickListener(this);
        main_repair_5.setOnClickListener(this);
        main_inspection_1.setOnClickListener(this);
        main_inspection_2.setOnClickListener(this);
        main_inspection_3.setOnClickListener(this);
        main_inspection_4.setOnClickListener(this);
        main_inspection_5.setOnClickListener(this);
        repair_all.setOnClickListener(this);
        inspection_all.setOnClickListener(this);
    }

    public void refresh() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_repair_1:
                switch  (SPUtils.getInstance().getInt("role_num",1)){
                    case 1://报修
                        BaseUtils.getInstence().intent(getContext(),RepairAddActivity.class);
                        break;
                    case 2://待接单
                        BaseUtils.getInstence().intent(getContext(),OrderSearchListActivity.class,"title","服务商待接单");
                        break;
                    case 3://维修工待接单
                        BaseUtils.getInstence().intent(getContext(),OrderSearchListActivity.class,"title","维修工待接单");
                        break;
                    case 4://甲方待审核
                        BaseUtils.getInstence().intent(getContext(),OrderSearchListActivity.class,"title","待审核");
                        break;
                }
                break;
            case R.id.main_repair_2:
                switch (SPUtils.getInstance().getInt("role_num",1)){
                    case 1://待确认
                        BaseUtils.getInstence().intent(getContext(),OrderSearchListActivity.class,"title","值机员待确认");
                        break;
                    case 2://服务商待审核
                        BaseUtils.getInstence().intent(getContext(),OrderSearchListActivity.class,"title","待审核备件");
                        Toast.makeText(getContext(),"Ops,审核备件正在开发中",Toast.LENGTH_LONG).show();
                        break;
                    case 3://维修工已接单
                        BaseUtils.getInstence().intent(getContext(),OrderSearchListActivity.class,"title","维修工已接单");
                        break;
                    case 4://
                        Toast.makeText(getContext(),"Ops,账单支付正在开发中",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.main_repair_3:
                switch (SPUtils.getInstance().getInt("role_num",1)){
                    case 1://维修中
                        BaseUtils.getInstence().intent(getContext(),OrderSearchListActivity.class,"title","维修中");
                        break;
                    case 3://维修工维修中
                        BaseUtils.getInstence().intent(getContext(),OrderSearchListActivity.class,"title","维修中");
                        break;
                }
                break;
            case R.id.main_repair_4:
                switch (SPUtils.getInstance().getInt("role_num",1)){
                    case 1://待验收
                        BaseUtils.getInstence().intent(getContext(),OrderSearchListActivity.class,"title","用户待验收");
                       // BaseUtils.getInstence().intent(getContext(),ContactActivity.class);
                        break;
                    case 4://
                       // BaseUtils.getInstence().intent(getContext(),OrderSearchListActivity.class,"title","待验收");
                       // BaseUtils.getInstence().intent(getContext(),ContactActivity.class);
                        break;
                }
                break;
            case R.id.main_repair_5:
                switch (SPUtils.getInstance().getInt("role_num",1)){
                    case 1://待评价
                        BaseUtils.getInstence().intent(getContext(),OrderSearchListActivity.class,"title","待评价");
                        Toast.makeText(getContext(),"Ops,设备管理正在开发中",Toast.LENGTH_LONG).show();
                        break;
                    case 4://
                       // Toast.makeText(getContext(),"Ops,设备管理正在开发中",Toast.LENGTH_LONG).show();
                        break;
                        default:
                            break;
                }
                break;
            case R.id.main_repair_all:
                BaseUtils.getInstence().intent(getContext(),UserOrderSearchActivitySpinner.class);
                break;
            case R.id.main_inspection_1:
                switch (SPUtils.getInstance().getInt("role_num",1)){
                    case 1://
                       // Toast.makeText(getContext(),"Ops,巡检全部查看正在开发中",Toast.LENGTH_LONG).show();
                        break;
                    case 2://服务商待接单//fuwushangchakan
                     //   BaseUtils.getInstence().intent(getContext(),InspectionSearchListActivity.class,"title","待确认");
                        break;
                    case 3://维修工待接单
                      //  BaseUtils.getInstence().intent(getContext(),InspectionSearchListActivity.class,"title","待确认");
                       // Toast.makeText(getContext(),"Ops,待执行正在开发中",Toast.LENGTH_LONG).show();
                        BaseUtils.getInstence().intent(getContext(), InspectionItemListActivity.class,"title","3");
                        break;
                    case 4://甲方巡检申请
                     BaseUtils.getInstence().intent(getContext(), InspectionAddActivity.class);
                     //   Toast.makeText(getContext(),"Ops,巡检全部查看正在开发中",Toast.LENGTH_LONG).show();
                        break;

                }
                break;
            case R.id.main_inspection_2:
                switch (SPUtils.getInstance().getInt("role_num",1)){
                    case 1://
                        break;
                    case 2://服务商待分配工程师
                        final AllUnDistributedWorkOrdersRequest allUnDistributedWorkOrdersRequest = new AllUnDistributedWorkOrdersRequest();
                       allUnDistributedWorkOrdersRequest.setType("inspection");
                        Net.instance.getAllUnDistributedWorkOrder(allUnDistributedWorkOrdersRequest, SPUtils.getInstance().getString("Token", " "))
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Subscriber<AllUnDistributedWorkOrdersResponse>() {
                                    @Override
                                    public void onCompleted() {

                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.v("ErrorGetUnauthorTask", System.currentTimeMillis() + "");
                                        e.printStackTrace();
                                    }

                                    @Override
                                    public void onNext(AllUnDistributedWorkOrdersResponse allUnDistributedWorkOrdersResponse) {
                                        if (TextUtils.equals(allUnDistributedWorkOrdersResponse.getCode(),"200")) {
                                            ArrayList<InspectionInfo> result = allUnDistributedWorkOrdersResponse.getResult().getList();
                                            if (result != null) {
                                                Bundle bundle = new Bundle();
                                                bundle.putParcelableArrayList("result", result);
                                                BaseUtils.getInstence().intent(getContext(),InspectionSearchListActivity.class,bundle,"title","2-2");
                                            }
                                        }
                                    }
                                });
                        break;
                    case 3://维修工巡检中
                     //   Toast.makeText(getContext(),"巡检中",Toast.LENGTH_LONG).show();
                      //  BaseUtils.getInstence().intent(getContext(),InspectionSearchListActivity.class,"title","巡检中");
                        break;
                    case 4://甲方待确认
                        BaseUtils.getInstence().intent(getContext(), InspectionSearchListActivity.class,"title","待确认");
                        break;
                }
                break;
            case R.id.main_inspection_3:
                switch (SPUtils.getInstance().getInt("role_num",1)){
                    case 2://服务商待审查
                       // BaseUtils.getInstence().intent(getContext(),InspectionSearchListActivity.class,"title","待审查");
                        break;
                    case 3://维修工待通过
                       // BaseUtils.getInstence().intent(getContext(),InspectionSearchListActivity.class,"title","待通过");
                        break;
                    case 4://甲方巡检中//2chakanshenherenwu
                        final AllUnauthorizedTaskRequest allUnauthorizedTaskRequest = new AllUnauthorizedTaskRequest();
                        allUnauthorizedTaskRequest.setPageNum(0);
                        allUnauthorizedTaskRequest.setPageSize(0);
                        allUnauthorizedTaskRequest.setUserId(1);
                        Net.instance.getAllUnauthorizedTask(allUnauthorizedTaskRequest, SPUtils.getInstance().getString("Token", " "))
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Subscriber<AllUnauthorizedTaskResponse>() {
                                    @Override
                                    public void onCompleted() {

                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.v("ErrorGetUnauthorTask", System.currentTimeMillis() + "");
                                        e.printStackTrace();
                                        Toast.makeText(mContext, "网络异常，请检查网络状态", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onNext(AllUnauthorizedTaskResponse allUnauthorizedTaskResponse) {
                                        if (TextUtils.equals(allUnauthorizedTaskResponse.getCode(),"200")) {
                                            ArrayList<InspectionInfo> result = allUnauthorizedTaskResponse.getResult();
                                            if (result != null) {
                                                Bundle bundle = new Bundle();
                                                bundle.putParcelableArrayList("result", result);
                                                BaseUtils.getInstence().intent(getContext(),InspectionSearchListActivity.class,bundle,"title","4-2");
                                            }
                                        }
                                    }
                                });

                      //  BaseUtils.getInstence().intent(getContext(),InspectionSearchListActivity.class,"title","巡检中");
                        break;
                }
                break;
            case R.id.main_inspection_4:
                switch (SPUtils.getInstance().getInt("role_num",1)){
                    case 3://
                        break;
                    case 4://甲方待付款
                     //   BaseUtils.getInstence().intent(getContext(),InspectionSearchListActivity.class,"title","待付款");
                      //  Toast.makeText(getContext(),"Ops,数据展示正在开发中",Toast.LENGTH_LONG).show();
                        break;
                }
                break;
            case R.id.main_inspection_5:
                switch (SPUtils.getInstance().getInt("role_num",1)){
                    case 3://无
                        break;
                    case 4://甲方待评价
                       // BaseUtils.getInstence().intent(getContext(),InspectionSearchListActivity.class,"title","待评价");
                        // Toast.makeText(getContext(),"Ops,数据展示正在开发中",Toast.LENGTH_LONG).show();
                        break;
                }
                break;
            case R.id.main_inspection_all:
                BaseUtils.getInstence().intent(getContext(), ProjectListActivity.class);
                break;
                default:
                    break;
        }
    }
    }


