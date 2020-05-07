package com.developer.chithlal.mjc.app.util;

import static com.developer.chithlal.mjc.app.util.Constants.INVALID_DATA_ERROR;
import static com.developer.chithlal.mjc.app.util.Constants.IS_USER_ENGINEER;
import static com.developer.chithlal.mjc.app.util.Constants.user_details_shared_pref_USER_ID;

import android.content.Context;
import android.content.SharedPreferences;

import com.developer.chithlal.mjc.app.engineer.Engineer;
import com.developer.chithlal.mjc.app.engineer.User;

public class SharedPreferenceManger {

    private Context mContext;
    private String mPreferenceId;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public SharedPreferenceManger(Context context, String preferenceId) {
        mContext = context;
        mPreferenceId = preferenceId;
    }

    public void initPreference(){
        mSharedPreferences = mContext.getSharedPreferences(mPreferenceId,Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    public void writeUserDetails(User user){
        mEditor.putString(user_details_shared_pref_USER_ID,user.getPhone());
        mEditor.putBoolean(IS_USER_ENGINEER,user.isUserMode());
        mEditor.commit();

    }
    public String getUserID(){
       return mSharedPreferences.getString(user_details_shared_pref_USER_ID,INVALID_DATA_ERROR);
    }

    public void write(String key,String data){
        mEditor.putString(key,data);
        mEditor.commit();
    }
    public void write(String key,int data){
        mEditor.putInt(key,data);
        mEditor.commit();
    }
    public void write(String key,boolean data){
        mEditor.putBoolean(key,data);
        mEditor.commit();
    }

    public String readString(String key){
        return mSharedPreferences.getString(key,INVALID_DATA_ERROR);
    }
    public int readInt(String key){
        return mSharedPreferences.getInt(key,0);
    }
    public boolean readBoolean(String key){
        return mSharedPreferences.getBoolean(key,false);
    }
}
