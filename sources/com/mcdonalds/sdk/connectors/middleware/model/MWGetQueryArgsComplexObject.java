package com.mcdonalds.sdk.connectors.middleware.model;

import java.util.List;

public interface MWGetQueryArgsComplexObject {
    Object getQueryProperty(String str);

    List<String> getQueryPropertyNames();
}
