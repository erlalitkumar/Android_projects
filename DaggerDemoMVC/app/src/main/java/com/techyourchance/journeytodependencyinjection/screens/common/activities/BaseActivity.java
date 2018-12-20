package com.techyourchance.journeytodependencyinjection.screens.common.activities;

import android.support.v7.app.AppCompatActivity;

import com.techyourchance.journeytodependencyinjection.MyApplication;
import com.techyourchance.journeytodependencyinjection.common.dependencyinjection.CompositionRoot;

public class BaseActivity extends AppCompatActivity {
    protected CompositionRoot getCompositionRoot() {
        return ((MyApplication) getApplication()).getCompositionRoot();
    }
}
