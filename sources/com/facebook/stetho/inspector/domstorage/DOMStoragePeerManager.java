package com.facebook.stetho.inspector.domstorage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.inspector.helper.ChromePeerManager;
import com.facebook.stetho.inspector.helper.PeerRegistrationListener;
import com.facebook.stetho.inspector.helper.PeersRegisteredListener;
import com.facebook.stetho.inspector.protocol.module.DOMStorage.DomStorageItemAddedParams;
import com.facebook.stetho.inspector.protocol.module.DOMStorage.DomStorageItemRemovedParams;
import com.facebook.stetho.inspector.protocol.module.DOMStorage.DomStorageItemUpdatedParams;
import com.facebook.stetho.inspector.protocol.module.DOMStorage.StorageId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class DOMStoragePeerManager extends ChromePeerManager {
    private final Context mContext;
    private final PeerRegistrationListener mPeerListener = new C19781();

    /* renamed from: com.facebook.stetho.inspector.domstorage.DOMStoragePeerManager$1 */
    class C19781 extends PeersRegisteredListener {
        private final List<DevToolsSharedPreferencesListener> mPrefsListeners = new ArrayList();

        C19781() {
        }

        /* Access modifiers changed, original: protected|declared_synchronized */
        public synchronized void onFirstPeerRegistered() {
            for (String tag : SharedPreferencesHelper.getSharedPreferenceTags(DOMStoragePeerManager.this.mContext)) {
                SharedPreferences prefs = DOMStoragePeerManager.this.mContext.getSharedPreferences(tag, 0);
                DevToolsSharedPreferencesListener listener = new DevToolsSharedPreferencesListener(prefs, tag);
                prefs.registerOnSharedPreferenceChangeListener(listener);
                this.mPrefsListeners.add(listener);
            }
        }

        /* Access modifiers changed, original: protected|declared_synchronized */
        public synchronized void onLastPeerUnregistered() {
            for (DevToolsSharedPreferencesListener prefsListener : this.mPrefsListeners) {
                prefsListener.unregister();
            }
            this.mPrefsListeners.clear();
        }
    }

    private class DevToolsSharedPreferencesListener implements OnSharedPreferenceChangeListener {
        private final Map<String, Object> mCopy;
        private final SharedPreferences mPrefs;
        private final StorageId mStorageId = new StorageId();

        public DevToolsSharedPreferencesListener(SharedPreferences prefs, String tag) {
            this.mPrefs = prefs;
            this.mStorageId.securityOrigin = tag;
            this.mStorageId.isLocalStorage = true;
            this.mCopy = DOMStoragePeerManager.prefsCopy(prefs.getAll());
        }

        public void unregister() {
            this.mPrefs.unregisterOnSharedPreferenceChangeListener(this);
        }

        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            Map<String, ?> entries = sharedPreferences.getAll();
            boolean existedBefore = this.mCopy.containsKey(key);
            boolean existsNow = entries.containsKey(key);
            Object newValue = existsNow ? entries.get(key) : null;
            if (existedBefore && existsNow) {
                DOMStoragePeerManager.this.signalItemUpdated(this.mStorageId, key, SharedPreferencesHelper.valueToString(this.mCopy.get(key)), SharedPreferencesHelper.valueToString(newValue));
                this.mCopy.put(key, newValue);
            } else if (existedBefore) {
                DOMStoragePeerManager.this.signalItemRemoved(this.mStorageId, key);
                this.mCopy.remove(key);
            } else if (existsNow) {
                DOMStoragePeerManager.this.signalItemAdded(this.mStorageId, key, SharedPreferencesHelper.valueToString(newValue));
                this.mCopy.put(key, newValue);
            } else {
                LogUtil.m7452i("Detected rapid put/remove of %s", key);
            }
        }
    }

    public DOMStoragePeerManager(Context context) {
        this.mContext = context;
        setListener(this.mPeerListener);
    }

    public void signalItemRemoved(StorageId storageId, String key) {
        DomStorageItemRemovedParams params = new DomStorageItemRemovedParams();
        params.storageId = storageId;
        params.key = key;
        sendNotificationToPeers("DOMStorage.domStorageItemRemoved", params);
    }

    public void signalItemAdded(StorageId storageId, String key, String value) {
        DomStorageItemAddedParams params = new DomStorageItemAddedParams();
        params.storageId = storageId;
        params.key = key;
        params.newValue = value;
        sendNotificationToPeers("DOMStorage.domStorageItemAdded", params);
    }

    public void signalItemUpdated(StorageId storageId, String key, String oldValue, String newValue) {
        DomStorageItemUpdatedParams params = new DomStorageItemUpdatedParams();
        params.storageId = storageId;
        params.key = key;
        params.oldValue = oldValue;
        params.newValue = newValue;
        sendNotificationToPeers("DOMStorage.domStorageItemUpdated", params);
    }

    private static Map<String, Object> prefsCopy(Map<String, ?> src) {
        HashMap<String, Object> dst = new HashMap(src.size());
        for (Entry<String, ?> entry : src.entrySet()) {
            String key = (String) entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Set) {
                dst.put(key, shallowCopy((Set) value));
            } else {
                dst.put(key, value);
            }
        }
        return dst;
    }

    private static <T> Set<T> shallowCopy(Set<T> src) {
        HashSet<T> dst = new HashSet();
        for (T item : src) {
            dst.add(item);
        }
        return dst;
    }
}
