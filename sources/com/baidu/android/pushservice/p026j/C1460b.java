package com.baidu.android.pushservice.p026j;

import java.util.concurrent.PriorityBlockingQueue;

/* renamed from: com.baidu.android.pushservice.j.b */
public class C1460b<E> extends PriorityBlockingQueue<E> {
    public boolean offer(E e) {
        return size() >= 20 ? false : super.offer(e);
    }
}
