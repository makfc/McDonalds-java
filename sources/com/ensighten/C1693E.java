package com.ensighten;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import com.ensighten.C1746c.C1745a;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: com.ensighten.E */
public class C1693E {
    /* renamed from: a */
    protected Map<String, Object> f5601a;
    /* renamed from: b */
    protected Object[] f5602b;
    /* renamed from: c */
    protected Object f5603c;
    /* renamed from: d */
    protected int f5604d;
    /* renamed from: e */
    public Object f5605e;
    /* renamed from: f */
    public String f5606f;

    public C1693E() {
        this(null);
    }

    private C1693E(Object[] objArr) {
        this.f5601a = new HashMap();
        this.f5602b = null;
        this.f5604d = 0;
    }

    /* renamed from: a */
    public final String mo15020a(ArrayList<C1692D> arrayList, boolean z) {
        try {
            int size = arrayList.size();
            this.f5604d = 0;
            while (this.f5604d < size) {
                C1692D c1692d = (C1692D) arrayList.get(this.f5604d);
                String a = c1692d.mo15016a();
                if (C1845i.m7361h()) {
                    C1845i.m7350b(String.format("Executing instruction %s.", new Object[]{a}));
                }
                C1746c c1746c = C1745a.f5835a;
                synchronized (c1746c.f5836a) {
                    Iterator it = c1746c.f5836a.iterator();
                    while (it.hasNext()) {
                        it.next();
                    }
                }
                Field field;
                if (c1692d.mo15016a().equals("setCurrentValue")) {
                    this.f5603c = m7187b(c1692d.mo15019c());
                } else if (c1692d.mo15016a().equals("setRegisterCV")) {
                    this.f5601a.put(m7184a(c1692d.mo15019c()), this.f5603c);
                } else if (c1692d.mo15016a().equals("setRegister")) {
                    this.f5601a.put(c1692d.mo15017a("arg1"), m7187b(c1692d.mo15019c()));
                } else if (c1692d.mo15016a().equals("replace")) {
                    this.f5603c = String.valueOf(this.f5603c).replaceAll(m7184a(c1692d.mo15017a("arg1")), m7184a(c1692d.mo15017a("arg2")));
                } else if (c1692d.mo15016a().equals("search")) {
                    this.f5601a.put(m7184a(c1692d.mo15017a("arg2")), Integer.valueOf(String.valueOf(this.f5603c).indexOf(m7184a(c1692d.mo15017a("arg1")))));
                } else if (c1692d.mo15016a().equals("split")) {
                    this.f5601a.put(m7184a(c1692d.mo15017a("arg3")), String.valueOf(this.f5603c).split(m7184a(c1692d.mo15017a("arg1")))[Integer.parseInt(m7184a(c1692d.mo15017a("arg2")))]);
                } else if (c1692d.mo15016a().equals("length")) {
                    this.f5601a.put(m7184a(c1692d.mo15017a("arg1")), String.valueOf(String.valueOf(this.f5603c).length()));
                } else if (c1692d.mo15016a().equals("count")) {
                    this.f5601a.put(m7184a(c1692d.mo15017a("arg2")), Integer.valueOf(String.valueOf(this.f5603c).split(m7184a(c1692d.mo15017a("arg1"))).length));
                } else if (c1692d.mo15016a().equals("cut")) {
                    this.f5603c = String.valueOf(this.f5603c).substring(Integer.parseInt(m7184a(c1692d.mo15017a("arg1"))), Integer.parseInt(m7184a(c1692d.mo15017a("arg2"))));
                } else if (c1692d.mo15016a().equals("append")) {
                    this.f5603c += c1692d.mo15019c();
                } else if (c1692d.mo15016a().equals("prepend")) {
                    this.f5603c = c1692d.mo15019c() + this.f5603c;
                } else if (c1692d.mo15016a().equals("compare")) {
                    this.f5601a.put(m7184a(c1692d.mo15017a("arg1")), String.valueOf(this.f5603c).toLowerCase().equals(c1692d.mo15019c().toLowerCase()) ? "1" : "0");
                } else if (c1692d.mo15016a().equals("jumpIfZ")) {
                    if (Integer.parseInt(String.valueOf(this.f5601a.get(m7184a(c1692d.mo15017a("arg1"))))) == 0) {
                        this.f5604d = Integer.parseInt(c1692d.mo15019c());
                    }
                } else if (c1692d.mo15016a().equals("jumpIfNZ")) {
                    if (Integer.parseInt(String.valueOf(this.f5601a.get(m7184a(c1692d.mo15017a("arg1"))))) != 0) {
                        this.f5604d = Integer.parseInt(c1692d.mo15019c());
                    }
                } else if (c1692d.mo15016a().equals("end")) {
                    this.f5604d = 2147483646;
                } else if (c1692d.mo15016a().equals("getInvTarget")) {
                    this.f5603c = this.f5605e;
                } else if (c1692d.mo15016a().equals("getInvArg")) {
                    this.f5603c = this.f5602b[Integer.parseInt(m7184a(c1692d.mo15017a("arg1")))];
                } else if (c1692d.mo15016a().equals("callJSFunction")) {
                    m7186a(c1692d, z);
                } else if (c1692d.mo15016a().equals("callMethod")) {
                    m7188b(c1692d);
                } else if (c1692d.mo15016a().equals("callStaticMethod")) {
                    m7185a(c1692d);
                } else if (c1692d.mo15016a().equals("getPropVal")) {
                    if (this.f5603c == null) {
                        throw new Exception("Error getting the property of a null object.");
                    }
                    field = Utils.getField(this.f5603c.getClass(), m7184a(c1692d.mo15017a("arg1")));
                    if (field != null) {
                        field.setAccessible(true);
                        this.f5603c = field.get(this.f5603c);
                    }
                } else if (c1692d.mo15016a().equals("getStaticPropVal")) {
                    field = Utils.getField(Class.forName(m7184a(c1692d.mo15017a("arg1"))), m7184a(c1692d.mo15017a("arg2")));
                    if (field != null) {
                        field.setAccessible(true);
                        this.f5603c = field.get(null);
                    }
                } else if (c1692d.mo15016a().equals("loadDLObject")) {
                    m7189c(c1692d);
                } else if (c1692d.mo15016a().equals("stringify")) {
                    this.f5603c = String.valueOf(this.f5603c);
                } else if (c1692d.mo15016a().equals("getClass")) {
                    this.f5603c = Class.forName(m7184a(c1692d.mo15017a("arg1")));
                } else if (c1692d.mo15016a().equals("getCallingMethodName")) {
                    this.f5603c = this.f5606f;
                } else if (c1692d.mo15016a().equals("setVideoAdBreaks")) {
                    m7190d(c1692d);
                } else if (c1692d.mo15016a().equals("setVideoChapters")) {
                    m7191e(c1692d);
                } else if (C1845i.m7361h()) {
                    C1845i.m7350b(String.format("Instruction opcode not recognized: %s.", new Object[]{a}));
                }
                this.f5604d++;
            }
        } catch (Exception e) {
            this.f5603c = null;
            if (C1845i.m7361h()) {
                C1845i.m7346a("Failed to execute instruction.", e);
            }
        }
        this.f5603c = this.f5603c == null ? "" : this.f5603c;
        return String.valueOf(this.f5603c);
    }

    /* renamed from: a */
    private void m7186a(C1692D c1692d, boolean z) {
        Pattern compile = Pattern.compile("@[^@]*@", 2);
        String c = c1692d.mo15019c();
        Matcher matcher = compile.matcher(c);
        String str = new String(c);
        while (true) {
            c = str;
            if (!matcher.find()) {
                break;
            }
            CharSequence valueOf;
            String group = matcher.group();
            String replace = group.replace("@", "");
            if (replace.equals("cv")) {
                valueOf = String.valueOf(this.f5603c);
            } else if (replace.startsWith("r:")) {
                valueOf = String.valueOf(this.f5601a.get(replace.substring(2)));
            } else {
                Object obj;
                if (replace.startsWith("target") || replace.startsWith("arg[")) {
                    Object obj2;
                    Class cls;
                    if (true == replace.startsWith("target")) {
                        obj2 = this.f5605e;
                        cls = obj2.getClass();
                    } else if (true == replace.startsWith("arg[")) {
                        obj2 = this.f5602b[Integer.parseInt(replace.substring(4, replace.indexOf("]")))];
                        cls = obj2.getClass();
                    } else {
                        obj2 = null;
                        cls = null;
                    }
                    if (cls != null) {
                        try {
                            String[] split = replace.split("\\.");
                            int i = 1;
                            Object obj3 = null;
                            while (i < split.length) {
                                Class cls2;
                                obj3 = cls.getMethod(split[i], C1743a.f5831c).invoke(obj2, (Object[]) C1743a.f5831c);
                                if (obj3 != null) {
                                    cls2 = obj3.getClass();
                                    obj = obj3;
                                } else {
                                    obj = obj2;
                                    cls2 = cls;
                                }
                                i++;
                                cls = cls2;
                                obj2 = obj;
                            }
                            valueOf = String.valueOf(obj3);
                        } catch (Exception e) {
                            if (C1845i.m7361h()) {
                                C1845i.m7353c(e);
                            }
                        }
                    }
                }
                obj = group;
            }
            str = c.replace("@" + replace + "@", valueOf);
        }
        if (z) {
            Ensighten.evaluateSyncJS(c);
        } else {
            Ensighten.evaluateJS(c);
        }
    }

    /* renamed from: a */
    private void m7185a(C1692D c1692d) throws Exception {
        String a = m7184a(c1692d.mo15017a("arg1"));
        String a2 = m7184a(c1692d.mo15017a("arg2"));
        String a3 = m7184a(c1692d.mo15017a("arg3"));
        Class[] clsArr = null;
        if (!a3.equals("na")) {
            String[] split = a3.split(",");
            Class[] clsArr2 = new Class[split.length];
            for (int i = 0; i < split.length; i++) {
                if (split[i].equals("byte")) {
                    clsArr2[i] = Byte.TYPE;
                } else if (split[i].equals("short")) {
                    clsArr2[i] = Short.TYPE;
                } else if (split[i].equals("int")) {
                    clsArr2[i] = Integer.TYPE;
                } else if (split[i].equals("long")) {
                    clsArr2[i] = Long.TYPE;
                } else if (split[i].equals("char")) {
                    clsArr2[i] = Character.TYPE;
                } else if (split[i].equals("float")) {
                    clsArr2[i] = Float.TYPE;
                } else if (split[i].equals("double")) {
                    clsArr2[i] = Double.TYPE;
                } else if (split[i].equals("boolean")) {
                    clsArr2[i] = Boolean.TYPE;
                } else {
                    clsArr2[i] = Class.forName(split[i]);
                }
            }
            clsArr = clsArr2;
        }
        Method declaredMethod = Class.forName(a).getDeclaredMethod(a2, clsArr);
        declaredMethod.setAccessible(true);
        Object[] objArr = new Object[(c1692d.mo15018b() - 3)];
        int i2 = 4;
        int i3 = 0;
        while (i2 <= c1692d.mo15018b()) {
            if (clsArr[i3].equals(Byte.TYPE)) {
                objArr[i3] = Byte.valueOf(Byte.parseByte(m7184a(c1692d.mo15017a("arg" + i2))));
            } else if (clsArr[i3].equals(Short.TYPE)) {
                objArr[i3] = Short.valueOf(Short.parseShort(m7184a(c1692d.mo15017a("arg" + i2))));
            } else if (clsArr[i3].equals(Integer.TYPE)) {
                objArr[i3] = Integer.valueOf(Integer.parseInt(m7184a(c1692d.mo15017a("arg" + i2))));
            } else if (clsArr[i3].equals(Long.TYPE)) {
                objArr[i3] = Long.valueOf(Long.parseLong(m7184a(c1692d.mo15017a("arg" + i2))));
            } else if (clsArr[i3].equals(Character.TYPE)) {
                objArr[i3] = Character.valueOf(m7184a(c1692d.mo15017a("arg" + i2)).charAt(0));
            } else if (clsArr[i3].equals(Float.TYPE)) {
                objArr[i3] = Float.valueOf(Float.parseFloat(m7184a(c1692d.mo15017a("arg" + i2))));
            } else if (clsArr[i3].equals(Double.TYPE)) {
                objArr[i3] = Double.valueOf(Double.parseDouble(m7184a(c1692d.mo15017a("arg" + i2))));
            } else if (clsArr[i3].equals(Boolean.TYPE)) {
                objArr[i3] = Boolean.valueOf(Boolean.parseBoolean(m7184a(c1692d.mo15017a("arg" + i2))));
            } else {
                objArr[i3] = clsArr[i3].cast(m7187b(c1692d.mo15017a("arg" + i2)));
            }
            i2++;
            i3++;
        }
        this.f5603c = declaredMethod.invoke(this.f5603c, objArr);
    }

    /* renamed from: b */
    private void m7188b(C1692D c1692d) throws Exception {
        if (this.f5603c == null) {
            throw new Exception("Error calling a method of a null object.");
        }
        String a = m7184a(c1692d.mo15017a("arg1"));
        String a2 = m7184a(c1692d.mo15017a("arg2"));
        Class[] clsArr = null;
        if (!a2.equals("na")) {
            String[] split = a2.split(",");
            Class[] clsArr2 = new Class[split.length];
            for (int i = 0; i < split.length; i++) {
                if (split[i].equals("byte")) {
                    clsArr2[i] = Byte.TYPE;
                } else if (split[i].equals("short")) {
                    clsArr2[i] = Short.TYPE;
                } else if (split[i].equals("int")) {
                    clsArr2[i] = Integer.TYPE;
                } else if (split[i].equals("long")) {
                    clsArr2[i] = Long.TYPE;
                } else if (split[i].equals("char")) {
                    clsArr2[i] = Character.TYPE;
                } else if (split[i].equals("float")) {
                    clsArr2[i] = Float.TYPE;
                } else if (split[i].equals("double")) {
                    clsArr2[i] = Double.TYPE;
                } else if (split[i].equals("boolean")) {
                    clsArr2[i] = Boolean.TYPE;
                } else {
                    clsArr2[i] = Class.forName(split[i]);
                }
            }
            clsArr = clsArr2;
        }
        Method method = Utils.getMethod(this.f5603c.getClass(), a, clsArr);
        method.setAccessible(true);
        Object[] objArr = new Object[(c1692d.mo15018b() - 2)];
        int i2 = 3;
        int i3 = 0;
        while (i2 <= c1692d.mo15018b()) {
            if (clsArr[i3].equals(Byte.TYPE)) {
                objArr[i3] = Byte.valueOf(Byte.parseByte(m7184a(c1692d.mo15017a("arg" + i2))));
            } else if (clsArr[i3].equals(Short.TYPE)) {
                objArr[i3] = Short.valueOf(Short.parseShort(m7184a(c1692d.mo15017a("arg" + i2))));
            } else if (clsArr[i3].equals(Integer.TYPE)) {
                objArr[i3] = Integer.valueOf(Integer.parseInt(m7184a(c1692d.mo15017a("arg" + i2))));
            } else if (clsArr[i3].equals(Long.TYPE)) {
                objArr[i3] = Long.valueOf(Long.parseLong(m7184a(c1692d.mo15017a("arg" + i2))));
            } else if (clsArr[i3].equals(Character.TYPE)) {
                objArr[i3] = Character.valueOf(m7184a(c1692d.mo15017a("arg" + i2)).charAt(0));
            } else if (clsArr[i3].equals(Float.TYPE)) {
                objArr[i3] = Float.valueOf(Float.parseFloat(m7184a(c1692d.mo15017a("arg" + i2))));
            } else if (clsArr[i3].equals(Double.TYPE)) {
                objArr[i3] = Double.valueOf(Double.parseDouble(m7184a(c1692d.mo15017a("arg" + i2))));
            } else if (clsArr[i3].equals(Boolean.TYPE)) {
                objArr[i3] = Boolean.valueOf(Boolean.parseBoolean(m7184a(c1692d.mo15017a("arg" + i2))));
            } else {
                objArr[i3] = clsArr[i3].cast(m7187b(c1692d.mo15017a("arg" + i2)));
            }
            i2++;
            i3++;
        }
        this.f5603c = method.invoke(this.f5603c, objArr);
    }

    /* renamed from: c */
    private void m7189c(C1692D c1692d) throws Exception {
        if (Ensighten.getCurrentActivity() != null) {
            Object obj;
            String a = m7184a(c1692d.mo15017a("arg1"));
            String a2 = m7184a(c1692d.mo15019c());
            if (a2.indexOf(",") != -1) {
                View findViewById = Ensighten.getCurrentActivity().findViewById(16908290);
                ViewGroup viewGroup = (ViewGroup) findViewById;
                String[] split = a2.split(",");
                int length = split.length;
                ViewGroup viewGroup2 = viewGroup;
                obj = findViewById;
                int i = 0;
                while (i < length) {
                    obj = viewGroup2.getChildAt(Integer.parseInt(split[i]));
                    if (!(obj instanceof ViewGroup)) {
                        break;
                    }
                    viewGroup = (ViewGroup) obj;
                    i++;
                    viewGroup2 = viewGroup;
                }
            } else {
                obj = Ensighten.getCurrentActivity().findViewById(Integer.parseInt(a2));
            }
            if (obj == null || !obj.getClass().getName().equalsIgnoreCase(a)) {
                throw new Exception("Error finding a view in the UI.");
            }
            this.f5603c = obj;
        } else if (C1845i.m7361h()) {
            C1845i.m7345a("Failed to execute getDlObject instruction because the current activity is null.");
        }
    }

    /* renamed from: d */
    private void m7190d(C1692D c1692d) throws Exception {
        Ensighten.setVideoHeartbeatEnabled(true);
        Ensighten.setVideoHeartbeatAdBreaks(new SparseArray());
        int b = c1692d.mo15018b();
        for (int i = 1; i <= b; i++) {
            try {
                String obj = m7187b(c1692d.mo15017a("arg" + i)).toString();
                if (!obj.equals("na")) {
                    ArrayList arrayList;
                    String[] split = obj.split(",");
                    Integer valueOf = Integer.valueOf(split[0]);
                    Integer valueOf2 = Integer.valueOf(split[1]);
                    int intValue = valueOf2.intValue() - valueOf.intValue();
                    Integer valueOf3 = Integer.valueOf(valueOf2.intValue() - 1);
                    C1729U c1729u = new C1729U(false, "adStart", valueOf.intValue(), intValue, i);
                    for (int intValue2 = valueOf.intValue(); intValue2 < valueOf3.intValue(); intValue2++) {
                        arrayList = (ArrayList) Ensighten.getVideoHeartbeatAdBreaks().get(intValue2);
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(c1729u);
                        Ensighten.getVideoHeartbeatAdBreaks().put(intValue2, arrayList);
                    }
                    c1729u = new C1729U(false, "adComplete", valueOf.intValue(), intValue, i);
                    arrayList = (ArrayList) Ensighten.getVideoHeartbeatAdBreaks().get(valueOf3.intValue());
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(c1729u);
                    Ensighten.getVideoHeartbeatAdBreaks().put(valueOf3.intValue(), arrayList);
                }
            } catch (Exception e) {
                if (C1845i.m7361h()) {
                    C1845i.m7346a(String.format("Failed to execute instruction.", new Object[]{c1692d.mo15016a()}), e);
                }
            }
        }
    }

    /* renamed from: e */
    private void m7191e(C1692D c1692d) throws Exception {
        Ensighten.setVideoHeartbeatEnabled(true);
        Ensighten.setVideoHeartbeatChapters(new SparseArray());
        int b = c1692d.mo15018b();
        for (int i = 1; i <= b; i++) {
            try {
                String obj = m7187b(c1692d.mo15017a("arg" + i)).toString();
                if (!obj.equals("na")) {
                    ArrayList arrayList;
                    String[] split = obj.split(",");
                    Integer valueOf = Integer.valueOf(split[0]);
                    Integer valueOf2 = Integer.valueOf(split[1]);
                    int intValue = valueOf2.intValue() - valueOf.intValue();
                    Integer valueOf3 = Integer.valueOf(valueOf2.intValue() - 1);
                    C1729U c1729u = new C1729U(true, "chapterStart", valueOf.intValue(), intValue, i);
                    for (int intValue2 = valueOf.intValue(); intValue2 < valueOf3.intValue(); intValue2++) {
                        arrayList = (ArrayList) Ensighten.getVideoHeartbeatChapters().get(intValue2);
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(c1729u);
                        Ensighten.getVideoHeartbeatChapters().put(intValue2, arrayList);
                    }
                    c1729u = new C1729U(true, "chapterComplete", valueOf.intValue(), intValue, i);
                    arrayList = (ArrayList) Ensighten.getVideoHeartbeatChapters().get(valueOf3.intValue());
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(c1729u);
                    Ensighten.getVideoHeartbeatChapters().put(valueOf3.intValue(), arrayList);
                }
            } catch (Exception e) {
                if (C1845i.m7361h()) {
                    C1845i.m7346a(String.format("Failed to execute instruction.", new Object[]{c1692d.mo15016a()}), e);
                }
            }
        }
    }

    /* renamed from: a */
    private String m7184a(String str) {
        return String.valueOf(m7187b(str));
    }

    /* renamed from: b */
    private Object m7187b(String str) {
        if (str == null || str.length() < 2 || str.length() < 4) {
            return str;
        }
        if ("r:".equals(str.substring(0, 2))) {
            return this.f5601a.get(str.substring(2));
        }
        if (!"arg:".equals(str.substring(0, 4))) {
            return str;
        }
        return this.f5602b[Integer.parseInt(str.substring(4))];
    }

    /* renamed from: a */
    public final void mo15021a(Object[] objArr) {
        this.f5602b = objArr;
    }

    /* renamed from: a */
    public final Object[] mo15022a() {
        return this.f5602b;
    }
}
