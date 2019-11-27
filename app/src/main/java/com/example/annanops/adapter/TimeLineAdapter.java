package com.example.annanops.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.annanops.entity.TimeLine;
import com.example.annaops.R;

import java.util.List;

public class TimeLineAdapter extends BaseAdapter {
     private Context mContact;
     private List<TimeLine> timeLines=null;
     public TimeLineAdapter(Context mContact,List<TimeLine> timeLines){
         this.mContact=mContact;
         this.timeLines=timeLines;
     }
    public void updateListView(List<TimeLine> list) {
        this.timeLines = list;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return this.timeLines.size();
    }
    @Override
    public Object getItem(int position) {
        return timeLines.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View view, ViewGroup arg2){
        ViewHolder viewHolder = null;
       final TimeLine timeLine=timeLines.get(position);
       if(view==null){
           viewHolder=new ViewHolder();
           view= LayoutInflater.from(mContact).inflate(R.layout.order_time_line_item,null);
           viewHolder.tvDirector=view.findViewById(R.id.item_director);
           viewHolder.tvProcess=view.findViewById(R.id.item_status);
           viewHolder.tvTime=view.findViewById(R.id.item_time);
           viewHolder.rlDoing=view.findViewById(R.id.listitem_doing);
           viewHolder.rlDone=view.findViewById(R.id.listitem_done);
       }
       else {
           viewHolder = (ViewHolder) view.getTag();
       }
       if(position==0){

           viewHolder.rlDone.setVisibility(View.GONE);
           viewHolder.rlDoing.setVisibility(View.VISIBLE);
           viewHolder.tvTime.setTextColor(mContact.getResources().getColor(R.color.blue));
           viewHolder.tvDirector.setTextColor(mContact.getResources().getColor(R.color.blue));
           viewHolder.tvProcess.setTextColor(mContact.getResources().getColor(R.color.blue));
       }
       else {
           viewHolder.rlDone.setVisibility(View.VISIBLE);
           viewHolder.rlDoing.setVisibility(View.GONE);
       }
        viewHolder.tvDirector.setText(timeLine.getOrderDirector());
        viewHolder.tvProcess.setText(timeLine.getProcessStatus());
        viewHolder.tvTime.setText(timeLine.getProcessTime());
return view;
    }
    final static class ViewHolder {
        TextView tvDirector;
        TextView tvProcess;
        TextView tvTime;
        RelativeLayout rlDone;
        RelativeLayout rlDoing;
    }
}
