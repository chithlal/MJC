package com.developer.chithlal.mjc.app.engineer;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class HireEngineerModel implements HireEngineerContract.Model {
    private Context mContext;

    public HireEngineerModel(Context context) {
        mContext = context;
    }

    @Override
    public List<Engineer> getEngineersList() {

        List<Engineer> engineerList = new ArrayList<>();

        Engineer engineer1 = new Engineer("Chithlal");
        engineer1.setAge("24");
        engineer1.setFeePerHour(1500);
        engineer1.setAddress("Bellandur, Bengaluru");
        engineer1.setProfession("Software Engineer");
        engineer1.setRating(5);
        engineer1.setWorks(20);
        engineer1.setPhone("8714136584");
        engineer1.setEmail("chithlalkrishna@gmail.com");

        engineerList.add(engineer1);
        engineerList.add(engineer1);
        engineerList.add(engineer1);
        engineerList.add(engineer1);
        engineerList.add(engineer1);
        engineerList.add(engineer1);
        engineerList.add(engineer1);
        engineerList.add(engineer1);
        engineerList.add(engineer1);
        engineerList.add(engineer1);


        return engineerList;
    }
}
