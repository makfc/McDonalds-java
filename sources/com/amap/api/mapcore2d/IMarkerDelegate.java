package com.amap.api.mapcore2d;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.RemoteException;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.LatLng;
import java.util.ArrayList;

/* renamed from: com.amap.api.mapcore2d.aa */
public interface IMarkerDelegate extends IMarkerText {
    /* renamed from: a */
    void mo9618a(float f) throws RemoteException;

    /* renamed from: a */
    void mo9619a(float f, float f2);

    /* renamed from: a */
    void mo9620a(int i) throws RemoteException;

    /* renamed from: a */
    void mo9621a(int i, int i2) throws RemoteException;

    /* renamed from: a */
    void mo9622a(Canvas canvas, IAMapDelegate iAMapDelegate);

    /* renamed from: a */
    void mo9623a(BitmapDescriptor bitmapDescriptor);

    /* renamed from: a */
    void mo9624a(LatLng latLng);

    /* renamed from: a */
    void mo9625a(String str);

    /* renamed from: a */
    void mo9626a(ArrayList<BitmapDescriptor> arrayList) throws RemoteException;

    /* renamed from: a */
    void mo9627a(boolean z);

    /* renamed from: a */
    boolean mo9628a() throws RemoteException;

    /* renamed from: a */
    boolean mo9629a(IMarkerDelegate iMarkerDelegate);

    /* renamed from: b */
    Rect mo9630b();

    /* renamed from: b */
    void mo9631b(String str);

    /* renamed from: b */
    void mo9632b(boolean z);

    /* renamed from: c */
    LatLng mo9633c();

    /* renamed from: d */
    String mo9634d();

    /* renamed from: e */
    C1044r mo9635e();

    /* renamed from: f */
    String mo9636f();

    /* renamed from: g */
    String mo9637g();

    /* renamed from: h */
    boolean mo9638h();

    /* renamed from: i */
    void mo9639i();

    /* renamed from: j */
    void mo9640j();

    /* renamed from: k */
    boolean mo9641k();

    /* renamed from: l */
    void mo9642l();

    /* renamed from: m */
    int mo9643m();

    /* renamed from: n */
    int mo9644n();

    /* renamed from: o */
    int mo9645o() throws RemoteException;

    /* renamed from: p */
    ArrayList<BitmapDescriptor> mo9646p() throws RemoteException;

    /* renamed from: q */
    boolean mo9647q() throws RemoteException;
}
