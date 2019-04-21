package com.baidu.android.pushservice.p033e;

import android.content.Context;
import com.baidu.android.pushservice.config.ModeConfig;
import com.baidu.android.pushservice.p036h.C1425a;
import java.util.HashMap;

/* renamed from: com.baidu.android.pushservice.e.ac */
public class C1368ac extends C1367d {

    /* renamed from: com.baidu.android.pushservice.e.ac$a */
    private enum C1366a {
        MODEL_O(1),
        MODEL_C(2),
        MODEL_HW(3),
        MODEL_XM(4);
        
        /* renamed from: e */
        private int f4816e;

        private C1366a(int i) {
            this.f4816e = i;
        }

        /* renamed from: a */
        private int m6197a() {
            return this.f4816e;
        }
    }

    public C1368ac(C1378l c1378l, Context context) {
        super(c1378l, context);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo13722a(HashMap<String, String> hashMap) {
        super.mo13722a((HashMap) hashMap);
        hashMap.put("method", "unbind");
        int a = C1366a.MODEL_O.m6197a();
        int currentMode = ModeConfig.getInstance(this.f4799a).getCurrentMode();
        if (ModeConfig.isHuaweiProxyMode(this.f4799a)) {
            a = C1366a.MODEL_HW.m6197a();
        } else if (ModeConfig.isXiaomiProxyMode(this.f4799a)) {
            a = C1366a.MODEL_XM.m6197a();
        } else if (currentMode == ModeConfig.MODE_C || currentMode == ModeConfig.MODE_C_C || currentMode == ModeConfig.MODE_C_H) {
            a = C1366a.MODEL_C.m6197a();
        }
        hashMap.put("model", a + "");
        C1425a.m6442c("Unbind", "UNBIND param -- " + C1370b.m6202a((HashMap) hashMap));
    }
}
