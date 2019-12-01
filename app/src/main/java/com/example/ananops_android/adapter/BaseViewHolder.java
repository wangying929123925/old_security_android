package com.example.ananops_android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private BaseRecyclerAdapter.OnSubViewClickListener onSubViewClickListener;
    public BaseViewHolder(View itemView) {
        super(itemView);
        findViewById(itemView);
    }

    /**
     * 传入子项点击事件所需参数
     * @param listener 自定义的接口
     * @param tagPosition tag
     */
    public void setSubViewClickListener(BaseRecyclerAdapter.OnSubViewClickListener listener, int tagPosition){
        this.onSubViewClickListener = listener;
        initSubViewClick(tagPosition);
    }

    /**
     * 通过id匹配控件（开发者自行实现）
     * @param itemView 父布局
     */
    abstract protected void findViewById(View itemView);

    /**
     * 用于装载数据（开发者自行实现）
     * @param position 当前位置
     */
    abstract protected void onBind(int position);

    /**
     * 初始化子项的点击事件（为子项设置tag）
     * @param tagPosition tag
     */
    protected void initSubViewClick(int tagPosition){

    }

    /**
     * 实现子项点击事件的转发
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (v.getTag() != null) {
            int position = (int) v.getTag();
            onSubViewClickListener.onSubViewClick(v,position);
        }
    }

}
