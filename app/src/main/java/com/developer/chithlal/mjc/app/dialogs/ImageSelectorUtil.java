package com.developer.chithlal.mjc.app.dialogs;

import android.content.Context;
import android.net.Uri;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.developer.chithlal.mjc.app.util.ProgressView;

public class ImageSelectorUtil implements ImageSelectorDialog.ImageSelectionDialogInterface {
    private Context mContext;
    private int mReqCode;
    private PhotoSelectorInterface mSelectorInterface;
    private FragmentManager mFragmentManager;
    private ImageSelectorDialog mImageSelectorDialog;

    public ImageSelectorUtil(Context context,int reqCode,PhotoSelectorInterface selectorInterface) {
        mContext = context;
        mReqCode = reqCode;
        mSelectorInterface = selectorInterface;
    }
    public void openSelector(){
        selectPhoto(mReqCode);
    }

    void selectPhoto(int reqCode){
        mFragmentManager = ((FragmentActivity)mContext).getSupportFragmentManager();
        mImageSelectorDialog = new ImageSelectorDialog(this);
        mImageSelectorDialog.show(mFragmentManager,"Select photo");
    }

    @Override
    public void onCameraImageCaptured(Uri imageUri) {
        mSelectorInterface.onPhotoSelected(imageUri,mReqCode);
    }

    @Override
    public void onPhotoPickedFromDevice(Uri imageUri) {
        mSelectorInterface.onPhotoSelected(imageUri,mReqCode);
    }

    @Override
    public void onImageSelectionError(String message) {
        mSelectorInterface.onPhotoSelectionError("No photo selected!");
    }
    public interface PhotoSelectorInterface{
        void onPhotoSelected(Uri uri,int reqCode);
        void onPhotoSelectionError(String message);
    }
}
