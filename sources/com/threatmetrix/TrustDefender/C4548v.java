package com.threatmetrix.TrustDefender;

import android.location.Location;
import com.threatmetrix.TrustDefender.C4532g.C4518b.C4516a;
import com.threatmetrix.TrustDefender.C4532g.C4518b.C4517b;
import java.lang.reflect.Method;

/* renamed from: com.threatmetrix.TrustDefender.v */
final class C4548v extends C4485at {
    /* renamed from: a */
    static final Method f7835a = C4485at.m8325a(Location.class, "isFromMockProvider", new Class[0]);
    /* renamed from: b */
    private boolean f7836b = false;

    C4548v(Location location) {
        if (C4516a.f7584c >= C4517b.f7596k) {
            Boolean b = (Boolean) C4485at.m8323a((Object) location, f7835a, new Object[0]);
            if (b != null) {
                this.f7836b = b.booleanValue();
            }
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final boolean mo34261a() {
        return this.f7836b;
    }
}
