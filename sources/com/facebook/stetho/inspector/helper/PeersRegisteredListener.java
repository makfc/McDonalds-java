package com.facebook.stetho.inspector.helper;

import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class PeersRegisteredListener implements PeerRegistrationListener {
    private AtomicInteger mPeers = new AtomicInteger(0);

    public abstract void onFirstPeerRegistered();

    public abstract void onLastPeerUnregistered();

    public final void onPeerRegistered(JsonRpcPeer peer) {
        if (this.mPeers.incrementAndGet() == 1) {
            onFirstPeerRegistered();
        }
        onPeerAdded(peer);
    }

    public final void onPeerUnregistered(JsonRpcPeer peer) {
        if (this.mPeers.decrementAndGet() == 0) {
            onLastPeerUnregistered();
        }
        onPeerRemoved(peer);
    }

    /* Access modifiers changed, original: protected */
    public void onPeerAdded(JsonRpcPeer peer) {
    }

    /* Access modifiers changed, original: protected */
    public void onPeerRemoved(JsonRpcPeer peer) {
    }
}
