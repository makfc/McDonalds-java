package com.newrelic.agent.android.logging;

import com.mcdonalds.sdk.services.configuration.Configuration;

public class ConsoleAgentLog implements AgentLog {
    private int level = 3;

    public void audit(String message) {
        if (this.level == 6) {
            print("AUDIT", message);
        }
    }

    public void debug(String message) {
        if (this.level >= 5) {
            print(Configuration.DEBUG_CONFIG_KEY, message);
        }
    }

    public void verbose(String message) {
        if (this.level >= 4) {
            print("VERBOSE", message);
        }
    }

    public void info(String message) {
        if (this.level >= 3) {
            print("INFO", message);
        }
    }

    public void warning(String message) {
        if (this.level >= 2) {
            print("WARN", message);
        }
    }

    public void error(String message, Throwable cause) {
        if (this.level >= 1) {
            print("ERROR", message + " " + cause.getMessage());
        }
    }

    public void error(String message) {
        if (this.level >= 1) {
            print("ERROR", message);
        }
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    private static void print(String tag, String message) {
        System.out.println("[" + tag + "] " + message);
    }
}
