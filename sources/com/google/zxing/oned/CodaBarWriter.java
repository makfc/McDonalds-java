package com.google.zxing.oned;

public final class CodaBarWriter extends OneDimensionalCodeWriter {
    private static final char[] ALT_START_END_CHARS = new char[]{'T', 'N', '*', 'E'};
    private static final char[] CHARS_WHICH_ARE_TEN_LENGTH_EACH_AFTER_DECODED = new char[]{'/', ':', '+', '.'};
    private static final char DEFAULT_GUARD = START_END_CHARS[0];
    private static final char[] START_END_CHARS = new char[]{'A', 'B', 'C', 'D'};

    /* JADX WARNING: Removed duplicated region for block: B:50:0x019d  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x01da A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x01d4  */
    public boolean[] encode(java.lang.String r22) {
        /*
        r21 = this;
        r18 = r22.length();
        r19 = 2;
        r0 = r18;
        r1 = r19;
        if (r0 >= r1) goto L_0x0063;
    L_0x000c:
        r18 = new java.lang.StringBuilder;
        r18.<init>();
        r19 = DEFAULT_GUARD;
        r18 = r18.append(r19);
        r0 = r18;
        r1 = r22;
        r18 = r0.append(r1);
        r19 = DEFAULT_GUARD;
        r18 = r18.append(r19);
        r22 = r18.toString();
    L_0x0029:
        r15 = 20;
        r10 = 1;
    L_0x002c:
        r18 = r22.length();
        r18 = r18 + -1;
        r0 = r18;
        if (r10 >= r0) goto L_0x014c;
    L_0x0036:
        r0 = r22;
        r18 = r0.charAt(r10);
        r18 = java.lang.Character.isDigit(r18);
        if (r18 != 0) goto L_0x005e;
    L_0x0042:
        r0 = r22;
        r18 = r0.charAt(r10);
        r19 = 45;
        r0 = r18;
        r1 = r19;
        if (r0 == r1) goto L_0x005e;
    L_0x0050:
        r0 = r22;
        r18 = r0.charAt(r10);
        r19 = 36;
        r0 = r18;
        r1 = r19;
        if (r0 != r1) goto L_0x0119;
    L_0x005e:
        r15 = r15 + 9;
    L_0x0060:
        r10 = r10 + 1;
        goto L_0x002c;
    L_0x0063:
        r18 = 0;
        r0 = r22;
        r1 = r18;
        r18 = r0.charAt(r1);
        r9 = java.lang.Character.toUpperCase(r18);
        r18 = r22.length();
        r18 = r18 + -1;
        r0 = r22;
        r1 = r18;
        r18 = r0.charAt(r1);
        r12 = java.lang.Character.toUpperCase(r18);
        r18 = START_END_CHARS;
        r0 = r18;
        r17 = com.google.zxing.oned.CodaBarReader.arrayContains(r0, r9);
        r18 = START_END_CHARS;
        r0 = r18;
        r8 = com.google.zxing.oned.CodaBarReader.arrayContains(r0, r12);
        r18 = ALT_START_END_CHARS;
        r0 = r18;
        r16 = com.google.zxing.oned.CodaBarReader.arrayContains(r0, r9);
        r18 = ALT_START_END_CHARS;
        r0 = r18;
        r7 = com.google.zxing.oned.CodaBarReader.arrayContains(r0, r12);
        if (r17 == 0) goto L_0x00c0;
    L_0x00a5:
        if (r8 != 0) goto L_0x0029;
    L_0x00a7:
        r18 = new java.lang.IllegalArgumentException;
        r19 = new java.lang.StringBuilder;
        r20 = "Invalid start/end guards: ";
        r19.<init>(r20);
        r0 = r19;
        r1 = r22;
        r19 = r0.append(r1);
        r19 = r19.toString();
        r18.<init>(r19);
        throw r18;
    L_0x00c0:
        if (r16 == 0) goto L_0x00dd;
    L_0x00c2:
        if (r7 != 0) goto L_0x0029;
    L_0x00c4:
        r18 = new java.lang.IllegalArgumentException;
        r19 = new java.lang.StringBuilder;
        r20 = "Invalid start/end guards: ";
        r19.<init>(r20);
        r0 = r19;
        r1 = r22;
        r19 = r0.append(r1);
        r19 = r19.toString();
        r18.<init>(r19);
        throw r18;
    L_0x00dd:
        if (r8 != 0) goto L_0x00e1;
    L_0x00df:
        if (r7 == 0) goto L_0x00fa;
    L_0x00e1:
        r18 = new java.lang.IllegalArgumentException;
        r19 = new java.lang.StringBuilder;
        r20 = "Invalid start/end guards: ";
        r19.<init>(r20);
        r0 = r19;
        r1 = r22;
        r19 = r0.append(r1);
        r19 = r19.toString();
        r18.<init>(r19);
        throw r18;
    L_0x00fa:
        r18 = new java.lang.StringBuilder;
        r18.<init>();
        r19 = DEFAULT_GUARD;
        r18 = r18.append(r19);
        r0 = r18;
        r1 = r22;
        r18 = r0.append(r1);
        r19 = DEFAULT_GUARD;
        r18 = r18.append(r19);
        r22 = r18.toString();
        goto L_0x0029;
    L_0x0119:
        r18 = CHARS_WHICH_ARE_TEN_LENGTH_EACH_AFTER_DECODED;
        r0 = r22;
        r19 = r0.charAt(r10);
        r18 = com.google.zxing.oned.CodaBarReader.arrayContains(r18, r19);
        if (r18 == 0) goto L_0x012b;
    L_0x0127:
        r15 = r15 + 10;
        goto L_0x0060;
    L_0x012b:
        r18 = new java.lang.IllegalArgumentException;
        r19 = new java.lang.StringBuilder;
        r20 = "Cannot encode : '";
        r19.<init>(r20);
        r0 = r22;
        r20 = r0.charAt(r10);
        r19 = r19.append(r20);
        r20 = 39;
        r19 = r19.append(r20);
        r19 = r19.toString();
        r18.<init>(r19);
        throw r18;
    L_0x014c:
        r18 = r22.length();
        r18 = r18 + -1;
        r18 = r18 + r15;
        r0 = r18;
        r14 = new boolean[r0];
        r13 = 0;
        r11 = 0;
    L_0x015a:
        r18 = r22.length();
        r0 = r18;
        if (r11 >= r0) goto L_0x01de;
    L_0x0162:
        r0 = r22;
        r18 = r0.charAt(r11);
        r3 = java.lang.Character.toUpperCase(r18);
        if (r11 == 0) goto L_0x0178;
    L_0x016e:
        r18 = r22.length();
        r18 = r18 + -1;
        r0 = r18;
        if (r11 != r0) goto L_0x017b;
    L_0x0178:
        switch(r3) {
            case 42: goto L_0x01bc;
            case 69: goto L_0x01bf;
            case 78: goto L_0x01b9;
            case 84: goto L_0x01b6;
            default: goto L_0x017b;
        };
    L_0x017b:
        r4 = 0;
        r10 = 0;
    L_0x017d:
        r18 = com.google.zxing.oned.CodaBarReader.ALPHABET;
        r0 = r18;
        r0 = r0.length;
        r18 = r0;
        r0 = r18;
        if (r10 >= r0) goto L_0x0194;
    L_0x0188:
        r18 = com.google.zxing.oned.CodaBarReader.ALPHABET;
        r18 = r18[r10];
        r0 = r18;
        if (r3 != r0) goto L_0x01c2;
    L_0x0190:
        r18 = com.google.zxing.oned.CodaBarReader.CHARACTER_ENCODINGS;
        r4 = r18[r10];
    L_0x0194:
        r5 = 1;
        r6 = 0;
        r2 = 0;
    L_0x0197:
        r18 = 7;
        r0 = r18;
        if (r2 >= r0) goto L_0x01ca;
    L_0x019d:
        r14[r13] = r5;
        r13 = r13 + 1;
        r18 = 6 - r2;
        r18 = r4 >> r18;
        r18 = r18 & 1;
        if (r18 == 0) goto L_0x01af;
    L_0x01a9:
        r18 = 1;
        r0 = r18;
        if (r6 != r0) goto L_0x01c7;
    L_0x01af:
        if (r5 != 0) goto L_0x01c5;
    L_0x01b1:
        r5 = 1;
    L_0x01b2:
        r2 = r2 + 1;
        r6 = 0;
        goto L_0x0197;
    L_0x01b6:
        r3 = 65;
        goto L_0x017b;
    L_0x01b9:
        r3 = 66;
        goto L_0x017b;
    L_0x01bc:
        r3 = 67;
        goto L_0x017b;
    L_0x01bf:
        r3 = 68;
        goto L_0x017b;
    L_0x01c2:
        r10 = r10 + 1;
        goto L_0x017d;
    L_0x01c5:
        r5 = 0;
        goto L_0x01b2;
    L_0x01c7:
        r6 = r6 + 1;
        goto L_0x0197;
    L_0x01ca:
        r18 = r22.length();
        r18 = r18 + -1;
        r0 = r18;
        if (r11 >= r0) goto L_0x01da;
    L_0x01d4:
        r18 = 0;
        r14[r13] = r18;
        r13 = r13 + 1;
    L_0x01da:
        r11 = r11 + 1;
        goto L_0x015a;
    L_0x01de:
        return r14;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.CodaBarWriter.encode(java.lang.String):boolean[]");
    }
}
