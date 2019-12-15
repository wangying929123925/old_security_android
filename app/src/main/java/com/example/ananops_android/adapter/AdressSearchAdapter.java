package com.example.ananops_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.amap.api.services.help.Tip;
import com.example.ananops_android.R;


import java.util.List;

public class AdressSearchAdapter extends BaseAdapter {
    private Context mContact;
    private List<Tip> addressSearches;
    public AdressSearchAdapter(Context mContact,List<Tip> addressSearches){
        this.mContact=mContact;
        this.addressSearches=addressSearches;
    }
    public void updateListView(List<Tip> list){
        this.addressSearches=list;
        notifyDataSetChanged();
    }
    final static class ViewHolder {
        TextView address_name;
        TextView address_deteail;
    }
    @Override
    public int getCount() {
        return this.addressSearches.size();
    }
    @Override
    public Object getItem(int position) {
        return addressSearches.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View view, ViewGroup arg2){
        ViewHolder viewHolder;
        if(view==null){
            viewHolder=new ViewHolder();
            view= LayoutInflater.from(mContact).inflate(R.layout.item_address_search,null);
            viewHolder.address_name=view.findViewById(R.id.address_name);
            viewHolder.address_deteail=view.findViewById(R.id.address_detail);
            view.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder) view.getTag();
        }
        if(addressSearches==null){
            return view;
        }
        final Tip tip=addressSearches.get(position);
        viewHolder.address_name.setText(tip.getName());
       // viewHolder.address_deteail.setText(tip.getAddress());
        String address_detail=tip.getAddress();
        if(address_detail==null||address_detail.equals("")){
            viewHolder.address_deteail.setVisibility(View.GONE);
        }else{
            viewHolder.address_deteail.setVisibility(View.VISIBLE);
             viewHolder.address_deteail.setText(address_detail);
        }
        return view;
    }
}
