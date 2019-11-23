package com.example.weather.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.weather.R;
import com.example.weather.entity.RepairContent;

import java.util.List;

public class RepairAdapter extends RecyclerView.Adapter<RepairAdapter.ViewHolder>{
    private Context mComtext;
    private List<RepairContent> mrepairContentList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View repairView;
        ImageView repairImage;
        TextView repairId;
        TextView repairAddress;
        TextView repairContent;
        TextView checkGroup;
        TextView repairTime;
        TextView repairStatus;
        public ViewHolder(View view) {
            super(view);
            repairView=view;
            repairImage=view.findViewById(R.id.head);
            repairId=view.findViewById(R.id.repair_id);
            repairAddress=view.findViewById(R.id.repair_address);
            repairContent=view.findViewById(R.id.repair_content);
            checkGroup=view.findViewById(R.id.check_group);
            repairTime=view.findViewById(R.id.repair_time);
           repairStatus=view.findViewById(R.id.repair_status);
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
        holder.repairView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                RepairContent repairContent=mrepairContentList.get(position);
                Toast.makeText(mComtext,repairContent.getRepair_id(),Toast.LENGTH_SHORT).show();
                //Intent intent=new Intent(mComtext, PandaActivity.class);
               // intent.putExtra(PandaActivity.PANDA_NAME,pandas.getName());
               // intent.putExtra(PandaActivity.PANDA_IMAGE_ID,pandas.getImageId());
             //   mComtext.startActivity(intent);
            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){//对RecycleView子项进行赋值，
        // 通过position参数得到当前实例，再将数据设置到ViewHolder
        RepairContent repairContent=mrepairContentList.get(position);
        holder.repairId.setText(repairContent.getRepair_id());
        holder.repairAddress.setText(repairContent.getRepair_address());
        holder.repairContent.setText(repairContent.getRepair_content());
        holder.checkGroup.setText(repairContent.getCheck_group());
        holder.repairTime.setText(repairContent.getRepair_time());
        holder.repairStatus.setText(repairContent.getRepair_status());
        Glide.with(mComtext).load(R.drawable.haoyue).into(holder.repairImage);//使用Glide依赖库，是一个强大的图片加载库load()可以传入URL或者本地路径或者资源ID
        //into()将图片设置到某个具体的ImageView
    }
    @Override
    public int getItemCount(){//获得子项数目
        return mrepairContentList.size();
    }
}
