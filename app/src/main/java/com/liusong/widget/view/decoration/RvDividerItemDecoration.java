package com.liusong.widget.view.decoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
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

    /**分割线类型**/
    public static final int LIST = 1001; //默认分割线：有最下面，没有最上面、左侧和右侧.
    public static final int LIST_VERTICAL_ALL = 1002; //分割线：有最上面和最下面，没有左侧和右侧.
    public static final int LIST_ALL = 1004; //分割线：四周都有.
    public static final int GRID = 2001; //分割线：没有最上面、最下面、左侧和右侧.
    public static final int GRID_VERTICAL_ALL = 2002; //分割线：有最上面和最下面，没有左侧和右侧.
    public static final int GRID_ALL = 2004; //分割线：分割线：四周都有.

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    private Drawable mDivider;
    private int mDividerType;

    public RvDividerItemDecoration(Context context, int dividerType) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        setDividerType(dividerType);
    }


    public void setDividerType(int dividerType) {
        if (dividerType != LIST && dividerType != LIST_VERTICAL_ALL && dividerType!=LIST_ALL &&
                dividerType != GRID && dividerType != GRID_VERTICAL_ALL && dividerType != GRID_ALL) {
            throw new IllegalArgumentException("invalid divider type");
        }
        mDividerType=dividerType;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mDividerType == LIST ||mDividerType==LIST_VERTICAL_ALL) {
            drawVertical(c, parent);
        }else if(mDividerType==LIST_ALL) {
            drawVertical(c, parent);
            drawHorizontal(c, parent);
        }else if(mDividerType==GRID) {
            drawHorizontal(c, parent);
            drawVertical(c, parent);
        }else if(mDividerType==GRID_VERTICAL_ALL) {

        }else if(mDividerType==GRID_ALL) {

        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mDividerType == LIST || mDividerType==LIST_VERTICAL_ALL) {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        }else if(mDividerType==LIST_ALL) {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicHeight());
        }else if(mDividerType==GRID) {
            int itemPosition = parent.getChildAdapterPosition(view);
            int spanCount = getSpanCount(parent);
            if ((itemPosition+1)%spanCount==0) {
                // 如果是最后一列，则不需要绘制右边
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            } else {
                outRect.set(0, 0, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicHeight());
            }
        }else if(mDividerType==GRID_VERTICAL_ALL) {

        }else if(mDividerType==GRID_ALL) {

        }
    }

    /**
     * 画横线分割线
     * # item分割线:应该在parent的padding内,在item自己的margin外,取相交线.
     * @param c
     * @param parent
     */
    public void drawVertical(Canvas c, RecyclerView parent) {
        int left =0;
        int right=0;
        int top =0;
        int bottom=0;

        //itemView的总数
        final int childCount = parent.getChildCount();
        //遍历每个itemView，画垂直方向的横线分割线
        for (int i = 0; i < childCount; i++) {
            //拿到itemView
            final View child = parent.getChildAt(i);
            //拿到itemView的参数
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            if(mDividerType!=GRID) {
                //left和right固定，分别为itemView最左边和最右边.
                left = parent.getPaddingLeft();
                right = parent.getWidth() - parent.getPaddingRight();
                if (i == 0 && (mDividerType == LIST_VERTICAL_ALL || mDividerType == LIST_ALL)) {
                    /**
                     * 上方横线分割线
                     * # item最顶部分割线Y方向位置 = item上边界位置+边界Y偏移量(偏移Y一般会加到parent的padding里).
                     * # child.getTop()=item顶部边界距离parent顶部边界的相对位置=parent.getPaddingTop()+params.topMargin.
                     */
                    Log.e("PING","---"+parent.getPaddingTop());
                    top = child.getTop()+ Math.round(ViewCompat.getTranslationY(child));
                    //分割线的底部位置=item的底部位置+分割线的高度.
                    bottom = top + mDivider.getIntrinsicHeight();
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(c);
                }

                //item下方横线分割线
                top = child.getBottom()+ Math.round(ViewCompat.getTranslationY(child));
                bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }

            if(mDividerType==GRID){
                left = child.getLeft()+ Math.round(ViewCompat.getTranslationX(child)) ;
                right = child.getRight()+mDivider.getIntrinsicWidth();

                top = child.getBottom() + Math.round(ViewCompat.getTranslationX(child)) ;
                bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }

    /**
     * 画竖直分割线
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

            if(mDividerType!=GRID){
                //左侧垂直分割线
                left = child.getLeft() + params.leftMargin;
                right = left + mDivider.getIntrinsicWidth();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
                //右侧垂直分割线
                left = child.getRight()+params.rightMargin;
                right = left+mDivider.getIntrinsicWidth();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }

            if(mDividerType==GRID){
               if((i+1)%getSpanCount(parent)!=0) {
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
