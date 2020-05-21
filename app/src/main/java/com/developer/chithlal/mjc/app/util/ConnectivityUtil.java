package com.developer.chithlal.mjc.app.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.util.Log;

public class ConnectivityUtil {
    private static final String DEBUG_TAG = "NetworkStatus:";
    private Context mContext;
    private boolean isWifiConn = false;
    private boolean isMobileConn = false;

    public ConnectivityUtil(Context context) {
        mContext = context;
        checkNetworkStatus();
    }

    private void checkNetworkStatus(){

        ConnectivityManager connMgr =
                (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        for (Network network : connMgr.getAllNetworks()) {
            NetworkInfo networkInfo = connMgr.getNetworkInfo(network);
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                isWifiConn |= networkInfo.isConnected();
            }
            if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                isMobileConn |= networkInfo.isConnected();
            }
        }
        Log.d(DEBUG_TAG, "Wifi connected: " + isWifiConn);
        Log.d(DEBUG_TAG, "Mobile connected: " + isMobileConn);

    }

    public boolean isNetworkConnected(){
        return isMobileConn||isWifiConn;
    }
}
