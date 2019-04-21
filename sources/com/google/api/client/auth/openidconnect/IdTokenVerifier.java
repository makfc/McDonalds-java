package com.google.api.client.auth.openidconnect;

import com.google.api.client.util.Beta;
import com.google.api.client.util.Clock;
import java.util.Collection;
import java.util.Collections;

@Beta
public class IdTokenVerifier {
    private final long acceptableTimeSkewSeconds;
    private final Collection<String> audience;
    private final Clock clock;
    private final String issuer;

    @Beta
    public static class Builder {
        long acceptableTimeSkewSeconds = 300;
        Collection<String> audience;
        Clock clock = Clock.SYSTEM;
        String issuer;
    }

    public IdTokenVerifier() {
        this(new Builder());
    }

    protected IdTokenVerifier(Builder builder) {
        this.clock = builder.clock;
        this.acceptableTimeSkewSeconds = builder.acceptableTimeSkewSeconds;
        this.issuer = builder.issuer;
        this.audience = builder.audience == null ? null : Collections.unmodifiableCollection(builder.audience);
    }
}
