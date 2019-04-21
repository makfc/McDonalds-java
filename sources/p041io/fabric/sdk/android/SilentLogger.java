package p041io.fabric.sdk.android;

/* renamed from: io.fabric.sdk.android.SilentLogger */
public class SilentLogger implements Logger {
    private int logLevel = 7;

    public boolean isLoggable(String tag, int level) {
        return false;
    }

    /* renamed from: d */
    public void mo34404d(String tag, String text, Throwable throwable) {
    }

    /* renamed from: w */
    public void mo34412w(String tag, String text, Throwable throwable) {
    }

    /* renamed from: e */
    public void mo34406e(String tag, String text, Throwable throwable) {
    }

    /* renamed from: d */
    public void mo34403d(String tag, String text) {
    }

    /* renamed from: v */
    public void mo34410v(String tag, String text) {
    }

    /* renamed from: i */
    public void mo34407i(String tag, String text) {
    }

    /* renamed from: w */
    public void mo34411w(String tag, String text) {
    }

    /* renamed from: e */
    public void mo34405e(String tag, String text) {
    }

    public void log(int priority, String tag, String msg) {
    }
}
