package com.threatmetrix.TrustDefender;

public class ProfilingResult {
    /* renamed from: a */
    private String f7264a;
    /* renamed from: b */
    private THMStatusCode f7265b;

    ProfilingResult(String sessionID, THMStatusCode status) {
        this.f7264a = sessionID;
        this.f7265b = status;
    }

    public String getSessionID() {
        return this.f7264a;
    }

    public THMStatusCode getStatus() {
        return this.f7265b;
    }
}
