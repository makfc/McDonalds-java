package com.google.api.client.http;

import com.autonavi.amap.mapcore.VTMCDataCache;
import com.google.api.client.util.Beta;
import com.google.api.client.util.ExponentialBackOff;
import java.io.IOException;

@Beta
@Deprecated
public class ExponentialBackOffPolicy implements BackOffPolicy {
    private final ExponentialBackOff exponentialBackOff;

    @Beta
    @Deprecated
    public static class Builder {
        final com.google.api.client.util.ExponentialBackOff.Builder exponentialBackOffBuilder = new com.google.api.client.util.ExponentialBackOff.Builder();

        protected Builder() {
        }
    }

    public ExponentialBackOffPolicy() {
        this(new Builder());
    }

    protected ExponentialBackOffPolicy(Builder builder) {
        this.exponentialBackOff = builder.exponentialBackOffBuilder.build();
    }

    public boolean isBackOffRequired(int statusCode) {
        switch (statusCode) {
            case VTMCDataCache.MAXSIZE /*500*/:
            case 503:
                return true;
            default:
                return false;
        }
    }

    public final void reset() {
        this.exponentialBackOff.reset();
    }

    public long getNextBackOffMillis() throws IOException {
        return this.exponentialBackOff.nextBackOffMillis();
    }
}
