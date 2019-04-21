package com.amap.api.mapcore2d;

import android.os.RemoteException;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;

/* renamed from: com.amap.api.mapcore2d.y */
public interface IGroundOverlayDelegate extends IOverlayDelegate {
    /* renamed from: a */
    void mo10343a(float f, float f2) throws RemoteException;

    /* renamed from: a */
    void mo10344a(BitmapDescriptor bitmapDescriptor) throws RemoteException;

    /* renamed from: a */
    void mo10345a(LatLng latLng) throws RemoteException;

    /* renamed from: a */
    void mo10346a(LatLngBounds latLngBounds) throws RemoteException;

    /* renamed from: b */
    void mo10347b(float f) throws RemoteException;

    /* renamed from: c */
    void mo10348c(float f) throws RemoteException;

    /* renamed from: d */
    void mo10349d(float f) throws RemoteException;

    /* renamed from: h */
    LatLng mo10350h() throws RemoteException;

    /* renamed from: i */
    float mo10351i() throws RemoteException;

    /* renamed from: j */
    float mo10352j() throws RemoteException;

    /* renamed from: k */
    LatLngBounds mo10353k() throws RemoteException;

    /* renamed from: m */
    float mo10354m() throws RemoteException;

    /* renamed from: n */
    float mo10355n() throws RemoteException;
}
