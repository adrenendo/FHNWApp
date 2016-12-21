package com.example.admin.fhnwapp;

import android.widget.BaseAdapter;

/**
 * Created by admin on 17.12.2016.
 */

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.fragments.FAQFragment;

public class CustomAdapter extends BaseAdapter {
    Context mContext;
    List<FAQEntry> beandata;
    LayoutInflater mInflater;

    public CustomAdapter(Context context, List<FAQEntry> itemList) {
        mContext = context;
        beandata = itemList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return beandata.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = mInflater.inflate(R.layout.row,
                    parent, false);
            vh.tv1 = (TextView) convertView.findViewById(R.id.textView1);
            vh.tv2 = (TextView) convertView.findViewById(R.id.textView2);
            convertView.setTag(vh);

        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        FAQEntry objBean = beandata.get(position);
        vh.tv1.setText(objBean.getQuestion());
        vh.tv2.setText(objBean.getAnswer());

        return convertView;
    }

    static class ViewHolder {
        TextView tv1, tv2;
    }
}


