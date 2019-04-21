package com.bumptech.glide.load;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public interface Key {
    void updateDiskCacheKey(MessageDigest messageDigest) throws UnsupportedEncodingException;
}
