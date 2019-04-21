package com.facebook.internal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;
import com.facebook.FacebookException;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.FacebookDialog.Callback;
import com.facebook.widget.FacebookDialog.PendingCall;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONException;

public class FacebookWebFallbackDialog extends WebDialog {
    private static final int OS_BACK_BUTTON_RESPONSE_TIMEOUT_MILLISECONDS = 1500;
    private static final String TAG = FacebookWebFallbackDialog.class.getName();
    private boolean waitingForDialogToClose;

    /* renamed from: com.facebook.internal.FacebookWebFallbackDialog$2 */
    class C19352 implements Runnable {
        C19352() {
        }

        public void run() {
            if (!FacebookWebFallbackDialog.this.isListenerCalled()) {
                FacebookWebFallbackDialog.this.sendCancelToListener();
            }
        }
    }

    public static boolean presentWebFallback(final Context context, String dialogUrl, String applicationId, final PendingCall appCall, final Callback callback) {
        if (Utility.isNullOrEmpty(dialogUrl)) {
            return false;
        }
        FacebookWebFallbackDialog fallbackWebDialog = new FacebookWebFallbackDialog(context, dialogUrl, String.format("fb%s://bridge/", new Object[]{applicationId}));
        fallbackWebDialog.setOnCompleteListener(new OnCompleteListener() {
            public void onComplete(Bundle values, FacebookException error) {
                Intent dummyIntent = new Intent();
                if (values == null) {
                    values = new Bundle();
                }
                dummyIntent.putExtras(values);
                FacebookDialog.handleActivityResult(context, appCall, appCall.getRequestCode(), dummyIntent, callback);
            }
        });
        fallbackWebDialog.show();
        return true;
    }

    private FacebookWebFallbackDialog(Context context, String url, String expectedRedirectUrl) {
        super(context, url);
        setExpectedRedirectUrl(expectedRedirectUrl);
    }

    /* Access modifiers changed, original: protected */
    public Bundle parseResponseUri(String url) {
        Bundle queryParams = Utility.parseUrlQueryString(Uri.parse(url).getQuery());
        String bridgeArgsJSONString = queryParams.getString(ServerProtocol.FALLBACK_DIALOG_PARAM_BRIDGE_ARGS);
        queryParams.remove(ServerProtocol.FALLBACK_DIALOG_PARAM_BRIDGE_ARGS);
        if (!Utility.isNullOrEmpty(bridgeArgsJSONString)) {
            try {
                queryParams.putBundle(NativeProtocol.EXTRA_PROTOCOL_BRIDGE_ARGS, BundleJSONConverter.convertToBundle(JSONObjectInstrumentation.init(bridgeArgsJSONString)));
            } catch (JSONException je) {
                Utility.logd(TAG, "Unable to parse bridge_args JSON", je);
            }
        }
        String methodResultsJSONString = queryParams.getString(ServerProtocol.FALLBACK_DIALOG_PARAM_METHOD_RESULTS);
        queryParams.remove(ServerProtocol.FALLBACK_DIALOG_PARAM_METHOD_RESULTS);
        if (!Utility.isNullOrEmpty(methodResultsJSONString)) {
            if (Utility.isNullOrEmpty(methodResultsJSONString)) {
                methodResultsJSONString = "{}";
            }
            try {
                queryParams.putBundle(NativeProtocol.EXTRA_PROTOCOL_METHOD_RESULTS, BundleJSONConverter.convertToBundle(JSONObjectInstrumentation.init(methodResultsJSONString)));
            } catch (JSONException je2) {
                Utility.logd(TAG, "Unable to parse bridge_args JSON", je2);
            }
        }
        queryParams.remove("version");
        queryParams.putInt(NativeProtocol.EXTRA_PROTOCOL_VERSION, NativeProtocol.getLatestKnownVersion());
        return queryParams;
    }

    public void dismiss() {
        WebView webView = getWebView();
        if (isListenerCalled() || webView == null || !webView.isShown()) {
            super.dismiss();
        } else if (!this.waitingForDialogToClose) {
            this.waitingForDialogToClose = true;
            webView.loadUrl("javascript:" + "(function() {  var event = document.createEvent('Event');  event.initEvent('fbPlatformDialogMustClose',true,true);  document.dispatchEvent(event);})();");
            new Handler(Looper.getMainLooper()).postDelayed(new C19352(), 1500);
        }
    }
}
