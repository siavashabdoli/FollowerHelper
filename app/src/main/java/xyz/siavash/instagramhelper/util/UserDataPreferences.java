package xyz.siavash.instagramhelper.util;

import android.content.Context;
import android.preference.PreferenceManager;

import xyz.siavash.instagramhelper.MyApp;

/**
 * Created by siavash on 6/19/16.
 */

public class UserDataPreferences {


    private final static String TOKEN_KEY="userToken";

    private UserDataPreferences(Context context){
    }

    public static void setToken(String token){
        PreferenceManager.getDefaultSharedPreferences(MyApp.getInstance()).edit().putString(TOKEN_KEY,token).apply();
    }
    public static String getToken(){
        return PreferenceManager.getDefaultSharedPreferences(MyApp.getInstance()).getString(TOKEN_KEY,null);
    }
}
