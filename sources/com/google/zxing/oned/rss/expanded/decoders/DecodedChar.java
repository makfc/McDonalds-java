package com.google.zxing.oned.rss.expanded.decoders;

final class DecodedChar extends DecodedObject {
    private final char value;

    DecodedChar(int newPosition, char value) {
        super(newPosition);
        this.value = value;
    }

    /* Access modifiers changed, original: 0000 */
    public char getValue() {
        return this.value;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isFNC1() {
        return this.value == '$';
    }
}
