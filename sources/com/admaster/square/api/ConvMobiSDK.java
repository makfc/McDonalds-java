package com.admaster.square.api;

import android.content.Context;
import android.content.Intent;
import com.admaster.square.utils.Order;

public class ConvMobiSDK {
    /* renamed from: a */
    private static volatile ConvMobiInstance f149a;

    private ConvMobiSDK() {
    }

    /* renamed from: a */
    public static synchronized ConvMobiInstance m274a() {
        ConvMobiInstance convMobiInstance;
        synchronized (ConvMobiSDK.class) {
            if (f149a == null) {
                f149a = ConvMobiInstance.m312a();
            }
            convMobiInstance = f149a;
        }
        return convMobiInstance;
    }

    public static void initial(Context context, String str, String str2) {
        m274a().mo7754a(context, str, str2);
    }

    public static void initial(Context context, String str) {
        m274a().mo7753a(context, str);
    }

    public static void initial(Context context, String str, String str2, boolean z) {
        m274a().mo7755a(context, str, str2, z);
    }

    public static void initial(Context context, String str, boolean z) {
        m274a().mo7756a(context, str, z);
    }

    public static void initial(Context context) {
        m274a().mo7765b(context);
    }

    public static void setDebugMode(boolean z) {
        m274a().mo7763a(z);
    }

    public static void doRegisterEvent(String str, long j) {
        m274a().mo7760a(str, j);
    }

    public static void doLoginEvent(String str, long j) {
        m274a().mo7766b(str, j);
    }

    public static void doCustomerEvent(CustomEvent customEvent, long j) {
        m274a().mo7758a(customEvent, j);
    }

    public static void placeOrder(String str, Order order) {
        m274a().mo7761a(str, order);
    }

    public static void orderPaySucc(String str, Order order) {
        m274a().mo7767b(str, order);
    }

    public static void onDestroy() {
        m274a().mo7764b();
    }

    public static void reportError(Throwable th) {
        m274a().mo7762a(th);
    }

    public static void setReferrer(String str) {
        m274a().mo7759a(str);
    }

    public static void setIntent(Intent intent) {
        m274a().mo7757a(intent);
    }
}
