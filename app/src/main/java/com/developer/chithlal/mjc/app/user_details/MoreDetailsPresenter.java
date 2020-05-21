package com.developer.chithlal.mjc.app.user_details;

import static com.developer.chithlal.mjc.app.util.Constants.UPLOAD_TYPE_USER_ID_PROOF;
import static com.developer.chithlal.mjc.app.util.Constants.UPLOAD_TYPE_WORK_IMAGE;
import static com.smarteist.autoimageslider.SliderView.TAG;

import android.content.Context;
import android.util.Log;

import com.developer.chithlal.mjc.app.work.Work;
import com.developer.chithlal.mjc.app.engineer.User;
import com.developer.chithlal.mjc.app.firebase.UpdateDataUtil;
import com.developer.chithlal.mjc.app.firebase.UploadUtil;
import com.google.firebase.storage.StorageReference;

public class MoreDetailsPresenter implements MoreDetailContract.Presenter, UpdateDataUtil.firebaseDataUpdateListener,UploadUtil.UploadProgressListener {

    private MoreDetailContract.View mView;
   private UpdateDataUtil mUpdateDataUtil;
   private UploadUtil mUploadUtil;
   private int workQueue = 0;
    private User mUser;

    public MoreDetailsPresenter() {

    }

    @Override
    public void setContext(Context context) {

    }

    @Override
    public void onExtraDetailsCollected(User user) {
        mView.dismissDialog();
        mUpdateDataUtil = new UpdateDataUtil(this);
        mUploadUtil = new UploadUtil(this);
        if (user!=null) {
            mUser = user;
            mUpdateDataUtil.updateUserData(user);

           mView.showMessage("Updating data,Please wait!");

        }


    }

    private void uploadIDCard(User user) {

        mUploadUtil.uploadFile(mView.getIdProofUri(),null,UPLOAD_TYPE_USER_ID_PROOF,user.getUserId());
    }

    @Override
    public void onWorkAdded(User user) {
        mView.showMessage("Please wait, Adding works!");
        if (user!=null){
            if (user.getAllPreviousWorks()!=null){
                workQueue = user.getAllPreviousWorks().size();
                for (Work work:user.getAllPreviousWorks()){
                    mView.showMessage("Please wait, Adding works!");
                    mUpdateDataUtil.uploadWorkWithPhotos(work);
                    Log.d(TAG, "onWorkAdded: update util called");
                }
            }
            else {
                onUpdateFailed("Invalid Data!,Please check the details!");
            }
        }

    }

    @Override
    public void onRegistrationFinished(User user) {
            mView.onRegistrationFinished();
    }

    @Override
    public void startUI(MoreDetailContract.View view) {

        mView = view;
        mView.showExtraDetailsFragment();
    }


    @Override
    public void onUserUpdateCompleted(User user) {
        if (user.getIDProof()==null)
        {
                mView.dismissDialog();
                uploadIDCard(user);
                mView.showMessage("Uploading Data please wait!");

            }
            else {
                if (user.getIDProof().length()==0){
                    mView.dismissDialog();
                    uploadIDCard(user);
                    mView.showMessage("Uploading Data please wait!");
                }
                else {
                    mView.dismissDialog();

                }
        }




    }

    @Override
    public void onWorkUpdateCompleted(Work work) {
        Log.d(TAG, "onWorkUpdateCompleted: work added ");
        mView.dismissDialog();
        if(workQueue!=0) {
            workQueue--;
        }

        if (workQueue==0)
            onRegistrationFinished(mUser);

        Log.d(TAG, "onWorkUpdateCompleted: workQueue : "+workQueue);
    }

    @Override
    public void onUpdateFailed(String message) {
        mView.dismissDialog();
        mView.showToast(message);

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
    public void onUploadCompleted(String url, StorageReference storageReference,String UPLOAD_TYPE) {
        if (UPLOAD_TYPE.equals(UPLOAD_TYPE_USER_ID_PROOF)) {
            mUser.setIDProof(url);
            mUpdateDataUtil.updateUserData(mUser);
            mView.setIDProofURLAfterUploading(url);
            mView.showWorkDetailFragment();
        }
        else if (UPLOAD_TYPE.equals(UPLOAD_TYPE_WORK_IMAGE)){

        }
        mView.dismissDialog();
    }

    @Override
    public void onError(String message) {
        mView.dismissDialog();
        mView.showToast(message);

    }
}
