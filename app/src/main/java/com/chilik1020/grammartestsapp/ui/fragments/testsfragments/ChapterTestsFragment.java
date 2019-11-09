package com.chilik1020.grammartestsapp.ui.fragments.testsfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chilik1020.grammartestsapp.R;
import com.chilik1020.grammartestsapp.model.entities.Chapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChapterTestsFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.tvTestChapter10q) TextView test10Q;
    @BindView(R.id.tvTestChapter20q) TextView test20Q;

    @BindView(R.id.testChapter10Q) View card10q;
    @BindView(R.id.testChapter20Q) View card20q;

    private Chapter chapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chapter_tests, container, false);
        ButterKnife.bind(this, rootView);
        Bundle bundle = getArguments();
        chapter = (Chapter) bundle.getSerializable("chapterId");
        String chapterTopic = chapter.getChapter();

        test10Q.setText(chapterTopic +"\n10 random questions");
        test20Q.setText(chapterTopic +"\n20 random questions");

        card10q.setOnClickListener(this);
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