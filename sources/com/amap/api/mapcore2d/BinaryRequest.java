package com.amap.api.mapcore2d;

import android.content.Context;
import com.mcdonalds.sdk.connectors.autonavi.AutoNavi.Parameters;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.amap.api.mapcore2d.eb */
public abstract class BinaryRequest extends C0931eg {
    /* renamed from: a */
    protected Context f2727a;
    /* renamed from: b */
    protected C0977cv f2728b;

    /* renamed from: a */
    public abstract byte[] mo10156a();

    /* renamed from: b */
    public abstract byte[] mo10158b();

    public BinaryRequest(Context context, C0977cv c0977cv) {
        if (context != null) {
            this.f2727a = context.getApplicationContext();
        }
        this.f2728b = c0977cv;
    }

    /* renamed from: f */
    public Map<String, String> mo10072f() {
        String f = C0957cm.m3906f(this.f2727a);
        String a = C0966cp.m3935a();
        String a2 = C0966cp.m3940a(this.f2727a, a, "key=" + f);
        HashMap hashMap = new HashMap();
        hashMap.put("ts", a);
        hashMap.put(Parameters.API_KEY, f);
        hashMap.put("scode", a2);
        return hashMap;
    }

    /* renamed from: a_ */
    public final byte[] mo10069a_() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(m3908j());
            byteArrayOutputStream.write(mo10160d());
            byteArrayOutputStream.write(m3909k());
            byteArrayOutputStream.write(m3910l());
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
                return toByteArray;
            } catch (Throwable th) {
                C0982da.m4076a(th, "BinaryRequest", "getEntityBytes");
                return toByteArray;
            }
        } catch (Throwable th2) {
            C0982da.m4076a(th2, "BinaryRequest", "getEntityBytes");
        }
        return null;
    }

    /* renamed from: j */
    private byte[] m3908j() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(C0978cw.m4050a("PANDORA$"));
            byteArrayOutputStream.write(new byte[]{(byte) 1});
            byteArrayOutputStream.write(new byte[]{(byte) 0});
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
                return toByteArray;
            } catch (Throwable th) {
                C0982da.m4076a(th, "BinaryRequest", "getBinaryHead");
                return toByteArray;
            }
        } catch (Throwable th2) {
            C0982da.m4076a(th2, "BinaryRequest", "getBinaryHead");
        }
        return null;
    }

    /* renamed from: d */
    public byte[] mo10160d() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] a = C0966cp.m3943a(this.f2727a, false);
            byte[] a2 = mo10157a(a);
            byteArrayOutputStream.write(new byte[]{(byte) 3});
            byteArrayOutputStream.write(a2);
            byteArrayOutputStream.write(a);
            a = C0978cw.m4050a(mo10159c());
            if (a == null || a.length <= 0) {
                byteArrayOutputStream.write(new byte[]{(byte) 0, (byte) 0});
            } else {
                byteArrayOutputStream.write(mo10157a(a));
                byteArrayOutputStream.write(a);
            }
            a = C0978cw.m4050a(String.format("platform=Android&sdkversion=%s&product=%s", new Object[]{this.f2728b.mo10174b(), this.f2728b.mo10172a()}));
            if (a == null || a.length <= 0) {
                byteArrayOutputStream.write(new byte[]{(byte) 0, (byte) 0});
            } else {
                byteArrayOutputStream.write(mo10157a(a));
                byteArrayOutputStream.write(a);
            }
            a = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
                return a;
            } catch (Throwable th) {
                C0982da.m4076a(th, "BinaryRequest", "getRequestEncryptData");
                return a;
            }
        } catch (Throwable th2) {
            C0982da.m4076a(th2, "BinaryRequest", "getRequestEncryptData");
        }
        return new byte[]{(byte) 0};
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: c */
    public String mo10159c() {
        return "2.1";
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public byte[] mo10157a(byte[] bArr) {
        byte length = (byte) (bArr.length % 256);
        return new byte[]{(byte) (bArr.length / 256), length};
    }

    /* renamed from: k */
    private byte[] m3909k() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] a = mo10156a();
            if (a == null || a.length == 0) {
                byteArrayOutputStream.write(new byte[]{(byte) 0});
                a = byteArrayOutputStream.toByteArray();
                try {
                    byteArrayOutputStream.close();
                    return a;
                } catch (Throwable th) {
                    C0982da.m4076a(th, "BinaryRequest", "getRequestRawData");
                    return a;
                }
            }
            byteArrayOutputStream.write(new byte[]{(byte) 1});
            byteArrayOutputStream.write(mo10157a(a));
            byteArrayOutputStream.write(a);
            a = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
                return a;
            } catch (Throwable th2) {
                C0982da.m4076a(th2, "BinaryRequest", "getRequestRawData");
                return a;
            }
        } catch (Throwable th3) {
            C0982da.m4076a(th3, "BinaryRequest", "getRequestRawData");
        }
        return new byte[]{(byte) 0};
    }

    /* renamed from: l */
    private byte[] m3910l() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] b = mo10158b();
            if (b == null || b.length == 0) {
                byteArrayOutputStream.write(new byte[]{(byte) 0});
                b = byteArrayOutputStream.toByteArray();
                try {
                    byteArrayOutputStream.close();
                    return b;
                } catch (Throwable th) {
                    C0982da.m4076a(th, "BinaryRequest", "getRequestEncryptData");
                    return b;
                }
            }
            byteArrayOutputStream.write(new byte[]{(byte) 1});
            b = C0966cp.m3944a(this.f2727a, b);
            byteArrayOutputStream.write(mo10157a(b));
            byteArrayOutputStream.write(b);
            b = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
                return b;
            } catch (Throwable th2) {
                C0982da.m4076a(th2, "BinaryRequest", "getRequestEncryptData");
                return b;
            }
        } catch (Throwable th3) {
            C0982da.m4076a(th3, "BinaryRequest", "getRequestEncryptData");
        }
        return new byte[]{(byte) 0};
    }
}
