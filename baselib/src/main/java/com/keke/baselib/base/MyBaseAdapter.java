package com.keke.baselib.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by wpz on 2016/11/23.
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {
    private List<T> list;
    private List<Integer> list0;
    private int resId;
    private Context context;

    public MyBaseAdapter(List<T> list, int resId, Context context) {
        this.list = list;
        this.resId = resId;
        this.context = context;
    }
    public MyBaseAdapter(List<T> list, int resId, Context context,List<Integer> list0) {
        this.list = list;
        this.resId = resId;
        this.context = context;
        this.list0 = list0;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = ViewHolder.getHolder(context, resId, convertView);
        bindData(viewHolder, position);
        return viewHolder.getView();
    }

    public abstract void bindData(ViewHolder viewHolder, int position);
}
