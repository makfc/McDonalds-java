package com.crashlytics.android.answers;

import java.io.File;
import java.util.List;
import p041io.fabric.sdk.android.Fabric;
import p041io.fabric.sdk.android.Kit;
import p041io.fabric.sdk.android.services.common.AbstractSpiCall;
import p041io.fabric.sdk.android.services.common.ResponseParser;
import p041io.fabric.sdk.android.services.events.FilesSender;
import p041io.fabric.sdk.android.services.network.HttpMethod;
import p041io.fabric.sdk.android.services.network.HttpRequest;
import p041io.fabric.sdk.android.services.network.HttpRequestFactory;

class SessionAnalyticsFilesSender extends AbstractSpiCall implements FilesSender {
    private final String apiKey;

    public SessionAnalyticsFilesSender(Kit kit, String protocolAndHostOverride, String url, HttpRequestFactory requestFactory, String apiKey) {
        super(kit, protocolAndHostOverride, url, requestFactory, HttpMethod.POST);
        this.apiKey = apiKey;
    }

    public boolean send(List<File> files) {
        HttpRequest httpRequest = getHttpRequest().header("X-CRASHLYTICS-API-CLIENT-TYPE", "android").header("X-CRASHLYTICS-API-CLIENT-VERSION", this.kit.getVersion()).header("X-CRASHLYTICS-API-KEY", this.apiKey);
        int i = 0;
        for (File file : files) {
            httpRequest.part("session_analytics_file_" + i, file.getName(), "application/vnd.crashlytics.android.events", file);
            i++;
        }
        Fabric.getLogger().mo34403d("Answers", "Sending " + files.size() + " analytics files to " + getUrl());
        int statusCode = httpRequest.code();
        Fabric.getLogger().mo34403d("Answers", "Response code for analytics file send is " + statusCode);
        return ResponseParser.parse(statusCode) == 0;
    }
}
