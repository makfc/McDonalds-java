package com.amap.api.location;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amap.api.location.core.AuthManager;
import com.amap.api.location.core.CoreUtil;
import com.aps.Fence;
import java.util.Iterator;
import java.util.Vector;

/* renamed from: com.amap.api.location.a */
public class AMapLocationManager {
    /* renamed from: j */
    static AMapLocationManager f836j = null;
    /* renamed from: k */
    static Context f837k;
    /* renamed from: a */
    Vector<RequestLocationEntity> f838a = null;
    /* renamed from: b */
    IGPSManager f839b = null;
    /* renamed from: c */
    IAPSManager f840c = null;
    /* renamed from: d */
    boolean f841d = false;
    /* renamed from: e */
    long f842e;
    /* renamed from: f */
    boolean f843f = true;
    /* renamed from: g */
    boolean f844g = true;
    /* renamed from: h */
    AMapWeather f845h;
    /* renamed from: i */
    long f846i;
    /* renamed from: l */
    private Context f847l;
    /* renamed from: m */
    private C0723a f848m = null;
    /* renamed from: n */
    private Vector<RequestLocationEntity> f849n = new Vector();
    /* renamed from: o */
    private AMapLocation f850o;
    /* renamed from: p */
    private AMapLocation f851p;
    /* renamed from: q */
    private volatile Thread f852q;
    /* renamed from: r */
    private long f853r = 2000;
    /* renamed from: s */
    private float f854s = 10.0f;

    /* compiled from: AMapLocationManager */
    /* renamed from: com.amap.api.location.a$2 */
    class C07212 implements OnClickListener {
        C07212() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            AMapLocationManager.this.m1359g();
            dialogInterface.cancel();
        }
    }

    /* compiled from: AMapLocationManager */
    /* renamed from: com.amap.api.location.a$3 */
    class C07223 implements OnClickListener {
        C07223() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.cancel();
        }
    }

    /* compiled from: AMapLocationManager */
    /* renamed from: com.amap.api.location.a$a */
    class C0723a extends Handler {
        public C0723a(Looper looper) {
            super(looper);
            Looper.prepare();
        }

        public void handleMessage(Message message) {
            if (message != null) {
                if (message.what == 100 && AMapLocationManager.this.f838a != null) {
                    try {
                        AMapLocationManager.this.f850o = (AMapLocation) message.obj;
                        if (!(AMapLocationManager.this.f850o == null || AMapLocationManager.this.f850o.getAdCode() == null || AMapLocationManager.this.f850o.getAdCode().length() <= 0)) {
                            AMapLocationManager.this.f851p = AMapLocationManager.this.f850o;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } catch (Throwable th) {
                        return;
                    }
                    Iterator it = AMapLocationManager.this.f838a.iterator();
                    while (it.hasNext()) {
                        RequestLocationEntity requestLocationEntity = (RequestLocationEntity) it.next();
                        if (requestLocationEntity.f953b != null) {
                            AMapLocation aMapLocation = (AMapLocation) message.obj;
                            if (requestLocationEntity.f954c.booleanValue() || aMapLocation.getAMapException().getErrorCode() == 0) {
                                try {
                                    requestLocationEntity.f953b.onLocationChanged(aMapLocation);
                                } catch (Throwable th2) {
                                }
                            }
                        }
                        if (requestLocationEntity.f954c.booleanValue() && requestLocationEntity.f952a == -1 && AMapLocationManager.this.f849n != null) {
                            AMapLocationManager.this.f849n.add(requestLocationEntity);
                        }
                    }
                    if (AMapLocationManager.this.f849n != null && AMapLocationManager.this.f849n.size() > 0) {
                        for (int i = 0; i < AMapLocationManager.this.f849n.size(); i++) {
                            AMapLocationManager.this.mo8356a(((RequestLocationEntity) AMapLocationManager.this.f849n.get(i)).f953b);
                        }
                        AMapLocationManager.this.f849n.clear();
                    }
                    if (AMapLocationManager.this.f850o != null) {
                        CoreUtil.m1457a(AMapLocationManager.this.f847l, AMapLocationManager.this.f850o);
                    }
                }
                if (message.what == 101 && AuthManager.m1416i()) {
                    AuthManager.m1407c("0");
                    AMapLocationManager.this.m1357f();
                }
            }
        }
    }

    /* renamed from: a */
    public static synchronized AMapLocationManager m1347a(Context context, Context context2, LocationManager locationManager) {
        AMapLocationManager aMapLocationManager;
        synchronized (AMapLocationManager.class) {
            f837k = context;
            if (f836j == null) {
                f836j = new AMapLocationManager(context2, locationManager);
            }
            aMapLocationManager = f836j;
        }
        return aMapLocationManager;
    }

    private AMapLocationManager(Context context, LocationManager locationManager) {
        this.f847l = context;
        m1356e();
        if (Looper.myLooper() == null) {
            this.f848m = new C0723a(context.getMainLooper());
        } else {
            this.f848m = new C0723a();
        }
        this.f839b = new IGPSManager(context, locationManager, this.f848m, this);
        this.f840c = new IAPSManager(context, this.f848m, this);
        mo8361b(false);
        this.f843f = true;
        this.f844g = true;
        this.f845h = new AMapWeather(this, context);
    }

    /* renamed from: e */
    private void m1356e() {
        this.f838a = new Vector();
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo8352a(double d, double d2, float f, long j, PendingIntent pendingIntent) {
        Fence fence = new Fence();
        fence.f4483b = d;
        fence.f4482a = d2;
        fence.f4484c = f;
        fence.mo13275a(j);
        this.f840c.mo8374a(fence, pendingIntent);
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: b */
    public void mo8359b(double d, double d2, float f, long j, PendingIntent pendingIntent) {
        Fence fence = new Fence();
        fence.f4483b = d;
        fence.f4482a = d2;
        fence.f4484c = f;
        fence.mo13275a(j);
        this.f840c.mo8379b(fence, pendingIntent);
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo8355a(PendingIntent pendingIntent) {
        this.f840c.mo8373a(pendingIntent);
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: b */
    public void mo8360b(PendingIntent pendingIntent) {
        this.f840c.mo8378b(pendingIntent);
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public AMapLocation mo8351a() {
        if (this.f850o != null) {
            return this.f850o;
        }
        return CoreUtil.m1461b(this.f847l);
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo8354a(long j, float f, AMapLocationListener aMapLocationListener, String str, boolean z) {
        this.f853r = j;
        this.f854s = f;
        if (aMapLocationListener != null) {
            RequestLocationEntity requestLocationEntity = new RequestLocationEntity(j, f, aMapLocationListener, str, z);
            if (!this.f838a.contains(requestLocationEntity)) {
                this.f838a.add(requestLocationEntity);
            }
            if ("gps".equals(str)) {
                this.f839b.mo8413a(j, f);
            } else if (LocationProviderProxy.AMapNetwork.equals(str)) {
                if (this.f844g) {
                    this.f839b.mo8413a(j, f);
                }
                this.f840c.mo8372a(j);
                m1352c(true);
                if (this.f852q == null) {
                    this.f840c.mo8380b(true);
                    this.f852q = new Thread(this.f840c);
                    this.f852q.start();
                }
            }
        }
    }

    /* renamed from: c */
    private void m1352c(boolean z) {
        this.f843f = z;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo8356a(AMapLocationListener aMapLocationListener) {
        int size;
        if (this.f838a != null) {
            size = this.f838a.size();
        } else {
            size = 0;
        }
        int i = 0;
        int i2 = size;
        while (i < i2) {
            RequestLocationEntity requestLocationEntity = (RequestLocationEntity) this.f838a.get(i);
            if (requestLocationEntity == null) {
                this.f838a.remove(i);
                i2--;
                size = i - 1;
            } else if (requestLocationEntity.f953b == null || aMapLocationListener.equals(requestLocationEntity.f953b)) {
                this.f838a.remove(requestLocationEntity);
                i2--;
                size = i - 1;
            } else {
                size = i;
            }
            i2 = i2;
            i = size + 1;
        }
        if (this.f838a == null || this.f838a.size() == 0) {
            mo8361b(false);
            m1352c(false);
            mo8358b();
            if (this.f839b != null) {
                this.f839b.mo8414b();
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: b */
    public void mo8358b() {
        if (this.f840c != null) {
            this.f840c.mo8380b(false);
        }
        if (this.f852q != null) {
            this.f852q.interrupt();
            this.f852q = null;
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo8357a(boolean z) {
        m1354d(z);
        if (this.f838a != null && this.f838a.size() > 0) {
            if (z) {
                this.f839b.mo8414b();
                this.f839b.mo8413a(this.f853r, this.f854s);
                return;
            }
            this.f839b.mo8414b();
        }
    }

    /* renamed from: d */
    private void m1354d(boolean z) {
        this.f844g = z;
    }

    /* renamed from: c */
    static synchronized void m1351c() {
        synchronized (AMapLocationManager.class) {
            if (f836j != null) {
                f836j.mo8362d();
            }
            f836j = null;
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: d */
    public void mo8362d() {
        if (this.f839b != null) {
            this.f839b.mo8414b();
            this.f839b.mo8412a();
            this.f839b = null;
        }
        if (this.f840c != null) {
            this.f840c.mo8377b();
        }
        if (this.f838a != null) {
            this.f838a.clear();
        }
        mo8361b(false);
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: b */
    public void mo8361b(boolean z) {
        this.f841d = z;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo8353a(final int i, final AMapLocalWeatherListener aMapLocalWeatherListener) {
        try {
            new Thread() {
                public void run() {
                    AMapLocationManager.this.f845h.mo8366a(i, aMapLocalWeatherListener, AMapLocationManager.this.f851p);
                }
            }.start();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: f */
    private void m1357f() {
        Object obj = 1;
        Object obj2 = null;
        try {
            if (f837k.checkCallingOrSelfPermission("android.permission.SYSTEM_ALERT_WINDOW") == 0) {
                obj2 = 1;
            } else if (f837k instanceof Activity) {
                int obj22 = 1;
                obj = null;
            } else {
                obj = null;
            }
            if (obj22 != null) {
                Builder builder = new Builder(f837k);
                builder.setMessage(AuthManager.m1402b());
                if (!("".equals(AuthManager.m1406c()) || AuthManager.m1406c() == null)) {
                    builder.setPositiveButton(AuthManager.m1406c(), new C07212());
                }
                builder.setNegativeButton(AuthManager.m1408d(), new C07223());
                AlertDialog create = builder.create();
                if (obj != null) {
                    create.getWindow().setType(2003);
                }
                create.setCanceledOnTouchOutside(false);
                create.show();
                return;
            }
            m1359g();
        } catch (Throwable th) {
            m1359g();
        }
    }

    /* renamed from: g */
    private void m1359g() {
        Intent intent;
        try {
            intent = new Intent();
            intent.setComponent(new ComponentName("com.autonavi.minimap", AuthManager.m1414g()));
            intent.setFlags(268435456);
            intent.setData(Uri.parse(AuthManager.m1410e()));
            f837k.startActivity(intent);
        } catch (Throwable th) {
        }
    }
}
