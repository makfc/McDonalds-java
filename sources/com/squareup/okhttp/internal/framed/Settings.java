package com.squareup.okhttp.internal.framed;

import java.util.Arrays;

public final class Settings {
    private int persistValue;
    private int persisted;
    private int set;
    private final int[] values = new int[10];

    /* Access modifiers changed, original: 0000 */
    public void clear() {
        this.persisted = 0;
        this.persistValue = 0;
        this.set = 0;
        Arrays.fill(this.values, 0);
    }

    /* Access modifiers changed, original: 0000 */
    public Settings set(int id, int idFlags, int value) {
        if (id < this.values.length) {
            int bit = 1 << id;
            this.set |= bit;
            if ((idFlags & 1) != 0) {
                this.persistValue |= bit;
            } else {
                this.persistValue &= bit ^ -1;
            }
            if ((idFlags & 2) != 0) {
                this.persisted |= bit;
            } else {
                this.persisted &= bit ^ -1;
            }
            this.values[id] = value;
        }
        return this;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isSet(int id) {
        if ((this.set & (1 << id)) != 0) {
            return true;
        }
        return false;
    }

    /* Access modifiers changed, original: 0000 */
    public int get(int id) {
        return this.values[id];
    }

    /* Access modifiers changed, original: 0000 */
    public int flags(int id) {
        int result = 0;
        if (isPersisted(id)) {
            result = 0 | 2;
        }
        if (persistValue(id)) {
            return result | 1;
        }
        return result;
    }

    /* Access modifiers changed, original: 0000 */
    public int size() {
        return Integer.bitCount(this.set);
    }

    /* Access modifiers changed, original: 0000 */
    public int getHeaderTableSize() {
        return (this.set & 2) != 0 ? this.values[1] : -1;
    }

    /* Access modifiers changed, original: 0000 */
    public int getMaxConcurrentStreams(int defaultValue) {
        return (this.set & 16) != 0 ? this.values[4] : defaultValue;
    }

    /* Access modifiers changed, original: 0000 */
    public int getMaxFrameSize(int defaultValue) {
        return (this.set & 32) != 0 ? this.values[5] : defaultValue;
    }

    /* Access modifiers changed, original: 0000 */
    public int getInitialWindowSize(int defaultValue) {
        return (this.set & 128) != 0 ? this.values[7] : defaultValue;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean persistValue(int id) {
        if ((this.persistValue & (1 << id)) != 0) {
            return true;
        }
        return false;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isPersisted(int id) {
        if ((this.persisted & (1 << id)) != 0) {
            return true;
        }
        return false;
    }

    /* Access modifiers changed, original: 0000 */
    public void merge(Settings other) {
        for (int i = 0; i < 10; i++) {
            if (other.isSet(i)) {
                set(i, other.flags(i), other.get(i));
            }
        }
    }
}
