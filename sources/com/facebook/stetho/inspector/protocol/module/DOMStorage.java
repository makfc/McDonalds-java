package com.facebook.stetho.inspector.protocol.module;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.facebook.stetho.inspector.domstorage.DOMStoragePeerManager;
import com.facebook.stetho.inspector.domstorage.SharedPreferencesHelper;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsMethod;
import com.facebook.stetho.json.ObjectMapper;
import com.facebook.stetho.json.annotation.JsonProperty;
import com.mcdonalds.sdk.connectors.autonavi.AutoNavi.Parameters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class DOMStorage implements ChromeDevtoolsDomain {
    private final Context mContext;
    private final DOMStoragePeerManager mDOMStoragePeerManager;
    private final ObjectMapper mObjectMapper = new ObjectMapper();

    private static class DOMStorageAssignmentException extends Exception {
        public DOMStorageAssignmentException(String message) {
            super(message);
        }
    }

    public static class DomStorageItemAddedParams {
        @JsonProperty(required = true)
        public String key;
        @JsonProperty(required = true)
        public String newValue;
        @JsonProperty(required = true)
        public StorageId storageId;
    }

    public static class DomStorageItemRemovedParams {
        @JsonProperty(required = true)
        public String key;
        @JsonProperty(required = true)
        public StorageId storageId;
    }

    public static class DomStorageItemUpdatedParams {
        @JsonProperty(required = true)
        public String key;
        @JsonProperty(required = true)
        public String newValue;
        @JsonProperty(required = true)
        public String oldValue;
        @JsonProperty(required = true)
        public StorageId storageId;
    }

    public static class DomStorageItemsClearedParams {
        @JsonProperty(required = true)
        public StorageId storageId;
    }

    private static class GetDOMStorageItemsResult implements JsonRpcResult {
        @JsonProperty(required = true)
        public List<List<String>> entries;

        private GetDOMStorageItemsResult() {
        }
    }

    public static class StorageId {
        @JsonProperty(required = true)
        public boolean isLocalStorage;
        @JsonProperty(required = true)
        public String securityOrigin;
    }

    public DOMStorage(Context context) {
        this.mContext = context;
        this.mDOMStoragePeerManager = new DOMStoragePeerManager(context);
    }

    @ChromeDevtoolsMethod
    public void enable(JsonRpcPeer peer, JSONObject params) {
        this.mDOMStoragePeerManager.addPeer(peer);
    }

    @ChromeDevtoolsMethod
    public void disable(JsonRpcPeer peer, JSONObject params) {
        this.mDOMStoragePeerManager.removePeer(peer);
    }

    @ChromeDevtoolsMethod
    public JsonRpcResult getDOMStorageItems(JsonRpcPeer peer, JSONObject params) throws JSONException {
        StorageId storage = (StorageId) this.mObjectMapper.convertValue(params.getJSONObject("storageId"), StorageId.class);
        ArrayList<List<String>> entries = new ArrayList();
        String prefTag = storage.securityOrigin;
        if (storage.isLocalStorage) {
            for (Entry<String, ?> prefsEntry : this.mContext.getSharedPreferences(prefTag, 0).getAll().entrySet()) {
                ArrayList<String> entry = new ArrayList(2);
                entry.add(prefsEntry.getKey());
                entry.add(SharedPreferencesHelper.valueToString(prefsEntry.getValue()));
                entries.add(entry);
            }
        }
        GetDOMStorageItemsResult result = new GetDOMStorageItemsResult();
        result.entries = entries;
        return result;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:4:0x0034, B:13:0x0075] */
    /* JADX WARNING: Missing block: B:7:0x0053, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:8:0x0054, code skipped:
            com.facebook.stetho.inspector.console.CLog.writeToConsole(r13.mDOMStoragePeerManager, com.facebook.stetho.inspector.protocol.module.Console.MessageLevel.ERROR, com.facebook.stetho.inspector.protocol.module.Console.MessageSource.STORAGE, r0.getMessage());
     */
    /* JADX WARNING: Missing block: B:9:0x0065, code skipped:
            if (r4.contains(r3) != false) goto L_0x0067;
     */
    /* JADX WARNING: Missing block: B:10:0x0067, code skipped:
            r13.mDOMStoragePeerManager.signalItemUpdated(r5, r3, r6, com.facebook.stetho.inspector.domstorage.SharedPreferencesHelper.valueToString(r2));
     */
    /* JADX WARNING: Missing block: B:18:0x00a2, code skipped:
            throw new com.facebook.stetho.inspector.protocol.module.DOMStorage.DOMStorageAssignmentException(java.lang.String.format(java.util.Locale.US, "Type mismatch setting %s to %s (expected %s)", new java.lang.Object[]{r3, r6, r2.getClass().getSimpleName()}));
     */
    /* JADX WARNING: Missing block: B:19:0x00a3, code skipped:
            r13.mDOMStoragePeerManager.signalItemRemoved(r5, r3);
     */
    /* JADX WARNING: Missing block: B:20:?, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:22:?, code skipped:
            return;
     */
    @com.facebook.stetho.inspector.protocol.ChromeDevtoolsMethod
    public void setDOMStorageItem(com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer r14, org.json.JSONObject r15) throws org.json.JSONException, com.facebook.stetho.inspector.jsonrpc.JsonRpcException {
        /*
        r13 = this;
        r10 = 0;
        r7 = r13.mObjectMapper;
        r8 = "storageId";
        r8 = r15.getJSONObject(r8);
        r9 = com.facebook.stetho.inspector.protocol.module.DOMStorage.StorageId.class;
        r5 = r7.convertValue(r8, r9);
        r5 = (com.facebook.stetho.inspector.protocol.module.DOMStorage.StorageId) r5;
        r7 = "key";
        r3 = r15.getString(r7);
        r7 = "value";
        r6 = r15.getString(r7);
        r7 = r5.isLocalStorage;
        if (r7 == 0) goto L_0x0070;
    L_0x0022:
        r7 = r13.mContext;
        r8 = r5.securityOrigin;
        r4 = r7.getSharedPreferences(r8, r10);
        r7 = r4.getAll();
        r2 = r7.get(r3);
        if (r2 != 0) goto L_0x0071;
    L_0x0034:
        r7 = new com.facebook.stetho.inspector.protocol.module.DOMStorage$DOMStorageAssignmentException;	 Catch:{ DOMStorageAssignmentException -> 0x0053 }
        r8 = new java.lang.StringBuilder;	 Catch:{ DOMStorageAssignmentException -> 0x0053 }
        r8.<init>();	 Catch:{ DOMStorageAssignmentException -> 0x0053 }
        r9 = "Unsupported: cannot add new key ";
        r8 = r8.append(r9);	 Catch:{ DOMStorageAssignmentException -> 0x0053 }
        r8 = r8.append(r3);	 Catch:{ DOMStorageAssignmentException -> 0x0053 }
        r9 = " due to lack of type inference";
        r8 = r8.append(r9);	 Catch:{ DOMStorageAssignmentException -> 0x0053 }
        r8 = r8.toString();	 Catch:{ DOMStorageAssignmentException -> 0x0053 }
        r7.<init>(r8);	 Catch:{ DOMStorageAssignmentException -> 0x0053 }
        throw r7;	 Catch:{ DOMStorageAssignmentException -> 0x0053 }
    L_0x0053:
        r0 = move-exception;
        r7 = r13.mDOMStoragePeerManager;
        r8 = com.facebook.stetho.inspector.protocol.module.Console.MessageLevel.ERROR;
        r9 = com.facebook.stetho.inspector.protocol.module.Console.MessageSource.STORAGE;
        r10 = r0.getMessage();
        com.facebook.stetho.inspector.console.CLog.writeToConsole(r7, r8, r9, r10);
        r7 = r4.contains(r3);
        if (r7 == 0) goto L_0x00a3;
    L_0x0067:
        r7 = r13.mDOMStoragePeerManager;
        r8 = com.facebook.stetho.inspector.domstorage.SharedPreferencesHelper.valueToString(r2);
        r7.signalItemUpdated(r5, r3, r6, r8);
    L_0x0070:
        return;
    L_0x0071:
        r1 = r4.edit();	 Catch:{ DOMStorageAssignmentException -> 0x0053 }
        r7 = com.facebook.stetho.inspector.domstorage.SharedPreferencesHelper.valueFromString(r6, r2);	 Catch:{ IllegalArgumentException -> 0x0080 }
        assignByType(r1, r3, r7);	 Catch:{ IllegalArgumentException -> 0x0080 }
        r1.apply();	 Catch:{ IllegalArgumentException -> 0x0080 }
        goto L_0x0070;
    L_0x0080:
        r0 = move-exception;
        r7 = new com.facebook.stetho.inspector.protocol.module.DOMStorage$DOMStorageAssignmentException;	 Catch:{ DOMStorageAssignmentException -> 0x0053 }
        r8 = java.util.Locale.US;	 Catch:{ DOMStorageAssignmentException -> 0x0053 }
        r9 = "Type mismatch setting %s to %s (expected %s)";
        r10 = 3;
        r10 = new java.lang.Object[r10];	 Catch:{ DOMStorageAssignmentException -> 0x0053 }
        r11 = 0;
        r10[r11] = r3;	 Catch:{ DOMStorageAssignmentException -> 0x0053 }
        r11 = 1;
        r10[r11] = r6;	 Catch:{ DOMStorageAssignmentException -> 0x0053 }
        r11 = 2;
        r12 = r2.getClass();	 Catch:{ DOMStorageAssignmentException -> 0x0053 }
        r12 = r12.getSimpleName();	 Catch:{ DOMStorageAssignmentException -> 0x0053 }
        r10[r11] = r12;	 Catch:{ DOMStorageAssignmentException -> 0x0053 }
        r8 = java.lang.String.format(r8, r9, r10);	 Catch:{ DOMStorageAssignmentException -> 0x0053 }
        r7.<init>(r8);	 Catch:{ DOMStorageAssignmentException -> 0x0053 }
        throw r7;	 Catch:{ DOMStorageAssignmentException -> 0x0053 }
    L_0x00a3:
        r7 = r13.mDOMStoragePeerManager;
        r7.signalItemRemoved(r5, r3);
        goto L_0x0070;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.stetho.inspector.protocol.module.DOMStorage.setDOMStorageItem(com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer, org.json.JSONObject):void");
    }

    @ChromeDevtoolsMethod
    public void removeDOMStorageItem(JsonRpcPeer peer, JSONObject params) throws JSONException {
        StorageId storage = (StorageId) this.mObjectMapper.convertValue(params.getJSONObject("storageId"), StorageId.class);
        String key = params.getString(Parameters.API_KEY);
        if (storage.isLocalStorage) {
            this.mContext.getSharedPreferences(storage.securityOrigin, 0).edit().remove(key).apply();
        }
    }

    private static void assignByType(Editor editor, String key, Object value) throws IllegalArgumentException {
        if (value instanceof Integer) {
            editor.putInt(key, ((Integer) value).intValue());
        } else if (value instanceof Long) {
            editor.putLong(key, ((Long) value).longValue());
        } else if (value instanceof Float) {
            editor.putFloat(key, ((Float) value).floatValue());
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, ((Boolean) value).booleanValue());
        } else if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Set) {
            putStringSet(editor, key, (Set) value);
        } else {
            throw new IllegalArgumentException("Unsupported type=" + value.getClass().getName());
        }
    }

    @TargetApi(11)
    private static void putStringSet(Editor editor, String key, Set<String> value) {
        editor.putStringSet(key, value);
    }
}
