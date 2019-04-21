package com.autonavi.amap.mapcore.interfaces;

import android.os.RemoteException;
import com.amap.api.maps.model.LatLng;
import java.util.List;

public interface IPolygonDelegate extends IOverlayDelegate {
    boolean contains(LatLng latLng) throws RemoteException;

    int getFillColor() throws RemoteException;

    List<LatLng> getHoles();

    List<LatLng> getPoints() throws RemoteException;

    int getStrokeColor() throws RemoteException;

    float getStrokeWidth() throws RemoteException;

    boolean isGeodesic();

    void setFillColor(int i) throws RemoteException;

    void setGeodesic(boolean z);

    void setHoles(List<LatLng> list) throws RemoteException;

    void setPoints(List<LatLng> list) throws RemoteException;

    void setStrokeColor(int i) throws RemoteException;

    void setStrokeWidth(float f) throws RemoteException;
}
