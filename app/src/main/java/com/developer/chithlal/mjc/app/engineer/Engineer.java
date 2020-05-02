package com.developer.chithlal.mjc.app.engineer;

import com.developer.chithlal.mjc.app.UserProfile.Work;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Engineer extends User implements Serializable {
    private String Profession;
    private String IDProof;
    private int rating;
    private int works;
    private float feePerHour;
    private List<Work> previousWorks;


    public Engineer(String name) {
        super(name);
        previousWorks = new ArrayList<>();
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
