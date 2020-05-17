package com.developer.chithlal.mjc.app.UserProfile;

import android.content.Context;

import com.developer.chithlal.mjc.app.engineer.User;

import java.util.ArrayList;
import java.util.List;

public class UserProfileModel implements UserProfileContract.Model {
    Context mContext;
    @Override
    public void setContext(Context context) {
        mContext = context;

    }

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public void setUser(User user) {

    }

    @Override
    public List<String> getProfessionList() {
        List<String> professionList = new ArrayList<>();
        professionList.add("Software Engineer");
        professionList.add("Civil Engineer");
        professionList.add("Mechanical Engineer");
        professionList.add("Architect");
        professionList.add("Contractor");
        return professionList;
    }


}
