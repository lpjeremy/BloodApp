package com.lpjeremy.uimodule.recyclerview;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lpjeremy.uimodule.R;
import com.lpjeremy.uimodule.recyclerview.interfaces.OnLoadMoreListener;

import java.util.LinkedList;

/**
 * @desc:自定义RecyclerView
 * @date:2017/9/13 9:22
 * @auther:lp
 * @version:1.0
 */

public class CustomRecyclerView extends RecyclerView {
    private CostomLinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private Context mContext;
    private Activity mActivity;
    /**
     * 每页显示条数
     */
    public static final int PAGE_COUNT = 20;

    /**
     * item 类型
     */
    public final static int TYPE_NORMAL = 0;
    public final static int TYPE_HEADER = 1;//头部--支持头部增加一个headerView
    public final static int TYPE_FOOTER = 2;//底部--往往是loading_more
    public final static int TYPE_LIST = 3;//代表item展示的模式是list模式
    public final static int TYPE_STAGGER = 4;//代码item展示模式是网格模式

    private boolean mIsFooterEnable = false;//是否允许加载更多

    /**
     * 自定义实现了头部和底部加载更多的adapter
     */
    private AutoLoadAdapter mAutoLoadAdapter;
    /**
     * 标记是否正在加载更多，防止再次调用加载更多接口
     */
    private boolean mIsLoadingMore;
    /**
     * 标记加载更多的position
     */
    private int mLoadMorePosition;
    /**
     * 加载跟多view
     */
    private View footView;
    /**
     * 加载更多显示信息的TextView
     */
    private TextView textLoadMore;
    /**
     * 加载更多的进度条
     */
    private ProgressBar progressBar;
    /**
     * 加载更多的监听-业务需要实现加载数据
     */
    private OnLoadMoreListener mListener;
    /**
     * gridView左右上下间距
     */
    private int topBottom, leftRight;

    public CustomRecyclerView(Context context) {
        super(context);
        mContext = context;
        addOnScrollListenerToRecyclerView();
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        addOnScrollListenerToRecyclerView();
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        addOnScrollListenerToRecyclerView();
    }

    public CostomLinearLayoutManager getLinearLayoutManager() {
        return linearLayoutManager;
    }

    public GridLayoutManager getGridLayoutManager() {
        return gridLayoutManager;
    }

    /**
     * 横向滑动的listView
     */
    public void isHorizontalListView() {
        setUiType(UiType.LISTVIEW_HORIZONTAL, 0, 0, 0, 0);

    }

    public void isListView() {
        setUiType(UiType.LISTVIEW, 0, 0, 0, 0);

    }

    public void isListView(int dividerHeight) {
        setUiType(UiType.LISTVIEW, dividerHeight, 0, 0, 0);

    }


    public void isGridView(int num, int topBottom, int leftRight) {
        setUiType(UiType.GRIDVIEW, 0, num, topBottom, leftRight);
    }

    public void isGridView(int num) {
        setUiType(UiType.GRIDVIEW, 0, num, 0, 0);
    }

    /**
     * 设置RecyclerView显示类型
     *
     * @param uiType
     */
    public void setUiType(UiType uiType, int dividerHeight, int num, int topBottom, int leftRight) {
        int orientation = CostomLinearLayoutManager.INVALID_OFFSET;
        switch (uiType) {
            case LISTVIEW_HORIZONTAL:
            case LISTVIEW:
                if (uiType == UiType.LISTVIEW) {
                    orientation = LinearLayoutManager.VERTICAL;
                } else if (uiType == UiType.LISTVIEW_HORIZONTAL) {
                    orientation = LinearLayoutManager.HORIZONTAL;
                }
                linearLayoutManager = new CostomLinearLayoutManager(mContext, orientation, false);
                setLayoutManager(linearLayoutManager);
                break;
            case GRIDVIEW:
//                orientation = CostomLinearLayoutManager.HORIZONTAL;
                gridLayoutManager = new GridLayoutManager(mContext, num);
                setLayoutManager(gridLayoutManager);
                break;
        }


        setHasFixedSize(true);

        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.divider_line);
        if (uiType == UiType.GRIDVIEW) {
            if (topBottom > 0 && leftRight > 0) {
                CustomItemDecoration itemDecoration = new CustomItemDecoration(mContext, drawable);
                itemDecoration.setLeftRight(leftRight);
                itemDecoration.setTopBottom(topBottom);
                addItemDecoration(itemDecoration);
            }
        } else {
            if (dividerHeight > 0) {
                addItemDecoration(new CustomItemDecoration(mContext, drawable, dividerHeight));
            } else {
                addItemDecoration(new CustomItemDecoration(mContext, dividerHeight));
            }
        }

    }

    /**
     * 初始化-添加滚动监听 回调加载更多方法，前提是
     * 1、有监听并且支持加载更多：null != mListener && mIsFooterEnable
     * 2、目前没有在加载，正在上拉（dy>0），当前最后一条可见的view是否是当前数据列表的最好一条--及加载更多
     */
    private void addOnScrollListenerToRecyclerView() {
        super.addOnScrollListener(new OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (null != mListener && mIsFooterEnable && !mIsLoadingMore && dy > 0) {
                    int lastVisiblePosition = getLastVisiblePosition();
                    if (lastVisiblePosition + 1 == mAutoLoadAdapter.getItemCount()) {
                        setLoadingMore(true);
                        mLoadMorePosition = lastVisiblePosition;
                        mListener.onLoadMore();
                    }
                }
            }
        });
    }

    /**
     * 手动加载更多
     */
    public void loadMore() {
        if (mListener != null)
            mListener.onLoadMore();
    }

    /**
     * 设置加载更多的监听
     *
     * @param listener
     */
    public void setLoadMoreListener(OnLoadMoreListener listener) {
        mListener = listener;
    }

    /**
     * 设置正在加载更多
     *
     * @param loadingMore
     */
    public void setLoadingMore(boolean loadingMore) {
        this.mIsLoadingMore = loadingMore;
    }


    public class AutoLoadAdapter extends Adapter<ViewHolder> {

        /**
         * 数据adapter
         */
        private Adapter mInternalAdapter;

        private LinkedList<View> headViews = new LinkedList<>();


        public AutoLoadAdapter(Adapter adapter) {
            mInternalAdapter = adapter;
            headViews.clear();
        }

        @Override
        public int getItemViewType(int position) {
            int headerPosition = 0;
            int footerPosition = getItemCount() - 1;

            if (headerPosition == position && !headViews.isEmpty()) {
                return TYPE_HEADER;
            }
            if (footerPosition == position && mIsFooterEnable) {
                return TYPE_FOOTER;
            }
            return mInternalAdapter.getItemViewType(position);
//            /**
//             * 这么做保证layoutManager切换之后能及时的刷新上对的布局
//             */
//            if (getLayoutManager() instanceof LinearLayoutManager) {
//                return TYPE_LIST;
//            } else if (getLayoutManager() instanceof StaggeredGridLayoutManager) {
//                return TYPE_STAGGER;
//            } else {
//                return TYPE_NORMAL;
//            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == TYPE_HEADER && !headViews.isEmpty()) {
                return new HeaderViewHolder(headViews.get(0));
            }
            if (viewType == TYPE_FOOTER) {
                footView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recyclerview_footview, parent, false);
                return new FooterViewHolder(footView);
            }
            return mInternalAdapter.onCreateViewHolder(parent, viewType);
        }

        public class FooterViewHolder extends ViewHolder {

            public FooterViewHolder(View itemView) {
                super(itemView);
                textLoadMore = itemView.findViewById(R.id.textLoadMore);
                progressBar = itemView.findViewById(R.id.progressbar);
            }
        }

        public class HeaderViewHolder extends ViewHolder {
            public HeaderViewHolder(View itemView) {
                super(itemView);
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            int type = getItemViewType(position);
            if (type != TYPE_FOOTER && type != TYPE_HEADER) {
                if (!headViews.isEmpty()) {
                    int index = position - headViews.size();
                    mInternalAdapter.onBindViewHolder(holder, index < 0 ? 0 : index);
                } else {
                    mInternalAdapter.onBindViewHolder(holder, position);
                }
            }
        }

        /**
         * 需要计算上加载更多和添加的头部俩个
         *
         * @return
         */
        @Override
        public int getItemCount() {
            int count = mInternalAdapter.getItemCount();
            if (mIsFooterEnable)
                count++;
            count += headViews.size();

            return count;
        }


        public void addHeaderViewHolder(View view) {
            if (view != null) {
                headViews.clear();
                headViews.add(view);
            }
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (adapter != null) {
            mAutoLoadAdapter = new AutoLoadAdapter(adapter);
        }
        super.swapAdapter(mAutoLoadAdapter, true);
    }

    public void notifyDataSetChanged() {
        if (mAutoLoadAdapter != null) {
            setLoadingMore(false);
            setLoadMoreText(false);
            //if (mAutoLoadAdapter.getItemCount() < PAGE_COUNT) {
            setFootViewVisibility(GONE);
//            } else {
//                setAutoLoadMoreEnable(true);
//                setFootViewVisibility(VISIBLE);
//            }
            mAutoLoadAdapter.notifyDataSetChanged();
        }
    }

    public void notifyItemRemoved(int position) {
        if (mAutoLoadAdapter != null && position < mAutoLoadAdapter.getItemCount()) {
            mAutoLoadAdapter.notifyItemRemoved(position);
        }
    }

    /**
     * 切换layoutManager
     * <p/>
     * 为了保证切换之后页面上还是停留在当前展示的位置，记录下切换之前的第一条展示位置，切换完成之后滚动到该位置
     * 另外切换之后必须要重新刷新下当前已经缓存的itemView，否则会出现布局错乱（俩种模式下的item布局不同），
     * RecyclerView提供了swapAdapter来进行切换adapter并清理老的itemView cache
     *
     * @param layoutManager
     */
    public void switchLayoutManager(LayoutManager layoutManager) {
        int firstVisiblePosition = getFirstVisiblePosition();
        setLayoutManager(layoutManager);
        getLayoutManager().scrollToPosition(firstVisiblePosition);
    }

    /**
     * 获取第一条展示的位置
     *
     * @return
     */
    private int getFirstVisiblePosition() {
        int position;
        if (getLayoutManager() instanceof CostomLinearLayoutManager) {
            position = ((CostomLinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
        } else if (getLayoutManager() instanceof GridLayoutManager) {
            position = ((GridLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
        } else if (getLayoutManager() instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) getLayoutManager();
            int[] lastPositions = layoutManager.findFirstVisibleItemPositions(new int[layoutManager.getSpanCount()]);
            position = getMinPositions(lastPositions);
        } else {
            position = 0;
        }
        return position;
    }

    /**
     * 获得当前展示最小的position
     *
     * @param positions
     * @return
     */
    private int getMinPositions(int[] positions) {
        int size = positions.length;
        int minPosition = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            minPosition = Math.min(minPosition, positions[i]);
        }
        return minPosition;
    }

    /**
     * 获取最后一条展示的位置
     *
     * @return
     */
    public int getLastVisiblePosition() {
        int position;
        if (getLayoutManager() instanceof CostomLinearLayoutManager) {
            position = ((CostomLinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
        } else if (getLayoutManager() instanceof GridLayoutManager) {
            position = ((GridLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
        } else if (getLayoutManager() instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) getLayoutManager();
            int[] lastPositions = layoutManager.findLastVisibleItemPositions(new int[layoutManager.getSpanCount()]);
            position = getMaxPosition(lastPositions);
        } else {
            position = getLayoutManager().getItemCount() - 1;
        }
        return position;
    }

    /**
     * 获得最大的位置
     *
     * @param positions
     * @return
     */
    private int getMaxPosition(int[] positions) {
        int size = positions.length;
        int maxPosition = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            maxPosition = Math.max(maxPosition, positions[i]);
        }
        return maxPosition;
    }

    /**
     * 添加头部view
     *
     * @param view
     */
    public void addHeaderViewHolder(View view) {
        if (mAutoLoadAdapter != null)
            mAutoLoadAdapter.addHeaderViewHolder(view);
    }

    /**
     * 设置是否支持自动加载更多
     *
     * @param autoLoadMore
     */
    public void setAutoLoadMoreEnable(boolean autoLoadMore) {
        mIsFooterEnable = autoLoadMore;
    }

    /**
     * 通知更多的数据已经加载
     * <p/>
     * 每次加载完成之后添加了Data数据，用notifyItemRemoved来刷新列表展示，
     * 而不是用notifyDataSetChanged来刷新列表
     *
     * @param hasMore
     */
    public void notifyMoreFinish(boolean hasMore) {
        setAutoLoadMoreEnable(hasMore);
        setFootViewVisibility(GONE);
        mIsLoadingMore = false;
    }

    private void setFootViewVisibility(int visibility) {
        if (footView != null)
            footView.setVisibility(visibility);
        if (getAdapter() == null)
            return;
        if (visibility == GONE) {
            getAdapter().notifyItemRemoved(mLoadMorePosition);
        } else {
            getAdapter().notifyItemInserted(getAdapter().getItemCount() - 1);
        }
    }


    /**
     * 设置加载更多控件显示内容
     *
     * @param loadFailure 是否是加载失败
     */
    public void setLoadMoreText(boolean loadFailure) {
        if (textLoadMore != null && footView != null && footView.getVisibility() == VISIBLE) {
            if (loadFailure) {
                if (progressBar != null)
                    progressBar.setVisibility(GONE);
                textLoadMore.setText("加载更多失败、点击重试");
                textLoadMore.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null) {
                            if (progressBar != null)
                                progressBar.setVisibility(VISIBLE);
                            mListener.onLoadMore();
                        }
                    }
                });
            } else {
                textLoadMore.setText("加载中...");
                if (progressBar != null)
                    progressBar.setVisibility(VISIBLE);
            }
        }
    }

    /**
     * 是否正在加载更多
     *
     * @return
     */
    public boolean isLoadingMore() {
        return mIsLoadingMore;
    }

    public int getHeadViewCount() {
        return mAutoLoadAdapter == null ? 0 : 1;
    }


    /**
     * RecyclerView显示类型
     */
    public enum UiType {
        LISTVIEW,//listview_竖向
        LISTVIEW_HORIZONTAL,//listview_横向
        GRIDVIEW;
    }


}
