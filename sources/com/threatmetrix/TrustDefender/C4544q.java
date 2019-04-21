package com.threatmetrix.TrustDefender;

import android.annotation.TargetApi;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import com.threatmetrix.TrustDefender.C4532g.C4518b.C4516a;
import com.threatmetrix.TrustDefender.C4532g.C4518b.C4517b;
import com.threatmetrix.TrustDefender.C4532g.C4519c;

/* renamed from: com.threatmetrix.TrustDefender.q */
class C4544q {
    /* renamed from: a */
    private static final String f7819a = C4549w.m8585a(C4544q.class);

    C4544q() {
    }

    @TargetApi(11)
    /* renamed from: a */
    static int m8574a(Context context) {
        if (C4519c.f7616a == null) {
            return 16;
        }
        if (C4516a.f7584c < C4517b.f7589d) {
            return 1;
        }
        try {
            Object policyService = context.getSystemService("device_policy");
            if (policyService == null || !(policyService instanceof DevicePolicyManager)) {
                return 16;
            }
            return ((DevicePolicyManager) policyService).getStorageEncryptionStatus();
        } catch (SecurityException e) {
            String str = f7819a;
            return 16;
        } catch (Exception e2) {
            C4549w.m8594c(f7819a, e2.getMessage());
            return 16;
        }
    }
}
