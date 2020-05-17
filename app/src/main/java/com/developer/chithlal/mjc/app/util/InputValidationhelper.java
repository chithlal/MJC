package com.developer.chithlal.mjc.app.util;

import android.widget.EditText;
import android.widget.TextView;

public class InputValidationhelper {
    private static final String EMPTY_INPUT = "Field is empty!";
    private static final String INVALID_PHONE = "Invalid phone number!";
    private static final String INVALID_EMAIL = "Invalid Email!";
    private static final String INVALID_DIGIT = "Invalid value!";
    private static final String INVALID_DATE = "Invalid date!";
    private EditText mEditText = null;
    private TextView mTextView = null;
    private String inputType;

    public static String TYPE_PHONE_NUMBER = "phone";
    public static String TYPE_EMAIL = "email";
    public static String TYPE_TEXT = "text";
    public static String TYPE_DIGIT = "digit";
    public static String TYPE_DATE = "date";
    String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
    String digitRegex = "^([0-9])+(.[0-9]+)*$";



    public InputValidationhelper() {

    }
    public boolean validate(EditText editText, String inputType){
        boolean isValid=true;

        mEditText = editText;
        this.inputType = inputType;
        String text = mEditText.getText().toString();
        if (mEditText!=null){
            if (text.isEmpty()) {
                showError(EMPTY_INPUT);
                isValid=false;
            }
            else {

                if (inputType.equals(TYPE_PHONE_NUMBER)){
                        if (text.length()<10) {
                            showError(INVALID_PHONE);
                            isValid = false;
                        }

                }
                else if (inputType.equals(TYPE_EMAIL)){
                        if (!text.matches(emailRegex)) {
                            showError(INVALID_EMAIL);
                            isValid = false;
                        }
                }
                else if (inputType.equals(TYPE_DIGIT)){
                    if (!text.matches(digitRegex)) {
                        showError(INVALID_DIGIT);
                        isValid = false;
                    }
                }
                else if (inputType.equals(TYPE_DATE)){
                    String dateRegex = "^[0-9]{2}-[0-9]{2}-[0-9]{4}$";
                    if (!text.matches(dateRegex)) {
                        showError(INVALID_DATE);
                        isValid = false;
                    }
                }
                else if (inputType.equals(TYPE_TEXT)){
                    isValid = true;
                }
            }

        }
        return isValid;
    }
    public boolean validate(TextView textView, String inputType){
        boolean isValid=true;

        mTextView = textView;
        this.inputType = inputType;
        String text = mTextView.getText().toString();
        if (mTextView!=null){
            if (text.isEmpty()) {
                showError(EMPTY_INPUT);
                isValid=false;
            }
            else {

                if (inputType.equals(TYPE_PHONE_NUMBER)){
                    if (text.length()<10) {
                        showError(INVALID_PHONE);
                        isValid = false;
                    }

                }
                else if (inputType.equals(TYPE_EMAIL)){
                    if (!text.matches(emailRegex)) {
                        showError(INVALID_EMAIL);
                        isValid = false;
                    }
                }
                else if (inputType.equals(TYPE_DIGIT)){
                    if (!text.matches(digitRegex)) {
                        showError(INVALID_DIGIT);
                        isValid = false;
                    }
                }
                else if (inputType.equals(TYPE_DATE)){
                    String dateRegex = "^[0-9]{2}-[0-9]{2}-[0-9]{4}$";
                    if (!text.matches(dateRegex)) {
                        showError(INVALID_DATE);
                        isValid = false;
                    }
                }
                else if (inputType.equals(TYPE_TEXT)){
                    isValid = true;
                }
            }

        }
        return isValid;
    }

    void showError(String message){
        if (mTextView==null)
        mEditText.setError(message);
        else mTextView.setError(message);
    }

}
