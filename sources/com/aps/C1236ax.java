package com.aps;

/* renamed from: com.aps.ax */
public final class C1236ax extends Thread {
    /* renamed from: a */
    private /* synthetic */ C1213aa f4292a;

    public final void run() {
        while (this.f4292a.f4170k) {
            try {
                C1213aa.m5282a(this.f4292a, this.f4292a.f4158D, 1, System.currentTimeMillis());
                try {
                    Thread.sleep((long) this.f4292a.f4175p);
                } catch (Exception e) {
                }
            } catch (Exception e2) {
                return;
            }
        }
    }
}
