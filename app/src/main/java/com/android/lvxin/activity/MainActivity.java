package com.android.lvxin.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.lvxin.R;
import com.android.lvxin.adapter.BaseRecyclerAdapter;
import com.android.lvxin.adapter.MainAdapter;
import com.android.lvxin.model.TObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        BaseRecyclerAdapter.OnItemClickListener, BaseRecyclerAdapter.OnItemLongClickListener {

    private RecyclerView recyclerView;
    private MainAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MainAdapter(this);
        mAdapter.update(buildData());
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemLongClickListener(this);
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onItemClick(View view, int position, Object data) {
        if (0 == position) {
            LinearLayoutManagerActivity.start(MainActivity.this);
        } else if (1 == position) {
            GridLayoutManagerActivity.start(MainActivity.this);
        }
    }

    @Override
    public void onItemLongClick(View view, int position, Object data) {
        Toast.makeText(this, "long click item", Toast.LENGTH_SHORT).show();
    }


    private List<TObject> buildData() {
        int size = 2;
        List<TObject> data = new ArrayList<>(size);
        TObject obj = new TObject();
        obj.content = "RecyclerView for LinearLayoutManager";
        data.add(obj);
        obj = new TObject();
        obj.content = "RecyclerView for GridLayoutManager";
        data.add(obj);
        return data;
    }
}
