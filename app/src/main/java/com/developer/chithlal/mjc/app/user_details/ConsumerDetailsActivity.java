package com.developer.chithlal.mjc.app.user_details;

import static com.developer.chithlal.mjc.app.util.Constants.GALLERY_REQUEST_CODE;
import static com.developer.chithlal.mjc.app.util.Constants.UPLOAD_TYPE_USER_ID_PROOF;
import static com.developer.chithlal.mjc.app.util.Constants.USER_OBJECT;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.developer.chithlal.mjc.R;
import com.developer.chithlal.mjc.app.Base.BaseActivity;
import com.developer.chithlal.mjc.app.dialogs.ImageSelectorUtil;
import com.developer.chithlal.mjc.app.work.Work;
import com.developer.chithlal.mjc.app.engineers_list.User;
import com.developer.chithlal.mjc.app.firebase.UpdateDataUtil;
import com.developer.chithlal.mjc.app.firebase.UploadUtil;
import com.developer.chithlal.mjc.app.util.ProgressViewUtil;
import com.developer.chithlal.mjc.databinding.ActivityConsumerDetailsBinding;
import com.developer.chithlal.mjc.root.account_manager.AccountManager;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.shape.CornerFamily;
import com.google.firebase.storage.StorageReference;

public class ConsumerDetailsActivity extends AppCompatActivity
        implements UpdateDataUtil.firebaseDataUpdateListener,UploadUtil.UploadProgressListener,ImageSelectorUtil.PhotoSelectorInterface {

    private ActivityConsumerDetailsBinding mBinding;
    private User mUser;
    private UpdateDataUtil mUpdateDataUtil;
    private UploadUtil mUploadUtil;
    private ProgressViewUtil mProgressViewUtil;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityConsumerDetailsBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        //get User object
        Intent intent = getIntent();
        if (intent != null)
            mUser = (User) intent.getSerializableExtra(USER_OBJECT);


        //setup progress view
        mProgressViewUtil = new ProgressViewUtil(this);
        //select image
        mBinding.tvMoreDetailsIdUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        //button click
        mBinding.btContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadIdProof(imageUri);
                mProgressViewUtil.showLoading("Uploading data,please wait!");
            }
        });
        mBinding.btSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUser!=null)
                postLogin(mUser);
                Intent intent = new Intent(getApplicationContext(), BaseActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void uploadIdProof(Uri imageUri) {
        mUpdateDataUtil = new UpdateDataUtil(this);
        mUploadUtil = new UploadUtil(this);

        mUploadUtil.uploadFile(imageUri,null,UPLOAD_TYPE_USER_ID_PROOF,mUser.getUserId());
    }

    private void selectImage() {
        ImageSelectorUtil imageSelectorUtil = new ImageSelectorUtil(this,GALLERY_REQUEST_CODE,this);
        imageSelectorUtil.openSelector();
    }


    void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    void customCardView(MaterialCardView cardView){
        float radius = 28;
        cardView.setShapeAppearanceModel(
                cardView.getShapeAppearanceModel()
                        .toBuilder()
                        .setTopLeftCorner(CornerFamily.ROUNDED,0)
                        .setTopRightCorner(CornerFamily.ROUNDED,0)
                        .setBottomRightCorner(CornerFamily.ROUNDED,radius)
                        .setBottomLeftCornerSize(radius)
                        .build());
    }

    @Override
    public void onUserUpdateCompleted(User user) {
        mUser = user;
        mProgressViewUtil.cancel();
        showMessage("Data updated!");
        showMessage("Registration completed,Login to continue!");
        finish();

    }

    @Override
    public void onWorkUpdateCompleted(Work work) {

    }

    @Override
    public void onUpdateFailed(String message) {
        mProgressViewUtil.cancel();
        showMessage("Something went wrong! Unable to update data");

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
        mProgressViewUtil.cancel();
            if (url!=null){
                mUser.setIDProof(url);
                updateUser(mUser);
            }
    }

    private void updateUser(User user) {
        mProgressViewUtil.showLoading("Updating Data!");
        mUpdateDataUtil.updateUserData(mUser);
    }

    @Override
    public void onError(String message) {
            showMessage("Something went wrong!, Upload failed");
    }

    @Override
    public void onPhotoSelected(Uri uri, int reqCode) {
        if (reqCode == GALLERY_REQUEST_CODE) {

            mBinding.tvMoreDetailsIdUpload.setVisibility(View.GONE);
                customCardView(mBinding.moreDetailsIdImageCard);
                mBinding.moreDetailsIdImageCard.setVisibility(View.VISIBLE);
                Glide.with(this)
                        .load(uri)
                        .centerCrop()
                        .placeholder(R.drawable.ic_image_black_24dp)
                        .error(R.drawable.ic_broken_image_black_24dp)
                        .into(mBinding.ivMoreDetailsIdImage);
                /* mBinding.ivUserProfileIdProofImage.setImageURI(selectedImage);*/
                imageUri = uri;

            } else {
                showMessage("Please select a photo!");
            }
    }
    void postLogin(User user) {
        AccountManager accountManager = new AccountManager(
                this); //Setting the user  for the entire app session
        accountManager.loginUser(user);
    }
    @Override
    public void onPhotoSelectionError(String message) {
        showMessage("Please select a photo!");
    }
}
