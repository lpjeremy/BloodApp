package com.lpjeremy.uimodule.dialog;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lpjeremy.uimodule.R;


/**
 * @desc:项目中用到的提示弹框
 * @date:2018/5/31 15:51
 * @auther:lp
 * @version:1.0
 */

public class ToastDialog extends BaseDialog {

    TextView mDialogTitle;
    TextView mDialogContent;
    TextView mDialogCancel;
    TextView mDialogOk;
    View mVLine;
    EditText mDialogEditText;

    private boolean dis = true;

    public ToastDialog(@NonNull Context context) {
        super(context);
        mDialogTitle = findViewById(R.id.dialog_title);
        mDialogContent = findViewById(R.id.dialog_content);
        mDialogCancel=findViewById(R.id.dialog_cancel);
        mDialogOk = findViewById(R.id.dialog_ok);
        mVLine = findViewById(R.id.vLine);
        mDialogEditText = findViewById(R.id.dialog_editText);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.dialog_toast_layout;
    }

    @Override
    protected boolean getCancelable() {
        return false;
    }

    @Override
    public void show() {
        if (getContext() instanceof Activity) {
            if (((Activity) getContext()).isFinishing()) {
                return;
            }
        }
        super.show();
    }


    /**
     * 没有title的取消和确定dialog
     *
     * @param content             内容
     * @param toastDialogListener 按钮事件监听
     */
    public void showNoTitle(String content, String title, ToastDialogListener toastDialogListener) {
        showDialog(false, title, content, "", "", toastDialogListener);
        show();
    }


    /**
     * 一个按钮 的dialog
     *
     * @param cancelable          是否可以返回
     * @param content             内容
     * @param confirm             确认按钮文本
     * @param toastDialogListener 按钮事件监听
     */
    public void showConfirmDialog(boolean cancelable, String content, String confirm, ToastDialogListener toastDialogListener) {
        showDialog(cancelable, getContext().getString(R.string.toast), content, null, confirm, toastDialogListener);
        show();
    }

    public void showInputDialog(String title, String cancel, String confirm, final InputToastDialogListener toastDialogListener) {
        dis = false;
        mDialogEditText.setVisibility(View.VISIBLE);
        mDialogContent.setVisibility(View.GONE);
        initShowDialog(false, title, null, cancel, confirm, new ToastDialogListener() {
            @Override
            public void onCancelDialog(View view) {

            }

            @Override
            public void onConfirm(View view) {
                String inputValue = mDialogEditText.getText().toString().trim();
                if (TextUtils.isEmpty(inputValue)) {
                    Toast.makeText(getContext(), "请输入内容！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (toastDialogListener != null) {
                    toastDialogListener.onConfirm(inputValue);
                }

            }
        });
        show();
    }

    /**
     * 确定取消按钮的dialog
     *
     * @param cancelable          是否可以返回
     * @param content             内容
     * @param cancel              左边按钮文本
     * @param confirm             右边按钮文本
     * @param toastDialogListener 按钮事件监听
     */
    public void showDialog(boolean cancelable, CharSequence content, String cancel, String confirm, ToastDialogListener toastDialogListener) {
        initShowDialog(cancelable, getContext().getString(R.string.toast), content, cancel, confirm, toastDialogListener);
        show();
    }

    private void showDialog(boolean cancelable, String title, String content, String cancel, String confirm, final ToastDialogListener toastDialogListener) {
        if (TextUtils.isEmpty(content))
            return;
        initShowDialog(cancelable, title, new SpannableString(content), cancel, confirm, toastDialogListener);
    }

    private void initShowDialog(boolean cancelable, String title, CharSequence content, String cancel, String confirm, final ToastDialogListener toastDialogListener) {
        setCancelable(cancelable);
        if (title == null) {
            mDialogTitle.setVisibility(View.GONE);
        } else {
            mDialogTitle.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(title))
                mDialogTitle.setText(title);
        }

        if (content == null) {
            mDialogContent.setVisibility(View.GONE);
        } else {
            mDialogContent.setVisibility(View.VISIBLE);
            mDialogContent.setText(content);
            mDialogContent.setMovementMethod(LinkMovementMethod.getInstance());
        }
        if (cancel == null) {
            mDialogCancel.setVisibility(View.GONE);
            mVLine.setVisibility(View.GONE);
        } else {
            mDialogCancel.setVisibility(View.VISIBLE);
            mVLine.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(cancel))
                mDialogCancel.setText(cancel);
        }
        if (null == confirm) {
            mDialogOk.setVisibility(View.GONE);
        } else {
            mDialogOk.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(confirm))
                mDialogOk.setText(confirm);
        }


        mDialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ToastDialog.this.dismiss();
                if (toastDialogListener != null)
                    toastDialogListener.onCancelDialog(view);
            }
        });
        mDialogOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dis)
                    ToastDialog.this.dismiss();

                if (toastDialogListener != null)
                    toastDialogListener.onConfirm(view);
            }
        });
    }

    public interface ToastDialogListener {

        void onCancelDialog(View view);

        void onConfirm(View view);
    }

    public interface InputToastDialogListener {

        void onConfirm(String inputValue);
    }


}

