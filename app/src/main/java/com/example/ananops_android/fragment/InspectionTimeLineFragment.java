package com.example.ananops_android.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.db.InspectionLogResponse;
import com.example.ananops_android.db.InspectionLogsRequest;
import com.example.ananops_android.entity.InspectionTaskLog;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class InspectionTimeLineFragment extends Fragment {
    private RecyclerView myRV;
    private List<InspectionTaskLog> taskLogs = new ArrayList<>();
    private static String inspectionId;
   private Context mContext;
    private View mRootView;
    public static InspectionTimeLineFragment newIntance(String id) {
        InspectionTimeLineFragment inspectionDetailFragment = new InspectionTimeLineFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        inspectionDetailFragment.setArguments(bundle);
        return inspectionDetailFragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.view_show, container, false);
        mContext = getActivity();
        return mRootView;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myRV = mRootView.findViewById(R.id.rv_inspection);
        Bundle bundle = getArguments();
        if (bundle != null) {
            inspectionId = bundle.getString("id");
        }
        Log.v("inspectionTimeLine",inspectionId);
        // taskItems= InspectionUtils.getInstence().getInspectionTaskItems(taskItems,Long.valueOf(inspectionId),mComtext);
        myRV.setLayoutManager(new LinearLayoutManager(getContext()));
      //  taskLogs = InspectionUtils.getInstence().getInspectionLogs(taskLogs, Long.valueOf(inspectionId), mContext);
       getTaskLogs();

    }

    private void getTaskLogs() {
        if (inspectionId != null) {
            InspectionLogsRequest inspectionLogsRequest=new InspectionLogsRequest();
            inspectionLogsRequest.setTaskId(Long.valueOf(inspectionId));
            inspectionLogsRequest.setOrderBy("string");
            Net.instance.getInspectionLog(inspectionLogsRequest, SPUtils.getInstance(mContext).getString("Token", " "))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<InspectionLogResponse>() {
                        @Override
                        public void onCompleted() {

                        }
                        @Override
                        public void onError(Throwable e) {
                            Log.v("ErrorGetInspectionLog", System.currentTimeMillis() + "");
                            e.printStackTrace();
                        }
                        @Override
                        public void onNext(InspectionLogResponse inspectionLogResponse) {
                            if (TextUtils.equals(inspectionLogResponse.getCode(), "200")) {

                               taskLogs.clear();
                                if (inspectionLogResponse.getResult().size() > 0) {
                                    taskLogs.addAll(inspectionLogResponse.getResult());
                                    Log.v("巡检日志列表1", inspectionLogResponse.getResult().get(0).getId() + "");
                                }
                                myRV.setAdapter(new MyRecyclerViewAdapter(taskLogs, getContext()));
                            } else {
                                Toast.makeText(mContext, inspectionLogResponse.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(mContext, "没有巡检单号！", Toast.LENGTH_LONG).show();
        }
    }
    private class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.InnerHolder> {

        private List<InspectionTaskLog> taskLogs;
        private Context mContext;


        public class InnerHolder extends RecyclerView.ViewHolder {

           private TextView tvDirector;
            private TextView tvProcess;
            private TextView tvTime;
            private RelativeLayout rlDone;
            private RelativeLayout rlDoing;

            public InnerHolder(@NonNull View itemView) {
                super(itemView);
                tvDirector = itemView.findViewById(R.id.item_director);
                tvProcess = itemView.findViewById(R.id.item_status);
                tvTime = itemView.findViewById(R.id.item_time);
                rlDoing = itemView.findViewById(R.id.listitem_doing);
                rlDone = itemView.findViewById(R.id.listitem_done);
            }
        }

        public MyRecyclerViewAdapter(List<InspectionTaskLog> taskLogs, Context mContext) {
            this.taskLogs = taskLogs;
            this.mContext = mContext;
        }

        @NonNull
        @Override
        public InnerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_order_time_line_item, null);
            final InnerHolder innerHolder = new InnerHolder(view);
            return innerHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull InnerHolder innerHolder, final int i) {
            if(i==0){
                innerHolder.rlDoing.setVisibility(View.VISIBLE);
                innerHolder.rlDone.setVisibility(View.GONE);
                innerHolder.tvTime.setTextColor(mContext.getResources().getColor(R.color.blue));
                innerHolder.tvDirector.setTextColor(mContext.getResources().getColor(R.color.blue));
                innerHolder.tvProcess.setTextColor(mContext.getResources().getColor(R.color.blue));
                innerHolder.tvDirector.setText(taskLogs.get(i).getLastOperator());
                innerHolder.tvProcess.setText(taskLogs.get(i).getMovement());
                innerHolder.tvTime.setText(taskLogs.get(i).getUpdateTime());

            }
            else {
                innerHolder.rlDone.setVisibility(View.VISIBLE);
                innerHolder.rlDoing.setVisibility(View.GONE);
                innerHolder.tvDirector.setText(taskLogs.get(i).getLastOperator());
                innerHolder.tvProcess.setText(taskLogs.get(i).getMovement());
                innerHolder.tvTime.setText(taskLogs.get(i).getUpdateTime());
            }

        }

        @Override
        public int getItemCount() {
            return taskLogs.size();
        }
    }
}
