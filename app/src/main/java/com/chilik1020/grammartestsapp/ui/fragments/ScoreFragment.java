package com.chilik1020.grammartestsapp.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.chilik1020.grammartestsapp.R;
import com.chilik1020.grammartestsapp.data.App;
import com.chilik1020.grammartestsapp.data.dao.ChapterDao;
import com.chilik1020.grammartestsapp.data.dao.LessonTestDao;
import com.chilik1020.grammartestsapp.data.dao.ScoreDao;
import com.chilik1020.grammartestsapp.data.db.AppGeneralDataDatabase;
import com.chilik1020.grammartestsapp.data.db.AppPersonalDataDatabase;
import com.chilik1020.grammartestsapp.data.model.Chapter;
import com.chilik1020.grammartestsapp.data.model.LessonTest;
import com.chilik1020.grammartestsapp.data.model.Score;
import com.chilik1020.grammartestsapp.ui.adapters.ScoreExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ScoreFragment extends Fragment implements View.OnClickListener {
    private ScoreDao scoreDao;
    private ChapterDao chapterDao;
    private LessonTestDao lessonTestDao;
    private ScoreExpandableListAdapter scoreExpandableListAdapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_score, container, false);

        AppPersonalDataDatabase db = App.getInstance().getAppPersonalDataDatabase();
        AppGeneralDataDatabase generalDb = App.getInstance().getAppGeneralDataDatabase();
        scoreDao = db.scoreDao();
        chapterDao = generalDb.chapterDao();
        lessonTestDao = generalDb.lessonTestsDao();

        Button btnResetScores = rootView.findViewById(R.id.btnResetScores);
        btnResetScores.setOnClickListener(this);

        ExpandableListView elvScores = rootView.findViewById(R.id.elvScores);

        scoreExpandableListAdapter = new ScoreExpandableListAdapter(getActivity());

        loadScoresFromDB();

        elvScores.setAdapter(scoreExpandableListAdapter);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        AlertDialog.Builder adb = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        View dialogResult = getLayoutInflater().inflate(R.layout.dialog_reset_score, null);
        adb.setView(dialogResult)
            .setCancelable(true);

        AlertDialog ad = adb.create();
        ad.show();

        Button btnResetScoreConfirm = dialogResult.findViewById(R.id.btnResetScoreConfirm);
        btnResetScoreConfirm.setOnClickListener(view -> {
            resetScores();
            ad.cancel();
        });
        Button btnResetScoreCancel = dialogResult.findViewById(R.id.btnResetScoreCancel);
        btnResetScoreCancel.setOnClickListener(view -> ad.cancel());
    }

    private void resetScores() {
        scoreDao.deleteAllScores()
                .subscribeOn(Schedulers.io())
                .subscribe(() -> loadScoresFromDB());
    }

    private void loadScoresFromDB() {
        List<List<Score>> groupData = new ArrayList<>();
        List<List<Score>> childData = new ArrayList<>();
        scoreDao.getSingleScoresByTestTypeAndTypeResult(0,1)
                .mergeWith(scoreDao.getSingleScoresByTestTypeAndTypeResultAndChapterId(1,1,-1))
                .mergeWith(scoreDao.getSingleScoresByTestTypeAndTypeResultAndLessonId(2,1, -1))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    groupData.add(item);
                    scoreExpandableListAdapter.setmGroupsParent(groupData);
                });

        scoreDao.getSingleScoresByTestTypeAndTypeResult(0,0)
                .mergeWith(scoreDao.getSingleScoresByTestTypeAndTypeResultWhereChapterIdPositive(1,1))
                .mergeWith(scoreDao.getSingleScoresByTestTypeAndTypeResultWhereLessonIdPositive(2,1))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    childData.add(item);
                    scoreExpandableListAdapter.setmGroupsChild(childData);
                });

        chapterDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(chapters -> {
                    Map<Integer, String> chaptersTemp = new HashMap<>();
                    for (Chapter ch : chapters) {
                        chaptersTemp.put(ch.get_id(), ch.getChapter());
                    }
                    scoreExpandableListAdapter.setChapters(chaptersTemp);
                });

        lessonTestDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(lessonTests -> {
                    Map<Integer, String> lessonsTestsTemp = new HashMap<>();
                    for (LessonTest lt : lessonTests) {
                        lessonsTestsTemp.put(lt.getId(), lt.getTopic());
                    }
                    scoreExpandableListAdapter.setLessonTests(lessonsTestsTemp);
                });
    }
}
