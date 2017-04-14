package com.liusong.widget.ui.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.liusong.widget.R;


/**
 * Created by VIC1 on 2016/10/10.
 */

public class RvMainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_main);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_list:
                startActivity(new Intent(this,RvDividerListItemActivity.class));
                break;
            case R.id.btn_list_all:
                startActivity(new Intent(this,RvDividerAllListItemActivity.class));
                break;
            case R.id.btn_grid:
                startActivity(new Intent(this,RvDividerGridItemActivity.class));
                break;
            case R.id.btn_grid_all:
                startActivity(new Intent(this,RvDividerAllGridItemActivity.class));
                break;
            case R.id.btn_divider:
                startActivity(new Intent(this,RvDividerActivity.class));
                break;
            default:
                break;
        }
    }
}
