package com.facebook.stetho.inspector.jsonrpc;

import android.database.Observable;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcRequest;
import com.facebook.stetho.json.ObjectMapper;
import com.facebook.stetho.websocket.SimpleSession;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.nio.channels.NotYetConnectedException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import org.json.JSONObject;

@ThreadSafe
public class JsonRpcPeer {
    private final DisconnectObservable mDisconnectObservable = new DisconnectObservable();
    @GuardedBy
    private long mNextRequestId;
    private final ObjectMapper mObjectMapper;
    private final SimpleSession mPeer;
    @GuardedBy
    private final Map<Long, PendingRequest> mPendingRequests = new HashMap();

    private static class DisconnectObservable extends Observable<DisconnectReceiver> {
        private DisconnectObservable() {
        }

        public void onDisconnect() {
            int N = this.mObservers.size();
            for (int i = 0; i < N; i++) {
                ((DisconnectReceiver) this.mObservers.get(i)).onDisconnect();
            }
        }
    }

    public JsonRpcPeer(ObjectMapper objectMapper, SimpleSession peer) {
        this.mObjectMapper = objectMapper;
        this.mPeer = (SimpleSession) Util.throwIfNull(peer);
    }

    public SimpleSession getWebSocket() {
        return this.mPeer;
    }

    public void invokeMethod(String method, Object paramsObject, @Nullable PendingRequestCallback callback) throws NotYetConnectedException {
        Util.throwIfNull(method);
        JSONObject jsonObject = (JSONObject) this.mObjectMapper.convertValue(new JsonRpcRequest(callback != null ? Long.valueOf(preparePendingRequest(callback)) : null, method, (JSONObject) this.mObjectMapper.convertValue(paramsObject, JSONObject.class)), JSONObject.class);
        this.mPeer.sendText(!(jsonObject instanceof JSONObject) ? jsonObject.toString() : JSONObjectInstrumentation.toString(jsonObject));
    }

    public void registerDisconnectReceiver(DisconnectReceiver callback) {
        this.mDisconnectObservable.registerObserver(callback);
    }

    public void unregisterDisconnectReceiver(DisconnectReceiver callback) {
        this.mDisconnectObservable.unregisterObserver(callback);
    }

    public void invokeDisconnectReceivers() {
        this.mDisconnectObservable.onDisconnect();
    }

    private synchronized long preparePendingRequest(PendingRequestCallback callback) {
        long requestId;
        requestId = this.mNextRequestId;
        this.mNextRequestId = 1 + requestId;
        this.mPendingRequests.put(Long.valueOf(requestId), new PendingRequest(requestId, callback));
        return requestId;
    }

    public synchronized PendingRequest getAndRemovePendingRequest(long requestId) {
        return (PendingRequest) this.mPendingRequests.remove(Long.valueOf(requestId));
    }
}
