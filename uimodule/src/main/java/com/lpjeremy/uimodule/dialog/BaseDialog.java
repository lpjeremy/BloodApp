package com.lpjeremy.uimodule.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.lpjeremy.uimodule.R;


/**
 * @desc:dialog的基类
 * @date:2018/5/25 14:42
 * @auther:lp
 * @version:1.0
 */

public abstract class BaseDialog extends Dialog{
    protected View dialogView;
    protected Context mContext;
    protected int pageIndex = 1;
    protected OnDialogCancelListener onDialogCancelListener;
    /**
     * 是否初始化过宽度
     */
    private boolean initedWidth = false;
    /**
     * 是否自适应宽度
     * true 自适应 false 设置宽度0
     */
    private boolean wrapWidth;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    private ProgressDialog progressBarDialog;

    public BaseDialog(@NonNull Context context, boolean wrap, OnDialogCancelListener onDialogCancelListener) {
        super(context, R.style.dialog);
        this.onDialogCancelListener = onDialogCancelListener;
        wrapWidth = wrap;
        init();
    }

    public BaseDialog(@NonNull Context context) {
        super(context, R.style.dialog);
        wrapWidth = true;
        init();
    }

    public BaseDialog(@NonNull Context context, boolean wrap) {
        super(context, R.style.dialog);
        wrapWidth = wrap;
        init();
    }

    private void init() {
        mContext = getContext();
        dialogView = getLayoutInflater().inflate(getContentLayoutId(), null);
        dialogView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard();
            }
        });
        setContentView(dialogView);
//        setContentView(dialogView, new ViewGroup.LayoutParams(getLayoutParamsWidth() != 0 ? getLayoutParamsWidth() : ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setCancelable(getCancelable());
    }

    public int getLayoutParamsWidth() {
        return 0;
    }

    protected abstract int getContentLayoutId();

    /**
     * 是否可取消 true可取消 false不可取消
     *
     * @return
     */
    protected abstract boolean getCancelable();


    /**
     * 获取屏幕宽度 单位：px
     *
     * @param context
     * @return
     */
    private int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    private int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    @Override
    public void show() {
        super.show();
        if (!wrapWidth) {
            initWidth();
        }
    }

    private void initWidth() {
        // 将对话框的大小按屏幕大小的百分比设置
//        WindowManager windowManager = getWindow().getWindowManager();
//        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = getWidth() > 0 ? getWidth() : (int) (getScreenWidth(getContext()) * getWidthProportion()); //设置宽度
        lp.height = heightIsWrapContent() ? ViewGroup.LayoutParams.WRAP_CONTENT : (getHeight() > 0 ? getHeight() : (int) (getScreenHeight(getContext()) * getHeightProportion()));//设置高度
        getWindow().setAttributes(lp);
    }

    /**
     * 获取dialog在屏幕中显示的宽度的屏幕的宽度的比例
     *
     * @return 默认0.8 即显示80%
     */
    protected float getWidthProportion() {
        return 0.8f;
    }

    protected float getHeightProportion() {
        return 0.8f;
    }

    protected int getHeight() {
        return 0;
    }

    protected int getWidth() {
        return 0;
    }

    /**
     * 高度是否自适应
     *
     * @return
     */
    protected boolean heightIsWrapContent() {
        return true;
    }

    protected void showToast(final CharSequence msg) {
        if (TextUtils.isEmpty(msg)) return;
        Toast.makeText(mContext, msg.toString(), Toast.LENGTH_SHORT).show();
    }

    /**
     * 取消dialog的监听
     */
    public interface OnDialogCancelListener {
        void onCancel();
    }

    /**
     * 显示数据加载中progressbar
     */
    public void showLoadingProgressBar() {
        showProgressBar(mContext.getString(R.string.data_loading));
    }

    /**
     * 显示“操作中...”progressbar
     */
    public void showOperationProgressBar() {
        showProgressBar(mContext.getString(R.string.operation_toast));
    }

    public void showProgressBar(final String toast) {
        if (progressBarDialog == null)
            progressBarDialog = new ProgressDialog(mContext);
        if (!progressBarDialog.isShowing()) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    progressBarDialog.showProgressBar(toast);
                }
            });

        } else {
            progressBarDialog.setmTxtProgressValue(toast);
        }
    }

    public void hideProgressBar() {
        if (progressBarDialog != null && progressBarDialog.isShowing())
            progressBarDialog.dismiss();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("BaseDialog", "  super.onStop();");

    }

    /**
     * 隐藏键盘
     */
    public void hideKeyBoard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    /**
     * 重写onTouch
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isShowing() && shouldCloseOnTouch(getContext(), event)) {
            hideKeyBoard();
        }
        return super.onTouchEvent(event);
    }

    public boolean shouldCloseOnTouch(Context context, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN
                && isOutOfBounds(context, event) && getWindow().peekDecorView() != null) {
            return true;
        }
        return false;
    }

    private boolean isOutOfBounds(Context context, MotionEvent event) {
        final int x = (int) event.getX();
        final int y = (int) event.getY();
        final int slop = ViewConfiguration.get(context).getScaledWindowTouchSlop();
        final View decorView = getWindow().getDecorView();
        return (x < -slop) || (y < -slop)
                || (x > (decorView.getWidth() + slop))
                || (y > (decorView.getHeight() + slop));
    }

}