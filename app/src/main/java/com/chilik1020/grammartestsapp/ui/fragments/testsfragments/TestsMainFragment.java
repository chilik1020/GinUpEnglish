package com.chilik1020.grammartestsapp.ui.fragments.testsfragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.chilik1020.grammartestsapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestsMainFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.test10QTestsMainFragment) View card10q;
    @BindView(R.id.test20QTestsMainFragment) View card20q;
    @BindView(R.id.testChQTestsMainFragment) View cardChQ;
    @BindView(R.id.testLsQTestsMainFragment) View cardLsQ;

    @BindView(R.id.tv10QTestsMainFragment) TextView test10Q;
    @BindView(R.id.tv20QTestsMainFragment) TextView test20Q;
    @BindView(R.id.tvChQTestsMainFragment) TextView testChQ;
    @BindView(R.id.tvLsQTestsMainFragment) TextView testLsQ;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tests, container, false);
        ButterKnife.bind(this, rootView);

        init();

        return rootView;
    }

    private void init(){
        test10Q.setText(R.string.random10test);
        test20Q.setText(R.string.random20test);
        testChQ.setText(R.string.chapters);
        testLsQ.setText(R.string.lessons);

        card10q.setOnClickListener(this);
        card20q.setOnClickListener(this);
        cardChQ.setOnClickListener(this);
        cardLsQ.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Fragment frag = null;

        switch (view.getId()) {
            case R.id.test10QTestsMainFragment : {
                Bundle bundle = new Bundle();
                bundle.putInt("chapterId", -1);
                bundle.putInt("lessonId", -1);
                bundle.putInt("testId", -1);
                bundle.putInt("number", 10);
                bundle.putInt("type", 0);
                frag = new Test4VarFragment();
                frag.setArguments(bundle);
                break;
            }

            case R.id.test20QTestsMainFragment : {
                Bundle bundle = new Bundle();
                bundle.putInt("chapterId", -1);
                bundle.putInt("lessonId", -1);
                bundle.putInt("testId", -1);
                bundle.putInt("number", 20);
                bundle.putInt("type", 0);
                frag = new Test4VarFragment();
                frag.setArguments(bundle);
                break;
            }

            case R.id.testChQTestsMainFragment : {
                frag = new ChaptersTestsFragment();
                break;
            }

            case R.id.testLsQTestsMainFragment : {
                 frag = new LessonsTestsFragment();
                 break;
            }
        }
        if (frag != null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.frame_container, frag)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit();
        }

    }
}
