package com.google.android.gms.gcm;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class GoogleCloudMessaging {
    public static int zzaSW = 5000000;
    public static int zzaSX = 6500000;
    public static int zzaSY = 7000000;
    private static final AtomicInteger zzaTc = new AtomicInteger(1);
    private Map<String, Handler> zzaTb = Collections.synchronizedMap(new HashMap());
    private final BlockingQueue<Intent> zzaTd = new LinkedBlockingQueue();
    final Messenger zzaTe = new Messenger(new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            if (message == null || !(message.obj instanceof Intent)) {
                Log.w("GCM", "Dropping invalid message");
            }
            Intent intent = (Intent) message.obj;
            if ("com.google.android.c2dm.intent.REGISTRATION".equals(intent.getAction())) {
                GoogleCloudMessaging.this.zzaTd.add(intent);
            } else if (!GoogleCloudMessaging.this.zzp(intent)) {
                intent.setPackage(GoogleCloudMessaging.this.zzov.getPackageName());
                GoogleCloudMessaging.this.zzov.sendBroadcast(intent);
            }
        }
    });
    private Context zzov;

    private boolean zzp(Intent intent) {
        Object stringExtra = intent.getStringExtra("In-Reply-To");
        if (stringExtra == null && intent.hasExtra("error")) {
            stringExtra = intent.getStringExtra("google.message_id");
        }
        if (stringExtra != null) {
            Handler handler = (Handler) this.zzaTb.remove(stringExtra);
            if (handler != null) {
                Message obtain = Message.obtain();
                obtain.obj = intent;
                return handler.sendMessage(obtain);
            }
        }
        return false;
    }
}
