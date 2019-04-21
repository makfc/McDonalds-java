package com.threatmetrix.TrustDefender;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.SparseArray;
import com.newrelic.agent.android.api.common.CarrierType;
import com.threatmetrix.TrustDefender.C4532g.C4523g;
import com.threatmetrix.TrustDefender.C4532g.C4525i;
import com.threatmetrix.TrustDefender.C4532g.C4530n;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.threatmetrix.TrustDefender.x */
class C4551x {
    /* renamed from: a */
    private static final String f7847a = C4549w.m8585a(C4551x.class);
    /* renamed from: f */
    private static SparseArray<String> f7848f = new SparseArray(30);
    /* renamed from: b */
    private String f7849b = null;
    /* renamed from: c */
    private String f7850c = null;
    /* renamed from: d */
    private String f7851d = null;
    /* renamed from: e */
    private String f7852e = null;

    /* renamed from: com.threatmetrix.TrustDefender.x$a */
    enum C4550a {
        BLUETOOTH("bluetooth tethering"),
        CELLULAR(CarrierType.CELLULAR),
        MOBILE("mobile"),
        WIFI("wifi"),
        ETHERNET(CarrierType.ETHERNET);
        
        /* renamed from: f */
        String f7846f;

        private C4550a(String value) {
            this.f7846f = value;
        }

        /* renamed from: a */
        public final String mo34262a() {
            return this.f7846f;
        }
    }

    static {
        List<String> currentTypes = new ArrayList();
        Class connectivityManager = C4485at.m8327b("android.net.ConnectivityManager");
        List<String> fieldNames = C4485at.m8326a(connectivityManager);
        if (fieldNames != null) {
            for (String fieldName : fieldNames) {
                if (C4491ai.m8349f(fieldName) && fieldName.startsWith("TYPE_")) {
                    currentTypes.add(fieldName);
                }
            }
            for (String type : currentTypes) {
                Object value = C4485at.m8319a(connectivityManager, type, null);
                if (value != null) {
                    Object obj;
                    SparseArray sparseArray = f7848f;
                    int intValue = ((Integer) value).intValue();
                    if (C4491ai.m8348e(type)) {
                        obj = null;
                    } else {
                        String trim = type.trim();
                        if (C4491ai.m8348e(trim)) {
                            obj = null;
                        } else {
                            trim = trim.toLowerCase();
                            if (trim.startsWith("type")) {
                                trim = trim.replaceFirst("type", "");
                            }
                            obj = trim.replaceAll("_", " ").trim();
                        }
                    }
                    sparseArray.put(intValue, obj);
                }
            }
        }
    }

    public C4551x(Context context) {
        String b;
        m8598a(NativeGatherer.m8207a().mo34064i());
        if (this.f7850c == null || this.f7849b == null || this.f7852e == null) {
            m8598a(m8601b(context));
            if (this.f7850c == null || this.f7849b == null || this.f7852e == null) {
                String[] strArr;
                Object telephonyService;
                int dataState;
                if (C4523g.m8500c()) {
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("android.net.wifi.STATE_CHANGE");
                    Intent registerReceiver = context.registerReceiver(null, intentFilter);
                    if (registerReceiver != null) {
                        String[] strArr2 = new String[4];
                        WifiInfo wifiInfo = (WifiInfo) registerReceiver.getParcelableExtra("wifiInfo");
                        String a;
                        if (wifiInfo != null) {
                            String b2 = C4551x.m8600b(wifiInfo.getBSSID());
                            a = C4551x.m8597a(wifiInfo.getSSID());
                            int rssi = wifiInfo.getRssi();
                            if (b2 != null) {
                                strArr2[0] = b2;
                            }
                            if (a != null) {
                                strArr2[1] = a;
                            }
                            strArr2[2] = String.valueOf(rssi);
                            strArr2[3] = C4550a.WIFI.mo34262a();
                            if (!(strArr2[0] == null || strArr2[1] == null || strArr2[2] == null)) {
                                strArr = strArr2;
                                m8598a(strArr);
                                if (this.f7849b == null || this.f7850c == null || this.f7852e == null) {
                                    m8598a(m8599a(context));
                                }
                                if (this.f7851d == null && C4530n.m8528k()) {
                                    telephonyService = context.getSystemService("phone");
                                    if (telephonyService != null && (telephonyService instanceof TelephonyManager)) {
                                        dataState = ((TelephonyManager) telephonyService).getDataState();
                                        if (dataState == 2 || dataState == 1 || dataState == 3) {
                                            this.f7851d = C4550a.CELLULAR.mo34262a();
                                        }
                                    }
                                    this.f7850c = null;
                                    this.f7849b = null;
                                    this.f7852e = null;
                                }
                            }
                        } else {
                            Bundle extras = registerReceiver.getExtras();
                            NetworkInfo networkInfo = (NetworkInfo) extras.get("networkInfo");
                            if (networkInfo != null && networkInfo.getState() == State.CONNECTED) {
                                a = C4551x.m8597a(networkInfo.getExtraInfo());
                                b = C4551x.m8600b((String) extras.get("bssid"));
                                if (b != null) {
                                    strArr2[0] = b;
                                }
                                if (a != null) {
                                    strArr2[1] = a;
                                }
                                strArr2[3] = C4550a.WIFI.mo34262a();
                            }
                        }
                        strArr = strArr2;
                        m8598a(strArr);
                        m8598a(m8599a(context));
                        telephonyService = context.getSystemService("phone");
                        dataState = ((TelephonyManager) telephonyService).getDataState();
                        this.f7851d = C4550a.CELLULAR.mo34262a();
                        this.f7850c = null;
                        this.f7849b = null;
                        this.f7852e = null;
                    }
                }
                strArr = null;
                m8598a(strArr);
                m8598a(m8599a(context));
                try {
                    telephonyService = context.getSystemService("phone");
                    dataState = ((TelephonyManager) telephonyService).getDataState();
                    this.f7851d = C4550a.CELLULAR.mo34262a();
                    this.f7850c = null;
                    this.f7849b = null;
                    this.f7852e = null;
                } catch (SecurityException e) {
                    b = f7847a;
                    this.f7850c = null;
                    this.f7849b = null;
                    this.f7852e = null;
                } catch (Exception e2) {
                    C4549w.m8594c(f7847a, e2.getMessage());
                    this.f7850c = null;
                    this.f7849b = null;
                    this.f7852e = null;
                } catch (Throwable th) {
                    this.f7850c = null;
                    this.f7849b = null;
                    this.f7852e = null;
                    throw th;
                }
            }
        }
        b = f7847a;
        new StringBuilder("Network Info (Final values) BSSID: ").append(this.f7850c).append(" SSID: ").append(this.f7849b).append(" RSSI: ").append(this.f7852e).append(" Type: ").append(this.f7851d);
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final String mo34263a() {
        return this.f7849b;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: b */
    public final String mo34264b() {
        return this.f7850c;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: c */
    public final String mo34265c() {
        return this.f7851d;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: d */
    public final String mo34266d() {
        return this.f7852e;
    }

    /* renamed from: a */
    private String[] m8599a(Context context) {
        if (!C4523g.m8499b()) {
            return null;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        Intent intent = context.registerReceiver(null, intentFilter);
        if (intent == null) {
            return null;
        }
        String[] networkInfo = new String[4];
        int typeCode = intent.getIntExtra("networkType", -99);
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return networkInfo;
        }
        NetworkInfo info = (NetworkInfo) extras.get("networkInfo");
        if (info != null) {
            if (info.getState() != State.CONNECTED) {
                return null;
            }
            if (typeCode == -99) {
                try {
                    typeCode = info.getType();
                } catch (SecurityException e) {
                    String str = f7847a;
                    return null;
                } catch (Exception e2) {
                    C4549w.m8594c(f7847a, e2.getMessage());
                    return null;
                }
            }
        }
        String typeName = (String) f7848f.get(typeCode);
        if (typeName == null) {
            C4549w.m8593b(f7847a, "Unexpected connection type code {}", String.valueOf(typeCode));
            return networkInfo;
        } else if (typeCode == 0 || typeName.contains(C4550a.MOBILE.mo34262a())) {
            networkInfo[3] = C4550a.CELLULAR.mo34262a();
            return networkInfo;
        } else if (typeCode == 1 || typeName.contains(C4550a.WIFI.mo34262a())) {
            networkInfo[3] = C4550a.WIFI.mo34262a();
            String ssid = info == null ? null : C4551x.m8597a(info.getExtraInfo());
            String bssid = C4551x.m8600b((String) extras.get("bssid"));
            if (bssid != null) {
                networkInfo[0] = bssid;
            }
            if (ssid == null) {
                return networkInfo;
            }
            networkInfo[1] = ssid;
            return networkInfo;
        } else if (typeCode == 7 || typeName.contains(C4550a.BLUETOOTH.mo34262a())) {
            networkInfo[3] = C4550a.BLUETOOTH.mo34262a();
            return networkInfo;
        } else if (typeCode == 9 || typeName.contains(C4550a.ETHERNET.mo34262a())) {
            networkInfo[3] = C4550a.ETHERNET.mo34262a();
            return networkInfo;
        } else {
            C4549w.m8593b(f7847a, "Unexpected connection type {}", typeName);
            networkInfo[3] = typeName;
            return networkInfo;
        }
    }

    /* renamed from: b */
    private String[] m8601b(Context context) {
        if (!C4523g.m8501d()) {
            return null;
        }
        if (!new C4525i(context).mo34228a("android.permission.ACCESS_WIFI_STATE", context.getPackageName())) {
            return null;
        }
        try {
            Object wifiService = context.getSystemService("wifi");
            if (wifiService == null || !(wifiService instanceof WifiManager)) {
                return null;
            }
            WifiInfo wifiInfo = ((WifiManager) wifiService).getConnectionInfo();
            if (C4551x.m8600b(wifiInfo.getBSSID()) == null) {
                return null;
            }
            if (C4551x.m8597a(wifiInfo.getSSID()) == null) {
                return null;
            }
            int rssi = wifiInfo.getRssi();
            return new String[]{bssid, ssid, String.valueOf(rssi), C4550a.WIFI.mo34262a()};
        } catch (SecurityException e) {
            String str = f7847a;
            return null;
        } catch (Exception e2) {
            C4549w.m8594c(f7847a, e2.getMessage());
            return null;
        }
    }

    /* renamed from: a */
    private static String m8597a(String ssid) {
        if (ssid == null || ssid.contains("unknown ssid") || ssid.length() <= 0) {
            return null;
        }
        if (ssid.charAt(0) == '\"') {
            ssid = ssid.substring(1);
        }
        if (ssid.length() > 0 && ssid.charAt(ssid.length() - 1) == '\"') {
            ssid = ssid.substring(0, ssid.length() - 1);
        }
        if (ssid.isEmpty()) {
            return null;
        }
        return C4491ai.m8351h(ssid);
    }

    /* renamed from: b */
    private static String m8600b(String bssid) {
        if (bssid == null || bssid.length() < 17 || "00:00:00:00:00:00".equals(bssid)) {
            return null;
        }
        return bssid.toUpperCase();
    }

    /* renamed from: a */
    private void m8598a(String[] networkInfo) {
        if (networkInfo != null && networkInfo.length >= 4) {
            if (this.f7850c == null && networkInfo[0] != null) {
                this.f7850c = networkInfo[0];
            }
            if (this.f7849b == null && networkInfo[1] != null) {
                this.f7849b = networkInfo[1];
            }
            if (this.f7852e == null && networkInfo[2] != null) {
                this.f7852e = networkInfo[2];
            }
            if (this.f7851d == null && networkInfo[3] != null) {
                this.f7851d = networkInfo[3];
            }
        }
    }

    public final boolean equals(Object o) {
        if (o == null || !(o instanceof C4551x)) {
            return false;
        }
        if (o == this) {
            return true;
        }
        boolean isEqual = true;
        C4551x other = (C4551x) o;
        if (!(this.f7849b == null || this.f7849b.equals(other.f7849b)) || (this.f7849b == null && other.f7849b != null)) {
            isEqual = false;
        }
        if (!(this.f7852e == null || this.f7852e.equals(other.f7852e)) || (this.f7852e == null && other.f7852e != null)) {
            isEqual = false;
        }
        if (!(this.f7851d == null || this.f7851d.equals(other.f7851d)) || (this.f7851d == null && other.f7851d != null)) {
            isEqual = false;
        }
        if ((this.f7850c == null || this.f7850c.equals(other.f7850c)) && (this.f7850c != null || other.f7850c == null)) {
            return isEqual;
        }
        return false;
    }
}
