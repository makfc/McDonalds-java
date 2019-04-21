package com.facebook;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.p000v4.content.LocalBroadcastManager;
import android.util.Log;
import com.facebook.Session.StatusCallback;
import com.facebook.internal.LikeActionController;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.PendingCallStore;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.FacebookDialog.Callback;
import com.facebook.widget.FacebookDialog.PendingCall;
import com.mcdonalds.sdk.modules.models.SocialNetwork;
import java.util.UUID;

public class UiLifecycleHelper {
    private static final String ACTIVITY_NULL_MESSAGE = "activity cannot be null";
    private static final String DIALOG_CALL_ID_SAVE_KEY = "com.facebook.UiLifecycleHelper.pendingFacebookDialogCallKey";
    private final Activity activity;
    private AppEventsLogger appEventsLogger;
    private final LocalBroadcastManager broadcastManager;
    private final StatusCallback callback;
    private UUID pendingFacebookDialogCallId;
    private PendingCallStore pendingFacebookDialogCallStore;
    private final BroadcastReceiver receiver;

    private class ActiveSessionBroadcastReceiver extends BroadcastReceiver {
        private ActiveSessionBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            Session session;
            if (Session.ACTION_ACTIVE_SESSION_SET.equals(intent.getAction())) {
                session = Session.getActiveSession();
                if (session != null && UiLifecycleHelper.this.callback != null) {
                    session.addCallback(UiLifecycleHelper.this.callback);
                }
            } else if (Session.ACTION_ACTIVE_SESSION_UNSET.equals(intent.getAction())) {
                session = Session.getActiveSession();
                if (session != null && UiLifecycleHelper.this.callback != null) {
                    session.removeCallback(UiLifecycleHelper.this.callback);
                }
            }
        }
    }

    public UiLifecycleHelper(Activity activity, StatusCallback callback) {
        if (activity == null) {
            throw new IllegalArgumentException(ACTIVITY_NULL_MESSAGE);
        }
        this.activity = activity;
        this.callback = callback;
        this.receiver = new ActiveSessionBroadcastReceiver();
        this.broadcastManager = LocalBroadcastManager.getInstance(activity);
        this.pendingFacebookDialogCallStore = PendingCallStore.getInstance();
        Settings.sdkInitialize(activity);
    }

    public void onCreate(Bundle savedInstanceState) {
        Session session = Session.getActiveSession();
        if (session == null) {
            if (savedInstanceState != null) {
                session = Session.restoreSession(this.activity, null, this.callback, savedInstanceState);
            }
            if (session == null) {
                session = new Session(this.activity);
            }
            Session.setActiveSession(session);
        }
        if (savedInstanceState != null) {
            String callIdString = savedInstanceState.getString(DIALOG_CALL_ID_SAVE_KEY);
            if (callIdString != null) {
                this.pendingFacebookDialogCallId = UUID.fromString(callIdString);
            }
            this.pendingFacebookDialogCallStore.restoreFromSavedInstanceState(savedInstanceState);
        }
    }

    public void onResume() {
        Session session = Session.getActiveSession();
        if (session != null) {
            if (this.callback != null) {
                session.addCallback(this.callback);
            }
            if (SessionState.CREATED_TOKEN_LOADED.equals(session.getState())) {
                session.openForRead(null);
            }
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(Session.ACTION_ACTIVE_SESSION_SET);
        filter.addAction(Session.ACTION_ACTIVE_SESSION_UNSET);
        this.broadcastManager.registerReceiver(this.receiver, filter);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        onActivityResult(requestCode, resultCode, data, null);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data, Callback facebookDialogCallback) {
        Session session = Session.getActiveSession();
        if (session != null) {
            session.onActivityResult(this.activity, requestCode, resultCode, data);
        }
        if (!LikeActionController.handleOnActivityResult(this.activity, requestCode, resultCode, data)) {
            handleFacebookDialogActivityResult(requestCode, resultCode, data, facebookDialogCallback);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        Session.saveSession(Session.getActiveSession(), outState);
        if (this.pendingFacebookDialogCallId != null) {
            outState.putString(DIALOG_CALL_ID_SAVE_KEY, this.pendingFacebookDialogCallId.toString());
        }
        this.pendingFacebookDialogCallStore.saveInstanceState(outState);
    }

    public void onPause() {
        this.broadcastManager.unregisterReceiver(this.receiver);
        if (this.callback != null) {
            Session session = Session.getActiveSession();
            if (session != null) {
                session.removeCallback(this.callback);
            }
        }
    }

    public void onStop() {
        AppEventsLogger.onContextStop();
    }

    public void onDestroy() {
    }

    public void trackPendingDialogCall(PendingCall pendingCall) {
        if (this.pendingFacebookDialogCallId != null) {
            Log.i(SocialNetwork.FACEBOOK_NAME, "Tracking new app call while one is still pending; canceling pending call.");
            cancelPendingAppCall(null);
        }
        if (pendingCall != null) {
            this.pendingFacebookDialogCallId = pendingCall.getCallId();
            this.pendingFacebookDialogCallStore.trackPendingCall(pendingCall);
        }
    }

    public AppEventsLogger getAppEventsLogger() {
        Session session = Session.getActiveSession();
        if (session == null) {
            return null;
        }
        if (this.appEventsLogger == null || !this.appEventsLogger.isValidForSession(session)) {
            if (this.appEventsLogger != null) {
                AppEventsLogger.onContextStop();
            }
            this.appEventsLogger = AppEventsLogger.newLogger(this.activity, session);
        }
        return this.appEventsLogger;
    }

    private boolean handleFacebookDialogActivityResult(int requestCode, int resultCode, Intent data, Callback facebookDialogCallback) {
        if (this.pendingFacebookDialogCallId == null) {
            return false;
        }
        PendingCall pendingCall = this.pendingFacebookDialogCallStore.getPendingCallById(this.pendingFacebookDialogCallId);
        if (pendingCall == null || pendingCall.getRequestCode() != requestCode) {
            return false;
        }
        if (data == null) {
            cancelPendingAppCall(facebookDialogCallback);
            return true;
        }
        UUID callId = NativeProtocol.getCallIdFromIntent(data);
        if (callId == null || !this.pendingFacebookDialogCallId.equals(callId)) {
            cancelPendingAppCall(facebookDialogCallback);
        } else {
            FacebookDialog.handleActivityResult(this.activity, pendingCall, requestCode, data, facebookDialogCallback);
        }
        stopTrackingPendingAppCall();
        return true;
    }

    private void cancelPendingAppCall(Callback facebookDialogCallback) {
        if (this.pendingFacebookDialogCallId != null) {
            PendingCall pendingCall = this.pendingFacebookDialogCallStore.getPendingCallById(this.pendingFacebookDialogCallId);
            if (pendingCall != null) {
                if (facebookDialogCallback != null) {
                    Intent pendingIntent = pendingCall.getRequestIntent();
                    Intent cancelIntent = new Intent();
                    cancelIntent.putExtra(NativeProtocol.EXTRA_PROTOCOL_CALL_ID, pendingIntent.getStringExtra(NativeProtocol.EXTRA_PROTOCOL_CALL_ID));
                    cancelIntent.putExtra(NativeProtocol.EXTRA_PROTOCOL_ACTION, pendingIntent.getStringExtra(NativeProtocol.EXTRA_PROTOCOL_ACTION));
                    cancelIntent.putExtra(NativeProtocol.EXTRA_PROTOCOL_VERSION, pendingIntent.getIntExtra(NativeProtocol.EXTRA_PROTOCOL_VERSION, 0));
                    cancelIntent.putExtra(NativeProtocol.STATUS_ERROR_TYPE, NativeProtocol.ERROR_UNKNOWN_ERROR);
                    FacebookDialog.handleActivityResult(this.activity, pendingCall, pendingCall.getRequestCode(), cancelIntent, facebookDialogCallback);
                }
                stopTrackingPendingAppCall();
            }
        }
    }

    private void stopTrackingPendingAppCall() {
        this.pendingFacebookDialogCallStore.stopTrackingPendingCall(this.pendingFacebookDialogCallId);
        this.pendingFacebookDialogCallId = null;
    }
}
