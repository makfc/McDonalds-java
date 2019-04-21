package com.crashlytics.android.core;

class CompositeCreateReportSpiCall implements CreateReportSpiCall {
    private final DefaultCreateReportSpiCall javaReportSpiCall;
    private final NativeCreateReportSpiCall nativeReportSpiCall;

    public CompositeCreateReportSpiCall(DefaultCreateReportSpiCall javaReportSpiCall, NativeCreateReportSpiCall nativeReportSpiCall) {
        this.javaReportSpiCall = javaReportSpiCall;
        this.nativeReportSpiCall = nativeReportSpiCall;
    }

    public boolean invoke(CreateReportRequest requestData) {
        switch (requestData.report.getType()) {
            case JAVA:
                this.javaReportSpiCall.invoke(requestData);
                return true;
            case NATIVE:
                this.nativeReportSpiCall.invoke(requestData);
                return true;
            default:
                return false;
        }
    }
}
