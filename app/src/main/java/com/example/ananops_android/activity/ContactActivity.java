package com.example.ananops_android.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.adapter.ContactAdapter;
import com.example.ananops_android.entity.Contacts;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.view.EditTextWithDel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView sortListView;
    private List<Contacts> SourceDateList;
    private ContactAdapter adapter;
    private EditTextWithDel mEtSearchName;
    private TextView title;
    private TextView noResult;
    private TextView departNum;
    private TextView contactNum;
    private ImageView imageBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_main);
        initViews();
    }
    private void initDatas() {
        title.setText("工作人员");
        imageBack.setOnClickListener(this);

    }
    private void initViews() {
        sortListView=findViewById(R.id.lv_contact);
        mEtSearchName = (EditTextWithDel) findViewById(R.id.et_search_contact);
        title=findViewById(R.id.txt_title);
        noResult=findViewById(R.id.no_result_text);
        departNum=findViewById(R.id.department_num);
        contactNum=findViewById(R.id.department_men_num);
        imageBack=findViewById(R.id.img_back);
        initDatas();
        initEvents();
        setListAdapter();

    }
    private void setListAdapter() {
        SourceDateList = filledData(getResources().getStringArray(R.array.contacts));
        adapter=new ContactAdapter(this,SourceDateList);
        sortListView.setAdapter(adapter);
    }
    private void initEvents() {
        //注册监听事件
        //ListView的点击事件
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
               showExitAlertDialog(view);
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

    public void showExitAlertDialog(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确定指派该联系人？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                BaseUtils.getInstence().intent(ContactActivity.this,UserMainActivity.class);
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
    List<Contacts> contactsList=new ArrayList<>();
    if(TextUtils.isEmpty(filterStr)){
        contactsList=SourceDateList;
    }else {
        contactsList.clear();
        for(Contacts contacts:SourceDateList){
            String name=contacts.getContact_name();
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
    if (contactsList.size()==0){
noResult.setVisibility(View.VISIBLE);
    }
    else {
        noResult.setVisibility(View.GONE);
    }
}

private List<Contacts> filledData(String[] data) {
    List<Contacts> mSortList = new ArrayList<>();
    ArrayList<Integer> countDepart = new ArrayList<>();
    for(int i=0;i<data.length;i++){
    Contacts contacts=new Contacts();
    int temp=Integer.parseInt(data[i].substring(0,1));
        contacts.setDepartment(temp);
  //  contacts.setDepartment("第"+temp+"组");
    String temp1=data[i];
    contacts.setContact_name(temp1.substring(1));
    mSortList.add(contacts);

    if(!countDepart.contains(contacts.getDepartment())){
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

