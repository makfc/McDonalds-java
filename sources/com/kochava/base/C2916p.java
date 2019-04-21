package com.kochava.base;

import android.support.annotation.NonNull;
import com.facebook.internal.ServerProtocol;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.kochava.base.p */
final class C2916p extends C2907i {
    @NonNull
    /* renamed from: b */
    private final String f6653b;
    /* renamed from: c */
    private final boolean f6654c;
    /* renamed from: d */
    private final boolean f6655d;

    C2916p(@NonNull C2906h c2906h, @NonNull String str, boolean z, boolean z2) {
        super(c2906h, true);
        this.f6653b = str;
        this.f6654c = z;
        this.f6655d = z2;
    }

    public final void run() {
        String str = "run";
        Tracker.m7517a(4, "TPT", "run", new Object[0]);
        if (!this.f6655d) {
            Object a = C2901d.m7628a(this.f6629a.f6611d.mo26572c("push_token"));
            Object b = C2901d.m7648b(this.f6629a.f6611d.mo26572c("push_token_enable"));
            if (C2901d.m7643a(this.f6653b, a) && C2901d.m7643a(Boolean.valueOf(this.f6654c), b)) {
                Tracker.m7517a(4, "TPT", "run", "Skip");
                return;
            }
        }
        this.f6629a.f6611d.mo26565a("push_token", this.f6653b);
        this.f6629a.f6611d.mo26565a("push_token_enable", Boolean.valueOf(this.f6654c));
        this.f6629a.f6611d.mo26565a("push_token_sent", Boolean.valueOf(false));
        if (C2901d.m7644a(this.f6629a.f6611d.mo26572c("push"), false)) {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put(ServerProtocol.DIALOG_RESPONSE_TYPE_TOKEN, this.f6653b);
            } catch (JSONException e) {
                Tracker.m7517a(2, "TPT", "run", e);
            }
            mo26607a(this.f6654c ? 9 : 10, jSONObject, jSONObject2);
            this.f6629a.f6611d.mo26574c(jSONObject);
            this.f6629a.f6611d.mo26565a("push_token_sent", Boolean.valueOf(true));
            if (this.f6629a.f6616i.mo26533k()) {
                mo26621k();
            }
            Tracker.m7517a(4, "TPT", "run", "Complete");
            return;
        }
        Tracker.m7517a(4, "TPT", "run", "Push Disabled: Skip");
    }
}
