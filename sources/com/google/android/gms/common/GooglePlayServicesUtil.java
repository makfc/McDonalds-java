package com.google.android.gms.common;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.Notification;
import android.app.Notification.BigTextStyle;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentActivity;
import android.support.p000v4.app.NotificationCompat;
import android.support.p000v4.app.NotificationCompatExtras;
import android.util.Log;
import android.util.TypedValue;
import com.google.android.gms.C2063R;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzh;
import com.google.android.gms.common.internal.zzi;
import com.google.android.gms.common.util.zzs;
import com.newrelic.agent.android.payload.PayloadController;

public final class GooglePlayServicesUtil extends zze {
    public static final String GMS_ERROR_DIALOG = "GooglePlayServicesErrorDialog";
    @Deprecated
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    @Deprecated
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = zze.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";

    private static class zza extends Handler {
        private final Context zztm;

        zza(Context context) {
            super(Looper.myLooper() == null ? Looper.getMainLooper() : Looper.myLooper());
            this.zztm = context.getApplicationContext();
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.zztm);
                    if (GooglePlayServicesUtil.isUserRecoverableError(isGooglePlayServicesAvailable)) {
                        GooglePlayServicesUtil.zza(isGooglePlayServicesAvailable, this.zztm);
                        return;
                    }
                    return;
                default:
                    Log.w("GooglePlayServicesUtil", "Don't know how to handle this message: " + message.what);
                    return;
            }
        }
    }

    private GooglePlayServicesUtil() {
    }

    @Deprecated
    public static Dialog getErrorDialog(int i, Activity activity, int i2) {
        return getErrorDialog(i, activity, i2, null);
    }

    @Deprecated
    public static Dialog getErrorDialog(int i, Activity activity, int i2, OnCancelListener onCancelListener) {
        return zza(i, activity, zzi.zza(activity, GoogleApiAvailability.getInstance().zza((Context) activity, i, "d"), i2), onCancelListener);
    }

    @Deprecated
    public static PendingIntent getErrorPendingIntent(int i, Context context, int i2) {
        return zze.getErrorPendingIntent(i, context, i2);
    }

    @Deprecated
    public static String getErrorString(int i) {
        return zze.getErrorString(i);
    }

    @Deprecated
    public static String getOpenSourceSoftwareLicenseInfo(Context context) {
        return zze.getOpenSourceSoftwareLicenseInfo(context);
    }

    public static Context getRemoteContext(Context context) {
        return zze.getRemoteContext(context);
    }

    public static Resources getRemoteResource(Context context) {
        return zze.getRemoteResource(context);
    }

    @Deprecated
    public static int isGooglePlayServicesAvailable(Context context) {
        return zze.isGooglePlayServicesAvailable(context);
    }

    @Deprecated
    public static boolean isUserRecoverableError(int i) {
        return zze.isUserRecoverableError(i);
    }

    @Deprecated
    public static boolean showErrorDialogFragment(int i, Activity activity, int i2) {
        return showErrorDialogFragment(i, activity, i2, null);
    }

    @Deprecated
    public static boolean showErrorDialogFragment(int i, Activity activity, int i2, OnCancelListener onCancelListener) {
        return showErrorDialogFragment(i, activity, null, i2, onCancelListener);
    }

    public static boolean showErrorDialogFragment(int i, Activity activity, Fragment fragment, int i2, OnCancelListener onCancelListener) {
        Intent zza = GoogleApiAvailability.getInstance().zza((Context) activity, i, "d");
        Dialog zza2 = zza(i, activity, fragment == null ? zzi.zza(activity, zza, i2) : zzi.zza(fragment, zza, i2), onCancelListener);
        if (zza2 == null) {
            return false;
        }
        zza(activity, onCancelListener, GMS_ERROR_DIALOG, zza2);
        return true;
    }

    @Deprecated
    public static void showErrorNotification(int i, Context context) {
        if (com.google.android.gms.common.util.zzi.zzaB(context) && i == 2) {
            i = 42;
        }
        if (zzc(context, i) || zzd(context, i)) {
            zzai(context);
        } else {
            zza(i, context);
        }
    }

    @TargetApi(14)
    public static Dialog zza(int i, Activity activity, zzi zzi, OnCancelListener onCancelListener) {
        Builder builder = null;
        if (i == 0) {
            return null;
        }
        if (com.google.android.gms.common.util.zzi.zzaB(activity) && i == 2) {
            i = 42;
        }
        if (zzc(activity, i)) {
            i = 18;
        }
        if (zzs.zzva()) {
            TypedValue typedValue = new TypedValue();
            activity.getTheme().resolveAttribute(16843529, typedValue, true);
            if ("Theme.Dialog.Alert".equals(activity.getResources().getResourceEntryName(typedValue.resourceId))) {
                builder = new Builder(activity, 5);
            }
        }
        if (builder == null) {
            builder = new Builder(activity);
        }
        builder.setMessage(zzh.zzb(activity, i, zze.zzam(activity)));
        if (onCancelListener != null) {
            builder.setOnCancelListener(onCancelListener);
        }
        String zzh = zzh.zzh(activity, i);
        if (zzh != null) {
            builder.setPositiveButton(zzh, zzi);
        }
        zzh = zzh.zzf(activity, i);
        if (zzh != null) {
            builder.setTitle(zzh);
        }
        return builder.create();
    }

    private static void zza(int i, Context context) {
        zza(i, context, null);
    }

    static void zza(int i, Context context, PendingIntent pendingIntent) {
        zza(i, context, null, pendingIntent);
    }

    private static void zza(int i, Context context, String str) {
        zza(i, context, str, GoogleApiAvailability.getInstance().zza(context, i, 0, "n"));
    }

    @TargetApi(20)
    private static void zza(int i, Context context, String str, PendingIntent pendingIntent) {
        Notification build;
        int i2;
        Resources resources = context.getResources();
        String zzam = zze.zzam(context);
        Object zzg = zzh.zzg(context, i);
        if (zzg == null) {
            zzg = resources.getString(C2063R.string.common_google_play_services_notification_ticker);
        }
        zzam = zzh.zzc(context, i, zzam);
        if (com.google.android.gms.common.util.zzi.zzaB(context)) {
            zzaa.zzai(zzs.zzvb());
            build = new Notification.Builder(context).setSmallIcon(C2063R.C2061drawable.common_ic_googleplayservices).setPriority(2).setAutoCancel(true).setStyle(new BigTextStyle().bigText(new StringBuilder((String.valueOf(zzg).length() + 1) + String.valueOf(zzam).length()).append(zzg).append(" ").append(zzam).toString())).addAction(C2063R.C2061drawable.common_full_open_on_phone, resources.getString(C2063R.string.common_open_on_phone), pendingIntent).build();
        } else {
            String string = resources.getString(C2063R.string.common_google_play_services_notification_ticker);
            if (zzs.zzuX()) {
                Notification build2;
                Notification.Builder autoCancel = new Notification.Builder(context).setSmallIcon(17301642).setContentTitle(zzg).setContentText(zzam).setContentIntent(pendingIntent).setTicker(string).setAutoCancel(true);
                if (zzs.zzvf()) {
                    autoCancel.setLocalOnly(true);
                }
                if (zzs.zzvb()) {
                    autoCancel.setStyle(new BigTextStyle().bigText(zzam));
                    build2 = autoCancel.build();
                } else {
                    build2 = autoCancel.getNotification();
                }
                if (VERSION.SDK_INT == 19) {
                    build2.extras.putBoolean(NotificationCompatExtras.EXTRA_LOCAL_ONLY, true);
                }
                build = build2;
            } else {
                build = new NotificationCompat.Builder(context).setSmallIcon(17301642).setTicker(string).setWhen(System.currentTimeMillis()).setAutoCancel(true).setContentIntent(pendingIntent).setContentTitle(zzg).setContentText(zzam).build();
            }
        }
        if (zze.zzbD(i)) {
            zzakA.set(false);
            i2 = 10436;
        } else {
            i2 = 39789;
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if (str != null) {
            notificationManager.notify(str, i2, build);
        } else {
            notificationManager.notify(i2, build);
        }
    }

    @TargetApi(11)
    public static void zza(Activity activity, OnCancelListener onCancelListener, String str, @NonNull Dialog dialog) {
        boolean z;
        try {
            z = activity instanceof FragmentActivity;
        } catch (NoClassDefFoundError e) {
            z = false;
        }
        if (z) {
            SupportErrorDialogFragment.newInstance(dialog, onCancelListener).show(((FragmentActivity) activity).getSupportFragmentManager(), str);
        } else if (zzs.zzuX()) {
            ErrorDialogFragment.newInstance(dialog, onCancelListener).show(activity.getFragmentManager(), str);
        } else {
            throw new RuntimeException("This Activity does not support Fragments.");
        }
    }

    private static void zzai(Context context) {
        zza zza = new zza(context);
        zza.sendMessageDelayed(zza.obtainMessage(1), PayloadController.PAYLOAD_REQUEUE_PERIOD_MS);
    }

    @Deprecated
    public static Intent zzbC(int i) {
        return zze.zzbC(i);
    }

    @Deprecated
    public static boolean zzc(Context context, int i) {
        return zze.zzc(context, i);
    }

    @Deprecated
    public static boolean zzd(Context context, int i) {
        return zze.zzd(context, i);
    }
}
