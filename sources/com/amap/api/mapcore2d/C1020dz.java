package com.amap.api.mapcore2d;

import com.facebook.stetho.common.Utf8Charset;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/* compiled from: Util */
/* renamed from: com.amap.api.mapcore2d.dz */
public final class C1020dz {
    /* renamed from: a */
    public static final Charset f2893a = Charset.forName("US-ASCII");
    /* renamed from: b */
    static final Charset f2894b = Charset.forName(Utf8Charset.NAME);

    /* renamed from: a */
    static void m4259a(File file) throws IOException {
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            throw new IOException("not a readable directory: " + file);
        }
        int length = listFiles.length;
        int i = 0;
        while (i < length) {
            File file2 = listFiles[i];
            if (file2.isDirectory()) {
                C1020dz.m4259a(file2);
            }
            if (file2.delete()) {
                i++;
            } else {
                throw new IOException("failed to delete file: " + file2);
            }
        }
    }

    /* renamed from: a */
    static void m4258a(Closeable closeable) {
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
