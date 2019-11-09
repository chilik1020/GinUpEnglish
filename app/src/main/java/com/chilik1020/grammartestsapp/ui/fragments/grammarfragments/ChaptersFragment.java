package com.chilik1020.grammartestsapp.ui.fragments.grammarfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chilik1020.grammartestsapp.R;
import com.chilik1020.grammartestsapp.model.entities.Chapter;
import com.chilik1020.grammartestsapp.presenters.ChaptersPresenter;
import com.chilik1020.grammartestsapp.presenters.ChapterContract;
import com.chilik1020.grammartestsapp.ui.listeners.RecyclerViewClickListener;
import com.chilik1020.grammartestsapp.ui.adapters.ChapterRecyclerViewAdapter;

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

public class ChaptersFragment extends Fragment implements ChapterContract.View {

    @Inject
    public ChaptersPresenter chaptersPresenter;

    @BindView(R.id.my_recycler_view_chapters_frag) RecyclerView mRV;

    private ChapterRecyclerViewAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chapters_list, container, false);
        ButterKnife.bind(this, rootView);
        AndroidSupportInjection.inject(this);

        init();

        chaptersPresenter.attachView(this);
        chaptersPresenter.loadData();

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        chaptersPresenter.detachView();
    }

    @Override
    public void setData(List<Chapter> data) {
        adapter.setData(data);
    }

    private void init(){
        adapter = new ChapterRecyclerViewAdapter();
        adapter.setmListener(listener);
        RecyclerView.LayoutManager mRVManager = new LinearLayoutManager(getActivity());
        mRV.setLayoutManager(mRVManager);
        mRV.setAdapter(adapter);
        mRV.setHasFixedSize(true);
    }

    private RecyclerViewClickListener listener = (view, position) -> {

        Bundle bundle = new Bundle();
        bundle.putInt("chapter", adapter.getData().get(position).get_id());

        final LessonsFragment uFrag = new LessonsFragment();
        uFrag.setArguments(bundle);

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .replace(R.id.frame_container, uFrag)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack("LessonsFragment")
                        .commit();
    };
}
