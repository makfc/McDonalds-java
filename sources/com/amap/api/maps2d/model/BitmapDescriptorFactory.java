package com.amap.api.maps2d.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import com.amap.api.mapcore2d.C0885ah.C0884a;
import com.amap.api.mapcore2d.C0893aq;
import com.amap.api.mapcore2d.C0955ck;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import java.io.FileInputStream;

public final class BitmapDescriptorFactory {
    public static final float HUE_AZURE = 210.0f;
    public static final float HUE_BLUE = 240.0f;
    public static final float HUE_CYAN = 180.0f;
    public static final float HUE_GREEN = 120.0f;
    public static final float HUE_MAGENTA = 300.0f;
    public static final float HUE_ORANGE = 30.0f;
    public static final float HUE_RED = 0.0f;
    public static final float HUE_ROSE = 330.0f;
    public static final float HUE_VIOLET = 270.0f;
    public static final float HUE_YELLOW = 60.0f;

    public static BitmapDescriptor fromResource(int i) {
        String str = "fromResource";
        try {
            Context context = C0893aq.f2276a;
            if (context != null) {
                return fromBitmap(BitmapFactoryInstrumentation.decodeStream(context.getResources().openRawResource(i)));
            }
            return null;
        } catch (Throwable th) {
            C0955ck.m3888a(th, "BitmapDescriptorFactory", str);
            return null;
        }
    }

    public static BitmapDescriptor fromView(View view) {
        String str = "fromView";
        try {
            Context context = C0893aq.f2276a;
            if (context == null) {
                return null;
            }
            FrameLayout frameLayout = new FrameLayout(context);
            frameLayout.addView(view);
            frameLayout.destroyDrawingCache();
            return fromBitmap(m4568a(frameLayout));
        } catch (Throwable th) {
            C0955ck.m3888a(th, "BitmapDescriptorFactory", str);
            return null;
        }
    }

    /* renamed from: a */
    private static Bitmap m4568a(View view) {
        view.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        return view.getDrawingCache().copy(Config.ARGB_8888, false);
    }

    public static BitmapDescriptor fromPath(String str) {
        String str2 = "fromPath";
        try {
            return fromBitmap(BitmapFactoryInstrumentation.decodeFile(str));
        } catch (Throwable th) {
            C0955ck.m3888a(th, "BitmapDescriptorFactory", str2);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x004a A:{SYNTHETIC, Splitter:B:23:0x004a} */
    public static com.amap.api.maps2d.model.BitmapDescriptor fromAsset(java.lang.String r5) {
        /*
        r0 = 0;
        r3 = "fromAsset";
        r1 = com.amap.api.maps2d.model.BitmapDescriptorFactory.class;
        r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0032, all -> 0x0046 }
        r2.<init>();	 Catch:{ Throwable -> 0x0032, all -> 0x0046 }
        r4 = "/assets/";
        r2 = r2.append(r4);	 Catch:{ Throwable -> 0x0032, all -> 0x0046 }
        r2 = r2.append(r5);	 Catch:{ Throwable -> 0x0032, all -> 0x0046 }
        r2 = r2.toString();	 Catch:{ Throwable -> 0x0032, all -> 0x0046 }
        r2 = r1.getResourceAsStream(r2);	 Catch:{ Throwable -> 0x0032, all -> 0x0046 }
        r1 = com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation.decodeStream(r2);	 Catch:{ Throwable -> 0x0057 }
        r1 = fromBitmap(r1);	 Catch:{ Throwable -> 0x0057 }
        if (r2 == 0) goto L_0x0029;
    L_0x0026:
        r2.close();	 Catch:{ Throwable -> 0x002b }
    L_0x0029:
        r0 = r1;
    L_0x002a:
        return r0;
    L_0x002b:
        r1 = move-exception;
        r2 = "BitmapDescriptorFactory";
        com.amap.api.mapcore2d.C0955ck.m3888a(r1, r2, r3);
        goto L_0x002a;
    L_0x0032:
        r1 = move-exception;
        r2 = r0;
    L_0x0034:
        r4 = "BitmapDescriptorFactory";
        com.amap.api.mapcore2d.C0955ck.m3888a(r1, r4, r3);	 Catch:{ all -> 0x0055 }
        if (r2 == 0) goto L_0x002a;
    L_0x003b:
        r2.close();	 Catch:{ Throwable -> 0x003f }
        goto L_0x002a;
    L_0x003f:
        r1 = move-exception;
        r2 = "BitmapDescriptorFactory";
        com.amap.api.mapcore2d.C0955ck.m3888a(r1, r2, r3);
        goto L_0x002a;
    L_0x0046:
        r1 = move-exception;
        r2 = r0;
    L_0x0048:
        if (r2 == 0) goto L_0x004d;
    L_0x004a:
        r2.close();	 Catch:{ Throwable -> 0x004e }
    L_0x004d:
        throw r1;
    L_0x004e:
        r1 = move-exception;
        r2 = "BitmapDescriptorFactory";
        com.amap.api.mapcore2d.C0955ck.m3888a(r1, r2, r3);
        goto L_0x002a;
    L_0x0055:
        r1 = move-exception;
        goto L_0x0048;
    L_0x0057:
        r1 = move-exception;
        goto L_0x0034;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.maps2d.model.BitmapDescriptorFactory.fromAsset(java.lang.String):com.amap.api.maps2d.model.BitmapDescriptor");
    }

    public static BitmapDescriptor fromFile(String str) {
        String str2 = "fromFile";
        try {
            Context context = C0893aq.f2276a;
            if (context == null) {
                return null;
            }
            FileInputStream openFileInput = context.openFileInput(str);
            Bitmap decodeStream = BitmapFactoryInstrumentation.decodeStream(openFileInput);
            openFileInput.close();
            return fromBitmap(decodeStream);
        } catch (Throwable th) {
            C0955ck.m3888a(th, "BitmapDescriptorFactory", str2);
            return null;
        }
    }

    public static BitmapDescriptor defaultMarker() {
        String str = "defaultMarker";
        try {
            return fromAsset(C0884a.marker_default2d.name() + ".png");
        } catch (Throwable th) {
            C0955ck.m3888a(th, "BitmapDescriptorFactory", str);
            return null;
        }
    }

    public static BitmapDescriptor defaultMarker(float f) {
        String str = "defaultMarker";
        try {
            float f2;
            float f3 = (float) ((((int) (15.0f + f)) / 30) * 30);
            if (f3 > 330.0f) {
                f2 = 330.0f;
            } else if (f3 < 0.0f) {
                f2 = 0.0f;
            } else {
                f2 = f3;
            }
            String str2 = "";
            if (f2 == 0.0f) {
                str2 = "RED";
            } else if (f2 == 30.0f) {
                str2 = "ORANGE";
            } else if (f2 == 60.0f) {
                str2 = "YELLOW";
            } else if (f2 == 120.0f) {
                str2 = "GREEN";
            } else if (f2 == 180.0f) {
                str2 = "CYAN";
            } else if (f2 == 210.0f) {
                str2 = "AZURE";
            } else if (f2 == 240.0f) {
                str2 = "BLUE";
            } else if (f2 == 270.0f) {
                str2 = "VIOLET";
            } else if (f2 == 300.0f) {
                str2 = "MAGENTAV";
            } else if (f2 == 330.0f) {
                str2 = "ROSE";
            }
            return fromAsset(str2 + "2d.png");
        } catch (Throwable th) {
            C0955ck.m3888a(th, "BitmapDescriptorFactory", str);
            return null;
        }
    }

    public static BitmapDescriptor fromBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return new BitmapDescriptor(bitmap);
    }
}
