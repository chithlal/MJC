package com.developer.chithlal.mjc.app.engineer;

import com.developer.chithlal.mjc.app.UserProfile.Work;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    private String name;
    private String email;
    private String phone;
    private String age;
    private String address;
    private String doj;
    private String photo;
    private String Profession;
    private String IDProof;
    private int rating;
    private int works;
    private float feePerHour;
    private List<Work> previousWorks;
    private boolean userMode; //true if its normal user false if it is engineer
    private boolean editable = false;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public boolean isUserMode() {
        return userMode;
    }

    public void setUserMode(boolean userMode) {
        this.userMode = userMode;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public String getProfession() {
        return Profession;
    }

    public void setProfession(String profession) {
        Profession = profession;
    }

    public String getIDProof() {
        return IDProof;
    }

    public void setIDProof(String IDProof) {
        this.IDProof = IDProof;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getWorks() {
        return works;
    }

    public void setWorks(int works) {
        this.works = works;
    }

    public float getFeePerHour() {
        return feePerHour;
    }

    public void setFeePerHour(float feePerHour) {
        this.feePerHour = feePerHour;
    }


    public List<Work> getAllPreviousWorks() {
        return previousWorks;
    }

    public void addWork(Work work){
        this.previousWorks.add(work);
        this.works++;
    }

    public void setAllPreviousWorks(List<Work> previousWorks) {
        this.previousWorks = previousWorks;
    }
}
