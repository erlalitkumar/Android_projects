package com.techyourchance.journeytodependencyinjection.common.dependencyinjection.presentation;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;

import com.techyourchance.journeytodependencyinjection.common.dependencyinjection.application.ApplicationComponent;
import com.techyourchance.journeytodependencyinjection.networking.StackoverflowApi;
import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionsListUseCase;
import com.techyourchance.journeytodependencyinjection.screens.common.ImageLoader;
import com.techyourchance.journeytodependencyinjection.screens.common.dialogs.DialogsManager;
import com.techyourchance.journeytodependencyinjection.screens.common.mvcviews.ViewMvcFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class PresentationModule {

    //private final ApplicationComponent mApplicationComponent;
    private final FragmentActivity mActivity;

    public PresentationModule(FragmentActivity fragmentActivity) {
        mActivity = fragmentActivity;
    }

    @Provides
    FragmentManager getFragmentManager() {
        return mActivity.getSupportFragmentManager();
    }

    @Provides
    LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(mActivity);
    }

    @Provides
    Activity getActivity() {
        return mActivity;
    }

    @Provides
    FetchQuestionDetailsUseCase getFetchQuestionDetailsUseCase(StackoverflowApi stackoverflowApi) {
        return new FetchQuestionDetailsUseCase(stackoverflowApi);
    }

    @Provides
    DialogsManager getDialogsManager() {
        return new DialogsManager(getFragmentManager());
    }


    @Provides
    ViewMvcFactory getViewMvcFactory() {
        return new ViewMvcFactory(getLayoutInflater(), getImageLoader());
    }

    @Provides
    ImageLoader getImageLoader() {
        return new ImageLoader(getActivity());
    }
}
