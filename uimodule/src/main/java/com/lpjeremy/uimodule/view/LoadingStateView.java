package com.lpjeremy.uimodule.view;

import android.content.Context;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lpjeremy.uimodule.R;

/**
 * @desc:加载状态展示view
 * @date:2019/2/11 10:48
 * @auther:lp
 * @version:1.0
 */
public class LoadingStateView extends LinearLayout {
    ImageView mImageView;
    TextView mTextView;

    public LoadingStateView(Context context) {
        super(context);
    }

    public LoadingStateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingStateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mImageView = findViewById(R.id.imageView);
        mTextView = findViewById(R.id.textView);
        hide();
    }

    /**
     * 加载成功
     */
    public void hide() {
        setVisibility(GONE);
    }

    /**
     * 加载失败，默认提示文本，无提示图片
     */
    public void onLoadFail() {
        onLoadFail(0, "加载失败！！！");
    }

    /**
     * 加载失败，设置提示文本，无提示图片
     * @param failToast
     */
    public void onLoadFail(String failToast) {
        onLoadFail(0, failToast);
    }

    /**
     * 加载失败，设置提示图片和文本
     *
     * @param resId     提示图片
     * @param failToast 提示文本
     */
    public void onLoadFail(int resId, String failToast) {
        setVisibility(VISIBLE);
        if (resId > 0) {
            mImageView.setVisibility(VISIBLE);
            mImageView.setImageResource(resId);
        } else {
            mImageView.setVisibility(GONE);
        }
        if (TextUtils.isEmpty(failToast)) {
            mTextView.setText("加载失败！！！");
        } else {
            mTextView.setText(failToast);
        }
    }
}
