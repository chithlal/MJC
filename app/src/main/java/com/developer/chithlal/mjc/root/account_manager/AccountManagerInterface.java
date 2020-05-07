package com.developer.chithlal.mjc.root.account_manager;

import com.developer.chithlal.mjc.app.engineer.User;

public interface AccountManagerInterface  {

    com.developer.chithlal.mjc.app.engineer.User User = null;
    void loginUser(User user);
    User getUser();
    void logoutUser();
    void saveUser(User user);
    void validateUser();
    boolean isLoggedIn();

}
