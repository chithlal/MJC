package com.developer.chithlal.mjc.app.work;

import java.io.Serializable;
import java.util.List;

public class Work implements Serializable {

    private String workName;
    private String workType;
    private String constructionArea;
    private String finishingDate;
    private String ownerName;
    private String description;
    private List<String> images;

    private String fireStoreRef;
    private String engineerId;

    public Work() {
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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
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
