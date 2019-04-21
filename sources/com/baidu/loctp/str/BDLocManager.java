package com.baidu.loctp.str;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class BDLocManager {
    /* renamed from: e */
    private static Method f5550e = null;
    /* renamed from: f */
    private static Method f5551f = null;
    /* renamed from: g */
    private static Method f5552g = null;
    /* renamed from: h */
    private static Class<?> f5553h = null;
    /* renamed from: r */
    private static char[] f5554r = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_.".toCharArray();
    /* renamed from: a */
    private final long f5555a = 5000;
    /* renamed from: b */
    private Context f5556b = null;
    /* renamed from: c */
    private TelephonyManager f5557c = null;
    /* renamed from: d */
    private C1581a f5558d = new C1581a();
    /* renamed from: i */
    private WifiManager f5559i = null;
    /* renamed from: j */
    private WifiList f5560j = null;
    /* renamed from: k */
    private Object f5561k = null;
    /* renamed from: l */
    private Method f5562l = null;
    /* renamed from: m */
    private boolean f5563m = true;
    /* renamed from: n */
    private long f5564n = 0;
    /* renamed from: o */
    private String f5565o = null;
    /* renamed from: p */
    private int f5566p = 0;
    /* renamed from: q */
    private String f5567q = null;

    protected class WifiList {
        public List<ScanResult> _WifiList = null;
        /* renamed from: b */
        private long f5543b = 0;

        public WifiList(List<ScanResult> list) {
            this._WifiList = list;
            this.f5543b = System.currentTimeMillis();
            m7159a();
        }

        /* renamed from: a */
        private void m7159a() {
            if (size() >= 1) {
                Object obj = 1;
                for (int size = this._WifiList.size() - 1; size >= 1 && obj != null; size--) {
                    int i = 0;
                    obj = null;
                    while (i < size) {
                        Object obj2;
                        if (((ScanResult) this._WifiList.get(i)).level < ((ScanResult) this._WifiList.get(i + 1)).level) {
                            ScanResult scanResult = (ScanResult) this._WifiList.get(i + 1);
                            this._WifiList.set(i + 1, this._WifiList.get(i));
                            this._WifiList.set(i, scanResult);
                            obj2 = 1;
                        } else {
                            obj2 = obj;
                        }
                        i++;
                        obj = obj2;
                    }
                }
            }
        }

        /* renamed from: b */
        private boolean m7161b() {
            long currentTimeMillis = System.currentTimeMillis() - this.f5543b;
            return currentTimeMillis < 0 || currentTimeMillis > 500;
        }

        public int size() {
            return this._WifiList == null ? 0 : this._WifiList.size();
        }

        public String toString(int i) {
            if (size() < 1) {
                return null;
            }
            Object obj;
            int obj2;
            Object obj3;
            boolean a = BDLocManager.this.m7169a();
            if (a) {
                i--;
                obj2 = null;
            } else {
                obj2 = 1;
            }
            StringBuffer stringBuffer = new StringBuffer(512);
            int size = this._WifiList.size();
            int i2 = 0;
            int i3 = 0;
            Object obj4 = 1;
            Object obj5 = obj2;
            while (i2 < size) {
                if (((ScanResult) this._WifiList.get(i2)).level == 0) {
                    obj2 = i3;
                    obj3 = obj4;
                    obj4 = obj5;
                } else {
                    String str = ((ScanResult) this._WifiList.get(i2)).BSSID;
                    obj2 = ((ScanResult) this._WifiList.get(i2)).level;
                    str = str.replace(":", "");
                    if (BDLocManager.this.f5565o == null || !str.equals(BDLocManager.this.f5565o)) {
                        if (i3 < i) {
                            stringBuffer.append("h");
                            stringBuffer.append(str);
                            stringBuffer.append("m");
                            stringBuffer.append(StrictMath.abs(obj2));
                            obj2 = i3 + 1;
                            obj3 = null;
                        } else {
                            obj2 = i3;
                            obj3 = obj4;
                        }
                        if (obj2 > i && obj5 != null) {
                            break;
                        }
                        obj4 = obj5;
                    } else {
                        BDLocManager.this.f5566p = StrictMath.abs(obj2);
                        obj2 = i3;
                        obj3 = obj4;
                        int i4 = 1;
                    }
                }
                i2++;
                obj5 = obj4;
                obj4 = obj3;
                i3 = obj2;
            }
            obj3 = obj4;
            String str2 = a ? "h" + BDLocManager.this.f5565o + "km" + BDLocManager.this.f5566p : null;
            return obj3 == null ? str2 + stringBuffer.toString() : str2;
        }
    }

    /* renamed from: com.baidu.loctp.str.BDLocManager$a */
    private class C1581a {
        /* renamed from: a */
        public int f5544a;
        /* renamed from: b */
        public int f5545b;
        /* renamed from: c */
        public int f5546c;
        /* renamed from: d */
        public int f5547d;
        /* renamed from: e */
        public char f5548e;

        private C1581a() {
            this.f5544a = -1;
            this.f5545b = -1;
            this.f5546c = -1;
            this.f5547d = -1;
            this.f5548e = 0;
        }

        /* renamed from: a */
        private boolean m7162a() {
            return this.f5544a > -1 && this.f5545b > 0;
        }

        public String toString() {
            if (!m7162a()) {
                return null;
            }
            StringBuffer stringBuffer = new StringBuffer(128);
            stringBuffer.append(this.f5548e);
            stringBuffer.append("h");
            if (this.f5546c != 460) {
                stringBuffer.append(this.f5546c);
            }
            stringBuffer.append(String.format(Locale.CHINA, "h%xh%xh%x", new Object[]{Integer.valueOf(this.f5547d), Integer.valueOf(this.f5544a), Integer.valueOf(this.f5545b)}));
            return stringBuffer.toString();
        }
    }

    public BDLocManager(Context context) {
        String deviceId;
        this.f5556b = context.getApplicationContext();
        String packageName = this.f5556b.getPackageName();
        try {
            this.f5557c = (TelephonyManager) this.f5556b.getSystemService("phone");
            deviceId = this.f5557c.getDeviceId();
        } catch (Exception e) {
            deviceId = null;
        }
        this.f5567q = "&" + packageName + "&" + deviceId;
        this.f5559i = (WifiManager) this.f5556b.getSystemService("wifi");
        try {
            Field declaredField = Class.forName("android.net.wifi.WifiManager").getDeclaredField("mService");
            if (declaredField != null) {
                declaredField.setAccessible(true);
                this.f5561k = declaredField.get(this.f5559i);
                this.f5562l = this.f5561k.getClass().getDeclaredMethod("startScan", new Class[]{Boolean.TYPE});
                if (this.f5562l != null) {
                    this.f5562l.setAccessible(true);
                }
            }
        } catch (Exception e2) {
        }
    }

    /* renamed from: a */
    private String m7165a(int i) {
        String c1581a;
        String wifiList;
        if (i < 3) {
            i = 3;
        }
        try {
            m7168a(this.f5557c.getCellLocation());
            c1581a = this.f5558d.toString();
        } catch (Exception e) {
            c1581a = null;
        }
        if (c1581a == null) {
            c1581a = "Z";
        }
        try {
            if (this.f5560j == null || this.f5560j.m7161b()) {
                this.f5560j = new WifiList(this.f5559i.getScanResults());
            }
            wifiList = this.f5560j.toString(i);
        } catch (Exception e2) {
            wifiList = null;
        }
        if (wifiList != null) {
            c1581a = c1581a + wifiList;
        }
        return c1581a.equals("Z") ? null : m7166a(c1581a + "t" + System.currentTimeMillis() + this.f5567q);
    }

    /* renamed from: a */
    private static String m7166a(String str) {
        int i = 0;
        if (str == null) {
            return null;
        }
        byte[] bytes = str.getBytes();
        byte nextInt = (byte) new Random().nextInt(255);
        byte nextInt2 = (byte) new Random().nextInt(255);
        byte[] bArr = new byte[(bytes.length + 2)];
        int length = bytes.length;
        int i2 = 0;
        while (i < length) {
            int i3 = i2 + 1;
            bArr[i2] = (byte) (bytes[i] ^ nextInt);
            i++;
            i2 = i3;
        }
        i = i2 + 1;
        bArr[i2] = nextInt;
        i2 = i + 1;
        bArr[i] = nextInt2;
        return m7167a(bArr);
    }

    /* renamed from: a */
    private static String m7167a(byte[] bArr) {
        char[] cArr = new char[(((bArr.length + 2) / 3) * 4)];
        int i = 0;
        int i2 = 0;
        while (i2 < bArr.length) {
            Object obj;
            Object obj2;
            int i3 = (bArr[i2] & 255) << 8;
            if (i2 + 1 < bArr.length) {
                i3 |= bArr[i2 + 1] & 255;
                obj = 1;
            } else {
                obj = null;
            }
            i3 <<= 8;
            if (i2 + 2 < bArr.length) {
                i3 |= bArr[i2 + 2] & 255;
                obj2 = 1;
            } else {
                obj2 = null;
            }
            cArr[i + 3] = f5554r[obj2 != null ? 63 - (i3 & 63) : 64];
            int i4 = i3 >> 6;
            cArr[i + 2] = f5554r[obj != null ? 63 - (i4 & 63) : 64];
            i3 = i4 >> 6;
            cArr[i + 1] = f5554r[63 - (i3 & 63)];
            cArr[i + 0] = f5554r[63 - ((i3 >> 6) & 63)];
            i2 += 3;
            i += 4;
        }
        return new String(cArr);
    }

    /* renamed from: a */
    private void m7168a(CellLocation cellLocation) {
        int i = 0;
        if (cellLocation != null && this.f5557c != null) {
            C1581a c1581a = new C1581a();
            String networkOperator = this.f5557c.getNetworkOperator();
            if (networkOperator != null && networkOperator.length() > 0) {
                try {
                    if (networkOperator.length() >= 3) {
                        int intValue = Integer.valueOf(networkOperator.substring(0, 3)).intValue();
                        if (intValue < 0) {
                            intValue = this.f5558d.f5546c;
                        }
                        c1581a.f5546c = intValue;
                    }
                    String substring = networkOperator.substring(3);
                    if (substring != null) {
                        char[] toCharArray = substring.toCharArray();
                        while (i < toCharArray.length && Character.isDigit(toCharArray[i])) {
                            i++;
                        }
                    }
                    i = Integer.valueOf(substring.substring(0, i)).intValue();
                    if (i < 0) {
                        i = this.f5558d.f5547d;
                    }
                    c1581a.f5547d = i;
                } catch (Exception e) {
                }
            }
            if (cellLocation instanceof GsmCellLocation) {
                c1581a.f5544a = ((GsmCellLocation) cellLocation).getLac();
                c1581a.f5545b = ((GsmCellLocation) cellLocation).getCid();
                c1581a.f5548e = 'g';
            } else if (cellLocation instanceof CdmaCellLocation) {
                c1581a.f5548e = 'w';
                if (f5553h == null) {
                    try {
                        f5553h = Class.forName("android.telephony.cdma.CdmaCellLocation");
                        f5550e = f5553h.getMethod("getBaseStationId", new Class[0]);
                        f5551f = f5553h.getMethod("getNetworkId", new Class[0]);
                        f5552g = f5553h.getMethod("getSystemId", new Class[0]);
                    } catch (Exception e2) {
                        f5553h = null;
                        return;
                    }
                }
                if (f5553h != null && f5553h.isInstance(cellLocation)) {
                    try {
                        i = ((Integer) f5552g.invoke(cellLocation, new Object[0])).intValue();
                        if (i < 0) {
                            i = this.f5558d.f5547d;
                        }
                        c1581a.f5547d = i;
                        c1581a.f5545b = ((Integer) f5550e.invoke(cellLocation, new Object[0])).intValue();
                        c1581a.f5544a = ((Integer) f5551f.invoke(cellLocation, new Object[0])).intValue();
                    } catch (Exception e3) {
                        return;
                    }
                }
            }
            if (c1581a.m7162a()) {
                this.f5558d = c1581a;
            }
        }
    }

    /* renamed from: a */
    private boolean m7169a() {
        String str = null;
        this.f5565o = null;
        this.f5566p = 0;
        WifiInfo connectionInfo = this.f5559i.getConnectionInfo();
        if (connectionInfo == null) {
            return false;
        }
        try {
            String bssid = connectionInfo.getBSSID();
            if (bssid != null) {
                str = bssid.replace(":", "");
            }
            if (str.length() != 12) {
                return false;
            }
            this.f5565o = new String(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getLocString(int i) {
        try {
            return m7165a(i);
        } catch (Exception e) {
            return null;
        }
    }
}
