package com.techyourchance.journeytodependencyinjection.screens.questionslist;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.techyourchance.journeytodependencyinjection.networking.QuestionsListResponseSchema;
import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionsListUseCase;
import com.techyourchance.journeytodependencyinjection.questions.Question;
import com.techyourchance.journeytodependencyinjection.screens.common.dialogs.DialogsManager;
import com.techyourchance.journeytodependencyinjection.screens.common.dialogs.ServerErrorDialogFragment;
import com.techyourchance.journeytodependencyinjection.screens.questiondetails.QuestionDetailsActivity;

import java.util.List;

import retrofit2.Call;

public class QuestionsListActivity extends AppCompatActivity implements
        FetchQuestionsListUseCase.Listener, QuestionListViewMvc.Listener {

    private QuestionListViewMvc mViewMvc;

    //private StackoverflowApi mStackoverflowApi;

    private Call<QuestionsListResponseSchema> mCall;

    private DialogsManager mDialogManager;

    private FetchQuestionsListUseCase mFetchQuestionListUseCase;

    private static final int NUM_OF_QUESTIONS_TO_FETCH = 20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewMvc = new QuestionsListViewMvcImpl(LayoutInflater.from(this), null);
        setContentView(mViewMvc.getRootView());
        mFetchQuestionListUseCase = new FetchQuestionsListUseCase();

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Constants.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        mStackoverflowApi = retrofit.create(StackoverflowApi.class);

        mDialogManager = new DialogsManager(getSupportFragmentManager());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewMvc.registerListener(this);
        mFetchQuestionListUseCase.registerListener(this);
        mFetchQuestionListUseCase.fetchLastActiveQuestionsAndNotify(NUM_OF_QUESTIONS_TO_FETCH);
//        mCall = mStackoverflowApi.lastActiveQuestions(20);
//        mCall.enqueue(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mViewMvc.unregisterListener(this);
        mFetchQuestionListUseCase.unregisterListener(this);
//        if (mCall != null) {
//            mCall.cancel();
//        }
    }
//
//    @Override
//    public void onResponse(Call<QuestionsListResponseSchema> call, Response<QuestionsListResponseSchema> response) {
//        QuestionsListResponseSchema responseSchema;
//        if (response.isSuccessful() && (responseSchema = response.body()) != null) {
//            mViewMvc.bindQuestions(responseSchema.getQuestions());
//        } else {
//            onFailure(call, null);
//        }
//    }
//
//    @Override
//    public void onFailure(Call<QuestionsListResponseSchema> call, Throwable t) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//                .add(ServerErrorDialogFragment.newInstance(), null)
//                .commitAllowingStateLoss();
//    }

    @Override
    public void onQuestionClicked(Question question) {
        QuestionDetailsActivity.start(QuestionsListActivity.this, question.getId());
    }

    @Override
    public void onFetchOfQuestionsSucceeded(List<Question> questions) {
        mViewMvc.bindQuestions(questions);
    }

    @Override
    public void onFetchOfQuestionsFailed() {
        mDialogManager.showRetainedDialogWithId(ServerErrorDialogFragment.newInstance(), "");
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(ServerErrorDialogFragment.newInstance(), null)
                .commitAllowingStateLoss();
    }
}
