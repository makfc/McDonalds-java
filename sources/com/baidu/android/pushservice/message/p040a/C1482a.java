package com.baidu.android.pushservice.message.p040a;

import android.content.ComponentName;
import android.content.Context;
import com.baidu.android.pushservice.C1328a;
import com.baidu.android.pushservice.message.C1508h;
import com.baidu.android.pushservice.message.C1512k;
import com.baidu.android.pushservice.message.PublicMsg;
import com.baidu.android.pushservice.p031c.C1334d;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.util.C1578v;

/* renamed from: com.baidu.android.pushservice.message.a.a */
public class C1482a extends C1481d {
    /* renamed from: b */
    private static final String f5199b = C1482a.class.getSimpleName();

    /* renamed from: com.baidu.android.pushservice.message.a.a$a */
    enum C1480a {
        PushADInfoId,
        PushADSwitch,
        PushADMaxCount,
        PushADServerMaxCount,
        PushADCurCount,
        PushADCurTimeStamp
    }

    public C1482a(Context context) {
        super(context);
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x01a2 A:{SYNTHETIC, Splitter:B:36:0x01a2} */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x01a2 A:{SYNTHETIC, Splitter:B:36:0x01a2} */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x01c8 A:{SYNTHETIC, Splitter:B:43:0x01c8} */
    /* renamed from: a */
    private int m6734a(java.lang.String r14) {
        /*
        r13 = this;
        r8 = 2;
        r7 = 0;
        r6 = 0;
        r0 = r13.f5198a;	 Catch:{ Exception -> 0x0180, all -> 0x01c4 }
        r0 = r0.getContentResolver();	 Catch:{ Exception -> 0x0180, all -> 0x01c4 }
        if (r0 == 0) goto L_0x01fe;
    L_0x000b:
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0180, all -> 0x01c4 }
        r1.<init>();	 Catch:{ Exception -> 0x0180, all -> 0x01c4 }
        r2 = "content://";
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x0180, all -> 0x01c4 }
        r1 = r1.append(r14);	 Catch:{ Exception -> 0x0180, all -> 0x01c4 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0180, all -> 0x01c4 }
        r1 = android.net.Uri.parse(r1);	 Catch:{ Exception -> 0x0180, all -> 0x01c4 }
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r2 = r0.query(r1, r2, r3, r4, r5);	 Catch:{ Exception -> 0x0180, all -> 0x01c4 }
        r0 = f5199b;	 Catch:{ Exception -> 0x01ec }
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01ec }
        r1.<init>();	 Catch:{ Exception -> 0x01ec }
        r3 = "cursor  is ";
        r1 = r1.append(r3);	 Catch:{ Exception -> 0x01ec }
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x01ec }
        r1 = r1.toString();	 Catch:{ Exception -> 0x01ec }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r0, r1);	 Catch:{ Exception -> 0x01ec }
        if (r2 == 0) goto L_0x01fb;
    L_0x0044:
        r0 = r2.moveToFirst();	 Catch:{ Exception -> 0x01ec }
        if (r0 == 0) goto L_0x01fb;
    L_0x004a:
        r0 = com.baidu.android.pushservice.message.p040a.C1482a.C1480a.PushADSwitch;	 Catch:{ Exception -> 0x01ec }
        r0 = r0.name();	 Catch:{ Exception -> 0x01ec }
        r0 = r2.getColumnIndex(r0);	 Catch:{ Exception -> 0x01ec }
        r0 = r2.getInt(r0);	 Catch:{ Exception -> 0x01ec }
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01ec }
        r1.<init>();	 Catch:{ Exception -> 0x01ec }
        r3 = "pushadswitch  is ";
        r1 = r1.append(r3);	 Catch:{ Exception -> 0x01ec }
        r1 = r1.append(r0);	 Catch:{ Exception -> 0x01ec }
        r1 = r1.toString();	 Catch:{ Exception -> 0x01ec }
        r3 = r13.f5198a;	 Catch:{ Exception -> 0x01ec }
        com.baidu.android.pushservice.util.C1578v.m7095b(r1, r3);	 Catch:{ Exception -> 0x01ec }
        r1 = f5199b;	 Catch:{ Exception -> 0x01ec }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01ec }
        r3.<init>();	 Catch:{ Exception -> 0x01ec }
        r4 = "pushadswitch  is ";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x01ec }
        r3 = r3.append(r0);	 Catch:{ Exception -> 0x01ec }
        r3 = r3.toString();	 Catch:{ Exception -> 0x01ec }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r1, r3);	 Catch:{ Exception -> 0x01ec }
        r1 = 1;
        if (r0 != r1) goto L_0x01f8;
    L_0x008b:
        r1 = r8;
    L_0x008c:
        if (r1 == r8) goto L_0x01f5;
    L_0x008e:
        r0 = com.baidu.android.pushservice.message.p040a.C1482a.C1480a.PushADMaxCount;	 Catch:{ Exception -> 0x01f0 }
        r0 = r0.name();	 Catch:{ Exception -> 0x01f0 }
        r0 = r2.getColumnIndex(r0);	 Catch:{ Exception -> 0x01f0 }
        r0 = r2.getInt(r0);	 Catch:{ Exception -> 0x01f0 }
        r3 = com.baidu.android.pushservice.message.p040a.C1482a.C1480a.PushADServerMaxCount;	 Catch:{ Exception -> 0x01f0 }
        r3 = r3.name();	 Catch:{ Exception -> 0x01f0 }
        r3 = r2.getColumnIndex(r3);	 Catch:{ Exception -> 0x01f0 }
        r3 = r2.getInt(r3);	 Catch:{ Exception -> 0x01f0 }
        r4 = com.baidu.android.pushservice.message.p040a.C1482a.C1480a.PushADCurCount;	 Catch:{ Exception -> 0x01f0 }
        r4 = r4.name();	 Catch:{ Exception -> 0x01f0 }
        r4 = r2.getColumnIndex(r4);	 Catch:{ Exception -> 0x01f0 }
        r4 = r2.getInt(r4);	 Catch:{ Exception -> 0x01f0 }
        r5 = com.baidu.android.pushservice.message.p040a.C1482a.C1480a.PushADCurTimeStamp;	 Catch:{ Exception -> 0x01f0 }
        r5 = r5.name();	 Catch:{ Exception -> 0x01f0 }
        r5 = r2.getColumnIndex(r5);	 Catch:{ Exception -> 0x01f0 }
        r6 = r2.getLong(r5);	 Catch:{ Exception -> 0x01f0 }
        r8 = com.baidu.android.pushservice.util.C1578v.m7101c();	 Catch:{ Exception -> 0x01f0 }
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01f0 }
        r5.<init>();	 Catch:{ Exception -> 0x01f0 }
        r10 = "pushcurcount = ";
        r5 = r5.append(r10);	 Catch:{ Exception -> 0x01f0 }
        r5 = r5.append(r4);	 Catch:{ Exception -> 0x01f0 }
        r10 = "  pushadmaxcount = ";
        r5 = r5.append(r10);	 Catch:{ Exception -> 0x01f0 }
        r5 = r5.append(r0);	 Catch:{ Exception -> 0x01f0 }
        r10 = "  pushadtimestamp";
        r5 = r5.append(r10);	 Catch:{ Exception -> 0x01f0 }
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x01f0 }
        r10 = "  todaystamp ";
        r5 = r5.append(r10);	 Catch:{ Exception -> 0x01f0 }
        r5 = r5.append(r8);	 Catch:{ Exception -> 0x01f0 }
        r5 = r5.toString();	 Catch:{ Exception -> 0x01f0 }
        r10 = r13.f5198a;	 Catch:{ Exception -> 0x01f0 }
        com.baidu.android.pushservice.util.C1578v.m7095b(r5, r10);	 Catch:{ Exception -> 0x01f0 }
        r5 = f5199b;	 Catch:{ Exception -> 0x01f0 }
        r10 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01f0 }
        r10.<init>();	 Catch:{ Exception -> 0x01f0 }
        r11 = "pushcurcount = ";
        r10 = r10.append(r11);	 Catch:{ Exception -> 0x01f0 }
        r10 = r10.append(r4);	 Catch:{ Exception -> 0x01f0 }
        r11 = "  pushadmaxcount = ";
        r10 = r10.append(r11);	 Catch:{ Exception -> 0x01f0 }
        r10 = r10.append(r0);	 Catch:{ Exception -> 0x01f0 }
        r11 = "  pushadtimestamp";
        r10 = r10.append(r11);	 Catch:{ Exception -> 0x01f0 }
        r10 = r10.append(r6);	 Catch:{ Exception -> 0x01f0 }
        r11 = "  todaystamp ";
        r10 = r10.append(r11);	 Catch:{ Exception -> 0x01f0 }
        r10 = r10.append(r8);	 Catch:{ Exception -> 0x01f0 }
        r10 = r10.toString();	 Catch:{ Exception -> 0x01f0 }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r5, r10);	 Catch:{ Exception -> 0x01f0 }
        r5 = r4 + 1;
        if (r5 > r0) goto L_0x013e;
    L_0x013a:
        r0 = r4 + 1;
        if (r0 <= r3) goto L_0x01f5;
    L_0x013e:
        r0 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1));
        if (r0 != 0) goto L_0x01f5;
    L_0x0142:
        r1 = 3;
        r0 = r1;
    L_0x0144:
        if (r2 == 0) goto L_0x0149;
    L_0x0146:
        r2.close();	 Catch:{ Exception -> 0x0162 }
    L_0x0149:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "getAdvertiseLimit ret  is ";
        r1 = r1.append(r2);
        r1 = r1.append(r0);
        r1 = r1.toString();
        r2 = r13.f5198a;
        com.baidu.android.pushservice.util.C1578v.m7095b(r1, r2);
        return r0;
    L_0x0162:
        r1 = move-exception;
        r2 = f5199b;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "error cursor close  ";
        r3 = r3.append(r4);
        r1 = r1.getMessage();
        r1 = r3.append(r1);
        r1 = r1.toString();
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r2, r1);
        goto L_0x0149;
    L_0x0180:
        r0 = move-exception;
        r1 = r0;
        r2 = r7;
        r0 = r6;
    L_0x0184:
        r3 = f5199b;	 Catch:{ all -> 0x01ea }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01ea }
        r4.<init>();	 Catch:{ all -> 0x01ea }
        r5 = "error ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x01ea }
        r1 = r1.getMessage();	 Catch:{ all -> 0x01ea }
        r1 = r4.append(r1);	 Catch:{ all -> 0x01ea }
        r1 = r1.toString();	 Catch:{ all -> 0x01ea }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r3, r1);	 Catch:{ all -> 0x01ea }
        if (r2 == 0) goto L_0x0149;
    L_0x01a2:
        r2.close();	 Catch:{ Exception -> 0x01a6 }
        goto L_0x0149;
    L_0x01a6:
        r1 = move-exception;
        r2 = f5199b;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "error cursor close  ";
        r3 = r3.append(r4);
        r1 = r1.getMessage();
        r1 = r3.append(r1);
        r1 = r1.toString();
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r2, r1);
        goto L_0x0149;
    L_0x01c4:
        r0 = move-exception;
        r2 = r7;
    L_0x01c6:
        if (r2 == 0) goto L_0x01cb;
    L_0x01c8:
        r2.close();	 Catch:{ Exception -> 0x01cc }
    L_0x01cb:
        throw r0;
    L_0x01cc:
        r1 = move-exception;
        r2 = f5199b;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "error cursor close  ";
        r3 = r3.append(r4);
        r1 = r1.getMessage();
        r1 = r3.append(r1);
        r1 = r1.toString();
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r2, r1);
        goto L_0x01cb;
    L_0x01ea:
        r0 = move-exception;
        goto L_0x01c6;
    L_0x01ec:
        r0 = move-exception;
        r1 = r0;
        r0 = r6;
        goto L_0x0184;
    L_0x01f0:
        r0 = move-exception;
        r12 = r0;
        r0 = r1;
        r1 = r12;
        goto L_0x0184;
    L_0x01f5:
        r0 = r1;
        goto L_0x0144;
    L_0x01f8:
        r1 = r6;
        goto L_0x008c;
    L_0x01fb:
        r0 = r6;
        goto L_0x0144;
    L_0x01fe:
        r0 = r6;
        r2 = r7;
        goto L_0x0144;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.message.p040a.C1482a.m6734a(java.lang.String):int");
    }

    /* renamed from: a */
    public C1508h mo13966a(C1512k c1512k, byte[] bArr) {
        return null;
    }

    /* renamed from: a */
    public C1508h mo13967a(String str, String str2, int i, byte[] bArr, byte[] bArr2) {
        int i2;
        PublicMsg a = C1495k.m6776a(this.f5198a, str2, str, bArr2);
        if (a != null) {
            String str3;
            String str4;
            if (C1328a.m6006b() > 0) {
                str3 = str2;
                str4 = str;
                a.handleADShowNotification(this.f5198a, str3, str4, "01", String.valueOf(a.mAdvertiseStyle));
            }
            C1334d a2 = C1334d.m6039a(this.f5198a, str);
            switch (a2.mo13603a()) {
                case PUSH_CLIENT:
                    a.mPkgName = a2.f4730a.mo13589c();
                    int a3 = m6734a(a.mPkgName);
                    if (a3 == 2 || a3 == 3) {
                        if (a3 == 2) {
                            str3 = str2;
                            str4 = str;
                            a.handleADShowNotification(this.f5198a, str3, str4, "02", String.valueOf(a.mAdvertiseStyle));
                        } else if (a3 == 3) {
                            str3 = str2;
                            str4 = str;
                            a.handleADShowNotification(this.f5198a, str3, str4, "03", String.valueOf(a.mAdvertiseStyle));
                        }
                        i2 = ((((byte) (a3 & 255)) & 255) << 16) & 16711680;
                        C1425a.m6442c(f5199b, ">>> Not Show pMsg AD Notification!  errorcode = " + i2);
                        C1578v.m7095b(">>>Not Show pMsg AD Notification! errorcode = " + i2, this.f5198a);
                    } else {
                        ComponentName a4 = C1489g.m6754a(this.f5198a, a, str2, str);
                        if (a4 == null) {
                            str3 = str2;
                            str4 = str;
                            a.handleADShowNotification(this.f5198a, str3, str4, "04", String.valueOf(a.mAdvertiseStyle));
                            i2 = 65536;
                        } else {
                            C1425a.m6442c(f5199b, "componentName = " + a4);
                            if (C1328a.m6006b() > 0) {
                                str3 = str2;
                                str4 = str;
                                a.handleADShowNotification(this.f5198a, str3, str4, "05", String.valueOf(a.mAdvertiseStyle));
                            }
                            i2 = 1;
                        }
                        C1578v.m7095b("showAdvertiseNotification returnResult = " + i2, this.f5198a);
                        C1425a.m6442c(f5199b, ">>> Show pMsg AD Notification!");
                    }
                    C1425a.m6442c(f5199b, "returnresult(pushclient)  =  " + i2);
                    break;
                default:
                    C1425a.m6442c(f5199b, ">>> no push client receive advertise message");
                    str3 = str2;
                    str4 = str;
                    a.handleADShowNotification(this.f5198a, str3, str4, "06", String.valueOf(a.mAdvertiseStyle));
                    C1425a.m6442c(f5199b, ">>> Not Show pMsg AD Notification!  errorcode = " + 65536);
                    C1578v.m7095b(">>>Not Show pMsg AD Notification! errorcode = " + 65536, this.f5198a);
                    i2 = 65536;
                    break;
            }
        }
        i2 = 0;
        C1578v.m7095b("AdvertiseHandler handleMessage returnResult = " + i2, this.f5198a);
        C1508h c1508h = new C1508h();
        c1508h.mo13991a(i2);
        return c1508h;
    }
}
