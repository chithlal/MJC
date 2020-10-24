package com.developer.chithlal.mjc.app.database;

import com.developer.chithlal.mjc.app.engineers_list.User;
import com.developer.chithlal.mjc.app.work.Work;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmUser extends RealmObject {
    @PrimaryKey
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
    private RealmList<String> buildingType;
    private float feePerHour;
    private RealmList<RealmWork> previousWorks;
    private RealmList<String> workRef;
    private boolean userMode; //true if its normal user false if it is engineer
    private boolean editable = false;

    public RealmUser() {
    }

    public RealmUser(User user){
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
            this.buildingType = new RealmList<>();
            this.buildingType.addAll(user.getBuildingType());
        }
        this.feePerHour = user.getFeePerHour();
        if (user.getAllPreviousWorks()!=null) { // Serializing work list into realmObjects
            this.previousWorks = new RealmList<>();
            for (Work work : user.getAllPreviousWorks()) {
                this.previousWorks.add(new RealmWork(work));
            }
        }
        if (user.getWorkRef()!=null) {
            this.workRef = new RealmList<>();
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

    public RealmUser(String name) {
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


    public RealmList<RealmWork> getAllPreviousWorks() {
        return previousWorks;
    }

    public void addWork(RealmWork work){
        this.previousWorks.add(work);
        this.works++;
    }

    public void setAllPreviousWorks(RealmList<RealmWork> previousWorks) {
        this.previousWorks = previousWorks;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public RealmList<String> getWorkRef() {
        return workRef;
    }

    public void setWorkRef(RealmList<String> workRef) {
        this.workRef = workRef;
    }

    public int getNumberOfWorkers() {
        return numberOfWorkers;
    }

    public void setNumberOfWorkers(int numberOfWorkers) {
        this.numberOfWorkers = numberOfWorkers;
    }

    public RealmList<String> getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(RealmList<String> buildingType) {
        this.buildingType = buildingType;
    }
}
