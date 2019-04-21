package com.facebook.stetho.inspector.jsonrpc.protocol;

import android.annotation.SuppressLint;
import com.facebook.stetho.json.annotation.JsonProperty;
import com.facebook.stetho.json.annotation.JsonValue;
import javax.annotation.Nullable;
import org.json.JSONObject;

@SuppressLint({"UsingDefaultJsonDeserializer", "EmptyJsonPropertyUse"})
public class JsonRpcError {
    @JsonProperty(required = true)
    public ErrorCode code;
    @JsonProperty
    public JSONObject data;
    @JsonProperty(required = true)
    public String message;

    public enum ErrorCode {
        PARSER_ERROR(-32700),
        INVALID_REQUEST(-32600),
        METHOD_NOT_FOUND(-32601),
        INVALID_PARAMS(-32602),
        INTERNAL_ERROR(-32603);
        
        private final int mProtocolValue;

        private ErrorCode(int protocolValue) {
            this.mProtocolValue = protocolValue;
        }

        @JsonValue
        public int getProtocolValue() {
            return this.mProtocolValue;
        }
    }

    public JsonRpcError(ErrorCode code, String message, @Nullable JSONObject data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
