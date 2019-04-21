package com.crashlytics.android.answers;

class AnswersEventValidator {
    boolean failFast;
    final int maxNumAttributes;
    final int maxStringLength;

    public AnswersEventValidator(int maxNumAttributes, int maxStringLength, boolean failFast) {
        this.maxNumAttributes = maxNumAttributes;
        this.maxStringLength = maxStringLength;
        this.failFast = failFast;
    }
}
