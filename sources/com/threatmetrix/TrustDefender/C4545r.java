package com.threatmetrix.TrustDefender;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.PowerManager;
import com.threatmetrix.TrustDefender.C4532g.C4518b.C4516a;
import com.threatmetrix.TrustDefender.C4532g.C4518b.C4517b;
import com.threatmetrix.TrustDefender.C4532g.C4526j;

/* renamed from: com.threatmetrix.TrustDefender.r */
class C4545r {
    /* renamed from: a */
    private static final String f7820a = C4549w.m8585a(C4545r.class);

    C4545r() {
    }

    @TargetApi(20)
    /* renamed from: a */
    public static boolean m8575a(Context context) {
        if (!C4526j.m8507a() || C4516a.f7584c < C4517b.f7598m) {
            return true;
        }
        try {
            Object powerService = context.getSystemService("power");
            if (powerService == null || !(powerService instanceof PowerManager)) {
                return true;
            }
            return ((PowerManager) powerService).isInteractive();
        } catch (SecurityException e) {
            String str = f7820a;
            return true;
        } catch (Exception e2) {
            C4549w.m8594c(f7820a, e2.getMessage());
            return true;
        }
    }
}
