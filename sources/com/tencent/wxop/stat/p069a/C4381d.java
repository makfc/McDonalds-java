package com.tencent.wxop.stat.p069a;

import android.content.Context;
import com.tencent.wxop.stat.StatConfig;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import com.tencent.wxop.stat.common.C4424b;
import com.tencent.wxop.stat.common.C4439q;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.a.d */
public class C4381d extends C4377e {
    /* renamed from: a */
    private String f6961a;
    /* renamed from: m */
    private int f6962m;
    /* renamed from: n */
    private int f6963n = 100;
    /* renamed from: o */
    private Thread f6964o = null;

    public C4381d(Context context, int i, int i2, Throwable th, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        m7978a(i2, th);
    }

    public C4381d(Context context, int i, int i2, Throwable th, Thread thread, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        m7978a(i2, th);
        this.f6964o = thread;
    }

    public C4381d(Context context, int i, String str, int i2, int i3, Thread thread, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        if (str != null) {
            if (i3 <= 0) {
                i3 = StatConfig.getMaxReportEventLength();
            }
            if (str.length() <= i3) {
                this.f6961a = str;
            } else {
                this.f6961a = str.substring(0, i3);
            }
        }
        this.f6964o = thread;
        this.f6962m = i2;
    }

    /* renamed from: a */
    private void m7978a(int i, Throwable th) {
        if (th != null) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th.printStackTrace(printWriter);
            this.f6961a = stringWriter.toString();
            this.f6962m = i;
            printWriter.close();
        }
    }

    /* renamed from: a */
    public C4382f mo33883a() {
        return C4382f.ERROR;
    }

    /* renamed from: a */
    public boolean mo33884a(JSONObject jSONObject) {
        C4439q.m8159a(jSONObject, "er", this.f6961a);
        jSONObject.put("ea", this.f6962m);
        if (this.f6962m == 2 || this.f6962m == 3) {
            new C4424b(this.f6954l).mo33964a(jSONObject, this.f6964o);
        }
        return true;
    }
}
