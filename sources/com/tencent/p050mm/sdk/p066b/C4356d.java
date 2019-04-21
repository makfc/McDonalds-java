package com.tencent.p050mm.sdk.p066b;

import com.tencent.p050mm.sdk.p066b.C4358e.C4355a;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.tencent.mm.sdk.b.d */
public final class C4356d implements C4355a {
    /* renamed from: aJ */
    private C4358e f6789aJ = new C4358e(this);
    /* renamed from: aK */
    private ConcurrentHashMap<Runnable, WeakReference<C4360g>> f6790aK = new ConcurrentHashMap();
    /* renamed from: aL */
    private int f6791aL;
    /* renamed from: aM */
    private LinkedList<WeakReference<C4360g>> f6792aM = new LinkedList();

    public C4356d() {
        if (this.f6789aJ.getLooper().getThread().getName().equals("initThread")) {
            C4353b.m7888a("MicroMsg.MMHandler", "MMHandler can not init handler with initThread looper, stack %s", C4361h.m7905u());
        }
    }

    /* renamed from: a */
    public final void mo33771a(Runnable runnable, C4360g c4360g) {
        this.f6790aK.put(runnable, new WeakReference(c4360g));
    }

    /* renamed from: b */
    public final void mo33772b(Runnable runnable, C4360g c4360g) {
        WeakReference weakReference = (WeakReference) this.f6790aK.get(runnable);
        if (weakReference != null && weakReference.get() != null && weakReference.get() == c4360g) {
            this.f6790aK.remove(runnable);
            if (this.f6791aL > 0) {
                if (this.f6792aM.size() == this.f6791aL) {
                    this.f6792aM.pop();
                }
                this.f6792aM.add(weakReference);
            }
        }
    }

    public final boolean post(Runnable runnable) {
        return this.f6789aJ.post(runnable);
    }

    public final String toString() {
        return "MMHandler(" + getClass().getName() + ")";
    }
}
