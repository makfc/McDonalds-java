package com.alipay.sdk.authjs;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;
import com.alipay.sdk.authjs.C0606a.C0605a;
import com.kochava.base.InstallReferrer;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.Timer;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.alipay.sdk.authjs.d */
public class C0608d {
    /* renamed from: a */
    private C0597c f560a;
    /* renamed from: b */
    private Context f561b;

    public C0608d(Context context, C0597c c0597c) {
        this.f561b = context;
        this.f560a = c0597c;
    }

    /* renamed from: a */
    public void mo8047a(String str) {
        CharSequence charSequence;
        try {
            JSONObject init = JSONObjectInstrumentation.init(str);
            String string = init.getString("clientId");
            try {
                if (!TextUtils.isEmpty(string)) {
                    JSONObject jSONObject = init.getJSONObject("param");
                    if (jSONObject instanceof JSONObject) {
                        jSONObject = jSONObject;
                    } else {
                        jSONObject = null;
                    }
                    String string2 = init.getString("func");
                    String string3 = init.getString("bundleName");
                    C0606a c0606a = new C0606a("call");
                    c0606a.mo8040b(string3);
                    c0606a.mo8041c(string2);
                    c0606a.mo8038a(jSONObject);
                    c0606a.mo8037a(string);
                    mo8046a(c0606a);
                }
            } catch (Exception e) {
                charSequence = string;
                if (!TextUtils.isEmpty(charSequence)) {
                    try {
                        m856a(charSequence, C0605a.RUNTIME_ERROR, true);
                    } catch (JSONException e2) {
                    }
                }
            }
        } catch (Exception e3) {
            charSequence = null;
        }
    }

    /* renamed from: a */
    public void mo8046a(C0606a c0606a) throws JSONException {
        if (c0606a != null) {
            if (TextUtils.isEmpty(c0606a.mo8042d())) {
                m856a(c0606a.mo8039b(), C0605a.INVALID_PARAMETER, true);
            } else {
                C0608d.m855a(new C0609e(this, c0606a));
            }
        }
    }

    /* renamed from: a */
    private void m856a(String str, C0605a c0605a, boolean z) throws JSONException {
        if (!TextUtils.isEmpty(str)) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("error", c0605a.ordinal());
            C0606a c0606a = new C0606a("callback");
            c0606a.mo8038a(jSONObject);
            c0606a.mo8037a(str);
            if (z) {
                this.f560a.mo8031a(c0606a);
            } else {
                mo8046a(c0606a);
            }
        }
    }

    /* renamed from: a */
    private static void m855a(Runnable runnable) {
        if (runnable != null) {
            if ((Looper.getMainLooper() == Looper.myLooper() ? 1 : null) != null) {
                runnable.run();
            } else {
                new Handler(Looper.getMainLooper()).post(runnable);
            }
        }
    }

    /* renamed from: b */
    private C0605a m857b(C0606a c0606a) {
        if (c0606a != null && "toast".equals(c0606a.mo8042d())) {
            m858c(c0606a);
        }
        return C0605a.NONE_ERROR;
    }

    /* renamed from: c */
    private void m858c(C0606a c0606a) {
        JSONObject f = c0606a.mo8044f();
        String optString = f.optString("content");
        int optInt = f.optInt(InstallReferrer.KEY_DURATION);
        int i = 1;
        if (optInt < 2500) {
            i = 0;
        }
        Toast.makeText(this.f561b, optString, i).show();
        new Timer().schedule(new C0610f(this, c0606a), (long) i);
    }
}
