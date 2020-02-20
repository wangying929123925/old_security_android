package com.example.ananops_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ananops_android.R;
import com.example.ananops_android.entity.Contacts;

import java.util.List;

public class ContactAdapter extends BaseAdapter {
  private List<Contacts> list = null;
  private Context mContext;
 public ContactAdapter(Context mContext,  List<Contacts> list){
  this.list=list;
  this.mContext=mContext;
}
//更新数据
  public void updateListView(List<Contacts> list) {
      this.list = list;
      notifyDataSetChanged();
  }
  @Override
    public int getCount() {
        return this.list.size();
    }
  @Override
    public Object getItem(int position) {
        return list.get(position);
    }
  @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder = null;
        final Contacts mContent = list.get(position);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_contacts, null);
            viewHolder.tvTitle = (TextView) view.findViewById(R.id.tv_catagory);
            view.setTag(viewHolder);
            viewHolder.tvName = (TextView) view.findViewById(R.id.name);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        int section = getSectionForPosition(position);
        if (position == getPositionForSection(section)) {
            viewHolder.tvTitle.setVisibility(View.VISIBLE);

            viewHolder.tvTitle.setText("第"+mContent.getDepartment()+"组");
        } else {
            viewHolder.tvTitle.setVisibility(View.GONE);
        }
        viewHolder.tvName.setText(this.list.get(position).getName());
        return view;
    }
    final static class ViewHolder {
        TextView tvTitle;
        TextView tvName;
    }
    public int getSectionForPosition(int position) {
        return list.get(position).getDepartment();
    }

    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            int sortStr = list.get(i).getDepartment();
            int firstChar = sortStr;
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

}
