package com.lkb.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.tvHello);
        SpannableString spannableString = new SpannableString("Hello");
        // setting the text as a Spannable
        spannableString.setSpan(new
                        ForegroundColorSpan(Color.RED),
                0, 1,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(spannableString, TextView.BufferType.SPANNABLE);
    }
}
