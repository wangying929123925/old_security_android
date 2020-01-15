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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;

import java.util.ArrayList;
import java.util.List;

public class InspectionItemFragment extends Fragment {
    private RecyclerView myRV;
    private List<String> names = new ArrayList<>();
    private List<String> values = new ArrayList<>();
    private static String inspectionId;

    //Fragment中构造方法中不能传递参数
    //通过下面的方式 能将参数传进InspectionFragment中
    public static InspectionItemFragment newIntance(String id,ArrayList<String> names, ArrayList<String> values) {
        InspectionItemFragment inspectionItemFragment = new InspectionItemFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        bundle.putStringArrayList("data", names);
        bundle.putStringArrayList("value", values);
        inspectionItemFragment.setArguments(bundle);
        return inspectionItemFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_show, container, false);
        Bundle bundle = getArguments();
        inspectionId = bundle.getString("id");
        names = bundle.getStringArrayList("data");
        values = bundle.getStringArrayList("value");
        myRV = view.findViewById(R.id.rv_inspection);
        myRV.setLayoutManager(new LinearLayoutManager(getContext()));
        myRV.setAdapter(new MyRecyclerViewAdapter(names, values, getContext()));

        return view;
    }



    public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.InnerHolder> {

        private List<String> mValues;
        private List<String> mData;
        private Context mContext;


        public class InnerHolder extends RecyclerView.ViewHolder {
            private RelativeLayout mRl;
            private EditText mContent;
            private TextView mTitle;
            private Button mButton;

            public InnerHolder(@NonNull View itemView) {
                super(itemView);
                mRl = itemView.findViewById(R.id.rl_rl);
                mContent = itemView.findViewById(R.id.et_inspection_content);
                mTitle = itemView.findViewById(R.id.tv_inspection_title);
                mButton = itemView.findViewById(R.id.bt_item);
            }
        }

        public MyRecyclerViewAdapter(List<String> mData, List<String> values, Context mContext) {
            this.mValues = values;
            this.mData = mData;
            this.mContext = mContext;
        }

        @NonNull
        @Override
        public InnerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_sublist, null);
            final InnerHolder innerHolder = new InnerHolder(view);

            innerHolder.mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "click the"+ innerHolder.mTitle.getText(), Toast.LENGTH_SHORT).show();
                }
            });
            return innerHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull InnerHolder innerHolder, final int i) {
            innerHolder.mTitle.setText(mData.get(i));
            innerHolder.mContent.setText(mValues.get(i));
            innerHolder.mRl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "点击了条目" + i, Toast.LENGTH_SHORT).show();
                }
            });
            //电话可修改
            if (mData.get(i).contains("电话")||mData.get(i).contains("等级")) {
                innerHolder.mContent.setFocusableInTouchMode(true);
                innerHolder.mButton.setVisibility(View.VISIBLE);
                innerHolder.mButton.setText("可修改");
            }
            if (i == 2) {
                if (mData.get(i).equals("审核人")) {
                    innerHolder.mButton.setVisibility(View.VISIBLE);
                }
            }
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }
}
