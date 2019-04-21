package com.amap.api.mapcore.util;

import com.autonavi.amap.mapcore.interfaces.CameraUpdateFactoryDelegate;
import java.util.concurrent.CopyOnWriteArrayList;

/* renamed from: com.amap.api.mapcore.util.ad */
class MapMessageQueue {
    /* renamed from: a */
    AMapDelegateImp f983a;
    /* renamed from: b */
    private CopyOnWriteArrayList<CameraUpdateFactoryDelegate> f984b = new CopyOnWriteArrayList();
    /* renamed from: c */
    private CopyOnWriteArrayList<MapMessage> f985c = new CopyOnWriteArrayList();

    public MapMessageQueue(AMapDelegateImp aMapDelegateImp) {
        this.f983a = aMapDelegateImp;
    }

    /* renamed from: a */
    public synchronized void mo8486a(MapMessage mapMessage) {
        this.f983a.setRunLowFrame(false);
        this.f985c.add(mapMessage);
        this.f983a.setRunLowFrame(false);
    }

    /* renamed from: a */
    public MapMessage mo8485a() {
        if (mo8488b() == 0) {
            return null;
        }
        MapMessage mapMessage = (MapMessage) this.f985c.get(0);
        this.f985c.remove(mapMessage);
        return mapMessage;
    }

    /* renamed from: b */
    public synchronized int mo8488b() {
        return this.f985c.size();
    }

    /* renamed from: a */
    public void mo8487a(CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate) {
        this.f983a.setRunLowFrame(false);
        this.f984b.add(cameraUpdateFactoryDelegate);
        this.f983a.setRunLowFrame(false);
    }

    /* renamed from: c */
    public CameraUpdateFactoryDelegate mo8489c() {
        if (mo8490d() == 0) {
            return null;
        }
        CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate = (CameraUpdateFactoryDelegate) this.f984b.get(0);
        this.f984b.remove(cameraUpdateFactoryDelegate);
        this.f983a.setRunLowFrame(false);
        return cameraUpdateFactoryDelegate;
    }

    /* renamed from: d */
    public int mo8490d() {
        return this.f984b.size();
    }
}
