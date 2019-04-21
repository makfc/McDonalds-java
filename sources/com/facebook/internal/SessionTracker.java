package com.facebook.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.p000v4.content.LocalBroadcastManager;
import com.facebook.Session;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionState;

public class SessionTracker {
    private final LocalBroadcastManager broadcastManager;
    private final StatusCallback callback;
    private boolean isTracking;
    private final BroadcastReceiver receiver;
    private Session session;

    private class ActiveSessionBroadcastReceiver extends BroadcastReceiver {
        private ActiveSessionBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (Session.ACTION_ACTIVE_SESSION_SET.equals(intent.getAction())) {
                Session session = Session.getActiveSession();
                if (session != null) {
                    session.addCallback(SessionTracker.this.callback);
                }
            }
        }
    }

    private class CallbackWrapper implements StatusCallback {
        private final StatusCallback wrapped;

        public CallbackWrapper(StatusCallback wrapped) {
            this.wrapped = wrapped;
        }

        public void call(Session session, SessionState state, Exception exception) {
            if (this.wrapped != null && SessionTracker.this.isTracking()) {
                this.wrapped.call(session, state, exception);
            }
            if (session == SessionTracker.this.session && state.isClosed()) {
                SessionTracker.this.setSession(null);
            }
        }
    }

    public SessionTracker(Context context, StatusCallback callback) {
        this(context, callback, null);
    }

    SessionTracker(Context context, StatusCallback callback, Session session) {
        this(context, callback, session, true);
    }

    public SessionTracker(Context context, StatusCallback callback, Session session, boolean startTracking) {
        this.isTracking = false;
        this.callback = new CallbackWrapper(callback);
        this.session = session;
        this.receiver = new ActiveSessionBroadcastReceiver();
        this.broadcastManager = LocalBroadcastManager.getInstance(context);
        if (startTracking) {
            startTracking();
        }
    }

    public Session getSession() {
        return this.session == null ? Session.getActiveSession() : this.session;
    }

    public Session getOpenSession() {
        Session openSession = getSession();
        return (openSession == null || !openSession.isOpened()) ? null : openSession;
    }

    public void setSession(Session newSession) {
        if (newSession != null) {
            if (this.session == null) {
                Session activeSession = Session.getActiveSession();
                if (activeSession != null) {
                    activeSession.removeCallback(this.callback);
                }
                this.broadcastManager.unregisterReceiver(this.receiver);
            } else {
                this.session.removeCallback(this.callback);
            }
            this.session = newSession;
            this.session.addCallback(this.callback);
        } else if (this.session != null) {
            this.session.removeCallback(this.callback);
            this.session = null;
            addBroadcastReceiver();
            if (getSession() != null) {
                getSession().addCallback(this.callback);
            }
        }
    }

    public void startTracking() {
        if (!this.isTracking) {
            if (this.session == null) {
                addBroadcastReceiver();
            }
            if (getSession() != null) {
                getSession().addCallback(this.callback);
            }
            this.isTracking = true;
        }
    }

    public void stopTracking() {
        if (this.isTracking) {
            Session session = getSession();
            if (session != null) {
                session.removeCallback(this.callback);
            }
            this.broadcastManager.unregisterReceiver(this.receiver);
            this.isTracking = false;
        }
    }

    public boolean isTracking() {
        return this.isTracking;
    }

    public boolean isTrackingActiveSession() {
        return this.session == null;
    }

    private void addBroadcastReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Session.ACTION_ACTIVE_SESSION_SET);
        filter.addAction(Session.ACTION_ACTIVE_SESSION_UNSET);
        this.broadcastManager.registerReceiver(this.receiver, filter);
    }
}
