package com.example.ananops_android.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.example.ananops_android.R;

public class IntroductionActivity extends BaseActivity {
    private ImageView back;
    private Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_introduction);
        initView();
    }

    private void initView() {
        back = findViewById(R.id.basicinfo_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
