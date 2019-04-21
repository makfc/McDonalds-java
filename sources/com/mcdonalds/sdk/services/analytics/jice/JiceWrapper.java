package com.mcdonalds.sdk.services.analytics.jice;

import android.content.Context;
import android.util.SparseArray;
import com.admaster.jice.api.JiceConfig;
import com.admaster.jice.api.JiceSDK;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs;
import com.mcdonalds.sdk.services.analytics.AnalyticsWrapper;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import java.util.HashMap;

public class JiceWrapper extends AnalyticsWrapper {
    public static final String CONFIG_KEY = "Jice";
    private static final String TAG = "JiceWrapper";
    private JiceSDK mJiceAPI = null;

    public JiceWrapper(Context context) {
        super(context);
    }

    public void initialize() {
    }

    public synchronized void log(AnalyticType event, AnalyticsArgs args) {
        switch (event) {
            case Event:
                handleEvent(args);
                break;
            case Custom:
                handleCustomDimension(args);
                break;
            case ScreenLoad:
                handleScreenLoad(args);
                break;
        }
    }

    private void handleScreenLoad(AnalyticsArgs args) {
        handle(args);
    }

    private void handleEvent(AnalyticsArgs args) {
        handle(args);
    }

    private void handle(AnalyticsArgs args) {
        HashMap map = (HashMap) args.get(AnalyticsArgs.DATAKEY_JICE);
        if (map == null) {
            return;
        }
        if (map.containsKey(JiceArgs.EVENT_NAME)) {
            String eventName = (String) map.remove(JiceArgs.EVENT_NAME);
            if (map.size() > 0) {
                if (this.mJiceAPI != null) {
                    this.mJiceAPI.track(eventName, map);
                }
            } else if (this.mJiceAPI != null) {
                this.mJiceAPI.track(eventName);
            }
        } else if (map.containsKey(JiceArgs.INIT)) {
            this.mJiceAPI = JiceSDK.getInstance(this.mContext, new JiceConfig(false, false, false));
        }
    }

    private void handleCustomDimension(AnalyticsArgs args) {
        SparseArray<String> custom = (SparseArray) args.get(AnalyticsArgs.DATAKEY_CUSTOM);
        HashMap<String, Object> map = (HashMap) args.get(AnalyticsArgs.DATAKEY_JICE);
        if (map != null && custom != null && map.containsKey(JiceArgs.EVENT_LOGIN)) {
            HashMap properties = new HashMap();
            String id = (String) custom.get(2);
            if (this.mJiceAPI != null) {
                this.mJiceAPI.addUserIdentifier(id, properties);
            }
        }
    }
}
