package com.bumptech.glide.gifencoder;

import android.support.p000v4.internal.view.SupportMenu;
import android.support.p000v4.media.TransportMediator;
import java.io.IOException;
import java.io.OutputStream;

class LZWEncoder {
    int ClearCode;
    int EOFCode;
    int a_count;
    byte[] accum = new byte[256];
    boolean clear_flg = false;
    int[] codetab = new int[5003];
    private int curPixel;
    int cur_accum = 0;
    int cur_bits = 0;
    int free_ent = 0;
    int g_init_bits;
    int hsize = 5003;
    int[] htab = new int[5003];
    private int imgH;
    private int imgW;
    private int initCodeSize;
    int[] masks = new int[]{0, 1, 3, 7, 15, 31, 63, TransportMediator.KEYCODE_MEDIA_PAUSE, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, SupportMenu.USER_MASK};
    int maxbits = 12;
    int maxcode;
    int maxmaxcode = 4096;
    int n_bits;
    private byte[] pixAry;
    private int remaining;

    LZWEncoder(int width, int height, byte[] pixels, int color_depth) {
        this.imgW = width;
        this.imgH = height;
        this.pixAry = pixels;
        this.initCodeSize = Math.max(2, color_depth);
    }

    /* Access modifiers changed, original: 0000 */
    public void char_out(byte c, OutputStream outs) throws IOException {
        byte[] bArr = this.accum;
        int i = this.a_count;
        this.a_count = i + 1;
        bArr[i] = c;
        if (this.a_count >= 254) {
            flush_char(outs);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void cl_block(OutputStream outs) throws IOException {
        cl_hash(this.hsize);
        this.free_ent = this.ClearCode + 2;
        this.clear_flg = true;
        output(this.ClearCode, outs);
    }

    /* Access modifiers changed, original: 0000 */
    public void cl_hash(int hsize) {
        for (int i = 0; i < hsize; i++) {
            this.htab[i] = -1;
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0087  */
    public void compress(int r11, java.io.OutputStream r12) throws java.io.IOException {
        /*
        r10 = this;
        r9 = 0;
        r10.g_init_bits = r11;
        r10.clear_flg = r9;
        r7 = r10.g_init_bits;
        r10.n_bits = r7;
        r7 = r10.n_bits;
        r7 = r10.MAXCODE(r7);
        r10.maxcode = r7;
        r7 = 1;
        r8 = r11 + -1;
        r7 = r7 << r8;
        r10.ClearCode = r7;
        r7 = r10.ClearCode;
        r7 = r7 + 1;
        r10.EOFCode = r7;
        r7 = r10.ClearCode;
        r7 = r7 + 2;
        r10.free_ent = r7;
        r10.a_count = r9;
        r2 = r10.nextPixel();
        r4 = 0;
        r3 = r10.hsize;
    L_0x002c:
        r7 = 65536; // 0x10000 float:9.18355E-41 double:3.2379E-319;
        if (r3 >= r7) goto L_0x0035;
    L_0x0030:
        r4 = r4 + 1;
        r3 = r3 * 2;
        goto L_0x002c;
    L_0x0035:
        r4 = 8 - r4;
        r5 = r10.hsize;
        r10.cl_hash(r5);
        r7 = r10.ClearCode;
        r10.output(r7, r12);
    L_0x0041:
        r0 = r10.nextPixel();
        r7 = -1;
        if (r0 == r7) goto L_0x009a;
    L_0x0048:
        r7 = r10.maxbits;
        r7 = r0 << r7;
        r3 = r7 + r2;
        r7 = r0 << r4;
        r6 = r7 ^ r2;
        r7 = r10.htab;
        r7 = r7[r6];
        if (r7 != r3) goto L_0x005d;
    L_0x0058:
        r7 = r10.codetab;
        r2 = r7[r6];
        goto L_0x0041;
    L_0x005d:
        r7 = r10.htab;
        r7 = r7[r6];
        if (r7 < 0) goto L_0x007d;
    L_0x0063:
        r1 = r5 - r6;
        if (r6 != 0) goto L_0x0068;
    L_0x0067:
        r1 = 1;
    L_0x0068:
        r6 = r6 - r1;
        if (r6 >= 0) goto L_0x006c;
    L_0x006b:
        r6 = r6 + r5;
    L_0x006c:
        r7 = r10.htab;
        r7 = r7[r6];
        if (r7 != r3) goto L_0x0077;
    L_0x0072:
        r7 = r10.codetab;
        r2 = r7[r6];
        goto L_0x0041;
    L_0x0077:
        r7 = r10.htab;
        r7 = r7[r6];
        if (r7 >= 0) goto L_0x0068;
    L_0x007d:
        r10.output(r2, r12);
        r2 = r0;
        r7 = r10.free_ent;
        r8 = r10.maxmaxcode;
        if (r7 >= r8) goto L_0x0096;
    L_0x0087:
        r7 = r10.codetab;
        r8 = r10.free_ent;
        r9 = r8 + 1;
        r10.free_ent = r9;
        r7[r6] = r8;
        r7 = r10.htab;
        r7[r6] = r3;
        goto L_0x0041;
    L_0x0096:
        r10.cl_block(r12);
        goto L_0x0041;
    L_0x009a:
        r10.output(r2, r12);
        r7 = r10.EOFCode;
        r10.output(r7, r12);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.gifencoder.LZWEncoder.compress(int, java.io.OutputStream):void");
    }

    /* Access modifiers changed, original: 0000 */
    public void encode(OutputStream os) throws IOException {
        os.write(this.initCodeSize);
        this.remaining = this.imgW * this.imgH;
        this.curPixel = 0;
        compress(this.initCodeSize + 1, os);
        os.write(0);
    }

    /* Access modifiers changed, original: 0000 */
    public void flush_char(OutputStream outs) throws IOException {
        if (this.a_count > 0) {
            outs.write(this.a_count);
            outs.write(this.accum, 0, this.a_count);
            this.a_count = 0;
        }
    }

    /* Access modifiers changed, original: final */
    public final int MAXCODE(int n_bits) {
        return (1 << n_bits) - 1;
    }

    private int nextPixel() {
        if (this.remaining == 0) {
            return -1;
        }
        this.remaining--;
        byte[] bArr = this.pixAry;
        int i = this.curPixel;
        this.curPixel = i + 1;
        return bArr[i] & 255;
    }

    /* Access modifiers changed, original: 0000 */
    public void output(int code, OutputStream outs) throws IOException {
        this.cur_accum &= this.masks[this.cur_bits];
        if (this.cur_bits > 0) {
            this.cur_accum |= code << this.cur_bits;
        } else {
            this.cur_accum = code;
        }
        this.cur_bits += this.n_bits;
        while (this.cur_bits >= 8) {
            char_out((byte) (this.cur_accum & 255), outs);
            this.cur_accum >>= 8;
            this.cur_bits -= 8;
        }
        if (this.free_ent > this.maxcode || this.clear_flg) {
            if (this.clear_flg) {
                int i = this.g_init_bits;
                this.n_bits = i;
                this.maxcode = MAXCODE(i);
                this.clear_flg = false;
            } else {
                this.n_bits++;
                if (this.n_bits == this.maxbits) {
                    this.maxcode = this.maxmaxcode;
                } else {
                    this.maxcode = MAXCODE(this.n_bits);
                }
            }
        }
        if (code == this.EOFCode) {
            while (this.cur_bits > 0) {
                char_out((byte) (this.cur_accum & 255), outs);
                this.cur_accum >>= 8;
                this.cur_bits -= 8;
            }
            flush_char(outs);
        }
    }
}
