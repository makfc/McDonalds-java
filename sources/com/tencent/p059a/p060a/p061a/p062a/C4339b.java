package com.tencent.p059a.p060a.p061a.p062a;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/* renamed from: com.tencent.a.a.a.a.b */
final class C4339b extends C4338f {
    C4339b(Context context) {
        super(context);
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: a */
    public final void mo33757a(String str) {
        synchronized (this) {
            Log.i("MID", "write mid to InternalStorage");
            C4337a.m7847d(Environment.getExternalStorageDirectory() + "/" + C4344h.m7876f("6X8Y4XdM2Vhvn0I="));
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(Environment.getExternalStorageDirectory(), C4344h.m7876f("6X8Y4XdM2Vhvn0KfzcEatGnWaNU="))));
                bufferedWriter.write(C4344h.m7876f("4kU71lN96TJUomD1vOU9lgj9Tw==") + "," + str);
                bufferedWriter.write("\n");
                bufferedWriter.close();
            } catch (Exception e) {
                Log.w("MID", e);
            }
        }
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: a */
    public final boolean mo33758a() {
        return C4344h.m7872a(this.f6769a, "android.permission.WRITE_EXTERNAL_STORAGE") && Environment.getExternalStorageState().equals("mounted");
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: b */
    public final String mo33759b() {
        String str;
        synchronized (this) {
            Log.i("MID", "read mid from InternalStorage");
            try {
                for (String str2 : C4337a.m7846a(new File(Environment.getExternalStorageDirectory(), C4344h.m7876f("6X8Y4XdM2Vhvn0KfzcEatGnWaNU=")))) {
                    String[] split = str2.split(",");
                    if (split.length == 2 && split[0].equals(C4344h.m7876f("4kU71lN96TJUomD1vOU9lgj9Tw=="))) {
                        Log.i("MID", "read mid from InternalStorage:" + split[1]);
                        str2 = split[1];
                        break;
                    }
                }
                str2 = null;
            } catch (IOException e) {
                Log.w("MID", e);
                str2 = null;
            }
        }
        return str2;
    }
}
