package com.google.firebase.iid;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.support.p000v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import com.autonavi.amap.mapcore.VTMCDataCache;
import java.util.LinkedList;
import java.util.Queue;

public final class FirebaseInstanceIdInternalReceiver extends WakefulBroadcastReceiver {
    private static final Queue<Intent> zzbSG = new LinkedList();
    private static final Queue<Intent> zzbSH = new LinkedList();

    public static Intent zzUq() {
        return (Intent) zzbSG.poll();
    }

    private static Intent zza(Context context, String str, Intent intent) {
        Intent intent2 = new Intent(context, FirebaseInstanceIdInternalReceiver.class);
        intent2.setAction(str);
        intent2.putExtra("wrapped_intent", intent);
        return intent2;
    }

    static int zzb(Context context, String str, Intent intent) {
        Object obj = -1;
        switch (str.hashCode()) {
            case -842411455:
                if (str.equals("com.google.firebase.INSTANCE_ID_EVENT")) {
                    obj = null;
                    break;
                }
                break;
            case 41532704:
                if (str.equals("com.google.firebase.MESSAGING_EVENT")) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                zzbSG.offer(intent);
                break;
            case 1:
                zzbSH.offer(intent);
                break;
            default:
                String str2 = "FirebaseInstanceId";
                String str3 = "Unknown service action: ";
                String valueOf = String.valueOf(str);
                Log.w(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
                return VTMCDataCache.MAXSIZE;
        }
        Intent intent2 = new Intent(str);
        intent2.setPackage(context.getPackageName());
        return zzj(context, intent2);
    }

    private static void zzg(Context context, Intent intent) {
        ResolveInfo resolveService = context.getPackageManager().resolveService(intent, 0);
        if (resolveService == null || resolveService.serviceInfo == null) {
            Log.e("FirebaseInstanceId", "Failed to resolve target intent service, skipping classname enforcement");
            return;
        }
        ServiceInfo serviceInfo = resolveService.serviceInfo;
        String valueOf;
        String valueOf2;
        if (!context.getPackageName().equals(serviceInfo.packageName) || serviceInfo.name == null) {
            valueOf = String.valueOf(serviceInfo.packageName);
            valueOf2 = String.valueOf(serviceInfo.name);
            Log.e("FirebaseInstanceId", new StringBuilder((String.valueOf(valueOf).length() + 94) + String.valueOf(valueOf2).length()).append("Error resolving target intent service, skipping classname enforcement. Resolved service was: ").append(valueOf).append("/").append(valueOf2).toString());
            return;
        }
        String valueOf3;
        Object obj = serviceInfo.name;
        if (obj.startsWith(".")) {
            valueOf3 = String.valueOf(context.getPackageName());
            valueOf2 = String.valueOf(obj);
            obj = valueOf2.length() != 0 ? valueOf3.concat(valueOf2) : new String(valueOf3);
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            valueOf = "FirebaseInstanceId";
            String str = "Restricting intent to a specific service: ";
            valueOf3 = String.valueOf(obj);
            Log.d(valueOf, valueOf3.length() != 0 ? str.concat(valueOf3) : new String(str));
        }
        intent.setClassName(context.getPackageName(), obj);
    }

    public static Intent zzh(Context context, Intent intent) {
        return zza(context, "com.google.firebase.INSTANCE_ID_EVENT", intent);
    }

    private static int zzj(Context context, Intent intent) {
        zzg(context, intent);
        try {
            ComponentName startWakefulService;
            if (context.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") == 0) {
                startWakefulService = WakefulBroadcastReceiver.startWakefulService(context, intent);
            } else {
                startWakefulService = context.startService(intent);
                Log.d("FirebaseInstanceId", "Missing wake lock permission, service start may be delayed");
            }
            if (startWakefulService != null) {
                return -1;
            }
            Log.e("FirebaseInstanceId", "Error while delivering the message: ServiceIntent not found.");
            return 404;
        } catch (SecurityException e) {
            Log.e("FirebaseInstanceId", "Error while delivering the message to the serviceIntent", e);
            return 401;
        }
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            Intent intent2 = (Intent) intent.getParcelableExtra("wrapped_intent");
            if (intent2 == null) {
                Log.w("FirebaseInstanceId", "Missing wrapped intent");
            } else {
                zzb(context, intent.getAction(), intent2);
            }
        }
    }
}
