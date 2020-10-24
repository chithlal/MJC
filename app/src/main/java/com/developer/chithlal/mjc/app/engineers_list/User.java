package com.developer.chithlal.mjc.app.engineers_list;

import com.developer.chithlal.mjc.app.database.RealmUser;
import com.developer.chithlal.mjc.app.database.RealmWork;
import com.developer.chithlal.mjc.app.work.Work;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {


    private String userId;
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
    private int numberOfWorkers;
    private List<String> buildingType;
    private float feePerHour;
    private List<Work> previousWorks;
    private List<String> workRef;
    private boolean userMode; //true if its normal user false if it is engineer
    private boolean editable = false;

    public User() {
    }
    public User(RealmUser user){
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.age = user.getAge();
        this.address = user.getAddress();
        this.doj = user.getDoj();
        this.photo = user.getPhoto();
        this.Profession = user.getProfession();
        this.IDProof = user.getIDProof();
        this.rating = user.getRating();
        this.works = user.getWorks();
        this.numberOfWorkers = user.getNumberOfWorkers();
        if (user.getBuildingType()!=null) {
            this.buildingType = new ArrayList<>();
            this.buildingType.addAll(user.getBuildingType());
        }
        this.feePerHour = user.getFeePerHour();
        if (user.getAllPreviousWorks()!=null) {
            this.previousWorks = new ArrayList<>();
            for (RealmWork work : user.getAllPreviousWorks()) {
                this.previousWorks.add(new Work(work));
            }
        }
        if (user.getWorkRef()!=null) {
            this.workRef = new ArrayList<>();
            this.workRef.addAll(user.getWorkRef());
        }
        this.userMode = user.isUserMode();
        this.editable = user.isEditable();
    }
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
        if (name.length()!=0){

        name = name.substring(0,1).toUpperCase()+name.substring(1);
        }
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
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getWorkRef() {
        return workRef;
    }

    public void setWorkRef(List<String> workRef) {
        this.workRef = workRef;
    }

    public int getNumberOfWorkers() {
        return numberOfWorkers;
    }

    public void setNumberOfWorkers(int numberOfWorkers) {
        this.numberOfWorkers = numberOfWorkers;
    }

    public List<String> getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(List<String> buildingType) {
        this.buildingType = buildingType;
    }
}
