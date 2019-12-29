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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ananops_android.R;

import java.util.ArrayList;
import java.util.List;

public class InspectionFragment extends Fragment {
    private RecyclerView myRV;
    private List<String> strings = new ArrayList<>();

    //Fragment中构造方法中不能传递参数
    //通过下面的方式 能将参数传进InspectionFragment中
    public static InspectionFragment newIntance(ArrayList<String> strings) {
        InspectionFragment inspectionFragment = new InspectionFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("data", strings);
        inspectionFragment.setArguments(bundle);
        return inspectionFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_show, container, false);
        Bundle bundle = getArguments();
        strings  = bundle.getStringArrayList("data");
        myRV = view.findViewById(R.id.rv_inspection);
        myRV.setLayoutManager(new LinearLayoutManager(getContext()));
        myRV.setAdapter(new MyRecyclerViewAdapter(strings, getContext()));
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.InnerHolder> {

        private List<String> mData;
        private Context mContext;


        public class InnerHolder extends RecyclerView.ViewHolder {
            private EditText mContent;
            private TextView mTitle;

            public InnerHolder(@NonNull View itemView) {
                super(itemView);
                mContent = itemView.findViewById(R.id.et_inspection_content);
                mTitle = itemView.findViewById(R.id.tv_inspection_title);
            }
        }
        public MyRecyclerViewAdapter(List<String> mData, Context mContext) {
            this.mData = mData;
            this.mContext = mContext;
        }

        @NonNull
        @Override
        public InnerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_sublist, null);
            return new InnerHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull InnerHolder innerHolder, int i) {
            innerHolder.mTitle.setText(mData.get(i));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }
}
