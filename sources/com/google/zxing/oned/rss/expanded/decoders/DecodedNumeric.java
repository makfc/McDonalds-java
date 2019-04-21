package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.FormatException;

final class DecodedNumeric extends DecodedObject {
    private final int firstDigit;
    private final int secondDigit;

    DecodedNumeric(int newPosition, int firstDigit, int secondDigit) throws FormatException {
        super(newPosition);
        if (firstDigit < 0 || firstDigit > 10 || secondDigit < 0 || secondDigit > 10) {
            throw FormatException.getFormatInstance();
        }
        this.firstDigit = firstDigit;
        this.secondDigit = secondDigit;
    }

    /* Access modifiers changed, original: 0000 */
    public int getFirstDigit() {
        return this.firstDigit;
    }

    /* Access modifiers changed, original: 0000 */
    public int getSecondDigit() {
        return this.secondDigit;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isFirstDigitFNC1() {
        return this.firstDigit == 10;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isSecondDigitFNC1() {
        return this.secondDigit == 10;
    }
}
