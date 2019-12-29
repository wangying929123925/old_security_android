package com.example.ananops_android.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.ananops_android.R;
import com.example.ananops_android.activity.RepairCommentActivity;
import com.example.ananops_android.adapter.RepairAdapter;
import com.example.ananops_android.entity.RepairCommonDetail;
import com.example.ananops_android.entity.RepairContent;
import com.example.ananops_android.util.BaseUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailAuditFragment extends Fragment {
    private TextView tv_order_status;
    private TextView tv_audit_man;
    private TextView tv_audit_suggestion;
    private EditText tv_handle_suggestion;
    private static String STATUS_FLAG;
    private static String ORDER_ID;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_order_detail_audit,container,false);
        tv_order_status=view.findViewById(R.id.tv_order_status);
        tv_audit_man=view.findViewById(R.id.tv_audit_man);
        tv_audit_suggestion=view.findViewById(R.id.tv_audit_suggestion);
        tv_handle_suggestion=view.findViewById(R.id.tv_handle_suggestion);
        //init
        initdata();
        // mRecyclerView.setLayoutManager(new GridLayoutManager(this,4,VERTICAL,false));
        return view;
    }

    private void initdata() {
        if(!(getArguments()==null)){
            STATUS_FLAG=(String)getArguments().get("str");}
            if(STATUS_FLAG=="4-1"||STATUS_FLAG=="4-2"||STATUS_FLAG=="2-2"
              ||STATUS_FLAG.equals("4-1")||STATUS_FLAG.equals("4-2")||STATUS_FLAG.equals("2-2")){
                initDiffDetail();
            }else {
                tv_order_status.setText(BaseUtils.getInstence().statusNumConvertString(RepairCommonDetail.status));
                tv_audit_suggestion.setText("无");
                tv_handle_suggestion.setText("一般");
                tv_audit_suggestion.setText("通过");
                tv_handle_suggestion.setEnabled(false);
            }
        tv_audit_man.setText("no");
    }
    //待审核
private void initDiffDetail(){
        if(STATUS_FLAG=="4-1"){
            tv_order_status.setText("用户上级待审核");
        }else if(STATUS_FLAG=="4-2"){
            tv_order_status.setText("甲方待审核备件");
        }else {
            tv_order_status.setText("服务商待审核备件");
        }
     tv_handle_suggestion.setEnabled(true);
    tv_audit_suggestion.setOnClickListener(new View.OnClickListener() {
        final String[] items={"通过","不通过"};
        @Override
        public void onClick(View v) {
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(v.getContext());
            alertBuilder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getContext(),items[i],Toast.LENGTH_SHORT).show();
                    tv_audit_suggestion.setText(items[i]);
                    dialogInterface.dismiss();
                    //  tmp=items[i];
                }
            });
            alertBuilder.create().show();
        }
    });
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


}
