package com.example.ananops_android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ananops_android.R;
import com.example.ananops_android.entity.InspectionContent;
import com.example.ananops_android.entity.UserLogin;

import java.util.List;

public class InspectionAdapter extends BaseRecyclerAdapter {
    private List<InspectionContent> inspectionContents;
    public InspectionAdapter(List<InspectionContent> inspectionContents){
        this.inspectionContents=inspectionContents;
    }
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.inspection_board_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }
    @Override
    public int getItemCount() {
        return inspectionContents.size();
    }
    public class ViewHolder extends BaseViewHolder {
        private TextView inspection_name, inspection_id, inspection_time, inspection_status, inspection_more;
        private RelativeLayout relativeButton;
        private Button button1;
        private Button button2;

        ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void findViewById(View itemView) {
            relativeButton=itemView.findViewById(R.id.relative_button);
            inspection_name = itemView.findViewById(R.id.inspection_item_title);
            inspection_id = itemView.findViewById(R.id.inspection_item_id);
            inspection_time = itemView.findViewById(R.id.inspection_item_time);
            inspection_status = itemView.findViewById(R.id.inspection_item_status);
            inspection_more = itemView.findViewById(R.id.inspection_item_details);
            button1 = itemView.findViewById(R.id.inspection_item_button1);
            button2 = itemView.findViewById(R.id.inspection_item_button2);
        }

        @Override
        protected void onBind(int position) {
            InspectionContent inspectionContent = inspectionContents.get(position);
            inspection_name.setText(inspectionContent.getInspection_name());
            inspection_id.setText(inspectionContent.getInspection_id());
            inspection_time.setText(inspectionContent.getInspection_time());
            inspection_status.setText(inspectionContent.getInspection_status());
            switch (UserLogin.useCode){
                case 1:
                    relativeButton.setVisibility(View.GONE);
                    break;
                case 2:
                    relativeButton.setVisibility(View.GONE);
                    break;
                case 3:
                    switch (inspectionContent.getInspection_status()){
                        case "待执行":
                            relativeButton.setVisibility(View.VISIBLE);
                            button2.setText("去执行");
                            button1.setVisibility(View.INVISIBLE);
                            break;
                        case "已完成":
                            relativeButton.setVisibility(View.GONE);
                            break;
                    }
                    break;
            }
        }

        @Override
        protected void initSubViewClick(int tagPosition) {
            inspection_more.setTag(tagPosition);
            button2.setTag(tagPosition);
            inspection_more.setOnClickListener(this);
            button2.setOnClickListener(this);
        }
    }
    }

