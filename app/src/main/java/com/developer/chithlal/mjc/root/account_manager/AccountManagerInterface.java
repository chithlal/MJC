package com.developer.chithlal.mjc.root.account_manager;

import com.developer.chithlal.mjc.app.engineers_list.User;

public interface AccountManagerInterface  {

    com.developer.chithlal.mjc.app.engineers_list.User User = null;
    void loginUser(User user);
    User getUser();
    void logoutUser();
    void saveUser(User user);
    void validateUser();
    boolean isLoggedIn();
    String getUserId();
    void updateUser();

}
