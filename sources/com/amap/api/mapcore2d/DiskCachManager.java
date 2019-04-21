package com.amap.api.mapcore2d;

import android.content.Context;
import android.os.Environment;
import android.support.p000v4.view.MotionEventCompat;
import android.support.p000v4.view.ViewCompat;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/* renamed from: com.amap.api.mapcore2d.s */
class DiskCachManager {
    /* renamed from: a */
    private Context f3050a;
    /* renamed from: b */
    private MemoryTileManager f3051b = null;
    /* renamed from: c */
    private String f3052c = "/sdcard/Amap/RMap";
    /* renamed from: d */
    private final int f3053d = 128;

    public DiskCachManager(Context context, boolean z, C0886am c0886am) {
        this.f3050a = context;
        if (c0886am != null) {
            if (z) {
                this.f3052c = context.getFilesDir().getPath();
                return;
            }
            boolean z2 = false;
            if (!(c0886am.f2249m == null || c0886am.f2249m.equals(""))) {
                File file = new File(c0886am.f2249m);
                z2 = file.exists();
                if (!z2) {
                    z2 = file.mkdirs();
                }
                this.f3052c = c0886am.f2249m;
            }
            if (!z2) {
                this.f3052c = DiskCachManager.m4376a(this.f3050a, this.f3052c, c0886am);
            }
        }
    }

    /* renamed from: a */
    public static String m4376a(Context context, String str, C0886am c0886am) {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return context.getFilesDir().getPath();
        }
        File file = new File(C0955ck.m3896b(context), c0886am.f2238b);
        if (!file.exists()) {
            file.mkdir();
        }
        return file.toString() + "/";
    }

    /* renamed from: a */
    private String[] m4379a(int i, int i2, int i3, boolean z) {
        int i4 = i / 128;
        int i5 = i2 / 128;
        int i6 = i4 / 10;
        int i7 = i5 / 10;
        String[] strArr = new String[2];
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.f3052c);
        stringBuilder.append("/");
        stringBuilder.append(i3);
        stringBuilder.append("/");
        stringBuilder.append(i6);
        stringBuilder.append("/");
        stringBuilder.append(i7);
        stringBuilder.append("/");
        if (!z) {
            File file = new File(stringBuilder.toString());
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        stringBuilder.append(i3);
        stringBuilder.append("_");
        stringBuilder.append(i4);
        stringBuilder.append("_");
        stringBuilder.append(i5);
        strArr[0] = stringBuilder.toString() + ".idx";
        strArr[1] = stringBuilder.toString() + ".dat";
        return strArr;
    }

    /* renamed from: a */
    public void mo10323a(MemoryTileManager memoryTileManager) {
        this.f3051b = memoryTileManager;
    }

    /* renamed from: a */
    private byte[] m4378a(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & i) >> 8), (byte) ((16711680 & i) >> 16), (byte) ((ViewCompat.MEASURED_STATE_MASK & i) >> 24)};
    }

    /* renamed from: a */
    private void m4377a(byte[] bArr) {
        if (bArr != null && bArr.length == 4) {
            byte b = bArr[0];
            bArr[0] = bArr[3];
            bArr[3] = b;
            b = bArr[1];
            bArr[1] = bArr[2];
            bArr[2] = b;
        }
    }

    /* renamed from: b */
    private int m4380b(byte[] bArr) {
        return (((bArr[0] & 255) | ((bArr[1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK)) | ((bArr[2] << 16) & 16711680)) | ((bArr[3] << 24) & ViewCompat.MEASURED_STATE_MASK);
    }

    /* renamed from: a */
    private int m4375a(int i, int i2) {
        return ((i % 128) * 128) + (i2 % 128);
    }

    /* JADX WARNING: Removed duplicated region for block: B:87:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00fc  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00fc  */
    /* JADX WARNING: Removed duplicated region for block: B:87:? A:{SYNTHETIC, RETURN} */
    /* renamed from: a */
    public int mo10322a(com.amap.api.mapcore2d.TileCoordinate r12) {
        /*
        r11 = this;
        r10 = 4;
        r3 = 1;
        r2 = 0;
        r7 = 0;
        r0 = -1;
        r6 = "getTileFromCach";
        r1 = r12.f2595b;
        r4 = r12.f2596c;
        r5 = r12.f2597d;
        r4 = r11.m4379a(r1, r4, r5, r3);
        if (r4 == 0) goto L_0x002a;
    L_0x0013:
        r1 = r4.length;
        r5 = 2;
        if (r1 != r5) goto L_0x002a;
    L_0x0017:
        r1 = r4[r7];
        r5 = "";
        r1 = r1.equals(r5);
        if (r1 != 0) goto L_0x002a;
    L_0x0021:
        r1 = r4.length;
        r1 = new java.lang.String[r1];
        r1 = java.util.Arrays.equals(r4, r1);
        if (r1 == 0) goto L_0x002b;
    L_0x002a:
        return r0;
    L_0x002b:
        r5 = new java.io.File;
        r1 = r4[r7];
        r5.<init>(r1);
        r1 = r5.exists();
        if (r1 == 0) goto L_0x002a;
    L_0x0038:
        r1 = r12.f2595b;
        r7 = r12.f2596c;
        r7 = r11.m4375a(r1, r7);
        if (r7 < 0) goto L_0x002a;
    L_0x0042:
        r1 = new java.io.RandomAccessFile;	 Catch:{ FileNotFoundException -> 0x009d }
        r8 = "r";
        r1.<init>(r5, r8);	 Catch:{ FileNotFoundException -> 0x009d }
    L_0x0049:
        if (r1 == 0) goto L_0x002a;
    L_0x004b:
        r5 = r7 * 4;
        r8 = (long) r5;
        r1.seek(r8);	 Catch:{ IOException -> 0x00a5 }
    L_0x0051:
        r7 = new byte[r10];
        r5 = 0;
        r8 = 4;
        r1.read(r7, r5, r8);	 Catch:{ IOException -> 0x00ac }
    L_0x0058:
        r11.m4377a(r7);
        r8 = r11.m4380b(r7);
        r1.close();	 Catch:{ Throwable -> 0x00b3 }
    L_0x0062:
        if (r8 < 0) goto L_0x002a;
    L_0x0064:
        r5 = new java.io.File;
        r1 = r4[r3];
        r5.<init>(r1);
        r1 = r5.exists();
        if (r1 == 0) goto L_0x002a;
    L_0x0071:
        r1 = new java.io.RandomAccessFile;	 Catch:{ FileNotFoundException -> 0x00ba }
        r4 = "r";
        r1.<init>(r5, r4);	 Catch:{ FileNotFoundException -> 0x00ba }
        r5 = r1;
    L_0x0079:
        if (r5 == 0) goto L_0x002a;
    L_0x007b:
        r8 = (long) r8;
        r5.seek(r8);	 Catch:{ IOException -> 0x00c2 }
    L_0x007f:
        r1 = 0;
        r4 = 4;
        r5.read(r7, r1, r4);	 Catch:{ IOException -> 0x00c9 }
    L_0x0084:
        r11.m4377a(r7);
        r1 = r11.m4380b(r7);
        if (r1 <= 0) goto L_0x0092;
    L_0x008d:
        r4 = 204800; // 0x32000 float:2.86986E-40 double:1.011846E-318;
        if (r1 <= r4) goto L_0x00d0;
    L_0x0092:
        r5.close();	 Catch:{ IOException -> 0x0096 }
        goto L_0x002a;
    L_0x0096:
        r1 = move-exception;
        r2 = "CachManager";
        com.amap.api.mapcore2d.C0955ck.m3888a(r1, r2, r6);
        goto L_0x002a;
    L_0x009d:
        r1 = move-exception;
        r5 = "CachManager";
        com.amap.api.mapcore2d.C0955ck.m3888a(r1, r5, r6);
        r1 = r2;
        goto L_0x0049;
    L_0x00a5:
        r5 = move-exception;
        r7 = "CachManager";
        com.amap.api.mapcore2d.C0955ck.m3888a(r5, r7, r6);
        goto L_0x0051;
    L_0x00ac:
        r5 = move-exception;
        r8 = "CachManager";
        com.amap.api.mapcore2d.C0955ck.m3888a(r5, r8, r6);
        goto L_0x0058;
    L_0x00b3:
        r1 = move-exception;
        r5 = "CachManager";
        com.amap.api.mapcore2d.C0955ck.m3888a(r1, r5, r6);
        goto L_0x0062;
    L_0x00ba:
        r1 = move-exception;
        r4 = "CachManager";
        com.amap.api.mapcore2d.C0955ck.m3888a(r1, r4, r6);
        r5 = r2;
        goto L_0x0079;
    L_0x00c2:
        r1 = move-exception;
        r4 = "CachManager";
        com.amap.api.mapcore2d.C0955ck.m3888a(r1, r4, r6);
        goto L_0x007f;
    L_0x00c9:
        r1 = move-exception;
        r4 = "CachManager";
        com.amap.api.mapcore2d.C0955ck.m3888a(r1, r4, r6);
        goto L_0x0084;
    L_0x00d0:
        r4 = new byte[r1];	 Catch:{ Throwable -> 0x0109 }
        r7 = 0;
        r5.read(r4, r7, r1);	 Catch:{ Throwable -> 0x0119 }
        r1 = r4;
    L_0x00d7:
        r5.close();	 Catch:{ IOException -> 0x0112 }
    L_0x00da:
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = r12.f2595b;
        r4.append(r5);
        r5 = "-";
        r4.append(r5);
        r5 = r12.f2596c;
        r4.append(r5);
        r5 = "-";
        r4.append(r5);
        r5 = r12.f2597d;
        r4.append(r5);
        r5 = r11.f3051b;
        if (r5 == 0) goto L_0x002a;
    L_0x00fc:
        r0 = r11.f3051b;
        r5 = r4.toString();
        r4 = r2;
        r0 = r0.mo9919a(r1, r2, r3, r4, r5);
        goto L_0x002a;
    L_0x0109:
        r1 = move-exception;
        r4 = r2;
    L_0x010b:
        r7 = "CachManager";
        com.amap.api.mapcore2d.C0955ck.m3888a(r1, r7, r6);
        r1 = r4;
        goto L_0x00d7;
    L_0x0112:
        r4 = move-exception;
        r5 = "CachManager";
        com.amap.api.mapcore2d.C0955ck.m3888a(r4, r5, r6);
        goto L_0x00da;
    L_0x0119:
        r1 = move-exception;
        goto L_0x010b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore2d.DiskCachManager.mo10322a(com.amap.api.mapcore2d.bp):int");
    }

    /* renamed from: a */
    public synchronized boolean mo10324a(byte[] bArr, int i, int i2, int i3) {
        boolean z;
        String str = "addDataToCach";
        if (bArr == null) {
            z = false;
        } else {
            int length = bArr.length;
            if (length <= 0) {
                z = false;
            } else {
                String[] a = m4379a(i, i2, i3, false);
                if (a == null || a.length != 2 || a[0].equals("") || Arrays.equals(a, new String[a.length])) {
                    z = false;
                } else {
                    RandomAccessFile randomAccessFile;
                    File file = new File(a[1]);
                    if (!file.exists()) {
                        z = false;
                        try {
                            z = file.createNewFile();
                        } catch (IOException e) {
                            C0955ck.m3888a(e, "CachManager", str);
                        }
                        if (!z) {
                            z = false;
                        }
                    }
                    try {
                        randomAccessFile = new RandomAccessFile(file, "rws");
                    } catch (FileNotFoundException e2) {
                        C0955ck.m3888a(e2, "CachManager", str);
                        randomAccessFile = null;
                    }
                    if (randomAccessFile == null) {
                        z = false;
                    } else {
                        long length2;
                        byte[] a2 = m4378a(length);
                        m4377a(a2);
                        long j = 0;
                        try {
                            length2 = randomAccessFile.length();
                        } catch (IOException e3) {
                            C0955ck.m3888a(e3, "CachManager", str);
                            length2 = j;
                        }
                        try {
                            randomAccessFile.seek(length2);
                        } catch (IOException e4) {
                            C0955ck.m3888a(e4, "CachManager", str);
                        }
                        try {
                            randomAccessFile.write(a2);
                        } catch (IOException e42) {
                            C0955ck.m3888a(e42, "CachManager", str);
                        }
                        try {
                            randomAccessFile.write(bArr);
                        } catch (IOException e422) {
                            C0955ck.m3888a(e422, "CachManager", str);
                        }
                        try {
                            randomAccessFile.close();
                        } catch (IOException e4222) {
                            C0955ck.m3888a(e4222, "CachManager", str);
                        }
                        file = new File(a[0]);
                        if (!file.exists()) {
                            z = false;
                            try {
                                z = file.createNewFile();
                            } catch (IOException e5) {
                                C0955ck.m3888a(e5, "CachManager", str);
                            }
                            if (!z) {
                                z = false;
                            }
                        }
                        try {
                            randomAccessFile = new RandomAccessFile(file, "rws");
                        } catch (FileNotFoundException e22) {
                            C0955ck.m3888a(e22, "CachManager", str);
                            randomAccessFile = null;
                        }
                        if (randomAccessFile == null) {
                            z = false;
                        } else {
                            byte[] bArr2;
                            j = 0;
                            try {
                                j = randomAccessFile.length();
                            } catch (IOException e6) {
                                C0955ck.m3888a(e6, "CachManager", str);
                            }
                            if (j == 0) {
                                bArr2 = new byte[65536];
                                Arrays.fill(bArr2, (byte) -1);
                                try {
                                    randomAccessFile.write(bArr2);
                                } catch (IOException e42222) {
                                    C0955ck.m3888a(e42222, "CachManager", str);
                                }
                            }
                            int a3 = m4375a(i, i2);
                            if (a3 < 0) {
                                try {
                                    randomAccessFile.close();
                                } catch (IOException e422222) {
                                    C0955ck.m3888a(e422222, "CachManager", str);
                                }
                                z = false;
                            } else {
                                try {
                                    randomAccessFile.seek((long) (a3 * 4));
                                } catch (IOException e4222222) {
                                    C0955ck.m3888a(e4222222, "CachManager", str);
                                }
                                bArr2 = m4378a((int) length2);
                                m4377a(bArr2);
                                try {
                                    randomAccessFile.write(bArr2);
                                } catch (IOException e42222222) {
                                    C0955ck.m3888a(e42222222, "CachManager", str);
                                }
                                try {
                                    randomAccessFile.close();
                                } catch (IOException e422222222) {
                                    C0955ck.m3888a(e422222222, "CachManager", str);
                                }
                                z = true;
                            }
                        }
                    }
                }
            }
        }
        return z;
    }
}
