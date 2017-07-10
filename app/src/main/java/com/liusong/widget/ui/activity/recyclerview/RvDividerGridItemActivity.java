package com.liusong.widget.ui.activity.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.liusong.widget.R;
import com.liusong.widget.adapter.recyclerview.RvSingleTextAdapter;
import com.liusong.widget.widget.decoration.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView
 * GridLayoutManager: 网格样式，四周无分割线包裹.
 * Created by liusong on 2016/10/10.
 */

public class RvDividerGridItemActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_divider_grid_item);
        initView();
        initListener();
        initData();
    }

    private void initView() {

        //-------------------------------------------------------------------------------------
        //2.gridview样式的布局
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
    }

    private void initListener() {
    }

    private void initData() {

        //style
        List<String> data2 = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            data2.add("" + (char) i);
        }
        data2.add("X");
        RvSingleTextAdapter adapter2 = new RvSingleTextAdapter(data2);
        mRecyclerView.setAdapter(adapter2);
    }
}
