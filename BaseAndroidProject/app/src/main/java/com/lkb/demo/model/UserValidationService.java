package com.lkb.demo.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;

//think it as a network which is giving valid token after the password validation.
//or a database.
public class UserValidationService {
    static UserValidationService instance;
    volatile boolean shutdown;
    HashMap<String, String> dataSource;
    MutableLiveData validToken;

    private UserValidationService() {
        validToken = new MutableLiveData();
        this.dataSource = new HashMap<>();
        dataSource.put("lkb@gmail.com", "1234");
        dataSource.put("vishal@gmail.com", "4567");
    }

    public static UserValidationService getInstance() {
        if (instance == null) {
            instance = new UserValidationService();
        }
        return instance;
    }

    public void getData(String email, String password) {
        String pass = dataSource.get(email);
        shutdown = false;
        //validation logic
        try {
            new Thread(() -> {
                while (!shutdown) {
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (pass != null) {
                        if (pass.contentEquals(password)) {
                            validToken.postValue("kjdfkjkf-kdfk-jkjdkf-kjdfk");
                        } else {
                            validToken.postValue("");
                        }
                    } else {
                        validToken.postValue("");
                    }
                    shutdown = true;
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LiveData<String> subscribe() {
        return validToken;
    }
}
