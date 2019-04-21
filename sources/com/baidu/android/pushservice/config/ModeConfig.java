package com.baidu.android.pushservice.config;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.baidu.android.pushservice.C1328a;
import com.baidu.android.pushservice.C1457i;
import com.baidu.android.pushservice.PushSettings;
import com.baidu.android.pushservice.p026j.C1281c;
import com.baidu.android.pushservice.p026j.C1462d;
import com.baidu.android.pushservice.p033e.C1370b;
import com.baidu.android.pushservice.p034f.C1402a;
import com.baidu.android.pushservice.p034f.C1403b;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p037i.p038a.C1432b;
import com.baidu.android.pushservice.p039k.C1471e;
import com.baidu.android.pushservice.p039k.C1474h;
import com.baidu.android.pushservice.util.C1534b;
import com.baidu.android.pushservice.util.C1550n;
import com.baidu.android.pushservice.util.C1578v;
import com.mcdonalds.sdk.connectors.autonavi.AutoNavi.Parameters;
import com.mcdonalds.sdk.modules.notification.PushConstants;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.xiaomi.mipush.sdk.PushMessageReceiver;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ModeConfig extends C1347b {
    public static int MODE_C = 2;
    public static int MODE_C_C = 4;
    public static int MODE_C_H = 3;
    public static int MODE_I = 1;
    public static int MODE_I_HW = 5;
    public static int MODE_I_XM = 6;
    public static int MODE_O = 0;
    private static final String TAG = ModeConfig.class.getSimpleName();
    private static boolean hasCallBack = false;
    private static ModeConfig mInstance;
    private static int updateConfigTime = 259200000;
    private int mConfigVersion;
    private int mCurrentMode = MODE_O;
    private int mHighestVersion = C1328a.m6003a();
    private String mHostPackage = null;
    public HashMap<String, C1350c> mManufacturers;
    private String updateConfigUrl = "https://api.tuisong.baidu.com/rest/3.0/clientfile/updatesdkconfig";

    /* renamed from: com.baidu.android.pushservice.config.ModeConfig$a */
    public interface C1346a {
        /* renamed from: a */
        void mo13627a();
    }

    protected ModeConfig(Context context) {
        super(context);
        reload();
    }

    private static String byte2HexFormatted(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte toHexString : bArr) {
            String toHexString2 = Integer.toHexString(toHexString);
            int length = toHexString2.length();
            if (length == 1) {
                toHexString2 = "0" + toHexString2;
            }
            if (length > 2) {
                toHexString2 = toHexString2.substring(length - 2, length);
            }
            stringBuffer.append(toHexString2.toUpperCase(Locale.ENGLISH));
        }
        return stringBuffer.toString();
    }

    public static ModeConfig getInstance(Context context) {
        if (mInstance != null) {
            return mInstance;
        }
        mInstance = new ModeConfig(context);
        return mInstance;
    }

    public static boolean isHuaweiProxyMode(Context context) {
        return getInstance(context).getCurrentMode() == MODE_I_HW && PushSettings.m5895n(context);
    }

    public static boolean isProxyMode(Context context) {
        return isHuaweiProxyMode(context) || isXiaomiProxyMode(context);
    }

    public static boolean isXiaomiProxyMode(Context context) {
        try {
            PushMessageReceiver.isMipushPatch();
            return getInstance(context).getCurrentMode() == MODE_I_XM && PushSettings.m5894m(context);
        } catch (Throwable th) {
            C1425a.m6444e(TAG, "not found com.xiaomi.mipush pkg, not xiaomiproxy mode!!!");
            return false;
        }
    }

    private boolean parseConfig() {
        try {
            String string;
            JSONObject init = JSONObjectInstrumentation.init(this.mConfigContent);
            this.mConfigVersion = init.getInt("version");
            this.mManufacturers = new HashMap();
            JSONArray jSONArray = init.getJSONArray("modeconfig");
            for (int i = 0; i < jSONArray.length(); i++) {
                int i2;
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                C1350c c1350c = new C1350c();
                c1350c.mo13639a(jSONObject.getString("manufacturer"));
                C1425a.m6442c(TAG, " manufacturer name " + c1350c.mo13641b());
                string = jSONObject.getString("mode");
                C1425a.m6442c(TAG, "mode " + string);
                if ("I".equalsIgnoreCase(string)) {
                    c1350c.mo13637a(MODE_I);
                } else if ("I_HW".equalsIgnoreCase(string)) {
                    c1350c.mo13637a(MODE_I_HW);
                } else if ("I_XM".equalsIgnoreCase(string)) {
                    c1350c.mo13637a(MODE_I_XM);
                } else if ("C".equalsIgnoreCase(string)) {
                    c1350c.mo13637a(MODE_C);
                } else {
                    c1350c.mo13637a(MODE_O);
                }
                ArrayList arrayList = new ArrayList();
                if (jSONObject.has("osversion")) {
                    JSONArray jSONArray2 = jSONObject.getJSONArray("osversion");
                    for (i2 = 0; i2 < jSONArray2.length(); i2++) {
                        JSONObject jSONObject2 = jSONArray2.getJSONObject(i2);
                        C1351d c1351d = new C1351d();
                        c1351d.mo13652a(jSONObject2.getString(Parameters.API_KEY));
                        C1425a.m6442c(TAG, " key " + c1351d.mo13650a());
                        c1351d.mo13654b(jSONObject2.getString("value"));
                        C1425a.m6442c(TAG, " value " + c1351d.mo13653b());
                        String string2 = jSONObject2.getString("match");
                        if (string2.equalsIgnoreCase("above")) {
                            c1351d.mo13651a(0);
                        } else if (string2.equalsIgnoreCase("equal")) {
                            c1351d.mo13651a(1);
                        } else if (string2.equalsIgnoreCase("regular")) {
                            c1351d.mo13651a(2);
                        }
                        arrayList.add(c1351d);
                    }
                }
                ArrayList arrayList2 = new ArrayList();
                if (jSONObject.has("systemprop")) {
                    JSONArray jSONArray3 = jSONObject.getJSONArray("systemprop");
                    for (i2 = 0; i2 < jSONArray3.length(); i2++) {
                        JSONObject jSONObject3 = jSONArray3.getJSONObject(i2);
                        C1352e c1352e = new C1352e();
                        c1352e.mo13658a(jSONObject3.getString(Parameters.API_KEY));
                        c1352e.mo13660b(jSONObject3.getString("value"));
                        String string3 = jSONObject3.getString("match");
                        if (string3.equalsIgnoreCase("above")) {
                            c1352e.mo13657a(0);
                        } else if (string3.equalsIgnoreCase("equal")) {
                            c1352e.mo13657a(1);
                        }
                        c1352e.mo13662c(jSONObject3.getString("regular"));
                        arrayList2.add(c1352e);
                    }
                }
                if (jSONObject.has("apkname")) {
                    c1350c.mo13642b(jSONObject.getString("apkname"));
                    C1425a.m6442c(TAG, " pkgname " + c1350c.mo13648f());
                }
                if (jSONObject.has("apksign")) {
                    c1350c.mo13645c(jSONObject.getString("apksign"));
                    C1425a.m6442c(TAG, "apkSign " + c1350c.mo13649g());
                }
                if (jSONObject.has("apkversion")) {
                    init = jSONObject.getJSONObject("apkversion");
                    c1350c.mo13638a(init.optInt(PushConstants.FROM_ID), init.optInt("to"));
                    C1425a.m6442c(TAG, "from " + c1350c.mo13636a().f4758a + " to " + c1350c.mo13636a().f4759b);
                }
                if (arrayList.size() > 0) {
                    c1350c.mo13640a(arrayList);
                }
                if (arrayList2.size() > 0) {
                    c1350c.mo13643b(arrayList2);
                }
                this.mManufacturers.put(c1350c.mo13641b(), c1350c);
            }
            string = Build.MANUFACTURER.toUpperCase();
            if (!string.equalsIgnoreCase("unknown") || this.mManufacturers == null) {
                return caculateCurrentConfig(string);
            }
            for (Entry key : this.mManufacturers.entrySet()) {
                if (caculateCurrentConfig((String) key.getKey())) {
                    return true;
                }
            }
            return false;
        } catch (JSONException e) {
            C1425a.m6440a(TAG, e);
        }
    }

    private String requestConfig(HashMap<String, String> hashMap) {
        int i = 2;
        String b = C1457i.m6625b();
        if ((C1578v.m7112d() && PushSettings.m5894m(this.mContext)) || (C1578v.m7115e() && PushSettings.m5895n(this.mContext))) {
            this.updateConfigUrl = b + "/rest/3.0/clientfile/updateconf";
        } else {
            this.updateConfigUrl = b + "/rest/3.0/clientfile/updatesdkconfig";
        }
        C1425a.m6442c(TAG, this.updateConfigUrl);
        do {
            C1402a a = C1403b.m6260a(this.updateConfigUrl, "POST", hashMap, "BCCS_SDK/3.0");
            if (a != null) {
                int b2 = a.mo13745b();
                b = C1432b.m6481a(a.mo13742a());
                C1425a.m6442c(TAG, "update config request response, code=" + b2 + ", result=" + b);
                if (b2 == 200) {
                    return b;
                }
            }
            i--;
        } while (i > 0);
        return null;
    }

    private boolean setMode(C1350c c1350c) {
        byte[] bArr = null;
        if (c1350c.mo13644c() == MODE_I_HW) {
            this.mCurrentMode = MODE_I_HW;
            return true;
        } else if (c1350c.mo13644c() == MODE_I_XM) {
            this.mCurrentMode = MODE_I_XM;
            return true;
        } else {
            if (c1350c.mo13644c() == MODE_C && !TextUtils.isEmpty(c1350c.mo13648f())) {
                try {
                    PackageInfo packageInfo = this.mContext.getPackageManager().getPackageInfo(c1350c.mo13648f(), 16448);
                    if (packageInfo != null) {
                        boolean z;
                        int i = packageInfo.versionCode;
                        C1425a.m6442c(TAG, "version code = " + i);
                        if (c1350c.mo13636a() == null) {
                            z = false;
                        } else if (c1350c.mo13636a().f4759b == -1) {
                            C1425a.m6442c(TAG, "to = -1");
                            z = i >= c1350c.mo13636a().f4758a;
                        } else {
                            C1425a.m6442c(TAG, PushConstants.FROM_ID);
                            z = (i >= c1350c.mo13636a().f4758a ? 1 : 0) & (i <= c1350c.mo13636a().f4759b ? 1 : 0);
                        }
                        C1425a.m6442c(TAG, "version ret " + z);
                        if (z) {
                            CertificateFactory instance;
                            X509Certificate x509Certificate;
                            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(packageInfo.signatures[0].toByteArray());
                            try {
                                instance = CertificateFactory.getInstance("X509");
                            } catch (CertificateException e) {
                                C1425a.m6440a(TAG, e);
                                instance = bArr;
                            }
                            try {
                                x509Certificate = (X509Certificate) instance.generateCertificate(byteArrayInputStream);
                            } catch (CertificateException e2) {
                                C1425a.m6440a(TAG, e2);
                                x509Certificate = bArr;
                            }
                            String str = "";
                            try {
                                bArr = C1474h.m6720a(x509Certificate.getEncoded());
                            } catch (Exception e22) {
                                C1425a.m6440a(TAG, e22);
                            }
                            String byte2HexFormatted = byte2HexFormatted(bArr);
                            C1425a.m6442c(TAG, "hexString " + byte2HexFormatted);
                            C1425a.m6442c(TAG, "apkSignture" + c1350c.mo13649g());
                            if (byte2HexFormatted.equalsIgnoreCase(c1350c.mo13649g())) {
                                this.mHostPackage = c1350c.mo13648f();
                                this.mHighestVersion = C1578v.m7127k(this.mContext, this.mHostPackage);
                                if (this.mContext.getPackageName().equalsIgnoreCase(c1350c.mo13648f())) {
                                    this.mCurrentMode = MODE_C_H;
                                    C1425a.m6442c(TAG, "return true c_h");
                                    return true;
                                }
                                this.mCurrentMode = MODE_C_C;
                                C1425a.m6442c(TAG, "return true c_c");
                                return true;
                            }
                        }
                    }
                } catch (NameNotFoundException e3) {
                    return false;
                }
            }
            return false;
        }
    }

    public boolean caculateCurrentConfig(String str) {
        int i;
        C1425a.m6442c(TAG, "manufacturer " + str);
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String toUpperCase = str.toUpperCase();
        if (this.mManufacturers != null && this.mManufacturers.containsKey(toUpperCase)) {
            int i2;
            C1425a.m6442c(TAG, "contain key");
            C1350c c1350c = (C1350c) this.mManufacturers.get(toUpperCase);
            if (c1350c != null && c1350c.mo13646d() != null && c1350c.mo13646d().size() > 0) {
                i2 = 0;
                while (true) {
                    int i3 = i2;
                    if (i3 >= c1350c.mo13646d().size()) {
                        break;
                    }
                    C1351d c1351d = (C1351d) c1350c.mo13646d().get(i3);
                    try {
                        String a = C1534b.m6898a().mo14059a(c1351d.mo13650a(), "");
                        String b = c1351d.mo13653b();
                        if (c1351d.mo13655c() == 0) {
                            if (!(TextUtils.isEmpty(b) || TextUtils.isEmpty(a))) {
                                double d = 0.0d;
                                double d2 = 0.0d;
                                try {
                                    d = Double.parseDouble(b);
                                    d2 = d;
                                    d = Double.parseDouble(a);
                                } catch (NumberFormatException e) {
                                    C1425a.m6439a(TAG, "number format exception  confv " + d + " val " + d2, e);
                                    double d3 = d2;
                                    d2 = d;
                                    d = d3;
                                }
                                if (d < d2) {
                                    continue;
                                } else if (setMode(c1350c)) {
                                    return true;
                                }
                            }
                            i2 = i3 + 1;
                        } else {
                            if (c1351d.mo13655c() == 1) {
                                if (!(TextUtils.isEmpty(b) || TextUtils.isEmpty(a))) {
                                    i = 0;
                                    try {
                                        i = Integer.parseInt(b);
                                        i2 = Integer.parseInt(a);
                                    } catch (NumberFormatException e2) {
                                        C1425a.m6439a(TAG, "number format exception  confv " + i + " val " + 0, e2);
                                        i2 = 0;
                                    }
                                    if (i2 != i) {
                                        continue;
                                    } else if (setMode(c1350c)) {
                                        return true;
                                    }
                                }
                            } else if (c1351d.mo13655c() != 2) {
                                continue;
                            } else if (Pattern.matches(b, a)) {
                                C1425a.m6444e(TAG, " match ");
                                if (setMode(c1350c)) {
                                    C1425a.m6442c(TAG, "set mode return");
                                    return true;
                                }
                            } else {
                                C1425a.m6442c(TAG, " not match ");
                            }
                            i2 = i3 + 1;
                        }
                    } catch (Exception e3) {
                        C1425a.m6440a(TAG, e3);
                    }
                }
            }
            if (c1350c.mo13647e() != null && c1350c.mo13647e().size() > 0) {
                C1425a.m6442c(TAG, "getSystemProps");
                i2 = 0;
                while (true) {
                    i = i2;
                    if (i >= c1350c.mo13647e().size()) {
                        break;
                    }
                    C1352e c1352e = (C1352e) c1350c.mo13647e().get(i);
                    try {
                        String str2 = "";
                        Class cls = Class.forName("android.os.SystemProperties");
                        CharSequence charSequence = (String) cls.getDeclaredMethod("get", new Class[]{String.class}).invoke(cls, new Object[]{c1352e.mo13656a()});
                        C1425a.m6442c(TAG, " buildVersion " + charSequence);
                        if (toUpperCase.equalsIgnoreCase("HUAWEI") && !charSequence.matches("\\d+\\.\\d+$") && VERSION.SDK_INT >= 21 && PushSettings.m5895n(this.mContext)) {
                            charSequence = "3.1";
                        }
                        Matcher matcher = Pattern.compile(c1352e.mo13661c()).matcher(charSequence);
                        if (matcher.find()) {
                            Double valueOf = Double.valueOf(matcher.group());
                            Double valueOf2 = Double.valueOf(c1352e.mo13659b());
                            if (c1352e.mo13663d() == 0) {
                                if (valueOf.doubleValue() >= valueOf2.doubleValue() && setMode(c1350c)) {
                                    return true;
                                }
                            } else if (c1352e.mo13663d() == 1 && valueOf == valueOf2 && setMode(c1350c)) {
                                return true;
                            }
                        }
                        continue;
                    } catch (Exception e32) {
                        C1425a.m6440a(TAG, e32);
                    }
                    i2 = i + 1;
                }
            }
        }
        return false;
    }

    public int getCurrentMode() {
        return this.mCurrentMode;
    }

    public int getHighestVersion() {
        return this.mHighestVersion;
    }

    public String getHostPackageName() {
        return this.mHostPackage;
    }

    public void reload() {
        if (loadConfig()) {
            C1425a.m6442c(TAG, "loadConfig");
            parseConfig();
        }
    }

    public synchronized void updateConfig(C1346a c1346a) {
        String D;
        if (C1578v.m7112d() && PushSettings.m5894m(this.mContext) && !isXiaomiProxyMode(this.mContext)) {
            try {
                D = C1578v.m7055D(this.mContext);
                if (!TextUtils.isEmpty(D) && ((double) Float.parseFloat(D)) >= 4.0d) {
                    updateConfigTime = 0;
                }
            } catch (Exception e) {
                C1425a.m6440a(TAG, e);
            }
        } else if (C1578v.m7115e() && PushSettings.m5895n(this.mContext) && !isHuaweiProxyMode(this.mContext)) {
            try {
                D = C1578v.m7055D(this.mContext);
                if (!TextUtils.isEmpty(D) && ((double) Float.parseFloat(D)) >= 3.1d) {
                    updateConfigTime = 0;
                }
            } catch (Exception e2) {
                C1425a.m6440a(TAG, e2);
            }
        } else {
            updateConfigTime = 259200000;
            if (c1346a != null) {
                hasCallBack = true;
                c1346a.mo13627a();
            }
        }
        long b = C1550n.m6961b(this.mContext, "last_update_config_time");
        final long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - b > ((long) updateConfigTime)) {
            final C1346a c1346a2 = c1346a;
            C1462d.m6637a().mo13938a(new C1281c("ModeConfig-updateConfig", (short) 100) {
                /* renamed from: a */
                public void mo13487a() {
                    try {
                        HashMap hashMap = new HashMap();
                        C1370b.m6206b(hashMap);
                        hashMap.put("version", ModeConfig.this.mConfigVersion + "");
                        hashMap.put("model", Build.MODEL);
                        hashMap.put("osSdkInt", VERSION.SDK_INT + "");
                        if ((C1578v.m7112d() && PushSettings.m5894m(ModeConfig.this.mContext)) || (C1578v.m7115e() && PushSettings.m5895n(ModeConfig.this.mContext))) {
                            hashMap.put("manufacture", Build.MANUFACTURER);
                            hashMap.put("sdk_version", C1328a.m6003a() + "");
                            hashMap.put("cuid", C1471e.m6687a(ModeConfig.this.mContext));
                            hashMap.put("channelid", PushSettings.m5874a(ModeConfig.this.mContext));
                            hashMap.put("package_name", ModeConfig.this.mContext.getPackageName());
                            hashMap.put("pkg_sign", C1578v.m7138p(ModeConfig.this.mContext, ModeConfig.this.mContext.getPackageName()));
                            hashMap.put("rom_version", C1578v.m7055D(ModeConfig.this.mContext));
                        } else {
                            hashMap.put("manufacturer", Build.MANUFACTURER);
                            hashMap.put("pushSdkInt", C1328a.m6003a() + "");
                            C1425a.m6442c(ModeConfig.TAG, "update config request send, params=" + C1370b.m6202a(hashMap));
                        }
                        C1425a.m6442c(ModeConfig.TAG, "update config request send, params=" + C1370b.m6202a(hashMap));
                        String access$200 = ModeConfig.this.requestConfig(hashMap);
                        if (!TextUtils.isEmpty(access$200)) {
                            JSONObject init = JSONObjectInstrumentation.init(access$200);
                            JSONObject jSONObject = (JSONObject) init.get("response_params");
                            C1425a.m6442c(ModeConfig.TAG, "new config content=" + (!(init instanceof JSONObject) ? init.toString() : JSONObjectInstrumentation.toString(init)));
                            if (jSONObject != null && jSONObject.getInt("status") == 1) {
                                access$200 = jSONObject.getString("sdkconfig");
                                if (!TextUtils.isEmpty(access$200)) {
                                    boolean writeConfig = ModeConfig.this.writeConfig(access$200);
                                    C1425a.m6442c(ModeConfig.TAG, "write config >> " + writeConfig);
                                    if (writeConfig) {
                                        ModeConfig.this.reload();
                                        C1550n.m6957a(ModeConfig.this.mContext, "last_update_config_time", currentTimeMillis);
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        C1425a.m6440a(ModeConfig.TAG, e);
                    }
                    if (c1346a2 != null && !ModeConfig.hasCallBack) {
                        c1346a2.mo13627a();
                    }
                }
            });
        } else if (!(c1346a == null || hasCallBack)) {
            c1346a.mo13627a();
        }
        return;
    }
}
