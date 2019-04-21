package com.amap.api.mapcore.util;

import android.content.Context;
import android.view.MotionEvent;
import java.lang.reflect.Method;

/* renamed from: com.amap.api.mapcore.util.bd */
public class MultiTouchSupport {
    /* renamed from: a */
    protected Method f1322a;
    /* renamed from: b */
    protected Method f1323b;
    /* renamed from: c */
    protected Method f1324c;
    /* renamed from: d */
    private boolean f1325d = false;
    /* renamed from: e */
    private final C0754a f1326e;
    /* renamed from: f */
    private long f1327f = 0;
    /* renamed from: g */
    private boolean f1328g = false;

    /* compiled from: MultiTouchSupport */
    /* renamed from: com.amap.api.mapcore.util.bd$a */
    public interface C0754a {
        /* renamed from: a */
        void mo8796a();

        /* renamed from: a */
        void mo8797a(float f, float f2, float f3, float f4, float f5);

        /* renamed from: a */
        boolean mo8798a(MotionEvent motionEvent, float f, float f2, float f3, float f4);
    }

    public MultiTouchSupport(Context context, C0754a c0754a) {
        this.f1326e = c0754a;
        m1745c();
    }

    /* renamed from: a */
    public boolean mo8799a() {
        return this.f1328g;
    }

    /* renamed from: b */
    public long mo8801b() {
        return this.f1327f;
    }

    /* renamed from: c */
    private void m1745c() {
        try {
            this.f1322a = MotionEvent.class.getMethod("getPointerCount", new Class[0]);
            this.f1323b = MotionEvent.class.getMethod("getX", new Class[]{Integer.TYPE});
            this.f1324c = MotionEvent.class.getMethod("getY", new Class[]{Integer.TYPE});
            this.f1325d = true;
        } catch (Throwable th) {
            this.f1325d = false;
            SDKLogHandler.m2563a(th, "MultiTouchSupport", "initMethods");
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public boolean mo8800a(MotionEvent motionEvent) {
        if (!this.f1325d) {
            return false;
        }
        int action = motionEvent.getAction() & 255;
        try {
            if (((Integer) this.f1322a.invoke(motionEvent, new Object[0])).intValue() < 2) {
                this.f1327f = 0;
                this.f1328g = false;
                return false;
            }
            Float f = (Float) this.f1323b.invoke(motionEvent, new Object[]{Integer.valueOf(0)});
            Float f2 = (Float) this.f1323b.invoke(motionEvent, new Object[]{Integer.valueOf(1)});
            Float f3 = (Float) this.f1324c.invoke(motionEvent, new Object[]{Integer.valueOf(0)});
            Float f4 = (Float) this.f1324c.invoke(motionEvent, new Object[]{Integer.valueOf(1)});
            float sqrt = (float) Math.sqrt((double) (((f2.floatValue() - f.floatValue()) * (f2.floatValue() - f.floatValue())) + ((f4.floatValue() - f3.floatValue()) * (f4.floatValue() - f3.floatValue()))));
            if (action == 5) {
                this.f1326e.mo8797a(sqrt, f.floatValue(), f3.floatValue(), f2.floatValue(), f4.floatValue());
                this.f1328g = true;
                return true;
            } else if (action == 6) {
                this.f1327f = motionEvent.getEventTime();
                if (motionEvent.getPointerCount() == 2 && this.f1327f - motionEvent.getDownTime() < 100) {
                    this.f1326e.mo8796a();
                }
                if (this.f1328g) {
                    this.f1328g = false;
                }
                return false;
            } else {
                if (this.f1328g && action == 2) {
                    return this.f1326e.mo8798a(motionEvent, f.floatValue(), f3.floatValue(), f2.floatValue(), f4.floatValue());
                }
                return false;
            }
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "MultiTouchSupport", "onTouchEvent");
            th.printStackTrace();
        }
    }
}
