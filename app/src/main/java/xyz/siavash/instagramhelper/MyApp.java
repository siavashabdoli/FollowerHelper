package xyz.siavash.instagramhelper;

import android.app.Application;

/**
 * Created by siavash on 6/19/16.
 */

public class MyApp extends Application{

    private static MyApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=(MyApp)getApplicationContext();
    }

    public static synchronized MyApp getInstance() {
        return mInstance;
    }
}
