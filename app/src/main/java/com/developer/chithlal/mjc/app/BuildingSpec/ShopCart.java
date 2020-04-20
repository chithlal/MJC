package com.developer.chithlal.mjc.app.BuildingSpec;

import java.util.ArrayList;
import java.util.List;

public class ShopCart {

    private List<CartItem> mCartItemList;
    private float totalCost;

    public ShopCart() {
        mCartItemList = new ArrayList<>();
    }

    public List<CartItem> getCartItemList() {
        return mCartItemList;
    }

    public void setCartItemList(
            List<CartItem> cartItemList) {
        mCartItemList = cartItemList;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }
    public void addItem(CartItem cartItem){
        if (mCartItemList!=null&& mCartItemList.size()==0)
            mCartItemList.add(cartItem);
        else{
            boolean isAlreadyAdded = false;
            for(CartItem item:mCartItemList){
                if (item.getMaterials()==cartItem.getMaterials()) {
                    item.buy();
                    isAlreadyAdded = true;
                }
            }
            if (!isAlreadyAdded)
                mCartItemList.add(cartItem);
        }
    }

    public void removeItem(CartItem cartItem){
        if (mCartItemList!=null&& mCartItemList.size()==0)
            return;
        else {
            for(CartItem item:mCartItemList){
                if (item.getMaterials()==cartItem.getMaterials()) {
                    if (item.getItemQty()>0)
                    item.remove();

                }
            }

        }
    }
}
