package com.developer.chithlal.mjc.app.signup;

public class SignupModel implements SignUpContract.Model {
    private SignUpContract.Presenter mMSignupPresenter;

    public SignupModel(SignUpContract.Presenter mSignupPresenter) {
        mMSignupPresenter = mSignupPresenter;
    }

    @Override
    public void registerUser(SignUpEvent signUpEvent) {

    }
}
