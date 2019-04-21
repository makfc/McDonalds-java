package com.facebook.stetho.dumpapp;

import android.content.Context;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ByteArrayEntity;

public class RawDumpappHandler extends DumpappHandler {
    private static final String RESPONSE_HEADER_EXIT_CODE = "X-FAB-ExitCode";

    public RawDumpappHandler(Context context, Dumper dumper) {
        super(context, dumper);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: EliminatePhiNodes
        jadx.core.utils.exceptions.JadxRuntimeException: Unexpected register number in merge insn: ?: MERGE  (r3_3 'stdout' java.io.PrintStream) = (r3_2 java.io.PrintStream), (r3_5 'stderr' java.io.PrintStream)
        	at jadx.core.dex.visitors.ssa.EliminatePhiNodes.replaceMerge(EliminatePhiNodes.java:84)
        	at jadx.core.dex.visitors.ssa.EliminatePhiNodes.replaceMergeInstructions(EliminatePhiNodes.java:68)
        	at jadx.core.dex.visitors.ssa.EliminatePhiNodes.visit(EliminatePhiNodes.java:31)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.util.ArrayList.forEach(ArrayList.java:1249)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.ProcessClass.process(ProcessClass.java:32)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:51)
        	at java.lang.Iterable.forEach(Iterable.java:75)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:51)
        	at jadx.core.ProcessClass.process(ProcessClass.java:37)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        */
    protected org.apache.http.HttpEntity getResponseEntity(org.apache.http.HttpRequest r9, java.io.InputStream r10, org.apache.http.HttpResponse r11) throws java.io.IOException {
        /*
        r8 = this;
        r4 = new java.io.ByteArrayOutputStream;
        r4.<init>();
        r3 = new java.io.PrintStream;	 Catch:{ all -> 0x0063 }
        r3.<init>(r4);	 Catch:{ all -> 0x0063 }
        r2 = new java.io.ByteArrayOutputStream;	 Catch:{ all -> 0x005e }
        r2.<init>();	 Catch:{ all -> 0x005e }
        r1 = new java.io.PrintStream;	 Catch:{ all -> 0x005e }
        r1.<init>(r2);	 Catch:{ all -> 0x005e }
        r5 = r8.getDumper();	 Catch:{ all -> 0x004a }
        r6 = com.facebook.stetho.dumpapp.DumpappHandler.getArgs(r9);	 Catch:{ all -> 0x004a }
        r0 = r5.dump(r10, r3, r1, r6);	 Catch:{ all -> 0x004a }
        r5 = "X-FAB-ExitCode";	 Catch:{ all -> 0x004a }
        r6 = java.lang.String.valueOf(r0);	 Catch:{ all -> 0x004a }
        r11.addHeader(r5, r6);	 Catch:{ all -> 0x004a }
        r1.close();	 Catch:{ all -> 0x005e }
        r5 = r2.size();	 Catch:{ all -> 0x005e }
        if (r5 <= 0) goto L_0x003b;	 Catch:{ all -> 0x005e }
    L_0x0032:
        r5 = java.lang.System.err;	 Catch:{ all -> 0x005e }
        r6 = r2.toByteArray();	 Catch:{ all -> 0x005e }
        r5.write(r6);	 Catch:{ all -> 0x005e }
    L_0x003b:
        r3.close();	 Catch:{ all -> 0x0063 }
        r10.close();
        r5 = r4.toByteArray();
        r5 = createResponseEntity(r5);
        return r5;
    L_0x004a:
        r5 = move-exception;
        r1.close();	 Catch:{ all -> 0x005e }
        r6 = r2.size();	 Catch:{ all -> 0x005e }
        if (r6 <= 0) goto L_0x005d;	 Catch:{ all -> 0x005e }
    L_0x0054:
        r6 = java.lang.System.err;	 Catch:{ all -> 0x005e }
        r7 = r2.toByteArray();	 Catch:{ all -> 0x005e }
        r6.write(r7);	 Catch:{ all -> 0x005e }
    L_0x005d:
        throw r5;	 Catch:{ all -> 0x005e }
    L_0x005e:
        r5 = move-exception;
        r3.close();	 Catch:{ all -> 0x0063 }
        throw r5;	 Catch:{ all -> 0x0063 }
    L_0x0063:
        r5 = move-exception;
        r10.close();
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.stetho.dumpapp.RawDumpappHandler.getResponseEntity(org.apache.http.HttpRequest, java.io.InputStream, org.apache.http.HttpResponse):org.apache.http.HttpEntity");
    }

    private static HttpEntity createResponseEntity(byte[] data) {
        ByteArrayEntity entity = new ByteArrayEntity(data);
        if (isProbablyBinaryData(data)) {
            entity.setContentType("application/octet-stream");
        } else {
            entity.setContentType("text/plain");
        }
        return entity;
    }

    private static boolean isProbablyBinaryData(byte[] data) {
        for (byte b : data) {
            if (b >= Byte.MAX_VALUE || (b < (byte) 32 && b != (byte) 13 && b != (byte) 10 && b != (byte) 9)) {
                return true;
            }
        }
        return false;
    }
}
