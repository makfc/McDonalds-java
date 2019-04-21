package com.newrelic.agent.android.analytics;

import com.newrelic.agent.android.util.SafeJsonPrimitive;
import com.newrelic.com.google.gson.JsonElement;
import com.newrelic.com.google.gson.JsonObject;
import com.newrelic.com.google.gson.JsonPrimitive;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

public class AnalyticAttribute {
    public static final String ACCOUNT_ID_ATTRIBUTE = "accountId";
    public static final String APPLICATION_PLATFORM_ATTRIBUTE = "platform";
    public static final String APPLICATION_PLATFORM_VERSION_ATTRIBUTE = "platformVersion";
    public static final String APP_BUILD_ATTRIBUTE = "appBuild";
    public static final String APP_ID_ATTRIBUTE = "appId";
    public static final String APP_INSTALL_ATTRIBUTE = "install";
    public static final String APP_NAME_ATTRIBUTE = "appName";
    public static final String APP_UPGRADE_ATTRIBUTE = "upgradeFrom";
    public static final String ARCHITECTURE_ATTRIBUTE = "architecture";
    public static final int ATTRIBUTE_NAME_MAX_LENGTH = 256;
    public static final int ATTRIBUTE_VALUE_MAX_LENGTH = 4096;
    public static final String BYTES_RECEIVED_ATTRIBUTE = "bytesReceived";
    public static final String BYTES_SENT_ATTRIBUTE = "bytesSent";
    public static final String CARRIER_ATTRIBUTE = "carrier";
    public static final String CONNECTION_TYPE_ATTRIBUTE = "connectionType";
    public static final String DEVICE_MANUFACTURER_ATTRIBUTE = "deviceManufacturer";
    public static final String DEVICE_MODEL_ATTRIBUTE = "deviceModel";
    public static final String EVENT_CATEGORY_ATTRIBUTE = "category";
    public static final String EVENT_NAME_ATTRIBUTE = "name";
    public static final String EVENT_TIMESTAMP_ATTRIBUTE = "timestamp";
    public static final String EVENT_TYPE_ATTRIBUTE = "eventType";
    public static final String EVENT_TYPE_ATTRIBUTE_MOBILE = "Mobile";
    public static final String EVENT_TYPE_ATTRIBUTE_MOBILE_BREADCRUMB = "MobileBreadcrumb";
    public static final String EVENT_TYPE_ATTRIBUTE_MOBILE_CRASH = "MobileCrash";
    public static final String EVENT_TYPE_ATTRIBUTE_MOBILE_REQUEST = "MobileRequest";
    public static final String EVENT_TYPE_ATTRIBUTE_MOBILE_REQUEST_ERROR = "MobileRequestError";
    public static final String INTERACTION_DURATION_ATTRIBUTE = "interactionDuration";
    public static final String LAST_INTERACTION_ATTRIBUTE = "lastInteraction";
    public static final String MEM_USAGE_MB_ATTRIBUTE = "memUsageMb";
    public static final String NETWORK_ERROR_CODE_ATTRIBUTE = "networkErrorCode";
    public static final String NEW_RELIC_VERSION_ATTRIBUTE = "newRelicVersion";
    public static final String OS_BUILD_ATTRIBUTE = "osBuild";
    public static final String OS_MAJOR_VERSION_ATTRIBUTE = "osMajorVersion";
    public static final String OS_NAME_ATTRIBUTE = "osName";
    public static final String OS_VERSION_ATTRIBUTE = "osVersion";
    public static final String REQUEST_DOMAIN_ATTRIBUTE = "requestDomain";
    public static final String REQUEST_METHOD_ATTRIBUTE = "requestMethod";
    public static final String REQUEST_PATH_ATTRIBUTE = "requestPath";
    public static final String REQUEST_URL_ATTRIBUTE = "requestUrl";
    public static final String RESPONSE_TIME_ATTRIBUTE = "responseTime";
    public static final String RUNTIME_ATTRIBUTE = "runTime";
    public static final String SESSION_DURATION_ATTRIBUTE = "sessionDuration";
    public static final String SESSION_ID_ATTRIBUTE = "sessionId";
    public static final String SESSION_TIME_SINCE_LOAD_ATTRIBUTE = "timeSinceLoad";
    public static final String STATUS_CODE_ATTRIBUTE = "statusCode";
    public static final String TYPE_ATTRIBUTE = "type";
    public static final String USER_ID_ATTRIBUTE = "userId";
    public static final String UUID_ATTRIBUTE = "uuid";
    protected static Set<String> blackList = new C41451();
    private AttributeDataType attributeDataType;
    private float floatValue;
    private boolean isPersistent;
    private String name;
    private String stringValue;

    /* renamed from: com.newrelic.agent.android.analytics.AnalyticAttribute$1 */
    static class C41451 extends HashSet<String> {
        C41451() {
            add(AnalyticAttribute.APP_INSTALL_ATTRIBUTE);
            add(AnalyticAttribute.APP_UPGRADE_ATTRIBUTE);
            add(AnalyticAttribute.SESSION_DURATION_ATTRIBUTE);
        }
    }

    public enum AttributeDataType {
        VOID,
        STRING,
        FLOAT,
        BOOLEAN
    }

    protected AnalyticAttribute() {
        this.stringValue = null;
        this.floatValue = Float.NaN;
        this.isPersistent = false;
        this.attributeDataType = AttributeDataType.VOID;
    }

    public AnalyticAttribute(String name, String stringValue) {
        this(name, stringValue, true);
    }

    public AnalyticAttribute(String name, String stringValue, boolean isPersistent) {
        this.name = name;
        setStringValue(stringValue);
        this.isPersistent = isPersistent;
    }

    public AnalyticAttribute(String name, float floatValue) {
        this(name, floatValue, true);
    }

    public AnalyticAttribute(String name, float floatValue, boolean isPersistent) {
        this.name = name;
        setFloatValue(floatValue);
        this.isPersistent = isPersistent;
    }

    public AnalyticAttribute(String name, boolean boolValue) {
        this(name, boolValue, true);
    }

    public AnalyticAttribute(String name, boolean boolValue, boolean isPersistent) {
        this.name = name;
        setBooleanValue(boolValue);
        this.isPersistent = isPersistent;
    }

    public AnalyticAttribute(AnalyticAttribute clone) {
        this.name = clone.name;
        this.floatValue = clone.floatValue;
        this.stringValue = clone.stringValue;
        this.isPersistent = clone.isPersistent;
        this.attributeDataType = clone.attributeDataType;
    }

    public String getName() {
        return this.name;
    }

    public boolean isStringAttribute() {
        return this.attributeDataType == AttributeDataType.STRING;
    }

    public boolean isFloatAttribute() {
        return this.attributeDataType == AttributeDataType.FLOAT;
    }

    public boolean isBooleanAttribute() {
        return this.attributeDataType == AttributeDataType.BOOLEAN;
    }

    public String getStringValue() {
        return this.attributeDataType == AttributeDataType.STRING ? this.stringValue : null;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
        this.floatValue = Float.NaN;
        this.attributeDataType = AttributeDataType.STRING;
    }

    public float getFloatValue() {
        return this.attributeDataType == AttributeDataType.FLOAT ? this.floatValue : Float.NaN;
    }

    public void setFloatValue(float floatValue) {
        this.floatValue = floatValue;
        this.stringValue = null;
        this.attributeDataType = AttributeDataType.FLOAT;
    }

    public boolean getBooleanValue() {
        return this.attributeDataType == AttributeDataType.BOOLEAN ? Boolean.valueOf(this.stringValue).booleanValue() : false;
    }

    public void setBooleanValue(boolean boolValue) {
        this.stringValue = Boolean.toString(boolValue);
        this.floatValue = Float.NaN;
        this.attributeDataType = AttributeDataType.BOOLEAN;
    }

    public boolean isPersistent() {
        return this.isPersistent && !isAttributeBlacklisted(this);
    }

    public void setPersistent(boolean isPersistent) {
        this.isPersistent = isPersistent;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (this.name.equals(((AnalyticAttribute) o).name)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("AnalyticAttribute{");
        stringBuilder.append("name='" + this.name + "'");
        switch (this.attributeDataType) {
            case STRING:
                stringBuilder.append(",stringValue='" + this.stringValue + "'");
                break;
            case FLOAT:
                stringBuilder.append(",floatValue='" + this.floatValue + "'");
                break;
            case BOOLEAN:
                stringBuilder.append(",booleanValue=" + Boolean.valueOf(this.stringValue).toString());
                break;
        }
        stringBuilder.append(",isPersistent=" + this.isPersistent);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public static boolean isAttributeBlacklisted(AnalyticAttribute attribute) {
        return blackList.contains(attribute.getName());
    }

    public AttributeDataType getAttributeDataType() {
        return this.attributeDataType;
    }

    public String valueAsString() {
        switch (this.attributeDataType) {
            case STRING:
                return this.stringValue;
            case FLOAT:
                return Float.toString(this.floatValue);
            case BOOLEAN:
                return Boolean.valueOf(getBooleanValue()).toString();
            default:
                return null;
        }
    }

    public JsonElement asJsonElement() {
        switch (this.attributeDataType) {
            case STRING:
                return SafeJsonPrimitive.factory(getStringValue());
            case FLOAT:
                return SafeJsonPrimitive.factory(Float.valueOf(getFloatValue()));
            case BOOLEAN:
                return SafeJsonPrimitive.factory(Boolean.valueOf(getBooleanValue()));
            default:
                return null;
        }
    }

    public static Set<AnalyticAttribute> newFromJson(JsonObject attributesJson) {
        Set<AnalyticAttribute> attributeSet = new HashSet();
        for (Entry<String, JsonElement> elem : attributesJson.entrySet()) {
            String key = (String) elem.getKey();
            if (((JsonElement) elem.getValue()).isJsonPrimitive()) {
                JsonPrimitive value = ((JsonElement) elem.getValue()).getAsJsonPrimitive();
                if (value.isString()) {
                    attributeSet.add(new AnalyticAttribute(key, value.getAsString(), false));
                } else if (value.isBoolean()) {
                    attributeSet.add(new AnalyticAttribute(key, value.getAsBoolean(), false));
                } else if (value.isNumber()) {
                    attributeSet.add(new AnalyticAttribute(key, value.getAsFloat(), false));
                }
            } else {
                attributeSet.add(new AnalyticAttribute(key, ((JsonElement) elem.getValue()).getAsString(), false));
            }
        }
        return attributeSet;
    }
}
