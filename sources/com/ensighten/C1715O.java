package com.ensighten;

import android.os.StatFs;
import java.io.File;

/* renamed from: com.ensighten.O */
public final class C1715O {
    /* renamed from: a */
    public static long m7250a(File file) {
        StatFs statFs = new StatFs(file.getPath());
        return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
    }
}
