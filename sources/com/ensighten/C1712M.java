package com.ensighten;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.support.p000v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.internal.NativeProtocol;
import com.mcdonalds.sdk.modules.models.MenuType;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.security.SecureRandom;
import java.util.HashSet;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.ensighten.M */
public final class C1712M {
    /* renamed from: a */
    public boolean f5673a;
    /* renamed from: b */
    public View f5674b;
    /* renamed from: c */
    public int f5675c;
    /* renamed from: d */
    public View f5676d;
    /* renamed from: e */
    public int f5677e;
    /* renamed from: f */
    public JSONObject f5678f;
    /* renamed from: g */
    HashSet<String> f5679g;
    /* renamed from: h */
    Context f5680h;
    /* renamed from: i */
    public boolean f5681i;
    /* renamed from: j */
    public boolean f5682j;
    /* renamed from: k */
    public boolean f5683k;
    /* renamed from: l */
    private boolean f5684l;
    /* renamed from: m */
    private final long f5685m;
    /* renamed from: n */
    private Fragment f5686n;
    /* renamed from: o */
    private android.app.Fragment f5687o;
    /* renamed from: p */
    private CharSequence f5688p;
    /* renamed from: q */
    private Configuration f5689q;

    public C1712M() {
        this.f5684l = false;
        this.f5673a = false;
        this.f5685m = Runtime.getRuntime().maxMemory();
        this.f5675c = -1;
        this.f5677e = -1;
        this.f5688p = null;
        this.f5682j = false;
        this.f5683k = false;
        this.f5681i = true;
    }

    public C1712M(Context context, Configuration configuration) {
        this.f5684l = false;
        this.f5673a = false;
        this.f5685m = Runtime.getRuntime().maxMemory();
        this.f5675c = -1;
        this.f5677e = -1;
        this.f5688p = null;
        this.f5682j = false;
        this.f5683k = false;
        this.f5680h = context;
        this.f5689q = configuration;
        this.f5681i = false;
        this.f5679g = new HashSet();
        m7233c();
    }

    /* JADX WARNING: Missing block: B:28:?, code skipped:
            return null;
     */
    /* renamed from: a */
    private static android.content.res.XmlResourceParser m7221a(byte[] r4) {
        /*
        r0 = "android.content.res.XmlBlock";
        r0 = java.lang.Class.forName(r0);	 Catch:{ ClassNotFoundException -> 0x0037, IllegalAccessException -> 0x0043, IllegalArgumentException -> 0x004e, InstantiationException -> 0x0059, InvocationTargetException -> 0x0064, NoSuchMethodException -> 0x006f }
        r1 = 1;
        r1 = new java.lang.Class[r1];	 Catch:{ ClassNotFoundException -> 0x0037, IllegalAccessException -> 0x0043, IllegalArgumentException -> 0x004e, InstantiationException -> 0x0059, InvocationTargetException -> 0x0064, NoSuchMethodException -> 0x006f }
        r2 = 0;
        r3 = byte[].class;
        r1[r2] = r3;	 Catch:{ ClassNotFoundException -> 0x0037, IllegalAccessException -> 0x0043, IllegalArgumentException -> 0x004e, InstantiationException -> 0x0059, InvocationTargetException -> 0x0064, NoSuchMethodException -> 0x006f }
        r1 = r0.getDeclaredConstructor(r1);	 Catch:{ ClassNotFoundException -> 0x0037, IllegalAccessException -> 0x0043, IllegalArgumentException -> 0x004e, InstantiationException -> 0x0059, InvocationTargetException -> 0x0064, NoSuchMethodException -> 0x006f }
        r2 = 1;
        r1.setAccessible(r2);	 Catch:{ ClassNotFoundException -> 0x0037, IllegalAccessException -> 0x0043, IllegalArgumentException -> 0x004e, InstantiationException -> 0x0059, InvocationTargetException -> 0x0064, NoSuchMethodException -> 0x006f }
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ ClassNotFoundException -> 0x0037, IllegalAccessException -> 0x0043, IllegalArgumentException -> 0x004e, InstantiationException -> 0x0059, InvocationTargetException -> 0x0064, NoSuchMethodException -> 0x006f }
        r3 = 0;
        r2[r3] = r4;	 Catch:{ ClassNotFoundException -> 0x0037, IllegalAccessException -> 0x0043, IllegalArgumentException -> 0x004e, InstantiationException -> 0x0059, InvocationTargetException -> 0x0064, NoSuchMethodException -> 0x006f }
        r1 = r1.newInstance(r2);	 Catch:{ ClassNotFoundException -> 0x0037, IllegalAccessException -> 0x0043, IllegalArgumentException -> 0x004e, InstantiationException -> 0x0059, InvocationTargetException -> 0x0064, NoSuchMethodException -> 0x006f }
        r2 = "newParser";
        r3 = 0;
        r3 = new java.lang.Class[r3];	 Catch:{ ClassNotFoundException -> 0x0037, IllegalAccessException -> 0x0043, IllegalArgumentException -> 0x004e, InstantiationException -> 0x0059, InvocationTargetException -> 0x0064, NoSuchMethodException -> 0x006f }
        r0 = r0.getDeclaredMethod(r2, r3);	 Catch:{ ClassNotFoundException -> 0x0037, IllegalAccessException -> 0x0043, IllegalArgumentException -> 0x004e, InstantiationException -> 0x0059, InvocationTargetException -> 0x0064, NoSuchMethodException -> 0x006f }
        r2 = 1;
        r0.setAccessible(r2);	 Catch:{ ClassNotFoundException -> 0x0037, IllegalAccessException -> 0x0043, IllegalArgumentException -> 0x004e, InstantiationException -> 0x0059, InvocationTargetException -> 0x0064, NoSuchMethodException -> 0x006f }
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ ClassNotFoundException -> 0x0037, IllegalAccessException -> 0x0043, IllegalArgumentException -> 0x004e, InstantiationException -> 0x0059, InvocationTargetException -> 0x0064, NoSuchMethodException -> 0x006f }
        r0 = r0.invoke(r1, r2);	 Catch:{ ClassNotFoundException -> 0x0037, IllegalAccessException -> 0x0043, IllegalArgumentException -> 0x004e, InstantiationException -> 0x0059, InvocationTargetException -> 0x0064, NoSuchMethodException -> 0x006f }
        r0 = (android.content.res.XmlResourceParser) r0;	 Catch:{ ClassNotFoundException -> 0x0037, IllegalAccessException -> 0x0043, IllegalArgumentException -> 0x004e, InstantiationException -> 0x0059, InvocationTargetException -> 0x0064, NoSuchMethodException -> 0x006f }
    L_0x0036:
        return r0;
    L_0x0037:
        r0 = move-exception;
        r1 = com.ensighten.C1845i.m7362i();
        if (r1 == 0) goto L_0x0041;
    L_0x003e:
        com.ensighten.C1845i.m7344a(r0);
    L_0x0041:
        r0 = 0;
        goto L_0x0036;
    L_0x0043:
        r0 = move-exception;
        r1 = com.ensighten.C1845i.m7362i();
        if (r1 == 0) goto L_0x0041;
    L_0x004a:
        com.ensighten.C1845i.m7344a(r0);
        goto L_0x0041;
    L_0x004e:
        r0 = move-exception;
        r1 = com.ensighten.C1845i.m7362i();
        if (r1 == 0) goto L_0x0041;
    L_0x0055:
        com.ensighten.C1845i.m7344a(r0);
        goto L_0x0041;
    L_0x0059:
        r0 = move-exception;
        r1 = com.ensighten.C1845i.m7362i();
        if (r1 == 0) goto L_0x0041;
    L_0x0060:
        com.ensighten.C1845i.m7344a(r0);
        goto L_0x0041;
    L_0x0064:
        r0 = move-exception;
        r1 = com.ensighten.C1845i.m7362i();
        if (r1 == 0) goto L_0x0041;
    L_0x006b:
        com.ensighten.C1845i.m7344a(r0);
        goto L_0x0041;
    L_0x006f:
        r0 = move-exception;
        r1 = com.ensighten.C1845i.m7362i();
        if (r1 == 0) goto L_0x0041;
    L_0x0076:
        com.ensighten.C1845i.m7344a(r0);
        goto L_0x0041;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ensighten.C1712M.m7221a(byte[]):android.content.res.XmlResourceParser");
    }

    /* JADX WARNING: Removed duplicated region for block: B:281:0x0465 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x01bf A:{Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }} */
    /* JADX WARNING: Missing block: B:97:0x02c5, code skipped:
            if (r36.equals("getView") != false) goto L_0x02c7;
     */
    /* renamed from: a */
    public final void mo15045a(android.app.Activity r34, java.lang.String r35, java.lang.String r36) {
        /*
        r33 = this;
        r2 = android.os.Build.VERSION.SDK_INT;
        r3 = 11;
        if (r2 >= r3) goto L_0x0022;
    L_0x0006:
        r2 = com.ensighten.C1845i.m7362i();
        if (r2 == 0) goto L_0x0021;
    L_0x000c:
        r2 = "Not running optimization on device with Android API %d.";
        r3 = 1;
        r3 = new java.lang.Object[r3];
        r4 = 0;
        r5 = android.os.Build.VERSION.SDK_INT;
        r5 = java.lang.Integer.valueOf(r5);
        r3[r4] = r5;
        r2 = java.lang.String.format(r2, r3);
        com.ensighten.C1845i.m7345a(r2);
    L_0x0021:
        return;
    L_0x0022:
        r0 = r33;
        r2 = r0.f5685m;
        r4 = 33554432; // 0x2000000 float:9.403955E-38 double:1.6578092E-316;
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 >= 0) goto L_0x0058;
    L_0x002d:
        r2 = com.ensighten.C1845i.m7362i();
        if (r2 == 0) goto L_0x0021;
    L_0x0033:
        r2 = "Not running optimization on device with total memory %d MB that is less than the required %d MB.";
        r3 = 2;
        r3 = new java.lang.Object[r3];
        r4 = 0;
        r0 = r33;
        r6 = r0.f5685m;
        r8 = 1048576; // 0x100000 float:1.469368E-39 double:5.180654E-318;
        r6 = r6 / r8;
        r5 = java.lang.Long.valueOf(r6);
        r3[r4] = r5;
        r4 = 1;
        r6 = 32;
        r5 = java.lang.Long.valueOf(r6);
        r3[r4] = r5;
        r2 = java.lang.String.format(r2, r3);
        com.ensighten.C1845i.m7345a(r2);
        goto L_0x0021;
    L_0x0058:
        r2 = 0;
        r0 = r33;
        r0.f5684l = r2;
        r2 = 0;
        r0 = r33;
        r0.f5673a = r2;
        r0 = r33;
        r2 = r0.f5678f;
        if (r2 != 0) goto L_0x0074;
    L_0x0068:
        r2 = com.ensighten.C1845i.m7362i();
        if (r2 == 0) goto L_0x0021;
    L_0x006e:
        r2 = "The optimization configuratino is null.";
        com.ensighten.C1845i.m7345a(r2);
        goto L_0x0021;
    L_0x0074:
        r0 = r33;
        r2 = r0.f5678f;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = r2.length();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r0 = r33;
        r3 = r0.f5678f;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r15 = r3.keys();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r14 = r2;
    L_0x0085:
        if (r14 <= 0) goto L_0x0021;
    L_0x0087:
        r2 = r15.next();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = (java.lang.String) r2;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r6 = r2.toString();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r0 = r35;
        r2 = r6.equals(r0);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r2 == 0) goto L_0x0593;
    L_0x0099:
        r0 = r33;
        r2 = r0.f5678f;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r16 = r2.getJSONObject(r6);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = r16.length();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r17 = r16.keys();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r13 = r2;
    L_0x00aa:
        if (r13 <= 0) goto L_0x0593;
    L_0x00ac:
        r2 = r17.next();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = (java.lang.String) r2;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r7 = r2.toString();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r0 = r36;
        r2 = r7.equals(r0);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r2 == 0) goto L_0x058e;
    L_0x00be:
        r0 = r16;
        r2 = r0.getJSONObject(r7);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r3 = "tests";
        r18 = r2.getJSONObject(r3);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = r18.length();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r19 = r18.keys();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r12 = r2;
    L_0x00d3:
        if (r12 <= 0) goto L_0x058e;
    L_0x00d5:
        r2 = r19.next();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = (java.lang.String) r2;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r8 = r2.toString();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r0 = r18;
        r2 = r0.getJSONObject(r8);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r3 = "enabled";
        r2 = r2.getBoolean(r3);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r2 == 0) goto L_0x0589;
    L_0x00ed:
        r0 = r18;
        r2 = r0.getJSONObject(r8);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r3 = "elements";
        r20 = r2.getJSONObject(r3);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = r20.length();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r21 = com.ensighten.Ensighten.getViewManager();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r22 = r20.keys();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r11 = r2;
    L_0x0106:
        if (r11 <= 0) goto L_0x0589;
    L_0x0108:
        r2 = r22.next();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = (java.lang.String) r2;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r3 = r2.toString();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = 0;
        r0 = r33;
        r0.f5686n = r2;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = 0;
        r0 = r33;
        r0.f5687o = r2;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r0 = r20;
        r4 = r0.getJSONObject(r3);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = "fragment";
        r2 = r4.has(r2);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r2 == 0) goto L_0x0151;
    L_0x012a:
        r2 = "fragment";
        r2 = r4.getString(r2);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r0 = r21;
        r5 = r0.mo15087b(r2);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = android.support.p000v4.app.FragmentActivity.class;
        r0 = r34;
        r2 = r2.isInstance(r0);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r2 == 0) goto L_0x022c;
    L_0x0140:
        r0 = r34;
        r0 = (android.support.p000v4.app.FragmentActivity) r0;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = r0;
        r2 = r2.getSupportFragmentManager();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = r2.findFragmentById(r5);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r0 = r33;
        r0.f5686n = r2;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
    L_0x0151:
        r2 = com.ensighten.Ensighten.getViewManager();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r5 = r2.mo15082a(r3);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r5 == 0) goto L_0x0584;
    L_0x015b:
        r2 = "actions";
        r23 = r4.getJSONArray(r2);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r24 = r23.length();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = 0;
        r10 = r2;
    L_0x0167:
        r0 = r24;
        if (r10 >= r0) goto L_0x0584;
    L_0x016b:
        r0 = r23;
        r9 = r0.getJSONObject(r10);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = "type";
        r25 = r9.getString(r2);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r3 = 0;
        r26 = com.ensighten.Ensighten.getViewManager();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r0 = r26;
        r0 = r0.f5780a;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r27 = r0;
        r2 = "qualifiers";
        r2 = r9.has(r2);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r2 == 0) goto L_0x042a;
    L_0x018b:
        r2 = "qualifiers";
        r28 = r9.getJSONObject(r2);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = r28.length();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r29 = r28.keys();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r4 = r2;
    L_0x019a:
        if (r4 <= 0) goto L_0x042a;
    L_0x019c:
        r2 = r29.next();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = (java.lang.String) r2;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = r2.toString();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r0 = r28;
        r30 = r0.getString(r2);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r31 = "densityDpi";
        r0 = r31;
        r31 = r2.equals(r0);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r31 == 0) goto L_0x0275;
    L_0x01b6:
        r30 = com.ensighten.C1712M.m7232b(r30);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r30 != 0) goto L_0x0246;
    L_0x01bc:
        r2 = 0;
    L_0x01bd:
        if (r2 == 0) goto L_0x0465;
    L_0x01bf:
        r2 = "append";
        r0 = r25;
        r2 = r0.equals(r2);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r2 == 0) goto L_0x046a;
    L_0x01c9:
        r2 = "viewClass";
        r4 = r9.getString(r2);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = "data";
        r9 = r9.getJSONObject(r2);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r3 = r9.length();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r0 = r33;
        r25 = r0.m7231b(r5);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r25 == 0) goto L_0x0021;
    L_0x01e2:
        r2 = r25.getParent();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = (android.view.ViewGroup) r2;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r2 == 0) goto L_0x0021;
    L_0x01ea:
        r5 = android.widget.AdapterView.class;
        r5 = r5.isInstance(r2);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r5 != 0) goto L_0x0021;
    L_0x01f2:
        r0 = r25;
        r26 = r2.indexOfChild(r0);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r0 = r25;
        r4 = com.ensighten.C1712M.m7225a(r4, r0);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r0 = r26;
        r2.addView(r4, r0);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r5 = android.os.Build.VERSION.SDK_INT;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r27 = 17;
        r0 = r27;
        if (r5 < r0) goto L_0x0453;
    L_0x020b:
        r5 = android.view.View.generateViewId();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
    L_0x020f:
        r4.setId(r5);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r27 = r9.keys();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r4 = r3;
    L_0x0217:
        if (r4 <= 0) goto L_0x0459;
    L_0x0219:
        r3 = r27.next();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r3 = (java.lang.String) r3;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r3 = r3.toString();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r0 = r33;
        r0.m7219a(r3, r9, r5);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r3 = r4 + -1;
        r4 = r3;
        goto L_0x0217;
    L_0x022c:
        r2 = r34.getFragmentManager();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = r2.findFragmentById(r5);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r0 = r33;
        r0.f5687o = r2;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        goto L_0x0151;
    L_0x023a:
        r2 = move-exception;
        r3 = com.ensighten.C1845i.m7362i();
        if (r3 == 0) goto L_0x0021;
    L_0x0241:
        com.ensighten.C1845i.m7344a(r2);
        goto L_0x0021;
    L_0x0246:
        r2 = android.os.Build.VERSION.SDK_INT;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r31 = 17;
        r0 = r31;
        if (r2 < r0) goto L_0x025f;
    L_0x024e:
        r0 = r33;
        r2 = r0.f5689q;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = r2.densityDpi;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
    L_0x0254:
        r30 = r30.intValue();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r0 = r30;
        if (r0 == r2) goto L_0x026e;
    L_0x025c:
        r2 = 0;
        goto L_0x01bd;
    L_0x025f:
        r2 = r26.mo15088c();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = r2.getResources();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = r2.getDisplayMetrics();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = r2.densityDpi;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        goto L_0x0254;
    L_0x026e:
        r2 = r3;
    L_0x026f:
        r3 = r4 + -1;
        r4 = r3;
        r3 = r2;
        goto L_0x019a;
    L_0x0275:
        r31 = "orientation";
        r0 = r31;
        r31 = r2.equals(r0);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r31 == 0) goto L_0x02a9;
    L_0x027f:
        r0 = r33;
        r2 = r0.f5689q;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = r2.orientation;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r31 = "landscape";
        r31 = r30.equals(r31);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r31 == 0) goto L_0x0296;
    L_0x028d:
        r31 = 2;
        r0 = r31;
        if (r2 == r0) goto L_0x0296;
    L_0x0293:
        r2 = 0;
        goto L_0x01bd;
    L_0x0296:
        r31 = "portrait";
        r30 = r30.equals(r31);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r30 == 0) goto L_0x02a7;
    L_0x029e:
        r30 = 1;
        r0 = r30;
        if (r2 == r0) goto L_0x02a7;
    L_0x02a4:
        r2 = 0;
        goto L_0x01bd;
    L_0x02a7:
        r2 = r3;
        goto L_0x026f;
    L_0x02a9:
        r31 = "position";
        r0 = r31;
        r31 = r2.equals(r0);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r31 == 0) goto L_0x030d;
    L_0x02b3:
        r2 = "getPageTitle";
        r0 = r36;
        r2 = r0.equals(r2);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r2 != 0) goto L_0x02c7;
    L_0x02bd:
        r2 = "getView";
        r0 = r36;
        r2 = r0.equals(r2);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r2 == 0) goto L_0x05ec;
    L_0x02c7:
        r2 = com.ensighten.C1733V.ADAPTER;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r0 = r27;
        if (r0 == r2) goto L_0x02d3;
    L_0x02cd:
        r2 = com.ensighten.C1733V.PAGER_ADAPTER;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r0 = r27;
        if (r0 != r2) goto L_0x05ec;
    L_0x02d3:
        r2 = "*";
        r0 = r30;
        r31 = r0.equals(r2);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r31 == 0) goto L_0x02e9;
    L_0x02dd:
        r2 = -1;
    L_0x02de:
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r31 != 0) goto L_0x02f2;
    L_0x02e4:
        if (r2 != 0) goto L_0x02f2;
    L_0x02e6:
        r2 = 0;
        goto L_0x01bd;
    L_0x02e9:
        r2 = com.ensighten.C1712M.m7232b(r30);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = r2.intValue();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        goto L_0x02de;
    L_0x02f2:
        if (r31 != 0) goto L_0x0305;
    L_0x02f4:
        r2 = r2.intValue();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r0 = r26;
        r0 = r0.f5790k;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r30 = r0;
        r0 = r30;
        if (r2 == r0) goto L_0x0305;
    L_0x0302:
        r2 = 0;
        goto L_0x01bd;
    L_0x0305:
        r2 = 1;
        r0 = r33;
        r0.f5684l = r2;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = r3;
        goto L_0x026f;
    L_0x030d:
        r31 = "childPosition";
        r0 = r31;
        r31 = r2.equals(r0);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r31 == 0) goto L_0x0369;
    L_0x0317:
        r2 = "getChildView";
        r0 = r36;
        r2 = r0.equals(r2);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r2 == 0) goto L_0x05ec;
    L_0x0321:
        r2 = com.ensighten.C1733V.EXPANDABLE_LIST_ADAPTER;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r0 = r27;
        if (r0 != r2) goto L_0x05ec;
    L_0x0327:
        r2 = "*";
        r0 = r30;
        r31 = r0.equals(r2);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r31 == 0) goto L_0x0339;
    L_0x0331:
        r2 = 0;
    L_0x0332:
        if (r31 != 0) goto L_0x033e;
    L_0x0334:
        if (r2 != 0) goto L_0x033e;
    L_0x0336:
        r2 = 0;
        goto L_0x01bd;
    L_0x0339:
        r2 = com.ensighten.C1712M.m7232b(r30);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        goto L_0x0332;
    L_0x033e:
        if (r31 != 0) goto L_0x0353;
    L_0x0340:
        r30 = r2.intValue();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r0 = r33;
        r0 = r0.f5675c;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r32 = r0;
        r0 = r30;
        r1 = r32;
        if (r0 == r1) goto L_0x0353;
    L_0x0350:
        r2 = 0;
        goto L_0x01bd;
    L_0x0353:
        r30 = 1;
        r0 = r30;
        r1 = r33;
        r1.f5682j = r0;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r31 == 0) goto L_0x0364;
    L_0x035d:
        r2 = -1;
    L_0x035e:
        java.lang.Integer.valueOf(r2);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = r3;
        goto L_0x026f;
    L_0x0364:
        r2 = r2.intValue();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        goto L_0x035e;
    L_0x0369:
        r31 = "groupPosition";
        r0 = r31;
        r31 = r2.equals(r0);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r31 == 0) goto L_0x03bb;
    L_0x0373:
        r2 = com.ensighten.C1733V.EXPANDABLE_LIST_ADAPTER;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r0 = r27;
        if (r0 != r2) goto L_0x05ec;
    L_0x0379:
        r2 = "*";
        r0 = r30;
        r3 = r0.equals(r2);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r3 == 0) goto L_0x038b;
    L_0x0383:
        r2 = 0;
    L_0x0384:
        if (r3 != 0) goto L_0x0390;
    L_0x0386:
        if (r2 != 0) goto L_0x0390;
    L_0x0388:
        r2 = 0;
        goto L_0x01bd;
    L_0x038b:
        r2 = com.ensighten.C1712M.m7232b(r30);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        goto L_0x0384;
    L_0x0390:
        if (r3 != 0) goto L_0x03a5;
    L_0x0392:
        r30 = r2.intValue();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r0 = r33;
        r0 = r0.f5677e;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r31 = r0;
        r0 = r30;
        r1 = r31;
        if (r0 == r1) goto L_0x03a5;
    L_0x03a2:
        r2 = 0;
        goto L_0x01bd;
    L_0x03a5:
        r30 = 1;
        r0 = r30;
        r1 = r33;
        r1.f5683k = r0;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r3 == 0) goto L_0x03b6;
    L_0x03af:
        r2 = -1;
    L_0x03b0:
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        goto L_0x026f;
    L_0x03b6:
        r2 = r2.intValue();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        goto L_0x03b0;
    L_0x03bb:
        r31 = "smallestScreenWidthDp";
        r0 = r31;
        r2 = r2.equals(r0);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r2 == 0) goto L_0x05ec;
    L_0x03c5:
        r2 = com.ensighten.C1712M.m7232b(r30);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r2 != 0) goto L_0x03ce;
    L_0x03cb:
        r2 = 0;
        goto L_0x01bd;
    L_0x03ce:
        r30 = android.os.Build.VERSION.SDK_INT;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r31 = 13;
        r0 = r30;
        r1 = r31;
        if (r0 < r1) goto L_0x03f2;
    L_0x03d8:
        r0 = r33;
        r0 = r0.f5689q;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r30 = r0;
        r0 = r30;
        r0 = r0.smallestScreenWidthDp;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r30 = r0;
        r2 = r2.intValue();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r0 = r30;
        if (r2 == r0) goto L_0x03ef;
    L_0x03ec:
        r2 = 0;
        goto L_0x01bd;
    L_0x03ef:
        r2 = r3;
        goto L_0x026f;
    L_0x03f2:
        r30 = r26.mo15088c();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r30 = r30.getResources();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r30 = r30.getDisplayMetrics();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r0 = r30;
        r0 = r0.widthPixels;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r31 = r0;
        r0 = r30;
        r0 = r0.heightPixels;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r32 = r0;
        r31 = java.lang.Math.min(r31, r32);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r0 = r31;
        r0 = (float) r0;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r31 = r0;
        r0 = r30;
        r0 = r0.density;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r30 = r0;
        r30 = r31 / r30;
        r30 = java.lang.Math.round(r30);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = r2.intValue();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r0 = r30;
        if (r2 == r0) goto L_0x05ec;
    L_0x0427:
        r2 = 0;
        goto L_0x01bd;
    L_0x042a:
        r0 = r33;
        r2 = r0.f5682j;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r2 == 0) goto L_0x0450;
    L_0x0430:
        r2 = "getChildView";
        r0 = r36;
        r2 = r0.equals(r2);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r2 == 0) goto L_0x0450;
    L_0x043a:
        if (r3 == 0) goto L_0x0450;
    L_0x043c:
        r2 = r3.intValue();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r4 = -1;
        if (r2 == r4) goto L_0x0450;
    L_0x0443:
        r2 = r3.intValue();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r0 = r33;
        r3 = r0.f5677e;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r2 == r3) goto L_0x0450;
    L_0x044d:
        r2 = 0;
        goto L_0x01bd;
    L_0x0450:
        r2 = 1;
        goto L_0x01bd;
    L_0x0453:
        r5 = com.ensighten.C1720Q.m7254a();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        goto L_0x020f;
    L_0x0459:
        r0 = r25;
        r2.removeView(r0);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r0 = r25;
        r1 = r26;
        r2.addView(r0, r1);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
    L_0x0465:
        r2 = r10 + 1;
        r10 = r2;
        goto L_0x0167;
    L_0x046a:
        r2 = "hide";
        r0 = r25;
        r2 = r0.equals(r2);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r2 == 0) goto L_0x04b1;
    L_0x0474:
        r0 = r33;
        r2 = r0.f5684l;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r2 == 0) goto L_0x04aa;
    L_0x047a:
        r2 = com.ensighten.Ensighten.getViewManager();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = r2.f5784e;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
    L_0x0480:
        r0 = r33;
        r3 = r0.f5684l;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r3 == 0) goto L_0x0496;
    L_0x0486:
        r0 = r33;
        r3 = r0.f5673a;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r3 == 0) goto L_0x0496;
    L_0x048c:
        if (r2 == 0) goto L_0x0496;
    L_0x048e:
        r3 = r2 instanceof android.view.ViewGroup;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r3 == 0) goto L_0x0496;
    L_0x0492:
        r2 = r2.findViewById(r5);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
    L_0x0496:
        if (r2 == 0) goto L_0x0021;
    L_0x0498:
        r3 = 8;
        r2.setVisibility(r3);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        goto L_0x0465;
    L_0x049e:
        r2 = move-exception;
        r3 = com.ensighten.C1845i.m7362i();
        if (r3 == 0) goto L_0x0021;
    L_0x04a5:
        com.ensighten.C1845i.m7344a(r2);
        goto L_0x0021;
    L_0x04aa:
        r0 = r33;
        r2 = r0.m7231b(r5);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        goto L_0x0480;
    L_0x04b1:
        r2 = "inflate";
        r0 = r25;
        r2 = r0.equals(r2);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r2 == 0) goto L_0x04ef;
    L_0x04bb:
        r2 = "data";
        r4 = r9.getJSONObject(r2);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = r4.length();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r9 = r4.keys();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r3 = r2;
    L_0x04ca:
        if (r3 <= 0) goto L_0x0465;
    L_0x04cc:
        r2 = r9.next();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = (java.lang.String) r2;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = r2.toString();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r25 = "layout";
        r0 = r25;
        r2 = r2.equals(r0);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r2 == 0) goto L_0x04eb;
    L_0x04e0:
        r2 = "layout";
        r2 = r4.getJSONObject(r2);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r0 = r33;
        r0.m7227a(r5, r2);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
    L_0x04eb:
        r2 = r3 + -1;
        r3 = r2;
        goto L_0x04ca;
    L_0x04ef:
        r2 = "modify";
        r0 = r25;
        r2 = r0.equals(r2);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r2 == 0) goto L_0x051d;
    L_0x04f9:
        r2 = "data";
        r4 = r9.getJSONObject(r2);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = r4.length();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r25 = r4.keys();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r9 = r2;
    L_0x0508:
        if (r9 <= 0) goto L_0x0465;
    L_0x050a:
        r2 = r25.next();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = (java.lang.String) r2;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r3 = r2.toString();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = r33;
        r2.m7220a(r3, r4, r5, r6, r7, r8);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = r9 + -1;
        r9 = r2;
        goto L_0x0508;
    L_0x051d:
        r2 = "replace";
        r0 = r25;
        r2 = r0.equals(r2);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r2 == 0) goto L_0x0465;
    L_0x0527:
        r2 = "viewClass";
        r4 = r9.getString(r2);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = "data";
        r9 = r9.getJSONObject(r2);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r3 = r9.length();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r0 = r33;
        r25 = r0.m7231b(r5);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r25 == 0) goto L_0x0021;
    L_0x0540:
        r2 = r25.getParent();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = (android.view.ViewGroup) r2;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r2 == 0) goto L_0x0021;
    L_0x0548:
        r26 = android.widget.AdapterView.class;
        r0 = r26;
        r26 = r0.isInstance(r2);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        if (r26 != 0) goto L_0x0021;
    L_0x0552:
        r0 = r25;
        r26 = r2.indexOfChild(r0);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r0 = r25;
        r2.removeView(r0);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r0 = r25;
        r4 = com.ensighten.C1712M.m7225a(r4, r0);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r0 = r26;
        r2.addView(r4, r0);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r4.setId(r5);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r4 = r9.keys();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
    L_0x056f:
        if (r3 <= 0) goto L_0x0465;
    L_0x0571:
        r2 = r4.next();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = (java.lang.String) r2;	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = r2.toString();	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r0 = r33;
        r0.m7219a(r2, r9, r5);	 Catch:{ ClassCastException -> 0x023a, ClassNotFoundException -> 0x049e, JSONException -> 0x0598, IllegalAccessException -> 0x05a4, IllegalArgumentException -> 0x05b0, InstantiationException -> 0x05bc, InvocationTargetException -> 0x05c8, NoSuchMethodException -> 0x05d4, SecurityException -> 0x05e0 }
        r2 = r3 + -1;
        r3 = r2;
        goto L_0x056f;
    L_0x0584:
        r2 = r11 + -1;
        r11 = r2;
        goto L_0x0106;
    L_0x0589:
        r2 = r12 + -1;
        r12 = r2;
        goto L_0x00d3;
    L_0x058e:
        r2 = r13 + -1;
        r13 = r2;
        goto L_0x00aa;
    L_0x0593:
        r2 = r14 + -1;
        r14 = r2;
        goto L_0x0085;
    L_0x0598:
        r2 = move-exception;
        r3 = com.ensighten.C1845i.m7362i();
        if (r3 == 0) goto L_0x0021;
    L_0x059f:
        com.ensighten.C1845i.m7344a(r2);
        goto L_0x0021;
    L_0x05a4:
        r2 = move-exception;
        r3 = com.ensighten.C1845i.m7362i();
        if (r3 == 0) goto L_0x0021;
    L_0x05ab:
        com.ensighten.C1845i.m7344a(r2);
        goto L_0x0021;
    L_0x05b0:
        r2 = move-exception;
        r3 = com.ensighten.C1845i.m7362i();
        if (r3 == 0) goto L_0x0021;
    L_0x05b7:
        com.ensighten.C1845i.m7344a(r2);
        goto L_0x0021;
    L_0x05bc:
        r2 = move-exception;
        r3 = com.ensighten.C1845i.m7362i();
        if (r3 == 0) goto L_0x0021;
    L_0x05c3:
        com.ensighten.C1845i.m7344a(r2);
        goto L_0x0021;
    L_0x05c8:
        r2 = move-exception;
        r3 = com.ensighten.C1845i.m7362i();
        if (r3 == 0) goto L_0x0021;
    L_0x05cf:
        com.ensighten.C1845i.m7344a(r2);
        goto L_0x0021;
    L_0x05d4:
        r2 = move-exception;
        r3 = com.ensighten.C1845i.m7362i();
        if (r3 == 0) goto L_0x0021;
    L_0x05db:
        com.ensighten.C1845i.m7344a(r2);
        goto L_0x0021;
    L_0x05e0:
        r2 = move-exception;
        r3 = com.ensighten.C1845i.m7362i();
        if (r3 == 0) goto L_0x0021;
    L_0x05e7:
        com.ensighten.C1845i.m7344a(r2);
        goto L_0x0021;
    L_0x05ec:
        r2 = r3;
        goto L_0x026f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ensighten.C1712M.mo15045a(android.app.Activity, java.lang.String, java.lang.String):void");
    }

    /* renamed from: a */
    private static View m7225a(String str, View view) throws ClassCastException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        return (View) Class.forName(str).getConstructor(new Class[]{Context.class}).newInstance(new Object[]{view.getContext()});
    }

    /* JADX WARNING: Removed duplicated region for block: B:62:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00a3 A:{Catch:{ all -> 0x0110 }} */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00a8 A:{SYNTHETIC, Splitter:B:45:0x00a8} */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00ad A:{SYNTHETIC, Splitter:B:48:0x00ad} */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00bd A:{SYNTHETIC, Splitter:B:56:0x00bd} */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00c2 A:{SYNTHETIC, Splitter:B:59:0x00c2} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0082 A:{Catch:{ all -> 0x0115 }} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0087 A:{SYNTHETIC, Splitter:B:29:0x0087} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x008c A:{SYNTHETIC, Splitter:B:32:0x008c} */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00a3 A:{Catch:{ all -> 0x0110 }} */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00a8 A:{SYNTHETIC, Splitter:B:45:0x00a8} */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00ad A:{SYNTHETIC, Splitter:B:48:0x00ad} */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00bd A:{SYNTHETIC, Splitter:B:56:0x00bd} */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00c2 A:{SYNTHETIC, Splitter:B:59:0x00c2} */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00bd A:{SYNTHETIC, Splitter:B:56:0x00bd} */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00c2 A:{SYNTHETIC, Splitter:B:59:0x00c2} */
    /* JADX WARNING: Missing block: B:36:0x0095, code skipped:
            if (com.ensighten.C1845i.m7362i() == false) goto L_0x0066;
     */
    /* JADX WARNING: Missing block: B:37:0x0097, code skipped:
            com.ensighten.C1845i.m7344a(r1);
     */
    /* JADX WARNING: Missing block: B:52:0x00b6, code skipped:
            if (com.ensighten.C1845i.m7362i() == false) goto L_0x0066;
     */
    /* JADX WARNING: Missing block: B:86:0x010d, code skipped:
            if (com.ensighten.C1845i.m7362i() == false) goto L_0x0066;
     */
    /* renamed from: a */
    private boolean m7228a(org.json.JSONObject r11, java.lang.String r12) throws org.json.JSONException {
        /*
        r10 = this;
        r3 = 0;
        r0 = 0;
        r1 = "value";
        r1 = r11.getString(r1);
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r4 = r10.f5680h;
        r4 = r4.getFilesDir();
        r4 = r4.getPath();
        r2 = r2.append(r4);
        r4 = "/";
        r2 = r2.append(r4);
        r2 = r2.toString();
        r4 = new java.io.File;
        r4.<init>(r2);
        r4 = com.ensighten.C1715O.m7250a(r4);
        r2 = r1.length();
        r6 = (long) r2;
        r8 = 4;
        r6 = r6 + r8;
        r8 = com.ensighten.C1719P.m7253a(r1, r0);
        r1 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1));
        if (r1 <= 0) goto L_0x004b;
    L_0x003f:
        r1 = com.ensighten.C1845i.m7362i();
        if (r1 == 0) goto L_0x004a;
    L_0x0045:
        r1 = "Not writing file to storage due to insufficient space on disk.";
        com.ensighten.C1845i.m7345a(r1);
    L_0x004a:
        return r0;
    L_0x004b:
        r1 = 1;
        r2 = r10.f5680h;	 Catch:{ FileNotFoundException -> 0x007a, IOException -> 0x009b, all -> 0x00b9 }
        r4 = 0;
        r4 = r2.openFileOutput(r12, r4);	 Catch:{ FileNotFoundException -> 0x007a, IOException -> 0x009b, all -> 0x00b9 }
        r2 = new java.io.BufferedOutputStream;	 Catch:{ FileNotFoundException -> 0x011e, IOException -> 0x0119 }
        r2.<init>(r4);	 Catch:{ FileNotFoundException -> 0x011e, IOException -> 0x0119 }
        r2.write(r8);	 Catch:{ FileNotFoundException -> 0x0123, IOException -> 0x011b, all -> 0x0112 }
        if (r2 == 0) goto L_0x0060;
    L_0x005d:
        r2.close();	 Catch:{ IOException -> 0x00fb }
    L_0x0060:
        if (r4 == 0) goto L_0x0127;
    L_0x0062:
        r4.close();	 Catch:{ IOException -> 0x0108 }
        r0 = r1;
    L_0x0066:
        if (r0 == 0) goto L_0x00c6;
    L_0x0068:
        r1 = "type";
        r2 = "file";
        r11.put(r1, r2);
        r1 = "value";
        r11.put(r1, r12);
        r10.mo15044a();
        goto L_0x004a;
    L_0x007a:
        r1 = move-exception;
        r2 = r3;
    L_0x007c:
        r4 = com.ensighten.C1845i.m7362i();	 Catch:{ all -> 0x0115 }
        if (r4 == 0) goto L_0x0085;
    L_0x0082:
        com.ensighten.C1845i.m7344a(r1);	 Catch:{ all -> 0x0115 }
    L_0x0085:
        if (r2 == 0) goto L_0x008a;
    L_0x0087:
        r2.close();	 Catch:{ IOException -> 0x00e5 }
    L_0x008a:
        if (r3 == 0) goto L_0x0066;
    L_0x008c:
        r3.close();	 Catch:{ IOException -> 0x0090 }
        goto L_0x0066;
    L_0x0090:
        r1 = move-exception;
        r2 = com.ensighten.C1845i.m7362i();
        if (r2 == 0) goto L_0x0066;
    L_0x0097:
        com.ensighten.C1845i.m7344a(r1);
        goto L_0x0066;
    L_0x009b:
        r1 = move-exception;
        r4 = r3;
    L_0x009d:
        r2 = com.ensighten.C1845i.m7362i();	 Catch:{ all -> 0x0110 }
        if (r2 == 0) goto L_0x00a6;
    L_0x00a3:
        com.ensighten.C1845i.m7344a(r1);	 Catch:{ all -> 0x0110 }
    L_0x00a6:
        if (r3 == 0) goto L_0x00ab;
    L_0x00a8:
        r3.close();	 Catch:{ IOException -> 0x00f0 }
    L_0x00ab:
        if (r4 == 0) goto L_0x0066;
    L_0x00ad:
        r4.close();	 Catch:{ IOException -> 0x00b1 }
        goto L_0x0066;
    L_0x00b1:
        r1 = move-exception;
        r2 = com.ensighten.C1845i.m7362i();
        if (r2 == 0) goto L_0x0066;
    L_0x00b8:
        goto L_0x0097;
    L_0x00b9:
        r0 = move-exception;
        r4 = r3;
    L_0x00bb:
        if (r3 == 0) goto L_0x00c0;
    L_0x00bd:
        r3.close();	 Catch:{ IOException -> 0x00cf }
    L_0x00c0:
        if (r4 == 0) goto L_0x00c5;
    L_0x00c2:
        r4.close();	 Catch:{ IOException -> 0x00da }
    L_0x00c5:
        throw r0;
    L_0x00c6:
        r1 = com.ensighten.Ensighten.getContext();
        r1.deleteFile(r12);
        goto L_0x004a;
    L_0x00cf:
        r1 = move-exception;
        r2 = com.ensighten.C1845i.m7362i();
        if (r2 == 0) goto L_0x00c0;
    L_0x00d6:
        com.ensighten.C1845i.m7344a(r1);
        goto L_0x00c0;
    L_0x00da:
        r1 = move-exception;
        r2 = com.ensighten.C1845i.m7362i();
        if (r2 == 0) goto L_0x00c5;
    L_0x00e1:
        com.ensighten.C1845i.m7344a(r1);
        goto L_0x00c5;
    L_0x00e5:
        r1 = move-exception;
        r2 = com.ensighten.C1845i.m7362i();
        if (r2 == 0) goto L_0x008a;
    L_0x00ec:
        com.ensighten.C1845i.m7344a(r1);
        goto L_0x008a;
    L_0x00f0:
        r1 = move-exception;
        r2 = com.ensighten.C1845i.m7362i();
        if (r2 == 0) goto L_0x00ab;
    L_0x00f7:
        com.ensighten.C1845i.m7344a(r1);
        goto L_0x00ab;
    L_0x00fb:
        r1 = move-exception;
        r2 = com.ensighten.C1845i.m7362i();
        if (r2 == 0) goto L_0x0105;
    L_0x0102:
        com.ensighten.C1845i.m7344a(r1);
    L_0x0105:
        r1 = r0;
        goto L_0x0060;
    L_0x0108:
        r1 = move-exception;
        r2 = com.ensighten.C1845i.m7362i();
        if (r2 == 0) goto L_0x0066;
    L_0x010f:
        goto L_0x0097;
    L_0x0110:
        r0 = move-exception;
        goto L_0x00bb;
    L_0x0112:
        r0 = move-exception;
        r3 = r2;
        goto L_0x00bb;
    L_0x0115:
        r0 = move-exception;
        r4 = r3;
        r3 = r2;
        goto L_0x00bb;
    L_0x0119:
        r1 = move-exception;
        goto L_0x009d;
    L_0x011b:
        r1 = move-exception;
        r3 = r2;
        goto L_0x009d;
    L_0x011e:
        r1 = move-exception;
        r2 = r3;
        r3 = r4;
        goto L_0x007c;
    L_0x0123:
        r1 = move-exception;
        r3 = r4;
        goto L_0x007c;
    L_0x0127:
        r0 = r1;
        goto L_0x0066;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ensighten.C1712M.m7228a(org.json.JSONObject, java.lang.String):boolean");
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:59:0x0110=Splitter:B:59:0x0110, B:72:0x0128=Splitter:B:72:0x0128, B:27:0x00c9=Splitter:B:27:0x00c9} */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x015c A:{SYNTHETIC, Splitter:B:98:0x015c} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00ec A:{Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00ec A:{Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }} */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x015c A:{SYNTHETIC, Splitter:B:98:0x015c} */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x015c A:{SYNTHETIC, Splitter:B:98:0x015c} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00ec A:{Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }} */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0146 A:{SYNTHETIC, Splitter:B:86:0x0146} */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x014b A:{SYNTHETIC, Splitter:B:89:0x014b} */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0116 A:{Catch:{ all -> 0x01e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x011b A:{SYNTHETIC, Splitter:B:64:0x011b} */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0120 A:{SYNTHETIC, Splitter:B:67:0x0120} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00ec A:{Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }} */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x015c A:{SYNTHETIC, Splitter:B:98:0x015c} */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x012e A:{Catch:{ all -> 0x01e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0137 A:{SYNTHETIC, Splitter:B:77:0x0137} */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x013c A:{SYNTHETIC, Splitter:B:80:0x013c} */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x015c A:{SYNTHETIC, Splitter:B:98:0x015c} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00ec A:{Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }} */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00cf A:{Catch:{ all -> 0x01e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00d4 A:{SYNTHETIC, Splitter:B:32:0x00d4} */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00d9 A:{SYNTHETIC, Splitter:B:35:0x00d9} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00ec A:{Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }} */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x015c A:{SYNTHETIC, Splitter:B:98:0x015c} */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0116 A:{Catch:{ all -> 0x01e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x011b A:{SYNTHETIC, Splitter:B:64:0x011b} */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0120 A:{SYNTHETIC, Splitter:B:67:0x0120} */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x015c A:{SYNTHETIC, Splitter:B:98:0x015c} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00ec A:{Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }} */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x012e A:{Catch:{ all -> 0x01e7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0137 A:{SYNTHETIC, Splitter:B:77:0x0137} */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x013c A:{SYNTHETIC, Splitter:B:80:0x013c} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00ec A:{Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }} */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x015c A:{SYNTHETIC, Splitter:B:98:0x015c} */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0146 A:{SYNTHETIC, Splitter:B:86:0x0146} */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x014b A:{SYNTHETIC, Splitter:B:89:0x014b} */
    /* JADX WARNING: Missing block: B:164:?, code skipped:
            return false;
     */
    /* renamed from: a */
    private boolean m7227a(int r13, org.json.JSONObject r14) {
        /*
        r12 = this;
        r1 = 1;
        r4 = 0;
        r2 = 0;
        r0 = 16908290; // 0x1020002 float:2.3877235E-38 double:8.353805E-317;
        if (r13 != r0) goto L_0x0017;
    L_0x0008:
        r6 = r1;
    L_0x0009:
        r0 = r12.m7231b(r13);	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        r3 = android.view.ViewGroup.class;
        r3 = r3.isInstance(r0);	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        if (r3 != 0) goto L_0x0019;
    L_0x0015:
        r0 = r2;
    L_0x0016:
        return r0;
    L_0x0017:
        r6 = r2;
        goto L_0x0009;
    L_0x0019:
        r0 = (android.view.ViewGroup) r0;	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        r3 = "type";
        r3 = r14.getString(r3);	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        r5 = com.ensighten.Ensighten.getViewManager();	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        r7 = r5.mo15088c();	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        r5 = "base64";
        r5 = r3.equals(r5);	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        if (r5 == 0) goto L_0x0060;
    L_0x0032:
        r0 = r7.getClass();	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        r0 = r0.getSimpleName();	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        r1 = new java.lang.StringBuilder;	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        r1.<init>();	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        r0 = r1.append(r0);	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        r1 = "_";
        r0 = r0.append(r1);	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        r1 = java.lang.Integer.toHexString(r13);	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        r0 = r0.append(r1);	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        r1 = "_layout.bin";
        r0 = r0.append(r1);	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        r0 = r0.toString();	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        r0 = r12.m7228a(r14, r0);	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        goto L_0x0016;
    L_0x0060:
        r5 = "file";
        r3 = r3.equals(r5);	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        if (r3 == 0) goto L_0x015f;
    L_0x0068:
        r3 = "value";
        r3 = r14.getString(r3);	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        r5 = r7.getApplicationContext();	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        r8 = new java.lang.StringBuilder;	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        r8.<init>();	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        r9 = r5.getFilesDir();	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        r9 = r9.getPath();	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        r8 = r8.append(r9);	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        r9 = "/";
        r8 = r8.append(r9);	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        r8 = r8.toString();	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        r9 = new java.io.File;	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        r10 = new java.lang.StringBuilder;	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        r10.<init>();	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        r8 = r10.append(r8);	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        r8 = r8.append(r3);	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        r8 = r8.toString();	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        r9.<init>(r8);	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        r8 = r9.exists();	 Catch:{ FileNotFoundException -> 0x01f8, IOException -> 0x010d, OutOfMemoryError -> 0x0125, all -> 0x0141 }
        if (r8 == 0) goto L_0x0201;
    L_0x00aa:
        r5 = r5.openFileInput(r3);	 Catch:{ FileNotFoundException -> 0x01f8, IOException -> 0x010d, OutOfMemoryError -> 0x0125, all -> 0x0141 }
        r3 = new java.io.BufferedInputStream;	 Catch:{ FileNotFoundException -> 0x01fd, IOException -> 0x01f1, OutOfMemoryError -> 0x01ea, all -> 0x01e3 }
        r3.<init>(r5);	 Catch:{ FileNotFoundException -> 0x01fd, IOException -> 0x01f1, OutOfMemoryError -> 0x01ea, all -> 0x01e3 }
        r8 = new java.io.ByteArrayOutputStream;	 Catch:{ FileNotFoundException -> 0x00c8, IOException -> 0x01f5, OutOfMemoryError -> 0x01ee }
        r8.<init>();	 Catch:{ FileNotFoundException -> 0x00c8, IOException -> 0x01f5, OutOfMemoryError -> 0x01ee }
        r9 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r9 = new byte[r9];	 Catch:{ FileNotFoundException -> 0x00c8, IOException -> 0x01f5, OutOfMemoryError -> 0x01ee }
    L_0x00bc:
        r10 = r3.read(r9);	 Catch:{ FileNotFoundException -> 0x00c8, IOException -> 0x01f5, OutOfMemoryError -> 0x01ee }
        r11 = -1;
        if (r10 == r11) goto L_0x00f2;
    L_0x00c3:
        r11 = 0;
        r8.write(r9, r11, r10);	 Catch:{ FileNotFoundException -> 0x00c8, IOException -> 0x01f5, OutOfMemoryError -> 0x01ee }
        goto L_0x00bc;
    L_0x00c8:
        r1 = move-exception;
    L_0x00c9:
        r8 = com.ensighten.C1845i.m7362i();	 Catch:{ all -> 0x01e7 }
        if (r8 == 0) goto L_0x00d2;
    L_0x00cf:
        com.ensighten.C1845i.m7344a(r1);	 Catch:{ all -> 0x01e7 }
    L_0x00d2:
        if (r3 == 0) goto L_0x00d7;
    L_0x00d4:
        r3.close();	 Catch:{ IOException -> 0x018e }
    L_0x00d7:
        if (r5 == 0) goto L_0x00dc;
    L_0x00d9:
        r5.close();	 Catch:{ IOException -> 0x019a }
    L_0x00dc:
        r1 = r2;
    L_0x00dd:
        r3 = r7.getLayoutInflater();	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        r4 = com.ensighten.C1712M.m7221a(r4);	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        r5 = 0;
        r3 = r3.inflate(r4, r5);	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        if (r6 == 0) goto L_0x015c;
    L_0x00ec:
        r7.setContentView(r3);	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        r0 = r1;
        goto L_0x0016;
    L_0x00f2:
        r4 = r8.toByteArray();	 Catch:{ FileNotFoundException -> 0x00c8, IOException -> 0x01f5, OutOfMemoryError -> 0x01ee }
    L_0x00f6:
        if (r3 == 0) goto L_0x00fb;
    L_0x00f8:
        r3.close();	 Catch:{ IOException -> 0x01d6 }
    L_0x00fb:
        if (r5 == 0) goto L_0x00dd;
    L_0x00fd:
        r5.close();	 Catch:{ IOException -> 0x0101 }
        goto L_0x00dd;
    L_0x0101:
        r1 = move-exception;
        r3 = com.ensighten.C1845i.m7362i();	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        if (r3 == 0) goto L_0x010b;
    L_0x0108:
        com.ensighten.C1845i.m7344a(r1);	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
    L_0x010b:
        r1 = r2;
        goto L_0x00dd;
    L_0x010d:
        r1 = move-exception;
        r3 = r4;
        r5 = r4;
    L_0x0110:
        r8 = com.ensighten.C1845i.m7362i();	 Catch:{ all -> 0x01e7 }
        if (r8 == 0) goto L_0x0119;
    L_0x0116:
        com.ensighten.C1845i.m7344a(r1);	 Catch:{ all -> 0x01e7 }
    L_0x0119:
        if (r3 == 0) goto L_0x011e;
    L_0x011b:
        r3.close();	 Catch:{ IOException -> 0x01a6 }
    L_0x011e:
        if (r5 == 0) goto L_0x0123;
    L_0x0120:
        r5.close();	 Catch:{ IOException -> 0x01b2 }
    L_0x0123:
        r1 = r2;
        goto L_0x00dd;
    L_0x0125:
        r1 = move-exception;
        r3 = r4;
        r5 = r4;
    L_0x0128:
        r8 = com.ensighten.C1845i.m7362i();	 Catch:{ all -> 0x01e7 }
        if (r8 == 0) goto L_0x0135;
    L_0x012e:
        r1 = r1.getMessage();	 Catch:{ all -> 0x01e7 }
        com.ensighten.C1845i.m7345a(r1);	 Catch:{ all -> 0x01e7 }
    L_0x0135:
        if (r3 == 0) goto L_0x013a;
    L_0x0137:
        r3.close();	 Catch:{ IOException -> 0x01be }
    L_0x013a:
        if (r5 == 0) goto L_0x013f;
    L_0x013c:
        r5.close();	 Catch:{ IOException -> 0x01ca }
    L_0x013f:
        r1 = r2;
        goto L_0x00dd;
    L_0x0141:
        r0 = move-exception;
        r3 = r4;
        r5 = r4;
    L_0x0144:
        if (r3 == 0) goto L_0x0149;
    L_0x0146:
        r3.close();	 Catch:{ IOException -> 0x0178 }
    L_0x0149:
        if (r5 == 0) goto L_0x014e;
    L_0x014b:
        r5.close();	 Catch:{ IOException -> 0x0183 }
    L_0x014e:
        throw r0;	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
    L_0x014f:
        r0 = move-exception;
        r1 = com.ensighten.C1845i.m7362i();
        if (r1 == 0) goto L_0x0159;
    L_0x0156:
        com.ensighten.C1845i.m7344a(r0);
    L_0x0159:
        r0 = r2;
        goto L_0x0016;
    L_0x015c:
        r0.addView(r3);	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
    L_0x015f:
        r0 = r1;
        goto L_0x0016;
    L_0x0162:
        r0 = move-exception;
        r1 = com.ensighten.C1845i.m7362i();
        if (r1 == 0) goto L_0x0159;
    L_0x0169:
        com.ensighten.C1845i.m7344a(r0);
        goto L_0x0159;
    L_0x016d:
        r0 = move-exception;
        r1 = com.ensighten.C1845i.m7362i();
        if (r1 == 0) goto L_0x0159;
    L_0x0174:
        com.ensighten.C1845i.m7344a(r0);
        goto L_0x0159;
    L_0x0178:
        r1 = move-exception;
        r3 = com.ensighten.C1845i.m7362i();	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        if (r3 == 0) goto L_0x0149;
    L_0x017f:
        com.ensighten.C1845i.m7344a(r1);	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        goto L_0x0149;
    L_0x0183:
        r1 = move-exception;
        r3 = com.ensighten.C1845i.m7362i();	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        if (r3 == 0) goto L_0x014e;
    L_0x018a:
        com.ensighten.C1845i.m7344a(r1);	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        goto L_0x014e;
    L_0x018e:
        r1 = move-exception;
        r3 = com.ensighten.C1845i.m7362i();	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        if (r3 == 0) goto L_0x00d7;
    L_0x0195:
        com.ensighten.C1845i.m7344a(r1);	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        goto L_0x00d7;
    L_0x019a:
        r1 = move-exception;
        r3 = com.ensighten.C1845i.m7362i();	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        if (r3 == 0) goto L_0x00dc;
    L_0x01a1:
        com.ensighten.C1845i.m7344a(r1);	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        goto L_0x00dc;
    L_0x01a6:
        r1 = move-exception;
        r3 = com.ensighten.C1845i.m7362i();	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        if (r3 == 0) goto L_0x011e;
    L_0x01ad:
        com.ensighten.C1845i.m7344a(r1);	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        goto L_0x011e;
    L_0x01b2:
        r1 = move-exception;
        r3 = com.ensighten.C1845i.m7362i();	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        if (r3 == 0) goto L_0x0123;
    L_0x01b9:
        com.ensighten.C1845i.m7344a(r1);	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        goto L_0x0123;
    L_0x01be:
        r1 = move-exception;
        r3 = com.ensighten.C1845i.m7362i();	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        if (r3 == 0) goto L_0x013a;
    L_0x01c5:
        com.ensighten.C1845i.m7344a(r1);	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        goto L_0x013a;
    L_0x01ca:
        r1 = move-exception;
        r3 = com.ensighten.C1845i.m7362i();	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        if (r3 == 0) goto L_0x013f;
    L_0x01d1:
        com.ensighten.C1845i.m7344a(r1);	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        goto L_0x013f;
    L_0x01d6:
        r1 = move-exception;
        r3 = com.ensighten.C1845i.m7362i();	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
        if (r3 == 0) goto L_0x01e0;
    L_0x01dd:
        com.ensighten.C1845i.m7344a(r1);	 Catch:{ InflateException -> 0x014f, JSONException -> 0x0162, RuntimeException -> 0x016d }
    L_0x01e0:
        r1 = r2;
        goto L_0x00fb;
    L_0x01e3:
        r0 = move-exception;
        r3 = r4;
        goto L_0x0144;
    L_0x01e7:
        r0 = move-exception;
        goto L_0x0144;
    L_0x01ea:
        r1 = move-exception;
        r3 = r4;
        goto L_0x0128;
    L_0x01ee:
        r1 = move-exception;
        goto L_0x0128;
    L_0x01f1:
        r1 = move-exception;
        r3 = r4;
        goto L_0x0110;
    L_0x01f5:
        r1 = move-exception;
        goto L_0x0110;
    L_0x01f8:
        r1 = move-exception;
        r3 = r4;
        r5 = r4;
        goto L_0x00c9;
    L_0x01fd:
        r1 = move-exception;
        r3 = r4;
        goto L_0x00c9;
    L_0x0201:
        r3 = r4;
        r5 = r4;
        goto L_0x00f6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ensighten.C1712M.m7227a(int, org.json.JSONObject):boolean");
    }

    /* renamed from: a */
    private int m7219a(String str, JSONObject jSONObject, int i) throws JSONException {
        return m7220a(str, jSONObject, i, null, null, null);
    }

    /* renamed from: a */
    private int m7220a(String str, JSONObject jSONObject, int i, String str2, String str3, String str4) throws JSONException {
        if (str.equals("background")) {
            return m7230b(i, "background", jSONObject.getJSONObject("background"), str2, str3, str4);
        } else if (str.equals("layout_height")) {
            return m7229b(i, "layout_height", jSONObject.getJSONObject("layout_height"));
        } else if (str.equals("layout_width")) {
            return m7229b(i, "layout_width", jSONObject.getJSONObject("layout_width"));
        } else if (str.equals("src")) {
            return m7216a(i, "src", jSONObject.getJSONObject("src"), str2, str3, str4);
        } else if (str.equals("text")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("text");
            if (Ensighten.getViewManager().f5780a != C1733V.PAGER_ADAPTER) {
                return m7215a(i, "text", jSONObject2);
            }
            return m7218a("text", jSONObject2);
        } else if (str.equals("textColor")) {
            return m7215a(i, "textColor", jSONObject.getJSONObject("textColor"));
        } else if (str.equals("translationX")) {
            return m7229b(i, "translationX", jSONObject.getJSONObject("translationX"));
        } else if (str.equals("translationY")) {
            return m7229b(i, "translationY", jSONObject.getJSONObject("translationY"));
        } else if (str.equals("translationZ")) {
            return m7229b(i, "translationZ", jSONObject.getJSONObject("translationZ"));
        } else if (!str.equals("visibility")) {
            return 0;
        } else {
            return m7229b(i, "visibility", jSONObject.getJSONObject("visibility"));
        }
    }

    /* renamed from: a */
    private int m7216a(int i, String str, JSONObject jSONObject, String str2, String str3, String str4) {
        try {
            Object findViewById;
            View findViewById2 = m7224a(i);
            C1735W viewManager = Ensighten.getViewManager();
            if ((this.f5684l || this.f5683k || this.f5682j) && findViewById2 != null && (findViewById2 instanceof ViewGroup)) {
                findViewById2 = this.f5673a ? findViewById2.findViewById(i) : viewManager.mo15083a(i, findViewById2);
            }
            if (findViewById2 == null || !ImageView.class.isInstance(findViewById2)) {
                return 0;
            }
            return str.equals("src") ? m7217a((ImageView) findViewById2, true, jSONObject) : 0;
        } catch (Exception e) {
            if (C1845i.m7362i()) {
                C1845i.m7344a(e);
            }
            return 0;
        }
    }

    /* renamed from: a */
    private int m7217a(View view, boolean z, JSONObject jSONObject) {
        try {
            String string = jSONObject.getString("type");
            final String string2 = jSONObject.getString("value");
            final ImageView imageView = z ? (ImageView) view : null;
            Activity c = Ensighten.getViewManager().mo15088c();
            final boolean z2;
            final View view2;
            if (string.equals("base64") || string.equals("file")) {
                if (string.equals("base64")) {
                    String str;
                    try {
                        str = jSONObject.getString("crc32") + ".img";
                        jSONObject.remove("crc32");
                    } catch (JSONException e) {
                        str = "rand-" + Integer.toHexString(new SecureRandom().nextInt()) + ".img";
                    }
                    if (m7228a(jSONObject, str)) {
                        string2 = str;
                    }
                }
                z2 = z;
                view2 = view;
                view.post(new Runnable() {
                    public final void run() {
                        try {
                            Drawable background = !z2 ? view2.getBackground() : null;
                            int width = z2 ? imageView.getWidth() : background != null ? background.getMinimumWidth() : 0;
                            int height = z2 ? imageView.getHeight() : background != null ? background.getMinimumHeight() : 0;
                            if (width != 0 && height != 0) {
                                final String str = C1712M.this.f5680h.getFilesDir().getPath() + "/";
                                C17051 c17051 = new TraceFieldInterface() {
                                    public Trace _nr_trace;

                                    public void _nr_setTrace(Trace trace) {
                                        try {
                                            this._nr_trace = trace;
                                        } catch (Exception e) {
                                        }
                                    }

                                    /* Access modifiers changed, original: protected|final|synthetic */
                                    public final /* synthetic */ void onPostExecute(Object obj) {
                                        try {
                                            TraceMachine.enterMethod(this._nr_trace, "M$1$1#onPostExecute", null);
                                        } catch (NoSuchFieldError e) {
                                            while (true) {
                                                TraceMachine.enterMethod(null, "M$1$1#onPostExecute", null);
                                            }
                                        }
                                        Bitmap bitmap = (Bitmap) obj;
                                        if (bitmap != null) {
                                            if (z2) {
                                                imageView.setImageBitmap(bitmap);
                                                TraceMachine.exitMethod();
                                                return;
                                            }
                                            view2.setBackgroundDrawable(new BitmapDrawable(C1712M.this.f5680h.getResources(), bitmap));
                                        }
                                        TraceMachine.exitMethod();
                                    }

                                    /* Access modifiers changed, original: protected|final|synthetic */
                                    public final /* synthetic */ Object doInBackground(Object[] objArr) {
                                        try {
                                            TraceMachine.enterMethod(this._nr_trace, "M$1$1#doInBackground", null);
                                        } catch (NoSuchFieldError e) {
                                            while (true) {
                                                TraceMachine.enterMethod(null, "M$1$1#doInBackground", null);
                                            }
                                        }
                                        Options options = new Options();
                                        if (VERSION.SDK_INT >= 11) {
                                            options.inMutable = true;
                                        }
                                        if (VERSION.SDK_INT >= 10) {
                                            options.inPreferQualityOverSpeed = true;
                                        }
                                        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(BitmapFactoryInstrumentation.decodeFile(str + string2, options), width, height, false);
                                        TraceMachine.exitMethod();
                                        TraceMachine.unloadTraceContext(this);
                                        return createScaledBitmap;
                                    }
                                };
                                Void[] voidArr = new Void[0];
                                if (c17051 instanceof AsyncTask) {
                                    AsyncTaskInstrumentation.execute(c17051, voidArr);
                                } else {
                                    c17051.execute(voidArr);
                                }
                            }
                        } catch (Exception e) {
                            if (C1845i.m7362i()) {
                                C1845i.m7344a(e);
                            }
                        }
                    }
                });
                return -1;
            }
            int identifier;
            if (string.equals(MenuType.COLUMN_COLOR)) {
                if (string2.startsWith("R.color.")) {
                    string = string2.substring(8);
                    Resources resources = c.getResources();
                    identifier = resources.getIdentifier(string, MenuType.COLUMN_COLOR, c.getPackageName());
                    if (identifier == 0) {
                        return 0;
                    }
                    if (z) {
                        imageView.setImageDrawable(new ColorDrawable(resources.getColor(identifier)));
                    } else {
                        view.setBackgroundColor(resources.getColor(identifier));
                    }
                } else if (z) {
                    imageView.setImageDrawable(new ColorDrawable(Color.parseColor(string2)));
                } else {
                    view.setBackgroundColor(Color.parseColor(string2));
                }
            } else if (string.equals("image")) {
                if (string2.startsWith("R.drawable.")) {
                    identifier = c.getResources().getIdentifier(string2.substring(11), "drawable", c.getPackageName());
                    if (identifier == 0) {
                        return 0;
                    }
                    if (z) {
                        imageView.setImageResource(identifier);
                    } else {
                        view.setBackgroundResource(identifier);
                    }
                }
            } else if (string.equals(NativeProtocol.IMAGE_URL_KEY)) {
                if (string2 == null || string2.length() == 0) {
                    return 0;
                }
                final String str2 = "url-" + Integer.toHexString(string2.hashCode()) + ".img";
                final Context applicationContext = c.getApplicationContext();
                final String str3 = applicationContext.getFilesDir().getPath() + "/";
                final File file = new File(str3 + str2);
                z2 = z;
                view2 = view;
                final JSONObject jSONObject2 = jSONObject;
                view.post(new Runnable() {
                    public final void run() {
                        try {
                            Drawable background = !z2 ? view2.getBackground() : null;
                            int width = z2 ? imageView.getWidth() : background != null ? background.getMinimumWidth() : 0;
                            int height = z2 ? imageView.getHeight() : background != null ? background.getMinimumHeight() : 0;
                            String[] strArr;
                            if (C1712M.this.f5679g.contains(string2)) {
                                C17092 c17092 = new TraceFieldInterface() {
                                    public Trace _nr_trace;
                                    /* renamed from: a */
                                    String f5657a;

                                    public void _nr_setTrace(Trace trace) {
                                        try {
                                            this._nr_trace = trace;
                                        } catch (Exception e) {
                                        }
                                    }

                                    /* Access modifiers changed, original: protected|final|synthetic */
                                    public final /* synthetic */ Object doInBackground(Object[] objArr) {
                                        try {
                                            TraceMachine.enterMethod(this._nr_trace, "M$2$2#doInBackground", null);
                                        } catch (NoSuchFieldError e) {
                                            while (true) {
                                                TraceMachine.enterMethod(null, "M$2$2#doInBackground", null);
                                            }
                                        }
                                        this.f5657a = ((String[]) objArr)[0];
                                        Bitmap a = C1712M.m7223a(file, str2, applicationContext, width, height);
                                        TraceMachine.exitMethod();
                                        TraceMachine.unloadTraceContext(this);
                                        return a;
                                    }

                                    /* Access modifiers changed, original: protected|final|synthetic */
                                    public final /* synthetic */ void onPostExecute(Object obj) {
                                        try {
                                            TraceMachine.enterMethod(this._nr_trace, "M$2$2#onPostExecute", null);
                                        } catch (NoSuchFieldError e) {
                                            while (true) {
                                                TraceMachine.enterMethod(null, "M$2$2#onPostExecute", null);
                                            }
                                        }
                                        Bitmap bitmap = (Bitmap) obj;
                                        if (bitmap == null) {
                                            C1712M.this.f5679g.remove(this.f5657a);
                                            TraceMachine.exitMethod();
                                            return;
                                        }
                                        Object obj2 = 1;
                                        if (z2) {
                                            imageView.setImageBitmap(bitmap);
                                        } else {
                                            view2.setBackgroundDrawable(new BitmapDrawable(C1712M.this.f5680h.getResources(), bitmap));
                                        }
                                        try {
                                            jSONObject2.put("type", "file");
                                            jSONObject2.put("value", str2);
                                            String str = "setImage() downloaded image";
                                            C1712M.this.mo15044a();
                                        } catch (JSONException e2) {
                                            if (C1845i.m7362i()) {
                                                C1845i.m7344a(e2);
                                            }
                                            obj2 = null;
                                        }
                                        if (obj2 == null) {
                                            C1712M.this.f5679g.remove(this.f5657a);
                                        }
                                        TraceMachine.exitMethod();
                                    }
                                };
                                strArr = new String[]{string2};
                                if (c17092 instanceof AsyncTask) {
                                    AsyncTaskInstrumentation.execute(c17092, strArr);
                                    return;
                                } else {
                                    c17092.execute(strArr);
                                    return;
                                }
                            }
                            C1712M.this.f5679g.add(string2);
                            C17081 c17081 = new TraceFieldInterface() {
                                public Trace _nr_trace;
                                /* renamed from: a */
                                String f5653a;

                                /* renamed from: com.ensighten.M$2$1$1 */
                                class C17071 extends Thread {
                                    C17071() {
                                    }

                                    public final void run() {
                                        if (file.exists()) {
                                            file.delete();
                                        }
                                    }
                                }

                                public void _nr_setTrace(Trace trace) {
                                    try {
                                        this._nr_trace = trace;
                                    } catch (Exception e) {
                                    }
                                }

                                /* Access modifiers changed, original: protected|final|synthetic */
                                public final /* synthetic */ Object doInBackground(Object[] objArr) {
                                    try {
                                        TraceMachine.enterMethod(this._nr_trace, "M$2$1#doInBackground", null);
                                    } catch (NoSuchFieldError e) {
                                        while (true) {
                                            TraceMachine.enterMethod(null, "M$2$1#doInBackground", null);
                                        }
                                    }
                                    Bitmap a = m7213a((String[]) objArr);
                                    TraceMachine.exitMethod();
                                    TraceMachine.unloadTraceContext(this);
                                    return a;
                                }

                                /* Access modifiers changed, original: protected|final|synthetic */
                                public final /* synthetic */ void onPostExecute(Object obj) {
                                    try {
                                        TraceMachine.enterMethod(this._nr_trace, "M$2$1#onPostExecute", null);
                                    } catch (NoSuchFieldError e) {
                                        while (true) {
                                            TraceMachine.enterMethod(null, "M$2$1#onPostExecute", null);
                                        }
                                    }
                                    Bitmap bitmap = (Bitmap) obj;
                                    C17071 c17071 = new C17071();
                                    if (bitmap == null) {
                                        C1712M.this.f5679g.remove(this.f5653a);
                                        c17071.start();
                                        TraceMachine.exitMethod();
                                        return;
                                    }
                                    Object obj2 = 1;
                                    if (z2) {
                                        imageView.setImageBitmap(bitmap);
                                    } else {
                                        view2.setBackgroundDrawable(new BitmapDrawable(C1712M.this.f5680h.getResources(), bitmap));
                                    }
                                    try {
                                        jSONObject2.put("type", "file");
                                        jSONObject2.put("value", str2);
                                        String str = "setImage() downloaded image";
                                        C1712M.this.mo15044a();
                                    } catch (JSONException e2) {
                                        if (C1845i.m7362i()) {
                                            C1845i.m7344a(e2);
                                        }
                                        obj2 = null;
                                    }
                                    if (obj2 == null) {
                                        C1712M.this.f5679g.remove(this.f5653a);
                                        c17071.start();
                                    }
                                    TraceMachine.exitMethod();
                                }

                                /* JADX WARNING: Removed duplicated region for block: B:95:0x013a A:{SYNTHETIC, Splitter:B:95:0x013a} */
                                /* JADX WARNING: Removed duplicated region for block: B:98:0x013f A:{SYNTHETIC, Splitter:B:98:0x013f} */
                                /* JADX WARNING: Removed duplicated region for block: B:101:0x0144 A:{SYNTHETIC, Splitter:B:101:0x0144} */
                                /* JADX WARNING: Removed duplicated region for block: B:60:0x00d6 A:{Catch:{ all -> 0x0223 }} */
                                /* JADX WARNING: Removed duplicated region for block: B:63:0x00e2 A:{SYNTHETIC, Splitter:B:63:0x00e2} */
                                /* JADX WARNING: Removed duplicated region for block: B:66:0x00e7 A:{SYNTHETIC, Splitter:B:66:0x00e7} */
                                /* JADX WARNING: Removed duplicated region for block: B:69:0x00ec A:{SYNTHETIC, Splitter:B:69:0x00ec} */
                                /* JADX WARNING: Removed duplicated region for block: B:77:0x0111 A:{Catch:{ all -> 0x0217 }} */
                                /* JADX WARNING: Removed duplicated region for block: B:80:0x011d A:{SYNTHETIC, Splitter:B:80:0x011d} */
                                /* JADX WARNING: Removed duplicated region for block: B:83:0x0122 A:{SYNTHETIC, Splitter:B:83:0x0122} */
                                /* JADX WARNING: Removed duplicated region for block: B:86:0x0127 A:{SYNTHETIC, Splitter:B:86:0x0127} */
                                /* JADX WARNING: Removed duplicated region for block: B:60:0x00d6 A:{Catch:{ all -> 0x0223 }} */
                                /* JADX WARNING: Removed duplicated region for block: B:63:0x00e2 A:{SYNTHETIC, Splitter:B:63:0x00e2} */
                                /* JADX WARNING: Removed duplicated region for block: B:66:0x00e7 A:{SYNTHETIC, Splitter:B:66:0x00e7} */
                                /* JADX WARNING: Removed duplicated region for block: B:69:0x00ec A:{SYNTHETIC, Splitter:B:69:0x00ec} */
                                /* JADX WARNING: Removed duplicated region for block: B:77:0x0111 A:{Catch:{ all -> 0x0217 }} */
                                /* JADX WARNING: Removed duplicated region for block: B:80:0x011d A:{SYNTHETIC, Splitter:B:80:0x011d} */
                                /* JADX WARNING: Removed duplicated region for block: B:83:0x0122 A:{SYNTHETIC, Splitter:B:83:0x0122} */
                                /* JADX WARNING: Removed duplicated region for block: B:86:0x0127 A:{SYNTHETIC, Splitter:B:86:0x0127} */
                                /* JADX WARNING: Removed duplicated region for block: B:95:0x013a A:{SYNTHETIC, Splitter:B:95:0x013a} */
                                /* JADX WARNING: Removed duplicated region for block: B:98:0x013f A:{SYNTHETIC, Splitter:B:98:0x013f} */
                                /* JADX WARNING: Removed duplicated region for block: B:101:0x0144 A:{SYNTHETIC, Splitter:B:101:0x0144} */
                                /* JADX WARNING: Removed duplicated region for block: B:95:0x013a A:{SYNTHETIC, Splitter:B:95:0x013a} */
                                /* JADX WARNING: Removed duplicated region for block: B:98:0x013f A:{SYNTHETIC, Splitter:B:98:0x013f} */
                                /* JADX WARNING: Removed duplicated region for block: B:101:0x0144 A:{SYNTHETIC, Splitter:B:101:0x0144} */
                                /* JADX WARNING: Removed duplicated region for block: B:60:0x00d6 A:{Catch:{ all -> 0x0223 }} */
                                /* JADX WARNING: Removed duplicated region for block: B:63:0x00e2 A:{SYNTHETIC, Splitter:B:63:0x00e2} */
                                /* JADX WARNING: Removed duplicated region for block: B:66:0x00e7 A:{SYNTHETIC, Splitter:B:66:0x00e7} */
                                /* JADX WARNING: Removed duplicated region for block: B:69:0x00ec A:{SYNTHETIC, Splitter:B:69:0x00ec} */
                                /* JADX WARNING: Removed duplicated region for block: B:77:0x0111 A:{Catch:{ all -> 0x0217 }} */
                                /* JADX WARNING: Removed duplicated region for block: B:80:0x011d A:{SYNTHETIC, Splitter:B:80:0x011d} */
                                /* JADX WARNING: Removed duplicated region for block: B:83:0x0122 A:{SYNTHETIC, Splitter:B:83:0x0122} */
                                /* JADX WARNING: Removed duplicated region for block: B:86:0x0127 A:{SYNTHETIC, Splitter:B:86:0x0127} */
                                /* JADX WARNING: Removed duplicated region for block: B:95:0x013a A:{SYNTHETIC, Splitter:B:95:0x013a} */
                                /* JADX WARNING: Removed duplicated region for block: B:98:0x013f A:{SYNTHETIC, Splitter:B:98:0x013f} */
                                /* JADX WARNING: Removed duplicated region for block: B:101:0x0144 A:{SYNTHETIC, Splitter:B:101:0x0144} */
                                /* JADX WARNING: Missing block: B:90:0x0130, code skipped:
            if (com.ensighten.C1845i.m7362i() == false) goto L_0x00ef;
     */
                                /* JADX WARNING: Missing block: B:91:0x0132, code skipped:
            com.ensighten.C1845i.m7344a(r0);
     */
                                /* JADX WARNING: Missing block: B:126:0x0186, code skipped:
            if (com.ensighten.C1845i.m7362i() == false) goto L_0x00ef;
     */
                                /* JADX WARNING: Missing block: B:159:0x01e7, code skipped:
            if (r1 == null) goto L_0x01ec;
     */
                                /* JADX WARNING: Missing block: B:161:?, code skipped:
            r1.close();
     */
                                /* JADX WARNING: Missing block: B:162:0x01ec, code skipped:
            if (r4 == null) goto L_0x01f1;
     */
                                /* JADX WARNING: Missing block: B:164:?, code skipped:
            r4.close();
     */
                                /* JADX WARNING: Missing block: B:165:0x01f1, code skipped:
            if (r5 == null) goto L_0x00ef;
     */
                                /* JADX WARNING: Missing block: B:167:?, code skipped:
            r5.close();
     */
                                /* JADX WARNING: Missing block: B:168:0x01f8, code skipped:
            r0 = e;
     */
                                /* JADX WARNING: Missing block: B:170:0x01fd, code skipped:
            if (com.ensighten.C1845i.m7362i() == false) goto L_0x00ef;
     */
                                /* JADX WARNING: Missing block: B:171:0x0201, code skipped:
            r0 = move-exception;
     */
                                /* JADX WARNING: Missing block: B:173:0x0206, code skipped:
            if (com.ensighten.C1845i.m7362i() != false) goto L_0x0208;
     */
                                /* JADX WARNING: Missing block: B:174:0x0208, code skipped:
            com.ensighten.C1845i.m7344a(r0);
     */
                                /* JADX WARNING: Missing block: B:175:0x020c, code skipped:
            r0 = move-exception;
     */
                                /* JADX WARNING: Missing block: B:177:0x0211, code skipped:
            if (com.ensighten.C1845i.m7362i() != false) goto L_0x0213;
     */
                                /* JADX WARNING: Missing block: B:178:0x0213, code skipped:
            com.ensighten.C1845i.m7344a(r0);
     */
                                /* renamed from: a */
                                private android.graphics.Bitmap m7213a(java.lang.String... r13) {
                                    /*
                                    r12 = this;
                                    r0 = 0;
                                    r0 = r13[r0];
                                    r12.f5653a = r0;
                                    r4 = 0;
                                    r3 = 0;
                                    r2 = 0;
                                    r0 = new java.io.File;
                                    r1 = com.ensighten.C1712M.C17102.this;
                                    r1 = r7;
                                    r0.<init>(r1);
                                    r8 = com.ensighten.C1715O.m7250a(r0);
                                    r5 = new java.net.URL;	 Catch:{ FileNotFoundException -> 0x00cc, IOException -> 0x0109, all -> 0x0136 }
                                    r0 = r12.f5653a;	 Catch:{ FileNotFoundException -> 0x00cc, IOException -> 0x0109, all -> 0x0136 }
                                    r5.<init>(r0);	 Catch:{ FileNotFoundException -> 0x00cc, IOException -> 0x0109, all -> 0x0136 }
                                    r0 = r5.openConnection();	 Catch:{ FileNotFoundException -> 0x00cc, IOException -> 0x0109, all -> 0x0136 }
                                    r6 = com.newrelic.agent.android.instrumentation.HttpInstrumentation.openConnection(r0);	 Catch:{ FileNotFoundException -> 0x00cc, IOException -> 0x0109, all -> 0x0136 }
                                    r6.connect();	 Catch:{ FileNotFoundException -> 0x00cc, IOException -> 0x0109, all -> 0x0136 }
                                    r0 = r6.getContentLength();	 Catch:{ FileNotFoundException -> 0x00cc, IOException -> 0x0109, all -> 0x0136 }
                                    r0 = (long) r0;	 Catch:{ FileNotFoundException -> 0x00cc, IOException -> 0x0109, all -> 0x0136 }
                                    r10 = -1;
                                    r7 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1));
                                    if (r7 != 0) goto L_0x0246;
                                L_0x0032:
                                    r0 = "content-length";
                                    r0 = r6.getHeaderField(r0);	 Catch:{ FileNotFoundException -> 0x00cc, IOException -> 0x0109, all -> 0x0136 }
                                    r0 = java.lang.Long.getLong(r0);	 Catch:{ FileNotFoundException -> 0x00cc, IOException -> 0x0109, all -> 0x0136 }
                                    if (r0 == 0) goto L_0x0069;
                                L_0x003e:
                                    r0 = r0.longValue();	 Catch:{ FileNotFoundException -> 0x00cc, IOException -> 0x0109, all -> 0x0136 }
                                L_0x0042:
                                    r6 = r0;
                                L_0x0043:
                                    r0 = -1;
                                    r0 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1));
                                    if (r0 == 0) goto L_0x006c;
                                L_0x0049:
                                    r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
                                    if (r0 <= 0) goto L_0x006c;
                                L_0x004d:
                                    r0 = com.ensighten.C1845i.m7362i();	 Catch:{ FileNotFoundException -> 0x00cc, IOException -> 0x0109, all -> 0x0136 }
                                    if (r0 == 0) goto L_0x0058;
                                L_0x0053:
                                    r0 = "Not writing file to storage due to insufficient space on disk.";
                                    com.ensighten.C1845i.m7345a(r0);	 Catch:{ FileNotFoundException -> 0x00cc, IOException -> 0x0109, all -> 0x0136 }
                                L_0x0058:
                                    if (r2 == 0) goto L_0x005d;
                                L_0x005a:
                                    r2.close();	 Catch:{ IOException -> 0x019f }
                                L_0x005d:
                                    if (r3 == 0) goto L_0x0062;
                                L_0x005f:
                                    r3.close();	 Catch:{ IOException -> 0x01ab }
                                L_0x0062:
                                    if (r4 == 0) goto L_0x0067;
                                L_0x0064:
                                    r4.close();	 Catch:{ IOException -> 0x01b7 }
                                L_0x0067:
                                    r0 = 0;
                                L_0x0068:
                                    return r0;
                                L_0x0069:
                                    r0 = -1;
                                    goto L_0x0042;
                                L_0x006c:
                                    r0 = r5.openStream();	 Catch:{ FileNotFoundException -> 0x00cc, IOException -> 0x0109, all -> 0x0136 }
                                    r5 = new java.io.BufferedInputStream;	 Catch:{ FileNotFoundException -> 0x00cc, IOException -> 0x0109, all -> 0x0136 }
                                    r5.<init>(r0);	 Catch:{ FileNotFoundException -> 0x00cc, IOException -> 0x0109, all -> 0x0136 }
                                    r4 = new java.io.FileOutputStream;	 Catch:{ FileNotFoundException -> 0x0235, IOException -> 0x0229 }
                                    r0 = com.ensighten.C1712M.C17102.this;	 Catch:{ FileNotFoundException -> 0x0235, IOException -> 0x0229 }
                                    r0 = r8;	 Catch:{ FileNotFoundException -> 0x0235, IOException -> 0x0229 }
                                    r4.<init>(r0);	 Catch:{ FileNotFoundException -> 0x0235, IOException -> 0x0229 }
                                    r1 = new java.io.BufferedOutputStream;	 Catch:{ FileNotFoundException -> 0x023b, IOException -> 0x022c, all -> 0x021a }
                                    r1.<init>(r4);	 Catch:{ FileNotFoundException -> 0x023b, IOException -> 0x022c, all -> 0x021a }
                                    r0 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
                                    r2 = new byte[r0];	 Catch:{ FileNotFoundException -> 0x0241, IOException -> 0x0230, all -> 0x021e }
                                    r0 = 0;
                                L_0x0088:
                                    r3 = r5.read(r2);	 Catch:{ FileNotFoundException -> 0x0241, IOException -> 0x0230, all -> 0x021e }
                                    r8 = -1;
                                    if (r3 == r8) goto L_0x01e7;
                                L_0x008f:
                                    r8 = 0;
                                    r1.write(r2, r8, r3);	 Catch:{ FileNotFoundException -> 0x0241, IOException -> 0x0230, all -> 0x021e }
                                    r8 = -1;
                                    r3 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
                                    if (r3 != 0) goto L_0x00c9;
                                L_0x0099:
                                    r3 = r0 % 1024;
                                    if (r3 != 0) goto L_0x00c9;
                                L_0x009d:
                                    r3 = new java.io.File;	 Catch:{ FileNotFoundException -> 0x0241, IOException -> 0x0230, all -> 0x021e }
                                    r8 = com.ensighten.C1712M.C17102.this;	 Catch:{ FileNotFoundException -> 0x0241, IOException -> 0x0230, all -> 0x021e }
                                    r8 = r7;	 Catch:{ FileNotFoundException -> 0x0241, IOException -> 0x0230, all -> 0x021e }
                                    r3.<init>(r8);	 Catch:{ FileNotFoundException -> 0x0241, IOException -> 0x0230, all -> 0x021e }
                                    r8 = com.ensighten.C1715O.m7250a(r3);	 Catch:{ FileNotFoundException -> 0x0241, IOException -> 0x0230, all -> 0x021e }
                                    r10 = 8388608; // 0x800000 float:1.17549435E-38 double:4.144523E-317;
                                    r3 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
                                    if (r3 >= 0) goto L_0x00c9;
                                L_0x00b1:
                                    r0 = com.ensighten.C1712M.C17102.this;	 Catch:{ FileNotFoundException -> 0x0241, IOException -> 0x0230, all -> 0x021e }
                                    r0 = r8;	 Catch:{ FileNotFoundException -> 0x0241, IOException -> 0x0230, all -> 0x021e }
                                    r0.delete();	 Catch:{ FileNotFoundException -> 0x0241, IOException -> 0x0230, all -> 0x021e }
                                    if (r1 == 0) goto L_0x00bd;
                                L_0x00ba:
                                    r1.close();	 Catch:{ IOException -> 0x01c3 }
                                L_0x00bd:
                                    if (r4 == 0) goto L_0x00c2;
                                L_0x00bf:
                                    r4.close();	 Catch:{ IOException -> 0x01cf }
                                L_0x00c2:
                                    if (r5 == 0) goto L_0x00c7;
                                L_0x00c4:
                                    r5.close();	 Catch:{ IOException -> 0x01db }
                                L_0x00c7:
                                    r0 = 0;
                                    goto L_0x0068;
                                L_0x00c9:
                                    r0 = r0 + 1;
                                    goto L_0x0088;
                                L_0x00cc:
                                    r0 = move-exception;
                                    r1 = r2;
                                    r2 = r3;
                                    r3 = r4;
                                L_0x00d0:
                                    r4 = com.ensighten.C1845i.m7362i();	 Catch:{ all -> 0x0223 }
                                    if (r4 == 0) goto L_0x00d9;
                                L_0x00d6:
                                    com.ensighten.C1845i.m7344a(r0);	 Catch:{ all -> 0x0223 }
                                L_0x00d9:
                                    r0 = com.ensighten.C1712M.C17102.this;	 Catch:{ all -> 0x0223 }
                                    r0 = r8;	 Catch:{ all -> 0x0223 }
                                    r0.delete();	 Catch:{ all -> 0x0223 }
                                    if (r1 == 0) goto L_0x00e5;
                                L_0x00e2:
                                    r1.close();	 Catch:{ IOException -> 0x0169 }
                                L_0x00e5:
                                    if (r2 == 0) goto L_0x00ea;
                                L_0x00e7:
                                    r2.close();	 Catch:{ IOException -> 0x0175 }
                                L_0x00ea:
                                    if (r3 == 0) goto L_0x00ef;
                                L_0x00ec:
                                    r3.close();	 Catch:{ IOException -> 0x0181 }
                                L_0x00ef:
                                    r0 = com.ensighten.C1712M.C17102.this;
                                    r0 = com.ensighten.C1712M.this;
                                    r1 = com.ensighten.C1712M.C17102.this;
                                    r1 = r8;
                                    r2 = com.ensighten.C1712M.C17102.this;
                                    r2 = r9;
                                    r3 = com.ensighten.C1712M.C17102.this;
                                    r3 = r10;
                                    r4 = r2;
                                    r5 = r1;
                                    r0 = com.ensighten.C1712M.m7223a(r1, r2, r3, r4, r5);
                                    goto L_0x0068;
                                L_0x0109:
                                    r0 = move-exception;
                                    r5 = r4;
                                L_0x010b:
                                    r1 = com.ensighten.C1845i.m7362i();	 Catch:{ all -> 0x0217 }
                                    if (r1 == 0) goto L_0x0114;
                                L_0x0111:
                                    com.ensighten.C1845i.m7344a(r0);	 Catch:{ all -> 0x0217 }
                                L_0x0114:
                                    r0 = com.ensighten.C1712M.C17102.this;	 Catch:{ all -> 0x0217 }
                                    r0 = r8;	 Catch:{ all -> 0x0217 }
                                    r0.delete();	 Catch:{ all -> 0x0217 }
                                    if (r2 == 0) goto L_0x0120;
                                L_0x011d:
                                    r2.close();	 Catch:{ IOException -> 0x0189 }
                                L_0x0120:
                                    if (r3 == 0) goto L_0x0125;
                                L_0x0122:
                                    r3.close();	 Catch:{ IOException -> 0x0194 }
                                L_0x0125:
                                    if (r5 == 0) goto L_0x00ef;
                                L_0x0127:
                                    r5.close();	 Catch:{ IOException -> 0x012b }
                                    goto L_0x00ef;
                                L_0x012b:
                                    r0 = move-exception;
                                    r1 = com.ensighten.C1845i.m7362i();
                                    if (r1 == 0) goto L_0x00ef;
                                L_0x0132:
                                    com.ensighten.C1845i.m7344a(r0);
                                    goto L_0x00ef;
                                L_0x0136:
                                    r0 = move-exception;
                                    r5 = r4;
                                L_0x0138:
                                    if (r2 == 0) goto L_0x013d;
                                L_0x013a:
                                    r2.close();	 Catch:{ IOException -> 0x0148 }
                                L_0x013d:
                                    if (r3 == 0) goto L_0x0142;
                                L_0x013f:
                                    r3.close();	 Catch:{ IOException -> 0x0153 }
                                L_0x0142:
                                    if (r5 == 0) goto L_0x0147;
                                L_0x0144:
                                    r5.close();	 Catch:{ IOException -> 0x015e }
                                L_0x0147:
                                    throw r0;
                                L_0x0148:
                                    r1 = move-exception;
                                    r2 = com.ensighten.C1845i.m7362i();
                                    if (r2 == 0) goto L_0x013d;
                                L_0x014f:
                                    com.ensighten.C1845i.m7344a(r1);
                                    goto L_0x013d;
                                L_0x0153:
                                    r1 = move-exception;
                                    r2 = com.ensighten.C1845i.m7362i();
                                    if (r2 == 0) goto L_0x0142;
                                L_0x015a:
                                    com.ensighten.C1845i.m7344a(r1);
                                    goto L_0x0142;
                                L_0x015e:
                                    r1 = move-exception;
                                    r2 = com.ensighten.C1845i.m7362i();
                                    if (r2 == 0) goto L_0x0147;
                                L_0x0165:
                                    com.ensighten.C1845i.m7344a(r1);
                                    goto L_0x0147;
                                L_0x0169:
                                    r0 = move-exception;
                                    r1 = com.ensighten.C1845i.m7362i();
                                    if (r1 == 0) goto L_0x00e5;
                                L_0x0170:
                                    com.ensighten.C1845i.m7344a(r0);
                                    goto L_0x00e5;
                                L_0x0175:
                                    r0 = move-exception;
                                    r1 = com.ensighten.C1845i.m7362i();
                                    if (r1 == 0) goto L_0x00ea;
                                L_0x017c:
                                    com.ensighten.C1845i.m7344a(r0);
                                    goto L_0x00ea;
                                L_0x0181:
                                    r0 = move-exception;
                                    r1 = com.ensighten.C1845i.m7362i();
                                    if (r1 == 0) goto L_0x00ef;
                                L_0x0188:
                                    goto L_0x0132;
                                L_0x0189:
                                    r0 = move-exception;
                                    r1 = com.ensighten.C1845i.m7362i();
                                    if (r1 == 0) goto L_0x0120;
                                L_0x0190:
                                    com.ensighten.C1845i.m7344a(r0);
                                    goto L_0x0120;
                                L_0x0194:
                                    r0 = move-exception;
                                    r1 = com.ensighten.C1845i.m7362i();
                                    if (r1 == 0) goto L_0x0125;
                                L_0x019b:
                                    com.ensighten.C1845i.m7344a(r0);
                                    goto L_0x0125;
                                L_0x019f:
                                    r0 = move-exception;
                                    r1 = com.ensighten.C1845i.m7362i();
                                    if (r1 == 0) goto L_0x005d;
                                L_0x01a6:
                                    com.ensighten.C1845i.m7344a(r0);
                                    goto L_0x005d;
                                L_0x01ab:
                                    r0 = move-exception;
                                    r1 = com.ensighten.C1845i.m7362i();
                                    if (r1 == 0) goto L_0x0062;
                                L_0x01b2:
                                    com.ensighten.C1845i.m7344a(r0);
                                    goto L_0x0062;
                                L_0x01b7:
                                    r0 = move-exception;
                                    r1 = com.ensighten.C1845i.m7362i();
                                    if (r1 == 0) goto L_0x0067;
                                L_0x01be:
                                    com.ensighten.C1845i.m7344a(r0);
                                    goto L_0x0067;
                                L_0x01c3:
                                    r0 = move-exception;
                                    r1 = com.ensighten.C1845i.m7362i();
                                    if (r1 == 0) goto L_0x00bd;
                                L_0x01ca:
                                    com.ensighten.C1845i.m7344a(r0);
                                    goto L_0x00bd;
                                L_0x01cf:
                                    r0 = move-exception;
                                    r1 = com.ensighten.C1845i.m7362i();
                                    if (r1 == 0) goto L_0x00c2;
                                L_0x01d6:
                                    com.ensighten.C1845i.m7344a(r0);
                                    goto L_0x00c2;
                                L_0x01db:
                                    r0 = move-exception;
                                    r1 = com.ensighten.C1845i.m7362i();
                                    if (r1 == 0) goto L_0x00c7;
                                L_0x01e2:
                                    com.ensighten.C1845i.m7344a(r0);
                                    goto L_0x00c7;
                                L_0x01e7:
                                    if (r1 == 0) goto L_0x01ec;
                                L_0x01e9:
                                    r1.close();	 Catch:{ IOException -> 0x0201 }
                                L_0x01ec:
                                    if (r4 == 0) goto L_0x01f1;
                                L_0x01ee:
                                    r4.close();	 Catch:{ IOException -> 0x020c }
                                L_0x01f1:
                                    if (r5 == 0) goto L_0x00ef;
                                L_0x01f3:
                                    r5.close();	 Catch:{ IOException -> 0x01f8 }
                                    goto L_0x00ef;
                                L_0x01f8:
                                    r0 = move-exception;
                                    r1 = com.ensighten.C1845i.m7362i();
                                    if (r1 == 0) goto L_0x00ef;
                                L_0x01ff:
                                    goto L_0x0132;
                                L_0x0201:
                                    r0 = move-exception;
                                    r1 = com.ensighten.C1845i.m7362i();
                                    if (r1 == 0) goto L_0x01ec;
                                L_0x0208:
                                    com.ensighten.C1845i.m7344a(r0);
                                    goto L_0x01ec;
                                L_0x020c:
                                    r0 = move-exception;
                                    r1 = com.ensighten.C1845i.m7362i();
                                    if (r1 == 0) goto L_0x01f1;
                                L_0x0213:
                                    com.ensighten.C1845i.m7344a(r0);
                                    goto L_0x01f1;
                                L_0x0217:
                                    r0 = move-exception;
                                    goto L_0x0138;
                                L_0x021a:
                                    r0 = move-exception;
                                    r3 = r4;
                                    goto L_0x0138;
                                L_0x021e:
                                    r0 = move-exception;
                                    r2 = r1;
                                    r3 = r4;
                                    goto L_0x0138;
                                L_0x0223:
                                    r0 = move-exception;
                                    r5 = r3;
                                    r3 = r2;
                                    r2 = r1;
                                    goto L_0x0138;
                                L_0x0229:
                                    r0 = move-exception;
                                    goto L_0x010b;
                                L_0x022c:
                                    r0 = move-exception;
                                    r3 = r4;
                                    goto L_0x010b;
                                L_0x0230:
                                    r0 = move-exception;
                                    r2 = r1;
                                    r3 = r4;
                                    goto L_0x010b;
                                L_0x0235:
                                    r0 = move-exception;
                                    r1 = r2;
                                    r2 = r3;
                                    r3 = r5;
                                    goto L_0x00d0;
                                L_0x023b:
                                    r0 = move-exception;
                                    r1 = r2;
                                    r3 = r5;
                                    r2 = r4;
                                    goto L_0x00d0;
                                L_0x0241:
                                    r0 = move-exception;
                                    r2 = r4;
                                    r3 = r5;
                                    goto L_0x00d0;
                                L_0x0246:
                                    r6 = r0;
                                    goto L_0x0043;
                                    */
                                    throw new UnsupportedOperationException("Method not decompiled: com.ensighten.C1712M$C17102$C17081.m7213a(java.lang.String[]):android.graphics.Bitmap");
                                }
                            };
                            strArr = new String[]{string2};
                            if (c17081 instanceof AsyncTask) {
                                AsyncTaskInstrumentation.execute(c17081, strArr);
                            } else {
                                c17081.execute(strArr);
                            }
                        } catch (Exception e) {
                            if (C1845i.m7362i()) {
                                C1845i.m7344a(e);
                            }
                        }
                    }
                });
                return -1;
            }
            return 1;
        } catch (Exception e2) {
            if (C1845i.m7362i()) {
                C1845i.m7344a(e2);
            }
            return 0;
        }
    }

    /* renamed from: a */
    private int m7215a(int i, String str, JSONObject jSONObject) {
        C1735W viewManager = Ensighten.getViewManager();
        try {
            Object findViewById;
            View findViewById2 = m7224a(i);
            if ((this.f5684l || this.f5683k || this.f5682j) && findViewById2 != null && (findViewById2 instanceof ViewGroup)) {
                findViewById2 = this.f5673a ? findViewById2.findViewById(i) : viewManager.mo15083a(i, findViewById2);
            }
            if (findViewById2 == null || !TextView.class.isInstance(findViewById2)) {
                return 0;
            }
            TextView textView = (TextView) findViewById2;
            String string = jSONObject.getString("type");
            String string2 = jSONObject.getString("value");
            if (str.equals("text") && string.equals("string")) {
                textView.setText(string2);
            } else if (str.equals("textColor") && string.equals(MenuType.COLUMN_COLOR)) {
                textView.setTextColor(Color.parseColor(string2));
            }
            return 1;
        } catch (Exception e) {
            if (C1845i.m7362i()) {
                C1845i.m7344a(e);
            }
            return 0;
        }
    }

    /* renamed from: b */
    private int m7230b(int i, String str, JSONObject jSONObject, String str2, String str3, String str4) {
        try {
            C1735W viewManager = Ensighten.getViewManager();
            View a = m7224a(i);
            if ((this.f5684l || this.f5683k || this.f5682j) && a != null && (a instanceof ViewGroup)) {
                a = this.f5673a ? a.findViewById(i) : viewManager.mo15083a(i, a);
            }
            if (a == null) {
                return 0;
            }
            return str.equals("background") ? m7217a(a, false, jSONObject) : 0;
        } catch (Exception e) {
            if (C1845i.m7362i()) {
                C1845i.m7344a(e);
            }
            return 0;
        }
    }

    /* renamed from: b */
    private int m7229b(int i, String str, JSONObject jSONObject) {
        try {
            View findViewById;
            C1735W viewManager = Ensighten.getViewManager();
            View a = m7224a(i);
            if ((this.f5684l || this.f5683k || this.f5682j) && a != null && (a instanceof ViewGroup)) {
                findViewById = this.f5673a ? a.findViewById(i) : viewManager.mo15083a(i, a);
            } else {
                findViewById = a;
            }
            if (findViewById == null) {
                return 0;
            }
            String string = jSONObject.getString("type");
            String string2 = jSONObject.getString("value");
            Integer num;
            if (str.equals("layout_height") || str.equals("layout_width")) {
                num = null;
                if (string.equals("int")) {
                    num = C1712M.m7232b(string2);
                } else if (string.equals("string")) {
                    if (string2.equals("fill_parent") || string2.equals("match_parent")) {
                        num = Integer.valueOf(-1);
                    } else if (string2.equals("wrap_content")) {
                        num = Integer.valueOf(-1);
                    }
                }
                if (num != null) {
                    LayoutParams layoutParams = findViewById.getLayoutParams();
                    if (str.equals("layout_height")) {
                        layoutParams.height = num.intValue();
                    } else {
                        layoutParams.width = num.intValue();
                    }
                    findViewById.setLayoutParams(layoutParams);
                }
                return 1;
            } else if ((str.equals("translationX") || str.equals("translationY") || str.equals("translationZ")) && string.equals("float")) {
                Float a2 = C1712M.m7226a(string2);
                if (a2 == null) {
                    return 0;
                }
                if (str.equals("translationX")) {
                    findViewById.setTranslationX(a2.floatValue());
                } else if (str.equals("translationY")) {
                    findViewById.setTranslationY(a2.floatValue());
                } else if (VERSION.SDK_INT >= 21) {
                    findViewById.setTranslationZ(a2.floatValue());
                }
                return 1;
            } else if (!str.equals("visibility") || !string.equals("int")) {
                return 0;
            } else {
                num = C1712M.m7232b(string2);
                if (num == null) {
                    return 0;
                }
                findViewById.setVisibility(num.intValue());
                return 1;
            }
        } catch (Exception e) {
            if (C1845i.m7362i()) {
                C1845i.m7344a(e);
            }
            return 0;
        }
    }

    /* renamed from: a */
    private int m7218a(String str, JSONObject jSONObject) {
        try {
            if (Ensighten.getViewManager().f5780a != C1733V.PAGER_ADAPTER) {
                return 0;
            }
            String string = jSONObject.getString("type");
            String string2 = jSONObject.getString("value");
            if (str.equals("text") && string.equals("string")) {
                this.f5688p = string2;
            }
            return 1;
        } catch (Exception e) {
            if (!C1845i.m7362i()) {
                return 0;
            }
            C1845i.m7344a(e);
            return 0;
        }
    }

    /* renamed from: a */
    private static Float m7226a(String str) {
        Float f = null;
        try {
            return Float.valueOf(Float.parseFloat(str));
        } catch (NumberFormatException e) {
            if (!C1845i.m7362i()) {
                return f;
            }
            C1845i.m7344a(e);
            return f;
        }
    }

    /* renamed from: b */
    private static Integer m7232b(String str) {
        Integer num = null;
        try {
            return Integer.decode(str);
        } catch (NumberFormatException e) {
            if (!C1845i.m7362i()) {
                return num;
            }
            C1845i.m7344a(e);
            return num;
        }
    }

    /* renamed from: a */
    private View m7224a(int i) {
        C1735W viewManager = Ensighten.getViewManager();
        Activity c = viewManager.mo15088c();
        if (this.f5687o != null) {
            return this.f5687o.getView().findViewById(i);
        }
        if (this.f5686n != null) {
            return this.f5686n.getView().findViewById(i);
        }
        if (this.f5684l) {
            return viewManager.f5784e;
        }
        if (this.f5682j) {
            return this.f5674b;
        }
        if (this.f5683k) {
            return this.f5676d;
        }
        return c.findViewById(i);
    }

    /* renamed from: b */
    private View m7231b(int i) {
        if (this.f5687o != null) {
            return this.f5687o.getView().findViewById(i);
        }
        if (this.f5686n != null) {
            return this.f5686n.getView().findViewById(i);
        }
        return Ensighten.getViewManager().mo15088c().findViewById(i);
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:64:0x008c=Splitter:B:64:0x008c, B:79:0x00ac=Splitter:B:79:0x00ac, B:49:0x006d=Splitter:B:49:0x006d, B:34:0x004e=Splitter:B:34:0x004e} */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0073 A:{Catch:{ all -> 0x0131 }} */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0078 A:{SYNTHETIC, Splitter:B:54:0x0078} */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x007d A:{SYNTHETIC, Splitter:B:57:0x007d} */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0092 A:{Catch:{ all -> 0x0131 }} */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0097 A:{SYNTHETIC, Splitter:B:69:0x0097} */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x009c A:{SYNTHETIC, Splitter:B:72:0x009c} */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x00b2 A:{Catch:{ all -> 0x0131 }} */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x00bb A:{SYNTHETIC, Splitter:B:84:0x00bb} */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x00c0 A:{SYNTHETIC, Splitter:B:87:0x00c0} */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0054 A:{Catch:{ all -> 0x0131 }} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0059 A:{SYNTHETIC, Splitter:B:39:0x0059} */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x005e A:{SYNTHETIC, Splitter:B:42:0x005e} */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0073 A:{Catch:{ all -> 0x0131 }} */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0078 A:{SYNTHETIC, Splitter:B:54:0x0078} */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x007d A:{SYNTHETIC, Splitter:B:57:0x007d} */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0092 A:{Catch:{ all -> 0x0131 }} */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0097 A:{SYNTHETIC, Splitter:B:69:0x0097} */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x009c A:{SYNTHETIC, Splitter:B:72:0x009c} */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x00b2 A:{Catch:{ all -> 0x0131 }} */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x00bb A:{SYNTHETIC, Splitter:B:84:0x00bb} */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x00c0 A:{SYNTHETIC, Splitter:B:87:0x00c0} */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x00d4 A:{SYNTHETIC, Splitter:B:95:0x00d4} */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x00d9 A:{SYNTHETIC, Splitter:B:98:0x00d9} */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0054 A:{Catch:{ all -> 0x0131 }} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0059 A:{SYNTHETIC, Splitter:B:39:0x0059} */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x005e A:{SYNTHETIC, Splitter:B:42:0x005e} */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0073 A:{Catch:{ all -> 0x0131 }} */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0078 A:{SYNTHETIC, Splitter:B:54:0x0078} */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x007d A:{SYNTHETIC, Splitter:B:57:0x007d} */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0092 A:{Catch:{ all -> 0x0131 }} */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0097 A:{SYNTHETIC, Splitter:B:69:0x0097} */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x009c A:{SYNTHETIC, Splitter:B:72:0x009c} */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x00b2 A:{Catch:{ all -> 0x0131 }} */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x00bb A:{SYNTHETIC, Splitter:B:84:0x00bb} */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x00c0 A:{SYNTHETIC, Splitter:B:87:0x00c0} */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x00d4 A:{SYNTHETIC, Splitter:B:95:0x00d4} */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x00d9 A:{SYNTHETIC, Splitter:B:98:0x00d9} */
    /* JADX WARNING: Missing block: B:30:0x0045, code skipped:
            if (com.ensighten.C1845i.m7362i() == false) goto L_0x0006;
     */
    /* JADX WARNING: Missing block: B:31:0x0047, code skipped:
            com.ensighten.C1845i.m7344a(r1);
     */
    /* JADX WARNING: Missing block: B:46:0x0067, code skipped:
            if (com.ensighten.C1845i.m7362i() == false) goto L_0x0006;
     */
    /* JADX WARNING: Missing block: B:61:0x0086, code skipped:
            if (com.ensighten.C1845i.m7362i() == false) goto L_0x0006;
     */
    /* JADX WARNING: Missing block: B:76:0x00a6, code skipped:
            if (com.ensighten.C1845i.m7362i() == false) goto L_0x0006;
     */
    /* JADX WARNING: Missing block: B:91:0x00ca, code skipped:
            if (com.ensighten.C1845i.m7362i() == false) goto L_0x0006;
     */
    /* renamed from: a */
    private static android.graphics.Bitmap m7223a(java.io.File r6, java.lang.String r7, android.content.Context r8, int r9, int r10) {
        /*
        r4 = 1;
        r0 = 0;
        if (r9 == 0) goto L_0x0006;
    L_0x0004:
        if (r10 != 0) goto L_0x0007;
    L_0x0006:
        return r0;
    L_0x0007:
        r1 = new android.graphics.BitmapFactory$Options;
        r1.<init>();
        r2 = android.os.Build.VERSION.SDK_INT;
        r3 = 11;
        if (r2 < r3) goto L_0x0014;
    L_0x0012:
        r1.inMutable = r4;
    L_0x0014:
        r2 = android.os.Build.VERSION.SDK_INT;
        r3 = 10;
        if (r2 < r3) goto L_0x001c;
    L_0x001a:
        r1.inPreferQualityOverSpeed = r4;
    L_0x001c:
        r2 = r6.exists();	 Catch:{ FileNotFoundException -> 0x004b, IllegalArgumentException -> 0x006a, NullPointerException -> 0x0089, OutOfMemoryError -> 0x00a9, all -> 0x00ce }
        if (r2 == 0) goto L_0x0167;
    L_0x0022:
        r3 = r8.openFileInput(r7);	 Catch:{ FileNotFoundException -> 0x004b, IllegalArgumentException -> 0x006a, NullPointerException -> 0x0089, OutOfMemoryError -> 0x00a9, all -> 0x00ce }
        r2 = new java.io.BufferedInputStream;	 Catch:{ FileNotFoundException -> 0x015a, IllegalArgumentException -> 0x014d, NullPointerException -> 0x0140, OutOfMemoryError -> 0x0133, all -> 0x012d }
        r2.<init>(r3);	 Catch:{ FileNotFoundException -> 0x015a, IllegalArgumentException -> 0x014d, NullPointerException -> 0x0140, OutOfMemoryError -> 0x0133, all -> 0x012d }
        r4 = 0;
        r1 = com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation.decodeStream(r2, r4, r1);	 Catch:{ FileNotFoundException -> 0x015e, IllegalArgumentException -> 0x0151, NullPointerException -> 0x0144, OutOfMemoryError -> 0x0137 }
        r0 = 0;
        r0 = android.graphics.Bitmap.createScaledBitmap(r1, r9, r10, r0);	 Catch:{ FileNotFoundException -> 0x0161, IllegalArgumentException -> 0x0154, NullPointerException -> 0x0147, OutOfMemoryError -> 0x013a }
    L_0x0035:
        if (r2 == 0) goto L_0x003a;
    L_0x0037:
        r2.close();	 Catch:{ IOException -> 0x0121 }
    L_0x003a:
        if (r3 == 0) goto L_0x0006;
    L_0x003c:
        r3.close();	 Catch:{ IOException -> 0x0040 }
        goto L_0x0006;
    L_0x0040:
        r1 = move-exception;
        r2 = com.ensighten.C1845i.m7362i();
        if (r2 == 0) goto L_0x0006;
    L_0x0047:
        com.ensighten.C1845i.m7344a(r1);
        goto L_0x0006;
    L_0x004b:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
    L_0x004e:
        r4 = com.ensighten.C1845i.m7362i();	 Catch:{ all -> 0x0131 }
        if (r4 == 0) goto L_0x0057;
    L_0x0054:
        com.ensighten.C1845i.m7344a(r1);	 Catch:{ all -> 0x0131 }
    L_0x0057:
        if (r2 == 0) goto L_0x005c;
    L_0x0059:
        r2.close();	 Catch:{ IOException -> 0x00f3 }
    L_0x005c:
        if (r3 == 0) goto L_0x0006;
    L_0x005e:
        r3.close();	 Catch:{ IOException -> 0x0062 }
        goto L_0x0006;
    L_0x0062:
        r1 = move-exception;
        r2 = com.ensighten.C1845i.m7362i();
        if (r2 == 0) goto L_0x0006;
    L_0x0069:
        goto L_0x0047;
    L_0x006a:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
    L_0x006d:
        r4 = com.ensighten.C1845i.m7362i();	 Catch:{ all -> 0x0131 }
        if (r4 == 0) goto L_0x0076;
    L_0x0073:
        com.ensighten.C1845i.m7344a(r1);	 Catch:{ all -> 0x0131 }
    L_0x0076:
        if (r2 == 0) goto L_0x007b;
    L_0x0078:
        r2.close();	 Catch:{ IOException -> 0x00ff }
    L_0x007b:
        if (r3 == 0) goto L_0x0006;
    L_0x007d:
        r3.close();	 Catch:{ IOException -> 0x0081 }
        goto L_0x0006;
    L_0x0081:
        r1 = move-exception;
        r2 = com.ensighten.C1845i.m7362i();
        if (r2 == 0) goto L_0x0006;
    L_0x0088:
        goto L_0x0047;
    L_0x0089:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
    L_0x008c:
        r4 = com.ensighten.C1845i.m7362i();	 Catch:{ all -> 0x0131 }
        if (r4 == 0) goto L_0x0095;
    L_0x0092:
        com.ensighten.C1845i.m7344a(r1);	 Catch:{ all -> 0x0131 }
    L_0x0095:
        if (r2 == 0) goto L_0x009a;
    L_0x0097:
        r2.close();	 Catch:{ IOException -> 0x010b }
    L_0x009a:
        if (r3 == 0) goto L_0x0006;
    L_0x009c:
        r3.close();	 Catch:{ IOException -> 0x00a1 }
        goto L_0x0006;
    L_0x00a1:
        r1 = move-exception;
        r2 = com.ensighten.C1845i.m7362i();
        if (r2 == 0) goto L_0x0006;
    L_0x00a8:
        goto L_0x0047;
    L_0x00a9:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
    L_0x00ac:
        r4 = com.ensighten.C1845i.m7362i();	 Catch:{ all -> 0x0131 }
        if (r4 == 0) goto L_0x00b9;
    L_0x00b2:
        r1 = r1.getMessage();	 Catch:{ all -> 0x0131 }
        com.ensighten.C1845i.m7345a(r1);	 Catch:{ all -> 0x0131 }
    L_0x00b9:
        if (r2 == 0) goto L_0x00be;
    L_0x00bb:
        r2.close();	 Catch:{ IOException -> 0x0116 }
    L_0x00be:
        if (r3 == 0) goto L_0x0006;
    L_0x00c0:
        r3.close();	 Catch:{ IOException -> 0x00c5 }
        goto L_0x0006;
    L_0x00c5:
        r1 = move-exception;
        r2 = com.ensighten.C1845i.m7362i();
        if (r2 == 0) goto L_0x0006;
    L_0x00cc:
        goto L_0x0047;
    L_0x00ce:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r0 = r1;
    L_0x00d2:
        if (r2 == 0) goto L_0x00d7;
    L_0x00d4:
        r2.close();	 Catch:{ IOException -> 0x00dd }
    L_0x00d7:
        if (r3 == 0) goto L_0x00dc;
    L_0x00d9:
        r3.close();	 Catch:{ IOException -> 0x00e8 }
    L_0x00dc:
        throw r0;
    L_0x00dd:
        r1 = move-exception;
        r2 = com.ensighten.C1845i.m7362i();
        if (r2 == 0) goto L_0x00d7;
    L_0x00e4:
        com.ensighten.C1845i.m7344a(r1);
        goto L_0x00d7;
    L_0x00e8:
        r1 = move-exception;
        r2 = com.ensighten.C1845i.m7362i();
        if (r2 == 0) goto L_0x00dc;
    L_0x00ef:
        com.ensighten.C1845i.m7344a(r1);
        goto L_0x00dc;
    L_0x00f3:
        r1 = move-exception;
        r2 = com.ensighten.C1845i.m7362i();
        if (r2 == 0) goto L_0x005c;
    L_0x00fa:
        com.ensighten.C1845i.m7344a(r1);
        goto L_0x005c;
    L_0x00ff:
        r1 = move-exception;
        r2 = com.ensighten.C1845i.m7362i();
        if (r2 == 0) goto L_0x007b;
    L_0x0106:
        com.ensighten.C1845i.m7344a(r1);
        goto L_0x007b;
    L_0x010b:
        r1 = move-exception;
        r2 = com.ensighten.C1845i.m7362i();
        if (r2 == 0) goto L_0x009a;
    L_0x0112:
        com.ensighten.C1845i.m7344a(r1);
        goto L_0x009a;
    L_0x0116:
        r1 = move-exception;
        r2 = com.ensighten.C1845i.m7362i();
        if (r2 == 0) goto L_0x00be;
    L_0x011d:
        com.ensighten.C1845i.m7344a(r1);
        goto L_0x00be;
    L_0x0121:
        r1 = move-exception;
        r2 = com.ensighten.C1845i.m7362i();
        if (r2 == 0) goto L_0x003a;
    L_0x0128:
        com.ensighten.C1845i.m7344a(r1);
        goto L_0x003a;
    L_0x012d:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x00d2;
    L_0x0131:
        r0 = move-exception;
        goto L_0x00d2;
    L_0x0133:
        r1 = move-exception;
        r2 = r0;
        goto L_0x00ac;
    L_0x0137:
        r1 = move-exception;
        goto L_0x00ac;
    L_0x013a:
        r0 = move-exception;
        r5 = r0;
        r0 = r1;
        r1 = r5;
        goto L_0x00ac;
    L_0x0140:
        r1 = move-exception;
        r2 = r0;
        goto L_0x008c;
    L_0x0144:
        r1 = move-exception;
        goto L_0x008c;
    L_0x0147:
        r0 = move-exception;
        r5 = r0;
        r0 = r1;
        r1 = r5;
        goto L_0x008c;
    L_0x014d:
        r1 = move-exception;
        r2 = r0;
        goto L_0x006d;
    L_0x0151:
        r1 = move-exception;
        goto L_0x006d;
    L_0x0154:
        r0 = move-exception;
        r5 = r0;
        r0 = r1;
        r1 = r5;
        goto L_0x006d;
    L_0x015a:
        r1 = move-exception;
        r2 = r0;
        goto L_0x004e;
    L_0x015e:
        r1 = move-exception;
        goto L_0x004e;
    L_0x0161:
        r0 = move-exception;
        r5 = r0;
        r0 = r1;
        r1 = r5;
        goto L_0x004e;
    L_0x0167:
        r2 = r0;
        r3 = r0;
        goto L_0x0035;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ensighten.C1712M.m7223a(java.io.File, java.lang.String, android.content.Context, int, int):android.graphics.Bitmap");
    }

    /* renamed from: a */
    public void mo15044a() {
        if (Ensighten.getPersistentStoreEnabled()) {
            String str = this.f5680h.getFilesDir().getPath() + "/";
            try {
                JSONObject jSONObject = this.f5678f;
                final String jSONObject2 = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
                if (((long) jSONObject2.length()) <= C1715O.m7250a(new File(str))) {
                    try {
                        C17113 c17113 = new TraceFieldInterface() {
                            public Trace _nr_trace;

                            public void _nr_setTrace(Trace trace) {
                                try {
                                    this._nr_trace = trace;
                                } catch (Exception e) {
                                }
                            }

                            /* Access modifiers changed, original: protected|final|synthetic */
                            public final /* synthetic */ Object doInBackground(Object[] objArr) {
                                try {
                                    TraceMachine.enterMethod(this._nr_trace, "M$3#doInBackground", null);
                                } catch (NoSuchFieldError e) {
                                    while (true) {
                                        TraceMachine.enterMethod(null, "M$3#doInBackground", null);
                                    }
                                }
                                Void a = m7214a();
                                TraceMachine.exitMethod();
                                TraceMachine.unloadTraceContext(this);
                                return a;
                            }

                            /* JADX WARNING: Unknown top exception splitter block from list: {B:45:0x00b4=Splitter:B:45:0x00b4, B:81:0x0104=Splitter:B:81:0x0104, B:63:0x00da=Splitter:B:63:0x00da, B:26:0x008b=Splitter:B:26:0x008b} */
                            /* JADX WARNING: Removed duplicated region for block: B:21:0x0081  */
                            /* JADX WARNING: Removed duplicated region for block: B:21:0x0081  */
                            /* JADX WARNING: Removed duplicated region for block: B:21:0x0081  */
                            /* JADX WARNING: Removed duplicated region for block: B:21:0x0081  */
                            /* JADX WARNING: Removed duplicated region for block: B:100:0x012c A:{SYNTHETIC, Splitter:B:100:0x012c} */
                            /* JADX WARNING: Removed duplicated region for block: B:103:0x0131 A:{SYNTHETIC, Splitter:B:103:0x0131} */
                            /* JADX WARNING: Removed duplicated region for block: B:106:0x0136 A:{SYNTHETIC, Splitter:B:106:0x0136} */
                            /* JADX WARNING: Removed duplicated region for block: B:29:0x0091 A:{Catch:{ all -> 0x01ea }} */
                            /* JADX WARNING: Removed duplicated region for block: B:31:0x0096 A:{SYNTHETIC, Splitter:B:31:0x0096} */
                            /* JADX WARNING: Removed duplicated region for block: B:34:0x009b A:{SYNTHETIC, Splitter:B:34:0x009b} */
                            /* JADX WARNING: Removed duplicated region for block: B:37:0x00a0 A:{SYNTHETIC, Splitter:B:37:0x00a0} */
                            /* JADX WARNING: Removed duplicated region for block: B:21:0x0081  */
                            /* JADX WARNING: Removed duplicated region for block: B:48:0x00ba A:{Catch:{ all -> 0x01ea }} */
                            /* JADX WARNING: Removed duplicated region for block: B:50:0x00bf A:{SYNTHETIC, Splitter:B:50:0x00bf} */
                            /* JADX WARNING: Removed duplicated region for block: B:53:0x00c4 A:{SYNTHETIC, Splitter:B:53:0x00c4} */
                            /* JADX WARNING: Removed duplicated region for block: B:56:0x00c9 A:{SYNTHETIC, Splitter:B:56:0x00c9} */
                            /* JADX WARNING: Removed duplicated region for block: B:21:0x0081  */
                            /* JADX WARNING: Removed duplicated region for block: B:66:0x00e0 A:{Catch:{ all -> 0x01ea }} */
                            /* JADX WARNING: Removed duplicated region for block: B:68:0x00e9 A:{SYNTHETIC, Splitter:B:68:0x00e9} */
                            /* JADX WARNING: Removed duplicated region for block: B:71:0x00ee A:{SYNTHETIC, Splitter:B:71:0x00ee} */
                            /* JADX WARNING: Removed duplicated region for block: B:74:0x00f3 A:{SYNTHETIC, Splitter:B:74:0x00f3} */
                            /* JADX WARNING: Removed duplicated region for block: B:21:0x0081  */
                            /* JADX WARNING: Removed duplicated region for block: B:84:0x010a A:{Catch:{ all -> 0x01ea }} */
                            /* JADX WARNING: Removed duplicated region for block: B:86:0x010f A:{SYNTHETIC, Splitter:B:86:0x010f} */
                            /* JADX WARNING: Removed duplicated region for block: B:89:0x0114 A:{SYNTHETIC, Splitter:B:89:0x0114} */
                            /* JADX WARNING: Removed duplicated region for block: B:92:0x0119 A:{SYNTHETIC, Splitter:B:92:0x0119} */
                            /* JADX WARNING: Removed duplicated region for block: B:21:0x0081  */
                            /* JADX WARNING: Removed duplicated region for block: B:100:0x012c A:{SYNTHETIC, Splitter:B:100:0x012c} */
                            /* JADX WARNING: Removed duplicated region for block: B:103:0x0131 A:{SYNTHETIC, Splitter:B:103:0x0131} */
                            /* JADX WARNING: Removed duplicated region for block: B:106:0x0136 A:{SYNTHETIC, Splitter:B:106:0x0136} */
                            /* JADX WARNING: Removed duplicated region for block: B:29:0x0091 A:{Catch:{ all -> 0x01ea }} */
                            /* JADX WARNING: Removed duplicated region for block: B:31:0x0096 A:{SYNTHETIC, Splitter:B:31:0x0096} */
                            /* JADX WARNING: Removed duplicated region for block: B:34:0x009b A:{SYNTHETIC, Splitter:B:34:0x009b} */
                            /* JADX WARNING: Removed duplicated region for block: B:37:0x00a0 A:{SYNTHETIC, Splitter:B:37:0x00a0} */
                            /* JADX WARNING: Removed duplicated region for block: B:21:0x0081  */
                            /* JADX WARNING: Removed duplicated region for block: B:48:0x00ba A:{Catch:{ all -> 0x01ea }} */
                            /* JADX WARNING: Removed duplicated region for block: B:50:0x00bf A:{SYNTHETIC, Splitter:B:50:0x00bf} */
                            /* JADX WARNING: Removed duplicated region for block: B:53:0x00c4 A:{SYNTHETIC, Splitter:B:53:0x00c4} */
                            /* JADX WARNING: Removed duplicated region for block: B:56:0x00c9 A:{SYNTHETIC, Splitter:B:56:0x00c9} */
                            /* JADX WARNING: Removed duplicated region for block: B:21:0x0081  */
                            /* JADX WARNING: Removed duplicated region for block: B:66:0x00e0 A:{Catch:{ all -> 0x01ea }} */
                            /* JADX WARNING: Removed duplicated region for block: B:68:0x00e9 A:{SYNTHETIC, Splitter:B:68:0x00e9} */
                            /* JADX WARNING: Removed duplicated region for block: B:71:0x00ee A:{SYNTHETIC, Splitter:B:71:0x00ee} */
                            /* JADX WARNING: Removed duplicated region for block: B:74:0x00f3 A:{SYNTHETIC, Splitter:B:74:0x00f3} */
                            /* JADX WARNING: Removed duplicated region for block: B:21:0x0081  */
                            /* JADX WARNING: Removed duplicated region for block: B:84:0x010a A:{Catch:{ all -> 0x01ea }} */
                            /* JADX WARNING: Removed duplicated region for block: B:86:0x010f A:{SYNTHETIC, Splitter:B:86:0x010f} */
                            /* JADX WARNING: Removed duplicated region for block: B:89:0x0114 A:{SYNTHETIC, Splitter:B:89:0x0114} */
                            /* JADX WARNING: Removed duplicated region for block: B:92:0x0119 A:{SYNTHETIC, Splitter:B:92:0x0119} */
                            /* JADX WARNING: Removed duplicated region for block: B:21:0x0081  */
                            /* JADX WARNING: Removed duplicated region for block: B:48:0x00ba A:{Catch:{ all -> 0x01ea }} */
                            /* JADX WARNING: Removed duplicated region for block: B:50:0x00bf A:{SYNTHETIC, Splitter:B:50:0x00bf} */
                            /* JADX WARNING: Removed duplicated region for block: B:53:0x00c4 A:{SYNTHETIC, Splitter:B:53:0x00c4} */
                            /* JADX WARNING: Removed duplicated region for block: B:56:0x00c9 A:{SYNTHETIC, Splitter:B:56:0x00c9} */
                            /* JADX WARNING: Removed duplicated region for block: B:21:0x0081  */
                            /* JADX WARNING: Removed duplicated region for block: B:66:0x00e0 A:{Catch:{ all -> 0x01ea }} */
                            /* JADX WARNING: Removed duplicated region for block: B:68:0x00e9 A:{SYNTHETIC, Splitter:B:68:0x00e9} */
                            /* JADX WARNING: Removed duplicated region for block: B:71:0x00ee A:{SYNTHETIC, Splitter:B:71:0x00ee} */
                            /* JADX WARNING: Removed duplicated region for block: B:74:0x00f3 A:{SYNTHETIC, Splitter:B:74:0x00f3} */
                            /* JADX WARNING: Removed duplicated region for block: B:21:0x0081  */
                            /* JADX WARNING: Removed duplicated region for block: B:84:0x010a A:{Catch:{ all -> 0x01ea }} */
                            /* JADX WARNING: Removed duplicated region for block: B:86:0x010f A:{SYNTHETIC, Splitter:B:86:0x010f} */
                            /* JADX WARNING: Removed duplicated region for block: B:89:0x0114 A:{SYNTHETIC, Splitter:B:89:0x0114} */
                            /* JADX WARNING: Removed duplicated region for block: B:92:0x0119 A:{SYNTHETIC, Splitter:B:92:0x0119} */
                            /* JADX WARNING: Removed duplicated region for block: B:21:0x0081  */
                            /* JADX WARNING: Removed duplicated region for block: B:100:0x012c A:{SYNTHETIC, Splitter:B:100:0x012c} */
                            /* JADX WARNING: Removed duplicated region for block: B:103:0x0131 A:{SYNTHETIC, Splitter:B:103:0x0131} */
                            /* JADX WARNING: Removed duplicated region for block: B:106:0x0136 A:{SYNTHETIC, Splitter:B:106:0x0136} */
                            /* JADX WARNING: Missing block: B:41:0x00a9, code skipped:
            if (com.ensighten.C1845i.m7362i() == false) goto L_0x002c;
     */
                            /* JADX WARNING: Missing block: B:42:0x00ab, code skipped:
            com.ensighten.C1845i.m7344a(r1);
     */
                            /* JADX WARNING: Missing block: B:60:0x00d3, code skipped:
            if (com.ensighten.C1845i.m7362i() == false) goto L_0x002c;
     */
                            /* JADX WARNING: Missing block: B:78:0x00fd, code skipped:
            if (com.ensighten.C1845i.m7362i() == false) goto L_0x002c;
     */
                            /* JADX WARNING: Missing block: B:96:0x0123, code skipped:
            if (com.ensighten.C1845i.m7362i() == false) goto L_0x002c;
     */
                            /* JADX WARNING: Missing block: B:164:0x01dd, code skipped:
            if (com.ensighten.C1845i.m7362i() == false) goto L_0x002c;
     */
                            /* renamed from: a */
                            private java.lang.Void m7214a() {
                                /*
                                r7 = this;
                                r0 = 0;
                                r3 = 0;
                                r1 = com.ensighten.C1712M.this;	 Catch:{ FileNotFoundException -> 0x0087, IOException -> 0x00b0, OutOfMemoryError -> 0x00d6, Exception -> 0x0100, all -> 0x0126 }
                                r1 = r1.f5680h;	 Catch:{ FileNotFoundException -> 0x0087, IOException -> 0x00b0, OutOfMemoryError -> 0x00d6, Exception -> 0x0100, all -> 0x0126 }
                                r2 = "ensightenOptimizationConfig_new";
                                r4 = 0;
                                r5 = r1.openFileOutput(r2, r4);	 Catch:{ FileNotFoundException -> 0x0087, IOException -> 0x00b0, OutOfMemoryError -> 0x00d6, Exception -> 0x0100, all -> 0x0126 }
                                r4 = new java.io.BufferedOutputStream;	 Catch:{ FileNotFoundException -> 0x0211, IOException -> 0x0205, OutOfMemoryError -> 0x01f9, Exception -> 0x01ed, all -> 0x01e1 }
                                r4.<init>(r5);	 Catch:{ FileNotFoundException -> 0x0211, IOException -> 0x0205, OutOfMemoryError -> 0x01f9, Exception -> 0x01ed, all -> 0x01e1 }
                                r2 = new java.io.ObjectOutputStream;	 Catch:{ FileNotFoundException -> 0x0216, IOException -> 0x020a, OutOfMemoryError -> 0x01fe, Exception -> 0x01f2, all -> 0x01e6 }
                                r2.<init>(r4);	 Catch:{ FileNotFoundException -> 0x0216, IOException -> 0x020a, OutOfMemoryError -> 0x01fe, Exception -> 0x01f2, all -> 0x01e6 }
                                r1 = r1;	 Catch:{ FileNotFoundException -> 0x021a, IOException -> 0x020e, OutOfMemoryError -> 0x0202, Exception -> 0x01f6 }
                                r2.writeObject(r1);	 Catch:{ FileNotFoundException -> 0x021a, IOException -> 0x020e, OutOfMemoryError -> 0x0202, Exception -> 0x01f6 }
                                r0 = 1;
                                if (r2 == 0) goto L_0x0022;
                            L_0x001f:
                                r2.close();	 Catch:{ IOException -> 0x01c0 }
                            L_0x0022:
                                if (r4 == 0) goto L_0x0027;
                            L_0x0024:
                                r4.close();	 Catch:{ IOException -> 0x01cc }
                            L_0x0027:
                                if (r5 == 0) goto L_0x002c;
                            L_0x0029:
                                r5.close();	 Catch:{ IOException -> 0x01d8 }
                            L_0x002c:
                                r1 = new java.lang.StringBuilder;
                                r1.<init>();
                                r2 = com.ensighten.C1712M.this;
                                r2 = r2.f5680h;
                                r2 = r2.getFilesDir();
                                r2 = r2.getPath();
                                r1 = r1.append(r2);
                                r2 = "/";
                                r1 = r1.append(r2);
                                r1 = r1.toString();
                                r2 = new java.io.File;
                                r4 = new java.lang.StringBuilder;
                                r4.<init>();
                                r4 = r4.append(r1);
                                r5 = "ensightenOptimizationConfig_new";
                                r4 = r4.append(r5);
                                r4 = r4.toString();
                                r2.<init>(r4);
                                r4 = new java.io.File;
                                r5 = new java.lang.StringBuilder;
                                r5.<init>();
                                r1 = r5.append(r1);
                                r5 = "ensightenOptimizationConfig";
                                r1 = r1.append(r5);
                                r1 = r1.toString();
                                r4.<init>(r1);
                                r1 = r2.exists();
                                if (r1 == 0) goto L_0x0086;
                            L_0x0081:
                                if (r0 == 0) goto L_0x013a;
                            L_0x0083:
                                r2.renameTo(r4);
                            L_0x0086:
                                return r3;
                            L_0x0087:
                                r1 = move-exception;
                                r2 = r3;
                                r4 = r3;
                                r5 = r3;
                            L_0x008b:
                                r6 = com.ensighten.C1845i.m7362i();	 Catch:{ all -> 0x01ea }
                                if (r6 == 0) goto L_0x0094;
                            L_0x0091:
                                com.ensighten.C1845i.m7344a(r1);	 Catch:{ all -> 0x01ea }
                            L_0x0094:
                                if (r2 == 0) goto L_0x0099;
                            L_0x0096:
                                r2.close();	 Catch:{ IOException -> 0x0160 }
                            L_0x0099:
                                if (r4 == 0) goto L_0x009e;
                            L_0x009b:
                                r4.close();	 Catch:{ IOException -> 0x016c }
                            L_0x009e:
                                if (r5 == 0) goto L_0x002c;
                            L_0x00a0:
                                r5.close();	 Catch:{ IOException -> 0x00a4 }
                                goto L_0x002c;
                            L_0x00a4:
                                r1 = move-exception;
                                r2 = com.ensighten.C1845i.m7362i();
                                if (r2 == 0) goto L_0x002c;
                            L_0x00ab:
                                com.ensighten.C1845i.m7344a(r1);
                                goto L_0x002c;
                            L_0x00b0:
                                r1 = move-exception;
                                r2 = r3;
                                r4 = r3;
                                r5 = r3;
                            L_0x00b4:
                                r6 = com.ensighten.C1845i.m7362i();	 Catch:{ all -> 0x01ea }
                                if (r6 == 0) goto L_0x00bd;
                            L_0x00ba:
                                com.ensighten.C1845i.m7344a(r1);	 Catch:{ all -> 0x01ea }
                            L_0x00bd:
                                if (r2 == 0) goto L_0x00c2;
                            L_0x00bf:
                                r2.close();	 Catch:{ IOException -> 0x0178 }
                            L_0x00c2:
                                if (r4 == 0) goto L_0x00c7;
                            L_0x00c4:
                                r4.close();	 Catch:{ IOException -> 0x0184 }
                            L_0x00c7:
                                if (r5 == 0) goto L_0x002c;
                            L_0x00c9:
                                r5.close();	 Catch:{ IOException -> 0x00ce }
                                goto L_0x002c;
                            L_0x00ce:
                                r1 = move-exception;
                                r2 = com.ensighten.C1845i.m7362i();
                                if (r2 == 0) goto L_0x002c;
                            L_0x00d5:
                                goto L_0x00ab;
                            L_0x00d6:
                                r1 = move-exception;
                                r2 = r3;
                                r4 = r3;
                                r5 = r3;
                            L_0x00da:
                                r6 = com.ensighten.C1845i.m7362i();	 Catch:{ all -> 0x01ea }
                                if (r6 == 0) goto L_0x00e7;
                            L_0x00e0:
                                r1 = r1.getMessage();	 Catch:{ all -> 0x01ea }
                                com.ensighten.C1845i.m7345a(r1);	 Catch:{ all -> 0x01ea }
                            L_0x00e7:
                                if (r2 == 0) goto L_0x00ec;
                            L_0x00e9:
                                r2.close();	 Catch:{ IOException -> 0x0190 }
                            L_0x00ec:
                                if (r4 == 0) goto L_0x00f1;
                            L_0x00ee:
                                r4.close();	 Catch:{ IOException -> 0x019c }
                            L_0x00f1:
                                if (r5 == 0) goto L_0x002c;
                            L_0x00f3:
                                r5.close();	 Catch:{ IOException -> 0x00f8 }
                                goto L_0x002c;
                            L_0x00f8:
                                r1 = move-exception;
                                r2 = com.ensighten.C1845i.m7362i();
                                if (r2 == 0) goto L_0x002c;
                            L_0x00ff:
                                goto L_0x00ab;
                            L_0x0100:
                                r1 = move-exception;
                                r2 = r3;
                                r4 = r3;
                                r5 = r3;
                            L_0x0104:
                                r6 = com.ensighten.C1845i.m7362i();	 Catch:{ all -> 0x01ea }
                                if (r6 == 0) goto L_0x010d;
                            L_0x010a:
                                com.ensighten.C1845i.m7344a(r1);	 Catch:{ all -> 0x01ea }
                            L_0x010d:
                                if (r2 == 0) goto L_0x0112;
                            L_0x010f:
                                r2.close();	 Catch:{ IOException -> 0x01a8 }
                            L_0x0112:
                                if (r4 == 0) goto L_0x0117;
                            L_0x0114:
                                r4.close();	 Catch:{ IOException -> 0x01b4 }
                            L_0x0117:
                                if (r5 == 0) goto L_0x002c;
                            L_0x0119:
                                r5.close();	 Catch:{ IOException -> 0x011e }
                                goto L_0x002c;
                            L_0x011e:
                                r1 = move-exception;
                                r2 = com.ensighten.C1845i.m7362i();
                                if (r2 == 0) goto L_0x002c;
                            L_0x0125:
                                goto L_0x00ab;
                            L_0x0126:
                                r0 = move-exception;
                                r2 = r3;
                                r4 = r3;
                                r5 = r3;
                            L_0x012a:
                                if (r2 == 0) goto L_0x012f;
                            L_0x012c:
                                r2.close();	 Catch:{ IOException -> 0x013f }
                            L_0x012f:
                                if (r4 == 0) goto L_0x0134;
                            L_0x0131:
                                r4.close();	 Catch:{ IOException -> 0x014a }
                            L_0x0134:
                                if (r5 == 0) goto L_0x0139;
                            L_0x0136:
                                r5.close();	 Catch:{ IOException -> 0x0155 }
                            L_0x0139:
                                throw r0;
                            L_0x013a:
                                r2.delete();
                                goto L_0x0086;
                            L_0x013f:
                                r1 = move-exception;
                                r2 = com.ensighten.C1845i.m7362i();
                                if (r2 == 0) goto L_0x012f;
                            L_0x0146:
                                com.ensighten.C1845i.m7344a(r1);
                                goto L_0x012f;
                            L_0x014a:
                                r1 = move-exception;
                                r2 = com.ensighten.C1845i.m7362i();
                                if (r2 == 0) goto L_0x0134;
                            L_0x0151:
                                com.ensighten.C1845i.m7344a(r1);
                                goto L_0x0134;
                            L_0x0155:
                                r1 = move-exception;
                                r2 = com.ensighten.C1845i.m7362i();
                                if (r2 == 0) goto L_0x0139;
                            L_0x015c:
                                com.ensighten.C1845i.m7344a(r1);
                                goto L_0x0139;
                            L_0x0160:
                                r1 = move-exception;
                                r2 = com.ensighten.C1845i.m7362i();
                                if (r2 == 0) goto L_0x0099;
                            L_0x0167:
                                com.ensighten.C1845i.m7344a(r1);
                                goto L_0x0099;
                            L_0x016c:
                                r1 = move-exception;
                                r2 = com.ensighten.C1845i.m7362i();
                                if (r2 == 0) goto L_0x009e;
                            L_0x0173:
                                com.ensighten.C1845i.m7344a(r1);
                                goto L_0x009e;
                            L_0x0178:
                                r1 = move-exception;
                                r2 = com.ensighten.C1845i.m7362i();
                                if (r2 == 0) goto L_0x00c2;
                            L_0x017f:
                                com.ensighten.C1845i.m7344a(r1);
                                goto L_0x00c2;
                            L_0x0184:
                                r1 = move-exception;
                                r2 = com.ensighten.C1845i.m7362i();
                                if (r2 == 0) goto L_0x00c7;
                            L_0x018b:
                                com.ensighten.C1845i.m7344a(r1);
                                goto L_0x00c7;
                            L_0x0190:
                                r1 = move-exception;
                                r2 = com.ensighten.C1845i.m7362i();
                                if (r2 == 0) goto L_0x00ec;
                            L_0x0197:
                                com.ensighten.C1845i.m7344a(r1);
                                goto L_0x00ec;
                            L_0x019c:
                                r1 = move-exception;
                                r2 = com.ensighten.C1845i.m7362i();
                                if (r2 == 0) goto L_0x00f1;
                            L_0x01a3:
                                com.ensighten.C1845i.m7344a(r1);
                                goto L_0x00f1;
                            L_0x01a8:
                                r1 = move-exception;
                                r2 = com.ensighten.C1845i.m7362i();
                                if (r2 == 0) goto L_0x0112;
                            L_0x01af:
                                com.ensighten.C1845i.m7344a(r1);
                                goto L_0x0112;
                            L_0x01b4:
                                r1 = move-exception;
                                r2 = com.ensighten.C1845i.m7362i();
                                if (r2 == 0) goto L_0x0117;
                            L_0x01bb:
                                com.ensighten.C1845i.m7344a(r1);
                                goto L_0x0117;
                            L_0x01c0:
                                r1 = move-exception;
                                r2 = com.ensighten.C1845i.m7362i();
                                if (r2 == 0) goto L_0x0022;
                            L_0x01c7:
                                com.ensighten.C1845i.m7344a(r1);
                                goto L_0x0022;
                            L_0x01cc:
                                r1 = move-exception;
                                r2 = com.ensighten.C1845i.m7362i();
                                if (r2 == 0) goto L_0x0027;
                            L_0x01d3:
                                com.ensighten.C1845i.m7344a(r1);
                                goto L_0x0027;
                            L_0x01d8:
                                r1 = move-exception;
                                r2 = com.ensighten.C1845i.m7362i();
                                if (r2 == 0) goto L_0x002c;
                            L_0x01df:
                                goto L_0x00ab;
                            L_0x01e1:
                                r0 = move-exception;
                                r2 = r3;
                                r4 = r3;
                                goto L_0x012a;
                            L_0x01e6:
                                r0 = move-exception;
                                r2 = r3;
                                goto L_0x012a;
                            L_0x01ea:
                                r0 = move-exception;
                                goto L_0x012a;
                            L_0x01ed:
                                r1 = move-exception;
                                r2 = r3;
                                r4 = r3;
                                goto L_0x0104;
                            L_0x01f2:
                                r1 = move-exception;
                                r2 = r3;
                                goto L_0x0104;
                            L_0x01f6:
                                r1 = move-exception;
                                goto L_0x0104;
                            L_0x01f9:
                                r1 = move-exception;
                                r2 = r3;
                                r4 = r3;
                                goto L_0x00da;
                            L_0x01fe:
                                r1 = move-exception;
                                r2 = r3;
                                goto L_0x00da;
                            L_0x0202:
                                r1 = move-exception;
                                goto L_0x00da;
                            L_0x0205:
                                r1 = move-exception;
                                r2 = r3;
                                r4 = r3;
                                goto L_0x00b4;
                            L_0x020a:
                                r1 = move-exception;
                                r2 = r3;
                                goto L_0x00b4;
                            L_0x020e:
                                r1 = move-exception;
                                goto L_0x00b4;
                            L_0x0211:
                                r1 = move-exception;
                                r2 = r3;
                                r4 = r3;
                                goto L_0x008b;
                            L_0x0216:
                                r1 = move-exception;
                                r2 = r3;
                                goto L_0x008b;
                            L_0x021a:
                                r1 = move-exception;
                                goto L_0x008b;
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.ensighten.C1712M$C17113.m7214a():java.lang.Void");
                            }
                        };
                        Void[] voidArr = new Void[0];
                        if (c17113 instanceof AsyncTask) {
                            AsyncTaskInstrumentation.execute(c17113, voidArr);
                        } else {
                            c17113.execute(voidArr);
                        }
                    } catch (Exception e) {
                        if (C1845i.m7362i()) {
                            C1845i.m7344a(e);
                        }
                    }
                } else if (C1845i.m7362i()) {
                    C1845i.m7345a("Not writing file to storage due to insufficient space on disk.");
                }
            } catch (OutOfMemoryError e2) {
                if (C1845i.m7362i()) {
                    C1845i.m7345a(e2.getMessage());
                }
            }
        } else if (C1845i.m7362i()) {
            C1845i.m7345a("The optimization configuration was not saved due to persistent storage being disabled.");
        }
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:96:0x00d3=Splitter:B:96:0x00d3, B:42:0x0066=Splitter:B:42:0x0066, B:60:0x008a=Splitter:B:60:0x008a, B:114:0x00fd=Splitter:B:114:0x00fd, B:78:0x00ae=Splitter:B:78:0x00ae} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0045 A:{Catch:{ all -> 0x0210 }} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x004a A:{SYNTHETIC, Splitter:B:28:0x004a} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x004f A:{SYNTHETIC, Splitter:B:31:0x004f} */
    /* JADX WARNING: Removed duplicated region for block: B:253:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0054 A:{SYNTHETIC, Splitter:B:34:0x0054} */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x006c A:{Catch:{ all -> 0x0209 }} */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0071 A:{SYNTHETIC, Splitter:B:47:0x0071} */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0076 A:{SYNTHETIC, Splitter:B:50:0x0076} */
    /* JADX WARNING: Removed duplicated region for block: B:256:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x007b A:{SYNTHETIC, Splitter:B:53:0x007b} */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0090 A:{Catch:{ all -> 0x0209 }} */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0095 A:{SYNTHETIC, Splitter:B:65:0x0095} */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x009a A:{SYNTHETIC, Splitter:B:68:0x009a} */
    /* JADX WARNING: Removed duplicated region for block: B:258:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x009f A:{SYNTHETIC, Splitter:B:71:0x009f} */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x00b4 A:{Catch:{ all -> 0x0209 }} */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x00b9 A:{SYNTHETIC, Splitter:B:83:0x00b9} */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x00be A:{SYNTHETIC, Splitter:B:86:0x00be} */
    /* JADX WARNING: Removed duplicated region for block: B:260:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x00c3 A:{SYNTHETIC, Splitter:B:89:0x00c3} */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x00d9 A:{Catch:{ all -> 0x0209 }} */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x00e2 A:{SYNTHETIC, Splitter:B:101:0x00e2} */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x00e7 A:{SYNTHETIC, Splitter:B:104:0x00e7} */
    /* JADX WARNING: Removed duplicated region for block: B:262:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x00ec A:{SYNTHETIC, Splitter:B:107:0x00ec} */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x0103 A:{Catch:{ all -> 0x0209 }} */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x0108 A:{SYNTHETIC, Splitter:B:119:0x0108} */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x010d A:{SYNTHETIC, Splitter:B:122:0x010d} */
    /* JADX WARNING: Removed duplicated region for block: B:264:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x0112 A:{SYNTHETIC, Splitter:B:125:0x0112} */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x0125 A:{SYNTHETIC, Splitter:B:133:0x0125} */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x012a A:{SYNTHETIC, Splitter:B:136:0x012a} */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x012f A:{SYNTHETIC, Splitter:B:139:0x012f} */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x0125 A:{SYNTHETIC, Splitter:B:133:0x0125} */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x012a A:{SYNTHETIC, Splitter:B:136:0x012a} */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x012f A:{SYNTHETIC, Splitter:B:139:0x012f} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0045 A:{Catch:{ all -> 0x0210 }} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x004a A:{SYNTHETIC, Splitter:B:28:0x004a} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x004f A:{SYNTHETIC, Splitter:B:31:0x004f} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0054 A:{SYNTHETIC, Splitter:B:34:0x0054} */
    /* JADX WARNING: Removed duplicated region for block: B:253:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x006c A:{Catch:{ all -> 0x0209 }} */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0071 A:{SYNTHETIC, Splitter:B:47:0x0071} */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0076 A:{SYNTHETIC, Splitter:B:50:0x0076} */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x007b A:{SYNTHETIC, Splitter:B:53:0x007b} */
    /* JADX WARNING: Removed duplicated region for block: B:256:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0090 A:{Catch:{ all -> 0x0209 }} */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0095 A:{SYNTHETIC, Splitter:B:65:0x0095} */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x009a A:{SYNTHETIC, Splitter:B:68:0x009a} */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x009f A:{SYNTHETIC, Splitter:B:71:0x009f} */
    /* JADX WARNING: Removed duplicated region for block: B:258:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x00b4 A:{Catch:{ all -> 0x0209 }} */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x00b9 A:{SYNTHETIC, Splitter:B:83:0x00b9} */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x00be A:{SYNTHETIC, Splitter:B:86:0x00be} */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x00c3 A:{SYNTHETIC, Splitter:B:89:0x00c3} */
    /* JADX WARNING: Removed duplicated region for block: B:260:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x00d9 A:{Catch:{ all -> 0x0209 }} */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x00e2 A:{SYNTHETIC, Splitter:B:101:0x00e2} */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x00e7 A:{SYNTHETIC, Splitter:B:104:0x00e7} */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x00ec A:{SYNTHETIC, Splitter:B:107:0x00ec} */
    /* JADX WARNING: Removed duplicated region for block: B:262:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x0103 A:{Catch:{ all -> 0x0209 }} */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x0108 A:{SYNTHETIC, Splitter:B:119:0x0108} */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x010d A:{SYNTHETIC, Splitter:B:122:0x010d} */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x0112 A:{SYNTHETIC, Splitter:B:125:0x0112} */
    /* JADX WARNING: Removed duplicated region for block: B:264:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x0125 A:{SYNTHETIC, Splitter:B:133:0x0125} */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x012a A:{SYNTHETIC, Splitter:B:136:0x012a} */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x012f A:{SYNTHETIC, Splitter:B:139:0x012f} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0045 A:{Catch:{ all -> 0x0210 }} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x004a A:{SYNTHETIC, Splitter:B:28:0x004a} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x004f A:{SYNTHETIC, Splitter:B:31:0x004f} */
    /* JADX WARNING: Removed duplicated region for block: B:253:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0054 A:{SYNTHETIC, Splitter:B:34:0x0054} */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x006c A:{Catch:{ all -> 0x0209 }} */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0071 A:{SYNTHETIC, Splitter:B:47:0x0071} */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0076 A:{SYNTHETIC, Splitter:B:50:0x0076} */
    /* JADX WARNING: Removed duplicated region for block: B:256:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x007b A:{SYNTHETIC, Splitter:B:53:0x007b} */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0090 A:{Catch:{ all -> 0x0209 }} */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0095 A:{SYNTHETIC, Splitter:B:65:0x0095} */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x009a A:{SYNTHETIC, Splitter:B:68:0x009a} */
    /* JADX WARNING: Removed duplicated region for block: B:258:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x009f A:{SYNTHETIC, Splitter:B:71:0x009f} */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x00b4 A:{Catch:{ all -> 0x0209 }} */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x00b9 A:{SYNTHETIC, Splitter:B:83:0x00b9} */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x00be A:{SYNTHETIC, Splitter:B:86:0x00be} */
    /* JADX WARNING: Removed duplicated region for block: B:260:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x00c3 A:{SYNTHETIC, Splitter:B:89:0x00c3} */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x00d9 A:{Catch:{ all -> 0x0209 }} */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x00e2 A:{SYNTHETIC, Splitter:B:101:0x00e2} */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x00e7 A:{SYNTHETIC, Splitter:B:104:0x00e7} */
    /* JADX WARNING: Removed duplicated region for block: B:262:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x00ec A:{SYNTHETIC, Splitter:B:107:0x00ec} */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x0103 A:{Catch:{ all -> 0x0209 }} */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x0108 A:{SYNTHETIC, Splitter:B:119:0x0108} */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x010d A:{SYNTHETIC, Splitter:B:122:0x010d} */
    /* JADX WARNING: Removed duplicated region for block: B:264:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x0112 A:{SYNTHETIC, Splitter:B:125:0x0112} */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x0125 A:{SYNTHETIC, Splitter:B:133:0x0125} */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x012a A:{SYNTHETIC, Splitter:B:136:0x012a} */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x012f A:{SYNTHETIC, Splitter:B:139:0x012f} */
    /* JADX WARNING: Missing block: B:39:0x005f, code skipped:
            com.ensighten.C1845i.m7344a(r0);
     */
    /* JADX WARNING: Missing block: B:255:?, code skipped:
            return;
     */
    /* renamed from: c */
    private void m7233c() {
        /*
        r5 = this;
        r1 = 0;
        r0 = com.ensighten.C1845i.m7362i();	 Catch:{ FileNotFoundException -> 0x003c, IOException -> 0x0063, JSONException -> 0x0087, NullPointerException -> 0x00ab, OutOfMemoryError -> 0x00d0, Exception -> 0x00fa, all -> 0x0120 }
        if (r0 == 0) goto L_0x000c;
    L_0x0007:
        r0 = "Loading the optimization configuration file.";
        com.ensighten.C1845i.m7345a(r0);	 Catch:{ FileNotFoundException -> 0x003c, IOException -> 0x0063, JSONException -> 0x0087, NullPointerException -> 0x00ab, OutOfMemoryError -> 0x00d0, Exception -> 0x00fa, all -> 0x0120 }
    L_0x000c:
        r0 = r5.f5680h;	 Catch:{ FileNotFoundException -> 0x003c, IOException -> 0x0063, JSONException -> 0x0087, NullPointerException -> 0x00ab, OutOfMemoryError -> 0x00d0, Exception -> 0x00fa, all -> 0x0120 }
        r2 = "ensightenOptimizationConfig";
        r4 = r0.openFileInput(r2);	 Catch:{ FileNotFoundException -> 0x003c, IOException -> 0x0063, JSONException -> 0x0087, NullPointerException -> 0x00ab, OutOfMemoryError -> 0x00d0, Exception -> 0x00fa, all -> 0x0120 }
        r3 = new java.io.BufferedInputStream;	 Catch:{ FileNotFoundException -> 0x024c, IOException -> 0x0241, JSONException -> 0x0236, NullPointerException -> 0x022b, OutOfMemoryError -> 0x0220, Exception -> 0x0215, all -> 0x0205 }
        r3.<init>(r4);	 Catch:{ FileNotFoundException -> 0x024c, IOException -> 0x0241, JSONException -> 0x0236, NullPointerException -> 0x022b, OutOfMemoryError -> 0x0220, Exception -> 0x0215, all -> 0x0205 }
        r2 = new java.io.ObjectInputStream;	 Catch:{ FileNotFoundException -> 0x0251, IOException -> 0x0245, JSONException -> 0x023a, NullPointerException -> 0x022f, OutOfMemoryError -> 0x0224, Exception -> 0x0219 }
        r2.<init>(r3);	 Catch:{ FileNotFoundException -> 0x0251, IOException -> 0x0245, JSONException -> 0x023a, NullPointerException -> 0x022f, OutOfMemoryError -> 0x0224, Exception -> 0x0219 }
        r0 = new org.json.JSONObject;	 Catch:{ FileNotFoundException -> 0x0256, IOException -> 0x0248, JSONException -> 0x023d, NullPointerException -> 0x0232, OutOfMemoryError -> 0x0227, Exception -> 0x021c, all -> 0x020c }
        r0 = r2.readObject();	 Catch:{ FileNotFoundException -> 0x0256, IOException -> 0x0248, JSONException -> 0x023d, NullPointerException -> 0x0232, OutOfMemoryError -> 0x0227, Exception -> 0x021c, all -> 0x020c }
        r0 = (java.lang.String) r0;	 Catch:{ FileNotFoundException -> 0x0256, IOException -> 0x0248, JSONException -> 0x023d, NullPointerException -> 0x0232, OutOfMemoryError -> 0x0227, Exception -> 0x021c, all -> 0x020c }
        r0 = com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation.init(r0);	 Catch:{ FileNotFoundException -> 0x0256, IOException -> 0x0248, JSONException -> 0x023d, NullPointerException -> 0x0232, OutOfMemoryError -> 0x0227, Exception -> 0x021c, all -> 0x020c }
        r5.f5678f = r0;	 Catch:{ FileNotFoundException -> 0x0256, IOException -> 0x0248, JSONException -> 0x023d, NullPointerException -> 0x0232, OutOfMemoryError -> 0x0227, Exception -> 0x021c, all -> 0x020c }
        if (r2 == 0) goto L_0x0031;
    L_0x002e:
        r2.close();	 Catch:{ IOException -> 0x01e4 }
    L_0x0031:
        if (r3 == 0) goto L_0x0036;
    L_0x0033:
        r3.close();	 Catch:{ IOException -> 0x01f0 }
    L_0x0036:
        if (r4 == 0) goto L_0x003b;
    L_0x0038:
        r4.close();	 Catch:{ IOException -> 0x01fc }
    L_0x003b:
        return;
    L_0x003c:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
    L_0x003f:
        r4 = com.ensighten.C1845i.m7362i();	 Catch:{ all -> 0x0210 }
        if (r4 == 0) goto L_0x0048;
    L_0x0045:
        com.ensighten.C1845i.m7344a(r0);	 Catch:{ all -> 0x0210 }
    L_0x0048:
        if (r1 == 0) goto L_0x004d;
    L_0x004a:
        r1.close();	 Catch:{ IOException -> 0x0154 }
    L_0x004d:
        if (r2 == 0) goto L_0x0052;
    L_0x004f:
        r2.close();	 Catch:{ IOException -> 0x0160 }
    L_0x0052:
        if (r3 == 0) goto L_0x003b;
    L_0x0054:
        r3.close();	 Catch:{ IOException -> 0x0058 }
        goto L_0x003b;
    L_0x0058:
        r0 = move-exception;
        r1 = com.ensighten.C1845i.m7362i();
        if (r1 == 0) goto L_0x003b;
    L_0x005f:
        com.ensighten.C1845i.m7344a(r0);
        goto L_0x003b;
    L_0x0063:
        r0 = move-exception;
        r3 = r1;
        r4 = r1;
    L_0x0066:
        r2 = com.ensighten.C1845i.m7362i();	 Catch:{ all -> 0x0209 }
        if (r2 == 0) goto L_0x006f;
    L_0x006c:
        com.ensighten.C1845i.m7344a(r0);	 Catch:{ all -> 0x0209 }
    L_0x006f:
        if (r1 == 0) goto L_0x0074;
    L_0x0071:
        r1.close();	 Catch:{ IOException -> 0x016c }
    L_0x0074:
        if (r3 == 0) goto L_0x0079;
    L_0x0076:
        r3.close();	 Catch:{ IOException -> 0x0178 }
    L_0x0079:
        if (r4 == 0) goto L_0x003b;
    L_0x007b:
        r4.close();	 Catch:{ IOException -> 0x007f }
        goto L_0x003b;
    L_0x007f:
        r0 = move-exception;
        r1 = com.ensighten.C1845i.m7362i();
        if (r1 == 0) goto L_0x003b;
    L_0x0086:
        goto L_0x005f;
    L_0x0087:
        r0 = move-exception;
        r3 = r1;
        r4 = r1;
    L_0x008a:
        r2 = com.ensighten.C1845i.m7362i();	 Catch:{ all -> 0x0209 }
        if (r2 == 0) goto L_0x0093;
    L_0x0090:
        com.ensighten.C1845i.m7344a(r0);	 Catch:{ all -> 0x0209 }
    L_0x0093:
        if (r1 == 0) goto L_0x0098;
    L_0x0095:
        r1.close();	 Catch:{ IOException -> 0x0184 }
    L_0x0098:
        if (r3 == 0) goto L_0x009d;
    L_0x009a:
        r3.close();	 Catch:{ IOException -> 0x0190 }
    L_0x009d:
        if (r4 == 0) goto L_0x003b;
    L_0x009f:
        r4.close();	 Catch:{ IOException -> 0x00a3 }
        goto L_0x003b;
    L_0x00a3:
        r0 = move-exception;
        r1 = com.ensighten.C1845i.m7362i();
        if (r1 == 0) goto L_0x003b;
    L_0x00aa:
        goto L_0x005f;
    L_0x00ab:
        r0 = move-exception;
        r3 = r1;
        r4 = r1;
    L_0x00ae:
        r2 = com.ensighten.C1845i.m7362i();	 Catch:{ all -> 0x0209 }
        if (r2 == 0) goto L_0x00b7;
    L_0x00b4:
        com.ensighten.C1845i.m7344a(r0);	 Catch:{ all -> 0x0209 }
    L_0x00b7:
        if (r1 == 0) goto L_0x00bc;
    L_0x00b9:
        r1.close();	 Catch:{ IOException -> 0x019c }
    L_0x00bc:
        if (r3 == 0) goto L_0x00c1;
    L_0x00be:
        r3.close();	 Catch:{ IOException -> 0x01a8 }
    L_0x00c1:
        if (r4 == 0) goto L_0x003b;
    L_0x00c3:
        r4.close();	 Catch:{ IOException -> 0x00c8 }
        goto L_0x003b;
    L_0x00c8:
        r0 = move-exception;
        r1 = com.ensighten.C1845i.m7362i();
        if (r1 == 0) goto L_0x003b;
    L_0x00cf:
        goto L_0x005f;
    L_0x00d0:
        r0 = move-exception;
        r3 = r1;
        r4 = r1;
    L_0x00d3:
        r2 = com.ensighten.C1845i.m7362i();	 Catch:{ all -> 0x0209 }
        if (r2 == 0) goto L_0x00e0;
    L_0x00d9:
        r0 = r0.getMessage();	 Catch:{ all -> 0x0209 }
        com.ensighten.C1845i.m7345a(r0);	 Catch:{ all -> 0x0209 }
    L_0x00e0:
        if (r1 == 0) goto L_0x00e5;
    L_0x00e2:
        r1.close();	 Catch:{ IOException -> 0x01b4 }
    L_0x00e5:
        if (r3 == 0) goto L_0x00ea;
    L_0x00e7:
        r3.close();	 Catch:{ IOException -> 0x01c0 }
    L_0x00ea:
        if (r4 == 0) goto L_0x003b;
    L_0x00ec:
        r4.close();	 Catch:{ IOException -> 0x00f1 }
        goto L_0x003b;
    L_0x00f1:
        r0 = move-exception;
        r1 = com.ensighten.C1845i.m7362i();
        if (r1 == 0) goto L_0x003b;
    L_0x00f8:
        goto L_0x005f;
    L_0x00fa:
        r0 = move-exception;
        r3 = r1;
        r4 = r1;
    L_0x00fd:
        r2 = com.ensighten.C1845i.m7362i();	 Catch:{ all -> 0x0209 }
        if (r2 == 0) goto L_0x0106;
    L_0x0103:
        com.ensighten.C1845i.m7344a(r0);	 Catch:{ all -> 0x0209 }
    L_0x0106:
        if (r1 == 0) goto L_0x010b;
    L_0x0108:
        r1.close();	 Catch:{ IOException -> 0x01cc }
    L_0x010b:
        if (r3 == 0) goto L_0x0110;
    L_0x010d:
        r3.close();	 Catch:{ IOException -> 0x01d8 }
    L_0x0110:
        if (r4 == 0) goto L_0x003b;
    L_0x0112:
        r4.close();	 Catch:{ IOException -> 0x0117 }
        goto L_0x003b;
    L_0x0117:
        r0 = move-exception;
        r1 = com.ensighten.C1845i.m7362i();
        if (r1 == 0) goto L_0x003b;
    L_0x011e:
        goto L_0x005f;
    L_0x0120:
        r0 = move-exception;
        r3 = r1;
        r4 = r1;
    L_0x0123:
        if (r1 == 0) goto L_0x0128;
    L_0x0125:
        r1.close();	 Catch:{ IOException -> 0x0133 }
    L_0x0128:
        if (r3 == 0) goto L_0x012d;
    L_0x012a:
        r3.close();	 Catch:{ IOException -> 0x013e }
    L_0x012d:
        if (r4 == 0) goto L_0x0132;
    L_0x012f:
        r4.close();	 Catch:{ IOException -> 0x0149 }
    L_0x0132:
        throw r0;
    L_0x0133:
        r1 = move-exception;
        r2 = com.ensighten.C1845i.m7362i();
        if (r2 == 0) goto L_0x0128;
    L_0x013a:
        com.ensighten.C1845i.m7344a(r1);
        goto L_0x0128;
    L_0x013e:
        r1 = move-exception;
        r2 = com.ensighten.C1845i.m7362i();
        if (r2 == 0) goto L_0x012d;
    L_0x0145:
        com.ensighten.C1845i.m7344a(r1);
        goto L_0x012d;
    L_0x0149:
        r1 = move-exception;
        r2 = com.ensighten.C1845i.m7362i();
        if (r2 == 0) goto L_0x0132;
    L_0x0150:
        com.ensighten.C1845i.m7344a(r1);
        goto L_0x0132;
    L_0x0154:
        r0 = move-exception;
        r1 = com.ensighten.C1845i.m7362i();
        if (r1 == 0) goto L_0x004d;
    L_0x015b:
        com.ensighten.C1845i.m7344a(r0);
        goto L_0x004d;
    L_0x0160:
        r0 = move-exception;
        r1 = com.ensighten.C1845i.m7362i();
        if (r1 == 0) goto L_0x0052;
    L_0x0167:
        com.ensighten.C1845i.m7344a(r0);
        goto L_0x0052;
    L_0x016c:
        r0 = move-exception;
        r1 = com.ensighten.C1845i.m7362i();
        if (r1 == 0) goto L_0x0074;
    L_0x0173:
        com.ensighten.C1845i.m7344a(r0);
        goto L_0x0074;
    L_0x0178:
        r0 = move-exception;
        r1 = com.ensighten.C1845i.m7362i();
        if (r1 == 0) goto L_0x0079;
    L_0x017f:
        com.ensighten.C1845i.m7344a(r0);
        goto L_0x0079;
    L_0x0184:
        r0 = move-exception;
        r1 = com.ensighten.C1845i.m7362i();
        if (r1 == 0) goto L_0x0098;
    L_0x018b:
        com.ensighten.C1845i.m7344a(r0);
        goto L_0x0098;
    L_0x0190:
        r0 = move-exception;
        r1 = com.ensighten.C1845i.m7362i();
        if (r1 == 0) goto L_0x009d;
    L_0x0197:
        com.ensighten.C1845i.m7344a(r0);
        goto L_0x009d;
    L_0x019c:
        r0 = move-exception;
        r1 = com.ensighten.C1845i.m7362i();
        if (r1 == 0) goto L_0x00bc;
    L_0x01a3:
        com.ensighten.C1845i.m7344a(r0);
        goto L_0x00bc;
    L_0x01a8:
        r0 = move-exception;
        r1 = com.ensighten.C1845i.m7362i();
        if (r1 == 0) goto L_0x00c1;
    L_0x01af:
        com.ensighten.C1845i.m7344a(r0);
        goto L_0x00c1;
    L_0x01b4:
        r0 = move-exception;
        r1 = com.ensighten.C1845i.m7362i();
        if (r1 == 0) goto L_0x00e5;
    L_0x01bb:
        com.ensighten.C1845i.m7344a(r0);
        goto L_0x00e5;
    L_0x01c0:
        r0 = move-exception;
        r1 = com.ensighten.C1845i.m7362i();
        if (r1 == 0) goto L_0x00ea;
    L_0x01c7:
        com.ensighten.C1845i.m7344a(r0);
        goto L_0x00ea;
    L_0x01cc:
        r0 = move-exception;
        r1 = com.ensighten.C1845i.m7362i();
        if (r1 == 0) goto L_0x010b;
    L_0x01d3:
        com.ensighten.C1845i.m7344a(r0);
        goto L_0x010b;
    L_0x01d8:
        r0 = move-exception;
        r1 = com.ensighten.C1845i.m7362i();
        if (r1 == 0) goto L_0x0110;
    L_0x01df:
        com.ensighten.C1845i.m7344a(r0);
        goto L_0x0110;
    L_0x01e4:
        r0 = move-exception;
        r1 = com.ensighten.C1845i.m7362i();
        if (r1 == 0) goto L_0x0031;
    L_0x01eb:
        com.ensighten.C1845i.m7344a(r0);
        goto L_0x0031;
    L_0x01f0:
        r0 = move-exception;
        r1 = com.ensighten.C1845i.m7362i();
        if (r1 == 0) goto L_0x0036;
    L_0x01f7:
        com.ensighten.C1845i.m7344a(r0);
        goto L_0x0036;
    L_0x01fc:
        r0 = move-exception;
        r1 = com.ensighten.C1845i.m7362i();
        if (r1 == 0) goto L_0x003b;
    L_0x0203:
        goto L_0x005f;
    L_0x0205:
        r0 = move-exception;
        r3 = r1;
        goto L_0x0123;
    L_0x0209:
        r0 = move-exception;
        goto L_0x0123;
    L_0x020c:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0123;
    L_0x0210:
        r0 = move-exception;
        r4 = r3;
        r3 = r2;
        goto L_0x0123;
    L_0x0215:
        r0 = move-exception;
        r3 = r1;
        goto L_0x00fd;
    L_0x0219:
        r0 = move-exception;
        goto L_0x00fd;
    L_0x021c:
        r0 = move-exception;
        r1 = r2;
        goto L_0x00fd;
    L_0x0220:
        r0 = move-exception;
        r3 = r1;
        goto L_0x00d3;
    L_0x0224:
        r0 = move-exception;
        goto L_0x00d3;
    L_0x0227:
        r0 = move-exception;
        r1 = r2;
        goto L_0x00d3;
    L_0x022b:
        r0 = move-exception;
        r3 = r1;
        goto L_0x00ae;
    L_0x022f:
        r0 = move-exception;
        goto L_0x00ae;
    L_0x0232:
        r0 = move-exception;
        r1 = r2;
        goto L_0x00ae;
    L_0x0236:
        r0 = move-exception;
        r3 = r1;
        goto L_0x008a;
    L_0x023a:
        r0 = move-exception;
        goto L_0x008a;
    L_0x023d:
        r0 = move-exception;
        r1 = r2;
        goto L_0x008a;
    L_0x0241:
        r0 = move-exception;
        r3 = r1;
        goto L_0x0066;
    L_0x0245:
        r0 = move-exception;
        goto L_0x0066;
    L_0x0248:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0066;
    L_0x024c:
        r0 = move-exception;
        r2 = r1;
        r3 = r4;
        goto L_0x003f;
    L_0x0251:
        r0 = move-exception;
        r2 = r3;
        r3 = r4;
        goto L_0x003f;
    L_0x0256:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
        r3 = r4;
        goto L_0x003f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ensighten.C1712M.m7233c():void");
    }

    /* renamed from: b */
    public final void mo15047b() {
        this.f5678f = null;
        Ensighten.getContext().deleteFile("ensightenOptimizationConfig");
    }

    /* renamed from: a */
    public final boolean mo15046a(String str, String str2, String str3) {
        if (str == null || str2 == null || str3 == null) {
            if (C1845i.m7362i()) {
                C1845i.m7345a("The test was not added due to one or more arguments being null.");
            }
            return false;
        } else if (Ensighten.isPrivacyMode() || Ensighten.isBatteryKillEnabled()) {
            if (C1845i.m7362i()) {
                C1845i.m7345a("The test was not added due to privacy mode or battery kill enabled.");
            }
            return false;
        } else {
            try {
                JSONObject init = JSONObjectInstrumentation.init(str);
                if (this.f5678f == null) {
                    this.f5678f = new JSONObject().put(str2, new JSONObject().put(str3, new JSONObject().put("tests", new JSONObject())));
                } else if (!this.f5678f.has(str2)) {
                    this.f5678f.put(str2, new JSONObject().put(str3, new JSONObject().put("tests", new JSONObject())));
                }
                JSONObject jSONObject = this.f5678f.getJSONObject(str2);
                if (!jSONObject.has(str3)) {
                    jSONObject.put(str3, new JSONObject().put("tests", new JSONObject()));
                }
                JSONObject jSONObject2 = jSONObject.getJSONObject(str3);
                if (!jSONObject2.has("tests")) {
                    jSONObject2.put("tests", new JSONObject());
                }
                JSONObject jSONObject3 = jSONObject2.getJSONObject("tests");
                String string = init.getString("name");
                jSONObject3.put(string, init);
                jSONObject2.put("tests", jSONObject3);
                jSONObject.put(str3, jSONObject2);
                this.f5678f.put(str2, jSONObject);
                if (C1845i.m7362i()) {
                    C1845i.m7345a(String.format("Added test %s to the optimization configuration.", new Object[]{string}));
                }
                new StringBuilder("addTestToClass test ").append(string);
                mo15044a();
                return true;
            } catch (JSONException e) {
                if (C1845i.m7362i()) {
                    C1845i.m7344a(e);
                }
                return false;
            }
        }
    }
}
