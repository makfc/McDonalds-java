package com.amap.api.mapcore2d;

import android.os.RemoteException;
import com.amap.api.maps2d.model.LatLng;
import java.util.List;

/* renamed from: com.amap.api.mapcore2d.ae */
public interface IPolygonDelegate extends IOverlayDelegate {
    /* renamed from: a */
    void mo9659a(int i) throws RemoteException;

    /* renamed from: a */
    void mo9660a(List<LatLng> list) throws RemoteException;

    /* renamed from: a */
    boolean mo9661a(LatLng latLng) throws RemoteException;

    /* renamed from: b */
    void mo9662b(float f) throws RemoteException;

    /* renamed from: b */
    void mo9663b(int i) throws RemoteException;

    /* renamed from: g */
    float mo9664g() throws RemoteException;

    /* renamed from: h */
    int mo9665h() throws RemoteException;

    /* renamed from: i */
    List<LatLng> mo9666i() throws RemoteException;

    /* renamed from: j */
    int mo9667j() throws RemoteException;
}
