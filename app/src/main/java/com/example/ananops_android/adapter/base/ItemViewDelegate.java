package com.example.ananops_android.adapter.base;


import android.view.View;
import com.example.ananops_android.adapter.ListViewHolder;

/**
 * Created by zhy on 16/6/22.
 */
public interface ItemViewDelegate<T>
{

    public abstract int getItemViewLayoutId();

    public abstract boolean isForViewType(T item, int position);

    public abstract void convert(ListViewHolder holder, T t, int position);



}
