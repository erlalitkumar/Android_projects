package com.lkb.demo.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.lkb.demo.R;
import com.lkb.demo.viewmodel.LoginViewModel;

//this is view:
//view will communicate through the viewModel.
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        Button loginButton = findViewById(R.id.loginButton);
        EditText email = findViewById(R.id.email);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        EditText password = findViewById(R.id.password);

        LoginViewModel loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModel.init();
        loginViewModel.canShowProgressBar().observe(this, canShow -> {
            if (canShow) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });
        loginViewModel.updateUi().observe(this, t -> {
            if (t.isEmpty()) {
                Toast.makeText(this, "UserName or Password is invalid", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(new Intent(this, HomeActivity.class));
            }
        });

        loginButton.setOnClickListener(v -> {
            loginViewModel.doLogin(email.getText().toString(), password.getText().toString());
        });
    }
}
