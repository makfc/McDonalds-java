package com.newrelic.agent.android.analytics;

import com.mcdonalds.sdk.connectors.middleware.response.MWDeliveryStatusResponse;
import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.AgentConfiguration;
import com.newrelic.agent.android.AgentImpl;
import com.newrelic.agent.android.harvest.DeviceInformation;
import com.newrelic.agent.android.harvest.EnvironmentInformation;
import com.newrelic.agent.android.harvest.HttpTransaction;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.tracing.ActivityTrace;
import com.newrelic.agent.android.tracing.TraceLifecycleAware;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class AnalyticsControllerImpl implements AnalyticsController {
    protected static final int MAX_ATTRIBUTES = 64;
    private static final String NEW_RELIC_PREFIX = "newRelic";
    private static final String NR_PREFIX = "nr.";
    private static final AtomicBoolean initialized = new AtomicBoolean(false);
    private static final AnalyticsControllerImpl instance = new AnalyticsControllerImpl();
    private static final AgentLog log = AgentLogManager.getAgentLog();
    private static final List<String> reservedNames = new ArrayList();
    private AgentImpl agentImpl;
    private AnalyticAttributeStore attributeStore;
    private final EventManagerImpl eventManager = new EventManagerImpl();
    private final AtomicBoolean isEnabled = new AtomicBoolean(false);
    private final InteractionCompleteListener listener = new InteractionCompleteListener();
    private final ConcurrentLinkedQueue<AnalyticAttribute> systemAttributes = new ConcurrentLinkedQueue();
    private final ConcurrentLinkedQueue<AnalyticAttribute> userAttributes = new ConcurrentLinkedQueue();

    class InteractionCompleteListener implements TraceLifecycleAware {
        InteractionCompleteListener() {
        }

        public void onEnterMethod() {
        }

        public void onExitMethod() {
        }

        public void onTraceStart(ActivityTrace activityTrace) {
            AnalyticsControllerImpl.this.addAttributeUnchecked(new AnalyticAttribute(AnalyticAttribute.LAST_INTERACTION_ATTRIBUTE, activityTrace.getActivityName()), true);
        }

        public void onTraceComplete(ActivityTrace activityTrace) {
            AnalyticsControllerImpl.log.verbose("AnalyticsControllerImpl.InteractionCompleteListener.onTraceComplete invoke.");
            AnalyticsControllerImpl.getInstance().addEvent(createTraceEvent(activityTrace));
        }

        public void onTraceRename(ActivityTrace activityTrace) {
            AnalyticsControllerImpl.this.addAttributeUnchecked(new AnalyticAttribute(AnalyticAttribute.LAST_INTERACTION_ATTRIBUTE, activityTrace.getActivityName()), true);
        }

        private AnalyticsEvent createTraceEvent(ActivityTrace activityTrace) {
            float durationInSec = activityTrace.rootTrace.getDurationAsSeconds();
            Set<AnalyticAttribute> attrs = new HashSet();
            attrs.add(new AnalyticAttribute(AnalyticAttribute.INTERACTION_DURATION_ATTRIBUTE, durationInSec));
            return AnalyticsEventFactory.createEvent(activityTrace.rootTrace.displayName, AnalyticsEventCategory.Interaction, AnalyticAttribute.EVENT_TYPE_ATTRIBUTE_MOBILE, attrs);
        }
    }

    public static void initialize(AgentConfiguration agentConfiguration, AgentImpl agentImpl) {
        log.verbose("AnalyticsControllerImpl.initialize invoked.");
        if (initialized.compareAndSet(false, true)) {
            instance.clear();
            reservedNames.add(AnalyticAttribute.EVENT_TYPE_ATTRIBUTE);
            reservedNames.add("type");
            reservedNames.add(AnalyticAttribute.EVENT_TIMESTAMP_ATTRIBUTE);
            reservedNames.add("category");
            reservedNames.add(AnalyticAttribute.ACCOUNT_ID_ATTRIBUTE);
            reservedNames.add(AnalyticAttribute.APP_ID_ATTRIBUTE);
            reservedNames.add(AnalyticAttribute.APP_NAME_ATTRIBUTE);
            reservedNames.add(AnalyticAttribute.UUID_ATTRIBUTE);
            reservedNames.add("sessionId");
            reservedNames.add(AnalyticAttribute.OS_NAME_ATTRIBUTE);
            reservedNames.add(AnalyticAttribute.OS_VERSION_ATTRIBUTE);
            reservedNames.add(AnalyticAttribute.OS_MAJOR_VERSION_ATTRIBUTE);
            reservedNames.add(AnalyticAttribute.DEVICE_MANUFACTURER_ATTRIBUTE);
            reservedNames.add(AnalyticAttribute.DEVICE_MODEL_ATTRIBUTE);
            reservedNames.add(AnalyticAttribute.MEM_USAGE_MB_ATTRIBUTE);
            reservedNames.add(AnalyticAttribute.CARRIER_ATTRIBUTE);
            reservedNames.add(AnalyticAttribute.NEW_RELIC_VERSION_ATTRIBUTE);
            reservedNames.add(AnalyticAttribute.INTERACTION_DURATION_ATTRIBUTE);
            reservedNames.add(AnalyticAttribute.APP_INSTALL_ATTRIBUTE);
            reservedNames.add(AnalyticAttribute.APP_UPGRADE_ATTRIBUTE);
            reservedNames.add(AnalyticAttribute.APPLICATION_PLATFORM_ATTRIBUTE);
            reservedNames.add(AnalyticAttribute.APPLICATION_PLATFORM_VERSION_ATTRIBUTE);
            reservedNames.add(AnalyticAttribute.LAST_INTERACTION_ATTRIBUTE);
            reservedNames.add(AnalyticAttribute.OS_BUILD_ATTRIBUTE);
            reservedNames.add(AnalyticAttribute.RUNTIME_ATTRIBUTE);
            reservedNames.add(AnalyticAttribute.ARCHITECTURE_ATTRIBUTE);
            reservedNames.add("appBuild");
            instance.reinitialize(agentConfiguration, agentImpl);
            TraceMachine.addTraceListener(instance.listener);
            log.info("Analytics Controller started.");
            return;
        }
        log.verbose("AnalyticsControllerImpl has already been initialized.  Bypassing..");
    }

    public static void shutdown() {
        TraceMachine.removeTraceListener(instance.listener);
        instance.getEventManager().shutdown();
        initialized.compareAndSet(true, false);
    }

    private AnalyticsControllerImpl() {
    }

    /* Access modifiers changed, original: 0000 */
    public void reinitialize(AgentConfiguration agentConfiguration, AgentImpl agentImpl) {
        String osMajorVersion;
        this.agentImpl = agentImpl;
        this.eventManager.initialize();
        this.isEnabled.set(agentConfiguration.getEnableAnalyticsEvents());
        this.attributeStore = agentConfiguration.getAnalyticAttributeStore();
        loadPersistentAttributes();
        DeviceInformation deviceInformation = agentImpl.getDeviceInformation();
        String osVersion = deviceInformation.getOsVersion().replace(" ", "");
        String[] osMajorVersionArr = osVersion.split("[.:-]");
        if (osMajorVersionArr.length > 0) {
            osMajorVersion = osMajorVersionArr[0];
        } else {
            osMajorVersion = osVersion;
        }
        EnvironmentInformation environmentInformation = agentImpl.getEnvironmentInformation();
        this.systemAttributes.add(new AnalyticAttribute(AnalyticAttribute.OS_NAME_ATTRIBUTE, deviceInformation.getOsName()));
        this.systemAttributes.add(new AnalyticAttribute(AnalyticAttribute.OS_VERSION_ATTRIBUTE, osVersion));
        this.systemAttributes.add(new AnalyticAttribute(AnalyticAttribute.OS_BUILD_ATTRIBUTE, deviceInformation.getOsBuild()));
        this.systemAttributes.add(new AnalyticAttribute(AnalyticAttribute.OS_MAJOR_VERSION_ATTRIBUTE, osMajorVersion));
        this.systemAttributes.add(new AnalyticAttribute(AnalyticAttribute.DEVICE_MANUFACTURER_ATTRIBUTE, deviceInformation.getManufacturer()));
        this.systemAttributes.add(new AnalyticAttribute(AnalyticAttribute.DEVICE_MODEL_ATTRIBUTE, deviceInformation.getModel()));
        this.systemAttributes.add(new AnalyticAttribute(AnalyticAttribute.UUID_ATTRIBUTE, deviceInformation.getDeviceId()));
        this.systemAttributes.add(new AnalyticAttribute(AnalyticAttribute.CARRIER_ATTRIBUTE, agentImpl.getNetworkCarrier()));
        this.systemAttributes.add(new AnalyticAttribute(AnalyticAttribute.NEW_RELIC_VERSION_ATTRIBUTE, deviceInformation.getAgentVersion()));
        this.systemAttributes.add(new AnalyticAttribute(AnalyticAttribute.MEM_USAGE_MB_ATTRIBUTE, (float) environmentInformation.getMemoryUsage()));
        this.systemAttributes.add(new AnalyticAttribute("sessionId", agentConfiguration.getSessionID()));
        this.systemAttributes.add(new AnalyticAttribute(AnalyticAttribute.APPLICATION_PLATFORM_ATTRIBUTE, agentConfiguration.getApplicationPlatform().toString()));
        this.systemAttributes.add(new AnalyticAttribute(AnalyticAttribute.APPLICATION_PLATFORM_VERSION_ATTRIBUTE, agentConfiguration.getApplicationPlatformVersion()));
        this.systemAttributes.add(new AnalyticAttribute(AnalyticAttribute.RUNTIME_ATTRIBUTE, deviceInformation.getRunTime()));
        this.systemAttributes.add(new AnalyticAttribute(AnalyticAttribute.ARCHITECTURE_ATTRIBUTE, deviceInformation.getArchitecture()));
        if (agentConfiguration.getCustomBuildIdentifier() != null) {
            this.systemAttributes.add(new AnalyticAttribute("appBuild", agentConfiguration.getCustomBuildIdentifier()));
            return;
        }
        String appBuildString = String.valueOf(Agent.getApplicationInformation().getVersionCode());
        if (!appBuildString.isEmpty()) {
            this.systemAttributes.add(new AnalyticAttribute("appBuild", appBuildString));
        }
    }

    public AnalyticAttribute getAttribute(String name) {
        log.verbose("AnalyticsControllerImpl.getAttribute - retrieving " + name);
        AnalyticAttribute attribute = getUserAttribute(name);
        if (attribute == null) {
            return getSystemAttribute(name);
        }
        return attribute;
    }

    public Set<AnalyticAttribute> getSystemAttributes() {
        Set<AnalyticAttribute> attrs = new HashSet(this.systemAttributes.size());
        Iterator it = this.systemAttributes.iterator();
        while (it.hasNext()) {
            attrs.add(new AnalyticAttribute((AnalyticAttribute) it.next()));
        }
        return Collections.unmodifiableSet(attrs);
    }

    public Set<AnalyticAttribute> getUserAttributes() {
        Set<AnalyticAttribute> attrs = new HashSet(this.userAttributes.size());
        Iterator it = this.userAttributes.iterator();
        while (it.hasNext()) {
            attrs.add(new AnalyticAttribute((AnalyticAttribute) it.next()));
            if (attrs.size() == 64) {
                break;
            }
        }
        return Collections.unmodifiableSet(attrs);
    }

    public Set<AnalyticAttribute> getSessionAttributes() {
        Set<AnalyticAttribute> attrs = new HashSet(getSessionAttributeCount());
        attrs.addAll(getSystemAttributes());
        attrs.addAll(getUserAttributes());
        return Collections.unmodifiableSet(attrs);
    }

    public int getSystemAttributeCount() {
        return this.systemAttributes.size();
    }

    public int getUserAttributeCount() {
        return Math.min(this.userAttributes.size(), 64);
    }

    public int getSessionAttributeCount() {
        return this.systemAttributes.size() + this.userAttributes.size();
    }

    public boolean setAttribute(String name, String value) {
        log.verbose("AnalyticsControllerImpl.setAttribute - " + name + ": " + value);
        return setAttribute(name, value, true);
    }

    public boolean setAttribute(String name, String value, boolean persistent) {
        log.verbose("AnalyticsControllerImpl.setAttribute - " + name + ": " + value + (persistent ? " (persistent)" : " (transient)"));
        if (!isInitializedAndEnabled()) {
            return false;
        }
        if (!isAttributeNameValid(name) || !isStringValueValid(name, value)) {
            return false;
        }
        AnalyticAttribute attribute = getAttribute(name);
        if (attribute == null) {
            return addNewUserAttribute(new AnalyticAttribute(name, value, persistent));
        }
        attribute.setStringValue(value);
        attribute.setPersistent(persistent);
        if (!attribute.isPersistent()) {
            this.attributeStore.delete(attribute);
        } else if (!this.attributeStore.store(attribute)) {
            log.error("Failed to store attribute " + attribute + " to attribute store.");
            return false;
        }
        return true;
    }

    public boolean setAttribute(String name, float value) {
        log.verbose("AnalyticsControllerImpl.setAttribute - " + name + ": " + value);
        return setAttribute(name, value, true);
    }

    public boolean setAttribute(String name, float value, boolean persistent) {
        log.verbose("AnalyticsControllerImpl.setAttribute - " + name + ": " + value + (persistent ? " (persistent)" : " (transient)"));
        if (!isInitializedAndEnabled()) {
            return false;
        }
        if (!isAttributeNameValid(name)) {
            return false;
        }
        AnalyticAttribute attribute = getAttribute(name);
        if (attribute == null) {
            return addNewUserAttribute(new AnalyticAttribute(name, value, persistent));
        }
        attribute.setFloatValue(value);
        attribute.setPersistent(persistent);
        if (!attribute.isPersistent()) {
            this.attributeStore.delete(attribute);
        } else if (!this.attributeStore.store(attribute)) {
            log.error("Failed to store attribute " + attribute + " to attribute store.");
            return false;
        }
        return true;
    }

    public boolean setAttribute(String name, boolean value) {
        log.verbose("AnalyticsControllerImpl.setAttribute - " + name + ": " + value);
        return setAttribute(name, value, true);
    }

    public boolean setAttribute(String name, boolean value, boolean persistent) {
        log.verbose("AnalyticsControllerImpl.setAttribute - " + name + ": " + value + (persistent ? " (persistent)" : " (transient)"));
        if (!isInitializedAndEnabled()) {
            return false;
        }
        if (!isAttributeNameValid(name)) {
            return false;
        }
        AnalyticAttribute attribute = getAttribute(name);
        if (attribute == null) {
            return addNewUserAttribute(new AnalyticAttribute(name, value, persistent));
        }
        attribute.setBooleanValue(value);
        attribute.setPersistent(persistent);
        if (!attribute.isPersistent()) {
            this.attributeStore.delete(attribute);
        } else if (!this.attributeStore.store(attribute)) {
            log.error("Failed to store attribute " + attribute + " to attribute store.");
            return false;
        }
        return true;
    }

    public boolean addAttributeUnchecked(AnalyticAttribute attribute, boolean persistent) {
        log.verbose("AnalyticsControllerImpl.setAttributeUnchecked - " + attribute.getName() + ": " + attribute.getStringValue() + (persistent ? " (persistent)" : " (transient)"));
        if (!initialized.get()) {
            log.warning("Analytics controller is not initialized!");
            return false;
        } else if (this.isEnabled.get()) {
            String name = attribute.getName();
            if (!isNameValid(name)) {
                return false;
            }
            if (attribute.isStringAttribute() && !isStringValueValid(name, attribute.getStringValue())) {
                return false;
            }
            AnalyticAttribute cachedAttribute = getSystemAttribute(name);
            if (cachedAttribute == null) {
                this.systemAttributes.add(attribute);
                if (attribute.isPersistent() && !this.attributeStore.store(attribute)) {
                    log.error("Failed to store attribute " + attribute + " to attribute store.");
                    return false;
                }
            }
            switch (attribute.getAttributeDataType()) {
                case STRING:
                    cachedAttribute.setStringValue(attribute.getStringValue());
                    break;
                case FLOAT:
                    cachedAttribute.setFloatValue(attribute.getFloatValue());
                    break;
                case BOOLEAN:
                    cachedAttribute.setBooleanValue(attribute.getBooleanValue());
                    break;
            }
            cachedAttribute.setPersistent(persistent);
            if (!cachedAttribute.isPersistent()) {
                this.attributeStore.delete(cachedAttribute);
            } else if (!this.attributeStore.store(cachedAttribute)) {
                log.error("Failed to store attribute " + cachedAttribute + " to attribute store.");
                return false;
            }
            return true;
        } else {
            log.warning("Analytics controller is not enabled!");
            return false;
        }
    }

    public boolean incrementAttribute(String name, float value) {
        log.verbose("AnalyticsControllerImpl.incrementAttribute - " + name + ": " + value);
        return incrementAttribute(name, value, true);
    }

    public boolean incrementAttribute(String name, float value, boolean persistent) {
        log.verbose("AnalyticsControllerImpl.incrementAttribute - " + name + ": " + value + (persistent ? " (persistent)" : " (transient)"));
        if (!isInitializedAndEnabled()) {
            return false;
        }
        if (!isAttributeNameValid(name)) {
            return false;
        }
        AnalyticAttribute attribute = getAttribute(name);
        if (attribute != null && attribute.isFloatAttribute()) {
            attribute.setFloatValue(attribute.getFloatValue() + value);
            attribute.setPersistent(persistent);
            if (!attribute.isPersistent() || this.attributeStore.store(attribute)) {
                return true;
            }
            log.error("Failed to store attribute " + attribute + " to attribute store.");
            return false;
        } else if (attribute == null) {
            return addNewUserAttribute(new AnalyticAttribute(name, value, persistent));
        } else {
            log.warning("Cannot increment attribute " + name + ": the attribute is already defined as a non-float value.");
            return false;
        }
    }

    public boolean removeAttribute(String name) {
        log.verbose("AnalyticsControllerImpl.removeAttribute - " + name);
        if (!isInitializedAndEnabled()) {
            return false;
        }
        AnalyticAttribute attribute = getAttribute(name);
        if (attribute != null) {
            this.userAttributes.remove(attribute);
            if (attribute.isPersistent()) {
                this.attributeStore.delete(attribute);
            }
        }
        return true;
    }

    public boolean removeAllAttributes() {
        log.verbose("AnalyticsControllerImpl.removeAttributes - ");
        if (isInitializedAndEnabled()) {
            this.attributeStore.clear();
            this.userAttributes.clear();
        }
        return false;
    }

    public boolean addEvent(String name, Set<AnalyticAttribute> eventAttributes) {
        return addEvent(name, AnalyticsEventCategory.Custom, AnalyticAttribute.EVENT_TYPE_ATTRIBUTE_MOBILE, eventAttributes);
    }

    public boolean addEvent(String name, AnalyticsEventCategory eventCategory, String eventType, Set<AnalyticAttribute> eventAttributes) {
        log.verbose("AnalyticsControllerImpl.addEvent - " + name + ": category=" + eventCategory + ", eventType: " + eventType + ", eventAttributes:" + eventAttributes);
        if (!isInitializedAndEnabled()) {
            return false;
        }
        Set<AnalyticAttribute> validatedAttributes = new HashSet();
        for (AnalyticAttribute attribute : eventAttributes) {
            if (isAttributeNameValid(attribute.getName())) {
                validatedAttributes.add(attribute);
            }
        }
        return addEvent(AnalyticsEventFactory.createEvent(name, eventCategory, eventType, validatedAttributes));
    }

    public boolean addEvent(AnalyticsEvent event) {
        if (!isInitializedAndEnabled()) {
            return false;
        }
        Set<AnalyticAttribute> sessionAttributes = new HashSet();
        long sessionDuration = this.agentImpl.getSessionDurationMillis();
        if (0 == sessionDuration) {
            log.error("Harvest instance is not running! Session duration will be invalid");
        } else {
            sessionAttributes.add(new AnalyticAttribute(AnalyticAttribute.SESSION_TIME_SINCE_LOAD_ATTRIBUTE, ((float) sessionDuration) / 1000.0f));
            event.addAttributes(sessionAttributes);
        }
        return this.eventManager.addEvent(event);
    }

    public int getMaxEventPoolSize() {
        return this.eventManager.getMaxEventPoolSize();
    }

    public void setMaxEventPoolSize(int maxSize) {
        this.eventManager.setMaxEventPoolSize(maxSize);
    }

    public void setMaxEventBufferTime(int maxBufferTimeInSec) {
        this.eventManager.setMaxEventBufferTime(maxBufferTimeInSec);
    }

    public int getMaxEventBufferTime() {
        return this.eventManager.getMaxEventBufferTime();
    }

    public EventManager getEventManager() {
        return this.eventManager;
    }

    public static AnalyticsControllerImpl getInstance() {
        return instance;
    }

    /* Access modifiers changed, original: 0000 */
    public void loadPersistentAttributes() {
        log.verbose("AnalyticsControllerImpl.loadPersistentAttributes - loading userAttributes from the attribute store...");
        List<AnalyticAttribute> storedAttrs = this.attributeStore.fetchAll();
        log.debug("AnalyticsControllerImpl.loadPersistentAttributes - found " + storedAttrs.size() + " userAttributes in the attribute store...");
        int size = this.userAttributes.size();
        for (AnalyticAttribute attr : storedAttrs) {
            if (!this.userAttributes.contains(attr) && size <= 64) {
                this.userAttributes.add(attr);
                size++;
            }
        }
    }

    private AnalyticAttribute getSystemAttribute(String name) {
        Iterator it = this.systemAttributes.iterator();
        while (it.hasNext()) {
            AnalyticAttribute nextAttribute = (AnalyticAttribute) it.next();
            if (nextAttribute.getName().equals(name)) {
                return nextAttribute;
            }
        }
        return null;
    }

    private AnalyticAttribute getUserAttribute(String name) {
        Iterator it = this.userAttributes.iterator();
        while (it.hasNext()) {
            AnalyticAttribute nextAttribute = (AnalyticAttribute) it.next();
            if (nextAttribute.getName().equals(name)) {
                return nextAttribute;
            }
        }
        return null;
    }

    private void clear() {
        log.verbose("AnalyticsControllerImpl.clear - clearing out attributes and events");
        this.systemAttributes.clear();
        this.userAttributes.clear();
        this.eventManager.empty();
    }

    private boolean isAttributeNameValid(String name) {
        boolean valid = isNameValid(name);
        if (valid) {
            valid = !isNameReserved(name);
            if (!valid) {
                log.error("Attribute name " + name + " is reserved for internal use and will be ignored.");
            }
        }
        return valid;
    }

    private boolean isNameValid(String name) {
        boolean valid = (name == null || name.equals("") || name.length() >= 256) ? false : true;
        if (!valid) {
            log.error("Attribute name " + name + " is null, empty, or exceeds the maximum length of " + 256 + " characters.");
        }
        return valid;
    }

    private boolean isStringValueValid(String name, String value) {
        boolean valid = (value == null || value.equals("") || value.getBytes().length >= 4096) ? false : true;
        if (!valid) {
            log.error("Attribute value for name " + name + " is null, empty, or exceeds the maximum length of " + 4096 + " bytes.");
        }
        return valid;
    }

    private boolean isNameReserved(String name) {
        if (reservedNames.contains(name)) {
            log.verbose("Name " + name + " is in the reserved names list.");
            return true;
        } else if (name.startsWith(NEW_RELIC_PREFIX)) {
            log.verbose("Name " + name + " starts with reserved prefix " + NEW_RELIC_PREFIX);
            return true;
        } else if (!name.startsWith(NR_PREFIX)) {
            return false;
        } else {
            log.verbose("Name " + name + " starts with reserved prefix " + NR_PREFIX);
            return true;
        }
    }

    private AnalyticAttribute createAttribute(String key, Object value) {
        try {
            if (value instanceof String) {
                return new AnalyticAttribute(key, String.valueOf(value));
            }
            if (value instanceof Float) {
                return new AnalyticAttribute(key, Float.valueOf(((Float) value).floatValue()).floatValue());
            }
            if (value instanceof Double) {
                return new AnalyticAttribute(key, Float.valueOf(((Double) value).floatValue()).floatValue());
            }
            if (value instanceof Integer) {
                return new AnalyticAttribute(key, Float.valueOf((float) ((Integer) value).intValue()).floatValue());
            }
            if (value instanceof Short) {
                return new AnalyticAttribute(key, Float.valueOf((float) ((Short) value).shortValue()).floatValue());
            }
            if (value instanceof Long) {
                return new AnalyticAttribute(key, Float.valueOf((float) ((Long) value).longValue()).floatValue());
            }
            if (value instanceof BigDecimal) {
                return new AnalyticAttribute(key, Float.valueOf(((BigDecimal) value).floatValue()).floatValue());
            }
            if (value instanceof BigInteger) {
                return new AnalyticAttribute(key, Float.valueOf(((BigInteger) value).floatValue()).floatValue());
            }
            if (value instanceof Boolean) {
                return new AnalyticAttribute(key, Boolean.valueOf(((Boolean) value).booleanValue()).booleanValue());
            }
            log.error("Unsupported event attribute type for key [" + key + "]: " + value.getClass().getName());
            return null;
        } catch (ClassCastException e) {
            log.error(String.format("Error casting attribute [%s] to String or Float: ", new Object[]{key}), e);
            return null;
        }
    }

    public boolean recordCustomEvent(String eventType, Map<String, Object> eventAttributes) {
        log.verbose("AnalyticsControllerImpl.recordCustomEvent - " + eventType + ": " + eventAttributes.size() + " attributes");
        if (!isInitializedAndEnabled() || this.eventManager.isEventTypeReserved(eventType) || !this.eventManager.isEventTypeValid(eventType)) {
            return false;
        }
        String eventName = eventType;
        Set<AnalyticAttribute> attributes = new HashSet();
        try {
            for (String key : eventAttributes.keySet()) {
                AnalyticAttribute attr = createAttribute(key, eventAttributes.get(key));
                if (attr == null) {
                    return false;
                }
                if (attr.getName().equals("name")) {
                    String name = attr.getStringValue();
                    if (!(name == null || name.isEmpty())) {
                        eventName = attr.getStringValue();
                    }
                }
                attributes.add(attr);
            }
        } catch (Exception e) {
            log.error(String.format("Error occurred while recording event [%s]: ", new Object[]{eventType}), e);
        }
        return addEvent(eventName, AnalyticsEventCategory.Custom, eventType, attributes);
    }

    public boolean recordBreadcrumb(String name, Map<String, Object> eventAttributes) {
        if (!isInitializedAndEnabled()) {
            return false;
        }
        Set<AnalyticAttribute> attributes = new HashSet();
        try {
            for (String key : eventAttributes.keySet()) {
                AnalyticAttribute attr = createAttribute(key, eventAttributes.get(key));
                if (attr == null) {
                    return false;
                }
                attributes.add(attr);
            }
        } catch (Exception e) {
            log.error(String.format("Error occurred while recording event [%s]: ", new Object[]{name}), e);
        }
        return addEvent(name, AnalyticsEventCategory.Breadcrumb, AnalyticAttribute.EVENT_TYPE_ATTRIBUTE_MOBILE_BREADCRUMB, attributes);
    }

    public boolean recordEvent(String name, AnalyticsEventCategory eventCategory, String eventType, Map<String, Object> eventAttributes) {
        log.verbose("AnalyticsControllerImpl.recordEvent - " + name + ": " + eventAttributes.size() + " attributes");
        if (!isInitializedAndEnabled() || !this.eventManager.isEventTypeValid(eventType)) {
            return false;
        }
        Set<AnalyticAttribute> attributes = new HashSet();
        try {
            for (String key : eventAttributes.keySet()) {
                AnalyticAttribute attr = createAttribute(key, eventAttributes.get(key));
                if (attr == null) {
                    return false;
                }
                attributes.add(attr);
            }
        } catch (Exception e) {
            log.error(String.format("Error occurred while recording event [%s]: ", new Object[]{name}), e);
        }
        return addEvent(name, eventCategory, eventType, attributes);
    }

    public boolean recordEvent(String name, Map<String, Object> eventAttributes) {
        log.verbose("AnalyticsControllerImpl.recordEvent - " + name + ": " + eventAttributes.size() + " attributes");
        if (!isInitializedAndEnabled()) {
            return false;
        }
        Set<AnalyticAttribute> attributes = new HashSet();
        try {
            for (String key : eventAttributes.keySet()) {
                AnalyticAttribute attr = createAttribute(key, eventAttributes.get(key));
                if (attr == null) {
                    return false;
                }
                attributes.add(attr);
            }
        } catch (Exception e) {
            log.error(String.format("Error occurred while recording event [%s]: ", new Object[]{name}), e);
        }
        return addEvent(name, AnalyticsEventCategory.Custom, AnalyticAttribute.EVENT_TYPE_ATTRIBUTE_MOBILE, attributes);
    }

    /* Access modifiers changed, original: 0000 */
    public void createHttpErrorEvent(HttpTransaction txn) {
        if (isInitializedAndEnabled()) {
            NetworkEventController.createHttpErrorEvent(txn);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void createNetworkFailureEvent(HttpTransaction txn) {
        if (isInitializedAndEnabled()) {
            NetworkEventController.createNetworkFailureEvent(txn);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void createNetworkRequestEvent(HttpTransaction txn) {
        if (isInitializedAndEnabled()) {
            NetworkEventController.createNetworkRequestEvent(txn);
        }
    }

    public void createNetworkRequestEvents(HttpTransaction txn) {
        if (!isInitializedAndEnabled()) {
            return;
        }
        if (isHttpError(txn)) {
            NetworkEventController.createHttpErrorEvent(txn);
        } else if (isNetworkFailure(txn)) {
            NetworkEventController.createNetworkFailureEvent(txn);
        } else if (isSuccessfulRequest(txn)) {
            NetworkEventController.createNetworkRequestEvent(txn);
        }
    }

    private boolean isNetworkFailure(HttpTransaction txn) {
        return txn.getErrorCode() != 0;
    }

    private boolean isHttpError(HttpTransaction txn) {
        return ((long) txn.getStatusCode()) >= 400;
    }

    private boolean isSuccessfulRequest(HttpTransaction txn) {
        return txn.getStatusCode() > 0 && txn.getStatusCode() < MWDeliveryStatusResponse.STATUS_ORDER_DELIVERED;
    }

    private boolean isInitializedAndEnabled() {
        if (!initialized.get()) {
            log.warning("Analytics controller is not initialized!");
            return false;
        } else if (this.isEnabled.get()) {
            return true;
        } else {
            log.warning("Analytics controller is not enabled!");
            return false;
        }
    }

    public void setEnabled(boolean enabled) {
        this.isEnabled.set(enabled);
    }

    private boolean addNewUserAttribute(AnalyticAttribute attribute) {
        if (this.userAttributes.size() < 64) {
            this.userAttributes.add(attribute);
            if (attribute.isPersistent() && !this.attributeStore.store(attribute)) {
                log.error("Failed to store attribute " + attribute + " to attribute store.");
                return false;
            }
        }
        log.warning("Attribute limit exceeded: at most 64 are allowed.");
        log.debug("Currently defined attributes:");
        Iterator it = this.userAttributes.iterator();
        while (it.hasNext()) {
            AnalyticAttribute attr = (AnalyticAttribute) it.next();
            log.debug("\t" + attr.getName() + ": " + attr.valueAsString());
        }
        return true;
    }
}
