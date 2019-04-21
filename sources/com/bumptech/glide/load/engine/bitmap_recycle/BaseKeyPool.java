package com.bumptech.glide.load.engine.bitmap_recycle;

import com.bumptech.glide.util.Util;
import java.util.Queue;

abstract class BaseKeyPool<T extends Poolable> {
    private final Queue<T> keyPool = Util.createQueue(20);

    public abstract T create();

    BaseKeyPool() {
    }

    /* Access modifiers changed, original: protected */
    public T get() {
        Poolable result = (Poolable) this.keyPool.poll();
        if (result == null) {
            return create();
        }
        return result;
    }

    public void offer(T key) {
        if (this.keyPool.size() < 20) {
            this.keyPool.offer(key);
        }
    }
}
