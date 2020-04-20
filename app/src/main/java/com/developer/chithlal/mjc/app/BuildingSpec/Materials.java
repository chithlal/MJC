package com.developer.chithlal.mjc.app.BuildingSpec;

import java.io.Serializable;

public class Materials implements Serializable {

    private String itemName;
    private String itemBrand;
    private String itemImage;
    private float itemPricePerUnit;
    private int minimumQuantity;
    private int maximumQuantity;

    public Materials(String itemName, String itemBrand, String itemImage, float itemPricePerUnit,
            int minimumQuantity, int maximumQuantity) {
        this.itemName = itemName;
        this.itemBrand = itemBrand;
        this.itemImage = itemImage;
        this.itemPricePerUnit = itemPricePerUnit;
        this.minimumQuantity = minimumQuantity;
        this.maximumQuantity = maximumQuantity;
    }

    public Materials() {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemBrand() {
        return itemBrand;
    }

    public void setItemBrand(String itemBrand) {
        this.itemBrand = itemBrand;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public float getItemPricePerUnit() {
        return itemPricePerUnit;
    }

    public void setItemPricePerUnit(float itemPricePerUnit) {
        this.itemPricePerUnit = itemPricePerUnit;
    }

    public int getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(int minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    public int getMaximumQuantity() {
        return maximumQuantity;
    }

    public void setMaximumQuantity(int maximumQuantity) {
        this.maximumQuantity = maximumQuantity;
    }
}
