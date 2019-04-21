package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.MapsInitializer;
import com.mcdonalds.sdk.connectors.autonavi.AutoNavi.Parameters;
import com.mcdonalds.sdk.connectors.autonavi.AutoNaviConnector;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.amap.api.mapcore.util.bk */
public class OfflineInitHandler extends ProtocalHandler<String, OfflineInitBean> {
    public OfflineInitHandler(Context context, String str) {
        super(context, str);
        getClass();
        mo8902a(5000);
        getClass();
        mo8906b(AutoNaviConnector.DEFAULT_SEARCH_RADIUS);
    }

    /* renamed from: a */
    public String mo8901a() {
        return "http://restapi.amap.com/v3/config/version";
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public OfflineInitBean mo8910b(String str) throws AMapException {
        OfflineInitBean offlineInitBean = new OfflineInitBean();
        try {
            JSONObject init = JSONObjectInstrumentation.init(str);
            if (init.has("offlinemap")) {
                init = init.getJSONObject("offlinemap");
                String optString = init.optString("update", "");
                if (optString.equals("0")) {
                    offlineInitBean.mo8899a(false);
                } else if (optString.equals("1")) {
                    offlineInitBean.mo8899a(true);
                }
                offlineInitBean.mo8898a(init.optString("version", ""));
            }
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "OfflineInitHandler", "loadData parseJson");
        }
        return offlineInitBean;
    }

    /* renamed from: b */
    public Map<String, String> mo8905b() {
        HashMap hashMap = new HashMap();
        hashMap.put("mapver", this.f1400a);
        hashMap.put("output", "json");
        if (!TextUtils.isEmpty(MapsInitializer.KEY)) {
            C0811dm.m2391a(MapsInitializer.KEY);
        }
        hashMap.put(Parameters.API_KEY, AppInfo.m2387f(this.f1403d));
        hashMap.put("opertype", "offlinemap_with_province_vfour");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("mapver=").append((String) this.f1400a);
        stringBuffer.append("&output=json");
        stringBuffer.append("&key=").append(AppInfo.m2387f(this.f1403d));
        stringBuffer.append("&opertype=offlinemap_with_province_vfour");
        String d = Utils.m2521d(stringBuffer.toString());
        String a = ClientInfo.m2396a();
        hashMap.put("ts", a);
        hashMap.put("scode", ClientInfo.m2401a(this.f1403d, a, d));
        return hashMap;
    }
}
