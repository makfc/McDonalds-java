package com.crashlytics.android.core;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import p041io.fabric.sdk.android.services.common.CommonUtils;

class Sha1FileIdStrategy implements FileIdStrategy {
    Sha1FileIdStrategy() {
    }

    public String createId(File file) throws IOException {
        return getFileSHA(file.getPath());
    }

    private static String getFileSHA(String path) throws IOException {
        Throwable th;
        InputStream data = null;
        try {
            InputStream data2 = new BufferedInputStream(new FileInputStream(path));
            try {
                String sha = CommonUtils.sha1(data2);
                CommonUtils.closeQuietly(data2);
                return sha;
            } catch (Throwable th2) {
                th = th2;
                data = data2;
                CommonUtils.closeQuietly(data);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            CommonUtils.closeQuietly(data);
            throw th;
        }
    }
}
