package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.FeedBackType;
import java.io.Serializable;

public class MWFeedbackTypeName implements Serializable {
    @SerializedName("CultureAbbreviation")
    public String cultureAbbreviation;
    @SerializedName("FeedbackTypeID")
    public int feedbackTypeID;
    @SerializedName("IsValid")
    public boolean isValid;
    @SerializedName("LastModification")
    public String lastModification;
    @SerializedName("Name")
    public String name;

    public FeedBackType toFeedBackType() {
        FeedBackType type = new FeedBackType();
        type.setID(this.feedbackTypeID);
        type.setName(this.name);
        type.setValid(this.isValid);
        return type;
    }
}
