package p041io.fabric.sdk.android.services.settings;

/* renamed from: io.fabric.sdk.android.services.settings.SessionSettingsData */
public class SessionSettingsData {
    public final int identifierMask;
    public final int logBufferSize;
    public final int maxChainedExceptionDepth;
    public final int maxCompleteSessionsCount;
    public final int maxCustomExceptionEvents;
    public final int maxCustomKeyValuePairs;
    public final boolean sendSessionWithoutCrash;

    public SessionSettingsData(int logBufferSize, int maxChainedExceptionDepth, int maxCustomExceptionEvents, int maxCustomKeyValuePairs, int identifierMask, boolean sendSessionWithoutCrash, int maxCompleteSessionsCount) {
        this.logBufferSize = logBufferSize;
        this.maxChainedExceptionDepth = maxChainedExceptionDepth;
        this.maxCustomExceptionEvents = maxCustomExceptionEvents;
        this.maxCustomKeyValuePairs = maxCustomKeyValuePairs;
        this.identifierMask = identifierMask;
        this.sendSessionWithoutCrash = sendSessionWithoutCrash;
        this.maxCompleteSessionsCount = maxCompleteSessionsCount;
    }
}
