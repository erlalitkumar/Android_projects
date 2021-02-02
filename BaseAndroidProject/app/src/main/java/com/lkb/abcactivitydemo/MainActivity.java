package com.lkb.abcactivitydemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
    Button next;
    Button btnLoadFragment;
    FrameLayout fragmentContainer;
    String myString = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        next = findViewById(R.id.button_next_activity_a);
        btnLoadFragment = findViewById(R.id.loadFragment);
        fragmentContainer = findViewById(R.id.fragmentContainer);
        final Intent i = new Intent(this, Main2Activity.class);
        //i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        next.setOnClickListener(view -> startActivity(i));

//        btnLoadFragment.setOnClickListener(v -> loadFragment()
//        );
        loadFragment();
        Log.d("LKB-Activity1", "onCreate called");
    }

    private void loadFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragmentContainer, BlankFragment.newInstance("new", "fragment"), "blankFragment");
        ft.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LKB-Activity1", "onStart  called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("LKB-Activity1", "onRestart  called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LKB-Activity1", "onresume called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LKB-Activity1", "onStop called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LKB-Activity1", "onpause called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LKB-Activity1", "onDestroy called");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("LKB-Activity1", "onSaveInstanceState called");
        outState.putString("msg", "welcome back!!");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("LKB-Activity1", "onRestoreInstanceState called");
        myString = savedInstanceState.getString("MyString");

    }
}
