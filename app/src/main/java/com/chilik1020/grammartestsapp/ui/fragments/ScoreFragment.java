package com.chilik1020.grammartestsapp.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.chilik1020.grammartestsapp.R;
import com.chilik1020.grammartestsapp.model.entities.Score;
import com.chilik1020.grammartestsapp.presenters.ScoreContract;
import com.chilik1020.grammartestsapp.presenters.ScorePresenter;
import com.chilik1020.grammartestsapp.ui.adapters.ScoreExpandableListAdapter;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class ScoreFragment extends Fragment implements ScoreContract.View, View.OnClickListener {

    @Inject
    public ScorePresenter scorePresenter;

    @BindView(R.id.elvScores) ExpandableListView elvScores;
    @BindView(R.id.btnResetScores) Button btnResetScores;

    private ScoreExpandableListAdapter scoreExpandableListAdapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_score, container, false);
        ButterKnife.bind(this, rootView);
        AndroidSupportInjection.inject(this);

        init();

        scorePresenter.attachView(this);
        scorePresenter.loadData();

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        scorePresenter.detachView();
    }

    private void init() {
        scoreExpandableListAdapter = new ScoreExpandableListAdapter(getActivity());
        elvScores.setAdapter(scoreExpandableListAdapter);
        btnResetScores.setOnClickListener(this);
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
            scorePresenter.resetScore();
            ad.cancel();
        });
        Button btnResetScoreCancel = dialogResult.findViewById(R.id.btnResetScoreCancel);
        btnResetScoreCancel.setOnClickListener(view -> ad.cancel());
    }

    @Override
    public void setParentsData(List<List<Score>> data) {
        scoreExpandableListAdapter.setmGroupsParent(data);
    }

    @Override
    public void setChildsData(List<List<Score>> data) {
        scoreExpandableListAdapter.setmGroupsChild(data);
    }

    @Override
    public void setChapters(Map<Integer, String> data) {
        scoreExpandableListAdapter.setChapters(data);
    }

    @Override
    public void setLessons(Map<Integer, String> data) {
        scoreExpandableListAdapter.setLessonTests(data);
    }
}
