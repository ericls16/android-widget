package com.liusong.widget.listener;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by VIC1 on 2016/10/10.
 */

public class RvItemTouchListener implements RecyclerView.OnItemTouchListener {
    private GestureDetector gestureDetector;
    private OnClickEventListener onClickEventListener;

    public RvItemTouchListener(Context context, final RecyclerView recyclerView, final OnClickEventListener onClickEventListener) {
        this.onClickEventListener = onClickEventListener;

        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && onClickEventListener != null) {
                    onClickEventListener.onLongClick(child, recyclerView.getChildPosition(child));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child != null && onClickEventListener != null && gestureDetector.onTouchEvent(e)) {
            onClickEventListener.onClick(child, rv.getChildPosition(child));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public interface OnClickEventListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }
}
