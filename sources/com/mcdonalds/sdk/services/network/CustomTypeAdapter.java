package com.mcdonalds.sdk.services.network;

public interface CustomTypeAdapter<T> {
    Object getDeserializer();

    Object getSerializer();

    Class<T> getType();
}
