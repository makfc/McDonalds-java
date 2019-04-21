package com.aps;

import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.telephony.NeighboringCellInfo;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.zip.GZIPOutputStream;

/* renamed from: com.aps.aj */
public class C1222aj {
    /* renamed from: a */
    private Context f4232a;
    /* renamed from: b */
    private int f4233b = 0;
    /* renamed from: c */
    private int f4234c = 0;
    /* renamed from: d */
    private int f4235d = 0;

    protected C1222aj(Context context) {
        this.f4232a = context;
        mo13095a(768);
    }

    /* renamed from: a */
    private static int m5332a(int i, int i2) {
        return i < i2 ? i : i2;
    }

    /* renamed from: a */
    protected static C1215ac m5333a(Location location, C1225am c1225am, int i, byte b, long j, boolean z) {
        C1215ac c1215ac = new C1215ac();
        if (i <= 0 || i > 3 || c1225am == null) {
            return null;
        }
        int i2;
        C1273z c1273z;
        int i3;
        Object obj = (i == 1 || i == 3) ? 1 : null;
        Object obj2 = (i == 2 || i == 3) ? 1 : null;
        byte[] bytes = c1225am.mo13124p().getBytes();
        System.arraycopy(bytes, 0, c1215ac.f4199c, 0, C1222aj.m5332a(bytes.length, c1215ac.f4199c.length));
        bytes = c1225am.mo13112f().getBytes();
        System.arraycopy(bytes, 0, c1215ac.f4203g, 0, C1222aj.m5332a(bytes.length, c1215ac.f4203g.length));
        bytes = c1225am.mo13113g().getBytes();
        System.arraycopy(bytes, 0, c1215ac.f4197a, 0, C1222aj.m5332a(bytes.length, c1215ac.f4197a.length));
        bytes = c1225am.mo13116h().getBytes();
        System.arraycopy(bytes, 0, c1215ac.f4198b, 0, C1222aj.m5332a(bytes.length, c1215ac.f4198b.length));
        c1215ac.f4200d = (short) c1225am.mo13125q();
        c1215ac.f4201e = (short) c1225am.mo13126r();
        c1215ac.f4202f = (byte) c1225am.mo13127s();
        bytes = c1225am.mo13128t().getBytes();
        System.arraycopy(bytes, 0, c1215ac.f4204h, 0, C1222aj.m5332a(bytes.length, c1215ac.f4204h.length));
        long j2 = j / 1000;
        Object obj3 = (location == null || !c1225am.mo13110e()) ? null : 1;
        C1273z c1273z2;
        if (obj3 != null) {
            c1273z2 = new C1273z();
            c1273z2.f4552b = (int) j2;
            C1214ab c1214ab = new C1214ab();
            c1214ab.f4186a = (int) (location.getLongitude() * 1000000.0d);
            c1214ab.f4187b = (int) (location.getLatitude() * 1000000.0d);
            c1214ab.f4188c = (int) location.getAltitude();
            c1214ab.f4189d = (int) location.getAccuracy();
            c1214ab.f4190e = (int) location.getSpeed();
            c1214ab.f4191f = (short) ((int) location.getBearing());
            if (Build.MODEL.equals("sdk") || (C1225am.m5380b(c1225am.mo13133y()) && C1213aa.f4146b)) {
                c1214ab.f4192g = (byte) 1;
            } else {
                c1214ab.f4192g = (byte) 0;
            }
            c1214ab.f4193h = b;
            c1214ab.f4194i = System.currentTimeMillis();
            c1214ab.f4195j = c1225am.mo13123o();
            c1273z2.f4553c = c1214ab;
            i2 = 1;
            c1215ac.f4206j.add(c1273z2);
        } else if (!z) {
            return null;
        } else {
            c1273z2 = new C1273z();
            c1273z2.f4552b = (int) j2;
            C1217ae c1217ae = new C1217ae();
            c1217ae.f4209a = c1225am.mo13132x();
            for (i2 = 0; i2 < c1217ae.f4209a; i2++) {
                C1218af c1218af = new C1218af();
                c1218af.f4212a = (byte) c1225am.mo13099a(i2).length();
                System.arraycopy(c1225am.mo13099a(i2).getBytes(), 0, c1218af.f4213b, 0, c1218af.f4212a);
                c1218af.f4214c = c1225am.mo13102b(i2);
                c1218af.f4215d = c1225am.mo13105c(i2);
                c1218af.f4216e = c1225am.mo13107d(i2);
                c1218af.f4217f = c1225am.mo13109e(i2);
                c1218af.f4218g = c1225am.mo13111f(i2);
                c1218af.f4219h = (byte) c1225am.mo13114g(i2).length();
                System.arraycopy(c1225am.mo13114g(i2).getBytes(), 0, c1218af.f4220i, 0, c1218af.f4219h);
                c1218af.f4221j = c1225am.mo13115h(i2);
                c1217ae.f4210b.add(c1218af);
            }
            c1273z2.f4557g = c1217ae;
            i2 = 1;
            c1215ac.f4206j.add(c1273z2);
        }
        if (!(!c1225am.mo13106c() || c1225am.mo13117i() || obj == null || z)) {
            c1273z = new C1273z();
            c1273z.f4552b = (int) j2;
            C1271x c1271x = new C1271x();
            List a = c1225am.mo13100a(location.getSpeed());
            if (a != null && a.size() >= 3) {
                c1271x.f4540a = (short) ((Integer) a.get(0)).intValue();
                c1271x.f4541b = ((Integer) a.get(1)).intValue();
            }
            c1271x.f4542c = c1225am.mo13120l();
            List m = c1225am.mo13121m();
            c1271x.f4543d = (byte) m.size();
            i2 = 0;
            while (true) {
                i3 = i2;
                if (i3 >= m.size()) {
                    break;
                }
                C1224al c1224al = new C1224al();
                c1224al.f4243a = (short) ((NeighboringCellInfo) m.get(i3)).getLac();
                c1224al.f4244b = ((NeighboringCellInfo) m.get(i3)).getCid();
                c1224al.f4245c = (byte) ((NeighboringCellInfo) m.get(i3)).getRssi();
                c1271x.f4544e.add(c1224al);
                i2 = i3 + 1;
            }
            c1273z.f4554d = c1271x;
            i2 = 2;
            c1215ac.f4206j.add(c1273z);
        }
        i3 = i2;
        if (c1225am.mo13106c() && c1225am.mo13117i() && obj != null && !z) {
            C1273z c1273z3 = new C1273z();
            c1273z3.f4552b = (int) j2;
            C1223ak c1223ak = new C1223ak();
            List b2 = c1225am.mo13103b(location.getSpeed());
            if (b2 != null && b2.size() >= 6) {
                c1223ak.f4236a = ((Integer) b2.get(3)).intValue();
                c1223ak.f4237b = ((Integer) b2.get(4)).intValue();
                c1223ak.f4238c = (short) ((Integer) b2.get(0)).intValue();
                c1223ak.f4239d = (short) ((Integer) b2.get(1)).intValue();
                c1223ak.f4240e = ((Integer) b2.get(2)).intValue();
                c1223ak.f4241f = c1225am.mo13120l();
            }
            c1273z3.f4555e = c1223ak;
            i3++;
            c1215ac.f4206j.add(c1273z3);
        }
        if (!(!c1225am.mo13108d() || obj2 == null || z)) {
            c1273z = new C1273z();
            C1219ag c1219ag = new C1219ag();
            List u = c1225am.mo13129u();
            c1273z.f4552b = (int) (((Long) u.get(0)).longValue() / 1000);
            c1219ag.f4222a = (byte) (u.size() - 1);
            i2 = 1;
            while (true) {
                int i4 = i2;
                if (i4 >= u.size()) {
                    break;
                }
                List list = (List) u.get(i4);
                if (list != null && list.size() >= 3) {
                    C1220ah c1220ah = new C1220ah();
                    bytes = ((String) list.get(0)).getBytes();
                    System.arraycopy(bytes, 0, c1220ah.f4225a, 0, C1222aj.m5332a(bytes.length, c1220ah.f4225a.length));
                    c1220ah.f4226b = (short) ((Integer) list.get(1)).intValue();
                    bytes = ((String) list.get(2)).getBytes();
                    System.arraycopy(bytes, 0, c1220ah.f4227c, 0, C1222aj.m5332a(bytes.length, c1220ah.f4227c.length));
                    c1219ag.f4223b.add(c1220ah);
                }
                i2 = i4 + 1;
            }
            c1273z.f4556f = c1219ag;
            i3++;
            c1215ac.f4206j.add(c1273z);
        }
        c1215ac.f4205i = (short) i3;
        return (i3 >= 2 || z) ? c1215ac : null;
    }

    /* renamed from: a */
    protected static File m5334a(Context context) {
        return new File(Environment.getExternalStorageDirectory().getPath() + ("/Android/data/" + context.getPackageName() + "/files/"));
    }

    /* renamed from: a */
    public static Object m5335a(Object obj, String str, Object... objArr) {
        Class cls = obj.getClass();
        Class[] clsArr = new Class[objArr.length];
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            clsArr[i] = objArr[i].getClass();
            if (clsArr[i] == Integer.class) {
                clsArr[i] = Integer.TYPE;
            }
        }
        Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
        if (!declaredMethod.isAccessible()) {
            declaredMethod.setAccessible(true);
        }
        return declaredMethod.invoke(obj, objArr);
    }

    /* renamed from: a */
    private static ArrayList m5336a(File[] fileArr) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < fileArr.length) {
            if (fileArr[i].isFile() && fileArr[i].getName().length() == 10 && TextUtils.isDigitsOnly(fileArr[i].getName())) {
                arrayList.add(fileArr[i]);
            }
            i++;
        }
        return arrayList;
    }

    /* renamed from: a */
    protected static byte[] m5337a(BitSet bitSet) {
        byte[] bArr = new byte[(bitSet.size() / 8)];
        for (int i = 0; i < bitSet.size(); i++) {
            int i2 = i / 8;
            bArr[i2] = (byte) (((bitSet.get(i) ? 1 : 0) << (7 - (i % 8))) | bArr[i2]);
        }
        return bArr;
    }

    /* renamed from: a */
    protected static byte[] m5338a(byte[] bArr) {
        byte[] bArr2 = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.finish();
            gZIPOutputStream.close();
            bArr2 = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return bArr2;
        } catch (Exception e) {
            return bArr2;
        }
    }

    /* renamed from: a */
    protected static byte[] m5339a(byte[] bArr, int i) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        int indexOf = new String(bArr).indexOf(0);
        if (indexOf <= 0) {
            i = 1;
        } else if (indexOf + 1 <= i) {
            i = indexOf + 1;
        }
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 0, bArr2, 0, i);
        bArr2[i - 1] = (byte) 0;
        return bArr2;
    }

    /* renamed from: b */
    public static int m5340b(Object obj, String str, Object... objArr) {
        Class cls = obj.getClass();
        Class[] clsArr = new Class[objArr.length];
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            clsArr[i] = objArr[i].getClass();
            if (clsArr[i] == Integer.class) {
                clsArr[i] = Integer.TYPE;
            }
        }
        Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
        if (!declaredMethod.isAccessible()) {
            declaredMethod.setAccessible(true);
        }
        return ((Integer) declaredMethod.invoke(obj, objArr)).intValue();
    }

    /* renamed from: b */
    protected static BitSet m5341b(byte[] bArr) {
        BitSet bitSet = new BitSet(bArr.length << 3);
        int i = 0;
        for (byte b : bArr) {
            int i2 = 7;
            while (i2 >= 0) {
                int i3 = i + 1;
                bitSet.set(i, ((b & (1 << i2)) >> i2) == 1);
                i2--;
                i = i3;
            }
        }
        return bitSet;
    }

    /* renamed from: c */
    private File m5342c(long j) {
        boolean z = false;
        if (Process.myUid() == 1000) {
            return null;
        }
        File file;
        boolean equals;
        try {
            equals = "mounted".equals(Environment.getExternalStorageState());
        } catch (Exception e) {
            equals = z;
        }
        if (!C1222aj.m5343c() || equals) {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            if (((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize()) <= ((long) (this.f4234c / 2))) {
                return null;
            }
            File file2 = new File(C1222aj.m5334a(this.f4232a).getPath() + File.separator + "carrierdata");
            if (!(file2.exists() && file2.isDirectory())) {
                file2.mkdirs();
            }
            file = new File(file2.getPath() + File.separator + j);
            try {
                z = file.createNewFile();
            } catch (IOException e2) {
            }
        } else {
            file = null;
        }
        return !z ? null : file;
    }

    /* renamed from: c */
    protected static boolean m5343c() {
        if (VERSION.SDK_INT >= 9) {
            try {
                return ((Boolean) Environment.class.getMethod("isExternalStorageRemovable", null).invoke(null, null)).booleanValue();
            } catch (Exception e) {
            }
        }
        return true;
    }

    /* renamed from: d */
    private File m5344d() {
        if (Process.myUid() == 1000) {
            return null;
        }
        File file;
        boolean equals;
        try {
            equals = "mounted".equals(Environment.getExternalStorageState());
        } catch (Exception e) {
            equals = false;
        }
        if (!C1222aj.m5343c() || equals) {
            File file2 = new File(C1222aj.m5334a(this.f4232a).getPath() + File.separator + "carrierdata");
            if (file2.exists() && file2.isDirectory()) {
                File[] listFiles = file2.listFiles();
                if (listFiles != null && listFiles.length > 0) {
                    ArrayList a = C1222aj.m5336a(listFiles);
                    if (a.size() == 1) {
                        if (((File) a.get(0)).length() < ((long) this.f4235d)) {
                            file = (File) a.get(0);
                            return file;
                        }
                    } else if (a.size() >= 2) {
                        file = (File) a.get(0);
                        File file3 = (File) a.get(1);
                        if (file.getName().compareTo(file3.getName()) <= 0) {
                            file = file3;
                        }
                        return file;
                    }
                }
            }
        }
        file = null;
        return file;
    }

    /* renamed from: e */
    private int m5345e() {
        if (Process.myUid() == 1000) {
            return 0;
        }
        boolean equals;
        try {
            equals = "mounted".equals(Environment.getExternalStorageState());
        } catch (Exception e) {
            equals = false;
        }
        if (C1222aj.m5343c() && !equals) {
            return 0;
        }
        File file = new File(C1222aj.m5334a(this.f4232a).getPath() + File.separator + "carrierdata");
        if (!file.exists() || !file.isDirectory()) {
            return 0;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length <= 0) {
            return 0;
        }
        ArrayList a = C1222aj.m5336a(listFiles);
        return a.size() == 1 ? ((File) a.get(0)).length() <= 0 ? 10 : 1 : a.size() >= 2 ? 2 : 0;
    }

    /* renamed from: f */
    private File m5346f() {
        if (Process.myUid() == 1000) {
            return null;
        }
        File file;
        boolean equals;
        try {
            equals = "mounted".equals(Environment.getExternalStorageState());
        } catch (Exception e) {
            equals = false;
        }
        if (!C1222aj.m5343c() || equals) {
            File a = C1222aj.m5334a(this.f4232a);
            if (a != null) {
                File file2 = new File(a.getPath() + File.separator + "carrierdata");
                if (file2.exists() && file2.isDirectory()) {
                    File[] listFiles = file2.listFiles();
                    if (listFiles != null && listFiles.length > 0) {
                        ArrayList a2 = C1222aj.m5336a(listFiles);
                        if (a2.size() >= 2) {
                            a = (File) a2.get(0);
                            file = (File) a2.get(1);
                            if (a.getName().compareTo(file.getName()) <= 0) {
                                file = a;
                            }
                            return file;
                        }
                    }
                }
            }
        }
        file = null;
        return file;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public int mo13093a() {
        return this.f4233b;
    }

    /* Access modifiers changed, original: protected|declared_synchronized */
    /* renamed from: a */
    public synchronized File mo13094a(long j) {
        File d;
        d = m5344d();
        if (d == null) {
            d = m5342c(j);
        }
        return d;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo13095a(int i) {
        this.f4233b = i;
        this.f4234c = (((this.f4233b << 3) * 1500) + this.f4233b) + 4;
        if (this.f4233b == 256 || this.f4233b == 768) {
            this.f4235d = this.f4234c / 100;
        } else if (this.f4233b == 8736) {
            this.f4235d = this.f4234c - 5000;
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public File mo13096b() {
        return m5346f();
    }

    /* Access modifiers changed, original: protected|declared_synchronized */
    /* renamed from: b */
    public synchronized boolean mo13097b(long j) {
        boolean z = false;
        synchronized (this) {
            int e = m5345e();
            if (e != 0) {
                if (e == 1) {
                    if (m5342c(j) != null) {
                        z = true;
                    }
                } else if (e == 2) {
                    z = true;
                }
            }
        }
        return z;
    }
}
