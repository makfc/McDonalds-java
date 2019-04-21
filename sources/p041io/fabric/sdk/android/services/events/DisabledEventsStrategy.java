package p041io.fabric.sdk.android.services.events;

/* renamed from: io.fabric.sdk.android.services.events.DisabledEventsStrategy */
public class DisabledEventsStrategy<T> implements EventsStrategy<T> {
    public void sendEvents() {
    }

    public void deleteAllEvents() {
    }

    public void recordEvent(T t) {
    }

    public void cancelTimeBasedFileRollOver() {
    }

    public boolean rollFileOver() {
        return false;
    }
}
