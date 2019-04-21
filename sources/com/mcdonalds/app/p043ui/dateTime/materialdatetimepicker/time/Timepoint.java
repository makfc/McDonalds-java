package com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker.time;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import com.ensighten.Ensighten;

/* renamed from: com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.Timepoint */
public class Timepoint implements Parcelable, Comparable<Timepoint> {
    public static final Creator<Timepoint> CREATOR = new C38391();
    private int hour;
    private int minute;
    private int second;

    /* renamed from: com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.Timepoint$1 */
    static class C38391 implements Creator<Timepoint> {
        C38391() {
        }

        public Timepoint createFromParcel(Parcel in) {
            return new Timepoint(in);
        }

        public Timepoint[] newArray(int size) {
            return new Timepoint[size];
        }
    }

    /* renamed from: com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.Timepoint$TYPE */
    public enum TYPE {
        HOUR,
        MINUTE,
        SECOND
    }

    public Timepoint(Timepoint time) {
        this(time.hour, time.minute, time.second);
    }

    public Timepoint(@IntRange int hour, @IntRange int minute, @IntRange int second) {
        this.hour = hour % 24;
        this.minute = minute % 60;
        this.second = second % 60;
    }

    public Timepoint(@IntRange int hour, @IntRange int minute) {
        this(hour, minute, 0);
    }

    public Timepoint(@IntRange int hour) {
        this(hour, 0);
    }

    public Timepoint(Parcel in) {
        this.hour = in.readInt();
        this.minute = in.readInt();
        this.second = in.readInt();
    }

    @IntRange
    public int getHour() {
        Ensighten.evaluateEvent(this, "getHour", null);
        return this.hour;
    }

    @IntRange
    public int getMinute() {
        Ensighten.evaluateEvent(this, "getMinute", null);
        return this.minute;
    }

    @IntRange
    public int getSecond() {
        Ensighten.evaluateEvent(this, "getSecond", null);
        return this.second;
    }

    public boolean isAM() {
        Ensighten.evaluateEvent(this, "isAM", null);
        return this.hour < 12;
    }

    public boolean isPM() {
        Ensighten.evaluateEvent(this, "isPM", null);
        return !isAM();
    }

    public void setAM() {
        Ensighten.evaluateEvent(this, "setAM", null);
        if (this.hour >= 12) {
            this.hour %= 12;
        }
    }

    public void setPM() {
        Ensighten.evaluateEvent(this, "setPM", null);
        if (this.hour < 12) {
            this.hour = (this.hour + 12) % 24;
        }
    }

    public void add(TYPE type, int value) {
        Ensighten.evaluateEvent(this, "add", new Object[]{type, new Integer(value)});
        if (type == TYPE.MINUTE) {
            value *= 60;
        }
        if (type == TYPE.HOUR) {
            value *= 3600;
        }
        value += toSeconds();
        switch (type) {
            case SECOND:
                this.second = (value % 3600) % 60;
                break;
            case MINUTE:
                break;
            case HOUR:
                break;
            default:
                return;
        }
        this.minute = (value % 3600) / 60;
        this.hour = (value / 3600) % 24;
    }

    public int get(@NonNull TYPE type) {
        Ensighten.evaluateEvent(this, "get", new Object[]{type});
        switch (type) {
            case SECOND:
                return getSecond();
            case MINUTE:
                return getMinute();
            default:
                return getHour();
        }
    }

    public int toSeconds() {
        Ensighten.evaluateEvent(this, "toSeconds", null);
        return ((this.hour * 3600) + (this.minute * 60)) + this.second;
    }

    public int hashCode() {
        Ensighten.evaluateEvent(this, "hashCode", null);
        return toSeconds();
    }

    public boolean equals(Object o) {
        Ensighten.evaluateEvent(this, "equals", new Object[]{o});
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (hashCode() != ((Timepoint) o).hashCode()) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Missing block: B:9:0x002e, code skipped:
            if (r0 == false) goto L_0x004b;
     */
    /* JADX WARNING: Missing block: B:11:0x0038, code skipped:
            if (r6.getMinute() != getMinute()) goto L_0x004b;
     */
    /* JADX WARNING: Missing block: B:12:0x003a, code skipped:
            r0 = true;
     */
    /* JADX WARNING: Missing block: B:13:0x003b, code skipped:
            if (r0 == false) goto L_0x004d;
     */
    /* JADX WARNING: Missing block: B:15:0x0045, code skipped:
            if (r6.getHour() != getHour()) goto L_0x004d;
     */
    /* JADX WARNING: Missing block: B:16:0x0047, code skipped:
            r0 = true;
     */
    /* JADX WARNING: Missing block: B:18:0x004b, code skipped:
            r0 = false;
     */
    /* JADX WARNING: Missing block: B:19:0x004d, code skipped:
            r0 = false;
     */
    /* JADX WARNING: Missing block: B:21:?, code skipped:
            return r0;
     */
    public boolean equals(@android.support.annotation.Nullable com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker.time.Timepoint r6, @android.support.annotation.NonNull com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker.time.Timepoint.TYPE r7) {
        /*
        r5 = this;
        r1 = 1;
        r2 = 0;
        r3 = "equals";
        r4 = 2;
        r4 = new java.lang.Object[r4];
        r4[r2] = r6;
        r4[r1] = r7;
        com.ensighten.Ensighten.evaluateEvent(r5, r3, r4);
        if (r6 != 0) goto L_0x0013;
    L_0x0012:
        return r2;
    L_0x0013:
        r0 = 1;
        r3 = com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker.time.Timepoint.C38402.f6673x89ebf93a;
        r4 = r7.ordinal();
        r3 = r3[r4];
        switch(r3) {
            case 1: goto L_0x0021;
            case 2: goto L_0x002e;
            case 3: goto L_0x003b;
            default: goto L_0x001f;
        };
    L_0x001f:
        r2 = r0;
        goto L_0x0012;
    L_0x0021:
        if (r0 == 0) goto L_0x0049;
    L_0x0023:
        r3 = r6.getSecond();
        r4 = r5.getSecond();
        if (r3 != r4) goto L_0x0049;
    L_0x002d:
        r0 = r1;
    L_0x002e:
        if (r0 == 0) goto L_0x004b;
    L_0x0030:
        r3 = r6.getMinute();
        r4 = r5.getMinute();
        if (r3 != r4) goto L_0x004b;
    L_0x003a:
        r0 = r1;
    L_0x003b:
        if (r0 == 0) goto L_0x004d;
    L_0x003d:
        r3 = r6.getHour();
        r4 = r5.getHour();
        if (r3 != r4) goto L_0x004d;
    L_0x0047:
        r0 = r1;
    L_0x0048:
        goto L_0x001f;
    L_0x0049:
        r0 = r2;
        goto L_0x002e;
    L_0x004b:
        r0 = r2;
        goto L_0x003b;
    L_0x004d:
        r0 = r2;
        goto L_0x0048;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker.time.Timepoint.equals(com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.Timepoint, com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.Timepoint$TYPE):boolean");
    }

    public int compareTo(@NonNull Timepoint t) {
        Ensighten.evaluateEvent(this, "compareTo", new Object[]{t});
        return hashCode() - t.hashCode();
    }

    public void writeToParcel(Parcel out, int flags) {
        Ensighten.evaluateEvent(this, "writeToParcel", new Object[]{out, new Integer(flags)});
        out.writeInt(this.hour);
        out.writeInt(this.minute);
        out.writeInt(this.second);
    }

    public int describeContents() {
        Ensighten.evaluateEvent(this, "describeContents", null);
        return 0;
    }

    public String toString() {
        Ensighten.evaluateEvent(this, "toString", null);
        return "" + this.hour + "h " + this.minute + "m " + this.second + "s";
    }
}
