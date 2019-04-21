package com.threatmetrix.TrustDefender;

import android.content.Context;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* renamed from: com.threatmetrix.TrustDefender.l */
class C4497l {
    /* renamed from: A */
    static Method f7412A;
    /* renamed from: B */
    static Method f7413B;
    /* renamed from: C */
    static Method f7414C;
    /* renamed from: D */
    static Method f7415D;
    /* renamed from: E */
    static Method f7416E;
    /* renamed from: F */
    static Method f7417F;
    /* renamed from: G */
    static Method f7418G;
    /* renamed from: H */
    static Method f7419H;
    /* renamed from: I */
    static Method f7420I;
    /* renamed from: J */
    static Object f7421J;
    /* renamed from: K */
    static Object f7422K;
    /* renamed from: L */
    static Object f7423L;
    /* renamed from: M */
    static Object f7424M;
    /* renamed from: N */
    static boolean f7425N;
    /* renamed from: O */
    private static final String f7426O = C4549w.m8585a(C4497l.class);
    /* renamed from: U */
    private static String f7427U = null;
    /* renamed from: a */
    static Class<?> f7428a;
    /* renamed from: b */
    static Class<?> f7429b;
    /* renamed from: c */
    static Class<?> f7430c;
    /* renamed from: d */
    static Class<?> f7431d;
    /* renamed from: e */
    static Class<?> f7432e;
    /* renamed from: f */
    static Class<?> f7433f;
    /* renamed from: g */
    static Class<?> f7434g;
    /* renamed from: h */
    static Class<?> f7435h;
    /* renamed from: i */
    static Class<?> f7436i;
    /* renamed from: j */
    static Class<?> f7437j;
    /* renamed from: k */
    static Class<?> f7438k;
    /* renamed from: l */
    static Class<?> f7439l;
    /* renamed from: m */
    static Class<?> f7440m;
    /* renamed from: n */
    static Field f7441n;
    /* renamed from: o */
    static Field f7442o;
    /* renamed from: p */
    static Field f7443p;
    /* renamed from: q */
    static Field f7444q;
    /* renamed from: r */
    static Field f7445r;
    /* renamed from: s */
    static Field f7446s;
    /* renamed from: t */
    static Method f7447t;
    /* renamed from: u */
    static Method f7448u;
    /* renamed from: v */
    static Method f7449v;
    /* renamed from: w */
    static Method f7450w;
    /* renamed from: x */
    static Method f7451x;
    /* renamed from: y */
    static Method f7452y;
    /* renamed from: z */
    static Method f7453z;
    /* renamed from: P */
    private Object f7454P;
    /* renamed from: Q */
    private Object f7455Q;
    /* renamed from: R */
    private Object f7456R;
    /* renamed from: S */
    private Object f7457S;
    /* renamed from: T */
    private Object f7458T;
    /* renamed from: V */
    private volatile C4498a f7459V = C4498a.API_NOT_INITIATED;

    /* renamed from: com.threatmetrix.TrustDefender.l$a */
    enum C4498a {
        LIBRARY_NOT_AVAILABLE(false),
        API_NOT_INITIATED(false),
        API_AVAILABLE(false),
        NOT_AVAILABLE(false),
        LOCATION_SERVICES_CONNECT_REQUEST_SENT(false),
        LOCATION_SERVICES_CONNECTED(true),
        LOCATION_UPDATE_REGISTER_REQUEST_SENT(true),
        LOCATION_LISTENER_REGISTERED(true),
        LOCATION_SERVICES_PAUSED(true),
        LOCATION_SERVICES_DISCONNECTED(false);
        
        /* renamed from: k */
        private boolean f7471k;

        private C4498a(boolean isAccessible) {
            this.f7471k = isAccessible;
        }

        /* Access modifiers changed, original: final */
        /* renamed from: a */
        public final boolean mo34162a() {
            return this.f7471k;
        }
    }

    C4497l() {
    }

    static {
        f7421J = Integer.valueOf(105);
        f7422K = Integer.valueOf(104);
        f7423L = Integer.valueOf(100);
        f7424M = Integer.valueOf(102);
        f7425N = false;
        Class b = C4485at.m8327b("com.google.android.gms.common.GooglePlayServicesUtil");
        f7428a = b;
        if (b == null) {
            b = C4485at.m8327b("com.google.android.gms.common.GoogleApiAvailability");
            f7429b = b;
            if (b == null) {
                return;
            }
        }
        b = C4485at.m8327b("com.google.android.gms.common.api.GoogleApiClient$Builder");
        f7430c = b;
        if (b != null) {
            b = C4485at.m8327b("com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks");
            f7431d = b;
            if (b != null) {
                b = C4485at.m8327b("com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener");
                f7432e = b;
                if (b != null) {
                    b = C4485at.m8327b("com.google.android.gms.location.LocationServices");
                    f7433f = b;
                    if (b != null) {
                        b = C4485at.m8327b("com.google.android.gms.common.api.Api");
                        f7434g = b;
                        if (b != null) {
                            b = C4485at.m8327b("com.google.android.gms.common.api.GoogleApiClient");
                            f7435h = b;
                            if (b != null) {
                                b = C4485at.m8327b("com.google.android.gms.location.LocationRequest");
                                f7436i = b;
                                if (b != null) {
                                    b = C4485at.m8327b("com.google.android.gms.location.LocationListener");
                                    f7437j = b;
                                    if (b != null) {
                                        b = C4485at.m8327b("com.google.android.gms.common.api.PendingResult");
                                        f7438k = b;
                                        if (b != null) {
                                            b = C4485at.m8327b("com.google.android.gms.common.api.Status");
                                            f7439l = b;
                                            if (b != null) {
                                                b = C4485at.m8327b("com.google.android.gms.common.api.ResultCallback");
                                                f7440m = b;
                                                if (b != null) {
                                                    Field a = C4485at.m8324a(f7433f, "API");
                                                    f7441n = a;
                                                    if (a != null) {
                                                        a = C4485at.m8324a(f7433f, "FusedLocationApi");
                                                        f7442o = a;
                                                        if (a != null) {
                                                            a = C4485at.m8324a(f7436i, "PRIORITY_NO_POWER");
                                                            f7443p = a;
                                                            if (a != null) {
                                                                a = C4485at.m8324a(f7436i, "PRIORITY_LOW_POWER");
                                                                f7444q = a;
                                                                if (a != null) {
                                                                    a = C4485at.m8324a(f7436i, "PRIORITY_HIGH_ACCURACY");
                                                                    f7445r = a;
                                                                    if (a != null) {
                                                                        a = C4485at.m8324a(f7436i, "PRIORITY_BALANCED_POWER_ACCURACY");
                                                                        f7446s = a;
                                                                        if (a != null) {
                                                                            Method a2 = C4485at.m8325a(f7430c, "build", new Class[0]);
                                                                            f7452y = a2;
                                                                            if (a2 != null) {
                                                                                a2 = C4485at.m8325a(f7435h, "connect", new Class[0]);
                                                                                f7453z = a2;
                                                                                if (a2 != null) {
                                                                                    a2 = C4485at.m8325a(f7439l, "isSuccess", new Class[0]);
                                                                                    f7420I = a2;
                                                                                    if (a2 != null) {
                                                                                        a2 = C4485at.m8325a(f7435h, "disconnect", new Class[0]);
                                                                                        f7412A = a2;
                                                                                        if (a2 != null) {
                                                                                            a2 = C4485at.m8325a(f7430c, "addApi", f7434g);
                                                                                            f7451x = a2;
                                                                                            if (a2 != null) {
                                                                                                a2 = C4485at.m8325a(f7436i, "setPriority", Integer.TYPE);
                                                                                                f7415D = a2;
                                                                                                if (a2 != null) {
                                                                                                    a2 = C4485at.m8325a(f7436i, "setInterval", Long.TYPE);
                                                                                                    f7413B = a2;
                                                                                                    if (a2 != null) {
                                                                                                        a2 = C4485at.m8325a(f7436i, "setFastestInterval", Long.TYPE);
                                                                                                        f7414C = a2;
                                                                                                        if (a2 != null) {
                                                                                                            a2 = C4485at.m8325a(f7430c, "addConnectionCallbacks", f7431d);
                                                                                                            f7449v = a2;
                                                                                                            if (a2 != null) {
                                                                                                                a2 = C4485at.m8325a(f7430c, "addOnConnectionFailedListener", f7432e);
                                                                                                                f7450w = a2;
                                                                                                                if (a2 != null) {
                                                                                                                    a2 = C4485at.m8325a(f7438k, "setResultCallback", f7440m);
                                                                                                                    f7419H = a2;
                                                                                                                    if (a2 != null) {
                                                                                                                        a2 = C4485at.m8325a(f7428a, "isGooglePlayServicesAvailable", Context.class);
                                                                                                                        f7448u = a2;
                                                                                                                        if (a2 == null) {
                                                                                                                            a2 = C4485at.m8325a(f7429b, "isGooglePlayServicesAvailable", Context.class);
                                                                                                                            f7448u = a2;
                                                                                                                            if (a2 == null) {
                                                                                                                                a2 = C4485at.m8325a(f7429b, "getInstance", new Class[0]);
                                                                                                                                f7447t = a2;
                                                                                                                                if (a2 == null) {
                                                                                                                                    return;
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                        a2 = C4485at.m8325a(f7442o.getType(), "getLastLocation", f7435h);
                                                                                                                        f7418G = a2;
                                                                                                                        if (a2 != null) {
                                                                                                                            a2 = C4485at.m8325a(f7442o.getType(), "removeLocationUpdates", f7435h, f7437j);
                                                                                                                            f7417F = a2;
                                                                                                                            if (a2 != null) {
                                                                                                                                a2 = C4485at.m8325a(f7442o.getType(), "requestLocationUpdates", f7435h, f7436i, f7437j);
                                                                                                                                f7416E = a2;
                                                                                                                                if (a2 != null) {
                                                                                                                                    Object a3 = C4485at.m8322a(null, f7443p);
                                                                                                                                    f7421J = a3;
                                                                                                                                    if (a3 != null) {
                                                                                                                                        a3 = C4485at.m8322a(null, f7444q);
                                                                                                                                        f7422K = a3;
                                                                                                                                        if (a3 != null) {
                                                                                                                                            a3 = C4485at.m8322a(null, f7445r);
                                                                                                                                            f7423L = a3;
                                                                                                                                            if (a3 != null) {
                                                                                                                                                a3 = C4485at.m8322a(null, f7446s);
                                                                                                                                                f7424M = a3;
                                                                                                                                                if (a3 != null) {
                                                                                                                                                    f7425N = true;
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /* Access modifiers changed, original: final|declared_synchronized */
    /* renamed from: a */
    public final synchronized C4498a mo34149a(Context context) {
        C4498a c4498a;
        if (!f7425N || this.f7459V == C4498a.LIBRARY_NOT_AVAILABLE) {
            c4498a = C4498a.LIBRARY_NOT_AVAILABLE;
            this.f7459V = c4498a;
        } else {
            if (this.f7459V == C4498a.API_NOT_INITIATED || this.f7459V == C4498a.NOT_AVAILABLE) {
                Object isAvailable = C4485at.m8323a(null, f7448u, context);
                if (isAvailable == null || !isAvailable.equals(Integer.valueOf(0))) {
                    Object instance = C4485at.m8323a(null, f7447t, new Object[0]);
                    if (instance != null) {
                        isAvailable = C4485at.m8323a(instance, f7448u, context);
                        if (isAvailable == null) {
                            c4498a = C4498a.NOT_AVAILABLE;
                            this.f7459V = c4498a;
                        } else if (isAvailable.equals(Integer.valueOf(0))) {
                            c4498a = C4498a.API_AVAILABLE;
                            this.f7459V = c4498a;
                        }
                    }
                } else {
                    c4498a = C4498a.API_AVAILABLE;
                    this.f7459V = c4498a;
                }
            }
            c4498a = this.f7459V;
        }
        return c4498a;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final Object mo34150a(Method method, Object[] args) {
        if ("toString".equals(method.getName())) {
            return method.getName();
        }
        if ("hashCode".equals(method.getName())) {
            return Integer.valueOf(hashCode());
        }
        if (!"equals".equals(method.getName())) {
            return null;
        }
        if (args == null || args.length != 2) {
            return Boolean.valueOf(true);
        }
        return Boolean.valueOf(args[0].equals(args[1]));
    }

    /* Access modifiers changed, original: final|declared_synchronized */
    /* renamed from: a */
    public final synchronized void mo34152a(Context context, long interval, long fastestInterval, int accuracy, InvocationHandler connectCallBackHandler, InvocationHandler locationListener, InvocationHandler passiveLocationListener) {
        if (this.f7459V == C4498a.API_AVAILABLE) {
            Object builderInstance = C4485at.m8320a(f7430c, new Class[]{Context.class}, new Object[]{context});
            if (builderInstance != null) {
                Object proxy = C4485at.m8321a(f7431d.getClassLoader(), new Class[]{f7431d}, connectCallBackHandler);
                builderInstance = C4485at.m8323a(builderInstance, f7449v, proxy);
                if (builderInstance != null) {
                    Object failedListenerProxy = C4485at.m8321a(f7432e.getClassLoader(), new Class[]{f7432e}, connectCallBackHandler);
                    builderInstance = C4485at.m8323a(builderInstance, f7450w, failedListenerProxy);
                    if (builderInstance != null) {
                        builderInstance = C4485at.m8323a(builderInstance, f7451x, C4485at.m8322a(null, f7441n));
                        if (builderInstance != null) {
                            this.f7454P = C4485at.m8323a(builderInstance, f7452y, new Object[0]);
                            if (this.f7454P != null) {
                                Integer valueOf = Integer.valueOf(102);
                                if (f7425N) {
                                    switch (accuracy) {
                                        case 1:
                                        case 2:
                                            valueOf = f7424M;
                                            break;
                                        case 3:
                                            valueOf = f7423L;
                                            break;
                                        default:
                                            valueOf = f7424M;
                                            break;
                                    }
                                }
                                this.f7455Q = C4497l.m8368a(interval, fastestInterval, valueOf.intValue());
                                this.f7456R = C4497l.m8368a(interval, fastestInterval, ((Integer) f7421J).intValue());
                                if (!(this.f7455Q == null || this.f7456R == null)) {
                                    this.f7457S = C4485at.m8321a(f7437j.getClassLoader(), new Class[]{f7437j}, locationListener);
                                    this.f7458T = C4485at.m8321a(f7437j.getClassLoader(), new Class[]{f7437j}, passiveLocationListener);
                                    C4485at.m8323a(this.f7454P, f7453z, new Object[0]);
                                    this.f7459V = C4498a.LOCATION_SERVICES_CONNECT_REQUEST_SENT;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /* Access modifiers changed, original: final|declared_synchronized */
    /* renamed from: a */
    public final synchronized void mo34154a(Object locationRequestInstance, Object fusedLocationListener, InvocationHandler resultCallbackHandler) {
        if (f7425N && this.f7459V.mo34162a()) {
            Object pendingResult = C4485at.m8323a(C4485at.m8322a(null, f7442o), f7416E, this.f7454P, locationRequestInstance, fusedLocationListener);
            this.f7459V = C4498a.LOCATION_UPDATE_REGISTER_REQUEST_SENT;
            if (!(this.f7459V == C4498a.LOCATION_LISTENER_REGISTERED || pendingResult == null)) {
                Object resultCallbackProxy = C4485at.m8321a(f7440m.getClassLoader(), new Class[]{f7440m}, resultCallbackHandler);
                C4485at.m8323a(pendingResult, f7419H, resultCallbackProxy);
            }
        }
    }

    /* Access modifiers changed, original: final|declared_synchronized */
    /* renamed from: a */
    public final synchronized void mo34151a() {
        if (f7425N && this.f7459V.mo34162a() && this.f7459V != C4498a.LOCATION_SERVICES_PAUSED) {
            C4485at.m8323a(C4485at.m8322a(null, f7442o), f7417F, this.f7454P, this.f7457S);
            this.f7459V = C4498a.LOCATION_SERVICES_PAUSED;
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: b */
    public final String mo34156b(Context context) {
        if (f7427U == null && mo34149a(context) == C4498a.API_AVAILABLE) {
            Class advertisingClass = C4485at.m8327b("com.google.android.gms.ads.identifier.AdvertisingIdClient");
            if (advertisingClass == null) {
                return null;
            }
            Class advertisingInfoClass = C4485at.m8327b("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
            if (advertisingInfoClass == null) {
                return null;
            }
            Method getInfoMethod = C4485at.m8325a(advertisingInfoClass, "getId", new Class[0]);
            if (getInfoMethod == null) {
                return null;
            }
            Method getAdvertisingInfoMethod = C4485at.m8325a(advertisingClass, "getAdvertisingIdInfo", Context.class);
            if (getAdvertisingInfoMethod == null) {
                return null;
            }
            Object advertisingInfo = C4485at.m8323a(null, getAdvertisingInfoMethod, context);
            if (advertisingInfo != null) {
                Object id = C4485at.m8323a(advertisingInfo, getInfoMethod, new Object[0]);
                if (id != null) {
                    String valueOf = String.valueOf(id);
                    f7427U = valueOf;
                    return valueOf;
                }
            }
        }
        return f7427U;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: b */
    public final C4498a mo34155b() {
        return this.f7459V;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final void mo34153a(C4498a m_GooglePlayApiStatus) {
        this.f7459V = m_GooglePlayApiStatus;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: c */
    public final Object mo34157c() {
        return this.f7454P;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: d */
    public final Object mo34158d() {
        return this.f7455Q;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: e */
    public final Object mo34159e() {
        return this.f7456R;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: f */
    public final Object mo34160f() {
        return this.f7457S;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: g */
    public final Object mo34161g() {
        return this.f7458T;
    }

    /* renamed from: h */
    static boolean m8369h() {
        return f7425N;
    }

    /* renamed from: a */
    private static Object m8368a(long interval, long fastestInterval, int priority) {
        if (!f7425N) {
            return null;
        }
        Object locReqInstance = C4485at.m8320a(f7436i, null, null);
        C4485at.m8323a(locReqInstance, f7413B, Long.valueOf(interval));
        C4485at.m8323a(locReqInstance, f7414C, Long.valueOf(fastestInterval));
        C4485at.m8323a(locReqInstance, f7415D, Integer.valueOf(priority));
        return locReqInstance;
    }
}
