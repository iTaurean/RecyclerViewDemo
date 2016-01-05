package com.android.lvxin.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.lvxin.R;
import com.android.lvxin.adapter.BaseRecyclerAdapter;
import com.android.lvxin.adapter.MainAdapter;
import com.android.lvxin.model.TObject;

import java.util.ArrayList;
import java.util.List;

public class LinearLayoutManagerActivity extends AppCompatActivity implements BaseRecyclerAdapter.OnItemClickListener, BaseRecyclerAdapter.OnItemLongClickListener {

    private RecyclerView recyclerView;
    private MainAdapter mAdapter;

    public static void start(Context context) {
        Intent intent = new Intent(context, LinearLayoutManagerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout_manager);

        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_linear_layout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.addItemDecoration(new DividerItemDecoration(DisplayUtils.dp2px(this, 5)));
        mAdapter = new MainAdapter(this);
        mAdapter.update(buildData());
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemLongClickListener(this);
        recyclerView.setAdapter(mAdapter);

        initHeaderView();
        initFooterView();
    }

    private void initFooterView() {
        View footerView = LayoutInflater.from(this).inflate(R.layout.layout_footer_view, recyclerView, false);
        mAdapter.setFooterView(footerView);
    }

    private void initHeaderView() {
        View headerView = LayoutInflater.from(this).inflate(R.layout.layout_header_view, recyclerView, false);
        TextView headerText = (TextView) headerView.findViewById(R.id.tv_header);
        headerText.setText("LinearLayoutManager Demo");
        mAdapter.setHeaderView(headerView);
    }

    @Override
    public void onItemClick(View view, int position, Object data) {
        Toast.makeText(this, "click item", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, int position, Object data) {
        Toast.makeText(this, "long click item", Toast.LENGTH_SHORT).show();
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
