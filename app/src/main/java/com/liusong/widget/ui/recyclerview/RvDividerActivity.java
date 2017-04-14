package com.liusong.widget.ui.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.liusong.widget.R;
import com.liusong.widget.adapter.RvSingleTextAdapter;
import com.liusong.widget.view.decoration.RecyclerViewItemLine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VIC1 on 2016/10/19.
 */

public class RvDividerActivity extends AppCompatActivity {
    private RecyclerView mListRecyclerView;
    private RecyclerView mGridRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_divider);
        initView();
        initData();
    }

    private void initView() {
        mListRecyclerView = (RecyclerView) findViewById(R.id.list);
        mListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mListRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mListRecyclerView.addItemDecoration(new RecyclerViewItemLine(this,RecyclerViewItemLine.LIST));
        mListRecyclerView.addItemDecoration(new RecyclerViewItemLine(this,RecyclerViewItemLine.LIST_VERTICAL_ALL));
//        mListRecyclerView.addItemDecoration(new RecyclerViewItemLine(this,RecyclerViewItemLine.LIST_ALL));
        //-------------------------------
        mGridRecyclerView = (RecyclerView) findViewById(R.id.grid);
        mGridRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        mGridRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mGridRecyclerView.addItemDecoration(new RecyclerViewItemLine(this,RecyclerViewItemLine.GRID));
    }

    private void initData() {
        List<String> data = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            data.add("" + (char) i);
        }
        RvSingleTextAdapter adapter=new RvSingleTextAdapter(data);
        //-----------------------------------
        mListRecyclerView.setAdapter(adapter);
        mGridRecyclerView.setAdapter(adapter);
    }
}
