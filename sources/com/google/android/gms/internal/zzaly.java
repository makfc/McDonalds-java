package com.google.android.gms.internal;

import com.facebook.internal.ServerProtocol;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

public class zzaly implements Closeable, Flushable {
    private static final String[] zzbZJ = new String[128];
    private static final String[] zzbZK = ((String[]) zzbZJ.clone());
    private final Writer out;
    private String separator;
    private boolean zzbWb;
    private boolean zzbWc;
    private String zzbZL;
    private String zzbZM;
    private boolean zzbZm;
    private int[] zzbZu = new int[32];
    private int zzbZv = 0;

    static {
        for (int i = 0; i <= 31; i++) {
            zzbZJ[i] = String.format("\\u%04x", new Object[]{Integer.valueOf(i)});
        }
        zzbZJ[34] = "\\\"";
        zzbZJ[92] = "\\\\";
        zzbZJ[9] = "\\t";
        zzbZJ[8] = "\\b";
        zzbZJ[10] = "\\n";
        zzbZJ[13] = "\\r";
        zzbZJ[12] = "\\f";
        zzbZK[60] = "\\u003c";
        zzbZK[62] = "\\u003e";
        zzbZK[38] = "\\u0026";
        zzbZK[61] = "\\u003d";
        zzbZK[39] = "\\u0027";
    }

    public zzaly(Writer writer) {
        zznQ(6);
        this.separator = ":";
        this.zzbWb = true;
        if (writer == null) {
            throw new NullPointerException("out == null");
        }
        this.out = writer;
    }

    private void zzWA() throws IOException {
        if (this.zzbZL != null) {
            this.out.write("\n");
            int i = this.zzbZv;
            for (int i2 = 1; i2 < i; i2++) {
                this.out.write(this.zzbZL);
            }
        }
    }

    private void zzWB() throws IOException {
        int zzWy = zzWy();
        if (zzWy == 5) {
            this.out.write(44);
        } else if (zzWy != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        zzWA();
        zznS(4);
    }

    private int zzWy() {
        if (this.zzbZv != 0) {
            return this.zzbZu[this.zzbZv - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    private void zzWz() throws IOException {
        if (this.zzbZM != null) {
            zzWB();
            zziX(this.zzbZM);
            this.zzbZM = null;
        }
    }

    private void zzbb(boolean z) throws IOException {
        switch (zzWy()) {
            case 1:
                zznS(2);
                zzWA();
                return;
            case 2:
                this.out.append(',');
                zzWA();
                return;
            case 4:
                this.out.append(this.separator);
                zznS(5);
                return;
            case 6:
                break;
            case 7:
                if (!this.zzbZm) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
                break;
            default:
                throw new IllegalStateException("Nesting problem.");
        }
        if (this.zzbZm || z) {
            zznS(7);
            return;
        }
        throw new IllegalStateException("JSON must start with an array or an object.");
    }

    private zzaly zzc(int i, int i2, String str) throws IOException {
        int zzWy = zzWy();
        if (zzWy != i2 && zzWy != i) {
            throw new IllegalStateException("Nesting problem.");
        } else if (this.zzbZM != null) {
            String str2 = "Dangling name: ";
            String valueOf = String.valueOf(this.zzbZM);
            throw new IllegalStateException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        } else {
            this.zzbZv--;
            if (zzWy == i2) {
                zzWA();
            }
            this.out.write(str);
            return this;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0030  */
    private void zziX(java.lang.String r8) throws java.io.IOException {
        /*
        r7 = this;
        r1 = 0;
        r0 = r7.zzbWc;
        if (r0 == 0) goto L_0x0025;
    L_0x0005:
        r0 = zzbZK;
    L_0x0007:
        r2 = r7.out;
        r3 = "\"";
        r2.write(r3);
        r4 = r8.length();
        r3 = r1;
    L_0x0013:
        if (r3 >= r4) goto L_0x0046;
    L_0x0015:
        r2 = r8.charAt(r3);
        r5 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        if (r2 >= r5) goto L_0x0028;
    L_0x001d:
        r2 = r0[r2];
        if (r2 != 0) goto L_0x002e;
    L_0x0021:
        r2 = r3 + 1;
        r3 = r2;
        goto L_0x0013;
    L_0x0025:
        r0 = zzbZJ;
        goto L_0x0007;
    L_0x0028:
        r5 = 8232; // 0x2028 float:1.1535E-41 double:4.067E-320;
        if (r2 != r5) goto L_0x003f;
    L_0x002c:
        r2 = "\\u2028";
    L_0x002e:
        if (r1 >= r3) goto L_0x0037;
    L_0x0030:
        r5 = r7.out;
        r6 = r3 - r1;
        r5.write(r8, r1, r6);
    L_0x0037:
        r1 = r7.out;
        r1.write(r2);
        r1 = r3 + 1;
        goto L_0x0021;
    L_0x003f:
        r5 = 8233; // 0x2029 float:1.1537E-41 double:4.0676E-320;
        if (r2 != r5) goto L_0x0021;
    L_0x0043:
        r2 = "\\u2029";
        goto L_0x002e;
    L_0x0046:
        if (r1 >= r4) goto L_0x004f;
    L_0x0048:
        r0 = r7.out;
        r2 = r4 - r1;
        r0.write(r8, r1, r2);
    L_0x004f:
        r0 = r7.out;
        r1 = "\"";
        r0.write(r1);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaly.zziX(java.lang.String):void");
    }

    private void zznQ(int i) {
        int[] iArr;
        if (this.zzbZv == this.zzbZu.length) {
            iArr = new int[(this.zzbZv * 2)];
            System.arraycopy(this.zzbZu, 0, iArr, 0, this.zzbZv);
            this.zzbZu = iArr;
        }
        iArr = this.zzbZu;
        int i2 = this.zzbZv;
        this.zzbZv = i2 + 1;
        iArr[i2] = i;
    }

    private void zznS(int i) {
        this.zzbZu[this.zzbZv - 1] = i;
    }

    private zzaly zzp(int i, String str) throws IOException {
        zzbb(true);
        zznQ(i);
        this.out.write(str);
        return this;
    }

    public void close() throws IOException {
        this.out.close();
        int i = this.zzbZv;
        if (i > 1 || (i == 1 && this.zzbZu[i - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.zzbZv = 0;
    }

    public void flush() throws IOException {
        if (this.zzbZv == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.out.flush();
    }

    public boolean isLenient() {
        return this.zzbZm;
    }

    public final void setLenient(boolean z) {
        this.zzbZm = z;
    }

    public zzaly zzWg() throws IOException {
        zzWz();
        return zzp(1, "[");
    }

    public zzaly zzWh() throws IOException {
        return zzc(1, 2, "]");
    }

    public zzaly zzWi() throws IOException {
        zzWz();
        return zzp(3, "{");
    }

    public zzaly zzWj() throws IOException {
        return zzc(3, 5, "}");
    }

    public zzaly zzWk() throws IOException {
        if (this.zzbZM != null) {
            if (this.zzbWb) {
                zzWz();
            } else {
                this.zzbZM = null;
                return this;
            }
        }
        zzbb(false);
        this.out.write(SafeJsonPrimitive.NULL_STRING);
        return this;
    }

    public final boolean zzWx() {
        return this.zzbWb;
    }

    public zzaly zza(Number number) throws IOException {
        if (number == null) {
            return zzWk();
        }
        zzWz();
        String obj = number.toString();
        if (this.zzbZm || !(obj.equals("-Infinity") || obj.equals("Infinity") || obj.equals("NaN"))) {
            zzbb(false);
            this.out.append(obj);
            return this;
        }
        String valueOf = String.valueOf(number);
        throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 39).append("Numeric values must be finite, but was ").append(valueOf).toString());
    }

    public zzaly zzaN(long j) throws IOException {
        zzWz();
        zzbb(false);
        this.out.write(Long.toString(j));
        return this;
    }

    public zzaly zzaX(boolean z) throws IOException {
        zzWz();
        zzbb(false);
        this.out.write(z ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false");
        return this;
    }

    public zzaly zziT(String str) throws IOException {
        if (str == null) {
            throw new NullPointerException("name == null");
        } else if (this.zzbZM != null) {
            throw new IllegalStateException();
        } else if (this.zzbZv == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        } else {
            this.zzbZM = str;
            return this;
        }
    }

    public zzaly zziU(String str) throws IOException {
        if (str == null) {
            return zzWk();
        }
        zzWz();
        zzbb(false);
        zziX(str);
        return this;
    }
}
