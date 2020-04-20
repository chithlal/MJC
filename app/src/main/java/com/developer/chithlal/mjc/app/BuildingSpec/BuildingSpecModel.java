package com.developer.chithlal.mjc.app.BuildingSpec;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class BuildingSpecModel implements BuildingSpecContract.Model {

    private Context mContext;

    public BuildingSpecModel(Context context) {
        mContext = context;
    }

    @Override
    public List<Materials> getMaterials() {

        List<Materials> materialsList = new ArrayList<>();

        Materials materials = new Materials();
        materials.setItemName("Steel");
        materials.setItemBrand("TMT steel");
        materials.setItemPricePerUnit(100);
        Materials materialone = new Materials();
        materialone.setItemName("Cement");
        materialone.setItemBrand("TATA");
        materialone.setItemPricePerUnit(130);

        materialsList.add(materials);
        materialsList.add(materialone);
        return materialsList;
    }
}
