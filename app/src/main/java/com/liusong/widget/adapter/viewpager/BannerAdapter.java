package com.liusong.widget.adapter.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


import com.liusong.widget.R;

import static android.content.ContentValues.TAG;

/**
 * 循环轮播 pagesize+2的方式
 * Created by liusong on 2017/7/10.
 */

public class BannerAdapter extends PagerAdapter {
    private Context mContext;
    private ViewPager mBanner;
    private LayoutInflater mInflater;

    private int[] mDatas;


    public BannerAdapter(Context context, ViewPager banner, int[] datas) {
        mContext = context;
        mBanner = banner;
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas.length + 2;
    }

    /**
     * 判断传进来的object是否是View
     *
     * @param view
     * @param o
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    /**
     * 一次装填两个页面，即加载当前页时下一个页面就加载好了
     *
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        /**
         *    这个position是当前页面的下一个页面的缓存，若是向左滑动，position=当前item-1反之则加一
         *    举个例子这个viewpager总共包含了imagesize+2=7个页面
         *    position=5时 position % ImageSize=0，切换到了第一个position
         */
        position %= mDatas.length;

        View view = mInflater.inflate(R.layout.item_vp_banner, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv);
        imageView.setImageResource(mDatas[position]);

        /*ImageView view = new ImageView(mContext);
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        view.setImageResource(mDatas[position]);*/

        final int pos = position;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点击item :" + pos, Toast.LENGTH_SHORT).show();
            }
        });

        container.addView(view);
        return view;
    }

    /**
     * 销毁页面
     *
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.d(TAG, "destroyItem%" + mBanner.getCurrentItem() + "position=" + position);
        container.removeView((View) object);
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        int position = mBanner.getCurrentItem();
        /**
         *  这里获得当前的positon然后对其setCurrentItem进行变换
         *  这里设置当position=0时把position设置为图片列表的最大值,即：第二次轮播的第一张图的位置
         *  是为了position=0时左滑显示最后一张，我举个例子这里ImageSize是5
         *  当position==0时设置为5，左滑就是position=4，也就是第五张图片。
         *
         *  if (position == (ImageSize+2) - 1)
         *  这个判断 (ImageSize+2)这个是给viewpager设置的页面数，这里是7
         *  当position==7-1=6时，这时viewpager就滑到头了，所以把currentItem设置为1
         *  这里设置为1还是为了能够左滑，这时左滑position=0又执行了第一个判断又设置为5，
         *  这样就实现了无限轮播的效果
         *  setCurrentItem(position,false);
         *  这里第二个参数false是消除viewpager设置item时的滑动动画，不理解的去掉它运行下就知道啥意思了
         *
         */
        Log.i(TAG, "finishUpdate: " + position);
        if (position == 0) {
            position = mDatas.length;
            mBanner.setCurrentItem(position, false);
        } else if (position == (mDatas.length + 2) - 1) {
            position = 1;
            mBanner.setCurrentItem(position, false);
        }
    }

}
