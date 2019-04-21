package com.mcdonalds.app.model;

import com.ensighten.Ensighten;
import com.google.gson.annotations.SerializedName;

public class RegisterToggle {
    @SerializedName("defaultState")
    private boolean defaultState;
    @SerializedName("name")
    private String name;
    @SerializedName("requiredTrue")
    private boolean requiredTrue;

    public String getName() {
        Ensighten.evaluateEvent(this, "getName", null);
        return this.name;
    }

    public boolean isRequired() {
        Ensighten.evaluateEvent(this, "isRequired", null);
        return this.requiredTrue;
    }

    public boolean getDefaultState() {
        Ensighten.evaluateEvent(this, "getDefaultState", null);
        return this.defaultState;
    }
}
