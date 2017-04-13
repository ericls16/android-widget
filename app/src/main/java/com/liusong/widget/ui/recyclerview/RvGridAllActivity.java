package com.liusong.widget.ui.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.liusong.widget.R;
import com.liusong.widget.adapter.RvGeneralAdapter;
import com.liusong.widget.view.decoration.DividerGridAllDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView
 * 1> 网格样式布局，四周加上分割线包裹
 *
 * Created by VIC1 on 2016/10/18.
 */

public class RvGridAllActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_grid_all);
        initView();
        initData();
    }

    private void initView() {
        //gridview样式的布局
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerGridAllDecoration(this));
    }

    private void initData() {
        List<String> data = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            data.add("" + (char) i);
        }

        for (int i = 'A'; i < 'z'; i++) {
            data.add("" + (char) i);
        }

        RvGeneralAdapter adapter=new RvGeneralAdapter(data);
        mRecyclerView.setAdapter(adapter);
    }
}
