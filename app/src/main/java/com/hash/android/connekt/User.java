package com.hash.android.connekt;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class User implements Parcelable {


    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
    public Map<String, Boolean> updatesKey = new HashMap<>();
    private Map<String, Boolean> bookmarks = new HashMap<>();
    private String name;
    private String phoneNumber;
    private String university;
    private String gender;
    private String link;
    private String coverURL;
    private String photoURL;
    private String faculty;
    private String department;
    private String yearOfPassing;
    private String UID;
    private boolean targetPromo;
    private String about;
    private String birthday;
    private String email;
    private boolean isPrivate = false;
    private String notificationKey;

    protected User(Parcel in) {
        name = in.readString();
        phoneNumber = in.readString();
        university = in.readString();
        gender = in.readString();
        link = in.readString();
        coverURL = in.readString();
        photoURL = in.readString();
        faculty = in.readString();
        department = in.readString();
        yearOfPassing = in.readString();
        UID = in.readString();
        targetPromo = in.readByte() != 0;
        about = in.readString();
        birthday = in.readString();
        email = in.readString();
        isPrivate = in.readByte() != 0;
        notificationKey = in.readString();
    }

    public User(String name, String phoneNumber, String university, String gender, String link, String coverURL, String photoURL, String faculty, String department, String yearOfPassing, String UID, boolean targetPromo, String about, String birthday, String email, boolean isPrivate, String notificationKey) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.university = university;
        this.gender = gender;
        this.link = link;
        this.coverURL = coverURL;
        this.photoURL = photoURL;
        this.faculty = faculty;
        this.department = department;
        this.yearOfPassing = yearOfPassing;
        this.UID = UID;
        this.targetPromo = targetPromo;
        this.about = about;
        this.birthday = birthday;
        this.email = email;
        this.isPrivate = isPrivate;
        this.notificationKey = notificationKey;
    }

    public User() {
    }

    public String getNotificationKey() {
        return notificationKey;
    }

    public void setNotificationKey(String notificationKey) {
        this.notificationKey = notificationKey;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getYearOfPassing() {
        return yearOfPassing;
    }

    public void setYearOfPassing(String yearOfPassing) {
        this.yearOfPassing = yearOfPassing;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public boolean isTargetPromo() {
        return targetPromo;
    }

    public void setTargetPromo(boolean targetPromo) {
        this.targetPromo = targetPromo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(phoneNumber);
        parcel.writeString(university);
        parcel.writeString(gender);
        parcel.writeString(link);
        parcel.writeString(coverURL);
        parcel.writeString(photoURL);
        parcel.writeString(faculty);
        parcel.writeString(department);
        parcel.writeString(yearOfPassing);
        parcel.writeString(UID);
        parcel.writeByte((byte) (targetPromo ? 1 : 0));
        parcel.writeString(about);
        parcel.writeString(birthday);
        parcel.writeString(email);
        parcel.writeByte((byte) (isPrivate ? 1 : 0));
        parcel.writeString(notificationKey);
    }
}
