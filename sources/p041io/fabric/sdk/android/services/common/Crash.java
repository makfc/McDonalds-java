package p041io.fabric.sdk.android.services.common;

/* renamed from: io.fabric.sdk.android.services.common.Crash */
public abstract class Crash {
    private final String exceptionName;
    private final String sessionId;

    /* renamed from: io.fabric.sdk.android.services.common.Crash$FatalException */
    public static class FatalException extends Crash {
        public FatalException(String sessionId, String exceptionName) {
            super(sessionId, exceptionName);
        }
    }

    /* renamed from: io.fabric.sdk.android.services.common.Crash$LoggedException */
    public static class LoggedException extends Crash {
        public LoggedException(String sessionId, String exceptionName) {
            super(sessionId, exceptionName);
        }
    }

    public Crash(String sessionId, String exceptionName) {
        this.sessionId = sessionId;
        this.exceptionName = exceptionName;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public String getExceptionName() {
        return this.exceptionName;
    }
}
