package com.lkb.demo.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lkb.demo.model.repository.LoginRepository;

//this is viewModel:
//View model will not know anything about the view
public class LoginViewModel extends ViewModel {
    MutableLiveData canShow = new MutableLiveData();
    MutableLiveData token = new MutableLiveData();

    public void doLogin(String userName, String password) {
        canShow.setValue(true);
        LoginRepository.getInstance().validateUser(userName, password);
    }

    public LiveData<Boolean> canShowProgressBar() {
        return canShow;
    }

    public LiveData<String> updateUi() {
        return token;
    }

    public void init() {
        LoginRepository.getInstance().subscribe().observeForever(t -> {
            token.setValue(t);
            canShow.setValue(false);
            Log.d("LKB!", "value of the token is :" + t);
        });
    }
}
