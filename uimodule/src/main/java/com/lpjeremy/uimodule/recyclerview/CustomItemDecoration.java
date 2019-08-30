package com.lpjeremy.uimodule.recyclerview;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.lpjeremy.uimodule.R;

/**
 * @desc:自定义RecyclerView的ItemDecoration
 * @date:2017/11/13 15:02
 * @auther:lp
 * @version:1.0
 */
public class CustomItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable mDivider;     //分割线Drawable
    private int mDividerHeight;  //listView分割线高度

    private int topBottom, leftRight;//gridView左右上下间距

    /**
     * 使用line_divider中定义好的颜色
     *
     * @param context
     */
    public CustomItemDecoration(Context context) {
        mDivider = ContextCompat.getDrawable(context, R.drawable.divider_line_transparent);
    }

    /**
     * @param context
     * @param divider 分割线Drawable
     */
    public CustomItemDecoration(Context context, Drawable divider) {
        if (divider == null) {
            mDivider = ContextCompat.getDrawable(context, R.drawable.divider_line_transparent);
        } else {
            mDivider = divider;
        }
    }

    /**
     * 使用line_divider中定义好的颜色
     *
     * @param context
     */
    public CustomItemDecoration(Context context, int dividerHeight) {
        mDivider = ContextCompat.getDrawable(context, R.drawable.divider_line_transparent);
        if (dividerHeight > 0)
            mDividerHeight = dividerHeight;
    }

    /**
     * @param context
     * @param divider 分割线Drawable
     */
    public CustomItemDecoration(Context context, Drawable divider, int dividerHeight) {
        if (divider == null) {
            mDivider = ContextCompat.getDrawable(context, R.drawable.divider_line_transparent);
        } else {
            mDivider = divider;
        }
        if (dividerHeight > 0)
            mDividerHeight = dividerHeight;
    }

    public void setDividerHeight(int mDividerHeight) {
        this.mDividerHeight = mDividerHeight;
    }

    public void setTopBottom(int topBottom) {
        this.topBottom = topBottom;
    }

    public void setLeftRight(int leftRight) {
        this.leftRight = leftRight;
    }

    //获取分割线尺寸
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (parent.getLayoutManager() instanceof GridLayoutManager) {

            GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
            final GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) view.getLayoutParams();
            final int childPosition = parent.getChildAdapterPosition(view);
            final int spanCount = layoutManager.getSpanCount();

            if (layoutManager.getOrientation() == GridLayoutManager.VERTICAL) {
                //判断是否在第一排
                if (layoutManager.getSpanSizeLookup().getSpanGroupIndex(childPosition, spanCount) == 0) {//第一排的需要上面
                    outRect.top = topBottom;
                }
                outRect.bottom = topBottom;
                //这里忽略和合并项的问题，只考虑占满和单一的问题
                if (lp.getSpanSize() == spanCount) {//占满
                    outRect.left = leftRight;
                    outRect.right = leftRight;
                } else {
                    outRect.left = (int) (((float) (spanCount - lp.getSpanIndex())) / spanCount * leftRight);
                    outRect.right = (int) (((float) leftRight * (spanCount + 1) / spanCount) - outRect.left);
                }
            } else {
                if (layoutManager.getSpanSizeLookup().getSpanGroupIndex(childPosition, spanCount) == 0) {//第一排的需要left
                    outRect.left = leftRight;
                }
                outRect.right = leftRight;
                //这里忽略和合并项的问题，只考虑占满和单一的问题
                if (lp.getSpanSize() == spanCount) {//占满
                    outRect.top = topBottom;
                    outRect.bottom = topBottom;
                } else {
                    outRect.top = (int) (((float) (spanCount - lp.getSpanIndex())) / spanCount * topBottom);
                    outRect.bottom = (int) (((float) topBottom * (spanCount + 1) / spanCount) - outRect.top);
                }
            }
        } else if (parent.getLayoutManager() instanceof LinearLayoutManager) {
            outRect.set(0, 0, 0, mDividerHeight);
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDividerHeight;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }
}
