package com.android.lvxin.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @ClassName: DividerItemDecoration
 * @Description: TODO
 * @Author: lvxin
 * @Date: 1/4/16 14:42
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private final int mVerticalSpaceHeight;

    public DividerItemDecoration(int mVerticalSpaceHeight) {
        this.mVerticalSpaceHeight = mVerticalSpaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.bottom = mVerticalSpaceHeight;
    }
}
