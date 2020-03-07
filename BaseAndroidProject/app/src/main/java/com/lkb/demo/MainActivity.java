package com.lkb.demo;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView helloText = findViewById(R.id.hello_txt);
        Button button = findViewById(R.id.button);
        Account account = new Account("lalit143.er@gmail.com", getResources().getString(R.string.account_type));

        AccountManager am = AccountManager.get(this);
        Bundle bundle = new Bundle();
        bundle.putString("access_token2", "ha-ha");
        am.addAccountExplicitly(account, "123456", bundle);
        Account[] accounts = am.getAccountsByType(getResources().getString(R.string.account_type));
        //am.setUserData(account, "access_token", "sjdkiee_dfkje_fjdkjdf_djff");
        helloText.setText("account name is :" + accounts[0].name + " and access_token is: " + am.getUserData(account, "access_token2"));
        Log.d("LKB1", "account name is : " + accounts[0].name);
        Log.d("LKB1", "access_toke is  : " + am.getUserData(account, "access_token2"));

        button.setOnClickListener(v -> {
            am.removeAccount(account,
                    future -> {
                        Toast.makeText(this, "account removed", Toast.LENGTH_SHORT).show();
                    }, null);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
