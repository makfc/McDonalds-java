package p041io.fabric.sdk.android.services.events;

/* renamed from: io.fabric.sdk.android.services.events.EventsManager */
public interface EventsManager<T> {
    void deleteAllEvents();

    void recordEvent(T t);

    void sendEvents();
}
