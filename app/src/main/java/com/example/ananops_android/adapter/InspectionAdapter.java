package com.example.ananops_android.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ananops_android.R;
import com.example.ananops_android.entity.InspectionInfo;

import java.util.List;

public class InspectionAdapter extends BaseRecyclerAdapter {
    private List<InspectionInfo> inspectionContents;
    public InspectionAdapter(List<InspectionInfo> inspectionContents){
        this.inspectionContents=inspectionContents;
    }
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inspection_board,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }
    @Override
    public int getItemCount() {
        return inspectionContents.size();
    }
    public class ViewHolder extends BaseViewHolder {
        private TextView inspection_name, inspection_id, inspection_time, inspection_status, inspection_more,inspection_project;
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
            inspection_project = itemView.findViewById(R.id.inspection_item_name);
            inspection_time = itemView.findViewById(R.id.inspection_item_time);
            inspection_status = itemView.findViewById(R.id.inspection_item_status);
            inspection_more = itemView.findViewById(R.id.inspection_item_details);
            button1 = itemView.findViewById(R.id.inspection_item_button1);
            button2 = itemView.findViewById(R.id.inspection_item_button2);
        }

        @Override
        protected void onBind(int position) {
            InspectionInfo inspectionContent = inspectionContents.get(position);
            inspection_name.setText(inspectionContent.getTaskName());
            inspection_project.setText(inspectionContent.getProjectName());
            inspection_id.setText(String.valueOf(inspectionContent.getId()));
            inspection_time.setText(inspectionContent.getCycleTime()+"天");
            inspection_status.setText(String.valueOf(inspectionContent.getPointSum()));
            relativeButton.setVisibility(View.GONE);
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

