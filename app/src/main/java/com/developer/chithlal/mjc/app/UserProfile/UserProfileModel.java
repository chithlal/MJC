package com.developer.chithlal.mjc.app.UserProfile;

import static androidx.constraintlayout.widget.Constraints.TAG;

import static com.developer.chithlal.mjc.app.util.Constants.FB_FIELD_ID_PROOF;
import static com.developer.chithlal.mjc.app.util.Constants.FB_FIELD_PROFILE_IMAGE;
import static com.developer.chithlal.mjc.app.util.Constants.UPLOAD_TYPE_USER_ID_PROOF;
import static com.developer.chithlal.mjc.app.util.Constants.UPLOAD_TYPE_USER_PROFILE_IMAGE;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.developer.chithlal.mjc.app.engineer.User;
import com.developer.chithlal.mjc.app.firebase.UpdateDataUtil;
import com.developer.chithlal.mjc.app.firebase.UploadUtil;
import com.developer.chithlal.mjc.app.work.Work;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class UserProfileModel implements UserProfileContract.Model,UpdateDataUtil.firebaseDataUpdateListener,UploadUtil.UploadProgressListener,UpdateDataUtil.FieldUpdateListener {
    Context mContext;
    private UpdateDataUtil mUpdateDataUtil;
    private UserProfileContract.Presenter mPresenter;
    private String userId;


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
        UpdateDataUtil.firebaseDataUpdateListener firebaseDataUpdateListener= this;
        mUpdateDataUtil = new UpdateDataUtil(firebaseDataUpdateListener);
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
    public void uploadIdCard(Uri imageUri,String userId) {
        this.userId = userId;
        UploadUtil uploadUtil = new UploadUtil(this);
        uploadUtil.uploadFile(imageUri,null,UPLOAD_TYPE_USER_ID_PROOF,userId);

    }


    @Override
    public void uploadProfileImage(Uri profileUri,String userId) {
        this.userId = userId;
        UploadUtil uploadUtil = new UploadUtil(this);
        uploadUtil.uploadFile(profileUri,null,UPLOAD_TYPE_USER_PROFILE_IMAGE,userId);
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

    @Override
    public void onProgressChanged(int progress) {

    }

    @Override
    public void onUploadCompleted(String url, StorageReference storageReference,
            String UPLOAD_TYPE) {
        UpdateDataUtil.FieldUpdateListener fieldUpdateListener = this;
        UpdateDataUtil mUpdateUtil = new UpdateDataUtil(fieldUpdateListener);
            if (UPLOAD_TYPE.equals(UPLOAD_TYPE_USER_PROFILE_IMAGE)){
                mUpdateUtil.updateUserField(getUserId(),FB_FIELD_PROFILE_IMAGE,url);
                Log.d(TAG, "onUploadCompleted: Profile Pic updated "+url);

            }
            else if (UPLOAD_TYPE.equals(UPLOAD_TYPE_USER_ID_PROOF)){
                mUpdateUtil.updateUserField(getUserId(),FB_FIELD_ID_PROOF,url);
                Log.d(TAG, "onUploadCompleted: ID proof Pic updated "+url);

            }
    }

    @Override
    public void onError(String message) {
        mPresenter.onProfileUploadFailed("Unable to upload file..Please try again!");
    }

    private String getUserId(){
        return userId;
    }

    @Override
    public void onFieldUpdateSuccess(String field, String message) {
            if (field.equals(FB_FIELD_ID_PROOF)){
                mPresenter.onIdCardUploadSuccess("ID Image updated successfully!");
            }
            else if (field.equals(FB_FIELD_PROFILE_IMAGE)){
                mPresenter.onProfileImageUploadSuccess("Profile picture changed!");
            }
    }

    @Override
    public void onFieldUpdateFailed(String message) {

    }
}
