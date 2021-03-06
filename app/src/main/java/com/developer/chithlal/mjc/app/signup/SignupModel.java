package com.developer.chithlal.mjc.app.signup;

import com.developer.chithlal.mjc.app.engineers_list.User;
import com.developer.chithlal.mjc.app.firebase.SignupUtil;

public class SignupModel implements SignUpContract.Model {
    private SignUpContract.Presenter mMSignupPresenter;
    private SignupUtil mSignupUtil;

    public SignupModel(SignUpContract.Presenter mSignupPresenter) {
        mMSignupPresenter = mSignupPresenter;
    }

    @Override
    public void registerUser(SignUpEvent signUpEvent) {

        mSignupUtil = new SignupUtil(this);
        mSignupUtil.trySignup(signUpEvent);
       //onRegistrationSuccess(new User("Test"));


    }

    @Override
    public void onRegistrationSuccess(User user) {
        mMSignupPresenter.onRegistrationCompleted(user,"Registration successful!");

    }

    @Override
    public void onRegistrationFailed(String message) {
        mMSignupPresenter.onRegistrationFailed(message);

    }
}
