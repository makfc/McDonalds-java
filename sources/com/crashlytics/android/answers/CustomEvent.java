package com.crashlytics.android.answers;

public class CustomEvent extends AnswersEvent<CustomEvent> {
    private final String eventName;

    public String toString() {
        return "{eventName:\"" + this.eventName + '\"' + ", customAttributes:" + this.customAttributes + "}";
    }
}
