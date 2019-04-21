package com.crashlytics.android.answers;

import p041io.fabric.sdk.android.Fabric;

public abstract class AnswersEvent<T extends AnswersEvent> {
    final AnswersAttributes customAttributes = new AnswersAttributes(this.validator);
    final AnswersEventValidator validator = new AnswersEventValidator(20, 100, Fabric.isDebuggable());
}
