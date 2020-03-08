package com.lkb.demo;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout layout = findViewById(R.id.container);
        AccountManager am = AccountManager.get(this);
        Account[] accounts = am.getAccountsByType("com.lms.android.applications");
        layout.addView(getTextView("account name is : " + accounts[0].name));
        layout.addView(getTextView("facebook : " + am.getUserData(accounts[0],"facebook")));
        layout.addView(getTextView("USERACCESSTOKEN : " + am.getUserData(accounts[0],"USERACCESSTOKEN")));
        layout.addView(getTextView("REFRESHTOKEN : " + am.getUserData(accounts[0],"REFRESHTOKEN")));
        layout.addView(getTextView("USEREXPIRY : " + am.getUserData(accounts[0],"USEREXPIRY")));
        layout.addView(getTextView("FACEBOOKTOKEN : " + am.getUserData(accounts[0],"FACEBOOKTOKEN")));
        layout.addView(getTextView("REGISTERCARTID : " + am.getUserData(accounts[0],"REGISTERCARTID")));
        layout.addView(getTextView("REGISTERCARTGUID : " + am.getUserData(accounts[0],"REGISTERCARTGUID")));
        layout.addView(getTextView("PREF_CART_TYPE : " + am.getUserData(accounts[0],"PREF_CART_TYPE")));
        layout.addView(getTextView("===== common values ======"));
        layout.addView(getTextView("FAVORITE_STATUS : " + am.getUserData(accounts[0],"FAVORITE_STATUS")));
        layout.addView(getTextView("selected_lang : " + am.getUserData(accounts[0],"selected_lang")));
        layout.addView(getTextView("country_opted : " + am.getUserData(accounts[0],"country_opted")));
        layout.addView(getTextView("token_refresh_time : " + am.getUserData(accounts[0],"token_refresh_time")));
        layout.addView(getTextView("account_updated : " + am.getUserData(accounts[0],"account_updated")));
        layout.addView(getTextView("IS_ACCOUNT_UPDATED_INDIA : " + am.getUserData(accounts[0],"IS_ACCOUNT_UPDATED_INDIA")));
        layout.addView(getTextView("=====for anonymous user======"));
        layout.addView(getTextView("ACCESSTOKEN : " + am.getUserData(accounts[0],"ACCESSTOKEN")));
        layout.addView(getTextView("EXPIRY : " + am.getUserData(accounts[0],"EXPIRY")));
        layout.addView(getTextView("ANONYMOUSCARTID : " + am.getUserData(accounts[0],"ANONYMOUSCARTID")));
        layout.addView(getTextView("CARTTYPE : " + am.getUserData(accounts[0],"CARTTYPE")));
    }

    private View getTextView(String s) {
        LayoutInflater inflater = LayoutInflater.from(this);
       View view = inflater.inflate(R.layout.child_view,null);
        TextView tv = view.findViewById(R.id.textView);
        tv.setText(s);
        return view;
    }
}
