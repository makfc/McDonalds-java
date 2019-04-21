package com.ensighten;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/* renamed from: com.ensighten.B */
public final class C1686B extends WebViewClient {
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0097 A:{Catch:{ Exception -> 0x011d }} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00b3 A:{Catch:{ Exception -> 0x011d }} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ec A:{SYNTHETIC, Splitter:B:39:0x00ec} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:50:0x0112, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:52:0x0117, code skipped:
            if (com.ensighten.C1845i.m7357d() != false) goto L_0x0119;
     */
    /* JADX WARNING: Missing block: B:53:0x0119, code skipped:
            com.ensighten.C1845i.m7353c(r0);
     */
    public final void onLoadResource(android.webkit.WebView r7, java.lang.String r8) {
        /*
        r6 = this;
        r3 = 1;
        r0 = com.ensighten.C1845i.m7365l();	 Catch:{ Exception -> 0x0128 }
        if (r0 == 0) goto L_0x0016;
    L_0x0007:
        r0 = "Loaded resource with url %s.";
        r1 = 1;
        r1 = new java.lang.Object[r1];	 Catch:{ Exception -> 0x0128 }
        r2 = 0;
        r1[r2] = r8;	 Catch:{ Exception -> 0x0128 }
        r0 = java.lang.String.format(r0, r1);	 Catch:{ Exception -> 0x0128 }
        com.ensighten.C1845i.m7345a(r0);	 Catch:{ Exception -> 0x0128 }
    L_0x0016:
        r0 = "ensighten://";
        r0 = r8.startsWith(r0);	 Catch:{ Exception -> 0x0128 }
        if (r0 == 0) goto L_0x0104;
    L_0x001e:
        r0 = "ensighten://";
        r1 = "";
        r0 = r8.replace(r0, r1);	 Catch:{ Exception -> 0x0128 }
        r1 = "#";
        r0 = r0.split(r1);	 Catch:{ Exception -> 0x0128 }
        r1 = 0;
        r1 = r0[r1];	 Catch:{ Exception -> 0x0128 }
        r2 = r0.length;	 Catch:{ Exception -> 0x0128 }
        if (r2 <= r3) goto L_0x0105;
    L_0x0032:
        r2 = 1;
        r0 = r0[r2];	 Catch:{ Exception -> 0x0128 }
    L_0x0035:
        r2 = com.ensighten.Ensighten.getJavascriptProcessor();	 Catch:{ Exception -> 0x0128 }
        r0 = "javascriptInit";
        r0 = r0.equalsIgnoreCase(r1);	 Catch:{ Exception -> 0x011d }
        if (r0 == 0) goto L_0x013e;
    L_0x0041:
        r0 = com.ensighten.C1845i.m7360g();	 Catch:{ Exception -> 0x011d }
        if (r0 == 0) goto L_0x004c;
    L_0x0047:
        r0 = "The javascript ready event has been received.";
        com.ensighten.C1845i.m7345a(r0);	 Catch:{ Exception -> 0x011d }
    L_0x004c:
        r0 = r2.f5897g;	 Catch:{ Exception -> 0x011d }
        if (r0 == 0) goto L_0x00f4;
    L_0x0050:
        r0 = com.ensighten.C1845i.m7360g();	 Catch:{ Exception -> 0x011d }
        if (r0 == 0) goto L_0x005b;
    L_0x0056:
        r0 = "First launch detected. Evaluating initial rules and evaluating lifecycle events.";
        com.ensighten.C1845i.m7345a(r0);	 Catch:{ Exception -> 0x011d }
    L_0x005b:
        r0 = com.ensighten.Ensighten.getEventManager();	 Catch:{ Exception -> 0x011d }
        r1 = com.ensighten.Ensighten.getConfigurationManager();	 Catch:{ Exception -> 0x011d }
        r1 = r1.f5875a;	 Catch:{ Exception -> 0x011d }
        r1 = r1.f5847e;	 Catch:{ Exception -> 0x011d }
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r0.mo15500a(r1, r3, r4, r5);	 Catch:{ Exception -> 0x011d }
        r0 = "launch";
        com.ensighten.Ensighten.getInstance();	 Catch:{ Exception -> 0x011d }
        r1 = com.ensighten.Ensighten.getStorageManager();	 Catch:{ Exception -> 0x011d }
        r3 = com.ensighten.C1843h.C18403.f5888a;	 Catch:{ Exception -> 0x011d }
        r1 = r1.f5696d;	 Catch:{ Exception -> 0x011d }
        r1 = r1.f6016e;	 Catch:{ Exception -> 0x011d }
        r1 = r1.ordinal();	 Catch:{ Exception -> 0x011d }
        r1 = r3[r1];	 Catch:{ Exception -> 0x011d }
        switch(r1) {
            case 1: goto L_0x0109;
            case 2: goto L_0x010e;
            default: goto L_0x0085;
        };	 Catch:{ Exception -> 0x011d }
    L_0x0085:
        r1 = "Bootstrapper.onMobileLaunch(\\\"%s\\\", params);";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ Exception -> 0x011d }
        r4 = 0;
        r3[r4] = r0;	 Catch:{ Exception -> 0x011d }
        r0 = java.lang.String.format(r1, r3);	 Catch:{ Exception -> 0x011d }
        r1 = com.ensighten.C1845i.m7357d();	 Catch:{ Exception -> 0x011d }
        if (r1 == 0) goto L_0x00a6;
    L_0x0097:
        r1 = "Processing lifecycle javascript %s.";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ Exception -> 0x011d }
        r4 = 0;
        r3[r4] = r0;	 Catch:{ Exception -> 0x011d }
        r1 = java.lang.String.format(r1, r3);	 Catch:{ Exception -> 0x011d }
        com.ensighten.C1845i.m7350b(r1);	 Catch:{ Exception -> 0x011d }
    L_0x00a6:
        r2.mo15488c(r0);	 Catch:{ Exception -> 0x011d }
        r0 = com.ensighten.Ensighten.getConfigurationManager();	 Catch:{ Exception -> 0x011d }
        r0 = r0.f5875a;	 Catch:{ Exception -> 0x011d }
        r0 = r0.f5861s;	 Catch:{ Exception -> 0x011d }
        if (r0 == 0) goto L_0x00ba;
    L_0x00b3:
        r0 = android.webkit.CookieSyncManager.getInstance();	 Catch:{ Exception -> 0x011d }
        r0.sync();	 Catch:{ Exception -> 0x011d }
    L_0x00ba:
        com.ensighten.Ensighten.getInstance();	 Catch:{ Exception -> 0x0112 }
        r0 = com.ensighten.Ensighten.getStorageManager();	 Catch:{ Exception -> 0x0112 }
        r1 = "ensightenError";
        r1 = r0.mo15053b(r1);	 Catch:{ Exception -> 0x0112 }
        if (r1 == 0) goto L_0x00e1;
    L_0x00c9:
        r3 = "";
        r3 = r1.equals(r3);	 Catch:{ Exception -> 0x0112 }
        if (r3 != 0) goto L_0x00e1;
    L_0x00d1:
        r3 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0112 }
        r1 = com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation.init(r1);	 Catch:{ Exception -> 0x0112 }
        r2.mo15485a(r1);	 Catch:{ Exception -> 0x0112 }
        r1 = "ensightenError";
        r3 = "";
        r0.mo15052a(r1, r3);	 Catch:{ Exception -> 0x0112 }
    L_0x00e1:
        com.ensighten.Ensighten.getInstance();	 Catch:{ Exception -> 0x011d }
        r0 = com.ensighten.Ensighten.getOptimizationManager();	 Catch:{ Exception -> 0x011d }
        r0 = r0.f5681i;	 Catch:{ Exception -> 0x011d }
        if (r0 == 0) goto L_0x00f1;
    L_0x00ec:
        r0 = "Bootstrapper.onOptimizationStart();";
        r2.mo15488c(r0);	 Catch:{ Exception -> 0x0133 }
    L_0x00f1:
        r0 = 0;
        r2.f5897g = r0;	 Catch:{ Exception -> 0x011d }
    L_0x00f4:
        r0 = com.ensighten.Ensighten.getWebManager();	 Catch:{ Exception -> 0x011d }
        r1 = 1;
        r0.f5822l = r1;	 Catch:{ Exception -> 0x011d }
        r2.mo15486b();	 Catch:{ Exception -> 0x011d }
        r0 = 1;
        r2.f5896f = r0;	 Catch:{ Exception -> 0x011d }
        r2.mo15483a();	 Catch:{ Exception -> 0x011d }
    L_0x0104:
        return;
    L_0x0105:
        r0 = "";
        goto L_0x0035;
    L_0x0109:
        r0 = "upgrade";
        goto L_0x0085;
    L_0x010e:
        r0 = "install";
        goto L_0x0085;
    L_0x0112:
        r0 = move-exception;
        r1 = com.ensighten.C1845i.m7357d();	 Catch:{ Exception -> 0x011d }
        if (r1 == 0) goto L_0x00e1;
    L_0x0119:
        com.ensighten.C1845i.m7353c(r0);	 Catch:{ Exception -> 0x011d }
        goto L_0x00e1;
    L_0x011d:
        r0 = move-exception;
        r1 = com.ensighten.C1845i.m7360g();	 Catch:{ Exception -> 0x0128 }
        if (r1 == 0) goto L_0x0104;
    L_0x0124:
        com.ensighten.C1845i.m7353c(r0);	 Catch:{ Exception -> 0x0128 }
        goto L_0x0104;
    L_0x0128:
        r0 = move-exception;
        r1 = com.ensighten.C1845i.m7365l();
        if (r1 == 0) goto L_0x0104;
    L_0x012f:
        com.ensighten.C1845i.m7353c(r0);
        goto L_0x0104;
    L_0x0133:
        r0 = move-exception;
        r1 = com.ensighten.C1845i.m7357d();	 Catch:{ Exception -> 0x011d }
        if (r1 == 0) goto L_0x00f1;
    L_0x013a:
        com.ensighten.C1845i.m7353c(r0);	 Catch:{ Exception -> 0x011d }
        goto L_0x00f1;
    L_0x013e:
        r0 = r2.f5893c;	 Catch:{ Exception -> 0x011d }
        r0 = r0.containsKey(r1);	 Catch:{ Exception -> 0x011d }
        if (r0 == 0) goto L_0x0104;
    L_0x0146:
        r0 = r2.f5893c;	 Catch:{ Exception -> 0x011d }
        r0.remove(r1);	 Catch:{ Exception -> 0x011d }
        goto L_0x0104;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ensighten.C1686B.onLoadResource(android.webkit.WebView, java.lang.String):void");
    }

    public final void onPageFinished(WebView view, String url) {
        try {
            if (C1845i.m7365l()) {
                C1845i.m7345a(String.format("Finished loading page with url %s.", new Object[]{url}));
            }
            if ("ensighten".equals(view.getTitle()) && url.equals(Ensighten.getTagContainerUrl())) {
                Ensighten.setWaitingForWebview(false);
            }
        } catch (Exception e) {
            if (C1845i.m7365l()) {
                C1845i.m7353c(e);
            }
        }
    }

    public final void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        try {
            if (C1845i.m7365l()) {
                C1845i.m7345a(String.format("Error loading page with url %s. Encountered error code %d because of %s.", new Object[]{failingUrl, Integer.valueOf(errorCode), description}));
            }
            if (failingUrl.equals(Ensighten.getTagContainerUrl())) {
                Ensighten.setWaitingForWebview(false);
            }
        } catch (Exception e) {
            if (C1845i.m7365l()) {
                C1845i.m7353c(e);
            }
        }
    }
}
