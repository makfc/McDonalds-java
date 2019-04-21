package com.threatmetrix.TrustDefender;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.facebook.internal.ServerProtocol;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* renamed from: com.threatmetrix.TrustDefender.d */
class C4512d extends C4508c {
    /* renamed from: i */
    private static final String f7570i = C4549w.m8585a(C4512d.class);
    /* renamed from: j */
    private Context f7571j = null;
    /* renamed from: k */
    private boolean f7572k = false;
    /* renamed from: l */
    private boolean f7573l = false;
    /* renamed from: m */
    private C2625u f7574m = null;
    /* renamed from: n */
    private C4547t f7575n = null;
    /* renamed from: o */
    private CountDownLatch f7576o = null;
    /* renamed from: p */
    private long f7577p = 0;

    /* renamed from: com.threatmetrix.TrustDefender.d$a */
    static class C4509a implements Runnable {
        /* renamed from: b */
        C4512d f7566b = null;
        /* renamed from: c */
        CountDownLatch f7567c = null;

        public C4509a(C4512d info, CountDownLatch latch) {
            this.f7566b = info;
            this.f7567c = latch;
        }

        public void run() {
            throw new NoSuchMethodError();
        }
    }

    C4512d() {
    }

    /* renamed from: e */
    static /* synthetic */ void m8466e(C4512d x0) throws InterruptedException {
        if (!Thread.currentThread().isInterrupted()) {
            String a;
            if ((x0.f7577p & 32) != 0) {
                a = x0.f7575n.mo34258a("(function () { var plugins_string='', i=0; for (p=navigator.plugins[0]; i< navigator.plugins.length;p=navigator.plugins[i++]) {  plugins_string += p.name + '<FIELD_SEP>' + p.description + '<FIELD_SEP>' + p.filename + '<FIELD_SEP>' + p.length.toString() + '<REC_SEP>'; } return plugins_string;})();");
                if (a != null) {
                    x0.m8461a(a);
                }
            }
            if (!Thread.currentThread().isInterrupted() && (x0.f7577p & 4) != 0) {
                a = x0.f7575n.mo34258a("navigator.mimeTypes.length");
                if (a != null) {
                    try {
                        x0.f7563f = Integer.parseInt(a);
                    } catch (NumberFormatException e) {
                        C4549w.m8588a(f7570i, "failed to convert", e);
                    }
                }
                x0.f7564g = x0.f7575n.mo34258a("(function () { var mime_string='', i=0; for (var m=navigator.mimeTypes[0]; i< navigator.mimeTypes.length;m=navigator.mimeTypes[i++]) {  mime_string += m.type; } return mime_string;})();");
                if (x0.f7564g != null) {
                    x0.f7565h = C4491ai.m8344b(x0.f7564g);
                    C4549w.m8594c(f7570i, "Got:" + x0.f7564g);
                    return;
                }
                x0.f7565h = "";
            }
        }
    }

    /* renamed from: a */
    public final String mo34215a() {
        if (this.f7560c == null) {
            if (this.f7575n == null || this.f7573l) {
                this.f7562e = C4547t.m8579c();
            } else {
                this.f7562e = this.f7575n.mo34257a(this.f7571j);
            }
        }
        return this.f7562e;
    }

    /* renamed from: a */
    private static String m8460a(String name, String description, ArrayList<HashMap<String, String>> pluginArray) {
        HashMap<String, String> p;
        String version = "false";
        Iterator it = pluginArray.iterator();
        while (it.hasNext()) {
            HashMap<String, String> hashMap = (HashMap) it.next();
            String str = (String) hashMap.get("name");
            if (str != null && str.toLowerCase(Locale.US).contains(name.toLowerCase(Locale.US))) {
                p = hashMap;
                break;
            }
        }
        p = null;
        if (p != null) {
            String n = (String) p.get("name");
            if (n != null) {
                version = n.replace("[abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXY -]", "");
                if (!version.isEmpty()) {
                    version = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE;
                }
            }
        }
        return description + "^" + version + "!";
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final boolean mo34218a(Context context, boolean needWebview, long options) {
        this.f7571j = context;
        this.f7572k = needWebview;
        this.f7577p = options;
        if (!this.f7572k) {
            return false;
        }
        C4549w.m8594c(f7570i, "initJSExecutor: jsProblem = " + this.f7573l + ", jsExec = " + this.f7575n + ", hasBadOptions = " + (this.f7575n != null ? Boolean.valueOf(this.f7575n.mo34259a(needWebview)) : ServerProtocol.DIALOG_RETURN_SCOPES_TRUE));
        if ((this.f7573l || this.f7575n != null) && (this.f7575n == null || !this.f7575n.mo34259a(this.f7572k))) {
            C4549w.m8594c(f7570i, "reused JS Interface");
        } else {
            boolean altJSInterface;
            CountDownLatch latch = new CountDownLatch(1);
            Handler handler = new Handler(Looper.getMainLooper());
            if (C4547t.m8578b() || C4547t.m8577a()) {
                altJSInterface = true;
            } else {
                altJSInterface = false;
            }
            this.f7574m = new C2625u(altJSInterface ? latch : null);
            C4549w.m8594c(f7570i, "Firing off initJSExecutor on UI thread using latch: " + latch.hashCode() + " with count: " + latch.getCount());
            handler.post(new C4509a(this, latch) {
                public final void run() {
                    C4549w.m8594c(C4512d.f7570i, "Calling initJSExecutor() - on UI thread");
                    this.f7566b.f7575n = new C4547t(C4512d.this.f7571j, C4512d.this.f7574m, C4512d.this.f7572k);
                    try {
                        this.f7566b.f7575n.mo34260d();
                    } catch (InterruptedException e) {
                        C4549w.m8587a(C4512d.f7570i, "Interrupted initing js engine");
                    }
                    C4549w.m8594c(C4512d.f7570i, "js exec init complete");
                    if (this.f7567c != null) {
                        C4549w.m8594c(C4512d.f7570i, "js exec init countdown using latch: " + this.f7567c.hashCode() + " with count: " + this.f7567c.getCount());
                        this.f7567c.countDown();
                    }
                }
            });
            try {
                if (!latch.await(10, TimeUnit.SECONDS)) {
                    this.f7573l = true;
                    C4549w.m8587a(f7570i, "initJSExecutor no response from UI thread before timeout using init latch: " + latch.hashCode() + " with count: " + latch.getCount());
                    return false;
                }
            } catch (InterruptedException e) {
                C4549w.m8587a(f7570i, "Interrupted initing js engine");
                return false;
            }
        }
        return true;
    }

    /* Access modifiers changed, original: final */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x012a A:{Catch:{ InterruptedException -> 0x0115 }} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ca A:{Catch:{ InterruptedException -> 0x0115 }} */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x012f A:{Catch:{ InterruptedException -> 0x0115 }} */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0101 A:{Catch:{ InterruptedException -> 0x0115 }} */
    /* renamed from: a */
    public final void mo34217a(boolean r11) {
        /*
        r10 = this;
        r8 = 0;
        r2 = 1;
        r1 = 0;
        r3 = r10.f7576o;
        if (r3 == 0) goto L_0x010e;
    L_0x0008:
        r3 = f7570i;	 Catch:{ InterruptedException -> 0x0115 }
        r4 = new java.lang.StringBuilder;	 Catch:{ InterruptedException -> 0x0115 }
        r5 = "waiting for getBrowserInfo to finished, latch: ";
        r4.<init>(r5);	 Catch:{ InterruptedException -> 0x0115 }
        r5 = r10.f7576o;	 Catch:{ InterruptedException -> 0x0115 }
        r6 = r5.getCount();	 Catch:{ InterruptedException -> 0x0115 }
        r4 = r4.append(r6);	 Catch:{ InterruptedException -> 0x0115 }
        r5 = " - ";
        r4 = r4.append(r5);	 Catch:{ InterruptedException -> 0x0115 }
        r5 = r10.f7576o;	 Catch:{ InterruptedException -> 0x0115 }
        r5 = r5.hashCode();	 Catch:{ InterruptedException -> 0x0115 }
        r4 = r4.append(r5);	 Catch:{ InterruptedException -> 0x0115 }
        r4 = r4.toString();	 Catch:{ InterruptedException -> 0x0115 }
        com.threatmetrix.TrustDefender.C4549w.m8594c(r3, r4);	 Catch:{ InterruptedException -> 0x0115 }
        r3 = r10.f7576o;	 Catch:{ InterruptedException -> 0x0115 }
        r4 = 10;
        r6 = java.util.concurrent.TimeUnit.SECONDS;	 Catch:{ InterruptedException -> 0x0115 }
        r3 = r3.await(r4, r6);	 Catch:{ InterruptedException -> 0x0115 }
        if (r3 == 0) goto L_0x0132;
    L_0x003e:
        if (r11 == 0) goto L_0x010e;
    L_0x0040:
        r3 = com.threatmetrix.TrustDefender.C4547t.m8578b();	 Catch:{ InterruptedException -> 0x0115 }
        if (r3 != 0) goto L_0x004c;
    L_0x0046:
        r3 = com.threatmetrix.TrustDefender.C4547t.m8577a();	 Catch:{ InterruptedException -> 0x0115 }
        if (r3 == 0) goto L_0x010e;
    L_0x004c:
        r4 = r10.f7577p;	 Catch:{ InterruptedException -> 0x0115 }
        r6 = 32;
        r4 = r4 & r6;
        r3 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1));
        if (r3 == 0) goto L_0x0160;
    L_0x0055:
        r3 = r10.f7574m;	 Catch:{ InterruptedException -> 0x0115 }
        r3 = r3.f6079c;	 Catch:{ InterruptedException -> 0x0115 }
        r3 = r3.size();	 Catch:{ InterruptedException -> 0x0115 }
        if (r3 <= 0) goto L_0x0160;
    L_0x005f:
        r1 = r10.f7574m;	 Catch:{ InterruptedException -> 0x0115 }
        r1 = r1.f6079c;	 Catch:{ InterruptedException -> 0x0115 }
        r3 = 0;
        r1 = r1.get(r3);	 Catch:{ InterruptedException -> 0x0115 }
        r1 = (java.lang.String) r1;	 Catch:{ InterruptedException -> 0x0115 }
        if (r1 == 0) goto L_0x010f;
    L_0x006c:
        r3 = r1.isEmpty();	 Catch:{ InterruptedException -> 0x0115 }
        if (r3 != 0) goto L_0x010f;
    L_0x0072:
        r10.m8461a(r1);	 Catch:{ InterruptedException -> 0x0115 }
    L_0x0075:
        r1 = java.lang.Thread.currentThread();	 Catch:{ InterruptedException -> 0x0115 }
        r1 = r1.isInterrupted();	 Catch:{ InterruptedException -> 0x0115 }
        if (r1 != 0) goto L_0x010e;
    L_0x007f:
        r4 = r10.f7577p;	 Catch:{ InterruptedException -> 0x0115 }
        r6 = 4;
        r4 = r4 & r6;
        r1 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1));
        if (r1 == 0) goto L_0x010e;
    L_0x0088:
        r1 = r10.f7574m;	 Catch:{ InterruptedException -> 0x0115 }
        r1 = r1.f6079c;	 Catch:{ InterruptedException -> 0x0115 }
        r1 = r1.size();	 Catch:{ InterruptedException -> 0x0115 }
        if (r1 <= r2) goto L_0x010e;
    L_0x0092:
        r1 = r10.f7574m;	 Catch:{ InterruptedException -> 0x0115 }
        r1 = r1.f6079c;	 Catch:{ InterruptedException -> 0x0115 }
        r1 = r1.get(r2);	 Catch:{ InterruptedException -> 0x0115 }
        r1 = (java.lang.String) r1;	 Catch:{ InterruptedException -> 0x0115 }
        r2 = r2 + 1;
        if (r1 == 0) goto L_0x0126;
    L_0x00a0:
        r3 = r1.isEmpty();	 Catch:{ InterruptedException -> 0x0115 }
        if (r3 != 0) goto L_0x0126;
    L_0x00a6:
        r1 = java.lang.Integer.parseInt(r1);	 Catch:{ NumberFormatException -> 0x011e }
        r10.f7563f = r1;	 Catch:{ NumberFormatException -> 0x011e }
    L_0x00ac:
        r1 = r10.f7563f;	 Catch:{ InterruptedException -> 0x0115 }
        if (r1 <= 0) goto L_0x00c6;
    L_0x00b0:
        r1 = r10.f7574m;	 Catch:{ InterruptedException -> 0x0115 }
        r1 = r1.f6079c;	 Catch:{ InterruptedException -> 0x0115 }
        r1 = r1.size();	 Catch:{ InterruptedException -> 0x0115 }
        if (r1 <= r2) goto L_0x00c6;
    L_0x00ba:
        r1 = r10.f7574m;	 Catch:{ InterruptedException -> 0x0115 }
        r1 = r1.f6079c;	 Catch:{ InterruptedException -> 0x0115 }
        r1 = r1.get(r2);	 Catch:{ InterruptedException -> 0x0115 }
        r1 = (java.lang.String) r1;	 Catch:{ InterruptedException -> 0x0115 }
        r10.f7564g = r1;	 Catch:{ InterruptedException -> 0x0115 }
    L_0x00c6:
        r1 = r10.f7564g;	 Catch:{ InterruptedException -> 0x0115 }
        if (r1 == 0) goto L_0x012a;
    L_0x00ca:
        r1 = r10.f7564g;	 Catch:{ InterruptedException -> 0x0115 }
        r1 = com.threatmetrix.TrustDefender.C4491ai.m8344b(r1);	 Catch:{ InterruptedException -> 0x0115 }
        r10.f7565h = r1;	 Catch:{ InterruptedException -> 0x0115 }
        r1 = f7570i;	 Catch:{ InterruptedException -> 0x0115 }
        r2 = new java.lang.StringBuilder;	 Catch:{ InterruptedException -> 0x0115 }
        r3 = "Got:";
        r2.<init>(r3);	 Catch:{ InterruptedException -> 0x0115 }
        r3 = r10.f7564g;	 Catch:{ InterruptedException -> 0x0115 }
        r2 = r2.append(r3);	 Catch:{ InterruptedException -> 0x0115 }
        r2 = r2.toString();	 Catch:{ InterruptedException -> 0x0115 }
        com.threatmetrix.TrustDefender.C4549w.m8594c(r1, r2);	 Catch:{ InterruptedException -> 0x0115 }
    L_0x00e8:
        r2 = f7570i;	 Catch:{ InterruptedException -> 0x0115 }
        r1 = new java.lang.StringBuilder;	 Catch:{ InterruptedException -> 0x0115 }
        r3 = "Got mime ";
        r1.<init>(r3);	 Catch:{ InterruptedException -> 0x0115 }
        r3 = r10.f7563f;	 Catch:{ InterruptedException -> 0x0115 }
        r1 = r1.append(r3);	 Catch:{ InterruptedException -> 0x0115 }
        r3 = ":";
        r3 = r1.append(r3);	 Catch:{ InterruptedException -> 0x0115 }
        r1 = r10.f7564g;	 Catch:{ InterruptedException -> 0x0115 }
        if (r1 == 0) goto L_0x012f;
    L_0x0101:
        r1 = r10.f7564g;	 Catch:{ InterruptedException -> 0x0115 }
    L_0x0103:
        r1 = r3.append(r1);	 Catch:{ InterruptedException -> 0x0115 }
        r1 = r1.toString();	 Catch:{ InterruptedException -> 0x0115 }
        com.threatmetrix.TrustDefender.C4549w.m8594c(r2, r1);	 Catch:{ InterruptedException -> 0x0115 }
    L_0x010e:
        return;
    L_0x010f:
        r1 = "";
        r10.f7561d = r1;	 Catch:{ InterruptedException -> 0x0115 }
        goto L_0x0075;
    L_0x0115:
        r0 = move-exception;
        r1 = f7570i;
        r2 = "getBrowserInfo interrupted";
        com.threatmetrix.TrustDefender.C4549w.m8588a(r1, r2, r0);
        goto L_0x010e;
    L_0x011e:
        r1 = move-exception;
        r3 = f7570i;	 Catch:{ InterruptedException -> 0x0115 }
        r4 = "failed to convert";
        com.threatmetrix.TrustDefender.C4549w.m8588a(r3, r4, r1);	 Catch:{ InterruptedException -> 0x0115 }
    L_0x0126:
        r1 = 0;
        r10.f7563f = r1;	 Catch:{ InterruptedException -> 0x0115 }
        goto L_0x00ac;
    L_0x012a:
        r1 = "";
        r10.f7565h = r1;	 Catch:{ InterruptedException -> 0x0115 }
        goto L_0x00e8;
    L_0x012f:
        r1 = "";
        goto L_0x0103;
    L_0x0132:
        r1 = f7570i;	 Catch:{ InterruptedException -> 0x0115 }
        r2 = new java.lang.StringBuilder;	 Catch:{ InterruptedException -> 0x0115 }
        r3 = "getBrowserInfo no response from UI thread before timeout using latch: ";
        r2.<init>(r3);	 Catch:{ InterruptedException -> 0x0115 }
        r3 = r10.f7576o;	 Catch:{ InterruptedException -> 0x0115 }
        r3 = r3.hashCode();	 Catch:{ InterruptedException -> 0x0115 }
        r2 = r2.append(r3);	 Catch:{ InterruptedException -> 0x0115 }
        r3 = " with count: ";
        r2 = r2.append(r3);	 Catch:{ InterruptedException -> 0x0115 }
        r3 = r10.f7576o;	 Catch:{ InterruptedException -> 0x0115 }
        r4 = r3.getCount();	 Catch:{ InterruptedException -> 0x0115 }
        r2 = r2.append(r4);	 Catch:{ InterruptedException -> 0x0115 }
        r2 = r2.toString();	 Catch:{ InterruptedException -> 0x0115 }
        com.threatmetrix.TrustDefender.C4549w.m8587a(r1, r2);	 Catch:{ InterruptedException -> 0x0115 }
        r1 = 1;
        r10.f7573l = r1;	 Catch:{ InterruptedException -> 0x0115 }
        goto L_0x010e;
    L_0x0160:
        r2 = r1;
        goto L_0x0075;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.C4512d.mo34217a(boolean):void");
    }

    /* Access modifiers changed, original: final */
    /* renamed from: b */
    public final boolean mo34219b() {
        boolean z = (this.f7575n == null || this.f7573l) ? false : true;
        return z && this.f7572k;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: c */
    public final void mo34220c() {
        boolean altJSInterface = C4547t.m8578b() || C4547t.m8577a();
        int latchCount = 1;
        if (altJSInterface) {
            if ((this.f7577p & 32) != 0) {
                latchCount = 1 + 1;
            }
            if ((this.f7577p & 4) != 0) {
                latchCount += 2;
            }
        }
        this.f7576o = new CountDownLatch(latchCount);
        Handler handler = new Handler(Looper.getMainLooper());
        C4549w.m8594c(f7570i, "Firing off getBrowserInfo on UI thread using latch: " + this.f7576o.hashCode() + " with count: " + latchCount);
        this.f7574m.mo23199a(altJSInterface ? this.f7576o : null);
        handler.post(new C4509a(this, this.f7576o) {
            public final void run() {
                try {
                    C4549w.m8594c(C4512d.f7570i, "Calling getBrowserInfo() - on UI thread");
                    C4512d.m8466e(this.f7566b);
                } catch (InterruptedException e) {
                    C4549w.m8595c(C4512d.f7570i, "getBrowserInfo interrupted", e);
                }
                if (this.f7567c != null) {
                    C4549w.m8594c(C4512d.f7570i, "getBrowserInfo countdown using latch: " + this.f7567c.hashCode() + " with count: " + this.f7567c.getCount());
                    this.f7567c.countDown();
                }
            }
        });
    }

    /* renamed from: a */
    private void m8461a(String jsPluginList) throws InterruptedException {
        this.f7560c = jsPluginList.replaceAll("(<FIELD_SEP>|<REC_SEP>)", "");
        this.f7561d = C4491ai.m8344b(this.f7560c);
        ArrayList pluginArray = new ArrayList();
        String[] arr$ = jsPluginList.split("<REC_SEP>");
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            String pluginString = arr$[i$];
            if (!Thread.currentThread().isInterrupted()) {
                HashMap<String, String> p = new HashMap();
                String[] arr = pluginString.split("<FIELD_SEP>");
                if (arr.length == 4) {
                    p.put("name", arr[0]);
                    p.put("description", arr[1]);
                    p.put("filename", arr[2]);
                    p.put("length", arr[3]);
                    pluginArray.add(p);
                }
                i$++;
            } else {
                return;
            }
        }
        this.f7559b = Integer.toString(pluginArray.size());
        StringBuilder plugins = new StringBuilder();
        plugins.append(C4512d.m8460a("QuickTime Plug-in", "plugin_quicktime", pluginArray));
        plugins.append(C4512d.m8460a("Adobe Acrobat", "plugin_adobe_acrobat", pluginArray));
        plugins.append(C4512d.m8460a("Java", "plugin_java", pluginArray));
        plugins.append(C4512d.m8460a("SVG Viewer", "plugin_svg_viewer", pluginArray));
        plugins.append(C4512d.m8460a("Flash", "plugin_flash", pluginArray));
        plugins.append(C4512d.m8460a("Windows Media Player", "plugin_windows_media_player", pluginArray));
        plugins.append(C4512d.m8460a("Silverlight", "plugin_silverlight", pluginArray));
        plugins.append(C4512d.m8460a("Real Player", "plugin_realplayer", pluginArray));
        plugins.append(C4512d.m8460a("ShockWave Director", "plugin_shockwave", pluginArray));
        plugins.append(C4512d.m8460a("VLC", "plugin_vlc_player", pluginArray));
        plugins.append(C4512d.m8460a("DevalVR", "plugin_devalvr", pluginArray));
        this.f7558a = plugins.toString();
        C4549w.m8594c(f7570i, "Got" + this.f7559b + ":" + (this.f7558a != null ? this.f7558a : ""));
    }
}
