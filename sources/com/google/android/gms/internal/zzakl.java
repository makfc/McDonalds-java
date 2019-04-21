package com.google.android.gms.internal;

import java.math.BigInteger;

public final class zzakl extends zzakf {
    private static final Class<?>[] zzbWt = new Class[]{Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};
    private Object zzbIu;

    public zzakl(Boolean bool) {
        setValue(bool);
    }

    public zzakl(Number number) {
        setValue(number);
    }

    public zzakl(String str) {
        setValue(str);
    }

    private static boolean zza(zzakl zzakl) {
        if (!(zzakl.zzbIu instanceof Number)) {
            return false;
        }
        Number number = (Number) zzakl.zzbIu;
        return (number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte);
    }

    private static boolean zzaI(Object obj) {
        if (obj instanceof String) {
            return true;
        }
        Class cls = obj.getClass();
        for (Class isAssignableFrom : zzbWt) {
            if (isAssignableFrom.isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzakl zzakl = (zzakl) obj;
        if (this.zzbIu == null) {
            return zzakl.zzbIu == null;
        } else {
            if (zza(this) && zza(zzakl)) {
                return zzVz().longValue() == zzakl.zzVz().longValue();
            } else {
                if (!(this.zzbIu instanceof Number) || !(zzakl.zzbIu instanceof Number)) {
                    return this.zzbIu.equals(zzakl.zzbIu);
                }
                double doubleValue = zzVz().doubleValue();
                double doubleValue2 = zzakl.zzVz().doubleValue();
                if (doubleValue == doubleValue2 || (Double.isNaN(doubleValue) && Double.isNaN(doubleValue2))) {
                    z = true;
                }
                return z;
            }
        }
    }

    public int hashCode() {
        if (this.zzbIu == null) {
            return 31;
        }
        long longValue;
        if (zza(this)) {
            longValue = zzVz().longValue();
            return (int) (longValue ^ (longValue >>> 32));
        } else if (!(this.zzbIu instanceof Number)) {
            return this.zzbIu.hashCode();
        } else {
            longValue = Double.doubleToLongBits(zzVz().doubleValue());
            return (int) (longValue ^ (longValue >>> 32));
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void setValue(Object obj) {
        if (obj instanceof Character) {
            this.zzbIu = String.valueOf(((Character) obj).charValue());
            return;
        }
        boolean z = (obj instanceof Number) || zzaI(obj);
        zzakx.zzaj(z);
        this.zzbIu = obj;
    }

    public String zzVA() {
        return zzVO() ? zzVz().toString() : zzVN() ? zzVM().toString() : (String) this.zzbIu;
    }

    public boolean zzVE() {
        return zzVN() ? zzVM().booleanValue() : Boolean.parseBoolean(zzVA());
    }

    /* Access modifiers changed, original: 0000 */
    public Boolean zzVM() {
        return (Boolean) this.zzbIu;
    }

    public boolean zzVN() {
        return this.zzbIu instanceof Boolean;
    }

    public boolean zzVO() {
        return this.zzbIu instanceof Number;
    }

    public boolean zzVP() {
        return this.zzbIu instanceof String;
    }

    public Number zzVz() {
        return this.zzbIu instanceof String ? new zzalc((String) this.zzbIu) : (Number) this.zzbIu;
    }
}
