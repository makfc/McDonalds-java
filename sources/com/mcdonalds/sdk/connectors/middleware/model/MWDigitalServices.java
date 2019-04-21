package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class MWDigitalServices implements Serializable {
    @SerializedName("Key")
    public String key;
    @SerializedName("Technologies")
    public List<TechKey> technologies;

    public static class TechKey implements Serializable {
        @SerializedName("Key")
        public String key;
    }
}
