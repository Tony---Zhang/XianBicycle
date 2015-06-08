package com.thoughtworks.xianbicycle.view;

import android.view.View;
import android.widget.TextView;

import com.thoughtworks.xianbicycle.R;
import com.thoughtworks.xianbicycle.model.response.BicycleSet;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SearchResultItemHolder {

    @InjectView(R.id.bicycle_name)
    TextView mName;
    @InjectView(R.id.bicycle_street)
    TextView mStreet;

    public SearchResultItemHolder(View view) {
        ButterKnife.inject(this, view);
    }

    public void populate(BicycleSet bicycleSet) {
        mName.setText(bicycleSet.getSitename());
        mStreet.setText(bicycleSet.getLocation());
    }
}
