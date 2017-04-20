package com.liusong.widget.ui.activity.coordinatorlayout;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.liusong.widget.R;
import com.liusong.widget.databinding.ActivityClOneBinding;

/**
 * Created by liu song on 2017/4/20.
 */

public class ClOneActivity extends AppCompatActivity {
    private ActivityClOneBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this, R.layout.activity_cl_one);
    }
}
