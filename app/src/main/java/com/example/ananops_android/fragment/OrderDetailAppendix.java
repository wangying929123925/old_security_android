package com.example.ananops_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.ananops_android.R;
import com.example.ananops_android.adapter.GridAdapter;

import java.util.ArrayList;

public class OrderDetailAppendix extends Fragment {
    private GridView gridView;
    private GridAdapter gridAdapter;
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private ArrayList<String> imagePaths = new ArrayList<>();//图片
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_detail_appendix, container, false);
        gridView = view.findViewById(R.id.gridView_photo);
        int cols = getResources().getDisplayMetrics().widthPixels / getResources().getDisplayMetrics().densityDpi;
        cols = cols < 3 ? 3 : cols;
        gridView.setNumColumns(cols);//拍照图片
        gridAdapter = new GridAdapter(getContext(),imagePaths);
        gridView.setAdapter(gridAdapter);
        initData();
        return view;
    }

    private void initData() {
      //  imagePaths.add(R.drawable.ic_message_orange);
    }
}
