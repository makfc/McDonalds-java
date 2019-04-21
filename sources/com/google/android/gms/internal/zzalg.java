package com.google.android.gms.internal;

import java.io.IOException;
import java.io.Writer;

public final class zzalg {

    private static final class zza extends Writer {
        private final Appendable zzbXw;
        private final zza zzbXx;

        static class zza implements CharSequence {
            char[] zzbXy;

            zza() {
            }

            public char charAt(int i) {
                return this.zzbXy[i];
            }

            public int length() {
                return this.zzbXy.length;
            }

            public CharSequence subSequence(int i, int i2) {
                return new String(this.zzbXy, i, i2 - i);
            }
        }

        public void close() {
        }

        public void flush() {
        }

        public void write(int i) throws IOException {
            this.zzbXw.append((char) i);
        }

        public void write(char[] cArr, int i, int i2) throws IOException {
            this.zzbXx.zzbXy = cArr;
            this.zzbXw.append(this.zzbXx, i, i + i2);
        }
    }

    public static void zzb(zzakf zzakf, zzaly zzaly) throws IOException {
        zzalu.zzbYV.zza(zzaly, zzakf);
    }
}
