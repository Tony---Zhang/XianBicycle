package com.thoughtworks.xianbicycle;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xianbicycle.model.response.BicycleSet;
import com.thoughtworks.xianbicycle.utils.FileUtil;
import com.thoughtworks.xianbicycle.view.SearchResultAdapater;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SearchResultActivity extends AppCompatActivity {

    public static final int UPDATE_LISTVIEW = 1;

    @InjectView(R.id.listview)
    ListView mListView;
    private SearchResultAdapater mSearchResultAdapater;
    private List<BicycleSet> resultList;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_LISTVIEW:
                    mSearchResultAdapater.setResult(resultList);
                    mSearchResultAdapater.notifyDataSetChanged();
                    Toast.makeText(SearchResultActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                    break;
                default:
                    super.handleMessage(msg);
                    break;
            }
        }
    };

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
        search0();
    }

    private void search0() {
        new AsyncTask<Void, Void, List<BicycleSet>>() {

            @Override
            protected List<BicycleSet> doInBackground(Void... params) {
                String result = FileUtil.readRawTextFile(SearchResultActivity.this, R.raw.result);
                return new Gson().fromJson(result, new TypeToken<List<BicycleSet>>() {
                }.getType());
            }

            @Override
            protected void onPostExecute(List<BicycleSet> bicycleSets) {
                mSearchResultAdapater.setResult(bicycleSets);
                mSearchResultAdapater.notifyDataSetChanged();
            }
        }.execute();
    }

    private void search1() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = FileUtil.readRawTextFile(SearchResultActivity.this, R.raw.result);
                resultList = new Gson().fromJson(result, new TypeToken<List<BicycleSet>>() {
                }.getType());

                SearchResultActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mSearchResultAdapater.setResult(resultList);
                        mSearchResultAdapater.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    private void search2() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = FileUtil.readRawTextFile(SearchResultActivity.this, R.raw.result);
                resultList = new Gson().fromJson(result, new TypeToken<List<BicycleSet>>() {
                }.getType());

                SearchResultActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mSearchResultAdapater.setResult(resultList);
                        mSearchResultAdapater.notifyDataSetChanged();
                    }
                });

                Message msg = new Message();
                msg.what = UPDATE_LISTVIEW;
                msg.arg1 = 123;
                msg.obj = "123456";

                mHandler.obtainMessage();
                msg.setTarget(mHandler);
                msg.sendToTarget();

//                mHandler.sendMessageDelayed(msg, 2000);
//                mHandler.sendEmptyMessage(UPDATE_LISTVIEW);
            }
        }).start();

    }

    private void search3() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = FileUtil.readRawTextFile(SearchResultActivity.this, R.raw.result);
                resultList = new Gson().fromJson(result, new TypeToken<List<BicycleSet>>() {
                }.getType());

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mSearchResultAdapater.setResult(resultList);
                        mSearchResultAdapater.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    private void search4() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = FileUtil.readRawTextFile(SearchResultActivity.this, R.raw.result);
                resultList = new Gson().fromJson(result, new TypeToken<List<BicycleSet>>() {
                }.getType());

                mListView.post(new Runnable() {
                    @Override
                    public void run() {
                        mSearchResultAdapater.setResult(resultList);
                        mSearchResultAdapater.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }
}
