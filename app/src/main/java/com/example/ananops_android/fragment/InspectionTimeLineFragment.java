package com.example.ananops_android.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.entity.InspectionTaskItem;
import com.example.ananops_android.entity.InspectionTaskLog;
import com.example.ananops_android.util.InspectionUtils;

import java.util.ArrayList;
import java.util.List;

public class InspectionTimeLineFragment extends Fragment {
    private RecyclerView myRV;
    private List<InspectionTaskLog> taskLogs = new ArrayList<>();
    private static String inspectionId;
   private Context mContext;
    public static InspectionDetailFragment newIntance(String id) {
        InspectionDetailFragment inspectionDetailFragment = new InspectionDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        inspectionDetailFragment.setArguments(bundle);
        return inspectionDetailFragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_show, container, false);
        myRV = view.findViewById(R.id.rv_inspection);
        Bundle bundle = getArguments();
        mContext=getContext();
        if(bundle!=null){
        inspectionId = bundle.getString("id");}
        Log.v("inspectionTimeLine",inspectionId);
       // taskItems= InspectionUtils.getInstence().getInspectionTaskItems(taskItems,Long.valueOf(inspectionId),mComtext);
        myRV.setLayoutManager(new LinearLayoutManager(getContext()));
        taskLogs=InspectionUtils.getInstence().getInspectionLogs(taskLogs,Long.valueOf(inspectionId),mContext);
        myRV.setAdapter(new MyRecyclerViewAdapter(taskLogs, getContext()));
        return view;
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
            View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_order_time_line, null);
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
