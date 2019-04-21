package com.amap.api.services.core;

import com.facebook.stetho.common.Utf8Charset;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/* compiled from: Util */
/* renamed from: com.amap.api.services.core.bo */
public final class C1119bo {
    /* renamed from: a */
    public static final Charset f3770a = Charset.forName("US-ASCII");
    /* renamed from: b */
    static final Charset f3771b = Charset.forName(Utf8Charset.NAME);

    private C1119bo() {
    }

    /* renamed from: a */
    static void m4942a(File file) throws IOException {
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            throw new IOException("not a readable directory: " + file);
        }
        int length = listFiles.length;
        int i = 0;
        while (i < length) {
            File file2 = listFiles[i];
            if (file2.isDirectory()) {
                C1119bo.m4942a(file2);
            }
            if (file2.delete()) {
                i++;
            } else {
                throw new IOException("failed to delete file: " + file2);
            }
        }
    }

    /* renamed from: a */
    static void m4941a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
            }
        }
    }
}
