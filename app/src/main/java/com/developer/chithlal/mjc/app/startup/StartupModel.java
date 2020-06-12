package com.developer.chithlal.mjc.app.startup;

import static android.content.ContentValues.TAG;

import static com.developer.chithlal.mjc.app.util.Constants.INVALID_DATA_ERROR;
import static com.developer.chithlal.mjc.app.util.Constants.user_details_shared_pref_USER_ID;

import android.content.Context;
import android.util.Log;

import com.developer.chithlal.mjc.app.engineer.User;
import com.developer.chithlal.mjc.app.firebase.UserRepository;
import com.developer.chithlal.mjc.app.util.SharedPreferenceManger;

public class StartupModel implements StartupContract.Model,UserRepository.UserUpdateListener {

    private StartupContract.Presenter mPresenter;
    private String userId = null;
    private Context mContext;


    public StartupModel() {

    }

    @Override
    public void setupUserForLogin() {
        if (isLoggedIn())
            if (userId!=null) {
                UserRepository userRepository = new UserRepository(this);
                userRepository.getUserObject(userId);
            }
            else mPresenter.onPastUserNotAvailable();
        else mPresenter.onPastUserNotAvailable();

    }
    public void setPresenter(StartupContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onUserDataCollected(User user) {
        mPresenter.loginWithLastUser(user);
        Log.d(TAG, "onUserDataCollected: "+user.getUserId());
    }

    @Override
    public void onUserUpdateFailed(String message) {
        mPresenter.passMessage(message);
    }
    private boolean isLoggedIn(){
        SharedPreferenceManger sharedPreferenceManger = new SharedPreferenceManger(mContext,user_details_shared_pref_USER_ID);
        sharedPreferenceManger.initPreference();

        String uid = sharedPreferenceManger.getUserID();
        if (uid.equals(INVALID_DATA_ERROR)){
            userId = null;
            mPresenter.passMessage("No user logged in!");
            return false;
        }
        else {
            userId = uid;
            Log.d(TAG, "isLoggedIn: User present "+uid);
            return  true;
        }

    }

    public void setContext(Context context) {
        mContext = context;
    }
}

