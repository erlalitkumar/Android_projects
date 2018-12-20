package com.techyourchance.journeytodependencyinjection.common.dependencyinjection;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionsListUseCase;
import com.techyourchance.journeytodependencyinjection.screens.common.ImageLoader;
import com.techyourchance.journeytodependencyinjection.screens.common.dialogs.DialogsManager;
import com.techyourchance.journeytodependencyinjection.screens.common.mvcviews.ViewMvcFactory;

public class PresentationCompositionRoot {

    private final CompositionRoot mCompositionRoot;
    private final AppCompatActivity mActivity;
    //private final FragmentManager mFragmentManager;

    //private LayoutInflater mLayoutInflater;

    public PresentationCompositionRoot(CompositionRoot compositionRoot, AppCompatActivity activity) {
        this.mCompositionRoot = compositionRoot;
//        this.mFragmentManager = fragmentManager;
//        this.mLayoutInflater = layoutInflater;
        mActivity = activity;
    }
    private FragmentManager getFragmentManager(){
        return mActivity.getSupportFragmentManager();
    }

    private LayoutInflater getLayoutInflater(){
        return LayoutInflater.from(mActivity);
    }

    private Activity getActivity(){
        return mActivity;
    }

    public DialogsManager getDialogsManager(){
        return new DialogsManager(getFragmentManager());
    }

    public FetchQuestionsListUseCase getFetchQuestionsListUseCase(){
        return mCompositionRoot.getFetchQuestionsListUseCase();
    }

    public FetchQuestionDetailsUseCase getFetchQuestionDetailsUseCase(){
        return mCompositionRoot.getFetchQuestionDetailsUseCase();
    }

    public ViewMvcFactory getViewMvcFactory(){
        return new ViewMvcFactory(getLayoutInflater(),getImageLoader());
    }
    private ImageLoader getImageLoader(){
        return new ImageLoader(getActivity());
    }
}
