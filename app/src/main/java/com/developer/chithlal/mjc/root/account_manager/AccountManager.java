package com.developer.chithlal.mjc.root.account_manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.developer.chithlal.mjc.app.engineer.User;
import com.developer.chithlal.mjc.app.util.Constants;
import com.developer.chithlal.mjc.app.util.SharedPreferenceManger;

public class AccountManager implements AccountManagerInterface {
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
}
