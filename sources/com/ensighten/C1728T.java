package com.ensighten;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.p000v4.widget.ExploreByTouchHelper;
import android.widget.VideoView;
import com.kochava.base.InstallReferrer;
import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: com.ensighten.T */
public final class C1728T {
    /* renamed from: a */
    public static AtomicBoolean f5743a = new AtomicBoolean(false);
    /* renamed from: b */
    public static C1729U f5744b = new C1729U();
    /* renamed from: c */
    public static C1729U f5745c = new C1729U();
    /* renamed from: d */
    public static Message f5746d = null;
    /* renamed from: e */
    public static Message f5747e = null;
    /* renamed from: f */
    public final Handler f5748f = new C1727d();
    /* renamed from: g */
    public String f5749g = null;
    /* renamed from: h */
    public VideoView f5750h = null;
    /* renamed from: i */
    public C1722S f5751i = null;
    /* renamed from: j */
    AtomicBoolean f5752j = new AtomicBoolean(false);
    /* renamed from: k */
    AtomicBoolean f5753k = new AtomicBoolean(false);

    /* renamed from: com.ensighten.T$a */
    static class C1723a implements OnCompletionListener {
        /* renamed from: a */
        int f5728a = -1;
        /* renamed from: b */
        long f5729b = 0;
        /* renamed from: c */
        private OnCompletionListener f5730c;
        /* renamed from: d */
        private C1728T f5731d;

        public C1723a(OnCompletionListener onCompletionListener, C1728T c1728t) {
            this.f5730c = onCompletionListener;
            this.f5731d = c1728t;
        }

        public final void onCompletion(MediaPlayer mp) {
            long currentTimeMillis = System.currentTimeMillis();
            int currentPosition = mp.getCurrentPosition();
            long j = currentTimeMillis - this.f5729b;
            if (this.f5728a != currentPosition || j >= 1000) {
                Message a = C1728T.m7259a(this.f5731d.f5749g, "complete", currentPosition, mp.getDuration());
                this.f5731d.mo15068b(true);
                this.f5731d.mo15065a(true);
                this.f5731d.f5748f.sendMessage(a);
                C1728T.f5743a.set(false);
                this.f5728a = currentPosition;
                this.f5729b = currentTimeMillis;
                C1728T.f5744b = new C1729U();
                C1728T.f5745c = new C1729U();
                C1728T.f5746d = null;
                C1728T.f5747e = null;
            }
            if (this.f5730c != null) {
                this.f5730c.onCompletion(mp);
            }
        }
    }

    /* renamed from: com.ensighten.T$b */
    static class C1724b implements OnPreparedListener {
        /* renamed from: a */
        private OnPreparedListener f5732a;
        /* renamed from: b */
        private C1728T f5733b;

        public C1724b(OnPreparedListener onPreparedListener, C1728T c1728t) {
            this.f5732a = onPreparedListener;
            this.f5733b = c1728t;
        }

        public final void onPrepared(MediaPlayer mp) {
            this.f5733b.m7261c();
            if (this.f5732a != null) {
                this.f5732a.onPrepared(mp);
            }
        }
    }

    /* renamed from: com.ensighten.T$c */
    static class C1726c implements OnSeekCompleteListener {
        /* renamed from: a */
        private OnSeekCompleteListener f5740a;
        /* renamed from: b */
        private C1728T f5741b;
        /* renamed from: c */
        private C1725a f5742c;

        /* renamed from: com.ensighten.T$c$a */
        static class C1725a implements Runnable {
            /* renamed from: a */
            private AtomicBoolean f5734a = new AtomicBoolean(false);
            /* renamed from: b */
            private int f5735b;
            /* renamed from: c */
            private int f5736c;
            /* renamed from: d */
            private AtomicLong f5737d = new AtomicLong();
            /* renamed from: e */
            private long f5738e = 500;
            /* renamed from: f */
            private C1728T f5739f;

            public C1725a(int i, int i2, C1728T c1728t) {
                this.f5735b = i;
                this.f5736c = i2;
                this.f5739f = c1728t;
            }

            /* renamed from: a */
            public final synchronized void mo15061a(int i, int i2) {
                this.f5735b = i;
                this.f5736c = i2;
                this.f5737d.set(System.currentTimeMillis());
            }

            public final void run() {
                try {
                    this.f5737d.set(System.currentTimeMillis());
                    do {
                    } while (System.currentTimeMillis() - this.f5737d.get() <= this.f5738e);
                    this.f5739f.f5748f.sendMessage(C1728T.m7259a(this.f5739f.f5749g, "seek", this.f5735b, this.f5736c));
                    this.f5734a.set(true);
                } catch (Exception e) {
                    if (C1845i.m7357d()) {
                        C1845i.m7353c(e);
                    }
                }
            }
        }

        public C1726c(OnSeekCompleteListener onSeekCompleteListener, C1728T c1728t) {
            this.f5740a = onSeekCompleteListener;
            this.f5741b = c1728t;
        }

        public final void onSeekComplete(MediaPlayer mp) {
            String str = this.f5741b.f5749g;
            int currentPosition = mp.getCurrentPosition();
            int duration = mp.getDuration();
            if (Ensighten.getEventManager().f5949d) {
                Handler handler = this.f5741b.f5748f;
                if (!C1728T.f5743a.get()) {
                    Ensighten.evaluateJS("Bootstrapper.mobile.dataModel['videoLength'] = " + duration + ";");
                    Ensighten.evaluateJS("Bootstrapper.mobile.dataModel['videoName'] = '" + str + "';");
                    Ensighten.evaluateJS("Bootstrapper.mobile.dataModel['videoPlayerName'] = 'MediaPlayer';");
                    Ensighten.evaluateJS("Bootstrapper.mobile.dataModel['videoPlayhead'] = " + currentPosition + ";");
                    handler.sendMessage(C1728T.m7259a(str, "videoLoad", currentPosition, duration));
                    C1728T.f5743a.set(true);
                }
                handler.sendMessage(C1728T.m7259a(str, "seekStart", currentPosition, duration));
                handler.sendMessage(C1728T.m7259a(str, "seekComplete", currentPosition, duration));
            }
            if (this.f5742c == null || this.f5742c.f5734a.get()) {
                this.f5742c = new C1725a(currentPosition, duration, this.f5741b);
                new Thread(this.f5742c).start();
            } else {
                this.f5742c.mo15061a(currentPosition, duration);
            }
            if (this.f5740a != null) {
                this.f5740a.onSeekComplete(mp);
            }
        }
    }

    /* renamed from: com.ensighten.T$d */
    static final class C1727d extends Handler {
        private C1727d() {
        }

        /* synthetic */ C1727d(byte b) {
            this();
        }

        public final void handleMessage(Message msg) {
            super.handleMessage(msg);
            Ensighten.evaluateNotification(new C1699G(msg.getData().getString("videoName"), msg.getData().getString("event"), msg.getData().getInt("position"), msg.getData().getInt(InstallReferrer.KEY_DURATION)));
        }
    }

    public C1728T(VideoView videoView) {
        this.f5750h = videoView;
        Uri uri = (Uri) Utils.getFieldValue(videoView, "mUri");
        this.f5749g = uri == null ? "undefined" : uri.toString();
        this.f5748f.sendMessage(C1728T.m7259a(this.f5749g, "start", ExploreByTouchHelper.INVALID_ID, ExploreByTouchHelper.INVALID_ID));
        if (mo15067b() != null) {
            m7261c();
        } else {
            Utils.setFieldValue(this.f5750h, "mOnPreparedListener", new C1724b((OnPreparedListener) Utils.getFieldValue(this.f5750h, "mOnPreparedListener"), this));
        }
    }

    /* renamed from: a */
    public static Message m7259a(String str, String str2, int i, int i2) {
        Message obtain = Message.obtain();
        Bundle bundle = new Bundle();
        obtain.setData(bundle);
        bundle.putString("videoName", str);
        bundle.putString("event", str2);
        bundle.putInt("position", i);
        bundle.putInt(InstallReferrer.KEY_DURATION, i2);
        return obtain;
    }

    /* renamed from: a */
    public final void mo15065a(boolean z) {
        this.f5753k.set(z);
    }

    /* renamed from: b */
    public final void mo15068b(boolean z) {
        this.f5752j.set(z);
    }

    /* renamed from: a */
    public final boolean mo15066a() {
        MediaPlayer b = mo15067b();
        return b != null ? b.isPlaying() : false;
    }

    /* renamed from: c */
    private void m7261c() {
        MediaPlayer b = mo15067b();
        Utils.setFieldValue(b, "mOnCompletionListener", new C1723a((OnCompletionListener) Utils.getFieldValue(b, "mOnCompletionListener"), this));
        Utils.setFieldValue(b, "mOnSeekCompleteListener", new C1726c((OnSeekCompleteListener) Utils.getFieldValue(b, "mOnSeekCompleteListener"), this));
        this.f5751i = new C1722S(this, Ensighten.getVideoKeyframes(this.f5749g));
        new Thread(this.f5751i).start();
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: b */
    public MediaPlayer mo15067b() {
        try {
            Field field = Utils.getField(this.f5750h.getClass(), "mMediaPlayer");
            field.setAccessible(true);
            return (MediaPlayer) field.get(this.f5750h);
        } catch (Exception e) {
            if (C1845i.m7357d()) {
                C1845i.m7353c(e);
            }
            return null;
        }
    }
}
