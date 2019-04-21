package com.aps;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.p000v4.internal.view.SupportMenu;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.Log;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.core.AMapLocException;
import com.amap.api.location.core.AuthManager;
import com.amap.api.location.core.ClientInfoUtil;
import com.amap.api.location.core.CoreUtil;
import com.aps.ConnectionServiceManager.C1241a;
import com.facebook.internal.ServerProtocol;
import com.mcdonalds.sdk.connectors.middleware.model.DCSPhone;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.payload.PayloadController;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import org.json.JSONObject;

/* renamed from: com.aps.b */
public class APS implements IAPS {
    /* renamed from: A */
    private long f4311A = 0;
    /* renamed from: B */
    private boolean f4312B = false;
    /* renamed from: C */
    private long f4313C = 0;
    /* renamed from: D */
    private long f4314D = 0;
    /* renamed from: E */
    private long f4315E = 0;
    /* renamed from: F */
    private NetManager f4316F = NetManager.m5684a();
    /* renamed from: G */
    private int f4317G = 0;
    /* renamed from: H */
    private String f4318H = "00:00:00:00:00:00";
    /* renamed from: I */
    private C1213aa f4319I = null;
    /* renamed from: J */
    private StringBuilder f4320J = new StringBuilder();
    /* renamed from: K */
    private boolean f4321K = true;
    /* renamed from: L */
    private long f4322L = 0;
    /* renamed from: M */
    private Cache f4323M = null;
    /* renamed from: N */
    private CellLocation f4324N = null;
    /* renamed from: O */
    private boolean f4325O = false;
    /* renamed from: a */
    ConnectionServiceManager f4326a;
    /* renamed from: b */
    int f4327b = -1;
    /* renamed from: c */
    boolean f4328c = false;
    /* renamed from: d */
    C1242a f4329d = new C1242a();
    /* renamed from: e */
    public String f4330e = null;
    /* renamed from: f */
    public StringBuilder f4331f = null;
    /* renamed from: g */
    TimerTask f4332g;
    /* renamed from: h */
    Timer f4333h;
    /* renamed from: i */
    C1221ai f4334i;
    /* renamed from: j */
    int f4335j = 0;
    /* renamed from: k */
    private Context f4336k = null;
    /* renamed from: l */
    private int f4337l = 9;
    /* renamed from: m */
    private ConnectivityManager f4338m = null;
    /* renamed from: n */
    private WifiManager f4339n = null;
    /* renamed from: o */
    private LocFilter f4340o = null;
    /* renamed from: p */
    private TelephonyManager f4341p = null;
    /* renamed from: q */
    private List<Cgi> f4342q = new ArrayList();
    /* renamed from: r */
    private List<ScanResult> f4343r = new ArrayList();
    /* renamed from: s */
    private Map<PendingIntent, List<Fence>> f4344s = new HashMap();
    /* renamed from: t */
    private Map<PendingIntent, List<Fence>> f4345t = new HashMap();
    /* renamed from: u */
    private PhoneStateListener f4346u = null;
    /* renamed from: v */
    private int f4347v = -113;
    /* renamed from: w */
    private C1243b f4348w = new C1243b(this, this, null);
    /* renamed from: x */
    private WifiInfo f4349x = null;
    /* renamed from: y */
    private JSONObject f4350y = null;
    /* renamed from: z */
    private AmapLoc f4351z = null;

    /* compiled from: APS */
    /* renamed from: com.aps.b$1 */
    class C12391 extends PhoneStateListener {
        C12391() {
        }

        public void onCellLocationChanged(CellLocation cellLocation) {
            if (cellLocation != null) {
                try {
                    if (!APS.this.m5514r()) {
                        APS.this.f4324N = cellLocation;
                        APS.this.f4314D = C1269v.m5737b();
                        APS.this.f4313C = C1269v.m5737b();
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }

        public void onSignalStrengthChanged(int i) {
            int i2 = -113;
            try {
                switch (APS.this.f4337l) {
                    case 1:
                        i2 = C1269v.m5724a(i);
                        break;
                    case 2:
                        i2 = C1269v.m5724a(i);
                        break;
                }
                APS.this.m5483b(i2);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            int i = -113;
            try {
                switch (APS.this.f4337l) {
                    case 1:
                        i = C1269v.m5724a(signalStrength.getGsmSignalStrength());
                        break;
                    case 2:
                        i = signalStrength.getCdmaDbm();
                        break;
                }
                APS.this.m5483b(i);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        public void onServiceStateChanged(ServiceState serviceState) {
            try {
                switch (serviceState.getState()) {
                    case 1:
                        APS.this.f4342q.clear();
                        APS.this.f4347v = -113;
                        return;
                    default:
                        return;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* compiled from: APS */
    /* renamed from: com.aps.b$a */
    class C1242a implements C1241a {
        C1242a() {
        }

        /* renamed from: a */
        public void mo13168a(int i) {
            APS.this.f4327b = i;
        }
    }

    /* compiled from: APS */
    /* renamed from: com.aps.b$b */
    private class C1243b extends BroadcastReceiver {
        /* renamed from: a */
        APS f4309a;

        /* synthetic */ C1243b(APS aps, APS aps2, C12391 c12391) {
            this(aps2);
        }

        private C1243b(APS aps) {
            this.f4309a = aps;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                try {
                    String action = intent.getAction();
                    if (action.equals("android.net.wifi.SCAN_RESULTS")) {
                        if (APS.this.f4339n != null) {
                            APS.this.f4343r = APS.this.f4339n.getScanResults();
                            APS.this.f4315E = C1269v.m5737b();
                            if (APS.this.f4343r == null) {
                                APS.this.f4343r = new ArrayList();
                            }
                        }
                    } else if (action.equals("android.net.wifi.WIFI_STATE_CHANGED")) {
                        if (APS.this.f4339n != null) {
                            int i = 4;
                            try {
                                i = APS.this.f4339n.getWifiState();
                            } catch (SecurityException e) {
                            }
                            switch (i) {
                                case 0:
                                    APS.this.m5512p();
                                    return;
                                case 1:
                                    APS.this.m5512p();
                                    return;
                                case 4:
                                    APS.this.m5512p();
                                    return;
                                default:
                                    return;
                            }
                            th.printStackTrace();
                        }
                    } else if (action.equals("android.intent.action.SCREEN_ON")) {
                        APS.this.m5499h();
                        APS.this.m5513q();
                        if (this.f4309a != null) {
                            this.f4309a.f4321K = true;
                        }
                        Const.f4443j = 10000;
                        Const.f4444k = 30000;
                    } else if (action.equals("android.intent.action.SCREEN_OFF")) {
                        if (APS.this.f4317G >= 5) {
                            Const.f4443j = 20000;
                            Const.f4444k = 60000;
                        }
                        if (this.f4309a != null) {
                            this.f4309a.f4321K = false;
                            if (APS.this.f4319I != null) {
                                APS.this.f4319I.mo13081c();
                                APS.this.f4325O = false;
                            }
                        }
                    } else if (action.equals("android.intent.action.AIRPLANE_MODE")) {
                        APS.this.f4312B = C1269v.m5731a(context);
                    } else if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                        APS.this.mo13181a(true, 2);
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* renamed from: a */
    public void mo13172a(Context context) {
        if (context != null && this.f4336k == null) {
            this.f4336k = context.getApplicationContext();
            C1269v.m5727a(this.f4336k, "in debug mode, only for test");
            this.f4339n = (WifiManager) C1269v.m5739b(context, "wifi");
            this.f4340o = LocFilter.m5680a();
            this.f4338m = (ConnectivityManager) C1269v.m5739b(context, "connectivity");
            this.f4341p = (TelephonyManager) C1269v.m5739b(context, "phone");
            this.f4322L = C1269v.m5737b();
        }
    }

    /* renamed from: a */
    public synchronized AmapLoc mo13170a() throws Exception {
        AmapLoc amapLoc;
        Object obj = null;
        synchronized (this) {
            if (this.f4336k == null) {
                throw new AMapLocException("无效的参数 - IllegalArgumentException");
            } else if (TextUtils.isEmpty(Const.f4437d)) {
                throw new AMapLocException("key鉴权失败");
            } else if (TextUtils.isEmpty(Const.f4438e)) {
                throw new AMapLocException("key鉴权失败");
            } else if ("false".equals(NetManager.m5690a(this.f4350y)[0])) {
                Log.e("AuthLocation", "key鉴权失败");
                throw new AMapLocException("key鉴权失败");
            } else {
                this.f4317G++;
                if (this.f4317G > 1) {
                    if (this.f4321K) {
                        mo13182d();
                    }
                    if (!this.f4328c && AuthManager.m1415h()) {
                        m5461B();
                    }
                }
                if (m5509m()) {
                    m5499h();
                    this.f4314D = C1269v.m5737b();
                }
                if (m5510n()) {
                    m5513q();
                }
                if (this.f4343r == null) {
                    this.f4343r = new ArrayList();
                }
                if (this.f4317G == 1) {
                    this.f4312B = C1269v.m5731a(this.f4336k);
                    if (m5517u() && this.f4343r.isEmpty() && this.f4339n != null) {
                        this.f4343r = this.f4339n.getScanResults();
                    }
                }
                if (this.f4317G == 1 && m5517u() && this.f4343r.isEmpty()) {
                    for (int i = 4; i > 0 && this.f4343r.size() == 0; i--) {
                        SystemClock.sleep(500);
                    }
                }
                if (!m5472a(this.f4311A) || this.f4351z == null) {
                    try {
                        m5468a(this.f4324N);
                    } catch (AMapLocException e) {
                        if (amapLoc != null) {
                            if (C1269v.m5726a() - amapLoc.mo13216j() >= 86400000) {
                                throw e;
                            }
                        }
                        throw e;
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    m5470a(this.f4343r);
                    String j = m5504j();
                    if (TextUtils.isEmpty(j)) {
                        if (AuthManager.m1415h()) {
                            if (this.f4327b != 0) {
                                SystemClock.sleep(500);
                            }
                            if (this.f4327b == 0) {
                                this.f4351z = this.f4326a.mo13253d();
                                m5515s();
                                mo13183e();
                                if (this.f4351z != null) {
                                    amapLoc = this.f4351z;
                                }
                            }
                        }
                        throw new AMapLocException(AMapLocException.ERROR_FAILURE_INFO);
                    }
                    StringBuilder k = m5505k();
                    this.f4330e = j;
                    this.f4331f = k;
                    amapLoc = null;
                    if (this.f4317G > 1) {
                        try {
                            amapLoc = this.f4323M.mo13242a(j, k, "mem");
                        } catch (Throwable th2) {
                        }
                    }
                    if (amapLoc != null) {
                        if (C1269v.m5726a() - amapLoc.mo13216j() > 300000) {
                            obj = 1;
                        }
                    }
                    if (amapLoc == null || obj != null) {
                        amapLoc = m5518v();
                        this.f4351z = amapLoc;
                    } else {
                        this.f4351z = amapLoc;
                    }
                    if (this.f4317G > 1) {
                        this.f4323M.mo13244a(j, this.f4351z, k);
                    }
                    k.delete(0, k.length());
                    this.f4311A = C1269v.m5726a();
                    if (this.f4340o != null) {
                        this.f4351z = this.f4340o.mo13277a(this.f4351z);
                    }
                    m5515s();
                    mo13183e();
                    amapLoc = this.f4351z;
                } else {
                    this.f4311A = C1269v.m5726a();
                    amapLoc = this.f4351z;
                }
            }
        }
        return amapLoc;
    }

    /* renamed from: b */
    public void mo13177b() {
        if (this.f4317G == 1) {
            try {
                if (this.f4323M == null) {
                    this.f4323M = new Cache(this.f4336k);
                }
                m5497g();
                m5502i();
                if (this.f4326a == null) {
                    this.f4326a = new ConnectionServiceManager(this.f4336k);
                    this.f4326a.mo13250a(this.f4329d);
                }
                if (!this.f4328c && AuthManager.m1415h()) {
                    m5461B();
                }
                this.f4323M.mo13244a(this.f4330e, this.f4351z, this.f4331f);
            } catch (Throwable th) {
            }
        }
    }

    /* renamed from: a */
    public void mo13175a(String str) {
        if (str != null && str.indexOf("##") != -1) {
            String[] split = str.split("##");
            if (split.length == 3) {
                Const.m5625a(split[0]);
                if (!(Const.f4438e.equals(split[1]) || this.f4323M == null)) {
                    this.f4323M.mo13243a();
                }
                Const.m5627b(split[1]);
                Const.m5628c(split[2]);
            }
        }
    }

    /* renamed from: a */
    public void mo13176a(JSONObject jSONObject) {
        this.f4350y = jSONObject;
    }

    /* renamed from: a */
    public void mo13174a(Fence fence, PendingIntent pendingIntent) {
        if (pendingIntent != null && fence != null) {
            long a = fence.mo13274a();
            if (a != -1 && a < C1269v.m5737b()) {
                return;
            }
            if (this.f4344s.get(pendingIntent) != null) {
                List list = (List) this.f4344s.get(pendingIntent);
                list.add(fence);
                this.f4344s.put(pendingIntent, list);
                return;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(fence);
            this.f4344s.put(pendingIntent, arrayList);
        }
    }

    /* renamed from: b */
    public void mo13179b(Fence fence, PendingIntent pendingIntent) {
        if (pendingIntent != null && fence != null) {
            long a = fence.mo13274a();
            if (a != -1 && a < C1269v.m5737b()) {
                return;
            }
            if (this.f4345t.get(pendingIntent) != null) {
                List list = (List) this.f4345t.get(pendingIntent);
                list.add(fence);
                this.f4345t.put(pendingIntent, list);
                return;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(fence);
            this.f4345t.put(pendingIntent, arrayList);
        }
    }

    /* renamed from: a */
    public void mo13171a(PendingIntent pendingIntent) {
        if (pendingIntent != null) {
            this.f4344s.remove(pendingIntent);
        }
    }

    /* renamed from: b */
    public void mo13178b(PendingIntent pendingIntent) {
        if (pendingIntent != null) {
            this.f4345t.remove(pendingIntent);
        }
    }

    /* renamed from: c */
    public void mo13180c() {
        try {
            if (this.f4319I != null) {
                this.f4319I.mo13081c();
                this.f4325O = false;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        try {
            if (this.f4336k != null) {
                this.f4336k.unregisterReceiver(this.f4348w);
            }
        } catch (Throwable th2) {
            this.f4348w = null;
        }
        this.f4348w = null;
        m5520x();
        try {
            if (!(this.f4341p == null || this.f4346u == null)) {
                this.f4341p.listen(this.f4346u, 0);
            }
        } catch (Throwable th3) {
            th3.printStackTrace();
        }
        if (!(this.f4336k == null || this.f4323M == null)) {
            this.f4323M.mo13243a();
            this.f4323M.mo13246b();
        }
        Const.m5626a(false);
        this.f4311A = 0;
        this.f4342q.clear();
        this.f4344s.clear();
        this.f4345t.clear();
        this.f4347v = -113;
        m5512p();
        this.f4351z = null;
        this.f4336k = null;
        this.f4341p = null;
        if (this.f4340o != null) {
            this.f4340o.mo13278b();
            this.f4340o = null;
        }
        try {
            if (this.f4326a != null) {
                if (this.f4327b == 0 && this.f4328c) {
                    this.f4326a.mo13249a();
                }
                this.f4327b = -1;
                this.f4328c = false;
                this.f4326a = null;
            }
        } catch (Throwable th4) {
        }
    }

    /* renamed from: g */
    private void m5497g() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.AIRPLANE_MODE");
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        this.f4336k.registerReceiver(this.f4348w, intentFilter);
        m5513q();
    }

    /* renamed from: h */
    private void m5499h() {
        try {
            CellLocation.requestLocationUpdate();
        } catch (Throwable th) {
        }
    }

    /* renamed from: i */
    private void m5502i() {
        int phoneType;
        int i = 2;
        m5499h();
        this.f4314D = C1269v.m5737b();
        if (this.f4341p != null) {
            phoneType = this.f4341p.getPhoneType();
        } else {
            phoneType = 9;
        }
        switch (phoneType) {
            case 1:
                this.f4337l = 1;
                break;
            case 2:
                this.f4337l = 2;
                break;
            default:
                this.f4337l = 9;
                break;
        }
        this.f4346u = new C12391();
        if (C1269v.m5740c() >= 7) {
            i = 256;
        }
        if (i != 0) {
            try {
                if (this.f4341p != null) {
                    this.f4341p.listen(this.f4346u, i | 16);
                }
            } catch (SecurityException e) {
                C1269v.m5728a(e);
            }
        } else if (this.f4341p != null) {
            this.f4341p.listen(this.f4346u, 16);
        }
    }

    /* renamed from: a */
    private void m5468a(CellLocation cellLocation) {
        CellLocation cellLocation2 = null;
        if (!(this.f4312B || this.f4341p == null || this.f4341p == null)) {
            cellLocation2 = m5460A();
        }
        if (cellLocation2 != null) {
            cellLocation = cellLocation2;
        }
        if (cellLocation != null) {
            switch (C1269v.m5725a(cellLocation, this.f4336k)) {
                case 1:
                    if (this.f4341p != null) {
                        m5488c(cellLocation);
                        return;
                    }
                    return;
                case 2:
                    m5493d(cellLocation);
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: a */
    private boolean m5472a(long j) {
        long a = C1269v.m5726a();
        if (a - j >= 300) {
            return false;
        }
        long j2 = 0;
        if (this.f4351z != null) {
            j2 = a - this.f4351z.mo13216j();
        }
        if (j2 > 10000) {
            return false;
        }
        return true;
    }

    /* renamed from: j */
    private String m5504j() {
        m5519w();
        String str = "";
        String str2 = "";
        str2 = LocationManagerProxy.NETWORK_PROVIDER;
        if (m5517u()) {
            this.f4349x = this.f4339n.getConnectionInfo();
        } else {
            m5512p();
        }
        String str3 = "";
        Cgi cgi;
        StringBuilder stringBuilder;
        switch (this.f4337l) {
            case 1:
                if (this.f4342q.size() > 0) {
                    cgi = (Cgi) this.f4342q.get(0);
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(cgi.f4409a).append("#");
                    stringBuilder.append(cgi.f4410b).append("#");
                    stringBuilder.append(cgi.f4411c).append("#");
                    stringBuilder.append(cgi.f4412d).append("#");
                    stringBuilder.append(str2).append("#");
                    if (this.f4343r.size() > 0) {
                        str = "cellwifi";
                    } else {
                        str = DCSPhone.TYPE_CELL;
                    }
                    stringBuilder.append(str);
                    return stringBuilder.toString();
                }
                break;
            case 2:
                if (this.f4342q.size() > 0) {
                    cgi = (Cgi) this.f4342q.get(0);
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(cgi.f4409a).append("#");
                    stringBuilder.append(cgi.f4410b).append("#");
                    stringBuilder.append(cgi.f4415g).append("#");
                    stringBuilder.append(cgi.f4416h).append("#");
                    stringBuilder.append(cgi.f4417i).append("#");
                    stringBuilder.append(str2).append("#");
                    if (this.f4343r.size() > 0) {
                        str = "cellwifi";
                    } else {
                        str = DCSPhone.TYPE_CELL;
                    }
                    stringBuilder.append(str);
                    return stringBuilder.toString();
                }
                break;
            case 9:
                str2 = String.format("#%s#", new Object[]{str2});
                if ((this.f4343r.size() == 1 && !m5474a(this.f4349x)) || this.f4343r.size() == 0) {
                    return null;
                }
                if (this.f4343r.size() != 1 || !m5474a(this.f4349x)) {
                    return str2 + "wifi";
                }
                ScanResult scanResult = (ScanResult) this.f4343r.get(0);
                if (scanResult == null || !this.f4349x.getBSSID().equals(scanResult.BSSID)) {
                    str = str2;
                } else {
                    str = null;
                }
                return str;
        }
        return str;
    }

    /* renamed from: a */
    private boolean m5474a(WifiInfo wifiInfo) {
        if (wifiInfo == null || wifiInfo.getBSSID() == null || wifiInfo.getSSID() == null || wifiInfo.getBSSID().equals("00:00:00:00:00:00") || TextUtils.isEmpty(wifiInfo.getSSID())) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    private boolean m5473a(ScanResult scanResult) {
        boolean z = false;
        if (scanResult != null) {
            try {
                if (!(TextUtils.isEmpty(scanResult.BSSID) || scanResult.BSSID.equals("00:00:00:00:00:00"))) {
                    z = true;
                }
            } catch (Exception e) {
                return true;
            }
        }
        return z;
    }

    /* renamed from: k */
    private StringBuilder m5505k() {
        m5519w();
        StringBuilder stringBuilder = new StringBuilder(700);
        switch (this.f4337l) {
            case 1:
                for (int i = 0; i < this.f4342q.size(); i++) {
                    if (i != 0) {
                        Cgi cgi = (Cgi) this.f4342q.get(i);
                        stringBuilder.append("#").append(cgi.f4410b);
                        stringBuilder.append("|").append(cgi.f4411c);
                        stringBuilder.append("|").append(cgi.f4412d);
                    }
                }
                break;
        }
        if (this.f4318H == null || this.f4318H.equals("00:00:00:00:00:00")) {
            if (this.f4349x != null) {
                this.f4318H = this.f4349x.getMacAddress();
                if (this.f4318H == null) {
                    this.f4318H = "00:00:00:00:00:00";
                }
            } else if (this.f4339n != null) {
                this.f4349x = this.f4339n.getConnectionInfo();
                if (this.f4349x != null) {
                    this.f4318H = this.f4349x.getMacAddress();
                    if (this.f4318H == null) {
                        this.f4318H = "00:00:00:00:00:00";
                    }
                    this.f4349x = null;
                }
            }
        }
        if (m5517u()) {
            String bssid;
            String str = "";
            if (m5474a(this.f4349x)) {
                bssid = this.f4349x.getBSSID();
            } else {
                bssid = str;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.f4343r.size(); i3++) {
                ScanResult scanResult = (ScanResult) this.f4343r.get(i3);
                if (m5473a(scanResult)) {
                    String str2 = scanResult.BSSID;
                    str = "nb";
                    if (bssid.equals(str2)) {
                        str = "access";
                        i2 = 1;
                    }
                    stringBuilder.append(String.format("#%s,%s", new Object[]{str2, str}));
                }
            }
            if (i2 == 0 && bssid.length() > 0) {
                stringBuilder.append("#").append(bssid);
                stringBuilder.append(",access");
            }
        } else {
            m5512p();
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(0);
        }
        return stringBuilder;
    }

    /* renamed from: a */
    private synchronized byte[] m5477a(Object obj) {
        Req req;
        String str;
        StringBuilder stringBuilder;
        req = new Req();
        String str2 = "0";
        String str3 = "0";
        String str4 = "0";
        String str5 = "0";
        String str6 = "0";
        String str7 = "";
        Const.f4436c = "";
        String str8 = "";
        str7 = "";
        String str9 = "";
        String str10 = "1.4.1";
        String a = C1213aa.m5279a("version");
        StringBuilder stringBuilder2 = new StringBuilder();
        StringBuilder stringBuilder3 = new StringBuilder();
        StringBuilder stringBuilder4 = new StringBuilder();
        if (this.f4337l == 2) {
            str = "1";
        } else {
            str = str2;
        }
        if (this.f4341p != null) {
            if (Const.f4434a == null || "888888888888888".equals(Const.f4434a)) {
                try {
                    Const.f4434a = ClientInfoUtil.m1440i();
                    if (Const.f4434a == null) {
                        Const.f4434a = "888888888888888";
                    }
                } catch (SecurityException e) {
                }
            }
            if (Const.f4435b == null || "888888888888888".equals(Const.f4435b)) {
                try {
                    Const.f4435b = ClientInfoUtil.m1442j();
                    if (Const.f4435b == null) {
                        Const.f4435b = "888888888888888";
                    }
                } catch (SecurityException e2) {
                }
            }
        }
        NetworkInfo networkInfo = null;
        try {
            networkInfo = this.f4338m.getActiveNetworkInfo();
        } catch (SecurityException e3) {
        }
        if (NetManager.m5683a(networkInfo) != -1) {
            str8 = NetManager.m5685a(this.f4341p);
            if (m5517u()) {
                if (m5474a(this.f4349x)) {
                    str7 = "2";
                }
            }
            str7 = "1";
            if (!m5517u()) {
                m5512p();
            }
        } else {
            this.f4349x = null;
        }
        str2 = NetManager.m5690a(this.f4350y)[1];
        req.f4507i = str;
        req.f4508j = str3;
        req.f4509k = str4;
        req.f4510l = str5;
        req.f4511m = str6;
        req.f4501c = Const.f4437d;
        req.f4502d = Const.f4438e;
        req.f4512n = str2;
        req.f4513o = Const.f4434a;
        req.f4516r = Const.f4436c;
        req.f4514p = Const.f4435b;
        req.f4515q = this.f4318H;
        req.f4517s = str8;
        req.f4518t = str7;
        req.f4504f = ClientInfoUtil.m1433e();
        req.f4505g = "android" + ClientInfoUtil.m1429d();
        req.f4506h = ClientInfoUtil.m1436f() + "," + ClientInfoUtil.m1437g();
        req.f4495B = str10;
        req.f4496C = a;
        try {
            if (this.f4343r != null && this.f4343r.size() > 0) {
                req.f4498E = (C1269v.m5737b() - this.f4315E) + "";
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (this.f4342q.size() > 0) {
            StringBuilder stringBuilder5 = new StringBuilder();
            Cgi cgi;
            switch (this.f4337l) {
                case 1:
                    cgi = (Cgi) this.f4342q.get(0);
                    stringBuilder5.delete(0, stringBuilder5.length());
                    stringBuilder5.append("<mcc>").append(cgi.f4409a).append("</mcc>");
                    stringBuilder5.append("<mnc>").append(cgi.f4410b).append("</mnc>");
                    stringBuilder5.append("<lac>").append(cgi.f4411c).append("</lac>");
                    stringBuilder5.append("<cellid>").append(cgi.f4412d);
                    stringBuilder5.append("</cellid>");
                    stringBuilder5.append("<signal>").append(cgi.f4418j);
                    stringBuilder5.append("</signal>");
                    str9 = stringBuilder5.toString();
                    for (int i = 0; i < this.f4342q.size(); i++) {
                        if (i != 0) {
                            cgi = (Cgi) this.f4342q.get(i);
                            stringBuilder2.append(cgi.f4411c).append(",");
                            stringBuilder2.append(cgi.f4412d).append(",");
                            stringBuilder2.append(cgi.f4418j);
                            if (i != this.f4342q.size() - 1) {
                                stringBuilder2.append("*");
                            }
                        }
                    }
                    str7 = str9;
                    break;
                case 2:
                    cgi = (Cgi) this.f4342q.get(0);
                    stringBuilder5.delete(0, stringBuilder5.length());
                    stringBuilder5.append("<mcc>").append(cgi.f4409a).append("</mcc>");
                    stringBuilder5.append("<sid>").append(cgi.f4415g).append("</sid>");
                    stringBuilder5.append("<nid>").append(cgi.f4416h).append("</nid>");
                    stringBuilder5.append("<bid>").append(cgi.f4417i).append("</bid>");
                    if (cgi.f4414f > 0 && cgi.f4413e > 0) {
                        stringBuilder5.append("<lon>").append(cgi.f4414f).append("</lon>");
                        stringBuilder5.append("<lat>").append(cgi.f4413e).append("</lat>");
                    }
                    stringBuilder5.append("<signal>").append(cgi.f4418j).append("</signal>");
                    str7 = stringBuilder5.toString();
                    break;
                default:
                    str7 = str9;
                    break;
            }
            stringBuilder5.delete(0, stringBuilder5.length());
            str8 = str7;
        } else {
            str8 = str9;
        }
        if (m5517u()) {
            if (m5474a(this.f4349x)) {
                stringBuilder4.append(this.f4349x.getBSSID()).append(",");
                stringBuilder4.append(this.f4349x.getRssi()).append(",");
                stringBuilder4.append(this.f4349x.getSSID().replace("*", "."));
            }
            for (int i2 = 0; i2 < this.f4343r.size(); i2++) {
                ScanResult scanResult = (ScanResult) this.f4343r.get(i2);
                if (m5473a(scanResult)) {
                    stringBuilder3.append(scanResult.BSSID).append(",");
                    stringBuilder3.append(scanResult.level).append(",");
                    stringBuilder3.append(i2).append("*");
                }
            }
        } else {
            m5512p();
        }
        if (stringBuilder3.length() > 0) {
            stringBuilder3.deleteCharAt(stringBuilder3.length() - 1);
        }
        if (stringBuilder3.length() == 0) {
            stringBuilder = stringBuilder4;
        } else {
            stringBuilder = stringBuilder3;
        }
        req.f4520v = str8;
        req.f4521w = stringBuilder2.toString();
        req.f4522x = stringBuilder4.toString();
        req.f4523y = stringBuilder.toString();
        req.f4519u = String.valueOf(this.f4337l);
        stringBuilder2.delete(0, stringBuilder2.length());
        stringBuilder.delete(0, stringBuilder.length());
        stringBuilder4.delete(0, stringBuilder4.length());
        return req.mo13286a();
    }

    /* renamed from: a */
    private synchronized void m5470a(List<ScanResult> list) {
        if (list != null) {
            if (list.size() >= 1) {
                HashMap hashMap = new HashMap();
                for (int i = 0; i < list.size(); i++) {
                    ScanResult scanResult = (ScanResult) list.get(i);
                    if (list.size() <= 20 || m5471a(scanResult.level)) {
                        if (scanResult.SSID != null) {
                            scanResult.SSID = scanResult.SSID.replace("*", ".");
                        } else {
                            scanResult.SSID = SafeJsonPrimitive.NULL_STRING;
                        }
                        hashMap.put(Integer.valueOf((scanResult.level * 30) + i), scanResult);
                    }
                }
                TreeMap treeMap = new TreeMap(Collections.reverseOrder());
                treeMap.putAll(hashMap);
                list.clear();
                for (Entry value : treeMap.entrySet()) {
                    list.add(value.getValue());
                    if (list.size() > 29) {
                        break;
                    }
                }
                hashMap.clear();
                treeMap.clear();
            }
        }
    }

    /* renamed from: a */
    private boolean m5471a(int i) {
        int i2 = 20;
        try {
            i2 = WifiManager.calculateSignalLevel(i, 20);
        } catch (ArithmeticException e) {
            C1269v.m5728a(e);
        }
        if (i2 >= 1) {
            return true;
        }
        return false;
    }

    /* renamed from: l */
    private synchronized byte[] m5508l() {
        if (m5509m()) {
            m5499h();
            this.f4314D = C1269v.m5737b();
        }
        if (m5510n()) {
            m5513q();
        }
        return m5477a(null);
    }

    /* renamed from: m */
    private boolean m5509m() {
        if (this.f4312B || this.f4314D == 0 || C1269v.m5737b() - this.f4314D < Const.f4444k) {
            return false;
        }
        return true;
    }

    /* renamed from: n */
    private boolean m5510n() {
        if (m5517u() && this.f4315E != 0 && C1269v.m5737b() - this.f4315E >= Const.f4443j) {
            return true;
        }
        return false;
    }

    /* renamed from: o */
    private boolean m5511o() {
        if (this.f4339n == null || !m5517u()) {
            return false;
        }
        NetworkInfo networkInfo = null;
        try {
            if (this.f4338m != null) {
                networkInfo = this.f4338m.getActiveNetworkInfo();
            }
            if (NetManager.m5683a(networkInfo) == -1 || !m5474a(this.f4339n.getConnectionInfo())) {
                return false;
            }
            return true;
        } catch (SecurityException e) {
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    private AmapLoc m5464a(byte[] bArr, boolean z) throws Exception {
        if (this.f4336k == null) {
            return null;
        }
        Parser parser = new Parser();
        C1269v.m5737b();
        String a = this.f4316F.mo13281a(bArr, this.f4336k, this.f4350y);
        C1269v.m5737b();
        try {
            CoreUtil.m1459a(a);
        } catch (AMapLocException e) {
            throw e;
        } catch (Exception e2) {
        }
        String str = "";
        AmapLoc a2 = parser.mo13285a(a);
        if (C1269v.m5732a(a2)) {
            if (a2.mo13236v() != null) {
            }
            return a2;
        }
        throw new AMapLocException(AMapLocException.ERROR_UNKNOWN);
    }

    /* renamed from: b */
    private void m5483b(int i) {
        if (i == -113) {
            this.f4347v = -113;
            return;
        }
        this.f4347v = i;
        switch (this.f4337l) {
            case 1:
            case 2:
                if (this.f4342q.size() > 0) {
                    ((Cgi) this.f4342q.get(0)).f4418j = this.f4347v;
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: b */
    private Cgi m5482b(CellLocation cellLocation) {
        GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
        Cgi cgi = new Cgi();
        String[] a = C1269v.m5735a(this.f4341p);
        cgi.f4409a = a[0];
        cgi.f4410b = a[1];
        cgi.f4411c = gsmCellLocation.getLac();
        cgi.f4412d = gsmCellLocation.getCid();
        cgi.f4418j = this.f4347v;
        return cgi;
    }

    /* renamed from: a */
    private Cgi m5465a(NeighboringCellInfo neighboringCellInfo) {
        if (C1269v.m5740c() < 5) {
            return null;
        }
        try {
            Cgi cgi = new Cgi();
            String[] a = C1269v.m5735a(this.f4341p);
            cgi.f4409a = a[0];
            cgi.f4410b = a[1];
            cgi.f4411c = neighboringCellInfo.getLac();
            cgi.f4412d = neighboringCellInfo.getCid();
            cgi.f4418j = C1269v.m5724a(neighboringCellInfo.getRssi());
            return cgi;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: c */
    private void m5488c(CellLocation cellLocation) {
        if (this.f4342q != null && cellLocation != null && this.f4341p != null) {
            int i;
            this.f4342q.clear();
            GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
            if (gsmCellLocation.getLac() == -1) {
                i = 0;
            } else if (gsmCellLocation.getCid() == -1 || gsmCellLocation.getCid() == SupportMenu.USER_MASK || gsmCellLocation.getCid() >= 268435455) {
                i = 0;
            } else if (gsmCellLocation.getLac() == 0) {
                i = 0;
            } else if (gsmCellLocation.getLac() > SupportMenu.USER_MASK) {
                i = 0;
            } else if (gsmCellLocation.getCid() == 0) {
                i = 0;
            } else {
                i = 1;
            }
            if (i == 0) {
                this.f4337l = 9;
                C1269v.m5730a("case 2,gsm illegal");
                return;
            }
            this.f4337l = 1;
            List list = null;
            this.f4342q.add(m5482b(cellLocation));
            if (this.f4341p != null) {
                list = this.f4341p.getNeighboringCellInfo();
            }
            if (list != null) {
                for (NeighboringCellInfo neighboringCellInfo : list) {
                    if (neighboringCellInfo.getCid() != -1) {
                        int i2;
                        if (neighboringCellInfo.getLac() == -1) {
                            i2 = 0;
                        } else if (neighboringCellInfo.getLac() == 0) {
                            i2 = 0;
                        } else if (neighboringCellInfo.getLac() > SupportMenu.USER_MASK) {
                            i2 = 0;
                        } else if (neighboringCellInfo.getCid() == -1) {
                            i2 = 0;
                        } else if (neighboringCellInfo.getCid() == 0) {
                            i2 = 0;
                        } else if (neighboringCellInfo.getCid() == SupportMenu.USER_MASK) {
                            i2 = 0;
                        } else if (neighboringCellInfo.getCid() >= 268435455) {
                            i2 = 0;
                        } else {
                            i2 = 1;
                        }
                        if (i2 != 0) {
                            Cgi a = m5465a(neighboringCellInfo);
                            if (a != null) {
                                this.f4342q.add(a);
                            }
                        }
                    }
                }
            }
        }
    }

    /* renamed from: d */
    private void m5493d(CellLocation cellLocation) {
        this.f4342q.clear();
        if (C1269v.m5740c() >= 5) {
            try {
                CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
                if (cdmaCellLocation.getSystemId() <= 0) {
                    this.f4337l = 9;
                    C1269v.m5730a("cdma illegal");
                } else if (cdmaCellLocation.getNetworkId() < 0) {
                    this.f4337l = 9;
                    C1269v.m5730a("cdma illegal");
                } else if (cdmaCellLocation.getBaseStationId() < 0) {
                    this.f4337l = 9;
                    C1269v.m5730a("cdma illegal");
                } else {
                    this.f4337l = 2;
                    String[] a = C1269v.m5735a(this.f4341p);
                    Cgi cgi = new Cgi();
                    cgi.f4409a = a[0];
                    cgi.f4410b = a[1];
                    cgi.f4415g = cdmaCellLocation.getSystemId();
                    cgi.f4416h = cdmaCellLocation.getNetworkId();
                    cgi.f4417i = cdmaCellLocation.getBaseStationId();
                    cgi.f4418j = this.f4347v;
                    cgi.f4413e = cdmaCellLocation.getBaseStationLatitude();
                    cgi.f4414f = cdmaCellLocation.getBaseStationLongitude();
                    this.f4342q.add(cgi);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: p */
    private void m5512p() {
        this.f4343r.clear();
        this.f4349x = null;
    }

    /* renamed from: q */
    private void m5513q() {
        if (m5517u()) {
            try {
                if (this.f4339n.startScan()) {
                    this.f4315E = C1269v.m5737b();
                }
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: r */
    private boolean m5514r() {
        if (this.f4313C != 0 && C1269v.m5737b() - this.f4313C < 2000) {
            return true;
        }
        return false;
    }

    /* renamed from: d */
    public void mo13182d() {
        try {
            if (this.f4319I == null) {
                this.f4319I = C1213aa.m5277a(this.f4336k);
                this.f4319I.mo13078a(256);
            }
            if (!this.f4325O) {
                this.f4325O = true;
                this.f4319I.mo13077a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: s */
    private void m5515s() {
        if (this.f4351z != null && this.f4344s.size() >= 1) {
            Iterator it = this.f4344s.entrySet().iterator();
            while (it != null && it.hasNext()) {
                Entry entry = (Entry) it.next();
                PendingIntent pendingIntent = (PendingIntent) entry.getKey();
                List<Fence> list = (List) entry.getValue();
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                for (Fence fence : list) {
                    long a = fence.mo13274a();
                    if (a == -1 || a >= C1269v.m5737b()) {
                        float a2 = C1269v.m5723a(new double[]{fence.f4483b, fence.f4482a, this.f4351z.mo13212h(), this.f4351z.mo13210g()});
                        if (a2 < fence.f4484c) {
                            bundle.putFloat("distance", a2);
                            bundle.putString("fence", fence.mo13276b());
                            intent.putExtras(bundle);
                            try {
                                pendingIntent.send(this.f4336k, 0, intent);
                            } catch (Throwable th) {
                                th.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    /* renamed from: e */
    public void mo13183e() {
        if (this.f4351z != null && this.f4345t.size() >= 1) {
            Iterator it = this.f4345t.entrySet().iterator();
            while (it != null && it.hasNext()) {
                Entry entry = (Entry) it.next();
                PendingIntent pendingIntent = (PendingIntent) entry.getKey();
                List<Fence> list = (List) entry.getValue();
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                for (Fence fence : list) {
                    long a = fence.mo13274a();
                    if (a == -1 || a >= C1269v.m5737b()) {
                        float a2 = C1269v.m5723a(new double[]{fence.f4483b, fence.f4482a, this.f4351z.mo13212h(), this.f4351z.mo13210g()});
                        if (a2 >= fence.f4484c) {
                            if (fence.f4485d != 0) {
                                fence.f4485d = 0;
                            }
                        }
                        if (a2 < fence.f4484c) {
                            if (fence.f4485d != 1) {
                                fence.f4485d = 1;
                            }
                        }
                        bundle.putFloat("distance", a2);
                        bundle.putString("fence", fence.mo13276b());
                        bundle.putInt("status", fence.f4485d);
                        intent.putExtras(bundle);
                        try {
                            pendingIntent.send(this.f4336k, 0, intent);
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public void mo13173a(Context context, AMapLocation aMapLocation) {
        if (aMapLocation != null && this.f4345t.size() >= 1) {
            Iterator it = this.f4345t.entrySet().iterator();
            while (it != null && it.hasNext()) {
                Entry entry = (Entry) it.next();
                PendingIntent pendingIntent = (PendingIntent) entry.getKey();
                List<Fence> list = (List) entry.getValue();
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                for (Fence fence : list) {
                    long a = fence.mo13274a();
                    if (a == -1 || a >= C1269v.m5737b()) {
                        float a2 = C1269v.m5723a(new double[]{fence.f4483b, fence.f4482a, aMapLocation.getLatitude(), aMapLocation.getLongitude()});
                        if (a2 >= fence.f4484c) {
                            if (fence.f4485d != 0) {
                                fence.f4485d = 0;
                            }
                        }
                        if (a2 < fence.f4484c) {
                            if (fence.f4485d != 1) {
                                fence.f4485d = 1;
                            }
                        }
                        bundle.putFloat("distance", a2);
                        bundle.putString("fence", fence.mo13276b());
                        bundle.putInt("status", fence.f4485d);
                        intent.putExtras(bundle);
                        try {
                            pendingIntent.send(context, 0, intent);
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /* renamed from: t */
    private void m5516t() {
        switch (this.f4337l) {
            case 1:
                if (this.f4342q.size() == 0) {
                    this.f4337l = 9;
                    return;
                }
                return;
            case 2:
                if (this.f4342q.size() == 0) {
                    this.f4337l = 9;
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: u */
    private boolean m5517u() {
        boolean z = false;
        if (this.f4339n == null) {
            return z;
        }
        try {
            z = this.f4339n.isWifiEnabled();
        } catch (Exception e) {
        }
        if (z || C1269v.m5740c() <= 17) {
            return z;
        }
        try {
            return String.valueOf(Reflect.m5696a(this.f4339n, "isScanAlwaysAvailable", new Object[0])).equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        } catch (Exception e2) {
            return z;
        }
    }

    /* renamed from: v */
    private AmapLoc m5518v() throws Exception {
        return m5464a(m5508l(), false);
    }

    /* renamed from: w */
    private void m5519w() {
        if (this.f4312B) {
            this.f4337l = 9;
            this.f4342q.clear();
            return;
        }
        m5516t();
    }

    /* renamed from: a */
    public int mo13181a(boolean z, int i) {
        if (z) {
            m5487c(i);
        } else {
            m5520x();
        }
        return mo13184f() ? this.f4319I.mo13084f() : -1;
    }

    /* renamed from: c */
    private void m5487c(final int i) {
        try {
            if (C1269v.m5737b() - this.f4322L < 45000 || !mo13184f()) {
                return;
            }
            if (!mo13184f() || this.f4319I.mo13084f() >= 20) {
                m5522z();
                if (this.f4332g == null) {
                    this.f4332g = new TimerTask() {
                        public void run() {
                            try {
                                if (APS.this.m5511o()) {
                                    APS.this.m5492d(i);
                                    if (!APS.this.mo13184f()) {
                                        APS.this.m5520x();
                                        return;
                                    }
                                    return;
                                }
                                APS.this.m5520x();
                            } catch (Throwable th) {
                                th.printStackTrace();
                            }
                        }
                    };
                }
                if (this.f4333h == null) {
                    this.f4333h = new Timer(false);
                    this.f4333h.schedule(this.f4332g, PayloadController.PAYLOAD_COLLECTOR_TIMEOUT, PayloadController.PAYLOAD_COLLECTOR_TIMEOUT);
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: x */
    private void m5520x() {
        if (this.f4333h != null) {
            this.f4333h.cancel();
            this.f4333h = null;
        }
        if (this.f4332g != null) {
            this.f4332g.cancel();
            this.f4332g = null;
        }
    }

    /* renamed from: y */
    private void m5521y() {
        if (mo13184f()) {
            try {
                this.f4319I.mo13078a(768);
            } catch (Throwable th) {
                th.printStackTrace();
                C1269v.m5728a(th);
            }
        }
    }

    /* renamed from: d */
    private void m5492d(int i) {
        int i2 = 70254591;
        if (mo13184f()) {
            try {
                m5521y();
                switch (i) {
                    case 1:
                        i2 = 674234367;
                        break;
                    case 2:
                        if (!m5511o()) {
                            i2 = 674234367;
                            break;
                        } else {
                            i2 = 2083520511;
                            break;
                        }
                }
                this.f4319I.mo13079a(null, m5466a(1, i2, 1));
                this.f4334i = this.f4319I.mo13082d();
                if (this.f4334i != null) {
                    String a = this.f4316F.mo13280a(this.f4334i.mo13090a(), this.f4336k);
                    if (mo13184f()) {
                        if (TextUtils.isEmpty(a) || !a.equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) {
                            this.f4335j++;
                            this.f4319I.mo13079a(this.f4334i, m5466a(1, i2, 0));
                        } else {
                            this.f4319I.mo13079a(this.f4334i, m5466a(1, i2, 1));
                        }
                    }
                }
                m5522z();
                if (mo13184f() && this.f4319I.mo13084f() == 0) {
                    m5520x();
                } else if (this.f4335j >= 3) {
                    m5520x();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: f */
    public boolean mo13184f() {
        if (this.f4319I == null) {
            return false;
        }
        return true;
    }

    /* renamed from: z */
    private void m5522z() {
        if (mo13184f() && this.f4319I.mo13084f() <= 0) {
            try {
                if (this.f4319I.mo13083e()) {
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    private String m5466a(int i, int i2, int i3) throws Exception {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("e", i);
        jSONObject.put("d", i2);
        jSONObject.put("u", i3);
        return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
    }

    /* renamed from: A */
    private CellLocation m5460A() {
        CellLocation cellLocation = null;
        if (this.f4341p == null) {
            return cellLocation;
        }
        CellLocation cellLocation2;
        try {
            cellLocation2 = this.f4341p.getCellLocation();
        } catch (Exception e) {
            cellLocation2 = cellLocation;
        }
        if (m5495e(cellLocation2)) {
            return cellLocation2;
        }
        try {
            cellLocation2 = m5481b((List) Reflect.m5696a(this.f4341p, "getAllCellInfo", new Object[0]));
        } catch (Exception | NoSuchMethodException e2) {
        }
        if (m5495e(cellLocation2)) {
        }
        return cellLocation2;
    }

    /* JADX WARNING: Missing block: B:20:0x0043, code skipped:
            if (r6.getCid() < 268435455) goto L_0x0012;
     */
    /* JADX WARNING: Missing block: B:27:0x0065, code skipped:
            if (com.aps.Reflect.m5697b(r6, "getBaseStationId", new java.lang.Object[0]) >= 0) goto L_0x0012;
     */
    /* renamed from: e */
    private boolean m5495e(android.telephony.CellLocation r6) {
        /*
        r5 = this;
        r4 = 65535; // 0xffff float:9.1834E-41 double:3.23786E-319;
        r3 = -1;
        r0 = 0;
        if (r6 != 0) goto L_0x0008;
    L_0x0007:
        return r0;
    L_0x0008:
        r1 = 1;
        r2 = r5.f4336k;
        r2 = com.aps.C1269v.m5725a(r6, r2);
        switch(r2) {
            case 1: goto L_0x0016;
            case 2: goto L_0x0046;
            default: goto L_0x0012;
        };
    L_0x0012:
        r0 = r1;
    L_0x0013:
        if (r0 != 0) goto L_0x0007;
    L_0x0015:
        goto L_0x0007;
    L_0x0016:
        r6 = (android.telephony.gsm.GsmCellLocation) r6;
        r2 = r6.getLac();
        if (r2 == r3) goto L_0x0013;
    L_0x001e:
        r2 = r6.getLac();
        if (r2 == 0) goto L_0x0013;
    L_0x0024:
        r2 = r6.getLac();
        if (r2 > r4) goto L_0x0013;
    L_0x002a:
        r2 = r6.getCid();
        if (r2 == r3) goto L_0x0013;
    L_0x0030:
        r2 = r6.getCid();
        if (r2 == 0) goto L_0x0013;
    L_0x0036:
        r2 = r6.getCid();
        if (r2 == r4) goto L_0x0013;
    L_0x003c:
        r2 = r6.getCid();
        r3 = 268435455; // 0xfffffff float:2.5243547E-29 double:1.326247364E-315;
        if (r2 < r3) goto L_0x0012;
    L_0x0045:
        goto L_0x0013;
    L_0x0046:
        r2 = "getSystemId";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Exception -> 0x0068 }
        r2 = com.aps.Reflect.m5697b(r6, r2, r3);	 Catch:{ Exception -> 0x0068 }
        if (r2 <= 0) goto L_0x0013;
    L_0x0051:
        r2 = "getNetworkId";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Exception -> 0x0068 }
        r2 = com.aps.Reflect.m5697b(r6, r2, r3);	 Catch:{ Exception -> 0x0068 }
        if (r2 < 0) goto L_0x0013;
    L_0x005c:
        r2 = "getBaseStationId";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Exception -> 0x0068 }
        r2 = com.aps.Reflect.m5697b(r6, r2, r3);	 Catch:{ Exception -> 0x0068 }
        if (r2 >= 0) goto L_0x0012;
    L_0x0067:
        goto L_0x0013;
    L_0x0068:
        r0 = move-exception;
        r0 = r1;
        goto L_0x0013;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aps.APS.m5495e(android.telephony.CellLocation):boolean");
    }

    /* renamed from: b */
    private CellLocation m5481b(List<?> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        int i;
        CellLocation cdmaCellLocation;
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        CellLocation cellLocation = null;
        int i2 = 0;
        CellLocation cellLocation2 = null;
        for (int i3 = 0; i3 < list.size(); i3++) {
            Object obj = list.get(i3);
            if (obj != null) {
                try {
                    Class loadClass = systemClassLoader.loadClass("android.telephony.CellInfoGsm");
                    Class loadClass2 = systemClassLoader.loadClass("android.telephony.CellInfoWcdma");
                    Class loadClass3 = systemClassLoader.loadClass("android.telephony.CellInfoLte");
                    Class loadClass4 = systemClassLoader.loadClass("android.telephony.CellInfoCdma");
                    if (loadClass.isInstance(obj)) {
                        i = 1;
                    } else if (loadClass2.isInstance(obj)) {
                        i = 2;
                    } else if (loadClass3.isInstance(obj)) {
                        i = 3;
                    } else if (loadClass4.isInstance(obj)) {
                        i = 4;
                    } else {
                        i = 0;
                    }
                    if (i > 0) {
                        Object obj2 = null;
                        if (i == 1) {
                            try {
                                obj2 = loadClass.cast(obj);
                            } catch (Exception e) {
                                i2 = i;
                            }
                        } else if (i == 2) {
                            obj2 = loadClass2.cast(obj);
                        } else if (i == 3) {
                            obj2 = loadClass3.cast(obj);
                        } else if (i == 4) {
                            obj2 = loadClass4.cast(obj);
                        }
                        obj = Reflect.m5696a(obj2, "getCellIdentity", new Object[0]);
                        int b;
                        int b2;
                        CellLocation cellLocation3;
                        if (obj == null) {
                            i2 = i;
                        } else if (i == 4) {
                            cdmaCellLocation = new CdmaCellLocation();
                            try {
                                int b3 = Reflect.m5697b(obj, "getSystemId", new Object[0]);
                                int b4 = Reflect.m5697b(obj, "getNetworkId", new Object[0]);
                                cdmaCellLocation.setCellLocationData(Reflect.m5697b(obj, "getBasestationId", new Object[0]), Reflect.m5697b(obj, "getLatitude", new Object[0]), Reflect.m5697b(obj, "getLongitude", new Object[0]), b3, b4);
                                cellLocation2 = cellLocation;
                                break;
                            } catch (Exception e2) {
                                cellLocation2 = cdmaCellLocation;
                                i2 = i;
                            }
                        } else if (i == 3) {
                            b = Reflect.m5697b(obj, "getTac", new Object[0]);
                            b2 = Reflect.m5697b(obj, "getCi", new Object[0]);
                            cdmaCellLocation = new GsmCellLocation();
                            try {
                                cdmaCellLocation.setLacAndCid(b, b2);
                                cellLocation3 = cellLocation2;
                                cellLocation2 = cdmaCellLocation;
                                cdmaCellLocation = cellLocation3;
                                break;
                            } catch (Exception e3) {
                                cellLocation = cdmaCellLocation;
                                i2 = i;
                            }
                        } else {
                            b = Reflect.m5697b(obj, "getLac", new Object[0]);
                            b2 = Reflect.m5697b(obj, "getCid", new Object[0]);
                            cdmaCellLocation = new GsmCellLocation();
                            try {
                                cdmaCellLocation.setLacAndCid(b, b2);
                                cellLocation3 = cellLocation2;
                                cellLocation2 = cdmaCellLocation;
                                cdmaCellLocation = cellLocation3;
                                break;
                            } catch (Exception e4) {
                                cellLocation = cdmaCellLocation;
                                i2 = i;
                            }
                        }
                    } else {
                        i2 = i;
                    }
                } catch (Exception e5) {
                }
            }
        }
        i = i2;
        cdmaCellLocation = cellLocation2;
        cellLocation2 = cellLocation;
        if (i != 4) {
            return cellLocation2;
        }
        return cdmaCellLocation;
    }

    /* renamed from: B */
    private void m5461B() {
        try {
            if (!this.f4328c && AuthManager.m1415h() && this.f4326a != null) {
                this.f4326a.mo13251b();
                this.f4328c = true;
            }
        } catch (Throwable th) {
            this.f4328c = true;
        }
    }
}
