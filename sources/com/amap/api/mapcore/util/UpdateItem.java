package com.amap.api.mapcore.util;

import android.content.Context;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import com.facebook.internal.NativeProtocol;
import com.mcdonalds.sdk.modules.models.TenderType;
import com.mcdonalds.sdk.modules.notification.PushConstants;
import com.newrelic.agent.android.agentdata.HexAttributes;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONException;
import org.json.JSONObject;

@EntityClass(a = "update_item", b = true)
/* renamed from: com.amap.api.mapcore.util.bs */
public class UpdateItem extends DTInfo {
    /* renamed from: m */
    private String f1439m = "";
    /* renamed from: n */
    private Context f1440n;

    public UpdateItem(OfflineMapCity offlineMapCity, Context context) {
        this.f1440n = context;
        this.f1427a = offlineMapCity.getCity();
        this.f1429c = offlineMapCity.getAdcode();
        this.f1428b = offlineMapCity.getUrl();
        this.f1433g = offlineMapCity.getSize();
        mo8959a();
        this.f1431e = offlineMapCity.getVersion();
        this.f1437k = offlineMapCity.getCode();
        this.f1435i = 0;
        this.f1438l = offlineMapCity.getState();
        this.f1436j = offlineMapCity.getcompleteCode();
    }

    public UpdateItem(OfflineMapProvince offlineMapProvince, Context context) {
        this.f1440n = context;
        this.f1427a = offlineMapProvince.getProvinceName();
        this.f1429c = offlineMapProvince.getProvinceCode();
        this.f1428b = offlineMapProvince.getUrl();
        this.f1433g = offlineMapProvince.getSize();
        mo8959a();
        this.f1431e = offlineMapProvince.getVersion();
        this.f1435i = 1;
        this.f1438l = offlineMapProvince.getState();
        this.f1436j = offlineMapProvince.getcompleteCode();
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo8959a() {
        this.f1430d = Util.m2369b(this.f1440n) + this.f1429c + ".zip" + ".tmp";
    }

    /* renamed from: b */
    public void mo8961b() {
        this.f1438l = 6;
        mo8950a(0);
        mo8951a(0);
    }

    /* renamed from: c */
    public String mo8963c() {
        return this.f1439m;
    }

    /* renamed from: a */
    public void mo8960a(String str) {
        this.f1439m = str;
    }

    /* renamed from: b */
    public void mo8962b(String str) {
        if (str != null) {
            try {
                if (!str.equals("")) {
                    JSONObject jSONObject = JSONObjectInstrumentation.init(str).getJSONObject("file");
                    if (jSONObject != null) {
                        this.f1427a = jSONObject.optString(PushConstants.TITLE_KEY);
                        this.f1429c = jSONObject.optString(TenderType.COLUMN_CODE);
                        this.f1428b = jSONObject.optString(NativeProtocol.IMAGE_URL_KEY);
                        this.f1430d = jSONObject.optString(HexAttributes.HEX_ATTR_FILENAME);
                        this.f1432f = jSONObject.optLong("lLocalLength");
                        this.f1433g = jSONObject.optLong("lRemoteLength");
                        this.f1438l = jSONObject.optInt("mState");
                        this.f1431e = jSONObject.optString("version");
                        this.f1434h = jSONObject.optString("localPath");
                        this.f1439m = jSONObject.optString("vMapFileNames");
                        this.f1435i = jSONObject.optInt("isSheng");
                        this.f1436j = jSONObject.optInt("mCompleteCode");
                        this.f1437k = jSONObject.optString("mCityCode");
                    }
                }
            } catch (JSONException e) {
                SDKLogHandler.m2563a(e, "UpdateItem", "readFileToJSONObject");
                e.printStackTrace();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x00e1 A:{SYNTHETIC, Splitter:B:37:0x00e1} */
    /* renamed from: d */
    public void mo8964d() {
        /*
        r6 = this;
        r0 = new org.json.JSONObject;
        r0.<init>();
        r1 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00ba }
        r1.<init>();	 Catch:{ JSONException -> 0x00ba }
        r2 = "title";
        r3 = r6.f1427a;	 Catch:{ JSONException -> 0x00ba }
        r1.put(r2, r3);	 Catch:{ JSONException -> 0x00ba }
        r2 = "code";
        r3 = r6.f1429c;	 Catch:{ JSONException -> 0x00ba }
        r1.put(r2, r3);	 Catch:{ JSONException -> 0x00ba }
        r2 = "url";
        r3 = r6.f1428b;	 Catch:{ JSONException -> 0x00ba }
        r1.put(r2, r3);	 Catch:{ JSONException -> 0x00ba }
        r2 = "fileName";
        r3 = r6.f1430d;	 Catch:{ JSONException -> 0x00ba }
        r1.put(r2, r3);	 Catch:{ JSONException -> 0x00ba }
        r2 = "lLocalLength";
        r4 = r6.f1432f;	 Catch:{ JSONException -> 0x00ba }
        r1.put(r2, r4);	 Catch:{ JSONException -> 0x00ba }
        r2 = "lRemoteLength";
        r4 = r6.f1433g;	 Catch:{ JSONException -> 0x00ba }
        r1.put(r2, r4);	 Catch:{ JSONException -> 0x00ba }
        r2 = "mState";
        r3 = r6.f1438l;	 Catch:{ JSONException -> 0x00ba }
        r1.put(r2, r3);	 Catch:{ JSONException -> 0x00ba }
        r2 = "version";
        r3 = r6.f1431e;	 Catch:{ JSONException -> 0x00ba }
        r1.put(r2, r3);	 Catch:{ JSONException -> 0x00ba }
        r2 = "localPath";
        r3 = r6.f1434h;	 Catch:{ JSONException -> 0x00ba }
        r1.put(r2, r3);	 Catch:{ JSONException -> 0x00ba }
        r2 = r6.f1439m;	 Catch:{ JSONException -> 0x00ba }
        if (r2 == 0) goto L_0x0057;
    L_0x004f:
        r2 = "vMapFileNames";
        r3 = r6.f1439m;	 Catch:{ JSONException -> 0x00ba }
        r1.put(r2, r3);	 Catch:{ JSONException -> 0x00ba }
    L_0x0057:
        r2 = "isSheng";
        r3 = r6.f1435i;	 Catch:{ JSONException -> 0x00ba }
        r1.put(r2, r3);	 Catch:{ JSONException -> 0x00ba }
        r2 = "mCompleteCode";
        r3 = r6.f1436j;	 Catch:{ JSONException -> 0x00ba }
        r1.put(r2, r3);	 Catch:{ JSONException -> 0x00ba }
        r2 = "mCityCode";
        r3 = r6.f1437k;	 Catch:{ JSONException -> 0x00ba }
        r1.put(r2, r3);	 Catch:{ JSONException -> 0x00ba }
        r2 = "file";
        r0.put(r2, r1);	 Catch:{ JSONException -> 0x00ba }
        r3 = new java.io.File;	 Catch:{ JSONException -> 0x00ba }
        r1 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x00ba }
        r1.<init>();	 Catch:{ JSONException -> 0x00ba }
        r2 = r6.f1430d;	 Catch:{ JSONException -> 0x00ba }
        r1 = r1.append(r2);	 Catch:{ JSONException -> 0x00ba }
        r2 = ".dt";
        r1 = r1.append(r2);	 Catch:{ JSONException -> 0x00ba }
        r1 = r1.toString();	 Catch:{ JSONException -> 0x00ba }
        r3.<init>(r1);	 Catch:{ JSONException -> 0x00ba }
        r3.delete();	 Catch:{ JSONException -> 0x00ba }
        r2 = 0;
        r1 = new java.io.OutputStreamWriter;	 Catch:{ IOException -> 0x00c6, all -> 0x00dd }
        r4 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x00c6, all -> 0x00dd }
        r5 = 1;
        r4.<init>(r3, r5);	 Catch:{ IOException -> 0x00c6, all -> 0x00dd }
        r3 = "utf-8";
        r1.<init>(r4, r3);	 Catch:{ IOException -> 0x00c6, all -> 0x00dd }
        r2 = r0 instanceof org.json.JSONObject;	 Catch:{ IOException -> 0x00ec }
        if (r2 != 0) goto L_0x00ae;
    L_0x00a1:
        r0 = r0.toString();	 Catch:{ IOException -> 0x00ec }
    L_0x00a5:
        r1.write(r0);	 Catch:{ IOException -> 0x00ec }
        if (r1 == 0) goto L_0x00ad;
    L_0x00aa:
        r1.close();	 Catch:{ IOException -> 0x00b5 }
    L_0x00ad:
        return;
    L_0x00ae:
        r0 = (org.json.JSONObject) r0;	 Catch:{ IOException -> 0x00ec }
        r0 = com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation.toString(r0);	 Catch:{ IOException -> 0x00ec }
        goto L_0x00a5;
    L_0x00b5:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ JSONException -> 0x00ba }
        goto L_0x00ad;
    L_0x00ba:
        r0 = move-exception;
        r1 = "UpdateItem";
        r2 = "saveJSONObjectToFile parseJson";
        com.amap.api.mapcore.util.SDKLogHandler.m2563a(r0, r1, r2);
        r0.printStackTrace();
        goto L_0x00ad;
    L_0x00c6:
        r0 = move-exception;
        r1 = r2;
    L_0x00c8:
        r2 = "UpdateItem";
        r3 = "saveJSONObjectToFile";
        com.amap.api.mapcore.util.SDKLogHandler.m2563a(r0, r2, r3);	 Catch:{ all -> 0x00ea }
        r0.printStackTrace();	 Catch:{ all -> 0x00ea }
        if (r1 == 0) goto L_0x00ad;
    L_0x00d4:
        r1.close();	 Catch:{ IOException -> 0x00d8 }
        goto L_0x00ad;
    L_0x00d8:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ JSONException -> 0x00ba }
        goto L_0x00ad;
    L_0x00dd:
        r0 = move-exception;
        r1 = r2;
    L_0x00df:
        if (r1 == 0) goto L_0x00e4;
    L_0x00e1:
        r1.close();	 Catch:{ IOException -> 0x00e5 }
    L_0x00e4:
        throw r0;	 Catch:{ JSONException -> 0x00ba }
    L_0x00e5:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ JSONException -> 0x00ba }
        goto L_0x00e4;
    L_0x00ea:
        r0 = move-exception;
        goto L_0x00df;
    L_0x00ec:
        r0 = move-exception;
        goto L_0x00c8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.UpdateItem.mo8964d():void");
    }
}
