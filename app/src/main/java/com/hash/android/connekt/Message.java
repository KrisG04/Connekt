package com.hash.android.connekt;

/**
 * Created by aditi on 03-Sep-17.
 */

public class Message {
    private String text;
    private String photoUrl;
    private String userID;



    public Message(String text , String photoUrl,String userID) {
        this.text = text;
        this.photoUrl = photoUrl;
        this.userID = userID;
    }

    public String getText() {
        return text;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getUserID() {
        return userID;
    }

}

