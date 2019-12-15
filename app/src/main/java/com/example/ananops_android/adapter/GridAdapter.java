package com.example.ananops_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.ananops_android.R;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    private ArrayList<String> listUrls;
    private Context mContact;
    private LayoutInflater inflater;
    public GridAdapter(Context mContact, ArrayList<String> listUrls) {
        this.listUrls = listUrls;
        this.mContact=mContact;
        if(listUrls.size() == 7){
            listUrls.remove(listUrls.size()-1);
        }
        inflater = LayoutInflater.from(mContact);
    }

    public int getCount(){
        return  listUrls.size();
    }
    @Override
    public String getItem(int position) {
        return listUrls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_choose_photo, parent,false);
            holder.image = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        final String path=listUrls.get(position);
        if (path.equals("paizhao")){
            holder.image.setImageResource(R.drawable.ic_photo_add);
        }else {
            Glide.with(mContact)
                    .load(path)
                    .placeholder(R.drawable.ic_default_error)
                    .error(R.drawable.ic_default_error)
                    .centerCrop()
                    .crossFade()
                    .into(holder.image);
        }
        return convertView;
    }
    class ViewHolder {
        ImageView image;
    }
}
