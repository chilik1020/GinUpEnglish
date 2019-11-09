package com.chilik1020.grammartestsapp.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chilik1020.grammartestsapp.R;
import com.chilik1020.grammartestsapp.ui.fragments.grammarfragments.ChaptersFragment;
import com.chilik1020.grammartestsapp.ui.fragments.testsfragments.TestsMainFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.mainCardGrammar) View cardGrammar;
    @BindView(R.id.mainCardTests) View cardTests;
    @BindView(R.id.mainCardScore) View cardScore;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);

        cardGrammar.setOnClickListener(this);
        cardTests.setOnClickListener(this);
        cardScore.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        Fragment frag = null;
        switch (view.getId()) {

            case R.id.mainCardGrammar:
                frag = new ChaptersFragment();
                break;
            case R.id.mainCardTests:
                frag = new TestsMainFragment();
                break;

            case R.id.mainCardScore:
                frag = new ScoreFragment();
                break;
        }
        if (frag != null && getActivity() != null) {
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
