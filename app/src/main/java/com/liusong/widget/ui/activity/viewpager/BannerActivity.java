package com.liusong.widget.ui.activity.viewpager;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.liusong.widget.R;
import com.zhy.magicviewpager.transformer.AlphaPageTransformer;

public class BannerActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private PagerAdapter mAdapter;

    int[] imgRes = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        mViewPager.setPageMargin(40);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mAdapter = new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView view = new ImageView(BannerActivity.this);
                view.setScaleType(ImageView.ScaleType.FIT_XY);
                view.setImageResource(imgRes[position]);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public int getCount() {
                return imgRes.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object o) {
                return view == o;
            }
        });
        mViewPager.setPageTransformer(true, new AlphaPageTransformer()); //????弘扬的库
    }

}
