package com.developer.chithlal.mjc.app.BuildingSpec;

import static com.developer.chithlal.mjc.app.util.Constants.CONST_TYPE_DEFAULT;
import static com.developer.chithlal.mjc.app.util.Constants.CONST_TYPE_FINISHING;
import static com.developer.chithlal.mjc.app.util.Constants.CONST_TYPE_FULL_CONST;
import static com.developer.chithlal.mjc.app.util.Constants.CONST_TYPE_MAIN_CONC;

import java.io.Serializable;

public class ConstructionType implements Serializable {
    public  enum ConstructionTypes{
        FULL_CONSTRUCTION,MAIN_CONCRETE,FINISHING_WORK,DEFAULT
    }

    private String typeName;
    ConstructionTypes selectedType;

    public ConstructionType(String typeName) {
        this.typeName = typeName;
        if (typeName.equals(CONST_TYPE_DEFAULT))
            selectedType = ConstructionTypes.DEFAULT;
        else if (typeName.equals(CONST_TYPE_FULL_CONST))
            selectedType = ConstructionTypes.FULL_CONSTRUCTION;
        else if (typeName.equals(CONST_TYPE_MAIN_CONC))
            selectedType = ConstructionTypes.MAIN_CONCRETE;
        else if (typeName.equals(CONST_TYPE_FINISHING))
            selectedType = ConstructionTypes.FINISHING_WORK;

    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public ConstructionTypes getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(
            ConstructionTypes selectedType) {
        this.selectedType = selectedType;
    }
}
