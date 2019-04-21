package com.mcdonalds.app.offers;

import com.ensighten.Ensighten;

public class OfferSection {
    private boolean mEnabled;
    private String mSectionTitle;
    private String mSectionType;

    public enum OfferSections {
        NEAR("NEAR"),
        FAVORITE("FAVORITE"),
        CURRENT("CURRENT"),
        OTHER("OTHER");
        
        private String mName;

        private OfferSections(String name) {
            this.mName = name;
        }

        public String getName() {
            Ensighten.evaluateEvent(this, "getName", null);
            return this.mName;
        }
    }

    public OfferSection(String sectionTitle, String sectionType, boolean enabled) {
        this.mSectionTitle = sectionTitle;
        this.mSectionType = sectionType;
        this.mEnabled = enabled;
    }

    public String getSectionType() {
        Ensighten.evaluateEvent(this, "getSectionType", null);
        return this.mSectionType;
    }

    public boolean isEnabled() {
        Ensighten.evaluateEvent(this, "isEnabled", null);
        return this.mEnabled;
    }

    public String toString() {
        Ensighten.evaluateEvent(this, "toString", null);
        return "OfferSection{mSectionTitle=\"" + this.mSectionTitle + "\", mSectionType=\"" + this.mSectionType + "\", mEnabled=" + this.mEnabled + "}";
    }
}
