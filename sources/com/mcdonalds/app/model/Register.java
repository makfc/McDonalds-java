package com.mcdonalds.app.model;

import com.ensighten.Ensighten;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Register {
    @SerializedName("chooseEmailOrPhoneAsUsername")
    private boolean chooseSignInMethod;
    @SerializedName("fields")
    private List<FormField> fields;
    @SerializedName("phoneMaxNumberOfDigits")
    private int phoneLength;
    @SerializedName("showMessageErrorOnView")
    private boolean semanticErrors;
    @SerializedName("nameIsSingleField")
    private boolean singleFieldName;
    @SerializedName("toggles")
    private List<RegisterToggle> toggles;

    public List<FormField> getFields() {
        Ensighten.evaluateEvent(this, "getFields", null);
        return this.fields;
    }

    public List<RegisterToggle> getToggles() {
        Ensighten.evaluateEvent(this, "getToggles", null);
        return this.toggles;
    }

    public boolean isSingleFieldName() {
        Ensighten.evaluateEvent(this, "isSingleFieldName", null);
        return this.singleFieldName;
    }

    public boolean chooseSignInMethodEnabled() {
        Ensighten.evaluateEvent(this, "chooseSignInMethodEnabled", null);
        return this.chooseSignInMethod;
    }
}
