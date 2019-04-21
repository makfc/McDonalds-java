package com.newrelic.agent.android;

public class AgentInitializationException extends Exception {
    private static final long serialVersionUID = 2725421917845262499L;

    public AgentInitializationException(String message) {
        super(message);
    }
}
