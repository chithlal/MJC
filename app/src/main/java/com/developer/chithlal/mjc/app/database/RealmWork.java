package com.developer.chithlal.mjc.app.database;

import com.developer.chithlal.mjc.app.work.Work;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class RealmWork extends RealmObject {
    private String workName;
    private String workType;
    private String constructionArea;
    private String finishingDate;
    private String ownerName;
    private String description;
    private RealmList<String> images;
    private String fireStoreRef;
    private String engineerId;

    public RealmWork() {
    }
    public RealmWork(Work work) {
        this.workName = work.getWorkName();
        this.workType = work.getWorkType();
        this.constructionArea = work.getConstructionArea();
        this.finishingDate = work.getFinishingDate();
        this.ownerName = work.getOwnerName();
        this.description = work.getDescription();
        if (work.getImages()!=null) {
            this.images = new RealmList<>();
            images.addAll(work.getImages());
        }
        this.fireStoreRef = work.getFireStoreRef();
        this.engineerId = work.getEngineerId();
    }
    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getConstructionArea() {
        return constructionArea;
    }

    public void setConstructionArea(String constructionArea) {
        this.constructionArea = constructionArea;
    }

    public String getFinishingDate() {
        return finishingDate;
    }

    public void setFinishingDate(String finishingDate) {
        this.finishingDate = finishingDate;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RealmList<String> getImages() {
        return images;
    }

    public void setImages(RealmList<String> images) {
        this.images = images;
    }

    public String getFireStoreRef() {
        return fireStoreRef;
    }

    public void setFireStoreRef(String fireStoreRef) {
        this.fireStoreRef = fireStoreRef;
    }

    public String getEngineerId() {
        return engineerId;
    }

    public void setEngineerId(String engineerId) {
        this.engineerId = engineerId;
    }
}
