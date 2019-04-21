package com.mcdonalds.sdk.services;

import android.app.Service;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.modules.BaseModule;
import com.mcdonalds.sdk.modules.ModuleManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RonaldServiceConnection implements ServiceConnection {
    private Map<String, List<AsyncListener<BaseModule>>> mPendingListeners = new HashMap();
    private Service mService;

    public void onServiceConnected(ComponentName name, IBinder service) {
        processPendingListeners();
    }

    private void processPendingListeners() {
        if (this.mPendingListeners != null) {
            Map<String, List<AsyncListener<BaseModule>>> pendingListeners = new HashMap();
            pendingListeners.putAll(this.mPendingListeners);
            this.mPendingListeners.clear();
            for (String moduleName : pendingListeners.keySet()) {
                BaseModule module = (BaseModule) ModuleManager.getModule(moduleName);
                for (AsyncListener<BaseModule> listener : (List) pendingListeners.get(moduleName)) {
                    listener.onResponse(module, null, null);
                }
            }
            pendingListeners.clear();
        }
    }

    public void onServiceDisconnected(ComponentName name) {
        this.mService = null;
    }

    @Deprecated
    public void getModule(final String moduleName, final AsyncListener<? extends BaseModule> listener) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                BaseModule module = (BaseModule) ModuleManager.getModule(moduleName);
                if (listener != null) {
                    listener.onResponse(module, null, null);
                }
            }
        });
    }
}
