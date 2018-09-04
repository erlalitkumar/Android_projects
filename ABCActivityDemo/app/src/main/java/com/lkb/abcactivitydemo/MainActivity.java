package com.lkb.abcactivitydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        next = findViewById(R.id.button_next_activity_a);
        final Intent i = new Intent(this, Main2Activity.class);
        //i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity(i);
            }
        });
    }
}
