package com.threatmetrix.TrustDefender;

public interface EndNotifier extends EndNotifierBase {
    void complete(ProfilingResult profilingResult);
}
