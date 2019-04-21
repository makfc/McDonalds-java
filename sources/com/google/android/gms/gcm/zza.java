package com.google.android.gms.gcm;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.os.SystemClock;
import android.support.p000v4.app.NotificationCompat.Builder;
import android.text.TextUtils;
import android.util.Log;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import java.util.Iterator;
import java.util.List;
import java.util.MissingFormatArgumentException;
import org.json.JSONArray;
import org.json.JSONException;

class zza {
    static zza zzaSN;
    private final Context mContext;

    private class zza extends IllegalArgumentException {
    }

    private zza(Context context) {
        this.mContext = context.getApplicationContext();
    }

    static boolean zzA(Bundle bundle) {
        return "1".equals(zzf(bundle, "gcm.n.e")) || zzf(bundle, "gcm.n.icon") != null;
    }

    static void zzB(Bundle bundle) {
        String str;
        Bundle bundle2 = new Bundle();
        Iterator it = bundle.keySet().iterator();
        while (it.hasNext()) {
            str = (String) it.next();
            String string = bundle.getString(str);
            if (str.startsWith("gcm.notification.")) {
                str = str.replace("gcm.notification.", "gcm.n.");
            }
            if (str.startsWith("gcm.n.")) {
                if (!"gcm.n.e".equals(str)) {
                    bundle2.putString(str.substring("gcm.n.".length()), string);
                }
                it.remove();
            }
        }
        str = bundle2.getString("sound2");
        if (str != null) {
            bundle2.remove("sound2");
            bundle2.putString("sound", str);
        }
        if (!bundle2.isEmpty()) {
            bundle.putBundle("notification", bundle2);
        }
    }

    private int zzBN() {
        return (int) SystemClock.uptimeMillis();
    }

    private Notification zzD(Bundle bundle) {
        String zzg = zzg(bundle, "gcm.n.title");
        String zzg2 = zzg(bundle, "gcm.n.body");
        int zzex = zzex(zzf(bundle, "gcm.n.icon"));
        String zzf = zzf(bundle, "gcm.n.color");
        Uri zzey = zzey(zzf(bundle, "gcm.n.sound2"));
        PendingIntent zzE = zzE(bundle);
        Builder smallIcon = new Builder(this.mContext).setAutoCancel(true).setSmallIcon(zzex);
        if (TextUtils.isEmpty(zzg)) {
            smallIcon.setContentTitle(this.mContext.getApplicationInfo().loadLabel(this.mContext.getPackageManager()));
        } else {
            smallIcon.setContentTitle(zzg);
        }
        if (!TextUtils.isEmpty(zzg2)) {
            smallIcon.setContentText(zzg2);
        }
        if (!TextUtils.isEmpty(zzf)) {
            smallIcon.setColor(Color.parseColor(zzf));
        }
        if (zzey != null) {
            smallIcon.setSound(zzey);
        }
        if (zzE != null) {
            smallIcon.setContentIntent(zzE);
        }
        return smallIcon.build();
    }

    private PendingIntent zzE(Bundle bundle) {
        Intent intent;
        String zzf = zzf(bundle, "gcm.n.click_action");
        Intent launchIntentForPackage;
        if (TextUtils.isEmpty(zzf)) {
            launchIntentForPackage = this.mContext.getPackageManager().getLaunchIntentForPackage(this.mContext.getPackageName());
            if (launchIntentForPackage == null) {
                Log.w("GcmNotification", "No activity found to launch app");
                return null;
            }
            intent = launchIntentForPackage;
        } else {
            launchIntentForPackage = new Intent(zzf);
            launchIntentForPackage.setPackage(this.mContext.getPackageName());
            launchIntentForPackage.setFlags(268435456);
            intent = launchIntentForPackage;
        }
        Bundle bundle2 = new Bundle(bundle);
        GcmListenerService.zzz(bundle2);
        intent.putExtras(bundle2);
        for (String str : bundle2.keySet()) {
            if (str.startsWith("gcm.n.") || str.startsWith("gcm.notification.")) {
                intent.removeExtra(str);
            }
        }
        return PendingIntent.getActivity(this.mContext, zzBN(), intent, 1073741824);
    }

    private void zza(String str, Notification notification) {
        if (Log.isLoggable("GcmNotification", 3)) {
            Log.d("GcmNotification", "Showing notification");
        }
        NotificationManager notificationManager = (NotificationManager) this.mContext.getSystemService("notification");
        if (TextUtils.isEmpty(str)) {
            str = "GCM-Notification:" + SystemClock.uptimeMillis();
        }
        notificationManager.notify(str, 0, notification);
    }

    static synchronized zza zzaQ(Context context) {
        zza zza;
        synchronized (zza.class) {
            if (zzaSN == null) {
                zzaSN = new zza(context);
            }
            zza = zzaSN;
        }
        return zza;
    }

    static boolean zzaR(Context context) {
        if (((KeyguardManager) context.getSystemService("keyguard")).inKeyguardRestrictedInputMode()) {
            return false;
        }
        int myPid = Process.myPid();
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == myPid) {
                return runningAppProcessInfo.importance == 100;
            }
        }
        return false;
    }

    private String zzew(String str) {
        return str.substring("gcm.n.".length());
    }

    private int zzex(String str) {
        int identifier;
        if (!TextUtils.isEmpty(str)) {
            Resources resources = this.mContext.getResources();
            identifier = resources.getIdentifier(str, "drawable", this.mContext.getPackageName());
            if (identifier != 0) {
                return identifier;
            }
            identifier = resources.getIdentifier(str, "mipmap", this.mContext.getPackageName());
            if (identifier != 0) {
                return identifier;
            }
            Log.w("GcmNotification", new StringBuilder(String.valueOf(str).length() + 57).append("Icon resource ").append(str).append(" not found. Notification will use app icon.").toString());
        }
        identifier = this.mContext.getApplicationInfo().icon;
        return identifier == 0 ? 17301651 : identifier;
    }

    private Uri zzey(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if ("default".equals(str) || this.mContext.getResources().getIdentifier(str, "raw", this.mContext.getPackageName()) == 0) {
            return RingtoneManager.getDefaultUri(2);
        }
        String valueOf = String.valueOf("android.resource://");
        String valueOf2 = String.valueOf(this.mContext.getPackageName());
        return Uri.parse(new StringBuilder(((String.valueOf(valueOf).length() + 5) + String.valueOf(valueOf2).length()) + String.valueOf(str).length()).append(valueOf).append(valueOf2).append("/raw/").append(str).toString());
    }

    static String zzf(Bundle bundle, String str) {
        String string = bundle.getString(str);
        return string == null ? bundle.getString(str.replace("gcm.n.", "gcm.notification.")) : string;
    }

    private String zzg(Bundle bundle, String str) {
        String zzf = zzf(bundle, str);
        if (!TextUtils.isEmpty(zzf)) {
            return zzf;
        }
        String valueOf = String.valueOf(str);
        zzf = String.valueOf("_loc_key");
        valueOf = zzf(bundle, zzf.length() != 0 ? valueOf.concat(zzf) : new String(valueOf));
        if (TextUtils.isEmpty(valueOf)) {
            return null;
        }
        Resources resources = this.mContext.getResources();
        int identifier = resources.getIdentifier(valueOf, "string", this.mContext.getPackageName());
        String str2;
        if (identifier == 0) {
            str2 = "GcmNotification";
            String valueOf2 = String.valueOf(str);
            zzf = String.valueOf("_loc_key");
            zzf = String.valueOf(zzew(zzf.length() != 0 ? valueOf2.concat(zzf) : new String(valueOf2)));
            Log.w(str2, new StringBuilder((String.valueOf(zzf).length() + 49) + String.valueOf(valueOf).length()).append(zzf).append(" resource not found: ").append(valueOf).append(" Default value will be used.").toString());
            return null;
        }
        String valueOf3 = String.valueOf(str);
        zzf = String.valueOf("_loc_args");
        valueOf3 = zzf(bundle, zzf.length() != 0 ? valueOf3.concat(zzf) : new String(valueOf3));
        if (TextUtils.isEmpty(valueOf3)) {
            return resources.getString(identifier);
        }
        try {
            JSONArray init = JSONArrayInstrumentation.init(valueOf3);
            String[] strArr = new String[init.length()];
            for (int i = 0; i < strArr.length; i++) {
                strArr[i] = init.opt(i);
            }
            return resources.getString(identifier, strArr);
        } catch (JSONException e) {
            valueOf = "GcmNotification";
            str2 = String.valueOf(str);
            zzf = String.valueOf("_loc_args");
            zzf = String.valueOf(zzew(zzf.length() != 0 ? str2.concat(zzf) : new String(str2)));
            Log.w(valueOf, new StringBuilder((String.valueOf(zzf).length() + 41) + String.valueOf(valueOf3).length()).append("Malformed ").append(zzf).append(": ").append(valueOf3).append("  Default value will be used.").toString());
            return null;
        } catch (MissingFormatArgumentException e2) {
            Log.w("GcmNotification", new StringBuilder((String.valueOf(valueOf).length() + 58) + String.valueOf(valueOf3).length()).append("Missing format argument for ").append(valueOf).append(": ").append(valueOf3).append(" Default value will be used.").toString(), e2);
            return null;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean zzC(Bundle bundle) {
        try {
            zza(zzf(bundle, "gcm.n.tag"), zzD(bundle));
            return true;
        } catch (zza e) {
            String str = "GcmNotification";
            String str2 = "Failed to show notification: ";
            String valueOf = String.valueOf(e.getMessage());
            Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            return false;
        }
    }
}
