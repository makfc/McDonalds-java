package com.google.zxing.multi.qrcode.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.detector.FinderPattern;
import com.google.zxing.qrcode.detector.FinderPatternFinder;
import com.google.zxing.qrcode.detector.FinderPatternInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

final class MultiFinderPatternFinder extends FinderPatternFinder {
    private static final FinderPatternInfo[] EMPTY_RESULT_ARRAY = new FinderPatternInfo[0];

    private static final class ModuleSizeComparator implements Serializable, Comparator<FinderPattern> {
        private ModuleSizeComparator() {
        }

        public int compare(FinderPattern center1, FinderPattern center2) {
            float value = center2.getEstimatedModuleSize() - center1.getEstimatedModuleSize();
            if (((double) value) < 0.0d) {
                return -1;
            }
            return ((double) value) > 0.0d ? 1 : 0;
        }
    }

    MultiFinderPatternFinder(BitMatrix image, ResultPointCallback resultPointCallback) {
        super(image, resultPointCallback);
    }

    private FinderPattern[][] selectMutipleBestPatterns() throws NotFoundException {
        List<FinderPattern> possibleCenters = getPossibleCenters();
        int size = possibleCenters.size();
        if (size < 3) {
            throw NotFoundException.getNotFoundInstance();
        } else if (size == 3) {
            FinderPattern[][] finderPatternArr = new FinderPattern[1][];
            finderPatternArr[0] = new FinderPattern[]{(FinderPattern) possibleCenters.get(0), (FinderPattern) possibleCenters.get(1), (FinderPattern) possibleCenters.get(2)};
            return finderPatternArr;
        } else {
            Collections.sort(possibleCenters, new ModuleSizeComparator());
            List<FinderPattern[]> results = new ArrayList();
            for (int i1 = 0; i1 < size - 2; i1++) {
                FinderPattern p1 = (FinderPattern) possibleCenters.get(i1);
                if (p1 != null) {
                    for (int i2 = i1 + 1; i2 < size - 1; i2++) {
                        FinderPattern p2 = (FinderPattern) possibleCenters.get(i2);
                        if (p2 != null) {
                            float vModSize12 = (p1.getEstimatedModuleSize() - p2.getEstimatedModuleSize()) / Math.min(p1.getEstimatedModuleSize(), p2.getEstimatedModuleSize());
                            if (Math.abs(p1.getEstimatedModuleSize() - p2.getEstimatedModuleSize()) > 0.5f && vModSize12 >= 0.05f) {
                                break;
                            }
                            for (int i3 = i2 + 1; i3 < size; i3++) {
                                FinderPattern p3 = (FinderPattern) possibleCenters.get(i3);
                                if (p3 != null) {
                                    float vModSize23 = (p2.getEstimatedModuleSize() - p3.getEstimatedModuleSize()) / Math.min(p2.getEstimatedModuleSize(), p3.getEstimatedModuleSize());
                                    if (Math.abs(p2.getEstimatedModuleSize() - p3.getEstimatedModuleSize()) > 0.5f && vModSize23 >= 0.05f) {
                                        break;
                                    }
                                    Object test = new FinderPattern[]{p1, p2, p3};
                                    ResultPoint.orderBestPatterns(test);
                                    FinderPatternInfo info = new FinderPatternInfo(test);
                                    float dA = ResultPoint.distance(info.getTopLeft(), info.getBottomLeft());
                                    float dC = ResultPoint.distance(info.getTopRight(), info.getBottomLeft());
                                    float dB = ResultPoint.distance(info.getTopLeft(), info.getTopRight());
                                    float estimatedModuleCount = (dA + dB) / (p1.getEstimatedModuleSize() * 2.0f);
                                    if (estimatedModuleCount <= 180.0f && estimatedModuleCount >= 9.0f && Math.abs((dA - dB) / Math.min(dA, dB)) < 0.1f) {
                                        float dCpy = (float) Math.sqrt((double) ((dA * dA) + (dB * dB)));
                                        if (Math.abs((dC - dCpy) / Math.min(dC, dCpy)) < 0.1f) {
                                            results.add(test);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (!results.isEmpty()) {
                return (FinderPattern[][]) results.toArray(new FinderPattern[results.size()][]);
            }
            throw NotFoundException.getNotFoundInstance();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00df A:{LOOP_END, LOOP:2: B:42:0x00dd->B:43:0x00df} */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00fc  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:5:0x000f  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00df A:{LOOP_END, LOOP:2: B:42:0x00dd->B:43:0x00df} */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00fc  */
    public com.google.zxing.qrcode.detector.FinderPatternInfo[] findMulti(java.util.Map<com.google.zxing.DecodeHintType, ?> r18) throws com.google.zxing.NotFoundException {
        /*
        r17 = this;
        if (r18 == 0) goto L_0x0069;
    L_0x0002:
        r14 = com.google.zxing.DecodeHintType.TRY_HARDER;
        r0 = r18;
        r14 = r0.containsKey(r14);
        if (r14 == 0) goto L_0x0069;
    L_0x000c:
        r13 = 1;
    L_0x000d:
        if (r18 == 0) goto L_0x006b;
    L_0x000f:
        r14 = com.google.zxing.DecodeHintType.PURE_BARCODE;
        r0 = r18;
        r14 = r0.containsKey(r14);
        if (r14 == 0) goto L_0x006b;
    L_0x0019:
        r10 = 1;
    L_0x001a:
        r4 = r17.getImage();
        r6 = r4.getHeight();
        r7 = r4.getWidth();
        r14 = (float) r6;
        r15 = 1130627072; // 0x43640000 float:228.0 double:5.586039945E-315;
        r14 = r14 / r15;
        r15 = 1077936128; // 0x40400000 float:3.0 double:5.325712093E-315;
        r14 = r14 * r15;
        r3 = (int) r14;
        r14 = 3;
        if (r3 < r14) goto L_0x0033;
    L_0x0031:
        if (r13 == 0) goto L_0x0034;
    L_0x0033:
        r3 = 3;
    L_0x0034:
        r14 = 5;
        r12 = new int[r14];
        r2 = r3 + -1;
    L_0x0039:
        if (r2 >= r6) goto L_0x00d2;
    L_0x003b:
        r14 = 0;
        r15 = 0;
        r12[r14] = r15;
        r14 = 1;
        r15 = 0;
        r12[r14] = r15;
        r14 = 2;
        r15 = 0;
        r12[r14] = r15;
        r14 = 3;
        r15 = 0;
        r12[r14] = r15;
        r14 = 4;
        r15 = 0;
        r12[r14] = r15;
        r1 = 0;
        r5 = 0;
    L_0x0051:
        if (r5 >= r7) goto L_0x00c4;
    L_0x0053:
        r14 = r4.get(r5, r2);
        if (r14 == 0) goto L_0x006d;
    L_0x0059:
        r14 = r1 & 1;
        r15 = 1;
        if (r14 != r15) goto L_0x0060;
    L_0x005e:
        r1 = r1 + 1;
    L_0x0060:
        r14 = r12[r1];
        r14 = r14 + 1;
        r12[r1] = r14;
    L_0x0066:
        r5 = r5 + 1;
        goto L_0x0051;
    L_0x0069:
        r13 = 0;
        goto L_0x000d;
    L_0x006b:
        r10 = 0;
        goto L_0x001a;
    L_0x006d:
        r14 = r1 & 1;
        if (r14 != 0) goto L_0x00bd;
    L_0x0071:
        r14 = 4;
        if (r1 != r14) goto L_0x00b4;
    L_0x0074:
        r14 = com.google.zxing.qrcode.detector.FinderPatternFinder.foundPatternCross(r12);
        if (r14 == 0) goto L_0x0098;
    L_0x007a:
        r0 = r17;
        r14 = r0.handlePossibleCenter(r12, r2, r5, r10);
        if (r14 == 0) goto L_0x0098;
    L_0x0082:
        r1 = 0;
        r14 = 0;
        r15 = 0;
        r12[r14] = r15;
        r14 = 1;
        r15 = 0;
        r12[r14] = r15;
        r14 = 2;
        r15 = 0;
        r12[r14] = r15;
        r14 = 3;
        r15 = 0;
        r12[r14] = r15;
        r14 = 4;
        r15 = 0;
        r12[r14] = r15;
        goto L_0x0066;
    L_0x0098:
        r14 = 0;
        r15 = 2;
        r15 = r12[r15];
        r12[r14] = r15;
        r14 = 1;
        r15 = 3;
        r15 = r12[r15];
        r12[r14] = r15;
        r14 = 2;
        r15 = 4;
        r15 = r12[r15];
        r12[r14] = r15;
        r14 = 3;
        r15 = 1;
        r12[r14] = r15;
        r14 = 4;
        r15 = 0;
        r12[r14] = r15;
        r1 = 3;
        goto L_0x0066;
    L_0x00b4:
        r1 = r1 + 1;
        r14 = r12[r1];
        r14 = r14 + 1;
        r12[r1] = r14;
        goto L_0x0066;
    L_0x00bd:
        r14 = r12[r1];
        r14 = r14 + 1;
        r12[r1] = r14;
        goto L_0x0066;
    L_0x00c4:
        r14 = com.google.zxing.qrcode.detector.FinderPatternFinder.foundPatternCross(r12);
        if (r14 == 0) goto L_0x00cf;
    L_0x00ca:
        r0 = r17;
        r0.handlePossibleCenter(r12, r2, r7, r10);
    L_0x00cf:
        r2 = r2 + r3;
        goto L_0x0039;
    L_0x00d2:
        r9 = r17.selectMutipleBestPatterns();
        r11 = new java.util.ArrayList;
        r11.<init>();
        r15 = r9.length;
        r14 = 0;
    L_0x00dd:
        if (r14 >= r15) goto L_0x00f3;
    L_0x00df:
        r8 = r9[r14];
        com.google.zxing.ResultPoint.orderBestPatterns(r8);
        r16 = new com.google.zxing.qrcode.detector.FinderPatternInfo;
        r0 = r16;
        r0.<init>(r8);
        r0 = r16;
        r11.add(r0);
        r14 = r14 + 1;
        goto L_0x00dd;
    L_0x00f3:
        r14 = r11.isEmpty();
        if (r14 == 0) goto L_0x00fc;
    L_0x00f9:
        r14 = EMPTY_RESULT_ARRAY;
    L_0x00fb:
        return r14;
    L_0x00fc:
        r14 = r11.size();
        r14 = new com.google.zxing.qrcode.detector.FinderPatternInfo[r14];
        r14 = r11.toArray(r14);
        r14 = (com.google.zxing.qrcode.detector.FinderPatternInfo[]) r14;
        goto L_0x00fb;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.multi.qrcode.detector.MultiFinderPatternFinder.findMulti(java.util.Map):com.google.zxing.qrcode.detector.FinderPatternInfo[]");
    }
}
