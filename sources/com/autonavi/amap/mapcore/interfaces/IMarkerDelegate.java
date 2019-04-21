package com.autonavi.amap.mapcore.interfaces;

import android.graphics.Rect;
import android.os.RemoteException;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.LatLng;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;

public interface IMarkerDelegate {
    FPoint anchorUVoff();

    boolean calFPoint();

    boolean checkInBounds();

    void destroy();

    void drawMarker(GL10 gl10, IAMapDelegate iAMapDelegate);

    boolean equalsRemote(IMarkerDelegate iMarkerDelegate) throws RemoteException;

    IPoint getAnchor();

    float getAnchorU();

    float getAnchorV();

    BitmapDescriptor getBitmapDescriptor();

    IPoint getGeoPoint();

    int getHeight();

    ArrayList<BitmapDescriptor> getIcons() throws RemoteException;

    String getId() throws RemoteException;

    int getInfoWindowOffsetX();

    int getInfoWindowOffsetY();

    FPoint getMapPosition();

    Object getObject();

    int getPeriod() throws RemoteException;

    LatLng getPosition() throws RemoteException;

    int getRealInfoWindowOffsetX();

    int getRealInfoWindowOffsetY();

    LatLng getRealPosition();

    Rect getRect();

    float getRotateAngle();

    String getSnippet() throws RemoteException;

    int getTextureId();

    String getTitle() throws RemoteException;

    int getWidth();

    float getZIndex();

    int hashCodeRemote();

    void hideInfoWindow() throws RemoteException;

    boolean isAllowLow();

    boolean isContains();

    boolean isDestory();

    boolean isDraggable();

    boolean isFlat();

    boolean isInfoWindowShown();

    boolean isPerspective() throws RemoteException;

    boolean isViewMode();

    boolean isVisible() throws RemoteException;

    void reLoadTexture();

    void realDestroy();

    boolean remove() throws RemoteException;

    void set2Top() throws RemoteException;

    void setAnchor(float f, float f2) throws RemoteException;

    void setDraggable(boolean z) throws RemoteException;

    void setFlat(boolean z) throws RemoteException;

    void setGeoPoint(IPoint iPoint);

    void setIcon(BitmapDescriptor bitmapDescriptor) throws RemoteException;

    void setIcons(ArrayList<BitmapDescriptor> arrayList) throws RemoteException;

    void setInfoWindowOffset(int i, int i2) throws RemoteException;

    void setInfoWindowShown(boolean z);

    void setObject(Object obj);

    void setPeriod(int i) throws RemoteException;

    void setPerspective(boolean z) throws RemoteException;

    void setPosition(LatLng latLng) throws RemoteException;

    void setPositionByPixels(int i, int i2);

    void setRotateAngle(float f) throws RemoteException;

    void setSnippet(String str) throws RemoteException;

    void setTitle(String str) throws RemoteException;

    void setVisible(boolean z) throws RemoteException;

    void setZIndex(float f);

    void showInfoWindow() throws RemoteException;
}
