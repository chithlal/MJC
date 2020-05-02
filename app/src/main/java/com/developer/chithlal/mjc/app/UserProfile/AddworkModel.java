package com.developer.chithlal.mjc.app.UserProfile;

import com.developer.chithlal.mjc.app.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class AddworkModel implements AddworkContract.Model {
    @Override
    public List<String> getWorkTypeOptions() {
        List<String> workType = new ArrayList<>();
        workType.add(Constants.CONST_STRING_WRK_TYPE_MAIN_CONC);
        workType.add(Constants.CONST_STRING_WRK_TYPE_FINSHING);
        workType.add(Constants.CONST_STRING_WRK_TYPE_FULLCONSTR);

        return workType;
    }

    @Override
    public void updateWork() {

    }
}
