package com.lkb.demo.model.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.lkb.demo.model.UserValidationService;

//this is Repository - which talks to the database or api - comes under model:
//follow the screen shot in the doc -- solid lines are the direct communication
// dotted lines are the communication through the observer (LiveData).
public class LoginRepository {
    MutableLiveData token;
    static LoginRepository instance;

    private LoginRepository() {
        this.token = new MutableLiveData();
        init();
    }

    public static LoginRepository getInstance() {
        if (instance == null) {
            instance = new LoginRepository();
        }
        return instance;
    }

    public void validateUser(String userName, String password) {
        //Think as Api call or database call or file read
        UserValidationService.getInstance().getData(userName, password);
    }
    public LiveData<String> subscribe(){
        return token;
    }
    public void init(){
        UserValidationService.getInstance().subscribe().observeForever(t->{
            token.setValue(t);
        });
    }
}
