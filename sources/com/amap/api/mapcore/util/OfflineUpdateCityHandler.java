package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import com.mcdonalds.sdk.connectors.autonavi.AutoNavi.Parameters;
import com.mcdonalds.sdk.connectors.autonavi.AutoNaviConnector;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

/* renamed from: com.amap.api.mapcore.util.bp */
public class OfflineUpdateCityHandler extends ProtocalHandler<String, List<OfflineMapProvince>> {
    /* renamed from: j */
    private Context f1422j;

    public OfflineUpdateCityHandler(Context context, String str) {
        super(context, str);
        getClass();
        mo8902a(5000);
        getClass();
        mo8906b(AutoNaviConnector.DEFAULT_SEARCH_RADIUS);
    }

    /* renamed from: a */
    public void mo8943a(Context context) {
        this.f1422j = context;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public List<OfflineMapProvince> mo8911b(byte[] bArr) throws AMapException {
        List<OfflineMapProvince> arrayList = new ArrayList();
        try {
            String str = new String(bArr, "utf-8");
            Util.m2359a(str);
            if (str == null || "".equals(str)) {
                return arrayList;
            }
            String optString = JSONObjectInstrumentation.init(str).optString("status");
            if (optString == null || optString.equals("") || optString.equals("0")) {
                return arrayList;
            }
            return mo8910b(str);
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "OfflineUpdateCityHandler", "loadData jsonInit");
            th.printStackTrace();
            return arrayList;
        }
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:31:0x0083=Splitter:B:31:0x0083, B:22:0x006b=Splitter:B:22:0x006b} */
    /* JADX WARNING: Removed duplicated region for block: B:53:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0078 A:{SYNTHETIC, Splitter:B:25:0x0078} */
    /* JADX WARNING: Removed duplicated region for block: B:55:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0090 A:{SYNTHETIC, Splitter:B:34:0x0090} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x009f A:{SYNTHETIC, Splitter:B:41:0x009f} */
    /* renamed from: c */
    private void m1925c(java.lang.String r7) {
        /*
        r6 = this;
        r0 = r6.f1422j;
        r0 = com.amap.api.mapcore.util.Util.m2369b(r0);
        r1 = "";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x000f;
    L_0x000e:
        return;
    L_0x000f:
        r0 = new java.io.File;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = r6.f1422j;
        r2 = com.amap.api.mapcore.util.Util.m2369b(r2);
        r1 = r1.append(r2);
        r2 = "offlinemapv4.png";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        r1 = r0.exists();
        if (r1 != 0) goto L_0x0036;
    L_0x0033:
        r0.createNewFile();	 Catch:{ IOException -> 0x005c }
    L_0x0036:
        r2 = r6.mo8944b_();
        r4 = 1048576; // 0x100000 float:1.469368E-39 double:5.180654E-318;
        r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r1 <= 0) goto L_0x000e;
    L_0x0041:
        r2 = 0;
        r1 = new java.io.FileOutputStream;	 Catch:{ FileNotFoundException -> 0x0069, IOException -> 0x0081, all -> 0x009b }
        r1.<init>(r0);	 Catch:{ FileNotFoundException -> 0x0069, IOException -> 0x0081, all -> 0x009b }
        r0 = "utf-8";
        r0 = r7.getBytes(r0);	 Catch:{ FileNotFoundException -> 0x00ac, IOException -> 0x00aa }
        r1.write(r0);	 Catch:{ FileNotFoundException -> 0x00ac, IOException -> 0x00aa }
        if (r1 == 0) goto L_0x000e;
    L_0x0053:
        r1.close();	 Catch:{ IOException -> 0x0057 }
        goto L_0x000e;
    L_0x0057:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x000e;
    L_0x005c:
        r1 = move-exception;
        r2 = "OfflineUpdateCityHandler";
        r3 = "writeSD dirCreate";
        com.amap.api.mapcore.util.SDKLogHandler.m2563a(r1, r2, r3);
        r1.printStackTrace();
        goto L_0x0036;
    L_0x0069:
        r0 = move-exception;
        r1 = r2;
    L_0x006b:
        r2 = "OfflineUpdateCityHandler";
        r3 = "writeSD filenotfound";
        com.amap.api.mapcore.util.SDKLogHandler.m2563a(r0, r2, r3);	 Catch:{ all -> 0x00a8 }
        r0.printStackTrace();	 Catch:{ all -> 0x00a8 }
        if (r1 == 0) goto L_0x000e;
    L_0x0078:
        r1.close();	 Catch:{ IOException -> 0x007c }
        goto L_0x000e;
    L_0x007c:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x000e;
    L_0x0081:
        r0 = move-exception;
        r1 = r2;
    L_0x0083:
        r2 = "OfflineUpdateCityHandler";
        r3 = "writeSD io";
        com.amap.api.mapcore.util.SDKLogHandler.m2563a(r0, r2, r3);	 Catch:{ all -> 0x00a8 }
        r0.printStackTrace();	 Catch:{ all -> 0x00a8 }
        if (r1 == 0) goto L_0x000e;
    L_0x0090:
        r1.close();	 Catch:{ IOException -> 0x0095 }
        goto L_0x000e;
    L_0x0095:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x000e;
    L_0x009b:
        r0 = move-exception;
        r1 = r2;
    L_0x009d:
        if (r1 == 0) goto L_0x00a2;
    L_0x009f:
        r1.close();	 Catch:{ IOException -> 0x00a3 }
    L_0x00a2:
        throw r0;
    L_0x00a3:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00a2;
    L_0x00a8:
        r0 = move-exception;
        goto L_0x009d;
    L_0x00aa:
        r0 = move-exception;
        goto L_0x0083;
    L_0x00ac:
        r0 = move-exception;
        goto L_0x006b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.OfflineUpdateCityHandler.m1925c(java.lang.String):void");
    }

    /* renamed from: b_ */
    public long mo8944b_() {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return 0;
        }
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
    }

    /* renamed from: a */
    public String mo8901a() {
        return "http://restapi.amap.com/v3/config/resource";
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public List<OfflineMapProvince> mo8910b(String str) throws AMapException {
        List<OfflineMapProvince> list = null;
        try {
            if (this.f1422j != null) {
                m1925c(str);
            }
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "OfflineUpdateCityHandler", "loadData jsonInit");
            th.printStackTrace();
        }
        try {
            return Utility.m2183b(str);
        } catch (JSONException th2) {
            SDKLogHandler.m2563a(th2, "OfflineUpdateCityHandler", "loadData parseJson");
            th2.printStackTrace();
            return list;
        }
    }

    /* renamed from: b */
    public Map<String, String> mo8905b() {
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(MapsInitializer.KEY)) {
            C0811dm.m2391a(MapsInitializer.KEY);
        }
        hashMap.put(Parameters.API_KEY, AppInfo.m2387f(this.f1422j));
        hashMap.put("opertype", "offlinemap_with_province_vfour");
        hashMap.put("plattype", "android");
        hashMap.put("product", ConfigableConst.f2122b);
        hashMap.put("version", "3.3.2");
        hashMap.put("ext", "standard");
        hashMap.put("output", "json");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("key=").append(AppInfo.m2387f(this.f1422j));
        stringBuffer.append("&opertype=offlinemap_with_province_vfour");
        stringBuffer.append("&plattype=android");
        stringBuffer.append("&product=").append(ConfigableConst.f2122b);
        stringBuffer.append("&version=").append("3.3.2");
        stringBuffer.append("&ext=standard");
        stringBuffer.append("&output=json");
        String d = Utils.m2521d(stringBuffer.toString());
        String a = ClientInfo.m2396a();
        hashMap.put("ts", a);
        hashMap.put("scode", ClientInfo.m2401a(this.f1422j, a, d));
        return hashMap;
    }
}
