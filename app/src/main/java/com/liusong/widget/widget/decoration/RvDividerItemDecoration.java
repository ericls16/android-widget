package com.liusong.widget.widget.decoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

/**
 * RecyclerView分割线(？？？)
 * Created by liusong on 2016/10/19.
 */

public class RvDividerItemDecoration extends RecyclerView.ItemDecoration {

    /* 分割线类型 */
    //垂直列表分割线
    public static final int DIVIDER_LIST_VERTICAL = 0x01; //垂直列表分割线：四周没有线条.
    public static final int DIVIDER_LIST_VERTICAL_BOTTOM = 0x02; //垂直列表分割线：有最下面，没有最上面、左侧和右侧.
    public static final int DIVIDER_LIST_VERTICAL_TOP_BOTTOM = 0x03; //垂直列表分割线：有最上面、最下面，没有左侧和右侧.
    public static final int DIVIDER_LIST_VERTICAL_AROUND = 0x04; //垂直列表分割线：四周都有.
    public static final int DIVIDER_LIST_VERTICAL_LEFT_RIGHT = 0x05; //垂直列表分割线：有左侧和右侧,没有上面和下面.
    //水平网格分割线
    public static final int DIVIDER_GRID = 0x11; //水平网格分割线：没有最上面、最下面、左侧和右侧.
    public static final int DIVIDER_GRID_TOP_BOTTOM = 0x12; //水平网格分割线：有最上面和最下面，没有左侧和右侧.
    public static final int DIVIDER_GRID_AROUND = 0x13; //水平网格分割线：四周都有.

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    private Drawable mDivider; //分割线
    private int mDividerType; //分割线类型

    public RvDividerItemDecoration(@NonNull Context context, @NonNull int dividerType) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        setDividerType(dividerType);
    }

    /**
     * @param dividerType
     * @function 设置分割线类型
     * @desc 如果指定类型不合法，则抛出异常
     */
    public void setDividerType(int dividerType) {
        if (dividerType != DIVIDER_LIST_VERTICAL &&
                dividerType != DIVIDER_LIST_VERTICAL_BOTTOM &&
                dividerType != DIVIDER_LIST_VERTICAL_TOP_BOTTOM &&
                dividerType != DIVIDER_LIST_VERTICAL_AROUND &&
                dividerType != DIVIDER_GRID &&
                dividerType != DIVIDER_GRID_TOP_BOTTOM &&
                dividerType != DIVIDER_GRID_AROUND) {
            throw new IllegalArgumentException("invalid divider type");
        }
        mDividerType = dividerType;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mDividerType == DIVIDER_LIST_VERTICAL_BOTTOM || mDividerType == DIVIDER_LIST_VERTICAL_TOP_BOTTOM) {
            drawVertical(c, parent);
        } else if (mDividerType == DIVIDER_LIST_VERTICAL_AROUND) {
            drawVertical(c, parent);
            drawHorizontal(c, parent);
        } else if (mDividerType == DIVIDER_GRID) {
            drawHorizontal(c, parent);
            drawVertical(c, parent);
        } else if (mDividerType == DIVIDER_GRID_TOP_BOTTOM) {

        } else if (mDividerType == DIVIDER_GRID_AROUND) {

        }
    }

    /**
     * 设置指定itemview的paddingLeft，paddingTop， paddingRight， paddingBottom
     * 此方法主要是为了在每个Item的某一位置预留出分割线的空间 ，从而让Decoration绘制在预留的空间内
     *
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mDividerType == DIVIDER_LIST_VERTICAL_BOTTOM || mDividerType == DIVIDER_LIST_VERTICAL_TOP_BOTTOM) {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else if (mDividerType == DIVIDER_LIST_VERTICAL_AROUND) {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicHeight());
        } else if (mDividerType == DIVIDER_GRID) {
            int itemPosition = parent.getChildAdapterPosition(view);
            int spanCount = getSpanCount(parent);
            if ((itemPosition + 1) % spanCount == 0) {
                // 如果是最后一列，则不需要绘制右边
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            } else {
                outRect.set(0, 0, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicHeight());
            }
        } else if (mDividerType == DIVIDER_GRID_TOP_BOTTOM) {

        } else if (mDividerType == DIVIDER_GRID_AROUND) {

        }
    }

    /**
     * 画横线分割线
     * # item分割线:应该在parent的padding内,在item自己的margin外,取相交线.
     *
     * @param c
     * @param parent
     */
    public void drawVertical(Canvas c, RecyclerView parent) {
        int left = 0;
        int right = 0;
        int top = 0;
        int bottom = 0;

        //itemView的总数
        final int childCount = parent.getChildCount();
        //遍历每个itemView，画垂直方向的横线分割线
        for (int i = 0; i < childCount; i++) {
            //拿到itemView
            final View child = parent.getChildAt(i);
            //拿到itemView的参数
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            if (mDividerType != DIVIDER_GRID) {
                //left和right固定，分别为itemView最左边和最右边.
                left = parent.getPaddingLeft();
                right = parent.getWidth() - parent.getPaddingRight();
                if (i == 0 && (mDividerType == DIVIDER_LIST_VERTICAL_TOP_BOTTOM || mDividerType == DIVIDER_LIST_VERTICAL_AROUND)) {
                    /**
                     * 上方横线分割线
                     * # item最顶部分割线Y方向位置 = item上边界位置+边界Y偏移量(偏移Y一般会加到parent的padding里).
                     * # child.getTop()=item顶部边界距离parent顶部边界的相对位置=parent.getPaddingTop()+params.topMargin.
                     */
                    Log.e("PING", "---" + parent.getPaddingTop());
                    top = child.getTop() + Math.round(ViewCompat.getTranslationY(child));
                    //分割线的底部位置=item的底部位置+分割线的高度.
                    bottom = top + mDivider.getIntrinsicHeight();
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(c);
                }

                //item下方横线分割线
                top = child.getBottom() + Math.round(ViewCompat.getTranslationY(child));
                bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }

            if (mDividerType == DIVIDER_GRID) {
                left = child.getLeft() + Math.round(ViewCompat.getTranslationX(child));
                right = child.getRight() + mDivider.getIntrinsicWidth();

                top = child.getBottom() + Math.round(ViewCompat.getTranslationX(child));
                bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }

    /**
     * 画竖直分割线
     *
     * @param c
     * @param parent
     */
    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = 0;
            int right = 0;

            if (mDividerType != DIVIDER_GRID) {
                //左侧垂直分割线
                left = child.getLeft() + params.leftMargin;
                right = left + mDivider.getIntrinsicWidth();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
                //右侧垂直分割线
                left = child.getRight() + params.rightMargin;
                right = left + mDivider.getIntrinsicWidth();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }

            if (mDividerType == DIVIDER_GRID) {
                if ((i + 1) % getSpanCount(parent) != 0) {
                    final int top2 = child.getTop() - params.topMargin;
                    final int bottom2 = child.getBottom() + params.bottomMargin;
                    //右侧垂直分割线
                    left = child.getRight() + params.rightMargin;
                    right = left + mDivider.getIntrinsicWidth();
                    mDivider.setBounds(left, top2, right, bottom2);
                    mDivider.draw(c);
                }
            }

        }
    }

    /**
     * 计算列数
     *
     * @param parent
     * @return
     */
    private int getSpanCount(RecyclerView parent) {
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }
}
