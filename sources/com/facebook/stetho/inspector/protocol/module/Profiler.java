package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsMethod;
import com.facebook.stetho.json.annotation.JsonProperty;
import java.util.Collections;
import java.util.List;
import org.json.JSONObject;

public class Profiler implements ChromeDevtoolsDomain {

    private static class ProfileHeader {
        @JsonProperty(required = true)
        String title;
        @JsonProperty(required = true)
        String typeId;
        @JsonProperty(required = true)
        int uid;

        private ProfileHeader() {
        }
    }

    private static class ProfileHeaderResponse implements JsonRpcResult {
        @JsonProperty(required = true)
        public List<ProfileHeader> headers;

        private ProfileHeaderResponse() {
        }
    }

    @ChromeDevtoolsMethod
    public void enable(JsonRpcPeer peer, JSONObject params) {
    }

    @ChromeDevtoolsMethod
    public void disable(JsonRpcPeer peer, JSONObject params) {
    }

    @ChromeDevtoolsMethod
    public void setSamplingInterval(JsonRpcPeer peer, JSONObject params) {
    }

    @ChromeDevtoolsMethod
    public JsonRpcResult getProfileHeaders(JsonRpcPeer peer, JSONObject params) {
        ProfileHeaderResponse response = new ProfileHeaderResponse();
        response.headers = Collections.emptyList();
        return response;
    }
}
