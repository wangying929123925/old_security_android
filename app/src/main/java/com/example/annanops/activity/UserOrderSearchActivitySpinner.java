package com.example.annanops.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.example.annaops.R;
import com.jaredrummler.materialspinner.MaterialSpinner;

public class UserOrderSearchActivitySpinner extends AppCompatActivity {
    private static final String[] STATUS = {"状态", "待审核", "审核中", "待维修", "维修中", "待验证", "已完成", "待评价", "待评价", "待评价"};
    private static final String[] TIMES = {"今天", "本周", "一个月", "半年", "一年", "更多"};
    private static final String[] ALL = {"全部", "我申请", "我审核"};
    private MaterialSpinner spinner;
    private MaterialSpinner spinner1;
    private MaterialSpinner spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search_spinner);
        spinner = (MaterialSpinner) findViewById(R.id.spinner_status);
        spinner1 = (MaterialSpinner) findViewById(R.id.spinner_time);
        spinner2 = (MaterialSpinner) findViewById(R.id.spinner_all);
        initSpinner();

    }
    private void initSpinner() {
        spinner.setItems(STATUS);
        spinner.setArrowColor(getResources().getColor(R.color.deep_blue));
        spinner.setBackgroundColor(getResources().getColor(R.color.state_pressed));
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });
        spinner.setOnNothingSelectedListener(new MaterialSpinner.OnNothingSelectedListener() {
            @Override public void onNothingSelected(MaterialSpinner spinner) {
                Snackbar.make(spinner, "Nothing selected", Snackbar.LENGTH_LONG).show();
            }
        });

        spinner1.setItems(TIMES);
        spinner1.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });
        spinner1.setOnNothingSelectedListener(new MaterialSpinner.OnNothingSelectedListener() {
            @Override public void onNothingSelected(MaterialSpinner spinner) {
                Snackbar.make(spinner, "Nothing selected", Snackbar.LENGTH_LONG).show();
            }
        });

        spinner2.setItems(ALL);
        spinner2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });
        spinner2.setOnNothingSelectedListener(new MaterialSpinner.OnNothingSelectedListener() {
            @Override public void onNothingSelected(MaterialSpinner spinner) {
                Snackbar.make(spinner, "Nothing selected", Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
