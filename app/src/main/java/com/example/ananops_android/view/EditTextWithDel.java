package com.example.ananops_android.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.example.ananops_android.R;


public class EditTextWithDel extends android.support.v7.widget.AppCompatEditText {
    private final static String TAG = "EditTextWithDel";
    private Drawable imgLeft,imgAble;
    private Context mContext;
    public EditTextWithDel(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public EditTextWithDel(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        init();
    }

    public EditTextWithDel(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

        private void init() {
            imgLeft = mContext.getResources().getDrawable(
                    R.drawable.ic_search1);
            imgAble = mContext.getResources().getDrawable(
                    R.drawable.ic_delete_gray);
            addTextChangedListener(new TextWatcher() {
                @Override
                public void onTextChanged(CharSequence s, int start, int before,
                                          int count) {
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    setDrawable();
                }
            });
            setDrawable();
        }

        // 设置删除图片
        private void setDrawable() {
            if (length() < 1) {
                setCompoundDrawablesWithIntrinsicBounds(imgLeft, null, null, null);
            } else {
                setCompoundDrawablesWithIntrinsicBounds(imgLeft, null, imgAble, null);
            }
        }
    // 处理删除事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (imgAble != null && event.getAction() == MotionEvent.ACTION_UP) {
            int eventX = (int) event.getRawX();
            int eventY = (int) event.getRawY();
            Log.e(TAG, "eventX = " + eventX + "; eventY = " + eventY);
            Rect rect = new Rect();
            getGlobalVisibleRect(rect);
            rect.left = rect.right - 50;
            if (rect.contains(eventX, eventY))
                setText("");
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
    }

