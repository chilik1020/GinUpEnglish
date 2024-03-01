package com.chilik1020.grammartestsapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.PurchaseInfo;
import com.chilik1020.grammartestsapp.R;
import com.chilik1020.grammartestsapp.data.App;
import com.chilik1020.grammartestsapp.data.StringUtil;
import com.chilik1020.grammartestsapp.data.model.Purchase;
import com.chilik1020.grammartestsapp.data.model.Purchases;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PurchaseActivity extends AppCompatActivity implements BillingProcessor.IBillingHandler {
    public final String LOG = "tagz";

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
    public void onProductPurchased(@NonNull String productId, @Nullable PurchaseInfo details) {
        Toast.makeText(this, "You've purchased full app, thank you!", Toast.LENGTH_SHORT).show();

        App.getInstance().getAppPersonalDataDatabase().purchaseDao().update(new Purchase(0L,ENG_A2_B2_ALL_TESTS, 1))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> App.getInstance().setIsAllTestPurchased(true));
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
