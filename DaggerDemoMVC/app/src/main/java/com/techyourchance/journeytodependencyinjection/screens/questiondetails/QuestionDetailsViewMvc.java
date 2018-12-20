package com.techyourchance.journeytodependencyinjection.screens.questiondetails;

import com.techyourchance.journeytodependencyinjection.questions.QuestionDetails;
import com.techyourchance.journeytodependencyinjection.screens.common.mvcviews.ObservableViewMvc;

public interface QuestionDetailsViewMvc extends ObservableViewMvc<QuestionDetailsViewMvc.Listener> {
    interface Listener {
        //No user action
    }

    void bindQuestion(QuestionDetails question);
}
