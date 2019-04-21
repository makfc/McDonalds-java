package org.acra;

import android.content.Context;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map.Entry;
import org.acra.collector.CrashReportData;

final class CrashReportPersister {
    private final Context context;

    CrashReportPersister(Context context) {
        this.context = context;
    }

    public final CrashReportData load(String fileName) throws IOException {
        FileInputStream openFileInput = this.context.openFileInput(fileName);
        if (openFileInput == null) {
            throw new IllegalArgumentException("Invalid crash report fileName : " + fileName);
        }
        try {
            CrashReportData load = load(new InputStreamReader(new BufferedInputStream(openFileInput, 8192), "ISO8859-1"));
            return load;
        } finally {
            openFileInput.close();
        }
    }

    public final void store(CrashReportData crashData, String fileName) throws IOException {
        FileOutputStream openFileOutput = this.context.openFileOutput(fileName, 0);
        try {
            StringBuilder stringBuilder = new StringBuilder(200);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput, "ISO8859_1");
            for (Entry entry : crashData.entrySet()) {
                dumpString(stringBuilder, ((ReportField) entry.getKey()).toString(), true);
                stringBuilder.append('=');
                dumpString(stringBuilder, (String) entry.getValue(), false);
                stringBuilder.append("\n");
                outputStreamWriter.write(stringBuilder.toString());
                stringBuilder.setLength(0);
            }
            outputStreamWriter.flush();
        } finally {
            openFileOutput.close();
        }
    }

    private synchronized org.acra.collector.CrashReportData load(java.io.Reader r15) throws java.io.IOException {
        /*
        r14 = this;
        monitor-enter(r14);
        r3 = 0;
        r6 = 0;
        r5 = 0;
        r0 = 40;
        r7 = new char[r0];	 Catch:{ all -> 0x0053 }
        r2 = 0;
        r1 = -1;
        r0 = 1;
        r9 = new org.acra.collector.CrashReportData;	 Catch:{ all -> 0x0053 }
        r9.<init>();	 Catch:{ all -> 0x0053 }
        r10 = new java.io.BufferedReader;	 Catch:{ all -> 0x0053 }
        r4 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r10.<init>(r15, r4);	 Catch:{ all -> 0x0053 }
        r8 = r0;
        r4 = r6;
        r0 = r1;
        r1 = r5;
        r5 = r3;
    L_0x001c:
        r3 = r10.read();	 Catch:{ all -> 0x0053 }
        r6 = -1;
        if (r3 == r6) goto L_0x0147;
    L_0x0023:
        r3 = (char) r3;	 Catch:{ all -> 0x0053 }
        r6 = r7.length;	 Catch:{ all -> 0x0053 }
        if (r2 != r6) goto L_0x0032;
    L_0x0027:
        r6 = r7.length;	 Catch:{ all -> 0x0053 }
        r6 = r6 * 2;
        r6 = new char[r6];	 Catch:{ all -> 0x0053 }
        r11 = 0;
        r12 = 0;
        java.lang.System.arraycopy(r7, r11, r6, r12, r2);	 Catch:{ all -> 0x0053 }
        r7 = r6;
    L_0x0032:
        r6 = 2;
        if (r5 != r6) goto L_0x01a4;
    L_0x0035:
        r6 = 16;
        r6 = java.lang.Character.digit(r3, r6);	 Catch:{ all -> 0x0053 }
        if (r6 < 0) goto L_0x0048;
    L_0x003d:
        r4 = r4 << 4;
        r6 = r6 + r4;
        r4 = r1 + 1;
        r1 = 4;
        if (r4 >= r1) goto L_0x01a1;
    L_0x0045:
        r1 = r4;
        r4 = r6;
        goto L_0x001c;
    L_0x0048:
        r5 = 4;
        if (r1 > r5) goto L_0x0056;
    L_0x004b:
        r0 = new java.lang.IllegalArgumentException;	 Catch:{ all -> 0x0053 }
        r1 = "luni.09";
        r0.<init>(r1);	 Catch:{ all -> 0x0053 }
        throw r0;	 Catch:{ all -> 0x0053 }
    L_0x0053:
        r0 = move-exception;
        monitor-exit(r14);
        throw r0;
    L_0x0056:
        r5 = r4;
        r4 = r1;
    L_0x0058:
        r6 = 0;
        r1 = r2 + 1;
        r11 = (char) r5;
        r7[r2] = r11;	 Catch:{ all -> 0x0053 }
        r2 = 10;
        if (r3 == r2) goto L_0x0066;
    L_0x0062:
        r2 = 133; // 0x85 float:1.86E-43 double:6.57E-322;
        if (r3 != r2) goto L_0x019b;
    L_0x0066:
        r2 = r6;
        r6 = r5;
        r5 = r4;
    L_0x0069:
        r4 = 1;
        if (r2 != r4) goto L_0x00b2;
    L_0x006c:
        r4 = 0;
        switch(r3) {
            case 10: goto L_0x0089;
            case 13: goto L_0x0082;
            case 98: goto L_0x0090;
            case 102: goto L_0x0095;
            case 110: goto L_0x009a;
            case 114: goto L_0x009f;
            case 116: goto L_0x00a4;
            case 117: goto L_0x00a9;
            case 133: goto L_0x0089;
            default: goto L_0x0070;
        };	 Catch:{ all -> 0x0053 }
    L_0x0070:
        r2 = r4;
    L_0x0071:
        r4 = 0;
        r8 = 4;
        if (r2 != r8) goto L_0x0078;
    L_0x0075:
        r0 = 0;
        r2 = r0;
        r0 = r1;
    L_0x0078:
        r8 = r1 + 1;
        r7[r1] = r3;	 Catch:{ all -> 0x0053 }
        r1 = r5;
        r5 = r2;
        r2 = r8;
        r8 = r4;
        r4 = r6;
        goto L_0x001c;
    L_0x0082:
        r2 = 3;
        r4 = r6;
        r13 = r5;
        r5 = r2;
        r2 = r1;
        r1 = r13;
        goto L_0x001c;
    L_0x0089:
        r2 = 5;
        r4 = r6;
        r13 = r5;
        r5 = r2;
        r2 = r1;
        r1 = r13;
        goto L_0x001c;
    L_0x0090:
        r2 = 8;
        r3 = r2;
        r2 = r4;
        goto L_0x0071;
    L_0x0095:
        r2 = 12;
        r3 = r2;
        r2 = r4;
        goto L_0x0071;
    L_0x009a:
        r2 = 10;
        r3 = r2;
        r2 = r4;
        goto L_0x0071;
    L_0x009f:
        r2 = 13;
        r3 = r2;
        r2 = r4;
        goto L_0x0071;
    L_0x00a4:
        r2 = 9;
        r3 = r2;
        r2 = r4;
        goto L_0x0071;
    L_0x00a9:
        r2 = 2;
        r5 = 0;
        r4 = r5;
        r13 = r5;
        r5 = r2;
        r2 = r1;
        r1 = r13;
        goto L_0x001c;
    L_0x00b2:
        switch(r3) {
            case 10: goto L_0x00ee;
            case 13: goto L_0x00f9;
            case 33: goto L_0x00d1;
            case 35: goto L_0x00d1;
            case 58: goto L_0x0132;
            case 61: goto L_0x0132;
            case 92: goto L_0x0126;
            case 133: goto L_0x00f9;
            default: goto L_0x00b5;
        };	 Catch:{ all -> 0x0053 }
    L_0x00b5:
        r4 = java.lang.Character.isWhitespace(r3);	 Catch:{ all -> 0x0053 }
        if (r4 == 0) goto L_0x013e;
    L_0x00bb:
        r4 = 3;
        if (r2 != r4) goto L_0x00bf;
    L_0x00be:
        r2 = 5;
    L_0x00bf:
        if (r1 == 0) goto L_0x0194;
    L_0x00c1:
        if (r1 == r0) goto L_0x0194;
    L_0x00c3:
        r4 = 5;
        if (r2 == r4) goto L_0x0194;
    L_0x00c6:
        r4 = -1;
        if (r0 != r4) goto L_0x013e;
    L_0x00c9:
        r2 = 4;
        r4 = r6;
        r13 = r5;
        r5 = r2;
        r2 = r1;
        r1 = r13;
        goto L_0x001c;
    L_0x00d1:
        if (r8 == 0) goto L_0x00b5;
    L_0x00d3:
        r3 = r10.read();	 Catch:{ all -> 0x0053 }
        r4 = -1;
        if (r3 == r4) goto L_0x0194;
    L_0x00da:
        r3 = (char) r3;	 Catch:{ all -> 0x0053 }
        r4 = 13;
        if (r3 == r4) goto L_0x0194;
    L_0x00df:
        r4 = 10;
        if (r3 == r4) goto L_0x0194;
    L_0x00e3:
        r4 = 133; // 0x85 float:1.86E-43 double:6.57E-322;
        if (r3 != r4) goto L_0x00d3;
    L_0x00e7:
        r4 = r6;
        r13 = r5;
        r5 = r2;
        r2 = r1;
        r1 = r13;
        goto L_0x001c;
    L_0x00ee:
        r3 = 3;
        if (r2 != r3) goto L_0x00f9;
    L_0x00f1:
        r2 = 5;
        r4 = r6;
        r13 = r5;
        r5 = r2;
        r2 = r1;
        r1 = r13;
        goto L_0x001c;
    L_0x00f9:
        r3 = 0;
        r2 = 1;
        if (r1 > 0) goto L_0x0101;
    L_0x00fd:
        if (r1 != 0) goto L_0x011d;
    L_0x00ff:
        if (r0 != 0) goto L_0x011d;
    L_0x0101:
        r4 = -1;
        if (r0 != r4) goto L_0x0105;
    L_0x0104:
        r0 = r1;
    L_0x0105:
        r4 = new java.lang.String;	 Catch:{ all -> 0x0053 }
        r8 = 0;
        r4.<init>(r7, r8, r1);	 Catch:{ all -> 0x0053 }
        r1 = org.acra.ReportField.class;
        r8 = 0;
        r8 = r4.substring(r8, r0);	 Catch:{ all -> 0x0053 }
        r1 = java.lang.Enum.valueOf(r1, r8);	 Catch:{ all -> 0x0053 }
        r0 = r4.substring(r0);	 Catch:{ all -> 0x0053 }
        r9.put(r1, r0);	 Catch:{ all -> 0x0053 }
    L_0x011d:
        r0 = -1;
        r1 = 0;
        r8 = r2;
        r4 = r6;
        r2 = r1;
        r1 = r5;
        r5 = r3;
        goto L_0x001c;
    L_0x0126:
        r3 = 4;
        if (r2 != r3) goto L_0x012a;
    L_0x0129:
        r0 = r1;
    L_0x012a:
        r2 = 1;
        r4 = r6;
        r13 = r5;
        r5 = r2;
        r2 = r1;
        r1 = r13;
        goto L_0x001c;
    L_0x0132:
        r4 = -1;
        if (r0 != r4) goto L_0x00b5;
    L_0x0135:
        r2 = 0;
        r0 = r1;
        r4 = r6;
        r13 = r5;
        r5 = r2;
        r2 = r1;
        r1 = r13;
        goto L_0x001c;
    L_0x013e:
        r4 = 5;
        if (r2 == r4) goto L_0x0144;
    L_0x0141:
        r4 = 3;
        if (r2 != r4) goto L_0x0071;
    L_0x0144:
        r2 = 0;
        goto L_0x0071;
    L_0x0147:
        r3 = 2;
        if (r5 != r3) goto L_0x0155;
    L_0x014a:
        r3 = 4;
        if (r1 > r3) goto L_0x0155;
    L_0x014d:
        r0 = new java.lang.IllegalArgumentException;	 Catch:{ all -> 0x0053 }
        r1 = "luni.08";
        r0.<init>(r1);	 Catch:{ all -> 0x0053 }
        throw r0;	 Catch:{ all -> 0x0053 }
    L_0x0155:
        r1 = -1;
        if (r0 != r1) goto L_0x0192;
    L_0x0158:
        if (r2 <= 0) goto L_0x0192;
    L_0x015a:
        r1 = r2;
    L_0x015b:
        if (r1 < 0) goto L_0x018d;
    L_0x015d:
        r3 = new java.lang.String;	 Catch:{ all -> 0x0053 }
        r0 = 0;
        r3.<init>(r7, r0, r2);	 Catch:{ all -> 0x0053 }
        r0 = org.acra.ReportField.class;
        r2 = 0;
        r2 = r3.substring(r2, r1);	 Catch:{ all -> 0x0053 }
        r0 = java.lang.Enum.valueOf(r0, r2);	 Catch:{ all -> 0x0053 }
        r0 = (org.acra.ReportField) r0;	 Catch:{ all -> 0x0053 }
        r1 = r3.substring(r1);	 Catch:{ all -> 0x0053 }
        r2 = 1;
        if (r5 != r2) goto L_0x018a;
    L_0x0177:
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0053 }
        r2.<init>();	 Catch:{ all -> 0x0053 }
        r1 = r2.append(r1);	 Catch:{ all -> 0x0053 }
        r2 = "\u0000";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0053 }
        r1 = r1.toString();	 Catch:{ all -> 0x0053 }
    L_0x018a:
        r9.put(r0, r1);	 Catch:{ all -> 0x0053 }
    L_0x018d:
        org.acra.collector.CollectorUtil.safeClose(r15);	 Catch:{ all -> 0x0053 }
        monitor-exit(r14);
        return r9;
    L_0x0192:
        r1 = r0;
        goto L_0x015b;
    L_0x0194:
        r4 = r6;
        r13 = r5;
        r5 = r2;
        r2 = r1;
        r1 = r13;
        goto L_0x001c;
    L_0x019b:
        r2 = r1;
        r1 = r4;
        r4 = r5;
        r5 = r6;
        goto L_0x001c;
    L_0x01a1:
        r5 = r6;
        goto L_0x0058;
    L_0x01a4:
        r6 = r4;
        r13 = r1;
        r1 = r2;
        r2 = r5;
        r5 = r13;
        goto L_0x0069;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.acra.CrashReportPersister.load(java.io.Reader):org.acra.collector.CrashReportData");
    }

    private void dumpString(StringBuilder buffer, String string, boolean key) {
        int i;
        if (key || string.length() <= 0 || string.charAt(0) != SafeJsonPrimitive.NULL_CHAR) {
            i = 0;
        } else {
            buffer.append("\\ ");
            i = 1;
        }
        while (i < string.length()) {
            char charAt = string.charAt(i);
            switch (charAt) {
                case 9:
                    buffer.append("\\t");
                    break;
                case 10:
                    buffer.append("\\n");
                    break;
                case 12:
                    buffer.append("\\f");
                    break;
                case 13:
                    buffer.append("\\r");
                    break;
                default:
                    if ("\\#!=:".indexOf(charAt) >= 0 || (key && charAt == SafeJsonPrimitive.NULL_CHAR)) {
                        buffer.append('\\');
                    }
                    if (charAt >= SafeJsonPrimitive.NULL_CHAR && charAt <= '~') {
                        buffer.append(charAt);
                        break;
                    }
                    String toHexString = Integer.toHexString(charAt);
                    buffer.append("\\u");
                    for (int i2 = 0; i2 < 4 - toHexString.length(); i2++) {
                        buffer.append("0");
                    }
                    buffer.append(toHexString);
                    break;
                    break;
            }
            i++;
        }
    }
}
