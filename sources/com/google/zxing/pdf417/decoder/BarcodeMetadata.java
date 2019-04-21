package com.google.zxing.pdf417.decoder;

final class BarcodeMetadata {
    private final int columnCount;
    private final int errorCorrectionLevel;
    private final int rowCount;
    private final int rowCountLowerPart;
    private final int rowCountUpperPart;

    BarcodeMetadata(int columnCount, int rowCountUpperPart, int rowCountLowerPart, int errorCorrectionLevel) {
        this.columnCount = columnCount;
        this.errorCorrectionLevel = errorCorrectionLevel;
        this.rowCountUpperPart = rowCountUpperPart;
        this.rowCountLowerPart = rowCountLowerPart;
        this.rowCount = rowCountUpperPart + rowCountLowerPart;
    }

    /* Access modifiers changed, original: 0000 */
    public int getColumnCount() {
        return this.columnCount;
    }

    /* Access modifiers changed, original: 0000 */
    public int getErrorCorrectionLevel() {
        return this.errorCorrectionLevel;
    }

    /* Access modifiers changed, original: 0000 */
    public int getRowCount() {
        return this.rowCount;
    }

    /* Access modifiers changed, original: 0000 */
    public int getRowCountUpperPart() {
        return this.rowCountUpperPart;
    }

    /* Access modifiers changed, original: 0000 */
    public int getRowCountLowerPart() {
        return this.rowCountLowerPart;
    }
}
