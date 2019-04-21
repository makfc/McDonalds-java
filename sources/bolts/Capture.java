package bolts;

public class Capture<T> {
    private T value;

    public T get() {
        return this.value;
    }

    public void set(T value) {
        this.value = value;
    }
}
