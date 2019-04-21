package com.google.android.gms.internal;

import android.support.p000v4.media.TransportMediator;
import java.io.IOException;

public final class zzamb {
    private final byte[] buffer;
    private int zzbZQ;
    private int zzbZR;
    private int zzbZS;
    private int zzbZT;
    private int zzbZU;
    private int zzbZV = Integer.MAX_VALUE;
    private int zzbZW;
    private int zzbZX = 64;
    private int zzbZY = 67108864;

    private zzamb(byte[] bArr, int i, int i2) {
        this.buffer = bArr;
        this.zzbZQ = i;
        this.zzbZR = i + i2;
        this.zzbZT = i;
    }

    public static zzamb zzN(byte[] bArr) {
        return zza(bArr, 0, bArr.length);
    }

    private void zzWP() {
        this.zzbZR += this.zzbZS;
        int i = this.zzbZR;
        if (i > this.zzbZV) {
            this.zzbZS = i - this.zzbZV;
            this.zzbZR -= this.zzbZS;
            return;
        }
        this.zzbZS = 0;
    }

    public static zzamb zza(byte[] bArr, int i, int i2) {
        return new zzamb(bArr, i, i2);
    }

    public static long zzaO(long j) {
        return (j >>> 1) ^ (-(1 & j));
    }

    public int getPosition() {
        return this.zzbZT - this.zzbZQ;
    }

    public byte[] readBytes() throws IOException {
        int zzWL = zzWL();
        if (zzWL < 0) {
            throw zzami.zzWY();
        } else if (zzWL == 0) {
            return zzamm.zzcas;
        } else {
            if (zzWL > this.zzbZR - this.zzbZT) {
                throw zzami.zzWX();
            }
            byte[] bArr = new byte[zzWL];
            System.arraycopy(this.buffer, this.zzbZT, bArr, 0, zzWL);
            this.zzbZT = zzWL + this.zzbZT;
            return bArr;
        }
    }

    public double readDouble() throws IOException {
        return Double.longBitsToDouble(zzWO());
    }

    public float readFloat() throws IOException {
        return Float.intBitsToFloat(zzWN());
    }

    public String readString() throws IOException {
        int zzWL = zzWL();
        if (zzWL < 0) {
            throw zzami.zzWY();
        } else if (zzWL > this.zzbZR - this.zzbZT) {
            throw zzami.zzWX();
        } else {
            String str = new String(this.buffer, this.zzbZT, zzWL, zzamh.UTF_8);
            this.zzbZT = zzWL + this.zzbZT;
            return str;
        }
    }

    public byte[] zzD(int i, int i2) {
        if (i2 == 0) {
            return zzamm.zzcas;
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(this.buffer, this.zzbZQ + i, bArr, 0, i2);
        return bArr;
    }

    public int zzWC() throws IOException {
        if (zzWR()) {
            this.zzbZU = 0;
            return 0;
        }
        this.zzbZU = zzWL();
        if (this.zzbZU != 0) {
            return this.zzbZU;
        }
        throw zzami.zzXa();
    }

    public void zzWD() throws IOException {
        int zzWC;
        do {
            zzWC = zzWC();
            if (zzWC == 0) {
                return;
            }
        } while (zznU(zzWC));
    }

    public long zzWE() throws IOException {
        return zzWM();
    }

    public long zzWF() throws IOException {
        return zzWM();
    }

    public int zzWG() throws IOException {
        return zzWL();
    }

    public boolean zzWI() throws IOException {
        return zzWL() != 0;
    }

    public long zzWK() throws IOException {
        return zzaO(zzWM());
    }

    public int zzWL() throws IOException {
        byte zzWS = zzWS();
        if (zzWS >= (byte) 0) {
            return zzWS;
        }
        int i = zzWS & TransportMediator.KEYCODE_MEDIA_PAUSE;
        byte zzWS2 = zzWS();
        if (zzWS2 >= (byte) 0) {
            return i | (zzWS2 << 7);
        }
        i |= (zzWS2 & TransportMediator.KEYCODE_MEDIA_PAUSE) << 7;
        zzWS2 = zzWS();
        if (zzWS2 >= (byte) 0) {
            return i | (zzWS2 << 14);
        }
        i |= (zzWS2 & TransportMediator.KEYCODE_MEDIA_PAUSE) << 14;
        zzWS2 = zzWS();
        if (zzWS2 >= (byte) 0) {
            return i | (zzWS2 << 21);
        }
        i |= (zzWS2 & TransportMediator.KEYCODE_MEDIA_PAUSE) << 21;
        zzWS2 = zzWS();
        i |= zzWS2 << 28;
        if (zzWS2 >= (byte) 0) {
            return i;
        }
        for (int i2 = 0; i2 < 5; i2++) {
            if (zzWS() >= (byte) 0) {
                return i;
            }
        }
        throw zzami.zzWZ();
    }

    public long zzWM() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zzWS = zzWS();
            j |= ((long) (zzWS & TransportMediator.KEYCODE_MEDIA_PAUSE)) << i;
            if ((zzWS & 128) == 0) {
                return j;
            }
        }
        throw zzami.zzWZ();
    }

    public int zzWN() throws IOException {
        return (((zzWS() & 255) | ((zzWS() & 255) << 8)) | ((zzWS() & 255) << 16)) | ((zzWS() & 255) << 24);
    }

    public long zzWO() throws IOException {
        byte zzWS = zzWS();
        byte zzWS2 = zzWS();
        return ((((((((((long) zzWS2) & 255) << 8) | (((long) zzWS) & 255)) | ((((long) zzWS()) & 255) << 16)) | ((((long) zzWS()) & 255) << 24)) | ((((long) zzWS()) & 255) << 32)) | ((((long) zzWS()) & 255) << 40)) | ((((long) zzWS()) & 255) << 48)) | ((((long) zzWS()) & 255) << 56);
    }

    public int zzWQ() {
        if (this.zzbZV == Integer.MAX_VALUE) {
            return -1;
        }
        return this.zzbZV - this.zzbZT;
    }

    public boolean zzWR() {
        return this.zzbZT == this.zzbZR;
    }

    public byte zzWS() throws IOException {
        if (this.zzbZT == this.zzbZR) {
            throw zzami.zzWX();
        }
        byte[] bArr = this.buffer;
        int i = this.zzbZT;
        this.zzbZT = i + 1;
        return bArr[i];
    }

    public void zza(zzamj zzamj) throws IOException {
        int zzWL = zzWL();
        if (this.zzbZW >= this.zzbZX) {
            throw zzami.zzXd();
        }
        zzWL = zznW(zzWL);
        this.zzbZW++;
        zzamj.mergeFrom(this);
        zznT(0);
        this.zzbZW--;
        zznX(zzWL);
    }

    public void zza(zzamj zzamj, int i) throws IOException {
        if (this.zzbZW >= this.zzbZX) {
            throw zzami.zzXd();
        }
        this.zzbZW++;
        zzamj.mergeFrom(this);
        zznT(zzamm.zzJ(i, 4));
        this.zzbZW--;
    }

    public void zznT(int i) throws zzami {
        if (this.zzbZU != i) {
            throw zzami.zzXb();
        }
    }

    public boolean zznU(int i) throws IOException {
        switch (zzamm.zzon(i)) {
            case 0:
                zzWG();
                return true;
            case 1:
                zzWO();
                return true;
            case 2:
                zznZ(zzWL());
                return true;
            case 3:
                zzWD();
                zznT(zzamm.zzJ(zzamm.zzoo(i), 4));
                return true;
            case 4:
                return false;
            case 5:
                zzWN();
                return true;
            default:
                throw zzami.zzXc();
        }
    }

    public int zznW(int i) throws zzami {
        if (i < 0) {
            throw zzami.zzWY();
        }
        int i2 = this.zzbZT + i;
        int i3 = this.zzbZV;
        if (i2 > i3) {
            throw zzami.zzWX();
        }
        this.zzbZV = i2;
        zzWP();
        return i3;
    }

    public void zznX(int i) {
        this.zzbZV = i;
        zzWP();
    }

    public void zznY(int i) {
        if (i > this.zzbZT - this.zzbZQ) {
            throw new IllegalArgumentException("Position " + i + " is beyond current " + (this.zzbZT - this.zzbZQ));
        } else if (i < 0) {
            throw new IllegalArgumentException("Bad position " + i);
        } else {
            this.zzbZT = this.zzbZQ + i;
        }
    }

    public void zznZ(int i) throws IOException {
        if (i < 0) {
            throw zzami.zzWY();
        } else if (this.zzbZT + i > this.zzbZV) {
            zznZ(this.zzbZV - this.zzbZT);
            throw zzami.zzWX();
        } else if (i <= this.zzbZR - this.zzbZT) {
            this.zzbZT += i;
        } else {
            throw zzami.zzWX();
        }
    }
}
