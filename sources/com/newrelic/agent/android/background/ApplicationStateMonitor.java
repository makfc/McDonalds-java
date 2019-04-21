package com.newrelic.agent.android.background;

import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.util.NamedThreadFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ApplicationStateMonitor implements Runnable {
    private static ApplicationStateMonitor instance;
    private static final AgentLog log = AgentLogManager.getAgentLog();
    private final int activitySnoozeTimeInMilliseconds;
    protected final ArrayList<ApplicationStateListener> applicationStateListeners;
    private AtomicLong count;
    protected final ScheduledThreadPoolExecutor executor;
    private final Lock foregroundLock;
    protected AtomicBoolean foregrounded;
    private final Lock snoozeLock;
    private AtomicLong snoozeStartTime;

    /* renamed from: com.newrelic.agent.android.background.ApplicationStateMonitor$1 */
    class C41521 implements Runnable {
        C41521() {
        }

        public void run() {
            try {
                ApplicationStateMonitor.this.foregroundLock.lock();
                if (ApplicationStateMonitor.this.foregrounded.get()) {
                    ApplicationStateMonitor.log.info("UI has become hidden (app backgrounded)");
                    ApplicationStateMonitor.this.notifyApplicationInBackground();
                    ApplicationStateMonitor.this.foregrounded.set(false);
                }
                ApplicationStateMonitor.this.foregroundLock.unlock();
            } catch (Throwable th) {
                ApplicationStateMonitor.this.foregroundLock.unlock();
            }
        }
    }

    /* renamed from: com.newrelic.agent.android.background.ApplicationStateMonitor$2 */
    class C41532 implements Runnable {
        C41532() {
        }

        public void run() {
            try {
                ApplicationStateMonitor.this.foregroundLock.lock();
                ApplicationStateMonitor.this.snoozeLock.lock();
                if (ApplicationStateMonitor.this.count.decrementAndGet() == 0) {
                    ApplicationStateMonitor.this.snoozeStartTime.set(System.currentTimeMillis());
                }
                ApplicationStateMonitor.this.snoozeLock.unlock();
                ApplicationStateMonitor.this.foregroundLock.unlock();
            } catch (Throwable th) {
                ApplicationStateMonitor.this.foregroundLock.unlock();
            }
        }
    }

    /* renamed from: com.newrelic.agent.android.background.ApplicationStateMonitor$3 */
    class C41543 implements Runnable {
        C41543() {
        }

        public void run() {
            try {
                ApplicationStateMonitor.this.foregroundLock.lock();
                ApplicationStateMonitor.this.snoozeLock.lock();
                if (ApplicationStateMonitor.this.count.incrementAndGet() == 1) {
                    ApplicationStateMonitor.this.snoozeStartTime.set(0);
                }
                ApplicationStateMonitor.this.snoozeLock.unlock();
                if (!ApplicationStateMonitor.this.foregrounded.get()) {
                    ApplicationStateMonitor.log.verbose("Application appears to be in the foreground");
                    ApplicationStateMonitor.this.foregrounded.set(true);
                    ApplicationStateMonitor.this.notifyApplicationInForeground();
                }
                ApplicationStateMonitor.this.foregroundLock.unlock();
            } catch (Throwable th) {
                ApplicationStateMonitor.this.foregroundLock.unlock();
            }
        }
    }

    private ApplicationStateMonitor() {
        this(5, 5, TimeUnit.SECONDS, 5000);
    }

    ApplicationStateMonitor(int initialDelay, int period, TimeUnit timeUnit, int snoozeTimeInMilliseconds) {
        this.count = new AtomicLong(0);
        this.snoozeStartTime = new AtomicLong(0);
        this.snoozeLock = new ReentrantLock();
        this.applicationStateListeners = new ArrayList();
        this.foregrounded = new AtomicBoolean(true);
        this.foregroundLock = new ReentrantLock();
        this.executor = new ScheduledThreadPoolExecutor(1, new NamedThreadFactory("AppStateMon"));
        this.activitySnoozeTimeInMilliseconds = snoozeTimeInMilliseconds;
        this.executor.scheduleAtFixedRate(this, (long) initialDelay, (long) period, timeUnit);
        log.info("Application state monitor has started");
    }

    public static ApplicationStateMonitor getInstance() {
        if (instance == null) {
            instance = new ApplicationStateMonitor();
        }
        return instance;
    }

    public void addApplicationStateListener(ApplicationStateListener listener) {
        synchronized (this.applicationStateListeners) {
            this.applicationStateListeners.add(listener);
        }
    }

    public void removeApplicationStateListener(ApplicationStateListener listener) {
        synchronized (this.applicationStateListeners) {
            this.applicationStateListeners.remove(listener);
        }
    }

    public void run() {
        try {
            this.foregroundLock.lock();
            if (this.foregrounded.get() && getSnoozeTime() >= ((long) this.activitySnoozeTimeInMilliseconds)) {
                this.foregrounded.set(false);
                notifyApplicationInBackground();
            }
            this.foregroundLock.unlock();
        } catch (Throwable th) {
            this.foregroundLock.unlock();
        }
    }

    public void uiHidden() {
        this.executor.execute(new C41521());
    }

    public void activityStopped() {
        this.executor.execute(new C41532());
    }

    public void activityStarted() {
        this.executor.execute(new C41543());
    }

    private void notifyApplicationInBackground() {
        ArrayList<ApplicationStateListener> listeners;
        log.verbose("Application appears to have gone to the background");
        synchronized (this.applicationStateListeners) {
            listeners = new ArrayList(this.applicationStateListeners);
        }
        ApplicationStateEvent e = new ApplicationStateEvent(this);
        Iterator it = listeners.iterator();
        while (it.hasNext()) {
            ((ApplicationStateListener) it.next()).applicationBackgrounded(e);
        }
    }

    private void notifyApplicationInForeground() {
        ArrayList<ApplicationStateListener> listeners;
        synchronized (this.applicationStateListeners) {
            listeners = new ArrayList(this.applicationStateListeners);
        }
        ApplicationStateEvent e = new ApplicationStateEvent(this);
        Iterator it = listeners.iterator();
        while (it.hasNext()) {
            ((ApplicationStateListener) it.next()).applicationForegrounded(e);
        }
    }

    private long getSnoozeTime() {
        long snoozeValue = 0;
        try {
            this.foregroundLock.lock();
            this.snoozeLock.lock();
            long snoozeTime = this.snoozeStartTime.get();
            if (snoozeTime != 0) {
                snoozeValue = System.currentTimeMillis() - snoozeTime;
            }
            this.snoozeLock.unlock();
            this.foregroundLock.unlock();
            return snoozeValue;
        } catch (Throwable th) {
            this.foregroundLock.unlock();
        }
    }

    public ScheduledThreadPoolExecutor getExecutor() {
        return this.executor;
    }

    public boolean getForegrounded() {
        return this.foregrounded.get();
    }

    public static boolean isAppInBackground() {
        return !getInstance().getForegrounded();
    }
}
