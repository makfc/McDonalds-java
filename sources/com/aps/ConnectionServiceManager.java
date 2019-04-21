package com.aps;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import com.amap.api.location.core.Base64Util;
import com.amap.api.location.core.ClientInfoUtil;
import com.amap.api.location.core.Encrypt;
import com.aps.ILocationProviderService.C1211a;
import com.facebook.stetho.common.Utf8Charset;

/* renamed from: com.aps.f */
public class ConnectionServiceManager {
    /* renamed from: a */
    C1241a f4421a = null;
    /* renamed from: b */
    private String f4422b = null;
    /* renamed from: c */
    private Context f4423c = null;
    /* renamed from: d */
    private boolean f4424d = true;
    /* renamed from: e */
    private ILocationProviderService f4425e = null;
    /* renamed from: f */
    private ServiceConnection f4426f = null;
    /* renamed from: g */
    private Intent f4427g = new Intent();
    /* renamed from: h */
    private String f4428h = "com.autonavi.minimap";
    /* renamed from: i */
    private String f4429i = "com.amap.api.service.AMapService";
    /* renamed from: j */
    private String f4430j = "invaid type";
    /* renamed from: k */
    private String f4431k = "empty appkey";
    /* renamed from: l */
    private String f4432l = "refused";
    /* renamed from: m */
    private String f4433m = "failed";

    /* compiled from: ConnectionServiceManager */
    /* renamed from: com.aps.f$a */
    public interface C1241a {
        /* renamed from: a */
        void mo13168a(int i);
    }

    ConnectionServiceManager(Context context) {
        this.f4423c = context;
        try {
            this.f4422b = Base64Util.m1419a(Encrypt.m1473b(ClientInfoUtil.f910a.getBytes(Utf8Charset.NAME), "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDCEYwdO3V2ANrhApjqyk7X8FH5AEaWly58kP9IDAhMqwtIbmcJrUK9oO9Afh3KZnOlDtjiowy733YqpLRO7WBvdbW/c4Dz/d3dy/m+6HMqxaak+GQQRHw/VPdKciaZ3eIZp4MWOyIQwiFSQvPTAo/Na8hV4SgBZHB3lGFw0yu+BmG+h32eIE6p4Y8EDCn+G+yzekX+taMrWTQIysledrygZSGPv1ukbdFDnH/xZEI0dCr9pZT+AZQl3o9a2aMyuRrHM0oupXKKiYl69Y8fKh1Tyd752rF6LrR5uOb9aOfXt18hb+3YL5P9rQ+ZRYbyHYFaxzBPA2jLq0KUQ+Dmg7YhAgMBAAECggEAL9pj0lF3BUHwtssNKdf42QZJMD0BKuDcdZrLV9ifs0f54EJY5enzKw8j76MpdV8N5QVkNX4/BZR0bs9uJogh31oHFs5EXeWbb7V8P7bRrxpNnSAijGBWwscQsyqymf48YlcL28949ujnjoEz3jQjgWOyYnrCgpVhphrQbCGmB5TcZnTFvHfozt/0tzuMj5na5lRnkD0kYXgr0x/SRZcPoCybSpc3t/B/9MAAboGaV/QQkTotr7VOuJfaPRjvg8rzyPzavo3evxsjXj7vDXbN4w0cbk/Uqn2JtvPQ8HoysmF2HdYvILZibvJmWH1hA58b4sn5s6AqFRjMOL7rHdD+gQKBgQD+IzoofmZK5tTxgO9sWsG71IUeshQP9fe159jKCehk1RfuIqqbRP0UcxJiw4eNjHs4zU0HeRL3iF5XfUs0FQanO/pp6YL1xgVdfQlDdTdk6KFHJ0sUJapnJn1S2k7IKfRKE1+rkofSXMYUTsgHF1fDp+gxy4yUMY+h9O+JlKVKOwKBgQDDfaDIblaSm+B0lyG//wFPynAeGd0Q8wcMZbQQ/LWMJZhMZ7fyUZ+A6eL/jB53a2tgnaw2rXBpMe1qu8uSpym2plU0fkgLAnVugS5+KRhOkUHyorcbpVZbs5azf7GlTydR5dI1PHF3Bncemoa6IsEvumHWgQbVyTTz/O9mlFafUwKBgQCvDebms8KUf5JY1F6XfaCLWGVl8nZdVCmQFKbA7Lg2lI5KS3jHQWsupeEZRORffU/3nXsc1apZ9YY+r6CYvI77rRXd1KqPzxos/o7d96TzjkZhc9CEjTlmmh2jb5rqx/Ns/xFcZq/GGH+cx3ODZvHeZQ9NFY+9GLJ+dfB2DX0ZtwKBgQC+9/lZ8telbpqMqpqwqRaJ8LMn5JIdHZu0E6IcuhFLr+ogMW3zTKMpVtGGXEXi2M/TWRPDchiO2tQX4Q5T2/KW19QCbJ5KCwPWiGF3owN4tNOciDGh0xkSidRc0xAh8bnyejSoBry8zlcNUVztdkgMLOGonvCjZWPSOTNQnPYluwKBgCV+WVftpTk3l+OfAJTaXEPNYdh7+WQjzxZKjUaDzx80Ts7hRo2U+EQT7FBjQQNqmmDnWtujo5p1YmJC0FT3n1CVa7g901pb3b0RcOziYWAoJi0/+kLyeo6XBhuLeZ7h90S70GGh1o0V/j/9N1jb5DCL4xKkvdYePPTSTku0BM+n"));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public void mo13249a() {
        mo13252c();
        this.f4423c = null;
        this.f4425e = null;
        this.f4426f = null;
        if (this.f4421a != null) {
            this.f4421a.mo13168a(-1);
        }
        this.f4424d = true;
    }

    /* renamed from: a */
    public void mo13250a(final C1241a c1241a) {
        try {
            this.f4421a = c1241a;
            if (this.f4426f == null) {
                this.f4426f = new ServiceConnection() {
                    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                        ConnectionServiceManager.this.f4425e = C1211a.m5267a(iBinder);
                        c1241a.mo13168a(0);
                    }

                    public void onServiceDisconnected(ComponentName componentName) {
                        ConnectionServiceManager.this.f4425e = null;
                    }
                };
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: b */
    public boolean mo13251b() {
        try {
            this.f4427g.setComponent(new ComponentName(this.f4428h, this.f4429i));
            this.f4427g.putExtra("appkey", this.f4422b);
            return this.f4423c.bindService(this.f4427g, this.f4426f, 1);
        } catch (Exception e) {
            return false;
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: c */
    public void mo13252c() {
        try {
            this.f4423c.unbindService(this.f4426f);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Throwable th) {
        }
        this.f4425e = null;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: d */
    public AmapLoc mo13253d() {
        try {
            if (!this.f4424d) {
                return null;
            }
            Bundle bundle = new Bundle();
            bundle.putString("type", "corse");
            bundle.putString("appkey", this.f4422b);
            this.f4425e.mo13074a(bundle);
            if (bundle.size() >= 1) {
                return m5619a(bundle);
            }
            return null;
        } catch (Throwable th) {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:109:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0026  */
    /* renamed from: a */
    private com.aps.AmapLoc m5619a(android.os.Bundle r10) {
        /*
        r9 = this;
        r2 = 0;
        r1 = 0;
        if (r10 != 0) goto L_0x0006;
    L_0x0005:
        return r1;
    L_0x0006:
        r0 = "key";
        r0 = r10.containsKey(r0);
        if (r0 == 0) goto L_0x0080;
    L_0x000e:
        r0 = "key";
        r0 = r10.getString(r0);
        r0 = com.amap.api.location.core.Base64Util.m1420a(r0);
        r4 = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDCEYwdO3V2ANrhApjqyk7X8FH5AEaWly58kP9IDAhMqwtIbmcJrUK9oO9Afh3KZnOlDtjiowy733YqpLRO7WBvdbW/c4Dz/d3dy/m+6HMqxaak+GQQRHw/VPdKciaZ3eIZp4MWOyIQwiFSQvPTAo/Na8hV4SgBZHB3lGFw0yu+BmG+h32eIE6p4Y8EDCn+G+yzekX+taMrWTQIysledrygZSGPv1ukbdFDnH/xZEI0dCr9pZT+AZQl3o9a2aMyuRrHM0oupXKKiYl69Y8fKh1Tyd752rF6LrR5uOb9aOfXt18hb+3YL5P9rQ+ZRYbyHYFaxzBPA2jLq0KUQ+Dmg7YhAgMBAAECggEAL9pj0lF3BUHwtssNKdf42QZJMD0BKuDcdZrLV9ifs0f54EJY5enzKw8j76MpdV8N5QVkNX4/BZR0bs9uJogh31oHFs5EXeWbb7V8P7bRrxpNnSAijGBWwscQsyqymf48YlcL28949ujnjoEz3jQjgWOyYnrCgpVhphrQbCGmB5TcZnTFvHfozt/0tzuMj5na5lRnkD0kYXgr0x/SRZcPoCybSpc3t/B/9MAAboGaV/QQkTotr7VOuJfaPRjvg8rzyPzavo3evxsjXj7vDXbN4w0cbk/Uqn2JtvPQ8HoysmF2HdYvILZibvJmWH1hA58b4sn5s6AqFRjMOL7rHdD+gQKBgQD+IzoofmZK5tTxgO9sWsG71IUeshQP9fe159jKCehk1RfuIqqbRP0UcxJiw4eNjHs4zU0HeRL3iF5XfUs0FQanO/pp6YL1xgVdfQlDdTdk6KFHJ0sUJapnJn1S2k7IKfRKE1+rkofSXMYUTsgHF1fDp+gxy4yUMY+h9O+JlKVKOwKBgQDDfaDIblaSm+B0lyG//wFPynAeGd0Q8wcMZbQQ/LWMJZhMZ7fyUZ+A6eL/jB53a2tgnaw2rXBpMe1qu8uSpym2plU0fkgLAnVugS5+KRhOkUHyorcbpVZbs5azf7GlTydR5dI1PHF3Bncemoa6IsEvumHWgQbVyTTz/O9mlFafUwKBgQCvDebms8KUf5JY1F6XfaCLWGVl8nZdVCmQFKbA7Lg2lI5KS3jHQWsupeEZRORffU/3nXsc1apZ9YY+r6CYvI77rRXd1KqPzxos/o7d96TzjkZhc9CEjTlmmh2jb5rqx/Ns/xFcZq/GGH+cx3ODZvHeZQ9NFY+9GLJ+dfB2DX0ZtwKBgQC+9/lZ8telbpqMqpqwqRaJ8LMn5JIdHZu0E6IcuhFLr+ogMW3zTKMpVtGGXEXi2M/TWRPDchiO2tQX4Q5T2/KW19QCbJ5KCwPWiGF3owN4tNOciDGh0xkSidRc0xAh8bnyejSoBry8zlcNUVztdkgMLOGonvCjZWPSOTNQnPYluwKBgCV+WVftpTk3l+OfAJTaXEPNYdh7+WQjzxZKjUaDzx80Ts7hRo2U+EQT7FBjQQNqmmDnWtujo5p1YmJC0FT3n1CVa7g901pb3b0RcOziYWAoJi0/+kLyeo6XBhuLeZ7h90S70GGh1o0V/j/9N1jb5DCL4xKkvdYePPTSTku0BM+n";
        r0 = com.amap.api.location.core.Encrypt.m1476c(r0, r4);	 Catch:{ Exception -> 0x007c }
    L_0x001e:
        r4 = "result";
        r4 = r10.containsKey(r4);
        if (r4 == 0) goto L_0x0005;
    L_0x0026:
        r4 = "result";
        r4 = r10.getString(r4);
        r4 = com.amap.api.location.core.Base64Util.m1420a(r4);
        r0 = com.amap.api.location.core.Encrypt.m1469a(r0, r4);	 Catch:{ Exception -> 0x01e7 }
        r4 = new java.lang.String;	 Catch:{ Exception -> 0x01e7 }
        r5 = "utf-8";
        r4.<init>(r0, r5);	 Catch:{ Exception -> 0x01e7 }
        if (r4 == 0) goto L_0x0005;
    L_0x003e:
        r0 = new org.json.JSONObject;	 Catch:{ Exception -> 0x01e7 }
        r8 = com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation.init(r4);	 Catch:{ Exception -> 0x01e7 }
        r0 = "error";
        r0 = r8.has(r0);	 Catch:{ Exception -> 0x01e7 }
        if (r0 == 0) goto L_0x0082;
    L_0x004c:
        r0 = "error";
        r0 = r8.getString(r0);	 Catch:{ Exception -> 0x01e7 }
        r2 = r9.f4430j;	 Catch:{ Exception -> 0x01e7 }
        r2 = r2.equals(r0);	 Catch:{ Exception -> 0x01e7 }
        if (r2 == 0) goto L_0x005d;
    L_0x005a:
        r2 = 0;
        r9.f4424d = r2;	 Catch:{ Exception -> 0x01e7 }
    L_0x005d:
        r2 = r9.f4431k;	 Catch:{ Exception -> 0x01e7 }
        r2 = r2.equals(r0);	 Catch:{ Exception -> 0x01e7 }
        if (r2 == 0) goto L_0x0068;
    L_0x0065:
        r2 = 0;
        r9.f4424d = r2;	 Catch:{ Exception -> 0x01e7 }
    L_0x0068:
        r2 = r9.f4432l;	 Catch:{ Exception -> 0x01e7 }
        r2 = r2.equals(r0);	 Catch:{ Exception -> 0x01e7 }
        if (r2 == 0) goto L_0x0073;
    L_0x0070:
        r2 = 0;
        r9.f4424d = r2;	 Catch:{ Exception -> 0x01e7 }
    L_0x0073:
        r2 = r9.f4433m;	 Catch:{ Exception -> 0x01e7 }
        r0 = r2.equals(r0);	 Catch:{ Exception -> 0x01e7 }
        if (r0 == 0) goto L_0x0005;
    L_0x007b:
        goto L_0x0005;
    L_0x007c:
        r0 = move-exception;
        r0.printStackTrace();
    L_0x0080:
        r0 = r1;
        goto L_0x001e;
    L_0x0082:
        r0 = new com.aps.c;	 Catch:{ Exception -> 0x01e7 }
        r0.<init>();	 Catch:{ Exception -> 0x01e7 }
        r4 = "time";
        r4 = r8.has(r4);	 Catch:{ Exception -> 0x01e7 }
        if (r4 == 0) goto L_0x0098;
    L_0x008f:
        r4 = "time";
        r4 = r8.getLong(r4);	 Catch:{ Exception -> 0x01e7 }
        r0.mo13194a(r4);	 Catch:{ Exception -> 0x01e7 }
    L_0x0098:
        r4 = "acc";
        r4 = r8.has(r4);	 Catch:{ Exception -> 0x01e7 }
        if (r4 == 0) goto L_0x00aa;
    L_0x00a0:
        r4 = "acc";
        r4 = r8.getInt(r4);	 Catch:{ Exception -> 0x01e7 }
        r4 = (float) r4;	 Catch:{ Exception -> 0x01e7 }
        r0.mo13193a(r4);	 Catch:{ Exception -> 0x01e7 }
    L_0x00aa:
        r4 = "dir";
        r4 = r8.has(r4);	 Catch:{ Exception -> 0x01e7 }
        if (r4 == 0) goto L_0x00bf;
    L_0x00b2:
        r4 = "dir";
        r4 = r8.getString(r4);	 Catch:{ Exception -> 0x01e7 }
        r4 = java.lang.Float.parseFloat(r4);	 Catch:{ Exception -> 0x01e7 }
        r0.mo13200b(r4);	 Catch:{ Exception -> 0x01e7 }
    L_0x00bf:
        r4 = "lbs";
        r0.mo13209f(r4);	 Catch:{ Exception -> 0x01e7 }
        r4 = "lat";
        r4 = r8.has(r4);	 Catch:{ Exception -> 0x01e7 }
        if (r4 == 0) goto L_0x0212;
    L_0x00cc:
        r4 = "lat";
        r4 = r8.getDouble(r4);	 Catch:{ Exception -> 0x01e7 }
        r6 = r4;
    L_0x00d3:
        r4 = "lon";
        r4 = r8.has(r4);	 Catch:{ Exception -> 0x01e7 }
        if (r4 == 0) goto L_0x020f;
    L_0x00db:
        r2 = "lon";
        r2 = r8.getDouble(r2);	 Catch:{ Exception -> 0x01e7 }
        r4 = r2;
    L_0x00e2:
        r2 = "type";
        r2 = r8.has(r2);	 Catch:{ Exception -> 0x01e7 }
        if (r2 == 0) goto L_0x020c;
    L_0x00eb:
        r2 = "type";
        r2 = r8.getString(r2);	 Catch:{ Exception -> 0x01e7 }
        r3 = r2;
    L_0x00f3:
        r2 = "poiname";
        r2 = r8.has(r2);	 Catch:{ Exception -> 0x01e7 }
        if (r2 == 0) goto L_0x0209;
    L_0x00fb:
        r2 = "poiname";
        r2 = r8.getString(r2);	 Catch:{ Exception -> 0x01e7 }
    L_0x0101:
        r0.mo13231q(r2);	 Catch:{ Exception -> 0x01e7 }
        r2 = "desc";
        r2 = r8.has(r2);	 Catch:{ Exception -> 0x01e7 }
        if (r2 == 0) goto L_0x0206;
    L_0x010c:
        r2 = "desc";
        r2 = r8.getString(r2);	 Catch:{ Exception -> 0x01e7 }
    L_0x0112:
        r0.mo13217j(r2);	 Catch:{ Exception -> 0x01e7 }
        r2 = "street";
        r2 = r8.has(r2);	 Catch:{ Exception -> 0x01e7 }
        if (r2 == 0) goto L_0x0203;
    L_0x011d:
        r2 = "street";
        r2 = r8.getString(r2);	 Catch:{ Exception -> 0x01e7 }
    L_0x0123:
        r0.mo13229p(r2);	 Catch:{ Exception -> 0x01e7 }
        r2 = "pid";
        r2 = r8.has(r2);	 Catch:{ Exception -> 0x01e7 }
        if (r2 == 0) goto L_0x0200;
    L_0x012e:
        r2 = "pid";
        r2 = r8.getString(r2);	 Catch:{ Exception -> 0x01e7 }
    L_0x0134:
        r0.mo13196a(r2);	 Catch:{ Exception -> 0x01e7 }
        r2 = "flr";
        r2 = r8.has(r2);	 Catch:{ Exception -> 0x01e7 }
        if (r2 == 0) goto L_0x01fd;
    L_0x013f:
        r2 = "flr";
        r2 = r8.getString(r2);	 Catch:{ Exception -> 0x01e7 }
    L_0x0145:
        r0.mo13201b(r2);	 Catch:{ Exception -> 0x01e7 }
        r2 = "road";
        r2 = r8.has(r2);	 Catch:{ Exception -> 0x01e7 }
        if (r2 == 0) goto L_0x01fa;
    L_0x0150:
        r2 = "road";
        r2 = r8.getString(r2);	 Catch:{ Exception -> 0x01e7 }
    L_0x0156:
        r0.mo13227o(r2);	 Catch:{ Exception -> 0x01e7 }
        r2 = "city";
        r2 = r8.has(r2);	 Catch:{ Exception -> 0x01e7 }
        if (r2 == 0) goto L_0x01f7;
    L_0x0161:
        r2 = "city";
        r2 = r8.getString(r2);	 Catch:{ Exception -> 0x01e7 }
    L_0x0167:
        r0.mo13225n(r2);	 Catch:{ Exception -> 0x01e7 }
        r2 = "country";
        r2 = r8.has(r2);	 Catch:{ Exception -> 0x01e7 }
        if (r2 == 0) goto L_0x01f5;
    L_0x0172:
        r2 = "country";
        r2 = r8.getString(r2);	 Catch:{ Exception -> 0x01e7 }
    L_0x0178:
        r0.mo13221l(r2);	 Catch:{ Exception -> 0x01e7 }
        r2 = "citycode";
        r2 = r8.has(r2);	 Catch:{ Exception -> 0x01e7 }
        if (r2 == 0) goto L_0x01f3;
    L_0x0183:
        r2 = "citycode";
        r2 = r8.getString(r2);	 Catch:{ Exception -> 0x01e7 }
    L_0x0189:
        r0.mo13215i(r2);	 Catch:{ Exception -> 0x01e7 }
        r2 = "province";
        r2 = r8.has(r2);	 Catch:{ Exception -> 0x01e7 }
        if (r2 == 0) goto L_0x01f1;
    L_0x0194:
        r2 = "province";
        r2 = r8.getString(r2);	 Catch:{ Exception -> 0x01e7 }
    L_0x019a:
        r0.mo13223m(r2);	 Catch:{ Exception -> 0x01e7 }
        r2 = "adcode";
        r2 = r8.has(r2);	 Catch:{ Exception -> 0x01e7 }
        if (r2 == 0) goto L_0x01ef;
    L_0x01a5:
        r2 = "adcode";
        r2 = r8.getString(r2);	 Catch:{ Exception -> 0x01e7 }
    L_0x01ab:
        r0.mo13219k(r2);	 Catch:{ Exception -> 0x01e7 }
        r2 = "district";
        r2 = r8.has(r2);	 Catch:{ Exception -> 0x01e7 }
        if (r2 == 0) goto L_0x01ed;
    L_0x01b6:
        r2 = "district";
        r2 = r8.getString(r2);	 Catch:{ Exception -> 0x01e7 }
    L_0x01bc:
        r0.mo13203c(r2);	 Catch:{ Exception -> 0x01e7 }
        r2 = "WGS84";
        r2 = r2.equals(r3);	 Catch:{ Exception -> 0x01e7 }
        if (r2 == 0) goto L_0x01e0;
    L_0x01c7:
        r2 = com.amap.api.location.core.ClientInfoUtil.m1423a(r6, r4);	 Catch:{ Exception -> 0x01e7 }
        if (r2 == 0) goto L_0x01e0;
    L_0x01cd:
        r2 = com.aps.C1270w.m5742a(r4, r6);	 Catch:{ Exception -> 0x01e7 }
        r3 = 1;
        r4 = r2[r3];	 Catch:{ Exception -> 0x01e7 }
        r0.mo13199b(r4);	 Catch:{ Exception -> 0x01e7 }
        r3 = 0;
        r2 = r2[r3];	 Catch:{ Exception -> 0x01e7 }
        r0.mo13192a(r2);	 Catch:{ Exception -> 0x01e7 }
    L_0x01dd:
        r1 = r0;
        goto L_0x0005;
    L_0x01e0:
        r0.mo13199b(r6);	 Catch:{ Exception -> 0x01e7 }
        r0.mo13192a(r4);	 Catch:{ Exception -> 0x01e7 }
        goto L_0x01dd;
    L_0x01e7:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0005;
    L_0x01ed:
        r2 = r1;
        goto L_0x01bc;
    L_0x01ef:
        r2 = r1;
        goto L_0x01ab;
    L_0x01f1:
        r2 = r1;
        goto L_0x019a;
    L_0x01f3:
        r2 = r1;
        goto L_0x0189;
    L_0x01f5:
        r2 = r1;
        goto L_0x0178;
    L_0x01f7:
        r2 = r1;
        goto L_0x0167;
    L_0x01fa:
        r2 = r1;
        goto L_0x0156;
    L_0x01fd:
        r2 = r1;
        goto L_0x0145;
    L_0x0200:
        r2 = r1;
        goto L_0x0134;
    L_0x0203:
        r2 = r1;
        goto L_0x0123;
    L_0x0206:
        r2 = r1;
        goto L_0x0112;
    L_0x0209:
        r2 = r1;
        goto L_0x0101;
    L_0x020c:
        r3 = r1;
        goto L_0x00f3;
    L_0x020f:
        r4 = r2;
        goto L_0x00e2;
    L_0x0212:
        r6 = r2;
        goto L_0x00d3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aps.ConnectionServiceManager.m5619a(android.os.Bundle):com.aps.c");
    }
}
