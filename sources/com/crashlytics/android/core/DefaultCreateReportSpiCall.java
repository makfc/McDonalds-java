package com.crashlytics.android.core;

import java.io.File;
import java.util.Map.Entry;
import p041io.fabric.sdk.android.Fabric;
import p041io.fabric.sdk.android.Kit;
import p041io.fabric.sdk.android.services.common.AbstractSpiCall;
import p041io.fabric.sdk.android.services.common.ResponseParser;
import p041io.fabric.sdk.android.services.network.HttpMethod;
import p041io.fabric.sdk.android.services.network.HttpRequest;
import p041io.fabric.sdk.android.services.network.HttpRequestFactory;

class DefaultCreateReportSpiCall extends AbstractSpiCall implements CreateReportSpiCall {
    public DefaultCreateReportSpiCall(Kit kit, String protocolAndHostOverride, String url, HttpRequestFactory requestFactory) {
        super(kit, protocolAndHostOverride, url, requestFactory, HttpMethod.POST);
    }

    public boolean invoke(CreateReportRequest requestData) {
        HttpRequest httpRequest = applyMultipartDataTo(applyHeadersTo(getHttpRequest(), requestData), requestData.report);
        Fabric.getLogger().mo34403d("CrashlyticsCore", "Sending report to: " + getUrl());
        int statusCode = httpRequest.code();
        Fabric.getLogger().mo34403d("CrashlyticsCore", "Create report request ID: " + httpRequest.header("X-REQUEST-ID"));
        Fabric.getLogger().mo34403d("CrashlyticsCore", "Result was: " + statusCode);
        return ResponseParser.parse(statusCode) == 0;
    }

    private HttpRequest applyHeadersTo(HttpRequest request, CreateReportRequest requestData) {
        request = request.header("X-CRASHLYTICS-API-KEY", requestData.apiKey).header("X-CRASHLYTICS-API-CLIENT-TYPE", "android").header("X-CRASHLYTICS-API-CLIENT-VERSION", this.kit.getVersion());
        for (Entry entry : requestData.report.getCustomHeaders().entrySet()) {
            request = request.header(entry);
        }
        return request;
    }

    private HttpRequest applyMultipartDataTo(HttpRequest request, Report report) {
        request.part("report[identifier]", report.getIdentifier());
        if (report.getFiles().length == 1) {
            Fabric.getLogger().mo34403d("CrashlyticsCore", "Adding single file " + report.getFileName() + " to report " + report.getIdentifier());
            return request.part("report[file]", report.getFileName(), "application/octet-stream", report.getFile());
        }
        int i = 0;
        for (File file : report.getFiles()) {
            Fabric.getLogger().mo34403d("CrashlyticsCore", "Adding file " + file.getName() + " to report " + report.getIdentifier());
            request.part("report[file" + i + "]", file.getName(), "application/octet-stream", file);
            i++;
        }
        return request;
    }
}
