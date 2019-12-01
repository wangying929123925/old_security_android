package com.example.ananops_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;

public class UserMessageFragment extends Fragment implements View.OnClickListener {
    private TextView title;
    //   private ImageView search_img;
    private ImageView back_img;
    private TextView system_message_text;
    private TextView order_message_text;
    private TextView train_message_text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_uer_message, container, false);
        title = view.findViewById(R.id.txt_title);//标题
        //  search_img=findViewById(R.id.img_search);
        back_img = view.findViewById(R.id.img_back);
        system_message_text = view.findViewById(R.id.system_message_text);
        order_message_text = view.findViewById(R.id.order_message_text);
        train_message_text = view.findViewById(R.id.train_message_text);
        title.setText("消息");
        back_img.setVisibility(View.INVISIBLE);
        initDatas();
        setOnListener();
        return view;
    }

    private void initViews() {

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
        switch (v.getId()) {
            case R.id.system_message_text:
                Toast.makeText(getContext(),"Ops,系统消息正在开发中", Toast.LENGTH_LONG).show();
                break;
            case R.id.order_message_text:
                Toast.makeText(getContext(), "Ops,工单消息正在开发中", Toast.LENGTH_LONG).show();
                break;
            case R.id.train_message_text:
                Toast.makeText(getContext(), "Ops,巡检消息正在开发中", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
