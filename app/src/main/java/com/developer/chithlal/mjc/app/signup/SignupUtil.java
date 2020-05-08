package com.developer.chithlal.mjc.app.signup;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static androidx.core.content.ContextCompat.startActivity;

public class SignupUtil {

    private SignUpContract.Model mModel;
    private FirebaseAuth mAuth;

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
