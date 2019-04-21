package com.aps;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.aps.ai */
public class C1221ai {
    /* renamed from: a */
    protected File f4228a;
    /* renamed from: b */
    protected int[] f4229b;
    /* renamed from: c */
    private ArrayList f4230c;
    /* renamed from: d */
    private boolean f4231d = false;

    protected C1221ai(File file, ArrayList arrayList, int[] iArr) {
        this.f4228a = file;
        this.f4230c = arrayList;
        this.f4229b = iArr;
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: a */
    public final void mo13089a(boolean z) {
        this.f4231d = z;
    }

    /* renamed from: a */
    public byte[] mo13090a() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        Iterator it = this.f4230c.iterator();
        while (it.hasNext()) {
            byte[] bArr = (byte[]) it.next();
            try {
                dataOutputStream.writeInt(bArr.length);
                dataOutputStream.write(bArr);
            } catch (IOException e) {
            }
        }
        try {
            byteArrayOutputStream.close();
            dataOutputStream.close();
        } catch (IOException e2) {
        }
        return byteArrayOutputStream.toByteArray();
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: b */
    public final boolean mo13091b() {
        return this.f4231d;
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: c */
    public final int mo13092c() {
        if (this.f4230c == null) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.f4230c.size(); i2++) {
            i += this.f4230c.get(i2) != null ? ((byte[]) this.f4230c.get(i2)).length : 0;
        }
        return i;
    }
}
