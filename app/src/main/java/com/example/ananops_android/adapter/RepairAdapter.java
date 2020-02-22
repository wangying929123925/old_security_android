package com.example.ananops_android.adapter;

import android.content.Context;
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
import com.example.ananops_android.activity.ContactActivity;
import com.example.ananops_android.activity.OrderDetailActivity;
import com.example.ananops_android.activity.RepairAddActivity;
import com.example.ananops_android.activity.RepairCommentActivity;
import com.example.ananops_android.activity.UserMainActivity;
import com.example.ananops_android.entity.RepairContent;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.SPUtils;

import java.util.List;

public class RepairAdapter extends RecyclerView.Adapter<RepairAdapter.ViewHolder> implements View.OnClickListener {
    private Context mContext;
    private List<RepairContent> mrepairContentList;

    public class ViewHolder extends RecyclerView.ViewHolder{
        View order_item_view;
        ImageView repairImage;
        TextView order_item_title;
        TextView order_item_project;
        TextView order_item_contract;
        TextView order_item_time;
        TextView order_item_status;
        TextView order_item_details;
        Button order_item_button1;
        Button order_item_button2;
        RelativeLayout relative_button;
        public ViewHolder(View view) {
            super(view);
            order_item_view = view;
            repairImage = view.findViewById(R.id.order_item_img);
            order_item_title = view.findViewById(R.id.order_item_title);
            order_item_project = view.findViewById(R.id.order_item_id);
            order_item_contract = view.findViewById(R.id.order_item_name);
            order_item_time = view.findViewById(R.id.order_item_time);
            order_item_status = view.findViewById(R.id.order_item_status);
            order_item_details = view.findViewById(R.id.order_item_details);
            order_item_button1 = view.findViewById(R.id.order_item_button1);
            order_item_button2 = view.findViewById(R.id.order_item_button2);
            relative_button = view.findViewById(R.id.relative_button);
            order_item_view.setOnClickListener(RepairAdapter.this);
            order_item_details.setOnClickListener(RepairAdapter.this);
            order_item_button1.setOnClickListener(RepairAdapter.this);
            order_item_button2.setOnClickListener(RepairAdapter.this);
        }
    }
    public RepairAdapter(List<RepairContent> repairContentlist){
        mrepairContentList = repairContentlist;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewTye){//创建ViewHolder实例
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.repairs_board_item,parent,false);//动态加载布局文件，第一个参数是要加载布局文件的ID，第二个是给布局添加父布局
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){//对RecycleView子项进行赋值，
        // 通过position参数得到当前实例，再将数据设置到ViewHolder
        RepairContent repairContent = mrepairContentList.get(position);
//        holder.order_item_title.setText(repairContent.getTitle());
//        holder.order_item_id.setText(repairContent.getPrincipalId());
//        holder.order_item_name.setText(repairContent.getProjectId());
//        holder.order_item_status.setText(repairContent.getUserId());
        holder.order_item_title.setText(repairContent.getId());
        holder.order_item_project.setText(String.valueOf(repairContent.getProjectId()));
        holder.order_item_contract.setText(String.valueOf(repairContent.getContractId()));
        holder.order_item_time.setText(repairContent.getAppointTime());
       holder.order_item_status.setText(BaseUtils.getInstence().statusNumConvertString(repairContent.getStatus()));
        switch (SPUtils.getInstance().getInt("role_num",1)){
            case 1:
                switch (repairContent.getStatus()){
                    case 1:
                        holder.relative_button.setVisibility(View.VISIBLE);
                        holder.order_item_button1.setText("重新报修");
                        holder.order_item_button2.setText("放弃报修");
                        break;
                    case 10:
                        holder.relative_button.setVisibility(View.VISIBLE);
                        holder.order_item_button1.setText("合格");
                        holder.order_item_button2.setText("不合格");
                        break;
                    case 12:
                        holder.relative_button.setVisibility(View.VISIBLE);
                        holder.order_item_button2.setText("去评价");
                        holder.order_item_button1.setVisibility(View.INVISIBLE);
                        break;
                        default:
                            holder.relative_button.setVisibility(View.GONE);
                            break;
                }
                break;
            case 2:
                switch (repairContent.getStatus()){
                    case 3:
                        holder.relative_button.setVisibility(View.VISIBLE);
                        holder.order_item_button1.setText("接单派工");
                        holder.order_item_button2.setText("不接单");
                        break;
                    case 4:
                        holder.relative_button.setVisibility(View.VISIBLE);
                        holder.order_item_button1.setVisibility(View.INVISIBLE);
                        holder.order_item_button2.setText("派工");
                        break;
                    case 7:
                        holder.relative_button.setVisibility(View.VISIBLE);
                        holder.order_item_button1.setText("通过");
                        holder.order_item_button2.setText("不通过");
                        break;
                    default:
                        holder.relative_button.setVisibility(View.GONE);
                        break;
                }
                break;
            case 3:
                switch (repairContent.getStatus()){
                    case 5:
                        holder.relative_button.setVisibility(View.VISIBLE);
                        holder.order_item_button1.setText("去接单");
                        holder.order_item_button2.setText("不接单");
                        break;
                    case 6:
                        holder.relative_button.setVisibility(View.VISIBLE);
                        holder.order_item_button1.setVisibility(View.INVISIBLE);
                        holder.order_item_button2.setText("去填方案");
                        break;
                    case 9:
                        holder.relative_button.setVisibility(View.VISIBLE);
                        holder.order_item_button1.setVisibility(View.INVISIBLE);
                        holder.order_item_button2.setText("进入维修");
                    break;
                    default:
                        holder.relative_button.setVisibility(View.GONE);
                        break;
                }
                break;
            case 4:
                switch (repairContent.getStatus()){
            case 2:
                holder.relative_button.setVisibility(View.VISIBLE);
                holder.order_item_button1.setText("通过");
                holder.order_item_button2.setText("不通过");
                break;
            case 8:
                holder.relative_button.setVisibility(View.VISIBLE);
                holder.order_item_button1.setText("通过");
                holder.order_item_button2.setText("不通过");
                break;
            case 11:
                holder.relative_button.setVisibility(View.VISIBLE);
                holder.order_item_button1.setVisibility(View.INVISIBLE);
                holder.order_item_button2.setText("去支付");
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

        Glide.with(mContext).load(R.drawable.haoyue).into(holder.repairImage);//使用Glide依赖库，是一个强大的图片加载库load()可以传入URL或者本地路径或者资源ID
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
                //Intent intent1=new Intent(mContext.getApplicationContext(), OrderSearchListActivity.class);
               // Toast.makeText(mContext, "工单详情" + (position + 1), Toast.LENGTH_SHORT).show();
               // Intent intent1=new Intent(mContext, OrderDetailActivity.class);
             //   mContext.startActivity(intent1);
              //  mContext.startActivity(intent1);
                break;
            case R.id.order_item_button1:
                switch (SPUtils.getInstance().getInt("role_num",1)){
                    case 1:
                        switch (repairContent.getStatus()){
                            case 10:
                                Toast.makeText(mContext, "你点击了合格按钮" , Toast.LENGTH_SHORT).show();
                                BaseUtils.getInstence().changeStatus(12, repairContent.getId(), "验收通过", mContext);
                                BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                                break;
                            case 1:
                                Toast.makeText(mContext, "你点击了重新填单按钮" , Toast.LENGTH_SHORT).show();
                                BaseUtils.getInstence().intent(mContext, RepairAddActivity.class);
                                break;
                             default:
                                break;
                        }
                        break;
                    case 2:
                        switch (repairContent.getStatus()){
                            case 3:
                                Toast.makeText(mContext, "接单派工" , Toast.LENGTH_SHORT).show();
                               // BaseUtils.getInstence().changeStatus(4, repairContent.getId(), "服务商接单", mContext);
                                String repairId = repairContent.getId();
                                BaseUtils.getInstence().intent(mContext, ContactActivity.class,"repairId",repairId);
                                break;

                            case 7:
                                //Toast.makeText(mContext, "你点击了通过按钮" , Toast.LENGTH_SHORT).show();
                                //BaseUtils.getInstence().changeStatus(9, repairContent.getId(), "服务商审核通过", mContext);
                               // BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                                break;
                            default:
                                break;
                        }
                        break;
                    case 3:
                        switch (repairContent.getStatus()){
                            case 5:
                                Toast.makeText(mContext, "你点击了去接单按钮" , Toast.LENGTH_SHORT).show();
                                BaseUtils.getInstence().changeStatus(6, repairContent.getId(), "维修工接单", mContext);
                                BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                                break;
                            default:
                                break;
                        }
                    case 4:
                        switch (repairContent.getStatus()){
                            case 2:
                                Toast.makeText(mContext, "你点击了通过按钮" , Toast.LENGTH_SHORT).show();
                                BaseUtils.getInstence().changeStatus(3, repairContent.getId(), "提交方案", mContext);
                                BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                                break;
                            case 8:
                               Toast.makeText(mContext, "你点击了合格按钮" , Toast.LENGTH_SHORT).show();
                               BaseUtils.getInstence().changeStatus(9, repairContent.getId(), "提交方案", mContext);
                                BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                                break;
                            default:
                                break;
                        }
                        default:
                            break;
                }
                break;
            case R.id.order_item_button2:
                switch (SPUtils.getInstance().getInt("role_num",1)){
                        case 1:
                            switch (repairContent.getStatus()){
                                case 10:
                                    Toast.makeText(mContext, "你点击了不合格按钮" , Toast.LENGTH_SHORT).show();
                                    BaseUtils.getInstence().changeStatus(1,repairContent.getId(), "验收不通过通过", mContext);
                                    BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                                    break;
                                case 1:
                                    Toast.makeText(mContext, "你点击了放弃填单按钮" , Toast.LENGTH_SHORT).show();
                                    BaseUtils.getInstence().changeStatus(15,repairContent.getId(), "放弃填单", mContext);
                                    BaseUtils.getInstence().intent(mContext,UserMainActivity.class);
                                    break;
                                case 12:
                                 //   BaseUtils.getInstence().intent(mContext, RepairCommentActivity.class,"order_id",repairContent.getRepair_id());
                                    Bundle bundle2 = new Bundle();
                                    bundle2.putString("order_id",repairContent.getId());
                                    BaseUtils.getInstence().intent(mContext, RepairCommentActivity.class,bundle2);
                                    break;
                                default:
                                    Bundle bundle1 = new Bundle();
                                  //  bundle1.putString("order_id",repairContent.getRepair_id());
                                    bundle1.putString("status_do","no");
                                    BaseUtils.getInstence().intent(mContext,OrderDetailActivity.class,bundle1);
                                    break;
                            }
                            break;
                        case 2:
                            switch (repairContent.getStatus()){
                                case 3:
                                    Toast.makeText(mContext, "你点击了不接单按钮" , Toast.LENGTH_SHORT).show();
                                    BaseUtils.getInstence().changeStatus(14, repairContent.getId(), "服务商不接单", mContext);
                                    BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                                    break;
                                case 4:
                                    Toast.makeText(mContext, "派工" , Toast.LENGTH_SHORT).show();
                                    // BaseUtils.getInstence().changeStatus(4, repairContent.getId(), "服务商接单", mContext);
                                    String repairId1 = repairContent.getId();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("type","inspection");
                                    bundle.putString("typeId",repairId1);
                                    BaseUtils.getInstence().intent(mContext, ContactActivity.class,bundle);
                                    break;
                                case 7:
                                    Toast.makeText(mContext, "你点击了不通过按钮" , Toast.LENGTH_SHORT).show();
                                    BaseUtils.getInstence().changeStatus(16, repairContent.getId(), "服务商不接单", mContext);
                                    BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case 3:
                            switch (repairContent.getStatus()){
                                case 5:
                                    Toast.makeText(mContext, "你点击了去不接单按钮" , Toast.LENGTH_SHORT).show();
                                    BaseUtils.getInstence().changeStatus(15, repairContent.getId(), "服务商审核通过", mContext);
                                    BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                                    break;
                                case 6:
                                    Toast.makeText(mContext, "你点击了填方案按钮" , Toast.LENGTH_SHORT).show();
                                    Bundle bundle0 = new Bundle();
                                    bundle0.putString("order_id",repairContent.getId());
                                    bundle0.putString("status_do","3-2");
                                    BaseUtils.getInstence().intent(mContext,OrderDetailActivity.class,bundle0);
                                    break;
                                case 9:
                                    Toast.makeText(mContext, "你点击了进入维修按钮" , Toast.LENGTH_SHORT).show();
                                    Bundle bundle1 = new Bundle();
                                    bundle1.putString("order_id",repairContent.getId());
                                    bundle1.putString("status_do","3-3");
                                    BaseUtils.getInstence().intent(mContext,OrderDetailActivity.class,bundle1);
                                    break;
                                default:
                                    break;
                            }
                        case 4:
                            switch (repairContent.getStatus()){
                                case 2:
                                    Toast.makeText(mContext, "你点击了不合格按钮" , Toast.LENGTH_SHORT).show();
                                    BaseUtils.getInstence().changeStatus(16, repairContent.getId(), "提交方案", mContext);
                                    BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                                    break;
                                case 8:
                                    Toast.makeText(mContext, "你点击了不通过按钮" , Toast.LENGTH_SHORT).show();
                                    BaseUtils.getInstence().changeStatus(17, repairContent.getId(), "提交方案", mContext);
                                    BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                                    break;
                                case 11:
                                    Toast.makeText(mContext, "支付功能正在开发中" , Toast.LENGTH_SHORT).show();
                                    BaseUtils.getInstence().changeStatus(12, repairContent.getId(), "支付跳转", mContext);
                                    BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                                    break;
                                default:
                                    break;
                            }

                }
                break;
            default:
                Toast.makeText(mContext, "即将查看工单详情" + (position + 1), Toast.LENGTH_SHORT).show();
                switch (SPUtils.getInstance().getInt("role_num",1)){
                    case 1:
                        switch (repairContent.getStatus()){
                            case 1:
                                Bundle bundle = new Bundle();
                                bundle.putString("order_id",repairContent.getId());
                                bundle.putString("status_do","1-1");
                                BaseUtils.getInstence().intent(mContext,OrderDetailActivity.class,bundle);
                                break;
                            case 10:
                                Bundle bundle1 = new Bundle();
                                bundle1.putString("order_id",repairContent.getId());
                                bundle1.putString("status_do","1-2");
                                BaseUtils.getInstence().intent(mContext,OrderDetailActivity.class,bundle1);
                                break;
                            case 12:
                                Bundle bundle2 = new Bundle();
                                bundle2.putString("order_id",repairContent.getId());
                              //  bundle2.putString("status_do","1-3");
                              BaseUtils.getInstence().intent(mContext,RepairCommentActivity.class,bundle2);
                                break;

                            default:
                                Bundle bundle0 = new Bundle();
                                bundle0.putString("order_id",repairContent.getId());
                                bundle0.putString("status_do","no");
                                BaseUtils.getInstence().intent(mContext,OrderDetailActivity.class,bundle0);
                                break;
                        }
                        break;
                    case 2:
                        switch (repairContent.getStatus()){
                            case 3:
                                Bundle bundle = new Bundle();
                                bundle.putString("order_id",repairContent.getId());
                                bundle.putString("status_do","2-1");
                                BaseUtils.getInstence().intent(mContext,OrderDetailActivity.class,bundle);
                                break;
                            case 4:
                                Bundle bundle3 = new Bundle();
                                bundle3.putString("order_id",repairContent.getId());
                                bundle3.putString("status_do","2-1");
                                BaseUtils.getInstence().intent(mContext,OrderDetailActivity.class,bundle3);
                                break;
                            case 7:
                                Bundle bundle1 = new Bundle();
                                bundle1.putString("order_id",repairContent.getId());
                                bundle1.putString("status_do","2-2");
                                BaseUtils.getInstence().intent(mContext,OrderDetailActivity.class,bundle1);
                                break;
                            default:
                                Bundle bundle0 = new Bundle();
                                bundle0.putString("order_id",repairContent.getId());
                                bundle0.putString("status_do","no");
                                BaseUtils.getInstence().intent(mContext,OrderDetailActivity.class,bundle0);
                                break;
                        }
                        break;
                    case 3:
                        switch (repairContent.getStatus()){
                            case 5:
                                Bundle bundle = new Bundle();
                                bundle.putString("order_id",repairContent.getId());
                                bundle.putString("status_do","3-1");
                                BaseUtils.getInstence().intent(mContext,OrderDetailActivity.class,bundle);
                                break;
                            case 6:
                                Bundle bundle2 = new Bundle();
                                bundle2.putString("order_id",repairContent.getId());
                                bundle2.putString("status_do","3-2");
                                BaseUtils.getInstence().intent(mContext,OrderDetailActivity.class,bundle2);
                                break;
                            case 9:
                                Bundle bundle1 = new Bundle();
                                bundle1.putString("order_id",repairContent.getId());
                                bundle1.putString("status_do","3-3");
                                BaseUtils.getInstence().intent(mContext,OrderDetailActivity.class,bundle1);
                                break;
                            default:
                                Bundle bundle0 = new Bundle();
                                bundle0.putString("order_id",repairContent.getId());
                                bundle0.putString("status_do","no");
                                BaseUtils.getInstence().intent(mContext,OrderDetailActivity.class,bundle0);
                                break;
                        }
                        break;
                    case 4:
                        switch (repairContent.getStatus()){
                            case 2:
                                Bundle bundle = new Bundle();
                                bundle.putString("order_id",repairContent.getId());
                                bundle.putString("status_do","4-1");
                                BaseUtils.getInstence().intent(mContext,OrderDetailActivity.class,bundle);
                                break;
                            case 8:
                                Bundle bundle1 = new Bundle();
                                bundle1.putString("order_id",repairContent.getId());
                                bundle1.putString("status_do","4-2");
                                BaseUtils.getInstence().intent(mContext,OrderDetailActivity.class,bundle1);
                                break;
                            case 11:
                               //支付
                                Bundle bundle3 = new Bundle();
                                bundle3.putString("order_id",repairContent.getId());
                                bundle3.putString("status_do","4-3");
                                BaseUtils.getInstence().intent(mContext,OrderDetailActivity.class,bundle3);
                                break;
                            default:
                                Bundle bundle0 = new Bundle();
                                bundle0.putString("order_id",repairContent.getId());
                                bundle0.putString("status_do","no");
                                BaseUtils.getInstence().intent(mContext,OrderDetailActivity.class,bundle0);
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
