package com.android.lvxin.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: BaseRecyclerAdapter
 * @Description: Base adapter of RecyclerView, including a header and footer
 * @Author: lvxin
 * @Date: 1/4/16 14:15
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected static final int VIEW_TYPE_HEADER = 0;
    protected static final int VIEW_TYPE_FOOTER = 1;
    protected static final int VIEW_TYPE_ITEM = 2;

    private List<T> mData = new ArrayList<>();

    private View mHeaderView;
    private View mFooterView;

    private OnItemClickListener mClickListener;
    private OnItemLongClickListener mLongClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        mLongClickListener = listener;
    }

    /**
     * add header view
     *
     * @param headerView
     */
    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemChanged(0);
    }

    /**
     * add footer view
     *
     * @param footerView
     */
    public void setFooterView(View footerView) {
        mFooterView = footerView;
        notifyItemChanged(getItemCount() - 1);
    }

    public boolean hasHeaderView() {
        return null != mHeaderView;
    }

    public boolean hasFooterView() {
        return null != mFooterView;
    }

    public int getExtraCount() {
        int extraCount = 0;
        extraCount += hasHeaderView() ? 1 : 0;
        extraCount += hasFooterView() ? 1 : 0;

        return extraCount;
    }

    public void update(List<T> data) {
        mData = data;
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (null != mHeaderView && VIEW_TYPE_HEADER == viewType) {
            return new ViewHolder(mHeaderView);
        } else if (null != mFooterView && VIEW_TYPE_FOOTER == viewType) {
            return new ViewHolder(mFooterView);
        } else {
            return onCreate(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (VIEW_TYPE_HEADER == getItemViewType(position)) {
            return;
        } else if (VIEW_TYPE_FOOTER == getItemViewType(position)) {
            return;
        } else {
            final int pos = getRealPosition(holder);
            final T obj = mData.get(pos);
            onBind(holder, pos, obj);

            if (null != mClickListener) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mClickListener.onItemClick(v, pos, obj);
                    }
                });
            }

            if (null != mLongClickListener) {
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        mLongClickListener.onItemLongClick(v, pos, obj);
                        return false;
                    }
                });
            }
        }

    }

    @Override
    public int getItemCount() {
        return mData.size() + getExtraCount();
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return null == mHeaderView ? position : position - 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (null != mHeaderView && 0 == position) {
            return VIEW_TYPE_HEADER;
        } else if (null != mFooterView && getItemCount() - 1 == position) {
            return VIEW_TYPE_FOOTER;
        } else {
            return VIEW_TYPE_ITEM;
        }
    }

    /**
     * 解决GridLayoutManager时header或者footer时没有占据屏幕宽度
     *
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int item = getItemViewType(position);
                    return (item == VIEW_TYPE_HEADER || item == VIEW_TYPE_FOOTER)
                            ? gridLayoutManager.getSpanCount() : 1;
                }
            });
        }
    }

    /**
     * 解决StaggeredGridLayoutManager时header或者footer时没有占据屏幕宽度
     *
     * @param holder
     */
    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        boolean flag = (null != layoutParams)
                && (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams)
                && (0 == holder.getLayoutPosition() || getItemCount() - 1 == holder.getLayoutPosition());
        if (flag) {
            StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) layoutParams;
            lp.setFullSpan(true);
        }

    }

    public abstract RecyclerView.ViewHolder onCreate(ViewGroup parent, final int viewType);

    public abstract void onBind(RecyclerView.ViewHolder viewHolder, int realPosition, T data);

    public interface OnItemClickListener<T> {
        void onItemClick(View view, int position, T data);
    }

    public interface OnItemLongClickListener<T> {
        void onItemLongClick(View view, int position, T data);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View view) {
            super(view);
        }
    }
}
