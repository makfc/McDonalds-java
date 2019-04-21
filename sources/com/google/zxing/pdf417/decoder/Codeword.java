package com.google.zxing.pdf417.decoder;

final class Codeword {
    private final int bucket;
    private final int endX;
    private int rowNumber = -1;
    private final int startX;
    private final int value;

    Codeword(int startX, int endX, int bucket, int value) {
        this.startX = startX;
        this.endX = endX;
        this.bucket = bucket;
        this.value = value;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean hasValidRowNumber() {
        return isValidRowNumber(this.rowNumber);
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isValidRowNumber(int rowNumber) {
        return rowNumber != -1 && this.bucket == (rowNumber % 3) * 3;
    }

    /* Access modifiers changed, original: 0000 */
    public void setRowNumberAsRowIndicatorColumn() {
        this.rowNumber = ((this.value / 30) * 3) + (this.bucket / 3);
    }

    /* Access modifiers changed, original: 0000 */
    public int getWidth() {
        return this.endX - this.startX;
    }

    /* Access modifiers changed, original: 0000 */
    public int getStartX() {
        return this.startX;
    }

    /* Access modifiers changed, original: 0000 */
    public int getEndX() {
        return this.endX;
    }

    /* Access modifiers changed, original: 0000 */
    public int getBucket() {
        return this.bucket;
    }

    /* Access modifiers changed, original: 0000 */
    public int getValue() {
        return this.value;
    }

    /* Access modifiers changed, original: 0000 */
    public int getRowNumber() {
        return this.rowNumber;
    }

    /* Access modifiers changed, original: 0000 */
    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public String toString() {
        return this.rowNumber + "|" + this.value;
    }
}
