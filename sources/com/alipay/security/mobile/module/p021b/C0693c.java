package com.alipay.security.mobile.module.p021b;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

/* renamed from: com.alipay.security.mobile.module.b.c */
final class C0693c implements FileFilter {
    /* renamed from: a */
    final /* synthetic */ C0692b f726a;

    C0693c(C0692b c0692b) {
        this.f726a = c0692b;
    }

    public final boolean accept(File file) {
        return Pattern.matches("cpu[0-9]+", file.getName());
    }
}
