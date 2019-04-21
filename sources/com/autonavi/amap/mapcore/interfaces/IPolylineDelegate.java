package com.autonavi.amap.mapcore.interfaces;

import android.os.RemoteException;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.LatLng;
import java.util.List;

public interface IPolylineDelegate extends IOverlayDelegate {
    boolean contains(LatLng latLng);

    int getColor() throws RemoteException;

    LatLng getNearestLatLng(LatLng latLng);

    List<LatLng> getPoints() throws RemoteException;

    float getWidth() throws RemoteException;

    boolean isDottedLine();

    boolean isGeodesic();

    void reLoadTexture();

    void setColor(int i) throws RemoteException;

    void setColorValues(List<Integer> list);

    void setCustemTextureIndex(List<Integer> list);

    void setCustomTextureList(List<BitmapDescriptor> list);

    void setDottedLine(boolean z);

    void setGeodesic(boolean z) throws RemoteException;

    void setPoints(List<LatLng> list) throws RemoteException;

    void setTransparency(float f);

    void setWidth(float f) throws RemoteException;

    void useGradient(boolean z);
}
