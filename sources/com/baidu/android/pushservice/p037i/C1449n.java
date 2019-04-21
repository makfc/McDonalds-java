package com.baidu.android.pushservice.p037i;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.internal.NativeProtocol;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.android.pushservice.i.n */
public class C1449n extends C1439e {
    /* renamed from: a */
    public static int f5116a = 0;
    /* renamed from: b */
    public static int f5117b = 1;
    /* renamed from: c */
    private int f5118c = 0;

    public C1449n(String str) {
        super(str);
    }

    /* renamed from: a */
    public JSONObject mo13917a(Context context) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("app_type", this.f5118c);
        if (!TextUtils.isEmpty(mo13860d())) {
            jSONObject.put("app_package_name", mo13860d());
        }
        if (!TextUtils.isEmpty(mo13862e())) {
            jSONObject.put(NativeProtocol.BRIDGE_ARG_APP_NAME_STRING, mo13862e());
        }
        if (!TextUtils.isEmpty(mo13864f())) {
            jSONObject.put("app_cfrom", mo13864f());
        }
        if (mo13866g() != -1) {
            jSONObject.put("app_vercode", mo13866g());
        }
        if (!TextUtils.isEmpty(mo13868h())) {
            jSONObject.put("app_vername", mo13868h());
        }
        if (mo13869i() != -1) {
            jSONObject.put("app_push_version", mo13869i());
        }
        jSONObject.put("app_appid", mo13852a());
        if (!TextUtils.isEmpty(mo13855b())) {
            jSONObject.put("user_id_rsa", mo13855b());
        }
        if (!TextUtils.isEmpty(mo13858c())) {
            jSONObject.put("user_id", mo13858c());
        }
        return jSONObject;
    }

    /* renamed from: c */
    public void mo13918c(int i) {
        this.f5118c = i;
    }

    /* renamed from: j */
    public int mo13919j() {
        return this.f5118c;
    }
}
