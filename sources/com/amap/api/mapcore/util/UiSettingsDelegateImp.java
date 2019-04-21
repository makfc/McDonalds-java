package com.amap.api.mapcore.util;

import android.os.Handler;
import android.os.RemoteException;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.IUiSettingsDelegate;

/* renamed from: com.amap.api.mapcore.util.ax */
class UiSettingsDelegateImp implements IUiSettingsDelegate {
    /* renamed from: a */
    final Handler f1271a = new C0750ay(this);
    /* renamed from: b */
    private IAMapDelegate f1272b;
    /* renamed from: c */
    private boolean f1273c = true;
    /* renamed from: d */
    private boolean f1274d = true;
    /* renamed from: e */
    private boolean f1275e = true;
    /* renamed from: f */
    private boolean f1276f = false;
    /* renamed from: g */
    private boolean f1277g = true;
    /* renamed from: h */
    private boolean f1278h = true;
    /* renamed from: i */
    private boolean f1279i = true;
    /* renamed from: j */
    private boolean f1280j = false;
    /* renamed from: k */
    private int f1281k = 0;
    /* renamed from: l */
    private int f1282l = 1;
    /* renamed from: m */
    private boolean f1283m = true;

    UiSettingsDelegateImp(IAMapDelegate iAMapDelegate) {
        this.f1272b = iAMapDelegate;
    }

    public boolean isIndoorSwitchEnabled() throws RemoteException {
        return this.f1283m;
    }

    public void setIndoorSwitchEnabled(boolean z) throws RemoteException {
        this.f1283m = z;
        this.f1271a.obtainMessage(4).sendToTarget();
    }

    public void setScaleControlsEnabled(boolean z) throws RemoteException {
        this.f1280j = z;
        this.f1271a.obtainMessage(1).sendToTarget();
    }

    public void setZoomControlsEnabled(boolean z) throws RemoteException {
        this.f1278h = z;
        this.f1271a.obtainMessage(0).sendToTarget();
    }

    public void setCompassEnabled(boolean z) throws RemoteException {
        this.f1279i = z;
        this.f1271a.obtainMessage(2).sendToTarget();
    }

    public void setMyLocationButtonEnabled(boolean z) throws RemoteException {
        this.f1276f = z;
        this.f1271a.obtainMessage(3).sendToTarget();
    }

    public void setScrollGesturesEnabled(boolean z) throws RemoteException {
        this.f1274d = z;
    }

    public void setZoomGesturesEnabled(boolean z) throws RemoteException {
        this.f1277g = z;
    }

    public void setTiltGesturesEnabled(boolean z) throws RemoteException {
        this.f1275e = z;
    }

    public void setRotateGesturesEnabled(boolean z) throws RemoteException {
        this.f1273c = z;
    }

    public void setAllGesturesEnabled(boolean z) throws RemoteException {
        setRotateGesturesEnabled(z);
        setTiltGesturesEnabled(z);
        setZoomGesturesEnabled(z);
        setScrollGesturesEnabled(z);
    }

    public void setLogoPosition(int i) throws RemoteException {
        this.f1281k = i;
        this.f1272b.setLogoPosition(i);
    }

    public void setZoomPosition(int i) throws RemoteException {
        this.f1282l = i;
        this.f1272b.setZoomPosition(i);
    }

    public boolean isScaleControlsEnabled() throws RemoteException {
        return this.f1280j;
    }

    public boolean isZoomControlsEnabled() throws RemoteException {
        return this.f1278h;
    }

    public boolean isCompassEnabled() throws RemoteException {
        return this.f1279i;
    }

    public boolean isMyLocationButtonEnabled() throws RemoteException {
        return this.f1276f;
    }

    public boolean isScrollGesturesEnabled() throws RemoteException {
        return this.f1274d;
    }

    public boolean isZoomGesturesEnabled() throws RemoteException {
        return this.f1277g;
    }

    public boolean isTiltGesturesEnabled() throws RemoteException {
        return this.f1275e;
    }

    public boolean isRotateGesturesEnabled() throws RemoteException {
        return this.f1273c;
    }

    public int getLogoPosition() throws RemoteException {
        return this.f1281k;
    }

    public int getZoomPosition() throws RemoteException {
        return this.f1282l;
    }
}
