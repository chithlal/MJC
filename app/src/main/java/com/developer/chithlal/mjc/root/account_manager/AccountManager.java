package com.developer.chithlal.mjc.root.account_manager;

import static com.developer.chithlal.mjc.app.util.Constants.user_details_shared_pref_USER_ID;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.developer.chithlal.mjc.app.engineer.User;
import com.developer.chithlal.mjc.app.firebase.DataRepository;
import com.developer.chithlal.mjc.app.firebase.UserRepository;
import com.developer.chithlal.mjc.app.util.Constants;
import com.developer.chithlal.mjc.app.util.SharedPreferenceManger;

public class AccountManager implements AccountManagerInterface, UserRepository.UserUpdateListener {
    User mUser;
    SharedPreferenceManger mSharedPreferenceManger;
    private Context mContext;

    public AccountManager(Context context) {
        mContext = context;
    }

    @Override
    public void loginUser(User user) {
        mUser = user;

    }

    @Override
    public User getUser() {
        return mUser;
    }

    @Override
    public void logoutUser() {
        mUser = null;

    }

    @Override
    public void saveUser(User user) {
        mSharedPreferenceManger = new SharedPreferenceManger(mContext, Constants.user_details_shared_pref_USER_ID);
        mSharedPreferenceManger.initPreference();
        mSharedPreferenceManger.writeUserDetails(user);

    }

    @Override
    public void validateUser() {

    }

    @Override
    public boolean isLoggedIn() {
        return mUser != null;
    }

    @Override
    public String getUserId() {
        mSharedPreferenceManger = new SharedPreferenceManger(mContext, Constants.user_details_shared_pref_USER_ID);
        mSharedPreferenceManger.initPreference();

        return  mSharedPreferenceManger.readString(user_details_shared_pref_USER_ID);
    }


    @Override
    public void onUserDataCollected(com.developer.chithlal.mjc.app.engineer.User user) {
        mUser = user;
        Toast.makeText(mContext, "User data updated", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUserUpdateFailed(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }
    public void updateUser(){

        String id = getUserId();
        UserRepository dataRepository = new UserRepository(this);
        dataRepository.getUserObject(id);
    }

}
