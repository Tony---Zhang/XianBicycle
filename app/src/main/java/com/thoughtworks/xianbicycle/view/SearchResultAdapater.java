package com.thoughtworks.xianbicycle.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.thoughtworks.xianbicycle.R;
import com.thoughtworks.xianbicycle.model.response.BicycleSet;

import java.util.ArrayList;
import java.util.List;

public class SearchResultAdapater extends BaseAdapter {

    private Context mContext;
    private List<BicycleSet> mListResult;

    public SearchResultAdapater(Context context) {
        this.mContext = context;
        this.mListResult = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mListResult.size();
    }

    @Override
    public Object getItem(int position) {
        return mListResult.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SearchResultItemHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.bicycle_item, parent, false);
            holder = new SearchResultItemHolder(convertView);
            convertView.setTag(holder);
        }
        holder = (SearchResultItemHolder) convertView.getTag();
        holder.populate((BicycleSet) getItem(position));
        return convertView;
    }

    public void setResult(List<BicycleSet> list) {
        this.mListResult = list;
    }
}
