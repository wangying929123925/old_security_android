package com.example.ananops_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ananops_android.R;
import com.example.ananops_android.entity.InspectionTaskItem;

import java.util.List;

public class DialogItemAdapter extends BaseAdapter {
    //这里可以传递个对象，用来控制不同的item的效果
    //比如每个item的背景资源，选中样式等
    public List<InspectionTaskItem> list;
    LayoutInflater inflater;

    public DialogItemAdapter(Context context, List<InspectionTaskItem> list) {
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public InspectionTaskItem getItem(int i) {
        if (i == getCount() || list == null) {
            return null;
        }
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_inspection_sublist, null);
            holder.textview1 = (TextView) convertView.findViewById(R.id.inspection_name);
            holder.textView2 = (TextView) convertView.findViewById(R.id.inspection_id);
            holder.textView3 = (TextView) convertView.findViewById(R.id.inspection_maintainername);
            holder.textView4 = (TextView) convertView.findViewById(R.id.inspection_updateTime);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textview1.setText(list.get(position).getItemName());
        holder.textView2.setText(String.valueOf(list.get(position).getId()));
        holder.textView3.setText(list.get(position).getMaintainerName());
        holder.textView4.setText(list.get(position).getUpdateTime());
        return convertView;
    }

    public static class ViewHolder {
        public TextView textview1,textView2,textView3,textView4;
    }
}
