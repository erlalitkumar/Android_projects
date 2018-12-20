package com.techyourchance.journeytodependencyinjection.screens.questiondetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.techyourchance.journeytodependencyinjection.Constants;
import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionsListUseCase;
import com.techyourchance.journeytodependencyinjection.questions.QuestionWithBody;
import com.techyourchance.journeytodependencyinjection.screens.common.dialogs.DialogsManager;
import com.techyourchance.journeytodependencyinjection.screens.common.dialogs.ServerErrorDialogFragment;
import com.techyourchance.journeytodependencyinjection.networking.SingleQuestionResponseSchema;
import com.techyourchance.journeytodependencyinjection.networking.StackoverflowApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuestionDetailsActivity extends AppCompatActivity implements
        FetchQuestionDetailsUseCase.Listener, QuestionDetailsViewMvcImpl.Listener {

    public static final String EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID";

    public static void start(Context context, String questionId) {
        Intent intent = new Intent(context, QuestionDetailsActivity.class);
        intent.putExtra(EXTRA_QUESTION_ID, questionId);
        context.startActivity(intent);
    }

    //private TextView mTxtQuestionBody;

    private StackoverflowApi mStackoverflowApi;

    private Call<SingleQuestionResponseSchema> mCall;

    private String mQuestionId;

    private QuestionDetailsViewMvcImpl mViewMvc;

    private FetchQuestionDetailsUseCase mFetchQuestionDetailsUseCase;

    private DialogsManager mDialogsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.layout_question_details);
        mFetchQuestionDetailsUseCase = new FetchQuestionDetailsUseCase();
        mViewMvc = new QuestionDetailsViewMvcImpl(LayoutInflater.from(this), null);
        setContentView(mViewMvc.getRootView());

        //mTxtQuestionBody = findViewById(R.id.txt_question_body);

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Constants.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();

       // mStackoverflowApi = retrofit.create(StackoverflowApi.class);

        //noinspection ConstantConditions
        mQuestionId = getIntent().getExtras().getString(EXTRA_QUESTION_ID);
        mDialogsManager = new DialogsManager(getSupportFragmentManager());

    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewMvc.registerListener(this);
        mFetchQuestionDetailsUseCase.registerListener(this);
        mFetchQuestionDetailsUseCase.fetchQuestionDetailsAndNotify(mQuestionId);
//         mCall = mStackoverflowApi.questionDetails(mQuestionId);
//         mCall.enqueue(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mViewMvc.unregisterListener(this);
        mFetchQuestionDetailsUseCase.unregisterListener(this);
//        if (mCall != null) {
//            mCall.cancel();
//        }
    }
//
//     @Override
//     public void onResponse(Call<SingleQuestionResponseSchema> call, Response<SingleQuestionResponseSchema> response) {
//         SingleQuestionResponseSchema questionResponseSchema;
//         if (response.isSuccessful() && (questionResponseSchema = response.body()) != null) {
//             //String questionBody = questionResponseSchema.getQuestion().getBody();
//             if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//                // mTxtQuestionBody.setText(Html.fromHtml(questionBody,Html.FROM_HTML_MODE_LEGACY));
//                 mViewMvc.bindQuestion(questionResponseSchema.getQuestion());
//             }
////             else {
////                // mTxtQuestionBody.setText(Html.fromHtml(questionBody));
////             }
//         }else {
//             onFailure(call, null);
//         }
//     }

//     @Override
//     public void onFailure(Call<SingleQuestionResponseSchema> call, Throwable t) {
//         FragmentManager fragmentManager = getSupportFragmentManager();
//         fragmentManager.beginTransaction()
//                 .add(ServerErrorDialogFragment.newInstance(), null)
//                 .commitAllowingStateLoss();
//     }

    @Override
    public void onFetchOfQuestionDetailsSucceeded(QuestionWithBody question) {
        mViewMvc.bindQuestion(question);
    }

    @Override
    public void onFetchOfQuestionDetailsFailed() {
        mDialogsManager.showRetainedDialogWithId(ServerErrorDialogFragment.newInstance(), "");
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(ServerErrorDialogFragment.newInstance(), null)
                .commitAllowingStateLoss();
    }
}
