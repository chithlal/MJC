package com.developer.chithlal.mjc.app.UserProfile;

import android.content.Context;


import com.developer.chithlal.mjc.app.engineer.User;
import com.developer.chithlal.mjc.app.work.Work;

import java.util.ArrayList;
import java.util.List;


public class UserProfilePresenter implements UserProfileContract.Presenter {
    UserProfileContract.Model mModel;
    private UserProfileContract.View mView;
    private Context mContext;
    public UserProfilePresenter(UserProfileContract.Model model) {
        mModel = model;
        mModel.setPresenter(this);
    }

    @Override
    public void setUi(UserProfileContract.View view) {
        mView =view;
        mModel.getUser();
        mView.setUser(mView.getUser());
        if (!mView.getUser().isUserMode())
        setupPreviousWorkImage(mView.getUser());

    }

    private void setupPreviousWorkImage(User user) {
        List<SliderItem> slideList = new ArrayList<>();
        int i=0;
        if (user.getAllPreviousWorks()!=null)
        for(Work work:user.getAllPreviousWorks()){

            if (work.getImages().size()!=0 && work.getImages()!=null){

                slideList.add(new SliderItem(work.getImages().get(0),"",i));
                i++;

            }

        }
        mView.setPreviousWorkImages(slideList);

    }

    @Override
    public void setContext(Context context) {
        mContext = context;

    }

    @Override
    public void onHireMeClick() {

    }

    @Override
    public void onEditEnabled() {
        mView.setProfessionList(mModel.getProfessionList());
    }

    @Override
    public void saveUserData(User user) {
        mModel.setUser(user);
    }

    @Override
    public void onUserDataUpdateInServer(User user) {
        mView.setUser(user);
        mView.showMessage("Data updated successfully!");
    }
}
