package com.liusong.widget.ui.activity.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.liusong.widget.R;
import com.liusong.widget.adapter.recyclerview.RvSingleTextAdapter;
import com.liusong.widget.listener.RvItemTouchListener;
import com.liusong.widget.widget.decoration.DividerListItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * 展示：
 * RecyclerView垂直列表样式，四周无分割线，有点击和长按事件
 * LinearLayoutManager: 列表水平显示样式，需要向左滑动才能看到下一列.
 * Created by liusong on 2016/10/10.
 */

public class RvDividerListItemActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerViewHorizontal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_divider_list_item);
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

        //--------------------------------------------------------
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置item增加或移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置自定义分割线
        mRecyclerView.addItemDecoration(new DividerListItemDecoration(this, DividerListItemDecoration.VERTICAL_LIST));
    }

    private void initListener() {
        //点击事件方法一：
        mRecyclerView.addOnItemTouchListener(new RvItemTouchListener(this, mRecyclerView, new RvItemTouchListener.OnClickEventListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(RvDividerListItemActivity.this,"click->"+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(RvDividerListItemActivity.this,"longClick->"+position, Toast.LENGTH_SHORT).show();
            }
        }));
    }

    private void initData() {
        //水平RecyclerView
        List<String> dataX = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            dataX.add("" + (char) i);
        }
        RvSingleTextAdapter adapterX=new RvSingleTextAdapter(dataX);
        mRecyclerViewHorizontal.setAdapter(adapterX);
        //-------------------------------------------
        List<String> data = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            data.add("" + (char) i);
        }
        RvSingleTextAdapter adapter=new RvSingleTextAdapter(data);

        //点击事件方法二：
//        adapter.setOnItemClickListener(new RvSingleTextAdapter.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(View view, int position) {
//                Toast.makeText(RvDividerListItemActivity.this,"click->"+position,Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onItemLongClick(View view, int position) {
//                Toast.makeText(RvDividerListItemActivity.this,"longClick->"+position,Toast.LENGTH_SHORT).show();
//            }
//        });

        mRecyclerView.setAdapter(adapter);
    }
}
