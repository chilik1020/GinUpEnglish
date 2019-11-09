package com.chilik1020.grammartestsapp.ui.fragments.testsfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chilik1020.grammartestsapp.R;
import com.chilik1020.grammartestsapp.model.entities.LessonTest;
import com.chilik1020.grammartestsapp.model.entities.Score;
import com.chilik1020.grammartestsapp.presenters.LessonsTestContract;
import com.chilik1020.grammartestsapp.presenters.LessonsTestPresenter;
import com.chilik1020.grammartestsapp.ui.listeners.RecyclerViewClickListener;
import com.chilik1020.grammartestsapp.ui.adapters.LessonTestRecyclerViewAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class LessonsTestsFragment extends Fragment implements LessonsTestContract.View {

    @Inject
    public LessonsTestPresenter lessonsTestPresenter;

    @BindView(R.id.my_recycler_view_topics_list_frag) RecyclerView mRV;

    private LessonTestRecyclerViewAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_topics_list, container, false);
        ButterKnife.bind(this, rootView);
        AndroidSupportInjection.inject(this);

        init();

        lessonsTestPresenter.attachView(this);
        lessonsTestPresenter.loadData();

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lessonsTestPresenter.detachView();
    }

    @Override
    public void setData(List<LessonTest> data) {
        adapter.setData(data);
    }

    @Override
    public void setRates(List<Score> rates) {
        adapter.setRates(rates);
    }

    private void init() {
        adapter = new LessonTestRecyclerViewAdapter(getActivity());
        adapter.setmListener(listener);
        RecyclerView.LayoutManager mRVManager = new LinearLayoutManager(getActivity());
        mRV.setLayoutManager(mRVManager);
        mRV.setAdapter(adapter);
        mRV.setHasFixedSize(true);
    }

    private RecyclerViewClickListener listener = (view, position) -> {
        Bundle bundle = new Bundle();
        bundle.putSerializable("lessonId", adapter.getData().get(position).getId());
        bundle.putSerializable("chapterId", adapter.getData().get(position).getChapterId());

        final LessonTestsFragment uFrag = new LessonTestsFragment();
        uFrag.setArguments(bundle);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .replace(R.id.frame_container, uFrag)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(null)
                        .commit();
    };
}
