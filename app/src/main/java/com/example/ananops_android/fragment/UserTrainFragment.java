package com.example.ananops_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;

public class UserTrainFragment extends Fragment implements View.OnClickListener {
    private TextView title;
  //  private ImageView search_img;
    private ImageView back_img;
    private TextView knowledge_base;
    private TextView train_identification;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view=inflater.inflate(R.layout.fragment_order_detail_content,container,false);
        title=view.findViewById(R.id.txt_title);//标题
      //  search_img=view.findViewById(R.id.img_search);
        back_img=view.findViewById(R.id.img_back);
     //   knowledge_base=view.findViewById(R.id.knowledge_base);
      //  train_identification=view.findViewById(R.id.train_identification);
        initViews();
        initDatas();
        setOnListener();
        return view;
    }

    private void initViews() {
        title.setText("培训");
        back_img.setVisibility(View.INVISIBLE);
        //search_img.setVisibility(View.INVISIBLE);
    }

    private void initDatas() {

    }

    private void setOnListener() {
        knowledge_base.setOnClickListener(this);
        train_identification.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
        }
    }
}
