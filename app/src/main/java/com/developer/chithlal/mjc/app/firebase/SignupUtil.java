package com.developer.chithlal.mjc.app.firebase;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.developer.chithlal.mjc.app.engineer.User;
import com.developer.chithlal.mjc.app.signup.SignUpContract;
import com.developer.chithlal.mjc.app.signup.SignUpEvent;
import com.developer.chithlal.mjc.app.util.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static androidx.core.content.ContextCompat.startActivity;

public class SignupUtil {

    private SignUpContract.Model mModel;
    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;
    String userID;

    public void updateUI(User account,String message) {
        if (account != null) {
            //Toast.makeText(this,"U Signed In successfully",Toast.LENGTH_LONG).show();

            if (mModel != null)
                mModel.onRegistrationSuccess(account);
        } else {
            mModel.onRegistrationFailed(message);
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
                            Log.d("FIREBASE", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            /*userID = mAuth.getCurrentUser().getUid();*/
                            //DocumentReference documentReference = fStore.collection("Users").document(userID);
                            if (user!=null)
                           saveDetails(signUpEvent,user);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            if (task.getException()!=null)
                            updateUI(null,task.getException().getLocalizedMessage());

                        }
                    }
                });

    }
    public void saveDetails(SignUpEvent signUpEvent,FirebaseUser user){

        User newUser = new User(signUpEvent.getUsername());
        newUser.setEmail(signUpEvent.getEmail());
        newUser.setPhone(signUpEvent.getPhone());
        newUser.setUserMode(signUpEvent.getUserType());
        newUser.setDoj(getCurrentDate());
        newUser.setUserId(user.getUid());

        fStore.collection("/App/root_app/users").document(user.getUid()).set(newUser)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("FIREBASE", "DocumentSnapshot successfully written!");

                updateUI(newUser,"Account created!");

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("FIREBASE", "Error writing document", e);
                        mModel.onRegistrationFailed(e.getLocalizedMessage());
                    }
                });


    }
    private String getCurrentDate(){
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return  day+"-"+month+"-"+year;
    }
}
