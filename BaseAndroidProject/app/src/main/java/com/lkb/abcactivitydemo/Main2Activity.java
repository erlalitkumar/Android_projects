package com.lkb.abcactivitydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        finish();
        Log.d("Activity2","oncreate end");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Activity2","onresume called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Activity2","onStop called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Activity2","onpause called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Activity2","onDestroy called");
    }
}
