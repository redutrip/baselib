package com.keke.baselib.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by wpz on 2016/11/23.
 */
public class ViewHolder {

    private View convertView;
    private SparseArray<View> sparseArray = new SparseArray<>();

    public ViewHolder(Context context, int resId) {
        convertView = LayoutInflater.from(context).inflate(resId, null);
        convertView.setTag(this);
//        AutoUtils.auto(convertView);
    }

    public static ViewHolder getHolder(Context context, int resId, View view) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder(context, resId);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        return viewHolder;
    }

    public View getView() {
        return convertView;
    }

    public View findID(int id) {
        View view = sparseArray.get(id);
        if (view == null) {
            view = convertView.findViewById(id);
            sparseArray.append(id, view);
        }
        return view;
    }
}
