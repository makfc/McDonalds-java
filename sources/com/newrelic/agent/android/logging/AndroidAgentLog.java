package com.newrelic.agent.android.logging;

import android.util.Log;

public class AndroidAgentLog implements AgentLog {
    private static final String TAG = "com.newrelic.android";
    private int level = 3;

    public void audit(String message) {
        if (this.level == 6) {
            Log.d(TAG, message);
        }
    }

    public void debug(String message) {
        if (this.level >= 5) {
            Log.d(TAG, message);
        }
    }

    public void verbose(String message) {
        if (this.level >= 4) {
            Log.v(TAG, message);
        }
    }

    public void info(String message) {
        if (this.level >= 3) {
            Log.i(TAG, message);
        }
    }

    public void warning(String message) {
        if (this.level >= 2) {
            Log.w(TAG, message);
        }
    }

    public void error(String message) {
        if (this.level >= 1) {
            Log.e(TAG, message);
        }
    }

    public void error(String message, Throwable cause) {
        if (this.level >= 1) {
            Log.e(TAG, message, cause);
        }
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        if (level > 6 || level < 1) {
            throw new IllegalArgumentException("Log level is not between ERROR and AUDIT");
        }
        this.level = level;
    }
}
