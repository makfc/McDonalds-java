package com.facebook.internal;

import android.content.Context;
import android.os.Bundle;
import com.facebook.LoggingBehavior;
import com.facebook.Settings;
import com.facebook.android.Facebook;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.Collection;
import org.json.JSONException;
import org.json.JSONObject;

public final class ServerProtocol {
    private static final String DIALOG_AUTHORITY_FORMAT = "m.%s";
    public static final String DIALOG_PARAM_ACCESS_TOKEN = "access_token";
    public static final String DIALOG_PARAM_APP_ID = "app_id";
    public static final String DIALOG_PARAM_AUTH_TYPE = "auth_type";
    public static final String DIALOG_PARAM_CLIENT_ID = "client_id";
    public static final String DIALOG_PARAM_DEFAULT_AUDIENCE = "default_audience";
    public static final String DIALOG_PARAM_DISPLAY = "display";
    public static final String DIALOG_PARAM_E2E = "e2e";
    public static final String DIALOG_PARAM_LEGACY_OVERRIDE = "legacy_override";
    public static final String DIALOG_PARAM_REDIRECT_URI = "redirect_uri";
    public static final String DIALOG_PARAM_RESPONSE_TYPE = "response_type";
    public static final String DIALOG_PARAM_RETURN_SCOPES = "return_scopes";
    public static final String DIALOG_PARAM_SCOPE = "scope";
    public static final String DIALOG_PATH = "dialog/";
    public static final String DIALOG_REREQUEST_AUTH_TYPE = "rerequest";
    public static final String DIALOG_RESPONSE_TYPE_TOKEN = "token";
    public static final String DIALOG_RETURN_SCOPES_TRUE = "true";
    public static final String FALLBACK_DIALOG_DISPLAY_VALUE_TOUCH = "touch";
    public static final String FALLBACK_DIALOG_PARAM_APP_ID = "app_id";
    public static final String FALLBACK_DIALOG_PARAM_BRIDGE_ARGS = "bridge_args";
    public static final String FALLBACK_DIALOG_PARAM_KEY_HASH = "android_key_hash";
    public static final String FALLBACK_DIALOG_PARAM_METHOD_ARGS = "method_args";
    public static final String FALLBACK_DIALOG_PARAM_METHOD_RESULTS = "method_results";
    public static final String FALLBACK_DIALOG_PARAM_VERSION = "version";
    public static final String GRAPH_API_VERSION = "v2.2";
    private static final String GRAPH_URL_FORMAT = "https://graph.%s";
    private static final String GRAPH_VIDEO_URL_FORMAT = "https://graph-video.%s";
    private static final String LEGACY_API_VERSION = "v1.0";
    private static final String TAG = ServerProtocol.class.getName();
    public static final Collection<String> errorsProxyAuthDisabled = Utility.unmodifiableCollection(Facebook.SINGLE_SIGN_ON_DISABLED, "AndroidAuthKillSwitchException");
    public static final Collection<String> errorsUserCanceled = Utility.unmodifiableCollection("access_denied", "OAuthAccessDeniedException");

    public static final String getDialogAuthority() {
        return String.format(DIALOG_AUTHORITY_FORMAT, new Object[]{Settings.getFacebookDomain()});
    }

    public static final String getGraphUrlBase() {
        return String.format(GRAPH_URL_FORMAT, new Object[]{Settings.getFacebookDomain()});
    }

    public static final String getGraphVideoUrlBase() {
        return String.format(GRAPH_VIDEO_URL_FORMAT, new Object[]{Settings.getFacebookDomain()});
    }

    public static final String getAPIVersion() {
        if (Settings.getPlatformCompatibilityEnabled()) {
            return LEGACY_API_VERSION;
        }
        return GRAPH_API_VERSION;
    }

    public static Bundle getQueryParamsForPlatformActivityIntentWebFallback(Context context, String callId, int version, String applicationName, Bundle methodArgs) {
        String keyHash = Settings.getApplicationSignature(context);
        if (Utility.isNullOrEmpty(keyHash)) {
            return null;
        }
        Bundle webParams = new Bundle();
        webParams.putString(FALLBACK_DIALOG_PARAM_KEY_HASH, keyHash);
        webParams.putString("app_id", Settings.getApplicationId());
        webParams.putInt("version", version);
        webParams.putString(DIALOG_PARAM_DISPLAY, FALLBACK_DIALOG_DISPLAY_VALUE_TOUCH);
        Bundle bridgeArguments = new Bundle();
        bridgeArguments.putString("action_id", callId);
        bridgeArguments.putString(NativeProtocol.BRIDGE_ARG_APP_NAME_STRING, applicationName);
        if (methodArgs == null) {
            methodArgs = new Bundle();
        }
        try {
            JSONObject bridgeArgsJSON = BundleJSONConverter.convertToJSON(bridgeArguments);
            JSONObject methodArgsJSON = BundleJSONConverter.convertToJSON(methodArgs);
            if (bridgeArgsJSON == null || methodArgsJSON == null) {
                return null;
            }
            webParams.putString(FALLBACK_DIALOG_PARAM_BRIDGE_ARGS, !(bridgeArgsJSON instanceof JSONObject) ? bridgeArgsJSON.toString() : JSONObjectInstrumentation.toString(bridgeArgsJSON));
            webParams.putString(FALLBACK_DIALOG_PARAM_METHOD_ARGS, !(methodArgsJSON instanceof JSONObject) ? methodArgsJSON.toString() : JSONObjectInstrumentation.toString(methodArgsJSON));
            return webParams;
        } catch (JSONException je) {
            Logger.log(LoggingBehavior.DEVELOPER_ERRORS, 6, TAG, "Error creating Url -- " + je);
            return null;
        }
    }
}
