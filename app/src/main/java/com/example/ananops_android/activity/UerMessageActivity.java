package com.example.ananops_android.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;

public class UerMessageActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView title;
 //   private ImageView search_img;
    private ImageView back_img;
    private TextView system_message_text;
    private TextView order_message_text;
    private TextView train_message_text;
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
        system_message_text=findViewById(R.id.system_message_text);
        order_message_text=findViewById(R.id.order_message_text);
        train_message_text=findViewById(R.id.train_message_text);
        title.setText("消息");
     //   search_img.setVisibility(View.INVISIBLE);
    }

    private void initDatas() {
        //获取系统信息
        //获取工单信息
        //获取培训信息
    }

    private void setOnListener() {
        back_img.setOnClickListener(this);
         system_message_text.setOnClickListener(this);
         order_message_text.setOnClickListener(this);
         train_message_text.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.img_back:
               finish();
               break;
           case R.id.system_message_text:
               Toast.makeText(this,"Ops,系统消息正在开发中",Toast.LENGTH_LONG).show();
               break;
           case R.id.order_message_text:
               Toast.makeText(this,"Ops,订单消息正在开发中",Toast.LENGTH_LONG).show();
               break;
           case R.id.train_message_text:
               Toast.makeText(this,"Ops,培训消息正在开发中",Toast.LENGTH_LONG).show();
           break;
       }
    }
}
