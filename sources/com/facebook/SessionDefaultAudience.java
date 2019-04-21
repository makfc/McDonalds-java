package com.facebook;

import com.facebook.internal.NativeProtocol;

public enum SessionDefaultAudience {
    NONE(null),
    ONLY_ME(NativeProtocol.AUDIENCE_ME),
    FRIENDS(NativeProtocol.AUDIENCE_FRIENDS),
    EVERYONE(NativeProtocol.AUDIENCE_EVERYONE);
    
    private final String nativeProtocolAudience;

    private SessionDefaultAudience(String protocol) {
        this.nativeProtocolAudience = protocol;
    }

    public String getNativeProtocolAudience() {
        return this.nativeProtocolAudience;
    }
}
