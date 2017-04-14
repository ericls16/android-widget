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
import com.liusong.widget.view.decoration.DividerGridItemDecoration;
import com.liusong.widget.view.decoration.DividerListItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView
 * 1> LinearLayoutManager: 列表样式，需要向左滑动才能看到下一列.
 * 2> GridLayoutManager: 网格样式，四周无分割线包裹.
 * Created by liusong on 2016/10/10.
 */

public class RvDividerGridItemActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerViewHorizontal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_grid);
        initView();
        initListener();
        initData();
    }

    private void initView() {
        //1.水平RecyclerView
        mRecyclerViewHorizontal= (RecyclerView) findViewById(R.id.recycler_view_horizontal);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerViewHorizontal.setLayoutManager(linearLayoutManager);
        //设置item增加或移除动画
        mRecyclerViewHorizontal.setItemAnimator(new DefaultItemAnimator());
        //设置自定义分割线
        mRecyclerViewHorizontal.addItemDecoration(new DividerListItemDecoration(this, DividerListItemDecoration.HORIZONTAL_LIST));
        //-------------------------------------------------------------------------------------
        //2.gridview样式的布局
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
    }
    private void initListener() {
    }
    private void initData() {
        //水平RecyclerView
        List<String> data = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            data.add("" + (char) i);
        }
        RvSingleTextAdapter adapter=new RvSingleTextAdapter(data);
        mRecyclerViewHorizontal.setAdapter(adapter);
        //style
        List<String> data2 = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            data2.add("" + (char) i);
        }
        data2.add("X");
        RvSingleTextAdapter adapter2=new RvSingleTextAdapter(data2);
        mRecyclerView.setAdapter(adapter2);
    }
}
