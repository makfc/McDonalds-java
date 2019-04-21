package com.facebook.internal;

import android.os.Bundle;
import com.facebook.widget.FacebookDialog.PendingCall;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class PendingCallStore {
    private static final String CALL_ID_ARRAY_KEY = "com.facebook.internal.PendingCallStore.callIdArrayKey";
    private static final String CALL_KEY_PREFIX = "com.facebook.internal.PendingCallStore.";
    private static PendingCallStore mInstance;
    private Map<String, PendingCall> pendingCallMap = new HashMap();

    public static PendingCallStore getInstance() {
        if (mInstance == null) {
            createInstance();
        }
        return mInstance;
    }

    private static synchronized void createInstance() {
        synchronized (PendingCallStore.class) {
            if (mInstance == null) {
                mInstance = new PendingCallStore();
            }
        }
    }

    public void trackPendingCall(PendingCall pendingCall) {
        if (pendingCall != null) {
            this.pendingCallMap.put(pendingCall.getCallId().toString(), pendingCall);
        }
    }

    public void stopTrackingPendingCall(UUID callId) {
        if (callId != null) {
            this.pendingCallMap.remove(callId.toString());
        }
    }

    public PendingCall getPendingCallById(UUID callId) {
        if (callId == null) {
            return null;
        }
        return (PendingCall) this.pendingCallMap.get(callId.toString());
    }

    public void saveInstanceState(Bundle outState) {
        outState.putStringArrayList(CALL_ID_ARRAY_KEY, new ArrayList(this.pendingCallMap.keySet()));
        for (PendingCall pendingCall : this.pendingCallMap.values()) {
            outState.putParcelable(getSavedStateKeyForPendingCallId(pendingCall.getCallId().toString()), pendingCall);
        }
    }

    public void restoreFromSavedInstanceState(Bundle savedInstanceState) {
        ArrayList<String> callIds = savedInstanceState.getStringArrayList(CALL_ID_ARRAY_KEY);
        if (callIds != null) {
            Iterator i$ = callIds.iterator();
            while (i$.hasNext()) {
                PendingCall pendingCall = (PendingCall) savedInstanceState.getParcelable(getSavedStateKeyForPendingCallId((String) i$.next()));
                if (pendingCall != null) {
                    this.pendingCallMap.put(pendingCall.getCallId().toString(), pendingCall);
                }
            }
        }
    }

    private String getSavedStateKeyForPendingCallId(String pendingCallId) {
        return CALL_KEY_PREFIX + pendingCallId;
    }
}
