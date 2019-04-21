package com.p044ta.utdid2.device;

import android.content.Context;
import com.p044ta.utdid2.p055a.p056a.C4317d;
import com.p044ta.utdid2.p055a.p056a.C4321f;
import java.util.zip.Adler32;

/* renamed from: com.ta.utdid2.device.b */
public class C4333b {
    /* renamed from: a */
    private static C4332a f6757a = null;
    /* renamed from: d */
    static final Object f6758d = new Object();

    /* renamed from: a */
    static long m7824a(C4332a c4332a) {
        if (c4332a != null) {
            String format = String.format("%s%s%s%s%s", new Object[]{c4332a.getUtdid(), c4332a.getDeviceId(), Long.valueOf(c4332a.mo33737a()), c4332a.getImsi(), c4332a.getImei()});
            if (!C4321f.isEmpty(format)) {
                Adler32 adler32 = new Adler32();
                adler32.reset();
                adler32.update(format.getBytes());
                return adler32.getValue();
            }
        }
        return 0;
    }

    /* renamed from: a */
    private static C4332a m7825a(Context context) {
        if (context != null) {
            synchronized (f6758d) {
                String value = C4334c.m7827a(context).getValue();
                if (!C4321f.isEmpty(value)) {
                    String substring;
                    if (value.endsWith("\n")) {
                        substring = value.substring(0, value.length() - 1);
                    } else {
                        substring = value;
                    }
                    C4332a c4332a = new C4332a();
                    long currentTimeMillis = System.currentTimeMillis();
                    String imei = C4317d.getImei(context);
                    String imsi = C4317d.getImsi(context);
                    c4332a.mo33740b(imei);
                    c4332a.setImei(imei);
                    c4332a.mo33739b(currentTimeMillis);
                    c4332a.setImsi(imsi);
                    c4332a.mo33741c(substring);
                    c4332a.mo33738a(C4333b.m7824a(c4332a));
                    return c4332a;
                }
            }
        }
        return null;
    }

    /* renamed from: b */
    public static synchronized C4332a m7826b(Context context) {
        C4332a c4332a;
        synchronized (C4333b.class) {
            if (f6757a != null) {
                c4332a = f6757a;
            } else if (context != null) {
                c4332a = C4333b.m7825a(context);
                f6757a = c4332a;
            } else {
                c4332a = null;
            }
        }
        return c4332a;
    }
}
