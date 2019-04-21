package com.threatmetrix.TrustDefender;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.provider.Settings.Secure;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

/* renamed from: com.threatmetrix.TrustDefender.g */
class C4532g {
    /* renamed from: a */
    private static final String f7766a = C4549w.m8585a(C4532g.class);

    /* renamed from: com.threatmetrix.TrustDefender.g$a */
    class C4515a {
        /* renamed from: b */
        private ApplicationInfo f7581b = null;

        C4515a(Context context) {
            if (C4520d.f7620c != null && C4520d.f7621d != null) {
                this.f7581b = context.getApplicationInfo();
            }
        }

        /* Access modifiers changed, original: final */
        /* renamed from: a */
        public final String mo34221a() {
            if (this.f7581b != null) {
                return this.f7581b.packageName;
            }
            return "";
        }

        /* Access modifiers changed, original: final */
        /* renamed from: b */
        public final String mo34222b() {
            if (this.f7581b != null) {
                return this.f7581b.sourceDir;
            }
            return "";
        }

        /* Access modifiers changed, original: final */
        /* renamed from: c */
        public final String mo34223c() {
            if (this.f7581b != null) {
                return this.f7581b.nativeLibraryDir;
            }
            return "";
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.g$b */
    static class C4518b {
        /* renamed from: a */
        static long f7602a;
        /* renamed from: b */
        static String f7603b;
        /* renamed from: c */
        static String f7604c;
        /* renamed from: d */
        static String f7605d;
        /* renamed from: e */
        static String f7606e;
        /* renamed from: f */
        static String f7607f;
        /* renamed from: g */
        static String f7608g;
        /* renamed from: h */
        static String f7609h;
        /* renamed from: i */
        static String f7610i;
        /* renamed from: j */
        static String f7611j;
        /* renamed from: k */
        static String f7612k;
        /* renamed from: l */
        static String f7613l;
        /* renamed from: m */
        static String f7614m;
        /* renamed from: n */
        private static Class<?> f7615n;

        /* renamed from: com.threatmetrix.TrustDefender.g$b$a */
        static class C4516a {
            /* renamed from: a */
            static String f7582a;
            /* renamed from: b */
            static String f7583b;
            /* renamed from: c */
            static int f7584c;
            /* renamed from: d */
            private static Class<?> f7585d;

            static {
                f7582a = null;
                f7583b = null;
                f7584c = -1;
                Class b = C4485at.m8327b("android.os.Build$VERSION");
                f7585d = b;
                if (C4485at.m8324a(b, "RELEASE") != null) {
                    f7582a = VERSION.RELEASE;
                }
                if (C4485at.m8324a(f7585d, "SDK_INT") != null) {
                    f7584c = VERSION.SDK_INT;
                }
                if (C4485at.m8324a(f7585d, "CODENAME") != null) {
                    f7583b = VERSION.CODENAME;
                }
            }
        }

        /* renamed from: com.threatmetrix.TrustDefender.g$b$b */
        static class C4517b {
            /* renamed from: a */
            static int f7586a;
            /* renamed from: b */
            static int f7587b;
            /* renamed from: c */
            static int f7588c;
            /* renamed from: d */
            static int f7589d;
            /* renamed from: e */
            static int f7590e;
            /* renamed from: f */
            static int f7591f;
            /* renamed from: g */
            static int f7592g;
            /* renamed from: h */
            static int f7593h;
            /* renamed from: i */
            static int f7594i;
            /* renamed from: j */
            static int f7595j;
            /* renamed from: k */
            static int f7596k;
            /* renamed from: l */
            static int f7597l;
            /* renamed from: m */
            static int f7598m;
            /* renamed from: n */
            static int f7599n;
            /* renamed from: o */
            static int f7600o;
            /* renamed from: p */
            private static Class<?> f7601p;

            static {
                f7586a = 8;
                f7587b = 9;
                f7588c = 10;
                f7589d = 11;
                f7590e = 12;
                f7591f = 13;
                f7592g = 14;
                f7593h = 15;
                f7594i = 16;
                f7595j = 17;
                f7596k = 18;
                f7597l = 19;
                f7598m = 20;
                f7599n = 21;
                f7600o = 22;
                Class b = C4485at.m8327b("android.os.Build$VERSION_CODES");
                f7601p = b;
                if (C4485at.m8324a(b, "FROYO") != null) {
                    f7586a = 8;
                }
                if (C4485at.m8324a(f7601p, "GINGERBREAD") != null) {
                    f7587b = 9;
                }
                if (C4485at.m8324a(f7601p, "GINGERBREAD_MR1") != null) {
                    f7588c = 10;
                }
                if (C4485at.m8324a(f7601p, "HONEYCOMB") != null) {
                    f7589d = 11;
                }
                if (C4485at.m8324a(f7601p, "HONEYCOMB_MR1") != null) {
                    f7590e = 12;
                }
                if (C4485at.m8324a(f7601p, "HONEYCOMB_MR2") != null) {
                    f7591f = 13;
                }
                if (C4485at.m8324a(f7601p, "ICE_CREAM_SANDWICH") != null) {
                    f7592g = 14;
                }
                if (C4485at.m8324a(f7601p, "ICE_CREAM_SANDWICH_MR1") != null) {
                    f7593h = 15;
                }
                if (C4485at.m8324a(f7601p, "JELLY_BEAN") != null) {
                    f7594i = 16;
                }
                if (C4485at.m8324a(f7601p, "JELLY_BEAN_MR1") != null) {
                    f7595j = 17;
                }
                if (C4485at.m8324a(f7601p, "JELLY_BEAN_MR2") != null) {
                    f7596k = 18;
                }
                if (C4485at.m8324a(f7601p, "KITKAT") != null) {
                    f7597l = 19;
                }
                if (C4485at.m8324a(f7601p, "KITKAT_WATCH") != null) {
                    f7598m = 20;
                }
                if (C4485at.m8324a(f7601p, "LOLLIPOP") != null) {
                    f7599n = 21;
                }
                if (C4485at.m8324a(f7601p, "LOLLIPOP_MR1") != null) {
                    f7600o = 22;
                }
            }
        }

        static {
            f7602a = Long.MAX_VALUE;
            f7603b = null;
            f7604c = null;
            f7605d = null;
            f7606e = null;
            f7607f = null;
            f7608g = null;
            f7609h = null;
            f7610i = null;
            f7611j = null;
            f7612k = null;
            f7613l = null;
            f7614m = null;
            Class b = C4485at.m8327b("android.os.Build");
            f7615n = b;
            if (C4485at.m8324a(b, "TIME") != null) {
                f7602a = Build.TIME;
            }
            if (C4485at.m8324a(f7615n, "TYPE") != null) {
                f7603b = Build.TYPE;
            }
            if (C4485at.m8324a(f7615n, "TAGS") != null) {
                f7604c = Build.TAGS;
            }
            if (C4485at.m8324a(f7615n, "HOST") != null) {
                f7605d = Build.HOST;
            }
            if (C4485at.m8324a(f7615n, "BRAND") != null) {
                f7606e = Build.BRAND;
            }
            if (C4485at.m8324a(f7615n, "USER") != null) {
                f7607f = Build.USER;
            }
            if (C4485at.m8324a(f7615n, "ID") != null) {
                f7608g = Build.ID;
            }
            if (C4485at.m8324a(f7615n, "SERIAL") != null) {
                f7609h = Build.SERIAL;
            }
            if (C4485at.m8324a(f7615n, "DEVICE") != null) {
                f7610i = Build.DEVICE;
            }
            if (C4485at.m8324a(f7615n, "MODEL") != null) {
                f7611j = Build.MODEL;
            }
            if (C4485at.m8324a(f7615n, "DISPLAY") != null) {
                f7612k = Build.DISPLAY;
            }
            if (C4485at.m8324a(f7615n, "PRODUCT") != null) {
                f7613l = Build.PRODUCT;
            }
            if (C4485at.m8324a(f7615n, "MANUFACTURER") != null) {
                f7614m = Build.MANUFACTURER;
            }
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.g$c */
    static class C4519c {
        /* renamed from: a */
        static Method f7616a;
        /* renamed from: b */
        private static Class<?> f7617b;

        static {
            Class b = C4485at.m8327b("android.app.admin.DevicePolicyManager");
            f7617b = b;
            f7616a = C4485at.m8325a(b, "getStorageEncryptionStatus", new Class[0]);
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.g$d */
    static class C4520d {
        /* renamed from: a */
        static final int f7618a = 1;
        /* renamed from: b */
        static final int f7619b = 128;
        /* renamed from: c */
        private static Class<?> f7620c = C4485at.m8327b("android.content.pm.ApplicationInfo");
        /* renamed from: d */
        private static Class<?> f7621d = C4485at.m8327b("android.content.pm.PackageItemInfo");
        /* renamed from: e */
        private static Class<?> f7622e = C4485at.m8327b("android.content.pm.PackageManager");
        /* renamed from: f */
        private static Class<?> f7623f = C4485at.m8327b("android.content.pm.PackageInfo");
        /* renamed from: g */
        private static Method f7624g = C4485at.m8325a(f7622e, "checkPermission", String.class, String.class);
        /* renamed from: h */
        private static Field f7625h = C4485at.m8324a(f7623f, "versionCode");
        /* renamed from: i */
        private static Field f7626i = C4485at.m8324a(f7623f, "versionName");

        static {
            Class cls = f7622e;
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.g$e */
    static class C4521e {
        /* renamed from: a */
        private static Class<?> f7627a = C4485at.m8327b("android.content.SharedPreferences$Editor");
        /* renamed from: b */
        private static Class<?> f7628b = C4485at.m8327b("android.content.SharedPreferences");
        /* renamed from: c */
        private static Method f7629c = C4485at.m8325a(f7628b, "getString", String.class, String.class);
        /* renamed from: d */
        private static Method f7630d = C4485at.m8325a(f7628b, "getInt", String.class, Integer.TYPE);
        /* renamed from: e */
        private static Method f7631e = C4485at.m8325a(f7628b, "getLong", String.class, Long.TYPE);
        /* renamed from: f */
        private static Method f7632f = C4485at.m8325a(f7627a, "putString", String.class, String.class);
        /* renamed from: g */
        private static Method f7633g = C4485at.m8325a(f7627a, "putLong", String.class, Long.TYPE);
        /* renamed from: h */
        private static Method f7634h = C4485at.m8325a(f7627a, "putInt", String.class, Integer.TYPE);
        /* renamed from: i */
        private static Method f7635i = C4485at.m8325a(f7627a, "apply", new Class[0]);
    }

    /* renamed from: com.threatmetrix.TrustDefender.g$f */
    static class C4522f {
        /* renamed from: a */
        private static Class<?> f7636a = C4485at.m8327b("android.location.Criteria");
        /* renamed from: b */
        private static Class<?> f7637b = C4485at.m8327b("android.location.Location");
        /* renamed from: c */
        private static Class<?> f7638c = C4485at.m8327b("android.location.LocationProvider");
        /* renamed from: d */
        private static Class<?> f7639d = C4485at.m8327b("android.location.LocationListener");
        /* renamed from: e */
        private static Method f7640e = C4485at.m8325a(f7636a, "setAccuracy", Integer.TYPE);
        /* renamed from: f */
        private static Method f7641f = C4485at.m8325a(f7636a, "setAltitudeRequired", Boolean.TYPE);
        /* renamed from: g */
        private static Method f7642g = C4485at.m8325a(f7636a, "setBearingAccuracy", Integer.TYPE);
        /* renamed from: h */
        private static Method f7643h = C4485at.m8325a(f7636a, "setCostAllowed", Boolean.TYPE);
        /* renamed from: i */
        private static Method f7644i = C4485at.m8325a(f7636a, "setSpeedAccuracy", Integer.TYPE);
        /* renamed from: j */
        private static Method f7645j = C4485at.m8325a(f7636a, "setSpeedRequired", Boolean.TYPE);
        /* renamed from: k */
        private static Method f7646k = C4485at.m8325a(f7636a, "setVerticalAccuracy", Integer.TYPE);
        /* renamed from: l */
        private static Method f7647l = C4485at.m8325a(f7636a, "setPowerRequirement", Integer.TYPE);
        /* renamed from: m */
        private static Method f7648m = C4485at.m8325a(f7637b, "getTime", new Class[0]);
        /* renamed from: n */
        private static Method f7649n = C4485at.m8325a(f7637b, "getProvider", new Class[0]);
        /* renamed from: o */
        private static Method f7650o = C4485at.m8325a(f7637b, "getAccuracy", new Class[0]);
        /* renamed from: p */
        private static Method f7651p = C4485at.m8325a(f7637b, "getLatitude", new Class[0]);
        /* renamed from: q */
        private static Method f7652q = C4485at.m8325a(f7637b, "getLongitude", new Class[0]);
        /* renamed from: r */
        private static Field f7653r = C4485at.m8324a(f7636a, "NO_REQUIREMENT");
        /* renamed from: s */
        private static Field f7654s = C4485at.m8324a(f7636a, "POWER_LOW");
        /* renamed from: t */
        private static Field f7655t = C4485at.m8324a(f7636a, "ACCURACY_LOW");
        /* renamed from: u */
        private static Field f7656u = C4485at.m8324a(f7636a, "ACCURACY_COARSE");
        /* renamed from: v */
        private static Field f7657v = C4485at.m8324a(f7638c, "AVAILABLE");
        /* renamed from: w */
        private static Field f7658w = C4485at.m8324a(f7638c, "TEMPORARILY_UNAVAILABLE");
        /* renamed from: x */
        private static Field f7659x = C4485at.m8324a(f7638c, "OUT_OF_SERVICE");

        /* renamed from: a */
        static boolean m8496a() {
            return (f7640e == null || f7641f == null || f7642g == null || f7643h == null || f7644i == null || f7645j == null || f7646k == null || f7647l == null || f7653r == null || f7654s == null || f7655t == null || f7656u == null) ? false : true;
        }

        /* renamed from: b */
        static boolean m8497b() {
            return (f7639d == null || f7648m == null || f7649n == null || f7651p == null || f7652q == null || f7657v == null || f7658w == null || f7659x == null) ? false : true;
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.g$g */
    static class C4523g {
        /* renamed from: a */
        private static Class<?> f7660a = C4485at.m8327b("android.net.ConnectivityManager");
        /* renamed from: b */
        private static Class<?> f7661b = C4485at.m8327b("android.net.NetworkInfo");
        /* renamed from: c */
        private static Class<?> f7662c = C4485at.m8327b("android.net.NetworkInfo$State");
        /* renamed from: d */
        private static Class<?> f7663d = C4485at.m8327b("android.net.wifi.WifiInfo");
        /* renamed from: e */
        private static Class<?> f7664e = C4485at.m8327b("android.net.wifi.WifiManager");
        /* renamed from: f */
        private static Method f7665f = C4485at.m8325a(f7660a, "getActiveNetworkInfo", new Class[0]);
        /* renamed from: g */
        private static Method f7666g = C4485at.m8325a(f7661b, "isConnectedOrConnecting", new Class[0]);
        /* renamed from: h */
        private static Method f7667h = C4485at.m8325a(f7661b, "getState", new Class[0]);
        /* renamed from: i */
        private static Method f7668i = C4485at.m8325a(f7661b, "getType", new Class[0]);
        /* renamed from: j */
        private static Method f7669j = C4485at.m8325a(f7661b, "getExtraInfo", new Class[0]);
        /* renamed from: k */
        private static Method f7670k = C4485at.m8325a(f7663d, "getBSSID", new Class[0]);
        /* renamed from: l */
        private static Method f7671l = C4485at.m8325a(f7663d, "getSSID", new Class[0]);
        /* renamed from: m */
        private static Method f7672m = C4485at.m8325a(f7663d, "getRssi", new Class[0]);
        /* renamed from: n */
        private static Method f7673n = C4485at.m8325a(f7664e, "getConnectionInfo", new Class[0]);
        /* renamed from: o */
        private static Field f7674o = C4485at.m8324a(f7660a, "CONNECTIVITY_ACTION");
        /* renamed from: p */
        private static Field f7675p = C4485at.m8324a(f7660a, "TYPE_MOBILE");
        /* renamed from: q */
        private static Field f7676q = C4485at.m8324a(f7660a, "TYPE_WIFI");
        /* renamed from: r */
        private static Field f7677r = C4485at.m8324a(f7660a, "TYPE_BLUETOOTH");
        /* renamed from: s */
        private static Field f7678s = C4485at.m8324a(f7660a, "TYPE_ETHERNET");
        /* renamed from: t */
        private static Field f7679t = C4485at.m8324a(f7662c, "CONNECTED");
        /* renamed from: u */
        private static Field f7680u = C4485at.m8324a(f7664e, "NETWORK_STATE_CHANGED_ACTION");

        /* renamed from: a */
        static boolean m8498a() {
            return (f7665f == null || f7666g == null) ? false : true;
        }

        /* renamed from: b */
        static boolean m8499b() {
            boolean result;
            if (f7674o == null || f7679t == null || f7667h == null || f7669j == null || f7668i == null || f7675p == null || f7676q == null) {
                result = false;
            } else {
                result = true;
            }
            if (C4516a.f7584c < C4517b.f7591f) {
                return result;
            }
            if (!result || f7678s == null || f7677r == null) {
                return false;
            }
            return true;
        }

        /* renamed from: c */
        static boolean m8500c() {
            return (f7680u == null || f7679t == null || f7670k == null || f7671l == null || f7672m == null || f7667h == null || f7669j == null) ? false : true;
        }

        /* renamed from: d */
        static boolean m8501d() {
            return (f7673n == null || f7670k == null || f7671l == null || f7672m == null) ? false : true;
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.g$h */
    class C4524h {
        /* renamed from: b */
        private PackageInfo f7682b = null;

        C4524h(Context context, String pkgName, int flags) {
            if (C4520d.f7623f != null && C4520d.f7622e != null) {
                try {
                    this.f7682b = context.getPackageManager().getPackageInfo(pkgName, flags);
                } catch (NameNotFoundException e) {
                    C4532g.f7766a;
                } catch (SecurityException e2) {
                    C4532g.f7766a;
                } catch (Exception e3) {
                    C4549w.m8594c(C4532g.f7766a, e3.getMessage());
                }
            }
        }

        /* Access modifiers changed, original: final */
        /* renamed from: a */
        public final int mo34224a() {
            if (C4520d.f7625h == null || this.f7682b == null) {
                return -1;
            }
            return this.f7682b.versionCode;
        }

        /* Access modifiers changed, original: final */
        /* renamed from: b */
        public final String mo34225b() {
            if (C4520d.f7626i == null || this.f7682b == null) {
                return null;
            }
            return this.f7682b.versionName;
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.g$i */
    class C4525i {
        /* renamed from: b */
        private PackageManager f7684b = null;

        C4525i(Context context) {
            if (C4520d.f7622e != null) {
                try {
                    this.f7684b = context.getPackageManager();
                } catch (SecurityException e) {
                    C4532g.f7766a;
                } catch (Exception e2) {
                    C4549w.m8594c(C4532g.f7766a, e2.getMessage());
                }
            }
        }

        /* Access modifiers changed, original: final */
        /* renamed from: a */
        public final boolean mo34228a(String permName, String pkgName) {
            if (C4520d.f7624g == null || this.f7684b == null) {
                return false;
            }
            try {
                if (this.f7684b.checkPermission(permName, pkgName) == 0) {
                    return true;
                }
                return false;
            } catch (SecurityException e) {
                C4532g.f7766a;
                return false;
            } catch (Exception e2) {
                C4549w.m8594c(C4532g.f7766a, e2.getMessage());
                return false;
            }
        }

        /* Access modifiers changed, original: final */
        /* renamed from: a */
        public final boolean mo34227a(String packageName, int flags) {
            if (!(C4520d.f7622e == null || C4520d.f7623f == null || this.f7684b == null)) {
                try {
                    this.f7684b.getPackageInfo(packageName, flags);
                    return true;
                } catch (NameNotFoundException e) {
                    C4532g.f7766a;
                } catch (SecurityException e2) {
                    C4532g.f7766a;
                } catch (Exception e3) {
                    C4549w.m8594c(C4532g.f7766a, e3.getMessage());
                }
            }
            return false;
        }

        /* Access modifiers changed, original: final */
        /* renamed from: a */
        public final ArrayList<String> mo34226a() {
            ArrayList<String> packageList = new ArrayList();
            if (!(C4520d.f7622e == null || C4520d.f7620c == null || this.f7684b == null)) {
                try {
                    for (ApplicationInfo a : this.f7684b.getInstalledApplications(0)) {
                        if (!(a.sourceDir.startsWith("/system/app") || a.sourceDir.startsWith("/system/priv-app"))) {
                            packageList.add(a.sourceDir);
                        }
                    }
                } catch (SecurityException e) {
                    C4532g.f7766a;
                } catch (Exception e2) {
                    C4549w.m8594c(C4532g.f7766a, e2.getMessage());
                }
            }
            packageList.add("/system/app");
            packageList.add("/system/priv-app");
            return packageList;
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.g$j */
    static class C4526j {
        /* renamed from: a */
        private static Class<?> f7685a;
        /* renamed from: b */
        private static Method f7686b;
        /* renamed from: c */
        private static Method f7687c = C4485at.m8325a(f7685a, "isScreenOn", new Class[0]);

        static {
            Class b = C4485at.m8327b("android.os.PowerManager");
            f7685a = b;
            f7686b = C4485at.m8325a(b, "isInteractive", new Class[0]);
        }

        /* renamed from: a */
        static boolean m8507a() {
            return (f7685a == null || f7687c == null) ? false : true;
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.g$k */
    static class C4527k {
        /* renamed from: a */
        private static Class<?> f7688a;
        /* renamed from: b */
        private static Method f7689b;
        /* renamed from: c */
        private static Field f7690c = C4485at.m8324a(f7688a, "ANDROID_ID");
        /* renamed from: d */
        private static Field f7691d = C4485at.m8324a(f7688a, "ALLOW_MOCK_LOCATION");

        static {
            Class b = C4485at.m8327b("android.provider.Settings$Secure");
            f7688a = b;
            f7689b = C4485at.m8325a(b, "getString", ContentResolver.class, String.class);
        }

        /* renamed from: a */
        static String m8508a(ContentResolver contentResolver, String name) {
            if (contentResolver == null || C4491ai.m8348e(name) || f7689b == null) {
                return null;
            }
            try {
                if ("ANDROID_ID".equals(name) && f7690c != null) {
                    return Secure.getString(contentResolver, "android_id");
                }
                if (!"ALLOW_MOCK_LOCATION".equals(name) || f7691d == null) {
                    return null;
                }
                return Secure.getString(contentResolver, "mock_location");
            } catch (SecurityException e) {
                C4532g.f7766a;
                return null;
            } catch (Exception e2) {
                C4549w.m8594c(C4532g.f7766a, e2.getMessage());
                return null;
            }
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.g$l */
    class C4528l {
        /* renamed from: b */
        private SharedPreferences f7693b = null;
        /* renamed from: c */
        private Editor f7694c = null;

        C4528l(Context context, String label, int mode) {
            if (C4521e.f7628b != null) {
                this.f7693b = context.getSharedPreferences(label, 0);
            }
            if (C4521e.f7627a != null) {
                this.f7694c = this.f7693b.edit();
            }
        }

        /* Access modifiers changed, original: final */
        /* renamed from: a */
        public final int mo34229a(String key, int defaultValue) {
            if (C4521e.f7630d == null || this.f7693b == null) {
                return 0;
            }
            return this.f7693b.getInt(key, 0);
        }

        /* Access modifiers changed, original: final */
        /* renamed from: a */
        public final long mo34230a(String key, long defaultValue) {
            if (C4521e.f7631e == null || this.f7693b == null) {
                return 0;
            }
            return this.f7693b.getLong(key, 0);
        }

        /* Access modifiers changed, original: final */
        /* renamed from: a */
        public final String mo34231a(String key, String defaultValue) {
            if (C4521e.f7629c == null || this.f7693b == null) {
                return defaultValue;
            }
            return this.f7693b.getString(key, defaultValue);
        }

        /* Access modifiers changed, original: final */
        /* renamed from: b */
        public final void mo34233b(String key, int value) {
            if (C4521e.f7634h != null && this.f7694c != null) {
                this.f7694c.putInt(key, value);
            }
        }

        /* Access modifiers changed, original: final */
        /* renamed from: b */
        public final void mo34234b(String key, long value) {
            if (C4521e.f7633g != null && this.f7694c != null) {
                this.f7694c.putLong(key, value);
            }
        }

        /* Access modifiers changed, original: final */
        /* renamed from: b */
        public final void mo34235b(String key, String value) {
            if (C4521e.f7632f != null && this.f7694c != null) {
                this.f7694c.putString(key, value);
            }
        }

        /* Access modifiers changed, original: final */
        /* renamed from: a */
        public final void mo34232a() {
            if (C4521e.f7635i != null && this.f7694c != null) {
                this.f7694c.apply();
            }
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.g$m */
    static class C4529m {
        /* renamed from: a */
        private static Class<?> f7695a;
        /* renamed from: b */
        private static Method f7696b;
        /* renamed from: c */
        private static Method f7697c = C4485at.m8325a(f7695a, "elapsedRealtime", new Class[0]);

        static {
            Class b = C4485at.m8327b("android.os.SystemClock");
            f7695a = b;
            f7696b = C4485at.m8325a(b, "uptimeMillis", new Class[0]);
        }

        /* renamed from: a */
        static long m8516a() {
            if (f7696b != null) {
                return SystemClock.uptimeMillis();
            }
            return 0;
        }

        /* renamed from: b */
        static long m8517b() {
            if (f7697c != null) {
                return SystemClock.elapsedRealtime();
            }
            return 0;
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.g$n */
    static class C4530n {
        /* renamed from: A */
        private static Method f7698A;
        /* renamed from: B */
        private static Method f7699B;
        /* renamed from: C */
        private static Method f7700C;
        /* renamed from: D */
        private static Method f7701D;
        /* renamed from: E */
        private static Method f7702E;
        /* renamed from: F */
        private static Method f7703F;
        /* renamed from: G */
        private static Method f7704G;
        /* renamed from: H */
        private static Method f7705H;
        /* renamed from: I */
        private static Method f7706I;
        /* renamed from: J */
        private static Method f7707J;
        /* renamed from: K */
        private static Method f7708K;
        /* renamed from: L */
        private static Method f7709L;
        /* renamed from: M */
        private static Method f7710M;
        /* renamed from: N */
        private static Method f7711N;
        /* renamed from: O */
        private static Method f7712O;
        /* renamed from: P */
        private static Method f7713P;
        /* renamed from: Q */
        private static Method f7714Q;
        /* renamed from: R */
        private static Method f7715R;
        /* renamed from: S */
        private static Method f7716S;
        /* renamed from: T */
        private static Method f7717T;
        /* renamed from: U */
        private static Method f7718U;
        /* renamed from: V */
        private static Method f7719V;
        /* renamed from: W */
        private static Method f7720W;
        /* renamed from: X */
        private static Field f7721X;
        /* renamed from: Y */
        private static Field f7722Y;
        /* renamed from: Z */
        private static Field f7723Z;
        /* renamed from: a */
        private static Class<?> f7724a = C4485at.m8327b("android.telephony.TelephonyManager");
        /* renamed from: b */
        private static Class<?> f7725b = C4485at.m8327b("android.telephony.CellIdentityCdma");
        /* renamed from: c */
        private static Class<?> f7726c = C4485at.m8327b("android.telephony.CellIdentityGsm");
        /* renamed from: d */
        private static Class<?> f7727d = C4485at.m8327b("android.telephony.CellIdentityLte");
        /* renamed from: e */
        private static Class<?> f7728e = C4485at.m8327b("android.telephony.CellIdentityWcdma");
        /* renamed from: f */
        private static Class<?> f7729f = C4485at.m8327b("android.telephony.CellInfo");
        /* renamed from: g */
        private static Class<?> f7730g = C4485at.m8327b("android.telephony.CellInfoCdma");
        /* renamed from: h */
        private static Class<?> f7731h = C4485at.m8327b("android.telephony.CellInfoGsm");
        /* renamed from: i */
        private static Class<?> f7732i = C4485at.m8327b("android.telephony.CellInfoLte");
        /* renamed from: j */
        private static Class<?> f7733j = C4485at.m8327b("android.telephony.CellInfoWcdma");
        /* renamed from: k */
        private static Class<?> f7734k = C4485at.m8327b("android.telephony.CellSignalStrength");
        /* renamed from: l */
        private static Class<?> f7735l = C4485at.m8327b("android.telephony.NeighboringCellInfo");
        /* renamed from: m */
        private static Class<?> f7736m = C4485at.m8327b("android.telephony.CellLocation");
        /* renamed from: n */
        private static Class<?> f7737n = C4485at.m8327b("android.telephony.SubscriptionInfo");
        /* renamed from: o */
        private static Class<?> f7738o = C4485at.m8327b("android.telephony.SubscriptionManager");
        /* renamed from: p */
        private static Class<?> f7739p = C4485at.m8327b("android.telephony.cdma.CdmaCellLocation");
        /* renamed from: q */
        private static Class<?> f7740q = C4485at.m8327b("android.telephony.gsm.GsmCellLocation");
        /* renamed from: r */
        private static Method f7741r;
        /* renamed from: s */
        private static Method f7742s;
        /* renamed from: t */
        private static Method f7743t;
        /* renamed from: u */
        private static Method f7744u;
        /* renamed from: v */
        private static Method f7745v = C4485at.m8325a(f7724a, "getCellLocation", new Class[0]);
        /* renamed from: w */
        private static Method f7746w;
        /* renamed from: x */
        private static Method f7747x;
        /* renamed from: y */
        private static Method f7748y;
        /* renamed from: z */
        private static Method f7749z;

        /* renamed from: a */
        static boolean m8518a() {
            f7742s = C4485at.m8325a(f7724a, "getAllCellInfo", new Class[0]);
            f7719V = C4485at.m8325a(f7729f, "isRegistered", new Class[0]);
            if (f7724a == null || f7734k == null || f7729f == null || f7719V == null || f7742s == null) {
                return false;
            }
            return true;
        }

        /* renamed from: b */
        static boolean m8519b() {
            f7741r = C4485at.m8325a(f7724a, "getNetworkOperator", new Class[0]);
            f7743t = C4485at.m8325a(f7724a, "getNetworkCountryIso", new Class[0]);
            f7744u = C4485at.m8328b(f7724a, "getNetworkOperatorName", new Class[0]);
            if (f7724a == null || f7741r == null || f7743t == null || f7744u == null) {
                return false;
            }
            return true;
        }

        /* renamed from: c */
        static boolean m8520c() {
            f7699B = C4485at.m8325a(f7739p, "getSystemId", new Class[0]);
            f7700C = C4485at.m8325a(f7739p, "getBaseStationId", new Class[0]);
            f7701D = C4485at.m8325a(f7739p, "getBaseStationLatitude", new Class[0]);
            f7702E = C4485at.m8325a(f7739p, "getBaseStationLongitude", new Class[0]);
            if (f7736m == null || f7745v == null || f7699B == null || f7700C == null || f7701D == null || f7702E == null) {
                return false;
            }
            return true;
        }

        /* renamed from: d */
        static boolean m8521d() {
            f7746w = C4485at.m8325a(f7740q, "getLac", new Class[0]);
            f7747x = C4485at.m8325a(f7740q, "getCid", new Class[0]);
            f7748y = C4485at.m8325a(f7740q, "getPsc", new Class[0]);
            if (f7736m == null || f7745v == null || f7747x == null || f7746w == null || f7748y == null) {
                return false;
            }
            return true;
        }

        /* renamed from: e */
        static boolean m8522e() {
            f7749z = C4485at.m8325a(f7735l, "getCid", new Class[0]);
            f7698A = C4485at.m8325a(f7735l, "getRssi", new Class[0]);
            if (f7735l == null || f7749z == null || f7698A == null) {
                return false;
            }
            return true;
        }

        /* renamed from: f */
        static boolean m8523f() {
            f7704G = C4485at.m8325a(f7737n, "getSimSlotIndex", new Class[0]);
            f7705H = C4485at.m8325a(f7737n, "getCarrierName", new Class[0]);
            f7706I = C4485at.m8325a(f7737n, "getDisplayName", new Class[0]);
            f7707J = C4485at.m8325a(f7737n, "getIccId", new Class[0]);
            f7708K = C4485at.m8325a(f7737n, "getNumber", new Class[0]);
            f7709L = C4485at.m8325a(f7737n, "getCountryIso", new Class[0]);
            f7710M = C4485at.m8325a(f7737n, "getDataRoaming", new Class[0]);
            f7703F = C4485at.m8325a(f7738o, "getActiveSubscriptionInfoList", new Class[0]);
            if (f7738o == null || f7737n == null || f7704G == null || f7705H == null || f7706I == null || f7707J == null || f7708K == null || f7709L == null || f7710M == null || f7703F == null) {
                return false;
            }
            return true;
        }

        /* renamed from: g */
        static boolean m8524g() {
            f7717T = C4485at.m8328b(f7733j, "getCellSignalStrength", new Class[0]);
            f7718U = C4485at.m8328b(f7733j, "getCellIdentity", new Class[0]);
            if (f7728e == null || f7717T == null || f7718U == null) {
                return false;
            }
            return true;
        }

        /* renamed from: h */
        static boolean m8525h() {
            f7713P = C4485at.m8328b(f7731h, "getCellSignalStrength", new Class[0]);
            f7714Q = C4485at.m8328b(f7731h, "getCellIdentity", new Class[0]);
            if (f7726c == null || f7713P == null || f7714Q == null) {
                return false;
            }
            return true;
        }

        /* renamed from: i */
        static boolean m8526i() {
            f7715R = C4485at.m8328b(f7732i, "getCellSignalStrength", new Class[0]);
            f7716S = C4485at.m8328b(f7732i, "getCellIdentity", new Class[0]);
            if (f7727d == null || f7715R == null || f7716S == null) {
                return false;
            }
            return true;
        }

        /* renamed from: j */
        static boolean m8527j() {
            f7711N = C4485at.m8328b(f7730g, "getCellSignalStrength", new Class[0]);
            f7712O = C4485at.m8328b(f7730g, "getCellIdentity", new Class[0]);
            if (f7725b == null || f7711N == null || f7712O == null) {
                return false;
            }
            return true;
        }

        /* renamed from: k */
        static boolean m8528k() {
            f7720W = C4485at.m8325a(f7724a, "getDataState", new Class[0]);
            f7721X = C4485at.m8324a(f7724a, "DATA_CONNECTED");
            f7722Y = C4485at.m8324a(f7724a, "DATA_CONNECTING");
            f7723Z = C4485at.m8324a(f7724a, "DATA_SUSPENDED");
            if (f7724a == null || f7720W == null || f7721X == null || f7722Y == null || f7723Z == null) {
                return false;
            }
            return true;
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.g$o */
    static class C4531o {
        /* renamed from: a */
        private static Class<?> f7750a = C4485at.m8327b("android.webkit.WebView");
        /* renamed from: b */
        private static Class<?> f7751b = C4485at.m8327b("android.webkit.WebViewClient");
        /* renamed from: c */
        private static Class<?> f7752c = C4485at.m8327b("android.webkit.WebSettings");
        /* renamed from: d */
        private static Class<?> f7753d = C4485at.m8327b("android.webkit.WebSettings$PluginState");
        /* renamed from: e */
        private static Class<?> f7754e = C4485at.m8327b("android.webkit.WebChromeClient");
        /* renamed from: f */
        private static Class<?> f7755f;
        /* renamed from: g */
        private static Method f7756g;
        /* renamed from: h */
        private static Method f7757h = C4485at.m8325a(f7750a, "destroy", new Class[0]);
        /* renamed from: i */
        private static Method f7758i = C4485at.m8325a(f7750a, "loadUrl", String.class);
        /* renamed from: j */
        private static Method f7759j = C4485at.m8325a(f7750a, "loadData", String.class, String.class, String.class);
        /* renamed from: k */
        private static Method f7760k = C4485at.m8325a(f7750a, "getSettings", new Class[0]);
        /* renamed from: l */
        private static Method f7761l = C4485at.m8325a(f7750a, "setWebViewClient", f7751b);
        /* renamed from: m */
        private static Method f7762m = C4485at.m8325a(f7750a, "setWebChromeClient", f7754e);
        /* renamed from: n */
        private static Method f7763n = C4485at.m8325a(f7752c, "getUserAgentString", new Class[0]);
        /* renamed from: o */
        private static Method f7764o = C4485at.m8325a(f7752c, "setJavaScriptEnabled", Boolean.TYPE);
        /* renamed from: p */
        private static Field f7765p = C4485at.m8324a(f7753d, "ON");

        static {
            Class b = C4485at.m8327b("android.webkit.JsResult");
            f7755f = b;
            f7756g = C4485at.m8325a(b, "confirm", new Class[0]);
        }

        /* renamed from: a */
        static boolean m8529a() {
            return (f7751b == null || f7754e == null || f7756g == null || f7757h == null || f7758i == null || f7759j == null || f7760k == null || f7761l == null || f7762m == null || f7763n == null || f7764o == null || f7765p == null) ? false : true;
        }
    }

    C4532g() {
    }
}
