package com.lkb.abcactivitydemo;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        next = findViewById(R.id.button_next_activity_b);
        final Intent i = new Intent(this, Main3Activity.class);
        //i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(i);
                // finishAffinity();
                // finish();
            }
        });
        //finish();
        Log.d("LKB-Activity2", "oncreate end");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LKB-Activity2", "onStart  called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LKB-Activity2", "onresume called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LKB-Activity2", "onStop called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LKB-Activity2", "onpause called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LKB-Activity2", "onDestroy called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("LKB-Activity2", "onRestart  called");
    }
}
