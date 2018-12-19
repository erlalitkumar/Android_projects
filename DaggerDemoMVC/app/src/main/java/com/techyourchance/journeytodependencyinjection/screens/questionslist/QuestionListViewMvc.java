package com.techyourchance.journeytodependencyinjection.screens.questionslist;

import com.techyourchance.journeytodependencyinjection.questions.Question;
import com.techyourchance.journeytodependencyinjection.screens.common.mvcviews.ObservableViewMvc;

import java.util.List;

public interface QuestionListViewMvc
        extends ObservableViewMvc<QuestionListViewMvc.Listener> {
    interface Listener {
        void onQuestionClicked(Question question);
    }

    void bindQuestions(List<Question> questions);
}
