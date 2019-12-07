package com.example.ananops_android.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.example.ananops_android.R;
import com.example.ananops_android.activity.OrderDetailActivity;
import com.example.ananops_android.activity.OrderSearchListActivity;
import com.example.ananops_android.activity.RepairCommentActivity;
import com.example.ananops_android.entity.RepairContent;
import com.example.ananops_android.entity.UserLogin;
import com.example.ananops_android.util.BaseUtils;

import java.util.List;

public class RepairAdapter extends RecyclerView.Adapter<RepairAdapter.ViewHolder> implements View.OnClickListener {
    private Context mComtext;
    private List<RepairContent> mrepairContentList;


    public class ViewHolder extends RecyclerView.ViewHolder{
        View order_item_view;
        ImageView repairImage;
        TextView order_item_title;
        TextView order_item_id;
        TextView order_item_name;
        TextView order_item_status;
        TextView order_item_details;
        Button order_item_button1;
        Button order_item_button2;
        RelativeLayout relative_button;
        public ViewHolder(View view) {
            super(view);
            order_item_view=view;
            repairImage=view.findViewById(R.id.order_item_img);
            order_item_title=view.findViewById(R.id.order_item_title);
            order_item_id=view.findViewById(R.id.order_item_id);
            order_item_name=view.findViewById(R.id.order_item_name);
            order_item_status=view.findViewById(R.id.order_item_status);
            order_item_details=view.findViewById(R.id.order_item_details);
            order_item_button1=view.findViewById(R.id.order_item_button1);
            order_item_button2=view.findViewById(R.id.order_item_button2);
            relative_button=view.findViewById(R.id.relative_button);
            order_item_view.setOnClickListener(RepairAdapter.this);
            order_item_details.setOnClickListener(RepairAdapter.this);
            order_item_button1.setOnClickListener(RepairAdapter.this);
            order_item_button2.setOnClickListener(RepairAdapter.this);
        }
    }
    public RepairAdapter(List<RepairContent> repairContentlist){
        mrepairContentList=repairContentlist;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewTye){//创建ViewHolder实例
        if(mComtext==null){
            mComtext=parent.getContext();
        }
        View view= LayoutInflater.from(mComtext).inflate(R.layout.repairs_board_item,parent,false);//动态加载布局文件，第一个参数是要加载布局文件的ID，第二个是给布局添加父布局
        final ViewHolder holder=new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){//对RecycleView子项进行赋值，
        // 通过position参数得到当前实例，再将数据设置到ViewHolder
        RepairContent repairContent=mrepairContentList.get(position);
        holder.order_item_title.setText(repairContent.getRepair_content());
        holder.order_item_id.setText(repairContent.getRepair_id());
        holder.order_item_name.setText(repairContent.getRepair_address());
        holder.order_item_status.setText(repairContent.getRepair_status());
        switch (UserLogin.useCode){
            case 1:
                switch (repairContent.getRepair_status()){
                    case"计划中":
                        holder.relative_button.setVisibility(View.GONE);
                    break;
                    case "待接单":
                        holder.relative_button.setVisibility(View.VISIBLE);
                        holder.order_item_button1.setVisibility(View.INVISIBLE);
                        holder.order_item_button2.setText("确认");
                        break;
                    case "审核不通过":
                        holder.relative_button.setVisibility(View.VISIBLE);
                        holder.order_item_button1.setText("重新报修");
                        holder.order_item_button2.setText("放弃报修");
                        break;
                    case "维修工待接单":
                        holder.relative_button.setVisibility(View.GONE);
                        break;
                    case "待填方案":
                        holder.relative_button.setVisibility(View.GONE);
                        break;
                    case"服务商待审核备件":
                        holder.relative_button.setVisibility(View.GONE);
                        break;
                    case"甲方待审核备件":
                        holder.relative_button.setVisibility(View.GONE);
                        break;
                    case "维修中":
                        holder.relative_button.setVisibility(View.GONE);
                        break;
                    case "待验收":
                        holder.relative_button.setVisibility(View.VISIBLE);
                        holder.order_item_button1.setText("合格");
                        holder.order_item_button2.setText("不合格");
                        break;
                    case "待支付":
                        holder.relative_button.setVisibility(View.GONE);
                        break;
                    case "待评价":
                        holder.relative_button.setVisibility(View.VISIBLE);
                        holder.order_item_button2.setText("去评价");
                        holder.order_item_button1.setVisibility(View.INVISIBLE);
                        break;
                    case "已评价":
                        holder.relative_button.setVisibility(View.GONE);
                        break;
                }
                break;
            case 2:
                switch (repairContent.getRepair_status()){
                    case"计划中":
                        holder.relative_button.setVisibility(View.GONE);
                        break;
                    case "待接单":
                        holder.relative_button.setVisibility(View.VISIBLE);
                        holder.order_item_button1.setText("接单派工");
                        holder.order_item_button2.setText("不接单");
                        break;
                    case "审核不通过":
                        holder.relative_button.setVisibility(View.GONE);
                        break;
                    case "维修工待接单":
                        holder.relative_button.setVisibility(View.GONE);
                        break;
                    case "待填方案":
                        holder.relative_button.setVisibility(View.GONE);
                        break;
                    case"服务商待审核备件":
                        holder.relative_button.setVisibility(View.VISIBLE);
                        holder.order_item_button1.setText("通过");
                        holder.order_item_button2.setText("不通过");
                        break;
                    case"甲方待审核备件":
                        holder.relative_button.setVisibility(View.GONE);
                        break;
                    case "维修中":
                        holder.relative_button.setVisibility(View.GONE);
                        break;
                    case "待验收":
                        holder.relative_button.setVisibility(View.GONE);
                        break;
                    case "待评价":
                        holder.relative_button.setVisibility(View.GONE);
                        break;
                    case "待支付":
                        holder.relative_button.setVisibility(View.GONE);
                        break;
                    case "已评价":
                        holder.relative_button.setVisibility(View.GONE);
                        break;
                }
                break;
            case 3:
                switch (repairContent.getRepair_status()){
                    case"计划中":
                        holder.relative_button.setVisibility(View.GONE);
                        break;
                    case "待接单":
                        holder.relative_button.setVisibility(View.GONE);
                        break;
                    case "审核不通过":
                        holder.relative_button.setVisibility(View.GONE);
                        break;
                    case "维修工待接单":
                        holder.relative_button.setVisibility(View.VISIBLE);
                        holder.order_item_button1.setText("去接单");
                        holder.order_item_button2.setText("不接单");
                        break;
                    case "待填方案":
                        holder.relative_button.setVisibility(View.VISIBLE);
                        holder.order_item_button1.setVisibility(View.INVISIBLE);
                        holder.order_item_button2.setText("去填方案");
                        break;
                    case"服务商待审核备件":
                        holder.relative_button.setVisibility(View.VISIBLE);
                        holder.order_item_button1.setText("通过");
                        holder.order_item_button2.setText("不通过");
                        break;
                    case"甲方待审核备件":
                        holder.relative_button.setVisibility(View.GONE);
                        break;
                    case "维修中":
                        holder.relative_button.setVisibility(View.VISIBLE);
                        holder.order_item_button1.setVisibility(View.INVISIBLE);
                        holder.order_item_button2.setText("进入维修");
                    case "待验收":
                        holder.relative_button.setVisibility(View.GONE);
                        break;
                    case "待评价":
                        holder.relative_button.setVisibility(View.GONE);
                        break;
                    case "待支付":
                        holder.relative_button.setVisibility(View.GONE);
                        break;
                    case "已评价":
                        holder.relative_button.setVisibility(View.GONE);
                        break;
                }
                break;
            case 4:
                switch (repairContent.getRepair_status()){
            case"计划中":
                holder.relative_button.setVisibility(View.VISIBLE);
                holder.order_item_button1.setText("通过");
                holder.order_item_button2.setText("不通过");
                break;
            case "待接单":
                holder.relative_button.setVisibility(View.GONE);
                break;
            case "审核不通过":
                holder.relative_button.setVisibility(View.GONE);
                break;
            case "维修工待接单":
                holder.relative_button.setVisibility(View.GONE);
                break;
            case "待填方案":
                holder.relative_button.setVisibility(View.GONE);
                break;
            case"服务商待审核备件":
                holder.relative_button.setVisibility(View.GONE);
                break;
            case"甲方待审核备件":
                holder.relative_button.setVisibility(View.VISIBLE);
                holder.order_item_button1.setText("通过");
                holder.order_item_button2.setText("不通过");
                break;
            case "维修中":
                holder.relative_button.setVisibility(View.GONE);
                break;
            case "待验收":
                holder.relative_button.setVisibility(View.GONE);
                break;
            case "待支付":
                holder.relative_button.setVisibility(View.VISIBLE);
                holder.order_item_button1.setVisibility(View.INVISIBLE);
                holder.order_item_button2.setText("去支付");
                break;
            case "待评价":
                holder.relative_button.setVisibility(View.GONE);
                break;
            case "已评价":
                holder.relative_button.setVisibility(View.GONE);
                break;
                default:
                    holder.relative_button.setVisibility(View.GONE);
                    break;
        }
        break;
                default:
                    holder.relative_button.setVisibility(View.GONE);
                    break;
        }

        Glide.with(mComtext).load(R.drawable.haoyue).into(holder.repairImage);//使用Glide依赖库，是一个强大的图片加载库load()可以传入URL或者本地路径或者资源ID
        //into()将图片设置到某个具体的ImageView
        holder.order_item_details.setTag(position);
        holder.order_item_button1.setTag(position);
        holder.order_item_button2.setTag(position);
        holder.order_item_view.setTag(position);
    }
    @Override
    public int getItemCount(){//获得子项数目
        return mrepairContentList.size();
    }
    public enum ViewName {
        ITEM,
        PRACTISE
    }
    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        switch (v.getId()){
            case R.id.contact_recycler_view:
               onItemClick(v, ViewName.PRACTISE, position);
                break;
            default:
               onItemClick(v, ViewName.ITEM, position);
                break;
        }

    }
    void onItemClick(View v, ViewName viewName, int position) {
        RepairContent repairContent = mrepairContentList.get(position);
        switch (v.getId()) {
            case R.id.order_item_details:
                //Intent intent1=new Intent(mComtext.getApplicationContext(), OrderSearchListActivity.class);
               // Toast.makeText(mComtext, "工单详情" + (position + 1), Toast.LENGTH_SHORT).show();
               // Intent intent1=new Intent(mComtext, OrderDetailActivity.class);
             //   mComtext.startActivity(intent1);
              //  mComtext.startActivity(intent1);
                break;
            case R.id.order_item_button1:
                switch (UserLogin.useCode){
                    case 1:
                        switch (repairContent.getRepair_status()){
                            case "待验收":
                                Toast.makeText(mComtext, "你点击了合格按钮" , Toast.LENGTH_SHORT).show();
                                break;
                            case "审核不通过":
                                Toast.makeText(mComtext, "你点击了重新填单按钮" , Toast.LENGTH_SHORT).show();
                                break;
                             default:
                                break;
                        }
                        break;
                    case 2:
                        switch (repairContent.getRepair_status()){
                            case "待接单":
                                Toast.makeText(mComtext, "你点击了接单派工按钮" , Toast.LENGTH_SHORT).show();
                                break;
                            case"服务商待审核备件":
                                Toast.makeText(mComtext, "你点击了通过按钮" , Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                        break;
                    case 3:
                        switch (repairContent.getRepair_status()){
                            case "维修工待接单":
                                Toast.makeText(mComtext, "你点击了去接单按钮" , Toast.LENGTH_SHORT).show();
                                break;
                            case "维修中":
                                Toast.makeText(mComtext, "你点击了进入维修按钮" , Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                    case 4:
                        switch (repairContent.getRepair_status()){
                            case"计划中":
                                Toast.makeText(mComtext, "你点击了通过按钮" , Toast.LENGTH_SHORT).show();
                                break;
                            case "甲方待审核备件":
                                Toast.makeText(mComtext, "你点击了合格按钮" , Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                        default:
                            break;
                }
                break;
            case R.id.order_item_button2:
                switch (UserLogin.useCode){
                        case 1:
                            switch (repairContent.getRepair_status()){
                                case "待验收":
                                    Toast.makeText(mComtext, "你点击了不合格按钮" , Toast.LENGTH_SHORT).show();
                                    break;
                                case "审核不通过":
                                    Toast.makeText(mComtext, "你点击了放弃填单按钮" , Toast.LENGTH_SHORT).show();
                                    break;
                                case "待评价":
                                    BaseUtils.getInstence().intent(mComtext, RepairCommentActivity.class,"order_id",repairContent.getRepair_id());
                                    break;
                                default:
                                    Bundle bundle1=new Bundle();
                                    bundle1.putString("order_id",repairContent.getRepair_id());
                                    bundle1.putString("status_do","no");
                                    BaseUtils.getInstence().intent(mComtext,OrderDetailActivity.class,bundle1);
                                    break;
                            }
                            break;
                        case 2:
                            switch (repairContent.getRepair_status()){
                                case "待接单":
                                    Toast.makeText(mComtext, "你点击了不接单按钮" , Toast.LENGTH_SHORT).show();
                                    break;
                                case"服务商待审核备件":
                                    Toast.makeText(mComtext, "你点击了不通过按钮" , Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case 3:
                            switch (repairContent.getRepair_status()){
                                case "维修工待接单":
                                    Toast.makeText(mComtext, "你点击了去不接单按钮" , Toast.LENGTH_SHORT).show();
                                    break;
                                case "维修中":
                                    Toast.makeText(mComtext, "你点击了进入维修按钮" , Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    break;
                            }
                        case 4:
                            switch (repairContent.getRepair_status()){
                                case"计划中":
                                    Toast.makeText(mComtext, "你点击了合格按钮" , Toast.LENGTH_SHORT).show();
                                    break;
                                case "甲方待审核备件":
                                    Toast.makeText(mComtext, "你点击了合格按钮" , Toast.LENGTH_SHORT).show();
                                    break;
                                case "支付":
                                    Toast.makeText(mComtext, "你点击了去支付按钮" , Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    break;
                            }

                }
                break;
            default:
                Toast.makeText(mComtext, "即将查看工单详情" + (position + 1), Toast.LENGTH_SHORT).show();
                switch (UserLogin.useCode){
                    case 1:
                        switch (repairContent.getRepair_status()){
                            case "待接单":
                                Bundle bundle=new Bundle();
                                bundle.putString("order_id",repairContent.getRepair_id());
                                bundle.putString("status_do","1-1");
                                BaseUtils.getInstence().intent(mComtext,OrderDetailActivity.class,bundle);
                                break;
                            case "审核不通过":
                                Bundle bundle1=new Bundle();
                                bundle1.putString("order_id",repairContent.getRepair_id());
                                bundle1.putString("status_do","1-2");
                                BaseUtils.getInstence().intent(mComtext,OrderDetailActivity.class,bundle1);
                                break;
                            case "待评价":
                               // Bundle bundle2=new Bundle();
                              //  bundle2.putString("order_id",repairContent.getRepair_id());
                              //  bundle2.putString("status_do","1-3");
                              //  BaseUtils.getInstence().intent(mComtext,RepairCommentActivity.class,bundle2);
                                break;
                            case "待验收":
                                Bundle bundle3=new Bundle();
                                bundle3.putString("order_id",repairContent.getRepair_id());
                                bundle3.putString("status_do","1-3");
                                BaseUtils.getInstence().intent(mComtext,OrderDetailActivity.class,bundle3);
                                break;
                            default:
                                Bundle bundle0=new Bundle();
                                bundle0.putString("order_id",repairContent.getRepair_id());
                                bundle0.putString("status_do","no");
                                BaseUtils.getInstence().intent(mComtext,OrderDetailActivity.class,bundle0);
                                break;
                        }
                        break;
                    case 2:
                        switch (repairContent.getRepair_status()){
                            case "待接单":
                                Bundle bundle=new Bundle();
                                bundle.putString("order_id",repairContent.getRepair_id());
                                bundle.putString("status_do","2-1");
                                BaseUtils.getInstence().intent(mComtext,OrderDetailActivity.class,bundle);
                                break;
                            case"服务商待审核备件":
                                Bundle bundle1=new Bundle();
                                bundle1.putString("order_id",repairContent.getRepair_id());
                                bundle1.putString("status_do","2-2");
                                BaseUtils.getInstence().intent(mComtext,OrderDetailActivity.class,bundle1);
                                break;
                            default:
                                Bundle bundle0=new Bundle();
                                bundle0.putString("order_id",repairContent.getRepair_id());
                                bundle0.putString("status_do","no");
                                BaseUtils.getInstence().intent(mComtext,OrderDetailActivity.class,bundle0);
                                break;
                        }
                        break;
                    case 3:
                        switch (repairContent.getRepair_status()){
                            case "维修工待接单":
                                Bundle bundle=new Bundle();
                                bundle.putString("order_id",repairContent.getRepair_id());
                                bundle.putString("status_do","3-1");
                                BaseUtils.getInstence().intent(mComtext,OrderDetailActivity.class,bundle);
                                break;
                            case "待填方案":
                                Bundle bundle2=new Bundle();
                                bundle2.putString("order_id",repairContent.getRepair_id());
                                bundle2.putString("status_do","3-2");
                                BaseUtils.getInstence().intent(mComtext,OrderDetailActivity.class,bundle2);
                                break;
                            case "维修中":
                                Bundle bundle1=new Bundle();
                                bundle1.putString("order_id",repairContent.getRepair_id());
                                bundle1.putString("status_do","3-3");
                                BaseUtils.getInstence().intent(mComtext,OrderDetailActivity.class,bundle1);
                                break;
                            default:
                                Bundle bundle0=new Bundle();
                                bundle0.putString("order_id",repairContent.getRepair_id());
                                bundle0.putString("status_do","no");
                                BaseUtils.getInstence().intent(mComtext,OrderDetailActivity.class,bundle0);
                                break;
                        }
                        break;
                    case 4:
                        switch (repairContent.getRepair_status()){
                            case"计划中":
                                Bundle bundle=new Bundle();
                                bundle.putString("order_id",repairContent.getRepair_id());
                                bundle.putString("status_do","4-1");
                                BaseUtils.getInstence().intent(mComtext,OrderDetailActivity.class,bundle);
                                break;
                            case "甲方待审核备件":
                                Bundle bundle1=new Bundle();
                                bundle1.putString("order_id",repairContent.getRepair_id());
                                bundle1.putString("status_do","4-2");
                                BaseUtils.getInstence().intent(mComtext,OrderDetailActivity.class,bundle1);
                                break;
                            case "支付":
                               //支付
                                break;
                            default:
                                Bundle bundle0=new Bundle();
                                bundle0.putString("order_id",repairContent.getRepair_id());
                                bundle0.putString("status_do","no");
                                BaseUtils.getInstence().intent(mComtext,OrderDetailActivity.class,bundle0);
                                break;
                        }
                        break;
                        default:
                            break;
                }
                break;
        }
    }
}
