package com.chilik1020.grammartestsapp.data;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.chilik1020.grammartestsapp.data.dao.PurchaseDao;
import com.chilik1020.grammartestsapp.data.db.AppGeneralDataDatabase;
import com.chilik1020.grammartestsapp.data.db.AppPersonalDataDatabase;
import com.chilik1020.grammartestsapp.data.model.Purchases;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import androidx.room.Room;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class App extends Application {
    private static final String TAG = "tagz";

    private static final String ASSET_PERSONAL_DB_NAME = "eng_grammar_tests_personal_data_v1_0";
    private static final String ASSET_PERSONAL_DB_FULL_NAME = ASSET_PERSONAL_DB_NAME + ".db";
    private static final String APP_PERSONAL_DB_NAME = ASSET_PERSONAL_DB_NAME;

    private static final String ASSET_GENERAL_DB_NAME = "eng_grammar_tests_general_data_v1_0";
    private static final String ASSET_GENERAL_DB_FULL_NAME = ASSET_GENERAL_DB_NAME + ".db";
    private static final String APP_GENERAL_DB_NAME = ASSET_GENERAL_DB_NAME;

    private final String ENG_A2_B2_ALL_TESTS = Purchases.ENG_A2_B2_ALL_TESTS.getName();

    private static App instance;
    private AppGeneralDataDatabase appGeneralDataDatabase;
    private AppPersonalDataDatabase appPersonalDataDatabase;
    private static Context appContext;

    private boolean isAllTestPurchased = false;
    private int numberOfAppStarts = 0;
    private boolean isAppRatedByUser = false;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        instance = this;

        createDBs();

        checkPurchasedProduct();

        increaseNumberOfAppStarts();

        StringUtil.getPublicStr();
    }

    private void createDBs() {
        if (!appContext.getDatabasePath(APP_PERSONAL_DB_NAME).exists())
            copyAttachedDatabase(appContext, APP_PERSONAL_DB_NAME, ASSET_PERSONAL_DB_FULL_NAME);
//        else
//            Log.d(TAG, "DATABASE : " + APP_PERSONAL_DB_NAME + ", already exists");

        if (!appContext.getDatabasePath(APP_GENERAL_DB_NAME).exists())
            copyAttachedDatabase(appContext, APP_GENERAL_DB_NAME, ASSET_GENERAL_DB_FULL_NAME);
//        else
//            Log.d(TAG, "DATABASE : " + APP_GENERAL_DB_NAME + ", already exists");

        appPersonalDataDatabase = Room.databaseBuilder(this, AppPersonalDataDatabase.class, APP_PERSONAL_DB_NAME)
                .build();

        appGeneralDataDatabase = Room.databaseBuilder(this, AppGeneralDataDatabase.class, APP_GENERAL_DB_NAME)
                .build();

    }

    private void copyAttachedDatabase(Context context, String dbDest, String dbSrc) {
        final File dbPath = context.getDatabasePath(dbDest);

        dbPath.getParentFile().mkdirs();

        try {
            final InputStream inputStream = context.getAssets().open(dbSrc);
            final OutputStream output = new FileOutputStream(dbPath);

            byte[] buffer = new byte[8192];
            int length;

            while ((length = inputStream.read(buffer, 0, 8192)) > 0) {
                output.write(buffer, 0, length);
            }

            output.flush();
            output.close();
            inputStream.close();
            Log.d(TAG, "DATABASE " + dbDest + " was created");
        }
        catch (IOException e) {
            Log.d(TAG, "Failed to open file", e);
            e.printStackTrace();
        }
    }

    private void increaseNumberOfAppStarts() {
        instance.getAppPersonalDataDatabase().UserStatDao().getUserStatById(0L)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userStat -> {
                    numberOfAppStarts = userStat.getNumberOfAppStarts();
                    isAppRatedByUser = (userStat.getStatusRate() == 0) ? false : true;
                    userStat.setNumberOfAppStarts(numberOfAppStarts+1);

                    instance.getAppPersonalDataDatabase().UserStatDao().update(userStat)
                        .subscribeOn(Schedulers.computation())
                        .subscribe();
                });
    }

    private void checkPurchasedProduct() {
        PurchaseDao purchaseDao = appPersonalDataDatabase.purchaseDao();
        purchaseDao.getByName(ENG_A2_B2_ALL_TESTS)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(purchase -> isAllTestPurchased = (purchase.getStatus() != 0));
    }

    public static App getInstance() {
        return instance;
    }

    public AppGeneralDataDatabase getAppGeneralDataDatabase() {
        return appGeneralDataDatabase;
    }

    public AppPersonalDataDatabase getAppPersonalDataDatabase() {
        return appPersonalDataDatabase;
    }


    public boolean isIsAllTestPurchased() {
        return isAllTestPurchased;
    }

    public void setIsAllTestPurchased(boolean isAllTestPurchased) {
        App.getInstance().isAllTestPurchased = isAllTestPurchased;
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
