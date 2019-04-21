package com.amap.api.mapcore.indoor;

import com.amap.api.maps.model.IndoorBuildingInfo;
import com.autonavi.amap.mapcore.IPoint;

public class IndoorBuilding extends IndoorBuildingInfo {
    public String[] floor_nonas;
    public IPoint geoCenter = null;
    public String name_cn;
    public String name_en;
    public int numberofFloor;
    public int numberofParkFloor;
    public int[] park_floor_indexs;

    public interface IndoorBuildingListener {
        void indoorBuildingActivity(IndoorBuilding indoorBuilding);
    }
}
