package com.autonavi.amap.mapcore;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import com.amap.api.maps.MapsInitializer;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class FileUtil {
    private static final String TAG = "FileUtil";

    public static void copy(Context context, String str, File file) throws Exception {
        file.delete();
        InputStream open = context.getAssets().open(str);
        byte[] bArr = new byte[open.available()];
        open.read(bArr);
        open.close();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(bArr);
        fileOutputStream.close();
    }

    public static void deleteFile(File file) {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File deleteFile : listFiles) {
                    deleteFile(deleteFile);
                }
            } else {
                return;
            }
        }
        file.delete();
    }

    public static String getMapBaseStorage(Context context) {
        File file;
        String str = "map_base_path";
        if (VERSION.SDK_INT > 18) {
            str = "map_base_path_v44";
        }
        String str2 = "";
        SharedPreferences sharedPreferences = context.getSharedPreferences("base_path", 0);
        if (MapsInitializer.sdcardDir == null || MapsInitializer.sdcardDir.trim().length() <= 0) {
            str2 = sharedPreferences.getString(str, "");
        } else {
            str2 = MapsInitializer.sdcardDir;
        }
        if (str2 != null && str2.length() > 2) {
            file = new File(str2);
            if (!file.exists()) {
                file.mkdir();
            }
            if (file.isDirectory()) {
                if (file.canWrite()) {
                    return str2;
                }
                str2 = context.getCacheDir().toString();
                if (str2 != null && str2.length() > 2 && new File(str2).isDirectory()) {
                    return str2;
                }
            }
        }
        str2 = getExternalStroragePath(context);
        if (str2 != null && str2.length() > 2) {
            str2 = str2 + File.separator + MapTilsCacheAndResManager.AUTONAVI_PATH;
            file = new File(str2);
            if (!file.exists()) {
                file.mkdir();
            }
            if (file.isDirectory()) {
                Editor edit = sharedPreferences.edit();
                edit.putString(str, str2);
                edit.commit();
                createNoMediaFileIfNotExist(str2);
                return str2;
            }
        }
        str = context.getCacheDir().toString();
        if (str == null || str.length() <= 2) {
            return str;
        }
        str = str + File.separator + MapTilsCacheAndResManager.AUTONAVI_PATH;
        File file2 = new File(str);
        if (!file2.exists()) {
            file2.mkdir();
        }
        if (file2.isDirectory()) {
        }
        return str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x00e7 A:{SKIP} */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00bf A:{Catch:{ Exception -> 0x00f6 }} */
    public static java.lang.String getExternalStroragePath(android.content.Context r17) {
        /*
        r10 = android.os.Build.VERSION.SDK_INT;
        r1 = 12;
        if (r10 < r1) goto L_0x00f7;
    L_0x0006:
        r1 = "storage";
        r0 = r17;
        r1 = r0.getSystemService(r1);	 Catch:{ Exception -> 0x00f6 }
        r1 = (android.os.storage.StorageManager) r1;	 Catch:{ Exception -> 0x00f6 }
        r2 = android.os.storage.StorageManager.class;
        r3 = "getVolumeList";
        r4 = 0;
        r4 = new java.lang.Class[r4];	 Catch:{ Exception -> 0x00f6 }
        r2 = r2.getMethod(r3, r4);	 Catch:{ Exception -> 0x00f6 }
        r3 = android.os.storage.StorageManager.class;
        r4 = "getVolumeState";
        r5 = 1;
        r5 = new java.lang.Class[r5];	 Catch:{ Exception -> 0x00f6 }
        r6 = 0;
        r7 = java.lang.String.class;
        r5[r6] = r7;	 Catch:{ Exception -> 0x00f6 }
        r11 = r3.getMethod(r4, r5);	 Catch:{ Exception -> 0x00f6 }
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Exception -> 0x00f6 }
        r2 = r2.invoke(r1, r3);	 Catch:{ Exception -> 0x00f6 }
        r2 = (java.lang.Object[]) r2;	 Catch:{ Exception -> 0x00f6 }
        r2 = (java.lang.Object[]) r2;	 Catch:{ Exception -> 0x00f6 }
        r3 = 0;
        java.lang.Boolean.valueOf(r3);	 Catch:{ Exception -> 0x00f6 }
        r3 = "";
        r7 = "";
        r3 = "";
        r6 = "";
        r8 = 0;
        r12 = r2.length;	 Catch:{ Exception -> 0x00f6 }
        r3 = 0;
        r9 = r3;
    L_0x0046:
        if (r9 >= r12) goto L_0x0115;
    L_0x0048:
        r5 = r2[r9];	 Catch:{ Exception -> 0x00f6 }
        r3 = r5.getClass();	 Catch:{ Exception -> 0x00f6 }
        r4 = "getPath";
        r13 = 0;
        r13 = new java.lang.Class[r13];	 Catch:{ Exception -> 0x00f6 }
        r4 = r3.getMethod(r4, r13);	 Catch:{ Exception -> 0x00f6 }
        r3 = r5.getClass();	 Catch:{ Exception -> 0x00f6 }
        r13 = "isRemovable";
        r14 = 0;
        r14 = new java.lang.Class[r14];	 Catch:{ Exception -> 0x00f6 }
        r13 = r3.getMethod(r13, r14);	 Catch:{ Exception -> 0x00f6 }
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Exception -> 0x00f6 }
        r3 = r4.invoke(r5, r3);	 Catch:{ Exception -> 0x00f6 }
        r3 = (java.lang.String) r3;	 Catch:{ Exception -> 0x00f6 }
        r14 = 1;
        r14 = new java.lang.Object[r14];	 Catch:{ Exception -> 0x00f6 }
        r15 = 0;
        r16 = 0;
        r0 = r16;
        r0 = new java.lang.Object[r0];	 Catch:{ Exception -> 0x00f6 }
        r16 = r0;
        r0 = r16;
        r4 = r4.invoke(r5, r0);	 Catch:{ Exception -> 0x00f6 }
        r14[r15] = r4;	 Catch:{ Exception -> 0x00f6 }
        r4 = r11.invoke(r1, r14);	 Catch:{ Exception -> 0x00f6 }
        r4 = (java.lang.String) r4;	 Catch:{ Exception -> 0x00f6 }
        r14 = 0;
        r14 = new java.lang.Object[r14];	 Catch:{ Exception -> 0x00f6 }
        r5 = r13.invoke(r5, r14);	 Catch:{ Exception -> 0x00f6 }
        r5 = (java.lang.Boolean) r5;	 Catch:{ Exception -> 0x00f6 }
        r13 = r3.toLowerCase();	 Catch:{ Exception -> 0x00f6 }
        r14 = "private";
        r13 = r13.contains(r14);	 Catch:{ Exception -> 0x00f6 }
        if (r13 == 0) goto L_0x00a4;
    L_0x009c:
        r4 = r6;
        r3 = r7;
    L_0x009e:
        r5 = r9 + 1;
        r9 = r5;
        r6 = r4;
        r7 = r3;
        goto L_0x0046;
    L_0x00a4:
        r5 = r5.booleanValue();	 Catch:{ Exception -> 0x00f6 }
        if (r5 == 0) goto L_0x009e;
    L_0x00aa:
        if (r3 == 0) goto L_0x0112;
    L_0x00ac:
        if (r4 == 0) goto L_0x0112;
    L_0x00ae:
        r5 = "mounted";
        r4 = r4.equals(r5);	 Catch:{ Exception -> 0x00f6 }
        if (r4 == 0) goto L_0x0112;
    L_0x00b6:
        r1 = 18;
        if (r10 > r1) goto L_0x00ce;
    L_0x00ba:
        r1 = r3;
    L_0x00bb:
        r2 = 18;
        if (r10 > r2) goto L_0x00e7;
    L_0x00bf:
        if (r1 != 0) goto L_0x010e;
    L_0x00c1:
        if (r7 == 0) goto L_0x010e;
    L_0x00c3:
        if (r6 == 0) goto L_0x010e;
    L_0x00c5:
        r2 = "mounted";
        r2 = r6.equals(r2);	 Catch:{ Exception -> 0x00f6 }
        if (r2 == 0) goto L_0x010e;
    L_0x00cd:
        return r7;
    L_0x00ce:
        r1 = 0;
        r0 = r17;
        r1 = r0.getExternalFilesDirs(r1);	 Catch:{ Exception -> 0x00e4 }
        if (r1 == 0) goto L_0x0110;
    L_0x00d7:
        r2 = r1.length;	 Catch:{ Exception -> 0x00e4 }
        r4 = 1;
        if (r2 <= r4) goto L_0x00e2;
    L_0x00db:
        r2 = 1;
        r1 = r1[r2];	 Catch:{ Exception -> 0x00e4 }
        r3 = r1.getAbsolutePath();	 Catch:{ Exception -> 0x00e4 }
    L_0x00e2:
        r1 = r3;
        goto L_0x00bb;
    L_0x00e4:
        r1 = move-exception;
        r1 = r3;
        goto L_0x00bb;
    L_0x00e7:
        if (r7 == 0) goto L_0x00f4;
    L_0x00e9:
        if (r6 == 0) goto L_0x00f4;
    L_0x00eb:
        r2 = "mounted";
        r2 = r6.equals(r2);	 Catch:{ Exception -> 0x00f6 }
        if (r2 == 0) goto L_0x00f4;
    L_0x00f3:
        r1 = r7;
    L_0x00f4:
        r7 = r1;
        goto L_0x00cd;
    L_0x00f6:
        r1 = move-exception;
    L_0x00f7:
        r1 = android.os.Environment.getExternalStorageState();
        r2 = "mounted";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x010c;
    L_0x0103:
        r1 = android.os.Environment.getExternalStorageDirectory();
        r7 = r1.toString();
        goto L_0x00cd;
    L_0x010c:
        r7 = 0;
        goto L_0x00cd;
    L_0x010e:
        r7 = r1;
        goto L_0x00cd;
    L_0x0110:
        r3 = r8;
        goto L_0x00e2;
    L_0x0112:
        r4 = r6;
        r3 = r7;
        goto L_0x009e;
    L_0x0115:
        r1 = r8;
        goto L_0x00bb;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.amap.mapcore.FileUtil.getExternalStroragePath(android.content.Context):java.lang.String");
    }

    public static void writeDatasToFile(String str, byte[] bArr) {
        WriteLock writeLock = new ReentrantReadWriteLock().writeLock();
        writeLock.lock();
        if (bArr != null) {
            try {
                if (bArr.length != 0) {
                    File file = new File(str);
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(bArr);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            } finally {
                writeLock.unlock();
            }
        }
        writeLock.unlock();
    }

    public static byte[] readFileContents(String str) {
        byte[] bArr = null;
        try {
            File file = new File(str);
            if (!file.exists()) {
                return bArr;
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr2 = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read = fileInputStream.read(bArr2);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr2, 0, read);
                } else {
                    byteArrayOutputStream.close();
                    fileInputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return bArr;
        }
    }

    public static void createNoMediaFileIfNotExist(String str) {
    }
}
