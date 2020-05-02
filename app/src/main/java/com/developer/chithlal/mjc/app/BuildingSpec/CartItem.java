package com.developer.chithlal.mjc.app.BuildingSpec;

import android.util.Log;

import java.io.Serializable;

public class CartItem implements Serializable {

    private Materials mMaterials;
    private int itemQty;
    private float itemTotal;

    public CartItem() {
    }

    public CartItem(Materials materials) {
        mMaterials = materials;
    }

    public Materials getMaterials() {
        return mMaterials;
    }

    public void setMaterials(Materials materials) {
        mMaterials = materials;
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }

    public float getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(float itemTotal) {
        this.itemTotal = itemTotal;
    }
    public void buy(){
        if (this.itemQty>=0)
        this.itemQty++;

    }
    public void remove(){
        if (this.itemQty>0)
        this.itemQty--;
    }
}
