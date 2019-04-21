package com.google.android.gms.tagmanager;

class zzdk extends Number implements Comparable<zzdk> {
    private double zzbrD;
    private long zzbrE;
    private boolean zzbrF = false;

    private zzdk(double d) {
        this.zzbrD = d;
    }

    private zzdk(long j) {
        this.zzbrE = j;
    }

    public static zzdk zza(Double d) {
        return new zzdk(d.doubleValue());
    }

    public static zzdk zzar(long j) {
        return new zzdk(j);
    }

    public static zzdk zzgM(String str) throws NumberFormatException {
        try {
            return new zzdk(Long.parseLong(str));
        } catch (NumberFormatException e) {
            try {
                return new zzdk(Double.parseDouble(str));
            } catch (NumberFormatException e2) {
                throw new NumberFormatException(String.valueOf(str).concat(" is not a valid TypedNumber"));
            }
        }
    }

    public byte byteValue() {
        return (byte) ((int) longValue());
    }

    public double doubleValue() {
        return zzKJ() ? (double) this.zzbrE : this.zzbrD;
    }

    public boolean equals(Object obj) {
        return (obj instanceof zzdk) && compareTo((zzdk) obj) == 0;
    }

    public float floatValue() {
        return (float) doubleValue();
    }

    public int hashCode() {
        return new Long(longValue()).hashCode();
    }

    public int intValue() {
        return zzKL();
    }

    public long longValue() {
        return zzKK();
    }

    public short shortValue() {
        return zzKM();
    }

    public String toString() {
        return zzKJ() ? Long.toString(this.zzbrE) : Double.toString(this.zzbrD);
    }

    public boolean zzKI() {
        return !zzKJ();
    }

    public boolean zzKJ() {
        return this.zzbrF;
    }

    public long zzKK() {
        return zzKJ() ? this.zzbrE : (long) this.zzbrD;
    }

    public int zzKL() {
        return (int) longValue();
    }

    public short zzKM() {
        return (short) ((int) longValue());
    }

    /* renamed from: zza */
    public int compareTo(zzdk zzdk) {
        return (zzKJ() && zzdk.zzKJ()) ? new Long(this.zzbrE).compareTo(Long.valueOf(zzdk.zzbrE)) : Double.compare(doubleValue(), zzdk.doubleValue());
    }
}
