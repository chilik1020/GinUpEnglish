package com.chilik1020.grammartestsapp.utils;

import android.util.Log;

import com.chilik1020.grammartestsapp.model.entities.Purchases;
import com.chilik1020.grammartestsapp.model.repository.PurchaseRepository;
import com.chilik1020.grammartestsapp.model.repository.UserStatRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AppStatusUtil {

    @Inject
    public UserStatRepository userStatRepository;

    @Inject
    public PurchaseRepository purchaseRepository;

    public AppStatusUtil(UserStatRepository userStatRepository, PurchaseRepository purchaseRepository) {
        this.userStatRepository = userStatRepository;
        this.purchaseRepository = purchaseRepository;
    }

    private final String ENG_A2_B2_ALL_TESTS = Purchases.ENG_A2_B2_ALL_TESTS.getName();

    private boolean isAllTestPurchased = false;
    private boolean isAppRatedByUser = false;
    private int numberOfAppStarts = 0;

    public void increaseNumberOfAppStarts() {
        Disposable subscribe = userStatRepository.getUserStatById(0L)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userStat -> {
                    numberOfAppStarts = userStat.getNumberOfAppStarts();
                    isAppRatedByUser = userStat.getStatusRate() != 0;
                    userStat.setNumberOfAppStarts(numberOfAppStarts + 1);

                    userStatRepository.update(userStat)
                            .subscribeOn(Schedulers.computation())
                            .subscribe();
                });
    }

    public void checkPurchasedProduct() {
        if (purchaseRepository == null)
            Log.d(AppConstant.TAG, "null");

        Disposable subscribe = purchaseRepository.getByName(ENG_A2_B2_ALL_TESTS)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(purchase -> isAllTestPurchased = (purchase.getStatus() != 0));
    }

    public boolean isAllTestPurchased() {
        return isAllTestPurchased;
    }

    public void setIsAllTestPurchased(boolean isAllTestPurchased) {
        this.isAllTestPurchased = isAllTestPurchased;
    }

    public int getNumberOfAppStarts() {
        return numberOfAppStarts;
    }

    public boolean isAppRatedByUser() {
        return isAppRatedByUser;
    }

    public void setIsAppRatedByUser(boolean isAppRatedByUser) {
        this.isAppRatedByUser = isAppRatedByUser;
    }
}
