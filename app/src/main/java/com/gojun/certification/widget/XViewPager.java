package com.gojun.certification.widget;

import android.content.Context;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Porster on 2018/1/18.
 */

public class XViewPager extends ViewPager {

    boolean canScroll;

    public XViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCanScroll(true);
    }

    public void setCanScroll(boolean canScroll) {
        this.canScroll = canScroll;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!canScroll) {
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!canScroll) {
            return false;
        }
        return super.onTouchEvent(ev);
    }
}
