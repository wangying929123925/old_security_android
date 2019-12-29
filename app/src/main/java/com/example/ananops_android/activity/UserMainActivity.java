package com.example.ananops_android.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ananops_android.R;
import com.example.ananops_android.fragment.UserDeviceFragment;
import com.example.ananops_android.fragment.UserMainFragment;
import com.example.ananops_android.fragment.UserMessageFragment;
import com.example.ananops_android.fragment.UserMineFragment;
import com.example.ananops_android.fragment.UserTrainFragment;
import com.example.ananops_android.util.BaseUtils;

public class UserMainActivity extends AppCompatActivity implements View.OnClickListener {
    private Fragment[] fragments;
    private ImageView[] imagebuttons;
    private TextView[] textviews;
    private int index;
    private int currentTabIndex=0;// 当前fragment的index
    private UserMainFragment boardFragment;
    private UserMessageFragment userTrainFragment;
    private UserMineFragment mineFragment;
 //   private WorkFragment workFragment;
  //  private UserDeviceFragment userDeviceFragment;
   // private ImageView imageBack;
  //  private ImageView imageRight;
 //   private TextView title_text;
    private Button main_nav_button;
    private DrawerLayout drawerLayout1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        final Context mContext=this;
      //  imageBack=findViewById(R.id.img_back);
      //  imageRight=findViewById(R.id.img_right);
      //  title_text=findViewById(R.id.txt_title);//标题
        main_nav_button=findViewById(R.id.main_nav_button);
        drawerLayout1=findViewById(R.id.drawer_layout_main);
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.type_manage);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.logout:
                        BaseUtils.getInstence().intent(mContext, LoginActivity.class);
                       drawerLayout1.closeDrawers();
                    break;
                    default:
                        drawerLayout1.closeDrawers();
                        break;
                }
                return true;
            }
        });
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
        userTrainFragment=new UserMessageFragment();
       // userDeviceFragment=new UserDeviceFragment();
        mineFragment=new UserMineFragment();
      // workFragment=new WorkFragment();
        fragments=new Fragment[]{boardFragment,userTrainFragment,mineFragment};
        imagebuttons=new ImageView[3];//图标
        imagebuttons[0]=findViewById(R.id.tab_main_img1);
        imagebuttons[1]=findViewById(R.id.tab_main_img2);
        imagebuttons[2]=findViewById(R.id.tab_main_img3);
        imagebuttons[0].setSelected(true);

        textviews=new TextView[3];//文字
        textviews[0]=findViewById(R.id.tab_main_text1);
        textviews[1]=findViewById(R.id.tab_main_text2);
        textviews[2]=findViewById(R.id.tab_main_text3);
        textviews[0].setTextColor(getResources().getColor(R.color.deep_blue));
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container,boardFragment)
                .add(R.id.fragment_container,userTrainFragment)
                .add(R.id.fragment_container,mineFragment)
                .hide(userTrainFragment).hide(mineFragment)
                .show(boardFragment).commit();
    }
    public void onTabClicked(View view) {
        switch (view.getId()){
            case R.id.tab_main_1:
                index = 0;
            //   title_text.setText("看板1");
                if(boardFragment!=null){
                    boardFragment.refresh();
                }
                break;
            case R.id.tab_main_2:
                index=1;
          //      title_text.setText("工作");
                break;
            case R.id.tab_main_3:
                index=2;
             //   title_text.setText("工作");
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
