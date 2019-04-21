package com.autonavi.amap.mapcore.interfaces;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLDebugHelper;
import android.opengl.GLSurfaceView.Renderer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import java.io.Writer;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

@SuppressLint({"NewApi"})
public class GLTextureView extends TextureView implements SurfaceTextureListener {
    public static final int DEBUG_CHECK_GL_ERROR = 1;
    public static final int DEBUG_LOG_GL_CALLS = 2;
    private static final boolean LOG_ATTACH_DETACH = false;
    private static final boolean LOG_EGL = false;
    private static final boolean LOG_PAUSE_RESUME = false;
    private static final boolean LOG_RENDERER = false;
    private static final boolean LOG_RENDERER_DRAW_FRAME = false;
    private static final boolean LOG_SURFACE = false;
    private static final boolean LOG_THREADS = false;
    public static final int RENDERMODE_CONTINUOUSLY = 1;
    public static final int RENDERMODE_WHEN_DIRTY = 0;
    private static final String TAG = "GLSurfaceView";
    private static final C0862g sGLThreadManager = new C0862g();
    private int mDebugFlags;
    private boolean mDetached;
    private EGLConfigChooser mEGLConfigChooser;
    private int mEGLContextClientVersion;
    private EGLContextFactory mEGLContextFactory;
    private EGLWindowSurfaceFactory mEGLWindowSurfaceFactory;
    private C0861f mGLThread;
    private GLWrapper mGLWrapper;
    private boolean mPreserveEGLContextOnPause;
    private Renderer mRenderer;
    private final WeakReference<GLTextureView> mThisWeakRef = new WeakReference(this);

    public interface EGLConfigChooser {
        EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay);
    }

    public interface EGLContextFactory {
        EGLContext createContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig);

        void destroyContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext);
    }

    public interface EGLWindowSurfaceFactory {
        EGLSurface createWindowSurface(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj);

        void destroySurface(EGL10 egl10, EGLDisplay eGLDisplay, EGLSurface eGLSurface);
    }

    public interface GLWrapper {
        GL wrap(GL gl);
    }

    /* renamed from: com.autonavi.amap.mapcore.interfaces.GLTextureView$a */
    private abstract class C0856a implements EGLConfigChooser {
        /* renamed from: a */
        protected int[] f2025a;

        /* renamed from: a */
        public abstract EGLConfig mo9483a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr);

        public C0856a(int[] iArr) {
            this.f2025a = m2845a(iArr);
        }

        public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
            int[] iArr = new int[1];
            if (egl10.eglChooseConfig(eGLDisplay, this.f2025a, null, 0, iArr)) {
                int i = iArr[0];
                if (i <= 0) {
                    throw new IllegalArgumentException("No configs match configSpec");
                }
                EGLConfig[] eGLConfigArr = new EGLConfig[i];
                if (egl10.eglChooseConfig(eGLDisplay, this.f2025a, eGLConfigArr, i, iArr)) {
                    EGLConfig a = mo9483a(egl10, eGLDisplay, eGLConfigArr);
                    if (a != null) {
                        return a;
                    }
                    throw new IllegalArgumentException("No config chosen");
                }
                throw new IllegalArgumentException("eglChooseConfig#2 failed");
            }
            throw new IllegalArgumentException("eglChooseConfig failed");
        }

        /* renamed from: a */
        private int[] m2845a(int[] iArr) {
            if (GLTextureView.this.mEGLContextClientVersion != 2 && GLTextureView.this.mEGLContextClientVersion != 3) {
                return iArr;
            }
            int length = iArr.length;
            int[] iArr2 = new int[(length + 2)];
            System.arraycopy(iArr, 0, iArr2, 0, length - 1);
            iArr2[length - 1] = 12352;
            if (GLTextureView.this.mEGLContextClientVersion == 2) {
                iArr2[length] = 4;
            } else {
                iArr2[length] = 64;
            }
            iArr2[length + 1] = 12344;
            return iArr2;
        }
    }

    /* renamed from: com.autonavi.amap.mapcore.interfaces.GLTextureView$b */
    private class C0857b extends C0856a {
        /* renamed from: c */
        protected int f2027c;
        /* renamed from: d */
        protected int f2028d;
        /* renamed from: e */
        protected int f2029e;
        /* renamed from: f */
        protected int f2030f;
        /* renamed from: g */
        protected int f2031g;
        /* renamed from: h */
        protected int f2032h;
        /* renamed from: j */
        private int[] f2034j = new int[1];

        public C0857b(int i, int i2, int i3, int i4, int i5, int i6) {
            super(new int[]{12324, i, 12323, i2, 12322, i3, 12321, i4, 12325, i5, 12326, i6, 12344});
            this.f2027c = i;
            this.f2028d = i2;
            this.f2029e = i3;
            this.f2030f = i4;
            this.f2031g = i5;
            this.f2032h = i6;
        }

        /* renamed from: a */
        public EGLConfig mo9483a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
            for (EGLConfig eGLConfig : eGLConfigArr) {
                int a = m2847a(egl10, eGLDisplay, eGLConfig, 12325, 0);
                int a2 = m2847a(egl10, eGLDisplay, eGLConfig, 12326, 0);
                if (a >= this.f2031g && a2 >= this.f2032h) {
                    a = m2847a(egl10, eGLDisplay, eGLConfig, 12324, 0);
                    int a3 = m2847a(egl10, eGLDisplay, eGLConfig, 12323, 0);
                    int a4 = m2847a(egl10, eGLDisplay, eGLConfig, 12322, 0);
                    a2 = m2847a(egl10, eGLDisplay, eGLConfig, 12321, 0);
                    if (a == this.f2027c && a3 == this.f2028d && a4 == this.f2029e && a2 == this.f2030f) {
                        return eGLConfig;
                    }
                }
            }
            return null;
        }

        /* renamed from: a */
        private int m2847a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i, int i2) {
            if (egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, this.f2034j)) {
                return this.f2034j[0];
            }
            return i2;
        }
    }

    /* renamed from: com.autonavi.amap.mapcore.interfaces.GLTextureView$c */
    private class C0858c implements EGLContextFactory {
        /* renamed from: b */
        private int f2036b;

        private C0858c() {
            this.f2036b = 12440;
        }

        /* synthetic */ C0858c(GLTextureView gLTextureView, C1279a c1279a) {
            this();
        }

        public EGLContext createContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
            int[] iArr = new int[]{this.f2036b, GLTextureView.this.mEGLContextClientVersion, 12344};
            EGLContext eGLContext = EGL10.EGL_NO_CONTEXT;
            if (GLTextureView.this.mEGLContextClientVersion == 0) {
                iArr = null;
            }
            return egl10.eglCreateContext(eGLDisplay, eGLConfig, eGLContext, iArr);
        }

        public void destroyContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext) {
            if (!egl10.eglDestroyContext(eGLDisplay, eGLContext)) {
                Log.e("DefaultContextFactory", "display:" + eGLDisplay + " context: " + eGLContext);
                C0860e.m2850a("eglDestroyContex", egl10.eglGetError());
            }
        }
    }

    /* renamed from: com.autonavi.amap.mapcore.interfaces.GLTextureView$d */
    private static class C0859d implements EGLWindowSurfaceFactory {
        private C0859d() {
        }

        /* synthetic */ C0859d(C1279a c1279a) {
            this();
        }

        public EGLSurface createWindowSurface(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj) {
            EGLSurface eGLSurface = null;
            try {
                return egl10.eglCreateWindowSurface(eGLDisplay, eGLConfig, obj, null);
            } catch (IllegalArgumentException e) {
                Log.e(GLTextureView.TAG, "eglCreateWindowSurface", e);
                return eGLSurface;
            }
        }

        public void destroySurface(EGL10 egl10, EGLDisplay eGLDisplay, EGLSurface eGLSurface) {
            egl10.eglDestroySurface(eGLDisplay, eGLSurface);
        }
    }

    /* renamed from: com.autonavi.amap.mapcore.interfaces.GLTextureView$e */
    private static class C0860e {
        /* renamed from: a */
        EGL10 f2037a;
        /* renamed from: b */
        EGLDisplay f2038b;
        /* renamed from: c */
        EGLSurface f2039c;
        /* renamed from: d */
        EGLConfig f2040d;
        /* renamed from: e */
        EGLContext f2041e;
        /* renamed from: f */
        private WeakReference<GLTextureView> f2042f;

        public C0860e(WeakReference<GLTextureView> weakReference) {
            this.f2042f = weakReference;
        }

        /* renamed from: a */
        public void mo9484a() {
            this.f2037a = (EGL10) EGLContext.getEGL();
            this.f2038b = this.f2037a.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            if (this.f2038b == EGL10.EGL_NO_DISPLAY) {
                throw new RuntimeException("eglGetDisplay failed");
            }
            if (this.f2037a.eglInitialize(this.f2038b, new int[2])) {
                GLTextureView gLTextureView = (GLTextureView) this.f2042f.get();
                if (gLTextureView == null) {
                    this.f2040d = null;
                    this.f2041e = null;
                } else {
                    this.f2040d = gLTextureView.mEGLConfigChooser.chooseConfig(this.f2037a, this.f2038b);
                    this.f2041e = gLTextureView.mEGLContextFactory.createContext(this.f2037a, this.f2038b, this.f2040d);
                }
                if (this.f2041e == null || this.f2041e == EGL10.EGL_NO_CONTEXT) {
                    this.f2041e = null;
                    m2849a("createContext");
                }
                this.f2039c = null;
                return;
            }
            throw new RuntimeException("eglInitialize failed");
        }

        /* renamed from: b */
        public boolean mo9485b() {
            if (this.f2037a == null) {
                throw new RuntimeException("egl not initialized");
            } else if (this.f2038b == null) {
                throw new RuntimeException("eglDisplay not initialized");
            } else if (this.f2040d == null) {
                throw new RuntimeException("mEglConfig not initialized");
            } else {
                m2853g();
                GLTextureView gLTextureView = (GLTextureView) this.f2042f.get();
                if (gLTextureView != null) {
                    this.f2039c = gLTextureView.mEGLWindowSurfaceFactory.createWindowSurface(this.f2037a, this.f2038b, this.f2040d, gLTextureView.getSurfaceTexture());
                } else {
                    this.f2039c = null;
                }
                if (this.f2039c == null || this.f2039c == EGL10.EGL_NO_SURFACE) {
                    if (this.f2037a.eglGetError() == 12299) {
                        Log.e("EglHelper", "createWindowSurface returned EGL_BAD_NATIVE_WINDOW.");
                    }
                    return false;
                } else if (this.f2037a.eglMakeCurrent(this.f2038b, this.f2039c, this.f2039c, this.f2041e)) {
                    return true;
                } else {
                    C0860e.m2851a("EGLHelper", "eglMakeCurrent", this.f2037a.eglGetError());
                    return false;
                }
            }
        }

        /* Access modifiers changed, original: 0000 */
        /* renamed from: c */
        public GL mo9486c() {
            GL gl = this.f2041e.getGL();
            GLTextureView gLTextureView = (GLTextureView) this.f2042f.get();
            if (gLTextureView == null) {
                return gl;
            }
            if (gLTextureView.mGLWrapper != null) {
                gl = gLTextureView.mGLWrapper.wrap(gl);
            }
            if ((gLTextureView.mDebugFlags & 3) == 0) {
                return gl;
            }
            Writer c0863h;
            int i = 0;
            if ((gLTextureView.mDebugFlags & 1) != 0) {
                i = 1;
            }
            if ((gLTextureView.mDebugFlags & 2) != 0) {
                c0863h = new C0863h();
            } else {
                c0863h = null;
            }
            return GLDebugHelper.wrap(gl, i, c0863h);
        }

        /* renamed from: d */
        public int mo9487d() {
            if (this.f2037a.eglSwapBuffers(this.f2038b, this.f2039c)) {
                return 12288;
            }
            return this.f2037a.eglGetError();
        }

        /* renamed from: e */
        public void mo9488e() {
            m2853g();
        }

        /* renamed from: g */
        private void m2853g() {
            if (this.f2039c != null && this.f2039c != EGL10.EGL_NO_SURFACE) {
                this.f2037a.eglMakeCurrent(this.f2038b, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
                GLTextureView gLTextureView = (GLTextureView) this.f2042f.get();
                if (gLTextureView != null) {
                    gLTextureView.mEGLWindowSurfaceFactory.destroySurface(this.f2037a, this.f2038b, this.f2039c);
                }
                this.f2039c = null;
            }
        }

        /* renamed from: f */
        public void mo9489f() {
            if (this.f2041e != null) {
                GLTextureView gLTextureView = (GLTextureView) this.f2042f.get();
                if (gLTextureView != null) {
                    gLTextureView.mEGLContextFactory.destroyContext(this.f2037a, this.f2038b, this.f2041e);
                }
                this.f2041e = null;
            }
            if (this.f2038b != null) {
                this.f2037a.eglTerminate(this.f2038b);
                this.f2038b = null;
            }
        }

        /* renamed from: a */
        private void m2849a(String str) {
            C0860e.m2850a(str, this.f2037a.eglGetError());
        }

        /* renamed from: a */
        public static void m2850a(String str, int i) {
            throw new RuntimeException(C0860e.m2852b(str, i));
        }

        /* renamed from: a */
        public static void m2851a(String str, String str2, int i) {
            Log.w(str, C0860e.m2852b(str2, i));
        }

        /* renamed from: b */
        public static String m2852b(String str, int i) {
            return str + " failed: " + i;
        }
    }

    /* renamed from: com.autonavi.amap.mapcore.interfaces.GLTextureView$f */
    static class C0861f extends Thread {
        /* renamed from: a */
        private boolean f2043a;
        /* renamed from: b */
        private boolean f2044b;
        /* renamed from: c */
        private boolean f2045c;
        /* renamed from: d */
        private boolean f2046d;
        /* renamed from: e */
        private boolean f2047e;
        /* renamed from: f */
        private boolean f2048f;
        /* renamed from: g */
        private boolean f2049g;
        /* renamed from: h */
        private boolean f2050h;
        /* renamed from: i */
        private boolean f2051i;
        /* renamed from: j */
        private boolean f2052j;
        /* renamed from: k */
        private boolean f2053k;
        /* renamed from: l */
        private int f2054l = 0;
        /* renamed from: m */
        private int f2055m = 0;
        /* renamed from: n */
        private int f2056n = 1;
        /* renamed from: o */
        private boolean f2057o = true;
        /* renamed from: p */
        private boolean f2058p;
        /* renamed from: q */
        private ArrayList<Runnable> f2059q = new ArrayList();
        /* renamed from: r */
        private boolean f2060r = true;
        /* renamed from: s */
        private C0860e f2061s;
        /* renamed from: t */
        private WeakReference<GLTextureView> f2062t;

        C0861f(WeakReference<GLTextureView> weakReference) {
            this.f2062t = weakReference;
        }

        public void run() {
            setName("GLThread " + getId());
            try {
                m2863l();
            } catch (InterruptedException e) {
            } finally {
                GLTextureView.sGLThreadManager.mo9503a(this);
            }
        }

        /* renamed from: j */
        private void m2861j() {
            if (this.f2051i) {
                this.f2051i = false;
                this.f2061s.mo9488e();
            }
        }

        /* renamed from: k */
        private void m2862k() {
            if (this.f2050h) {
                this.f2061s.mo9489f();
                this.f2050h = false;
                GLTextureView.sGLThreadManager.mo9508c(this);
            }
        }

        /* JADX WARNING: Missing block: B:22:0x006d, code skipped:
            if (r4 == null) goto L_0x01f9;
     */
        /* JADX WARNING: Missing block: B:24:?, code skipped:
            r4.run();
     */
        /* JADX WARNING: Missing block: B:25:0x0072, code skipped:
            r3 = r5;
            r5 = r7;
            r7 = r8;
            r8 = r9;
            r9 = r10;
            r10 = r11;
            r11 = r1;
            r17 = r2;
            r2 = null;
            r4 = r6;
            r6 = r17;
     */
        /* JADX WARNING: Missing block: B:112:0x01f9, code skipped:
            if (r1 == null) goto L_0x02e1;
     */
        /* JADX WARNING: Missing block: B:115:0x0203, code skipped:
            if (r18.f2061s.mo9485b() == false) goto L_0x02ad;
     */
        /* JADX WARNING: Missing block: B:116:0x0205, code skipped:
            r3 = com.autonavi.amap.mapcore.interfaces.GLTextureView.access$800();
     */
        /* JADX WARNING: Missing block: B:117:0x0209, code skipped:
            monitor-enter(r3);
     */
        /* JADX WARNING: Missing block: B:120:?, code skipped:
            r18.f2052j = true;
            com.autonavi.amap.mapcore.interfaces.GLTextureView.access$800().notifyAll();
     */
        /* JADX WARNING: Missing block: B:121:0x0216, code skipped:
            monitor-exit(r3);
     */
        /* JADX WARNING: Missing block: B:122:0x0217, code skipped:
            r3 = null;
     */
        /* JADX WARNING: Missing block: B:123:0x0219, code skipped:
            if (r11 == null) goto L_0x02de;
     */
        /* JADX WARNING: Missing block: B:125:?, code skipped:
            r1 = (javax.microedition.khronos.opengles.GL10) r18.f2061s.mo9486c();
            com.autonavi.amap.mapcore.interfaces.GLTextureView.access$800().mo9504a(r1);
            r11 = null;
            r13 = r1;
     */
        /* JADX WARNING: Missing block: B:126:0x022e, code skipped:
            if (r12 == null) goto L_0x024a;
     */
        /* JADX WARNING: Missing block: B:127:0x0230, code skipped:
            r1 = (com.autonavi.amap.mapcore.interfaces.GLTextureView) r18.f2062t.get();
     */
        /* JADX WARNING: Missing block: B:128:0x023a, code skipped:
            if (r1 == null) goto L_0x0249;
     */
        /* JADX WARNING: Missing block: B:129:0x023c, code skipped:
            com.autonavi.amap.mapcore.interfaces.GLTextureView.access$1000(r1).onSurfaceCreated(r13, r18.f2061s.f2040d);
     */
        /* JADX WARNING: Missing block: B:130:0x0249, code skipped:
            r12 = null;
     */
        /* JADX WARNING: Missing block: B:131:0x024a, code skipped:
            if (r9 == null) goto L_0x0260;
     */
        /* JADX WARNING: Missing block: B:132:0x024c, code skipped:
            r1 = (com.autonavi.amap.mapcore.interfaces.GLTextureView) r18.f2062t.get();
     */
        /* JADX WARNING: Missing block: B:133:0x0256, code skipped:
            if (r1 == null) goto L_0x025f;
     */
        /* JADX WARNING: Missing block: B:134:0x0258, code skipped:
            com.autonavi.amap.mapcore.interfaces.GLTextureView.access$1000(r1).onSurfaceChanged(r13, r6, r5);
     */
        /* JADX WARNING: Missing block: B:135:0x025f, code skipped:
            r9 = null;
     */
        /* JADX WARNING: Missing block: B:136:0x0260, code skipped:
            r1 = (com.autonavi.amap.mapcore.interfaces.GLTextureView) r18.f2062t.get();
     */
        /* JADX WARNING: Missing block: B:137:0x026a, code skipped:
            if (r1 == null) goto L_0x0273;
     */
        /* JADX WARNING: Missing block: B:138:0x026c, code skipped:
            com.autonavi.amap.mapcore.interfaces.GLTextureView.access$1000(r1).onDrawFrame(r13);
     */
        /* JADX WARNING: Missing block: B:139:0x0273, code skipped:
            r1 = r18.f2061s.mo9487d();
     */
        /* JADX WARNING: Missing block: B:140:0x027b, code skipped:
            switch(r1) {
                case 12288: goto L_0x0297;
                case 12302: goto L_0x02d6;
                default: goto L_0x027e;
            };
     */
        /* JADX WARNING: Missing block: B:141:0x027e, code skipped:
            com.autonavi.amap.mapcore.interfaces.GLTextureView.C0860e.m2851a("GLThread", "eglSwapBuffers", r1);
            r14 = com.autonavi.amap.mapcore.interfaces.GLTextureView.access$800();
     */
        /* JADX WARNING: Missing block: B:142:0x0289, code skipped:
            monitor-enter(r14);
     */
        /* JADX WARNING: Missing block: B:145:?, code skipped:
            r18.f2048f = true;
            com.autonavi.amap.mapcore.interfaces.GLTextureView.access$800().notifyAll();
     */
        /* JADX WARNING: Missing block: B:146:0x0296, code skipped:
            monitor-exit(r14);
     */
        /* JADX WARNING: Missing block: B:147:0x0297, code skipped:
            if (r8 == null) goto L_0x02f5;
     */
        /* JADX WARNING: Missing block: B:148:0x0299, code skipped:
            r1 = 1;
     */
        /* JADX WARNING: Missing block: B:149:0x029a, code skipped:
            r2 = r4;
            r14 = r13;
            r4 = r6;
            r6 = r1;
            r17 = r7;
            r7 = r8;
            r8 = r9;
            r9 = r10;
            r10 = r11;
            r11 = r3;
            r3 = r5;
            r5 = r17;
     */
        /* JADX WARNING: Missing block: B:155:0x02ad, code skipped:
            r3 = com.autonavi.amap.mapcore.interfaces.GLTextureView.access$800();
     */
        /* JADX WARNING: Missing block: B:156:0x02b1, code skipped:
            monitor-enter(r3);
     */
        /* JADX WARNING: Missing block: B:159:?, code skipped:
            r18.f2052j = true;
            r18.f2048f = true;
            com.autonavi.amap.mapcore.interfaces.GLTextureView.access$800().notifyAll();
     */
        /* JADX WARNING: Missing block: B:160:0x02c3, code skipped:
            monitor-exit(r3);
     */
        /* JADX WARNING: Missing block: B:161:0x02c4, code skipped:
            r3 = r5;
            r5 = r7;
            r7 = r8;
            r8 = r9;
            r9 = r10;
            r10 = r11;
            r11 = r1;
            r17 = r2;
            r2 = r4;
            r4 = r6;
            r6 = r17;
     */
        /* JADX WARNING: Missing block: B:166:0x02d6, code skipped:
            r10 = 1;
     */
        /* JADX WARNING: Missing block: B:176:0x02de, code skipped:
            r13 = r14;
     */
        /* JADX WARNING: Missing block: B:177:0x02e1, code skipped:
            r3 = r1;
     */
        /* JADX WARNING: Missing block: B:181:0x02f5, code skipped:
            r1 = r2;
     */
        /* renamed from: l */
        private void m2863l() throws java.lang.InterruptedException {
            /*
            r18 = this;
            r1 = new com.autonavi.amap.mapcore.interfaces.GLTextureView$e;
            r0 = r18;
            r2 = r0.f2062t;
            r1.<init>(r2);
            r0 = r18;
            r0.f2061s = r1;
            r1 = 0;
            r0 = r18;
            r0.f2050h = r1;
            r1 = 0;
            r0 = r18;
            r0.f2051i = r1;
            r3 = 0;
            r12 = 0;
            r1 = 0;
            r11 = 0;
            r10 = 0;
            r9 = 0;
            r8 = 0;
            r2 = 0;
            r7 = 0;
            r6 = 0;
            r5 = 0;
            r4 = 0;
            r14 = r3;
            r3 = r5;
            r5 = r7;
            r7 = r8;
            r8 = r9;
            r9 = r10;
            r10 = r11;
            r11 = r1;
            r17 = r2;
            r2 = r4;
            r4 = r6;
            r6 = r17;
        L_0x0031:
            r15 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x01d5 }
            monitor-enter(r15);	 Catch:{ all -> 0x01d5 }
        L_0x0036:
            r0 = r18;
            r1 = r0.f2043a;	 Catch:{ all -> 0x01d2 }
            if (r1 == 0) goto L_0x004d;
        L_0x003c:
            monitor-exit(r15);	 Catch:{ all -> 0x01d2 }
            r2 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;
            monitor-enter(r2);
            r18.m2861j();	 Catch:{ all -> 0x004a }
            r18.m2862k();	 Catch:{ all -> 0x004a }
            monitor-exit(r2);	 Catch:{ all -> 0x004a }
            return;
        L_0x004a:
            r1 = move-exception;
            monitor-exit(r2);	 Catch:{ all -> 0x004a }
            throw r1;
        L_0x004d:
            r0 = r18;
            r1 = r0.f2059q;	 Catch:{ all -> 0x01d2 }
            r1 = r1.isEmpty();	 Catch:{ all -> 0x01d2 }
            if (r1 != 0) goto L_0x0081;
        L_0x0057:
            r0 = r18;
            r1 = r0.f2059q;	 Catch:{ all -> 0x01d2 }
            r2 = 0;
            r1 = r1.remove(r2);	 Catch:{ all -> 0x01d2 }
            r1 = (java.lang.Runnable) r1;	 Catch:{ all -> 0x01d2 }
            r2 = r6;
            r6 = r4;
            r4 = r1;
            r1 = r11;
            r11 = r10;
            r10 = r9;
            r9 = r8;
            r8 = r7;
            r7 = r5;
            r5 = r3;
        L_0x006c:
            monitor-exit(r15);	 Catch:{ all -> 0x01d2 }
            if (r4 == 0) goto L_0x01f9;
        L_0x006f:
            r4.run();	 Catch:{ all -> 0x01d5 }
            r4 = 0;
            r3 = r5;
            r5 = r7;
            r7 = r8;
            r8 = r9;
            r9 = r10;
            r10 = r11;
            r11 = r1;
            r17 = r2;
            r2 = r4;
            r4 = r6;
            r6 = r17;
            goto L_0x0031;
        L_0x0081:
            r1 = 0;
            r0 = r18;
            r13 = r0.f2046d;	 Catch:{ all -> 0x01d2 }
            r0 = r18;
            r0 = r0.f2045c;	 Catch:{ all -> 0x01d2 }
            r16 = r0;
            r0 = r16;
            if (r13 == r0) goto L_0x02f2;
        L_0x0090:
            r0 = r18;
            r1 = r0.f2045c;	 Catch:{ all -> 0x01d2 }
            r0 = r18;
            r13 = r0.f2045c;	 Catch:{ all -> 0x01d2 }
            r0 = r18;
            r0.f2046d = r13;	 Catch:{ all -> 0x01d2 }
            r13 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x01d2 }
            r13.notifyAll();	 Catch:{ all -> 0x01d2 }
            r13 = r1;
        L_0x00a4:
            r0 = r18;
            r1 = r0.f2053k;	 Catch:{ all -> 0x01d2 }
            if (r1 == 0) goto L_0x00b6;
        L_0x00aa:
            r18.m2861j();	 Catch:{ all -> 0x01d2 }
            r18.m2862k();	 Catch:{ all -> 0x01d2 }
            r1 = 0;
            r0 = r18;
            r0.f2053k = r1;	 Catch:{ all -> 0x01d2 }
            r5 = 1;
        L_0x00b6:
            if (r9 == 0) goto L_0x00bf;
        L_0x00b8:
            r18.m2861j();	 Catch:{ all -> 0x01d2 }
            r18.m2862k();	 Catch:{ all -> 0x01d2 }
            r9 = 0;
        L_0x00bf:
            if (r13 == 0) goto L_0x00ca;
        L_0x00c1:
            r0 = r18;
            r1 = r0.f2051i;	 Catch:{ all -> 0x01d2 }
            if (r1 == 0) goto L_0x00ca;
        L_0x00c7:
            r18.m2861j();	 Catch:{ all -> 0x01d2 }
        L_0x00ca:
            if (r13 == 0) goto L_0x00ee;
        L_0x00cc:
            r0 = r18;
            r1 = r0.f2050h;	 Catch:{ all -> 0x01d2 }
            if (r1 == 0) goto L_0x00ee;
        L_0x00d2:
            r0 = r18;
            r1 = r0.f2062t;	 Catch:{ all -> 0x01d2 }
            r1 = r1.get();	 Catch:{ all -> 0x01d2 }
            r1 = (com.autonavi.amap.mapcore.interfaces.GLTextureView) r1;	 Catch:{ all -> 0x01d2 }
            if (r1 != 0) goto L_0x01ab;
        L_0x00de:
            r1 = 0;
        L_0x00df:
            if (r1 == 0) goto L_0x00eb;
        L_0x00e1:
            r1 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x01d2 }
            r1 = r1.mo9505a();	 Catch:{ all -> 0x01d2 }
            if (r1 == 0) goto L_0x00ee;
        L_0x00eb:
            r18.m2862k();	 Catch:{ all -> 0x01d2 }
        L_0x00ee:
            if (r13 == 0) goto L_0x0101;
        L_0x00f0:
            r1 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x01d2 }
            r1 = r1.mo9506b();	 Catch:{ all -> 0x01d2 }
            if (r1 == 0) goto L_0x0101;
        L_0x00fa:
            r0 = r18;
            r1 = r0.f2061s;	 Catch:{ all -> 0x01d2 }
            r1.mo9489f();	 Catch:{ all -> 0x01d2 }
        L_0x0101:
            r0 = r18;
            r1 = r0.f2047e;	 Catch:{ all -> 0x01d2 }
            if (r1 != 0) goto L_0x0127;
        L_0x0107:
            r0 = r18;
            r1 = r0.f2049g;	 Catch:{ all -> 0x01d2 }
            if (r1 != 0) goto L_0x0127;
        L_0x010d:
            r0 = r18;
            r1 = r0.f2051i;	 Catch:{ all -> 0x01d2 }
            if (r1 == 0) goto L_0x0116;
        L_0x0113:
            r18.m2861j();	 Catch:{ all -> 0x01d2 }
        L_0x0116:
            r1 = 1;
            r0 = r18;
            r0.f2049g = r1;	 Catch:{ all -> 0x01d2 }
            r1 = 0;
            r0 = r18;
            r0.f2048f = r1;	 Catch:{ all -> 0x01d2 }
            r1 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x01d2 }
            r1.notifyAll();	 Catch:{ all -> 0x01d2 }
        L_0x0127:
            r0 = r18;
            r1 = r0.f2047e;	 Catch:{ all -> 0x01d2 }
            if (r1 == 0) goto L_0x013f;
        L_0x012d:
            r0 = r18;
            r1 = r0.f2049g;	 Catch:{ all -> 0x01d2 }
            if (r1 == 0) goto L_0x013f;
        L_0x0133:
            r1 = 0;
            r0 = r18;
            r0.f2049g = r1;	 Catch:{ all -> 0x01d2 }
            r1 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x01d2 }
            r1.notifyAll();	 Catch:{ all -> 0x01d2 }
        L_0x013f:
            if (r6 == 0) goto L_0x014f;
        L_0x0141:
            r7 = 0;
            r6 = 0;
            r1 = 1;
            r0 = r18;
            r0.f2058p = r1;	 Catch:{ all -> 0x01d2 }
            r1 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x01d2 }
            r1.notifyAll();	 Catch:{ all -> 0x01d2 }
        L_0x014f:
            r1 = r18.m2864m();	 Catch:{ all -> 0x01d2 }
            if (r1 == 0) goto L_0x01f0;
        L_0x0155:
            r0 = r18;
            r1 = r0.f2050h;	 Catch:{ all -> 0x01d2 }
            if (r1 != 0) goto L_0x015e;
        L_0x015b:
            if (r5 == 0) goto L_0x01b1;
        L_0x015d:
            r5 = 0;
        L_0x015e:
            r0 = r18;
            r1 = r0.f2050h;	 Catch:{ all -> 0x01d2 }
            if (r1 == 0) goto L_0x02ee;
        L_0x0164:
            r0 = r18;
            r1 = r0.f2051i;	 Catch:{ all -> 0x01d2 }
            if (r1 != 0) goto L_0x02ee;
        L_0x016a:
            r1 = 1;
            r0 = r18;
            r0.f2051i = r1;	 Catch:{ all -> 0x01d2 }
            r11 = 1;
            r10 = 1;
            r8 = 1;
            r1 = r8;
            r8 = r10;
        L_0x0174:
            r0 = r18;
            r10 = r0.f2051i;	 Catch:{ all -> 0x01d2 }
            if (r10 == 0) goto L_0x01ee;
        L_0x017a:
            r0 = r18;
            r10 = r0.f2060r;	 Catch:{ all -> 0x01d2 }
            if (r10 == 0) goto L_0x02e4;
        L_0x0180:
            r7 = 1;
            r0 = r18;
            r3 = r0.f2054l;	 Catch:{ all -> 0x01d2 }
            r0 = r18;
            r1 = r0.f2055m;	 Catch:{ all -> 0x01d2 }
            r4 = 1;
            r10 = 1;
            r11 = 0;
            r0 = r18;
            r0.f2060r = r11;	 Catch:{ all -> 0x01d2 }
        L_0x0190:
            r11 = 0;
            r0 = r18;
            r0.f2057o = r11;	 Catch:{ all -> 0x01d2 }
            r11 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x01d2 }
            r11.notifyAll();	 Catch:{ all -> 0x01d2 }
            r11 = r8;
            r8 = r4;
            r4 = r2;
            r2 = r6;
            r6 = r3;
            r17 = r1;
            r1 = r10;
            r10 = r9;
            r9 = r7;
            r7 = r5;
            r5 = r17;
            goto L_0x006c;
        L_0x01ab:
            r1 = r1.mPreserveEGLContextOnPause;	 Catch:{ all -> 0x01d2 }
            goto L_0x00df;
        L_0x01b1:
            r1 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x01d2 }
            r0 = r18;
            r1 = r1.mo9507b(r0);	 Catch:{ all -> 0x01d2 }
            if (r1 == 0) goto L_0x015e;
        L_0x01bd:
            r0 = r18;
            r1 = r0.f2061s;	 Catch:{ RuntimeException -> 0x01e3 }
            r1.mo9484a();	 Catch:{ RuntimeException -> 0x01e3 }
            r1 = 1;
            r0 = r18;
            r0.f2050h = r1;	 Catch:{ all -> 0x01d2 }
            r12 = 1;
            r1 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x01d2 }
            r1.notifyAll();	 Catch:{ all -> 0x01d2 }
            goto L_0x015e;
        L_0x01d2:
            r1 = move-exception;
            monitor-exit(r15);	 Catch:{ all -> 0x01d2 }
            throw r1;	 Catch:{ all -> 0x01d5 }
        L_0x01d5:
            r1 = move-exception;
            r2 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;
            monitor-enter(r2);
            r18.m2861j();	 Catch:{ all -> 0x02db }
            r18.m2862k();	 Catch:{ all -> 0x02db }
            monitor-exit(r2);	 Catch:{ all -> 0x02db }
            throw r1;
        L_0x01e3:
            r1 = move-exception;
            r2 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x01d2 }
            r0 = r18;
            r2.mo9508c(r0);	 Catch:{ all -> 0x01d2 }
            throw r1;	 Catch:{ all -> 0x01d2 }
        L_0x01ee:
            r10 = r8;
            r8 = r1;
        L_0x01f0:
            r1 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x01d2 }
            r1.wait();	 Catch:{ all -> 0x01d2 }
            goto L_0x0036;
        L_0x01f9:
            if (r1 == 0) goto L_0x02e1;
        L_0x01fb:
            r0 = r18;
            r3 = r0.f2061s;	 Catch:{ all -> 0x01d5 }
            r3 = r3.mo9485b();	 Catch:{ all -> 0x01d5 }
            if (r3 == 0) goto L_0x02ad;
        L_0x0205:
            r3 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x01d5 }
            monitor-enter(r3);	 Catch:{ all -> 0x01d5 }
            r1 = 1;
            r0 = r18;
            r0.f2052j = r1;	 Catch:{ all -> 0x02aa }
            r1 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x02aa }
            r1.notifyAll();	 Catch:{ all -> 0x02aa }
            monitor-exit(r3);	 Catch:{ all -> 0x02aa }
            r1 = 0;
            r3 = r1;
        L_0x0219:
            if (r11 == 0) goto L_0x02de;
        L_0x021b:
            r0 = r18;
            r1 = r0.f2061s;	 Catch:{ all -> 0x01d5 }
            r1 = r1.mo9486c();	 Catch:{ all -> 0x01d5 }
            r1 = (javax.microedition.khronos.opengles.GL10) r1;	 Catch:{ all -> 0x01d5 }
            r11 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x01d5 }
            r11.mo9504a(r1);	 Catch:{ all -> 0x01d5 }
            r11 = 0;
            r13 = r1;
        L_0x022e:
            if (r12 == 0) goto L_0x024a;
        L_0x0230:
            r0 = r18;
            r1 = r0.f2062t;	 Catch:{ all -> 0x01d5 }
            r1 = r1.get();	 Catch:{ all -> 0x01d5 }
            r1 = (com.autonavi.amap.mapcore.interfaces.GLTextureView) r1;	 Catch:{ all -> 0x01d5 }
            if (r1 == 0) goto L_0x0249;
        L_0x023c:
            r1 = r1.mRenderer;	 Catch:{ all -> 0x01d5 }
            r0 = r18;
            r12 = r0.f2061s;	 Catch:{ all -> 0x01d5 }
            r12 = r12.f2040d;	 Catch:{ all -> 0x01d5 }
            r1.onSurfaceCreated(r13, r12);	 Catch:{ all -> 0x01d5 }
        L_0x0249:
            r12 = 0;
        L_0x024a:
            if (r9 == 0) goto L_0x0260;
        L_0x024c:
            r0 = r18;
            r1 = r0.f2062t;	 Catch:{ all -> 0x01d5 }
            r1 = r1.get();	 Catch:{ all -> 0x01d5 }
            r1 = (com.autonavi.amap.mapcore.interfaces.GLTextureView) r1;	 Catch:{ all -> 0x01d5 }
            if (r1 == 0) goto L_0x025f;
        L_0x0258:
            r1 = r1.mRenderer;	 Catch:{ all -> 0x01d5 }
            r1.onSurfaceChanged(r13, r6, r5);	 Catch:{ all -> 0x01d5 }
        L_0x025f:
            r9 = 0;
        L_0x0260:
            r0 = r18;
            r1 = r0.f2062t;	 Catch:{ all -> 0x01d5 }
            r1 = r1.get();	 Catch:{ all -> 0x01d5 }
            r1 = (com.autonavi.amap.mapcore.interfaces.GLTextureView) r1;	 Catch:{ all -> 0x01d5 }
            if (r1 == 0) goto L_0x0273;
        L_0x026c:
            r1 = r1.mRenderer;	 Catch:{ all -> 0x01d5 }
            r1.onDrawFrame(r13);	 Catch:{ all -> 0x01d5 }
        L_0x0273:
            r0 = r18;
            r1 = r0.f2061s;	 Catch:{ all -> 0x01d5 }
            r1 = r1.mo9487d();	 Catch:{ all -> 0x01d5 }
            switch(r1) {
                case 12288: goto L_0x0297;
                case 12302: goto L_0x02d6;
                default: goto L_0x027e;
            };	 Catch:{ all -> 0x01d5 }
        L_0x027e:
            r14 = "GLThread";
            r15 = "eglSwapBuffers";
            com.autonavi.amap.mapcore.interfaces.GLTextureView.C0860e.m2851a(r14, r15, r1);	 Catch:{ all -> 0x01d5 }
            r14 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x01d5 }
            monitor-enter(r14);	 Catch:{ all -> 0x01d5 }
            r1 = 1;
            r0 = r18;
            r0.f2048f = r1;	 Catch:{ all -> 0x02d8 }
            r1 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x02d8 }
            r1.notifyAll();	 Catch:{ all -> 0x02d8 }
            monitor-exit(r14);	 Catch:{ all -> 0x02d8 }
        L_0x0297:
            if (r8 == 0) goto L_0x02f5;
        L_0x0299:
            r1 = 1;
        L_0x029a:
            r2 = r4;
            r14 = r13;
            r4 = r6;
            r6 = r1;
            r17 = r7;
            r7 = r8;
            r8 = r9;
            r9 = r10;
            r10 = r11;
            r11 = r3;
            r3 = r5;
            r5 = r17;
            goto L_0x0031;
        L_0x02aa:
            r1 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x02aa }
            throw r1;	 Catch:{ all -> 0x01d5 }
        L_0x02ad:
            r3 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x01d5 }
            monitor-enter(r3);	 Catch:{ all -> 0x01d5 }
            r13 = 1;
            r0 = r18;
            r0.f2052j = r13;	 Catch:{ all -> 0x02d3 }
            r13 = 1;
            r0 = r18;
            r0.f2048f = r13;	 Catch:{ all -> 0x02d3 }
            r13 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x02d3 }
            r13.notifyAll();	 Catch:{ all -> 0x02d3 }
            monitor-exit(r3);	 Catch:{ all -> 0x02d3 }
            r3 = r5;
            r5 = r7;
            r7 = r8;
            r8 = r9;
            r9 = r10;
            r10 = r11;
            r11 = r1;
            r17 = r2;
            r2 = r4;
            r4 = r6;
            r6 = r17;
            goto L_0x0031;
        L_0x02d3:
            r1 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x02d3 }
            throw r1;	 Catch:{ all -> 0x01d5 }
        L_0x02d6:
            r10 = 1;
            goto L_0x0297;
        L_0x02d8:
            r1 = move-exception;
            monitor-exit(r14);	 Catch:{ all -> 0x02d8 }
            throw r1;	 Catch:{ all -> 0x01d5 }
        L_0x02db:
            r1 = move-exception;
            monitor-exit(r2);	 Catch:{ all -> 0x02db }
            throw r1;
        L_0x02de:
            r13 = r14;
            goto L_0x022e;
        L_0x02e1:
            r3 = r1;
            goto L_0x0219;
        L_0x02e4:
            r10 = r11;
            r17 = r4;
            r4 = r7;
            r7 = r1;
            r1 = r3;
            r3 = r17;
            goto L_0x0190;
        L_0x02ee:
            r1 = r8;
            r8 = r10;
            goto L_0x0174;
        L_0x02f2:
            r13 = r1;
            goto L_0x00a4;
        L_0x02f5:
            r1 = r2;
            goto L_0x029a;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.autonavi.amap.mapcore.interfaces.GLTextureView$C0861f.m2863l():void");
        }

        /* renamed from: a */
        public boolean mo9493a() {
            return this.f2050h && this.f2051i && m2864m();
        }

        /* renamed from: m */
        private boolean m2864m() {
            return !this.f2046d && this.f2047e && !this.f2048f && this.f2054l > 0 && this.f2055m > 0 && (this.f2057o || this.f2056n == 1);
        }

        /* renamed from: a */
        public void mo9490a(int i) {
            if (i < 0 || i > 1) {
                throw new IllegalArgumentException("renderMode");
            }
            synchronized (GLTextureView.sGLThreadManager) {
                this.f2056n = i;
                GLTextureView.sGLThreadManager.notifyAll();
            }
        }

        /* renamed from: b */
        public int mo9494b() {
            int i;
            synchronized (GLTextureView.sGLThreadManager) {
                i = this.f2056n;
            }
            return i;
        }

        /* renamed from: c */
        public void mo9495c() {
            synchronized (GLTextureView.sGLThreadManager) {
                this.f2057o = true;
                GLTextureView.sGLThreadManager.notifyAll();
            }
        }

        /* renamed from: d */
        public void mo9496d() {
            synchronized (GLTextureView.sGLThreadManager) {
                this.f2047e = true;
                this.f2052j = false;
                GLTextureView.sGLThreadManager.notifyAll();
                while (this.f2049g && !this.f2052j && !this.f2044b) {
                    try {
                        GLTextureView.sGLThreadManager.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        /* renamed from: e */
        public void mo9497e() {
            synchronized (GLTextureView.sGLThreadManager) {
                this.f2047e = false;
                GLTextureView.sGLThreadManager.notifyAll();
                while (!this.f2049g && !this.f2044b) {
                    try {
                        GLTextureView.sGLThreadManager.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        /* renamed from: f */
        public void mo9498f() {
            synchronized (GLTextureView.sGLThreadManager) {
                this.f2045c = true;
                GLTextureView.sGLThreadManager.notifyAll();
                while (!this.f2044b && !this.f2046d) {
                    try {
                        GLTextureView.sGLThreadManager.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        /* renamed from: g */
        public void mo9499g() {
            synchronized (GLTextureView.sGLThreadManager) {
                this.f2045c = false;
                this.f2057o = true;
                this.f2058p = false;
                GLTextureView.sGLThreadManager.notifyAll();
                while (!this.f2044b && this.f2046d && !this.f2058p) {
                    try {
                        GLTextureView.sGLThreadManager.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        /* renamed from: a */
        public void mo9491a(int i, int i2) {
            synchronized (GLTextureView.sGLThreadManager) {
                this.f2054l = i;
                this.f2055m = i2;
                this.f2060r = true;
                this.f2057o = true;
                this.f2058p = false;
                GLTextureView.sGLThreadManager.notifyAll();
                while (!this.f2044b && !this.f2046d && !this.f2058p && mo9493a()) {
                    try {
                        GLTextureView.sGLThreadManager.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        /* renamed from: h */
        public void mo9500h() {
            synchronized (GLTextureView.sGLThreadManager) {
                this.f2043a = true;
                GLTextureView.sGLThreadManager.notifyAll();
                while (!this.f2044b) {
                    try {
                        GLTextureView.sGLThreadManager.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        /* renamed from: i */
        public void mo9501i() {
            this.f2053k = true;
            GLTextureView.sGLThreadManager.notifyAll();
        }

        /* renamed from: a */
        public void mo9492a(Runnable runnable) {
            if (runnable == null) {
                throw new IllegalArgumentException("r must not be null");
            }
            synchronized (GLTextureView.sGLThreadManager) {
                this.f2059q.add(runnable);
                GLTextureView.sGLThreadManager.notifyAll();
            }
        }
    }

    /* renamed from: com.autonavi.amap.mapcore.interfaces.GLTextureView$g */
    private static class C0862g {
        /* renamed from: a */
        private static String f2063a = "GLThreadManager";
        /* renamed from: b */
        private boolean f2064b;
        /* renamed from: c */
        private int f2065c;
        /* renamed from: d */
        private boolean f2066d;
        /* renamed from: e */
        private boolean f2067e;
        /* renamed from: f */
        private boolean f2068f;
        /* renamed from: g */
        private C0861f f2069g;

        private C0862g() {
        }

        /* synthetic */ C0862g(C1279a c1279a) {
            this();
        }

        /* renamed from: a */
        public synchronized void mo9503a(C0861f c0861f) {
            c0861f.f2044b = true;
            if (this.f2069g == c0861f) {
                this.f2069g = null;
            }
            notifyAll();
        }

        /* renamed from: b */
        public boolean mo9507b(C0861f c0861f) {
            if (this.f2069g == c0861f || this.f2069g == null) {
                this.f2069g = c0861f;
                notifyAll();
                return true;
            }
            m2877c();
            if (this.f2067e) {
                return true;
            }
            if (this.f2069g != null) {
                this.f2069g.mo9501i();
            }
            return false;
        }

        /* renamed from: c */
        public void mo9508c(C0861f c0861f) {
            if (this.f2069g == c0861f) {
                this.f2069g = null;
            }
            notifyAll();
        }

        /* renamed from: a */
        public synchronized boolean mo9505a() {
            return this.f2068f;
        }

        /* renamed from: b */
        public synchronized boolean mo9506b() {
            m2877c();
            return !this.f2067e;
        }

        /* renamed from: a */
        public synchronized void mo9504a(GL10 gl10) {
            boolean z = true;
            synchronized (this) {
                if (!this.f2066d) {
                    m2877c();
                    String glGetString = gl10.glGetString(7937);
                    if (this.f2065c < 131072) {
                        this.f2067e = !glGetString.startsWith("Q3Dimension MSM7500 ");
                        notifyAll();
                    }
                    if (this.f2067e) {
                        z = false;
                    }
                    this.f2068f = z;
                    this.f2066d = true;
                }
            }
        }

        /* renamed from: c */
        private void m2877c() {
            if (!this.f2064b) {
                this.f2065c = 131072;
                if (this.f2065c >= 131072) {
                    this.f2067e = true;
                }
                this.f2064b = true;
            }
        }
    }

    /* renamed from: com.autonavi.amap.mapcore.interfaces.GLTextureView$h */
    static class C0863h extends Writer {
        /* renamed from: a */
        private StringBuilder f2070a = new StringBuilder();

        C0863h() {
        }

        public void close() {
            m2884a();
        }

        public void flush() {
            m2884a();
        }

        public void write(char[] cArr, int i, int i2) {
            for (int i3 = 0; i3 < i2; i3++) {
                char c = cArr[i + i3];
                if (c == 10) {
                    m2884a();
                } else {
                    this.f2070a.append(c);
                }
            }
        }

        /* renamed from: a */
        private void m2884a() {
            if (this.f2070a.length() > 0) {
                Log.v(GLTextureView.TAG, this.f2070a.toString());
                this.f2070a.delete(0, this.f2070a.length());
            }
        }
    }

    /* renamed from: com.autonavi.amap.mapcore.interfaces.GLTextureView$i */
    private class C0864i extends C0857b {
        public C0864i(boolean z) {
            int i;
            if (z) {
                i = 16;
            } else {
                i = 0;
            }
            super(8, 8, 8, 0, i, 0);
        }
    }

    public GLTextureView(Context context) {
        super(context);
        init();
    }

    public GLTextureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    /* Access modifiers changed, original: protected */
    public void finalize() throws Throwable {
        try {
            if (this.mGLThread != null) {
                this.mGLThread.mo9500h();
            }
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
        }
    }

    private void init() {
        setSurfaceTextureListener(this);
    }

    public void setGLWrapper(GLWrapper gLWrapper) {
        this.mGLWrapper = gLWrapper;
    }

    public void setDebugFlags(int i) {
        this.mDebugFlags = i;
    }

    public int getDebugFlags() {
        return this.mDebugFlags;
    }

    public void setPreserveEGLContextOnPause(boolean z) {
        this.mPreserveEGLContextOnPause = z;
    }

    public boolean getPreserveEGLContextOnPause() {
        return this.mPreserveEGLContextOnPause;
    }

    public void setRenderer(Renderer renderer) {
        checkRenderThreadState();
        if (this.mEGLConfigChooser == null) {
            this.mEGLConfigChooser = new C0864i(true);
        }
        if (this.mEGLContextFactory == null) {
            this.mEGLContextFactory = new C0858c(this, null);
        }
        if (this.mEGLWindowSurfaceFactory == null) {
            this.mEGLWindowSurfaceFactory = new C0859d();
        }
        this.mRenderer = renderer;
        this.mGLThread = new C0861f(this.mThisWeakRef);
        this.mGLThread.start();
    }

    public void setEGLContextFactory(EGLContextFactory eGLContextFactory) {
        checkRenderThreadState();
        this.mEGLContextFactory = eGLContextFactory;
    }

    public void setEGLWindowSurfaceFactory(EGLWindowSurfaceFactory eGLWindowSurfaceFactory) {
        checkRenderThreadState();
        this.mEGLWindowSurfaceFactory = eGLWindowSurfaceFactory;
    }

    public void setEGLConfigChooser(EGLConfigChooser eGLConfigChooser) {
        checkRenderThreadState();
        this.mEGLConfigChooser = eGLConfigChooser;
    }

    public void setEGLConfigChooser(boolean z) {
        setEGLConfigChooser(new C0864i(z));
    }

    public void setEGLConfigChooser(int i, int i2, int i3, int i4, int i5, int i6) {
        setEGLConfigChooser(new C0857b(i, i2, i3, i4, i5, i6));
    }

    public void setEGLContextClientVersion(int i) {
        checkRenderThreadState();
        this.mEGLContextClientVersion = i;
    }

    public void setRenderMode(int i) {
        this.mGLThread.mo9490a(i);
    }

    public void requestRender() {
        this.mGLThread.mo9495c();
    }

    public int getRenderMode() {
        return this.mGLThread.mo9494b();
    }

    public void onPause() {
        this.mGLThread.mo9498f();
    }

    public void onResume() {
        this.mGLThread.mo9499g();
    }

    public void queueEvent(Runnable runnable) {
        this.mGLThread.mo9492a(runnable);
    }

    /* Access modifiers changed, original: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mDetached && this.mRenderer != null) {
            int b;
            if (this.mGLThread != null) {
                b = this.mGLThread.mo9494b();
            } else {
                b = 1;
            }
            this.mGLThread = new C0861f(this.mThisWeakRef);
            if (b != 1) {
                this.mGLThread.mo9490a(b);
            }
            this.mGLThread.start();
        }
        this.mDetached = false;
    }

    /* Access modifiers changed, original: protected */
    public void onDetachedFromWindow() {
        if (this.mGLThread != null) {
            this.mGLThread.mo9500h();
        }
        this.mDetached = true;
        super.onDetachedFromWindow();
    }

    private void checkRenderThreadState() {
        if (this.mGLThread != null) {
            throw new IllegalStateException("setRenderer has already been called for this instance.");
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        this.mGLThread.mo9496d();
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.mGLThread.mo9497e();
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        this.mGLThread.mo9491a(i, i2);
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    /* Access modifiers changed, original: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        onSurfaceTextureSizeChanged(getSurfaceTexture(), i3 - i, i4 - i2);
        super.onLayout(z, i, i2, i3, i4);
    }
}
