package com.liusong.widget;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintSet;
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

        setPropertyByCode();
    }

    /**
     * @desciption 在代码中设置约束布局的属性
     * @annotation ConstraintSet 是用来通过代码管理布局属性的集合对象;
     * 可以通过这个类来创建各种布局约束，然后把创建好的布局约束应用到一个 ConstraintLayout 上；
     */
    private void setPropertyByCode() {
        ConstraintSet cs = new ConstraintSet();

        //手工创建
//        cs.connect(...);
        //从 R.layout.* 对象获取
//        cs.clone(this,R.layout.activity_constraint_layout);
        //从 ConstraintLayout 中获取
        cs.clone(mBinding.constraintLayout);

        //设置属性
        cs.centerHorizontally(R.id.button, R.id.imageView);

        //通过applyTo函数将设置应用到ConstraintLayout上
        cs.applyTo(mBinding.constraintLayout);
    }
}
