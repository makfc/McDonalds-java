package com.newrelic.agent.android.payload;

import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import com.newrelic.com.google.gson.JsonObject;
import java.nio.ByteBuffer;
import java.util.UUID;

public class Payload {
    private boolean isPersistable;
    private ByteBuffer payload;
    private final long timestamp;
    private final String uuid;

    public Payload() {
        this.isPersistable = true;
        this.timestamp = System.currentTimeMillis();
        this.uuid = UUID.randomUUID().toString();
        this.isPersistable = true;
    }

    public Payload(byte[] bytes) {
        this();
        this.payload = ByteBuffer.wrap(bytes);
    }

    public byte[] getBytes() {
        return this.payload.array();
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public String getUuid() {
        return this.uuid;
    }

    public boolean isStale(long ttl) {
        return this.timestamp + ttl <= System.currentTimeMillis();
    }

    public void putBytes(byte[] payloadBytes) {
        this.payload = ByteBuffer.wrap(payloadBytes);
    }

    public void setPersisted(boolean isPersistable) {
        this.isPersistable = isPersistable;
    }

    public boolean isPersisted() {
        return this.isPersistable;
    }

    public boolean equals(Object object) {
        if (object == null || !(object instanceof Payload)) {
            return false;
        }
        return this.uuid.equalsIgnoreCase(((Payload) object).uuid);
    }

    public JsonObject asJsonObject() {
        JsonObject jsonObj = new JsonObject();
        jsonObj.add(AnalyticAttribute.EVENT_TIMESTAMP_ATTRIBUTE, SafeJsonPrimitive.factory(Long.valueOf(this.timestamp)));
        jsonObj.add(AnalyticAttribute.UUID_ATTRIBUTE, SafeJsonPrimitive.factory(this.uuid));
        return jsonObj;
    }

    public String asJsonMeta() {
        return asJsonObject().toString();
    }
}
