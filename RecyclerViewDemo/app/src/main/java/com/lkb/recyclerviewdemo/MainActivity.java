package com.lkb.recyclerviewdemo;

import android.graphics.Movie;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

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
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        // prepareData();
        SomeTask task = new SomeTask();
        task.execute();

        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), recyclerView, new RecyclerViewTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Movie movie = movieList.get(position);
                SomeData d = data.get(position);
                Toast.makeText(getApplicationContext(), d.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

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
    }

    private class SomeTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            prepareData();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mAdapter = new RecyclerViewAdapter(data);
            recyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
            super.onPostExecute(aVoid);
        }
    }
}
