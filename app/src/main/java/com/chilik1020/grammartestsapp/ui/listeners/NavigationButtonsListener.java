package com.chilik1020.grammartestsapp.ui.listeners;

import android.content.Intent;
import android.view.View;

import com.chilik1020.grammartestsapp.R;
import com.chilik1020.grammartestsapp.ui.activities.PurchaseActivity;
import com.chilik1020.grammartestsapp.ui.fragments.ScoreFragment;
import com.chilik1020.grammartestsapp.ui.fragments.grammarfragments.ChaptersFragment;
import com.chilik1020.grammartestsapp.ui.fragments.testsfragments.TestsMainFragment;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public class NavigationButtonsListener implements View.OnClickListener {

    private FragmentActivity act;

    public NavigationButtonsListener(FragmentActivity act) {
        this.act = act;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.navUpgrade:
                Intent intent = new Intent(act, PurchaseActivity.class);
                act.startActivity(intent);
                break;

            case R.id.navScore:
                clearBackStack();
                act.getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .replace(R.id.frame_container, new ScoreFragment())
                        .addToBackStack(null)
                        .commit();
                break;

            case R.id.navTests:
                clearBackStack();
                act.getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .replace(R.id.frame_container, new TestsMainFragment())
                        .addToBackStack(null)
                        .commit();
                break;

            case R.id.navGrammar:
                clearBackStack();
                act.getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .replace(R.id.frame_container, new ChaptersFragment())
                        .addToBackStack(null)
                        .commit();
                break;

            case R.id.btnExitTest:
                clearBackStack();
                act.getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .replace(R.id.frame_container, new TestsMainFragment())
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }

    private void clearBackStack(){
        FragmentManager fm = act.getSupportFragmentManager();
        int count = fm.getBackStackEntryCount();
        for(int i = 0; i < count; ++i) {
            fm.popBackStack();
        }
    }
}
