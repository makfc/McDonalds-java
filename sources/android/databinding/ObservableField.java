package android.databinding;

import java.io.Serializable;

public class ObservableField<T> extends BaseObservable implements Serializable {
    private T mValue;

    public ObservableField(T value) {
        this.mValue = value;
    }

    public T get() {
        return this.mValue;
    }
}
