package com.ensighten.exception;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import com.ensighten.C1845i;
import com.ensighten.C1850l;
import com.ensighten.C1850l.C1753a;
import com.ensighten.C1852n;
import com.ensighten.Ensighten;
import org.acra.ACRA;

public class ExceptionManager implements C1753a {
    /* renamed from: a */
    public Context f5871a;
    /* renamed from: b */
    public boolean f5872b = false;
    /* renamed from: c */
    private boolean f5873c = false;

    /* renamed from: com.ensighten.exception.ExceptionManager$1 */
    public class C17511 implements OnClickListener {
        public final void onClick(DialogInterface dialogInterface, int i) {
            throw new NullPointerException();
        }
    }

    /* renamed from: com.ensighten.exception.ExceptionManager$2 */
    public class C17522 implements OnDismissListener {
        public final void onDismiss(DialogInterface dialogInterface) {
            ExceptionManager.this.f5872b = false;
        }
    }

    public ExceptionManager(Context context) {
        this.f5871a = context;
    }

    /* renamed from: b */
    public final void mo15112b() {
        if (!this.f5873c) {
            Activity c = Ensighten.getViewManager().mo15088c();
            if (c != null) {
                Application application = c.getApplication();
                if (application != null) {
                    try {
                        ACRA.init(application);
                        ACRA.getErrorReporter().setReportSender(new C1852n());
                        this.f5873c = true;
                        return;
                    } catch (Exception e) {
                        if (C1845i.m7348a()) {
                            C1845i.m7351b(String.format("Error enabling crash reporter with error %s.", new Object[]{e.getMessage()}), e);
                            return;
                        }
                        return;
                    }
                }
                C1850l eventManager = Ensighten.getEventManager();
                synchronized (eventManager.f5950e) {
                    eventManager.f5950e.add(this);
                }
            }
        }
    }

    /* renamed from: a */
    public final void mo15111a() {
        mo15112b();
        C1850l eventManager = Ensighten.getEventManager();
        synchronized (eventManager.f5950e) {
            eventManager.f5950e.remove(this);
        }
    }
}
