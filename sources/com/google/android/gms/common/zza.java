package com.google.android.gms.common;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.common.internal.zzaa;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zza implements ServiceConnection {
    boolean zzakh = false;
    private final BlockingQueue<IBinder> zzaki = new LinkedBlockingQueue();

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.zzaki.add(iBinder);
    }

    public void onServiceDisconnected(ComponentName componentName) {
    }

    public IBinder zza(long j, TimeUnit timeUnit) throws InterruptedException, TimeoutException {
        zzaa.zzdd("BlockingServiceConnection.getServiceWithTimeout() called on main thread");
        if (this.zzakh) {
            throw new IllegalStateException("Cannot call get on this connection more than once");
        }
        this.zzakh = true;
        IBinder iBinder = (IBinder) this.zzaki.poll(j, timeUnit);
        if (iBinder != null) {
            return iBinder;
        }
        throw new TimeoutException("Timed out waiting for the service connection");
    }

    public IBinder zzqU() throws InterruptedException {
        zzaa.zzdd("BlockingServiceConnection.getService() called on main thread");
        if (this.zzakh) {
            throw new IllegalStateException("Cannot call get on this connection more than once");
        }
        this.zzakh = true;
        return (IBinder) this.zzaki.take();
    }
}
