package com.developer.chithlal.mjc.app.firebase;

import static com.developer.chithlal.mjc.app.util.Constants.UPLOAD_TYPE_USER_ID_PROOF;
import static com.developer.chithlal.mjc.app.util.Constants.UPLOAD_TYPE_USER_PROFILE_IMAGE;
import static com.developer.chithlal.mjc.app.util.Constants.UPLOAD_TYPE_WORK_IMAGE;
import static com.smarteist.autoimageslider.SliderView.TAG;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.Random;

public class UploadUtil {


    private FirebaseStorage  mStorage;
    private Context mContext;
    StorageReference rootRef;
    UploadProgressListener mListener;

    public UploadUtil(UploadProgressListener listener) {
        mListener = listener;
        mStorage = FirebaseStorage.getInstance();
        rootRef = mStorage.getReference();
    }

    public void setContext(Context context) {
        mContext = context;
    }

    /*Upload Files to firebase (Uri:file - uri of the file to be uploaded ,String dir_name - only for work images
                              String upload_type- constant specifying type of upload ,String userId - id of user )*/

    public void uploadFile(Uri file, @Nullable String dir_name,String upload_type,@Nullable String userId){
        StorageReference uploadRef = null;
        int limit = 99999999;
        Random random = new Random(limit);
        String random_name = String.valueOf(random.nextInt(limit));
        String file_name ;
        String actual="";
        String[] split= file.getLastPathSegment().split("/",100);
        if (split.length!=0){
            actual = "_"+split[split.length-1];
        }
        if (upload_type.equals(UPLOAD_TYPE_USER_PROFILE_IMAGE)) {
            file_name = UPLOAD_TYPE_USER_PROFILE_IMAGE+random_name+"_"+userId+"_"+actual;
           uploadRef = rootRef.child("/app/users/profile/"+file_name);

        }
        else if (upload_type.equals(UPLOAD_TYPE_WORK_IMAGE)){
            file_name = UPLOAD_TYPE_WORK_IMAGE+random_name+actual;
            uploadRef = rootRef.child("/app/works/"+dir_name+"/"+file_name);
        }
        else if (upload_type.equals(UPLOAD_TYPE_USER_ID_PROOF)){

            file_name = UPLOAD_TYPE_USER_ID_PROOF+userId+actual;
            Log.d(TAG, "uploadFile: file"+file_name );
            uploadRef = rootRef.child("/app/users/id_proof/"+file_name);

        }
        else {

            mListener.onError("Invalid file type");
        }

        if (uploadRef!=null) {

            UploadTask uploadTask = uploadRef.putFile(file);

// Register observers to listen for when the download is done or if it fails
            StorageReference finalUploadRef = uploadRef;


            uploadTask
                    .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                    mListener.onError(exception.getMessage());

                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            finalUploadRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {
                                                    Log.d(TAG, "onSuccess: uri= "+ uri.toString());
                                                    mListener.onUploadCompleted(uri.toString(),taskSnapshot.getMetadata().getReference(),upload_type);
                                                }
                                            });

                                            Log.d(TAG, "onSuccess: url:"+finalUploadRef.getDownloadUrl().toString());
                                        }
                                    }
            )
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                            mListener.onProgressChanged((int)taskSnapshot.getBytesTransferred());

                        }
                    });

        }



    }
    public void uploadFile(File file){

    }


    public interface UploadProgressListener{
        void onProgressChanged(int progress);
        void onUploadCompleted(String url,StorageReference storageReference,String UPLOAD_TYPE);
        void onError(String message);
    }
}
