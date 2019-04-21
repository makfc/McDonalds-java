package com.baidu.android.pushservice.p033e;

import android.content.Context;
import com.autonavi.amap.mapcore.MapTilsCacheAndResManager;
import com.baidu.android.pushservice.p036h.C1425a;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.android.pushservice.e.y */
public class C1392y extends C1367d {
    /* renamed from: e */
    protected String f4885e = null;

    public C1392y(C1378l c1378l, Context context, String str) {
        super(c1378l, context);
        this.f4885e = str;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo13722a(HashMap<String, String> hashMap) {
        super.mo13722a((HashMap) hashMap);
        hashMap.put("method", "sendmsgtoserver");
        if (this.f4885e != null) {
            try {
                JSONObject init = JSONObjectInstrumentation.init(this.f4885e);
                if (init.has("to")) {
                    hashMap.put("cb_url", init.getString("to"));
                    C1425a.m6442c("Send", init.getString("to"));
                }
                if (init.has(MapTilsCacheAndResManager.AUTONAVI_DATA_PATH)) {
                    hashMap.put("cb_data", init.getString(MapTilsCacheAndResManager.AUTONAVI_DATA_PATH));
                }
            } catch (JSONException e) {
                C1425a.m6444e("Send", "error " + e.getMessage());
            }
            C1425a.m6442c("Send", "send param -- " + C1370b.m6202a((HashMap) hashMap));
        }
    }
}
