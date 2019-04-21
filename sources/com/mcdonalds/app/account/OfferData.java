package com.mcdonalds.app.account;

import com.ensighten.Ensighten;

public class OfferData {
    private String mDescription;
    private Integer mId;
    private Boolean mState;

    public Integer getId() {
        Ensighten.evaluateEvent(this, "getId", null);
        return this.mId;
    }

    public void setId(Integer mId) {
        Ensighten.evaluateEvent(this, "setId", new Object[]{mId});
        this.mId = mId;
    }

    public String getDescription() {
        Ensighten.evaluateEvent(this, "getDescription", null);
        return this.mDescription;
    }

    public void setDescription(String mDescription) {
        Ensighten.evaluateEvent(this, "setDescription", new Object[]{mDescription});
        this.mDescription = mDescription;
    }

    public Boolean getState() {
        Ensighten.evaluateEvent(this, "getState", null);
        return this.mState;
    }

    public void setState(Boolean mState) {
        Ensighten.evaluateEvent(this, "setState", new Object[]{mState});
        this.mState = mState;
    }
}
