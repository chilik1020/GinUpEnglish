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

public class MainFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        View cardGrammar = rootView.findViewById(R.id.mainCardGrammar);
        cardGrammar.setOnClickListener(this);

        View cardTests = rootView.findViewById(R.id.mainCardTests);
        cardTests.setOnClickListener(this);

        View cardScore = rootView.findViewById(R.id.mainCardScore);
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
