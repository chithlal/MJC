package com.developer.chithlal.mjc.app.util;

import static com.developer.chithlal.mjc.app.util.Constants.PERMISSION_REQ_CODE;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionManager {
    private Activity mMActivity;

    public PermissionManager(Activity mActivity) {
        mMActivity = mActivity;
    }


    public boolean checkPermissionIfNotRequest(String PERMISSION_ID){
        if (ContextCompat.checkSelfPermission(mMActivity, PERMISSION_ID)
                != PackageManager.PERMISSION_GRANTED) {

                requestPermission(PERMISSION_ID);

            return true;
        }
        else
        return true;
    }

    int requestPermission(String PERMISSION_ID){

        ActivityCompat.requestPermissions(mMActivity,
                new String[]{PERMISSION_ID},
                PERMISSION_REQ_CODE);


        return 0;
    }

}
