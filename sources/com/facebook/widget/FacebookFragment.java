package com.facebook.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import com.facebook.Session;
import com.facebook.Session.Builder;
import com.facebook.Session.OpenRequest;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionLoginBehavior;
import com.facebook.SessionState;
import com.facebook.internal.SessionAuthorizationType;
import com.facebook.internal.SessionTracker;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import java.util.Date;
import java.util.List;

@Instrumented
class FacebookFragment extends Fragment implements TraceFieldInterface {
    private SessionTracker sessionTracker;

    private class DefaultSessionStatusCallback implements StatusCallback {
        private DefaultSessionStatusCallback() {
        }

        public void call(Session session, SessionState state, Exception exception) {
            FacebookFragment.this.onSessionStateChange(state, exception);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    /* Access modifiers changed, original: protected */
    public void onStop() {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }

    FacebookFragment() {
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.sessionTracker = new SessionTracker(getActivity(), new DefaultSessionStatusCallback());
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.sessionTracker.getSession().onActivityResult(getActivity(), requestCode, resultCode, data);
    }

    public void onDestroy() {
        super.onDestroy();
        this.sessionTracker.stopTracking();
    }

    public void setSession(Session newSession) {
        if (this.sessionTracker != null) {
            this.sessionTracker.setSession(newSession);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onSessionStateChange(SessionState state, Exception exception) {
    }

    /* Access modifiers changed, original: protected|final */
    public final Session getSession() {
        if (this.sessionTracker != null) {
            return this.sessionTracker.getSession();
        }
        return null;
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean isSessionOpen() {
        if (this.sessionTracker == null || this.sessionTracker.getOpenSession() == null) {
            return false;
        }
        return true;
    }

    /* Access modifiers changed, original: protected|final */
    public final SessionState getSessionState() {
        if (this.sessionTracker == null) {
            return null;
        }
        Session currentSession = this.sessionTracker.getSession();
        if (currentSession != null) {
            return currentSession.getState();
        }
        return null;
    }

    /* Access modifiers changed, original: protected|final */
    public final String getAccessToken() {
        if (this.sessionTracker == null) {
            return null;
        }
        Session currentSession = this.sessionTracker.getOpenSession();
        if (currentSession != null) {
            return currentSession.getAccessToken();
        }
        return null;
    }

    /* Access modifiers changed, original: protected|final */
    public final Date getExpirationDate() {
        if (this.sessionTracker == null) {
            return null;
        }
        Session currentSession = this.sessionTracker.getOpenSession();
        if (currentSession != null) {
            return currentSession.getExpirationDate();
        }
        return null;
    }

    /* Access modifiers changed, original: protected|final */
    public final void closeSession() {
        if (this.sessionTracker != null) {
            Session currentSession = this.sessionTracker.getOpenSession();
            if (currentSession != null) {
                currentSession.close();
            }
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void closeSessionAndClearTokenInformation() {
        if (this.sessionTracker != null) {
            Session currentSession = this.sessionTracker.getOpenSession();
            if (currentSession != null) {
                currentSession.closeAndClearTokenInformation();
            }
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final List<String> getSessionPermissions() {
        if (this.sessionTracker == null) {
            return null;
        }
        Session currentSession = this.sessionTracker.getSession();
        if (currentSession != null) {
            return currentSession.getPermissions();
        }
        return null;
    }

    /* Access modifiers changed, original: protected|final */
    public final void openSession() {
        openSessionForRead(null, null);
    }

    /* Access modifiers changed, original: protected|final */
    public final void openSessionForRead(String applicationId, List<String> permissions) {
        openSessionForRead(applicationId, permissions, SessionLoginBehavior.SSO_WITH_FALLBACK, Session.DEFAULT_AUTHORIZE_ACTIVITY_CODE);
    }

    /* Access modifiers changed, original: protected|final */
    public final void openSessionForRead(String applicationId, List<String> permissions, SessionLoginBehavior behavior, int activityCode) {
        openSession(applicationId, permissions, behavior, activityCode, SessionAuthorizationType.READ);
    }

    /* Access modifiers changed, original: protected|final */
    public final void openSessionForPublish(String applicationId, List<String> permissions) {
        openSessionForPublish(applicationId, permissions, SessionLoginBehavior.SSO_WITH_FALLBACK, Session.DEFAULT_AUTHORIZE_ACTIVITY_CODE);
    }

    /* Access modifiers changed, original: protected|final */
    public final void openSessionForPublish(String applicationId, List<String> permissions, SessionLoginBehavior behavior, int activityCode) {
        openSession(applicationId, permissions, behavior, activityCode, SessionAuthorizationType.PUBLISH);
    }

    private void openSession(String applicationId, List<String> permissions, SessionLoginBehavior behavior, int activityCode, SessionAuthorizationType authType) {
        if (this.sessionTracker != null) {
            Session currentSession = this.sessionTracker.getSession();
            if (currentSession == null || currentSession.getState().isClosed()) {
                Session session = new Builder(getActivity()).setApplicationId(applicationId).build();
                Session.setActiveSession(session);
                currentSession = session;
            }
            if (!currentSession.isOpened()) {
                OpenRequest openRequest = new OpenRequest((Fragment) this).setPermissions((List) permissions).setLoginBehavior(behavior).setRequestCode(activityCode);
                if (SessionAuthorizationType.PUBLISH.equals(authType)) {
                    currentSession.openForPublish(openRequest);
                } else {
                    currentSession.openForRead(openRequest);
                }
            }
        }
    }
}
