package com.developer.chithlal.mjc.app.UserProfile;

import static com.developer.chithlal.mjc.app.util.Constants.NO_IMAGE_AVAILABLE_URL;

import android.content.Context;
import android.net.Uri;


import com.developer.chithlal.mjc.app.engineers_list.User;
import com.developer.chithlal.mjc.app.work.Work;
import com.developer.chithlal.mjc.root.account_manager.AccountManager;

import java.util.ArrayList;
import java.util.List;


public class UserProfilePresenter implements UserProfileContract.Presenter {
    UserProfileContract.Model mModel;
    private UserProfileContract.View mView;
    private Context mContext;

    public UserProfilePresenter(UserProfileContract.Model model) {
        mModel = model;
        mModel.setPresenter(this);
    }

    @Override
    public void setUi(UserProfileContract.View view) {
        mView = view;
        mView.setUser(mView.getUser());
        if (!mView.getUser().isUserMode()) {
            setupPreviousWorkImage(mView.getUser());
        }

    }

    private void setupPreviousWorkImage(User user) {
        user.setAllPreviousWorks(null);
        mModel.resolveWorkData(user);

    }

    @Override
    public void setContext(Context context) {
        mContext = context;

    }

    @Override
    public void onHireMeClick() {

    }

    @Override
    public void onEditEnabled() {
        mView.setProfessionList(mModel.getProfessionList());
    }

    @Override
    public void saveUserData(User user) {
        mModel.setUser(user);
    }

    @Override
    public void onUserDataUpdateInServer(User user) {
        AccountManager accountManager = new AccountManager(mContext);
        accountManager.saveUser(user);
        accountManager.updateUser();
        mView.setUser(user);
        mView.showMessage("Data updated successfully!");
    }

    @Override
    public void uploadIdCard(Uri imageUri, String userId) {
        mModel.uploadIdCard(imageUri, userId);

    }

    @Override
    public void onIdCardUploadSuccess(String message) {
        mView.showMessage(message);
        mView.refreshIdCardImage();
    }

    @Override
    public void onIdCardUploadFailed(String message) {
        mView.showMessage(message);
    }

    @Override
    public void uploadProfileImage(Uri profileUri, String userId) {
        mModel.uploadProfileImage(profileUri, userId);

    }

    @Override
    public void onProfileImageUploadSuccess(String message) {
        mView.showMessage(message);
    }

    @Override
    public void onProfileUploadFailed(String message) {
        mView.showMessage(message);

    }

    @Override
    public void updateUserObject(String uid) {
        mModel.getUser(uid);
    }

    @Override
    public void onMessageArrived(String message) {
        mView.showMessage(message);
    }

    @Override
    public void onWorkDataResolved(User user) {
        List<SliderItem> slideList = new ArrayList<>();
        int i = 0;
        if (user.getAllPreviousWorks() != null && user.getAllPreviousWorks().size() > 0) {
            for (Work work : user.getAllPreviousWorks()) {

                if (work.getImages().size() != 0 && work.getImages() != null) {

                    slideList.add(new SliderItem(work.getImages().get(0), work.getWorkName(), i));
                    i++;

                }

            }

        } else {
            slideList.add(new SliderItem(NO_IMAGE_AVAILABLE_URL, "", 0));
        }
        mView.setPreviousWorkImages(slideList);
    }
}
