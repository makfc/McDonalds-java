package com.newrelic.agent.android.stores;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.analytics.AnalyticAttributeStore;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class SharedPrefsAnalyticAttributeStore extends SharedPrefsStore implements AnalyticAttributeStore {
    private static final String STORE_FILE = "NRAnalyticAttributeStore";
    private static final AgentLog log = AgentLogManager.getAgentLog();

    public SharedPrefsAnalyticAttributeStore(Context context) {
        super(context, STORE_FILE);
    }

    public SharedPrefsAnalyticAttributeStore(Context context, String storeFilename) {
        super(context, storeFilename);
    }

    public boolean store(AnalyticAttribute attribute) {
        boolean z = false;
        synchronized (this) {
            if (attribute.isPersistent()) {
                Editor editor = this.sharedPrefs.edit();
                switch (attribute.getAttributeDataType()) {
                    case STRING:
                        log.verbose("SharedPrefsAnalyticAttributeStore.store - storing analytic attribute " + attribute.getName() + "=" + attribute.getStringValue());
                        editor.putString(attribute.getName(), attribute.getStringValue());
                        break;
                    case FLOAT:
                        log.verbose("SharedPrefsAnalyticAttributeStore.store - storing analytic attribute " + attribute.getName() + "=" + attribute.getFloatValue());
                        editor.putFloat(attribute.getName(), attribute.getFloatValue());
                        break;
                    case BOOLEAN:
                        log.verbose("SharedPrefsAnalyticAttributeStore.store - storing analytic attribute " + attribute.getName() + "=" + attribute.getBooleanValue());
                        editor.putBoolean(attribute.getName(), attribute.getBooleanValue());
                        break;
                    default:
                        log.error("SharedPrefsAnalyticAttributeStore.store - unsupported analytic attribute data type" + attribute.getName());
                }
                z = applyOrCommitEditor(editor);
            }
        }
        return z;
    }

    public List<AnalyticAttribute> fetchAll() {
        ArrayList<AnalyticAttribute> analyticAttributeArrayList = new ArrayList();
        for (Entry entry : this.sharedPrefs.getAll().entrySet()) {
            log.verbose("SharedPrefsAnalyticAttributeStore.fetchAll - found analytic attribute " + entry.getKey() + "=" + entry.getValue());
            if (entry.getValue() instanceof String) {
                analyticAttributeArrayList.add(new AnalyticAttribute(entry.getKey().toString(), entry.getValue().toString(), true));
            } else if (entry.getValue() instanceof Float) {
                analyticAttributeArrayList.add(new AnalyticAttribute(entry.getKey().toString(), Float.valueOf(entry.getValue().toString()).floatValue(), true));
            } else if (entry.getValue() instanceof Boolean) {
                analyticAttributeArrayList.add(new AnalyticAttribute(entry.getKey().toString(), Boolean.valueOf(entry.getValue().toString()).booleanValue(), true));
            } else {
                log.error("SharedPrefsAnalyticAttributeStore.fetchAll - unsupported analytic attribute " + entry.getKey() + "=" + entry.getValue());
            }
        }
        return analyticAttributeArrayList;
    }

    public void delete(AnalyticAttribute attribute) {
        synchronized (this) {
            log.verbose("SharedPrefsAnalyticAttributeStore.delete - deleting attribute " + attribute.getName());
            super.delete(attribute.getName());
        }
    }
}
