package com.baidu.android.pushservice.p033e;

import android.content.Context;
import android.text.TextUtils;
import com.autonavi.amap.mapcore.MapTilsCacheAndResManager;
import com.baidu.android.pushservice.C1457i;
import com.baidu.android.pushservice.p026j.C1281c;
import com.baidu.android.pushservice.p034f.C1402a;
import com.baidu.android.pushservice.p034f.C1403b;
import com.baidu.android.pushservice.p035g.C1410c;
import com.baidu.android.pushservice.p035g.C1419f;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p037i.p038a.C1432b;
import com.mcdonalds.sdk.modules.notification.PushConstants;
import com.newrelic.agent.android.agentdata.HexAttributes;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.InputStream;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.android.pushservice.e.s */
public class C1386s extends C1281c {
    /* renamed from: a */
    private final String f4849a = (C1457i.f5138c + "/lightapp/infosbykey/%s");
    /* renamed from: b */
    private final String f4850b = (C1457i.f5138c + "/lightapp/infos/%s");
    /* renamed from: c */
    private Context f4851c;
    /* renamed from: d */
    private C1410c f4852d;
    /* renamed from: e */
    private int f4853e = 3;
    /* renamed from: f */
    private int f4854f = 0;
    /* renamed from: g */
    private boolean f4855g = false;
    /* renamed from: h */
    private C1385a f4856h;

    /* renamed from: com.baidu.android.pushservice.e.s$a */
    public interface C1385a {
        /* renamed from: a */
        void mo13734a(boolean z, C1410c c1410c);
    }

    public C1386s(Context context, String str, String str2, C1385a c1385a) {
        this.f4851c = context.getApplicationContext();
        this.f4856h = c1385a;
        this.f4852d = new C1410c(str, str2);
        mo13488a((short) 80);
        mo13489c("GetLightappInfoRequester");
    }

    /* renamed from: a */
    private boolean m6224a(String str) {
        try {
            JSONObject init = JSONObjectInstrumentation.init(str);
            if (init.isNull(HexAttributes.HEX_ATTR_THREAD_STATE)) {
                return false;
            }
            if (init.getInt(HexAttributes.HEX_ATTR_THREAD_STATE) == 0) {
                return false;
            }
            JSONObject jSONObject = init.getJSONArray(MapTilsCacheAndResManager.AUTONAVI_DATA_PATH).getJSONObject(0);
            String string = jSONObject.getString(PushConstants.TITLE_KEY);
            String string2 = jSONObject.getString("id");
            String string3 = jSONObject.getString("api_key");
            init = jSONObject.getJSONObject("logo");
            if (init != null) {
                JSONObject jSONObject2 = init.getJSONObject("thumbnails");
                if (jSONObject2 != null) {
                    this.f4852d.mo13783f(jSONObject2.getString("L"));
                    this.f4852d.mo13784g(jSONObject2.getString("M"));
                    this.f4852d.mo13786h(jSONObject2.getString("S"));
                    this.f4852d.mo13787i(jSONObject2.getString("XL"));
                    int i = this.f4851c.getResources().getDisplayMetrics().densityDpi;
                    switch (i) {
                        case 120:
                            this.f4852d.mo13779d(jSONObject2.getString("S"));
                            break;
                        case 160:
                            this.f4852d.mo13779d(jSONObject2.getString("M"));
                            break;
                        case 240:
                            this.f4852d.mo13779d(jSONObject2.getString("L"));
                            break;
                        default:
                            if (i <= 240) {
                                this.f4852d.mo13779d(jSONObject2.getString("S"));
                                break;
                            }
                            this.f4852d.mo13779d(jSONObject2.getString("XL"));
                            break;
                    }
                }
            }
            this.f4852d.mo13775b(string2);
            this.f4852d.mo13777c(string3);
            this.f4852d.mo13771a(string);
            this.f4852d.mo13781e(!(init instanceof JSONObject) ? init.toString() : JSONObjectInstrumentation.toString(init));
            this.f4852d.mo13772a(jSONObject.getBoolean("allow_shortcut"));
            return true;
        } catch (JSONException e) {
            C1425a.m6441b("GetLightAppInfoRequester", "GetLightAppInfo e : " + e);
            return false;
        }
    }

    /* renamed from: c */
    private boolean m6225c() {
        String format;
        InputStream inputStream = null;
        if (TextUtils.isEmpty(this.f4852d.mo13776c())) {
            format = String.format(this.f4850b, new Object[]{this.f4852d.mo13774b()});
        } else {
            format = String.format(this.f4849a, new Object[]{this.f4852d.mo13776c()});
        }
        C1425a.m6442c("GetLightAppInfoRequester", "getLightAppInfo request url:" + format);
        try {
            C1402a a = C1403b.m6259a(format, "GET", null);
            inputStream = a.mo13742a();
            if (a == null || a.mo13745b() != 200 || inputStream == null) {
                this.f4854f = 0;
                this.f4855g = false;
                C1403b.m6265a(inputStream);
                return false;
            }
            format = C1432b.m6481a(a.mo13742a());
            C1425a.m6441b("GetLightAppInfoRequester", "getLightAppInfo return string :  " + format);
            boolean a2 = m6224a(format);
            C1403b.m6265a(inputStream);
            return a2;
        } catch (Exception e) {
            C1425a.m6444e("GetLightAppInfoRequester", "error " + e.getMessage());
            this.f4855g = true;
            C1403b.m6265a(inputStream);
        } catch (Throwable th) {
            C1403b.m6265a(inputStream);
        }
    }

    /* renamed from: e */
    private void m6226e() {
        this.f4854f++;
        if (this.f4854f < this.f4853e) {
            int i = ((1 << (this.f4854f - 1)) * 5) * 1000;
            C1425a.m6441b("GetLightAppInfoRequester", "schedule retry-- retry times: " + this.f4854f + "time delay: " + i);
            try {
                Thread.sleep((long) i);
                return;
            } catch (InterruptedException e) {
                C1425a.m6444e("GetLightAppInfoRequester", "error : " + e.getMessage());
                return;
            }
        }
        C1425a.m6441b("GetLightAppInfoRequester", "hava reconnect " + this.f4853e + " times, all failed.");
        this.f4855g = false;
    }

    /* renamed from: a */
    public void mo13487a() {
        mo13736b();
    }

    /* renamed from: a */
    public void mo13735a(int i) {
        this.f4853e = i;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public void mo13736b() {
        boolean c;
        do {
            c = m6225c();
            if (c && this.f4852d.mo13770a() == null) {
                this.f4852d.mo13773a(C1419f.m6412a(this.f4851c, this.f4852d.mo13776c(), this.f4852d.mo13780e(), false));
            }
            if (this.f4855g) {
                m6226e();
            }
            if (this.f4856h != null) {
                this.f4853e = 0;
                this.f4856h.mo13734a(c, this.f4852d);
            }
            if (this.f4853e <= 0) {
                break;
            }
        } while (this.f4855g);
        C1425a.m6441b("GetLightAppInfoRequester", "GetLightAppInfoRequester connectResult: " + c);
    }
}
