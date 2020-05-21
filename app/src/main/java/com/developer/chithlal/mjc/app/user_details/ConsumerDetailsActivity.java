package com.developer.chithlal.mjc.app.user_details;

import static com.developer.chithlal.mjc.app.util.Constants.GALLERY_REQUEST_CODE;
import static com.developer.chithlal.mjc.app.util.Constants.UPLOAD_TYPE_USER_ID_PROOF;
import static com.developer.chithlal.mjc.app.util.Constants.USER_OBJECT;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.developer.chithlal.mjc.R;
import com.developer.chithlal.mjc.app.work.Work;
import com.developer.chithlal.mjc.app.engineer.User;
import com.developer.chithlal.mjc.app.firebase.UpdateDataUtil;
import com.developer.chithlal.mjc.app.firebase.UploadUtil;
import com.developer.chithlal.mjc.app.util.ProgressViewUtil;
import com.developer.chithlal.mjc.databinding.ActivityConsumerDetailsBinding;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.shape.CornerFamily;
import com.google.firebase.storage.StorageReference;

public class ConsumerDetailsActivity extends AppCompatActivity
        implements UpdateDataUtil.firebaseDataUpdateListener,UploadUtil.UploadProgressListener {

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
        Intent intent = new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        // Launching the Intent
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE) {
            if (data != null) {
                Uri selectedImage = data.getData();
                mBinding.tvMoreDetailsIdUpload.setVisibility(View.GONE);
                customCardView(mBinding.moreDetailsIdImageCard);
                mBinding.moreDetailsIdImageCard.setVisibility(View.VISIBLE);
                Glide.with(this)
                        .load(selectedImage)
                        .centerCrop()
                        .placeholder(R.drawable.ic_image_black_24dp)
                        .error(R.drawable.ic_broken_image_black_24dp)
                        .into(mBinding.ivMoreDetailsIdImage);
                /* mBinding.ivUserProfileIdProofImage.setImageURI(selectedImage);*/
                imageUri = selectedImage;

            } else {
                showMessage("Please select a photo!");
            }
        }
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
}
