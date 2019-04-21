package com.alipay.sdk.packet;

import com.alipay.sdk.cons.C0611a;
import com.alipay.sdk.encrypt.C0621c;
import com.alipay.sdk.encrypt.C0622d;
import com.alipay.sdk.encrypt.C0623e;
import com.alipay.sdk.util.C0657m;
import java.util.Locale;

/* renamed from: com.alipay.sdk.packet.c */
public final class C0629c {
    /* renamed from: a */
    private boolean f599a;
    /* renamed from: b */
    private String f600b = C0657m.m1052a(24);

    public C0629c(boolean z) {
        this.f599a = z;
    }

    /* renamed from: a */
    public C0630d mo8070a(C0628b c0628b, boolean z) {
        if (c0628b == null) {
            return null;
        }
        byte[] bytes = c0628b.mo8065a().getBytes();
        byte[] bytes2 = c0628b.mo8066b().getBytes();
        if (z) {
            try {
                bytes2 = C0621c.m902a(bytes2);
            } catch (Exception e) {
                z = false;
            }
        }
        if (this.f599a) {
            byte[] a = C0629c.m926a(this.f600b, C0611a.f567c);
            bytes2 = C0629c.m927a(this.f600b, bytes2);
            bytes2 = C0629c.m928a(bytes, a, bytes2);
        } else {
            bytes2 = C0629c.m928a(bytes, bytes2);
        }
        return new C0630d(z, bytes2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0065 A:{SYNTHETIC, Splitter:B:27:0x0065} */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x005a A:{SKIP} */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0072 A:{SYNTHETIC, Splitter:B:35:0x0072} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0065 A:{SYNTHETIC, Splitter:B:27:0x0065} */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x005a A:{SKIP} */
    /* renamed from: a */
    public com.alipay.sdk.packet.C0628b mo8069a(com.alipay.sdk.packet.C0630d r6) {
        /*
        r5 = this;
        r0 = 0;
        r2 = new java.io.ByteArrayInputStream;	 Catch:{ Exception -> 0x005d, all -> 0x006d }
        r1 = r6.mo8072b();	 Catch:{ Exception -> 0x005d, all -> 0x006d }
        r2.<init>(r1);	 Catch:{ Exception -> 0x005d, all -> 0x006d }
        r1 = 5;
        r1 = new byte[r1];	 Catch:{ Exception -> 0x0082 }
        r2.read(r1);	 Catch:{ Exception -> 0x0082 }
        r3 = new java.lang.String;	 Catch:{ Exception -> 0x0082 }
        r3.<init>(r1);	 Catch:{ Exception -> 0x0082 }
        r1 = com.alipay.sdk.packet.C0629c.m924a(r3);	 Catch:{ Exception -> 0x0082 }
        r1 = new byte[r1];	 Catch:{ Exception -> 0x0082 }
        r2.read(r1);	 Catch:{ Exception -> 0x0082 }
        r3 = new java.lang.String;	 Catch:{ Exception -> 0x0082 }
        r3.<init>(r1);	 Catch:{ Exception -> 0x0082 }
        r1 = 5;
        r1 = new byte[r1];	 Catch:{ Exception -> 0x0085 }
        r2.read(r1);	 Catch:{ Exception -> 0x0085 }
        r4 = new java.lang.String;	 Catch:{ Exception -> 0x0085 }
        r4.<init>(r1);	 Catch:{ Exception -> 0x0085 }
        r1 = com.alipay.sdk.packet.C0629c.m924a(r4);	 Catch:{ Exception -> 0x0085 }
        if (r1 <= 0) goto L_0x008b;
    L_0x0034:
        r1 = new byte[r1];	 Catch:{ Exception -> 0x0085 }
        r2.read(r1);	 Catch:{ Exception -> 0x0085 }
        r4 = r5.f599a;	 Catch:{ Exception -> 0x0085 }
        if (r4 == 0) goto L_0x0043;
    L_0x003d:
        r4 = r5.f600b;	 Catch:{ Exception -> 0x0085 }
        r1 = com.alipay.sdk.packet.C0629c.m929b(r4, r1);	 Catch:{ Exception -> 0x0085 }
    L_0x0043:
        r4 = r6.mo8071a();	 Catch:{ Exception -> 0x0085 }
        if (r4 == 0) goto L_0x0089;
    L_0x0049:
        r1 = com.alipay.sdk.encrypt.C0621c.m903b(r1);	 Catch:{ Exception -> 0x0085 }
        r4 = r1;
    L_0x004e:
        r1 = new java.lang.String;	 Catch:{ Exception -> 0x0085 }
        r1.<init>(r4);	 Catch:{ Exception -> 0x0085 }
    L_0x0053:
        if (r2 == 0) goto L_0x0058;
    L_0x0055:
        r2.close();	 Catch:{ Exception -> 0x007c }
    L_0x0058:
        if (r3 != 0) goto L_0x0076;
    L_0x005a:
        if (r1 != 0) goto L_0x0076;
    L_0x005c:
        return r0;
    L_0x005d:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
    L_0x0060:
        com.alipay.sdk.util.C0646c.m1016a(r1);	 Catch:{ all -> 0x0080 }
        if (r2 == 0) goto L_0x0087;
    L_0x0065:
        r2.close();	 Catch:{ Exception -> 0x006a }
        r1 = r0;
        goto L_0x0058;
    L_0x006a:
        r1 = move-exception;
        r1 = r0;
        goto L_0x0058;
    L_0x006d:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x0070:
        if (r2 == 0) goto L_0x0075;
    L_0x0072:
        r2.close();	 Catch:{ Exception -> 0x007e }
    L_0x0075:
        throw r0;
    L_0x0076:
        r0 = new com.alipay.sdk.packet.b;
        r0.<init>(r3, r1);
        goto L_0x005c;
    L_0x007c:
        r2 = move-exception;
        goto L_0x0058;
    L_0x007e:
        r1 = move-exception;
        goto L_0x0075;
    L_0x0080:
        r0 = move-exception;
        goto L_0x0070;
    L_0x0082:
        r1 = move-exception;
        r3 = r0;
        goto L_0x0060;
    L_0x0085:
        r1 = move-exception;
        goto L_0x0060;
    L_0x0087:
        r1 = r0;
        goto L_0x0058;
    L_0x0089:
        r4 = r1;
        goto L_0x004e;
    L_0x008b:
        r1 = r0;
        goto L_0x0053;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.packet.C0629c.mo8069a(com.alipay.sdk.packet.d):com.alipay.sdk.packet.b");
    }

    /* renamed from: a */
    private static byte[] m926a(String str, String str2) {
        return C0622d.m904a(str, str2);
    }

    /* renamed from: a */
    private static byte[] m927a(String str, byte[] bArr) {
        return C0623e.m907a(str, bArr);
    }

    /* renamed from: b */
    private static byte[] m929b(String str, byte[] bArr) {
        return C0623e.m909b(str, bArr);
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0045 A:{SYNTHETIC, Splitter:B:26:0x0045} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x004a A:{SYNTHETIC, Splitter:B:29:0x004a} */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0056 A:{SYNTHETIC, Splitter:B:35:0x0056} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x005b A:{SYNTHETIC, Splitter:B:38:0x005b} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0045 A:{SYNTHETIC, Splitter:B:26:0x0045} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x004a A:{SYNTHETIC, Splitter:B:29:0x004a} */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0056 A:{SYNTHETIC, Splitter:B:35:0x0056} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x005b A:{SYNTHETIC, Splitter:B:38:0x005b} */
    /* renamed from: a */
    private static byte[] m928a(byte[]... r7) {
        /*
        r0 = 0;
        if (r7 == 0) goto L_0x0006;
    L_0x0003:
        r1 = r7.length;
        if (r1 != 0) goto L_0x0007;
    L_0x0006:
        return r0;
    L_0x0007:
        r3 = new java.io.ByteArrayOutputStream;	 Catch:{ Exception -> 0x003d, all -> 0x0050 }
        r3.<init>();	 Catch:{ Exception -> 0x003d, all -> 0x0050 }
        r2 = new java.io.DataOutputStream;	 Catch:{ Exception -> 0x006d, all -> 0x0067 }
        r2.<init>(r3);	 Catch:{ Exception -> 0x006d, all -> 0x0067 }
        r4 = r7.length;	 Catch:{ Exception -> 0x0070 }
        r1 = 0;
    L_0x0013:
        if (r1 >= r4) goto L_0x0029;
    L_0x0015:
        r5 = r7[r1];	 Catch:{ Exception -> 0x0070 }
        r6 = r5.length;	 Catch:{ Exception -> 0x0070 }
        r6 = com.alipay.sdk.packet.C0629c.m925a(r6);	 Catch:{ Exception -> 0x0070 }
        r6 = r6.getBytes();	 Catch:{ Exception -> 0x0070 }
        r2.write(r6);	 Catch:{ Exception -> 0x0070 }
        r2.write(r5);	 Catch:{ Exception -> 0x0070 }
        r1 = r1 + 1;
        goto L_0x0013;
    L_0x0029:
        r2.flush();	 Catch:{ Exception -> 0x0070 }
        r0 = r3.toByteArray();	 Catch:{ Exception -> 0x0070 }
        if (r3 == 0) goto L_0x0035;
    L_0x0032:
        r3.close();	 Catch:{ Exception -> 0x005f }
    L_0x0035:
        if (r2 == 0) goto L_0x0006;
    L_0x0037:
        r2.close();	 Catch:{ Exception -> 0x003b }
        goto L_0x0006;
    L_0x003b:
        r1 = move-exception;
        goto L_0x0006;
    L_0x003d:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
    L_0x0040:
        com.alipay.sdk.util.C0646c.m1016a(r1);	 Catch:{ all -> 0x006b }
        if (r3 == 0) goto L_0x0048;
    L_0x0045:
        r3.close();	 Catch:{ Exception -> 0x0061 }
    L_0x0048:
        if (r2 == 0) goto L_0x0006;
    L_0x004a:
        r2.close();	 Catch:{ Exception -> 0x004e }
        goto L_0x0006;
    L_0x004e:
        r1 = move-exception;
        goto L_0x0006;
    L_0x0050:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r0 = r1;
    L_0x0054:
        if (r3 == 0) goto L_0x0059;
    L_0x0056:
        r3.close();	 Catch:{ Exception -> 0x0063 }
    L_0x0059:
        if (r2 == 0) goto L_0x005e;
    L_0x005b:
        r2.close();	 Catch:{ Exception -> 0x0065 }
    L_0x005e:
        throw r0;
    L_0x005f:
        r1 = move-exception;
        goto L_0x0035;
    L_0x0061:
        r1 = move-exception;
        goto L_0x0048;
    L_0x0063:
        r1 = move-exception;
        goto L_0x0059;
    L_0x0065:
        r1 = move-exception;
        goto L_0x005e;
    L_0x0067:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x0054;
    L_0x006b:
        r0 = move-exception;
        goto L_0x0054;
    L_0x006d:
        r1 = move-exception;
        r2 = r0;
        goto L_0x0040;
    L_0x0070:
        r1 = move-exception;
        goto L_0x0040;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.packet.C0629c.m928a(byte[][]):byte[]");
    }

    /* renamed from: a */
    private static String m925a(int i) {
        return String.format(Locale.getDefault(), "%05d", new Object[]{Integer.valueOf(i)});
    }

    /* renamed from: a */
    private static int m924a(String str) {
        return Integer.parseInt(str);
    }
}
