package com.alipay.sdk.authjs;

import com.facebook.internal.ServerProtocol;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.alipay.sdk.authjs.f */
class C0610f extends TimerTask {
    /* renamed from: a */
    final /* synthetic */ C0606a f564a;
    /* renamed from: b */
    final /* synthetic */ C0608d f565b;

    C0610f(C0608d c0608d, C0606a c0606a) {
        this.f565b = c0608d;
        this.f564a = c0606a;
    }

    public void run() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("toastCallBack", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        } catch (JSONException e) {
        }
        C0606a c0606a = new C0606a("callback");
        c0606a.mo8037a(this.f564a.mo8039b());
        c0606a.mo8038a(jSONObject);
        this.f565b.f560a.mo8031a(c0606a);
    }
}
