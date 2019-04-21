package com.ensighten;

import android.support.p000v4.view.ViewCompat;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.ensighten.Q */
public final class C1720Q {
    /* renamed from: a */
    private static final AtomicInteger f5719a = new AtomicInteger(1);

    /* renamed from: a */
    public static int m7254a() {
        int i;
        int i2;
        do {
            i = f5719a.get();
            i2 = i + 1;
            if (i2 > ViewCompat.MEASURED_SIZE_MASK) {
                i2 = 1;
            }
        } while (!f5719a.compareAndSet(i, i2));
        return i;
    }
}
