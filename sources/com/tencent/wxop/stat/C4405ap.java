package com.tencent.wxop.stat;

import android.content.Context;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import com.tencent.wxop.stat.p069a.C4386j;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;

/* renamed from: com.tencent.wxop.stat.ap */
class C4405ap implements Runnable {
    /* renamed from: a */
    private Context f7030a = null;
    /* renamed from: b */
    private Map<String, Integer> f7031b = null;
    /* renamed from: c */
    private StatSpecifyReportedInfo f7032c = null;

    public C4405ap(Context context, Map<String, Integer> map, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f7030a = context;
        this.f7032c = statSpecifyReportedInfo;
        if (map != null) {
            this.f7031b = map;
        }
    }

    /* renamed from: a */
    private NetworkMonitor m8011a(String str, int i) {
        Throwable th;
        NetworkMonitor networkMonitor = new NetworkMonitor();
        Socket socket = new Socket();
        int i2 = 0;
        try {
            networkMonitor.setDomain(str);
            networkMonitor.setPort(i);
            long currentTimeMillis = System.currentTimeMillis();
            InetSocketAddress inetSocketAddress = new InetSocketAddress(str, i);
            socket.connect(inetSocketAddress, 30000);
            networkMonitor.setMillisecondsConsume(System.currentTimeMillis() - currentTimeMillis);
            networkMonitor.setRemoteIp(inetSocketAddress.getAddress().getHostAddress());
            socket.close();
            try {
                socket.close();
            } catch (Throwable th2) {
                StatServiceImpl.f6934q.mo33949e(th2);
            }
        } catch (IOException e) {
            th2 = e;
            i2 = -1;
            StatServiceImpl.f6934q.mo33949e(th2);
            socket.close();
        } catch (Throwable th22) {
            StatServiceImpl.f6934q.mo33949e(th22);
        }
        networkMonitor.setStatusCode(i2);
        return networkMonitor;
    }

    /* renamed from: a */
    private Map<String, Integer> m8012a() {
        HashMap hashMap = new HashMap();
        String a = StatConfig.m7918a("__MTA_TEST_SPEED__", null);
        if (!(a == null || a.trim().length() == 0)) {
            for (String a2 : a2.split(";")) {
                String[] split = a2.split(",");
                if (split != null && split.length == 2) {
                    String str = split[0];
                    if (!(str == null || str.trim().length() == 0)) {
                        try {
                            hashMap.put(str, Integer.valueOf(Integer.valueOf(split[1]).intValue()));
                        } catch (NumberFormatException e) {
                            StatServiceImpl.f6934q.mo33949e(e);
                        }
                    }
                }
            }
        }
        return hashMap;
    }

    public void run() {
        try {
            if (this.f7031b == null) {
                this.f7031b = m8012a();
            }
            if (this.f7031b == null || this.f7031b.size() == 0) {
                StatServiceImpl.f6934q.mo33952i("empty domain list.");
                return;
            }
            JSONArray jSONArray = new JSONArray();
            for (Entry entry : this.f7031b.entrySet()) {
                String str = (String) entry.getKey();
                if (str == null || str.length() == 0) {
                    StatServiceImpl.f6934q.mo33956w("empty domain name.");
                } else if (((Integer) entry.getValue()) == null) {
                    StatServiceImpl.f6934q.mo33956w("port is null for " + str);
                } else {
                    jSONArray.put(m8011a((String) entry.getKey(), ((Integer) entry.getValue()).intValue()).toJSONObject());
                }
            }
            if (jSONArray.length() != 0) {
                C4386j c4386j = new C4386j(this.f7030a, StatServiceImpl.m7935a(this.f7030a, false, this.f7032c), this.f7032c);
                c4386j.mo33897a(!(jSONArray instanceof JSONArray) ? jSONArray.toString() : JSONArrayInstrumentation.toString(jSONArray));
                new C4406aq(c4386j).mo33922a();
            }
        } catch (Throwable th) {
            StatServiceImpl.f6934q.mo33949e(th);
        }
    }
}
