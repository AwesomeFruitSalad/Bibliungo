package com.chromicle.collapp.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
* @author by Chromicle (ajayprabhakar369@gmail.com)
* @since 1/25/2020
*/
public class UserUtils {

    private FirebaseUser user;
    private String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
    private String userName;
    private String dbUserName;

    public UserUtils() {}

    public void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName;
    }

    public String getDbUserName() {
        dbUserName = userEmail.substring(0, userEmail.indexOf("@"));
        return dbUserName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUser(FirebaseUser user) {
        this.user = user;
    }

    public FirebaseUser getUser() {
        return user;
    }
}
