package com.lkb.abcactivitydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Log.d("Activity3", "onCreate called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Activity3", "onStart  called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Activity3", "onresume called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Activity3", "onStop called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Activity3", "onpause called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Activity3", "onDestroy called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Activity3", "onRestart  called");
    }
}
