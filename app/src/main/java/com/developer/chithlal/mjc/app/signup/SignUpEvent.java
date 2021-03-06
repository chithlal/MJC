package com.developer.chithlal.mjc.app.signup;

public class SignUpEvent {
    private String username;
    private String email;
    private String phone;
    private String password;
    private boolean UserType;

    public SignUpEvent() {
    }

    public String getUsername() {
        return username;
    }

    public boolean getUserType() {
        return UserType;
    }

    public void setUserType(boolean userType) {
        UserType = userType;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SignUpEvent(String username, String email, String phone, String password,
            boolean userType) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        UserType = userType;
    }
}
