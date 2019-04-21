package com.amap.api.mapcore2d;

import android.content.Context;
import android.os.Message;
import com.amap.api.mapcore2d.AuthConfigManager.C0961a;
import com.amap.api.mapcore2d.C0977cv.C0976a;
import com.amap.api.maps2d.MapsInitializer;
import com.mcdonalds.sdk.modules.models.CreditCard;

/* renamed from: com.amap.api.mapcore2d.f */
public class AuthTask extends Thread {
    /* renamed from: a */
    private Context f2926a;
    /* renamed from: b */
    private IAMapDelegate f2927b;

    public AuthTask(Context context, IAMapDelegate iAMapDelegate) {
        this.f2926a = context;
        this.f2927b = iAMapDelegate;
    }

    public void run() {
        try {
            if (MapsInitializer.getNetworkEnable()) {
                C0977cv a = new C0976a("2dmap", "2.9.0", "AMAP_SDK_Android_2DMap_2.9.0").mo10170a(new String[]{"com.amap.api.maps", "com.amap.api.mapcore", "com.autonavi.amap.mapcore"}).mo10171a();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(CreditCard.TYPE_MASTER_CARD);
                stringBuilder.append(";");
                stringBuilder.append("11K");
                C0961a a2 = AuthConfigManager.m3924a(this.f2926a, C0955ck.m3883a(), stringBuilder.toString(), null);
                if (AuthConfigManager.f2731a != 1) {
                    Message obtainMessage = this.f2927b.mo9939a().obtainMessage();
                    obtainMessage.what = 2;
                    if (a2.f2713a != null) {
                        obtainMessage.obj = a2.f2713a;
                    }
                    this.f2927b.mo9939a().sendMessage(obtainMessage);
                }
                if (a2 != null) {
                    if (a2.f2724l != null) {
                        C0955ck.m3883a().mo10173a(a2.f2724l.f2706a);
                    }
                    if (a2.f2726n != null) {
                        new C0974cu(this.f2926a, "2dmap", a2.f2726n.f2708a, a2.f2726n.f2709b).mo10168a();
                    }
                }
                C1042p.f3045o = a;
                C0990dd.m4096a(this.f2926a, a);
                interrupt();
            }
        } catch (Throwable th) {
            interrupt();
            C0990dd.m4098b(th, "AMapDelegateImpGLSurfaceView", "mVerfy");
            th.printStackTrace();
        }
    }
}
