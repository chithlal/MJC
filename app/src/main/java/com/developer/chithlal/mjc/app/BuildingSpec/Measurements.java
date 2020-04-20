package com.developer.chithlal.mjc.app.BuildingSpec;

import java.io.Serializable;

public class Measurements implements Serializable {

    private float buildArea;
    private float totalArea;
    private String planURl;

    public Measurements(float buildArea, float totalArea) {
        this.buildArea = buildArea;
        this.totalArea = totalArea;
    }

    public float getBuildArea() {
        return buildArea;
    }

    public void setBuildArea(float buildArea) {
        this.buildArea = buildArea;
    }

    public float getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(float totalArea) {
        this.totalArea = totalArea;
    }

    public String getPlanURl() {
        return planURl;
    }

    public void setPlanURl(String planURl) {
        this.planURl = planURl;
    }
}
