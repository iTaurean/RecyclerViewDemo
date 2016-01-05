package com.android.lvxin.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.android.lvxin.R;
import com.android.lvxin.adapter.BaseRecyclerAdapter;
import com.android.lvxin.adapter.GridItemDecoration;
import com.android.lvxin.adapter.GridLayoutAdapter;
import com.android.lvxin.model.TObject;

import java.util.ArrayList;
import java.util.List;

public class GridLayoutManagerActivity extends AppCompatActivity implements
        BaseRecyclerAdapter.OnItemClickListener, BaseRecyclerAdapter.OnItemLongClickListener {

    private RecyclerView recyclerView;
    private GridLayoutAdapter mAdapter;

    public static void start(Context context) {
        Intent intent = new Intent(context, GridLayoutManagerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout_manager);

        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_grid_layout);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new GridItemDecoration(this, true));
        mAdapter = new GridLayoutAdapter(this);
        mAdapter.update(buildData());
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemLongClickListener(this);
        recyclerView.setAdapter(mAdapter);

        initHeaderView();
        initFooterView();
    }

    private void initHeaderView() {
        View headerView = LayoutInflater.from(this).inflate(R.layout.layout_header_view, recyclerView, false);
        TextView headerText = (TextView) headerView.findViewById(R.id.tv_header);
        headerText.setText("GridLayoutManager Demo");
        mAdapter.setHeaderView(headerView);
    }

    private void initFooterView() {
        View footerView = LayoutInflater.from(this).inflate(R.layout.layout_footer_view, recyclerView, false);
        mAdapter.setFooterView(footerView);
    }

    @Override
    public void onItemClick(View view, int position, Object data) {

    }

    @Override
    public void onItemLongClick(View view, int position, Object data) {

    }

    private List<TObject> buildData() {
        int size = 20;
        List<TObject> data = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            TObject obj = new TObject();
            obj.content = "content " + i;
            data.add(obj);
        }

        return data;


    }
}
