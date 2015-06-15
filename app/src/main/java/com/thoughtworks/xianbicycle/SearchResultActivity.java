package com.thoughtworks.xianbicycle;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xianbicycle.model.response.BicycleSet;
import com.thoughtworks.xianbicycle.utils.FileUtil;
import com.thoughtworks.xianbicycle.view.SearchResultAdapater;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SearchResultActivity extends AppCompatActivity {

    @InjectView(R.id.listview)
    ListView mListView;
    private SearchResultAdapater mSearchResultAdapater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ButterKnife.inject(this);

        loadData(getIntent().getStringExtra(SearchActivity.SEARCH_FILTER_START));
        mSearchResultAdapater = new SearchResultAdapater(this);
        mListView.setAdapter(mSearchResultAdapater);
    }

    private void loadData(final String searchStart) {
        new AsyncTask<Void, Void, List<BicycleSet>>() {

            @Override
            protected List<BicycleSet> doInBackground(Void... params) {
                String result = FileUtil.readRawTextFile(SearchResultActivity.this, R.raw.result);
                return new Gson().fromJson(result, new TypeToken<List<BicycleSet>>(){}.getType());
            }

            @Override
            protected void onPostExecute(List<BicycleSet> list) {
                mSearchResultAdapater.setResult(list);
                mSearchResultAdapater.notifyDataSetChanged();
            }
        }.execute();
    }
}
