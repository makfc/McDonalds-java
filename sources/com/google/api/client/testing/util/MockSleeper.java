package com.google.api.client.testing.util;

import com.google.api.client.util.Beta;
import com.google.api.client.util.Sleeper;

@Beta
public class MockSleeper implements Sleeper {
    private int count;
    private long lastMillis;

    public void sleep(long millis) throws InterruptedException {
        this.count++;
        this.lastMillis = millis;
    }
}
