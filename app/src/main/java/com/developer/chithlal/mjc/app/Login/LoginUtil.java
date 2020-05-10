package com.developer.chithlal.mjc.app.Login;

import android.content.Intent;
import android.util.Base64;
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

public class LoginUtil {

    private LoginContract.Model mModel;
    FirebaseAuth mAuth;
    public void  updateUI(FirebaseUser account){
        if(account != null){
            //Toast.makeText(this,"U Signed In successfully",Toast.LENGTH_LONG).show();
           // startActivity(new Intent(this, HomeActivity.class));
        }else {
            //Toast.makeText(this,"U Didnt signed in",Toast.LENGTH_LONG).show();
        }
    }
    public LoginUtil(LoginContract.Model model) {
        mModel = model;
        mAuth = FirebaseAuth.getInstance();
    }
    public void tryLogin(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            // Toast.makeText(LoginActivity.this,"Authentication failed.",Toast.LENGTH_SHORT).show();
                            updateUI(null);
                            // ...
                        }
                    }
                });
    }

}
