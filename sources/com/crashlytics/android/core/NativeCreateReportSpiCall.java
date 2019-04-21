package com.crashlytics.android.core;

import java.io.File;
import p041io.fabric.sdk.android.Fabric;
import p041io.fabric.sdk.android.Kit;
import p041io.fabric.sdk.android.services.common.AbstractSpiCall;
import p041io.fabric.sdk.android.services.common.ResponseParser;
import p041io.fabric.sdk.android.services.network.HttpMethod;
import p041io.fabric.sdk.android.services.network.HttpRequest;
import p041io.fabric.sdk.android.services.network.HttpRequestFactory;

class NativeCreateReportSpiCall extends AbstractSpiCall implements CreateReportSpiCall {
    public NativeCreateReportSpiCall(Kit kit, String protocolAndHostOverride, String url, HttpRequestFactory requestFactory) {
        super(kit, protocolAndHostOverride, url, requestFactory, HttpMethod.POST);
    }

    public boolean invoke(CreateReportRequest requestData) {
        HttpRequest httpRequest = applyMultipartDataTo(applyHeadersTo(getHttpRequest(), requestData.apiKey), requestData.report);
        Fabric.getLogger().mo34403d("CrashlyticsCore", "Sending report to: " + getUrl());
        int statusCode = httpRequest.code();
        Fabric.getLogger().mo34403d("CrashlyticsCore", "Result was: " + statusCode);
        return ResponseParser.parse(statusCode) == 0;
    }

    private HttpRequest applyHeadersTo(HttpRequest httpRequest, String apiKey) {
        httpRequest.header("User-Agent", "Crashlytics Android SDK/" + this.kit.getVersion()).header("X-CRASHLYTICS-API-CLIENT-TYPE", "android").header("X-CRASHLYTICS-API-CLIENT-VERSION", this.kit.getVersion()).header("X-CRASHLYTICS-API-KEY", apiKey);
        return httpRequest;
    }

    private HttpRequest applyMultipartDataTo(HttpRequest httpRequest, Report report) {
        httpRequest.part("report_id", report.getIdentifier());
        for (File f : report.getFiles()) {
            if (f.getName().equals("minidump")) {
                httpRequest.part("minidump_file", f.getName(), "application/octet-stream", f);
            } else if (f.getName().equals("metadata")) {
                httpRequest.part("crash_meta_file", f.getName(), "application/octet-stream", f);
            } else if (f.getName().equals("binaryImages")) {
                httpRequest.part("binary_images_file", f.getName(), "application/octet-stream", f);
            } else if (f.getName().equals("session")) {
                httpRequest.part("session_meta_file", f.getName(), "application/octet-stream", f);
            } else if (f.getName().equals("app")) {
                httpRequest.part("app_meta_file", f.getName(), "application/octet-stream", f);
            } else if (f.getName().equals("device")) {
                httpRequest.part("device_meta_file", f.getName(), "application/octet-stream", f);
            } else if (f.getName().equals("os")) {
                httpRequest.part("os_meta_file", f.getName(), "application/octet-stream", f);
            } else if (f.getName().equals("user")) {
                httpRequest.part("user_meta_file", f.getName(), "application/octet-stream", f);
            } else if (f.getName().equals("logs")) {
                httpRequest.part("logs_file", f.getName(), "application/octet-stream", f);
            } else if (f.getName().equals("keys")) {
                httpRequest.part("keys_file", f.getName(), "application/octet-stream", f);
            }
        }
        return httpRequest;
    }
}
