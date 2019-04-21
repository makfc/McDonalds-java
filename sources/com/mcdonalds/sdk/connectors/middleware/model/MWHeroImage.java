package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class MWHeroImage implements Serializable {
    @SerializedName("alt_text")
    public String altText;
    @SerializedName("description")
    public String description;
    @SerializedName("image_name")
    public String imageName;
    @SerializedName("url")
    public String url;
}
