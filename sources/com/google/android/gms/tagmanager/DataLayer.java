package com.google.android.gms.tagmanager;

import com.autonavi.amap.mapcore.VTMCDataCache;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataLayer {
    public static final Object OBJECT_NOT_PRESENT = new Object();
    static final String[] zzbow = "gtm.lifetime".toString().split("\\.");
    private static final Pattern zzbox = Pattern.compile("(\\d+)\\s*([smhd]?)");
    private final ReentrantLock zzboA;
    private final LinkedList<Map<String, Object>> zzboB;
    private final zzc zzboC;
    private final CountDownLatch zzboD;
    private final ConcurrentHashMap<zzb, Integer> zzboy;
    private final Map<String, Object> zzboz;

    interface zzc {

        public interface zza {
            void zzF(List<zza> list);
        }

        void zza(zza zza);

        void zza(List<zza> list, long j);

        void zzgt(String str);
    }

    /* renamed from: com.google.android.gms.tagmanager.DataLayer$1 */
    class C27131 implements zzc {
        C27131() {
        }

        public void zza(zza zza) {
            zza.zzF(new ArrayList());
        }

        public void zza(List<zza> list, long j) {
        }

        public void zzgt(String str) {
        }
    }

    /* renamed from: com.google.android.gms.tagmanager.DataLayer$2 */
    class C27142 implements zza {
        C27142() {
        }

        public void zzF(List<zza> list) {
            for (zza zza : list) {
                DataLayer.this.zzY(DataLayer.this.zzo(zza.zzwQ, zza.zzRF));
            }
            DataLayer.this.zzboD.countDown();
        }
    }

    static final class zza {
        public final Object zzRF;
        public final String zzwQ;

        zza(String str, Object obj) {
            this.zzwQ = str;
            this.zzRF = obj;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            return this.zzwQ.equals(zza.zzwQ) && this.zzRF.equals(zza.zzRF);
        }

        public int hashCode() {
            return Arrays.hashCode(new Integer[]{Integer.valueOf(this.zzwQ.hashCode()), Integer.valueOf(this.zzRF.hashCode())});
        }

        public String toString() {
            String str = this.zzwQ;
            String valueOf = String.valueOf(this.zzRF.toString());
            return new StringBuilder((String.valueOf(str).length() + 13) + String.valueOf(valueOf).length()).append("Key: ").append(str).append(" value: ").append(valueOf).toString();
        }
    }

    interface zzb {
        void zzW(Map<String, Object> map);
    }

    DataLayer() {
        this(new C27131());
    }

    DataLayer(zzc zzc) {
        this.zzboC = zzc;
        this.zzboy = new ConcurrentHashMap();
        this.zzboz = new HashMap();
        this.zzboA = new ReentrantLock();
        this.zzboB = new LinkedList();
        this.zzboD = new CountDownLatch(1);
        zzJw();
    }

    public static List<Object> listOf(Object... objArr) {
        ArrayList arrayList = new ArrayList();
        for (Object add : objArr) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public static Map<String, Object> mapOf(Object... objArr) {
        if (objArr.length % 2 != 0) {
            throw new IllegalArgumentException("expected even number of key-value pairs");
        }
        HashMap hashMap = new HashMap();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= objArr.length) {
                return hashMap;
            }
            if (objArr[i2] instanceof String) {
                hashMap.put((String) objArr[i2], objArr[i2 + 1]);
                i = i2 + 2;
            } else {
                String valueOf = String.valueOf(objArr[i2]);
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 21).append("key is not a string: ").append(valueOf).toString());
            }
        }
    }

    private void zzJw() {
        this.zzboC.zza(new C27142());
    }

    private void zzJx() {
        int i = 0;
        while (true) {
            int i2 = i;
            Map map = (Map) this.zzboB.poll();
            if (map != null) {
                zzad(map);
                i = i2 + 1;
                if (i > VTMCDataCache.MAXSIZE) {
                    this.zzboB.clear();
                    throw new RuntimeException("Seems like an infinite loop of pushing to the data layer");
                }
            } else {
                return;
            }
        }
    }

    private void zzY(Map<String, Object> map) {
        this.zzboA.lock();
        try {
            this.zzboB.offer(map);
            if (this.zzboA.getHoldCount() == 1) {
                zzJx();
            }
            zzZ(map);
        } finally {
            this.zzboA.unlock();
        }
    }

    private void zzZ(Map<String, Object> map) {
        Long zzaa = zzaa(map);
        if (zzaa != null) {
            List zzac = zzac(map);
            zzac.remove("gtm.lifetime");
            this.zzboC.zza(zzac, zzaa.longValue());
        }
    }

    private void zza(Map<String, Object> map, String str, Collection<zza> collection) {
        for (Entry entry : map.entrySet()) {
            Object obj;
            if (str.length() == 0) {
                obj = "";
            } else {
                String obj2 = ".";
            }
            String str2 = (String) entry.getKey();
            str2 = new StringBuilder(((String.valueOf(str).length() + 0) + String.valueOf(obj2).length()) + String.valueOf(str2).length()).append(str).append(obj2).append(str2).toString();
            if (entry.getValue() instanceof Map) {
                zza((Map) entry.getValue(), str2, collection);
            } else if (!str2.equals("gtm.lifetime")) {
                collection.add(new zza(str2, entry.getValue()));
            }
        }
    }

    private Long zzaa(Map<String, Object> map) {
        Object zzab = zzab(map);
        return zzab == null ? null : zzgs(zzab.toString());
    }

    private Object zzab(Map<String, Object> map) {
        String[] strArr = zzbow;
        int length = strArr.length;
        int i = 0;
        Object obj = map;
        while (i < length) {
            Object obj2 = strArr[i];
            if (!(obj instanceof Map)) {
                return null;
            }
            i++;
            obj = ((Map) obj).get(obj2);
        }
        return obj;
    }

    private List<zza> zzac(Map<String, Object> map) {
        ArrayList arrayList = new ArrayList();
        zza(map, "", arrayList);
        return arrayList;
    }

    private void zzad(Map<String, Object> map) {
        synchronized (this.zzboz) {
            for (String str : map.keySet()) {
                zzd(zzo(str, map.get(str)), this.zzboz);
            }
        }
        zzae(map);
    }

    private void zzae(Map<String, Object> map) {
        for (zzb zzW : this.zzboy.keySet()) {
            zzW.zzW(map);
        }
    }

    static Long zzgs(String str) {
        String str2;
        String valueOf;
        Matcher matcher = zzbox.matcher(str);
        if (matcher.matches()) {
            long parseLong;
            try {
                parseLong = Long.parseLong(matcher.group(1));
            } catch (NumberFormatException e) {
                str2 = "illegal number in _lifetime value: ";
                valueOf = String.valueOf(str);
                zzbn.zzaW(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                parseLong = 0;
            }
            if (parseLong <= 0) {
                str2 = "non-positive _lifetime: ";
                valueOf = String.valueOf(str);
                zzbn.zzaV(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                return null;
            }
            valueOf = matcher.group(2);
            if (valueOf.length() == 0) {
                return Long.valueOf(parseLong);
            }
            switch (valueOf.charAt(0)) {
                case 'd':
                    return Long.valueOf((((parseLong * 1000) * 60) * 60) * 24);
                case 'h':
                    return Long.valueOf(((parseLong * 1000) * 60) * 60);
                case 'm':
                    return Long.valueOf((parseLong * 1000) * 60);
                case 's':
                    return Long.valueOf(parseLong * 1000);
                default:
                    str2 = "unknown units in _lifetime: ";
                    valueOf = String.valueOf(str);
                    zzbn.zzaW(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                    return null;
            }
        }
        str2 = "unknown _lifetime: ";
        valueOf = String.valueOf(str);
        zzbn.zzaV(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        return null;
    }

    public Object get(String str) {
        synchronized (this.zzboz) {
            Map map = this.zzboz;
            String[] split = str.split("\\.");
            int length = split.length;
            Object obj = map;
            int i = 0;
            while (i < length) {
                Object obj2 = split[i];
                if (obj instanceof Map) {
                    obj2 = ((Map) obj).get(obj2);
                    if (obj2 == null) {
                        return null;
                    }
                    i++;
                    obj = obj2;
                } else {
                    return null;
                }
            }
            return obj;
        }
    }

    public void push(String str, Object obj) {
        push(zzo(str, obj));
    }

    public void push(Map<String, Object> map) {
        try {
            this.zzboD.await();
        } catch (InterruptedException e) {
            zzbn.zzaW("DataLayer.push: unexpected InterruptedException");
        }
        zzY(map);
    }

    public void pushEvent(String str, Map<String, Object> map) {
        HashMap hashMap = new HashMap(map);
        hashMap.put("event", str);
        push(hashMap);
    }

    public String toString() {
        String stringBuilder;
        synchronized (this.zzboz) {
            StringBuilder stringBuilder2 = new StringBuilder();
            for (Entry entry : this.zzboz.entrySet()) {
                stringBuilder2.append(String.format("{\n\tKey: %s\n\tValue: %s\n}\n", new Object[]{entry.getKey(), entry.getValue()}));
            }
            stringBuilder = stringBuilder2.toString();
        }
        return stringBuilder;
    }

    /* Access modifiers changed, original: 0000 */
    public void zza(zzb zzb) {
        this.zzboy.put(zzb, Integer.valueOf(0));
    }

    /* Access modifiers changed, original: 0000 */
    public void zzb(List<Object> list, List<Object> list2) {
        while (list2.size() < list.size()) {
            list2.add(null);
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < list.size()) {
                Object obj = list.get(i2);
                if (obj instanceof List) {
                    if (!(list2.get(i2) instanceof List)) {
                        list2.set(i2, new ArrayList());
                    }
                    zzb((List) obj, (List) list2.get(i2));
                } else if (obj instanceof Map) {
                    if (!(list2.get(i2) instanceof Map)) {
                        list2.set(i2, new HashMap());
                    }
                    zzd((Map) obj, (Map) list2.get(i2));
                } else if (obj != OBJECT_NOT_PRESENT) {
                    list2.set(i2, obj);
                }
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void zzd(Map<String, Object> map, Map<String, Object> map2) {
        for (String str : map.keySet()) {
            Object obj = map.get(str);
            if (obj instanceof List) {
                if (!(map2.get(str) instanceof List)) {
                    map2.put(str, new ArrayList());
                }
                zzb((List) obj, (List) map2.get(str));
            } else if (obj instanceof Map) {
                if (!(map2.get(str) instanceof Map)) {
                    map2.put(str, new HashMap());
                }
                zzd((Map) obj, (Map) map2.get(str));
            } else {
                map2.put(str, obj);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void zzgr(String str) {
        push(str, null);
        this.zzboC.zzgt(str);
    }

    /* Access modifiers changed, original: 0000 */
    public Map<String, Object> zzo(String str, Object obj) {
        HashMap hashMap = new HashMap();
        String[] split = str.toString().split("\\.");
        int i = 0;
        Map map = hashMap;
        while (i < split.length - 1) {
            HashMap hashMap2 = new HashMap();
            map.put(split[i], hashMap2);
            i++;
            Object map2 = hashMap2;
        }
        map2.put(split[split.length - 1], obj);
        return hashMap;
    }
}
