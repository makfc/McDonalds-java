package com.aps;

import android.support.p000v4.media.TransportMediator;
import android.text.TextUtils;
import java.util.zip.CRC32;

/* renamed from: com.aps.q */
public class Req {
    /* renamed from: A */
    public byte[] f4494A = null;
    /* renamed from: B */
    public String f4495B = null;
    /* renamed from: C */
    public String f4496C = null;
    /* renamed from: D */
    public String f4497D = null;
    /* renamed from: E */
    public String f4498E = null;
    /* renamed from: a */
    public String f4499a = "1";
    /* renamed from: b */
    public short f4500b = (short) 0;
    /* renamed from: c */
    public String f4501c = null;
    /* renamed from: d */
    public String f4502d = null;
    /* renamed from: e */
    public String f4503e = null;
    /* renamed from: f */
    public String f4504f = null;
    /* renamed from: g */
    public String f4505g = null;
    /* renamed from: h */
    public String f4506h = null;
    /* renamed from: i */
    public String f4507i = null;
    /* renamed from: j */
    public String f4508j = null;
    /* renamed from: k */
    public String f4509k = null;
    /* renamed from: l */
    public String f4510l = null;
    /* renamed from: m */
    public String f4511m = null;
    /* renamed from: n */
    public String f4512n = null;
    /* renamed from: o */
    public String f4513o = null;
    /* renamed from: p */
    public String f4514p = null;
    /* renamed from: q */
    public String f4515q = null;
    /* renamed from: r */
    public String f4516r = null;
    /* renamed from: s */
    public String f4517s = null;
    /* renamed from: t */
    public String f4518t = null;
    /* renamed from: u */
    public String f4519u = null;
    /* renamed from: v */
    public String f4520v = null;
    /* renamed from: w */
    public String f4521w = null;
    /* renamed from: x */
    public String f4522x = null;
    /* renamed from: y */
    public String f4523y = null;
    /* renamed from: z */
    public String f4524z = null;

    /* renamed from: a */
    public byte[] mo13286a() {
        byte[] bytes;
        int length;
        byte[] b;
        int length2;
        String[] split;
        m5701b();
        int i = 3072;
        if (this.f4494A != null) {
            i = 3072 + (this.f4494A.length + 1);
        }
        byte[] bArr = new byte[i];
        bArr[0] = Byte.parseByte(this.f4499a);
        byte[] b2 = Storage.m5715b(this.f4500b);
        System.arraycopy(b2, 0, bArr, 1, b2.length);
        i = b2.length + 1;
        try {
            bytes = this.f4501c.getBytes("GBK");
            bArr[i] = (byte) bytes.length;
            i++;
            System.arraycopy(bytes, 0, bArr, i, bytes.length);
            i += bytes.length;
        } catch (Exception e) {
            bArr[i] = (byte) 0;
            i++;
        }
        try {
            bytes = this.f4502d.getBytes("GBK");
            bArr[i] = (byte) bytes.length;
            i++;
            System.arraycopy(bytes, 0, bArr, i, bytes.length);
            i += bytes.length;
        } catch (Exception e2) {
            bArr[i] = (byte) 0;
            i++;
        }
        try {
            bytes = this.f4512n.getBytes("GBK");
            bArr[i] = (byte) bytes.length;
            i++;
            System.arraycopy(bytes, 0, bArr, i, bytes.length);
            i += bytes.length;
        } catch (Exception e3) {
            bArr[i] = (byte) 0;
            i++;
        }
        try {
            bytes = this.f4503e.getBytes("GBK");
            bArr[i] = (byte) bytes.length;
            i++;
            System.arraycopy(bytes, 0, bArr, i, bytes.length);
            i += bytes.length;
        } catch (Exception e4) {
            bArr[i] = (byte) 0;
            i++;
        }
        try {
            bytes = this.f4504f.getBytes("GBK");
            bArr[i] = (byte) bytes.length;
            i++;
            System.arraycopy(bytes, 0, bArr, i, bytes.length);
            i += bytes.length;
        } catch (Exception e5) {
            bArr[i] = (byte) 0;
            i++;
        }
        try {
            bytes = this.f4505g.getBytes("GBK");
            bArr[i] = (byte) bytes.length;
            i++;
            System.arraycopy(bytes, 0, bArr, i, bytes.length);
            i += bytes.length;
        } catch (Exception e6) {
            bArr[i] = (byte) 0;
            i++;
        }
        try {
            bytes = this.f4516r.getBytes("GBK");
            bArr[i] = (byte) bytes.length;
            i++;
            System.arraycopy(bytes, 0, bArr, i, bytes.length);
            i += bytes.length;
        } catch (Exception e7) {
            bArr[i] = (byte) 0;
            i++;
        }
        try {
            bytes = this.f4506h.getBytes("GBK");
            bArr[i] = (byte) bytes.length;
            i++;
            System.arraycopy(bytes, 0, bArr, i, bytes.length);
            i += bytes.length;
        } catch (Exception e8) {
            bArr[i] = (byte) 0;
            i++;
        }
        try {
            bytes = this.f4513o.getBytes("GBK");
            bArr[i] = (byte) bytes.length;
            i++;
            System.arraycopy(bytes, 0, bArr, i, bytes.length);
            i += bytes.length;
        } catch (Exception e9) {
            bArr[i] = (byte) 0;
            i++;
        }
        try {
            bytes = this.f4514p.getBytes("GBK");
            bArr[i] = (byte) bytes.length;
            i++;
            System.arraycopy(bytes, 0, bArr, i, bytes.length);
            i += bytes.length;
        } catch (Exception e10) {
            bArr[i] = (byte) 0;
            i++;
        }
        if (TextUtils.isEmpty(this.f4515q)) {
            bArr[i] = (byte) 0;
            i++;
        } else {
            bytes = m5699a(this.f4515q);
            bArr[i] = (byte) bytes.length;
            i++;
            System.arraycopy(bytes, 0, bArr, i, bytes.length);
            i += bytes.length;
        }
        try {
            bytes = this.f4495B.getBytes("GBK");
            bArr[i] = (byte) bytes.length;
            i++;
            System.arraycopy(bytes, 0, bArr, i, bytes.length);
            i += bytes.length;
        } catch (Exception e11) {
            bArr[i] = (byte) 0;
            i++;
        }
        try {
            bytes = this.f4496C.getBytes("GBK");
            bArr[i] = (byte) bytes.length;
            i++;
            System.arraycopy(bytes, 0, bArr, i, bytes.length);
            i += bytes.length;
        } catch (Exception e12) {
            bArr[i] = (byte) 0;
            i++;
        }
        try {
            bytes = this.f4517s.getBytes("GBK");
            bArr[i] = (byte) bytes.length;
            i++;
            System.arraycopy(bytes, 0, bArr, i, bytes.length);
            i += bytes.length;
        } catch (Exception e13) {
            bArr[i] = (byte) 0;
            i++;
        }
        bArr[i] = Byte.parseByte(this.f4518t);
        i++;
        bArr[i] = Byte.parseByte(this.f4508j);
        i++;
        if (this.f4508j.equals("1")) {
            try {
                bArr[i] = Byte.parseByte(this.f4497D);
                i++;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        if (this.f4508j.equals("1") || this.f4508j.equals("2")) {
            bytes = Storage.m5712a(Integer.parseInt(this.f4509k));
            System.arraycopy(bytes, 0, bArr, i, bytes.length);
            i += bytes.length;
        }
        if (this.f4508j.equals("1") || this.f4508j.equals("2")) {
            bytes = Storage.m5712a(Integer.parseInt(this.f4510l));
            System.arraycopy(bytes, 0, bArr, i, bytes.length);
            i += bytes.length;
        }
        if (this.f4508j.equals("1") || this.f4508j.equals("2")) {
            bytes = Storage.m5716b(this.f4511m);
            System.arraycopy(bytes, 0, bArr, i, bytes.length);
            i += bytes.length;
        }
        bArr[i] = Byte.parseByte(this.f4519u);
        i++;
        if (this.f4519u.equals("1")) {
            bytes = Storage.m5716b(m5700b("mcc"));
            System.arraycopy(bytes, 0, bArr, i, bytes.length);
            i += bytes.length;
            bytes = Storage.m5716b(m5700b("mnc"));
            System.arraycopy(bytes, 0, bArr, i, bytes.length);
            i += bytes.length;
            bytes = Storage.m5716b(m5700b("lac"));
            System.arraycopy(bytes, 0, bArr, i, bytes.length);
            i += bytes.length;
            bytes = Storage.m5714a(m5700b("cellid"));
            System.arraycopy(bytes, 0, bArr, i, bytes.length);
            length = bytes.length + i;
            i = Integer.parseInt(m5700b("signal"));
            if (i > TransportMediator.KEYCODE_MEDIA_PAUSE) {
                i = 0;
            } else if (i < -128) {
                i = 0;
            }
            bArr[length] = (byte) i;
            i = length + 1;
            if (this.f4521w.length() == 0) {
                bArr[i] = (byte) 0;
                i++;
            } else {
                int length3 = this.f4521w.split("\\*").length;
                bArr[i] = (byte) length3;
                i++;
                length = 0;
                while (length < length3) {
                    b = Storage.m5716b(m5698a("lac", length));
                    System.arraycopy(b, 0, bArr, i, b.length);
                    i += b.length;
                    b = Storage.m5714a(m5698a("cellid", length));
                    System.arraycopy(b, 0, bArr, i, b.length);
                    length2 = b.length + i;
                    i = Integer.parseInt(m5698a("signal", length));
                    if (i > TransportMediator.KEYCODE_MEDIA_PAUSE) {
                        i = 0;
                    } else if (i < -128) {
                        i = 0;
                    }
                    bArr[length2] = (byte) i;
                    length++;
                    i = length2 + 1;
                }
            }
        } else if (this.f4519u.equals("2")) {
            bytes = Storage.m5716b(m5700b("mcc"));
            System.arraycopy(bytes, 0, bArr, i, bytes.length);
            i += bytes.length;
            bytes = Storage.m5716b(m5700b("sid"));
            System.arraycopy(bytes, 0, bArr, i, bytes.length);
            i += bytes.length;
            bytes = Storage.m5716b(m5700b("nid"));
            System.arraycopy(bytes, 0, bArr, i, bytes.length);
            i += bytes.length;
            bytes = Storage.m5716b(m5700b("bid"));
            System.arraycopy(bytes, 0, bArr, i, bytes.length);
            i += bytes.length;
            bytes = Storage.m5714a(m5700b("lon"));
            System.arraycopy(bytes, 0, bArr, i, bytes.length);
            i += bytes.length;
            bytes = Storage.m5714a(m5700b("lat"));
            System.arraycopy(bytes, 0, bArr, i, bytes.length);
            length = bytes.length + i;
            i = Integer.parseInt(m5700b("signal"));
            if (i > TransportMediator.KEYCODE_MEDIA_PAUSE) {
                i = 0;
            } else if (i < -128) {
                i = 0;
            }
            bArr[length] = (byte) i;
            i = length + 1;
            bArr[i] = (byte) 0;
            i++;
        }
        if (this.f4522x.length() == 0) {
            bArr[i] = (byte) 0;
            i++;
        } else {
            bArr[i] = (byte) 1;
            i++;
            try {
                split = this.f4522x.split(",");
                b = m5699a(split[0]);
                System.arraycopy(b, 0, bArr, i, b.length);
                i += b.length;
                b = split[2].getBytes("GBK");
                bArr[i] = (byte) b.length;
                i++;
                System.arraycopy(b, 0, bArr, i, b.length);
                i += b.length;
            } catch (Exception e14) {
                bArr[i] = (byte) 0;
                i++;
            } catch (Throwable th2) {
                bytes = m5699a("00:00:00:00:00:00");
                System.arraycopy(bytes, 0, bArr, i, bytes.length);
                i += bytes.length;
                bArr[i] = (byte) 0;
                i++;
                bArr[i] = Byte.parseByte("0");
                i++;
            }
            length = Integer.parseInt(split[1]);
            if (length > TransportMediator.KEYCODE_MEDIA_PAUSE) {
                length = 0;
            } else if (length < -128) {
                length = 0;
            }
            bArr[i] = Byte.parseByte(String.valueOf(length));
            i++;
        }
        String[] split2 = this.f4523y.split("\\*");
        if (TextUtils.isEmpty(this.f4523y) || split2.length == 0) {
            bArr[i] = (byte) 0;
            i++;
        } else {
            bArr[i] = (byte) split2.length;
            i++;
            length2 = 0;
            while (length2 < split2.length) {
                split = split2[length2].split(",");
                byte[] a = m5699a(split[0]);
                System.arraycopy(a, 0, bArr, i, a.length);
                i += a.length;
                try {
                    a = split[2].getBytes("GBK");
                    bArr[i] = (byte) a.length;
                    i++;
                    System.arraycopy(a, 0, bArr, i, a.length);
                    i += a.length;
                } catch (Exception e15) {
                    bArr[i] = (byte) 0;
                    i++;
                }
                length = Integer.parseInt(split[1]);
                if (length > TransportMediator.KEYCODE_MEDIA_PAUSE) {
                    length = 0;
                } else if (length < -128) {
                    length = 0;
                }
                bArr[i] = Byte.parseByte(String.valueOf(length));
                length2++;
                i++;
            }
            if (this.f4498E != null && this.f4498E.length() > 0) {
                try {
                    bytes = Storage.m5715b(Integer.parseInt(this.f4498E));
                    System.arraycopy(bytes, 0, bArr, i, bytes.length);
                    i += bytes.length;
                } catch (Throwable th3) {
                    th3.printStackTrace();
                }
            }
        }
        try {
            Object bytes2 = this.f4524z.getBytes("GBK");
            if (bytes2.length > TransportMediator.KEYCODE_MEDIA_PAUSE) {
                bytes2 = null;
            }
            if (bytes2 == null) {
                bArr[i] = (byte) 0;
                i++;
            } else {
                bArr[i] = (byte) bytes2.length;
                i++;
                System.arraycopy(bytes2, 0, bArr, i, bytes2.length);
                i += bytes2.length;
            }
        } catch (Exception e16) {
            bArr[i] = (byte) 0;
            i++;
        }
        if (this.f4494A != null) {
            length = this.f4494A.length;
        } else {
            length = 0;
        }
        b = Storage.m5715b(length);
        System.arraycopy(b, 0, bArr, i, b.length);
        i += b.length;
        if (length > 0) {
            System.arraycopy(this.f4494A, 0, bArr, i, this.f4494A.length);
            i += this.f4494A.length;
        }
        bytes = new byte[i];
        System.arraycopy(bArr, 0, bytes, 0, i);
        CRC32 crc32 = new CRC32();
        crc32.update(bytes);
        b = Storage.m5713a(crc32.getValue());
        bArr = new byte[(b.length + i)];
        System.arraycopy(bytes, 0, bArr, 0, i);
        System.arraycopy(b, 0, bArr, i, b.length);
        i += b.length;
        return bArr;
    }

    /* renamed from: a */
    private byte[] m5699a(String str) {
        String[] split = str.split(":");
        if (split == null || split.length != 6) {
            String[] strArr = new String[6];
            for (int i = 0; i < strArr.length; i++) {
                strArr[i] = "0";
            }
            split = strArr;
        }
        byte[] bArr = new byte[6];
        for (int i2 = 0; i2 < split.length; i2++) {
            if (split[i2].length() > 2) {
                split[i2] = split[i2].substring(0, 2);
            }
            bArr[i2] = (byte) Integer.parseInt(split[i2], 16);
        }
        return bArr;
    }

    /* renamed from: b */
    private String m5700b(String str) {
        if (!this.f4520v.contains(str + ">")) {
            return "0";
        }
        int indexOf = this.f4520v.indexOf(str + ">");
        return this.f4520v.substring((indexOf + str.length()) + 1, this.f4520v.indexOf("</" + str));
    }

    /* renamed from: a */
    private String m5698a(String str, int i) {
        String[] split = this.f4521w.split("\\*")[i].split(",");
        if (str.equals("lac")) {
            return split[0];
        }
        if (str.equals("cellid")) {
            return split[1];
        }
        if (str.equals("signal")) {
            return split[2];
        }
        return null;
    }

    /* renamed from: b */
    private void m5701b() {
        if (TextUtils.isEmpty(this.f4499a)) {
            this.f4499a = "";
        }
        if (TextUtils.isEmpty(this.f4501c)) {
            this.f4501c = "";
        }
        if (TextUtils.isEmpty(this.f4502d)) {
            this.f4502d = "";
        }
        if (TextUtils.isEmpty(this.f4503e)) {
            this.f4503e = "";
        }
        if (TextUtils.isEmpty(this.f4504f)) {
            this.f4504f = "";
        }
        if (TextUtils.isEmpty(this.f4505g)) {
            this.f4505g = "";
        }
        if (TextUtils.isEmpty(this.f4506h)) {
            this.f4506h = "";
        }
        if (TextUtils.isEmpty(this.f4507i)) {
            this.f4507i = "";
        }
        if (TextUtils.isEmpty(this.f4508j)) {
            this.f4508j = "0";
        } else if (!(this.f4508j.equals("1") || this.f4508j.equals("2"))) {
            this.f4508j = "0";
        }
        if (TextUtils.isEmpty(this.f4497D)) {
            this.f4497D = "0";
        } else if (!(this.f4497D.equals("0") || this.f4497D.equals("1"))) {
            this.f4497D = "0";
        }
        if (TextUtils.isEmpty(this.f4509k)) {
            this.f4509k = "";
        } else {
            this.f4509k = String.valueOf(Double.valueOf(Double.parseDouble(this.f4509k) * 1200000.0d).intValue());
        }
        if (TextUtils.isEmpty(this.f4510l)) {
            this.f4510l = "";
        } else {
            this.f4510l = String.valueOf(Double.valueOf(Double.parseDouble(this.f4510l) * 1000000.0d).intValue());
        }
        if (TextUtils.isEmpty(this.f4511m)) {
            this.f4511m = "";
        }
        if (TextUtils.isEmpty(this.f4512n)) {
            this.f4512n = "";
        }
        if (TextUtils.isEmpty(this.f4513o)) {
            this.f4513o = "";
        }
        if (TextUtils.isEmpty(this.f4514p)) {
            this.f4514p = "";
        }
        if (TextUtils.isEmpty(this.f4515q)) {
            this.f4515q = "";
        }
        if (TextUtils.isEmpty(this.f4516r)) {
            this.f4516r = "";
        }
        if (TextUtils.isEmpty(this.f4495B)) {
            this.f4495B = "";
        }
        if (TextUtils.isEmpty(this.f4496C)) {
            this.f4496C = "";
        }
        if (TextUtils.isEmpty(this.f4517s)) {
            this.f4517s = "";
        }
        if (TextUtils.isEmpty(this.f4518t)) {
            this.f4518t = "0";
        } else if (!(this.f4518t.equals("1") || this.f4518t.equals("2"))) {
            this.f4518t = "0";
        }
        if (TextUtils.isEmpty(this.f4519u)) {
            this.f4519u = "0";
        } else if (!(this.f4519u.equals("1") || this.f4519u.equals("2"))) {
            this.f4519u = "0";
        }
        if (TextUtils.isEmpty(this.f4520v)) {
            this.f4520v = "";
        }
        if (TextUtils.isEmpty(this.f4521w)) {
            this.f4521w = "";
        }
        if (TextUtils.isEmpty(this.f4522x)) {
            this.f4522x = "";
        }
        if (TextUtils.isEmpty(this.f4523y)) {
            this.f4523y = "";
        }
        if (TextUtils.isEmpty(this.f4498E)) {
            this.f4498E = "";
        }
        if (TextUtils.isEmpty(this.f4524z)) {
            this.f4524z = "";
        }
        if (this.f4494A == null) {
            this.f4494A = new byte[0];
        }
    }
}
