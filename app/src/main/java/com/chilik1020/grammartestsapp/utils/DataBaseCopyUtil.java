package com.chilik1020.grammartestsapp.utils;

import android.content.Context;
import android.util.Log;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import static com.chilik1020.grammartestsapp.utils.AppConstant.*;

public class DataBaseCopyUtil {

    public Context context;

    public DataBaseCopyUtil(Context context) {
        this.context = context;
    }

    public void createDBs() {
        if (!context.getDatabasePath(APP_PERSONAL_DB_NAME).exists())
            copyAttachedDatabase(context, APP_PERSONAL_DB_NAME, ASSET_PERSONAL_DB_FULL_NAME);

        if (!context.getDatabasePath(APP_GENERAL_DB_NAME).exists())
            copyAttachedDatabase(context, APP_GENERAL_DB_NAME, ASSET_GENERAL_DB_FULL_NAME);
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
}
