package com.threatmetrix.TrustDefender;

import java.net.InetAddress;
import java.net.UnknownHostException;

/* renamed from: com.threatmetrix.TrustDefender.i */
class C4533i implements Runnable {
    /* renamed from: c */
    private static final String f7767c = C4549w.m8585a(C4533i.class);
    /* renamed from: a */
    private final String f7768a;
    /* renamed from: b */
    private InetAddress f7769b;

    public C4533i(String domain) {
        this.f7768a = domain;
    }

    public void run() {
        try {
            C4549w.m8594c(f7767c, "Starting DNS lookup");
            InetAddress addr = InetAddress.getByName(this.f7768a);
            C4549w.m8594c(f7767c, "DNS lookup complete");
            m8531a(addr);
        } catch (UnknownHostException e) {
            C4549w.m8594c(f7767c, "Failed DNS lookup");
        }
    }

    /* renamed from: a */
    private synchronized void m8531a(InetAddress inetAddr) {
        this.f7769b = inetAddr;
    }
}
