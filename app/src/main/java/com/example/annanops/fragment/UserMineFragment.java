package com.example.annanops.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.annaops.R;
import com.example.annanops.activity.UerMessageActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserMineFragment extends Fragment implements View.OnClickListener {
    private CircleImageView icon_mine;
    private TextView mine_text;
    private TextView today_order_num;
    private TextView toweek_order_num;
    private TextView tomonth_order_num;
    private TextView my_join;
    private TextView my_message;
    private TextView my_wallet;
    private TextView my_config;//我的信息

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_user_mine,container,false);
       icon_mine=view.findViewById(R.id.icon_mine);
        mine_text=view.findViewById(R.id.mine_text);
        today_order_num=view.findViewById(R.id.today_order_num);
        toweek_order_num=view.findViewById(R.id.toweek_order_num);
        tomonth_order_num=view.findViewById(R.id.tomonth_order_num);
        my_join=view.findViewById(R.id.mine_my_join);
        my_message=view.findViewById(R.id.mine_my_message);
        my_wallet=view.findViewById(R.id.mine_my_wallet);
        my_config=view.findViewById(R.id.mine_my_config);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setOnListener();
        inirDatas();
    }

    private void inirDatas() {
        mine_text.setText("您好，你的名字");
        today_order_num.setText(String.valueOf(0));
        toweek_order_num.setText(String.valueOf(0));
        tomonth_order_num.setText(String.valueOf(0));
    }

    private void setOnListener() {
        my_join.setOnClickListener(this);
        my_message.setOnClickListener(this);
        my_wallet.setOnClickListener(this);
        my_config.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_my_join:
                Toast.makeText(this.getContext(),"Ops,我的加入正在开发中",Toast.LENGTH_LONG).show();
                break;
            case  R.id.mine_my_message:
                Intent intent=new Intent(this.getContext(), UerMessageActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_my_wallet:
                Toast.makeText(this.getContext(),"Ops,我的钱包正在开发中",Toast.LENGTH_LONG).show();
                break;
            case  R.id.mine_my_config:
                Toast.makeText(this.getContext(),"Ops,我的配置正在开发中",Toast.LENGTH_LONG).show();
                break;

    }}
}

