package com.example.ananops_android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ananops_android.R;

public class UerMessageActivity extends BaseActivity {
    private TextView title;
 //   private ImageView search_img;
    private ImageView back_img;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uer_message);
        initViews();
        initDatas();
        setOnListener();
    }

    private void initViews() {
        title=findViewById(R.id.txt_title);//标题
      //  search_img=findViewById(R.id.img_search);
        back_img=findViewById(R.id.img_back);
        title.setText("消息");
     //   search_img.setVisibility(View.INVISIBLE);
    }

    private void initDatas() {
        //获取系统信息
        //获取工单信息
        //获取培训信息
    }

    private void setOnListener() {
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
