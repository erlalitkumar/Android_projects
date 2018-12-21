package com.techyourchance.journeytodependencyinjection.common.dependencyinjection.presentation;

import com.techyourchance.journeytodependencyinjection.screens.questiondetails.QuestionDetailsActivity;
import com.techyourchance.journeytodependencyinjection.screens.questionslist.QuestionsListActivity;

import dagger.Component;

@Component(modules = PresentationModule.class)
public interface PresentationComponent {
void inject(QuestionsListActivity questionsListActivity);
void inject(QuestionDetailsActivity questionDetailsActivity);
}
