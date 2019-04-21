package com.ensighten;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;

/* renamed from: com.ensighten.w */
public final class C1862w implements Serializable {
    private static final long serialVersionUID = 6374381828722046732L;
    /* renamed from: a */
    final transient Cookie f5999a;
    /* renamed from: b */
    transient BasicClientCookie f6000b;

    public C1862w(Cookie cookie) {
        this.f5999a = cookie;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(this.f5999a.getName());
        out.writeObject(this.f5999a.getValue());
        out.writeObject(this.f5999a.getComment());
        out.writeObject(this.f5999a.getDomain());
        out.writeObject(this.f5999a.getExpiryDate());
        out.writeObject(this.f5999a.getPath());
        out.writeInt(this.f5999a.getVersion());
        out.writeBoolean(this.f5999a.isSecure());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        this.f6000b = new BasicClientCookie((String) in.readObject(), (String) in.readObject());
        this.f6000b.setComment((String) in.readObject());
        this.f6000b.setDomain((String) in.readObject());
        this.f6000b.setExpiryDate((Date) in.readObject());
        this.f6000b.setPath((String) in.readObject());
        this.f6000b.setVersion(in.readInt());
        this.f6000b.setSecure(in.readBoolean());
    }
}
