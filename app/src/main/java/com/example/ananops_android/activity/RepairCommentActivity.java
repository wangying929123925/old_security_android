package com.example.ananops_android.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.util.BaseUtils;

public class RepairCommentActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView title;
    private ImageView back_img;
    private Button repair_commit_button;
    private TextView comment_repair_name;
    private RatingBar general_comment_rat;
    private RatingBar attitude_comment_rat;
    private RatingBar quality_comment_rat;
    private EditText et_comment;
    private String comment_content;
    private float general_comment_mum;
    private float attitude_comment_mum;
    private float quality_comment_mum;
    private static String ORDER_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_comment);
        initView();
        setOnListener();
    }

    private void initView(){
        title=findViewById(R.id.txt_title);//标题
        title.setText("评价");
        back_img=findViewById(R.id.img_back);
        repair_commit_button=findViewById(R.id.comment_submit_button);
        comment_repair_name=findViewById(R.id.comment_repair_name);
        general_comment_rat=findViewById(R.id.general_comment_rat);
        attitude_comment_rat=findViewById(R.id.attitude_comment_rat);
        quality_comment_rat=findViewById(R.id.quality_comment_rat);
        et_comment=findViewById(R.id.et_comment);
    }
    private void setOnListener() {

        back_img.setOnClickListener(this);
        repair_commit_button.setOnClickListener(this);
        general_comment_rat.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(RepairCommentActivity.this,"总体评分="+rating,Toast.LENGTH_LONG).show();
                general_comment_mum=rating;
            }
        });
        attitude_comment_rat.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(RepairCommentActivity.this,"态度评分="+rating,Toast.LENGTH_LONG).show();
                attitude_comment_mum=rating;
            }
        });
        quality_comment_rat.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(RepairCommentActivity.this,"质量评分="+rating,Toast.LENGTH_LONG).show();
                quality_comment_mum=rating;
            }
        });

    }

    @Override
    public void onClick(View v) {
     switch (v.getId()){
     case R.id.img_back:
         showExitAlertDialog(v);
         break;
     case R.id.comment_submit_button:
         Toast.makeText(RepairCommentActivity.this,"提交评论！",Toast.LENGTH_LONG).show();
         BaseUtils.getInstence().changeStatus(14, ORDER_ID, "提交评价",this);
         BaseUtils.getInstence().intent(this, UserMainActivity.class);
      break;
     }
    }
    public void showExitAlertDialog(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("评价未提交，退出将不报错编辑，确认退出吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
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
}
