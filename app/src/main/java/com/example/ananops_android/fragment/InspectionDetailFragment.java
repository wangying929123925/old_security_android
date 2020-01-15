package com.example.ananops_android.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.activity.InspectionItemDetailActivity;
import com.example.ananops_android.entity.InspectionTaskItem;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.InspectionUtils;

import java.util.ArrayList;
import java.util.List;

public class InspectionDetailFragment extends Fragment {
    private RecyclerView myRV;
    private List<InspectionTaskItem> taskItems = new ArrayList<>();
    private List<String> values = new ArrayList<>();
    private ListView sortListView;
    private static String inspectionId;
    private Context mComtext;

    //Fragment中构造方法中不能传递参数
    //通过下面的方式 能将参数传进InspectionFragment中
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
        mComtext=getContext();
        Bundle bundle = getArguments();
        if(bundle!=null){
        inspectionId = bundle.getString("id");}
        myRV.setLayoutManager(new LinearLayoutManager(getContext()));
        taskItems=InspectionUtils.getInstence().getInspectionTaskItemsImc(taskItems,Long.valueOf(inspectionId),mComtext);
        myRV.setAdapter(new MyRecyclerViewAdapter(taskItems,mComtext));
        return view;
    }



    private class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.InnerHolder> {

        private List<InspectionTaskItem> taskItems;
        private Context mContext;


        public class InnerHolder extends RecyclerView.ViewHolder {

            private RelativeLayout mRl2;
            private TextView tv1,tv2,tv3,tv4;
            public InnerHolder(@NonNull View itemView) {
                super(itemView);
                mRl2 = itemView.findViewById(R.id.mR2);
                tv1 = itemView.findViewById(R.id.Plist_name);
                tv2 = itemView.findViewById(R.id.Plist_id);
                tv3= itemView.findViewById(R.id.Plist_type);
                tv4 = itemView.findViewById(R.id.Plist_price);
            }
        }

        public MyRecyclerViewAdapter(List<InspectionTaskItem> taskItems, Context mContext) {
            this.taskItems = taskItems;
            this.mContext = mContext;
        }

        @NonNull
        @Override
        public InnerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_project_list, null);
            final InnerHolder innerHolder = new InnerHolder(view);
            return innerHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull InnerHolder innerHolder, final int i) {
            innerHolder.tv1.setText(taskItems.get(i).getItemName());
            innerHolder.tv2.setText(String.valueOf(taskItems.get(i).getId()));
            innerHolder.tv3.setText(taskItems.get(i).getMaintainerName());
            innerHolder.tv4.setText(taskItems.get(i).getUpdateTime());
            innerHolder.mRl2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "点击了条目" + i, Toast.LENGTH_SHORT).show();
                    Bundle bundle0=new Bundle();
                    bundle0.putString("inspectionId",String.valueOf(taskItems.get(i).getId()));
                    bundle0.putString("status_do","no");
                    BaseUtils.getInstence().intent(mContext, InspectionItemDetailActivity.class,bundle0);
                }
            });

        }

        @Override
        public int getItemCount() {
            return taskItems.size();
        }
    }
}

