package com.chilik1020.grammartestsapp.ui.fragments.testsfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chilik1020.grammartestsapp.R;
import com.chilik1020.grammartestsapp.data.App;
import com.chilik1020.grammartestsapp.data.dao.LessonTestDao;
import com.chilik1020.grammartestsapp.data.dao.ScoreDao;
import com.chilik1020.grammartestsapp.data.db.AppGeneralDataDatabase;
import com.chilik1020.grammartestsapp.data.db.AppPersonalDataDatabase;
import com.chilik1020.grammartestsapp.ui.listeners.RecyclerViewClickListener;
import com.chilik1020.grammartestsapp.ui.adapters.LessonTestRecyclerViewAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LessonsTestsFragment extends Fragment {

    private LessonTestRecyclerViewAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_topics_list, container, false);

        adapter = new LessonTestRecyclerViewAdapter(getActivity());

        AppGeneralDataDatabase db = App.getInstance().getAppGeneralDataDatabase();
        LessonTestDao lessonTestDao = db.lessonTestsDao();

        AppPersonalDataDatabase dbPersonal = App.getInstance().getAppPersonalDataDatabase();
        ScoreDao scoreDao = dbPersonal.scoreDao();

        lessonTestDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(lessonTests -> adapter.setData(lessonTests));

        scoreDao.getSingleScoresByTypeResultAndTestId(2, -1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(rates -> adapter.setRates(rates));


        adapter.setmListener(listener);
        RecyclerView mRV = rootView.findViewById(R.id.my_recycler_view_chapters_tests_frag);
        RecyclerView.LayoutManager mRVManager = new LinearLayoutManager(getActivity());
        mRV.setLayoutManager(mRVManager);
        mRV.setAdapter(adapter);
        mRV.setHasFixedSize(true);
        return rootView;
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
