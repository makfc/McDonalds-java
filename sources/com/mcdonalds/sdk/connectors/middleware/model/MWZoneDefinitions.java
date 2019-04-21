package com.mcdonalds.sdk.connectors.middleware.model;

import android.content.Context;
import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.ZoneDefinitions;
import java.io.Serializable;

public class MWZoneDefinitions implements Serializable {
    @SerializedName("Color")
    public String color;
    @SerializedName("Definition")
    public String definition;
    @SerializedName("Enable")
    public boolean enable;
    @SerializedName("Ids")
    public String ids;
    @SerializedName("ZoneId")
    public int zoneId;

    public ZoneDefinitions toZoneDefinitions(Context context) {
        return toZoneDefinitions(context, null);
    }

    public ZoneDefinitions toZoneDefinitions(Context context, ZoneDefinitions zoneDefinitions) {
        if (zoneDefinitions == null) {
            zoneDefinitions = new ZoneDefinitions();
        }
        zoneDefinitions.setColor(this.color);
        zoneDefinitions.setDefinition(this.definition);
        zoneDefinitions.setEnable(this.enable);
        zoneDefinitions.setZoneId(this.zoneId);
        zoneDefinitions.setIds(this.ids);
        return zoneDefinitions;
    }
}
