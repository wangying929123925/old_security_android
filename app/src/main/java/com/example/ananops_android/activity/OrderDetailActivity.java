package com.example.ananops_android.activity;
import android.content.Context;
import android.content.Intent;
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
import com.example.ananops_android.db.OrderDetailResponse;
import com.example.ananops_android.db.RepairFileUrlResponse;
import com.example.ananops_android.fragment.OrderDetailAppendix;
import com.example.ananops_android.fragment.OrderDetailContentFragment;
import com.example.ananops_android.fragment.OrderDetailReplacementFragment;
import com.example.ananops_android.fragment.TimeLineFragment;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.SPUtils;
import com.example.ananops_android.view.CommentWindow;
import com.example.ananops_android.view.RepairFinishWindow;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OrderDetailActivity extends BaseActivity implements View.OnClickListener {
    private TabLayout tab_search_order_title;                            //定义TabLayout
    private ViewPager vp_search_order_pager;  //内容
    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;
    private ArrayList<String> value1 = new ArrayList<>();//详细信息
    private ArrayList<String> value2 = new ArrayList<>();//维修信息
    private ArrayList<String> value3 = new ArrayList<>();//附件信息
    private FindTabAdapter findTabAdapter;
    private TextView title;
  //  private ImageView search_img;
    private ImageView back_img;
    private static String ACTIVITY_STATUS;
    private static String ORDER_ID;
    private static String projectId;
    private Button order_detail_button1;
    private Button order_detail_button2;
    private LinearLayout fragment_order_commit;
  private RepairFinishWindow repairFinishWindow ;
    private CommentWindow commentWindow;
    private Context mContext;
      @Override
    protected void onCreate(Bundle savedInstanceState){
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_order_detail);
        //  ActivityManager.getInstance().addActivity(this);
          mContext=this;
          defaultArrayLists();
          initViews();
          initDatas();
          setOnListener();
      }

    private void initViews() {
        tab_search_order_title=findViewById(R.id.search_order_tab);
        vp_search_order_pager=findViewById(R.id.vp_search_order_pager);
        title=findViewById(R.id.txt_title);//标题
      //  search_img=findViewById(R.id.img_search);
        order_detail_button1 = findViewById(R.id.order_detail_button1);
        order_detail_button2 = findViewById(R.id.order_detail_button2);
        fragment_order_commit = findViewById(R.id.fragment_order_commit);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            ACTIVITY_STATUS = bundle.getString("status_do","no");
            ORDER_ID = bundle.getString("order_id","1");
            projectId = bundle.getString("projectId", "1");
        }
        Log.e("projectId", projectId );
        switch (ACTIVITY_STATUS){
            case "1-1":
                //审核不通过
                fragment_order_commit.setVisibility(View.VISIBLE);
                order_detail_button1.setVisibility(View.VISIBLE);
                order_detail_button2.setVisibility(View.VISIBLE);
                order_detail_button1.setText("重新填单");
                order_detail_button2.setText("放弃填单");
                order_detail_button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //重新填单
                        Toast.makeText(mContext, "重新填单", Toast.LENGTH_SHORT).show();
                    }
                });
                order_detail_button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //放弃填单
                        Toast.makeText(mContext, "放弃填单", Toast.LENGTH_SHORT).show();
                        BaseUtils.getInstence().intent(mContext,UserMainActivity.class);
                    }
                });
                break;
            case "1-2":
                //待验收
                fragment_order_commit.setVisibility(View.VISIBLE);
                order_detail_button1.setVisibility(View.VISIBLE);
                order_detail_button2.setVisibility(View.GONE);
                order_detail_button1.setText("确认完成");
                order_detail_button1.setOnClickListener(v -> {
             //通过
                    String userId = SPUtils.getInstance(mContext).getString("user_id", "1");
                    commentWindow = new CommentWindow(ORDER_ID,userId,1,mContext);
                    commentWindow.show();
                });
                break;
            case "2-1":
                fragment_order_commit.setVisibility(View.VISIBLE);
                order_detail_button1.setVisibility(View.VISIBLE);
                order_detail_button2.setVisibility(View.VISIBLE);
                order_detail_button1.setText("接单");
                order_detail_button2.setText("不接单");
                order_detail_button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      //  Toast.makeText(mContext, "不接单", Toast.LENGTH_SHORT).show();
                        BaseUtils.getInstence().changeStatus(4, ORDER_ID, "服务商不接单", mContext);
                        BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                        //接单派工
                    }
                });
                order_detail_button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //不接单
                      //  Toast.makeText(mContext, "不接单", Toast.LENGTH_SHORT).show();
                        BaseUtils.getInstence().changeStatus(14, ORDER_ID, "服务商不接单", mContext);
                        BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                    }
                });
                break;
            case "2-2":
                //待接单
                fragment_order_commit.setVisibility(View.VISIBLE);
                order_detail_button1.setVisibility(View.VISIBLE);
                order_detail_button2.setVisibility(View.GONE);
                order_detail_button1.setText("分配维修工");
                order_detail_button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //接单派工
                        Toast.makeText(mContext, "接单派工", Toast.LENGTH_SHORT).show();
                        //BaseUtils.getInstence().changeStatus(5, ORDER_ID, "服务商接单", mContext);
                        Bundle bundle = new Bundle();
                        bundle.putString("type","repair");
                        bundle.putString("typeId",ORDER_ID);
                        bundle.putString("projectId",projectId);
                        BaseUtils.getInstence().intent(mContext, ContactActivity.class,bundle);
                    }
                });
                break;
            case "2-3":
                //服务商待审核
                fragment_order_commit.setVisibility(View.VISIBLE);
                order_detail_button1.setVisibility(View.VISIBLE);
                order_detail_button2.setVisibility(View.VISIBLE);
                order_detail_button1.setText("通过");
                order_detail_button1.setText("否决");
                order_detail_button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //确认审核
                    //    Toast.makeText(mContext, "确认提交", Toast.LENGTH_SHORT).show();
                      BaseUtils.getInstence().changeStatus(8, ORDER_ID, "服务商审核通过", mContext);
                       BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                    }
                });
                order_detail_button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //不接单
                        //  Toast.makeText(mContext, "不接单", Toast.LENGTH_SHORT).show();
                        BaseUtils.getInstence().changeStatus(16, ORDER_ID, "服务商不接单", mContext);
                        BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                    }
                });
                break;
            case "3-1":
                //维修工待接单
                fragment_order_commit.setVisibility(View.VISIBLE);
                order_detail_button1.setVisibility(View.VISIBLE);
                order_detail_button2.setVisibility(View.VISIBLE);
                order_detail_button1.setText("接单");
                order_detail_button2.setText("不接单");
                order_detail_button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //维修工接单
                        Toast.makeText(mContext, "接单", Toast.LENGTH_SHORT).show();
                        BaseUtils.getInstence().changeStatus(6, ORDER_ID, "维修工接单", mContext);
                       // BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                    }
                });
                order_detail_button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //不接单
                   //     Toast.makeText(mContext, "不接单", Toast.LENGTH_SHORT).show();
                        BaseUtils.getInstence().changeStatus(15, ORDER_ID, "服务商审核通过", mContext);
                       BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                    }
                });
                break;
            case "3-2":
                //维修中
                fragment_order_commit.setVisibility(View.VISIBLE);
                order_detail_button1.setVisibility(View.VISIBLE);
                order_detail_button2.setVisibility(View.VISIBLE);
                order_detail_button1.setText("备件申请");
                order_detail_button1.setOnClickListener(v -> {
                    //备件表申请
                    BaseUtils.getInstence().intent(mContext,ChooseReplacementActivity.class,"repairId",ORDER_ID);
                });
                order_detail_button2.setText("提交结果");
                order_detail_button2.setOnClickListener(v -> {
                    //维修提交
                   repairFinishWindow = new RepairFinishWindow(ORDER_ID, mContext);
                    repairFinishWindow.show();
                });
                break;
            case "4-1":
                //审核申请
                fragment_order_commit.setVisibility(View.VISIBLE);
                order_detail_button1.setVisibility(View.VISIBLE);
                order_detail_button2.setVisibility(View.VISIBLE);
                order_detail_button2.setText("拒绝");
                order_detail_button1.setText("通过");
                order_detail_button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // Toast.makeText(mContext, "确认提交", Toast.LENGTH_SHORT).show();
                        BaseUtils.getInstence().changeStatus(15, ORDER_ID, "用户管理员否决", mContext);
                        BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                    }
                });
                order_detail_button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //审核计划
                      //  Toast.makeText(mContext, "确认提交", Toast.LENGTH_SHORT).show();
                        BaseUtils.getInstence().changeStatus(3, ORDER_ID, "提交方案", mContext);
                         BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                    }
                });
                break;
            case "4-2":
                //甲方审核备件
                fragment_order_commit.setVisibility(View.VISIBLE);
                order_detail_button1.setVisibility(View.VISIBLE);
                order_detail_button2.setVisibility(View.VISIBLE);
                order_detail_button1.setText("通过");
                order_detail_button2.setText("否决");
                order_detail_button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                     //   Toast.makeText(mContext, "确认提交", Toast.LENGTH_SHORT).show();
                        BaseUtils.getInstence().changeStatus(16, ORDER_ID, "用户管理员否决", mContext);
                        BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                    }
                });
                order_detail_button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //确认审核
                        BaseUtils.getInstence().changeStatus(10, ORDER_ID, "用户管理员通过", mContext);
                        BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                    }
                });
                break;
                default://查看界面
                  //  Toast.makeText(mContext,"ACTIVITY_STATUS"+ACTIVITY_STATUS, Toast.LENGTH_SHORT).show();
                    fragment_order_commit.setVisibility(View.GONE);
                    break;
        }
        back_img=findViewById(R.id.img_back);
        title.setText("工单详情");
    }
    private void initDatas() {
        Net.instance.getOrderDetail(Long.valueOf(ORDER_ID), SPUtils.getInstance(mContext).getString("Token"," "))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OrderDetailResponse>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {
                        getFileUrl();
                        Log.e("getRepairDetail", System.currentTimeMillis() + "");
                        e.printStackTrace();
                    }
                    @Override
                    public void onNext(OrderDetailResponse orderDetailResponse) {
                        if (TextUtils.equals(orderDetailResponse.getCode(), "200")) {
                                value1.clear();
                                value1.add(orderDetailResponse.getResult().getPmcProjectDto().getProjectName());//项目
                                value1.add(orderDetailResponse.getResult().getPmcProjectDto().getPartyAName());//
                                value1.add(orderDetailResponse.getResult().getMdmcTask().getCreator());//
                                value1.add(orderDetailResponse.getResult().getMdmcTask().getCall());//
                                value1.add(orderDetailResponse.getResult().getMdmcTask().getAppointTime());//
                                value1.add(orderDetailResponse.getResult().getMdmcTask().getAddressName());//
                                value1.add(statusChange(orderDetailResponse.getResult().getMdmcTask().getLevel()));
                                value1.add(orderDetailResponse.getResult().getMdmcTask().getSuggestion());
                                value2.clear();
                                if(orderDetailResponse.getResult().getPrincipalInfoDto()!=null){
                                    value2.add(orderDetailResponse.getResult().getPrincipalInfoDto().getUserName());//服务商
                                    value2.add(orderDetailResponse.getResult().getPrincipalInfoDto().getId());//
                                }else {
                                    value2.add("");
                                    value2.add("");
                                }
                               if (orderDetailResponse.getResult().getEngineerDto() != null) {
                                value2.add(orderDetailResponse.getResult().getEngineerDto().getUserName());//工程师
                               }else {
                                   value2.add("");
                               }
                               value2.add(orderDetailResponse.getResult().getMdmcTask().getSuggestion());//维修建议
                               value2.add(orderDetailResponse.getResult().getMdmcTask().getResult());//维修结果
                               getFileUrl();
                        } else {
                            getFileUrl();
                            Toast.makeText(mContext,orderDetailResponse.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private String statusChange(int i) {
          if(i==0){
              return "紧急";
          } else if (i == 1) {
              return "中等";
          }else {
              return "一般";
          }
    }
    private void setOnListener() {
         // search_img.setOnClickListener(this);
          back_img.setOnClickListener(this);
    }
    private void initFragment(){
        list_title = new ArrayList<>();
        list_title.add("工单详情");
        list_title.add("维修详情");
        list_title.add("处理进度");
        list_title.add("附件信息");
        list_fragment = new ArrayList<>();
        if (ACTIVITY_STATUS != null || !ACTIVITY_STATUS.equals("")) {
            list_fragment.add(OrderDetailContentFragment.newInstance(value1));
            list_fragment.add(OrderDetailReplacementFragment.getInstance(value2, ORDER_ID, ACTIVITY_STATUS));
            list_fragment.add(TimeLineFragment.newInstance(ORDER_ID));
            list_fragment.add(OrderDetailAppendix.newInstance(value3));
        }else {
            list_fragment.add(OrderDetailContentFragment.newInstance(value1));
            list_fragment.add(OrderDetailReplacementFragment.getInstance(value2, "1", "no"));
            list_fragment.add(TimeLineFragment.newInstance(ORDER_ID));
            list_fragment.add(OrderDetailAppendix.newInstance(value3));
        }
        findTabAdapter=new FindTabAdapter(getSupportFragmentManager(),list_fragment,list_title);
        vp_search_order_pager.setAdapter(findTabAdapter);
        tab_search_order_title.setupWithViewPager(vp_search_order_pager);
        vp_search_order_pager.setOffscreenPageLimit(1);
    }
    private void getFileUrl() {
          Net.instance.getFilesUrl(Long.valueOf(ORDER_ID), SPUtils.getInstance(mContext).getString("Token"," "))
                  .subscribeOn(Schedulers.newThread())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Subscriber<RepairFileUrlResponse>() {
                      @Override
                      public void onCompleted() {

                      }

                      @Override
                      public void onError(Throwable e) {
                          initFragment();
                          Log.e("getRepairFiles", System.currentTimeMillis() + "");
                          e.printStackTrace();
                        //  Toast.makeText(mContext, "网络异常，请检查网络状态orderUrl", Toast.LENGTH_SHORT).show();

                      }

                      @Override
                      public void onNext(RepairFileUrlResponse repairFileUrlResponse) {
                          if (TextUtils.equals(repairFileUrlResponse.getCode(), "200")) {
                              if (repairFileUrlResponse.getResult().size() > 0) {
                                  int urlSize = repairFileUrlResponse.getResult().get(0).getElementImgUrlDtoList().size();
                                  if (urlSize > 0) {
                                      value3.clear();
                                      for (int i = 0; i < urlSize; i++) {
                                          String url = repairFileUrlResponse.getResult().get(0).getElementImgUrlDtoList().get(i).getAttachmentId().replace("\\", "");
                                          value3.add(url);
                                          Log.i("取到的URL为：", url);
                                      }
                                  }
                              }

                          }
                          initFragment();
                      }
                  });
    }

    private void defaultArrayLists() {
      value1.clear();
        value1.add("");
        value1.add("");
        value1.add("");
        value1.add("");
        value1.add("");
        value1.add("");
        value1.add("");
        value1.add("");
        value1.add("");
        value1.add("");
        value2.clear();
        value2.add("");
        value2.add("");
        value2.add("");
        value2.add("");
        value2.add("");
        value3.clear();
        value3.add("");
    }
    @Override
    public void onClick(View v) {
          switch (v.getId()){
              case R.id.img_back:
                  finish();
//                  if (ACTIVITY_STATUS == "no" || ACTIVITY_STATUS.equals("no")) {
//                      finish();
//                  } else {
//                      AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                      builder.setMessage("确认返回吗？");
//                      builder.setTitle("提示");
//                      builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//                          @Override
//                          public void onClick(DialogInterface dialog, int which) {
//                              dialog.dismiss();
//                              finish();
//                          }
//                      });
//                      builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                          @Override
//                          public void onClick(DialogInterface dialog, int which) {
//                              dialog.dismiss();
//                          }
//                      });
//                      builder.create().show();
//                  }
                  break;
              case R.id.fragment_order_commit:
                  break;
                  default:
                      break;
          }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (repairFinishWindow != null) {
            repairFinishWindow.onDestroy();
        }
        if (commentWindow != null) {
            commentWindow.onDestroy();
        }
    }
}
