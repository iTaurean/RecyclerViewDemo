package com.android.lvxin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.lvxin.R;
import com.android.lvxin.model.TObject;

/**
 * @ClassName: GridLayoutAdapter
 * @Description: TODO
 * @Author: lvxin
 * @Date: 1/5/16 15:17
 */
public class GridLayoutAdapter extends BaseRecyclerAdapter<TObject> {
    private Context mContext;

    public GridLayoutAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_grid_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int realPosition, TObject obj) {
        if (viewHolder instanceof ItemViewHolder) {
            ((ItemViewHolder) viewHolder).mContentText.setText(obj.content);
        }
    }

    static class ItemViewHolder extends BaseRecyclerAdapter.ViewHolder {

        TextView mContentText;

        public ItemViewHolder(View view) {
            super(view);
            mContentText = (TextView) view.findViewById(R.id.item_content);
        }
    }
}
