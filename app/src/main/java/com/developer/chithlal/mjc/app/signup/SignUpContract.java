package com.developer.chithlal.mjc.app.signup;

import java.util.List;

public interface SignUpContract {
    interface View{
        SignUpEvent getUserData();
        void setError(SignUpValidationError errorMessage);
        void onRegistrationCompleted(int status);
        void onRegistrationFailed(String errorMessage);
    }

    interface Presenter{
        void tryRegister();
        void onRegistrationCompleted();
        void onRegistrationFailed(String errorMessage);


    }

    interface Model{
        void registerUser(SignUpEvent signUpEvent);

    }
}
