package com.mcdonalds.sdk;

import java.util.UUID;

public class AsyncToken {
    private final String mPrefix;
    private final UUID mUUID = UUID.randomUUID();

    public AsyncToken(String prefix) {
        this.mPrefix = prefix;
    }

    public String getPrefix() {
        return this.mPrefix;
    }

    public UUID getUUID() {
        return this.mUUID;
    }

    public String toString() {
        return (this.mPrefix == null ? "" : this.mPrefix + ".") + this.mUUID.toString();
    }
}
