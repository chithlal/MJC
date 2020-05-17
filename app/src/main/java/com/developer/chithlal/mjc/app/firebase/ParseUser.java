package com.developer.chithlal.mjc.app.firebase;

import com.developer.chithlal.mjc.app.engineer.User;

public class ParseUser  {
    private User mUser;
    private User parsedUser;

    public ParseUser(User user) {
        mUser = user;
    }

    public User getParsedUser() {
        return parsedUser;
    }

    public void setParsedUser(User parsedUser) {
        this.parsedUser = parsedUser;
    }
    void parse(){
        if (mUser != null){
            parsedUser = mUser;
            if (parsedUser.getAllPreviousWorks()==null&&parsedUser.getWorkRef()!=null){

            }
        }
    }
}
