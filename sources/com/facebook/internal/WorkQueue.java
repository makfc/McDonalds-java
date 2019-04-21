package com.facebook.internal;

import com.facebook.Settings;
import java.util.concurrent.Executor;

class WorkQueue {
    static final /* synthetic */ boolean $assertionsDisabled = (!WorkQueue.class.desiredAssertionStatus());
    public static final int DEFAULT_MAX_CONCURRENT = 8;
    private final Executor executor;
    private final int maxConcurrent;
    private WorkNode pendingJobs;
    private int runningCount;
    private WorkNode runningJobs;
    private final Object workLock;

    interface WorkItem {
        boolean cancel();

        boolean isRunning();

        void moveToFront();
    }

    private class WorkNode implements WorkItem {
        static final /* synthetic */ boolean $assertionsDisabled = (!WorkQueue.class.desiredAssertionStatus());
        private final Runnable callback;
        private boolean isRunning;
        private WorkNode next;
        private WorkNode prev;

        WorkNode(Runnable callback) {
            this.callback = callback;
        }

        public boolean cancel() {
            synchronized (WorkQueue.this.workLock) {
                if (isRunning()) {
                    return false;
                }
                WorkQueue.this.pendingJobs = removeFromList(WorkQueue.this.pendingJobs);
                return true;
            }
        }

        public void moveToFront() {
            synchronized (WorkQueue.this.workLock) {
                if (!isRunning()) {
                    WorkQueue.this.pendingJobs = removeFromList(WorkQueue.this.pendingJobs);
                    WorkQueue.this.pendingJobs = addToList(WorkQueue.this.pendingJobs, true);
                }
            }
        }

        public boolean isRunning() {
            return this.isRunning;
        }

        /* Access modifiers changed, original: 0000 */
        public Runnable getCallback() {
            return this.callback;
        }

        /* Access modifiers changed, original: 0000 */
        public WorkNode getNext() {
            return this.next;
        }

        /* Access modifiers changed, original: 0000 */
        public void setIsRunning(boolean isRunning) {
            this.isRunning = isRunning;
        }

        /* Access modifiers changed, original: 0000 */
        public WorkNode addToList(WorkNode list, boolean addToFront) {
            if (!$assertionsDisabled && this.next != null) {
                throw new AssertionError();
            } else if ($assertionsDisabled || this.prev == null) {
                if (list == null) {
                    this.prev = this;
                    this.next = this;
                    list = this;
                } else {
                    this.next = list;
                    this.prev = list.prev;
                    WorkNode workNode = this.next;
                    this.prev.next = this;
                    workNode.prev = this;
                }
                if (addToFront) {
                    return this;
                }
                return list;
            } else {
                throw new AssertionError();
            }
        }

        /* Access modifiers changed, original: 0000 */
        public WorkNode removeFromList(WorkNode list) {
            if (!$assertionsDisabled && this.next == null) {
                throw new AssertionError();
            } else if ($assertionsDisabled || this.prev != null) {
                if (list == this) {
                    if (this.next == this) {
                        list = null;
                    } else {
                        list = this.next;
                    }
                }
                this.next.prev = this.prev;
                this.prev.next = this.next;
                this.prev = null;
                this.next = null;
                return list;
            } else {
                throw new AssertionError();
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void verify(boolean shouldBeRunning) {
            if (!$assertionsDisabled && this.prev.next != this) {
                throw new AssertionError();
            } else if (!$assertionsDisabled && this.next.prev != this) {
                throw new AssertionError();
            } else if (!$assertionsDisabled && isRunning() != shouldBeRunning) {
                throw new AssertionError();
            }
        }
    }

    WorkQueue() {
        this(8);
    }

    WorkQueue(int maxConcurrent) {
        this(maxConcurrent, Settings.getExecutor());
    }

    WorkQueue(int maxConcurrent, Executor executor) {
        this.workLock = new Object();
        this.runningJobs = null;
        this.runningCount = 0;
        this.maxConcurrent = maxConcurrent;
        this.executor = executor;
    }

    /* Access modifiers changed, original: 0000 */
    public WorkItem addActiveWorkItem(Runnable callback) {
        return addActiveWorkItem(callback, true);
    }

    /* Access modifiers changed, original: 0000 */
    public WorkItem addActiveWorkItem(Runnable callback, boolean addToFront) {
        WorkNode node = new WorkNode(callback);
        synchronized (this.workLock) {
            this.pendingJobs = node.addToList(this.pendingJobs, addToFront);
        }
        startItem();
        return node;
    }

    /* Access modifiers changed, original: 0000 */
    public void validate() {
        synchronized (this.workLock) {
            int count = 0;
            if (this.runningJobs != null) {
                WorkNode walk = this.runningJobs;
                do {
                    walk.verify(true);
                    count++;
                    walk = walk.getNext();
                } while (walk != this.runningJobs);
            }
            if ($assertionsDisabled || this.runningCount == count) {
            } else {
                throw new AssertionError();
            }
        }
    }

    private void startItem() {
        finishItemAndStartNew(null);
    }

    private void finishItemAndStartNew(WorkNode finished) {
        WorkNode ready = null;
        synchronized (this.workLock) {
            if (finished != null) {
                this.runningJobs = finished.removeFromList(this.runningJobs);
                this.runningCount--;
            }
            if (this.runningCount < this.maxConcurrent) {
                ready = this.pendingJobs;
                if (ready != null) {
                    this.pendingJobs = ready.removeFromList(this.pendingJobs);
                    this.runningJobs = ready.addToList(this.runningJobs, false);
                    this.runningCount++;
                    ready.setIsRunning(true);
                }
            }
        }
        if (ready != null) {
            execute(ready);
        }
    }

    private void execute(final WorkNode node) {
        this.executor.execute(new Runnable() {
            public void run() {
                try {
                    node.getCallback().run();
                } finally {
                    WorkQueue.this.finishItemAndStartNew(node);
                }
            }
        });
    }
}
