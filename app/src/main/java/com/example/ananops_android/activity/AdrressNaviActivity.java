package com.example.ananops_android.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.example.ananops_android.R;
import com.example.ananops_android.util.ActivityManager;

public class AdrressNaviActivity extends AppCompatActivity {
    MapView mMapView = null;
    private AMap aMap;
    private ImageView back_img;
    private TextView title;
    private LatLng latLng;//经纬度信息
    private Circle circle;
    private Marker marker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
        setContentView(R.layout.activity_address_navigation);
        mMapView = (MapView) findViewById(R.id.mapSearch);
        mMapView.onCreate(savedInstanceState);
        initView();
        initLoc();
    }

    private void initView() {
        back_img = findViewById(R.id.img_back);
        title=findViewById(R.id.txt_title);
        title.setText("地点");
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            finish();
            }
        });
    }

    private void initLoc() {
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        Intent intent = getIntent();
        if (null != intent) {
            latLng = intent.getParcelableExtra("latLng");
        } else {
            latLng=new LatLng(39.26,118);
        }
       setMapCenter(latLng,100);
    }
    /**
     * 设置地图中心点
     *
     * @param latLng
     */
    private void setMapCenter(LatLng latLng, int radius) {
        aMap.moveCamera(CameraUpdateFactory.changeLatLng(latLng));
        aMap.moveCamera(CameraUpdateFactory.zoomTo(16));

        //清除圆圈和marker
        aMap.clear();
        //绘制圆圈
        drawCircle(latLng, 100);
        //绘制marker
        drawMarker(latLng);
    }
    /**
     * 绘制圆圈
     *
     * @param latLng
     */
    public void drawCircle(LatLng latLng, int radius) {
        String color = "#26b637";
        StringBuilder sb = new StringBuilder(color);// 构造一个StringBuilder对象
        sb.insert(1, "50");// 在指定的位置10，插入指定的字符串
        if (circle != null) {
            circle= null;
        }
        circle = aMap.addCircle(new CircleOptions()
                .center(latLng)
                .radius(radius)
                .fillColor(Color.parseColor(sb.toString()))
                .strokeColor(Color.parseColor(color))
                .strokeWidth(5));
    }

    /**
     * 绘制自定义marker（图标）
     */
    public void drawMarker(LatLng latLng) {
        MarkerOptions markerOption = new MarkerOptions();
        markerOption.position(latLng);
        markerOption.draggable(true);//设置Marker可拖动
        markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(getResources(), R.mipmap.amap_man)));
        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
        markerOption.setFlat(false);//设置marker平贴地图效果
        markerOption.anchor(0.5f, 0.5f); //设置marker偏移量
        marker = aMap.addMarker(markerOption);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }
}
