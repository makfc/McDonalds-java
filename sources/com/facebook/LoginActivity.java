package com.facebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.facebook.android.C1926R;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public class LoginActivity extends Activity implements TraceFieldInterface {
    private static final String EXTRA_REQUEST = "request";
    private static final String NULL_CALLING_PKG_ERROR_MSG = "Cannot call LoginActivity with a null calling package. This can occur if the launchMode of the caller is singleInstance.";
    static final String RESULT_KEY = "com.facebook.LoginActivity:Result";
    private static final String SAVED_AUTH_CLIENT = "authorizationClient";
    private static final String SAVED_CALLING_PKG_KEY = "callingPackage";
    private static final String TAG = LoginActivity.class.getName();
    public Trace _nr_trace;
    private AuthorizationClient authorizationClient;
    private String callingPackage;
    private AuthorizationRequest request;

    /* renamed from: com.facebook.LoginActivity$1 */
    class C18891 implements OnCompletedListener {
        C18891() {
        }

        public void onCompleted(Result outcome) {
            LoginActivity.this.onAuthClientCompleted(outcome);
        }
    }

    /* renamed from: com.facebook.LoginActivity$2 */
    class C18902 implements BackgroundProcessingListener {
        C18902() {
        }

        public void onBackgroundProcessingStarted() {
            LoginActivity.this.findViewById(C1926R.C1925id.com_facebook_login_activity_progress_bar).setVisibility(0);
        }

        public void onBackgroundProcessingStopped() {
            LoginActivity.this.findViewById(C1926R.C1925id.com_facebook_login_activity_progress_bar).setVisibility(8);
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

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("LoginActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "LoginActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "LoginActivity#onCreate", null);
            }
        }
        super.onCreate(bundle);
        setContentView(C1926R.layout.com_facebook_login_activity_layout);
        if (bundle != null) {
            this.callingPackage = bundle.getString(SAVED_CALLING_PKG_KEY);
            this.authorizationClient = (AuthorizationClient) bundle.getSerializable(SAVED_AUTH_CLIENT);
        } else {
            this.callingPackage = getCallingPackage();
            this.authorizationClient = new AuthorizationClient();
            this.request = (AuthorizationRequest) getIntent().getSerializableExtra(EXTRA_REQUEST);
        }
        this.authorizationClient.setContext((Activity) this);
        this.authorizationClient.setOnCompletedListener(new C18891());
        this.authorizationClient.setBackgroundProcessingListener(new C18902());
        TraceMachine.exitMethod();
    }

    private void onAuthClientCompleted(Result outcome) {
        this.request = null;
        int resultCode = outcome.code == Code.CANCEL ? 0 : -1;
        Bundle bundle = new Bundle();
        bundle.putSerializable(RESULT_KEY, outcome);
        Intent resultIntent = new Intent();
        resultIntent.putExtras(bundle);
        setResult(resultCode, resultIntent);
        finish();
    }

    public void onResume() {
        super.onResume();
        if (this.callingPackage == null) {
            Log.e(TAG, NULL_CALLING_PKG_ERROR_MSG);
            finish();
            return;
        }
        this.authorizationClient.startOrContinueAuth(this.request);
    }

    public void onPause() {
        super.onPause();
        this.authorizationClient.cancelCurrentHandler();
        findViewById(C1926R.C1925id.com_facebook_login_activity_progress_bar).setVisibility(8);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVED_CALLING_PKG_KEY, this.callingPackage);
        outState.putSerializable(SAVED_AUTH_CLIENT, this.authorizationClient);
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.authorizationClient.onActivityResult(requestCode, resultCode, data);
    }

    static Bundle populateIntentExtras(AuthorizationRequest request) {
        Bundle extras = new Bundle();
        extras.putSerializable(EXTRA_REQUEST, request);
        return extras;
    }
}
