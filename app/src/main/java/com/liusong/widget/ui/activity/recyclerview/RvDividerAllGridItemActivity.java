package com.liusong.widget.ui.activity.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.liusong.widget.R;
import com.liusong.widget.adapter.RvSingleTextAdapter;
import com.liusong.widget.view.decoration.DividerAllGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * 展示：RecyclerView网格样式，四周有分割线。
 * Created by liusong on 2016/10/18.
 */

public class RvDividerAllGridItemActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_divider_all_grid_item);
        initView();
        initData();
    }

    private void initView() {
        //gridview样式的布局
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerAllGridItemDecoration(this));
    }

    private void initData() {
        List<String> data = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            data.add("" + (char) i);
        }

        for (int i = 'A'; i < 'z'; i++) {
            data.add("" + (char) i);
        }

        RvSingleTextAdapter adapter=new RvSingleTextAdapter(data);
        mRecyclerView.setAdapter(adapter);
    }
}
