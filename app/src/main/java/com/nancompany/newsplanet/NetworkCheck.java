package com.nancompany.newsplanet;


import android.content.Context;
import android.net.ConnectivityManager;

public class NetworkCheck {
    private static Context context;

    public static boolean isNetworkAvailable() {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }

    public static void setContext(Context context) {
        NetworkCheck.context = context;
    }
}
