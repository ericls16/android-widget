package com.liusong.widget.ui.activity.coordinatorlayout;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;

import com.liusong.widget.R;
import com.liusong.widget.adapter.RvSingleTextAdapter;
import com.liusong.widget.databinding.ActivityBehaviorPullHideBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liu song on 2017/4/26.
 */

public class BehaviorPullHideActivity extends AppCompatActivity {
    private ActivityBehaviorPullHideBinding mBinding;

    private List<String> data = new ArrayList<String>();
    private RvSingleTextAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this, R.layout.activity_behavior_pull_hide);
        initView();
        initData();
    }

    private void initView() {
        mBinding.rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        adapter=new RvSingleTextAdapter(data);
        mBinding.rv.setAdapter(adapter);
    }

    private void initData() {
        for (int i = 'A'; i < 'z'; i++) {
            data.add("" + (char) i);
        }
        adapter.notifyDataSetChanged();
    }
}
