package com.newrelic.agent.android.stores;

import android.content.Context;
import com.newrelic.agent.android.crash.Crash;
import com.newrelic.agent.android.crash.CrashStore;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import com.newrelic.com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class SharedPrefsCrashStore extends SharedPrefsStore implements CrashStore {
    private static final String STORE_FILE = "NRCrashStore";

    public SharedPrefsCrashStore(Context context) {
        this(context, STORE_FILE);
    }

    public SharedPrefsCrashStore(Context context, String storeFilename) {
        super(context, storeFilename);
    }

    public boolean store(Crash crash) {
        boolean store;
        synchronized (this) {
            JsonObject jsonObj = crash.asJsonObject();
            jsonObj.add("uploadCount", SafeJsonPrimitive.factory(Integer.valueOf(crash.getUploadCount())));
            store = store(crash.getUuid().toString(), jsonObj.toString());
        }
        return store;
    }

    public List<Crash> fetchAll() {
        List<Crash> crashes = new ArrayList();
        for (Object object : super.fetchAll()) {
            if (object instanceof String) {
                try {
                    crashes.add(Crash.crashFromJsonString((String) object));
                } catch (Exception e) {
                    log.error("Exception encountered while deserializing crash", e);
                }
            }
        }
        return crashes;
    }

    public void delete(Crash crash) {
        super.delete(crash.getUuid().toString());
    }
}
