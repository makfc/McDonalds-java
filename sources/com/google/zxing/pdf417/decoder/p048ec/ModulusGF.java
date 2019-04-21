package com.google.zxing.pdf417.decoder.p048ec;

import com.google.zxing.pdf417.PDF417Common;

/* renamed from: com.google.zxing.pdf417.decoder.ec.ModulusGF */
public final class ModulusGF {
    public static final ModulusGF PDF417_GF = new ModulusGF(PDF417Common.NUMBER_OF_CODEWORDS, 3);
    private final int[] expTable;
    private final int[] logTable;
    private final int modulus;
    private final ModulusPoly one;
    private final ModulusPoly zero;

    private ModulusGF(int modulus, int generator) {
        int i;
        this.modulus = modulus;
        this.expTable = new int[modulus];
        this.logTable = new int[modulus];
        int x = 1;
        for (i = 0; i < modulus; i++) {
            this.expTable[i] = x;
            x = (x * generator) % modulus;
        }
        for (i = 0; i < modulus - 1; i++) {
            this.logTable[this.expTable[i]] = i;
        }
        this.zero = new ModulusPoly(this, new int[]{0});
        this.one = new ModulusPoly(this, new int[]{1});
    }

    /* Access modifiers changed, original: 0000 */
    public ModulusPoly getZero() {
        return this.zero;
    }

    /* Access modifiers changed, original: 0000 */
    public ModulusPoly getOne() {
        return this.one;
    }

    /* Access modifiers changed, original: 0000 */
    public ModulusPoly buildMonomial(int degree, int coefficient) {
        if (degree < 0) {
            throw new IllegalArgumentException();
        } else if (coefficient == 0) {
            return this.zero;
        } else {
            int[] coefficients = new int[(degree + 1)];
            coefficients[0] = coefficient;
            return new ModulusPoly(this, coefficients);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public int add(int a, int b) {
        return (a + b) % this.modulus;
    }

    /* Access modifiers changed, original: 0000 */
    public int subtract(int a, int b) {
        return ((this.modulus + a) - b) % this.modulus;
    }

    /* Access modifiers changed, original: 0000 */
    public int exp(int a) {
        return this.expTable[a];
    }

    /* Access modifiers changed, original: 0000 */
    public int log(int a) {
        if (a != 0) {
            return this.logTable[a];
        }
        throw new IllegalArgumentException();
    }

    /* Access modifiers changed, original: 0000 */
    public int inverse(int a) {
        if (a != 0) {
            return this.expTable[(this.modulus - this.logTable[a]) - 1];
        }
        throw new ArithmeticException();
    }

    /* Access modifiers changed, original: 0000 */
    public int multiply(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        return this.expTable[(this.logTable[a] + this.logTable[b]) % (this.modulus - 1)];
    }

    /* Access modifiers changed, original: 0000 */
    public int getSize() {
        return this.modulus;
    }
}
