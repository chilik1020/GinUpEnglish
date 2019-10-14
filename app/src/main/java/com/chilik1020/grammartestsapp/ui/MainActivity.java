package com.chilik1020.grammartestsapp.ui;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.chilik1020.grammartestsapp.R;
import com.chilik1020.grammartestsapp.data.App;
import com.chilik1020.grammartestsapp.data.model.UserStat;
import com.chilik1020.grammartestsapp.ui.fragments.MainFragment;
import com.chilik1020.grammartestsapp.ui.listeners.NavigationButtonsListener;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_container, new MainFragment())
                .commit();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void navOnClick(View v) {
        checkIfTimeToRateApp();
        if (v.getId() == R.id.navRate) {
            rateApp();
        } else {
            NavigationButtonsListener nbl = new NavigationButtonsListener(this);
            nbl.onClick(v);
        }
    }

    @Override
    public void onBackPressed() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments.get(0).toString().startsWith("Test4VarAnswersFragment")) {}
        else if (fragments.get(0).toString().startsWith("Test4VarFragment")) {
            createAlertDialogExitFromTest();
        }
        else {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStack();
            } else {
                super.onBackPressed();
            }

        }
    }

    private void createAlertDialogExitFromTest() {
        AlertDialog.Builder adb = new AlertDialog.Builder(Objects.requireNonNull(this));
        View dialogResult = getLayoutInflater().inflate(R.layout.dialog_exit_from_test, null);
        adb.setView(dialogResult);

        AlertDialog ad = adb.create();
        ad.show();

        Button btnExitFromTestConfirm = dialogResult.findViewById(R.id.btnExitFromTestConfirm);
        btnExitFromTestConfirm.setOnClickListener(view -> {
            MainActivity.super.onBackPressed();
            ad.cancel();
        });
        Button btnExitFromTestCancel = dialogResult.findViewById(R.id.btnExitFromTestCancel);
        btnExitFromTestCancel.setOnClickListener(view -> ad.cancel());
    }

    private void checkIfTimeToRateApp() {
        boolean isAppRated = App.getInstance().isAppRatedByUser();
        int numberOfStarts = App.getInstance().getNumberOfAppStarts();

        if (isAppRated)
            return;

        App.getInstance().setIsAppRatedByUser(true);

        if (numberOfStarts % 5 != 0 || numberOfStarts == 0)
            return;

        AlertDialog.Builder adb = new AlertDialog.Builder(Objects.requireNonNull(this));
        View dialogResult = getLayoutInflater().inflate(R.layout.dialog_rate_app, null);
        adb.setView(dialogResult)
                .setCancelable(true);

        AlertDialog ad = adb.create();
        ad.show();

        Button btnAlreadyRate = dialogResult.findViewById(R.id.btnAlreadyRate);
        btnAlreadyRate.setOnClickListener(view -> {
            ad.cancel();
            App.getInstance().getAppPersonalDataDatabase().UserStatDao()
                    .update(new UserStat(0, numberOfStarts+1, 1))
                    .subscribeOn(Schedulers.computation())
                    .subscribe();
        });

        Button btnRateLater = dialogResult.findViewById(R.id.btnRateLater);
        btnRateLater.setOnClickListener(view -> {
            ad.cancel();
        });

        Button btnRateNow = dialogResult.findViewById(R.id.btnRateNow);
        btnRateNow.setOnClickListener(view -> {
            ad.cancel();
            rateApp();
        });
    }

    public void rateApp()
    {
        try
        {
            Intent rateIntent = rateIntentForUrl("market://details");
            startActivity(rateIntent);
        }
        catch (ActivityNotFoundException e)
        {
            Intent rateIntent = rateIntentForUrl("https://play.google.com/store/apps/details");
            startActivity(rateIntent);
        }
    }

    private Intent rateIntentForUrl(String url)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("%s?id=%s", url, getPackageName())));
        int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
        if (Build.VERSION.SDK_INT >= 21)
        {
            flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
        }
        else
        {
            //noinspection deprecation
            flags |= Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
        }
        intent.addFlags(flags);
        return intent;
    }
}
