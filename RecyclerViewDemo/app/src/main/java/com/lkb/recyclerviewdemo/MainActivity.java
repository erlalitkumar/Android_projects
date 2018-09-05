package com.lkb.recyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter mAdapter;
    List<SomeData> data = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.my_recycler_view);
        mAdapter = new RecyclerViewAdapter(data);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        prepareData();

    }

    private void prepareData() {
        data.add(new SomeData("one","One is one"));
        data.add(new SomeData("two","two is two"));
        data.add(new SomeData("three","three is three"));
        data.add(new SomeData("four","four is four"));
        data.add(new SomeData("five","five is five"));
        data.add(new SomeData("one","One is one"));
        data.add(new SomeData("two","two is two"));
        data.add(new SomeData("three","three is three"));
        data.add(new SomeData("four","four is four"));
        data.add(new SomeData("five","five is five"));
        data.add(new SomeData("one","One is one"));
        data.add(new SomeData("two","two is two"));
        data.add(new SomeData("three","three is three"));
        data.add(new SomeData("four","four is four"));
        data.add(new SomeData("five","five is five"));
        mAdapter.notifyDataSetChanged();
    }
}
