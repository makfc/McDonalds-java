package com.ensighten;

import android.os.Build;
import android.os.Build.VERSION;
import com.facebook.internal.ServerProtocol;
import org.json.JSONObject;

/* renamed from: com.ensighten.b */
public final class C1744b {
    /* renamed from: a */
    public JSONObject f5833a = new JSONObject();
    /* renamed from: b */
    public JSONObject f5834b = new JSONObject();

    public C1744b(String str, String str2) {
        try {
            this.f5833a.put("udid", str);
            this.f5833a.put("macHash", str2);
            this.f5833a.put("board", Build.BOARD);
            this.f5833a.put("brand", Build.BRAND);
            this.f5833a.put("cpu_abi", Build.CPU_ABI);
            this.f5833a.put("device", Build.DEVICE);
            this.f5833a.put(ServerProtocol.DIALOG_PARAM_DISPLAY, Build.DISPLAY);
            this.f5833a.put("fingerprint", Build.FINGERPRINT);
            this.f5833a.put("host", Build.HOST);
            this.f5833a.put("id", Build.ID);
            this.f5833a.put("mfg", Build.MANUFACTURER);
            this.f5833a.put("model", Build.MODEL);
            this.f5833a.put("product", Build.PRODUCT);
            this.f5833a.put("tags", Build.TAGS);
            this.f5833a.put("time", Build.TIME);
            this.f5833a.put("type", Build.TYPE);
            this.f5833a.put("user", Build.USER);
            this.f5834b.put("codename", VERSION.CODENAME);
            this.f5834b.put("incremental", VERSION.INCREMENTAL);
            this.f5834b.put("release", VERSION.RELEASE);
            this.f5834b.put("sdk_int", VERSION.SDK_INT);
        } catch (Exception e) {
            if (C1845i.m7363j()) {
                C1845i.m7353c(e);
            }
        }
    }
}
