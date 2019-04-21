package com.tencent.wxop.stat;

import android.content.Context;
import android.content.IntentFilter;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.tencent.wxop.stat.common.C4427e;
import com.tencent.wxop.stat.common.C4433k;
import com.tencent.wxop.stat.common.C4439q;
import com.tencent.wxop.stat.common.StatLogger;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import org.apache.http.HttpHost;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.a */
public class C4389a {
    /* renamed from: g */
    private static C4389a f6989g = null;
    /* renamed from: a */
    private List<String> f6990a = null;
    /* renamed from: b */
    private volatile int f6991b = 2;
    /* renamed from: c */
    private volatile String f6992c = "";
    /* renamed from: d */
    private volatile HttpHost f6993d = null;
    /* renamed from: e */
    private C4427e f6994e = null;
    /* renamed from: f */
    private int f6995f = 0;
    /* renamed from: h */
    private Context f6996h = null;
    /* renamed from: i */
    private StatLogger f6997i = null;

    private C4389a(Context context) {
        this.f6996h = context.getApplicationContext();
        this.f6994e = new C4427e();
        C4445i.m8178a(context);
        this.f6997i = C4433k.m8111b();
        m8001l();
        m7998i();
        mo33905g();
    }

    /* renamed from: a */
    public static C4389a m7995a(Context context) {
        if (f6989g == null) {
            synchronized (C4389a.class) {
                if (f6989g == null) {
                    f6989g = new C4389a(context);
                }
            }
        }
        return f6989g;
    }

    /* renamed from: b */
    private boolean m7997b(String str) {
        return Pattern.compile("(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})").matcher(str).matches();
    }

    /* renamed from: i */
    private void m7998i() {
        this.f6990a = new ArrayList(10);
        this.f6990a.add("117.135.169.101");
        this.f6990a.add("140.207.54.125");
        this.f6990a.add("180.153.8.53");
        this.f6990a.add("120.198.203.175");
        this.f6990a.add("14.17.43.18");
        this.f6990a.add("163.177.71.186");
        this.f6990a.add("111.30.131.31");
        this.f6990a.add("123.126.121.167");
        this.f6990a.add("123.151.152.111");
        this.f6990a.add("113.142.45.79");
        this.f6990a.add("123.138.162.90");
        this.f6990a.add("103.7.30.94");
    }

    /* renamed from: j */
    private String m7999j() {
        try {
            String str = "pingma.qq.com";
            if (!m7997b(str)) {
                return InetAddress.getByName(str).getHostAddress();
            }
        } catch (Exception e) {
            this.f6997i.mo33949e(e);
        }
        return "";
    }

    /* renamed from: k */
    private void m8000k() {
        String j = m7999j();
        if (StatConfig.isDebugEnable()) {
            this.f6997i.mo33952i("remoteIp ip is " + j);
        }
        if (C4433k.m8115c(j)) {
            String str;
            if (this.f6990a.contains(j)) {
                str = j;
            } else {
                str = (String) this.f6990a.get(this.f6995f);
                if (StatConfig.isDebugEnable()) {
                    this.f6997i.mo33956w(j + " not in ip list, change to:" + str);
                }
            }
            StatConfig.setStatReportUrl("http://" + str + ":80/mstat/report");
        }
    }

    /* renamed from: l */
    private void m8001l() {
        this.f6991b = 0;
        this.f6993d = null;
        this.f6992c = null;
    }

    /* renamed from: a */
    public HttpHost mo33898a() {
        return this.f6993d;
    }

    /* renamed from: a */
    public void mo33899a(String str) {
        if (StatConfig.isDebugEnable()) {
            this.f6997i.mo33952i("updateIpList " + str);
        }
        try {
            if (C4433k.m8115c(str)) {
                JSONObject init = JSONObjectInstrumentation.init(str);
                if (init.length() > 0) {
                    Iterator keys = init.keys();
                    while (keys.hasNext()) {
                        String string = init.getString((String) keys.next());
                        if (C4433k.m8115c(string)) {
                            for (String str2 : string.split(";")) {
                                String str22;
                                if (C4433k.m8115c(str22)) {
                                    String[] split = str22.split(":");
                                    if (split.length > 1) {
                                        str22 = split[0];
                                        if (m7997b(str22) && !this.f6990a.contains(str22)) {
                                            if (StatConfig.isDebugEnable()) {
                                                this.f6997i.mo33952i("add new ip:" + str22);
                                            }
                                            this.f6990a.add(str22);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            this.f6997i.mo33949e(e);
        }
        this.f6995f = new Random().nextInt(this.f6990a.size());
    }

    /* renamed from: b */
    public String mo33900b() {
        return this.f6992c;
    }

    /* renamed from: c */
    public int mo33901c() {
        return this.f6991b;
    }

    /* renamed from: d */
    public void mo33902d() {
        this.f6995f = (this.f6995f + 1) % this.f6990a.size();
    }

    /* renamed from: e */
    public boolean mo33903e() {
        return this.f6991b == 1;
    }

    /* renamed from: f */
    public boolean mo33904f() {
        return this.f6991b != 0;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: g */
    public void mo33905g() {
        if (C4439q.m8166f(this.f6996h)) {
            if (StatConfig.f6893g) {
                m8000k();
            }
            this.f6992c = C4433k.m8129l(this.f6996h);
            if (StatConfig.isDebugEnable()) {
                this.f6997i.mo33952i("NETWORK name:" + this.f6992c);
            }
            if (C4433k.m8115c(this.f6992c)) {
                if ("WIFI".equalsIgnoreCase(this.f6992c)) {
                    this.f6991b = 1;
                } else {
                    this.f6991b = 2;
                }
                this.f6993d = C4433k.m8106a(this.f6996h);
            }
            if (StatServiceImpl.m7942a()) {
                StatServiceImpl.m7952d(this.f6996h);
                return;
            }
            return;
        }
        if (StatConfig.isDebugEnable()) {
            this.f6997i.mo33952i("NETWORK TYPE: network is close.");
        }
        m8001l();
    }

    /* renamed from: h */
    public void mo33906h() {
        this.f6996h.getApplicationContext().registerReceiver(new C4417b(this), new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }
}
