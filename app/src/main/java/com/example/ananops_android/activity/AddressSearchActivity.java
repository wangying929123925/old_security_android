package com.example.ananops_android.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.example.ananops_android.R;
import com.example.ananops_android.adapter.AdressSearchAdapter;
import com.example.ananops_android.view.EditTextWithDel;

import java.util.List;

public class AddressSearchActivity extends BaseActivity implements
        Inputtips.InputtipsListener, View.OnClickListener {
    private static final int REQUEST_CODE_ACCESS_COARSE_LOCATION = 1;
    private TextView title;
    private ImageView back_img;
    private static String TITLE;
    private List<Tip>tipList;
    private ListView listView;
    private AdressSearchAdapter adapter;
    private  String searchContent;
    private EditTextWithDel mEtSearchAdress;
    public static String DEFAULT_CITY = "北京";
    public static final int RESULT_CODE_INPUTTIPS = 101;
    public static final int REQUEST_SUC = 1000;
    private AMapLocationClient mLocationClient = null;//定位发起端
    private AMapLocationClientOption mLocationOption = null;//定位参数
    private LocationSource.OnLocationChangedListener mListener = null;//定位监听器
    private LatLng latLng;//经纬度信息
    private Circle circle;
    private Marker marker;
    MapView mMapView = null;
    private AMap aMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_search);
        mMapView = (MapView) findViewById(R.id.mapSearch);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        checkPermision();
        //initLoc();
        initViews();
        setListener();
      //  initData();
    }
    private void initViews() {
       // search_content=findViewById(R.id.et_search_address);
        back_img = findViewById(R.id.img_back);
//        title=findViewById(R.id.txt_title);
//        title.setText("地点");
        mEtSearchAdress=(EditTextWithDel)findViewById(R.id.et_search_address);
        listView=findViewById(R.id.address_list);
    }
    //定位
    private void initLoc() {
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW);
        myLocationStyle.showMyLocation(true);
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(false);
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false
        aMap.setOnMapClickListener(new AMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLngdata) {
                latLng= latLngdata;
                setMapCenter(latLng, 500);
            }
        });
        //marker的拖动监听
        aMap.setOnMarkerDragListener(new AMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {
                latLng= new LatLng(marker.getPosition().latitude, marker.getPosition().longitude);
                setMapCenter(latLng, 500);
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {

            }
        });
        //初始化定位
        mLocationClient = new AMapLocationClient(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位回调监听
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                latLng= new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                setMapCenter(latLng,500);
            }
        });
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(true);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.stopLocation();
        //启动定位
        mLocationClient.startLocation();
    }
    /*
    监听事件
     */
    private void setListener(){
    //    back_img.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(tipList!=null) {
                    Tip tip = tipList.get(position);
                   // Tip tip = (Tip) adapter.getItem(position);
                    if (null != tip.getPoint()) {
                        latLng= new LatLng(tip.getPoint().getLatitude(), tip.getPoint().getLongitude());
                        setMapCenter(latLng,500);
                        showExitAlertDialog(tip);
                    } else {
                        Toast.makeText(AddressSearchActivity.this,"请选择具体地理位置",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        mEtSearchAdress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String s1=s.toString();
                if(!s1.equals("")){
                    InputtipsQuery inputquery = new InputtipsQuery(s1,DEFAULT_CITY);
                    Inputtips inputTips = new Inputtips(AddressSearchActivity.this, inputquery);
                    inputTips.setInputtipsListener(AddressSearchActivity.this);
                    inputTips.requestInputtipsAsyn();
                }
                else {  // 如果输入为空  则清除 listView 数据
                    tipList.clear();
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    /**
     * 按下确认键触发，本例为键盘回车或搜索键
     */
//
    /**
     * 输入提示回调
     */
    @Override
    public void onGetInputtips(List<Tip> tips, int rCode) {
        // 正确返回
        if (rCode == REQUEST_SUC) {
            tipList = tips;
            adapter=new AdressSearchAdapter(this,tipList);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "错误码 :" + rCode, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
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
    public void showExitAlertDialog(Tip tip){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("您的报修位置为"+tip.getName());
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent();
                intent.putExtra("tip", tip);
                setResult(RESULT_CODE_INPUTTIPS, intent);
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
//判断定位权限
    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermision() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int perssion = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
            if (perssion != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
            } else {
                initLoc();
            }
        } else {
            initLoc();
        }
    }
    /**
     * 获取权限的回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_ACCESS_COARSE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //用户允许改权限，0表示允许，-1表示拒绝 PERMISSION_GRANTED = 0， PERMISSION_DENIED = -1
                //permission was granted, yay! Do the contacts-related task you need to do.
                //这里进行授权被允许的处理
                initLoc();
            } else {
                //permission denied, boo! Disable the functionality that depends on this permission.
                //这里进行权限被拒绝的处理
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
        if(null != mLocationClient){
            mLocationClient.onDestroy();
        }
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
