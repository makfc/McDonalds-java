package com.tencent.wxop.stat;

public class StatGameUser implements Cloneable {
    /* renamed from: a */
    private String f6913a = "";
    /* renamed from: b */
    private String f6914b = "";
    /* renamed from: c */
    private String f6915c = "";

    public StatGameUser clone() {
        try {
            return (StatGameUser) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public String getAccount() {
        return this.f6914b;
    }

    public String getLevel() {
        return this.f6915c;
    }

    public String getWorldName() {
        return this.f6913a;
    }

    public String toString() {
        return "StatGameUser [worldName=" + this.f6913a + ", account=" + this.f6914b + ", level=" + this.f6915c + "]";
    }
}
