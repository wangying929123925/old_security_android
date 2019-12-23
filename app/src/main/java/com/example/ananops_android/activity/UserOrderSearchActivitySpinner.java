package com.example.ananops_android.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ananops_android.R;
import com.example.ananops_android.adapter.RepairAdapter;
import com.example.ananops_android.entity.RepairContent;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

public class UserOrderSearchActivitySpinner extends AppCompatActivity implements View.OnClickListener {
    private static final String[] STATUS = {"状态", "计划中", "待接单", "待维修", "待审核", "维修中", "待验收", "待评价"};
    private static final String[] TIMES = {"状态", "今天", "本周", "一个月", "半年", "一年", "更多"};
    private static final String[] ALL = {"全部", "我申请",};
    private MaterialSpinner spinner;
    private MaterialSpinner spinner1;
    private MaterialSpinner spinner2;
    private Button[] buttons = new Button[4];
    private Button project_type;
    private Button order_type;
    private Button unit_type;
    private Button repair_date;
    private String searchType;
    private String searchContent;
    private List<RepairContent> repairContents = new ArrayList<>();
    private RepairAdapter adapter;//适配器
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView title;
    private ImageView search_img;
    private ImageView back_img;
    private EditText search_content;
    private TextView search_text;
    private int index;
    private int currentTabIndex = 0;// 当前fragment的index

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search_spinner);
        spinner = (MaterialSpinner) findViewById(R.id.spinner_status);
        spinner1 = (MaterialSpinner) findViewById(R.id.spinner_time);
        spinner2 = (MaterialSpinner) findViewById(R.id.spinner_all);
        initSpinner();
        //选择
        project_type = findViewById(R.id.search_order_project_type);
        order_type = findViewById(R.id.search_order_type);
        unit_type = findViewById(R.id.search_order_unit_type);
        repair_date = findViewById(R.id.search_order_date);
        title = findViewById(R.id.txt_title);//标题
        //search_img=findViewById(R.id.img_search);
        back_img = findViewById(R.id.img_back);
        search_content = findViewById(R.id.text_search);
        search_text = findViewById(R.id.search_title_txt);
        //按钮
        project_type = findViewById(R.id.search_order_project_type);
        order_type = findViewById(R.id.search_order_type);
        unit_type = findViewById(R.id.search_order_unit_type);
        repair_date = findViewById(R.id.search_order_date);
        buttons[0]=project_type;
        buttons[1]=order_type;
        buttons[2]=unit_type;
        buttons[3]=repair_date;
        buttons[0].setSelected(true);
        title.setText("工单查询");
       // search_img.setVisibility(View.INVISIBLE);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView = findViewById(R.id.contact_recycler_view);
        mRecyclerView.setLayoutManager(mLayoutManager);
       adapter = new RepairAdapter(repairContents);
        mRecyclerView.setAdapter(adapter);
        initDatas();
        setOnListener();
    }

    private void initSpinner() {
        spinner.setItems(STATUS);
        spinner.setArrowColor(getResources().getColor(R.color.deep_blue));
        spinner.setBackgroundColor(getResources().getColor(R.color.state_pressed));
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });
        spinner.setOnNothingSelectedListener(new MaterialSpinner.OnNothingSelectedListener() {
            @Override
            public void onNothingSelected(MaterialSpinner spinner) {
                Snackbar.make(spinner, "Nothing selected", Snackbar.LENGTH_LONG).show();
            }
        });

        spinner1.setItems(TIMES);
        spinner1.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });
        spinner1.setOnNothingSelectedListener(new MaterialSpinner.OnNothingSelectedListener() {
            @Override
            public void onNothingSelected(MaterialSpinner spinner) {
                Snackbar.make(spinner, "Nothing selected", Snackbar.LENGTH_LONG).show();
            }
        });

        spinner2.setItems(ALL);
        spinner2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });
        spinner2.setOnNothingSelectedListener(new MaterialSpinner.OnNothingSelectedListener() {
            @Override
            public void onNothingSelected(MaterialSpinner spinner) {
                Snackbar.make(spinner, "Nothing selected", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void initDatas() {
    }

    private void setOnListener() {
        back_img.setOnClickListener(this);
        search_text.setOnClickListener(this);
        project_type.setOnClickListener(this);
        order_type.setOnClickListener(this);
        unit_type.setOnClickListener(this);
        repair_date.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.search_title_txt:
                searchContent = search_content.getText().toString().trim();
                // showSearchResult(searchType,searchContent);
                break;
            case R.id.search_order_project_type:
                searchType = "项目类型";
                search_content.setHint("请输入项目类型");
                index = 0;
                break;
            case R.id.search_order_type:
                searchType = "工单类型";
                search_content.setHint("请输入工单类型");
                index = 1;
                break;
            case R.id.search_order_unit_type:
                searchType = "维修单位";
                search_content.setHint("请输入维修单位");
                index = 2;
                break;
            case R.id.search_order_date:
                searchType = "维修日期";
                search_content.setHint("请输入维修日期");
                index = 3;
                break;
            }
        if(currentTabIndex!=index){
            buttons[currentTabIndex].setSelected(false);
            buttons[index].setSelected(true);
            buttons[currentTabIndex].setTextColor(getResources().getColor(R.color.black));
            buttons[index].setTextColor(getResources().getColor(R.color.state_pressed));
            currentTabIndex=index;
        }
    }
}
