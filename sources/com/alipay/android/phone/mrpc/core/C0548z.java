package com.alipay.android.phone.mrpc.core;

import android.os.Looper;
import com.alipay.android.phone.mrpc.core.p012a.C0514d;
import com.alipay.android.phone.mrpc.core.p012a.C0515e;
import com.alipay.mobile.framework.service.annotation.OperationType;
import com.alipay.mobile.framework.service.annotation.ResetCookie;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.alipay.android.phone.mrpc.core.z */
public final class C0548z {
    /* renamed from: a */
    private static final ThreadLocal<Object> f404a = new ThreadLocal();
    /* renamed from: b */
    private static final ThreadLocal<Map<String, Object>> f405b = new ThreadLocal();
    /* renamed from: c */
    private byte f406c = (byte) 0;
    /* renamed from: d */
    private AtomicInteger f407d;
    /* renamed from: e */
    private C0546x f408e;

    public C0548z(C0546x c0546x) {
        this.f408e = c0546x;
        this.f407d = new AtomicInteger();
    }

    /* renamed from: a */
    public final Object mo7931a(Method method, Object[] objArr) {
        boolean z = true;
        boolean z2 = Looper.myLooper() != null && Looper.myLooper() == Looper.getMainLooper();
        if (z2) {
            throw new IllegalThreadStateException("can't in main thread call rpc .");
        }
        OperationType operationType = (OperationType) method.getAnnotation(OperationType.class);
        if (method.getAnnotation(ResetCookie.class) == null) {
            z = false;
        }
        Type genericReturnType = method.getGenericReturnType();
        method.getAnnotations();
        f404a.set(null);
        f405b.set(null);
        if (operationType == null) {
            throw new IllegalStateException("OperationType must be set.");
        }
        String value = operationType.value();
        int incrementAndGet = this.f407d.incrementAndGet();
        try {
            if (this.f406c == (byte) 0) {
                C0515e c0515e = new C0515e(incrementAndGet, value, objArr);
                if (f405b.get() != null) {
                    c0515e.mo7864a(f405b.get());
                }
                byte[] bArr = (byte[]) new C0533j(this.f408e.mo7928a(), method, incrementAndGet, value, c0515e.mo7865a(), z).mo7866a();
                f405b.set(null);
                Object a = new C0514d(genericReturnType, bArr).mo7863a();
                if (genericReturnType != Void.TYPE) {
                    f404a.set(a);
                }
            }
            return f404a.get();
        } catch (RpcException e) {
            e.setOperationType(value);
            throw e;
        }
    }
}
