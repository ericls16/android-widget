package com.liusong.widget;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.liusong.widget.databinding.ActivityConstraintLayoutBinding;

/**
 * Created by liu song on 2017/4/12.
 */

public class ConstraintLayoutActivity extends AppCompatActivity {
    private ActivityConstraintLayoutBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_constraint_layout);
    }
}
