package com.developer.chithlal.mjc.app.signup;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static androidx.core.content.ContextCompat.startActivity;

public class SignupUtil {

    private SignUpContract.Model mModel;
    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;
    String userID;

    public void updateUI(FirebaseUser account) {
        if (account != null) {
            //Toast.makeText(this,"U Signed In successfully",Toast.LENGTH_LONG).show();

            if (mModel != null)
                mModel.onRegistrationSuccess(account);
        } else {
            mModel.onRegistrationFailed("Something went wrong! try again");
        }
    }


    public SignupUtil(SignUpContract.Model model) {

        mModel = model;
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
    }

    public void trySignup(SignUpEvent signUpEvent) {
        mAuth.createUserWithEmailAndPassword(signUpEvent.getEmail(), signUpEvent.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            userID = mAuth.getCurrentUser().getUid();
                            //DocumentReference documentReference = fStore.collection("Users").document(userID);
                            Map<String,Object> storeUser = new HashMap<>();
                            storeUser.put("Name",signUpEvent.getUsername());
                            storeUser.put("Email",signUpEvent.getEmail());
                            storeUser.put("Phone",signUpEvent.getPhone());
                            storeUser.put("UserType",signUpEvent.getUserType());
                            fStore.collection("users").add(storeUser).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error adding document", e);
                                }
                            });;
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            updateUI(null);

                        }
                    }
                });

    }
}
