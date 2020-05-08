package com.developer.chithlal.mjc.app.signup;

import static com.developer.chithlal.mjc.app.util.Constants.SUCCESS_MESSAGE;

import com.developer.chithlal.mjc.app.Login.LoginConstants;
import com.developer.chithlal.mjc.app.engineer.User;
import com.developer.chithlal.mjc.app.util.Constants;

public class SignupPresenter implements SignUpContract.Presenter {
    private SignUpContract.View mMSingupView;
    private SignupModel mSignupModel;

    public SignupPresenter(SignUpContract.View mSingupView) {
        mMSingupView = mSingupView;
    }

    @Override
    public void tryRegister() {
        mSignupModel = new SignupModel(this);
        SignUpEvent signUpEvent = mMSingupView.getUserData();
        if ((validateCred(signUpEvent))==LoginConstants.VALID_CRED)
           mSignupModel.registerUser(signUpEvent);




    }

    @Override
    public void onRegistrationCompleted(User user, String message) {
        mMSingupView.onRegistrationCompleted(SUCCESS_MESSAGE);
        mMSingupView.showMessage(message);
    }


    @Override
    public void onRegistrationFailed(String errorMessage) {
        mMSingupView.showMessage(errorMessage);

    }
    private int validateCred(SignUpEvent mSignupEvent){
        SignUpValidationError signUpValidationError = new SignUpValidationError();
        signUpValidationError.setNameError(Constants.NO_ERROR);
        signUpValidationError.setEmailError(Constants.NO_ERROR);
        signUpValidationError.setPhoneError(Constants.NO_ERROR);
        signUpValidationError.setPasswordError(Constants.NO_ERROR);
        String passwordRegex = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        String phoneRegex = "[0-9]{10}";

        if(mSignupEvent!=null){

            if (mSignupEvent.getUsername().isEmpty()){
                signUpValidationError.setNameError(LoginConstants.EMPTY_INPUT_ERROR);

            }
            if(!mSignupEvent.getPassword().isEmpty()){
                if(!mSignupEvent.getPassword().matches(passwordRegex)){
                    signUpValidationError.setPasswordError(LoginConstants.PASSWORD_ERROR);
                }
            }
            else{
                signUpValidationError.setPasswordError(LoginConstants.EMPTY_INPUT_ERROR);
            }
            if(!mSignupEvent.getEmail().isEmpty()){
                if(!mSignupEvent.getEmail().matches(emailRegex)){
                    signUpValidationError.setEmailError(LoginConstants.EMAIL_ERROR);
                }
            }
            else{
                signUpValidationError.setEmailError(LoginConstants.EMPTY_INPUT_ERROR);
            }
            if(!mSignupEvent.getPhone().isEmpty()){
                if(!mSignupEvent.getPhone().matches(phoneRegex)){
                    signUpValidationError.setPhoneError(LoginConstants.PHONE_ERROR);
                }
            }
            else{
                signUpValidationError.setPhoneError(LoginConstants.EMPTY_INPUT_ERROR);
            }

        }
        mMSingupView.setError(signUpValidationError);
       return signUpValidationError.isCredValid();
    }
}
