package com.developer.chithlal.mjc.app.signup;

import com.developer.chithlal.mjc.app.engineers_list.User;

public interface SignUpContract {
    interface View{
        SignUpEvent getUserData();
        void setError(SignUpValidationError errorMessage);
        void onRegistrationCompleted(int status,User user);
        void onRegistrationFailed(String errorMessage);
        void showMessage(String message);
    }

    interface Presenter{
        void tryRegister();
        void onRegistrationCompleted(User user,String message);
        void onRegistrationFailed(String errorMessage);


    }

    interface Model  {
        void registerUser(SignUpEvent signUpEvent);
        void onRegistrationSuccess(User user);
        void onRegistrationFailed(String message);

    }
}
