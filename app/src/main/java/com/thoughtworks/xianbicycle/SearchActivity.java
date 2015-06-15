package com.thoughtworks.xianbicycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {

    public static final String SEARCH_FILTER_START = "search_start";
    
    @InjectView(R.id.search_start)
    EditText mSearchStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.search_button)
    void search() {
        String searchStartLocation = mSearchStart.getText().toString();
        if (TextUtils.isEmpty(searchStartLocation)) {
            Toast.makeText(this, getString(R.string.msg_empty_input), Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra(SEARCH_FILTER_START, searchStartLocation);
        startActivity(intent);
    }
}
