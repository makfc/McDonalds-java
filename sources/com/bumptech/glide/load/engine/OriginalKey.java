package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.Key;
import com.facebook.stetho.common.Utf8Charset;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

class OriginalKey implements Key {
    /* renamed from: id */
    private final String f5577id;
    private final Key signature;

    public OriginalKey(String id, Key signature) {
        this.f5577id = id;
        this.signature = signature;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OriginalKey that = (OriginalKey) o;
        if (!this.f5577id.equals(that.f5577id)) {
            return false;
        }
        if (this.signature.equals(that.signature)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.f5577id.hashCode() * 31) + this.signature.hashCode();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) throws UnsupportedEncodingException {
        messageDigest.update(this.f5577id.getBytes(Utf8Charset.NAME));
        this.signature.updateDiskCacheKey(messageDigest);
    }
}
