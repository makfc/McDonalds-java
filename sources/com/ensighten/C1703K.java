package com.ensighten;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;

/* renamed from: com.ensighten.K */
public final class C1703K implements C1701J {
    /* renamed from: a */
    String f5641a = "loading";
    /* renamed from: b */
    String f5642b = "loading";

    public C1703K(final Context context) {
        new Thread(new Runnable() {
            public final void run() {
                C1703K c1703k = C1703K.this;
                Context context = context;
                Info info = null;
                try {
                    if (C1845i.m7359f()) {
                        C1845i.m7345a("Attempting to retrieve the Google Play advertising id.");
                    }
                    info = AdvertisingIdClient.getAdvertisingIdInfo(context);
                } catch (Throwable th) {
                    if (C1845i.m7359f()) {
                        C1845i.m7345a("Unable to retrieve the Google Play advertising id.");
                    }
                }
                if (info != null) {
                    c1703k.f5641a = info.getId();
                    if (C1845i.m7359f()) {
                        C1845i.m7345a(String.format("The Google Play advertising id is %s.", new Object[]{c1703k.f5641a}));
                    }
                    c1703k.f5642b = String.valueOf(info.isLimitAdTrackingEnabled());
                    if (C1845i.m7359f()) {
                        C1845i.m7345a(String.format("The Google Play isLimitAdTrackingEnabled is %s.", new Object[]{c1703k.f5642b}));
                        return;
                    }
                    return;
                }
                c1703k.f5641a = "unavailable";
                c1703k.f5642b = "unavailable";
            }
        }).start();
    }

    /* renamed from: a */
    public final String mo15031a() {
        return this.f5641a;
    }

    /* renamed from: b */
    public final String mo15032b() {
        return this.f5642b;
    }
}
