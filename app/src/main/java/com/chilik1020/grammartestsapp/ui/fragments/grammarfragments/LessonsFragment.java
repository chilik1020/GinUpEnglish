package com.chilik1020.grammartestsapp.ui.fragments.grammarfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.chilik1020.grammartestsapp.R;
import com.chilik1020.grammartestsapp.model.entities.LessonGrammar;
import com.chilik1020.grammartestsapp.presenters.LessonGrammarContract;
import com.chilik1020.grammartestsapp.presenters.LessonsGrammarPresenter;
import com.chilik1020.grammartestsapp.ui.listeners.RecyclerViewClickListener;
import com.chilik1020.grammartestsapp.ui.adapters.LessonGrammarRecyclerViewAdapter;

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

public class LessonsFragment extends Fragment implements LessonGrammarContract.View {

    @Inject
    public LessonsGrammarPresenter lessonsGrammarPresenter;

    @BindView(R.id.my_recycler_view_topics_list_frag) RecyclerView mRV;

    private LessonGrammarRecyclerViewAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_topics_list, container, false);
        ButterKnife.bind(this, rootView);
        AndroidSupportInjection.inject(this);

        Bundle bundle = getArguments();
        long chapterId = bundle.getInt("chapter");

        init();

        lessonsGrammarPresenter.attachView(this);
        lessonsGrammarPresenter.loadData(chapterId);

        return rootView;
    }

    private void init() {
        adapter = new LessonGrammarRecyclerViewAdapter();
        adapter.setmListener(listener);
        RecyclerView.LayoutManager mRVManager = new LinearLayoutManager(getActivity());
        mRV.setLayoutManager(mRVManager);
        mRV.setAdapter(adapter);
        mRV.setHasFixedSize(true);
    }

    @Override
    public void setData(List<LessonGrammar> data) {
        adapter.setData(data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lessonsGrammarPresenter.detachView();
    }

    private RecyclerViewClickListener listener = (view, position) -> {

        Bundle bundle = new Bundle();
        bundle.putSerializable("unit", adapter.getData().get(position));

        final UnitFragment uFrag = new UnitFragment();
        uFrag.setArguments(bundle);

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_container, uFrag)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(null)
                        .commit();
    };
}
