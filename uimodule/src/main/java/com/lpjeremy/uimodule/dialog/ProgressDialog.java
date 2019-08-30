package com.lpjeremy.uimodule.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lpjeremy.uimodule.R;
import com.lpjeremy.uimodule.view.LoadingImage;


/**
 * @desc:progressBar
 * @date:2018/6/4 9:03
 * @auther:lp
 * @version:1.0
 */
public class ProgressDialog extends BaseDialog {
    TextView mTxtProgressValue;
    ProgressBar mProgressBar;

    LoadingImage loadingImage;

    public ProgressDialog(@NonNull Context context) {
        super(context);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mTxtProgressValue = findViewById(R.id.txtProgressValue);
        mProgressBar = findViewById(R.id.progressBar);
        loadingImage = findViewById(R.id.progressLoadingImage);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.dialog_progress_layout;
    }

    @Override
    protected boolean getCancelable() {
        return false;
    }

    @Override
    public int getLayoutParamsWidth() {
        return ViewGroup.LayoutParams.WRAP_CONTENT;
    }

    /**
     * 显示progressbar
     *
     * @param toast 自定义文字
     */
    public void showProgressBar(String toast) {
        loadingImage.startLoading();
        if (TextUtils.isEmpty(toast)) {
            mTxtProgressValue.setVisibility(View.GONE);
        } else {
            mTxtProgressValue.setText(toast);
            mTxtProgressValue.setVisibility(View.VISIBLE);
        }
        show();
    }

    public void setmTxtProgressValue(String value) {
        if (mTxtProgressValue != null) {
            mTxtProgressValue.setText(value);
        }
    }
}
