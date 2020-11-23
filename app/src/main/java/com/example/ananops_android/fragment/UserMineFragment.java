package com.example.ananops_android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.activity.ChangePasswordActivity;
import com.example.ananops_android.activity.IntroductionActivity;
import com.example.ananops_android.activity.LoginActivity;
import com.example.ananops_android.activity.QuestionSubmitActivity;
import com.example.ananops_android.util.ActivityManager;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.SPUtils;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserMineFragment extends Fragment implements View.OnClickListener {
    private CircleImageView icon_mine;
    private TextView mine_text;
    private TextView my_join;
    private TextView my_message;
    private TextView my_wallet;
    private TextView my_config;//我的信息
    private TextView mine_my_intro;
    private TextView mine_my_safe;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_mine, container, false);
        mine_my_safe=view.findViewById(R.id.mine_my_safe);
        icon_mine = view.findViewById(R.id.icon_mine);
        mine_text = view.findViewById(R.id.mine_text);
        my_join = view.findViewById(R.id.mine_my_join);
        my_message = view.findViewById(R.id.mine_my_message);
        my_wallet = view.findViewById(R.id.mine_my_wallet);
        my_config = view.findViewById(R.id.mine_my_config);
        mine_my_intro = view.findViewById(R.id.mine_my_intro);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setOnListener();
        inirDatas();
    }

    private void inirDatas() {
        mine_text.setText("您好,"+ SPUtils.getInstance(getActivity()).getString("role_name","111"));
    }

    private void setOnListener() {
        mine_my_safe.setOnClickListener(this);
        my_join.setOnClickListener(this);
        my_message.setOnClickListener(this);
        my_wallet.setOnClickListener(this);
        my_config.setOnClickListener(this);
        mine_my_intro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_my_safe:
                Intent intent = new Intent(this.getContext(), ChangePasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_my_join:
                Toast.makeText(this.getContext(),"Ops,正在开发中",Toast.LENGTH_LONG).show();
                break;
            case  R.id.mine_my_message:
//                Intent intent=new Intent(this.getContext(), UerMessageActivity.class);
//                startActivity(intent);
                Toast.makeText(this.getContext(),"Ops,正在开发中",Toast.LENGTH_LONG).show();
                break;
            case R.id.mine_my_wallet:
                Intent intent1 = new Intent(this.getContext(), QuestionSubmitActivity.class);
                startActivity(intent1);
                break;
            case  R.id.mine_my_config:
                BaseUtils.getInstence().intent(getContext(), LoginActivity.class);
                ActivityManager.getInstance().finishAllActivity();
                break;
            case R.id.mine_my_intro:
                Intent intent2 = new Intent(this.getContext(), IntroductionActivity.class);
                startActivity(intent2);
                break;
                default:
                    break;

    }}
}

