package com.hash.android.connekt;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.database.FirebaseDatabase;


public class PreferenceManager {
    private static final String PREFS_USER_NAME = "username";
    private static final String PREFS_USER_PHONE = "phoneNumber";
    private static final String PREFS_USER_UNIVERSITY = "university";
    private static final String PREFS_USER_GENDER = "gender";
    private static final String PREFS_USER_PROMO = "promo";
    private static final String PREFS_USER_PHOTO_URL = "photo";
    private static final String PREFS_USER_UID = "uid";
    private static final String PREFS_USER_EMAIL = "mail";
    private static final String PREFS_USER_NOTIFICATION = "notification9";
    private static final String PREFS_USER_NOTIFICATION_NEWS = "notificationNews";
    private static final String PREFS_USER_NOTIFICATION_CLASS = "notificationClass";
    private static final String PREFS_USER_NOTIFICATION_DEPARTMENT = "notificationDepartment";
    private static final String PREFS_USER_NOTIFICATION_UNIVERSITY = "notificationUniversity";
    private static final String PREFS_USER_NOTIFICATION_EVENTS = "notificationEvents";

    private static final String PREFS_LOCATION_ENABLED = "location";
    private static final String PREFS_NOTIFICATION_ID = "notificationID";
    private static final String PREFS_PENDING_INTENT_ID = "pendingIntentId";
    private final static String PREFS_FIRST_TIME_LAUNCH = "firstTimeLaunch";
    private final static String PREFS_USER_YEAR_OF_PASSING = "yearOfPassing";
    private final static String PREFS_USER_FACULTY = "faculty";
    private final static String PREFS_USER_DEPARTMENT = "department";
    private final static String PREFS_USER_ABOUT = "about";
    private final static String PREFS_USER_DOB = "dateofBirthday";
    private final static String PREFS_USER_COVER_URL = "coverURL";
    private final static String PREFS_USER_LINK = "profileLink";
    private final static String PREFS_USER_PRIVATE = "private";
    private final static String PREFS_USER_FLOW_COMPLETED = "loginFlow";
    private final static String PREFS_USER_NOTIFICATION_KEY = "notificationKey";
    private final static String PREFS_FACEBOOK_TOKEN = "fbtoken";


    private final SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;

    public PreferenceManager(Context context) {
        String PREFS_NAME_STRING = "JUAppPreferences";
        int PREFS_ACCESS_MODE = 0;
        mPrefs = context.getSharedPreferences(PREFS_NAME_STRING, PREFS_ACCESS_MODE);
    }

    public boolean isLocationEnabled() {
        return mPrefs.getBoolean(PREFS_LOCATION_ENABLED, true);
    }

    public void setLocationEnabled(boolean name) {
        mEditor = mPrefs.edit();
        mEditor.putBoolean(PREFS_LOCATION_ENABLED, name);
        mEditor.apply();
    }

    public boolean isFlowCompleted() {
        return mPrefs.getBoolean(PREFS_USER_FLOW_COMPLETED, false);
    }

    public void setFlowCompleted(boolean flow) {
        mEditor = mPrefs.edit();
        mEditor.putBoolean(PREFS_USER_FLOW_COMPLETED, flow);
        mEditor.apply();
    }


    public void setNotificationPrefs(boolean isNotificationEnabled) {
        mEditor = mPrefs.edit();
        mEditor.putBoolean(PREFS_USER_NOTIFICATION, isNotificationEnabled);
        mEditor.apply();
    }

    public boolean isNotificationEnabled() {
        return mPrefs.getBoolean(PREFS_USER_NOTIFICATION, false);
    }

    public String getName() {
        return mPrefs.getString(PREFS_USER_NAME, "");
    }

    public void setName(String name) {
        mEditor = mPrefs.edit();
        mEditor.putString(PREFS_USER_NAME, name);
        mEditor.apply();
    }

    public boolean getPrefsUserNotificationNews() {
        return mPrefs.getBoolean(PREFS_USER_NOTIFICATION_NEWS, true);
    }

    public void setPrefsUserNotificationNews(boolean name) {
        mEditor = mPrefs.edit();
        mEditor.putBoolean(PREFS_USER_NOTIFICATION_NEWS, name);
        mEditor.apply();
    }

    public boolean getPrefsUserEvents() {
        return mPrefs.getBoolean(PREFS_USER_NOTIFICATION_EVENTS, true);
    }

    public void setPrefsUserEvents(boolean name) {
        mEditor = mPrefs.edit();
        mEditor.putBoolean(PREFS_USER_NOTIFICATION_EVENTS, name);
        mEditor.apply();
    }

    public boolean getPrefsUserNotificationClass() {
        return mPrefs.getBoolean(PREFS_USER_NOTIFICATION_CLASS, true);
    }

    public void setPrefsUserNotificationClass(boolean name) {
        mEditor = mPrefs.edit();
        mEditor.putBoolean(PREFS_USER_NOTIFICATION_CLASS, name);
        mEditor.apply();
    }

    public boolean getPrefsUserNotificationDepartment() {
        return mPrefs.getBoolean(PREFS_USER_NOTIFICATION_DEPARTMENT, true);
    }

    public void setPrefsUserNotificationDepartment(boolean name) {
        mEditor = mPrefs.edit();
        mEditor.putBoolean(PREFS_USER_NOTIFICATION_DEPARTMENT, name);
        mEditor.apply();
    }

    public boolean getPrefsUserNotificationUniversity() {
        return mPrefs.getBoolean(PREFS_USER_NOTIFICATION_UNIVERSITY, true);
    }

    public void setPrefsUserNotificationUniversity(boolean dept) {
        mEditor = mPrefs.edit();
        mEditor.putBoolean(PREFS_USER_NOTIFICATION_UNIVERSITY, dept);
        mEditor.apply();
    }


    public String getAbout() {
        return mPrefs.getString(PREFS_USER_ABOUT, "");
    }

    public void setAbout(String about) {
        mEditor = mPrefs.edit();
        mEditor.putString(PREFS_USER_ABOUT, about);
        mEditor.apply();
    }


    public String getFacebookToken() {
        return mPrefs.getString(PREFS_FACEBOOK_TOKEN, "");
    }

    public void setFacebookToken(String about) {
        mEditor = mPrefs.edit();
        mEditor.putString(PREFS_FACEBOOK_TOKEN, about);
        mEditor.apply();
    }
    public String getNotificationKey() {
        return mPrefs.getString(PREFS_USER_NOTIFICATION_KEY, "");
    }


    /**
     * @param key The FCM Notification key
     */
    public void setNotificationKey(String key) {
        mEditor = mPrefs.edit();
        mEditor.putString(PREFS_USER_NOTIFICATION_KEY, key);
        mEditor.apply();
        FirebaseDatabase.getInstance().getReference().child("notificationTokens").child(key).setValue(true);
        FirebaseDatabase.getInstance().getReference().child("users").child(getUID()).child("notificationKey").setValue(key);
    }


    public String getBirthday() {
        return mPrefs.getString(PREFS_USER_DOB, "");
    }

    public void setBirthday(String dob) {
        mEditor = mPrefs.edit();
        mEditor.putString(PREFS_USER_DOB, dob);
        mEditor.apply();
    }

    public String getPhoneNumber() {
        return mPrefs.getString(PREFS_USER_PHONE, "");
    }

    public void setPhoneNumber(String number) {
        mEditor = mPrefs.edit();
        mEditor.putString(PREFS_USER_PHONE, number);
        mEditor.apply();
    }

    public String getUniversity() {
        return mPrefs.getString(PREFS_USER_UNIVERSITY, "");
    }

    public void setUniversity(String university) {
        mEditor = mPrefs.edit();
        mEditor.putString(PREFS_USER_UNIVERSITY, university);
        mEditor.apply();
    }

    public String getGender() {
        return mPrefs.getString(PREFS_USER_GENDER, "");
    }

    public void setGender(String gender) {
        mEditor = mPrefs.edit();
        mEditor.putString(PREFS_USER_GENDER, gender);
        mEditor.apply();
    }

    public String getYear() {
        return mPrefs.getString(PREFS_USER_YEAR_OF_PASSING, "");
    }

    public void setYear(String year) {
        mEditor = mPrefs.edit();
        mEditor.putString(PREFS_USER_YEAR_OF_PASSING, year);
        mEditor.apply();
    }

    public String getFaculty() {
        return mPrefs.getString(PREFS_USER_FACULTY, "");
    }

    public void setFaculty(String faculty) {
        mEditor = mPrefs.edit();
        mEditor.putString(PREFS_USER_FACULTY, faculty);
        mEditor.apply();
    }

    public String getDepartment() {
        return mPrefs.getString(PREFS_USER_DEPARTMENT, "");
    }

    public void setDepartment(String department) {
        mEditor = mPrefs.edit();
        mEditor.putString(PREFS_USER_DEPARTMENT, department);
        mEditor.apply();
    }


    public boolean getPromo() {
        return mPrefs.getBoolean(PREFS_USER_PROMO, false);
    }

    public void setPromo(boolean optInForPromo) {
        mEditor = mPrefs.edit();
        mEditor.putBoolean(PREFS_USER_PROMO, optInForPromo);
        mEditor.apply();
    }

    public String getEmail() {
        return mPrefs.getString(PREFS_USER_EMAIL, "");
    }

    public void setEmail(String email) {
        mEditor = mPrefs.edit();
        mEditor.putString(PREFS_USER_EMAIL, email);
        mEditor.apply();
    }

    public String getLink() {
        return mPrefs.getString(PREFS_USER_LINK, "");
    }

    public void setLink(String link) {
        mEditor = mPrefs.edit();
        mEditor.putString(PREFS_USER_LINK, link);
        mEditor.apply();
    }

    public String getCoverURL() {
        return mPrefs.getString(PREFS_USER_COVER_URL, "");
    }

    public void setCoverURL(String cover) {
        mEditor = mPrefs.edit();
        mEditor.putString(PREFS_USER_COVER_URL, cover);
        mEditor.apply();
    }


    public String getUID() {
        return mPrefs.getString(PREFS_USER_UID, "");
    }

    public void setUID(String uid) {
        mEditor = mPrefs.edit();
        mEditor.putString(PREFS_USER_UID, uid);
        mEditor.apply();
    }

    public void saveUser() {
        User user = getUser();
        FirebaseDatabase.getInstance().getReference("users").child(getUID()).child("name").setValue(user.getName());
        FirebaseDatabase.getInstance().getReference("users").child(getUID()).child("phoneNumber").setValue(user.getPhoneNumber());
        FirebaseDatabase.getInstance().getReference("users").child(getUID()).child("university").setValue(user.getUniversity());
        FirebaseDatabase.getInstance().getReference("users").child(getUID()).child("gender").setValue(user.getGender());
        FirebaseDatabase.getInstance().getReference("users").child(getUID()).child("link").setValue(user.getLink());
        FirebaseDatabase.getInstance().getReference("users").child(getUID()).child("coverURL").setValue(user.getCoverURL());
        FirebaseDatabase.getInstance().getReference("users").child(getUID()).child("photoURL").setValue(user.getPhotoURL());
        FirebaseDatabase.getInstance().getReference("users").child(getUID()).child("faculty").setValue(user.getFaculty());
        FirebaseDatabase.getInstance().getReference("users").child(getUID()).child("department").setValue(user.getDepartment());
        FirebaseDatabase.getInstance().getReference("users").child(getUID()).child("yearOfPassing").setValue(user.getYearOfPassing());
        FirebaseDatabase.getInstance().getReference("users").child(getUID()).child("uid").setValue(user.getUID());
        FirebaseDatabase.getInstance().getReference("users").child(getUID()).child("targetPromo").setValue(user.isTargetPromo());
        FirebaseDatabase.getInstance().getReference("users").child(getUID()).child("about").setValue(user.getAbout());
        FirebaseDatabase.getInstance().getReference("users").child(getUID()).child("birthday").setValue(user.getBirthday());
        FirebaseDatabase.getInstance().getReference("users").child(getUID()).child("email").setValue(user.getEmail());
        FirebaseDatabase.getInstance().getReference("users").child(getUID()).child("isPrivate").setValue(user.isPrivate());
        FirebaseDatabase.getInstance().getReference("users").child(getUID()).child("notificationKey").setValue(user.getNotificationKey());

    }

    public String getPhotoURL() {
        return mPrefs.getString(PREFS_USER_PHOTO_URL, "");
    }

    public void setPhotoURL(String url) {
        mEditor = mPrefs.edit();
        mEditor.putString(PREFS_USER_PHOTO_URL, url);
        mEditor.apply();
    }

    public boolean isPrivate() {
        return mPrefs.getBoolean(PREFS_USER_PRIVATE, false);
    }

    public void setPrivate(boolean isPrivate) {
        mEditor = mPrefs.edit();
        mEditor.putBoolean(PREFS_USER_PRIVATE, isPrivate);
        mEditor.apply();
    }

    public User getUser() {
        return new User(getName(), getPhoneNumber(), getUniversity(), getGender(), getLink(), getCoverURL(), getPhotoURL(), getFaculty(), getDepartment(), getYear(), getUID(), getPromo(), getAbout(), getBirthday(), getEmail(), isPrivate(), getNotificationKey());
    }

    public boolean isFirstTimeLaunch() {
        return mPrefs.getBoolean(PREFS_FIRST_TIME_LAUNCH, true);
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        mEditor = mPrefs.edit();
        mEditor.putBoolean(PREFS_FIRST_TIME_LAUNCH, isFirstTime);
        mEditor.apply();
    }

    public int getNotificationId() {
        mEditor = mPrefs.edit();
        int notificationID = mPrefs.getInt(PREFS_NOTIFICATION_ID, 1331);
        mEditor.putInt(PREFS_NOTIFICATION_ID, notificationID + 1);
        mEditor.apply();
        return notificationID;
    }

    public int getPendingIntentId() {
        mEditor = mPrefs.edit();
        int notificationID = mPrefs.getInt(PREFS_PENDING_INTENT_ID, 12345);
        mEditor.putInt(PREFS_PENDING_INTENT_ID, notificationID + 1);
        mEditor.apply();
        return notificationID;
    }
}
