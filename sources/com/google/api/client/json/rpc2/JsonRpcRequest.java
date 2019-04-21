package com.google.api.client.json.rpc2;

import com.google.api.client.util.Beta;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;

@Beta
public class JsonRpcRequest extends GenericData {
    @Key
    private final String jsonrpc = "2.0";

    public JsonRpcRequest set(String fieldName, Object value) {
        return (JsonRpcRequest) super.set(fieldName, value);
    }

    public JsonRpcRequest clone() {
        return (JsonRpcRequest) super.clone();
    }
}
