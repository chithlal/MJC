package com.developer.chithlal.mjc.app.dialogs;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

import static com.developer.chithlal.mjc.app.util.Constants.PERMISSION_CAMERA;
import static com.developer.chithlal.mjc.app.util.Constants.PERMISSION_STORAGE_READ;
import static com.developer.chithlal.mjc.app.util.Constants.PERMISSION_STORAGE_WRITE;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;

import com.developer.chithlal.mjc.R;
import com.developer.chithlal.mjc.app.util.PermissionManager;
import com.developer.chithlal.mjc.databinding.DialogImageSelectorBinding;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageSelectorDialog extends DialogFragment {

    private static final int REQ_CODE_GALLERY = 101;
    public static final int REQ_CODE_CAMERA = 102;
    DialogImageSelectorBinding mBinding;
    private ImageSelectionDialogInterface mDialogInterface;
    private  PermissionManager permissionManager;
    private Uri currentPhotoPath;
    private String currentPhotoFilePath;

    public ImageSelectorDialog(ImageSelectionDialogInterface dialogInterface) {
        mDialogInterface = dialogInterface;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        mBinding = DialogImageSelectorBinding.inflate(getLayoutInflater());
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme);
         permissionManager = new PermissionManager(getActivity());
        mBinding.selectCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCamera();

            }
        });
        mBinding.selectGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectGallery();

            }
        });
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
      //  mDialogInterface.onImageSelectionError("Dismissed!");
    }

    public void selectCamera(){
        if (!permissionManager.checkPermission(
                new String[]{PERMISSION_CAMERA, PERMISSION_STORAGE_READ, PERMISSION_STORAGE_WRITE})) {
            permissionManager.checkAndRequestMultiplePermission(
                    new String[]{PERMISSION_CAMERA, PERMISSION_STORAGE_READ,
                            PERMISSION_STORAGE_WRITE});

        }
        else{
            dispatchTakePictureIntent();
        }

    }
    public void selectGallery(){
        permissionManager.checkPermissionIfNotRequest(PERMISSION_STORAGE_WRITE);
        Intent intent=new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
        // Launching the Intent
        startActivityForResult(intent,REQ_CODE_GALLERY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null)
        {
            if (requestCode==REQ_CODE_CAMERA && resultCode == RESULT_OK){

                mDialogInterface.onCameraImageCaptured(currentPhotoPath);
                Log.d(TAG, "onActivityResult: File path:"+data.getData()+" Uri :"+currentPhotoPath);
            }
            else if (requestCode==REQ_CODE_GALLERY)
            {
                mDialogInterface.onPhotoPickedFromDevice(data.getData());
            }
        }
        else {
            if (requestCode==REQ_CODE_CAMERA){
                mDialogInterface.onCameraImageCaptured(currentPhotoPath);
                Log.d(TAG, "onActivityResult: File path:"+" Uri :"+currentPhotoPath);
            }
            mDialogInterface.onImageSelectionError("No photo selected!");
        }

    dismiss();
    }
    /*public Uri getImageUri(Context inContext, Bitmap inImage) {
        Bitmap OutImage = Bitmap.createScaledBitmap(inImage, 1000, 1000,true);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), OutImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        String path = "";
        if (getActivity().getContentResolver() != null) {
            Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
    }*/



    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        String dirName = "/MJC/Images";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoFilePath = image.getAbsolutePath();
        return image;
    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
           mDialogInterface.onImageSelectionError(ex.getLocalizedMessage());
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getActivity(),
                        "com.developer.chithlal.mjc.fileprovider",
                        photoFile);
                currentPhotoPath = photoURI;
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQ_CODE_CAMERA);
            }
        }
    }


    public interface ImageSelectionDialogInterface{
        void onCameraImageCaptured(Uri imageUri);
        void onPhotoPickedFromDevice(Uri imageUri);
        void onImageSelectionError(String message);
    }
}
