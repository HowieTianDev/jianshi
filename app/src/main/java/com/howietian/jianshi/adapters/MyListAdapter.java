package com.howietian.jianshi.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.howietian.jianshi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 83624 on 2017/5/9.
 */

public class MyListAdapter extends BaseAdapter {
    private Context context;
    private String[] titles;
    private int postion;

    public MyListAdapter(Context context, String[] titles) {
        this.context = context;
        this.titles = titles;

    }

    public void setPosition(int position) {
        this.postion = position;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int i) {
        return titles[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        //利用 Android 系统的视图缓存,没有视图缓存重新加载视图
        if (view == null) {
            vh = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_list, viewGroup, false);
            vh.tv_item = (TextView) view.findViewById(R.id.tv_item);

            view.setTag(vh);
        } else {//有视图缓存，直接复用
            vh = (ViewHolder) view.getTag();
        }
        // 先全部设置成白色背景
        vh.tv_item.setTextColor(Color.BLACK);
        view.setBackgroundColor(Color.WHITE);
        // 如果位置和被点击的位置相同，则改变背景
        if (i == postion) {
            view.setBackgroundColor(Color.DKGRAY);
            vh.tv_item.setTextColor(Color.WHITE);
        }

        vh.tv_item.setText(titles[i]);
        return view;
    }

    static class ViewHolder {
        private TextView tv_item;
    }
}
