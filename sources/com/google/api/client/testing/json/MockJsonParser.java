package com.google.api.client.testing.json;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.JsonToken;
import com.google.api.client.util.Beta;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

@Beta
public class MockJsonParser extends JsonParser {
    private final JsonFactory factory;
    private boolean isClosed;

    MockJsonParser(JsonFactory factory) {
        this.factory = factory;
    }

    public JsonFactory getFactory() {
        return this.factory;
    }

    public void close() throws IOException {
        this.isClosed = true;
    }

    public JsonToken nextToken() throws IOException {
        return null;
    }

    public JsonToken getCurrentToken() {
        return null;
    }

    public String getCurrentName() throws IOException {
        return null;
    }

    public JsonParser skipChildren() throws IOException {
        return null;
    }

    public String getText() throws IOException {
        return null;
    }

    public byte getByteValue() throws IOException {
        return (byte) 0;
    }

    public short getShortValue() throws IOException {
        return (short) 0;
    }

    public int getIntValue() throws IOException {
        return 0;
    }

    public float getFloatValue() throws IOException {
        return 0.0f;
    }

    public long getLongValue() throws IOException {
        return 0;
    }

    public double getDoubleValue() throws IOException {
        return 0.0d;
    }

    public BigInteger getBigIntegerValue() throws IOException {
        return null;
    }

    public BigDecimal getDecimalValue() throws IOException {
        return null;
    }
}
