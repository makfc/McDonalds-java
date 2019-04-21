package com.newrelic.agent.android.harvest;

import com.autonavi.amap.mapcore.VTMCDataCache;
import com.mcdonalds.sdk.connectors.middleware.response.MWDeliveryStatusResponse;

public class HarvestResponse {
    private static final String DISABLE_STRING = "DISABLE_NEW_RELIC";
    private String responseBody;
    private long responseTime;
    private int statusCode;

    public enum Code {
        OK(200),
        UNAUTHORIZED(401),
        FORBIDDEN(403),
        ENTITY_TOO_LARGE(413),
        INVALID_AGENT_ID(450),
        UNSUPPORTED_MEDIA_TYPE(415),
        INTERNAL_SERVER_ERROR(VTMCDataCache.MAXSIZE),
        UNKNOWN(-1);
        
        int statusCode;

        private Code(int statusCode) {
            this.statusCode = statusCode;
        }

        public int getStatusCode() {
            return this.statusCode;
        }

        public boolean isError() {
            return this != OK;
        }

        public boolean isOK() {
            return !isError();
        }
    }

    public Code getResponseCode() {
        if (isOK()) {
            return Code.OK;
        }
        for (Code code : Code.values()) {
            if (code.getStatusCode() == this.statusCode) {
                return code;
            }
        }
        return Code.UNKNOWN;
    }

    public boolean isDisableCommand() {
        return Code.FORBIDDEN == getResponseCode() && DISABLE_STRING.equals(getResponseBody());
    }

    public boolean isError() {
        return this.statusCode >= MWDeliveryStatusResponse.STATUS_ORDER_DELIVERED;
    }

    public boolean isUnknown() {
        return getResponseCode() == Code.UNKNOWN;
    }

    public boolean isOK() {
        return !isError();
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getResponseBody() {
        return this.responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public long getResponseTime() {
        return this.responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }
}
