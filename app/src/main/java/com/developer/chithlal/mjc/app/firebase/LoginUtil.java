package com.developer.chithlal.mjc.app.firebase;

import android.content.Intent;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.developer.chithlal.mjc.app.Login.LoginContract;
import com.developer.chithlal.mjc.app.Login.LoginEvent;
import com.developer.chithlal.mjc.app.engineer.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static androidx.core.content.ContextCompat.startActivity;

public class LoginUtil {

    private LoginContract.Model mModel;
    FirebaseAuth mAuth;
    FirebaseFirestore mFirestore;
    public void  updateUI(User account,String message){
        if(account != null){
            //Toast.makeText(this,"U Signed In successfully",Toast.LENGTH_LONG).show();
           // startActivity(new Intent(this, HomeActivity.class));
            mModel.onLoginSuccess(account);
        }else {
            //Toast.makeText(this,"U Didnt signed in",Toast.LENGTH_LONG).show();

            mModel.onLoginFailure(message);
        }
    }
    public LoginUtil(LoginContract.Model model) {
        mModel = model;
        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
    }
    public void tryLogin(LoginEvent loginEvent){
        mAuth.signInWithEmailAndPassword(loginEvent.getUsername(), loginEvent.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user!=null)
                            getUserObject(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            // Toast.makeText(LoginActivity.this,"Authentication failed.",Toast.LENGTH_SHORT).show();
                            String message;
                            try {
                                if (task.getException()!=null)
                                message    = task.getException().getLocalizedMessage();
                                else message = "Error login..try again";
                            }
                            catch (NullPointerException e){
                                message = "Something went wrong!";
                            }

                            updateUI(null, message);
                            // ...
                        }
                    }
                })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                updateUI(null,e.getLocalizedMessage());
            }
        });
    }

    void getUserObject(FirebaseUser firebaseUser){

        DocumentReference docRef = mFirestore.collection("/App/root_app/users").document(firebaseUser.getUid());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                User user = documentSnapshot.toObject(User.class);

                updateUI(user,"null");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                updateUI(null,"Something went wrong! Will get back to you");
            }
        });

    }

}
