package com.facebook.stetho.server;

import org.apache.http.protocol.HttpRequestHandlerRegistry;

public interface RegistryInitializer {
    HttpRequestHandlerRegistry getRegistry();
}
