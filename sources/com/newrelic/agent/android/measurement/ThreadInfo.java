package com.newrelic.agent.android.measurement;

public class ThreadInfo {
    /* renamed from: id */
    private long f6696id;
    private String name;

    public ThreadInfo() {
        this(Thread.currentThread());
    }

    public ThreadInfo(long id, String name) {
        this.f6696id = id;
        this.name = name;
    }

    public ThreadInfo(Thread thread) {
        this(thread.getId(), thread.getName());
    }

    public static ThreadInfo fromThread(Thread thread) {
        return new ThreadInfo(thread);
    }

    public long getId() {
        return this.f6696id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(long id) {
        this.f6696id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "ThreadInfo{id=" + this.f6696id + ", name='" + this.name + '\'' + '}';
    }
}
