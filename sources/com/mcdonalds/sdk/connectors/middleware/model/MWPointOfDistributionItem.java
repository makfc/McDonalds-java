package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class MWPointOfDistributionItem implements Serializable {
    public static final int CURBSIDE = 4;
    public static final int DRIVETHRU = 2;
    public static final int KIOSK = 8;
    public static final int LOBBY = 0;
    @SerializedName("DigitalServices")
    public List<MWDigitalServices> digitalServices;
    @SerializedName("LocationID")
    public int locationId;
    @SerializedName("POD")
    public int pod;
}
