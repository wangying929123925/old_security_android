package com.example.ananops_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.adapter.ListCommonAdapter;
import com.example.ananops_android.adapter.ListViewHolder;
import com.example.ananops_android.entity.Replacement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChooseReplacementActivity extends AppCompatActivity {
    private ListView lv_choose_replacement;
    private List<Replacement> replacements=new ArrayList<>();
    private TextView title;
    private ImageView imageBack;
    private Button replacement_choose_confirm;
    private ListCommonAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replacement_choose);
        initViews();
        initData();
        setListener();
    }
    private void initViews() {
        lv_choose_replacement=findViewById(R.id.lv_choose_replacement);
        title=findViewById(R.id.txt_title);
        imageBack=findViewById(R.id.img_back);
        replacement_choose_confirm=findViewById(R.id.replacement_choose_confirm);
    }
    private void initData() {
        title.setText("选择备件");
        replacements=getReplacementData(replacements);
        mAdapter=new ListCommonAdapter<Replacement>(getApplicationContext(),R.layout.item_chooese_replacement,replacements) {
            @Override
            protected void convert(ListViewHolder viewHolder, Replacement replacement, int position) {
               viewHolder.setText(R.id.replacement_name,replacement.getRepalcement_name());//名称
               viewHolder.setText(R.id.replacement_id,replacement.getRepalcement_id());//id
               viewHolder.setText(R.id.replacement_type,replacement.getReplacement_type());//类型
               viewHolder.setText(R.id.replacement_price,String.valueOf(replacement.getReplacement_price()));//价格
            }
        };
    lv_choose_replacement.setAdapter(mAdapter);
    }

    public List<Replacement> getReplacementData(List<Replacement> replacements) {
        Replacement replacement=new Replacement();
        replacement.setRepalcement_name("易美吉双头锯-继电器（30*5*1）");
        replacement.setRepalcement_id("1324");
        replacement.setReplacement_type("30*50*1");
        replacement.setReplacement_price((float) 20.00);
        Replacement replacement1=new Replacement();
        replacement1.setRepalcement_name("易美吉双头锯-继电器（20*5*1）");
        replacement1.setRepalcement_id("4321");
        replacement1.setReplacement_type("20*50*1");
        replacement1.setReplacement_price((float) 40.00);
        replacements.add(replacement);
       // replacements.add(replacement);
       // replacements.add(replacement1);
        replacements.add(replacement1);
        return replacements;
    }

    private void setListener() {
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        replacement_choose_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Replacement> replacementAdd=new ArrayList<>();
                SparseBooleanArray checkedArray =lv_choose_replacement.getCheckedItemPositions();
                for (int i = 0; i < checkedArray.size(); i++) {
                    if (checkedArray.valueAt(i)) {
                        replacements.get(i).setReplacement_num(1);
                       replacementAdd.add(replacements.get(i));
                    }
                    }
                Toast.makeText(getApplicationContext(),"选中了"+replacementAdd.size(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent("android.intent.action.CART_BROADCAST");
                intent.putExtra("dataList",(Serializable) replacementAdd);
                LocalBroadcastManager.getInstance(ChooseReplacementActivity.this).sendBroadcast(intent);
                sendBroadcast(intent);
                finish();
            }
        });
    }



}
