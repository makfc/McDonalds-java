package com.google.android.gms.gcm;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build.VERSION;
import android.support.p000v4.content.WakefulBroadcastReceiver;
import android.util.Base64;
import android.util.Log;
import com.autonavi.amap.mapcore.VTMCDataCache;
import com.mcdonalds.sdk.modules.notification.PushConstants;

public class GcmReceiver extends WakefulBroadcastReceiver {
    private static String zzaSR = "gcm.googleapis.com/refresh";

    private void zzf(Context context, Intent intent) {
        if (isOrderedBroadcast()) {
            setResultCode(VTMCDataCache.MAXSIZE);
        }
        zzg(context, intent);
        try {
            ComponentName startWakefulService;
            if (context.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") == 0) {
                startWakefulService = WakefulBroadcastReceiver.startWakefulService(context, intent);
            } else {
                startWakefulService = context.startService(intent);
                Log.d("GcmReceiver", "Missing wake lock permission, service start may be delayed");
            }
            if (startWakefulService == null) {
                Log.e("GcmReceiver", "Error while delivering the message: ServiceIntent not found.");
                if (isOrderedBroadcast()) {
                    setResultCode(404);
                }
            } else if (isOrderedBroadcast()) {
                setResultCode(-1);
            }
        } catch (SecurityException e) {
            Log.e("GcmReceiver", "Error while delivering the message to the serviceIntent", e);
            if (isOrderedBroadcast()) {
                setResultCode(401);
            }
        }
    }

    private void zzg(Context context, Intent intent) {
        ResolveInfo resolveService = context.getPackageManager().resolveService(intent, 0);
        if (resolveService == null || resolveService.serviceInfo == null) {
            Log.e("GcmReceiver", "Failed to resolve target intent service, skipping classname enforcement");
            return;
        }
        ServiceInfo serviceInfo = resolveService.serviceInfo;
        String valueOf;
        String valueOf2;
        if (!context.getPackageName().equals(serviceInfo.packageName) || serviceInfo.name == null) {
            valueOf = String.valueOf(serviceInfo.packageName);
            valueOf2 = String.valueOf(serviceInfo.name);
            Log.e("GcmReceiver", new StringBuilder((String.valueOf(valueOf).length() + 94) + String.valueOf(valueOf2).length()).append("Error resolving target intent service, skipping classname enforcement. Resolved service was: ").append(valueOf).append("/").append(valueOf2).toString());
            return;
        }
        String valueOf3;
        Object obj = serviceInfo.name;
        if (obj.startsWith(".")) {
            valueOf3 = String.valueOf(context.getPackageName());
            valueOf2 = String.valueOf(obj);
            obj = valueOf2.length() != 0 ? valueOf3.concat(valueOf2) : new String(valueOf3);
        }
        if (Log.isLoggable("GcmReceiver", 3)) {
            valueOf = "GcmReceiver";
            String str = "Restricting intent to a specific service: ";
            valueOf3 = String.valueOf(obj);
            Log.d(valueOf, valueOf3.length() != 0 ? str.concat(valueOf3) : new String(str));
        }
        intent.setClassName(context.getPackageName(), obj);
    }

    public void onReceive(Context context, Intent intent) {
        intent.setComponent(null);
        intent.setPackage(context.getPackageName());
        if (VERSION.SDK_INT <= 18) {
            intent.removeCategory(context.getPackageName());
        }
        String stringExtra = intent.getStringExtra(PushConstants.FROM_ID);
        if ("com.google.android.c2dm.intent.REGISTRATION".equals(intent.getAction()) || "google.com/iid".equals(stringExtra) || zzaSR.equals(stringExtra)) {
            intent.setAction("com.google.android.gms.iid.InstanceID");
        }
        stringExtra = intent.getStringExtra("gcm.rawData64");
        if (stringExtra != null) {
            intent.putExtra("rawData", Base64.decode(stringExtra, 0));
            intent.removeExtra("gcm.rawData64");
        }
        if ("com.google.android.c2dm.intent.RECEIVE".equals(intent.getAction())) {
            zze(context, intent);
        } else {
            zzf(context, intent);
        }
        if (isOrderedBroadcast() && getResultCode() == 0) {
            setResultCode(-1);
        }
    }

    public void zze(Context context, Intent intent) {
        zzf(context, intent);
    }
}
