package com.chilik1020.grammartestsapp.ui.fragments.grammarfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.chilik1020.grammartestsapp.R;
import com.chilik1020.grammartestsapp.data.App;
import com.chilik1020.grammartestsapp.data.dao.LessonGrammarDao;
import com.chilik1020.grammartestsapp.data.db.AppGeneralDataDatabase;
import com.chilik1020.grammartestsapp.ui.listeners.RecyclerViewClickListener;
import com.chilik1020.grammartestsapp.ui.adapters.LessonGrammarRecyclerViewAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LessonsFragment extends Fragment{

    private final LessonGrammarRecyclerViewAdapter adapter = new LessonGrammarRecyclerViewAdapter();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_topics_list, container, false);

        Bundle bundle = getArguments();
        int chapter = bundle.getInt("chapter");

        AppGeneralDataDatabase db = App.getInstance().getAppGeneralDataDatabase();
        LessonGrammarDao lessonGrammarDao = db.lessonGrammarDao();

        lessonGrammarDao.getLessonsByChapterId(chapter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(lessonGrammars -> adapter.setData(lessonGrammars));

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
