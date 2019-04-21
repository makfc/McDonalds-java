package com.newrelic.agent.android.logging;

public class NullAgentLog implements AgentLog {
    public void audit(String message) {
    }

    public void debug(String message) {
    }

    public void info(String message) {
    }

    public void verbose(String message) {
    }

    public void error(String message) {
    }

    public void error(String message, Throwable cause) {
    }

    public void warning(String message) {
    }

    public int getLevel() {
        return 5;
    }

    public void setLevel(int level) {
    }
}
