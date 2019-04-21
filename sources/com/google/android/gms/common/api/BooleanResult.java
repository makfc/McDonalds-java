package com.google.android.gms.common.api;

public class BooleanResult implements Result {
    private final Status zzaaO;
    private final boolean zzakR;

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BooleanResult)) {
            return false;
        }
        BooleanResult booleanResult = (BooleanResult) obj;
        return this.zzaaO.equals(booleanResult.zzaaO) && this.zzakR == booleanResult.zzakR;
    }

    public Status getStatus() {
        return this.zzaaO;
    }

    public final int hashCode() {
        return (this.zzakR ? 1 : 0) + ((this.zzaaO.hashCode() + 527) * 31);
    }
}
