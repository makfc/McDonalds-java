package com.newrelic.agent.android.background;

import java.util.EventObject;

public class ApplicationStateEvent extends EventObject {
    private static final long serialVersionUID = 1;

    public ApplicationStateEvent(Object source) {
        super(source);
    }
}
