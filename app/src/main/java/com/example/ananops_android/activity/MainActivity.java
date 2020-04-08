package com.example.ananops_android.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ananops_android.R;

public class MainActivity extends BaseActivity {

    protected Context mContext;
    private TextView textView;
    private String[] result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv_repair_time);

        mContext = this;



//        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
//        if(preferences.getString("weather",null)!=null){
//            Intent intent=new Intent(this,WeatherActivity.class);
//            startActivity(intent);
//            finish();//finish
//        }
    }

}
