package com.bumptech.glide.disklrucache;

import com.facebook.stetho.common.Utf8Charset;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

final class Util {
    static final Charset US_ASCII = Charset.forName("US-ASCII");
    static final Charset UTF_8 = Charset.forName(Utf8Charset.NAME);

    private Util() {
    }

    static void deleteContents(File dir) throws IOException {
        File[] files = dir.listFiles();
        if (files == null) {
            throw new IOException("not a readable directory: " + dir);
        }
        File[] arr$ = files;
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            File file = arr$[i$];
            if (file.isDirectory()) {
                deleteContents(file);
            }
            if (file.delete()) {
                i$++;
            } else {
                throw new IOException("failed to delete file: " + file);
            }
        }
    }

    static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException rethrown) {
                throw rethrown;
            } catch (Exception e) {
            }
        }
    }
}
