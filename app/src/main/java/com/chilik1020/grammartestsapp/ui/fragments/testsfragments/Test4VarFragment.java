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
import com.chilik1020.grammartestsapp.data.App;
import com.chilik1020.grammartestsapp.data.ScoreSaverUtil;
import com.chilik1020.grammartestsapp.data.dao.QuestionDao;
import com.chilik1020.grammartestsapp.data.model.Question;
import com.chilik1020.grammartestsapp.data.model.Score;
import com.chilik1020.grammartestsapp.data.db.AppGeneralDataDatabase;
import com.chilik1020.grammartestsapp.ui.adapters.Test4VarRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Test4VarFragment extends Fragment implements View.OnClickListener {

    private final Test4VarRecyclerViewAdapter adapter = new Test4VarRecyclerViewAdapter();

    private TextView tvTopic;

    private Score score;
    private int result;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_test4_var, container, false);

        tvTopic = rootView.findViewById(R.id.tvTest4VarAnswersTopic);

        getQuestionsForAdapter();

        RecyclerView mRV = rootView.findViewById(R.id.my_recycler_view_chapters_tests_frag);
        RecyclerView.LayoutManager mRVManager = new LinearLayoutManager(getActivity());
        mRV.setLayoutManager(mRVManager);
        mRV.setAdapter(adapter);
        mRV.setHasFixedSize(true);

        Button btnCheckTest = rootView.findViewById(R.id.btnCheckTest);
        btnCheckTest.setOnClickListener(this);
        return rootView;
    }

    private void getQuestionsForAdapter() {

        AppGeneralDataDatabase db = App.getInstance().getAppGeneralDataDatabase();
        QuestionDao questionDao = db.questionDao();

        int chapter = getArguments().getInt("chapterId");
        int lesson = getArguments().getInt("lessonId");
        int test = getArguments().getInt("testId");
        final int number = getArguments().getInt("number");
        int type = getArguments().getInt("type");

        score = new Score(0, type, 0, chapter, lesson, test, -1);

        switch (type) {
            case 0 :
                if (App.getInstance().isIsAllTestPurchased()) {
                    questionDao.getAllQuestions()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(questions -> adapter.setQuestions(getRandomQuestions(number,questions)));

                } else {
                    questionDao.getFreeQuestions()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(questions -> adapter.setQuestions(getRandomQuestions(number,questions)));
                }
                tvTopic.setText(number + " random questions");
                break;

            case 1 :
                if (App.getInstance().isIsAllTestPurchased()) {
                    questionDao.getAllQuestionsByChapterId(chapter)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(questions -> adapter.setQuestions(getRandomQuestions(number,questions)));
                }
                else {
                    questionDao.getFreeQuestionByChapterId(chapter)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(questions -> adapter.setQuestions(getRandomQuestions(number,questions)));
                }

                db.chapterDao().getChapterTopicById(chapter)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(s -> tvTopic.setText(s + "\n"+ number + " random questions"));
                break;

            case 2 :
                questionDao.getQuestionByTestId(test)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(questions -> adapter.setQuestions(getRandomQuestions(number,questions)));

                db.lessonTestsDao().getTopicById(lesson)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(s -> tvTopic.setText(s));
                break;
        }
    }

    private List<Question> getRandomQuestions(int number, List<Question> data) {
//        Log.d("tagz", "getRandomQuestions, questions size = " + data.size());
        if (data.size() <= 10)
            return data;

        List<Question> randomData = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            Question q = data.get((int) (Math.random()*data.size()));
            randomData.add(q);
            data.remove(q);
        }
        return randomData;
    }


    @Override
    public void onClick(View view) {
        int correctAnswerNumber = 0;
        for (Question q: adapter.getQuestions()) {
            if (q.getYourChoose() == q.getRightAnswer())
                correctAnswerNumber++;
        }

        result = correctAnswerNumber * 100 / adapter.getQuestions().size();

        score.setResult(result);

        Observable.just(score)
                .subscribeOn(Schedulers.io())
                .subscribe(ScoreSaverUtil::saveResultAndUpdateDBMeanResult);

        createAlertDialogResultTest(correctAnswerNumber);
    }

    private void createAlertDialogResultTest(int correctAnswerNumber) {
        AlertDialog.Builder adb = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        View dialogResult = getLayoutInflater().inflate(R.layout.dialog_result_test, null);
        adb.setView(dialogResult);

        adb.setCancelable(false);

        TextView tvResult = dialogResult.findViewById(R.id.tvResultTest);
        String tvResultString = correctAnswerNumber + " / " + adapter.getQuestions().size();
        tvResult.setText(tvResultString);

        ImageView ivTestResult = dialogResult.findViewById(R.id.ivTestResult);
        TextView tvGreat = dialogResult.findViewById(R.id.tvGreat);

        if (result == 0) {
            ivTestResult.setImageDrawable(getContext().getDrawable(R.drawable.stars_0));
            tvGreat.setText(R.string.result_0);
        }
        else if (result <= 30) {
            ivTestResult.setImageDrawable(getContext().getDrawable(R.drawable.stars_1));
            tvGreat.setText(R.string.result_1);
        }
        else if (result <= 50) {
            ivTestResult.setImageDrawable(getContext().getDrawable(R.drawable.stars_2));
            tvGreat.setText(R.string.result_2);
        }
        else if (result <= 70) {
            ivTestResult.setImageDrawable(getContext().getDrawable(R.drawable.stars_3));
            tvGreat.setText(R.string.result_3);
        }
        else if (result <= 99) {
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
