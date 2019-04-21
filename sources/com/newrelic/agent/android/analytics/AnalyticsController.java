package com.newrelic.agent.android.analytics;

import java.util.Map;
import java.util.Set;

public interface AnalyticsController {
    boolean addEvent(AnalyticsEvent analyticsEvent);

    boolean addEvent(String str, AnalyticsEventCategory analyticsEventCategory, String str2, Set<AnalyticAttribute> set);

    boolean addEvent(String str, Set<AnalyticAttribute> set);

    AnalyticAttribute getAttribute(String str);

    EventManager getEventManager();

    int getMaxEventBufferTime();

    int getMaxEventPoolSize();

    int getSessionAttributeCount();

    Set<AnalyticAttribute> getSessionAttributes();

    int getSystemAttributeCount();

    Set<AnalyticAttribute> getSystemAttributes();

    int getUserAttributeCount();

    Set<AnalyticAttribute> getUserAttributes();

    boolean incrementAttribute(String str, float f);

    boolean incrementAttribute(String str, float f, boolean z);

    boolean recordEvent(String str, Map<String, Object> map);

    boolean removeAllAttributes();

    boolean removeAttribute(String str);

    boolean setAttribute(String str, float f);

    boolean setAttribute(String str, float f, boolean z);

    boolean setAttribute(String str, String str2);

    boolean setAttribute(String str, String str2, boolean z);

    boolean setAttribute(String str, boolean z);

    boolean setAttribute(String str, boolean z, boolean z2);

    void setMaxEventBufferTime(int i);

    void setMaxEventPoolSize(int i);
}
