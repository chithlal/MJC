package com.developer.chithlal.mjc.root.account_manager;

import static android.content.ContentValues.TAG;

import static com.developer.chithlal.mjc.app.util.Constants.user_details_shared_pref_USER_ID;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.developer.chithlal.mjc.app.database.DatabaseHandler;
import com.developer.chithlal.mjc.app.engineers_list.User;
import com.developer.chithlal.mjc.app.firebase.UserRepository;
import com.developer.chithlal.mjc.app.util.Constants;
import com.developer.chithlal.mjc.app.util.SharedPreferenceManger;

public class AccountManager implements AccountManagerInterface, UserRepository.UserUpdateListener {


    private User mUser;
    private SharedPreferenceManger mSharedPreferenceManger;
    private Context mContext;
    private DatabaseHandler mDatabaseHandler;

    public AccountManager(Context context) {
        mContext = context;
        mDatabaseHandler = new DatabaseHandler();

    }

    @Override
    public void loginUser(User user) {
        mUser = user;
        saveUser(user);
        mDatabaseHandler.saveUser(user);

    }

    @Override
    public User getUser() {
        mUser = mDatabaseHandler.getAdminUser(mContext);
        return mUser;
    }

    public void setUser(com.developer.chithlal.mjc.app.engineers_list.User user) {
        mUser = user;
    }

    @Override
    public void logoutUser() {
        mUser = null;
        mDatabaseHandler.deleteAdminUser(mContext);
        mSharedPreferenceManger = new SharedPreferenceManger(mContext,
                Constants.user_details_shared_pref_USER_ID);
        mSharedPreferenceManger.initPreference();
        mSharedPreferenceManger.writeUserDetails(null);

    }

    @Override
    public void saveUser(User user) {
        mSharedPreferenceManger = new SharedPreferenceManger(mContext,
                Constants.user_details_shared_pref_USER_ID);
        mSharedPreferenceManger.initPreference();
        mSharedPreferenceManger.writeUserDetails(user);
        mDatabaseHandler.saveUser(user);
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
        mSharedPreferenceManger = new SharedPreferenceManger(mContext,
                Constants.user_details_shared_pref_USER_ID);
        mSharedPreferenceManger.initPreference();

        return mSharedPreferenceManger.readString(user_details_shared_pref_USER_ID);
    }


    @Override
    public void onUserDataCollected(com.developer.chithlal.mjc.app.engineers_list.User user) {
        mUser = user;
        Toast.makeText(mContext, "User data updated", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onUserDataCollected: User data updated in DB ");
        mDatabaseHandler.saveUser(user);
    }

    @Override
    public void onUserUpdateFailed(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    public void updateUser() {

        String id = getUserId();
        UserRepository dataRepository = new UserRepository(this);
        dataRepository.getUserObject(id);
    }

}
