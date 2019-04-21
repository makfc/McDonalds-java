package com.google.api.client.testing.util;

import com.google.api.client.util.BackOff;
import com.google.api.client.util.Beta;
import java.io.IOException;

@Beta
public class MockBackOff implements BackOff {
    private long backOffMillis;
    private int maxTries = 10;
    private int numTries;

    public long nextBackOffMillis() throws IOException {
        if (this.numTries >= this.maxTries || this.backOffMillis == -1) {
            return -1;
        }
        this.numTries++;
        return this.backOffMillis;
    }
}
