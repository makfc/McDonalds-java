package com.google.zxing.oned.rss.expanded.decoders;

abstract class DecodedObject {
    private final int newPosition;

    DecodedObject(int newPosition) {
        this.newPosition = newPosition;
    }

    /* Access modifiers changed, original: final */
    public final int getNewPosition() {
        return this.newPosition;
    }
}
