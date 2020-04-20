package com.developer.chithlal.mjc.app.BuildingSpec;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.developer.chithlal.mjc.R;

class MaterialViewHolder extends RecyclerView.ViewHolder {

    TextView itemName,itemBrand,itemQty,itemPrice,totalPrice;
    ImageButton buttonAdd,buttonRemove;
    public MaterialViewHolder(@NonNull View itemView) {
        super(itemView);
        itemName = itemView.findViewById(R.id.mesure_item_name);
        itemBrand = itemView.findViewById(R.id.measue_item_brand);
        itemPrice = itemView.findViewById(R.id.bs_tv_item_price);
        itemQty = itemView.findViewById(R.id.measue_item_count);
        totalPrice = itemView.findViewById(R.id.bs_tv_material_total);
        buttonAdd = itemView.findViewById(R.id.bs_bt_add);
        buttonRemove = itemView.findViewById(R.id.bs_bt_remove);

    }
}
