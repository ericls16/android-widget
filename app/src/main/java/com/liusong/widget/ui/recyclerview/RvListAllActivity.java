package com.liusong.widget.ui.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.liusong.widget.R;
import com.liusong.widget.adapter.RvSingleTextAdapter;
import com.liusong.widget.view.decoration.DividerAllListItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * 展示：RecyclerView垂直列表，四周都有分割线
 * Created by liusong on 2016/10/19.
 */

public class RvListAllActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_list_all);
        initView();
        initData();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerAllListItemDecoration(this));
    }

    private void initData() {
        List<String> data = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            data.add("" + (char) i);
        }
        RvSingleTextAdapter adapter=new RvSingleTextAdapter(data);
        mRecyclerView.setAdapter(adapter);
    }
}
