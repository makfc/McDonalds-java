package com.kochava.base;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.UiModeManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Point;
import android.hardware.input.InputManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.support.annotation.AnyThread;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.annotation.Size;
import android.support.annotation.UiThread;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import android.support.p000v4.view.InputDeviceCompat;
import android.support.p000v4.view.accessibility.AccessibilityEventCompat;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Patterns;
import android.view.Display;
import android.view.InputDevice;
import android.view.WindowManager;
import com.amap.api.location.LocationManagerProxy;
import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import com.facebook.android.Facebook;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi.GetConnectedNodesResult;
import com.google.android.gms.wearable.Wearable;
import com.google.android.instantapps.InstantApps;
import com.mcdonalds.sdk.modules.models.RecipeComponent;
import com.mcdonalds.sdk.modules.notification.PushConstants;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.api.common.CarrierType;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.Contract;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.kochava.base.c */
final class C2900c {
    @NonNull
    /* renamed from: a */
    static final C2900c[] f6581a;
    @VisibleForTesting
    @NonNull
    /* renamed from: b */
    final String f6582b;
    @IntRange
    /* renamed from: c */
    private final int f6583c;
    @Nullable
    /* renamed from: d */
    private final int[] f6584d;
    @Nullable
    /* renamed from: e */
    private final int[] f6585e;

    @AnyThread
    /* renamed from: com.kochava.base.c$a */
    static final class C2897a implements Runnable {
        @NonNull
        /* renamed from: a */
        final CountDownLatch f6574a = new CountDownLatch(1);
        @NonNull
        /* renamed from: b */
        final JSONObject f6575b = new JSONObject();
        @NonNull
        /* renamed from: c */
        private final Context f6576c;

        C2897a(@NonNull Context context) {
            this.f6576c = context;
        }

        @org.jetbrains.annotations.Contract(pure = true)
        /* renamed from: a */
        static int m7550a(@android.support.annotation.NonNull java.lang.String r6) {
            /*
            r3 = 2;
            r2 = 1;
            r1 = 0;
            r0 = -1;
            r4 = 6;
            r5 = r6.hashCode();
            switch(r5) {
                case -693070394: goto L_0x0026;
                case -319671111: goto L_0x0030;
                case -236196924: goto L_0x004e;
                case -63726253: goto L_0x003a;
                case 3548: goto L_0x001c;
                case 40661574: goto L_0x0044;
                case 920378310: goto L_0x0058;
                case 2057740165: goto L_0x0012;
                default: goto L_0x000c;
            };
        L_0x000c:
            r5 = r0;
        L_0x000d:
            switch(r5) {
                case 0: goto L_0x0011;
                case 1: goto L_0x0062;
                case 2: goto L_0x0064;
                case 3: goto L_0x0066;
                case 4: goto L_0x0068;
                case 5: goto L_0x006a;
                case 6: goto L_0x006c;
                case 7: goto L_0x006e;
                default: goto L_0x0010;
            };
        L_0x0010:
            r0 = r4;
        L_0x0011:
            return r0;
        L_0x0012:
            r5 = "service_disconnected";
            r5 = r6.equals(r5);
            if (r5 == 0) goto L_0x000c;
        L_0x001a:
            r5 = r1;
            goto L_0x000d;
        L_0x001c:
            r5 = "ok";
            r5 = r6.equals(r5);
            if (r5 == 0) goto L_0x000c;
        L_0x0024:
            r5 = r2;
            goto L_0x000d;
        L_0x0026:
            r5 = "service_unavailable";
            r5 = r6.equals(r5);
            if (r5 == 0) goto L_0x000c;
        L_0x002e:
            r5 = r3;
            goto L_0x000d;
        L_0x0030:
            r5 = "feature_not_supported";
            r5 = r6.equals(r5);
            if (r5 == 0) goto L_0x000c;
        L_0x0038:
            r5 = 3;
            goto L_0x000d;
        L_0x003a:
            r5 = "developer_error";
            r5 = r6.equals(r5);
            if (r5 == 0) goto L_0x000c;
        L_0x0042:
            r5 = 4;
            goto L_0x000d;
        L_0x0044:
            r5 = "timed_out";
            r5 = r6.equals(r5);
            if (r5 == 0) goto L_0x000c;
        L_0x004c:
            r5 = 5;
            goto L_0x000d;
        L_0x004e:
            r5 = "missing_dependency";
            r5 = r6.equals(r5);
            if (r5 == 0) goto L_0x000c;
        L_0x0056:
            r5 = r4;
            goto L_0x000d;
        L_0x0058:
            r5 = "not_gathered";
            r5 = r6.equals(r5);
            if (r5 == 0) goto L_0x000c;
        L_0x0060:
            r5 = 7;
            goto L_0x000d;
        L_0x0062:
            r0 = r1;
            goto L_0x0011;
        L_0x0064:
            r0 = r2;
            goto L_0x0011;
        L_0x0066:
            r0 = r3;
            goto L_0x0011;
        L_0x0068:
            r0 = 3;
            goto L_0x0011;
        L_0x006a:
            r0 = 4;
            goto L_0x0011;
        L_0x006c:
            r0 = 5;
            goto L_0x0011;
        L_0x006e:
            r0 = r4;
            goto L_0x0011;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.kochava.base.C2900c$C2897a.m7550a(java.lang.String):int");
        }

        @NonNull
        /* renamed from: a */
        static InstallReferrer m7551a(@NonNull JSONObject jSONObject, boolean z, @Nullable String str) {
            int i;
            long j = 0;
            long j2 = -1;
            boolean z2 = false;
            String a = C2901d.m7628a(jSONObject.opt("status"));
            int i2 = z ? 4 : 6;
            if (a != null) {
                i2 = C2897a.m7550a(a);
            }
            int b = C2901d.m7647b(jSONObject.opt(InstallReferrer.KEY_ATTEMPT_COUNT), -1);
            double a2 = C2901d.m7623a(jSONObject.opt(InstallReferrer.KEY_DURATION), -1.0d);
            a = "";
            if (i2 == 0) {
                a = C2901d.m7629a(jSONObject.opt("referrer"), "");
                j2 = (long) C2901d.m7647b(jSONObject.opt("install_begin_time"), -1);
                j = (long) C2901d.m7647b(jSONObject.opt("referrer_click_time"), -1);
                i = i2;
            } else if (str == null || str.isEmpty()) {
                j = -1;
                i = i2;
            } else {
                j2 = 0;
                a = str;
                i = 0;
                z2 = true;
            }
            return new InstallReferrer(a, j2, j, i, z2, b, a2);
        }

        @Contract(pure = true)
        @NonNull
        /* renamed from: a */
        static String m7552a(int i) {
            switch (i) {
                case -1:
                    return "service_disconnected";
                case 0:
                    return "ok";
                case 1:
                    return "service_unavailable";
                case 2:
                    return "feature_not_supported";
                case 3:
                    return "developer_error";
                case 4:
                    return "timed_out";
                case 5:
                    return "missing_dependency";
                case 6:
                    return "not_gathered";
                default:
                    return "not_gathered";
            }
        }

        @NonNull
        /* renamed from: a */
        static JSONObject m7553a(@NonNull InstallReferrer installReferrer) {
            JSONObject jSONObject = new JSONObject();
            C2901d.m7636a("status", C2897a.m7552a(installReferrer.status), jSONObject);
            C2901d.m7636a(InstallReferrer.KEY_ATTEMPT_COUNT, Integer.valueOf(installReferrer.attemptCount), jSONObject);
            C2901d.m7636a(InstallReferrer.KEY_DURATION, Double.valueOf(installReferrer.duration), jSONObject);
            if (installReferrer.isValid()) {
                C2901d.m7636a("referrer", installReferrer.referrer, jSONObject);
                C2901d.m7636a("install_begin_time", Long.valueOf(installReferrer.installBeginTime), jSONObject);
                C2901d.m7636a("referrer_click_time", Long.valueOf(installReferrer.referrerClickTime), jSONObject);
            }
            return jSONObject;
        }

        /* renamed from: a */
        private void m7554a() {
            if (C2901d.m7628a(this.f6575b.opt("status")) == null) {
                C2901d.m7636a("status", C2897a.m7552a(5), this.f6575b);
            }
        }

        /* Access modifiers changed, original: final */
        /* renamed from: b */
        public final void mo26554b(int i) {
            try {
                this.f6574a.await((long) i, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Tracker.m7517a(5, "IRH", "waitOnLock", e);
            }
        }

        @UiThread
        public final void run() {
            String str = "run";
            try {
                final InstallReferrerClient build = InstallReferrerClient.newBuilder(this.f6576c).build();
                build.startConnection(new InstallReferrerStateListener() {
                    public final void onInstallReferrerServiceDisconnected() {
                        String str = "onInstallRefe";
                        try {
                            Tracker.m7517a(5, "IRH", "onInstallRefe", "Disconnected");
                            C2901d.m7636a("status", C2897a.m7552a(-1), C2897a.this.f6575b);
                            build.endConnection();
                        } catch (Throwable th) {
                            Tracker.m7517a(4, "IRH", "onInstallRefe", th);
                            C2897a.this.m7554a();
                        }
                        C2897a.this.f6574a.countDown();
                    }

                    public final void onInstallReferrerSetupFinished(int i) {
                        String str = "onInstallRefe";
                        try {
                            Tracker.m7517a(5, "IRH", "onInstallRefe", "Setup Finished", "Response Code: " + i);
                            if (i == 0) {
                                ReferrerDetails installReferrer = build.getInstallReferrer();
                                if (installReferrer != null) {
                                    C2901d.m7636a("status", C2897a.m7552a(0), C2897a.this.f6575b);
                                    C2901d.m7636a("referrer", installReferrer.getInstallReferrer(), C2897a.this.f6575b);
                                    C2901d.m7636a("install_begin_time", Long.valueOf(installReferrer.getInstallBeginTimestampSeconds()), C2897a.this.f6575b);
                                    C2901d.m7636a("referrer_click_time", Long.valueOf(installReferrer.getReferrerClickTimestampSeconds()), C2897a.this.f6575b);
                                }
                            } else if (i == 1) {
                                C2901d.m7636a("status", C2897a.m7552a(1), C2897a.this.f6575b);
                            } else if (i == 2) {
                                C2901d.m7636a("status", C2897a.m7552a(2), C2897a.this.f6575b);
                            } else if (i == 3) {
                                C2901d.m7636a("status", C2897a.m7552a(3), C2897a.this.f6575b);
                            }
                            build.endConnection();
                        } catch (Throwable th) {
                            Tracker.m7517a(4, "IRH", "onInstallRefe", th);
                            C2897a.this.m7554a();
                        }
                        C2897a.this.f6574a.countDown();
                    }
                });
            } catch (Throwable th) {
                Tracker.m7517a(5, "IRH", "run", th);
                m7554a();
                this.f6574a.countDown();
            }
        }
    }

    /* renamed from: com.kochava.base.c$b */
    static class C2898b implements LocationListener {
        @NonNull
        /* renamed from: a */
        final CountDownLatch f6577a = new CountDownLatch(1);
        @NonNull
        /* renamed from: b */
        final JSONObject f6578b = new JSONObject();
        @NonNull
        /* renamed from: c */
        final JSONObject f6579c = new JSONObject();
        @NonNull
        /* renamed from: d */
        final String f6580d;

        C2898b(@NonNull String str) {
            this.f6580d = str;
        }

        @AnyThread
        /* renamed from: a */
        static void m7557a(@NonNull JSONObject jSONObject, @Nullable Location location, @NonNull String str) {
            if (location != null) {
                try {
                    jSONObject.put("latitude", location.getLatitude());
                    jSONObject.put("longitude", location.getLongitude());
                    jSONObject.put("accuracy", Math.round(location.getAccuracy()));
                    jSONObject.put("time", C2901d.m7624a((int) (location.getTime() / 1000), 0, (int) (C2901d.m7626a() / 1000)));
                    if (location.hasAltitude()) {
                        jSONObject.put("altitude", location.getAltitude());
                    }
                    if (location.hasBearing()) {
                        jSONObject.put("direction", (double) location.getBearing());
                    }
                    if (location.hasSpeed()) {
                        jSONObject.put("speed", (double) location.getSpeed());
                    }
                    jSONObject.put("mode", str);
                    jSONObject.put("provider", location.getProvider());
                    if (VERSION.SDK_INT >= 18) {
                        jSONObject.put("mock", location.isFromMockProvider());
                    }
                } catch (JSONException e) {
                    Tracker.m7517a(4, "DPT", "toJson", e);
                }
            }
        }

        @AnyThread
        @Contract("null, _, _ -> false")
        /* renamed from: a */
        static boolean m7558a(@Nullable JSONObject jSONObject, @IntRange int i, @IntRange int i2) {
            if (jSONObject == null) {
                return false;
            }
            boolean z = ((int) (C2901d.m7626a() / 1000)) - ((int) jSONObject.optLong("time", 0)) <= i2 && jSONObject.optInt("accuracy") <= i;
            Tracker.m7517a(4, "DPT", "WithinCriteri", "Within: ", Boolean.valueOf(z), "timeNow: " + r3, "timeLocation: " + r4, "accuracy: " + i, "accuracyLocation: " + r5);
            return z;
        }

        /* Access modifiers changed, original: final */
        @NonNull
        /* renamed from: a */
        public final JSONObject mo26556a() {
            if (this.f6579c.length() != 0) {
                Tracker.m7517a(4, "DPT", "getLocation", "Returning New");
                return this.f6579c;
            } else if (this.f6578b.length() == 0) {
                return new JSONObject();
            } else {
                Tracker.m7517a(4, "DPT", "getLocation", "Returning Last");
                return this.f6578b;
            }
        }

        /* Access modifiers changed, original: final */
        /* renamed from: a */
        public final void mo26557a(int i) {
            try {
                this.f6577a.await((long) i, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Tracker.m7517a(4, "DPT", "waitOnLock", e);
            }
        }

        public void onLocationChanged(Location location) {
            C2898b.m7557a(this.f6579c, location, this.f6580d);
            this.f6577a.countDown();
        }

        @AnyThread
        public final void onProviderDisabled(@Nullable String str) {
        }

        @AnyThread
        public final void onProviderEnabled(@Nullable String str) {
        }

        @AnyThread
        public final void onStatusChanged(@Nullable String str, int i, @Nullable Bundle bundle) {
        }
    }

    @VisibleForTesting
    /* renamed from: com.kochava.base.c$c */
    static final class C2899c extends C2898b implements ResultCallback<Status>, com.google.android.gms.location.LocationListener {
        C2899c() {
            super("googleplay");
        }

        @AnyThread
        /* renamed from: a */
        public final void onResult(@NonNull Status status) {
            if (!status.isSuccess()) {
                this.f6577a.countDown();
            }
        }
    }

    static {
        r0 = new C2900c[44];
        r0[9] = new C2900c(AnalyticAttribute.APPLICATION_PLATFORM_ATTRIBUTE, -1, new int[]{0}, null);
        r0[10] = new C2900c("device", -1, new int[]{1, 2, 3, 8, 6}, null);
        r0[11] = new C2900c("disp_h", 60, new int[]{1, 2, 3, 8, 6}, null);
        r0[12] = new C2900c("disp_w", 60, new int[]{1, 2, 3, 8, 6}, null);
        r0[13] = new C2900c("package", -1, new int[]{0, 1}, null);
        r0[14] = new C2900c("installed_date", -1, new int[]{1}, null);
        r0[15] = new C2900c("app_version", -1, new int[]{1, 2, 3, 4, 8, 6}, null);
        r0[16] = new C2900c("app_short_string", -1, new int[]{1, 2, 3, 4, 8, 6}, null);
        r0[17] = new C2900c("android_id", 60, new int[]{1, 4}, null);
        r0[18] = new C2900c("os_version", -1, new int[]{1, 2, 3, 4, 8, 6}, null);
        r0[19] = new C2900c("device_limit_tracking", -1, new int[]{1, 4}, null);
        r0[20] = new C2900c("fb_attribution_id", -1, new int[]{1}, null);
        r0[21] = new C2900c("ids", -1, null, new int[]{1, 4});
        r0[22] = new C2900c("is_genuine", -1, new int[]{1, 4}, null);
        r0[23] = new C2900c("language", 60, new int[]{1, 4}, null);
        r0[24] = new C2900c("screen_dpi", 60, new int[]{1, 2, 3, 8, 6}, null);
        r0[25] = new C2900c("screen_inches", 60, new int[]{1}, null);
        r0[26] = new C2900c("manufacturer", -1, new int[]{1, 2, 3, 8, 6}, null);
        r0[27] = new C2900c(RecipeComponent.COLUMN_PRODUCT_NAME, -1, new int[]{1, 2, 3, 8, 6}, null);
        r0[28] = new C2900c(AnalyticAttribute.ARCHITECTURE_ATTRIBUTE, -1, new int[]{1, 2, 3, 8, 6}, null);
        r0[29] = new C2900c("battery_status", 60, new int[]{1, 2, 3, 8, 6}, null);
        r0[30] = new C2900c("battery_level", 60, new int[]{1, 2, 3, 8, 6}, null);
        r0[31] = new C2900c("device_cores", -1, new int[]{1}, null);
        r0[32] = new C2900c("signal_bars", 30, new int[]{1, 2, 3, 8, 6}, null);
        r0[33] = new C2900c("installer_package", -1, new int[]{1}, null);
        r0[34] = new C2900c("instant_app", -1, new int[]{1, 2, 3, 8, 6}, null);
        r0[35] = new C2900c("locale", 60, new int[]{0, 1, 2, 3, 8, 6, 9, 10}, null);
        r0[36] = new C2900c(LocalDataManager.TIME_ZONE, 60, new int[]{0, 1, 2, 3, 8, 6, 9, 10}, null);
        r0[37] = new C2900c("bluetooth_name", 30, new int[]{1, 2, 3, 8, 6}, null);
        r0[38] = new C2900c("connected_devices", 30, new int[]{1, 2, 3, 8, 6}, null);
        r0[39] = new C2900c("capabilities", -1, new int[]{1, 2, 3, 8, 6}, null);
        r0[40] = new C2900c("ui_mode", 30, new int[]{1, 2, 3, 8, 6}, null);
        r0[41] = new C2900c("notifications_enabled", 30, new int[]{1, 2, 3, 8, 6}, null);
        r0[42] = new C2900c("install_referrer", -1, new int[]{1}, null);
        r0[43] = new C2900c(LocationManagerProxy.KEY_LOCATION_CHANGED, 1, null, new int[]{1, 2, 8, 6});
        f6581a = r0;
    }

    private C2900c(@NonNull String str, @IntRange int i, @Nullable int[] iArr, @Nullable int[] iArr2) {
        Tracker.m7517a(5, "DPT", "Data", str + "," + i);
        this.f6582b = str;
        this.f6583c = i;
        this.f6584d = iArr;
        this.f6585e = iArr2;
    }

    @Nullable
    @AnyThread
    @Contract(pure = true)
    /* renamed from: A */
    private static String m7562A(@NonNull Context context) {
        return context.getPackageManager().getInstallerPackageName(context.getPackageName());
    }

    @AnyThread
    @Contract(pure = true)
    /* renamed from: B */
    private static boolean m7563B(@NonNull Context context) {
        return InstantApps.isInstantApp(context);
    }

    @AnyThread
    @Contract(pure = true)
    @Nullable
    @RequiresPermission
    /* renamed from: C */
    private static String m7564C(@NonNull Context context) {
        if (!C2901d.m7653b(context, "android.permission.BLUETOOTH")) {
            return null;
        }
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        return (defaultAdapter == null || !defaultAdapter.isEnabled()) ? null : defaultAdapter.getName();
    }

    @WorkerThread
    @NonNull
    /* renamed from: D */
    private static JSONArray m7565D(@NonNull Context context) {
        JSONArray jSONArray = new JSONArray();
        try {
            if (VERSION.SDK_INT >= 18) {
                BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService(CarrierType.BLUETOOTH);
                if (bluetoothManager == null) {
                    return jSONArray;
                }
                ArrayList<BluetoothDevice> arrayList = new ArrayList();
                arrayList.addAll(bluetoothManager.getConnectedDevices(7));
                arrayList.addAll(bluetoothManager.getConnectedDevices(8));
                for (BluetoothDevice name : arrayList) {
                    String str = "BT-LE: " + name.getName();
                    if (!C2901d.m7645a(jSONArray, str)) {
                        jSONArray.put(str);
                    }
                }
            }
        } catch (Throwable th) {
            Tracker.m7517a(4, "DPT", "devicesBle", th);
        }
        return jSONArray;
    }

    @WorkerThread
    @NonNull
    /* renamed from: E */
    private static JSONArray m7566E(@NonNull Context context) {
        JSONArray jSONArray = new JSONArray();
        try {
            if (VERSION.SDK_INT >= 16) {
                InputManager inputManager = (InputManager) context.getSystemService("input");
                if (inputManager != null) {
                    for (int inputDevice : inputManager.getInputDeviceIds()) {
                        InputDevice inputDevice2 = inputManager.getInputDevice(inputDevice);
                        if (!inputDevice2.isVirtual()) {
                            String str;
                            int sources = inputDevice2.getSources();
                            if ((sources & InputDeviceCompat.SOURCE_STYLUS) == InputDeviceCompat.SOURCE_STYLUS) {
                                str = "Stylus: " + inputDevice2.getName();
                                if (!C2901d.m7645a(jSONArray, str)) {
                                    jSONArray.put(str);
                                }
                            }
                            if ((sources & InputDeviceCompat.SOURCE_DPAD) == InputDeviceCompat.SOURCE_DPAD) {
                                str = "D-pad: " + inputDevice2.getName();
                                if (!C2901d.m7645a(jSONArray, str)) {
                                    jSONArray.put(str);
                                }
                            }
                            if ((sources & InputDeviceCompat.SOURCE_GAMEPAD) == InputDeviceCompat.SOURCE_GAMEPAD) {
                                str = "GamePad: " + inputDevice2.getName();
                                if (!C2901d.m7645a(jSONArray, str)) {
                                    jSONArray.put(str);
                                }
                            }
                            if ((InputDeviceCompat.SOURCE_JOYSTICK & sources) == InputDeviceCompat.SOURCE_JOYSTICK) {
                                str = "Joystick: " + inputDevice2.getName();
                                if (!C2901d.m7645a(jSONArray, str)) {
                                    jSONArray.put(str);
                                }
                            }
                            if ((sources & InputDeviceCompat.SOURCE_KEYBOARD) == InputDeviceCompat.SOURCE_KEYBOARD && inputDevice2.getKeyboardType() == 2) {
                                str = "Keyboard: " + inputDevice2.getName();
                                if (!C2901d.m7645a(jSONArray, str)) {
                                    jSONArray.put(str);
                                }
                            }
                            if ((sources & 8194) == 8194) {
                                str = "Mouse: " + inputDevice2.getName();
                                if (!C2901d.m7645a(jSONArray, str)) {
                                    jSONArray.put(str);
                                }
                            }
                            if ((sources & InputDeviceCompat.SOURCE_TOUCHPAD) == InputDeviceCompat.SOURCE_TOUCHPAD) {
                                str = "TouchPad: " + inputDevice2.getName();
                                if (!C2901d.m7645a(jSONArray, str)) {
                                    jSONArray.put(str);
                                }
                            }
                            if ((sources & 65540) == 65540) {
                                str = "Trackball: " + inputDevice2.getName();
                                if (!C2901d.m7645a(jSONArray, str)) {
                                    jSONArray.put(str);
                                }
                            }
                            if (VERSION.SDK_INT >= 23 && (sources & 49154) == 49154) {
                                str = "Bluetooth Stylus " + inputDevice2.getName();
                                if (!C2901d.m7645a(jSONArray, str)) {
                                    jSONArray.put(str);
                                }
                            }
                            if (VERSION.SDK_INT >= 26) {
                                if ((sources & 131076) == 131076) {
                                    str = "Mouse Relative: " + inputDevice2.getName();
                                    if (!C2901d.m7645a(jSONArray, str)) {
                                        jSONArray.put(str);
                                    }
                                }
                                if ((sources & AccessibilityEventCompat.TYPE_WINDOWS_CHANGED) == AccessibilityEventCompat.TYPE_WINDOWS_CHANGED) {
                                    String str2 = "Rotary Encoder: " + inputDevice2.getName();
                                    if (!C2901d.m7645a(jSONArray, str2)) {
                                        jSONArray.put(str2);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Throwable th) {
            Tracker.m7517a(4, "DPT", "devicesInput", th);
        }
        return jSONArray;
    }

    @WorkerThread
    @NonNull
    /* renamed from: F */
    private static JSONArray m7567F(@NonNull Context context) {
        JSONArray jSONArray = new JSONArray();
        try {
            GoogleApiClient build = new Builder(context).addApi(Wearable.API).build();
            if (!build.blockingConnect(2, TimeUnit.SECONDS).isSuccess()) {
                return jSONArray;
            }
            List<Node> nodes = ((GetConnectedNodesResult) Wearable.NodeApi.getConnectedNodes(build).await(2, TimeUnit.SECONDS)).getNodes();
            if (nodes.isEmpty()) {
                return jSONArray;
            }
            for (Node node : nodes) {
                if (node.isNearby()) {
                    jSONArray.put("Wearable: " + node.getDisplayName());
                }
            }
            build.disconnect();
            return jSONArray;
        } catch (Throwable th) {
            Tracker.m7517a(4, "DPT", "devicesNode", th);
        }
    }

    @Nullable
    @WorkerThread
    @Contract(pure = true)
    /* renamed from: G */
    private static JSONArray m7568G(@NonNull Context context) {
        JSONArray jSONArray = new JSONArray();
        if (C2901d.m7653b(context, "android.permission.BLUETOOTH")) {
            C2901d.m7638a(jSONArray, C2900c.m7604j());
            C2901d.m7638a(jSONArray, C2900c.m7565D(context));
        }
        C2901d.m7638a(jSONArray, C2900c.m7566E(context));
        C2901d.m7638a(jSONArray, C2900c.m7567F(context));
        return jSONArray.length() != 0 ? jSONArray : null;
    }

    @Nullable
    @WorkerThread
    @Contract(pure = true)
    /* renamed from: H */
    private static JSONArray m7569H(@NonNull Context context) {
        return null;
    }

    @Nullable
    @AnyThread
    @Contract(pure = true)
    /* renamed from: I */
    private static String m7570I(@NonNull Context context) {
        UiModeManager uiModeManager = (UiModeManager) context.getSystemService("uimode");
        if (uiModeManager == null) {
            return null;
        }
        switch (uiModeManager.getCurrentModeType()) {
            case 0:
                return "Undefined";
            case 1:
                return "Normal";
            case 2:
                return "Desk";
            case 3:
                return "Car";
            case 4:
                return "Television";
            case 5:
                return "Appliance";
            case 6:
                return "Watch";
            case 7:
                return "VR_Headset";
            default:
                return null;
        }
    }

    @AnyThread
    @Contract(pure = true)
    /* renamed from: J */
    private static boolean m7571J(@NonNull Context context) {
        boolean z = true;
        if (VERSION.SDK_INT >= 24) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            if (notificationManager != null) {
                if (VERSION.SDK_INT >= 26) {
                    boolean z2;
                    List notificationChannels = notificationManager.getNotificationChannels();
                    Iterator it = notificationChannels.iterator();
                    while (true) {
                        z2 = z;
                        if (!it.hasNext()) {
                            break;
                        }
                        z = ((NotificationChannel) it.next()).getImportance() != 0 ? false : z2;
                    }
                    if (z2 && !notificationChannels.isEmpty()) {
                        return false;
                    }
                }
                return notificationManager.areNotificationsEnabled();
            }
        }
        if (VERSION.SDK_INT >= 19) {
            try {
                Object invoke = Class.forName("android.support.v4.app.NotificationManagerCompat").getMethod(PushConstants.FROM_ID, new Class[]{Context.class}).invoke(null, new Object[]{context});
                return ((Boolean) invoke.getClass().getMethod("areNotificationsEnabled", new Class[0]).invoke(invoke, new Object[0])).booleanValue();
            } catch (Throwable th) {
            }
        }
        return true;
    }

    @AnyThread
    @Contract(pure = true)
    /* renamed from: a */
    private static double m7572a(@NonNull Context context) {
        return C2901d.m7622a(((double) Math.round((((double) System.getInt(context.getContentResolver(), "screen_brightness")) / 255.0d) * 10000.0d)) / 10000.0d, 0.0d, 1.0d);
    }

    @WorkerThread
    @NonNull
    /* renamed from: a */
    static InstallReferrer m7573a(@NonNull Context context, @Size int i, @Size int i2, @Size double d) {
        InstallReferrer installReferrer;
        long a = C2901d.m7626a();
        int i3 = 0;
        Handler handler = new Handler(Looper.getMainLooper());
        InstallReferrer installReferrer2 = null;
        int i4 = 0;
        while (true) {
            int i5 = i4;
            installReferrer = installReferrer2;
            if (i5 >= i) {
                break;
            }
            i3++;
            C2897a c2897a = new C2897a(context);
            handler.post(c2897a);
            c2897a.mo26554b(i2);
            C2901d.m7636a(InstallReferrer.KEY_ATTEMPT_COUNT, Integer.valueOf(i3), c2897a.f6575b);
            C2901d.m7636a(InstallReferrer.KEY_DURATION, Double.valueOf(((double) (C2901d.m7626a() - a)) / 1000.0d), c2897a.f6575b);
            installReferrer2 = C2897a.m7551a(c2897a.f6575b, true, null);
            if (installReferrer2.isValid()) {
                installReferrer = installReferrer2;
                break;
            } else if (!installReferrer2.isSupported()) {
                installReferrer = installReferrer2;
                break;
            } else {
                try {
                    Thread.sleep(Math.round(1000.0d * d));
                } catch (InterruptedException e) {
                    Tracker.m7517a(4, "DPT", "getInstallRef", e);
                }
                i4 = i5 + 1;
            }
        }
        return installReferrer != null ? installReferrer : new InstallReferrer("", -1, -1, 4, false, i3, ((double) (C2901d.m7626a() - a)) / 1000.0d);
    }

    @Nullable
    @WorkerThread
    /* renamed from: a */
    private Object m7574a(@NonNull Context context, @NonNull C2901d c2901d, @Nullable Object obj, @Nullable Object obj2, boolean z, @NonNull List<String> list, @Nullable JSONObject jSONObject) {
        String str = "getValueNew";
        Object c = obj != null ? obj : c2901d.mo26572c(this.f6582b);
        if (obj == null || z || obj2 != null) {
            if (obj2 == null) {
                try {
                    obj2 = C2900c.m7575a(this, context, jSONObject, c);
                } catch (Throwable th) {
                    Tracker.m7517a(4, "DPT", "getValueNew", th);
                }
            }
            if (obj2 != null) {
                if (!list.contains(this.f6582b)) {
                    list.add(this.f6582b);
                }
                if (obj == null || !C2901d.m7643a(obj2, obj)) {
                    c2901d.mo26565a(this.f6582b, obj2);
                    c2901d.mo26565a(this.f6582b + "_ts", Integer.valueOf((int) (C2901d.m7626a() / 1000)));
                    if (!C2901d.m7643a(obj2, c)) {
                        c2901d.mo26565a(this.f6582b + "_upd", Boolean.valueOf(true));
                    }
                }
            }
        }
        return obj2;
    }

    @Nullable
    @WorkerThread
    @VisibleForTesting
    /* renamed from: a */
    static Object m7575a(@NonNull C2900c c2900c, @NonNull Context context, @Nullable JSONObject jSONObject, @Nullable Object obj) {
        String str = c2900c.f6582b;
        Object obj2 = -1;
        switch (str.hashCode()) {
            case -2086471997:
                if (str.equals("instant_app")) {
                    obj2 = 33;
                    break;
                }
                break;
            case -2076227591:
                if (str.equals(LocalDataManager.TIME_ZONE)) {
                    obj2 = 36;
                    break;
                }
                break;
            case -1969347631:
                if (str.equals("manufacturer")) {
                    obj2 = 25;
                    break;
                }
                break;
            case -1958212269:
                if (str.equals("installed_date")) {
                    obj2 = 16;
                    break;
                }
                break;
            case -1613589672:
                if (str.equals("language")) {
                    obj2 = 35;
                    break;
                }
                break;
            case -1487597642:
                if (str.equals("capabilities")) {
                    obj2 = 39;
                    break;
                }
                break;
            case -1335157162:
                if (str.equals("device")) {
                    obj2 = 12;
                    break;
                }
                break;
            case -1331545845:
                if (str.equals("disp_h")) {
                    obj2 = 13;
                    break;
                }
                break;
            case -1331545830:
                if (str.equals("disp_w")) {
                    obj2 = 14;
                    break;
                }
                break;
            case -1211390364:
                if (str.equals("battery_status")) {
                    obj2 = 28;
                    break;
                }
                break;
            case -1144512572:
                if (str.equals("device_limit_tracking")) {
                    obj2 = 10;
                    break;
                }
                break;
            case -1097462182:
                if (str.equals("locale")) {
                    obj2 = 34;
                    break;
                }
                break;
            case -901870406:
                if (str.equals("app_version")) {
                    obj2 = 17;
                    break;
                }
                break;
            case -877252910:
                if (str.equals("battery_level")) {
                    obj2 = 29;
                    break;
                }
                break;
            case -810883302:
                if (str.equals("volume")) {
                    obj2 = 4;
                    break;
                }
                break;
            case -807062458:
                if (str.equals("package")) {
                    obj2 = 15;
                    break;
                }
                break;
            case -600298101:
                if (str.equals("device_cores")) {
                    obj2 = 30;
                    break;
                }
                break;
            case -439099282:
                if (str.equals("ui_mode")) {
                    obj2 = 40;
                    break;
                }
                break;
            case -417046774:
                if (str.equals("screen_dpi")) {
                    obj2 = 23;
                    break;
                }
                break;
            case -345765233:
                if (str.equals("installer_package")) {
                    obj2 = 32;
                    break;
                }
                break;
            case -286797593:
                if (str.equals("fire_adid")) {
                    obj2 = 9;
                    break;
                }
                break;
            case -184604772:
                if (str.equals("network_conn_type")) {
                    obj2 = 3;
                    break;
                }
                break;
            case 104120:
                if (str.equals("ids")) {
                    obj2 = 21;
                    break;
                }
                break;
            case 2989182:
                if (str.equals("adid")) {
                    obj2 = 8;
                    break;
                }
                break;
            case 3539835:
                if (str.equals("ssid")) {
                    obj2 = 5;
                    break;
                }
                break;
            case 94044893:
                if (str.equals("bssid")) {
                    obj2 = 6;
                    break;
                }
                break;
            case 224914812:
                if (str.equals("bluetooth_name")) {
                    obj2 = 37;
                    break;
                }
                break;
            case 672545271:
                if (str.equals("signal_bars")) {
                    obj2 = 31;
                    break;
                }
                break;
            case 672836989:
                if (str.equals("os_version")) {
                    obj2 = 20;
                    break;
                }
                break;
            case 722989291:
                if (str.equals("android_id")) {
                    obj2 = 19;
                    break;
                }
                break;
            case 816209642:
                if (str.equals("notifications_enabled")) {
                    obj2 = 41;
                    break;
                }
                break;
            case 839674195:
                if (str.equals(AnalyticAttribute.ARCHITECTURE_ATTRIBUTE)) {
                    obj2 = 27;
                    break;
                }
                break;
            case 1014375387:
                if (str.equals(RecipeComponent.COLUMN_PRODUCT_NAME)) {
                    obj2 = 26;
                    break;
                }
                break;
            case 1241166251:
                if (str.equals("screen_inches")) {
                    obj2 = 24;
                    break;
                }
                break;
            case 1328981571:
                if (str.equals("install_referrer")) {
                    obj2 = 42;
                    break;
                }
                break;
            case 1420630150:
                if (str.equals("is_genuine")) {
                    obj2 = 22;
                    break;
                }
                break;
            case 1735689732:
                if (str.equals("screen_brightness")) {
                    obj2 = 1;
                    break;
                }
                break;
            case 1741791591:
                if (str.equals("device_orientation")) {
                    obj2 = 2;
                    break;
                }
                break;
            case 1757114046:
                if (str.equals("fb_attribution_id")) {
                    obj2 = 11;
                    break;
                }
                break;
            case 1774661031:
                if (str.equals("connected_devices")) {
                    obj2 = 38;
                    break;
                }
                break;
            case 1874684019:
                if (str.equals(AnalyticAttribute.APPLICATION_PLATFORM_ATTRIBUTE)) {
                    obj2 = null;
                    break;
                }
                break;
            case 1901043637:
                if (str.equals(LocationManagerProxy.KEY_LOCATION_CHANGED)) {
                    obj2 = 43;
                    break;
                }
                break;
            case 1974464370:
                if (str.equals("carrier_name")) {
                    obj2 = 7;
                    break;
                }
                break;
            case 2118140562:
                if (str.equals("app_short_string")) {
                    obj2 = 18;
                    break;
                }
                break;
        }
        switch (obj2) {
            case null:
                return "android";
            case 1:
                return Double.valueOf(C2900c.m7572a(context));
            case 2:
                return C2900c.m7587b(context);
            case 3:
                return C2900c.m7589c(context);
            case 4:
                return C2900c.m7591d(context);
            case 5:
                return C2900c.m7594e(context);
            case 6:
                return C2900c.m7596f(context);
            case 7:
                return C2900c.m7598g(context);
            case 8:
                return C2900c.m7600h(context);
            case 9:
                return C2900c.m7602i(context);
            case 10:
                return Boolean.valueOf(C2900c.m7611q(context));
            case 11:
                return C2900c.m7614t(context);
            case 12:
                return C2900c.m7576a();
            case 13:
                return C2900c.m7603j(context);
            case 14:
                return C2900c.m7605k(context);
            case 15:
                return C2900c.m7606l(context);
            case 16:
                return Integer.valueOf(C2900c.m7607m(context));
            case 17:
                return C2900c.m7608n(context);
            case 18:
                return C2900c.m7609o(context);
            case 19:
                return C2900c.m7610p(context);
            case 20:
                return C2900c.m7586b();
            case 21:
                return C2900c.m7615u(context);
            case 22:
                return Boolean.valueOf(C2900c.m7590c());
            case 23:
                return Integer.valueOf(C2900c.m7616v(context));
            case 24:
                return C2900c.m7617w(context);
            case 25:
                return C2900c.m7592d();
            case 26:
                return C2900c.m7593e();
            case 27:
                return C2900c.m7595f();
            case 28:
                return C2900c.m7618x(context);
            case 29:
                return C2900c.m7619y(context);
            case 30:
                return Integer.valueOf(C2900c.m7597g());
            case 31:
                return C2900c.m7620z(context);
            case 32:
                return C2900c.m7562A(context);
            case 33:
                return Boolean.valueOf(C2900c.m7563B(context));
            case 34:
            case 35:
                return C2900c.m7599h();
            case 36:
                return C2900c.m7601i();
            case 37:
                return C2900c.m7564C(context);
            case 38:
                return C2900c.m7568G(context);
            case 39:
                return C2900c.m7569H(context);
            case 40:
                return C2900c.m7570I(context);
            case 41:
                return Boolean.valueOf(C2900c.m7571J(context));
            case 42:
                return C2900c.m7579a(context, jSONObject);
            case 43:
                return C2900c.m7580a(context, jSONObject, obj);
            default:
                return null;
        }
    }

    @AnyThread
    @Contract(pure = true)
    @NonNull
    /* renamed from: a */
    private static String m7576a() {
        return Build.MODEL + "-" + Build.BRAND;
    }

    @Nullable
    @WorkerThread
    /* renamed from: a */
    private static JSONObject m7577a(@NonNull Context context, @IntRange int i, @IntRange int i2, @IntRange int i3) {
        GoogleApiClient build;
        String str = "getWithGoogle";
        Tracker.m7517a(5, "DPT", "getWithGoogle", new Object[0]);
        try {
            build = new Builder(context).addApi(LocationServices.API).build();
        } catch (Throwable th) {
            Tracker.m7517a(5, "DPT", "getWithGoogle", th);
            build = null;
        }
        if (build == null) {
            Tracker.m7517a(4, "DPT", "getWithGoogle", "Failed to connect to Google Play Services");
            return null;
        }
        if (build.blockingConnect(2, TimeUnit.SECONDS).isSuccess()) {
            C2899c c2899c = new C2899c();
            C2898b.m7557a(c2899c.f6578b, LocationServices.FusedLocationApi.getLastLocation(build), c2899c.f6580d);
            if (C2898b.m7558a(c2899c.f6578b, i, i3)) {
                build.disconnect();
                Tracker.m7517a(4, "DPT", "getWithGoogle", "Returning Last Known");
                return c2899c.f6578b;
            }
            try {
                LocationRequest numUpdates = new LocationRequest().setNumUpdates(1);
                if (i < 50) {
                    numUpdates.setPriority(100);
                } else if (i < 1000) {
                    numUpdates.setPriority(102);
                } else if (i < 10000) {
                    numUpdates.setPriority(104);
                } else {
                    numUpdates.setPriority(105);
                }
                LocationServices.FusedLocationApi.requestLocationUpdates(build, numUpdates, c2899c).setResultCallback(c2899c, (long) i2, TimeUnit.SECONDS);
            } catch (Exception e) {
                Tracker.m7517a(4, "DPT", "getWithGoogle", e);
            }
            c2899c.mo26557a(i2);
            LocationServices.FusedLocationApi.removeLocationUpdates(build, c2899c);
            build.disconnect();
            return c2899c.mo26556a();
        }
        Tracker.m7517a(4, "DPT", "getWithGoogle", build.blockingConnect(2, TimeUnit.SECONDS).getErrorMessage());
        return new JSONObject();
    }

    @Nullable
    @WorkerThread
    /* renamed from: a */
    private static JSONObject m7578a(@NonNull Context context, @IntRange int i, @IntRange int i2, @IntRange int i3, @NonNull String str) {
        JSONObject jSONObject = null;
        String str2 = "getLocation";
        Tracker.m7517a(5, "DPT", "getLocation", new Object[0]);
        if (C2901d.m7642a(context, "android.permission.ACCESS_FINE_LOCATION") || C2901d.m7642a(context, "android.permission.ACCESS_COARSE_LOCATION")) {
            int i4 = -1;
            if ("auto".equalsIgnoreCase(str) || "googleplay".equalsIgnoreCase(str)) {
                try {
                    i4 = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context);
                } catch (Throwable th) {
                    Tracker.m7517a(4, "DPT", "getLocation", "Missing Google Play Services");
                }
            }
            if (i4 == 0) {
                jSONObject = C2900c.m7577a(context, i, i2, i3);
            }
            return jSONObject == null ? ("auto".equalsIgnoreCase(str) || "device".equalsIgnoreCase(str)) ? C2900c.m7588b(context, i, i2, i3) : jSONObject : jSONObject;
        } else {
            Tracker.m7517a(3, "DPT", "getLocation", "Missing Permission: android.permission.ACCESS_FINE_LOCATION android.permission.ACCESS_COARSE_LOCATION");
            return null;
        }
    }

    @NonNull
    /* renamed from: a */
    static JSONObject m7579a(@NonNull Context context, @Nullable JSONObject jSONObject) {
        int a;
        int a2;
        double a3;
        String str = "getInstallRef";
        if (jSONObject != null) {
            a = jSONObject.has("install_referrer_attempts") ? C2901d.m7624a(C2901d.m7647b(jSONObject.opt("install_referrer_attempts"), 2), 1, Integer.MAX_VALUE) : 2;
            a2 = jSONObject.has("install_referrer_wait") ? C2901d.m7624a(C2901d.m7647b(jSONObject.opt("install_referrer_wait"), 10), 1, Integer.MAX_VALUE) : 10;
            a3 = jSONObject.has("install_referrer_retry_wait") ? C2901d.m7622a(C2901d.m7623a(jSONObject.opt("install_referrer_retry_wait"), 1.0d), 0.0d, Double.MAX_VALUE) : 1.0d;
        } else {
            a2 = 10;
            a = 2;
            a3 = 1.0d;
        }
        Tracker.m7517a(4, "DPT", "getInstallRef", "Attempts: " + a, "AttemptWait: " + a2, "AttemptRetryWait: " + a3);
        return C2897a.m7553a(C2900c.m7573a(context, a, a2, a3));
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x00ef  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00f1  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x00de  */
    @android.support.annotation.Nullable
    @android.support.annotation.WorkerThread
    /* renamed from: a */
    private static org.json.JSONObject m7580a(@android.support.annotation.NonNull android.content.Context r12, @android.support.annotation.Nullable org.json.JSONObject r13, @android.support.annotation.Nullable java.lang.Object r14) {
        /*
        r0 = "getLocation11";
        r0 = 50;
        r1 = 10;
        r2 = 90;
        r3 = "auto";
        if (r13 == 0) goto L_0x00f6;
    L_0x000c:
        r4 = "accuracy";
        r4 = r13.has(r4);
        if (r4 == 0) goto L_0x0026;
    L_0x0014:
        r4 = "accuracy";
        r4 = r13.opt(r4);
        r0 = com.kochava.base.C2901d.m7647b(r4, r0);
        r4 = 0;
        r5 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r0 = com.kochava.base.C2901d.m7624a(r0, r4, r5);
    L_0x0026:
        r4 = "timeout";
        r4 = r13.has(r4);
        if (r4 == 0) goto L_0x0040;
    L_0x002e:
        r4 = "timeout";
        r4 = r13.opt(r4);
        r1 = com.kochava.base.C2901d.m7647b(r4, r1);
        r4 = 1;
        r5 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r1 = com.kochava.base.C2901d.m7624a(r1, r4, r5);
    L_0x0040:
        r4 = "staleness";
        r4 = r13.has(r4);
        if (r4 == 0) goto L_0x005a;
    L_0x0048:
        r4 = "staleness";
        r4 = r13.opt(r4);
        r2 = com.kochava.base.C2901d.m7647b(r4, r2);
        r4 = 0;
        r5 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r2 = com.kochava.base.C2901d.m7624a(r2, r4, r5);
    L_0x005a:
        r4 = "mode";
        r4 = r13.has(r4);
        if (r4 == 0) goto L_0x00f6;
    L_0x0062:
        r4 = "mode";
        r4 = r13.opt(r4);
        r3 = com.kochava.base.C2901d.m7629a(r4, r3);
        r11 = r3;
        r3 = r0;
        r0 = r11;
    L_0x006f:
        r4 = 4;
        r5 = "DPT";
        r6 = "getLocation11";
        r7 = 4;
        r7 = new java.lang.Object[r7];
        r8 = 0;
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r10 = "Accuracy: ";
        r9 = r9.append(r10);
        r9 = r9.append(r3);
        r9 = r9.toString();
        r7[r8] = r9;
        r8 = 1;
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r10 = "Timeout: ";
        r9 = r9.append(r10);
        r9 = r9.append(r1);
        r9 = r9.toString();
        r7[r8] = r9;
        r8 = 2;
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r10 = "Staleness: ";
        r9 = r9.append(r10);
        r9 = r9.append(r2);
        r9 = r9.toString();
        r7[r8] = r9;
        r8 = 3;
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r10 = "Mode: ";
        r9 = r9.append(r10);
        r9 = r9.append(r0);
        r9 = r9.toString();
        r7[r8] = r9;
        com.kochava.base.Tracker.m7517a(r4, r5, r6, r7);
        r4 = r14 instanceof org.json.JSONObject;
        if (r4 == 0) goto L_0x00ef;
    L_0x00d6:
        r14 = (org.json.JSONObject) r14;
    L_0x00d8:
        r4 = com.kochava.base.C2900c.C2898b.m7558a(r14, r3, r2);
        if (r4 == 0) goto L_0x00f1;
    L_0x00de:
        r0 = 4;
        r1 = "DPT";
        r2 = "getLocation11";
        r3 = 1;
        r3 = new java.lang.Object[r3];
        r4 = 0;
        r5 = "Returning Cached";
        r3[r4] = r5;
        com.kochava.base.Tracker.m7517a(r0, r1, r2, r3);
    L_0x00ee:
        return r14;
    L_0x00ef:
        r14 = 0;
        goto L_0x00d8;
    L_0x00f1:
        r14 = com.kochava.base.C2900c.m7578a(r12, r3, r1, r2, r0);
        goto L_0x00ee;
    L_0x00f6:
        r11 = r3;
        r3 = r0;
        r0 = r11;
        goto L_0x006f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kochava.base.C2900c.m7580a(android.content.Context, org.json.JSONObject, java.lang.Object):org.json.JSONObject");
    }

    @WorkerThread
    /* renamed from: a */
    static void m7581a(@NonNull Context context, @NonNull C2901d c2901d, @NonNull JSONObject jSONObject, @NonNull JSONObject jSONObject2, @NonNull List<String> list, @Nullable JSONArray jSONArray, @Nullable JSONArray jSONArray2, int i, @NonNull JSONObject jSONObject3) {
        String str = "get";
        boolean z = i == 4;
        boolean z2 = i == 1;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 < f6581a.length) {
                C2900c c2900c = f6581a[i3];
                if (C2900c.m7585a(c2900c, i, jSONArray2, jSONArray)) {
                    Tracker.m7517a(5, "DPT", "get", i + "," + c2900c.f6582b);
                    c2900c.m7582a(context, c2901d, jSONObject3, z, z2, list, jSONObject2.opt(c2900c.f6582b), C2901d.m7661f(jSONObject.opt(c2900c.f6582b)));
                }
                i2 = i3 + 1;
            } else {
                return;
            }
        }
    }

    @WorkerThread
    /* renamed from: a */
    private void m7582a(@NonNull Context context, @NonNull C2901d c2901d, @NonNull JSONObject jSONObject, boolean z, boolean z2, @NonNull List<String> list, @Nullable Object obj, @Nullable JSONObject jSONObject2) {
        String str = "addToPayload";
        synchronized (this) {
            Object a = mo26563a(c2901d, z, list.contains(this.f6582b));
            Object a2 = m7574a(context, c2901d, a, obj, z, list, jSONObject2);
            Tracker.m7517a(4, "DPT", "addToPayload", this.f6582b + ": " + a + "," + a2 + " hasUpdated: " + C2901d.m7644a(c2901d.mo26572c(this.f6582b + "_upd"), false) + " isEqual: " + C2901d.m7643a(a2, a));
            m7583a(jSONObject, a2, a, z, r6);
            if (z2 || z) {
                c2901d.mo26565a(this.f6582b + "_upd", Boolean.valueOf(false));
            }
        }
    }

    /* JADX WARNING: Missing block: B:6:0x000e, code skipped:
            if (com.kochava.base.C2901d.m7643a(r10, r11) == false) goto L_0x0010;
     */
    @android.support.annotation.AnyThread
    /* renamed from: a */
    private void m7583a(@android.support.annotation.NonNull org.json.JSONObject r9, @android.support.annotation.Nullable java.lang.Object r10, @android.support.annotation.Nullable java.lang.Object r11, boolean r12, boolean r13) {
        /*
        r8 = this;
        r7 = 1;
        r6 = 0;
        r0 = "addToData";
        if (r12 == 0) goto L_0x0016;
    L_0x0006:
        if (r10 == 0) goto L_0x0016;
    L_0x0008:
        if (r13 != 0) goto L_0x0010;
    L_0x000a:
        r0 = com.kochava.base.C2901d.m7643a(r10, r11);	 Catch:{ JSONException -> 0x0020 }
        if (r0 != 0) goto L_0x0016;
    L_0x0010:
        r0 = r8.f6582b;	 Catch:{ JSONException -> 0x0020 }
        r8.m7584a(r9, r0, r10);	 Catch:{ JSONException -> 0x0020 }
    L_0x0015:
        return;
    L_0x0016:
        if (r12 != 0) goto L_0x002e;
    L_0x0018:
        if (r10 == 0) goto L_0x002e;
    L_0x001a:
        r0 = r8.f6582b;	 Catch:{ JSONException -> 0x0020 }
        r8.m7584a(r9, r0, r10);	 Catch:{ JSONException -> 0x0020 }
        goto L_0x0015;
    L_0x0020:
        r0 = move-exception;
        r1 = 4;
        r2 = "DPT";
        r3 = "addToData";
        r4 = new java.lang.Object[r7];
        r4[r6] = r0;
        com.kochava.base.Tracker.m7517a(r1, r2, r3, r4);
        goto L_0x0015;
    L_0x002e:
        if (r12 != 0) goto L_0x0038;
    L_0x0030:
        if (r11 == 0) goto L_0x0038;
    L_0x0032:
        r0 = r8.f6582b;	 Catch:{ JSONException -> 0x0020 }
        r8.m7584a(r9, r0, r11);	 Catch:{ JSONException -> 0x0020 }
        goto L_0x0015;
    L_0x0038:
        r0 = 5;
        r1 = "DPT";
        r2 = "addToData";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ JSONException -> 0x0020 }
        r4 = 0;
        r5 = "Skip";
        r3[r4] = r5;	 Catch:{ JSONException -> 0x0020 }
        com.kochava.base.Tracker.m7517a(r0, r1, r2, r3);	 Catch:{ JSONException -> 0x0020 }
        goto L_0x0015;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kochava.base.C2900c.m7583a(org.json.JSONObject, java.lang.Object, java.lang.Object, boolean, boolean):void");
    }

    /* renamed from: a */
    private void m7584a(@NonNull JSONObject jSONObject, @NonNull String str, @NonNull Object obj) {
        if (!(obj instanceof JSONObject) || ((JSONObject) obj).length() != 0) {
            if (!(obj instanceof JSONArray) || ((JSONArray) obj).length() != 0) {
                jSONObject.put(str, obj);
            }
        }
    }

    @AnyThread
    /* renamed from: a */
    static boolean m7585a(@NonNull C2900c c2900c, int i, @Nullable JSONArray jSONArray, @Nullable JSONArray jSONArray2) {
        if (jSONArray2 != null) {
            if (C2901d.m7645a(jSONArray2, c2900c.f6582b)) {
                return false;
            }
        } else if (i != 0) {
            return false;
        }
        if (c2900c.f6584d != null) {
            for (int i2 : c2900c.f6584d) {
                if (i2 == i) {
                    return true;
                }
            }
        }
        if (jSONArray == null || c2900c.f6585e == null) {
            return false;
        }
        for (int i22 : c2900c.f6585e) {
            if (i22 == i && C2901d.m7645a(jSONArray, c2900c.f6582b)) {
                return true;
            }
        }
        return false;
    }

    @AnyThread
    @Contract(pure = true)
    @NonNull
    /* renamed from: b */
    private static String m7586b() {
        return "Android " + VERSION.RELEASE;
    }

    @AnyThread
    @Contract(pure = true)
    @NonNull
    /* renamed from: b */
    private static String m7587b(@NonNull Context context) {
        return context.getResources().getConfiguration().orientation == 2 ? "landscape" : "portrait";
    }

    @WorkerThread
    @NonNull
    /* renamed from: b */
    private static JSONObject m7588b(@NonNull Context context, @IntRange int i, @IntRange int i2, @IntRange int i3) {
        String str = "getWithDevice";
        Tracker.m7517a(5, "DPT", "getWithDevice", new Object[0]);
        C2898b c2898b = new C2898b("device");
        LocationManager locationManager = (LocationManager) context.getSystemService(LocationManagerProxy.KEY_LOCATION_CHANGED);
        if (locationManager == null) {
            return c2898b.mo26556a();
        }
        if (i < 50 && C2901d.m7642a(context, "android.permission.ACCESS_FINE_LOCATION")) {
            C2898b.m7557a(c2898b.f6578b, locationManager.getLastKnownLocation("gps"), c2898b.f6580d);
        }
        if (c2898b.f6578b.length() == 0 && i < 10000) {
            C2898b.m7557a(c2898b.f6578b, locationManager.getLastKnownLocation(LocationManagerProxy.NETWORK_PROVIDER), c2898b.f6580d);
        }
        if (c2898b.f6578b.length() == 0) {
            C2898b.m7557a(c2898b.f6578b, locationManager.getLastKnownLocation("passive"), c2898b.f6580d);
        }
        if (C2898b.m7558a(c2898b.f6578b, i, i3)) {
            Tracker.m7517a(4, "DPT", "getWithDevice", "Returning Last Known");
            return c2898b.f6578b;
        }
        try {
            Criteria criteria = new Criteria();
            if (i < 50 && C2901d.m7642a(context, "android.permission.ACCESS_FINE_LOCATION")) {
                criteria.setAccuracy(1);
                criteria.setPowerRequirement(3);
                criteria.setCostAllowed(true);
            } else if (i < 10000) {
                criteria.setAccuracy(2);
                criteria.setPowerRequirement(2);
                criteria.setCostAllowed(true);
            } else {
                criteria.setAccuracy(2);
                criteria.setPowerRequirement(1);
            }
            criteria.setAltitudeRequired(true);
            criteria.setBearingRequired(true);
            criteria.setSpeedRequired(true);
            locationManager.requestSingleUpdate(criteria, c2898b, null);
        } catch (Exception e) {
            Tracker.m7517a(4, "DPT", "getWithDevice", e);
        }
        c2898b.mo26557a(i2);
        locationManager.removeUpdates(c2898b);
        return c2898b.mo26556a();
    }

    @AnyThread
    @Contract(pure = true)
    @Nullable
    @RequiresPermission
    /* renamed from: c */
    private static String m7589c(@NonNull Context context) {
        if (!C2901d.m7653b(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return null;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return null;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return "none";
        }
        int type = activeNetworkInfo.getType();
        return (type == 0 || type == 4 || type == 5 || type == 2 || type == 3) ? CarrierType.CELLULAR : type == 9 ? "wired" : "wifi";
    }

    @WorkerThread
    @Contract(pure = true)
    /* renamed from: c */
    private static boolean m7590c() {
        String str = Build.TAGS;
        if (str != null && str.contains("test-keys")) {
            return false;
        }
        for (String file : new String[]{"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"}) {
            if (new File(file).exists()) {
                return false;
            }
        }
        return true;
    }

    @Nullable
    @AnyThread
    @Contract(pure = true)
    /* renamed from: d */
    private static Double m7591d(@NonNull Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        return audioManager == null ? null : Double.valueOf(C2901d.m7622a(((double) Math.round(((((double) audioManager.getStreamVolume(3)) * 1.0d) / ((double) audioManager.getStreamMaxVolume(3))) * 10000.0d)) / 10000.0d, 0.0d, 1.0d));
    }

    @AnyThread
    @Contract(pure = true)
    @NonNull
    /* renamed from: d */
    private static String m7592d() {
        return Build.MANUFACTURER;
    }

    @AnyThread
    @Contract(pure = true)
    @NonNull
    /* renamed from: e */
    private static String m7593e() {
        return Build.PRODUCT;
    }

    @AnyThread
    @Contract(pure = true)
    @Nullable
    @RequiresPermission
    /* renamed from: e */
    private static String m7594e(@NonNull Context context) {
        if (!C2901d.m7653b(context, "android.permission.ACCESS_WIFI_STATE")) {
            return null;
        }
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
        return wifiManager == null ? null : wifiManager.getConnectionInfo().getSSID();
    }

    @Nullable
    @AnyThread
    @Contract(pure = true)
    /* renamed from: f */
    private static String m7595f() {
        return System.getProperty("os.arch");
    }

    @AnyThread
    @Contract(pure = true)
    @Nullable
    @RequiresPermission
    /* renamed from: f */
    private static String m7596f(@NonNull Context context) {
        if (!C2901d.m7653b(context, "android.permission.ACCESS_WIFI_STATE")) {
            return null;
        }
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
        return wifiManager == null ? null : wifiManager.getConnectionInfo().getBSSID();
    }

    @AnyThread
    @Contract(pure = true)
    /* renamed from: g */
    private static int m7597g() {
        return C2901d.m7624a(Runtime.getRuntime().availableProcessors(), 1, Integer.MAX_VALUE);
    }

    @Nullable
    @AnyThread
    @Contract(pure = true)
    /* renamed from: g */
    private static String m7598g(@NonNull Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        return telephonyManager == null ? null : telephonyManager.getNetworkOperatorName();
    }

    @AnyThread
    @Contract(pure = true)
    @NonNull
    /* renamed from: h */
    private static String m7599h() {
        return Locale.getDefault().getLanguage() + "-" + Locale.getDefault().getCountry();
    }

    @Nullable
    @WorkerThread
    @Contract(pure = true)
    /* renamed from: h */
    private static String m7600h(@NonNull Context context) {
        Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
        return advertisingIdInfo == null ? null : advertisingIdInfo.getId();
    }

    @AnyThread
    @Contract(pure = true)
    @NonNull
    /* renamed from: i */
    private static String m7601i() {
        return TimeZone.getDefault().getID();
    }

    @Nullable
    @AnyThread
    @Contract(pure = true)
    /* renamed from: i */
    private static String m7602i(@NonNull Context context) {
        return Secure.getString(context.getContentResolver(), "advertising_id");
    }

    @Nullable
    @AnyThread
    @Contract(pure = true)
    /* renamed from: j */
    private static Integer m7603j(@NonNull Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager == null) {
            return null;
        }
        Display defaultDisplay = windowManager.getDefaultDisplay();
        if (defaultDisplay == null) {
            return null;
        }
        Point point;
        if (VERSION.SDK_INT >= 17) {
            point = new Point();
            defaultDisplay.getRealSize(point);
            return Integer.valueOf(point.y);
        }
        point = new Point();
        defaultDisplay.getSize(point);
        return Integer.valueOf(point.y);
    }

    @WorkerThread
    @NonNull
    /* renamed from: j */
    private static JSONArray m7604j() {
        JSONArray jSONArray = new JSONArray();
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter == null || !defaultAdapter.isEnabled()) {
                return jSONArray;
            }
            String str;
            Set<BluetoothDevice> bondedDevices = defaultAdapter.getBondedDevices();
            if (bondedDevices != null) {
                for (BluetoothDevice name : bondedDevices) {
                    str = "BT-PAIRED: " + name.getName();
                    if (!C2901d.m7645a(jSONArray, str)) {
                        jSONArray.put(str);
                    }
                }
            }
            if (VERSION.SDK_INT >= 18) {
                if (defaultAdapter.getProfileConnectionState(1) == 2) {
                    str = "BT-PROFILE: HEADSET";
                    if (!C2901d.m7645a(jSONArray, "BT-PROFILE: HEADSET")) {
                        jSONArray.put("BT-PROFILE: HEADSET");
                    }
                }
                if (defaultAdapter.getProfileConnectionState(2) == 2) {
                    str = "BT-PROFILE: A2DP";
                    if (!C2901d.m7645a(jSONArray, "BT-PROFILE: A2DP")) {
                        jSONArray.put("BT-PROFILE: A2DP");
                    }
                }
                if (defaultAdapter.getProfileConnectionState(3) == 2) {
                    str = "BT-PROFILE: HEALTH";
                    if (!C2901d.m7645a(jSONArray, "BT-PROFILE: HEALTH")) {
                        jSONArray.put("BT-PROFILE: HEALTH");
                    }
                }
                if (VERSION.SDK_INT >= 18) {
                    if (defaultAdapter.getProfileConnectionState(7) == 2) {
                        str = "BT-PROFILE: GATT";
                        if (!C2901d.m7645a(jSONArray, "BT-PROFILE: GATT")) {
                            jSONArray.put("BT-PROFILE: GATT");
                        }
                    }
                    if (defaultAdapter.getProfileConnectionState(8) == 2) {
                        str = "BT-PROFILE: GATT_SERVER";
                        if (!C2901d.m7645a(jSONArray, "BT-PROFILE: GATT_SERVER")) {
                            jSONArray.put("BT-PROFILE: GATT_SERVER");
                        }
                    }
                }
                if (VERSION.SDK_INT >= 23 && defaultAdapter.getProfileConnectionState(10) == 2) {
                    str = "BT-PROFILE: SAP";
                    if (!C2901d.m7645a(jSONArray, "BT-PROFILE: SAP")) {
                        jSONArray.put("BT-PROFILE: SAP");
                    }
                }
            }
            return jSONArray;
        } catch (Throwable th) {
            Tracker.m7517a(4, "DPT", "devicesBtPair", th);
        }
    }

    @Nullable
    @AnyThread
    @Contract(pure = true)
    /* renamed from: k */
    private static Integer m7605k(@NonNull Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager == null) {
            return null;
        }
        Display defaultDisplay = windowManager.getDefaultDisplay();
        if (defaultDisplay == null) {
            return null;
        }
        Point point;
        if (VERSION.SDK_INT >= 17) {
            point = new Point();
            defaultDisplay.getRealSize(point);
            return Integer.valueOf(point.x);
        }
        point = new Point();
        defaultDisplay.getSize(point);
        return Integer.valueOf(point.x);
    }

    @AnyThread
    @Contract(pure = true)
    @NonNull
    /* renamed from: l */
    private static String m7606l(@NonNull Context context) {
        return context.getPackageName();
    }

    @AnyThread
    @Contract(pure = true)
    /* renamed from: m */
    private static int m7607m(@NonNull Context context) {
        return (int) (context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime / 1000);
    }

    @AnyThread
    @Contract(pure = true)
    @NonNull
    /* renamed from: n */
    private static String m7608n(@NonNull Context context) {
        return context.getApplicationInfo().loadLabel(context.getPackageManager()).toString() + " " + context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
    }

    @AnyThread
    @Contract(pure = true)
    @NonNull
    /* renamed from: o */
    private static String m7609o(@NonNull Context context) {
        return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
    }

    @AnyThread
    @SuppressLint({"HardwareIds"})
    @Contract(pure = true)
    @NonNull
    /* renamed from: p */
    private static String m7610p(@NonNull Context context) {
        return Secure.getString(context.getContentResolver(), "android_id");
    }

    @WorkerThread
    @Contract(pure = true)
    /* renamed from: q */
    private static boolean m7611q(@NonNull Context context) {
        try {
            return C2900c.m7612r(context);
        } catch (UnsupportedOperationException e) {
            Tracker.m7517a(4, "DPT", "getDeviceLimi", e);
            return C2900c.m7613s(context);
        }
    }

    @AnyThread
    @Contract(pure = true)
    /* renamed from: r */
    private static boolean m7612r(@NonNull Context context) {
        try {
            int i = Secure.getInt(context.getContentResolver(), "limit_ad_tracking", -1);
            if (i >= 0) {
                Tracker.m7517a(5, "DPT", "getFireDevice", "Kindle Fire");
                return i != 0;
            }
        } catch (Throwable th) {
            Tracker.m7517a(4, "DPT", "getFireDevice", th);
        }
        throw new UnsupportedOperationException("Unsupported Device");
    }

    @WorkerThread
    @Contract(pure = true)
    /* renamed from: s */
    private static boolean m7613s(@NonNull Context context) {
        try {
            Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
            if (advertisingIdInfo != null) {
                return advertisingIdInfo.isLimitAdTrackingEnabled();
            }
        } catch (Throwable th) {
            Tracker.m7517a(4, "DPT", "getGoogleDevi", th);
        }
        throw new UnsupportedOperationException("Unsupported Device");
    }

    @Nullable
    @WorkerThread
    @Contract(pure = true)
    /* renamed from: t */
    private static String m7614t(@NonNull Context context) {
        String str = null;
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider"), new String[]{Facebook.ATTRIBUTION_ID_COLUMN_NAME}, null, null, null);
        if (query != null) {
            if (query.moveToFirst()) {
                int columnIndex = query.getColumnIndex(Facebook.ATTRIBUTION_ID_COLUMN_NAME);
                if (columnIndex != -1) {
                    str = query.getString(columnIndex);
                }
            }
            query.close();
        }
        return str;
    }

    @AnyThread
    @Contract(pure = true)
    @Nullable
    @RequiresPermission
    /* renamed from: u */
    private static JSONObject m7615u(@NonNull Context context) {
        if (C2901d.m7642a(context, "android.permission.GET_ACCOUNTS")) {
            JSONArray jSONArray = new JSONArray();
            try {
                Class cls = Class.forName("android.accounts.AccountManager");
                Field field = Class.forName("android.accounts.Account").getField("name");
                Object invoke = cls.getMethod("get", new Class[]{Context.class}).invoke(null, new Object[]{context});
                for (Object obj : (Object[]) invoke.getClass().getMethod("getAccounts", new Class[0]).invoke(invoke, new Object[0])) {
                    Object obj2 = (String) field.get(obj2);
                    if (Patterns.EMAIL_ADDRESS.matcher(obj2).matches()) {
                        C2901d.m7635a(obj2, jSONArray, false);
                    }
                }
            } catch (Throwable th) {
                Tracker.m7517a(4, "DPT", "getIds", th);
            }
            if (jSONArray.length() <= 0) {
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            C2901d.m7636a("email", C2901d.m7631a(jSONArray).replaceAll("\"", ""), jSONObject);
            return jSONObject;
        }
        Tracker.m7517a(4, "DPT", "ids", "Missing Permission: android.permission.GET_ACCOUNTS");
        return null;
    }

    @AnyThread
    @Contract(pure = true)
    /* renamed from: v */
    private static int m7616v(@NonNull Context context) {
        return context.getResources().getDisplayMetrics().densityDpi;
    }

    @Nullable
    @AnyThread
    @Contract(pure = true)
    /* renamed from: w */
    private static Double m7617w(@NonNull Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (displayMetrics == null) {
            return null;
        }
        return Double.valueOf(((double) Math.round(Math.sqrt(Math.pow((double) (((float) displayMetrics.heightPixels) / displayMetrics.ydpi), 2.0d) + Math.pow((double) (((float) displayMetrics.widthPixels) / displayMetrics.xdpi), 2.0d)) * 10.0d)) / 10.0d);
    }

    @Nullable
    @AnyThread
    @Contract(pure = true)
    /* renamed from: x */
    private static String m7618x(@NonNull Context context) {
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver == null || !registerReceiver.hasExtra("status")) {
            return null;
        }
        switch (registerReceiver.getIntExtra("status", -1)) {
            case 2:
                return "charging";
            case 3:
                return "discharging";
            case 4:
                return "not_charging";
            case 5:
                return "full";
            default:
                return "unknown";
        }
    }

    @Nullable
    @AnyThread
    @Contract(pure = true)
    /* renamed from: y */
    private static Integer m7619y(@NonNull Context context) {
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        return (registerReceiver == null || !registerReceiver.hasExtra("level")) ? null : Integer.valueOf(C2901d.m7624a(registerReceiver.getIntExtra("level", -1), 0, 100));
    }

    @Nullable
    @AnyThread
    @Contract(pure = true)
    /* renamed from: z */
    private static Integer m7620z(@NonNull Context context) {
        int i = (C2901d.m7642a(context, "android.permission.ACCESS_COARSE_LOCATION") || C2901d.m7642a(context, "android.permission.ACCESS_FINE_LOCATION")) ? 1 : 0;
        if (i == 0) {
            return null;
        }
        if (VERSION.SDK_INT < 17) {
            return null;
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            return null;
        }
        List<CellInfo> allCellInfo = telephonyManager.getAllCellInfo();
        if (allCellInfo == null) {
            return null;
        }
        for (CellInfo cellInfo : allCellInfo) {
            if (cellInfo.isRegistered()) {
                if (!(cellInfo instanceof CellInfoGsm)) {
                    if (!(cellInfo instanceof CellInfoCdma)) {
                        if (!(cellInfo instanceof CellInfoLte)) {
                            if (VERSION.SDK_INT >= 18 && (cellInfo instanceof CellInfoWcdma)) {
                                i = ((CellInfoWcdma) cellInfo).getCellSignalStrength().getLevel();
                                break;
                            }
                        }
                        i = ((CellInfoLte) cellInfo).getCellSignalStrength().getLevel();
                        break;
                    }
                    i = ((CellInfoCdma) cellInfo).getCellSignalStrength().getLevel();
                    break;
                }
                i = ((CellInfoGsm) cellInfo).getCellSignalStrength().getLevel();
                break;
            }
        }
        i = -1;
        return i == -1 ? null : Integer.valueOf(C2901d.m7624a(i * 25, 0, 100));
    }

    /* Access modifiers changed, original: final */
    @Nullable
    @AnyThread
    /* renamed from: a */
    public final Object mo26563a(@NonNull C2901d c2901d, boolean z, boolean z2) {
        Object c = c2901d.mo26572c(this.f6582b);
        if (c == null) {
            return null;
        }
        if (z) {
            return c;
        }
        if (this.f6583c == -1) {
            return !z2 ? null : c;
        } else {
            Integer c2 = C2901d.m7656c(c2901d.mo26572c(this.f6582b + "_ts"));
            return (c2 == null || ((long) (c2.intValue() + this.f6583c)) < C2901d.m7626a() / 1000) ? null : c;
        }
    }
}
