package com.squareup.okhttp;

public interface Connection {
    Handshake getHandshake();

    Route getRoute();
}
