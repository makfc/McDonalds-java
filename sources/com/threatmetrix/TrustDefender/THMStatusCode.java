package com.threatmetrix.TrustDefender;

public enum THMStatusCode {
    THM_NotYet("Not Yet", "Profile request has successfully started but is not completed"),
    THM_OK("Okay", "Completed, No errors"),
    THM_Connection_Error("Connection Error", "There has been a connection issue, profiling aborted"),
    THM_HostNotFound_Error("Host Not Found", "Unable to resolve the host name"),
    THM_NetworkTimeout_Error("Network Timeout", "Communications layer timed out"),
    THM_Internal_Error("Internal Error", "Internal Error, profiling incomplete or interrupted"),
    THM_HostVerification_Error("Host Verification Error", "Certificate verification failure! Potential MITM attack"),
    THM_Interrupted_Error("Interrupted", "Request was cancelled"),
    THM_Invalid_OrgID("Invalid ORG ID", "Request contained an invalid org id"),
    THM_ConfigurationError("Configuration Error", "Failed to retrieve configuration from server"),
    THM_PartialProfile("Partial Profile", "Connection error, only partial profile completed"),
    THM_Blocked("Profiling is blocked", "Profiling is blocked.(Screen is off)"),
    THM_Already_Initialised("Already Initialised", "TrustDefender is already initialised, use the current instance"),
    THM_Invalid_Context("Invalid Context", "Request contained an invalid context"),
    THM_Invalid_FP_Server("Invalid FP Server", "Request contained an invalid Fingerprint server url"),
    THM_EndNotifier_NotFound("EndNotifier not found", "EndNotifier is mandatory in profile request"),
    THM_ThirdPartyLibrary_Not_Found("Okhttp library Not found", "okhttp library can't be found"),
    THM_In_Quiet_Period("In Quiet Period", "Profiling is blocked (In Quiet Period)");
    
    /* renamed from: a */
    private final String f7267a;
    /* renamed from: b */
    private final String f7268b;

    private THMStatusCode(String label, String desc) {
        this.f7267a = label;
        this.f7268b = desc;
    }

    public final String toString() {
        return this.f7267a;
    }

    public final String getDesc() {
        return this.f7268b;
    }
}
