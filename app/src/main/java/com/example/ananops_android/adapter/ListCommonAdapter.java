package com.example.ananops_android.adapter;

import android.content.Context;

import com.example.ananops_android.adapter.base.ItemViewDelegate;

import java.util.List;

public abstract class ListCommonAdapter<T> extends ListMultiItemTypeAdapter<T>
{

    public ListCommonAdapter(Context context, final int layoutId, List<T> datas)
    {
        super(context, datas);

        addItemViewDelegate(new ItemViewDelegate<T>()
        {
            @Override
            public int getItemViewLayoutId()
            {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position)
            {
                return true;
            }

            @Override
            public void convert(ListViewHolder holder, T t, int position)
            {
                ListCommonAdapter.this.convert(holder, t, position);
            }
        });
    }

    protected abstract void convert(ListViewHolder viewHolder, T item, int position);

}
