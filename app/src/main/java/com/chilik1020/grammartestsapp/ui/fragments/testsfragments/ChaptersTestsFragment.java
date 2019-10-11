package com.chilik1020.grammartestsapp.ui.fragments.testsfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chilik1020.grammartestsapp.R;
import com.chilik1020.grammartestsapp.data.App;
import com.chilik1020.grammartestsapp.data.dao.ChapterDao;
import com.chilik1020.grammartestsapp.data.db.AppGeneralDataDatabase;
import com.chilik1020.grammartestsapp.ui.listeners.RecyclerViewClickListener;
import com.chilik1020.grammartestsapp.ui.adapters.ChapterRecyclerViewAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class ChaptersTestsFragment extends Fragment {

    private final ChapterRecyclerViewAdapter adapter = new ChapterRecyclerViewAdapter();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_chapters_list, container, false);

        AppGeneralDataDatabase db = App.getInstance().getAppGeneralDataDatabase();
        ChapterDao chapterDao = db.chapterDao();

        adapter.setmListener(listener);

        chapterDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(chapters -> adapter.setData(chapters));

        RecyclerView mRV = rootView.findViewById(R.id.my_recycler_view_chapters_frag);
        RecyclerView.LayoutManager mRVManager = new LinearLayoutManager(getActivity());
        mRV.setLayoutManager(mRVManager);
        mRV.setAdapter(adapter);
        mRV.setHasFixedSize(true);
        return rootView;
    }

    private RecyclerViewClickListener listener = (view, position) -> {
        Bundle bundle = new Bundle();
        bundle.putSerializable("chapterId", adapter.getData().get(position));

        final ChapterTestsFragment uFrag = new ChapterTestsFragment();
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
