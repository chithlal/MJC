package com.developer.chithlal.mjc.app.signup;

import com.developer.chithlal.mjc.app.Login.LoginConstants;
import com.developer.chithlal.mjc.app.util.Constants;

public class SignUpValidationError {
    private String nameError;
    private String phoneError;
    private String emailError;
    private String passwordError;

    public SignUpValidationError() {
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }
    public int isCredValid(){
        if (nameError.equals(Constants.NO_ERROR)&&phoneError.equals(Constants.NO_ERROR)&&passwordError.equals(Constants.NO_ERROR)&&
                emailError.equals(Constants.NO_ERROR))
            return LoginConstants.VALID_CRED;
        else return LoginConstants.INVALID_CRED;
    }
}
