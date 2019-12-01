package com.example.ananops_android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> implements View.OnClickListener,View.OnLongClickListener{
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    private OnRecyclerViewItemLongClickListener onRecyclerViewItemLongClickListener;
    private OnSubViewClickListener onSubViewClickListener;

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.onBind(position);
        if (onRecyclerViewItemClickListener != null) {
            holder.itemView.setOnClickListener(this);
        }
        if (onRecyclerViewItemLongClickListener != null) {
            holder.itemView.setOnClickListener(this);
        }
        if (onSubViewClickListener != null) {
            holder.setSubViewClickListener(onSubViewClickListener,position);
        }
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    public void setOnRecyclerViewItemLongClickListener(OnRecyclerViewItemLongClickListener onRecyclerViewItemLongClickListener) {
        this.onRecyclerViewItemLongClickListener = onRecyclerViewItemLongClickListener;
    }

    public void setOnSubViewClickListener(OnSubViewClickListener listener){
        this.onSubViewClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (v.getTag() != null) {
            int position = (int) v.getTag();
            onRecyclerViewItemClickListener.onItemClick(position);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (v.getTag() != null){
            int position = (int)v.getTag();
            onRecyclerViewItemLongClickListener.onItemLongClick(position);
        }
        return true;
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);
    }

    public interface OnSubViewClickListener{
        void onSubViewClick(View v, int position);
    }

    public interface OnRecyclerViewItemLongClickListener {
        void onItemLongClick(int position);
    }

}
