package com.example.ananops_android.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.adapter.TimeLineAdapter;
import com.example.ananops_android.db.OrderTimelineResponse;
import com.example.ananops_android.entity.TimeLine;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TimeLineFragment extends Fragment {
    private static String ORDER_ID;
    private List<TimeLine> timeLines=new ArrayList<>();
    private TimeLineAdapter adapter;//适配器
    private ListView mListView;
    private RecyclerView.LayoutManager mLayoutManager;
    private View mRootView;
    private Context mContext;

    public static TimeLineFragment newInstance(String orderId) {
        TimeLineFragment timeLineFragment = new TimeLineFragment();
        Bundle bundle = new Bundle();
        bundle.putString("orderId",orderId);
        timeLineFragment.setArguments(bundle);
        return timeLineFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_order_time_line, container, false);
        mContext = getActivity();
        return mRootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mListView=mRootView.findViewById(R.id.lv_time_line);
        initdata();
    }

    private void initdata() {
        if(!(getArguments()==null)){
            ORDER_ID=(String) getArguments().get("orderId");
            Net.instance.getTimeLine(ORDER_ID, SPUtils.getInstance(mContext).getString("Token"," "))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<OrderTimelineResponse>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.v("TimeLineTime", System.currentTimeMillis() + "");
                            e.printStackTrace();
                            Toast.makeText(getContext(), "网络异常，请检查网络状态", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNext(OrderTimelineResponse orderTimelineResponse) {
                            if (TextUtils.equals(orderTimelineResponse.getCode(), "200")) {
                                timeLines.addAll(orderTimelineResponse.getResult());
                                adapter=new TimeLineAdapter(getContext(),timeLines);
                                mListView.setAdapter(adapter);
                              //  Toast.makeText(getContext(),"repairContents"+orderTimelineResponse.getResult().get(0).getLastOperator(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), orderTimelineResponse.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }else {
            TimeLine timeLine = new TimeLine();
            timeLine.setLastOperator("试用用户");
            timeLine.setMovement("执行维修");
            timeLine.setUpdateTime("2019-11-25 15:05:02");
            timeLines.add(timeLine);
            TimeLine timeLine1 = new TimeLine();
            timeLine1.setLastOperator("试用用户");
            timeLine1.setMovement("派工给试用用户，待维修");
            timeLine1.setUpdateTime("2019-11-25 13:05:02");
            timeLines.add(timeLine1);
        }
    }

                  }

