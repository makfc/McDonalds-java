package com.p044ta.utdid2.p055a.p056a;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.util.Random;

/* renamed from: com.ta.utdid2.a.a.d */
public class C4317d {
    public static String getUniqueID() {
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        int nanoTime = (int) System.nanoTime();
        int nextInt = new Random().nextInt();
        int nextInt2 = new Random().nextInt();
        byte[] bytes = C4316c.getBytes(currentTimeMillis);
        byte[] bytes2 = C4316c.getBytes(nanoTime);
        byte[] bytes3 = C4316c.getBytes(nextInt);
        byte[] bytes4 = C4316c.getBytes(nextInt2);
        byte[] bArr = new byte[16];
        System.arraycopy(bytes, 0, bArr, 0, 4);
        System.arraycopy(bytes2, 0, bArr, 4, 4);
        System.arraycopy(bytes3, 0, bArr, 8, 4);
        System.arraycopy(bytes4, 0, bArr, 12, 4);
        return C4315b.encodeToString(bArr, 2);
    }

    public static String getImei(Context context) {
        String str = null;
        if (context != null) {
            try {
                String deviceId;
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    deviceId = telephonyManager.getDeviceId();
                } else {
                    deviceId = null;
                }
                str = deviceId;
            } catch (Exception e) {
            }
        }
        if (C4321f.isEmpty(str)) {
            str = C4317d.m7758a();
        }
        if (C4321f.isEmpty(str)) {
            return C4317d.getUniqueID();
        }
        return str;
    }

    /* renamed from: a */
    private static String m7758a() {
        String str = C4322g.get("ro.aliyun.clouduuid", "");
        if (TextUtils.isEmpty(str)) {
            str = C4322g.get("ro.sys.aliyun.clouduuid", "");
        }
        if (TextUtils.isEmpty(str)) {
            return C4317d.m7759b();
        }
        return str;
    }

    /* renamed from: b */
    private static String m7759b() {
        try {
            return (String) Class.forName("com.yunos.baseservice.clouduuid.CloudUUID").getMethod("getCloudUUID", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e) {
            return "";
        }
    }

    public static String getImsi(Context context) {
        String str = null;
        if (context != null) {
            try {
                String subscriberId;
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    subscriberId = telephonyManager.getSubscriberId();
                } else {
                    subscriberId = null;
                }
                str = subscriberId;
            } catch (Exception e) {
            }
        }
        if (C4321f.isEmpty(str)) {
            return C4317d.getUniqueID();
        }
        return str;
    }
}
