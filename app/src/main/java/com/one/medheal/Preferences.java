package com.one.medheal;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {

    static final String KEY_FIRST_RUN = "firstRun";
    static final String KEY_LOGIN = "login";

    private static SharedPreferences getSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setFirstRun(Context context, boolean run){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putBoolean(KEY_FIRST_RUN, run);
        editor.apply();
    }

    public static void setLogged(Context context, boolean login){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putBoolean(KEY_LOGIN, login);
        editor.apply();
    }

    public static boolean getStatusLogin(Context context){
        return getSharedPreference(context).getBoolean(KEY_LOGIN, false);
    }

    public static boolean getStatusFirstRun(Context context){
        return getSharedPreference(context).getBoolean(KEY_FIRST_RUN, true);
    }

}
