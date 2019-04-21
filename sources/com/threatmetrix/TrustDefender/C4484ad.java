package com.threatmetrix.TrustDefender;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* renamed from: com.threatmetrix.TrustDefender.ad */
class C4484ad implements C4483e {
    /* renamed from: i */
    private static final String f7357i = C4549w.m8585a(C4484ad.class);
    /* renamed from: a */
    volatile boolean f7358a = false;
    /* renamed from: b */
    volatile boolean f7359b = false;
    /* renamed from: c */
    volatile boolean f7360c = false;
    /* renamed from: d */
    volatile boolean f7361d = false;
    /* renamed from: e */
    volatile boolean f7362e = false;
    /* renamed from: f */
    CountDownLatch f7363f = null;
    /* renamed from: g */
    CountDownLatch f7364g = null;
    /* renamed from: h */
    private final ReentrantReadWriteLock f7365h = new ReentrantReadWriteLock();

    C4484ad() {
    }

    /* Access modifiers changed, original: final */
    /* renamed from: b */
    public final boolean mo34117b() {
        this.f7365h.readLock().lock();
        try {
            boolean z = this.f7358a;
            return z;
        } finally {
            this.f7365h.readLock().unlock();
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: c */
    public final boolean mo34119c() {
        this.f7365h.readLock().lock();
        try {
            boolean z = this.f7358a && this.f7363f.getCount() == 0;
            this.f7365h.readLock().unlock();
            return z;
        } catch (Throwable th) {
            this.f7365h.readLock().unlock();
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: d */
    public final boolean mo34120d() {
        this.f7365h.writeLock().lock();
        try {
            if (this.f7358a) {
                this.f7365h.writeLock().unlock();
                return false;
            }
            this.f7358a = true;
            this.f7363f = new CountDownLatch(1);
            return true;
        } finally {
            this.f7365h.writeLock().unlock();
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final void mo34115a(boolean wasSuccessful) {
        this.f7365h.readLock().lock();
        try {
            this.f7358a = wasSuccessful;
            CountDownLatch latch = this.f7363f;
            if (latch != null) {
                latch.countDown();
            }
        } finally {
            this.f7365h.readLock().unlock();
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: e */
    public final boolean mo34121e() {
        CountDownLatch latch = null;
        this.f7365h.readLock().lock();
        try {
            if (this.f7358a) {
                latch = this.f7363f;
            }
            this.f7365h.readLock().unlock();
            return latch != null && latch.getCount() > 0;
        } catch (Throwable th) {
            this.f7365h.readLock().unlock();
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final boolean mo34116a(int timeout_ms) {
        boolean waitForInit = false;
        this.f7365h.readLock().lock();
        try {
            if (!this.f7358a || this.f7363f == null) {
                C4549w.m8594c(f7357i, "init not in progress, nothing to wait for");
                return waitForInit;
            }
            CountDownLatch initLatch = this.f7363f;
            this.f7365h.readLock().unlock();
            C4549w.m8594c(f7357i, "Waiting for init to complete");
            boolean initCompleted = false;
            try {
                initCompleted = initLatch.await((long) timeout_ms, TimeUnit.MILLISECONDS);
                if (!initCompleted) {
                    C4549w.m8587a(f7357i, "Timed out waiting for init to complete");
                }
            } catch (InterruptedException e) {
                C4549w.m8588a(f7357i, "Waiting for init to complete interrupted", e);
            }
            this.f7365h.readLock().lock();
            try {
                if (this.f7358a && initCompleted) {
                    waitForInit = true;
                }
                this.f7365h.readLock().unlock();
                return waitForInit;
            } catch (Throwable th) {
                this.f7365h.readLock().unlock();
            }
        } finally {
            this.f7365h.readLock().unlock();
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: g */
    public final boolean mo34122g() {
        this.f7365h.writeLock().lock();
        try {
            if (this.f7359b) {
                this.f7365h.writeLock().unlock();
                return false;
            }
            this.f7359b = true;
            this.f7360c = false;
            return true;
        } finally {
            this.f7365h.writeLock().unlock();
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: h */
    public final void mo34123h() {
        this.f7365h.writeLock().lock();
        try {
            this.f7359b = false;
        } finally {
            this.f7365h.writeLock().unlock();
        }
    }

    /* renamed from: a */
    public final boolean mo34114a() {
        this.f7365h.readLock().lock();
        try {
            boolean z = this.f7360c;
            return z;
        } finally {
            this.f7365h.readLock().unlock();
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: k */
    public final boolean mo34124k() {
        C4549w.m8594c(f7357i, "Attempting to cancel doPackageScan");
        this.f7365h.writeLock().lock();
        try {
            if (this.f7361d) {
                this.f7365h.writeLock().unlock();
                return false;
            }
            this.f7361d = true;
            return true;
        } finally {
            this.f7365h.writeLock().unlock();
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: b */
    public final boolean mo34118b(boolean checkProfiling) {
        this.f7365h.writeLock().lock();
        try {
            if (this.f7362e || ((!checkProfiling || this.f7359b) && checkProfiling)) {
                this.f7365h.writeLock().unlock();
                return false;
            } else if (this.f7361d) {
                C4549w.m8594c(f7357i, "startScanning: aborted, marked as cancelled");
                this.f7361d = false;
                return false;
            } else {
                this.f7362e = true;
                this.f7364g = new CountDownLatch(1);
                this.f7365h.writeLock().unlock();
                return true;
            }
        } finally {
            this.f7365h.writeLock().unlock();
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: l */
    public final void mo34125l() {
        CountDownLatch latch = null;
        this.f7365h.readLock().lock();
        try {
            if (this.f7362e) {
                this.f7362e = false;
                this.f7361d = false;
                latch = this.f7364g;
            }
            this.f7365h.readLock().unlock();
            if (latch != null) {
                latch.countDown();
            }
        } catch (Throwable th) {
            this.f7365h.readLock().unlock();
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: m */
    public final boolean mo34126m() {
        this.f7365h.readLock().lock();
        try {
            if (!this.f7362e || this.f7364g == null) {
                C4549w.m8594c(f7357i, "waitForScan: No scan in progress, nothing to wait for");
                return true;
            }
            CountDownLatch initLatch = this.f7364g;
            this.f7365h.readLock().unlock();
            C4549w.m8594c(f7357i, "waitForScan: Waiting for scan to complete");
            try {
                initLatch.await();
                return true;
            } catch (InterruptedException e) {
                if (mo34114a()) {
                    C4549w.m8594c(f7357i, "waitForScan: interrupted by cancel");
                    return false;
                }
                C4549w.m8588a(f7357i, "waitForScan: Waiting for scan to complete interrupted", e);
                return false;
            }
        } finally {
            this.f7365h.readLock().unlock();
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: n */
    public final boolean mo34127n() {
        CountDownLatch latch = null;
        this.f7365h.readLock().lock();
        try {
            if (this.f7362e) {
                latch = this.f7364g;
            }
            this.f7365h.readLock().unlock();
            return latch != null && latch.getCount() > 0;
        } catch (Throwable th) {
            this.f7365h.readLock().unlock();
        }
    }
}
