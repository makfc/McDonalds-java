package com.alipay.android.phone.mrpc.core;

import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

/* renamed from: com.alipay.android.phone.mrpc.core.j */
public final class C0533j extends C0517a {
    /* renamed from: g */
    private C0529g f347g;

    public C0533j(C0529g c0529g, Method method, int i, String str, byte[] bArr, boolean z) {
        super(method, i, str, bArr, "application/x-www-form-urlencoded", z);
        this.f347g = c0529g;
    }

    /* renamed from: a */
    public final Object mo7866a() {
        C0540o c0540o = new C0540o(this.f347g.mo7892a());
        c0540o.mo7911a(this.f322b);
        c0540o.mo7907a(this.f325e);
        c0540o.mo7910a(this.f326f);
        c0540o.mo7908a("id", String.valueOf(this.f324d));
        c0540o.mo7908a("operationType", this.f323c);
        c0540o.mo7908a("gzip", String.valueOf(this.f347g.mo7895d()));
        c0540o.mo7909a(new BasicHeader(AnalyticAttribute.UUID_ATTRIBUTE, UUID.randomUUID().toString()));
        List<Header> b = this.f347g.mo7894c().mo7869b();
        if (!(b == null || b.isEmpty())) {
            for (Header a : b) {
                c0540o.mo7909a(a);
            }
        }
        new StringBuilder("threadid = ").append(Thread.currentThread().getId()).append("; ").append(c0540o.toString());
        try {
            C0541u c0541u = (C0541u) this.f347g.mo7893b().mo7871a(c0540o).get();
            if (c0541u != null) {
                return c0541u.mo7921b();
            }
            throw new RpcException(Integer.valueOf(9), "response is null");
        } catch (InterruptedException e) {
            throw new RpcException(Integer.valueOf(13), "", e);
        } catch (ExecutionException e2) {
            ExecutionException executionException = e2;
            Throwable cause = executionException.getCause();
            if (cause == null || !(cause instanceof HttpException)) {
                throw new RpcException(Integer.valueOf(9), "", executionException);
            }
            HttpException httpException = (HttpException) cause;
            int code = httpException.getCode();
            switch (code) {
                case 1:
                    code = 2;
                    break;
                case 2:
                    code = 3;
                    break;
                case 3:
                    code = 4;
                    break;
                case 4:
                    code = 5;
                    break;
                case 5:
                    code = 6;
                    break;
                case 6:
                    code = 7;
                    break;
                case 7:
                    code = 8;
                    break;
                case 8:
                    code = 15;
                    break;
                case 9:
                    code = 16;
                    break;
            }
            throw new RpcException(Integer.valueOf(code), httpException.getMsg());
        } catch (CancellationException e3) {
            throw new RpcException(Integer.valueOf(13), "", e3);
        }
    }
}
