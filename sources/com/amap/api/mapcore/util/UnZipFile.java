package com.amap.api.mapcore.util;

import android.text.TextUtils;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* renamed from: com.amap.api.mapcore.util.ce */
public class UnZipFile {
    /* renamed from: a */
    private C0803b f1686a;

    /* compiled from: UnZipFile */
    /* renamed from: com.amap.api.mapcore.util.ce$c */
    public interface C0800c {
        /* renamed from: a */
        void mo9197a();

        /* renamed from: a */
        void mo9198a(long j);
    }

    /* compiled from: UnZipFile */
    /* renamed from: com.amap.api.mapcore.util.ce$a */
    public static class C0802a {
        /* renamed from: a */
        public boolean f1679a = false;
    }

    /* compiled from: UnZipFile */
    /* renamed from: com.amap.api.mapcore.util.ce$b */
    private class C0803b {
        /* renamed from: b */
        private String f1681b;
        /* renamed from: c */
        private String f1682c;
        /* renamed from: d */
        private IUnZipListener f1683d = null;
        /* renamed from: e */
        private C0802a f1684e = new C0802a();
        /* renamed from: f */
        private String f1685f;

        public C0803b(IUnzipItem iUnzipItem, IUnZipListener iUnZipListener) {
            this.f1681b = iUnzipItem.mo8840A();
            this.f1682c = iUnzipItem.mo8841B();
            this.f1683d = iUnZipListener;
        }

        /* renamed from: a */
        public void mo9200a(String str) {
            if (str.length() > 1) {
                this.f1685f = str;
            }
        }

        /* renamed from: a */
        public String mo9199a() {
            return this.f1681b;
        }

        /* renamed from: b */
        public String mo9201b() {
            return this.f1682c;
        }

        /* renamed from: c */
        public String mo9202c() {
            return this.f1685f;
        }

        /* renamed from: d */
        public IUnZipListener mo9203d() {
            return this.f1683d;
        }

        /* renamed from: e */
        public C0802a mo9204e() {
            return this.f1684e;
        }

        /* renamed from: f */
        public void mo9205f() {
            this.f1684e.f1679a = true;
        }
    }

    public UnZipFile(IUnzipItem iUnzipItem, IUnZipListener iUnZipListener) {
        this.f1686a = new C0803b(iUnzipItem, iUnZipListener);
    }

    /* renamed from: a */
    public void mo9206a() {
        if (this.f1686a != null) {
            this.f1686a.mo9205f();
        }
    }

    /* renamed from: b */
    public void mo9207b() {
        if (this.f1686a != null) {
            UnZipFile.m2169a(this.f1686a);
        }
    }

    /* renamed from: a */
    private static void m2169a(C0803b c0803b) {
        if (c0803b != null) {
            final IUnZipListener d = c0803b.mo9203d();
            if (d != null) {
                d.mo8837p();
            }
            String a = c0803b.mo9199a();
            String b = c0803b.mo9201b();
            if (!TextUtils.isEmpty(a) && !TextUtils.isEmpty(b)) {
                File file = new File(a);
                if (file.exists()) {
                    File file2 = new File(b);
                    if (file2.exists() || !file2.mkdirs()) {
                    }
                    C08011 c08011 = new C0800c() {
                        /* renamed from: a */
                        public void mo9198a(long j) {
                            try {
                                if (d != null) {
                                    d.mo8835a(j);
                                }
                            } catch (Exception e) {
                            }
                        }

                        /* renamed from: a */
                        public void mo9197a() {
                            if (d != null) {
                                d.mo8838q();
                            }
                        }
                    };
                    try {
                        if (c0803b.mo9204e().f1679a && d != null) {
                            d.mo8839r();
                        }
                        UnZipFile.m2171a(file, file2, c08011, c0803b);
                        if (c0803b.mo9204e().f1679a) {
                            if (d != null) {
                                d.mo8839r();
                            }
                        } else if (d != null) {
                            d.mo8836b(c0803b.mo9202c());
                        }
                    } catch (Exception e) {
                        if (c0803b.mo9204e().f1679a) {
                            if (d != null) {
                                d.mo8839r();
                            }
                        } else if (d != null) {
                            d.mo8838q();
                        }
                    }
                } else if (c0803b.mo9204e().f1679a) {
                    if (d != null) {
                        d.mo8839r();
                    }
                } else if (d != null) {
                    d.mo8838q();
                }
            } else if (c0803b.mo9204e().f1679a) {
                if (d != null) {
                    d.mo8839r();
                }
            } else if (d != null) {
                d.mo8838q();
            }
        }
    }

    /* renamed from: a */
    private static void m2171a(File file, File file2, C0800c c0800c, C0803b c0803b) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        C0802a e = c0803b.mo9204e();
        long j = 0;
        if (c0800c != null) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                CheckedInputStream checkedInputStream = new CheckedInputStream(fileInputStream, new CRC32());
                ZipInputStream zipInputStream = new ZipInputStream(checkedInputStream);
                while (true) {
                    ZipEntry nextEntry = zipInputStream.getNextEntry();
                    if (nextEntry != null) {
                        if (e != null && e.f1679a) {
                            zipInputStream.closeEntry();
                            zipInputStream.close();
                            checkedInputStream.close();
                            fileInputStream.close();
                            break;
                        }
                        if (!nextEntry.isDirectory()) {
                            if (!UnZipFile.m2173a(nextEntry.getName())) {
                                c0800c.mo9197a();
                                break;
                            }
                            stringBuffer.append(nextEntry.getName()).append(";");
                        }
                        j += nextEntry.getSize();
                        zipInputStream.closeEntry();
                    } else {
                        break;
                    }
                }
                c0803b.mo9200a(stringBuffer.toString());
                zipInputStream.close();
                checkedInputStream.close();
                fileInputStream.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        FileInputStream fileInputStream2 = new FileInputStream(file);
        CheckedInputStream checkedInputStream2 = new CheckedInputStream(fileInputStream2, new CRC32());
        ZipInputStream zipInputStream2 = new ZipInputStream(checkedInputStream2);
        UnZipFile.m2172a(file2, zipInputStream2, j, c0800c, e);
        zipInputStream2.close();
        checkedInputStream2.close();
        fileInputStream2.close();
    }

    /* renamed from: a */
    private static void m2172a(File file, ZipInputStream zipInputStream, long j, C0800c c0800c, C0802a c0802a) throws Exception {
        int i = 0;
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry == null) {
                return;
            }
            if (c0802a == null || !c0802a.f1679a) {
                String str = file.getPath() + File.separator + nextEntry.getName();
                if (UnZipFile.m2173a(str)) {
                    File file2 = new File(str);
                    UnZipFile.m2170a(file2);
                    int a = nextEntry.isDirectory() ? !file2.mkdirs() ? i : i : UnZipFile.m2168a(file2, zipInputStream, (long) i, j, c0800c, c0802a) + i;
                    zipInputStream.closeEntry();
                    i = a;
                } else if (c0800c != null) {
                    c0800c.mo9197a();
                    return;
                } else {
                    return;
                }
            }
            zipInputStream.closeEntry();
            return;
        }
    }

    /* renamed from: a */
    private static boolean m2173a(String str) {
        if (str.contains("../")) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    private static int m2168a(File file, ZipInputStream zipInputStream, long j, long j2, C0800c c0800c, C0802a c0802a) throws Exception {
        int i = 0;
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
        byte[] bArr = new byte[1024];
        while (true) {
            int read = zipInputStream.read(bArr, 0, 1024);
            if (read != -1) {
                if (c0802a != null && c0802a.f1679a) {
                    bufferedOutputStream.close();
                    break;
                }
                bufferedOutputStream.write(bArr, 0, read);
                i += read;
                if (j2 > 0 && c0800c != null) {
                    long j3 = ((((long) i) + j) * 100) / j2;
                    if (c0802a == null || !c0802a.f1679a) {
                        c0800c.mo9198a(j3);
                    }
                }
            } else {
                bufferedOutputStream.close();
                break;
            }
        }
        return i;
    }

    /* renamed from: a */
    private static void m2170a(File file) {
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            UnZipFile.m2170a(parentFile);
            if (!parentFile.mkdir()) {
            }
        }
    }
}
