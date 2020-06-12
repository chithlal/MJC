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
    int requestPermission(String[] PERMISSION_ID){

        ActivityCompat.requestPermissions(mMActivity,
               PERMISSION_ID,
                PERMISSION_REQ_CODE);


        return 0;
    }
    public void checkAndRequestMultiplePermission(String[] permissions){
        String[] temp_perm_array=new String[5];
        if (permissions!=null){
            int i=0;
            for (String permission:permissions){

                        if (ActivityCompat.checkSelfPermission(mMActivity, permission) != PackageManager.PERMISSION_GRANTED) {
                            temp_perm_array[i]=permission;
                            i++;
                        }
            }

        }
        if (temp_perm_array.length!=0){
            requestPermission(temp_perm_array);
        }
    }
public boolean checkPermission(String[] permissions){
    if (mMActivity != null && permissions != null) {
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(mMActivity, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
    }
    return true;
}
}
