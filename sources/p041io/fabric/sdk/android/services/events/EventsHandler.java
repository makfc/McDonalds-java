package p041io.fabric.sdk.android.services.events;

import android.content.Context;
import p041io.fabric.sdk.android.services.common.CommonUtils;

/* renamed from: io.fabric.sdk.android.services.events.EventsHandler */
public abstract class EventsHandler<T> implements EventsStorageListener {
    protected final Context context;
    protected EventsStrategy<T> strategy;

    /* renamed from: io.fabric.sdk.android.services.events.EventsHandler$1 */
    class C46011 implements Runnable {
        final /* synthetic */ EventsHandler this$0;
        final /* synthetic */ Object val$event;
        final /* synthetic */ boolean val$sendImmediately;

        public void run() {
            try {
                this.this$0.strategy.recordEvent(this.val$event);
                if (this.val$sendImmediately) {
                    this.this$0.strategy.rollFileOver();
                }
            } catch (Exception e) {
                CommonUtils.logControlledError(this.this$0.context, "Failed to record event.", e);
            }
        }
    }

    /* renamed from: io.fabric.sdk.android.services.events.EventsHandler$2 */
    class C46022 implements Runnable {
        final /* synthetic */ EventsHandler this$0;
        final /* synthetic */ Object val$event;

        public void run() {
            try {
                this.this$0.strategy.recordEvent(this.val$event);
            } catch (Exception e) {
                CommonUtils.logControlledError(this.this$0.context, "Crashlytics failed to record event", e);
            }
        }
    }

    /* renamed from: io.fabric.sdk.android.services.events.EventsHandler$3 */
    class C46033 implements Runnable {
        final /* synthetic */ EventsHandler this$0;

        public void run() {
            try {
                this.this$0.strategy.sendEvents();
            } catch (Exception e) {
                CommonUtils.logControlledError(this.this$0.context, "Failed to send events files.", e);
            }
        }
    }

    /* renamed from: io.fabric.sdk.android.services.events.EventsHandler$4 */
    class C46044 implements Runnable {
        final /* synthetic */ EventsHandler this$0;

        public void run() {
            try {
                EventsStrategy<T> prevStrategy = this.this$0.strategy;
                this.this$0.strategy = this.this$0.getDisabledEventsStrategy();
                prevStrategy.deleteAllEvents();
            } catch (Exception e) {
                CommonUtils.logControlledError(this.this$0.context, "Failed to disable events.", e);
            }
        }
    }

    public abstract EventsStrategy<T> getDisabledEventsStrategy();
}
