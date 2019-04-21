package com.autonavi.amap.mapcore.interfaces;

import android.os.RemoteException;
import com.amap.api.maps.model.LatLng;

public interface IArcDelegate extends IOverlayDelegate {
    int getStrokeColor() throws RemoteException;

    float getStrokeWidth() throws RemoteException;

    void setEnd(LatLng latLng);

    void setPassed(LatLng latLng);

    void setStart(LatLng latLng);

    void setStrokeColor(int i) throws RemoteException;

    void setStrokeWidth(float f) throws RemoteException;
}
