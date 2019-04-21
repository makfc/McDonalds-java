package com.aps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.support.p000v4.internal.view.SupportMenu;
import android.support.p000v4.media.TransportMediator;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import com.amap.api.location.LocationManagerProxy;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TreeMap;

/* renamed from: com.aps.am */
public final class C1225am {
    /* renamed from: D */
    private static int f4246D = 10000;
    /* renamed from: u */
    private static C1225am f4247u = null;
    /* renamed from: A */
    private Timer f4248A = null;
    /* renamed from: B */
    private Thread f4249B = null;
    /* renamed from: C */
    private Looper f4250C = null;
    /* renamed from: a */
    private Context f4251a = null;
    /* renamed from: b */
    private TelephonyManager f4252b = null;
    /* renamed from: c */
    private LocationManager f4253c = null;
    /* renamed from: d */
    private WifiManager f4254d = null;
    /* renamed from: e */
    private SensorManager f4255e = null;
    /* renamed from: f */
    private String f4256f = "";
    /* renamed from: g */
    private String f4257g = "";
    /* renamed from: h */
    private String f4258h = "";
    /* renamed from: i */
    private boolean f4259i = false;
    /* renamed from: j */
    private int f4260j = 0;
    /* renamed from: k */
    private boolean f4261k = false;
    /* renamed from: l */
    private long f4262l = -1;
    /* renamed from: m */
    private String f4263m = "";
    /* renamed from: n */
    private String f4264n = "";
    /* renamed from: o */
    private int f4265o = 0;
    /* renamed from: p */
    private int f4266p = 0;
    /* renamed from: q */
    private int f4267q = 0;
    /* renamed from: r */
    private String f4268r = "";
    /* renamed from: s */
    private long f4269s = 0;
    /* renamed from: t */
    private long f4270t = 0;
    /* renamed from: v */
    private C1227ao f4271v = null;
    /* renamed from: w */
    private C1228ap f4272w = null;
    /* renamed from: x */
    private CellLocation f4273x = null;
    /* renamed from: y */
    private C1229aq f4274y = null;
    /* renamed from: z */
    private List f4275z = new ArrayList();

    private C1225am(Context context) {
        if (context != null) {
            this.f4251a = context;
            this.f4256f = Build.MODEL;
            this.f4252b = (TelephonyManager) context.getSystemService("phone");
            this.f4253c = (LocationManager) context.getSystemService(LocationManagerProxy.KEY_LOCATION_CHANGED);
            this.f4254d = (WifiManager) context.getSystemService("wifi");
            this.f4255e = (SensorManager) context.getSystemService("sensor");
            if (this.f4252b != null && this.f4254d != null) {
                try {
                    this.f4257g = this.f4252b.getDeviceId();
                } catch (Exception e) {
                }
                this.f4258h = this.f4252b.getSubscriberId();
                if (this.f4254d.getConnectionInfo() != null) {
                    this.f4264n = this.f4254d.getConnectionInfo().getMacAddress();
                    if (this.f4264n != null && this.f4264n.length() > 0) {
                        this.f4264n = this.f4264n.replace(":", "");
                    }
                }
                String[] b = C1225am.m5381b(this.f4252b);
                this.f4265o = Integer.parseInt(b[0]);
                this.f4266p = Integer.parseInt(b[1]);
                this.f4267q = this.f4252b.getNetworkType();
                this.f4268r = context.getPackageName();
                this.f4259i = this.f4252b.getPhoneType() == 2;
            }
        }
    }

    /* renamed from: A */
    private void m5353A() {
        if (this.f4254d != null) {
            try {
                if (C1249bf.f4368a) {
                    this.f4254d.startScan();
                }
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: B */
    private CellLocation m5354B() {
        if (this.f4252b == null) {
            return null;
        }
        CellLocation b;
        try {
            b = C1225am.m5377b((List) C1222aj.m5335a(this.f4252b, "getAllCellInfo", new Object[0]));
        } catch (NoSuchMethodException e) {
            b = null;
        } catch (Exception e2) {
            b = null;
        }
        return b;
    }

    /* renamed from: a */
    private static int m5355a(CellLocation cellLocation, Context context) {
        if (System.getInt(context.getContentResolver(), "airplane_mode_on", 0) == 1 || cellLocation == null) {
            return 9;
        }
        if (cellLocation instanceof GsmCellLocation) {
            return 1;
        }
        try {
            Class.forName("android.telephony.cdma.CdmaCellLocation");
            return 2;
        } catch (Exception e) {
            return 9;
        }
    }

    /* renamed from: a */
    protected static C1225am m5360a(Context context) {
        if (f4247u == null && C1225am.m5385c(context)) {
            Object obj;
            LocationManager locationManager = (LocationManager) context.getSystemService(LocationManagerProxy.KEY_LOCATION_CHANGED);
            if (locationManager != null) {
                for (String str : locationManager.getAllProviders()) {
                    if (!str.equals("passive")) {
                        if (str.equals("gps")) {
                        }
                    }
                    obj = 1;
                }
            }
            obj = null;
            if (obj != null) {
                f4247u = new C1225am(context);
            }
        }
        return f4247u;
    }

    /* renamed from: a */
    private void m5366a(BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver != null && this.f4251a != null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
            this.f4251a.registerReceiver(broadcastReceiver, intentFilter);
        }
    }

    /* renamed from: a */
    private static void m5369a(List list) {
        if (list != null && list.size() > 0) {
            HashMap hashMap = new HashMap();
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= list.size()) {
                    break;
                }
                ScanResult scanResult = (ScanResult) list.get(i2);
                if (scanResult.SSID == null) {
                    scanResult.SSID = SafeJsonPrimitive.NULL_STRING;
                }
                hashMap.put(Integer.valueOf(scanResult.level), scanResult);
                i = i2 + 1;
            }
            TreeMap treeMap = new TreeMap(Collections.reverseOrder());
            treeMap.putAll(hashMap);
            list.clear();
            for (Integer num : treeMap.keySet()) {
                list.add(treeMap.get(num));
            }
            hashMap.clear();
            treeMap.clear();
        }
    }

    /* renamed from: a */
    private boolean m5370a(CellLocation cellLocation) {
        if (cellLocation == null) {
            return false;
        }
        switch (C1225am.m5355a(cellLocation, this.f4251a)) {
            case 1:
                GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                if (gsmCellLocation.getLac() == -1 || gsmCellLocation.getLac() == 0 || gsmCellLocation.getLac() > SupportMenu.USER_MASK || gsmCellLocation.getCid() == -1 || gsmCellLocation.getCid() == 0 || gsmCellLocation.getCid() == SupportMenu.USER_MASK || gsmCellLocation.getCid() >= 268435455) {
                    return false;
                }
            case 2:
                try {
                    if (C1222aj.m5340b(cellLocation, "getSystemId", new Object[0]) <= 0 || C1222aj.m5340b(cellLocation, "getNetworkId", new Object[0]) < 0 || C1222aj.m5340b(cellLocation, "getBaseStationId", new Object[0]) < 0) {
                        return false;
                    }
                } catch (Exception e) {
                    break;
                }
        }
        return true;
    }

    /* renamed from: a */
    private static boolean m5372a(Object obj) {
        try {
            Method declaredMethod = WifiManager.class.getDeclaredMethod("isScanAlwaysAvailable", null);
            if (declaredMethod != null) {
                return ((Boolean) declaredMethod.invoke(obj, null)).booleanValue();
            }
        } catch (Exception e) {
        }
        return false;
    }

    /* renamed from: b */
    private static int m5375b(Object obj) {
        try {
            Method declaredMethod = Sensor.class.getDeclaredMethod("getMinDelay", null);
            if (declaredMethod != null) {
                return ((Integer) declaredMethod.invoke(obj, null)).intValue();
            }
        } catch (Exception e) {
        }
        return 0;
    }

    /* renamed from: b */
    private static CellLocation m5377b(List list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        int i;
        CellLocation cellLocation;
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        CellLocation cellLocation2 = null;
        int i2 = 0;
        int i3 = 0;
        CellLocation cellLocation3 = null;
        while (i2 < list.size()) {
            CellLocation cellLocation4;
            Object obj = list.get(i2);
            if (obj != null) {
                try {
                    Class loadClass = systemClassLoader.loadClass("android.telephony.CellInfoGsm");
                    Class loadClass2 = systemClassLoader.loadClass("android.telephony.CellInfoWcdma");
                    Class loadClass3 = systemClassLoader.loadClass("android.telephony.CellInfoLte");
                    Class loadClass4 = systemClassLoader.loadClass("android.telephony.CellInfoCdma");
                    i = loadClass.isInstance(obj) ? 1 : loadClass2.isInstance(obj) ? 2 : loadClass3.isInstance(obj) ? 3 : loadClass4.isInstance(obj) ? 4 : 0;
                    if (i > 0) {
                        Object obj2 = null;
                        if (i == 1) {
                            try {
                                obj2 = loadClass.cast(obj);
                            } catch (Exception e) {
                                i3 = i;
                                cellLocation4 = cellLocation2;
                            }
                        } else if (i == 2) {
                            obj2 = loadClass2.cast(obj);
                        } else if (i == 3) {
                            obj2 = loadClass3.cast(obj);
                        } else if (i == 4) {
                            obj2 = loadClass4.cast(obj);
                        }
                        obj = C1222aj.m5335a(obj2, "getCellIdentity", new Object[0]);
                        if (obj != null) {
                            if (i != 4) {
                                int b;
                                if (i != 3) {
                                    i3 = C1222aj.m5340b(obj, "getLac", new Object[0]);
                                    b = C1222aj.m5340b(obj, "getCid", new Object[0]);
                                    cellLocation4 = new GsmCellLocation();
                                    cellLocation4.setLacAndCid(i3, b);
                                    cellLocation = cellLocation3;
                                    cellLocation3 = cellLocation4;
                                    break;
                                }
                                i3 = C1222aj.m5340b(obj, "getTac", new Object[0]);
                                b = C1222aj.m5340b(obj, "getCi", new Object[0]);
                                cellLocation4 = new GsmCellLocation();
                                try {
                                    cellLocation4.setLacAndCid(i3, b);
                                    cellLocation = cellLocation3;
                                    cellLocation3 = cellLocation4;
                                    break;
                                } catch (Exception e2) {
                                    i3 = i;
                                }
                            } else {
                                cellLocation = new CdmaCellLocation();
                                try {
                                    cellLocation.setCellLocationData(C1222aj.m5340b(obj, "getBasestationId", new Object[0]), C1222aj.m5340b(obj, "getLatitude", new Object[0]), C1222aj.m5340b(obj, "getLongitude", new Object[0]), C1222aj.m5340b(obj, "getSystemId", new Object[0]), C1222aj.m5340b(obj, "getNetworkId", new Object[0]));
                                    cellLocation3 = cellLocation2;
                                    break;
                                } catch (Exception e3) {
                                    cellLocation3 = cellLocation;
                                    cellLocation4 = cellLocation2;
                                    i3 = i;
                                }
                            }
                        } else {
                            i3 = i;
                            cellLocation4 = cellLocation2;
                        }
                    } else {
                        i3 = i;
                        cellLocation4 = cellLocation2;
                    }
                } catch (Exception e4) {
                    cellLocation4 = cellLocation2;
                }
            } else {
                cellLocation4 = cellLocation2;
            }
            i2++;
            cellLocation2 = cellLocation4;
        }
        i = i3;
        cellLocation = cellLocation3;
        cellLocation3 = cellLocation2;
        return i != 4 ? cellLocation3 : cellLocation;
    }

    /* renamed from: b */
    private void m5379b(BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver != null && this.f4251a != null) {
            try {
                this.f4251a.unregisterReceiver(broadcastReceiver);
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: b */
    protected static boolean m5380b(Context context) {
        if (context == null) {
            return true;
        }
        boolean z;
        if (!Secure.getString(context.getContentResolver(), "mock_location").equals("0")) {
            PackageManager packageManager = context.getPackageManager();
            List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(128);
            String str = "android.permission.ACCESS_MOCK_LOCATION";
            String packageName = context.getPackageName();
            z = false;
            for (ApplicationInfo applicationInfo : installedApplications) {
                if (z) {
                    break;
                }
                boolean z2;
                try {
                    String[] strArr = packageManager.getPackageInfo(applicationInfo.packageName, 4096).requestedPermissions;
                    if (strArr != null) {
                        int length = strArr.length;
                        int i = 0;
                        while (i < length) {
                            if (!strArr[i].equals(str)) {
                                i++;
                            } else if (!applicationInfo.packageName.equals(packageName)) {
                                z2 = true;
                                z = z2;
                            }
                        }
                    }
                } catch (Exception e) {
                    z2 = z;
                }
            }
        } else {
            z = false;
        }
        return z;
    }

    /* renamed from: b */
    private static String[] m5381b(TelephonyManager telephonyManager) {
        int i = 0;
        CharSequence charSequence = null;
        if (telephonyManager != null) {
            charSequence = telephonyManager.getNetworkOperator();
        }
        String[] strArr = new String[]{"0", "0"};
        if (TextUtils.isDigitsOnly(charSequence) && charSequence.length() > 4) {
            strArr[0] = charSequence.substring(0, 3);
            char[] toCharArray = charSequence.substring(3).toCharArray();
            while (i < toCharArray.length && Character.isDigit(toCharArray[i])) {
                i++;
            }
            strArr[1] = charSequence.substring(3, i + 3);
        }
        return strArr;
    }

    /* renamed from: c */
    private static boolean m5385c(Context context) {
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            for (String a : C1249bf.f4369b) {
                if (!C1249bf.m5543a(strArr, a)) {
                    return false;
                }
            }
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: a */
    public final String mo13099a(int i) {
        ArrayList arrayList = new ArrayList();
        if (this.f4255e == null) {
            return SafeJsonPrimitive.NULL_STRING;
        }
        List sensorList = this.f4255e.getSensorList(-1);
        return (sensorList == null || sensorList.get(i) == null || ((Sensor) sensorList.get(i)).getName() == null || ((Sensor) sensorList.get(i)).getName().length() <= 0) ? SafeJsonPrimitive.NULL_STRING : ((Sensor) sensorList.get(i)).getName();
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: a */
    public final List mo13100a(float f) {
        ArrayList arrayList = new ArrayList();
        long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(f) <= 1.0f) {
            f = 1.0f;
        }
        if (mo13106c()) {
            CellLocation cellLocation = (CellLocation) mo13118j().get(1);
            if (cellLocation != null && (cellLocation instanceof GsmCellLocation)) {
                arrayList.add(Integer.valueOf(((GsmCellLocation) cellLocation).getLac()));
                arrayList.add(Integer.valueOf(((GsmCellLocation) cellLocation).getCid()));
                if (((double) (currentTimeMillis - ((Long) mo13118j().get(0)).longValue())) <= 50000.0d / ((double) f)) {
                    arrayList.add(Integer.valueOf(1));
                } else {
                    arrayList.add(Integer.valueOf(0));
                }
            }
        }
        return arrayList;
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: a */
    public final void mo13101a() {
        String str = "";
        mo13104b();
        if (this.f4250C != null) {
            this.f4250C.quit();
            this.f4250C = null;
        }
        if (this.f4249B != null) {
            this.f4249B.interrupt();
            this.f4249B = null;
        }
        this.f4249B = new C1226an(this, str);
        this.f4249B.start();
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: b */
    public final double mo13102b(int i) {
        ArrayList arrayList = new ArrayList();
        if (this.f4255e == null) {
            return 0.0d;
        }
        List sensorList = this.f4255e.getSensorList(-1);
        return (sensorList == null || sensorList.get(i) == null) ? 0.0d : (double) ((Sensor) sensorList.get(i)).getMaximumRange();
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: b */
    public final List mo13103b(float f) {
        ArrayList arrayList = new ArrayList();
        long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(f) <= 1.0f) {
            f = 1.0f;
        }
        if (mo13106c()) {
            CellLocation cellLocation = (CellLocation) mo13118j().get(1);
            if (cellLocation != null && (cellLocation instanceof CdmaCellLocation)) {
                CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
                arrayList.add(Integer.valueOf(cdmaCellLocation.getSystemId()));
                arrayList.add(Integer.valueOf(cdmaCellLocation.getNetworkId()));
                arrayList.add(Integer.valueOf(cdmaCellLocation.getBaseStationId()));
                arrayList.add(Integer.valueOf(cdmaCellLocation.getBaseStationLongitude()));
                arrayList.add(Integer.valueOf(cdmaCellLocation.getBaseStationLatitude()));
                if (((double) (currentTimeMillis - ((Long) mo13118j().get(0)).longValue())) <= 50000.0d / ((double) f)) {
                    arrayList.add(Integer.valueOf(1));
                } else {
                    arrayList.add(Integer.valueOf(0));
                }
            }
        }
        return arrayList;
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: b */
    public final void mo13104b() {
        if (this.f4271v != null) {
            C1227ao c1227ao = this.f4271v;
            if (this.f4252b != null) {
                this.f4252b.listen(c1227ao, 0);
            }
            this.f4271v = null;
        }
        if (this.f4272w != null) {
            C1228ap c1228ap = this.f4272w;
            if (!(this.f4253c == null || c1228ap == null)) {
                this.f4253c.removeNmeaListener(c1228ap);
            }
            this.f4272w = null;
        }
        if (this.f4248A != null) {
            this.f4248A.cancel();
            this.f4248A = null;
        }
        if (this.f4250C != null) {
            this.f4250C.quit();
            this.f4250C = null;
        }
        if (this.f4249B != null) {
            this.f4249B.interrupt();
            this.f4249B = null;
        }
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: c */
    public final int mo13105c(int i) {
        ArrayList arrayList = new ArrayList();
        if (this.f4255e == null) {
            return 0;
        }
        List sensorList = this.f4255e.getSensorList(-1);
        return (sensorList == null || sensorList.get(i) == null) ? 0 : C1225am.m5375b(sensorList.get(i));
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: c */
    public final boolean mo13106c() {
        CellLocation cellLocation = null;
        if (this.f4252b != null && this.f4252b.getSimState() == 5 && this.f4261k) {
            return true;
        }
        if (this.f4252b != null) {
            try {
                cellLocation = this.f4252b.getCellLocation();
            } catch (Exception e) {
            }
            if (cellLocation != null) {
                this.f4270t = System.currentTimeMillis();
                this.f4273x = cellLocation;
                return true;
            }
        }
        return false;
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: d */
    public final int mo13107d(int i) {
        ArrayList arrayList = new ArrayList();
        if (this.f4255e == null) {
            return 0;
        }
        List sensorList = this.f4255e.getSensorList(-1);
        return (sensorList == null || sensorList.get(i) == null) ? 0 : (int) (((double) ((Sensor) sensorList.get(i)).getPower()) * 100.0d);
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: d */
    public final boolean mo13108d() {
        return this.f4254d != null && (this.f4254d.isWifiEnabled() || C1225am.m5372a(this.f4254d));
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: e */
    public final double mo13109e(int i) {
        ArrayList arrayList = new ArrayList();
        if (this.f4255e == null) {
            return 0.0d;
        }
        List sensorList = this.f4255e.getSensorList(-1);
        return (sensorList == null || sensorList.get(i) == null) ? 0.0d : (double) ((Sensor) sensorList.get(i)).getResolution();
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: e */
    public final boolean mo13110e() {
        try {
            if (this.f4253c != null && this.f4253c.isProviderEnabled("gps")) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: f */
    public final byte mo13111f(int i) {
        ArrayList arrayList = new ArrayList();
        if (this.f4255e == null) {
            return Byte.MAX_VALUE;
        }
        List sensorList = this.f4255e.getSensorList(-1);
        return (sensorList == null || sensorList.get(i) == null || ((Sensor) sensorList.get(i)).getType() > TransportMediator.KEYCODE_MEDIA_PAUSE) ? Byte.MAX_VALUE : (byte) ((Sensor) sensorList.get(i)).getType();
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: f */
    public final String mo13112f() {
        if (this.f4256f == null) {
            this.f4256f = Build.MODEL;
        }
        return this.f4256f != null ? this.f4256f : "";
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: g */
    public final String mo13113g() {
        if (this.f4257g == null && this.f4251a != null) {
            this.f4252b = (TelephonyManager) this.f4251a.getSystemService("phone");
            if (this.f4252b != null) {
                try {
                    this.f4257g = this.f4252b.getDeviceId();
                } catch (Exception e) {
                }
            }
        }
        return this.f4257g != null ? this.f4257g : "";
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: g */
    public final String mo13114g(int i) {
        ArrayList arrayList = new ArrayList();
        if (this.f4255e == null) {
            return SafeJsonPrimitive.NULL_STRING;
        }
        List sensorList = this.f4255e.getSensorList(-1);
        return (sensorList == null || sensorList.get(i) == null || ((Sensor) sensorList.get(i)).getVendor() == null || ((Sensor) sensorList.get(i)).getVendor().length() <= 0) ? SafeJsonPrimitive.NULL_STRING : ((Sensor) sensorList.get(i)).getVendor();
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: h */
    public final byte mo13115h(int i) {
        ArrayList arrayList = new ArrayList();
        if (this.f4255e == null) {
            return Byte.MAX_VALUE;
        }
        List sensorList = this.f4255e.getSensorList(-1);
        return (sensorList == null || sensorList.get(i) == null || ((Sensor) sensorList.get(i)).getType() > TransportMediator.KEYCODE_MEDIA_PAUSE) ? Byte.MAX_VALUE : (byte) ((Sensor) sensorList.get(i)).getVersion();
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: h */
    public final String mo13116h() {
        if (this.f4258h == null && this.f4251a != null) {
            this.f4252b = (TelephonyManager) this.f4251a.getSystemService("phone");
            if (this.f4252b != null) {
                this.f4258h = this.f4252b.getSubscriberId();
            }
        }
        return this.f4258h != null ? this.f4258h : "";
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: i */
    public final boolean mo13117i() {
        return this.f4259i;
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: j */
    public final List mo13118j() {
        if (System.getInt(this.f4251a.getContentResolver(), "airplane_mode_on", 0) == 1) {
            return new ArrayList();
        }
        if (!mo13106c()) {
            return new ArrayList();
        }
        Object B;
        ArrayList arrayList = new ArrayList();
        if (!m5370a(this.f4273x)) {
            B = m5354B();
            if (m5370a((CellLocation) B)) {
                this.f4270t = System.currentTimeMillis();
                arrayList.add(Long.valueOf(this.f4270t));
                arrayList.add(B);
                return arrayList;
            }
        }
        B = this.f4273x;
        arrayList.add(Long.valueOf(this.f4270t));
        arrayList.add(B);
        return arrayList;
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: k */
    public final List mo13119k() {
        int i = 0;
        ArrayList arrayList = new ArrayList();
        if (!mo13108d()) {
            return new ArrayList();
        }
        ArrayList arrayList2 = new ArrayList();
        synchronized (this) {
            if ((System.currentTimeMillis() - this.f4269s < 3500 ? 1 : 0) != 0) {
                arrayList2.add(Long.valueOf(this.f4269s));
                while (i < this.f4275z.size()) {
                    arrayList.add(this.f4275z.get(i));
                    i++;
                }
                arrayList2.add(arrayList);
            }
        }
        return arrayList2;
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: l */
    public final byte mo13120l() {
        return mo13106c() ? (byte) this.f4260j : Byte.MIN_VALUE;
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: m */
    public final List mo13121m() {
        ArrayList arrayList = new ArrayList();
        if (this.f4252b == null) {
            return arrayList;
        }
        if (!mo13106c()) {
            return arrayList;
        }
        if (this.f4252b.getSimState() == 1) {
            return arrayList;
        }
        int i = 0;
        for (NeighboringCellInfo neighboringCellInfo : this.f4252b.getNeighboringCellInfo()) {
            if (i > 15) {
                break;
            } else if (!(neighboringCellInfo.getLac() == 0 || neighboringCellInfo.getLac() == SupportMenu.USER_MASK || neighboringCellInfo.getCid() == SupportMenu.USER_MASK || neighboringCellInfo.getCid() == 268435455)) {
                arrayList.add(neighboringCellInfo);
                i++;
            }
        }
        return arrayList;
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: n */
    public final List mo13122n() {
        long j;
        Object obj;
        ArrayList arrayList = new ArrayList();
        String str = "";
        if (mo13110e()) {
            long j2 = this.f4262l;
            j = j2;
            obj = this.f4263m;
        } else {
            String str2 = str;
            j = -1;
            String obj2 = str2;
        }
        if (j <= 0) {
            j = System.currentTimeMillis() / 1000;
        }
        if (j > 2147483647L) {
            j /= 1000;
        }
        arrayList.add(Long.valueOf(j));
        arrayList.add(obj2);
        return arrayList;
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: o */
    public final long mo13123o() {
        long j = 0;
        long j2 = this.f4262l;
        if (j2 > 0) {
            j = j2;
            int length = String.valueOf(j2).length();
            while (length != 13) {
                j = length > 13 ? j / 10 : j * 10;
                length = String.valueOf(j).length();
            }
        }
        return j;
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: p */
    public final String mo13124p() {
        if (this.f4264n == null && this.f4251a != null) {
            this.f4254d = (WifiManager) this.f4251a.getSystemService("wifi");
            if (!(this.f4254d == null || this.f4254d.getConnectionInfo() == null)) {
                this.f4264n = this.f4254d.getConnectionInfo().getMacAddress();
                if (this.f4264n != null && this.f4264n.length() > 0) {
                    this.f4264n = this.f4264n.replace(":", "");
                }
            }
        }
        return this.f4264n != null ? this.f4264n : "";
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: q */
    public final int mo13125q() {
        return this.f4265o;
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: r */
    public final int mo13126r() {
        return this.f4266p;
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: s */
    public final int mo13127s() {
        return this.f4267q;
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: t */
    public final String mo13128t() {
        if (this.f4268r == null && this.f4251a != null) {
            this.f4268r = this.f4251a.getPackageName();
        }
        return this.f4268r != null ? this.f4268r : "";
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: u */
    public final List mo13129u() {
        int i = 0;
        ArrayList arrayList = new ArrayList();
        if (mo13108d()) {
            List k = mo13119k();
            List list = (List) k.get(1);
            long longValue = ((Long) k.get(0)).longValue();
            C1225am.m5369a(list);
            arrayList.add(Long.valueOf(longValue));
            if (list != null && list.size() > 0) {
                while (i < list.size()) {
                    ScanResult scanResult = (ScanResult) list.get(i);
                    if (arrayList.size() - 1 >= 40) {
                        break;
                    }
                    if (scanResult != null) {
                        ArrayList arrayList2 = new ArrayList();
                        arrayList2.add(scanResult.BSSID.replace(":", ""));
                        arrayList2.add(Integer.valueOf(scanResult.level));
                        arrayList2.add(scanResult.SSID);
                        arrayList.add(arrayList2);
                    }
                    i++;
                }
            }
        }
        return arrayList;
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: v */
    public final void mo13130v() {
        synchronized (this) {
            this.f4275z.clear();
        }
        if (this.f4274y != null) {
            m5379b(this.f4274y);
            this.f4274y = null;
        }
        if (this.f4248A != null) {
            this.f4248A.cancel();
            this.f4248A = null;
        }
        this.f4248A = new Timer();
        this.f4274y = new C1229aq(this, (byte) 0);
        m5366a(this.f4274y);
        m5353A();
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: w */
    public final void mo13131w() {
        synchronized (this) {
            this.f4275z.clear();
        }
        if (this.f4274y != null) {
            m5379b(this.f4274y);
            this.f4274y = null;
        }
        if (this.f4248A != null) {
            this.f4248A.cancel();
            this.f4248A = null;
        }
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: x */
    public final byte mo13132x() {
        ArrayList arrayList = new ArrayList();
        if (this.f4255e == null) {
            return (byte) 0;
        }
        List sensorList = this.f4255e.getSensorList(-1);
        return sensorList != null ? (byte) sensorList.size() : (byte) 0;
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: y */
    public final Context mo13133y() {
        return this.f4251a;
    }
}
