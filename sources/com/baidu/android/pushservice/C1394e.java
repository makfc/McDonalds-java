package com.baidu.android.pushservice;

import android.app.Notification;
import android.content.Context;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p039k.C1465b;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;

/* renamed from: com.baidu.android.pushservice.e */
public class C1394e {
    /* renamed from: a */
    private static String f4890a = "NotificationBuilderManager";
    /* renamed from: b */
    private static String f4891b = "notification_builder_storage";
    /* renamed from: c */
    private static Object f4892c = new Object();
    /* renamed from: d */
    private static int f4893d = 0;

    /* renamed from: a */
    public static Notification m6248a(Context context, int i, int i2, String str, String str2, boolean z) {
        Notification construct;
        synchronized (f4892c) {
            PushNotificationBuilder a = C1394e.m6251a(context, i);
            a.setNotificationTitle(str);
            a.setNotificationText(str2);
            construct = a.construct(context);
            if ((i2 & 1) != 0) {
                construct.flags &= -33;
            } else {
                construct.flags |= 32;
            }
            if (z) {
                construct.defaults = 0;
            } else {
                construct.defaults = -1;
                if ((i2 & 4) != 0) {
                    construct.defaults |= 1;
                } else {
                    construct.defaults &= -2;
                }
                if ((i2 & 2) != 0) {
                    construct.defaults |= 2;
                } else {
                    construct.defaults &= -3;
                }
            }
        }
        return construct;
    }

    /* renamed from: a */
    public static Notification m6249a(Context context, int i, String str, String str2, boolean z) {
        Notification construct;
        synchronized (f4892c) {
            PushNotificationBuilder a = C1394e.m6251a(context, i);
            a.setNotificationTitle(str);
            a.setNotificationText(str2);
            construct = a.construct(context);
            if (z) {
                construct.defaults = -1;
            } else {
                construct.defaults = 0;
            }
        }
        return construct;
    }

    /* renamed from: a */
    private static PushNotificationBuilder m6250a(Context context) {
        BasicPushNotificationBuilder basicPushNotificationBuilder = new BasicPushNotificationBuilder();
        basicPushNotificationBuilder.setNotificationFlags(16);
        basicPushNotificationBuilder.setNotificationDefaults(3);
        basicPushNotificationBuilder.setStatusbarIcon(context.getApplicationInfo().icon);
        return basicPushNotificationBuilder;
    }

    /* renamed from: a */
    private static PushNotificationBuilder m6251a(Context context, int i) {
        StreamCorruptedException e;
        IOException e2;
        ClassNotFoundException e3;
        C1425a.m6442c(f4890a, "getBuilder id=" + i);
        String string = context.getSharedPreferences(f4891b, 0).getString("" + i, null);
        if (string == null) {
            return C1394e.m6252b(context);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(C1465b.m6679a(string.getBytes()));
        PushNotificationBuilder pushNotificationBuilder;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            pushNotificationBuilder = (PushNotificationBuilder) objectInputStream.readObject();
            try {
                objectInputStream.close();
                byteArrayInputStream.close();
                return pushNotificationBuilder;
            } catch (StreamCorruptedException e4) {
                e = e4;
            } catch (IOException e5) {
                e2 = e5;
                C1425a.m6444e(f4890a, "getBuilder read object error");
                C1425a.m6444e(f4890a, "error " + e2.getMessage());
                return pushNotificationBuilder;
            } catch (ClassNotFoundException e6) {
                e3 = e6;
                C1425a.m6444e(f4890a, "getBuilder read object error: class not found");
                C1425a.m6444e(f4890a, "error " + e3.getMessage());
                return pushNotificationBuilder;
            }
        } catch (StreamCorruptedException e7) {
            StreamCorruptedException streamCorruptedException = e7;
            pushNotificationBuilder = null;
            e = streamCorruptedException;
            C1425a.m6444e(f4890a, "getBuilder read object error");
            C1425a.m6444e(f4890a, "error " + e.getMessage());
            return pushNotificationBuilder;
        } catch (IOException e8) {
            IOException iOException = e8;
            pushNotificationBuilder = null;
            e2 = iOException;
            C1425a.m6444e(f4890a, "getBuilder read object error");
            C1425a.m6444e(f4890a, "error " + e2.getMessage());
            return pushNotificationBuilder;
        } catch (ClassNotFoundException e9) {
            ClassNotFoundException classNotFoundException = e9;
            pushNotificationBuilder = null;
            e3 = classNotFoundException;
            C1425a.m6444e(f4890a, "getBuilder read object error: class not found");
            C1425a.m6444e(f4890a, "error " + e3.getMessage());
            return pushNotificationBuilder;
        }
    }

    /* renamed from: b */
    private static PushNotificationBuilder m6252b(Context context) {
        StreamCorruptedException e;
        IOException e2;
        ClassNotFoundException e3;
        String string = context.getSharedPreferences(f4891b, 0).getString("" + f4893d, null);
        if (string == null) {
            return C1394e.m6250a(context);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(C1465b.m6679a(string.getBytes()));
        PushNotificationBuilder pushNotificationBuilder;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            pushNotificationBuilder = (PushNotificationBuilder) objectInputStream.readObject();
            try {
                objectInputStream.close();
                byteArrayInputStream.close();
                return pushNotificationBuilder;
            } catch (StreamCorruptedException e4) {
                e = e4;
            } catch (IOException e5) {
                e2 = e5;
                C1425a.m6444e(f4890a, "getDefaultBuilder read object error");
                C1425a.m6444e(f4890a, "error " + e2.getMessage());
                return pushNotificationBuilder;
            } catch (ClassNotFoundException e6) {
                e3 = e6;
                C1425a.m6444e(f4890a, "getDefaultBuilder read object error: class not found");
                C1425a.m6444e(f4890a, "error " + e3.getMessage());
                return pushNotificationBuilder;
            }
        } catch (StreamCorruptedException e7) {
            StreamCorruptedException streamCorruptedException = e7;
            pushNotificationBuilder = null;
            e = streamCorruptedException;
            C1425a.m6444e(f4890a, "getDefaultBuilder read object error");
            C1425a.m6444e(f4890a, "error " + e.getMessage());
            return pushNotificationBuilder;
        } catch (IOException e8) {
            IOException iOException = e8;
            pushNotificationBuilder = null;
            e2 = iOException;
            C1425a.m6444e(f4890a, "getDefaultBuilder read object error");
            C1425a.m6444e(f4890a, "error " + e2.getMessage());
            return pushNotificationBuilder;
        } catch (ClassNotFoundException e9) {
            ClassNotFoundException classNotFoundException = e9;
            pushNotificationBuilder = null;
            e3 = classNotFoundException;
            C1425a.m6444e(f4890a, "getDefaultBuilder read object error: class not found");
            C1425a.m6444e(f4890a, "error " + e3.getMessage());
            return pushNotificationBuilder;
        }
    }
}
