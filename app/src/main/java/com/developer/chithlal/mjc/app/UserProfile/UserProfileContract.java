package com.developer.chithlal.mjc.app.UserProfile;

import android.content.Context;
import android.net.Uri;

import com.developer.chithlal.mjc.app.engineer.User;

import java.util.List;


public interface UserProfileContract {

    interface View{
        User getUser();
        void setUser(User user);
        String getIDCard();
        void showMessage(String message);
        void setPreviousWorkImages(List<SliderItem> images);
        void setProfessionList(List<String> professionList);

    }

    interface Presenter{
        void setUi(UserProfileContract.View view);
        void setContext(Context context);
        void onHireMeClick();
        void onEditEnabled();
        void saveUserData(User user);
        void onUserDataUpdateInServer(User user);
        void uploadIdCard(Uri imageUri,String userId);
        void onIdCardUploadSuccess(String message);
        void onIdCardUploadFailed(String message);
        void uploadProfileImage(Uri profileUri,String userId);
        void onProfileImageUploadSuccess(String message);
        void onProfileUploadFailed(String message);
    }

    interface Model  {
        void setContext(Context context);
        void setPresenter(UserProfileContract.Presenter presenter);
        User getUser();
        void setUser(User user);
        List<String> getProfessionList();
        void uploadIdCard(Uri imageUri,String userId);
        void uploadProfileImage(Uri profileUri,String userId);

    }
}
