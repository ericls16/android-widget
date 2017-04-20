package com.liusong.widget.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.liusong.widget.R;
import com.liusong.widget.databinding.ActivityMainBinding;
import com.liusong.widget.ui.activity.coordinatorlayout.ClMainActivity;
import com.liusong.widget.ui.activity.recyclerview.RvMainActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_coordinator_layout:
                startActivity(new Intent(this,ClMainActivity.class));
                break;
            case R.id.btn_constraint_layout:
                startActivity(new Intent(this,ConstraintLayoutActivity.class));
                break;
            case R.id.btn_rv_main:
                startActivity(new Intent(this,RvMainActivity.class));
                break;
            default:
                break;
        }
    }
}
