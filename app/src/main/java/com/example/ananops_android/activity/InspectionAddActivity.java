package com.example.ananops_android.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.adapter.ListCommonAdapter;
import com.example.ananops_android.adapter.ListViewHolder;
import com.example.ananops_android.db.InspectionItemListResponse;
import com.example.ananops_android.db.InspectionListResponse;
import com.example.ananops_android.db.ProjectListResponse;
import com.example.ananops_android.entity.InspectionAddContent;
import com.example.ananops_android.entity.InspectionInfo;
import com.example.ananops_android.entity.InspectionTaskItem;
import com.example.ananops_android.entity.ProjectInfo;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.ActivityManager;
import com.example.ananops_android.util.InspectionUtils;
import com.example.ananops_android.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class InspectionAddActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView et_project_name;//项目
    private TextView et_inspection_name;//巡检名
    private TextView et_inspection_time;//周期
    private TextView et_start_time;//开始时间
    private TextView et_plan_time;//计划时间
    private TextView et_inspection_content;//巡检内容
    private TextView et_inspection_tip;//备注
    private TextView et_inspection_do_result;//是否执行
    private CheckBox do_result;//选择框
    private TextView add_dot;//网点
    private TextView choose_service;//选择服务商
    private TextView inspection_add_confirm;//确认
    private ImageView basicinfo_back;
    private ListView recyclerView;
    private ListCommonAdapter mAdapter;
    private Context mContext;
    private ScrollView scrollView;
    private List<ProjectInfo> projectInfos = new ArrayList<>();
    private List<InspectionInfo> inspectionInfos = new ArrayList<>();
    private List<InspectionTaskItem> inspectionTaskItems = new ArrayList<>();
    private String[] projectArray;
    private String[] inspectionArray;
    private String[] inspectionItemArray;
    private static int projectTemp=0;
    private static int inspectionTemp=0;
    private static int inspectionItemTemp=0;
    private SparseBooleanArray checkedArray;
    private ListView itemList;
    private List<InspectionTaskItem> inspectionTaskItemList1;
    private static final int REQUEST_LOADPIC = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_add);
        ActivityManager.getInstance().addActivity(this);
        mContext = this;
        initViews();
        // initDatas();
        setOnListener();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LOADPIC) {
            if (resultCode == InspectionAddPicActicity.RESULT_CODE_INSPICS && data != null) {
                ArrayList<String> strings = new ArrayList<>();
                strings = data.getStringArrayListExtra("attachmentIds");
                 Log.v("获取到的strings：",strings+"");
                int i = data.getIntExtra("num", 1);
                if (null != inspectionTaskItemList1.get(i)) {
                    inspectionTaskItemList1.get(i).setAttachmentIds(strings);
                }
            }
        }
    }

    private void initViews() {
        et_project_name = findViewById(R.id.et_project_name);//项目
        et_inspection_name = findViewById(R.id.et_inspection_name);//巡检名
        et_inspection_time = findViewById(R.id.et_inspection_time);//周期
        et_start_time = findViewById(R.id.et_start_time);//开始时间
        et_plan_time = findViewById(R.id.et_plan_time);//计划时间
        et_inspection_content = findViewById(R.id.et_inspection_content);//巡检内容
        et_inspection_tip = findViewById(R.id.et_inspection_tip);//备注
        et_inspection_do_result = findViewById(R.id.et_inspection_do_result);//是否执行
        et_inspection_do_result.setText(String.valueOf(0));
        do_result = findViewById(R.id.do_result);//选择框
        do_result.setChecked(false);
        add_dot = findViewById(R.id.add_dot);//网点
        choose_service = findViewById(R.id.choose_service);//选择服务商
        inspection_add_confirm = findViewById(R.id.inspection_add_confirm);//确认
        basicinfo_back = findViewById(R.id.basicinfo_back);
        recyclerView = findViewById(R.id.dot_recycler_view);
//        mAdapter = new ListCommonAdapter<InspectionTaskItem>(getApplicationContext(), R.layout.item_inspection_sublist, inspectionTaskItems) {
//            @Override
//            protected void convert(ListViewHolder viewHolder, InspectionTaskItem inspectionTaskItem, int position) {
//                viewHolder.setText(R.id.inspection_name, inspectionTaskItem.getItemName());//名称
//                viewHolder.setText(R.id.inspection_id, String.valueOf(inspectionTaskItem.getId()));//id
//                viewHolder.setText(R.id.inspection_maintainername, inspectionTaskItem.getMaintainerName());//类型
//                viewHolder.setText(R.id.inspection_updateTime, inspectionTaskItem.getUpdateTime());//价格
//            }
//        };
//        recyclerView.setAdapter(mAdapter);
        projectInfos=getProjectList(projectInfos);
    }

    private void setOnListener() {
        et_project_name.setOnClickListener(this);
        et_inspection_name.setOnClickListener(this);
        do_result.setOnClickListener(this);
        add_dot.setOnClickListener(this);
        choose_service.setOnClickListener(this);
        basicinfo_back.setOnClickListener(this);
        inspection_add_confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.et_project_name:
                showChooseDislog(v,projectArray,et_project_name,1);
                Log.d("projectTemp",projectTemp+"");
                if(projectInfos.size()>projectTemp) {
                    //  inspectionInfos=getInspectionList(inspectionInfos,projectInfos.get(projectTemp).getId());}
                }
                break;
            case R.id.et_inspection_name:
                showChooseDislog(v,inspectionArray,et_inspection_name,2);
                if(inspectionInfos.size()>inspectionTemp){
               }
                break;
            case R.id.do_result:
                if (do_result.isChecked()) {
                    et_inspection_do_result.setText("1");
                }else {
                    et_inspection_do_result.setText("0");
                }
                break;
            case R.id.add_dot:
                 ListCommonAdapter mListAdapter = new ListCommonAdapter<InspectionTaskItem>(getApplicationContext(), R.layout.item_inspection_sublist, inspectionTaskItems) {
                    @Override
                    protected void convert(ListViewHolder viewHolder, InspectionTaskItem inspectionTaskItem, int position) {
                        viewHolder.setText(R.id.inspection_name, inspectionTaskItem.getItemName());//名称
                        viewHolder.setText(R.id.inspection_id, String.valueOf(inspectionTaskItem.getId()));//id
                        viewHolder.setText(R.id.inspection_maintainername, inspectionTaskItem.getCreator());//类型
                        viewHolder.setText(R.id.inspection_updateTime, inspectionTaskItem.getUpdateTime());//价格
                    }
                };
                itemList =new ListView(mContext);
                itemList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
//                itemList.setAdapter(new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_multiple_choice, inspectionTaskItems));
                itemList.setAdapter(mListAdapter);
                new AlertDialog.Builder(mContext)
                        .setTitle("请选择巡检子项")
                        .setView(itemList)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                checkedArray = itemList.getCheckedItemPositions();

                                List<InspectionTaskItem> items = packageCheckedSub();
                                mAdapter = new ListCommonAdapter<InspectionTaskItem>(getApplicationContext(), R.layout.item_inspection_sub_add, items) {
                                    @Override
                                    protected void convert(ListViewHolder viewHolder, InspectionTaskItem inspectionTaskItem, int position) {
                                       // CheckBox checkBox = viewHolder.getView(R.id.inspection_sub_checkBox1);
                                       // checkBox.setChecked(true);
                                        viewHolder.setText(R.id.Plist_name, inspectionTaskItem.getItemName());//名称
                                        viewHolder.setText(R.id.Plist_id, String.valueOf(inspectionTaskItem.getId()));//id
                                        viewHolder.setText(R.id.Plist_type, inspectionTaskItem.getCreator());//类型
                                        viewHolder.setText(R.id.Plist_price, inspectionTaskItem.getUpdateTime());//价格
                                        viewHolder.setOnClickListener(R.id.btn_add_pic, new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = new Intent(InspectionAddActivity.this,InspectionAddPicActicity.class);
                                                intent.putExtra("num",position);
                                                startActivityForResult(intent, REQUEST_LOADPIC);
                                            }
                                        });
                                    }
                                };
                                recyclerView.setAdapter(mAdapter);

                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
//                DialogItemAdapter adapter = new DialogItemAdapter(mContext, inspectionTaskItems);
//                AlertDialog alertDialog = new AlertDialog
//                        .Builder(mContext)
//                        .setAdapter(adapter, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                            }
//                        })
//                        .setSingleChoiceItems(adapter, 0, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(mContext, "选择了" + which, Toast.LENGTH_SHORT).show();
//
//                            }
//                        }).create();
//                alertDialog.show();

                break;
//            case R.id.choose_service:
//                String[]services={"1","2","3"};
//                showChooseDislog(v,services,choose_service,3);
//                break;
            case R.id.basicinfo_back:
                showExitAlertDialog(v);
                break;
            case R.id.inspection_add_confirm:
                submitInspection();
                break;
                default:
                    break;
        }
    }

    public void showExitAlertDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("未填完巡检计划，确认退出吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                InspectionAddActivity.this.finish();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }
    /*
     获取项目列表
      */
    private List<ProjectInfo> getProjectList(final List<ProjectInfo> projectInfo) {
        Net.instance.getProjectList(Long.valueOf(SPUtils.getInstance().getString("groupId", "1")), SPUtils.getInstance().getString("Token", " "))
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
                            projectInfo.clear();
                            if (projectListResponse.getResult().size() > 0) {
                                projectArray = new String[projectListResponse.getResult().size()];
                                for (int i = 0; i < projectListResponse.getResult().size(); i++) {
                                    projectInfo.add(projectListResponse.getResult().get(i));
                                    projectArray[i] = projectListResponse.getResult().get(i).getProjectName();
                                }
                                Log.v("项目列表1", projectListResponse.getResult().get(0).getId() + "");
                            } else {
                                Toast.makeText(mContext, "无项目列表！", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(mContext, projectListResponse.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
        return projectInfo;
    }

    /*
    获取巡检列表
     */
    private List<InspectionInfo> getInspectionList(final List<InspectionInfo> inspectionInfoList, Long projectId) {
        if (projectId != null) {
            Net.instance.getInspectionList(projectId, SPUtils.getInstance().getString("Token", " "))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<InspectionListResponse>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.v("ErrorGetInspectionList", System.currentTimeMillis() + "");
                            e.printStackTrace();
                            Toast.makeText(mContext, "网络异常，请检查网络状态getInspectionList", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNext(InspectionListResponse inspectionListResponse) {
                            if (TextUtils.equals(inspectionListResponse.getCode(), "200")) {
                                inspectionInfoList.clear();
                                if (inspectionListResponse.getResult().size() > 0) {
                                    inspectionArray = new String[inspectionListResponse.getResult().size()];
                                    Log.v("巡检列表数",inspectionArray.length+"");
                                    for (int i = 0; i < inspectionListResponse.getResult().size(); i++) {
                                        inspectionInfoList.add(inspectionListResponse.getResult().get(i));
                                        if(inspectionListResponse.getResult().get(i).getTaskName()!=null){
                                        inspectionArray[i] = inspectionListResponse.getResult().get(i).getTaskName();}
                                        else {
                                            inspectionArray[i]="null" ;
                                        }
                                        Log.v("巡检列表名", inspectionListResponse.getResult().get(i).getTaskName() + "");
                                    }
                                    Log.v("巡检列表1", inspectionListResponse.getResult().get(0).getId() + "");
                                    Log.v("inspectionArray[1]",inspectionArray[0]+ "");
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
        return inspectionInfoList;
    }
                /*
                获取巡检子项
                 */
    private List<InspectionTaskItem> getInspectionTaskItems(final List<InspectionTaskItem> inspectionTaskItemList, Long inspectTaskId) {
        if (inspectTaskId != null) {
            Net.instance.getInspectionItemList(inspectTaskId, SPUtils.getInstance().getString("Token", " "))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<InspectionItemListResponse>() {
                        @Override
                        public void onCompleted() {

//                            mAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.v("ErrorGetInspectionItem", System.currentTimeMillis() + "");
                            e.printStackTrace();
                            Toast.makeText(mContext, "网络异常，请检查网络状态ErrorGetInspectionItem", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNext(InspectionItemListResponse inspectionItemListResponse) {
                            if (TextUtils.equals(inspectionItemListResponse.getCode(), "200")) {
                                inspectionTaskItemList.clear();
                                if (inspectionItemListResponse.getResult().size() > 0) {
                                    inspectionItemArray = new String[inspectionItemListResponse.getResult().size()];
                                    inspectionTaskItemList.addAll(inspectionItemListResponse.getResult());
                                    Log.v("巡检子项列表1", inspectionItemListResponse.getResult().get(0).getId() + "");
                                } else {
                                    Toast.makeText(mContext, "无巡检子项列表！", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(mContext, inspectionItemListResponse.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(mContext, "请选择巡检项目！", Toast.LENGTH_LONG).show();
        }
        return inspectionTaskItemList;
    }


    private void refresh() {
        mAdapter.notifyDataSetChanged();
    }

    /*
    1;项目
    2，服务商
     */
    public void showChooseDislog(View view, final String[] item, final TextView textView, final int type) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择")
                .setSingleChoiceItems(item, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView.setText(item[which]);
                        if (type == 1) {
                            Log.v("projectName", item[which]);
                            projectTemp = which;
                            inspectionInfos=getInspectionList(inspectionInfos,projectInfos.get(projectTemp).getId());
                        } else if (type == 2) {
                            Log.v("inspectionName", item[which]);
                            inspectionTemp = which;
                            setInspectionInfo(which);
                            inspectionTaskItems=getInspectionTaskItems(inspectionTaskItems,inspectionInfos.get(inspectionTemp).getId());
//                            refresh();
                        } else {
                            Log.v("serviceName", item[which]);
                        }
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }
    private void setInspectionInfo(int i){
        Log.v("巡检列表N",i+"");
        if(inspectionInfos.size()>i){
            if(inspectionInfos.get(i).getCycleTime()!=0){
                et_inspection_time.setText(String.valueOf(inspectionInfos.get(i).getCycleTime()));}
            et_start_time.setText(inspectionInfos.get(i).getScheduledStartTime());
            et_plan_time.setText(inspectionInfos.get(i).getScheduledFinishTime());
            et_inspection_content.setText(inspectionInfos.get(i).getInspectionContent());
            et_inspection_tip.setText(inspectionInfos.get(i).getInspectionCondition());
            choose_service.setText(projectInfos.get(i).getBleaderName());
        }
        else {
            et_inspection_time.setText("");
            et_start_time.setText("");
            et_plan_time.setText("");
            et_inspection_content.setText("");
            et_inspection_tip.setText("");
        }
    }
    private List<InspectionTaskItem> packageCheckedSub(){
        inspectionTaskItemList1 = new ArrayList<>();
//        checkedArray = recyclerView.getCheckedItemPositions();
        if (checkedArray.size() > 0) {
            for (int i = 0; i < checkedArray.size(); i++) {
                if (checkedArray.valueAt(i)) {
                    //添加巡检子项
                    inspectionTaskItems.get(i).setUserId(Long.valueOf(SPUtils.getInstance().getString("user_id", "")));
                    inspectionTaskItemList1.add(inspectionTaskItems.get(i));
                    Log.v("选中的位置：", i+ "");
                }
            }
            Log.v("选中子项名称", inspectionTaskItemList1.get(0).getItemName()+ "");
            return inspectionTaskItemList1;
        } else {
            Toast.makeText(mContext, "请选择巡检子项！", Toast.LENGTH_SHORT).show();
            return null;
        }
    }
    private void submitInspection() {
        //获取选择子项
        InspectionAddContent inspectionAddContent=new InspectionAddContent();
        if (inspectionTaskItemList1 == null) {
            Toast.makeText(mContext, "请添加网点", Toast.LENGTH_SHORT).show();
        }
        inspectionAddContent.setImcAddInspectionItemDtoList(inspectionTaskItemList1);
        Log.v("inspectionTaskItemList", inspectionTaskItemList1.size() + "");
        inspectionAddContent.setLoginAuthDto(new InspectionAddContent.LoginAuthDtoBean());
        inspectionAddContent.setProjectId(projectInfos.get(projectTemp).getId());
        inspectionAddContent.setFacilitatorGroupId(4L);
        inspectionAddContent.setFacilitatorId(projectInfos.get(projectTemp).getBleaderId());
        inspectionAddContent.setScheduledStartTime(inspectionInfos.get(inspectionTemp).getScheduledStartTime());
        inspectionAddContent.setTaskName(inspectionInfos.get(inspectionTemp).getTaskName());
        inspectionAddContent.setTotalCost(inspectionInfos.get(inspectionTemp).getTotalCost());
        inspectionAddContent.setFacilitatorManagerId(projectInfos.get(projectTemp).getAleaderId());
        inspectionAddContent.setPrincipalId(Long.valueOf(SPUtils.getInstance().getString("user_id", "")));
        inspectionAddContent.setFrequency(inspectionInfos.get(inspectionTemp).getCycleTime());
        inspectionAddContent.setInspectionType(1);
        inspectionAddContent.setStatus(0);
        inspectionAddContent.setUserId(Long.valueOf(SPUtils.getInstance().getString("user_id", "")));
        Log.v("inspectionAddContent", inspectionAddContent+ "");
        InspectionUtils.getInstence().addInspection(inspectionAddContent,mContext);
    }

}
