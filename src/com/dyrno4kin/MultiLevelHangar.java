package com.dyrno4kin;

import java.util.ArrayList;

public class MultiLevelHangar {
    ArrayList<Hangar<IAir>> parkingStages;
    private  int countPlaces = 20;
    public MultiLevelHangar(int countStages, int pictureWidth, int pictureHeight)
    {
        parkingStages = new ArrayList<Hangar<IAir>>();
        for(int i =0; i< countStages; ++i)
        {
            parkingStages.add(new Hangar<IAir>(countPlaces, pictureWidth, pictureHeight));
        }
    }


    public Hangar<IAir> getHangar(int ind){
        if((ind>-1) && (ind < parkingStages.size()))
        {
            return parkingStages.get(ind);
        }
        return null;
    }
}
