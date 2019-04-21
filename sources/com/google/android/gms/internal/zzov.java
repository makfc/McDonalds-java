package com.google.android.gms.internal;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

public class zzov {
    private final Set<zzou<?>> zzaox = Collections.newSetFromMap(new WeakHashMap());

    public void release() {
        for (zzou clear : this.zzaox) {
            clear.clear();
        }
        this.zzaox.clear();
    }
}
