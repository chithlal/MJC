package com.developer.chithlal.mjc.app.firebase;

import static com.developer.chithlal.mjc.app.util.Constants.UPLOAD_TYPE_WORK_IMAGE;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.developer.chithlal.mjc.app.work.Work;
import com.developer.chithlal.mjc.app.engineer.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class UpdateDataUtil implements UploadUtil.UploadProgressListener{

    private static final String TAG = "UpdateDataUtil";
    private FirebaseFirestore mFirestore;
     private Context mContext;
     private firebaseDataUpdateListener mUpdateListener;

     private int workImageUploadQueue=0;

     private UploadUtil mUploadUtil;
     private Work mWork;
    private List<String> workImageList = new ArrayList<>();


    public UpdateDataUtil(firebaseDataUpdateListener UpdateListener) {
        mUpdateListener = UpdateListener;
        mFirestore = FirebaseFirestore.getInstance();
    }

    public void setContext(Context context){

        mContext = context;
    }

   public void updateUserData(User user){

        if (user!=null){
            if (user.getUserId()!=null)
                mFirestore.collection("/App/root_app/users").document(user.getUserId())
                .set(user, SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        mUpdateListener.onUserUpdateCompleted(user);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        mUpdateListener.onUpdateFailed(e.getMessage());
                    }
                });

        }
        else {
            mUpdateListener.onUpdateFailed("Invalid user!");
        }

    }
    public void deleteUser(User user){
        if (user!=null){
            if (user.getUserId()!=null)
                mFirestore.collection("/App/root_app/users").document(user.getUserId())
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                mUpdateListener.onDeleteSuccess();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                mUpdateListener.onDeleteFailed(e.getMessage());
                            }
                        });

        }
        else {
            mUpdateListener.onUpdateFailed("Invalid user!");
        }
    }

    public void addWork(Work work){
        Log.d(TAG, "updateWork: updating work"+work.getWorkName());
        if (work!=null){

                mFirestore.collection("/App/root_app/works")
                        .add(work)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "onSuccess: getId():"+documentReference.getId()+" Path:"+documentReference.getPath());
                                work.setFireStoreRef(documentReference.getPath());

                                updateWork(work);
                                Log.d(TAG, "onSuccess: work updated");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "onFailure: updating work failed");
                                mUpdateListener.onUpdateFailed(e.getMessage());
                            }
                        });

           

        }
        else {
            mUpdateListener.onUpdateFailed("Invalid work!");
        }
    }

    public void deleteWork(Work work){
        if (work!=null){
            if (work.getFireStoreRef()!=null)
                mFirestore.collection("/App/root_app/works").document(work.getFireStoreRef())
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                mUpdateListener.onDeleteSuccess();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                mUpdateListener.onDeleteFailed(e.getMessage());
                            }
                        });

        }
        else {
            mUpdateListener.onUpdateFailed("Invalid work!");
        }

    }
    public void updateWork(Work work){
        if (work!=null){
            if (work.getFireStoreRef()!=null) {
                DocumentReference documentReference = mFirestore.document(work.getFireStoreRef());
               documentReference.set(work, SetOptions.merge())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                mUpdateListener.onWorkUpdateCompleted(work);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                mUpdateListener.onUpdateFailed(e.getMessage());
                            }
                        });
            }
            else mUpdateListener.onUpdateFailed("Work reference not available!");

        }
        else {
            mUpdateListener.onUpdateFailed("Invalid user!");
        }

    }

    @Override
    public void onProgressChanged(int progress) {

    }

    @Override
    public void onUploadCompleted(String url, StorageReference storageReference,
            String UPLOAD_TYPE) {
        if (UPLOAD_TYPE.equals(UPLOAD_TYPE_WORK_IMAGE)){
                workImageList.add(url);
                workImageUploadQueue--;
        }
        if (workImageUploadQueue==0){
            mWork.setImages(workImageList);

            addWork(mWork);
        }

    }

    @Override
    public void onError(String message) {

    }

    public interface firebaseDataUpdateListener{
        void onUserUpdateCompleted(User user);
        void onWorkUpdateCompleted(Work work);
        void onUpdateFailed(String message);
        void onDeleteSuccess();
        void onDeleteFailed(String message);
    }
    public void uploadWorkWithPhotos(Work work){
        mUploadUtil = new UploadUtil(this);
        mWork = work;
        workImageUploadQueue = work.getImages().size();
        List<Uri> imageUri = new ArrayList<>();
        for (String s:work.getImages()) {
            imageUri.add( Uri.parse(s));
        }
        for (Uri uri:imageUri){
            mUploadUtil.uploadFile(uri,work.getEngineerId(),UPLOAD_TYPE_WORK_IMAGE,null);
        }
    }


}
