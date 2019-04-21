package android.support.p001v7.util;

import android.os.Handler;
import android.support.p001v7.util.ThreadUtil.BackgroundCallback;
import android.support.p001v7.util.ThreadUtil.MainThreadCallback;
import android.support.p001v7.util.TileList.Tile;
import android.util.Log;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: android.support.v7.util.MessageThreadUtil */
class MessageThreadUtil<T> implements ThreadUtil<T> {

    /* renamed from: android.support.v7.util.MessageThreadUtil$1 */
    class C03441 implements MainThreadCallback<T> {
        private final Handler mMainThreadHandler;
        private Runnable mMainThreadRunnable;
        final MessageQueue mQueue;
        final /* synthetic */ MainThreadCallback val$callback;

        /* renamed from: android.support.v7.util.MessageThreadUtil$1$1 */
        class C03431 implements Runnable {
            final /* synthetic */ C03441 this$1;

            public void run() {
                SyncQueueItem msg = this.this$1.mQueue.next();
                while (msg != null) {
                    switch (msg.what) {
                        case 1:
                            this.this$1.val$callback.updateItemCount(msg.arg1, msg.arg2);
                            break;
                        case 2:
                            this.this$1.val$callback.addTile(msg.arg1, (Tile) msg.data);
                            break;
                        case 3:
                            this.this$1.val$callback.removeTile(msg.arg1, msg.arg2);
                            break;
                        default:
                            Log.e("ThreadUtil", "Unsupported message, what=" + msg.what);
                            break;
                    }
                    msg = this.this$1.mQueue.next();
                }
            }
        }

        public void updateItemCount(int generation, int itemCount) {
            sendMessage(SyncQueueItem.obtainMessage(1, generation, itemCount));
        }

        public void addTile(int generation, Tile<T> tile) {
            sendMessage(SyncQueueItem.obtainMessage(2, generation, (Object) tile));
        }

        public void removeTile(int generation, int position) {
            sendMessage(SyncQueueItem.obtainMessage(3, generation, position));
        }

        private void sendMessage(SyncQueueItem msg) {
            this.mQueue.sendMessage(msg);
            this.mMainThreadHandler.post(this.mMainThreadRunnable);
        }
    }

    /* renamed from: android.support.v7.util.MessageThreadUtil$2 */
    class C03462 implements BackgroundCallback<T> {
        private Runnable mBackgroundRunnable;
        AtomicBoolean mBackgroundRunning;
        private final Executor mExecutor;
        final MessageQueue mQueue;
        final /* synthetic */ BackgroundCallback val$callback;

        /* renamed from: android.support.v7.util.MessageThreadUtil$2$1 */
        class C03451 implements Runnable {
            final /* synthetic */ C03462 this$1;

            public void run() {
                while (true) {
                    SyncQueueItem msg = this.this$1.mQueue.next();
                    if (msg != null) {
                        switch (msg.what) {
                            case 1:
                                this.this$1.mQueue.removeMessages(1);
                                this.this$1.val$callback.refresh(msg.arg1);
                                break;
                            case 2:
                                this.this$1.mQueue.removeMessages(2);
                                this.this$1.mQueue.removeMessages(3);
                                this.this$1.val$callback.updateRange(msg.arg1, msg.arg2, msg.arg3, msg.arg4, msg.arg5);
                                break;
                            case 3:
                                this.this$1.val$callback.loadTile(msg.arg1, msg.arg2);
                                break;
                            case 4:
                                this.this$1.val$callback.recycleTile((Tile) msg.data);
                                break;
                            default:
                                Log.e("ThreadUtil", "Unsupported message, what=" + msg.what);
                                break;
                        }
                    }
                    this.this$1.mBackgroundRunning.set(false);
                    return;
                }
            }
        }

        public void refresh(int generation) {
            sendMessageAtFrontOfQueue(SyncQueueItem.obtainMessage(1, generation, null));
        }

        public void updateRange(int rangeStart, int rangeEnd, int extRangeStart, int extRangeEnd, int scrollHint) {
            sendMessageAtFrontOfQueue(SyncQueueItem.obtainMessage(2, rangeStart, rangeEnd, extRangeStart, extRangeEnd, scrollHint, null));
        }

        public void loadTile(int position, int scrollHint) {
            sendMessage(SyncQueueItem.obtainMessage(3, position, scrollHint));
        }

        public void recycleTile(Tile<T> tile) {
            sendMessage(SyncQueueItem.obtainMessage(4, 0, (Object) tile));
        }

        private void sendMessage(SyncQueueItem msg) {
            this.mQueue.sendMessage(msg);
            maybeExecuteBackgroundRunnable();
        }

        private void sendMessageAtFrontOfQueue(SyncQueueItem msg) {
            this.mQueue.sendMessageAtFrontOfQueue(msg);
            maybeExecuteBackgroundRunnable();
        }

        private void maybeExecuteBackgroundRunnable() {
            if (this.mBackgroundRunning.compareAndSet(false, true)) {
                this.mExecutor.execute(this.mBackgroundRunnable);
            }
        }
    }

    /* renamed from: android.support.v7.util.MessageThreadUtil$MessageQueue */
    static class MessageQueue {
        private SyncQueueItem mRoot;

        MessageQueue() {
        }

        /* Access modifiers changed, original: declared_synchronized */
        public synchronized SyncQueueItem next() {
            SyncQueueItem next;
            if (this.mRoot == null) {
                next = null;
            } else {
                next = this.mRoot;
                this.mRoot = this.mRoot.next;
            }
            return next;
        }

        /* Access modifiers changed, original: declared_synchronized */
        public synchronized void sendMessageAtFrontOfQueue(SyncQueueItem item) {
            item.next = this.mRoot;
            this.mRoot = item;
        }

        /* Access modifiers changed, original: declared_synchronized */
        public synchronized void sendMessage(SyncQueueItem item) {
            if (this.mRoot == null) {
                this.mRoot = item;
            } else {
                SyncQueueItem last = this.mRoot;
                while (last.next != null) {
                    last = last.next;
                }
                last.next = item;
            }
        }

        /* Access modifiers changed, original: declared_synchronized */
        public synchronized void removeMessages(int what) {
            SyncQueueItem item;
            while (this.mRoot != null && this.mRoot.what == what) {
                item = this.mRoot;
                this.mRoot = this.mRoot.next;
                item.recycle();
            }
            if (this.mRoot != null) {
                SyncQueueItem prev = this.mRoot;
                item = prev.next;
                while (item != null) {
                    SyncQueueItem next = item.next;
                    if (item.what == what) {
                        prev.next = next;
                        item.recycle();
                    } else {
                        prev = item;
                    }
                    item = next;
                }
            }
        }
    }

    /* renamed from: android.support.v7.util.MessageThreadUtil$SyncQueueItem */
    static class SyncQueueItem {
        private static SyncQueueItem sPool;
        private static final Object sPoolLock = new Object();
        public int arg1;
        public int arg2;
        public int arg3;
        public int arg4;
        public int arg5;
        public Object data;
        private SyncQueueItem next;
        public int what;

        SyncQueueItem() {
        }

        /* Access modifiers changed, original: 0000 */
        public void recycle() {
            this.next = null;
            this.arg5 = 0;
            this.arg4 = 0;
            this.arg3 = 0;
            this.arg2 = 0;
            this.arg1 = 0;
            this.what = 0;
            this.data = null;
            synchronized (sPoolLock) {
                if (sPool != null) {
                    this.next = sPool;
                }
                sPool = this;
            }
        }

        static SyncQueueItem obtainMessage(int what, int arg1, int arg2, int arg3, int arg4, int arg5, Object data) {
            SyncQueueItem item;
            synchronized (sPoolLock) {
                if (sPool == null) {
                    item = new SyncQueueItem();
                } else {
                    item = sPool;
                    sPool = sPool.next;
                    item.next = null;
                }
                item.what = what;
                item.arg1 = arg1;
                item.arg2 = arg2;
                item.arg3 = arg3;
                item.arg4 = arg4;
                item.arg5 = arg5;
                item.data = data;
            }
            return item;
        }

        static SyncQueueItem obtainMessage(int what, int arg1, int arg2) {
            return SyncQueueItem.obtainMessage(what, arg1, arg2, 0, 0, 0, null);
        }

        static SyncQueueItem obtainMessage(int what, int arg1, Object data) {
            return SyncQueueItem.obtainMessage(what, arg1, 0, 0, 0, 0, data);
        }
    }

    MessageThreadUtil() {
    }
}
