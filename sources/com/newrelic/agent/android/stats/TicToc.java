package com.newrelic.agent.android.stats;

public class TicToc {
    private long endTime;
    private long startTime;
    private State state;

    private enum State {
        STOPPED,
        STARTED
    }

    public void tic() {
        this.state = State.STARTED;
        this.startTime = System.currentTimeMillis();
    }

    public long toc() {
        this.endTime = System.currentTimeMillis();
        if (this.state != State.STARTED) {
            return -1;
        }
        this.state = State.STOPPED;
        return this.endTime - this.startTime;
    }

    public long peek() {
        return this.state == State.STARTED ? System.currentTimeMillis() - this.startTime : 0;
    }
}
