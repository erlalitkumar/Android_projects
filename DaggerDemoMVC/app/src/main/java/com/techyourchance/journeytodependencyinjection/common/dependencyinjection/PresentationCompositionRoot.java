package com.techyourchance.journeytodependencyinjection.common.dependencyinjection;

import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;

import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionsListUseCase;
import com.techyourchance.journeytodependencyinjection.screens.common.dialogs.DialogsManager;
import com.techyourchance.journeytodependencyinjection.screens.common.mvcviews.ViewMvcFactory;

public class PresentationCompositionRoot {

    private final CompositionRoot mCompositionRoot;
    private final FragmentManager mFragmentManager;
    private LayoutInflater mLayoutInflater;

    public PresentationCompositionRoot(CompositionRoot compositionRoot, FragmentManager fragmentManager,LayoutInflater layoutInflater) {
        this.mCompositionRoot = compositionRoot;
        this.mFragmentManager = fragmentManager;
        this.mLayoutInflater = layoutInflater;
    }

    public DialogsManager getDialogsManager(){
        return new DialogsManager(mFragmentManager);
    }

    public FetchQuestionsListUseCase getFetchQuestionsListUseCase(){
        return mCompositionRoot.getFetchQuestionsListUseCase();
    }

    public FetchQuestionDetailsUseCase getFetchQuestionDetailsUseCase(){
        return mCompositionRoot.getFetchQuestionDetailsUseCase();
    }

    public ViewMvcFactory getViewMvcFactory(){
        return new ViewMvcFactory(mLayoutInflater);
    }
}
