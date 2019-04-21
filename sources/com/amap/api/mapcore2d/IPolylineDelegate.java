package com.amap.api.mapcore2d;

import android.os.RemoteException;
import com.amap.api.maps2d.model.LatLng;
import java.util.List;

/* renamed from: com.amap.api.mapcore2d.af */
public interface IPolylineDelegate extends IOverlayDelegate {
    /* renamed from: a */
    void mo9668a(int i) throws RemoteException;

    /* renamed from: a */
    void mo9669a(List<LatLng> list) throws RemoteException;

    /* renamed from: b */
    void mo9670b(float f) throws RemoteException;

    /* renamed from: b */
    void mo9671b(boolean z);

    /* renamed from: c */
    void mo9672c(boolean z) throws RemoteException;

    /* renamed from: g */
    float mo9673g() throws RemoteException;

    /* renamed from: h */
    int mo9674h() throws RemoteException;

    /* renamed from: i */
    List<LatLng> mo9675i() throws RemoteException;

    /* renamed from: j */
    boolean mo9676j();

    /* renamed from: k */
    boolean mo9677k();
}
