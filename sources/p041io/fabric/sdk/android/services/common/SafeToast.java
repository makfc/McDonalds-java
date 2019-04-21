package p041io.fabric.sdk.android.services.common;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import p041io.fabric.sdk.android.services.concurrency.PriorityRunnable;

/* renamed from: io.fabric.sdk.android.services.common.SafeToast */
public class SafeToast extends Toast {

    /* renamed from: io.fabric.sdk.android.services.common.SafeToast$1 */
    class C45991 extends PriorityRunnable {
        C45991() {
        }

        public void run() {
            super.show();
        }
    }

    public void show() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.show();
        } else {
            new Handler(Looper.getMainLooper()).post(new C45991());
        }
    }
}
