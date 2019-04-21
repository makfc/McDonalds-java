package com.alipay.security.mobile.module.p023d;

import com.alipay.security.mobile.module.p019a.C0689a;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.alipay.security.mobile.module.d.d */
public final class C0704d {
    /* renamed from: a */
    private static String f738a = "";
    /* renamed from: b */
    private static String f739b = "";
    /* renamed from: c */
    private static String f740c = "";

    /* renamed from: a */
    public static synchronized void m1260a(String str) {
        synchronized (C0704d.class) {
            List arrayList = new ArrayList();
            arrayList.add(str);
            C0704d.m1263a(arrayList);
        }
    }

    /* renamed from: a */
    public static synchronized void m1261a(String str, String str2, String str3) {
        synchronized (C0704d.class) {
            f738a = str;
            f739b = str2;
            f740c = str3;
        }
    }

    /* renamed from: a */
    public static synchronized void m1262a(Throwable th) {
        synchronized (C0704d.class) {
            Object obj;
            List arrayList = new ArrayList();
            if (th != null) {
                StringWriter stringWriter = new StringWriter();
                th.printStackTrace(new PrintWriter(stringWriter));
                obj = stringWriter.toString();
            } else {
                obj = "";
            }
            arrayList.add(obj);
            C0704d.m1263a(arrayList);
        }
    }

    /* renamed from: a */
    private static synchronized void m1263a(List<String> list) {
        synchronized (C0704d.class) {
            if (!(C0689a.m1169a(f739b) || C0689a.m1169a(f740c))) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(f740c);
                for (String str : list) {
                    stringBuffer.append(", " + str);
                }
                stringBuffer.append("\n");
                try {
                    File file = new File(f738a);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    File file2 = new File(f738a, f739b);
                    if (!file2.exists()) {
                        file2.createNewFile();
                    }
                    FileWriter fileWriter = file2.length() + ((long) stringBuffer.length()) <= 51200 ? new FileWriter(file2, true) : new FileWriter(file2);
                    fileWriter.write(stringBuffer.toString());
                    fileWriter.flush();
                    fileWriter.close();
                } catch (Exception e) {
                }
            }
        }
    }
}
