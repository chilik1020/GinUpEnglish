package com.chilik1020.grammartestsapp.ui.fragments.testsfragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chilik1020.grammartestsapp.R;
import com.chilik1020.grammartestsapp.data.App;
import com.chilik1020.grammartestsapp.data.dao.ScoreDao;
import com.chilik1020.grammartestsapp.data.dao.TestDao;
import com.chilik1020.grammartestsapp.data.db.AppGeneralDataDatabase;
import com.chilik1020.grammartestsapp.data.db.AppPersonalDataDatabase;
import com.chilik1020.grammartestsapp.ui.listeners.RecyclerViewClickListener;
import com.chilik1020.grammartestsapp.ui.adapters.TestRecyclerViewAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LessonTestsFragment extends Fragment {

    private TestRecyclerViewAdapter adapter;

    private int lessonId;
    private int chapterId;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_topic_tests, container, false);

        Bundle bundle = getArguments();
        lessonId = bundle.getInt("lessonId");
        chapterId = bundle.getInt("chapterId");

        adapter = new TestRecyclerViewAdapter(getActivity());

        AppGeneralDataDatabase db = App.getInstance().getAppGeneralDataDatabase();
        TestDao testDao = db.testDao();

        AppPersonalDataDatabase dbPersonal = App.getInstance().getAppPersonalDataDatabase();
        ScoreDao scoreDao = dbPersonal.scoreDao();

        testDao.getSingleTestsByLessonId(lessonId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tests -> adapter.setTests(tests));

        scoreDao.getSingleScoresByTypeResultAndLessonIdAndPositiveTestId(2, lessonId)
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
