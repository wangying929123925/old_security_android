package com.example.ananops_android.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.ananops_android.R;

import java.util.Timer;
import java.util.TimerTask;


public class LoadingActivity extends BaseActivity {
    private ImageView welcomeImg = null;
    private static final long DELAY = 500;
    private TimerTask task;
    private RelativeLayout introduction_title;
    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_loading);
//        setTransparentStatusBar();
//        welcomeImg=findViewById(R.id.welcome_img);
//        AlphaAnimation anima = new AlphaAnimation(0.3f, 1.0f);
//        anima.setDuration(1500);// 设置动画显示时间
//        welcomeImg.startAnimation(anima);
//        anima.setAnimationListener(new AnimationImpl());
        setContentView(R.layout.activity_introduction);
        introduction_title = findViewById(R.id.introduction_title);
        introduction_title.setVisibility(View.INVISIBLE);
        Timer timer=new Timer();
        TimerTask tast=new TimerTask() {
            @Override
            public void run(){
              //  startActivity(localIntent);//执行
                skip();
            }
        };
        timer.schedule(tast,DELAY);//10秒后
    }

    private class AnimationImpl implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
        //    welcomeImg.setBackgroundResource(R.drawable.load_pic);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            skip(); // 动画结束后跳转到别的页面
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    }

    private void skip() {
       // startActivity(new Intent(this,LoginActivity.class));
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }

    private void setTransparentStatusBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色透明
            window.setStatusBarColor(Color.TRANSPARENT);
            int visibility = window.getDecorView().getSystemUiVisibility();
            //布局内容全屏展示
            visibility |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            //隐藏虚拟导航栏
            visibility |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            //防止内容区域大小发生变化
            visibility |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

            window.getDecorView().setSystemUiVisibility(visibility);
        }else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
