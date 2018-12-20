package com.techyourchance.journeytodependencyinjection.common.dependencyinjection;

import android.support.v4.app.FragmentManager;

import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionsListUseCase;
import com.techyourchance.journeytodependencyinjection.screens.common.dialogs.DialogsManager;

public class PresentationCompositionRoot {

    private final CompositionRoot mCompositionRoot;
    private final FragmentManager mFragmentManager;

    public PresentationCompositionRoot(CompositionRoot mCompositionRoot, FragmentManager mFragmentManager) {
        this.mCompositionRoot = mCompositionRoot;
        this.mFragmentManager = mFragmentManager;
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
}
