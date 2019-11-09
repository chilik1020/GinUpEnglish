package com.chilik1020.grammartestsapp.ui.fragments.testsfragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chilik1020.grammartestsapp.R;
import com.chilik1020.grammartestsapp.presenters.TestsContract;
import com.chilik1020.grammartestsapp.presenters.TestsPresenter;
import com.chilik1020.grammartestsapp.model.entities.Question;
import com.chilik1020.grammartestsapp.model.entities.Score;
import com.chilik1020.grammartestsapp.ui.adapters.Test4VarRecyclerViewAdapter;
import com.chilik1020.grammartestsapp.utils.AppConstant;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class Test4VarFragment extends Fragment implements TestsContract.View, View.OnClickListener {

    @Inject
    public TestsPresenter testsPresenter;

    @BindView(R.id.tvTest4VarTopic) TextView tvTopic;
    @BindView(R.id.my_recycler_view_test_4var_frag) RecyclerView mRV;
    @BindView(R.id.btnCheckTest) Button btnCheckTest;


    private Test4VarRecyclerViewAdapter adapter;
    private Score score;
    private int result;

    private int chapter;
    private int lesson;
    private int test;
    private int number;
    private int type;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_test4_var, container, false);
        ButterKnife.bind(this, rootView);
        AndroidSupportInjection.inject(this);

        init();

        testsPresenter.attachView(this);
        testsPresenter.loadData(type, number, chapter, lesson, test);

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        testsPresenter.detachView();
    }

    private void init() {
        chapter = getArguments().getInt("chapterId");
        lesson = getArguments().getInt("lessonId");
        test = getArguments().getInt("testId");
        number = getArguments().getInt("number");
        type = getArguments().getInt("type");

        score = new Score(0, type, 0, chapter, lesson, test, -1);

        adapter = new Test4VarRecyclerViewAdapter();
        RecyclerView.LayoutManager mRVManager = new LinearLayoutManager(getActivity());
        mRV.setLayoutManager(mRVManager);
        mRV.setAdapter(adapter);
        mRV.setHasFixedSize(true);
        btnCheckTest.setOnClickListener(this);
    }

    @Override
    public void setData(List<Question> data) {
        adapter.setQuestions(data);
    }

    @Override
    public void setTopic(String topic) {
        tvTopic.setText(topic);
    }

    @Override
    public void onClick(View view) {
        int correctAnswerNumber = 0;
        int numberOfQuestions = adapter.getQuestions().size();
        for (Question q: adapter.getQuestions()) {
            if (q.getYourChoose() == q.getRightAnswer())
                correctAnswerNumber++;
        }

        result = correctAnswerNumber * 100 / numberOfQuestions;

        score.setResult(result);

        testsPresenter.saveResult(score);

        createAlertDialogResultTest(correctAnswerNumber);
    }

    private void createAlertDialogResultTest(int correctAnswerNumber) {
        AlertDialog.Builder adb = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        View dialogResult = getLayoutInflater().inflate(R.layout.dialog_result_test, null);
        adb.setView(dialogResult);

        adb.setCancelable(false);

        TextView tvResultOfTest = dialogResult.findViewById(R.id.tvResultTest);
        String tvResultString = correctAnswerNumber + " / " + adapter.getQuestions().size();
        tvResultOfTest.setText(tvResultString);

        ImageView ivTestResult = dialogResult.findViewById(R.id.ivTestResult);
        TextView tvGreat = dialogResult.findViewById(R.id.tvGreat);

        if (result == AppConstant.resultFor0star) {
            ivTestResult.setImageDrawable(getContext().getDrawable(R.drawable.stars_0));
            tvGreat.setText(R.string.result_0);
        }
        else if (result <= AppConstant.resultFor1star) {
            ivTestResult.setImageDrawable(getContext().getDrawable(R.drawable.stars_1));
            tvGreat.setText(R.string.result_1);
        }
        else if (result <= AppConstant.resultFor2star) {
            ivTestResult.setImageDrawable(getContext().getDrawable(R.drawable.stars_2));
            tvGreat.setText(R.string.result_2);
        }
        else if (result <= AppConstant.resultFor3star) {
            ivTestResult.setImageDrawable(getContext().getDrawable(R.drawable.stars_3));
            tvGreat.setText(R.string.result_3);
        }
        else if (result <= AppConstant.resultFor4star) {
            ivTestResult.setImageDrawable(getContext().getDrawable(R.drawable.stars_4));
            tvGreat.setText(R.string.result_4);
        }
        else {
            ivTestResult.setImageDrawable(getContext().getDrawable(R.drawable.stars_5));
            tvGreat.setText(R.string.result_5);
        }

        AlertDialog ad = adb.create();
        ad.show();

        Button btnShowResultConfirm = dialogResult.findViewById(R.id.btnShowResultConfirm);
        btnShowResultConfirm.setOnClickListener(view -> {
            ad.cancel();
            Test4VarAnswersFragment fragment = new Test4VarAnswersFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("data", (ArrayList<? extends Parcelable>) adapter.getQuestions());
            bundle.putString("topic", (String) tvTopic.getText());
            fragment.setArguments(bundle);


            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.frame_container, fragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit();
        });
        Button btnExitFromTestCancel = dialogResult.findViewById(R.id.btnExitFromResult);
        btnExitFromTestCancel.setOnClickListener(view -> {
            ad.cancel();
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStackImmediate();
        });
    }
}
