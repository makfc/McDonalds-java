package android.support.p000v4.p002os;

/* renamed from: android.support.v4.os.OperationCanceledException */
public class OperationCanceledException extends RuntimeException {
    public OperationCanceledException() {
        this(null);
    }

    public OperationCanceledException(String message) {
        if (message == null) {
            message = "The operation has been canceled.";
        }
        super(message);
    }
}
