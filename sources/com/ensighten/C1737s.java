package com.ensighten;

import android.os.Message;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* renamed from: com.ensighten.s */
public class C1737s extends C1730q {
    /* renamed from: a */
    public void mo15090a(JSONObject jSONObject) {
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: a */
    public final void mo15071a(int i, Header[] headerArr, String str) {
        if (i != 204) {
            try {
                Object a = C1737s.m7292a(str);
                mo15078b(mo15069a(100, (Object) new Object[]{Integer.valueOf(i), headerArr, a}));
                return;
            } catch (JSONException e) {
                mo15074a((Throwable) e, str);
                return;
            }
        }
        mo15078b(mo15069a(100, (Object) new Object[]{Integer.valueOf(i), headerArr, new JSONObject()}));
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: a */
    public final void mo15072a(Message message) {
        switch (message.what) {
            case 100:
                Object[] objArr = (Object[]) message.obj;
                ((Integer) objArr[0]).intValue();
                Header[] headerArr = (Header[]) objArr[1];
                Object obj = objArr[2];
                if (obj instanceof JSONObject) {
                    mo15090a((JSONObject) obj);
                    return;
                } else if (!(obj instanceof JSONArray)) {
                    JSONException jSONException = new JSONException("Unexpected type " + obj.getClass().getName());
                    return;
                } else {
                    return;
                }
            default:
                super.mo15072a(message);
                return;
        }
    }

    /* renamed from: a */
    private static Object m7292a(String str) throws JSONException {
        Object obj = null;
        String trim = str.trim();
        if (trim.startsWith("{") || trim.startsWith("[")) {
            obj = new JSONTokener(trim).nextValue();
        }
        return obj == null ? trim : obj;
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: b */
    public final void mo15080b(Throwable th, String str) {
        if (str != null) {
            try {
                Object a = C1737s.m7292a(str);
                if (!(a instanceof JSONObject) && !(a instanceof JSONArray)) {
                    mo15079b(th);
                    return;
                }
                return;
            } catch (JSONException e) {
                mo15079b(th);
                return;
            }
        }
        mo15079b(th);
    }
}
