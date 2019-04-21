package com.facebook.widget;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.facebook.AppEventsLogger;
import com.facebook.FacebookException;
import com.facebook.Request;
import com.facebook.Request.GraphUserCallback;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.Session.Builder;
import com.facebook.Session.OpenRequest;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionDefaultAudience;
import com.facebook.SessionLoginBehavior;
import com.facebook.SessionState;
import com.facebook.android.C1926R;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.SessionAuthorizationType;
import com.facebook.internal.SessionTracker;
import com.facebook.internal.Utility;
import com.facebook.internal.Utility.FetchedAppSettings;
import com.facebook.model.GraphUser;
import com.facebook.widget.ToolTipPopup.Style;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class LoginButton extends Button {
    private static final String TAG = LoginButton.class.getName();
    private String applicationId = null;
    private boolean confirmLogout;
    private boolean fetchUserInfo;
    private OnClickListener listenerCallback;
    private String loginLogoutEventName = AnalyticsEvents.EVENT_LOGIN_VIEW_USAGE;
    private String loginText;
    private String logoutText;
    private boolean nuxChecked;
    private long nuxDisplayTime = ToolTipPopup.DEFAULT_POPUP_DISPLAY_TIME;
    private ToolTipMode nuxMode = ToolTipMode.DEFAULT;
    private ToolTipPopup nuxPopup;
    private Style nuxStyle = Style.BLUE;
    private Fragment parentFragment;
    private LoginButtonProperties properties = new LoginButtonProperties();
    private SessionTracker sessionTracker;
    private GraphUser user = null;
    private UserInfoChangedCallback userInfoChangedCallback;
    private Session userInfoSession = null;

    private class LoginButtonCallback implements StatusCallback {
        private LoginButtonCallback() {
        }

        /* synthetic */ LoginButtonCallback(LoginButton x0, C20481 x1) {
            this();
        }

        public void call(Session session, SessionState state, Exception exception) {
            LoginButton.this.fetchUserInfo();
            LoginButton.this.setButtonText();
            if (LoginButton.this.properties.sessionStatusCallback != null) {
                LoginButton.this.properties.sessionStatusCallback.call(session, state, exception);
            } else if (exception != null) {
                LoginButton.this.handleError(exception);
            }
        }
    }

    static class LoginButtonProperties {
        private SessionAuthorizationType authorizationType = null;
        private SessionDefaultAudience defaultAudience = SessionDefaultAudience.FRIENDS;
        private SessionLoginBehavior loginBehavior = SessionLoginBehavior.SSO_WITH_FALLBACK;
        private OnErrorListener onErrorListener;
        private List<String> permissions = Collections.emptyList();
        private StatusCallback sessionStatusCallback;

        LoginButtonProperties() {
        }

        public void setOnErrorListener(OnErrorListener onErrorListener) {
            this.onErrorListener = onErrorListener;
        }

        public OnErrorListener getOnErrorListener() {
            return this.onErrorListener;
        }

        public void setDefaultAudience(SessionDefaultAudience defaultAudience) {
            this.defaultAudience = defaultAudience;
        }

        public SessionDefaultAudience getDefaultAudience() {
            return this.defaultAudience;
        }

        public void setReadPermissions(List<String> permissions, Session session) {
            if (SessionAuthorizationType.PUBLISH.equals(this.authorizationType)) {
                throw new UnsupportedOperationException("Cannot call setReadPermissions after setPublishPermissions has been called.");
            } else if (validatePermissions(permissions, SessionAuthorizationType.READ, session)) {
                this.permissions = permissions;
                this.authorizationType = SessionAuthorizationType.READ;
            }
        }

        public void setPublishPermissions(List<String> permissions, Session session) {
            if (SessionAuthorizationType.READ.equals(this.authorizationType)) {
                throw new UnsupportedOperationException("Cannot call setPublishPermissions after setReadPermissions has been called.");
            } else if (validatePermissions(permissions, SessionAuthorizationType.PUBLISH, session)) {
                this.permissions = permissions;
                this.authorizationType = SessionAuthorizationType.PUBLISH;
            }
        }

        private boolean validatePermissions(List<String> permissions, SessionAuthorizationType authType, Session currentSession) {
            if (SessionAuthorizationType.PUBLISH.equals(authType) && Utility.isNullOrEmpty((Collection) permissions)) {
                throw new IllegalArgumentException("Permissions for publish actions cannot be null or empty.");
            } else if (currentSession == null || !currentSession.isOpened() || Utility.isSubset(permissions, currentSession.getPermissions())) {
                return true;
            } else {
                Log.e(LoginButton.TAG, "Cannot set additional permissions when session is already open.");
                return false;
            }
        }

        /* Access modifiers changed, original: 0000 */
        public List<String> getPermissions() {
            return this.permissions;
        }

        public void clearPermissions() {
            this.permissions = null;
            this.authorizationType = null;
        }

        public void setLoginBehavior(SessionLoginBehavior loginBehavior) {
            this.loginBehavior = loginBehavior;
        }

        public SessionLoginBehavior getLoginBehavior() {
            return this.loginBehavior;
        }

        public void setSessionStatusCallback(StatusCallback callback) {
            this.sessionStatusCallback = callback;
        }

        public StatusCallback getSessionStatusCallback() {
            return this.sessionStatusCallback;
        }
    }

    private class LoginClickListener implements OnClickListener {
        private LoginClickListener() {
        }

        /* synthetic */ LoginClickListener(LoginButton x0, C20481 x1) {
            this();
        }

        public void onClick(View v) {
            Context context = LoginButton.this.getContext();
            final Session openSession = LoginButton.this.sessionTracker.getOpenSession();
            if (openSession == null) {
                Session currentSession = LoginButton.this.sessionTracker.getSession();
                if (currentSession == null || currentSession.getState().isClosed()) {
                    LoginButton.this.sessionTracker.setSession(null);
                    Session session = new Builder(context).setApplicationId(LoginButton.this.applicationId).build();
                    Session.setActiveSession(session);
                    currentSession = session;
                }
                if (!currentSession.isOpened()) {
                    OpenRequest openRequest = null;
                    if (LoginButton.this.parentFragment != null) {
                        openRequest = new OpenRequest(LoginButton.this.parentFragment);
                    } else if (context instanceof Activity) {
                        openRequest = new OpenRequest((Activity) context);
                    } else if (context instanceof ContextWrapper) {
                        Context baseContext = ((ContextWrapper) context).getBaseContext();
                        if (baseContext instanceof Activity) {
                            openRequest = new OpenRequest((Activity) baseContext);
                        }
                    }
                    if (openRequest != null) {
                        openRequest.setDefaultAudience(LoginButton.this.properties.defaultAudience);
                        openRequest.setPermissions(LoginButton.this.properties.permissions);
                        openRequest.setLoginBehavior(LoginButton.this.properties.loginBehavior);
                        if (SessionAuthorizationType.PUBLISH.equals(LoginButton.this.properties.authorizationType)) {
                            currentSession.openForPublish(openRequest);
                        } else {
                            currentSession.openForRead(openRequest);
                        }
                    }
                }
            } else if (LoginButton.this.confirmLogout) {
                String message;
                String logout = LoginButton.this.getResources().getString(C1926R.string.com_facebook_loginview_log_out_action);
                String cancel = LoginButton.this.getResources().getString(C1926R.string.com_facebook_loginview_cancel_action);
                if (LoginButton.this.user == null || LoginButton.this.user.getName() == null) {
                    message = LoginButton.this.getResources().getString(C1926R.string.com_facebook_loginview_logged_in_using_facebook);
                } else {
                    message = String.format(LoginButton.this.getResources().getString(C1926R.string.com_facebook_loginview_logged_in_as), new Object[]{LoginButton.this.user.getName()});
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(message).setCancelable(true).setPositiveButton(logout, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        openSession.closeAndClearTokenInformation();
                    }
                }).setNegativeButton(cancel, null);
                builder.create().show();
            } else {
                openSession.closeAndClearTokenInformation();
            }
            AppEventsLogger logger = AppEventsLogger.newLogger(LoginButton.this.getContext());
            Bundle parameters = new Bundle();
            parameters.putInt("logging_in", openSession != null ? 0 : 1);
            logger.logSdkEvent(LoginButton.this.loginLogoutEventName, null, parameters);
            if (LoginButton.this.listenerCallback != null) {
                LoginButton.this.listenerCallback.onClick(v);
            }
        }
    }

    public interface OnErrorListener {
        void onError(FacebookException facebookException);
    }

    public enum ToolTipMode {
        DEFAULT,
        DISPLAY_ALWAYS,
        NEVER_DISPLAY
    }

    public interface UserInfoChangedCallback {
        void onUserInfoFetched(GraphUser graphUser);
    }

    public LoginButton(Context context) {
        super(context);
        initializeActiveSessionWithCachedToken(context);
        finishInit();
    }

    public LoginButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (attrs.getStyleAttribute() == 0) {
            setGravity(17);
            setTextColor(getResources().getColor(C1926R.color.com_facebook_loginview_text_color));
            setTextSize(0, getResources().getDimension(C1926R.dimen.com_facebook_loginview_text_size));
            setTypeface(Typeface.DEFAULT_BOLD);
            if (isInEditMode()) {
                setBackgroundColor(getResources().getColor(C1926R.color.com_facebook_blue));
                this.loginText = "Log in with Facebook";
            } else {
                setBackgroundResource(C1926R.C1924drawable.com_facebook_button_blue);
                setCompoundDrawablesWithIntrinsicBounds(C1926R.C1924drawable.com_facebook_inverse_icon, 0, 0, 0);
                setCompoundDrawablePadding(getResources().getDimensionPixelSize(C1926R.dimen.com_facebook_loginview_compound_drawable_padding));
                setPadding(getResources().getDimensionPixelSize(C1926R.dimen.com_facebook_loginview_padding_left), getResources().getDimensionPixelSize(C1926R.dimen.com_facebook_loginview_padding_top), getResources().getDimensionPixelSize(C1926R.dimen.com_facebook_loginview_padding_right), getResources().getDimensionPixelSize(C1926R.dimen.com_facebook_loginview_padding_bottom));
            }
        }
        parseAttributes(attrs);
        if (!isInEditMode()) {
            initializeActiveSessionWithCachedToken(context);
        }
    }

    public LoginButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        parseAttributes(attrs);
        initializeActiveSessionWithCachedToken(context);
    }

    public void setOnErrorListener(OnErrorListener onErrorListener) {
        this.properties.setOnErrorListener(onErrorListener);
    }

    public OnErrorListener getOnErrorListener() {
        return this.properties.getOnErrorListener();
    }

    public void setDefaultAudience(SessionDefaultAudience defaultAudience) {
        this.properties.setDefaultAudience(defaultAudience);
    }

    public SessionDefaultAudience getDefaultAudience() {
        return this.properties.getDefaultAudience();
    }

    public void setReadPermissions(List<String> permissions) {
        this.properties.setReadPermissions(permissions, this.sessionTracker.getSession());
    }

    public void setReadPermissions(String... permissions) {
        this.properties.setReadPermissions(Arrays.asList(permissions), this.sessionTracker.getSession());
    }

    public void setPublishPermissions(List<String> permissions) {
        this.properties.setPublishPermissions(permissions, this.sessionTracker.getSession());
    }

    public void setPublishPermissions(String... permissions) {
        this.properties.setPublishPermissions(Arrays.asList(permissions), this.sessionTracker.getSession());
    }

    public void clearPermissions() {
        this.properties.clearPermissions();
    }

    public void setLoginBehavior(SessionLoginBehavior loginBehavior) {
        this.properties.setLoginBehavior(loginBehavior);
    }

    public SessionLoginBehavior getLoginBehavior() {
        return this.properties.getLoginBehavior();
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public UserInfoChangedCallback getUserInfoChangedCallback() {
        return this.userInfoChangedCallback;
    }

    public void setUserInfoChangedCallback(UserInfoChangedCallback userInfoChangedCallback) {
        this.userInfoChangedCallback = userInfoChangedCallback;
    }

    public void setSessionStatusCallback(StatusCallback callback) {
        this.properties.setSessionStatusCallback(callback);
    }

    public StatusCallback getSessionStatusCallback() {
        return this.properties.getSessionStatusCallback();
    }

    public void setToolTipStyle(Style nuxStyle) {
        this.nuxStyle = nuxStyle;
    }

    public void setToolTipMode(ToolTipMode nuxMode) {
        this.nuxMode = nuxMode;
    }

    public ToolTipMode getToolTipMode() {
        return this.nuxMode;
    }

    public void setToolTipDisplayTime(long displayTime) {
        this.nuxDisplayTime = displayTime;
    }

    public long getToolTipDisplayTime() {
        return this.nuxDisplayTime;
    }

    public void dismissToolTip() {
        if (this.nuxPopup != null) {
            this.nuxPopup.dismiss();
            this.nuxPopup = null;
        }
    }

    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        Session session = this.sessionTracker.getSession();
        if (session != null) {
            return session.onActivityResult((Activity) getContext(), requestCode, resultCode, data);
        }
        return false;
    }

    public void setSession(Session newSession) {
        this.sessionTracker.setSession(newSession);
        fetchUserInfo();
        setButtonText();
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        finishInit();
    }

    private void finishInit() {
        super.setOnClickListener(new LoginClickListener(this, null));
        setButtonText();
        if (!isInEditMode()) {
            this.sessionTracker = new SessionTracker(getContext(), new LoginButtonCallback(this, null), null, false);
            fetchUserInfo();
        }
    }

    public void setFragment(Fragment fragment) {
        this.parentFragment = fragment;
    }

    /* Access modifiers changed, original: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.sessionTracker != null && !this.sessionTracker.isTracking()) {
            this.sessionTracker.startTracking();
            fetchUserInfo();
            setButtonText();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.nuxChecked && this.nuxMode != ToolTipMode.NEVER_DISPLAY && !isInEditMode()) {
            this.nuxChecked = true;
            checkNuxSettings();
        }
    }

    private void showNuxPerSettings(FetchedAppSettings settings) {
        if (settings != null && settings.getNuxEnabled() && getVisibility() == 0) {
            displayNux(settings.getNuxContent());
        }
    }

    private void displayNux(String nuxString) {
        this.nuxPopup = new ToolTipPopup(nuxString, this);
        this.nuxPopup.setStyle(this.nuxStyle);
        this.nuxPopup.setNuxDisplayTime(this.nuxDisplayTime);
        this.nuxPopup.show();
    }

    private void checkNuxSettings() {
        if (this.nuxMode == ToolTipMode.DISPLAY_ALWAYS) {
            displayNux(getResources().getString(C1926R.string.com_facebook_tooltip_default));
            return;
        }
        final String appId = Utility.getMetadataApplicationId(getContext());
        AsyncTask<Void, Void, FetchedAppSettings> task = new TraceFieldInterface() {
            public Trace _nr_trace;

            public void _nr_setTrace(Trace trace) {
                try {
                    this._nr_trace = trace;
                } catch (Exception e) {
                }
            }

            /* Access modifiers changed, original: protected|varargs */
            public FetchedAppSettings doInBackground(Void... params) {
                return Utility.queryAppSettings(appId, false);
            }

            /* Access modifiers changed, original: protected */
            public void onPostExecute(FetchedAppSettings result) {
                LoginButton.this.showNuxPerSettings(result);
            }
        };
        Void[] voidArr = (Void[]) null;
        if (task instanceof AsyncTask) {
            AsyncTaskInstrumentation.execute(task, voidArr);
        } else {
            task.execute(voidArr);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.sessionTracker != null) {
            this.sessionTracker.stopTracking();
        }
        dismissToolTip();
    }

    /* Access modifiers changed, original: protected */
    public void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility != 0) {
            dismissToolTip();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public List<String> getPermissions() {
        return this.properties.getPermissions();
    }

    /* Access modifiers changed, original: 0000 */
    public void setProperties(LoginButtonProperties properties) {
        this.properties = properties;
    }

    /* Access modifiers changed, original: 0000 */
    public void setLoginLogoutEventName(String eventName) {
        this.loginLogoutEventName = eventName;
    }

    private void parseAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, C1926R.styleable.com_facebook_login_view);
        this.confirmLogout = a.getBoolean(C1926R.styleable.com_facebook_login_view_confirm_logout, true);
        this.fetchUserInfo = a.getBoolean(C1926R.styleable.com_facebook_login_view_fetch_user_info, true);
        this.loginText = a.getString(C1926R.styleable.com_facebook_login_view_login_text);
        this.logoutText = a.getString(C1926R.styleable.com_facebook_login_view_logout_text);
        a.recycle();
    }

    private void setButtonText() {
        if (this.sessionTracker == null || this.sessionTracker.getOpenSession() == null) {
            setText(this.loginText != null ? this.loginText : getResources().getString(C1926R.string.com_facebook_loginview_log_in_button));
        } else {
            setText(this.logoutText != null ? this.logoutText : getResources().getString(C1926R.string.com_facebook_loginview_log_out_button));
        }
    }

    private boolean initializeActiveSessionWithCachedToken(Context context) {
        if (context == null) {
            return false;
        }
        Session session = Session.getActiveSession();
        if (session != null) {
            return session.isOpened();
        }
        if (Utility.getMetadataApplicationId(context) == null || Session.openActiveSessionFromCache(context) == null) {
            return false;
        }
        return true;
    }

    private void fetchUserInfo() {
        if (this.fetchUserInfo) {
            final Session currentSession = this.sessionTracker.getOpenSession();
            if (currentSession == null) {
                this.user = null;
                if (this.userInfoChangedCallback != null) {
                    this.userInfoChangedCallback.onUserInfoFetched(this.user);
                }
            } else if (currentSession != this.userInfoSession) {
                Request request = Request.newMeRequest(currentSession, new GraphUserCallback() {
                    public void onCompleted(GraphUser me, Response response) {
                        if (currentSession == LoginButton.this.sessionTracker.getOpenSession()) {
                            LoginButton.this.user = me;
                            if (LoginButton.this.userInfoChangedCallback != null) {
                                LoginButton.this.userInfoChangedCallback.onUserInfoFetched(LoginButton.this.user);
                            }
                        }
                        if (response.getError() != null) {
                            LoginButton.this.handleError(response.getError().getException());
                        }
                    }
                });
                Request.executeBatchAsync(request);
                this.userInfoSession = currentSession;
            }
        }
    }

    public void setOnClickListener(OnClickListener clickListener) {
        this.listenerCallback = clickListener;
    }

    /* Access modifiers changed, original: 0000 */
    public void handleError(Exception exception) {
        if (this.properties.onErrorListener == null) {
            return;
        }
        if (exception instanceof FacebookException) {
            this.properties.onErrorListener.onError((FacebookException) exception);
        } else {
            this.properties.onErrorListener.onError(new FacebookException((Throwable) exception));
        }
    }
}
