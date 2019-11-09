package com.chilik1020.grammartestsapp.ui.fragments.testsfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chilik1020.grammartestsapp.R;
import com.chilik1020.grammartestsapp.model.entities.Score;
import com.chilik1020.grammartestsapp.model.entities.Test;
import com.chilik1020.grammartestsapp.presenters.LessonTestsContract;
import com.chilik1020.grammartestsapp.presenters.LessonTestsPresenter;
import com.chilik1020.grammartestsapp.ui.listeners.RecyclerViewClickListener;
import com.chilik1020.grammartestsapp.ui.adapters.TestRecyclerViewAdapter;

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

public class LessonTestsFragment extends Fragment implements LessonTestsContract.View {

    @Inject
    public LessonTestsPresenter lessonTestsPresenter;

    @BindView(R.id.my_recycler_view_topic_tests_frag) RecyclerView mRV;

    private TestRecyclerViewAdapter adapter;

    private int lessonId;
    private int chapterId;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_topic_tests, container, false);
        ButterKnife.bind(this, rootView);
        AndroidSupportInjection.inject(this);

        Bundle bundle = getArguments();
        lessonId = bundle.getInt("lessonId");
        chapterId = bundle.getInt("chapterId");

        init();

        lessonTestsPresenter.attachView(this);
        lessonTestsPresenter.loadData(lessonId);

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lessonTestsPresenter.detachView();
    }

    @Override
    public void setData(List<Test> data) {
        adapter.setTests(data);
    }

    @Override
    public void setRates(List<Score> rates) {
        adapter.setRates(rates);
    }

    @Override
    public void setAppStatusPurchased(boolean isPurchased) {
        adapter.setAllTestPurchased(isPurchased);
    }

    private void init() {
        adapter = new TestRecyclerViewAdapter(getActivity());
        adapter.setmListener(listener);
        RecyclerView.LayoutManager mRVManager = new LinearLayoutManager(getActivity());
        mRV.setLayoutManager(mRVManager);
        mRV.setAdapter(adapter);
        mRV.setHasFixedSize(true);
    }

    private RecyclerViewClickListener listener = new RecyclerViewClickListener() {
        @Override
        public void onClick(View view, int position) {
            Bundle bundle = new Bundle();

            bundle.putInt("chapterId", chapterId);
            bundle.putInt("lessonId", lessonId);
            bundle.putInt("testId", adapter.getTests().get(position).get_id());
            bundle.putInt("number", 10);
            bundle.putInt("type", 2);

            final Test4VarFragment uFrag = new Test4VarFragment();
            uFrag.setArguments(bundle);

            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.frame_container, uFrag)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit();
        }
    };
}
