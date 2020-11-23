package com.example.ananops_android.fragment;

import android.content.Context;
import android.os.Bundle;
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
import com.example.ananops_android.adapter.RepairAdapter;
import com.example.ananops_android.db.AllAcceptedItemByMaintainerRequest;
import com.example.ananops_android.db.AllUnDistributedWorkOrdersRequest;
import com.example.ananops_android.db.AllUnDistributedWorkOrdersResponse;
import com.example.ananops_android.db.AllUnauthorizedTaskResponse;
import com.example.ananops_android.db.InspectionItemListResponse;
import com.example.ananops_android.db.InspectionListByUserIdAndStatusRequest;
import com.example.ananops_android.db.OrderRequest;
import com.example.ananops_android.db.OrderResponse;
import com.example.ananops_android.entity.InspectionInfo;
import com.example.ananops_android.entity.InspectionTaskItem;
import com.example.ananops_android.entity.RepairContent;
import com.example.ananops_android.entity.UnReadNum;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.SPUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserMainFragment extends LazyFragment2 implements View.OnClickListener{
    private LinearLayout noResult;
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
    private CircleImageView icon_mine;
    private RepairAdapter adapter;//适配器
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private int[] repairStatus = new int[5];
    private int[] inspectionStatus=new int[5];
    private List<RepairContent> repairContents = new ArrayList<>();
    private List<RepairContent> repairContentsUndo = new ArrayList<>();
    private List<InspectionInfo> inspectionInfos = new ArrayList<>();
    private List<InspectionTaskItem> inspectionTaskItems = new ArrayList<>();
   private Context mContext;
   private static String token;
   private static int role_num;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
   //   View view=inflater.inflate(R.layout.fragment_user_main,container,false);
      //  initData();
       // mRecyclerView.setLayoutManager(new GridLayoutManager(this,4,VERTICAL,false));
       // return view;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_user_main;
    }

    @Override
    protected void initView(View view) {
        role_num = SPUtils.getInstance(mContext).getInt("role_num", 0);
        token = SPUtils.getInstance(mContext).getString("Token", " ");
        mContext = getContext();
        // token = SPUtils.getInstance(mContext).getString("Token", "");
        noResult = view.findViewById(R.id.no_result_text);
        icon_mine = view.findViewById(R.id.icon_mine);
        user_Type = view.findViewById(R.id.user_type);
        main_repair = view.findViewById(R.id.main_repair);
        main_inspection = view.findViewById(R.id.main_inspection);
        main_repair_1 = view.findViewById(R.id.main_repair_1);
        main_repair_2 = view.findViewById(R.id.main_repair_2);
        main_repair_3 = view.findViewById(R.id.main_repair_3);
        main_repair_4 = view.findViewById(R.id.main_repair_4);
        main_repair_5 = view.findViewById(R.id.main_repair_5);
        main_repair_img1 = view.findViewById(R.id.main_repair_img1);
        main_repair_img2 = view.findViewById(R.id.main_repair_img2);
        main_repair_img3 = view.findViewById(R.id.main_repair_img3);
        main_repair_img4 = view.findViewById(R.id.main_repair_img4);
        main_repair_img5 = view.findViewById(R.id.main_repair_img5);
        main_repair_text1 = view.findViewById(R.id.main_order_text1);
        main_repair_text2 = view.findViewById(R.id.main_order_text2);
        main_repair_text3 = view.findViewById(R.id.main_order_text3);
        main_repair_text4 = view.findViewById(R.id.main_order_text4);
        main_repair_text5 = view.findViewById(R.id.main_order_text5);
        main_repair_num1 = view.findViewById(R.id.main_order_num1);
        main_repair_num2 = view.findViewById(R.id.main_order_num2);
        main_repair_num3 = view.findViewById(R.id.main_order_num3);
        main_repair_num4 = view.findViewById(R.id.main_order_num4);
        main_repair_num5 = view.findViewById(R.id.main_order_num5);
        main_inspection_1 = view.findViewById(R.id.main_inspection_1);
        main_inspection_2 = view.findViewById(R.id.main_inspection_2);
        main_inspection_3 = view.findViewById(R.id.main_inspection_3);
        main_inspection_4 = view.findViewById(R.id.main_inspection_4);
        main_inspection_5 = view.findViewById(R.id.main_inspection_5);
        main_inspection_img1 = view.findViewById(R.id.main_inspection_img1);
        main_inspection_img2 = view.findViewById(R.id.main_inspection_img2);
        main_inspection_img3 = view.findViewById(R.id.main_inspection_img3);
        main_inspection_img4 = view.findViewById(R.id.main_inspection_img4);
        main_inspection_img5 = view.findViewById(R.id.main_inspection_img5);
        main_inspection_text1 = view.findViewById(R.id.main_inspection_text1);
        main_inspection_text2 = view.findViewById(R.id.main_inspection_text2);
        main_inspection_text3 = view.findViewById(R.id.main_inspection_text3);
        main_inspection_text4 = view.findViewById(R.id.main_inspection_text4);
        main_inspection_text5 = view.findViewById(R.id.main_inspection_text5);
        main_inspection_num1 = view.findViewById(R.id.main_inspection_num1);
        main_inspection_num2 = view.findViewById(R.id.main_inspection_num2);
        main_inspection_num3 = view.findViewById(R.id.main_inspection_num3);
        main_inspection_num4 = view.findViewById(R.id.main_inspection_num4);
        main_inspection_num5 = view.findViewById(R.id.main_inspection_num5);
        repair_all = view.findViewById(R.id.main_repair_all);
        inspection_all = view.findViewById(R.id.main_inspection_all);
        mRecyclerView = view.findViewById(R.id.contact_recycler_view);
        main_repair_num1.setVisibility(View.INVISIBLE);
        main_repair_num2.setVisibility(View.INVISIBLE);
        main_repair_num3.setVisibility(View.INVISIBLE);
        main_repair_num4.setVisibility(View.INVISIBLE);
        main_repair_num5.setVisibility(View.INVISIBLE);
        main_inspection_num1.setVisibility(View.INVISIBLE);
        main_inspection_num2.setVisibility(View.INVISIBLE);
        main_inspection_num3.setVisibility(View.INVISIBLE);
        main_inspection_num4.setVisibility(View.INVISIBLE);
        main_inspection_num5.setVisibility(View.INVISIBLE);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        adapter=new RepairAdapter(repairContentsUndo);
        mRecyclerView.setAdapter(adapter);
        dataChangesNotify();
        initData();
        setOnListener();
    }

    @Override
    protected void onFragmentFirstVisible() {

        if (role_num == 2 ) {
            getInspectionListByFac();
        } else if ( role_num == 4) {
            getInspectionList();
        } else if (role_num == 3) {
            getInspectionItemList();
        }
        getRepairList();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    protected void onFragmentResume() {
        super.onFragmentResume();
        refresh();
    }

    @Override
    protected void onFragmentPause() {
        super.onFragmentPause();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void initData() {
//        UserLogin userLogin=new UserLogin();
//        userLogin.setUseCode(2);
       // UserLogin.useCode = 2;
        //switch (SPUtils.getInstance().getInt("role_code",1)){
        switch (role_num){
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

    private void dataChangesNotify() {
        if (repairContentsUndo.size() == 0) {
            noResult.setVisibility(View.VISIBLE);
        } else {
            noResult.setVisibility(View.GONE);
        }
        adapter.notifyDataSetChanged();
    }
    private void initWorkData() {
        icon_mine.setImageResource(R.drawable.ic_engineer_top);
        user_Type.setText(getResources().getString(R.string.REPAIR_MAN));//维修工
        main_repair_4.setVisibility(View.GONE);//1.3不可见
        main_repair_5.setVisibility(View.GONE);//1.3不可见
        main_inspection_4.setVisibility(View.GONE);//2.3不可见
        main_inspection_5.setVisibility(View.GONE);//2.3不可见
        main_repair_img1.setImageResource(R.drawable.ic_workorder);
        main_repair_img2.setImageResource(R.drawable.ic_workorder_1);
        main_repair_img3.setImageResource(R.drawable.ic_workorder_2);
//        main_repair_num1.setText(String.valueOf(UnReadNum.main_repair_num1));
//        main_repair_num2.setText(String.valueOf(UnReadNum.main_repair_num2));
//        main_repair_num3.setText(String.valueOf(UnReadNum.main_repair_num3));
        main_repair_text1.setText(getResources().getString(R.string.worker_main_repair1));//待确认
        main_repair_text2.setText(getResources().getString(R.string.worker_main_repair2));//维修中
        main_repair_text3.setText(getResources().getString(R.string.worker_main_repair3));//维修中
        main_inspection_img1.setImageResource(R.drawable.ic_inspection_order);
        main_inspection_img2.setImageResource(R.drawable.ic_inspection_order_1);
        main_inspection_img3.setImageResource(R.drawable.ic_inspection_order_2);
        main_inspection_num1.setText(String.valueOf(UnReadNum.main_inspection_num1));
        main_inspection_num2.setText(String.valueOf(UnReadNum.main_inspection_num2));
        main_inspection_num3.setText(String.valueOf(UnReadNum.main_inspection_num3));
        main_inspection_text1.setText(getResources().getString(R.string.worker_main_inspection1));//待巡检
        main_inspection_text2.setText(getResources().getString(R.string.worker_main_inspection2));//巡检中
        main_inspection_text3.setText(getResources().getString(R.string.worker_main_inspection3));//巡检中

    }

    private void initServiceData() {
        icon_mine.setImageResource(R.drawable.ic_faci_top);
        user_Type.setText(getResources().getString(R.string.SERVICE_MAN));//服务商
       // main_repair_3.setVisibility(View.GONE);//1.3为空
        main_repair_4.setVisibility(View.GONE);//1.3为空
        main_repair_5.setVisibility(View.GONE);//1.3为空
        main_inspection_4.setVisibility(View.GONE);//2.3为空
        main_inspection_5.setVisibility(View.GONE);//2.3为空
        main_repair_img1.setImageResource(R.drawable.ic_workorder);
        main_repair_img2.setImageResource(R.drawable.ic_workorder_3);
        main_repair_img3.setImageResource(R.drawable.ic_workorder_2);
        main_repair_text1.setText(getResources().getString(R.string.service_main_repair1));//待接单
        main_repair_text2.setText(getResources().getString(R.string.service_main_repair2));//审核备件
        main_repair_text3.setText(getResources().getString(R.string.service_main_repair3));//审核备件
        main_inspection_img1.setImageResource(R.drawable.ic_inspection_order);
        main_inspection_img2.setImageResource(R.drawable.ic_inspection_order_2);
        main_inspection_img3.setImageResource(R.drawable.ic_inspection_order_1);
        main_inspection_text1.setText(getResources().getString(R.string.service_main_inspection1));//新建巡检
        main_inspection_text2.setText(getResources().getString(R.string.service_main_inspection2));//验收
        main_inspection_text3.setText(getResources().getString(R.string.service_main_inspection3));//验收
//        main_inspection_num1.setText(String.valueOf(UnReadNum.main_inspection_num1));
//        main_inspection_num2.setText(String.valueOf(UnReadNum.main_inspection_num2));
//        main_inspection_num3.setText(String.valueOf(UnReadNum.main_inspection_num3));

    }

    private void initUserData() {
        icon_mine.setImageResource(R.drawable.ic_user_top);
        user_Type.setText(getResources().getString(R.string.USER));//用户
        main_inspection.setVisibility(View.GONE);
        main_repair_5.setVisibility(View.GONE);//1.3为空
        main_repair_img1.setImageResource(R.drawable.ic_workorder_3);
        main_repair_img2.setImageResource(R.drawable.ic_workorder);
        main_repair_img3.setImageResource(R.drawable.ic_workorder_2);
        main_repair_img4.setImageResource(R.drawable.ic_workorder_1);
        main_repair_text1.setText(getResources().getString(R.string.user_main_repair1));//添加
        main_repair_text2.setText(getResources().getString(R.string.user_main_repair2));//验收
        main_repair_text3.setText(getResources().getString(R.string.user_main_repair3));//评价
        main_repair_text4.setText(getResources().getString(R.string.user_main_repair4));//评价

    }
    private void initUserManagerData(){
    icon_mine.setImageResource(R.drawable.ic_manager_top);
    user_Type.setText(getResources().getString(R.string.USER_MANAGER));//用户管理员
   // main_repair_3.setVisibility(View.GONE);//1.3为空
    //main_repair_4.setVisibility(View.GONE);//1.3为空
    main_repair_5.setVisibility(View.GONE);//1.3为空
    main_repair_img1.setImageResource(R.drawable.ic_workorder);
    main_repair_img2.setImageResource(R.drawable.ic_workorder_1);
    main_repair_img3.setImageResource(R.drawable.ic_workorder_3);
    main_repair_img4.setImageResource(R.drawable.ic_workorder_2);
    main_repair_text1.setText(getResources().getString(R.string.userManager_main_repair1));//添加
    main_repair_text2.setText(getResources().getString(R.string.userManager_main_repair2));//验收
    main_repair_text3.setText(getResources().getString(R.string.userManager_main_repair3));//验收
    main_repair_text4.setText(getResources().getString(R.string.userManager_main_repair4));//验收
//    main_repair_num1.setText(String.valueOf(UnReadNum.main_repair_num1));
//    main_repair_num2.setText(String.valueOf(UnReadNum.main_repair_num2));
//    main_repair_num3.setText(String.valueOf(UnReadNum.main_repair_num3));
    main_inspection_img1.setImageResource(R.drawable.ic_inspection_order_4);
    main_inspection_img2.setImageResource(R.drawable.ic_inspection_order);
    main_inspection_img3.setImageResource(R.drawable.ic_inspection_order_2);
    main_inspection_img4.setImageResource(R.drawable.ic_inspection_order_3);
    main_inspection_img5.setImageResource(R.drawable.ic_inspection_order_1);
    main_inspection_text1.setText(getResources().getString(R.string.userManager_main_inspection1));//查看巡检结果
    main_inspection_text2.setText(getResources().getString(R.string.userManager_main_inspection2));//查看巡检结果
    main_inspection_text3.setText(getResources().getString(R.string.userManager_main_inspection3));//查看巡检结果
    main_inspection_text4.setText(getResources().getString(R.string.userManager_main_inspection4));//查看巡检结果
    main_inspection_text5.setText(getResources().getString(R.string.userManager_main_inspection5));//查看巡检结果

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
        if (role_num == 2 ) {
            getInspectionListByFac();
        } else if ( role_num == 4) {
            getInspectionList();
        } else if (role_num == 3) {
            getInspectionItemList();
        }
        getRepairList();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_repair_1:
                switch  (SPUtils.getInstance(mContext).getInt("role_num",1)){
                    case 1://报修
                        BaseUtils.getInstence().intent(getContext(),RepairAddActivity.class);
                        break;
                    case 2://服务商待接单
                        BaseUtils.getInstence().intent(getContext(),OrderSearchListActivity.class,"title","3");
                        break;
                    case 3://维修工待接单
                        BaseUtils.getInstence().intent(getContext(),OrderSearchListActivity.class,"title","5");
                        break;
                    case 4://甲方待审核
                        BaseUtils.getInstence().intent(getContext(),OrderSearchListActivity.class,"title","2");
                        break;
                        default:
                            break;
                }
                break;
            case R.id.main_repair_2:
                switch (SPUtils.getInstance(mContext).getInt("role_num",1)){
                    case 1://维修中
                        BaseUtils.getInstence().intent(getContext(),OrderSearchListActivity.class,"title","2");
                        break;
                    case 2://服务商待分配工程师
                        BaseUtils.getInstence().intent(getContext(),OrderSearchListActivity.class,"title","4");
                       // Toast.makeText(getContext(),"Ops,审核备件正在开发中",Toast.LENGTH_LONG).show();
                        break;
                    case 3://维修工提交备件申请
                        BaseUtils.getInstence().intent(getContext(),OrderSearchListActivity.class,"title","6");
                        break;
                    case 4://甲方确认备件申请
                        BaseUtils.getInstence().intent(getContext(),OrderSearchListActivity.class,"title","8");
                        break;
                       // Toast.makeText(getContext(),"Ops,账单支付正在开发中",Toast.LENGTH_LONG).show();
                    default:
                        break;
                }
                break;
            case R.id.main_repair_3:
                switch (SPUtils.getInstance(mContext).getInt("role_num",1)){
                    case 1://用户确认完成
                        BaseUtils.getInstence().intent(getContext(),OrderSearchListActivity.class,"title","10");
                        break;
                    case 2://服务商处理备件//未完
                        BaseUtils.getInstence().intent(getContext(), OrderSearchListActivity.class,"title","7");
                        break;
                    case 3://维修工维修中,确认完成//待定
                        BaseUtils.getInstence().intent(getContext(),OrderSearchListActivity.class,"title","13");
                        break;
                    case 4://甲方确认支付
                        BaseUtils.getInstence().intent(getContext(),OrderSearchListActivity.class,"title","11");
                        break;
                        default:
                            break;
                }
                break;
            case R.id.main_repair_4:
                switch (SPUtils.getInstance(mContext).getInt("role_num",1)){
                    case 1://已完成
                        BaseUtils.getInstence().intent(getContext(),OrderSearchListActivity.class,"title","13");
                       // BaseUtils.getInstence().intent(getContext(),ContactActivity.class);
                        break;
                    case 2:
                        BaseUtils.getInstence().intent(getContext(),OrderSearchListActivity.class,"title","13");
                        break;
                    case 4://已完成
                        BaseUtils.getInstence().intent(getContext(),OrderSearchListActivity.class,"title","13");
                        break;
                }
                break;
            case R.id.main_repair_all:
                BaseUtils.getInstence().intent(getContext(),OrderSearchListActivity.class);
                break;
            case R.id.main_inspection_1:
                switch (SPUtils.getInstance(mContext).getInt("role_num",1)){
                    case 1://
                       // Toast.makeText(getContext(),"Ops,巡检全部查看正在开发中",Toast.LENGTH_LONG).show();
                        break;
                    case 2://服务商待接单//fuwushangchakan
                        BaseUtils.getInstence().intent(getContext(), InspectionSearchListActivity.class, "statusDo","2-1");
                        break;
                    case 3://维修工待接单
                        Bundle bundle = new Bundle();
                        bundle.putString("inspectionItemId","0");
                        bundle.putString("statusDo","3-1");
                        BaseUtils.getInstence().intent(getContext(), InspectionItemListActivity.class,bundle);
                        break;
                    case 4://甲方巡检申请
                     BaseUtils.getInstence().intent(getContext(), InspectionAddActivity.class);
                     //   Toast.makeText(getContext(),"Ops,巡检全部查看正在开发中",Toast.LENGTH_LONG).show();
                        break;

                }
                break;
            case R.id.main_inspection_2:
                switch (SPUtils.getInstance(mContext).getInt("role_num",1)){
                    case 1://
                        break;
                    case 2://服务商待分配工程师
                        BaseUtils.getInstence().intent(getContext(), InspectionSearchListActivity.class, "statusDo","2-2");
                        break;
                    case 3://维修工巡检中
                        Bundle bundle = new Bundle();
                        bundle.putString("inspectionItemId","0");
                        bundle.putString("statusDo","3-2");
                        BaseUtils.getInstence().intent(getContext(), InspectionItemListActivity.class,bundle);
                        break;
                    case 4://甲方待确认
                        BaseUtils.getInstence().intent(getContext(), InspectionSearchListActivity.class,"statusDo","4-2");
                   //     BaseUtils.getInstence().getAndPassInspectionList(0,"4-2",getContext());
                        break;
                        default:
                            break;
                }
                break;
            case R.id.main_inspection_3:
                switch (SPUtils.getInstance(mContext).getInt("role_num",1)){
                    case 2://服务商已完成
                        BaseUtils.getInstence().intent(getContext(), InspectionSearchListActivity.class,"statusDo","2-3");
                        break;
                    case 3://维修工已完成
                        Bundle bundle = new Bundle();
                        bundle.putString("inspectionItemId","0");
                        bundle.putString("statusDo","3-3");
                        BaseUtils.getInstence().intent(getContext(), InspectionItemListActivity.class,bundle);
                        break;
                    case 4://甲方巡检中确认任务完成
                        BaseUtils.getInstence().intent(getContext(), InspectionSearchListActivity.class,"statusDo","4-3");
                    //    BaseUtils.getInstence().getAndPassInspectionList(3,"4-3",getContext());
                        break;
                }
                break;
            case R.id.main_inspection_4:
                switch (SPUtils.getInstance(mContext).getInt("role_num",1)){
                    case 3://
                        break;
                    case 4://甲方待确认
                        BaseUtils.getInstence().intent(getContext(), InspectionSearchListActivity.class,"statusDo","4-4");
                      //  BaseUtils.getInstence().getAndPassInspectionList(4,"4-4",getContext());
                        break;
                }
                break;
            case R.id.main_inspection_5:
                switch (SPUtils.getInstance(mContext).getInt("role_num",1)){
                    case 3://无
                        break;
                    case 4://甲方待评价
                        BaseUtils.getInstence().intent(getContext(), InspectionSearchListActivity.class,"statusDo","4-5");
                     //   BaseUtils.getInstence().getAndPassInspectionList(5,"4-5",getContext());
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

    private void getInspectionList() {
        InspectionListByUserIdAndStatusRequest inspectionListByUserIdAndStatusRequest = new InspectionListByUserIdAndStatusRequest();
        inspectionListByUserIdAndStatusRequest.setRole(1);
        inspectionListByUserIdAndStatusRequest.setUserId(Long.valueOf(SPUtils.getInstance(mContext).getString("user_id", "")));
        //getContext().getSharedPreferences("GeneralStore",Context.MODE_PRIVATE).getString("Token", " ")
        Net.instance.getInspectionTaskByUserId(inspectionListByUserIdAndStatusRequest, token)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AllUnauthorizedTaskResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("ErrorGetInsTaskById", System.currentTimeMillis() + "");
                        e.printStackTrace();
                        Toast.makeText(mContext, "获取巡检列表失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(AllUnauthorizedTaskResponse allUnauthorizedTaskResponse) {
                        if (TextUtils.equals(allUnauthorizedTaskResponse.getCode(), "200")) {
                            if (allUnauthorizedTaskResponse.getResult() != null) {
                                inspectionInfos = allUnauthorizedTaskResponse.getResult();
                            }
                        } else {
                            Log.i("insepctionListCode",allUnauthorizedTaskResponse.getCode());
                            Log.i("insepctionListMessage",allUnauthorizedTaskResponse.getMessage());

                        }
                       setMainPageInspectionNum();
                     //  initData();
                    }
                });
    }

    private void getInspectionListByFac() {
        AllUnDistributedWorkOrdersRequest inspectionListByUserIdAndStatusRequest = new AllUnDistributedWorkOrdersRequest();
        //  inspectionListByUserIdAndStatusRequest.setRole(1);
        inspectionListByUserIdAndStatusRequest.setUserId(Long.valueOf(SPUtils.getInstance(mContext).getString("user_id", "")));
        //getContext().getSharedPreferences("GeneralStore",Context.MODE_PRIVATE).getString("Token", " ")
        Net.instance.getInspectionTaskByFacilitatorId(inspectionListByUserIdAndStatusRequest, token)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AllUnDistributedWorkOrdersResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("ErrorGetInsTaskById", System.currentTimeMillis() + "");
                        e.printStackTrace();
                        Toast.makeText(mContext, "获取巡检列表失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(AllUnDistributedWorkOrdersResponse allUnauthorizedTaskResponse) {
                        if (TextUtils.equals(allUnauthorizedTaskResponse.getCode(),"200")) {
                            if (allUnauthorizedTaskResponse.getResult() != null) {
                                inspectionInfos=allUnauthorizedTaskResponse.getResult().getList();
                            }
                        }
                        setMainPageInspectionNum();
                        //  initData();
                    }
                });
    }
    private void getInspectionItemList() {
        AllAcceptedItemByMaintainerRequest allItemByTaskIdAndStatuRequest = new AllAcceptedItemByMaintainerRequest();
        allItemByTaskIdAndStatuRequest.setMaintainerId(Long.valueOf(SPUtils.getInstance(mContext).getString("user_id", "")));
        Net.instance.getAllItemByMaintainer(allItemByTaskIdAndStatuRequest,SPUtils.getInstance(mContext).getString("Token", " "))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<InspectionItemListResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                      //  Log.v("ErrorInspectionListTime", System.currentTimeMillis() + "");
                        e.printStackTrace();
                      //  initViews();
                    }

                    @Override
                    public void onNext(InspectionItemListResponse inspectionItemListResponse) {
                        if (TextUtils.equals(inspectionItemListResponse.getCode(), "200")) {
                            inspectionTaskItems.clear();
                            if (inspectionItemListResponse.getResult().size() > 0) {
                                inspectionTaskItems.addAll(inspectionItemListResponse.getResult());
                             //   Log.v("巡检子项列表1", inspectionItemListResponse.getResult().get(0).getId() + "");
                               // initViews();//
                                setMainPageInspectionNum();
                            }
                        } else {
                            Toast.makeText(mContext, inspectionItemListResponse.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    private void getRepairList() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setId(SPUtils.getInstance(mContext).getString("user_id",""));
        orderRequest.setStatus(null);
        orderRequest.setRoleCode(SPUtils.getInstance(mContext).getString("role_code",""));
      //  repairContents = BaseUtils.getInstence().getRepairList(repairContents, orderRequest, mContext);
        Net.instance.getRepairList(orderRequest, token)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OrderResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("LoginTime", "getErrorRepair"+System.currentTimeMillis() + "");
                        e.printStackTrace();
                        Toast.makeText(mContext, "获取维修列表失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(OrderResponse orderResponse) {
                        if(TextUtils.equals(orderResponse.getCode(),"200")){
                            repairContents.clear();
                            repairContents.addAll(orderResponse.getResult().getList());
//                            mLayoutManager = new LinearLayoutManager(getContext());
//                            mRecyclerView.setLayoutManager(mLayoutManager);
//                            adapter=new RepairAdapter(repairContents);
//                            mRecyclerView.setAdapter(adapter);
                            setMainPageRepairNum();
                        }
                        else{
                            Toast.makeText(mContext, orderResponse.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                });

    }

    private void setMainPageRepairNum() {
        //数组初始化
        for (int i = 0; i < repairStatus.length; i++) {
            repairStatus[i] = 0;
        }
        repairContentsUndo.clear();
        if (repairContents.size() > 0) {

            for (RepairContent repairContent:repairContents) {
                int status = repairContent.getStatus();
                switch (role_num) {
                    case 1:
                        switch (status) {
                            case 2:
                                repairStatus[1]++;
                                repairContentsUndo.add(repairContent);
                                break;
                            case 10://待确认
                                repairStatus[2]++;
                                repairContentsUndo.add(repairContent);
                                break;
                            case 13://已完成
                                repairStatus[3]++;
                           //     repairContentsUndo.add(repairContent);
                                break;
                                default:
                                    break;
                        }
                        break;
                    case 2:
                        switch (status) {
                            case 3://待接单
                                repairStatus[0]++;
                                repairContentsUndo.add(repairContent);
                                break;
                            case 4://待派工
                                repairStatus[1]++;
                                repairContentsUndo.add(repairContent);
                                break;
                            case 7://待审核备件
                                repairStatus[2]++;
                                repairContentsUndo.add(repairContent);
                                break;
                            case 13:
                                repairStatus[3]++;
                             //   repairContentsUndo.add(repairContent);
                                break;
                            default:
                                break;
                        }
                        break;
                    case 3:
                        switch (status) {
                            case 5://待接单
                                repairStatus[0]++;
                                repairContentsUndo.add(repairContent);
                                break;
                            case 6://维修中
                                repairStatus[1]++;
                                repairContentsUndo.add(repairContent);
                                break;
                            case 13://确认完成
                                repairStatus[2]++;
                            //    repairContentsUndo.add(repairContent);
                            default:
                                break;
                        }
                        break;
                    case 4:
                        switch (status) {
                            case 2://审核
                                repairStatus[0]++;
                                repairContentsUndo.add(repairContent);
                                break;
                            case 8://待确认备件
                                repairStatus[1]++;
                                repairContentsUndo.add(repairContent);
                                break;
                            case 11://支付
                                repairStatus[2]++;
                                repairContentsUndo.add(repairContent);
                            case 13://支付
                                repairStatus[3]++;
                             //   repairContentsUndo.add(repairContent);
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
            }

        }
        dataChangesNotify();
        showMainPageRepairNum();
    }

    private void showMainPageRepairNum() {
        main_repair_num1.setVisibility(View.INVISIBLE);
        main_repair_num2.setVisibility(View.INVISIBLE);
        main_repair_num3.setVisibility(View.INVISIBLE);
        main_repair_num4.setVisibility(View.INVISIBLE);
        main_repair_num5.setVisibility(View.INVISIBLE);
        switch (role_num) {
            case 1:
                if (repairStatus[0] > 0) {
                    main_repair_num1.setVisibility(View.VISIBLE);
                    main_repair_num1.setText(String.valueOf(repairStatus[0]));
                }
                if (repairStatus[1] > 0) {
                    main_repair_num2.setVisibility(View.VISIBLE);
                    main_repair_num2.setText(String.valueOf(repairStatus[1]));
                }
                if (repairStatus[2] > 0) {
                    main_repair_num3.setVisibility(View.VISIBLE);
                    main_repair_num3.setText(String.valueOf(repairStatus[2]));
                }
                if (repairStatus[3] > 0) {
                    main_repair_num4.setVisibility(View.VISIBLE);
                    main_repair_num4.setText(String.valueOf(repairStatus[3]));
                }
                break;
            case 2:
                if (repairStatus[0] > 0) {
                    main_repair_num1.setVisibility(View.VISIBLE);
                    main_repair_num1.setText(String.valueOf(repairStatus[0]));
                }
                if (repairStatus[1] > 0) {
                    main_repair_num2.setVisibility(View.VISIBLE);
                    main_repair_num2.setText(String.valueOf(repairStatus[1]));
                }
                if (repairStatus[2] > 0) {
                    main_repair_num3.setVisibility(View.VISIBLE);
                    main_repair_num3.setText(String.valueOf(repairStatus[2]));
                }
                break;
            case 3:
                if (repairStatus[0] > 0) {
                    main_repair_num1.setVisibility(View.VISIBLE);
                    main_repair_num1.setText(String.valueOf(repairStatus[0]));
                }
                if (repairStatus[1] > 0) {
                    main_repair_num2.setVisibility(View.VISIBLE);
                    main_repair_num2.setText(String.valueOf(repairStatus[1]));
                }
                if (repairStatus[2] > 0) {
                    main_repair_num3.setVisibility(View.VISIBLE);
                    main_repair_num3.setText(String.valueOf(repairStatus[2]));
                }
                break;
            case 4:
                if (repairStatus[0] > 0) {
                    main_repair_num1.setVisibility(View.VISIBLE);
                    main_repair_num1.setText(String.valueOf(repairStatus[0]));
                }
                if (repairStatus[1] > 0) {
                    main_repair_num2.setVisibility(View.VISIBLE);
                    main_repair_num2.setText(String.valueOf(repairStatus[1]));
                }
                if (repairStatus[2] > 0) {
                    main_repair_num3.setVisibility(View.VISIBLE);
                    main_repair_num3.setText(String.valueOf(repairStatus[2]));
                }
                break;
                default:
                    break;
        }

    }
    private void setMainPageInspectionNum() {
        for (int i = 0; i < inspectionStatus.length; i++) {
            inspectionStatus[i] = 0;
        }
        if (role_num == 2 || role_num == 4) {
            if (inspectionInfos.size() > 0) {
                for (InspectionInfo inspectionInfo : inspectionInfos) {
                    int status = inspectionInfo.getStatus();
                    switch (role_num) {
                        case 2:
                            switch (status) {
                                case 2://待接单
                                    inspectionStatus[0]++;
                                    break;
                                case 3://维修中
                                    inspectionStatus[1]++;
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case 4:
                            switch (status) {
                                case 0://审核
                                    inspectionStatus[1]++;
                                    break;
                                case 3://维修中
                                    inspectionStatus[2]++;
                                    break;
                                case 4://待确认
                                    inspectionStatus[3]++;
                                    break;
                                case 5://付款
                                    inspectionStatus[4]++;
                                default:
                                    break;
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        } else if (role_num == 3) {
            if (inspectionTaskItems.size() > 0) {
                for (InspectionTaskItem inspectionTaskItem : inspectionTaskItems) {
                    int status = inspectionTaskItem.getStatus();
                    switch (status) {
                        case 2://待接单
                            inspectionStatus[0]++;
                            break;
                        case 3://巡检中
                            inspectionStatus[1]++;
                            break;
                        case 4://已完成
                            inspectionStatus[2]++;
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        Log.v("inspectionStatus", "一"+inspectionStatus[0]+"二"+inspectionStatus[1]+"三"+inspectionStatus[2]);
            showMainPageInspectionNum();
    }

    private void showMainPageInspectionNum() {
        main_inspection_num1.setVisibility(View.INVISIBLE);
        main_inspection_num2.setVisibility(View.INVISIBLE);
        main_inspection_num3.setVisibility(View.INVISIBLE);
        main_inspection_num4.setVisibility(View.INVISIBLE);
        main_inspection_num5.setVisibility(View.INVISIBLE);
        switch (role_num) {
            case 1:

                break;
            case 2:
                if (inspectionStatus[0] > 0) {
                    main_inspection_num1.setVisibility(View.VISIBLE);
                    main_inspection_num1.setText(String.valueOf(inspectionStatus[0]));
                }
                if (inspectionStatus[1] > 0) {
                    main_inspection_num2.setVisibility(View.VISIBLE);
                    main_inspection_num2.setText(String.valueOf(inspectionStatus[1]));
                }
                if (inspectionStatus[2] > 0) {
                    main_inspection_num3.setVisibility(View.VISIBLE);
                    main_inspection_num3.setText(String.valueOf(inspectionStatus[2]));
                }
                break;
            case 3:
                if (inspectionStatus[0] > 0) {
                    main_inspection_num1.setVisibility(View.VISIBLE);
                    main_inspection_num1.setText(String.valueOf(inspectionStatus[0]));
                }
                if (inspectionStatus[1] > 0) {
                    main_inspection_num2.setVisibility(View.VISIBLE);
                    main_inspection_num2.setText(String.valueOf(inspectionStatus[1]));
                }
                if (inspectionStatus[2] > 0) {
                    main_inspection_num3.setVisibility(View.VISIBLE);
                    main_inspection_num3.setText(String.valueOf(inspectionStatus[2]));
                }
                break;
            case 4:
                if (inspectionStatus[0] > 0) {
                    main_inspection_num1.setVisibility(View.VISIBLE);
                    main_inspection_num1.setText(String.valueOf(inspectionStatus[0]));
                }
                if (inspectionStatus[1] > 0) {
                    main_inspection_num2.setVisibility(View.VISIBLE);
                    main_inspection_num2.setText(String.valueOf(inspectionStatus[1]));
                }
                if (inspectionStatus[2] > 0) {
                    main_inspection_num3.setVisibility(View.VISIBLE);
                    main_inspection_num3.setText(String.valueOf(inspectionStatus[2]));
                }
                if (inspectionStatus[3] > 0) {
                    main_inspection_num4.setVisibility(View.VISIBLE);
                    main_inspection_num4.setText(String.valueOf(inspectionStatus[3]));
                }
                if (inspectionStatus[4] > 0) {
                    main_inspection_num5.setVisibility(View.VISIBLE);
                    main_inspection_num5.setText(String.valueOf(inspectionStatus[4]));
                }
                break;
            default:
                break;
        }
    }
    }


