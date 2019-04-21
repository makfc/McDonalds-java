package com.google.zxing.qrcode.encoder;

import com.google.zxing.WriterException;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.Version;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;

public final class Encoder {
    private static final int[] ALPHANUMERIC_TABLE = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};
    static final String DEFAULT_BYTE_MODE_ENCODING = "ISO-8859-1";

    private Encoder() {
    }

    private static int calculateMaskPenalty(ByteMatrix matrix) {
        return ((MaskUtil.applyMaskPenaltyRule1(matrix) + MaskUtil.applyMaskPenaltyRule2(matrix)) + MaskUtil.applyMaskPenaltyRule3(matrix)) + MaskUtil.applyMaskPenaltyRule4(matrix);
    }

    public static QRCode encode(String content, ErrorCorrectionLevel ecLevel) throws WriterException {
        return encode(content, ecLevel, null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x008d  */
    public static com.google.zxing.qrcode.encoder.QRCode encode(java.lang.String r17, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel r18, java.util.Map<com.google.zxing.EncodeHintType, ?> r19) throws com.google.zxing.WriterException {
        /*
        r5 = "ISO-8859-1";
        if (r19 == 0) goto L_0x001a;
    L_0x0004:
        r15 = com.google.zxing.EncodeHintType.CHARACTER_SET;
        r0 = r19;
        r15 = r0.containsKey(r15);
        if (r15 == 0) goto L_0x001a;
    L_0x000e:
        r15 = com.google.zxing.EncodeHintType.CHARACTER_SET;
        r0 = r19;
        r15 = r0.get(r15);
        r5 = r15.toString();
    L_0x001a:
        r0 = r17;
        r11 = chooseMode(r0, r5);
        r8 = new com.google.zxing.common.BitArray;
        r8.<init>();
        r15 = com.google.zxing.qrcode.decoder.Mode.BYTE;
        if (r11 != r15) goto L_0x003a;
    L_0x0029:
        r15 = "ISO-8859-1";
        r15 = r15.equals(r5);
        if (r15 != 0) goto L_0x003a;
    L_0x0031:
        r4 = com.google.zxing.common.CharacterSetECI.getCharacterSetECIByName(r5);
        if (r4 == 0) goto L_0x003a;
    L_0x0037:
        appendECI(r4, r8);
    L_0x003a:
        appendModeInfo(r11, r8);
        r1 = new com.google.zxing.common.BitArray;
        r1.<init>();
        r0 = r17;
        appendBytes(r0, r11, r1, r5);
        if (r19 == 0) goto L_0x007b;
    L_0x0049:
        r15 = com.google.zxing.EncodeHintType.QR_VERSION;
        r0 = r19;
        r15 = r0.containsKey(r15);
        if (r15 == 0) goto L_0x007b;
    L_0x0053:
        r15 = com.google.zxing.EncodeHintType.QR_VERSION;
        r0 = r19;
        r15 = r0.get(r15);
        r15 = r15.toString();
        r15 = java.lang.Integer.parseInt(r15);
        r14 = com.google.zxing.qrcode.decoder.Version.getVersionForNumber(r15);
        r15 = calculateBitsNeeded(r11, r8, r1, r14);
        r0 = r18;
        r15 = willFit(r15, r14, r0);
        if (r15 != 0) goto L_0x0081;
    L_0x0073:
        r15 = new com.google.zxing.WriterException;
        r16 = "Data too big for requested version";
        r15.<init>(r16);
        throw r15;
    L_0x007b:
        r0 = r18;
        r14 = recommendVersion(r0, r11, r8, r1);
    L_0x0081:
        r7 = new com.google.zxing.common.BitArray;
        r7.<init>();
        r7.appendBitArray(r8);
        r15 = com.google.zxing.qrcode.decoder.Mode.BYTE;
        if (r11 != r15) goto L_0x00e3;
    L_0x008d:
        r15 = r1.getSizeInBytes();
    L_0x0091:
        appendLengthInfo(r15, r14, r11, r7);
        r7.appendBitArray(r1);
        r0 = r18;
        r3 = r14.getECBlocksForLevel(r0);
        r15 = r14.getTotalCodewords();
        r16 = r3.getTotalECCodewords();
        r12 = r15 - r16;
        terminateBits(r12, r7);
        r15 = r14.getTotalCodewords();
        r16 = r3.getNumBlocks();
        r0 = r16;
        r6 = interleaveWithECBytes(r7, r15, r12, r0);
        r13 = new com.google.zxing.qrcode.encoder.QRCode;
        r13.<init>();
        r0 = r18;
        r13.setECLevel(r0);
        r13.setMode(r11);
        r13.setVersion(r14);
        r2 = r14.getDimensionForVersion();
        r10 = new com.google.zxing.qrcode.encoder.ByteMatrix;
        r10.<init>(r2, r2);
        r0 = r18;
        r9 = chooseMaskPattern(r6, r0, r14, r10);
        r13.setMaskPattern(r9);
        r0 = r18;
        com.google.zxing.qrcode.encoder.MatrixUtil.buildMatrix(r6, r0, r14, r9, r10);
        r13.setMatrix(r10);
        return r13;
    L_0x00e3:
        r15 = r17.length();
        goto L_0x0091;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.encoder.Encoder.encode(java.lang.String, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel, java.util.Map):com.google.zxing.qrcode.encoder.QRCode");
    }

    private static Version recommendVersion(ErrorCorrectionLevel ecLevel, Mode mode, BitArray headerBits, BitArray dataBits) throws WriterException {
        return chooseVersion(calculateBitsNeeded(mode, headerBits, dataBits, chooseVersion(calculateBitsNeeded(mode, headerBits, dataBits, Version.getVersionForNumber(1)), ecLevel)), ecLevel);
    }

    private static int calculateBitsNeeded(Mode mode, BitArray headerBits, BitArray dataBits, Version version) {
        return (headerBits.getSize() + mode.getCharacterCountBits(version)) + dataBits.getSize();
    }

    static int getAlphanumericCode(int code) {
        if (code < ALPHANUMERIC_TABLE.length) {
            return ALPHANUMERIC_TABLE[code];
        }
        return -1;
    }

    public static Mode chooseMode(String content) {
        return chooseMode(content, null);
    }

    private static Mode chooseMode(String content, String encoding) {
        if ("Shift_JIS".equals(encoding) && isOnlyDoubleByteKanji(content)) {
            return Mode.KANJI;
        }
        boolean hasNumeric = false;
        boolean hasAlphanumeric = false;
        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            if (c >= '0' && c <= '9') {
                hasNumeric = true;
            } else if (getAlphanumericCode(c) == -1) {
                return Mode.BYTE;
            } else {
                hasAlphanumeric = true;
            }
        }
        if (hasAlphanumeric) {
            return Mode.ALPHANUMERIC;
        }
        if (hasNumeric) {
            return Mode.NUMERIC;
        }
        return Mode.BYTE;
    }

    private static boolean isOnlyDoubleByteKanji(String content) {
        try {
            byte[] bytes = content.getBytes("Shift_JIS");
            int length = bytes.length;
            if (length % 2 != 0) {
                return false;
            }
            for (int i = 0; i < length; i += 2) {
                int byte1 = bytes[i] & 255;
                if ((byte1 < 129 || byte1 > 159) && (byte1 < 224 || byte1 > 235)) {
                    return false;
                }
            }
            return true;
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }

    private static int chooseMaskPattern(BitArray bits, ErrorCorrectionLevel ecLevel, Version version, ByteMatrix matrix) throws WriterException {
        int minPenalty = Integer.MAX_VALUE;
        int bestMaskPattern = -1;
        for (int maskPattern = 0; maskPattern < 8; maskPattern++) {
            MatrixUtil.buildMatrix(bits, ecLevel, version, maskPattern, matrix);
            int penalty = calculateMaskPenalty(matrix);
            if (penalty < minPenalty) {
                minPenalty = penalty;
                bestMaskPattern = maskPattern;
            }
        }
        return bestMaskPattern;
    }

    private static Version chooseVersion(int numInputBits, ErrorCorrectionLevel ecLevel) throws WriterException {
        for (int versionNum = 1; versionNum <= 40; versionNum++) {
            Version version = Version.getVersionForNumber(versionNum);
            if (willFit(numInputBits, version, ecLevel)) {
                return version;
            }
        }
        throw new WriterException("Data too big");
    }

    private static boolean willFit(int numInputBits, Version version, ErrorCorrectionLevel ecLevel) {
        return version.getTotalCodewords() - version.getECBlocksForLevel(ecLevel).getTotalECCodewords() >= (numInputBits + 7) / 8;
    }

    static void terminateBits(int numDataBytes, BitArray bits) throws WriterException {
        int capacity = numDataBytes << 3;
        if (bits.getSize() > capacity) {
            throw new WriterException("data bits cannot fit in the QR Code" + bits.getSize() + " > " + capacity);
        }
        int i;
        for (i = 0; i < 4 && bits.getSize() < capacity; i++) {
            bits.appendBit(false);
        }
        int numBitsInLastByte = bits.getSize() & 7;
        if (numBitsInLastByte > 0) {
            for (i = numBitsInLastByte; i < 8; i++) {
                bits.appendBit(false);
            }
        }
        int numPaddingBytes = numDataBytes - bits.getSizeInBytes();
        for (i = 0; i < numPaddingBytes; i++) {
            bits.appendBits((i & 1) == 0 ? 236 : 17, 8);
        }
        if (bits.getSize() != capacity) {
            throw new WriterException("Bits size does not equal capacity");
        }
    }

    static void getNumDataBytesAndNumECBytesForBlockID(int numTotalBytes, int numDataBytes, int numRSBlocks, int blockID, int[] numDataBytesInBlock, int[] numECBytesInBlock) throws WriterException {
        if (blockID >= numRSBlocks) {
            throw new WriterException("Block ID too large");
        }
        int numRsBlocksInGroup2 = numTotalBytes % numRSBlocks;
        int numRsBlocksInGroup1 = numRSBlocks - numRsBlocksInGroup2;
        int numTotalBytesInGroup1 = numTotalBytes / numRSBlocks;
        int numDataBytesInGroup1 = numDataBytes / numRSBlocks;
        int numDataBytesInGroup2 = numDataBytesInGroup1 + 1;
        int numEcBytesInGroup1 = numTotalBytesInGroup1 - numDataBytesInGroup1;
        int numEcBytesInGroup2 = (numTotalBytesInGroup1 + 1) - numDataBytesInGroup2;
        if (numEcBytesInGroup1 != numEcBytesInGroup2) {
            throw new WriterException("EC bytes mismatch");
        } else if (numRSBlocks != numRsBlocksInGroup1 + numRsBlocksInGroup2) {
            throw new WriterException("RS blocks mismatch");
        } else if (numTotalBytes != ((numDataBytesInGroup1 + numEcBytesInGroup1) * numRsBlocksInGroup1) + ((numDataBytesInGroup2 + numEcBytesInGroup2) * numRsBlocksInGroup2)) {
            throw new WriterException("Total bytes mismatch");
        } else if (blockID < numRsBlocksInGroup1) {
            numDataBytesInBlock[0] = numDataBytesInGroup1;
            numECBytesInBlock[0] = numEcBytesInGroup1;
        } else {
            numDataBytesInBlock[0] = numDataBytesInGroup2;
            numECBytesInBlock[0] = numEcBytesInGroup2;
        }
    }

    static BitArray interleaveWithECBytes(BitArray bits, int numTotalBytes, int numDataBytes, int numRSBlocks) throws WriterException {
        if (bits.getSizeInBytes() != numDataBytes) {
            throw new WriterException("Number of bits and data bytes does not match");
        }
        int i;
        byte[] dataBytes;
        byte[] ecBytes;
        int dataBytesOffset = 0;
        int maxNumDataBytes = 0;
        int maxNumEcBytes = 0;
        Collection<BlockPair> blocks = new ArrayList(numRSBlocks);
        for (i = 0; i < numRSBlocks; i++) {
            int[] numDataBytesInBlock = new int[1];
            int[] numEcBytesInBlock = new int[1];
            getNumDataBytesAndNumECBytesForBlockID(numTotalBytes, numDataBytes, numRSBlocks, i, numDataBytesInBlock, numEcBytesInBlock);
            int size = numDataBytesInBlock[0];
            dataBytes = new byte[size];
            bits.toBytes(dataBytesOffset << 3, dataBytes, 0, size);
            ecBytes = generateECBytes(dataBytes, numEcBytesInBlock[0]);
            blocks.add(new BlockPair(dataBytes, ecBytes));
            maxNumDataBytes = Math.max(maxNumDataBytes, size);
            maxNumEcBytes = Math.max(maxNumEcBytes, ecBytes.length);
            dataBytesOffset += numDataBytesInBlock[0];
        }
        if (numDataBytes != dataBytesOffset) {
            throw new WriterException("Data bytes does not match offset");
        }
        BitArray result = new BitArray();
        for (i = 0; i < maxNumDataBytes; i++) {
            for (BlockPair dataBytes2 : blocks) {
                dataBytes = dataBytes2.getDataBytes();
                if (i < dataBytes.length) {
                    result.appendBits(dataBytes[i], 8);
                }
            }
        }
        for (i = 0; i < maxNumEcBytes; i++) {
            for (BlockPair dataBytes22 : blocks) {
                ecBytes = dataBytes22.getErrorCorrectionBytes();
                if (i < ecBytes.length) {
                    result.appendBits(ecBytes[i], 8);
                }
            }
        }
        if (numTotalBytes == result.getSizeInBytes()) {
            return result;
        }
        throw new WriterException("Interleaving error: " + numTotalBytes + " and " + result.getSizeInBytes() + " differ.");
    }

    static byte[] generateECBytes(byte[] dataBytes, int numEcBytesInBlock) {
        int i;
        int numDataBytes = dataBytes.length;
        int[] toEncode = new int[(numDataBytes + numEcBytesInBlock)];
        for (i = 0; i < numDataBytes; i++) {
            toEncode[i] = dataBytes[i] & 255;
        }
        new ReedSolomonEncoder(GenericGF.QR_CODE_FIELD_256).encode(toEncode, numEcBytesInBlock);
        byte[] ecBytes = new byte[numEcBytesInBlock];
        for (i = 0; i < numEcBytesInBlock; i++) {
            ecBytes[i] = (byte) toEncode[numDataBytes + i];
        }
        return ecBytes;
    }

    static void appendModeInfo(Mode mode, BitArray bits) {
        bits.appendBits(mode.getBits(), 4);
    }

    static void appendLengthInfo(int numLetters, Version version, Mode mode, BitArray bits) throws WriterException {
        int numBits = mode.getCharacterCountBits(version);
        if (numLetters >= (1 << numBits)) {
            throw new WriterException(numLetters + " is bigger than " + ((1 << numBits) - 1));
        }
        bits.appendBits(numLetters, numBits);
    }

    static void appendBytes(String content, Mode mode, BitArray bits, String encoding) throws WriterException {
        switch (mode) {
            case NUMERIC:
                appendNumericBytes(content, bits);
                return;
            case ALPHANUMERIC:
                appendAlphanumericBytes(content, bits);
                return;
            case BYTE:
                append8BitBytes(content, bits, encoding);
                return;
            case KANJI:
                appendKanjiBytes(content, bits);
                return;
            default:
                throw new WriterException("Invalid mode: " + mode);
        }
    }

    static void appendNumericBytes(CharSequence content, BitArray bits) {
        int length = content.length();
        int i = 0;
        while (i < length) {
            int num1 = content.charAt(i) - 48;
            if (i + 2 < length) {
                int num3 = content.charAt(i + 2) - 48;
                bits.appendBits(((num1 * 100) + ((content.charAt(i + 1) - 48) * 10)) + num3, 10);
                i += 3;
            } else if (i + 1 < length) {
                bits.appendBits((num1 * 10) + (content.charAt(i + 1) - 48), 7);
                i += 2;
            } else {
                bits.appendBits(num1, 4);
                i++;
            }
        }
    }

    static void appendAlphanumericBytes(CharSequence content, BitArray bits) throws WriterException {
        int length = content.length();
        int i = 0;
        while (i < length) {
            int code1 = getAlphanumericCode(content.charAt(i));
            if (code1 == -1) {
                throw new WriterException();
            } else if (i + 1 < length) {
                int code2 = getAlphanumericCode(content.charAt(i + 1));
                if (code2 == -1) {
                    throw new WriterException();
                }
                bits.appendBits((code1 * 45) + code2, 11);
                i += 2;
            } else {
                bits.appendBits(code1, 6);
                i++;
            }
        }
    }

    static void append8BitBytes(String content, BitArray bits, String encoding) throws WriterException {
        try {
            for (byte b : content.getBytes(encoding)) {
                bits.appendBits(b, 8);
            }
        } catch (UnsupportedEncodingException uee) {
            throw new WriterException(uee);
        }
    }

    static void appendKanjiBytes(String content, BitArray bits) throws WriterException {
        try {
            byte[] bytes = content.getBytes("Shift_JIS");
            int length = bytes.length;
            for (int i = 0; i < length; i += 2) {
                int byte2 = bytes[i + 1] & 255;
                int code = ((bytes[i] & 255) << 8) | byte2;
                int subtracted = -1;
                if (code >= 33088 && code <= 40956) {
                    subtracted = code - 33088;
                } else if (code >= 57408 && code <= 60351) {
                    subtracted = code - 49472;
                }
                if (subtracted == -1) {
                    throw new WriterException("Invalid byte sequence");
                }
                bits.appendBits(((subtracted >> 8) * 192) + (subtracted & 255), 13);
            }
        } catch (UnsupportedEncodingException uee) {
            throw new WriterException(uee);
        }
    }

    private static void appendECI(CharacterSetECI eci, BitArray bits) {
        bits.appendBits(Mode.ECI.getBits(), 4);
        bits.appendBits(eci.getValue(), 8);
    }
}
