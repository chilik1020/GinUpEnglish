package com.chilik1020.grammartestsapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.chilik1020.grammartestsapp.BuildConfig;
import com.chilik1020.grammartestsapp.R;
import com.chilik1020.grammartestsapp.App;
import com.chilik1020.grammartestsapp.model.repository.PurchaseRepository;
import com.chilik1020.grammartestsapp.utils.AppConstant;
import com.chilik1020.grammartestsapp.utils.AppStatusUtil;
import com.chilik1020.grammartestsapp.utils.StringUtil;
import com.chilik1020.grammartestsapp.model.entities.Purchase;
import com.chilik1020.grammartestsapp.model.entities.Purchases;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PurchaseActivity extends AppCompatActivity implements BillingProcessor.IBillingHandler {
    public final String LOG = AppConstant.TAG;

    @Inject
    public AppStatusUtil appStatusUtil;

    @Inject
    public PurchaseRepository purchaseRepository;

    BillingProcessor bp;

    private final String ENG_A2_B2_ALL_TESTS = Purchases.ENG_A2_B2_ALL_TESTS.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        bp = new BillingProcessor(this, StringUtil.getPublicStr(), this);
        bp.initialize();
        View btnAllTests = findViewById(R.id.btnAllTests);
        btnAllTests.setOnClickListener(purchaseBtnListener);
    }

    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {

        Log.d(AppConstant.TAG, "Details: " + details.purchaseInfo);
        Toast.makeText(this, "You've purchased full app, thank you!", Toast.LENGTH_SHORT).show();

        Disposable subscribe = purchaseRepository.update(new Purchase(0L, ENG_A2_B2_ALL_TESTS, 1))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> appStatusUtil.setIsAllTestPurchased(true));
    }

    @Override
    public void onPurchaseHistoryRestored() {
        Log.d(LOG, "onPurchaseHistoryRestored");
    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {
        Log.d(LOG, "On Billing error!");
    }

    @Override
    public void onBillingInitialized() {
        Log.d(LOG, "On Billing Initialized");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!bp.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onDestroy() {
        if (bp != null) {
            bp.release();
        }
        super.onDestroy();
    }

    View.OnClickListener purchaseBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btnAllTests) {
                bp.purchase(PurchaseActivity.this, ENG_A2_B2_ALL_TESTS);
            }
        }
    };
}
