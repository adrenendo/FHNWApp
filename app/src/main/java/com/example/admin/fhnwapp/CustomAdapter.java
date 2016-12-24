package com.example.admin.fhnwapp;

import android.widget.BaseAdapter;

/**
 * Created by admin on 17.12.2016.
 */

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
    private Context mContext;
    private List<FAQEntry> faqEntryList;
    private LayoutInflater menuInflator;

    public CustomAdapter(Context context, List<FAQEntry> itemList) {
        mContext = context;
        faqEntryList = itemList;
        menuInflator = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return faqEntryList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = menuInflator.inflate(R.layout.row_faq, parent, false);
            vh.tv1 = (TextView) convertView.findViewById(R.id.textView1);
            vh.tv2 = (TextView) convertView.findViewById(R.id.textView2);
            convertView.setTag(vh);

        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        FAQEntry objBean = faqEntryList.get(position);
        vh.tv1.setText(objBean.getQuestion());
        vh.tv2.setText(objBean.getAnswer());

        return convertView;
    }

    static class ViewHolder {
        TextView tv1, tv2;
    }
}



