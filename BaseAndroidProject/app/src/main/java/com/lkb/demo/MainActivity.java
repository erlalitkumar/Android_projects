package com.lkb.demo;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Account account = new Account("lalit143.er@gmail.com", getResources().getString(R.string.account_type));

        AccountManager am = AccountManager.get(this);
        am.addAccountExplicitly(account, "123456", null);
        Account[] accounts = am.getAccountsByType("com.lkb.demo.app");
        am.setUserData(account,"access_token","sjdkiee_dfkje_fjdkjdf_djff");
        Log.d("LKB1", "account name is : " + accounts[0].name);
        Log.d("LKB1", "access_toke is  : " + am.getUserData(account,"access_token"));
    }
}
