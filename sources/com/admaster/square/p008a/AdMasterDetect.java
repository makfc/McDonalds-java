package com.admaster.square.p008a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Vibrator;
import android.telephony.TelephonyManager;
import java.io.File;
import java.io.FileInputStream;

/* renamed from: com.admaster.square.a.b */
public class AdMasterDetect {
    /* renamed from: a */
    private static final String f140a = AdMasterDetect.class.getSimpleName();
    /* renamed from: b */
    private static AdMasterDetect f141b = null;
    /* renamed from: e */
    private static final String[] f142e = new String[]{"15555215554", "15555215556", "15555215558", "15555215560", "15555215562", "15555215564", "15555215566", "15555215568", "15555215570", "15555215572", "15555215574", "15555215576", "15555215578", "15555215580", "15555215582", "15555215584"};
    /* renamed from: f */
    private static final String[] f143f = new String[]{"000000000000000"};
    /* renamed from: g */
    private static final String[] f144g = new String[]{"310260000000000"};
    /* renamed from: h */
    private static final String[] f145h = new String[]{"/dev/socket/qemud", "/dev/qemu_pipe"};
    /* renamed from: i */
    private static final String[] f146i = new String[]{"goldfish"};
    /* renamed from: c */
    private Context f147c;
    /* renamed from: d */
    private int f148d = 0;

    private AdMasterDetect(Context context) {
        this.f147c = context;
        this.f148d = 0;
    }

    /* renamed from: a */
    public static AdMasterDetect m261a(Context context) {
        if (context == null) {
            throw new NullPointerException("context can not be null!");
        }
        if (f141b == null) {
            f141b = new AdMasterDetect(context);
        }
        return f141b;
    }

    /* renamed from: a */
    private static void m262a(String str) {
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0075 A:{SYNTHETIC, Splitter:B:33:0x0075} */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0062 A:{SYNTHETIC, Splitter:B:24:0x0062} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0075 A:{SYNTHETIC, Splitter:B:33:0x0075} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0075 A:{SYNTHETIC, Splitter:B:33:0x0075} */
    /* renamed from: a */
    public java.lang.String mo7726a() {
        /*
        r7 = this;
        r2 = 0;
        r0 = "0";
        r1 = java.lang.Runtime.getRuntime();	 Catch:{ Exception -> 0x0053, all -> 0x006b }
        r3 = "getprop ro.product.brand";
        r3 = r1.exec(r3);	 Catch:{ Exception -> 0x0053, all -> 0x006b }
        r1 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x008b, all -> 0x0083 }
        r4 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x008b, all -> 0x0083 }
        r5 = r3.getInputStream();	 Catch:{ Exception -> 0x008b, all -> 0x0083 }
        r6 = "GBK";
        r4.<init>(r5, r6);	 Catch:{ Exception -> 0x008b, all -> 0x0083 }
        r1.<init>(r4);	 Catch:{ Exception -> 0x008b, all -> 0x0083 }
        r3.waitFor();	 Catch:{ Exception -> 0x008e, all -> 0x0086 }
        r2 = r1.readLine();	 Catch:{ Exception -> 0x008e, all -> 0x0086 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x008e, all -> 0x0086 }
        r5 = "brt:";
        r4.<init>(r5);	 Catch:{ Exception -> 0x008e, all -> 0x0086 }
        r4 = r4.append(r2);	 Catch:{ Exception -> 0x008e, all -> 0x0086 }
        r4 = r4.toString();	 Catch:{ Exception -> 0x008e, all -> 0x0086 }
        com.admaster.square.p008a.AdMasterDetect.m262a(r4);	 Catch:{ Exception -> 0x008e, all -> 0x0086 }
        if (r2 == 0) goto L_0x0048;
    L_0x0038:
        r4 = "generic";
        r2 = r2.equals(r4);	 Catch:{ Exception -> 0x008e, all -> 0x0086 }
        if (r2 == 0) goto L_0x0048;
    L_0x0040:
        r0 = "1";
        r2 = r7.f148d;	 Catch:{ Exception -> 0x008e, all -> 0x0086 }
        r2 = r2 + 1;
        r7.f148d = r2;	 Catch:{ Exception -> 0x008e, all -> 0x0086 }
    L_0x0048:
        if (r3 == 0) goto L_0x004d;
    L_0x004a:
        r3.destroy();
    L_0x004d:
        if (r1 == 0) goto L_0x0052;
    L_0x004f:
        r1.close();	 Catch:{ Exception -> 0x007e }
    L_0x0052:
        return r0;
    L_0x0053:
        r0 = move-exception;
        r1 = r0;
        r3 = r2;
    L_0x0056:
        r0 = "E";
        r1.printStackTrace();	 Catch:{ all -> 0x0088 }
        if (r3 == 0) goto L_0x0060;
    L_0x005d:
        r3.destroy();
    L_0x0060:
        if (r2 == 0) goto L_0x0052;
    L_0x0062:
        r2.close();	 Catch:{ Exception -> 0x0066 }
        goto L_0x0052;
    L_0x0066:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0052;
    L_0x006b:
        r0 = move-exception;
        r1 = r2;
        r3 = r2;
    L_0x006e:
        if (r3 == 0) goto L_0x0073;
    L_0x0070:
        r3.destroy();
    L_0x0073:
        if (r1 == 0) goto L_0x0078;
    L_0x0075:
        r1.close();	 Catch:{ Exception -> 0x0079 }
    L_0x0078:
        throw r0;
    L_0x0079:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0078;
    L_0x007e:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0052;
    L_0x0083:
        r0 = move-exception;
        r1 = r2;
        goto L_0x006e;
    L_0x0086:
        r0 = move-exception;
        goto L_0x006e;
    L_0x0088:
        r0 = move-exception;
        r1 = r2;
        goto L_0x006e;
    L_0x008b:
        r0 = move-exception;
        r1 = r0;
        goto L_0x0056;
    L_0x008e:
        r0 = move-exception;
        r2 = r1;
        r1 = r0;
        goto L_0x0056;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.admaster.square.p008a.AdMasterDetect.mo7726a():java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0075 A:{SYNTHETIC, Splitter:B:33:0x0075} */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0062 A:{SYNTHETIC, Splitter:B:24:0x0062} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0075 A:{SYNTHETIC, Splitter:B:33:0x0075} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0075 A:{SYNTHETIC, Splitter:B:33:0x0075} */
    /* renamed from: b */
    public java.lang.String mo7727b() {
        /*
        r7 = this;
        r2 = 0;
        r0 = "0";
        r1 = java.lang.Runtime.getRuntime();	 Catch:{ Exception -> 0x0053, all -> 0x006b }
        r3 = "getprop ro.product.model";
        r3 = r1.exec(r3);	 Catch:{ Exception -> 0x0053, all -> 0x006b }
        r1 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x008b, all -> 0x0083 }
        r4 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x008b, all -> 0x0083 }
        r5 = r3.getInputStream();	 Catch:{ Exception -> 0x008b, all -> 0x0083 }
        r6 = "GBK";
        r4.<init>(r5, r6);	 Catch:{ Exception -> 0x008b, all -> 0x0083 }
        r1.<init>(r4);	 Catch:{ Exception -> 0x008b, all -> 0x0083 }
        r3.waitFor();	 Catch:{ Exception -> 0x008e, all -> 0x0086 }
        r2 = r1.readLine();	 Catch:{ Exception -> 0x008e, all -> 0x0086 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x008e, all -> 0x0086 }
        r5 = "mrt :";
        r4.<init>(r5);	 Catch:{ Exception -> 0x008e, all -> 0x0086 }
        r4 = r4.append(r2);	 Catch:{ Exception -> 0x008e, all -> 0x0086 }
        r4 = r4.toString();	 Catch:{ Exception -> 0x008e, all -> 0x0086 }
        com.admaster.square.p008a.AdMasterDetect.m262a(r4);	 Catch:{ Exception -> 0x008e, all -> 0x0086 }
        if (r2 == 0) goto L_0x0048;
    L_0x0038:
        r4 = "sdk";
        r2 = r2.equals(r4);	 Catch:{ Exception -> 0x008e, all -> 0x0086 }
        if (r2 == 0) goto L_0x0048;
    L_0x0040:
        r0 = "1";
        r2 = r7.f148d;	 Catch:{ Exception -> 0x008e, all -> 0x0086 }
        r2 = r2 + 1;
        r7.f148d = r2;	 Catch:{ Exception -> 0x008e, all -> 0x0086 }
    L_0x0048:
        if (r3 == 0) goto L_0x004d;
    L_0x004a:
        r3.destroy();
    L_0x004d:
        if (r1 == 0) goto L_0x0052;
    L_0x004f:
        r1.close();	 Catch:{ Exception -> 0x007e }
    L_0x0052:
        return r0;
    L_0x0053:
        r0 = move-exception;
        r1 = r0;
        r3 = r2;
    L_0x0056:
        r0 = "E";
        r1.printStackTrace();	 Catch:{ all -> 0x0088 }
        if (r3 == 0) goto L_0x0060;
    L_0x005d:
        r3.destroy();
    L_0x0060:
        if (r2 == 0) goto L_0x0052;
    L_0x0062:
        r2.close();	 Catch:{ Exception -> 0x0066 }
        goto L_0x0052;
    L_0x0066:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0052;
    L_0x006b:
        r0 = move-exception;
        r1 = r2;
        r3 = r2;
    L_0x006e:
        if (r3 == 0) goto L_0x0073;
    L_0x0070:
        r3.destroy();
    L_0x0073:
        if (r1 == 0) goto L_0x0078;
    L_0x0075:
        r1.close();	 Catch:{ Exception -> 0x0079 }
    L_0x0078:
        throw r0;
    L_0x0079:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0078;
    L_0x007e:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0052;
    L_0x0083:
        r0 = move-exception;
        r1 = r2;
        goto L_0x006e;
    L_0x0086:
        r0 = move-exception;
        goto L_0x006e;
    L_0x0088:
        r0 = move-exception;
        r1 = r2;
        goto L_0x006e;
    L_0x008b:
        r0 = move-exception;
        r1 = r0;
        goto L_0x0056;
    L_0x008e:
        r0 = move-exception;
        r2 = r1;
        r1 = r0;
        goto L_0x0056;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.admaster.square.p008a.AdMasterDetect.mo7727b():java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0079 A:{SYNTHETIC, Splitter:B:35:0x0079} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0066 A:{SYNTHETIC, Splitter:B:26:0x0066} */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0079 A:{SYNTHETIC, Splitter:B:35:0x0079} */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0079 A:{SYNTHETIC, Splitter:B:35:0x0079} */
    /* renamed from: c */
    public java.lang.String mo7728c() {
        /*
        r7 = this;
        r2 = 0;
        r0 = "0";
        r1 = java.lang.Runtime.getRuntime();	 Catch:{ Exception -> 0x0057, all -> 0x006f }
        r3 = "getprop ro.kernel.qemu";
        r3 = r1.exec(r3);	 Catch:{ Exception -> 0x0057, all -> 0x006f }
        r1 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x008f, all -> 0x0087 }
        r4 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x008f, all -> 0x0087 }
        r5 = r3.getInputStream();	 Catch:{ Exception -> 0x008f, all -> 0x0087 }
        r6 = "GBK";
        r4.<init>(r5, r6);	 Catch:{ Exception -> 0x008f, all -> 0x0087 }
        r1.<init>(r4);	 Catch:{ Exception -> 0x008f, all -> 0x0087 }
        r2 = r1.readLine();	 Catch:{ Exception -> 0x0092, all -> 0x008a }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0092, all -> 0x008a }
        r5 = "qrt:";
        r4.<init>(r5);	 Catch:{ Exception -> 0x0092, all -> 0x008a }
        r4 = r4.append(r2);	 Catch:{ Exception -> 0x0092, all -> 0x008a }
        r4 = r4.toString();	 Catch:{ Exception -> 0x0092, all -> 0x008a }
        com.admaster.square.p008a.AdMasterDetect.m262a(r4);	 Catch:{ Exception -> 0x0092, all -> 0x008a }
        r4 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Exception -> 0x0092, all -> 0x008a }
        if (r4 != 0) goto L_0x003a;
    L_0x0039:
        r0 = r2;
    L_0x003a:
        if (r2 == 0) goto L_0x004c;
    L_0x003c:
        r4 = "1";
        r2 = r2.equals(r4);	 Catch:{ Exception -> 0x0092, all -> 0x008a }
        if (r2 == 0) goto L_0x004c;
    L_0x0044:
        r0 = "1";
        r2 = r7.f148d;	 Catch:{ Exception -> 0x0092, all -> 0x008a }
        r2 = r2 + 1;
        r7.f148d = r2;	 Catch:{ Exception -> 0x0092, all -> 0x008a }
    L_0x004c:
        if (r3 == 0) goto L_0x0051;
    L_0x004e:
        r3.destroy();
    L_0x0051:
        if (r1 == 0) goto L_0x0056;
    L_0x0053:
        r1.close();	 Catch:{ Exception -> 0x0082 }
    L_0x0056:
        return r0;
    L_0x0057:
        r0 = move-exception;
        r1 = r0;
        r3 = r2;
    L_0x005a:
        r0 = "E";
        r1.printStackTrace();	 Catch:{ all -> 0x008c }
        if (r3 == 0) goto L_0x0064;
    L_0x0061:
        r3.destroy();
    L_0x0064:
        if (r2 == 0) goto L_0x0056;
    L_0x0066:
        r2.close();	 Catch:{ Exception -> 0x006a }
        goto L_0x0056;
    L_0x006a:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0056;
    L_0x006f:
        r0 = move-exception;
        r1 = r2;
        r3 = r2;
    L_0x0072:
        if (r3 == 0) goto L_0x0077;
    L_0x0074:
        r3.destroy();
    L_0x0077:
        if (r1 == 0) goto L_0x007c;
    L_0x0079:
        r1.close();	 Catch:{ Exception -> 0x007d }
    L_0x007c:
        throw r0;
    L_0x007d:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x007c;
    L_0x0082:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0056;
    L_0x0087:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0072;
    L_0x008a:
        r0 = move-exception;
        goto L_0x0072;
    L_0x008c:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0072;
    L_0x008f:
        r0 = move-exception;
        r1 = r0;
        goto L_0x005a;
    L_0x0092:
        r0 = move-exception;
        r2 = r1;
        r1 = r0;
        goto L_0x005a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.admaster.square.p008a.AdMasterDetect.mo7728c():java.lang.String");
    }

    /* renamed from: d */
    public String mo7729d() {
        try {
            String str = Build.BOARD;
            String str2 = Build.BRAND;
            String str3 = Build.DEVICE;
            String str4 = Build.HARDWARE;
            String str5 = Build.MODEL;
            String str6 = Build.PRODUCT;
            if (str.equals("unknown")) {
                this.f148d++;
                return "1";
            } else if (str.equals("goldfish") || str4.equals("goldfish")) {
                this.f148d++;
                return "1";
            } else if (str2.equals("generic") || str3.equals("generic")) {
                this.f148d++;
                return "1";
            } else {
                if (str5.equals("sdk") || str6.equals("sdk")) {
                    this.f148d++;
                    return "1";
                }
                return "0";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint({"DefaultLocale"})
    /* renamed from: e */
    public String mo7730e() {
        try {
            String networkOperatorName = ((TelephonyManager) this.f147c.getSystemService("phone")).getNetworkOperatorName();
            AdMasterDetect.m262a("non:" + networkOperatorName);
            if (networkOperatorName != null && networkOperatorName.toLowerCase().equals("android")) {
                this.f148d++;
                return "1";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    /* renamed from: f */
    public String mo7731f() {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) this.f147c.getSystemService("phone");
            String line1Number = telephonyManager.getLine1Number();
            if (line1Number != null) {
                for (String equalsIgnoreCase : f142e) {
                    if (equalsIgnoreCase.equalsIgnoreCase(line1Number)) {
                        this.f148d++;
                        return "1";
                    }
                }
            }
            line1Number = telephonyManager.getDeviceId();
            if (line1Number != null) {
                for (String equalsIgnoreCase2 : f143f) {
                    if (equalsIgnoreCase2.equalsIgnoreCase(line1Number)) {
                        this.f148d++;
                        return "1";
                    }
                }
            }
            String subscriberId = telephonyManager.getSubscriberId();
            if (subscriberId != null) {
                for (String equalsIgnoreCase3 : f144g) {
                    if (equalsIgnoreCase3.equalsIgnoreCase(subscriberId)) {
                        this.f148d++;
                        return "1";
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    /* renamed from: g */
    public String mo7732g() {
        try {
            for (String file : f145h) {
                File file2 = new File(file);
                if (file2.exists()) {
                    AdMasterDetect.m262a("dpf:" + file2.getName());
                    this.f148d++;
                    return "1";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    /* renamed from: h */
    public String mo7733h() {
        try {
            File file = new File("/proc/tty/drivers");
            if (file.exists() && file.canRead()) {
                byte[] bArr = new byte[((int) file.length())];
                FileInputStream fileInputStream = new FileInputStream(file);
                fileInputStream.read(bArr);
                fileInputStream.close();
                String str = new String(bArr);
                for (String str2 : f146i) {
                    if (str.indexOf(str2) != -1) {
                        AdMasterDetect.m262a("qd:" + str2);
                        this.f148d++;
                        return "1";
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    /* renamed from: i */
    public String mo7734i() {
        String str;
        Exception e;
        String str2 = "0";
        try {
            for (PackageInfo packageInfo : this.f147c.getPackageManager().getInstalledPackages(0)) {
                str = packageInfo.packageName;
                if (str != null && str.startsWith("de.robv.android.xposed.installer")) {
                    str2 = "1";
                    this.f148d++;
                    str = str2;
                    break;
                }
            }
            str = str2;
            try {
                AdMasterDetect.m262a("xp:" + str);
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return str;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            str = str2;
            e = exception;
            e.printStackTrace();
            return str;
        }
        return str;
    }

    @SuppressLint({"NewApi"})
    /* renamed from: j */
    public String mo7735j() {
        try {
            Vibrator vibrator = (Vibrator) this.f147c.getSystemService("vibrator");
            if (VERSION.SDK_INT < 11) {
                return "0";
            }
            boolean hasVibrator = vibrator.hasVibrator();
            AdMasterDetect.m262a("vib:" + hasVibrator);
            if (hasVibrator) {
                return "0";
            }
            this.f148d++;
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    @SuppressLint({"DefaultLocale"})
    /* renamed from: k */
    public String mo7736k() {
        try {
            Object obj = null;
            for (Sensor sensor : ((SensorManager) this.f147c.getSystemService("sensor")).getSensorList(-1)) {
                String name = sensor.getName();
                int type = sensor.getType();
                String vendor = sensor.getVendor();
                if (name == null || !name.toLowerCase().equals("goldfish")) {
                    if (vendor != null) {
                        if (vendor.toLowerCase().equals("goldfish")) {
                            this.f148d++;
                            return "1";
                        }
                    }
                    if (type == 5) {
                        obj = 1;
                    }
                } else {
                    this.f148d++;
                    return "1";
                }
            }
            if (obj == null) {
                this.f148d++;
                return "1";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }
}
