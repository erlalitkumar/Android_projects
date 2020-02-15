package com.lkb.demo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.jakewharton.rxbinding3.view.RxView;
import io.reactivex.disposables.Disposable;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = findViewById(R.id.btnSubmit);
        TextView t = findViewById(R.id.tvHello);
        Disposable s = RxView.clicks(b).subscribe(unit -> t.setText("clicked"));
    }
}
