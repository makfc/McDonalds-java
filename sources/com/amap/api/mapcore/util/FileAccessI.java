package com.amap.api.mapcore.util;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/* renamed from: com.amap.api.mapcore.util.by */
class FileAccessI {
    /* renamed from: a */
    RandomAccessFile f1452a;

    public FileAccessI() throws IOException {
        this("", 0);
    }

    public FileAccessI(String str, long j) throws IOException {
        File file = new File(str);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
            } catch (IOException e) {
                SDKLogHandler.m2563a(e, "FileAccessI", "create");
                e.printStackTrace();
            }
        }
        this.f1452a = new RandomAccessFile(str, "rw");
        this.f1452a.seek(j);
    }

    /* renamed from: a */
    public synchronized int mo8986a(byte[] bArr) throws IOException {
        this.f1452a.write(bArr);
        return bArr.length;
    }

    /* renamed from: a */
    public void mo8987a() {
        if (this.f1452a != null) {
            try {
                this.f1452a.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.f1452a = null;
        }
    }
}
