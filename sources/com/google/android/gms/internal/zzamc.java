package com.google.android.gms.internal;

import android.support.p000v4.media.TransportMediator;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

public final class zzamc {
    private final ByteBuffer zzbZZ;

    public static class zza extends IOException {
        zza(int i, int i2) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space (pos " + i + " limit " + i2 + ").");
        }
    }

    private zzamc(ByteBuffer byteBuffer) {
        this.zzbZZ = byteBuffer;
        this.zzbZZ.order(ByteOrder.LITTLE_ENDIAN);
    }

    private zzamc(byte[] bArr, int i, int i2) {
        this(ByteBuffer.wrap(bArr, i, i2));
    }

    public static int zzG(int i, int i2) {
        return zzof(i) + zzoc(i2);
    }

    public static zzamc zzO(byte[] bArr) {
        return zzb(bArr, 0, bArr.length);
    }

    public static int zzQ(byte[] bArr) {
        return zzoh(bArr.length) + bArr.length;
    }

    private static int zza(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        int i3 = i;
        while (i3 < length) {
            char charAt = charSequence.charAt(i3);
            if (charAt < 2048) {
                i2 += (127 - charAt) >>> 31;
            } else {
                i2 += 2;
                if (55296 <= charAt && charAt <= 57343) {
                    if (Character.codePointAt(charSequence, i3) < 65536) {
                        throw new IllegalArgumentException("Unpaired surrogate at index " + i3);
                    }
                    i3++;
                }
            }
            i3++;
        }
        return i2;
    }

    private static int zza(CharSequence charSequence, byte[] bArr, int i, int i2) {
        char charAt;
        int length = charSequence.length();
        int i3 = 0;
        int i4 = i + i2;
        while (i3 < length && i3 + i < i4) {
            charAt = charSequence.charAt(i3);
            if (charAt >= 128) {
                break;
            }
            bArr[i + i3] = (byte) charAt;
            i3++;
        }
        if (i3 == length) {
            return i + length;
        }
        int i5 = i + i3;
        while (i3 < length) {
            int i6;
            char charAt2 = charSequence.charAt(i3);
            int i7;
            if (charAt2 < 128 && i5 < i4) {
                i6 = i5 + 1;
                bArr[i5] = (byte) charAt2;
            } else if (charAt2 < 2048 && i5 <= i4 - 2) {
                i7 = i5 + 1;
                bArr[i5] = (byte) ((charAt2 >>> 6) | 960);
                i6 = i7 + 1;
                bArr[i7] = (byte) ((charAt2 & 63) | 128);
            } else if ((charAt2 < 55296 || 57343 < charAt2) && i5 <= i4 - 3) {
                i6 = i5 + 1;
                bArr[i5] = (byte) ((charAt2 >>> 12) | 480);
                i5 = i6 + 1;
                bArr[i6] = (byte) (((charAt2 >>> 6) & 63) | 128);
                i6 = i5 + 1;
                bArr[i5] = (byte) ((charAt2 & 63) | 128);
            } else if (i5 <= i4 - 4) {
                if (i3 + 1 != charSequence.length()) {
                    i3++;
                    charAt = charSequence.charAt(i3);
                    if (Character.isSurrogatePair(charAt2, charAt)) {
                        int toCodePoint = Character.toCodePoint(charAt2, charAt);
                        i6 = i5 + 1;
                        bArr[i5] = (byte) ((toCodePoint >>> 18) | 240);
                        i5 = i6 + 1;
                        bArr[i6] = (byte) (((toCodePoint >>> 12) & 63) | 128);
                        i7 = i5 + 1;
                        bArr[i5] = (byte) (((toCodePoint >>> 6) & 63) | 128);
                        i6 = i7 + 1;
                        bArr[i7] = (byte) ((toCodePoint & 63) | 128);
                    }
                }
                throw new IllegalArgumentException("Unpaired surrogate at index " + (i3 - 1));
            } else {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt2 + " at index " + i5);
            }
            i3++;
            i5 = i6;
        }
        return i5;
    }

    private static void zza(CharSequence charSequence, ByteBuffer byteBuffer) {
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        } else if (byteBuffer.hasArray()) {
            try {
                byteBuffer.position(zza(charSequence, byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining()) - byteBuffer.arrayOffset());
            } catch (ArrayIndexOutOfBoundsException e) {
                BufferOverflowException bufferOverflowException = new BufferOverflowException();
                bufferOverflowException.initCause(e);
                throw bufferOverflowException;
            }
        } else {
            zzb(charSequence, byteBuffer);
        }
    }

    public static int zzaT(long j) {
        return zzaY(j);
    }

    public static int zzaU(long j) {
        return zzaY(j);
    }

    public static int zzaW(long j) {
        return zzaY(zzba(j));
    }

    public static int zzaY(long j) {
        return (-128 & j) == 0 ? 1 : (-16384 & j) == 0 ? 2 : (-2097152 & j) == 0 ? 3 : (-268435456 & j) == 0 ? 4 : (-34359738368L & j) == 0 ? 5 : (-4398046511104L & j) == 0 ? 6 : (-562949953421312L & j) == 0 ? 7 : (-72057594037927936L & j) == 0 ? 8 : (Long.MIN_VALUE & j) == 0 ? 9 : 10;
    }

    public static int zzb(int i, double d) {
        return zzof(i) + zzq(d);
    }

    public static int zzb(int i, zzamj zzamj) {
        return (zzof(i) * 2) + zzd(zzamj);
    }

    public static int zzb(int i, byte[] bArr) {
        return zzof(i) + zzQ(bArr);
    }

    public static zzamc zzb(byte[] bArr, int i, int i2) {
        return new zzamc(bArr, i, i2);
    }

    private static void zzb(CharSequence charSequence, ByteBuffer byteBuffer) {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (charAt < 128) {
                byteBuffer.put((byte) charAt);
            } else if (charAt < 2048) {
                byteBuffer.put((byte) ((charAt >>> 6) | 960));
                byteBuffer.put((byte) ((charAt & 63) | 128));
            } else if (charAt < 55296 || 57343 < charAt) {
                byteBuffer.put((byte) ((charAt >>> 12) | 480));
                byteBuffer.put((byte) (((charAt >>> 6) & 63) | 128));
                byteBuffer.put((byte) ((charAt & 63) | 128));
            } else {
                if (i + 1 != charSequence.length()) {
                    i++;
                    char charAt2 = charSequence.charAt(i);
                    if (Character.isSurrogatePair(charAt, charAt2)) {
                        int toCodePoint = Character.toCodePoint(charAt, charAt2);
                        byteBuffer.put((byte) ((toCodePoint >>> 18) | 240));
                        byteBuffer.put((byte) (((toCodePoint >>> 12) & 63) | 128));
                        byteBuffer.put((byte) (((toCodePoint >>> 6) & 63) | 128));
                        byteBuffer.put((byte) ((toCodePoint & 63) | 128));
                    }
                }
                throw new IllegalArgumentException("Unpaired surrogate at index " + (i - 1));
            }
            i++;
        }
    }

    public static long zzba(long j) {
        return (j << 1) ^ (j >> 63);
    }

    public static int zzbd(boolean z) {
        return 1;
    }

    public static int zzc(int i, float f) {
        return zzof(i) + zzj(f);
    }

    public static int zzc(int i, zzamj zzamj) {
        return zzof(i) + zze(zzamj);
    }

    public static int zzd(zzamj zzamj) {
        return zzamj.getSerializedSize();
    }

    private static int zzd(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        while (i < length && charSequence.charAt(i) < 128) {
            i++;
        }
        int i2 = i;
        i = length;
        while (i2 < length) {
            char charAt = charSequence.charAt(i2);
            if (charAt >= 2048) {
                i += zza(charSequence, i2);
                break;
            }
            i2++;
            i = ((127 - charAt) >>> 31) + i;
        }
        if (i >= length) {
            return i;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long) i) + 4294967296L));
    }

    public static int zze(int i, long j) {
        return zzof(i) + zzaU(j);
    }

    public static int zze(zzamj zzamj) {
        int serializedSize = zzamj.getSerializedSize();
        return serializedSize + zzoh(serializedSize);
    }

    public static int zzg(int i, long j) {
        return zzof(i) + zzaW(j);
    }

    public static int zziZ(String str) {
        int zzd = zzd((CharSequence) str);
        return zzd + zzoh(zzd);
    }

    public static int zzj(float f) {
        return 4;
    }

    public static int zzk(int i, boolean z) {
        return zzof(i) + zzbd(z);
    }

    public static int zzoc(int i) {
        return i >= 0 ? zzoh(i) : 10;
    }

    public static int zzof(int i) {
        return zzoh(zzamm.zzJ(i, 0));
    }

    public static int zzoh(int i) {
        return (i & -128) == 0 ? 1 : (i & -16384) == 0 ? 2 : (-2097152 & i) == 0 ? 3 : (-268435456 & i) == 0 ? 4 : 5;
    }

    public static int zzq(double d) {
        return 8;
    }

    public static int zzr(int i, String str) {
        return zzof(i) + zziZ(str);
    }

    public void zzE(int i, int i2) throws IOException {
        zzI(i, 0);
        zzoa(i2);
    }

    public void zzI(int i, int i2) throws IOException {
        zzog(zzamm.zzJ(i, i2));
    }

    public void zzP(byte[] bArr) throws IOException {
        zzog(bArr.length);
        zzR(bArr);
    }

    public void zzR(byte[] bArr) throws IOException {
        zzc(bArr, 0, bArr.length);
    }

    public int zzWT() {
        return this.zzbZZ.remaining();
    }

    public void zzWU() {
        if (zzWT() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public void zza(int i, double d) throws IOException {
        zzI(i, 1);
        zzp(d);
    }

    public void zza(int i, long j) throws IOException {
        zzI(i, 0);
        zzaP(j);
    }

    public void zza(int i, zzamj zzamj) throws IOException {
        zzI(i, 2);
        zzc(zzamj);
    }

    public void zza(int i, byte[] bArr) throws IOException {
        zzI(i, 2);
        zzP(bArr);
    }

    public void zzaP(long j) throws IOException {
        zzaX(j);
    }

    public void zzaQ(long j) throws IOException {
        zzaX(j);
    }

    public void zzaS(long j) throws IOException {
        zzaX(zzba(j));
    }

    public void zzaX(long j) throws IOException {
        while ((-128 & j) != 0) {
            zzoe((((int) j) & TransportMediator.KEYCODE_MEDIA_PAUSE) | 128);
            j >>>= 7;
        }
        zzoe((int) j);
    }

    public void zzaZ(long j) throws IOException {
        if (this.zzbZZ.remaining() < 8) {
            throw new zza(this.zzbZZ.position(), this.zzbZZ.limit());
        }
        this.zzbZZ.putLong(j);
    }

    public void zzb(int i, float f) throws IOException {
        zzI(i, 5);
        zzi(f);
    }

    public void zzb(int i, long j) throws IOException {
        zzI(i, 0);
        zzaQ(j);
    }

    public void zzb(zzamj zzamj) throws IOException {
        zzamj.writeTo(this);
    }

    public void zzbc(boolean z) throws IOException {
        zzoe(z ? 1 : 0);
    }

    public void zzc(byte b) throws IOException {
        if (this.zzbZZ.hasRemaining()) {
            this.zzbZZ.put(b);
            return;
        }
        throw new zza(this.zzbZZ.position(), this.zzbZZ.limit());
    }

    public void zzc(zzamj zzamj) throws IOException {
        zzog(zzamj.getCachedSize());
        zzamj.writeTo(this);
    }

    public void zzc(byte[] bArr, int i, int i2) throws IOException {
        if (this.zzbZZ.remaining() >= i2) {
            this.zzbZZ.put(bArr, i, i2);
            return;
        }
        throw new zza(this.zzbZZ.position(), this.zzbZZ.limit());
    }

    public void zzd(int i, long j) throws IOException {
        zzI(i, 0);
        zzaS(j);
    }

    public void zzi(float f) throws IOException {
        zzoi(Float.floatToIntBits(f));
    }

    public void zziY(String str) throws IOException {
        try {
            int zzoh = zzoh(str.length());
            if (zzoh == zzoh(str.length() * 3)) {
                int position = this.zzbZZ.position();
                if (this.zzbZZ.remaining() < zzoh) {
                    throw new zza(zzoh + position, this.zzbZZ.limit());
                }
                this.zzbZZ.position(position + zzoh);
                zza((CharSequence) str, this.zzbZZ);
                int position2 = this.zzbZZ.position();
                this.zzbZZ.position(position);
                zzog((position2 - position) - zzoh);
                this.zzbZZ.position(position2);
                return;
            }
            zzog(zzd((CharSequence) str));
            zza((CharSequence) str, this.zzbZZ);
        } catch (BufferOverflowException e) {
            zza zza = new zza(this.zzbZZ.position(), this.zzbZZ.limit());
            zza.initCause(e);
            throw zza;
        }
    }

    public void zzj(int i, boolean z) throws IOException {
        zzI(i, 0);
        zzbc(z);
    }

    public void zzoa(int i) throws IOException {
        if (i >= 0) {
            zzog(i);
        } else {
            zzaX((long) i);
        }
    }

    public void zzoe(int i) throws IOException {
        zzc((byte) i);
    }

    public void zzog(int i) throws IOException {
        while ((i & -128) != 0) {
            zzoe((i & TransportMediator.KEYCODE_MEDIA_PAUSE) | 128);
            i >>>= 7;
        }
        zzoe(i);
    }

    public void zzoi(int i) throws IOException {
        if (this.zzbZZ.remaining() < 4) {
            throw new zza(this.zzbZZ.position(), this.zzbZZ.limit());
        }
        this.zzbZZ.putInt(i);
    }

    public void zzp(double d) throws IOException {
        zzaZ(Double.doubleToLongBits(d));
    }

    public void zzq(int i, String str) throws IOException {
        zzI(i, 2);
        zziY(str);
    }
}
