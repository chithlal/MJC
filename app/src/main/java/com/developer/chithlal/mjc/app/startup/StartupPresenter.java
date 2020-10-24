package com.developer.chithlal.mjc.app.startup;

import com.developer.chithlal.mjc.app.engineers_list.User;

public class StartupPresenter implements StartupContract.Presenter {
    private StartupContract.Model mModel;
    private StartupContract.View mView;

    public StartupPresenter(StartupContract.Model model) {
        mModel = model;

    }

    @Override
    public void onAppStartup(StartupContract.View view) {

        mView = view;
        mModel.setPresenter(this);
        mModel.setContext(mView.getViewContext());
        mModel.setupUserForLogin();
    }

    @Override
    public void loginWithLastUser(User user) {
        mView.directLogin(user);
    }

    @Override
    public void onPastUserNotAvailable() {
        mView.navigateToLogin();
    }

    @Override
    public void passMessage(String message) {
        mView.showMessage(message);
    }
}
