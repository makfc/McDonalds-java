package com.google.zxing.pdf417.detector;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class Detector {
    private static final int BARCODE_MIN_HEIGHT = 10;
    private static final int[] INDEXES_START_PATTERN = new int[]{0, 4, 1, 5};
    private static final int[] INDEXES_STOP_PATTERN = new int[]{6, 2, 7, 3};
    private static final float MAX_AVG_VARIANCE = 0.42f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.8f;
    private static final int MAX_PATTERN_DRIFT = 5;
    private static final int MAX_PIXEL_DRIFT = 3;
    private static final int ROW_STEP = 5;
    private static final int SKIPPED_ROW_COUNT_MAX = 25;
    private static final int[] START_PATTERN = new int[]{8, 1, 1, 1, 1, 1, 1, 3};
    private static final int[] STOP_PATTERN = new int[]{7, 1, 1, 3, 1, 1, 1, 2, 1};

    private Detector() {
    }

    public static PDF417DetectorResult detect(BinaryBitmap image, Map<DecodeHintType, ?> map, boolean multiple) throws NotFoundException {
        BitMatrix bitMatrix = image.getBlackMatrix();
        List<ResultPoint[]> barcodeCoordinates = detect(multiple, bitMatrix);
        if (barcodeCoordinates.isEmpty()) {
            bitMatrix = bitMatrix.clone();
            bitMatrix.rotate180();
            barcodeCoordinates = detect(multiple, bitMatrix);
        }
        return new PDF417DetectorResult(bitMatrix, barcodeCoordinates);
    }

    private static List<ResultPoint[]> detect(boolean multiple, BitMatrix bitMatrix) {
        List<ResultPoint[]> barcodeCoordinates = new ArrayList();
        int row = 0;
        int column = 0;
        boolean foundBarcodeInRow = false;
        while (row < bitMatrix.getHeight()) {
            ResultPoint[] vertices = findVertices(bitMatrix, row, column);
            if (vertices[0] == null && vertices[3] == null) {
                if (!foundBarcodeInRow) {
                    break;
                }
                foundBarcodeInRow = false;
                column = 0;
                for (ResultPoint[] barcodeCoordinate : barcodeCoordinates) {
                    if (barcodeCoordinate[1] != null) {
                        row = (int) Math.max((float) row, barcodeCoordinate[1].getY());
                    }
                    if (barcodeCoordinate[3] != null) {
                        row = Math.max(row, (int) barcodeCoordinate[3].getY());
                    }
                }
                row += 5;
            } else {
                foundBarcodeInRow = true;
                barcodeCoordinates.add(vertices);
                if (!multiple) {
                    break;
                } else if (vertices[2] != null) {
                    column = (int) vertices[2].getX();
                    row = (int) vertices[2].getY();
                } else {
                    column = (int) vertices[4].getX();
                    row = (int) vertices[4].getY();
                }
            }
        }
        return barcodeCoordinates;
    }

    private static ResultPoint[] findVertices(BitMatrix matrix, int startRow, int startColumn) {
        int height = matrix.getHeight();
        int width = matrix.getWidth();
        ResultPoint[] result = new ResultPoint[8];
        copyToResult(result, findRowsWithPattern(matrix, height, width, startRow, startColumn, START_PATTERN), INDEXES_START_PATTERN);
        if (result[4] != null) {
            startColumn = (int) result[4].getX();
            startRow = (int) result[4].getY();
        }
        copyToResult(result, findRowsWithPattern(matrix, height, width, startRow, startColumn, STOP_PATTERN), INDEXES_STOP_PATTERN);
        return result;
    }

    private static void copyToResult(ResultPoint[] result, ResultPoint[] tmpResult, int[] destinationIndexes) {
        for (int i = 0; i < destinationIndexes.length; i++) {
            result[destinationIndexes[i]] = tmpResult[i];
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00d2  */
    private static com.google.zxing.ResultPoint[] findRowsWithPattern(com.google.zxing.common.BitMatrix r14, int r15, int r16, int r17, int r18, int[] r19) {
        /*
        r1 = 4;
        r12 = new com.google.zxing.ResultPoint[r1];
        r8 = 0;
        r0 = r19;
        r1 = r0.length;
        r7 = new int[r1];
    L_0x0009:
        r0 = r17;
        if (r0 >= r15) goto L_0x0054;
    L_0x000d:
        r5 = 0;
        r1 = r14;
        r2 = r18;
        r3 = r17;
        r4 = r16;
        r6 = r19;
        r10 = findGuardPattern(r1, r2, r3, r4, r5, r6, r7);
        if (r10 == 0) goto L_0x00a4;
    L_0x001d:
        if (r17 <= 0) goto L_0x0035;
    L_0x001f:
        r17 = r17 + -1;
        r5 = 0;
        r1 = r14;
        r2 = r18;
        r3 = r17;
        r4 = r16;
        r6 = r19;
        r11 = findGuardPattern(r1, r2, r3, r4, r5, r6, r7);
        if (r11 == 0) goto L_0x0033;
    L_0x0031:
        r10 = r11;
        goto L_0x001d;
    L_0x0033:
        r17 = r17 + 1;
    L_0x0035:
        r1 = 0;
        r2 = new com.google.zxing.ResultPoint;
        r4 = 0;
        r4 = r10[r4];
        r4 = (float) r4;
        r0 = r17;
        r5 = (float) r0;
        r2.<init>(r4, r5);
        r12[r1] = r2;
        r1 = 1;
        r2 = new com.google.zxing.ResultPoint;
        r4 = 1;
        r4 = r10[r4];
        r4 = (float) r4;
        r0 = r17;
        r5 = (float) r0;
        r2.<init>(r4, r5);
        r12[r1] = r2;
        r8 = 1;
    L_0x0054:
        r3 = r17 + 1;
        if (r8 == 0) goto L_0x00cc;
    L_0x0058:
        r13 = 0;
        r1 = 2;
        r11 = new int[r1];
        r1 = 0;
        r2 = 0;
        r2 = r12[r2];
        r2 = r2.getX();
        r2 = (int) r2;
        r11[r1] = r2;
        r1 = 1;
        r2 = 1;
        r2 = r12[r2];
        r2 = r2.getX();
        r2 = (int) r2;
        r11[r1] = r2;
    L_0x0072:
        if (r3 >= r15) goto L_0x00af;
    L_0x0074:
        r1 = 0;
        r2 = r11[r1];
        r5 = 0;
        r1 = r14;
        r4 = r16;
        r6 = r19;
        r10 = findGuardPattern(r1, r2, r3, r4, r5, r6, r7);
        if (r10 == 0) goto L_0x00a8;
    L_0x0083:
        r1 = 0;
        r1 = r11[r1];
        r2 = 0;
        r2 = r10[r2];
        r1 = r1 - r2;
        r1 = java.lang.Math.abs(r1);
        r2 = 5;
        if (r1 >= r2) goto L_0x00a8;
    L_0x0091:
        r1 = 1;
        r1 = r11[r1];
        r2 = 1;
        r2 = r10[r2];
        r1 = r1 - r2;
        r1 = java.lang.Math.abs(r1);
        r2 = 5;
        if (r1 >= r2) goto L_0x00a8;
    L_0x009f:
        r11 = r10;
        r13 = 0;
    L_0x00a1:
        r3 = r3 + 1;
        goto L_0x0072;
    L_0x00a4:
        r17 = r17 + 5;
        goto L_0x0009;
    L_0x00a8:
        r1 = 25;
        if (r13 > r1) goto L_0x00af;
    L_0x00ac:
        r13 = r13 + 1;
        goto L_0x00a1;
    L_0x00af:
        r1 = r13 + 1;
        r3 = r3 - r1;
        r1 = 2;
        r2 = new com.google.zxing.ResultPoint;
        r4 = 0;
        r4 = r11[r4];
        r4 = (float) r4;
        r5 = (float) r3;
        r2.<init>(r4, r5);
        r12[r1] = r2;
        r1 = 3;
        r2 = new com.google.zxing.ResultPoint;
        r4 = 1;
        r4 = r11[r4];
        r4 = (float) r4;
        r5 = (float) r3;
        r2.<init>(r4, r5);
        r12[r1] = r2;
    L_0x00cc:
        r1 = r3 - r17;
        r2 = 10;
        if (r1 >= r2) goto L_0x00dc;
    L_0x00d2:
        r9 = 0;
    L_0x00d3:
        r1 = 4;
        if (r9 >= r1) goto L_0x00dc;
    L_0x00d6:
        r1 = 0;
        r12[r9] = r1;
        r9 = r9 + 1;
        goto L_0x00d3;
    L_0x00dc:
        return r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.detector.Detector.findRowsWithPattern(com.google.zxing.common.BitMatrix, int, int, int, int, int[]):com.google.zxing.ResultPoint[]");
    }

    private static int[] findGuardPattern(BitMatrix matrix, int column, int row, int width, boolean whiteFirst, int[] pattern, int[] counters) {
        int x;
        Arrays.fill(counters, 0, counters.length, 0);
        int patternStart = column;
        int pixelDrift = 0;
        while (matrix.get(patternStart, row) && patternStart > 0) {
            int pixelDrift2 = pixelDrift + 1;
            if (pixelDrift >= 3) {
                break;
            }
            patternStart--;
            pixelDrift = pixelDrift2;
        }
        int counterPosition = 0;
        int patternLength = pattern.length;
        boolean isWhite = whiteFirst;
        for (x = patternStart; x < width; x++) {
            if ((matrix.get(x, row) ^ isWhite) != 0) {
                counters[counterPosition] = counters[counterPosition] + 1;
            } else {
                if (counterPosition != patternLength - 1) {
                    counterPosition++;
                } else if (patternMatchVariance(counters, pattern, MAX_INDIVIDUAL_VARIANCE) < MAX_AVG_VARIANCE) {
                    return new int[]{patternStart, x};
                } else {
                    patternStart += counters[0] + counters[1];
                    System.arraycopy(counters, 2, counters, 0, patternLength - 2);
                    counters[patternLength - 2] = 0;
                    counters[patternLength - 1] = 0;
                    counterPosition--;
                }
                counters[counterPosition] = 1;
                isWhite = !isWhite;
            }
        }
        if (counterPosition != patternLength - 1 || patternMatchVariance(counters, pattern, MAX_INDIVIDUAL_VARIANCE) >= MAX_AVG_VARIANCE) {
            return null;
        }
        return new int[]{patternStart, x - 1};
    }

    private static float patternMatchVariance(int[] counters, int[] pattern, float maxIndividualVariance) {
        int numCounters = counters.length;
        int total = 0;
        int patternLength = 0;
        for (int i = 0; i < numCounters; i++) {
            total += counters[i];
            patternLength += pattern[i];
        }
        if (total < patternLength) {
            return Float.POSITIVE_INFINITY;
        }
        float unitBarWidth = ((float) total) / ((float) patternLength);
        maxIndividualVariance *= unitBarWidth;
        float totalVariance = 0.0f;
        for (int x = 0; x < numCounters; x++) {
            int counter = counters[x];
            float scaledPattern = ((float) pattern[x]) * unitBarWidth;
            float variance = ((float) counter) > scaledPattern ? ((float) counter) - scaledPattern : scaledPattern - ((float) counter);
            if (variance > maxIndividualVariance) {
                return Float.POSITIVE_INFINITY;
            }
            totalVariance += variance;
        }
        return totalVariance / ((float) total);
    }
}
