package com.techyourchance.journeytodependencyinjection.common.dependencyinjection;

import com.techyourchance.journeytodependencyinjection.screens.questiondetails.QuestionDetailsActivity;
import com.techyourchance.journeytodependencyinjection.screens.questionslist.QuestionsListActivity;

public class Injector {
    private final PresentationCompositionRoot mPresentationCompositionRoot;

    public Injector(PresentationCompositionRoot presentationCompositionRoot) {
        this.mPresentationCompositionRoot = presentationCompositionRoot;
    }

    public void inject(Object client){
        if(client instanceof QuestionsListActivity){
            injectQuestionsListActivity((QuestionsListActivity) client);
        }else if(client instanceof QuestionDetailsActivity){
            injectQuestionDetailsActivity((QuestionDetailsActivity) client);
        }else{
            throw new RuntimeException("Invalid client:"+client);
        }
    }

    private void injectQuestionDetailsActivity(QuestionDetailsActivity client) {
        client.mViewMvcFactory = mPresentationCompositionRoot.getViewMvcFactory();
        client.mDialogsManager = mPresentationCompositionRoot.getDialogsManager();
        client.mFetchQuestionDetailsUseCase = mPresentationCompositionRoot.getFetchQuestionDetailsUseCase();
    }

    private void injectQuestionsListActivity(QuestionsListActivity client) {
        client.mViewMvcFactory = mPresentationCompositionRoot.getViewMvcFactory();
        client.mDialogsManager = mPresentationCompositionRoot.getDialogsManager();
        client.mFetchQuestionsListUseCase = mPresentationCompositionRoot.getFetchQuestionsListUseCase();
    }
}
