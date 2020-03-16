package com.developer.chithlal.mjc.app.Login;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mLoginView;
    private LoginModel mLoginModel;

    public LoginPresenter(LoginContract.View LoginView) {
        mLoginView = LoginView;

    }

    @Override
    public void tryLogin() {
        mLoginModel = new LoginModel(this);
        LoginEvent mLoginEvent = mLoginView.getLoginData();
        //if(validateCred(mLoginEvent)==LoginConstants.VALID_CRED)
            mLoginModel.login(mLoginEvent);


    }

    @Override
    public void onLoginSuccess() {
        mLoginView.onLoginSuccess();

    }

    @Override
    public void onLoginFailure(String message) {
        mLoginView.onLoginFailure(message);
    }

    @Override
    public int validateCred(LoginEvent mLoginEvent) {
        String userNameStatus = LoginConstants.NO_ERROR;
        String passwordStatus = LoginConstants.NO_ERROR;
        String passwordRegex = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";

        if(mLoginEvent!=null){

            if (mLoginEvent.getUsername().isEmpty()){
                    userNameStatus = LoginConstants.EMPTY_INPUT_ERROR ;

            }
            if(!mLoginEvent.getPassword().isEmpty()){
                    if(!mLoginEvent.getPassword().matches(passwordRegex)){
                        passwordStatus = LoginConstants.PASSWORD_ERROR;
                    }
            }
            else{
                passwordStatus = LoginConstants.EMPTY_INPUT_ERROR ;
            }
        }
        mLoginView.showValidationMessage(userNameStatus,passwordStatus);
        if(userNameStatus.equals(LoginConstants.NO_ERROR) && (passwordStatus.equals(LoginConstants.NO_ERROR))){
            return LoginConstants.VALID_CRED;
        }
        else return LoginConstants.INVALID_CRED;

    }
}
