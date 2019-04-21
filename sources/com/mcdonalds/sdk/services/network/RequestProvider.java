package com.mcdonalds.sdk.services.network;

import java.util.List;
import java.util.Map;

public interface RequestProvider<T, E> {

    public enum MethodType {
        GET,
        POST,
        PUT,
        DELETE
    }

    public enum RequestType {
        JSON,
        MMAP_JSON,
        IMAGE,
        HTML
    }

    String getBody();

    List<? extends CustomTypeAdapter> getCustomTypeAdapters();

    Map<String, String> getHeaders();

    MethodType getMethodType();

    RequestType getRequestType();

    Class<T> getResponseClass();

    String getURLString();

    void setBody(E e);

    String toString();
}
