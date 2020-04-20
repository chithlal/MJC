package com.developer.chithlal.mjc.app.engineer;

import java.util.List;

public class Engineer extends User {
    private String Profession;
    private String IDProof;
    private int rating;
    private int works;
    private float feePerHour;
    private List<String> previousWorks;


    public Engineer(String name) {
        super(name);
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

    public List<String> getPreviousWorks() {
        return previousWorks;
    }

    public void setPreviousWorks(List<String> previousWorks) {
        this.previousWorks = previousWorks;
    }
}
