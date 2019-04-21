package com.tencent.wxop.stat.common;

import android.net.wifi.ScanResult;
import java.util.Comparator;

/* renamed from: com.tencent.wxop.stat.common.r */
final class C4440r implements Comparator<ScanResult> {
    C4440r() {
    }

    /* renamed from: a */
    public final int compare(ScanResult scanResult, ScanResult scanResult2) {
        int abs = Math.abs(scanResult.level);
        int abs2 = Math.abs(scanResult2.level);
        return abs > abs2 ? 1 : abs == abs2 ? 0 : -1;
    }
}
