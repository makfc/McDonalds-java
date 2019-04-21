package com.facebook.stetho.inspector.helper;

import com.facebook.stetho.common.LogRedirector;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.jsonrpc.DisconnectReceiver;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.jsonrpc.PendingRequestCallback;
import java.nio.channels.NotYetConnectedException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public class ChromePeerManager {
    private static final String TAG = "ChromePeerManager";
    @GuardedBy
    private PeerRegistrationListener mListener;
    @GuardedBy
    private final Map<JsonRpcPeer, DisconnectReceiver> mReceivingPeers = new HashMap();
    @GuardedBy
    private JsonRpcPeer[] mReceivingPeersSnapshot;

    private class UnregisterOnDisconnect implements DisconnectReceiver {
        private final JsonRpcPeer mPeer;

        public UnregisterOnDisconnect(JsonRpcPeer peer) {
            this.mPeer = peer;
        }

        public void onDisconnect() {
            ChromePeerManager.this.removePeer(this.mPeer);
        }
    }

    public synchronized void setListener(PeerRegistrationListener listener) {
        this.mListener = listener;
    }

    public synchronized boolean addPeer(JsonRpcPeer peer) {
        boolean z;
        if (this.mReceivingPeers.containsKey(peer)) {
            z = false;
        } else {
            DisconnectReceiver disconnectReceiver = new UnregisterOnDisconnect(peer);
            peer.registerDisconnectReceiver(disconnectReceiver);
            this.mReceivingPeers.put(peer, disconnectReceiver);
            this.mReceivingPeersSnapshot = null;
            if (this.mListener != null) {
                this.mListener.onPeerRegistered(peer);
            }
            z = true;
        }
        return z;
    }

    public synchronized void removePeer(JsonRpcPeer peer) {
        if (this.mReceivingPeers.remove(peer) != null) {
            this.mReceivingPeersSnapshot = null;
            if (this.mListener != null) {
                this.mListener.onPeerUnregistered(peer);
            }
        }
    }

    public synchronized boolean hasRegisteredPeers() {
        return !this.mReceivingPeers.isEmpty();
    }

    private synchronized JsonRpcPeer[] getReceivingPeersSnapshot() {
        if (this.mReceivingPeersSnapshot == null) {
            this.mReceivingPeersSnapshot = (JsonRpcPeer[]) this.mReceivingPeers.keySet().toArray(new JsonRpcPeer[this.mReceivingPeers.size()]);
        }
        return this.mReceivingPeersSnapshot;
    }

    public void sendNotificationToPeers(String method, Object params) {
        sendMessageToPeers(method, params, null);
    }

    public void invokeMethodOnPeers(String method, Object params, PendingRequestCallback callback) {
        Util.throwIfNull(callback);
        sendMessageToPeers(method, params, callback);
    }

    private void sendMessageToPeers(String method, Object params, @Nullable PendingRequestCallback callback) {
        for (JsonRpcPeer peer : getReceivingPeersSnapshot()) {
            try {
                peer.invokeMethod(method, params, callback);
            } catch (NotYetConnectedException e) {
                LogRedirector.m7436e(TAG, "Error delivering data to Chrome", e);
            }
        }
    }
}
