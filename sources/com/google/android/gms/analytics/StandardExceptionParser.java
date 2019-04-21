package com.google.android.gms.analytics;

import android.content.Context;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

public class StandardExceptionParser implements ExceptionParser {
    private final TreeSet<String> zzUJ = new TreeSet();

    public StandardExceptionParser(Context context, Collection<String> collection) {
        setIncludedPackages(context, collection);
    }

    /* Access modifiers changed, original: protected */
    public StackTraceElement getBestStackTraceElement(Throwable th) {
        StackTraceElement[] stackTrace = th.getStackTrace();
        if (stackTrace == null || stackTrace.length == 0) {
            return null;
        }
        for (StackTraceElement stackTraceElement : stackTrace) {
            String className = stackTraceElement.getClassName();
            Iterator it = this.zzUJ.iterator();
            while (it.hasNext()) {
                if (className.startsWith((String) it.next())) {
                    return stackTraceElement;
                }
            }
        }
        return stackTrace[0];
    }

    /* Access modifiers changed, original: protected */
    public Throwable getCause(Throwable th) {
        while (th.getCause() != null) {
            th = th.getCause();
        }
        return th;
    }

    public String getDescription(String str, Throwable th) {
        return getDescription(getCause(th), getBestStackTraceElement(getCause(th)), str);
    }

    /* Access modifiers changed, original: protected */
    public String getDescription(Throwable th, StackTraceElement stackTraceElement, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(th.getClass().getSimpleName());
        if (stackTraceElement != null) {
            String[] split = stackTraceElement.getClassName().split("\\.");
            String str2 = "unknown";
            if (split != null && split.length > 0) {
                str2 = split[split.length - 1];
            }
            stringBuilder.append(String.format(" (@%s:%s:%s)", new Object[]{str2, stackTraceElement.getMethodName(), Integer.valueOf(stackTraceElement.getLineNumber())}));
        }
        if (str != null) {
            stringBuilder.append(String.format(" {%s}", new Object[]{str}));
        }
        return stringBuilder.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0049 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x007b  */
    public void setIncludedPackages(android.content.Context r9, java.util.Collection<java.lang.String> r10) {
        /*
        r8 = this;
        r3 = 1;
        r4 = 0;
        r0 = r8.zzUJ;
        r0.clear();
        r1 = new java.util.HashSet;
        r1.<init>();
        if (r10 == 0) goto L_0x0011;
    L_0x000e:
        r1.addAll(r10);
    L_0x0011:
        if (r9 == 0) goto L_0x0045;
    L_0x0013:
        r0 = r9.getApplicationContext();	 Catch:{ NameNotFoundException -> 0x003f }
        r0 = r0.getPackageName();	 Catch:{ NameNotFoundException -> 0x003f }
        r2 = r8.zzUJ;	 Catch:{ NameNotFoundException -> 0x003f }
        r2.add(r0);	 Catch:{ NameNotFoundException -> 0x003f }
        r2 = r9.getApplicationContext();	 Catch:{ NameNotFoundException -> 0x003f }
        r2 = r2.getPackageManager();	 Catch:{ NameNotFoundException -> 0x003f }
        r5 = 1;
        r0 = r2.getPackageInfo(r0, r5);	 Catch:{ NameNotFoundException -> 0x003f }
        r2 = r0.activities;	 Catch:{ NameNotFoundException -> 0x003f }
        if (r2 == 0) goto L_0x0045;
    L_0x0031:
        r5 = r2.length;	 Catch:{ NameNotFoundException -> 0x003f }
        r0 = r4;
    L_0x0033:
        if (r0 >= r5) goto L_0x0045;
    L_0x0035:
        r6 = r2[r0];	 Catch:{ NameNotFoundException -> 0x003f }
        r6 = r6.packageName;	 Catch:{ NameNotFoundException -> 0x003f }
        r1.add(r6);	 Catch:{ NameNotFoundException -> 0x003f }
        r0 = r0 + 1;
        goto L_0x0033;
    L_0x003f:
        r0 = move-exception;
        r0 = "No package found";
        com.google.android.gms.analytics.internal.zzae.zzaV(r0);
    L_0x0045:
        r5 = r1.iterator();
    L_0x0049:
        r0 = r5.hasNext();
        if (r0 == 0) goto L_0x0083;
    L_0x004f:
        r0 = r5.next();
        r0 = (java.lang.String) r0;
        r1 = r8.zzUJ;
        r6 = r1.iterator();
        r2 = r3;
    L_0x005c:
        r1 = r6.hasNext();
        if (r1 == 0) goto L_0x0079;
    L_0x0062:
        r1 = r6.next();
        r1 = (java.lang.String) r1;
        r7 = r0.startsWith(r1);
        if (r7 != 0) goto L_0x0081;
    L_0x006e:
        r6 = r1.startsWith(r0);
        if (r6 == 0) goto L_0x0079;
    L_0x0074:
        r6 = r8.zzUJ;
        r6.remove(r1);
    L_0x0079:
        if (r2 == 0) goto L_0x0049;
    L_0x007b:
        r1 = r8.zzUJ;
        r1.add(r0);
        goto L_0x0049;
    L_0x0081:
        r2 = r4;
        goto L_0x005c;
    L_0x0083:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.StandardExceptionParser.setIncludedPackages(android.content.Context, java.util.Collection):void");
    }
}
