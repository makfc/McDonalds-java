package com.threatmetrix.TrustDefender;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.threatmetrix.TrustDefender.C4532g.C4518b;
import com.threatmetrix.TrustDefender.C4532g.C4521e;
import com.threatmetrix.TrustDefender.C4532g.C4525i;
import com.threatmetrix.TrustDefender.C4532g.C4527k;
import com.threatmetrix.TrustDefender.C4532g.C4528l;
import java.util.Locale;
import java.util.UUID;

/* renamed from: com.threatmetrix.TrustDefender.an */
class C4500an {
    /* renamed from: a */
    private static final String f7482a = C4549w.m8585a(C4500an.class);

    C4500an() {
    }

    /* renamed from: a */
    static String m8394a(Context context) {
        int i = 0;
        C4528l preferences = new C4528l(context, "ThreatMetrixMobileSDK", 0);
        if (!(C4521e.f7628b == null || C4521e.f7627a == null)) {
            i = 1;
        }
        if (i == 0) {
            C4549w.m8594c(f7482a, "SharedPreferences wasn't found, generating GUID");
            return UUID.randomUUID().toString().replace("-", "").toLowerCase(Locale.US);
        }
        String genID = null;
        try {
            genID = preferences.mo34231a("ThreatMetrixMobileSDK", null);
        } catch (ClassCastException e) {
            String str = f7482a;
        }
        if (genID != null) {
            return genID;
        }
        C4549w.m8594c(f7482a, "Found nothing in shared prefs, generating GUID");
        genID = UUID.randomUUID().toString().replace("-", "").toLowerCase(Locale.US);
        preferences.mo34235b("ThreatMetrixMobileSDK", genID);
        preferences.mo34232a();
        return genID;
    }

    /* renamed from: b */
    static String m8399b(Context context) {
        String id = C4527k.m8508a(context.getContentResolver(), "ANDROID_ID");
        if (id != null && !id.equals("9774d56d682e549c") && id.length() >= 15) {
            return id;
        }
        C4549w.m8594c(f7482a, "ANDROID_ID contains nothing useful");
        return null;
    }

    /* renamed from: c */
    static String m8401c(Context context) {
        String imei = null;
        if (new C4525i(context).mo34228a("android.permission.READ_PHONE_STATE", context.getPackageName())) {
            String str;
            try {
                Object telephonyService = context.getSystemService("phone");
                if (telephonyService == null || !(telephonyService instanceof TelephonyManager)) {
                    return null;
                }
                imei = ((TelephonyManager) telephonyService).getDeviceId();
                if (imei == null || imei.equals("000000000000000")) {
                    imei = "";
                }
                if (imei.isEmpty()) {
                    C4549w.m8594c(f7482a, "Failed to get useful imei");
                }
                str = f7482a;
                new StringBuilder("imei: ").append(imei);
            } catch (SecurityException e) {
                str = f7482a;
                imei = "";
            } catch (Exception e2) {
                C4549w.m8594c(f7482a, e2.getMessage());
                imei = "";
            }
        }
        return imei;
    }

    /* renamed from: a */
    static boolean m8398a() {
        String serial = C4518b.f7609h;
        if (serial == null) {
            return false;
        }
        if (serial.equals("unknown") || serial.equals("1234567890ABCDEF")) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private static String m8395a(String in) throws InterruptedException {
        String out = in;
        if (in == null || in.length() == 0) {
            return null;
        }
        if (in.length() == 32) {
            return out;
        }
        if (in.length() < 32) {
            String md5 = C4491ai.m8344b(in);
            if (md5 == null) {
                return null;
            }
            int len = 32 - in.length();
            if (len > md5.length()) {
                len = md5.length();
            }
            out = in + md5.substring(0, len);
        } else {
            out = C4491ai.m8344b(in);
        }
        return out;
    }

    /* renamed from: a */
    static String m8397a(String genID, boolean useAltIDScheme) throws InterruptedException {
        String HTML5Cookie = genID;
        if (useAltIDScheme) {
            HTML5Cookie = C4491ai.m8344b(genID);
        }
        C4549w.m8594c(f7482a, "using generated ID for LSC:" + HTML5Cookie);
        return C4500an.m8395a(HTML5Cookie);
    }

    /* renamed from: b */
    static String m8400b(String androidID, boolean useAltIDScheme) throws InterruptedException {
        String cookie = androidID;
        if (androidID != null) {
            if (useAltIDScheme) {
                cookie = C4491ai.m8344b(androidID);
            }
            C4549w.m8594c(f7482a, "using ANDROID_ID for TPC:" + cookie);
        }
        return C4500an.m8395a(cookie);
    }

    /* renamed from: a */
    static String m8396a(String androidID, String genID, String imei, boolean useAltIDScheme) throws InterruptedException {
        String str = C4518b.f7609h == null ? "" : C4518b.f7609h;
        flashCookie = C4491ai.m8349f(imei) ? str + imei : C4491ai.m8349f(androidID) ? str + androidID : C4491ai.m8349f(genID) ? str + genID : str;
        if (useAltIDScheme) {
            flashCookie = C4491ai.m8344b(flashCookie);
        }
        return C4500an.m8395a(C4491ai.m8344b(flashCookie));
    }
}
