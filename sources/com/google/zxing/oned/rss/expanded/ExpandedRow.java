package com.google.zxing.oned.rss.expanded;

import java.util.ArrayList;
import java.util.List;

final class ExpandedRow {
    private final List<ExpandedPair> pairs;
    private final int rowNumber;
    private final boolean wasReversed;

    ExpandedRow(List<ExpandedPair> pairs, int rowNumber, boolean wasReversed) {
        this.pairs = new ArrayList(pairs);
        this.rowNumber = rowNumber;
        this.wasReversed = wasReversed;
    }

    /* Access modifiers changed, original: 0000 */
    public List<ExpandedPair> getPairs() {
        return this.pairs;
    }

    /* Access modifiers changed, original: 0000 */
    public int getRowNumber() {
        return this.rowNumber;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isEquivalent(List<ExpandedPair> otherPairs) {
        return this.pairs.equals(otherPairs);
    }

    public String toString() {
        return "{ " + this.pairs + " }";
    }

    public boolean equals(Object o) {
        if (!(o instanceof ExpandedRow)) {
            return false;
        }
        ExpandedRow that = (ExpandedRow) o;
        if (this.pairs.equals(that.getPairs()) && this.wasReversed == that.wasReversed) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.pairs.hashCode() ^ Boolean.valueOf(this.wasReversed).hashCode();
    }
}
