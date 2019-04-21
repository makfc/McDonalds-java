package com.ensighten;

import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.ArrayList;
import org.json.JSONException;

/* renamed from: com.ensighten.A */
public final class C1685A extends WebChromeClient {
    /* renamed from: a */
    C1693E f5585a;

    public C1685A(C1693E c1693e) {
        this.f5585a = c1693e;
    }

    public final boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        if (message.equals("opcode")) {
            ArrayList arrayList = new ArrayList();
            try {
                arrayList.add(new C1692D(JSONObjectInstrumentation.init(defaultValue)));
            } catch (JSONException e) {
                if (C1845i.m7365l()) {
                    C1845i.m7353c(e);
                }
            } catch (OutOfMemoryError e2) {
                if (C1845i.m7365l()) {
                    C1845i.m7356d(e2.getMessage());
                }
            }
            try {
                result.confirm(this.f5585a.mo15020a(arrayList, false));
                return true;
            } catch (OutOfMemoryError e22) {
                if (C1845i.m7365l()) {
                    C1845i.m7356d(e22.getMessage());
                }
            }
        }
        return super.onJsPrompt(view, url, message, defaultValue, result);
    }
}
