package com.example.ananops_android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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
    private View mRootView;
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private ArrayList<String> imagePaths = new ArrayList<>();//图片
    public static OrderDetailAppendix newInstance(ArrayList<String> imagePaths) {
        OrderDetailAppendix orderDetailAppendix = new OrderDetailAppendix();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("data", imagePaths);
        orderDetailAppendix.setArguments(bundle);
        return orderDetailAppendix;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_order_detail_appendix, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            imagePaths=bundle.getStringArrayList("data");
        }
        //imagePaths.add("");
        return mRootView;
    }
   @Override
   public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    Log.v("图片路径", imagePaths+"");
    gridView = mRootView.findViewById(R.id.gridView_photo);
    int cols = getResources().getDisplayMetrics().widthPixels / getResources().getDisplayMetrics().densityDpi;
    cols = cols < 3 ? 3 : cols;
    gridView.setNumColumns(cols);//拍照图片
    gridAdapter = new GridAdapter(getContext(),imagePaths);
    gridView.setAdapter(gridAdapter);
    initData();
    }
    private void initData() {
      //  imagePaths.add(R.drawable.ic_message_orange);
    }
}
