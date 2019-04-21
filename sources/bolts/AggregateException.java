package bolts;

public class AggregateException extends Exception {
    private Throwable[] causes;

    public AggregateException(String detailMessage, Throwable[] causes) {
        Throwable th;
        if (causes == null || causes.length <= 0) {
            th = null;
        } else {
            th = causes[0];
        }
        super(detailMessage, th);
        if (causes == null || causes.length <= 0) {
            causes = null;
        }
        this.causes = causes;
    }
}
