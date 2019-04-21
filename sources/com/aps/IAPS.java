package com.aps;

import android.app.PendingIntent;
import android.content.Context;
import com.amap.api.location.AMapLocation;
import org.json.JSONObject;

/* renamed from: com.aps.l */
public interface IAPS {
    /* renamed from: a */
    AmapLoc mo13170a() throws Exception;

    /* renamed from: a */
    void mo13171a(PendingIntent pendingIntent);

    /* renamed from: a */
    void mo13172a(Context context);

    /* renamed from: a */
    void mo13173a(Context context, AMapLocation aMapLocation);

    /* renamed from: a */
    void mo13174a(Fence fence, PendingIntent pendingIntent);

    /* renamed from: a */
    void mo13175a(String str);

    /* renamed from: a */
    void mo13176a(JSONObject jSONObject);

    /* renamed from: b */
    void mo13177b();

    /* renamed from: b */
    void mo13178b(PendingIntent pendingIntent);

    /* renamed from: b */
    void mo13179b(Fence fence, PendingIntent pendingIntent);

    /* renamed from: c */
    void mo13180c();
}
