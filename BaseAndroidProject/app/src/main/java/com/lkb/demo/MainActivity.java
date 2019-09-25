package com.lkb.demo;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;

import static com.lkb.demo.UtilKt.makeBold;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.tvHello);

        tv.setText(makeBold("Hello world","Hello"), TextView.BufferType.SPANNABLE);
    }
}
