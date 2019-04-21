package com.autonavi.amap.mapcore.interfaces;

import android.os.RemoteException;
import javax.microedition.khronos.opengles.GL10;

public interface IOverlayDelegate {
    void calMapFPoint() throws RemoteException;

    boolean checkInBounds();

    void destroy();

    void draw(GL10 gl10) throws RemoteException;

    boolean equalsRemote(IOverlayDelegate iOverlayDelegate) throws RemoteException;

    String getId() throws RemoteException;

    float getZIndex() throws RemoteException;

    int hashCodeRemote() throws RemoteException;

    boolean isDrawFinish();

    boolean isVisible() throws RemoteException;

    void remove() throws RemoteException;

    void setVisible(boolean z) throws RemoteException;

    void setZIndex(float f) throws RemoteException;
}
