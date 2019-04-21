package p049hk.com.aisoft.easyaddrui;

import javax.crypto.SecretKey;

/* renamed from: hk.com.aisoft.easyaddrui.PasswordKey */
public class PasswordKey {
    private final SecretKey key;
    private final byte[] salt;

    PasswordKey(SecretKey key, byte[] salt) {
        this.key = key;
        this.salt = salt;
    }

    /* Access modifiers changed, original: 0000 */
    public SecretKey getKey() {
        return this.key;
    }

    /* Access modifiers changed, original: 0000 */
    public byte[] getSalt() {
        return this.salt;
    }
}
