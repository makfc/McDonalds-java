package com.google.android.gms.internal;

import java.io.IOException;
import java.io.StringWriter;

public abstract class zzakf {
    public String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            zzaly zzaly = new zzaly(stringWriter);
            zzaly.setLenient(true);
            zzalg.zzb(this, zzaly);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public String zzVA() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public boolean zzVE() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public boolean zzVF() {
        return this instanceof zzakc;
    }

    public boolean zzVG() {
        return this instanceof zzaki;
    }

    public boolean zzVH() {
        return this instanceof zzakl;
    }

    public boolean zzVI() {
        return this instanceof zzakh;
    }

    public zzaki zzVJ() {
        if (zzVG()) {
            return (zzaki) this;
        }
        String valueOf = String.valueOf(this);
        throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 19).append("Not a JSON Object: ").append(valueOf).toString());
    }

    public zzakc zzVK() {
        if (zzVF()) {
            return (zzakc) this;
        }
        throw new IllegalStateException("This is not a JSON Array.");
    }

    public zzakl zzVL() {
        if (zzVH()) {
            return (zzakl) this;
        }
        throw new IllegalStateException("This is not a JSON Primitive.");
    }

    /* Access modifiers changed, original: 0000 */
    public Boolean zzVM() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Number zzVz() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }
}
