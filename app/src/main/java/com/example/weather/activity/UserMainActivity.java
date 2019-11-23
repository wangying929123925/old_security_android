package com.example.weather.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weather.R;
import com.example.weather.fragment.UserDeviceFragment;
import com.example.weather.fragment.UserMainFragment;
import com.example.weather.fragment.UserMineFragment;
import com.example.weather.fragment.UserTrainFragment;


public class UserMainActivity extends AppCompatActivity implements View.OnClickListener {
    private Fragment[] fragments;
    private ImageView[] imagebuttons;
    private TextView[] textviews;
    private int index;
    private int currentTabIndex=0;// 当前fragment的index
    private UserMainFragment boardFragment;
    private UserTrainFragment userTrainFragment;
    private UserMineFragment mineFragment;
 //   private WorkFragment workFragment;
    private UserDeviceFragment userDeviceFragment;
    private ImageView imageBack;
    private ImageView imageRight;
 //   private TextView title_text;
    private Button main_nav_button;
    private DrawerLayout drawerLayout1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        imageBack=findViewById(R.id.img_back);
        imageRight=findViewById(R.id.img_right);
      //  title_text=findViewById(R.id.txt_title);//标题
        main_nav_button=findViewById(R.id.main_nav_button);
        drawerLayout1=findViewById(R.id.drawer_layout_main);
     //   initViews();
        initTabView();
    }
    @Override
    public void onClick(View v) {
    }
    private void initViews() {
//    imageBack.setVisibility(View.GONE);//GONE与INVISIBLE区别，一个保留位置，一个不保留
    //imageRight.setVisibility(View.INVISIBLE);
        main_nav_button.setVisibility(View.VISIBLE);
        main_nav_button.setOnClickListener(new View.OnClickListener() {//展示导航栏
            @Override
            public void onClick(View v) {
                drawerLayout1.openDrawer(GravityCompat.START);
            }
        });
    }

    private void initTabView() {
        boardFragment=new UserMainFragment();
        userTrainFragment=new UserTrainFragment();
        userDeviceFragment=new UserDeviceFragment();
        mineFragment=new UserMineFragment();
      // workFragment=new WorkFragment();
        fragments=new Fragment[]{boardFragment,userTrainFragment,userDeviceFragment,mineFragment};
        imagebuttons=new ImageView[4];//图标
        imagebuttons[0]=findViewById(R.id.ib_main);
        imagebuttons[1]=findViewById(R.id.ib_train);
        imagebuttons[2]=findViewById(R.id.ib_device);
        imagebuttons[3]=findViewById(R.id.ib_mine);
        imagebuttons[0].setSelected(true);

        textviews=new TextView[4];//文字
        textviews[0]=findViewById(R.id.tv_main);
        textviews[1]=findViewById(R.id.tv_train);
        textviews[2]=findViewById(R.id.tv_device);
        textviews[3]=findViewById(R.id.tv_mine);
        textviews[0].setTextColor(0xFF45C01A);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container,boardFragment)
                .add(R.id.fragment_container,userTrainFragment)
                .add(R.id.fragment_container,userDeviceFragment)
                .add(R.id.fragment_container,mineFragment)
                .hide(userTrainFragment).hide(userDeviceFragment).hide(mineFragment)
                .show(boardFragment).commit();

    }
    public void onTabClicked(View view) {
        switch (view.getId()){
            case R.id.tab_main:
                index = 0;
            //   title_text.setText("看板1");
                if(boardFragment!=null){
                    boardFragment.refresh();
                }
                break;
            case R.id.tab_train:
                index=1;
          //      title_text.setText("工作");
                break;
            case R.id.tab_device:
                index=2;
             //   title_text.setText("工作");
                break;
            case R.id.tab_mine:
                index=3;
            //    title_text.setText("我的");
                break;
        }
        if(currentTabIndex!=index){
            FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
            fragmentTransaction.hide(fragments[currentTabIndex]);
            if(!fragments[index].isAdded()){
                fragmentTransaction.add(R.id.fragment_container,fragments[index]);
            }
           // fragmentTransaction..commit();
            fragmentTransaction.hide(fragments[currentTabIndex]).show(fragments[index]).commit();
        }
        imagebuttons[currentTabIndex].setSelected(false);
        imagebuttons[index].setSelected(true);
        textviews[currentTabIndex].setTextColor(0xFF999999);
        textviews[index].setTextColor(0xFF45C01A);
        currentTabIndex=index;
    }


}
