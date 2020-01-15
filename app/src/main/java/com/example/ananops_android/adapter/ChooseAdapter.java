package com.example.ananops_android.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.ananops_android.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChooseAdapter extends BaseAdapter {
    List<String> mStringList;
    Context mContext;
    private Map<Integer,Boolean> map=new HashMap<>();// 存放已被选中的CheckBox
    public ChooseAdapter(Context context,List<String> stringList){
        mStringList = stringList;
        mContext=context;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if(convertView==null){
            convertView=View.inflate(mContext, R.layout.item_chooese_replacement,null);
            holder=new MyViewHolder();
            holder.mTextView= (TextView) convertView.findViewById(R.id.replacement_name);
            holder.mCheckBox= (CheckBox) convertView.findViewById(R.id.inspection_sub_checkBox);
      //      holder.replacement_detail=convertView.findViewById(R.id.replacement_detail);
            convertView.setTag(holder);
        }else {
            holder= (MyViewHolder) convertView.getTag();
        }

        holder.mTextView.setText(mStringList.get(position));
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    map.put(position,true);
                }else {
                    map.remove(position);
                }
            }
        });

        if(map!=null&&map.containsKey(position)){
            holder.mCheckBox.setChecked(true);
        }else {
            holder.mCheckBox.setChecked(false);
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return mStringList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    public static class  MyViewHolder {
        TextView mTextView;
        TextView replacement_detail;
        CheckBox mCheckBox;
    }
}
