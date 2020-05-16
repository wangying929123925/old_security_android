package com.example.ananops_android.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.adapter.ContactAdapter;
import com.example.ananops_android.datePicker.CustomDatePicker;
import com.example.ananops_android.datePicker.DateFormatUtils;
import com.example.ananops_android.db.CodeMessageResponse;
import com.example.ananops_android.db.EngineerListByGroupIdRequest;
import com.example.ananops_android.db.EngineerListByGroupIdResponse;
import com.example.ananops_android.db.InspectionEngineerDistributeRequest;
import com.example.ananops_android.db.RepairerListResponse;
import com.example.ananops_android.entity.Contacts;
import com.example.ananops_android.entity.RepairAddContent;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.InspectionUtils;
import com.example.ananops_android.util.SPUtils;
import com.example.ananops_android.view.EditTextWithDel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ContactActivity extends BaseActivity implements View.OnClickListener {
    private ListView sortListView;
    private List<Contacts> SourceDateList = new ArrayList<>();
    private ContactAdapter adapter;
    private EditTextWithDel mEtSearchName;
    private TextView title;
    private LinearLayout noResult;
    private TextView departNum;
    private TextView contactNum;
    private ImageView imageBack;
    private Context mContext;
    private String typeId;
    private String type;
    private String projectId;
    private Contacts contacts;
    private CustomDatePicker mTimerPicker;//时间选择
    private String[] result = new String[4];//日期
    final private RepairAddContent repairAddContent = new RepairAddContent();//save
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
      //  ActivityManager.getInstance().addActivity(this);
        setContentView(R.layout.activity_contacts_main);
     //   initDatas();
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mTimerPicker!=null){
            mTimerPicker.onDestroy();
        }

    }

    private void initDatas() {
        title.setText("工作人员");
        imageBack.setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            typeId = bundle.getString("typeId","1");
            type = bundle.getString("type","repair");
            projectId = bundle.getString("projectId", "1");
        }

    }
    private void initViews() {
        sortListView = findViewById(R.id.lv_contact);
        mEtSearchName = (EditTextWithDel) findViewById(R.id.et_search_contact);
        title = findViewById(R.id.txt_title);
        noResult = findViewById(R.id.no_result_text);
        departNum = findViewById(R.id.department_num);
        contactNum = findViewById(R.id.department_men_num);
        imageBack = findViewById(R.id.img_back);
        initDatas();
     //   initEvents();
      //  getResource();
       getResourceByGroupId();
    }

    private void getResourceByGroupId() {
        EngineerListByGroupIdRequest engineerListByGroupIdRequest = new EngineerListByGroupIdRequest();
        engineerListByGroupIdRequest.setOrderBy("string");
        engineerListByGroupIdRequest.setPosition("engineer");
      //  engineerListByGroupIdRequest.setGroupId(Long.valueOf(SPUtils.getInstance(mContext).getString("groupId", "1")));
        Net.instance.getRepairListByGroupId(engineerListByGroupIdRequest,SPUtils.getInstance(mContext).getString("Token", " "))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<EngineerListByGroupIdResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("getEngineerList","time");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(EngineerListByGroupIdResponse engineerListByGroupIdResponse) {
                        if (TextUtils.equals(engineerListByGroupIdResponse.getCode(), "200")) {
                            SourceDateList.clear();
                            int num = engineerListByGroupIdResponse.getResult().getList().size();
                            if (num > 0) {
                                for (int i = 0; i < num; i++) {
                                    Contacts contacts = new Contacts();
                                    contacts.setId(engineerListByGroupIdResponse.getResult().getList().get(i).getUserId());
                                    contacts.setName(engineerListByGroupIdResponse.getResult().getList().get(i).getUserName());
                                    SourceDateList.add(contacts);
                                }
                                setListAdapter();
                            } else {
                                Toast.makeText(mContext, "无维修工列表！", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }
    private void getResource(){
        Net.instance.getRepairerList(Long.valueOf(projectId), SPUtils.getInstance(mContext).getString("Token", " "))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RepairerListResponse>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {

                    }
                    @Override
                    public void onNext(RepairerListResponse repairerListResponse) {
                        if (TextUtils.equals(repairerListResponse.getCode(), "200")) {
                            SourceDateList.clear();
                            if (repairerListResponse.getResult().size() > 0) {
                                SourceDateList.addAll(repairerListResponse.getResult());
                                Log.v("维修工列表1", repairerListResponse.getResult().get(0).getId() + "");
                                setListAdapter();
                            } else {
                                Toast.makeText(mContext, "无维修工列表！", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(mContext, repairerListResponse.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    private void setListAdapter() {
     //   SourceDateList = filledData(getResources().getStringArray(R.array.contacts));
        adapter=new ContactAdapter(this,SourceDateList);
        sortListView.setAdapter(adapter);
        initEvents();
    }
    private void initEvents() {
        //注册监听事件
        //ListView的点击事件
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if(type.equals("repair")){
                    showExitAlertDialog(view, position);
                } else if (type.equals("inspection")) {
                    submitInspectionRepairer(view,position);
                }

            }
        });

        //根据输入框输入值的改变来过滤搜索
        mEtSearchName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
         //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
           //     filterData(s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void showExitAlertDialog(View view, int i) {
        contacts = new Contacts();
        if (SourceDateList.size() != 0) {
            contacts = SourceDateList.get(i);
        }
        final EditText editText = new EditText(mContext);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String endTime = "2023-12-31 23:59:00";
                String beginTime = BaseUtils.getInstence().getTime();
                mTimerPicker = new CustomDatePicker(mContext, new CustomDatePicker.Callback() {
                    @Override
                    public void onTimeSelected(long timestamp) {
                        editText.setText(DateFormatUtils.long2Str(timestamp, true));
                    }
                }, beginTime, endTime);
                // 允许点击屏幕或物理返回键关闭
                mTimerPicker.setCancelable(true);
                // 显示时和分
                mTimerPicker.setCanShowPreciseTime(true);
                // 允许循环滚动
                mTimerPicker.setScrollLoop(true);
                // 允许滚动动画
                mTimerPicker.setCanShowAnim(true);
                mTimerPicker.show(beginTime);
//                BaseUtils.showConfirmDialog(result, mContext, "请选择具体deadline的时间", new ConfirmDialogInterface() {
//                    @Override
//                    public void onConfirmClickListener() {
//                        editText.setText(result[0] + "-" + result[1] + "-" + result[2] + " " + result[3]);
//                    }
//
//                    @Override
//                    public void onCancelClickListener() {
//
//                    }
//                });
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("请输入deadline");
        builder.setTitle("提示");
        builder.setView(editText);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                String deadline = editText.getText().toString().trim();
                //指派维修工
                repairAddContent.setId(Long.valueOf(typeId));
                repairAddContent.setMaintainerId(Long.valueOf(contacts.getId()));
                repairAddContent.setDeadline(deadline);
                repairAddContent.setStatus(5);
                //repairAddContent.setDeadline(deadline);
                Net.instance.repairAddPost(repairAddContent, SPUtils.getInstance(mContext).getString("Token", ""))
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<CodeMessageResponse>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.v("ContactAddTime", System.currentTimeMillis() + "");
                                e.printStackTrace();
                                Toast.makeText(mContext, "提交失败", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNext(CodeMessageResponse codeMessageResponse) {
                                if(TextUtils.equals(codeMessageResponse.getCode(),"200")){
                                    Toast.makeText(mContext,"提交成功！",Toast.LENGTH_SHORT).show();
                                    Log.v("ContactAddTime200:", System.currentTimeMillis() + "");
                                    BaseUtils.getInstence().changeStatus(5,typeId,"服务商接单派工",mContext);
                                   // BaseUtils.getInstence().intent(mContext,UserMainActivity.class);
                                }
                                else{
                                    Toast.makeText(mContext,"服务器故障！",Toast.LENGTH_SHORT).show();
                                  //  BaseUtils.getInstence().intent(RepairAddActivity.this,UserMainActivity.class);
                                }
                            }
                        });
               // BaseUtils.getInstence().intent(ContactActivity.this, UserMainActivity.class);
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
private void submitInspectionRepairer(View view, int i) {
    contacts = new Contacts();
    if (SourceDateList.size() != 0) {
        contacts = SourceDateList.get(i);
    }
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setMessage("确认指派工程师吗");
    builder.setTitle("提示");
    builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            InspectionEngineerDistributeRequest inspectionEngineerDistributeRequest = new InspectionEngineerDistributeRequest();
            inspectionEngineerDistributeRequest.setTaskId(Long.valueOf(typeId));
            inspectionEngineerDistributeRequest.setEngineerId(Long.valueOf(contacts.getId()));
            Net.instance.inspectionDistributeEngineer(inspectionEngineerDistributeRequest,SPUtils.getInstance(mContext).getString("Token", " "))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<CodeMessageResponse>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.v("ErrorEngineerDistribute", System.currentTimeMillis() + "");
                            e.printStackTrace();
                            Toast.makeText(mContext, "服务器异常", Toast.LENGTH_SHORT).show();
                           // BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                        }

                        @Override
                        public void onNext(CodeMessageResponse codeMessageResponse) {
                            if (TextUtils.equals(codeMessageResponse.getCode(), "200")) {
                                Toast.makeText(mContext, "分配工程师成功！", Toast.LENGTH_LONG).show();
                                InspectionUtils.getInstence().changeInspectionItemStatus(2,typeId,mContext);
                                //BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                            } else {
                                Toast.makeText(mContext, codeMessageResponse.getMessage(), Toast.LENGTH_LONG).show();
                            }

                        }
                    });
            dialog.dismiss();
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
/**
 * 根据输入框中的值来过滤数据并更新ListView
 */
private void filterData(String filterStr) {
    List<Contacts> contactsList = new ArrayList<>();
    if(TextUtils.isEmpty(filterStr)){
        contactsList = SourceDateList;
    }else {
        contactsList.clear();
        for(Contacts contacts:SourceDateList){
            String name = contacts.getName();
           Pattern p = Pattern.compile(filterStr);
           Matcher m = p.matcher(name);
           // String match=".*"+filterStr+".*";
        //    if(name.matches(match)){
            if(m.find()){
                contactsList.add(contacts);
            }
        }
    }
    Collections.sort(contactsList,new SortDerpart());
    adapter.updateListView(contactsList);
    if (contactsList.size() == 0) {
        noResult.setVisibility(View.VISIBLE);
    } else {
        noResult.setVisibility(View.GONE);
    }
}

private List<Contacts> filledData(String[] data) {
    List<Contacts> mSortList = new ArrayList<>();
    ArrayList<Integer> countDepart = new ArrayList<>();
    for (int i = 0; i < data.length; i++) {
        Contacts contacts = new Contacts();
        int temp = Integer.parseInt(data[i].substring(0, 1));
        contacts.setDepartment(temp);
        //  contacts.setDepartment("第"+temp+"组");
        String temp1 = data[i];
        contacts.setName(temp1.substring(1));
        mSortList.add(contacts);

        if (!countDepart.contains(contacts.getDepartment())) {
            countDepart.add(contacts.getDepartment());
        }
    }
 //  departNum.setText(countDepart.size());
    departNum.setText(String.valueOf(countDepart.size()));
     contactNum.setText(String.valueOf(mSortList.size()));
    Collections.sort(mSortList,new SortDerpart());
    return mSortList;
}

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
               break;
        }
    }

    class SortDerpart implements Comparator<Contacts>{
    @Override
    public int compare(Contacts o1,Contacts o2){
    return o1.getDepartment()-o2.getDepartment();
    }
}
}

