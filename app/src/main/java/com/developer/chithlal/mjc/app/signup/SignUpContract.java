package com.developer.chithlal.mjc.app.signup;

import com.developer.chithlal.mjc.app.engineer.User;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public interface SignUpContract {
    interface View{
        SignUpEvent getUserData();
        void setError(SignUpValidationError errorMessage);
        void onRegistrationCompleted(int status);
        void onRegistrationFailed(String errorMessage);
        void showMessage(String message);
    }

    interface Presenter{
        void tryRegister();
        void onRegistrationCompleted(User user,String message);
        void onRegistrationFailed(String errorMessage);


    }

    interface Model{
        void registerUser(SignUpEvent signUpEvent);
        void onRegistrationSuccess(User user);
        void onRegistrationFailed(String message);

    }
}
