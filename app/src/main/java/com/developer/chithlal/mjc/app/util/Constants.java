package com.developer.chithlal.mjc.app.util;

import android.Manifest;
import android.net.Uri;

public  class Constants {
    public static final int LOGIN_TYPE_ENGINEER = 100;
    public static final int LOGIN_TYPE_CONSUME = 101;
    public static final String NO_ERROR = "1997";


    public static final String PERMISSION_STORAGE_READ = Manifest.permission.READ_EXTERNAL_STORAGE;
    public static final String PERMISSION_STORAGE_WRITE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    public static final String PERMISSION_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    public static final String PERMISSION_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    public static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;
    public static final String PERMISSION_CONTACT = Manifest.permission.READ_CONTACTS;

    public static final int PERMISSION_REQ_CODE = 123;

    public static final String  BUILDING_TYPE = "Building type";

    /*Bulding Spec Constants*/
    public static final String CONST_TYPE_FULL_CONST = "FULL CONSTRUCTION";
    public static final String CONST_TYPE_MAIN_CONC = "MAIN CONCRETE";
    public static final String CONST_TYPE_FINISHING = "FINISHING WORK";
    public static final String CONST_TYPE_DEFAULT = "NON SELECTED";

    //Profile user constants
    public static final int GALLERY_REQUEST_CODE = 1023;


    public static final String ADD_IMAGE_BUTTON = "add_image_button";
    public static final String CONST_STRING_WRK_TYPE_FULLCONSTR = "Full Construction";
    public static final String CONST_STRING_WRK_TYPE_MAIN_CONC = "Main Concrete";
    public static final String CONST_STRING_WRK_TYPE_FINSHING = "Finishing Work";
    public static final Uri ADD_IMAGE_BUTTON_URI = null;
    public static final String user_details_shared_pref_key = "com.developer.chithlal.mjc.USER_DATA";
    public static final String user_details_shared_pref_USER_ID = "unique id";
    public static final String INVALID_DATA_ERROR = "INVALID DATA_ERROR";
    public static final String IS_USER_ENGINEER = "IS_CONSUMER";

}
