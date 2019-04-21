package com.threatmetrix.TrustDefender;

import android.location.Location;
import java.util.List;

public class ProfilingOptions {
    /* renamed from: a */
    private String f7260a;
    /* renamed from: b */
    private List<String> f7261b;
    /* renamed from: c */
    private Location f7262c;
    /* renamed from: d */
    private EndNotifierBase f7263d = null;

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final String mo34068a() {
        return this.f7260a;
    }

    public ProfilingOptions setSessionID(String sessionID) {
        this.f7260a = sessionID;
        return this;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: b */
    public final List<String> mo34069b() {
        return this.f7261b;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: c */
    public final Location mo34070c() {
        return this.f7262c;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: d */
    public final EndNotifierBase mo34071d() {
        return this.f7263d;
    }

    public ProfilingOptions setEndNotifier(EndNotifierBase notifier) {
        this.f7263d = notifier;
        return this;
    }
}
