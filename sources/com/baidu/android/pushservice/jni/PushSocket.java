package com.baidu.android.pushservice.jni;

import android.content.Context;
import android.util.Log;
import com.baidu.android.pushservice.message.C1509i;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.util.C1578v;

public class PushSocket {
    /* renamed from: a */
    public static boolean f5158a;
    /* renamed from: b */
    private static byte[] f5159b = null;
    /* renamed from: c */
    private static int f5160c = 0;
    /* renamed from: d */
    private static String f5161d = "PushSocket";
    /* renamed from: e */
    private static int f5162e = 36;
    /* renamed from: f */
    private static int f5163f = 32;

    static {
        f5158a = false;
        try {
            System.loadLibrary("bdpush_V2_7");
            f5158a = true;
        } catch (UnsatisfiedLinkError e) {
            C1425a.m6444e(f5161d, "Native library not found! Please copy libbdpush_V2_7.so into your project!");
        }
    }

    /* renamed from: a */
    public static short m6670a(byte[] bArr, int i) {
        return (short) ((bArr[i + 1] << 8) | (bArr[i + 0] & 255));
    }

    /* renamed from: a */
    public static void m6671a(int i) {
        f5159b = null;
        f5160c = 0;
        closeSocket(i);
    }

    /* renamed from: a */
    public static boolean m6672a(Context context) {
        if (!f5158a) {
            try {
                System.loadLibrary("bdpush_V2_7");
                f5158a = true;
            } catch (UnsatisfiedLinkError e) {
                Log.e("BDPushSDK-" + f5161d, "Native library not found! Please copy libbdpush_V2_7.so into your project!");
            }
        }
        return f5158a;
    }

    /* renamed from: a */
    public static byte[] m6673a(Context context, int i) {
        short a;
        byte[] bArr;
        while (true) {
            if (f5159b != null) {
                int length = f5159b.length;
                if (length == f5160c) {
                    f5159b = null;
                    f5160c = 0;
                } else if (length - f5160c > 1) {
                    a = m6670a(f5159b, f5160c);
                    C1425a.m6441b(f5161d, "msgid:" + a);
                    if (a == C1509i.MSG_ID_TINY_HEARTBEAT_CLIENT.mo13994a() || a == C1509i.MSG_ID_TINY_HEARTBEAT_SERVER.mo13994a()) {
                        bArr = new byte[2];
                        System.arraycopy(f5159b, f5160c, bArr, 0, bArr.length);
                    } else if (length - f5160c < f5162e && !m6675b(i)) {
                        return null;
                    } else {
                        int b = m6674b(f5159b, f5160c + f5163f);
                        if ((f5160c + b) + f5162e <= length - f5160c) {
                            bArr = new byte[(f5162e + b)];
                            System.arraycopy(f5159b, f5160c, bArr, 0, bArr.length);
                            f5160c += b + f5162e;
                            return bArr;
                        } else if (!m6675b(i)) {
                            return null;
                        }
                    }
                } else if (!m6675b(i)) {
                    return null;
                }
            } else if (!m6675b(i)) {
                return null;
            }
        }
        bArr = new byte[2];
        System.arraycopy(f5159b, f5160c, bArr, 0, bArr.length);
        if (a == C1509i.MSG_ID_TINY_HEARTBEAT_SERVER.mo13994a()) {
            C1425a.m6441b(f5161d, "MSG_ID_TINY_HEARTBEAT_SERVER");
            C1578v.m7095b("MSG_ID_TINY_HEARTBEAT_SERVER", context);
        }
        f5160c += 2;
        return bArr;
    }

    /* renamed from: b */
    public static int m6674b(byte[] bArr, int i) {
        return ((((bArr[i + 3] & 255) << 24) | ((bArr[i + 2] & 255) << 16)) | ((bArr[i + 1] & 255) << 8)) | ((bArr[i + 0] & 255) << 0);
    }

    /* renamed from: b */
    private static boolean m6675b(int i) {
        byte[] rcvMsg = rcvMsg(i);
        if (rcvMsg == null || rcvMsg.length == 0) {
            return false;
        }
        if (f5159b == null) {
            f5159b = rcvMsg;
        } else {
            byte[] bArr = new byte[(f5159b.length + rcvMsg.length)];
            System.arraycopy(f5159b, f5160c, bArr, 0, f5159b.length - f5160c);
            System.arraycopy(rcvMsg, 0, bArr, f5159b.length, rcvMsg.length);
            f5159b = bArr;
        }
        return true;
    }

    public static native int closeSocket(int i);

    public static native int createSocket(String str, int i);

    public static native int getLastSocketError();

    private static native byte[] rcvMsg(int i);

    public static native int sendMsg(int i, byte[] bArr, int i2);
}
