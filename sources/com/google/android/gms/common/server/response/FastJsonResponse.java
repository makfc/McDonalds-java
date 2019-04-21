package com.google.android.gms.common.server.response;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.server.converter.ConverterWrapper;
import com.google.android.gms.common.util.zzc;
import com.google.android.gms.common.util.zzp;
import com.google.android.gms.common.util.zzq;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class FastJsonResponse {

    public interface zza<I, O> {
        I convertBack(O o);

        int zzud();

        int zzue();
    }

    public static class Field<I, O> extends AbstractSafeParcelable {
        public static final zza CREATOR = new zza();
        private final int mVersionCode;
        protected final int zzasJ;
        protected final boolean zzasK;
        protected final int zzasL;
        protected final boolean zzasM;
        protected final String zzasN;
        protected final int zzasO;
        protected final Class<? extends FastJsonResponse> zzasP;
        protected final String zzasQ;
        private FieldMappingDictionary zzasR;
        private zza<I, O> zzasS;

        Field(int i, int i2, boolean z, int i3, boolean z2, String str, int i4, String str2, ConverterWrapper converterWrapper) {
            this.mVersionCode = i;
            this.zzasJ = i2;
            this.zzasK = z;
            this.zzasL = i3;
            this.zzasM = z2;
            this.zzasN = str;
            this.zzasO = i4;
            if (str2 == null) {
                this.zzasP = null;
                this.zzasQ = null;
            } else {
                this.zzasP = SafeParcelResponse.class;
                this.zzasQ = str2;
            }
            if (converterWrapper == null) {
                this.zzasS = null;
            } else {
                this.zzasS = converterWrapper.zzub();
            }
        }

        protected Field(int i, boolean z, int i2, boolean z2, String str, int i3, Class<? extends FastJsonResponse> cls, zza<I, O> zza) {
            this.mVersionCode = 1;
            this.zzasJ = i;
            this.zzasK = z;
            this.zzasL = i2;
            this.zzasM = z2;
            this.zzasN = str;
            this.zzasO = i3;
            this.zzasP = cls;
            if (cls == null) {
                this.zzasQ = null;
            } else {
                this.zzasQ = cls.getCanonicalName();
            }
            this.zzasS = zza;
        }

        public static Field zza(String str, int i, zza<?, ?> zza, boolean z) {
            return new Field(zza.zzud(), z, zza.zzue(), false, str, i, null, zza);
        }

        public static <T extends FastJsonResponse> Field<T, T> zza(String str, int i, Class<T> cls) {
            return new Field(11, false, 11, false, str, i, cls, null);
        }

        public static <T extends FastJsonResponse> Field<ArrayList<T>, ArrayList<T>> zzb(String str, int i, Class<T> cls) {
            return new Field(11, true, 11, true, str, i, cls, null);
        }

        public static Field<Integer, Integer> zzi(String str, int i) {
            return new Field(0, false, 0, false, str, i, null, null);
        }

        public static Field<Boolean, Boolean> zzj(String str, int i) {
            return new Field(6, false, 6, false, str, i, null, null);
        }

        public static Field<String, String> zzk(String str, int i) {
            return new Field(7, false, 7, false, str, i, null, null);
        }

        public I convertBack(O o) {
            return this.zzasS.convertBack(o);
        }

        public int getVersionCode() {
            return this.mVersionCode;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Field\n");
            stringBuilder.append("            versionCode=").append(this.mVersionCode).append(10);
            stringBuilder.append("                 typeIn=").append(this.zzasJ).append(10);
            stringBuilder.append("            typeInArray=").append(this.zzasK).append(10);
            stringBuilder.append("                typeOut=").append(this.zzasL).append(10);
            stringBuilder.append("           typeOutArray=").append(this.zzasM).append(10);
            stringBuilder.append("        outputFieldName=").append(this.zzasN).append(10);
            stringBuilder.append("      safeParcelFieldId=").append(this.zzasO).append(10);
            stringBuilder.append("       concreteTypeName=").append(zzun()).append(10);
            if (zzum() != null) {
                stringBuilder.append("     concreteType.class=").append(zzum().getCanonicalName()).append(10);
            }
            stringBuilder.append("          converterName=").append(this.zzasS == null ? SafeJsonPrimitive.NULL_STRING : this.zzasS.getClass().getCanonicalName()).append(10);
            return stringBuilder.toString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            zza zza = CREATOR;
            zza.zza(this, parcel, i);
        }

        public void zza(FieldMappingDictionary fieldMappingDictionary) {
            this.zzasR = fieldMappingDictionary;
        }

        public int zzud() {
            return this.zzasJ;
        }

        public int zzue() {
            return this.zzasL;
        }

        public boolean zzui() {
            return this.zzasK;
        }

        public boolean zzuj() {
            return this.zzasM;
        }

        public String zzuk() {
            return this.zzasN;
        }

        public int zzul() {
            return this.zzasO;
        }

        public Class<? extends FastJsonResponse> zzum() {
            return this.zzasP;
        }

        /* Access modifiers changed, original: 0000 */
        public String zzun() {
            return this.zzasQ == null ? null : this.zzasQ;
        }

        public boolean zzuo() {
            return this.zzasS != null;
        }

        /* Access modifiers changed, original: 0000 */
        public ConverterWrapper zzup() {
            return this.zzasS == null ? null : ConverterWrapper.zza(this.zzasS);
        }

        public Map<String, Field<?, ?>> zzuq() {
            zzaa.zzz(this.zzasQ);
            zzaa.zzz(this.zzasR);
            return this.zzasR.zzdq(this.zzasQ);
        }
    }

    private void zza(StringBuilder stringBuilder, Field field, Object obj) {
        if (field.zzud() == 11) {
            stringBuilder.append(((FastJsonResponse) field.zzum().cast(obj)).toString());
        } else if (field.zzud() == 7) {
            stringBuilder.append("\"");
            stringBuilder.append(zzp.zzdu((String) obj));
            stringBuilder.append("\"");
        } else {
            stringBuilder.append(obj);
        }
    }

    private void zza(StringBuilder stringBuilder, Field field, ArrayList<Object> arrayList) {
        stringBuilder.append("[");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                stringBuilder.append(",");
            }
            Object obj = arrayList.get(i);
            if (obj != null) {
                zza(stringBuilder, field, obj);
            }
        }
        stringBuilder.append("]");
    }

    public String toString() {
        Map zzuf = zzuf();
        StringBuilder stringBuilder = new StringBuilder(100);
        for (String str : zzuf.keySet()) {
            Field field = (Field) zzuf.get(str);
            if (zza(field)) {
                Object zza = zza(field, zzb(field));
                if (stringBuilder.length() == 0) {
                    stringBuilder.append("{");
                } else {
                    stringBuilder.append(",");
                }
                stringBuilder.append("\"").append(str).append("\":");
                if (zza != null) {
                    switch (field.zzue()) {
                        case 8:
                            stringBuilder.append("\"").append(zzc.zzk((byte[]) zza)).append("\"");
                            break;
                        case 9:
                            stringBuilder.append("\"").append(zzc.zzl((byte[]) zza)).append("\"");
                            break;
                        case 10:
                            zzq.zza(stringBuilder, (HashMap) zza);
                            break;
                        default:
                            if (!field.zzui()) {
                                zza(stringBuilder, field, zza);
                                break;
                            }
                            zza(stringBuilder, field, (ArrayList) zza);
                            break;
                    }
                }
                stringBuilder.append(SafeJsonPrimitive.NULL_STRING);
            }
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.append("}");
        } else {
            stringBuilder.append("{}");
        }
        return stringBuilder.toString();
    }

    /* Access modifiers changed, original: protected */
    public <O, I> I zza(Field<I, O> field, Object obj) {
        return field.zzasS != null ? field.convertBack(obj) : obj;
    }

    /* Access modifiers changed, original: protected */
    public boolean zza(Field field) {
        return field.zzue() == 11 ? field.zzuj() ? zzdp(field.zzuk()) : zzdo(field.zzuk()) : zzdn(field.zzuk());
    }

    /* Access modifiers changed, original: protected */
    public Object zzb(Field field) {
        String zzuk = field.zzuk();
        if (field.zzum() == null) {
            return zzdm(field.zzuk());
        }
        zzaa.zza(zzdm(field.zzuk()) == null, "Concrete field shouldn't be value object: %s", field.zzuk());
        Map zzuh = field.zzuj() ? zzuh() : zzug();
        if (zzuh != null) {
            return zzuh.get(zzuk);
        }
        try {
            char toUpperCase = Character.toUpperCase(zzuk.charAt(0));
            String valueOf = String.valueOf(zzuk.substring(1));
            return getClass().getMethod(new StringBuilder(String.valueOf(valueOf).length() + 4).append("get").append(toUpperCase).append(valueOf).toString(), new Class[0]).invoke(this, new Object[0]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public abstract Object zzdm(String str);

    public abstract boolean zzdn(String str);

    /* Access modifiers changed, original: protected */
    public boolean zzdo(String str) {
        throw new UnsupportedOperationException("Concrete types not supported");
    }

    /* Access modifiers changed, original: protected */
    public boolean zzdp(String str) {
        throw new UnsupportedOperationException("Concrete type arrays not supported");
    }

    public abstract Map<String, Field<?, ?>> zzuf();

    public HashMap<String, Object> zzug() {
        return null;
    }

    public HashMap<String, Object> zzuh() {
        return null;
    }
}
