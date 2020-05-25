package com.developer.chithlal.mjc.app.firebase;

import androidx.annotation.NonNull;

import com.developer.chithlal.mjc.app.engineer.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

public class UserRepository implements ParseUser.ParseListener {
    FirebaseFirestore mFirestore;
    FirebaseStorage mFirebaseStorage;
    ParseUser.ParseListener mParseListener;
    private UserUpdateListener mUserUpdateListener;

    public UserRepository(UserUpdateListener userUpdateListener) {
        mUserUpdateListener = userUpdateListener;
        mParseListener = this;
        mUserUpdateListener = userUpdateListener;
        mFirebaseStorage = FirebaseStorage.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
    }

    public void getUserObject(String userId) {

        DocumentReference docRef = mFirestore.collection("/App/root_app/users").document(userId);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                User user = documentSnapshot.toObject(User.class);

                ParseUser parseUser = new ParseUser(user,mParseListener);
                parseUser.parse();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                mUserUpdateListener.onUserUpdateFailed("Unable to update user! "+e.getLocalizedMessage());
            }
        });
    }

    @Override
    public void onParsedDataArrived(User parsedUser) {
        mUserUpdateListener.onUserDataCollected(parsedUser);
    }

    @Override
    public void onParseError(String message) {
        mUserUpdateListener.onUserUpdateFailed("Unable to update user! "+message);
    }
    public interface UserUpdateListener{
        void onUserDataCollected(User user);
        void onUserUpdateFailed(String message);
    }
}
