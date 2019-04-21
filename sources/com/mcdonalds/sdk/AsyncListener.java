package com.mcdonalds.sdk;

public interface AsyncListener<T> {
    void onResponse(T t, AsyncToken asyncToken, AsyncException asyncException);
}
