package com.developer.chithlal.mjc.app.UserProfile;

import android.content.Context;

import com.developer.chithlal.mjc.app.engineer.User;
import com.developer.chithlal.mjc.app.firebase.UpdateDataUtil;
import com.developer.chithlal.mjc.app.work.Work;

import java.util.ArrayList;
import java.util.List;

public class UserProfileModel implements UserProfileContract.Model,UpdateDataUtil.firebaseDataUpdateListener {
    Context mContext;
    private UpdateDataUtil mUpdateDataUtil;
    private UserProfileContract.Presenter mPresenter;

    @Override
    public void setContext(Context context) {
        mContext = context;

    }

    @Override
    public void setPresenter(UserProfileContract.Presenter presenter) {

        mPresenter = presenter;
    }

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public void setUser(User user) {
        mUpdateDataUtil = new UpdateDataUtil(this);
        mUpdateDataUtil.updateUserData(user);

    }

    @Override
    public List<String> getProfessionList() {
        List<String> professionList = new ArrayList<>();
        professionList.add("Software Engineer");
        professionList.add("Civil Engineer");
        professionList.add("Mechanical Engineer");
        professionList.add("Architect");
        professionList.add("Contractor");
        return professionList;
    }


    @Override
    public void onUserUpdateCompleted(User user) {
        mPresenter.onUserDataUpdateInServer(user);

    }

    @Override
    public void onWorkUpdateCompleted(Work work) {

    }

    @Override
    public void onUpdateFailed(String message) {

    }

    @Override
    public void onDeleteSuccess() {

    }

    @Override
    public void onDeleteFailed(String message) {

    }
}
