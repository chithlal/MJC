package com.developer.chithlal.mjc.app.BuildingSpec;

import java.util.List;

public interface BuildingSpecContract {

    interface View{
        ConstructionType getConstructionType();
        Measurements getMeasurements();
        void setupMaterial(List<Materials> materialsList);


    }

    interface Presenter{

        void setupViews();
        void onContinuePress();
        void getMaterials();
        void onPlanUpload();

    }

    interface Model{
        List<Materials> getMaterials();
    }
}
