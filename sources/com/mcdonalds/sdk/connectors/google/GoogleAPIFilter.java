package com.mcdonalds.sdk.connectors.google;

public enum GoogleAPIFilter {
    PlayLand("PlayLand"),
    DriveThru("DriveThru"),
    WiFi("WiFi"),
    GiftCards("GiftCards"),
    MobileOffers("MobileOffers"),
    MobileOrdering("MobileOrdering");
    
    private String text;

    private GoogleAPIFilter(String text) {
        this.text = text;
    }

    public String toString() {
        return this.text;
    }
}
