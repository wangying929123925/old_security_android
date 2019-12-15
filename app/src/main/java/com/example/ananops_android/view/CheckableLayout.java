package com.example.ananops_android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Checkable;

public class CheckableLayout extends RelativeLayout implements Checkable {
    private static final int[] CHECKED_STATE_SET = {android.R.attr.state_checked};

    private boolean mChecked;
    /** @param context
     * @param attrs */
    public CheckableLayout(Context context, AttributeSet attrs){
        super(context,attrs);
    }
    @Override
    public void setChecked(boolean b) {
        if (b != mChecked){
            mChecked = b;
            refreshDrawableState();
            for (int i = 0, len = getChildCount(); i < len; i++) {
                View child = getChildAt(i);
                if(child instanceof Checkable){
                    ((Checkable) child).setChecked(b);
                }
            }
        }
    }

    @Override
    public boolean isChecked() {
        return false;
    }

    @Override
    public void toggle() {

    }

}
