package com.example.ananops_android.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ananops_android.R;

public class QuestionSubmitActivity extends BaseActivity {
    private ImageView back_img;
    private TextView title;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_submit);
        back_img = findViewById(R.id.img_back);
        title = findViewById(R.id.txt_title);
        title.setText("安安运维");
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
    }
}
