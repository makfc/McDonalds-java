package com.bumptech.glide.load.engine.prefill;

import java.util.List;
import java.util.Map;

final class PreFillQueue {
    private final Map<PreFillType, Integer> bitmapsPerType;
    private int bitmapsRemaining;
    private int keyIndex;
    private final List<PreFillType> keyList;

    public PreFillType remove() {
        PreFillType result = (PreFillType) this.keyList.get(this.keyIndex);
        Integer countForResult = (Integer) this.bitmapsPerType.get(result);
        if (countForResult.intValue() == 1) {
            this.bitmapsPerType.remove(result);
            this.keyList.remove(this.keyIndex);
        } else {
            this.bitmapsPerType.put(result, Integer.valueOf(countForResult.intValue() - 1));
        }
        this.bitmapsRemaining--;
        this.keyIndex = this.keyList.isEmpty() ? 0 : (this.keyIndex + 1) % this.keyList.size();
        return result;
    }

    public boolean isEmpty() {
        return this.bitmapsRemaining == 0;
    }
}
