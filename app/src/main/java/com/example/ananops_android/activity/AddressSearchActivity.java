package com.example.ananops_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.example.ananops_android.R;
import com.example.ananops_android.adapter.AdressSearchAdapter;
import com.example.ananops_android.entity.AddressSearch;
import com.example.ananops_android.view.EditTextWithDel;

import java.util.List;

public class AddressSearchActivity extends AppCompatActivity implements
        Inputtips.InputtipsListener, View.OnClickListener {
    private TextView title;
    private ImageView back_img;
    private EditText search_content;
    private TextView search_text;
    private static String TITLE;
    private List<Tip>tipList;
    private ListView listView;
    private AdressSearchAdapter adapter;
    private  String searchContent;
    private EditTextWithDel mEtSearchAdress;
    public static String DEFAULT_CITY = "北京";
    public static final int RESULT_CODE_INPUTTIPS = 101;
    public static final int REQUEST_SUC = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_search);
        initViews();
        setListener();
      //  initData();
    }
    private void initViews() {
       // search_content=findViewById(R.id.et_search_address);
        back_img=findViewById(R.id.img_back);
        title=findViewById(R.id.txt_title);
        mEtSearchAdress=(EditTextWithDel)findViewById(R.id.et_search_address);
        listView=findViewById(R.id.address_list);
    }
    /*
    监听事件
     */
    private void setListener(){
        back_img.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(tipList!=null) {
                    Tip tip = (Tip) adapter.getItem(position);
                    Intent intent = new Intent();
                    intent.putExtra("tip", tip);
                    setResult(RESULT_CODE_INPUTTIPS, intent);
                    finish();
                }
            }
        });
        mEtSearchAdress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String s1=s.toString();
                if(!s1.equals("")){
                    InputtipsQuery inputquery = new InputtipsQuery(s1,DEFAULT_CITY);
                    Inputtips inputTips = new Inputtips(AddressSearchActivity.this, inputquery);
                    inputTips.setInputtipsListener(AddressSearchActivity.this);
                    inputTips.requestInputtipsAsyn();
                }
                else {  // 如果输入为空  则清除 listView 数据
                    tipList.clear();
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    /**
     * 按下确认键触发，本例为键盘回车或搜索键
     */
//
    /**
     * 输入提示回调
     */
    @Override
    public void onGetInputtips(List<Tip> tips, int rCode) {
        // 正确返回
        if (rCode == REQUEST_SUC) {
            tipList = tips;
            adapter=new AdressSearchAdapter(this,tipList);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "错误码 :" + rCode, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {

    }

}
