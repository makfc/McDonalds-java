package com.facebook;

import android.net.Uri;
import android.os.Bundle;
import bolts.AppLink;
import bolts.AppLink.Target;
import bolts.AppLinkResolver;
import bolts.Continuation;
import bolts.Task;
import bolts.Task.TaskCompletionSource;
import com.facebook.Request.Callback;
import com.facebook.model.GraphObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FacebookAppLinkResolver implements AppLinkResolver {
    private static final String APP_LINK_ANDROID_TARGET_KEY = "android";
    private static final String APP_LINK_KEY = "app_links";
    private static final String APP_LINK_TARGET_APP_NAME_KEY = "app_name";
    private static final String APP_LINK_TARGET_CLASS_KEY = "class";
    private static final String APP_LINK_TARGET_PACKAGE_KEY = "package";
    private static final String APP_LINK_TARGET_SHOULD_FALLBACK_KEY = "should_fallback";
    private static final String APP_LINK_TARGET_URL_KEY = "url";
    private static final String APP_LINK_WEB_TARGET_KEY = "web";
    private final HashMap<Uri, AppLink> cachedAppLinks = new HashMap();

    public Task<AppLink> getAppLinkFromUrlInBackground(final Uri uri) {
        ArrayList<Uri> uris = new ArrayList();
        uris.add(uri);
        return getAppLinkFromUrlsInBackground(uris).onSuccess(new Continuation<Map<Uri, AppLink>, AppLink>() {
            public AppLink then(Task<Map<Uri, AppLink>> resolveUrisTask) throws Exception {
                return (AppLink) ((Map) resolveUrisTask.getResult()).get(uri);
            }
        });
    }

    public Task<Map<Uri, AppLink>> getAppLinkFromUrlsInBackground(List<Uri> uris) {
        final Map<Uri, AppLink> appLinkResults = new HashMap();
        final HashSet<Uri> urisToRequest = new HashSet();
        StringBuilder graphRequestFields = new StringBuilder();
        for (Uri uri : uris) {
            AppLink appLink;
            synchronized (this.cachedAppLinks) {
                appLink = (AppLink) this.cachedAppLinks.get(uri);
            }
            if (appLink != null) {
                appLinkResults.put(uri, appLink);
            } else {
                if (!urisToRequest.isEmpty()) {
                    graphRequestFields.append(',');
                }
                graphRequestFields.append(uri.toString());
                urisToRequest.add(uri);
            }
        }
        if (urisToRequest.isEmpty()) {
            return Task.forResult(appLinkResults);
        }
        final TaskCompletionSource taskCompletionSource = Task.create();
        Bundle appLinkRequestParameters = new Bundle();
        appLinkRequestParameters.putString("ids", graphRequestFields.toString());
        appLinkRequestParameters.putString("fields", String.format("%s.fields(%s,%s)", new Object[]{APP_LINK_KEY, APP_LINK_ANDROID_TARGET_KEY, APP_LINK_WEB_TARGET_KEY}));
        new Request(null, "", appLinkRequestParameters, null, new Callback() {
            public void onCompleted(Response response) {
                FacebookRequestError error = response.getError();
                if (error != null) {
                    taskCompletionSource.setError(error.getException());
                    return;
                }
                GraphObject responseObject = response.getGraphObject();
                JSONObject responseJson = responseObject != null ? responseObject.getInnerJSONObject() : null;
                if (responseJson == null) {
                    taskCompletionSource.setResult(appLinkResults);
                    return;
                }
                Iterator i$ = urisToRequest.iterator();
                while (i$.hasNext()) {
                    Uri uri = (Uri) i$.next();
                    if (responseJson.has(uri.toString())) {
                        try {
                            JSONObject appLinkData = responseJson.getJSONObject(uri.toString()).getJSONObject(FacebookAppLinkResolver.APP_LINK_KEY);
                            JSONArray rawTargets = appLinkData.getJSONArray(FacebookAppLinkResolver.APP_LINK_ANDROID_TARGET_KEY);
                            int targetsCount = rawTargets.length();
                            List<Target> targets = new ArrayList(targetsCount);
                            for (int i = 0; i < targetsCount; i++) {
                                Target target = FacebookAppLinkResolver.getAndroidTargetFromJson(rawTargets.getJSONObject(i));
                                if (target != null) {
                                    targets.add(target);
                                }
                            }
                            AppLink appLink = new AppLink(uri, targets, FacebookAppLinkResolver.getWebFallbackUriFromJson(uri, appLinkData));
                            appLinkResults.put(uri, appLink);
                            synchronized (FacebookAppLinkResolver.this.cachedAppLinks) {
                                FacebookAppLinkResolver.this.cachedAppLinks.put(uri, appLink);
                            }
                        } catch (JSONException e) {
                        }
                    }
                }
                taskCompletionSource.setResult(appLinkResults);
            }
        }).executeAsync();
        return taskCompletionSource.getTask();
    }

    private static Target getAndroidTargetFromJson(JSONObject targetJson) {
        String packageName = tryGetStringFromJson(targetJson, APP_LINK_TARGET_PACKAGE_KEY, null);
        if (packageName == null) {
            return null;
        }
        String className = tryGetStringFromJson(targetJson, APP_LINK_TARGET_CLASS_KEY, null);
        String appName = tryGetStringFromJson(targetJson, "app_name", null);
        String targetUrlString = tryGetStringFromJson(targetJson, "url", null);
        Uri targetUri = null;
        if (targetUrlString != null) {
            targetUri = Uri.parse(targetUrlString);
        }
        return new Target(packageName, className, targetUri, appName);
    }

    private static Uri getWebFallbackUriFromJson(Uri sourceUrl, JSONObject urlData) {
        try {
            JSONObject webTarget = urlData.getJSONObject(APP_LINK_WEB_TARGET_KEY);
            if (!tryGetBooleanFromJson(webTarget, APP_LINK_TARGET_SHOULD_FALLBACK_KEY, true)) {
                return null;
            }
            String webTargetUrlString = tryGetStringFromJson(webTarget, "url", null);
            Uri webUri = null;
            if (webTargetUrlString != null) {
                webUri = Uri.parse(webTargetUrlString);
            }
            if (webUri == null) {
                return sourceUrl;
            }
            return webUri;
        } catch (JSONException e) {
            return sourceUrl;
        }
    }

    private static String tryGetStringFromJson(JSONObject json, String propertyName, String defaultValue) {
        try {
            return json.getString(propertyName);
        } catch (JSONException e) {
            return defaultValue;
        }
    }

    private static boolean tryGetBooleanFromJson(JSONObject json, String propertyName, boolean defaultValue) {
        try {
            return json.getBoolean(propertyName);
        } catch (JSONException e) {
            return defaultValue;
        }
    }
}
