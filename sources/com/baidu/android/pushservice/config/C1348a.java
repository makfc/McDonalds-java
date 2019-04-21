package com.baidu.android.pushservice.config;

import android.content.Context;
import com.baidu.android.pushservice.p034f.C1403b;
import com.baidu.android.pushservice.p036h.C1425a;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;

/* renamed from: com.baidu.android.pushservice.config.a */
public class C1348a {
    /* JADX WARNING: Unknown top exception splitter block from list: {B:18:0x0046=Splitter:B:18:0x0046, B:11:0x002b=Splitter:B:11:0x002b} */
    /* renamed from: a */
    public static java.lang.String m6083a(android.content.Context r8, java.lang.String r9) {
        /*
        r7 = 1;
        r6 = 0;
        r1 = com.baidu.android.pushservice.config.C1348a.m6085b(r8, r9);
        if (r1 != 0) goto L_0x000a;
    L_0x0008:
        r0 = 0;
    L_0x0009:
        return r0;
    L_0x000a:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = new java.io.BufferedReader;	 Catch:{ UnsupportedEncodingException -> 0x004f }
        r0 = new java.io.InputStreamReader;	 Catch:{ UnsupportedEncodingException -> 0x004f }
        r4 = "utf-8";
        r0.<init>(r1, r4);	 Catch:{ UnsupportedEncodingException -> 0x004f }
        r3.<init>(r0);	 Catch:{ UnsupportedEncodingException -> 0x004f }
        r0 = r3.readLine();	 Catch:{ IOException -> 0x003f }
    L_0x0020:
        if (r0 == 0) goto L_0x002a;
    L_0x0022:
        r2.append(r0);	 Catch:{ IOException -> 0x003f }
        r0 = r3.readLine();	 Catch:{ IOException -> 0x003f }
        goto L_0x0020;
    L_0x002a:
        r0 = 1;
        r0 = new java.io.Closeable[r0];	 Catch:{ UnsupportedEncodingException -> 0x004f }
        r4 = 0;
        r0[r4] = r3;	 Catch:{ UnsupportedEncodingException -> 0x004f }
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r0);	 Catch:{ UnsupportedEncodingException -> 0x004f }
    L_0x0033:
        r0 = new java.io.Closeable[r7];
        r0[r6] = r1;
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r0);
    L_0x003a:
        r0 = r2.toString();
        goto L_0x0009;
    L_0x003f:
        r0 = move-exception;
        r4 = "AssetFileUtils";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r4, r0);	 Catch:{ all -> 0x005d }
        r0 = 1;
        r0 = new java.io.Closeable[r0];	 Catch:{ UnsupportedEncodingException -> 0x004f }
        r4 = 0;
        r0[r4] = r3;	 Catch:{ UnsupportedEncodingException -> 0x004f }
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r0);	 Catch:{ UnsupportedEncodingException -> 0x004f }
        goto L_0x0033;
    L_0x004f:
        r0 = move-exception;
        r3 = "AssetFileUtils";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r3, r0);	 Catch:{ all -> 0x0068 }
        r0 = new java.io.Closeable[r7];
        r0[r6] = r1;
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r0);
        goto L_0x003a;
    L_0x005d:
        r0 = move-exception;
        r4 = 1;
        r4 = new java.io.Closeable[r4];	 Catch:{ UnsupportedEncodingException -> 0x004f }
        r5 = 0;
        r4[r5] = r3;	 Catch:{ UnsupportedEncodingException -> 0x004f }
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r4);	 Catch:{ UnsupportedEncodingException -> 0x004f }
        throw r0;	 Catch:{ UnsupportedEncodingException -> 0x004f }
    L_0x0068:
        r0 = move-exception;
        r2 = new java.io.Closeable[r7];
        r2[r6] = r1;
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r2);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.config.C1348a.m6083a(android.content.Context, java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    public static boolean m6084a(String str, String str2) {
        Throwable e;
        Object obj;
        Closeable obj2;
        Object obj3;
        Closeable closeable = null;
        FileWriter fileWriter;
        try {
            BufferedWriter bufferedWriter;
            File file = new File(str);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            fileWriter = new FileWriter(str, false);
            try {
                bufferedWriter = new BufferedWriter(fileWriter);
            } catch (Exception e2) {
                e = e2;
                obj2 = fileWriter;
                try {
                    C1425a.m6440a("AssetFileUtils", e);
                    C1403b.m6265a(closeable, obj2);
                    return false;
                } catch (Throwable th) {
                    e = th;
                    Closeable fileWriter2 = obj2;
                    C1403b.m6265a(closeable, fileWriter2);
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                C1403b.m6265a(closeable, fileWriter2);
                throw e;
            }
            try {
                bufferedWriter.write(str2);
                C1403b.m6265a(bufferedWriter, fileWriter2);
                return true;
            } catch (Exception e3) {
                e = e3;
                obj3 = bufferedWriter;
                obj2 = fileWriter2;
                C1425a.m6440a("AssetFileUtils", e);
                C1403b.m6265a(closeable, obj2);
                return false;
            } catch (Throwable th3) {
                e = th3;
                obj3 = bufferedWriter;
                C1403b.m6265a(closeable, fileWriter2);
                throw e;
            }
        } catch (Exception e4) {
            e = e4;
            obj2 = null;
            C1425a.m6440a("AssetFileUtils", e);
            C1403b.m6265a(closeable, obj2);
            return false;
        } catch (Throwable th4) {
            e = th4;
            fileWriter2 = null;
            C1403b.m6265a(closeable, fileWriter2);
            throw e;
        }
    }

    /* renamed from: b */
    private static InputStream m6085b(Context context, String str) {
        try {
            File file = new File(str);
            return !file.exists() ? C1348a.class.getResourceAsStream(str) : new FileInputStream(file);
        } catch (Exception e) {
            C1425a.m6440a("AssetFileUtils", e);
            return null;
        }
    }
}
