package com.mcdonalds.sdk.services.analytics.datalayer;

import android.text.TextUtils;
import android.util.Log;
import com.mcdonalds.sdk.utils.MapUtils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataLayer {
    private static final String EVENT_STATE_NAME = "name";
    private static final String EVENT_STATE_POSITION = "position";
    private static final String EVENT_STATE_ROOT = "event";
    private static final String EVENT_STATE_TYPE = "type";
    private static final String GLOBAL_NAME = "Global";
    private static final String PAGE_NAME = "page.name";
    private Map<String, Object> dataLayerMap = new LinkedHashMap();
    private Listener listener;
    private Map<String, Object> oldDataLayerMap;
    private Map<String, Object> trackedEvents;

    public interface Listener {
        void dataLayerLoggedEvent(String str, Map<String, Object> map, Map<String, Object> map2);
    }

    public DataLayer(Map<String, Object> trackedEvents) {
        this.trackedEvents = trackedEvents;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public <T> T getState(String key) {
        return MapUtils.get(this.dataLayerMap, key);
    }

    public <T> DataLayer setState(String key, T value) {
        if (!TextUtils.isEmpty(key)) {
            this.oldDataLayerMap = MapUtils.copy(this.dataLayerMap);
            MapUtils.put(this.dataLayerMap, key, (Object) value);
        } else if (value != null && (value instanceof Map)) {
            this.oldDataLayerMap = MapUtils.copy(this.dataLayerMap);
            this.dataLayerMap = MapUtils.merge(this.dataLayerMap, (Map) value);
        }
        return this;
    }

    public DataLayer setPageName(String pageName) {
        setState(PAGE_NAME, pageName);
        return this;
    }

    public DataLayer recordEvent(String eventName, String type, String position) {
        boolean nameEqual = stringEquals(eventName, (String) MapUtils.get(this.dataLayerMap, "event.name"));
        boolean typeEqual = stringEquals(type, (String) MapUtils.get(this.dataLayerMap, "event.type"));
        boolean positionEqual = stringEquals(position, (String) MapUtils.get(this.dataLayerMap, "event.position"));
        boolean mapEqual = MapUtils.mapEquals(this.dataLayerMap, this.oldDataLayerMap);
        if (nameEqual && typeEqual && positionEqual && mapEqual) {
            Log.w("DLA", "Duplicate event ignored");
        } else {
            Map<String, Object> eventMap = new LinkedHashMap();
            eventMap.put("name", eventName);
            eventMap.put("type", type);
            eventMap.put(EVENT_STATE_POSITION, position);
            setState("event", eventMap);
        }
        return this;
    }

    public DataLayer recordEvent(String eventName, String type) {
        return recordEvent(eventName, type, null);
    }

    public void log() {
        if (this.trackedEvents.isEmpty()) {
            generateLogData((String) MapUtils.get(this.dataLayerMap, PAGE_NAME), (String) MapUtils.get(this.dataLayerMap, "event.name"), new ArrayList());
            return;
        }
        String pageName = GLOBAL_NAME;
        String eventName = (String) MapUtils.get(this.dataLayerMap, "event.name");
        List<String> trackedParams = (List) ((Map) MapUtils.get(this.trackedEvents, pageName)).get(eventName);
        if (trackedParams != null) {
            generateLogData(pageName, eventName, trackedParams);
        }
        pageName = (String) MapUtils.get(this.dataLayerMap, PAGE_NAME);
        Map<String, Object> pageTrackedParams = (Map) MapUtils.get(this.trackedEvents, pageName);
        if (pageTrackedParams != null) {
            trackedParams = (List) pageTrackedParams.get(eventName);
            if (trackedParams != null) {
                generateLogData(pageName, eventName, trackedParams);
            }
        }
    }

    private void generateLogData(String pageName, String eventName, List<String> trackedParams) {
        if (trackedParams.isEmpty()) {
            dispatchLoggedEventWithDataPoints(this.dataLayerMap);
            return;
        }
        Map dataPoints = new LinkedHashMap();
        for (String param : trackedParams) {
            MapUtils.put(dataPoints, param, MapUtils.get(this.dataLayerMap, param));
        }
        dispatchLoggedEventWithDataPoints(dataPoints);
    }

    private void dispatchLoggedEventWithDataPoints(Map<String, Object> dataPoints) {
        String eventName = String.format("%s->%s", new Object[]{MapUtils.get(this.dataLayerMap, PAGE_NAME), MapUtils.get(this.dataLayerMap, "event.name")});
        Map<String, Object> flattenedDataPoints = MapUtils.flatten(dataPoints);
        if (this.listener != null) {
            this.listener.dataLayerLoggedEvent(eventName, flattenedDataPoints, MapUtils.copy(this.dataLayerMap));
        }
    }

    private boolean stringEquals(String first, String second) {
        return (first != null && first.equals(second)) || (first == null && second == null);
    }
}
