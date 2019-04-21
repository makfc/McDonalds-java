package p049hk.com.aisoft.easyaddrui;

/* renamed from: hk.com.aisoft.easyaddrui.AES256v3Ciphertext */
class AES256v3Ciphertext extends AES256Ciphertext {
    public AES256v3Ciphertext(byte[] encryptionSalt, byte[] hmacSalt, byte[] iv, byte[] ciphertext) {
        super(encryptionSalt, hmacSalt, iv, ciphertext);
    }

    /* Access modifiers changed, original: 0000 */
    public int getVersionNumber() {
        return 3;
    }
}
