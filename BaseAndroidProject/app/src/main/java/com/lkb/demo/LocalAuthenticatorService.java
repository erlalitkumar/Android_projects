package com.lkb.demo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class LocalAuthenticatorService extends Service {
    private LocalAuthenticator mAuthenticator;
    @Override
    public void onCreate() {
        // Create a new authenticator object
        mAuthenticator = new LocalAuthenticator(this);
    }
    /*
     * When the system binds to this Service to make the RPC call
     * return the authenticator's IBinder.
     */
    @Override
    public IBinder onBind(Intent intent) {
        return mAuthenticator.getIBinder();
    }
}
