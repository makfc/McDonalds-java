package com.google.zxing.pdf417.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.pdf417.PDF417Common;
import com.google.zxing.pdf417.PDF417ResultMetadata;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;

final class DecodedBitStreamParser {
    private static final Charset DEFAULT_ENCODING = Charset.forName("ISO-8859-1");
    private static final BigInteger[] EXP900;
    private static final char[] MIXED_CHARS = "0123456789&\r\t,:#-.$/+%*=^".toCharArray();
    private static final char[] PUNCT_CHARS = ";<>@[\\]_`~!\r\t,:\n-.$/\"|*()?{}'".toCharArray();

    private enum Mode {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }

    static {
        BigInteger[] bigIntegerArr = new BigInteger[16];
        EXP900 = bigIntegerArr;
        bigIntegerArr[0] = BigInteger.ONE;
        BigInteger nineHundred = BigInteger.valueOf(900);
        EXP900[1] = nineHundred;
        for (int i = 2; i < EXP900.length; i++) {
            EXP900[i] = EXP900[i - 1].multiply(nineHundred);
        }
    }

    private DecodedBitStreamParser() {
    }

    static DecoderResult decode(int[] codewords, String ecLevel) throws FormatException {
        StringBuilder result = new StringBuilder(codewords.length << 1);
        Charset encoding = DEFAULT_ENCODING;
        int codeIndex = 1 + 1;
        int code = codewords[1];
        PDF417ResultMetadata resultMetadata = new PDF417ResultMetadata();
        while (codeIndex < codewords[0]) {
            int codeIndex2;
            switch (code) {
                case 900:
                    codeIndex = textCompaction(codewords, codeIndex, result);
                    break;
                case 901:
                case 924:
                    codeIndex = byteCompaction(code, codewords, encoding, codeIndex, result);
                    break;
                case 902:
                    codeIndex = numericCompaction(codewords, codeIndex, result);
                    break;
                case 913:
                    codeIndex2 = codeIndex + 1;
                    result.append((char) codewords[codeIndex]);
                    codeIndex = codeIndex2;
                    break;
                case 922:
                case 923:
                    throw FormatException.getFormatInstance();
                case 925:
                    codeIndex++;
                    break;
                case 926:
                    codeIndex += 2;
                    break;
                case 927:
                    codeIndex2 = codeIndex + 1;
                    encoding = Charset.forName(CharacterSetECI.getCharacterSetECIByValue(codewords[codeIndex]).name());
                    codeIndex = codeIndex2;
                    break;
                case PDF417Common.MAX_CODEWORDS_IN_BARCODE /*928*/:
                    codeIndex = decodeMacroBlock(codewords, codeIndex, resultMetadata);
                    break;
                default:
                    codeIndex = textCompaction(codewords, codeIndex - 1, result);
                    break;
            }
            if (codeIndex < codewords.length) {
                codeIndex2 = codeIndex + 1;
                code = codewords[codeIndex];
                codeIndex = codeIndex2;
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (result.length() == 0) {
            throw FormatException.getFormatInstance();
        }
        DecoderResult decoderResult = new DecoderResult(null, result.toString(), null, ecLevel);
        decoderResult.setOther(resultMetadata);
        return decoderResult;
    }

    private static int decodeMacroBlock(int[] codewords, int codeIndex, PDF417ResultMetadata resultMetadata) throws FormatException {
        if (codeIndex + 2 > codewords[0]) {
            throw FormatException.getFormatInstance();
        }
        int[] segmentIndexArray = new int[2];
        int i = 0;
        while (i < 2) {
            segmentIndexArray[i] = codewords[codeIndex];
            i++;
            codeIndex++;
        }
        resultMetadata.setSegmentIndex(Integer.parseInt(decodeBase900toBase10(segmentIndexArray, 2)));
        StringBuilder fileId = new StringBuilder();
        codeIndex = textCompaction(codewords, codeIndex, fileId);
        resultMetadata.setFileId(fileId.toString());
        if (codewords[codeIndex] == 923) {
            codeIndex++;
            int[] additionalOptionCodeWords = new int[(codewords[0] - codeIndex)];
            int additionalOptionCodeWordsIndex = 0;
            boolean end = false;
            while (codeIndex < codewords[0] && !end) {
                int codeIndex2 = codeIndex + 1;
                int code = codewords[codeIndex];
                if (code < 900) {
                    int additionalOptionCodeWordsIndex2 = additionalOptionCodeWordsIndex + 1;
                    additionalOptionCodeWords[additionalOptionCodeWordsIndex] = code;
                    additionalOptionCodeWordsIndex = additionalOptionCodeWordsIndex2;
                    codeIndex = codeIndex2;
                } else {
                    switch (code) {
                        case 922:
                            resultMetadata.setLastSegment(true);
                            codeIndex = codeIndex2 + 1;
                            end = true;
                            break;
                        default:
                            throw FormatException.getFormatInstance();
                    }
                }
            }
            resultMetadata.setOptionalData(Arrays.copyOf(additionalOptionCodeWords, additionalOptionCodeWordsIndex));
            return codeIndex;
        } else if (codewords[codeIndex] != 922) {
            return codeIndex;
        } else {
            resultMetadata.setLastSegment(true);
            return codeIndex + 1;
        }
    }

    private static int textCompaction(int[] codewords, int codeIndex, StringBuilder result) {
        int[] textCompactionData = new int[((codewords[0] - codeIndex) << 1)];
        int[] byteCompactionData = new int[((codewords[0] - codeIndex) << 1)];
        int index = 0;
        boolean end = false;
        while (codeIndex < codewords[0] && !end) {
            int codeIndex2 = codeIndex + 1;
            int code = codewords[codeIndex];
            if (code >= 900) {
                switch (code) {
                    case 900:
                        int index2 = index + 1;
                        textCompactionData[index] = 900;
                        index = index2;
                        codeIndex = codeIndex2;
                        break;
                    case 901:
                    case 902:
                    case 922:
                    case 923:
                    case 924:
                    case PDF417Common.MAX_CODEWORDS_IN_BARCODE /*928*/:
                        codeIndex = codeIndex2 - 1;
                        end = true;
                        break;
                    case 913:
                        textCompactionData[index] = 913;
                        codeIndex = codeIndex2 + 1;
                        byteCompactionData[index] = codewords[codeIndex2];
                        index++;
                        break;
                    default:
                        codeIndex = codeIndex2;
                        break;
                }
            }
            textCompactionData[index] = code / 30;
            textCompactionData[index + 1] = code % 30;
            index += 2;
            codeIndex = codeIndex2;
        }
        decodeTextCompaction(textCompactionData, byteCompactionData, index, result);
        return codeIndex;
    }

    private static void decodeTextCompaction(int[] textCompactionData, int[] byteCompactionData, int length, StringBuilder result) {
        Mode subMode = Mode.ALPHA;
        Mode priorToShiftMode = Mode.ALPHA;
        for (int i = 0; i < length; i++) {
            int subModeCh = textCompactionData[i];
            char ch = 0;
            switch (subMode) {
                case ALPHA:
                    if (subModeCh >= 26) {
                        if (subModeCh != 26) {
                            if (subModeCh != 27) {
                                if (subModeCh != 28) {
                                    if (subModeCh != 29) {
                                        if (subModeCh != 913) {
                                            if (subModeCh == 900) {
                                                subMode = Mode.ALPHA;
                                                break;
                                            }
                                        }
                                        result.append((char) byteCompactionData[i]);
                                        break;
                                    }
                                    priorToShiftMode = subMode;
                                    subMode = Mode.PUNCT_SHIFT;
                                    break;
                                }
                                subMode = Mode.MIXED;
                                break;
                            }
                            subMode = Mode.LOWER;
                            break;
                        }
                        ch = SafeJsonPrimitive.NULL_CHAR;
                        break;
                    }
                    ch = (char) (subModeCh + 65);
                    break;
                    break;
                case LOWER:
                    if (subModeCh >= 26) {
                        if (subModeCh != 26) {
                            if (subModeCh != 27) {
                                if (subModeCh != 28) {
                                    if (subModeCh != 29) {
                                        if (subModeCh != 913) {
                                            if (subModeCh == 900) {
                                                subMode = Mode.ALPHA;
                                                break;
                                            }
                                        }
                                        result.append((char) byteCompactionData[i]);
                                        break;
                                    }
                                    priorToShiftMode = subMode;
                                    subMode = Mode.PUNCT_SHIFT;
                                    break;
                                }
                                subMode = Mode.MIXED;
                                break;
                            }
                            priorToShiftMode = subMode;
                            subMode = Mode.ALPHA_SHIFT;
                            break;
                        }
                        ch = SafeJsonPrimitive.NULL_CHAR;
                        break;
                    }
                    ch = (char) (subModeCh + 97);
                    break;
                    break;
                case MIXED:
                    if (subModeCh >= 25) {
                        if (subModeCh != 25) {
                            if (subModeCh != 26) {
                                if (subModeCh != 27) {
                                    if (subModeCh != 28) {
                                        if (subModeCh != 29) {
                                            if (subModeCh != 913) {
                                                if (subModeCh == 900) {
                                                    subMode = Mode.ALPHA;
                                                    break;
                                                }
                                            }
                                            result.append((char) byteCompactionData[i]);
                                            break;
                                        }
                                        priorToShiftMode = subMode;
                                        subMode = Mode.PUNCT_SHIFT;
                                        break;
                                    }
                                    subMode = Mode.ALPHA;
                                    break;
                                }
                                subMode = Mode.LOWER;
                                break;
                            }
                            ch = SafeJsonPrimitive.NULL_CHAR;
                            break;
                        }
                        subMode = Mode.PUNCT;
                        break;
                    }
                    ch = MIXED_CHARS[subModeCh];
                    break;
                    break;
                case PUNCT:
                    if (subModeCh >= 29) {
                        if (subModeCh != 29) {
                            if (subModeCh != 913) {
                                if (subModeCh == 900) {
                                    subMode = Mode.ALPHA;
                                    break;
                                }
                            }
                            result.append((char) byteCompactionData[i]);
                            break;
                        }
                        subMode = Mode.ALPHA;
                        break;
                    }
                    ch = PUNCT_CHARS[subModeCh];
                    break;
                    break;
                case ALPHA_SHIFT:
                    subMode = priorToShiftMode;
                    if (subModeCh >= 26) {
                        if (subModeCh != 26) {
                            if (subModeCh == 900) {
                                subMode = Mode.ALPHA;
                                break;
                            }
                        }
                        ch = SafeJsonPrimitive.NULL_CHAR;
                        break;
                    }
                    ch = (char) (subModeCh + 65);
                    break;
                    break;
                case PUNCT_SHIFT:
                    subMode = priorToShiftMode;
                    if (subModeCh >= 29) {
                        if (subModeCh != 29) {
                            if (subModeCh != 913) {
                                if (subModeCh == 900) {
                                    subMode = Mode.ALPHA;
                                    break;
                                }
                            }
                            result.append((char) byteCompactionData[i]);
                            break;
                        }
                        subMode = Mode.ALPHA;
                        break;
                    }
                    ch = PUNCT_CHARS[subModeCh];
                    break;
                    break;
            }
            if (ch != 0) {
                result.append(ch);
            }
        }
    }

    private static int byteCompaction(int mode, int[] codewords, Charset encoding, int codeIndex, StringBuilder result) {
        ByteArrayOutputStream decodedBytes = new ByteArrayOutputStream();
        int count;
        long value;
        boolean end;
        int codeIndex2;
        int j;
        if (mode == 901) {
            int count2;
            count = 0;
            value = 0;
            int[] byteCompactedCodewords = new int[6];
            end = false;
            codeIndex2 = codeIndex + 1;
            int nextCode = codewords[codeIndex];
            codeIndex = codeIndex2;
            while (codeIndex < codewords[0] && !end) {
                count2 = count + 1;
                byteCompactedCodewords[count] = nextCode;
                value = (900 * value) + ((long) nextCode);
                codeIndex2 = codeIndex + 1;
                nextCode = codewords[codeIndex];
                if (nextCode == 900 || nextCode == 901 || nextCode == 902 || nextCode == 924 || nextCode == PDF417Common.MAX_CODEWORDS_IN_BARCODE || nextCode == 923 || nextCode == 922) {
                    codeIndex = codeIndex2 - 1;
                    end = true;
                    count = count2;
                } else if (count2 % 5 != 0 || count2 <= 0) {
                    count = count2;
                    codeIndex = codeIndex2;
                } else {
                    for (j = 0; j < 6; j++) {
                        decodedBytes.write((byte) ((int) (value >> ((5 - j) * 8))));
                    }
                    value = 0;
                    count = 0;
                    codeIndex = codeIndex2;
                }
            }
            if (codeIndex == codewords[0] && nextCode < 900) {
                count2 = count + 1;
                byteCompactedCodewords[count] = nextCode;
                count = count2;
            }
            for (int i = 0; i < count; i++) {
                decodedBytes.write((byte) byteCompactedCodewords[i]);
            }
        } else if (mode == 924) {
            count = 0;
            value = 0;
            end = false;
            while (codeIndex < codewords[0] && !end) {
                codeIndex2 = codeIndex + 1;
                int code = codewords[codeIndex];
                if (code < 900) {
                    count++;
                    value = (900 * value) + ((long) code);
                    codeIndex = codeIndex2;
                } else if (code == 900 || code == 901 || code == 902 || code == 924 || code == PDF417Common.MAX_CODEWORDS_IN_BARCODE || code == 923 || code == 922) {
                    codeIndex = codeIndex2 - 1;
                    end = true;
                } else {
                    codeIndex = codeIndex2;
                }
                if (count % 5 == 0 && count > 0) {
                    for (j = 0; j < 6; j++) {
                        decodedBytes.write((byte) ((int) (value >> ((5 - j) * 8))));
                    }
                    value = 0;
                    count = 0;
                }
            }
        }
        result.append(new String(decodedBytes.toByteArray(), encoding));
        return codeIndex;
    }

    private static int numericCompaction(int[] codewords, int codeIndex, StringBuilder result) throws FormatException {
        int count = 0;
        boolean end = false;
        int[] numericCodewords = new int[15];
        while (codeIndex < codewords[0] && !end) {
            int codeIndex2 = codeIndex + 1;
            int code = codewords[codeIndex];
            if (codeIndex2 == codewords[0]) {
                end = true;
            }
            if (code < 900) {
                numericCodewords[count] = code;
                count++;
                codeIndex = codeIndex2;
            } else if (code == 900 || code == 901 || code == 924 || code == PDF417Common.MAX_CODEWORDS_IN_BARCODE || code == 923 || code == 922) {
                codeIndex = codeIndex2 - 1;
                end = true;
            } else {
                codeIndex = codeIndex2;
            }
            if ((count % 15 == 0 || code == 902 || end) && count > 0) {
                result.append(decodeBase900toBase10(numericCodewords, count));
                count = 0;
            }
        }
        return codeIndex;
    }

    private static String decodeBase900toBase10(int[] codewords, int count) throws FormatException {
        BigInteger result = BigInteger.ZERO;
        for (int i = 0; i < count; i++) {
            result = result.add(EXP900[(count - i) - 1].multiply(BigInteger.valueOf((long) codewords[i])));
        }
        String resultString = result.toString();
        if (resultString.charAt(0) == '1') {
            return resultString.substring(1);
        }
        throw FormatException.getFormatInstance();
    }
}
