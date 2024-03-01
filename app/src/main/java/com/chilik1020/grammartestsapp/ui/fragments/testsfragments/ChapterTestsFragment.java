package com.chilik1020.grammartestsapp.ui.fragments.testsfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chilik1020.grammartestsapp.R;
import com.chilik1020.grammartestsapp.data.model.Chapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class ChapterTestsFragment extends Fragment implements View.OnClickListener {

    private Chapter chapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chapter_tests, container, false);

        Bundle bundle = getArguments();
        chapter = (Chapter) bundle.getSerializable("chapterId");
        String chapterTopic = chapter.getChapter();

        TextView test10Q = rootView.findViewById(R.id.tvTestChapter10q);
        test10Q.setText(chapterTopic +"\n10 random questions");

        TextView test20Q = rootView.findViewById(R.id.tvTestChapter20q);
        test20Q.setText(chapterTopic +"\n20 random questions");

        View card10q = rootView.findViewById(R.id.testChapter10Q);
        card10q.setOnClickListener(this);

        View card20q = rootView.findViewById(R.id.testChapter20Q);
        card20q.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        getActivity();

        Fragment frag;

        Bundle bundle = new Bundle();
        bundle.putInt("chapterId", chapter.get_id());
        bundle.putInt("lessonId", -1);
        bundle.putInt("testId", -1);
        bundle.putInt("type", 1);

        switch (view.getId()) {
            case R.id.testChapter10Q: {
                bundle.putInt("number", 10);
                break;
            }

            case R.id.testChapter20Q: {
                bundle.putInt("number", 20);
                break;
            }
        }

        frag = new Test4VarFragment();
        frag.setArguments(bundle);

        getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.frame_container, frag)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit();
    }
}