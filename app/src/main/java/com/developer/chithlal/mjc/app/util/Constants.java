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
    public static final int PROFILE_IMAGE_REQUEST = 1024;


    public static final String ADD_IMAGE_BUTTON = "add_image_button";
    public static final String CONST_STRING_WRK_TYPE_FULLCONSTR = "Full Construction";
    public static final String CONST_STRING_WRK_TYPE_MAIN_CONC = "Main Concrete";
    public static final String CONST_STRING_WRK_TYPE_FINSHING = "Finishing Work";
    public static final Uri ADD_IMAGE_BUTTON_URI = null;
    public static final String user_details_shared_pref_key = "com.developer.chithlal.mjc.USER_DATA";
    public static final String user_details_shared_pref_USER_ID = "unique id";
    public static final String INVALID_DATA_ERROR = "INVALID DATA_ERROR";
    public static final String IS_USER_ENGINEER = "IS_CONSUMER";
    public static final int SUCCESS_MESSAGE = 100;

    public static final int CONSUMER_MODE = 111;
    public static final int ENGINEER_MODE = 112;
    public static final String KEY_USER_TYPE = "USER_TYPE";
    public static final String USER_OBJECT = "USER";


    //upload
    public static final String UPLOAD_TYPE_USER_PROFILE_IMAGE = "profile_";
    public static final String UPLOAD_TYPE_WORK_IMAGE = "work_";
    public static final String UPLOAD_TYPE_USER_ID_PROOF = "id_proof_";


    //FireBase Field constants
    public static final String FB_FIELD_ID_PROOF = "idproof";
    public static final String FB_FIELD_PROFILE_IMAGE  = "photo";


    //Resource
    public static final String NO_IMAGE_AVAILABLE_URL = "https://firebasestorage.googleapis.com/v0/b/mjc-5256d.appspot.com/o/app%2Fresourse%2Fimages%2Fno_works_added.png?alt=media&token=8c15d694-ea0e-4496-a319-7488d8dd23c2";


    public static final String RUPEE_SIGN = "₹";
    public static final String RATE_PER_SQRFT_STRING = "₹/Sq.Ft";

}
