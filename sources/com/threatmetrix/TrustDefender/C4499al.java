package com.threatmetrix.TrustDefender;

import android.content.Context;
import android.location.Location;
import com.threatmetrix.TrustDefender.C4497l.C4498a;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* renamed from: com.threatmetrix.TrustDefender.al */
class C4499al extends C4497l {
    /* renamed from: O */
    private static final String f7472O = C4549w.m8585a(C4499al.class);
    /* renamed from: P */
    private Object f7473P;
    /* renamed from: Q */
    private Object f7474Q;
    /* renamed from: R */
    private Object f7475R;
    /* renamed from: S */
    private Object f7476S;
    /* renamed from: T */
    private Object f7477T;
    /* renamed from: U */
    private C4501ao f7478U;
    /* renamed from: V */
    private C4505ap f7479V;
    /* renamed from: W */
    private volatile boolean f7480W = false;
    /* renamed from: X */
    private final Object f7481X = new Object();

    /* renamed from: com.threatmetrix.TrustDefender.al$a */
    class C4494a implements InvocationHandler {
        C4494a() {
        }

        public final Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if ("onConnected".equals(method.getName())) {
                if (!C4497l.m8369h()) {
                    return null;
                }
                Location locationResult = C4485at.m8323a(C4485at.m8322a(null, C4497l.f7442o), C4497l.f7418G, C4499al.this.f7473P);
                if (locationResult != null) {
                    C4499al.this.f7478U.onLocationChanged(locationResult);
                }
                synchronized (C4499al.this.f7481X) {
                    C4499al.this.mo34153a(C4498a.LOCATION_SERVICES_CONNECTED);
                    if (!C4499al.this.f7480W) {
                        C4499al.this.mo34165i();
                    }
                }
                return null;
            } else if ("onConnectionSuspended".equals(method.getName())) {
                if (args == null || args.length <= 0) {
                    return null;
                }
                C4549w.m8592b(C4499al.f7472O, "Connection to the Google Service is suspended, the error code is " + args[0]);
                return null;
            } else if (!"onConnectionFailed".equals(method.getName())) {
                return C4499al.this.mo34150a(method, args);
            } else {
                if (args == null || args.length <= 0) {
                    return null;
                }
                C4549w.m8592b(C4499al.f7472O, "Connection to the Google Service is failed. The error code is " + args[0]);
                return null;
            }
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.al$b */
    class C4495b implements InvocationHandler {
        C4495b() {
        }

        public final Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (!"onLocationChanged".equals(method.getName())) {
                return C4499al.this.mo34150a(method, args);
            }
            if (args != null && args.length > 0) {
                C4499al.this.f7478U.onLocationChanged(args[0]);
            }
            return null;
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.al$c */
    class C4496c implements InvocationHandler {
        C4496c() {
        }

        public final Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (!"onResult".equals(method.getName())) {
                return C4499al.this.mo34150a(method, args);
            }
            if (args != null && args.length > 0) {
                Object result = C4485at.m8323a(args[0], C4497l.f7420I, new Object[0]);
                synchronized (this) {
                    if (result != null) {
                        if (((Boolean) result).booleanValue() && C4499al.this.mo34155b() != C4498a.LOCATION_LISTENER_REGISTERED) {
                            C4499al.this.mo34153a(C4498a.LOCATION_LISTENER_REGISTERED);
                            C4499al.this.f7479V.mo34182e();
                        }
                    }
                }
            }
            return null;
        }
    }

    C4499al() {
    }

    /* Access modifiers changed, original: final|declared_synchronized */
    /* renamed from: a */
    public final synchronized void mo34163a(Context context, long interval, long fastestInterval, int accuracy, C4501ao tdLocationListener, C4505ap tdLocationManager) {
        if (mo34149a(context) == C4498a.API_AVAILABLE) {
            this.f7479V = tdLocationManager;
            this.f7478U = tdLocationListener;
            mo34152a(context, interval, fastestInterval, accuracy, new C4494a(), new C4495b(), new C4495b());
            this.f7477T = mo34161g();
            this.f7474Q = mo34158d();
            this.f7473P = mo34157c();
            this.f7475R = mo34159e();
            this.f7476S = mo34160f();
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: i */
    public final void mo34165i() {
        InvocationHandler resultCallbackHandler = new C4496c();
        mo34154a(this.f7474Q, this.f7476S, resultCallbackHandler);
        mo34154a(this.f7475R, this.f7477T, resultCallbackHandler);
    }

    /* Access modifiers changed, original: final|declared_synchronized */
    /* renamed from: j */
    public final synchronized void mo34166j() {
        mo34151a();
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final void mo34164a(boolean isPaused) {
        this.f7480W = isPaused;
    }
}
