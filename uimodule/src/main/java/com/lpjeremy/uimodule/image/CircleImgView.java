package com.lpjeremy.uimodule.image;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @desc:圆形图片控件
 * @date:2019/1/27 15:52
 * @auther:lp
 * @version:1.0
 */

public class CircleImgView extends CircleImageView {
    public CircleImgView(Context context) {
        super(context);
        initParams();
    }

    public CircleImgView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initParams();
    }

    public CircleImgView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initParams();
    }

    private void initParams() {
        if (getBorderWidth() <= 0) {
            setBorderWidth(2);
            setBorderColor(Color.TRANSPARENT);
        }
    }
}
