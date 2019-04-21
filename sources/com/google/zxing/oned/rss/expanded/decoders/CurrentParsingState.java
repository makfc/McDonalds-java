package com.google.zxing.oned.rss.expanded.decoders;

final class CurrentParsingState {
    private State encoding = State.NUMERIC;
    private int position = 0;

    private enum State {
        NUMERIC,
        ALPHA,
        ISO_IEC_646
    }

    CurrentParsingState() {
    }

    /* Access modifiers changed, original: 0000 */
    public int getPosition() {
        return this.position;
    }

    /* Access modifiers changed, original: 0000 */
    public void setPosition(int position) {
        this.position = position;
    }

    /* Access modifiers changed, original: 0000 */
    public void incrementPosition(int delta) {
        this.position += delta;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isAlpha() {
        return this.encoding == State.ALPHA;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isIsoIec646() {
        return this.encoding == State.ISO_IEC_646;
    }

    /* Access modifiers changed, original: 0000 */
    public void setNumeric() {
        this.encoding = State.NUMERIC;
    }

    /* Access modifiers changed, original: 0000 */
    public void setAlpha() {
        this.encoding = State.ALPHA;
    }

    /* Access modifiers changed, original: 0000 */
    public void setIsoIec646() {
        this.encoding = State.ISO_IEC_646;
    }
}
