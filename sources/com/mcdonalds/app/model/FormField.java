package com.mcdonalds.app.model;

import com.ensighten.Ensighten;
import com.google.gson.annotations.SerializedName;

public class FormField {
    @SerializedName("name")
    private String name;
    @SerializedName("options")
    private String[] options;
    @SerializedName("required")
    private boolean required;
    @SerializedName("show")
    private boolean show;

    public String getName() {
        Ensighten.evaluateEvent(this, "getName", null);
        return this.name;
    }

    public boolean isRequired() {
        Ensighten.evaluateEvent(this, "isRequired", null);
        return this.required;
    }

    public boolean doShow() {
        Ensighten.evaluateEvent(this, "doShow", null);
        return this.show;
    }

    public String[] getOptions() {
        Ensighten.evaluateEvent(this, "getOptions", null);
        return this.options;
    }
}
