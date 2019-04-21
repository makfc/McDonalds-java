package com.mcdonalds.app.social;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.ensighten.Ensighten;
import com.ensighten.model.EnsightenGestureRecognizerFactory;
import com.ensighten.model.activity.EnsightenActivityHandler;
import com.facebook.stetho.common.Utf8Charset;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.modules.models.SocialLoginAuthenticationResults;
import com.mcdonalds.sdk.services.log.MCDLog;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.net.URLDecoder;

@Instrumented
@SuppressLint({"SetJavaScriptEnabled"})
public class OAuthAccessTokenActivity extends Activity implements TraceFieldInterface {
    public Trace _nr_trace;
    boolean handled = false;
    private boolean hasLoggedIn;
    private AsyncListener<SocialLoginAuthenticationResults> mListener;
    private OAuth2Helper oAuth2Helper;
    private WebView webview;

    /* renamed from: com.mcdonalds.app.social.OAuthAccessTokenActivity$1 */
    class C36991 extends WebViewClient {
        C36991() {
        }

        public void onPageStarted(WebView view, String url, Bitmap bitmap) {
            Ensighten.evaluateEvent(this, "onPageStarted", new Object[]{view, url, bitmap});
            MCDLog.temp("onPageStarted : " + url + " handled = " + OAuthAccessTokenActivity.this.handled);
        }

        public void onPageFinished(WebView view, String url) {
            Ensighten.evaluateEvent(this, "onPageFinished", new Object[]{view, url});
            MCDLog.temp("onPageFinished : " + url + " handled = " + OAuthAccessTokenActivity.this.handled);
            if (url.startsWith(Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.OAuthAccessTokenActivity", "access$000", new Object[]{OAuthAccessTokenActivity.this}).getOauth2Params().getRedirectUri())) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.OAuthAccessTokenActivity", "access$100", new Object[]{OAuthAccessTokenActivity.this}).setVisibility(4);
                if (!OAuthAccessTokenActivity.this.handled) {
                    ProcessToken processToken = new ProcessToken(url, Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.OAuthAccessTokenActivity", "access$000", new Object[]{OAuthAccessTokenActivity.this}));
                    Uri[] uriArr = new Uri[0];
                    if (processToken instanceof AsyncTask) {
                        AsyncTaskInstrumentation.execute(processToken, uriArr);
                        return;
                    } else {
                        processToken.execute(uriArr);
                        return;
                    }
                }
                return;
            }
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.OAuthAccessTokenActivity", "access$100", new Object[]{OAuthAccessTokenActivity.this}).setVisibility(0);
        }
    }

    private class ProcessToken extends AsyncTask<Uri, Void, Void> implements TraceFieldInterface {
        public Trace _nr_trace;
        final OAuth2Helper mAuthHelper;
        boolean startActivity = false;
        String url;

        public void _nr_setTrace(Trace trace) {
            try {
                this._nr_trace = trace;
            } catch (Exception e) {
            }
        }

        public ProcessToken(String url, OAuth2Helper oAuth2Helper) {
            this.url = url;
            this.mAuthHelper = oAuth2Helper;
        }

        /* Access modifiers changed, original: protected|varargs */
        public Void doInBackground(Uri... params) {
            Ensighten.evaluateEvent(this, "doInBackground", new Object[]{params});
            if (this.url.startsWith(this.mAuthHelper.getOauth2Params().getRedirectUri())) {
                MCDLog.temp("Redirect URL found" + this.url);
                OAuthAccessTokenActivity.this.handled = true;
                try {
                    if (this.url.indexOf("code=") != -1) {
                        String authorizationCode = extractCodeFromUrl(this.url);
                        MCDLog.temp("Found code = " + authorizationCode);
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.OAuthAccessTokenActivity", "access$000", new Object[]{OAuthAccessTokenActivity.this}).retrieveAndStoreAccessToken(authorizationCode);
                        this.startActivity = true;
                        OAuthAccessTokenActivity.access$202(OAuthAccessTokenActivity.this, true);
                    } else if (this.url.indexOf("error=") != -1) {
                        this.startActivity = true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                MCDLog.temp("Not doing anything for url " + this.url);
            }
            return null;
        }

        private String extractCodeFromUrl(String url) throws Exception {
            Ensighten.evaluateEvent(this, "extractCodeFromUrl", new Object[]{url});
            return URLDecoder.decode(url.substring(this.mAuthHelper.getOauth2Params().getRedirectUri().length() + 6, url.length()), Utf8Charset.NAME);
        }

        /* Access modifiers changed, original: protected */
        public void onPreExecute() {
            Ensighten.evaluateEvent(this, "onPreExecute", null);
        }

        /* Access modifiers changed, original: protected */
        public void onPostExecute(Void result) {
            Ensighten.evaluateEvent(this, "onPostExecute", new Object[]{result});
            if (this.startActivity) {
                AsyncException asyncException;
                MCDLog.temp(" ++++++++++++ Notifying Listener");
                SocialLoginAuthenticationResults results = new SocialLoginAuthenticationResults();
                Exception e = null;
                AsyncListener access$300 = Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.OAuthAccessTokenActivity", "access$300", new Object[]{OAuthAccessTokenActivity.this});
                if (e != null) {
                    asyncException = new AsyncException(e.getLocalizedMessage());
                } else {
                    asyncException = null;
                }
                access$300.onResponse(results, null, asyncException);
                OAuthAccessTokenActivity.this.finish();
            }
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        EnsightenGestureRecognizerFactory.getFourFingerGestureRecognizer().process(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onActivityResult", new Object[]{new Integer(i), new Integer(i2), intent});
        super.onActivityResult(i, i2, intent);
    }

    /* Access modifiers changed, original: protected */
    public Dialog onCreateDialog(int i, Bundle bundle) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreateDialog", new Object[]{new Integer(i), bundle});
        return super.onCreateDialog(i);
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDestroy", null);
        super.onDestroy();
    }

    /* Access modifiers changed, original: protected */
    public void onPause() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onPause", null);
        super.onPause();
        Ensighten.processView((Object) this, "onPause");
    }

    /* Access modifiers changed, original: protected */
    public void onRestart() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onRestart", null);
        super.onRestart();
    }

    /* Access modifiers changed, original: protected */
    public void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        EnsightenActivityHandler.onLifecycleMethod(this, "onStart", null);
        super.onStart();
        Ensighten.processView((Object) this, "onStart");
    }

    /* Access modifiers changed, original: protected */
    public void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        EnsightenActivityHandler.onLifecycleMethod(this, "onStop", null);
        super.onStop();
    }

    static /* synthetic */ boolean access$202(OAuthAccessTokenActivity x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.OAuthAccessTokenActivity", "access$202", new Object[]{x0, new Boolean(x1)});
        x0.hasLoggedIn = x1;
        return x1;
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("OAuthAccessTokenActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "OAuthAccessTokenActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "OAuthAccessTokenActivity#onCreate", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreate", new Object[]{bundle});
        super.onCreate(bundle);
        int socialNetworkType = getIntent().getExtras().getInt("SOCIAL_NETWORK_EXTRA");
        int listenerID = getIntent().getExtras().getInt("LISTENER_EXTRA");
        MCDLog.temp("Starting task to retrieve request token.");
        this.oAuth2Helper = new OAuth2Helper(this, socialNetworkType);
        this.webview = new WebView(this);
        this.webview.getSettings().setJavaScriptEnabled(true);
        this.webview.setVisibility(0);
        setContentView(this.webview);
        String authorizationUrl = this.oAuth2Helper.getAuthorizationUrl();
        MCDLog.temp("Using authorizationUrl = " + authorizationUrl);
        this.handled = false;
        this.webview.setWebViewClient(new C36991());
        this.webview.loadUrl(authorizationUrl);
        Ensighten.processView((Object) this, "onCreate");
        TraceMachine.exitMethod();
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onResume", null);
        super.onResume();
        MCDLog.temp("onResume called with " + this.hasLoggedIn);
        if (this.hasLoggedIn) {
            finish();
        }
        Ensighten.processView((Object) this, "onResume");
    }
}
