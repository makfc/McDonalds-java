package com.newrelic.agent.android.logging;

public class DefaultAgentLog implements AgentLog {
    private AgentLog impl = new NullAgentLog();

    public void setImpl(AgentLog impl) {
        synchronized (this) {
            this.impl = impl;
        }
    }

    public void audit(String message) {
        synchronized (this) {
            this.impl.audit(message);
        }
    }

    public void debug(String message) {
        synchronized (this) {
            this.impl.debug(message);
        }
    }

    public void info(String message) {
        synchronized (this) {
            this.impl.info(message);
        }
    }

    public void verbose(String message) {
        synchronized (this) {
            this.impl.verbose(message);
        }
    }

    public void warning(String message) {
        synchronized (this) {
            this.impl.warning(message);
        }
    }

    public void error(String message) {
        synchronized (this) {
            this.impl.error(message);
        }
    }

    public void error(String message, Throwable cause) {
        synchronized (this) {
            this.impl.error(message, cause);
        }
    }

    public int getLevel() {
        int level;
        synchronized (this) {
            level = this.impl.getLevel();
        }
        return level;
    }

    public void setLevel(int level) {
        synchronized (this) {
            this.impl.setLevel(level);
        }
    }
}
