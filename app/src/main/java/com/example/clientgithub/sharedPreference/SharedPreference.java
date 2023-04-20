package com.example.clientgithub.sharedPreference;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.fragment.app.FragmentActivity;

public final class SharedPreference {

    public static final String TOKEN_KEY = "tokenKey";

    public static void saveToken(String token, String key, FragmentActivity activity) {
        SharedPreferences.Editor editor = activity.getPreferences(Context.MODE_PRIVATE).edit();

        editor.putString(key, token);
        editor.apply();
    }

    public static String loadToken(String key, FragmentActivity activity) {
       return activity.getPreferences(Context.MODE_PRIVATE).getString(key, "");
    }

}
