package com.developer.chithlal.mjc.app.BuildingSpec;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.developer.chithlal.mjc.R;

import java.util.List;

public class MaterialAdapter extends RecyclerView.Adapter<MaterialViewHolder> {
    private final Context mContext;
    private final List<Materials> mMaterialsList;
    private final ShopCart mShopCart;

    public MaterialAdapter(Context context, List<Materials> materialsList) {
        mContext = context;
        mMaterialsList = materialsList;
        mShopCart = new ShopCart();
    }

    @NonNull
    @Override
    public MaterialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_material,parent,false);
        return new MaterialViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialViewHolder holder, int position) {
        Materials materials = mMaterialsList.get(position);


        holder.itemName.setText(materials.getItemName());
        holder.itemBrand.setText(materials.getItemBrand());
        holder.itemQty.setText("0");
        holder.itemPrice.setText(String.valueOf(materials.getItemPricePerUnit()));

        holder.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem cartItem = new CartItem(materials);

                mShopCart.addItem(cartItem);
                List<CartItem> tempCartList = mShopCart.getCartItemList();
                for (CartItem item: tempCartList){
                    if (item.getMaterials().getItemName().equals(materials.getItemName()))
                        holder.itemQty.setText(Integer.toString(item.getItemQty()));


                }

            }
        });
        holder.buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem cartItem = new CartItem(materials);
                mShopCart.removeItem(cartItem);
                List<CartItem> tempCartList = mShopCart.getCartItemList();
                for (CartItem item: tempCartList){
                    if (item.getMaterials().getItemName().equals(materials.getItemName()))
                        holder.itemQty.setText(Integer.toString(item.getItemQty()));


                }
            }
        });

        



    }

    @Override
    public int getItemCount() {
        if (mMaterialsList!=null)
        return mMaterialsList.size();
        else return 0;
    }
}
