package com.example.whitneybb.model;

public class UserModel {
    String userEmail;
    public static final String USER_EMAIL = "userEmail";
    String uid;
    public static final String USER_ID = "uid";
    String dob;
    public static final String DATE_OF_BIRTH = "dob";

    public UserModel(String userEmail, String uid, String dob) {
        this.userEmail = userEmail;
        this.uid = uid;
        this.dob = dob;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
