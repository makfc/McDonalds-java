package com.tencent.wxop.stat.p069a;

import android.content.Context;
import com.tencent.wxop.stat.StatServiceImpl;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import java.util.Map.Entry;
import java.util.Properties;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.a.b */
public class C4379b extends C4377e {
    /* renamed from: a */
    protected C4380c f6956a = new C4380c();
    /* renamed from: m */
    private long f6957m = -1;

    public C4379b(Context context, int i, String str, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        this.f6956a.f6958a = str;
    }

    /* renamed from: h */
    private void m7973h() {
        if (this.f6956a.f6958a != null) {
            Properties commonKeyValueForKVEvent = StatServiceImpl.getCommonKeyValueForKVEvent(this.f6956a.f6958a);
            if (commonKeyValueForKVEvent != null && commonKeyValueForKVEvent.size() > 0) {
                if (this.f6956a.f6960c == null || this.f6956a.f6960c.length() == 0) {
                    this.f6956a.f6960c = new JSONObject(commonKeyValueForKVEvent);
                    return;
                }
                for (Entry entry : commonKeyValueForKVEvent.entrySet()) {
                    try {
                        this.f6956a.f6960c.put(entry.getKey().toString(), entry.getValue());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public C4382f mo33883a() {
        return C4382f.CUSTOM;
    }

    /* renamed from: a */
    public void mo33891a(long j) {
        this.f6957m = j;
    }

    /* renamed from: a */
    public boolean mo33884a(JSONObject jSONObject) {
        jSONObject.put("ei", this.f6956a.f6958a);
        if (this.f6957m > 0) {
            jSONObject.put("du", this.f6957m);
        }
        if (this.f6956a.f6959b == null) {
            m7973h();
            jSONObject.put("kv", this.f6956a.f6960c);
        } else {
            jSONObject.put("ar", this.f6956a.f6959b);
        }
        return true;
    }

    /* renamed from: b */
    public C4380c mo33892b() {
        return this.f6956a;
    }
}
