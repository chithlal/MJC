package com.developer.chithlal.mjc.app.engineer;

import android.content.Context;

import com.developer.chithlal.mjc.app.UserProfile.Work;

import java.util.ArrayList;
import java.util.List;

public class HireEngineerModel implements HireEngineerContract.Model {
    private Context mContext;

    public HireEngineerModel(){
    }


    @Override
    public void setContext(Context context) {

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

        Work work = new Work();
        work.setWorkName("Lilly Villa ");
        work.setOwnerName("Chithlal");
        work.setWorkType("Full Construction");
        work.setConstructionArea("1500 sqft");
        work.setFinishingDate("12/4/2020");

        List<String> imageList = new ArrayList<>();
        imageList.add("https://images.unsplash.com/photo-1580587771525-78b9dba3b914?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80");
        imageList.add("https://www.villapadmaphuket.com/wp-content/uploads/2019/08/01-Villa-Padma-Phuket-Pool-Area-4.jpg");

        work.setImages(imageList);

        List<Work> workList = new ArrayList<>();
        workList.add(work);
        workList.add(work);
        workList.add(work);

        engineer1.setAllPreviousWorks(workList);

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
