package com.liusong.widget.ui.activity.coordinatorlayout;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.liusong.widget.R;
import com.liusong.widget.databinding.ActivityClMainBinding;

/**
 * Created by liu song on 2017/4/20.
 */

public class ClMainActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityClMainBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this, R.layout.activity_cl_main);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_one:
                startActivity(new Intent(this,ClOneActivity.class));
                break;
            default:
                break;
        }
    }
}
