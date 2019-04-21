package com.google.zxing.pdf417.decoder;

import java.util.Formatter;

class DetectionResultColumn {
    private final BoundingBox boundingBox;
    private final Codeword[] codewords;

    DetectionResultColumn(BoundingBox boundingBox) {
        this.boundingBox = new BoundingBox(boundingBox);
        this.codewords = new Codeword[((boundingBox.getMaxY() - boundingBox.getMinY()) + 1)];
    }

    /* Access modifiers changed, original: final */
    public final Codeword getCodewordNearby(int imageRow) {
        Codeword codeword = getCodeword(imageRow);
        if (codeword != null) {
            return codeword;
        }
        for (int i = 1; i < 5; i++) {
            int nearImageRow = imageRowToCodewordIndex(imageRow) - i;
            if (nearImageRow >= 0) {
                codeword = this.codewords[nearImageRow];
                if (codeword != null) {
                    return codeword;
                }
            }
            nearImageRow = imageRowToCodewordIndex(imageRow) + i;
            if (nearImageRow < this.codewords.length) {
                codeword = this.codewords[nearImageRow];
                if (codeword != null) {
                    return codeword;
                }
            }
        }
        return null;
    }

    /* Access modifiers changed, original: final */
    public final int imageRowToCodewordIndex(int imageRow) {
        return imageRow - this.boundingBox.getMinY();
    }

    /* Access modifiers changed, original: final */
    public final void setCodeword(int imageRow, Codeword codeword) {
        this.codewords[imageRowToCodewordIndex(imageRow)] = codeword;
    }

    /* Access modifiers changed, original: final */
    public final Codeword getCodeword(int imageRow) {
        return this.codewords[imageRowToCodewordIndex(imageRow)];
    }

    /* Access modifiers changed, original: final */
    public final BoundingBox getBoundingBox() {
        return this.boundingBox;
    }

    /* Access modifiers changed, original: final */
    public final Codeword[] getCodewords() {
        return this.codewords;
    }

    public String toString() {
        Formatter formatter = new Formatter();
        Codeword[] codewordArr = this.codewords;
        int length = codewordArr.length;
        int i = 0;
        int row = 0;
        while (i < length) {
            int row2;
            Codeword codeword = codewordArr[i];
            Object[] objArr;
            if (codeword == null) {
                objArr = new Object[1];
                row2 = row + 1;
                objArr[0] = Integer.valueOf(row);
                formatter.format("%3d:    |   %n", objArr);
            } else {
                objArr = new Object[3];
                row2 = row + 1;
                objArr[0] = Integer.valueOf(row);
                objArr[1] = Integer.valueOf(codeword.getRowNumber());
                objArr[2] = Integer.valueOf(codeword.getValue());
                formatter.format("%3d: %3d|%3d%n", objArr);
            }
            i++;
            row = row2;
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
