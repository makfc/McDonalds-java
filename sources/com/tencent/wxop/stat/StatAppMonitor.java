package com.tencent.wxop.stat;

public class StatAppMonitor implements Cloneable {
    /* renamed from: a */
    private String f6861a;
    /* renamed from: b */
    private long f6862b;
    /* renamed from: c */
    private long f6863c;
    /* renamed from: d */
    private int f6864d;
    /* renamed from: e */
    private long f6865e;
    /* renamed from: f */
    private int f6866f;
    /* renamed from: g */
    private int f6867g;

    public StatAppMonitor clone() {
        try {
            return (StatAppMonitor) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public String getInterfaceName() {
        return this.f6861a;
    }

    public long getMillisecondsConsume() {
        return this.f6865e;
    }

    public long getReqSize() {
        return this.f6862b;
    }

    public long getRespSize() {
        return this.f6863c;
    }

    public int getResultType() {
        return this.f6864d;
    }

    public int getReturnCode() {
        return this.f6866f;
    }

    public int getSampling() {
        return this.f6867g;
    }
}
