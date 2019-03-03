package com.masudias.gmailview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements ZoomListener {

    private RecyclerView mRecyclerView;
    private RecyclerViewWithHeaderFooterAdapter adapter;

    public static int DEMO_LIST_SIZE = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        mRecyclerView = findViewById(R.id.dummy_rv);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        adapter = new RecyclerViewWithHeaderFooterAdapter(this, this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onZoomListener(int position) {
        mRecyclerView.scrollToPosition(position);
    }
}