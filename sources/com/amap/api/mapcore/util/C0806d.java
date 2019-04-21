package com.amap.api.mapcore.util;

import android.text.TextUtils;
import com.amap.api.mapcore.util.ConfigManager.C0817a;
import com.amap.api.mapcore.util.ConfigManager.C0817a.C0816c;
import com.amap.api.mapcore.util.SDKInfo.C0824a;
import com.amap.api.maps.MapsInitializer;

/* compiled from: AMapDelegateImp */
/* renamed from: com.amap.api.mapcore.util.d */
class C0806d extends Thread {
    /* renamed from: a */
    final /* synthetic */ AMapDelegateImp f1704a;

    C0806d(AMapDelegateImp aMapDelegateImp) {
        this.f1704a = aMapDelegateImp;
    }

    public void run() {
        try {
            if (MapsInitializer.getNetWorkEnable()) {
                SDKInfo a = new C0824a(ConfigableConst.f2122b, "3.3.2", ConfigableConst.f2124d).mo9290a(new String[]{"com.amap.api.maps", "com.amap.api.mapcore", "com.autonavi.amap.mapcore"}).mo9291a();
                if (!TextUtils.isEmpty(MapsInitializer.KEY)) {
                    C0811dm.m2391a(MapsInitializer.KEY);
                }
                C0811dm.m2392a(this.f1704a.f1540H, a);
                if (C0811dm.f1758a == 0) {
                    this.f1704a.f1645l.sendEmptyMessage(2);
                }
                C0817a a2 = ConfigManager.m2413a(this.f1704a.f1540H, a, "common;exception;sdkcoordinate;sdkupdate");
                if (a2 != null) {
                    if (a2.f1796g != null) {
                        a.mo9293a(a2.f1796g.f1783a);
                    }
                    if (a2.f1798i != null) {
                        new SDKCoordinatorDownload(this.f1704a.f1540H, ConfigableConst.f2122b, a2.f1798i.f1785a, a2.f1798i.f1786b).mo9288a();
                    }
                    if (a2.f1797h != null) {
                        C0816c c0816c = a2.f1797h;
                        if (c0816c != null) {
                            String str = c0816c.f1788b;
                            String str2 = c0816c.f1787a;
                            String str3 = c0816c.f1789c;
                            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
                                new DexDownLoad(this.f1704a.f1540H, null, a).mo9363a();
                            } else {
                                new DexDownLoad(this.f1704a.f1540H, new DexDownloadItem(str2, str, str3), a).mo9363a();
                            }
                        } else {
                            new DexDownLoad(this.f1704a.f1540H, null, a).mo9363a();
                        }
                    }
                }
                ConfigableConst.f2128h = a;
                SDKLogHandler.m2562a(this.f1704a.f1540H, a);
                interrupt();
                this.f1704a.setRunLowFrame(false);
            }
        } catch (Throwable th) {
            interrupt();
            SDKLogHandler.m2563a(th, "AMapDelegateImpGLSurfaceView", "mVerfy");
            th.printStackTrace();
        }
    }
}
