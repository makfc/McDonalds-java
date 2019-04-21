package p049hk.com.aisoft.easyaddrui;

import java.util.Arrays;

/* renamed from: hk.com.aisoft.easyaddrui.AES256Ciphertext */
abstract class AES256Ciphertext {
    private final byte[] ciphertext;
    private final byte[] encryptionSalt;
    private byte[] hmac;
    private final byte[] hmacSalt;
    private final boolean isPasswordBased;
    /* renamed from: iv */
    private final byte[] f7858iv;
    private final byte options = (byte) 1;
    private final int version = getVersionNumber();

    public abstract int getVersionNumber();

    AES256Ciphertext(byte[] encryptionSalt, byte[] hmacSalt, byte[] iv, byte[] ciphertext) {
        AES256Ciphertext.validateLength(encryptionSalt, "encryption salt", 8);
        AES256Ciphertext.validateLength(hmacSalt, "HMAC salt", 8);
        AES256Ciphertext.validateLength(iv, "IV", 16);
        this.encryptionSalt = encryptionSalt;
        this.hmacSalt = hmacSalt;
        this.f7858iv = iv;
        this.ciphertext = ciphertext;
        this.isPasswordBased = true;
        this.hmac = new byte[32];
    }

    private static void validateLength(byte[] data, String dataName, int expectedLength) throws IllegalArgumentException {
        if (data.length != expectedLength) {
            throw new IllegalArgumentException(String.format("Invalid %s length. Expected %d bytes but found %d.", new Object[]{dataName, Integer.valueOf(expectedLength), Integer.valueOf(data.length)}));
        }
    }

    /* Access modifiers changed, original: 0000 */
    public byte[] getRawData() {
        int dataSize;
        byte[] header = new byte[]{(byte) getVersionNumber(), (byte) 0};
        if (this.isPasswordBased) {
            header[1] = (byte) (header[1] | 1);
        }
        if (this.isPasswordBased) {
            dataSize = ((((header.length + this.encryptionSalt.length) + this.hmacSalt.length) + this.f7858iv.length) + this.ciphertext.length) + this.hmac.length;
        } else {
            dataSize = ((header.length + this.f7858iv.length) + this.ciphertext.length) + this.hmac.length;
        }
        byte[] result = new byte[dataSize];
        System.arraycopy(header, 0, result, 0, header.length);
        if (this.isPasswordBased) {
            System.arraycopy(this.encryptionSalt, 0, result, header.length, this.encryptionSalt.length);
            System.arraycopy(this.hmacSalt, 0, result, header.length + this.encryptionSalt.length, this.hmacSalt.length);
            System.arraycopy(this.f7858iv, 0, result, (header.length + this.encryptionSalt.length) + this.hmacSalt.length, this.f7858iv.length);
            System.arraycopy(this.ciphertext, 0, result, ((header.length + this.encryptionSalt.length) + this.hmacSalt.length) + this.f7858iv.length, this.ciphertext.length);
            System.arraycopy(this.hmac, 0, result, (((header.length + this.encryptionSalt.length) + this.hmacSalt.length) + this.f7858iv.length) + this.ciphertext.length, this.hmac.length);
        } else {
            System.arraycopy(this.f7858iv, 0, result, header.length, this.f7858iv.length);
            System.arraycopy(this.ciphertext, 0, result, header.length + this.f7858iv.length, this.ciphertext.length);
            System.arraycopy(this.hmac, 0, result, (header.length + this.f7858iv.length) + this.ciphertext.length, this.hmac.length);
        }
        return result;
    }

    /* Access modifiers changed, original: 0000 */
    public byte[] getDataToHMAC() {
        byte[] rawData = getRawData();
        byte[] result = new byte[(rawData.length - 32)];
        System.arraycopy(rawData, 0, result, 0, result.length);
        return result;
    }

    /* Access modifiers changed, original: 0000 */
    public void setHmac(byte[] hmac) {
        this.hmac = hmac;
    }

    public int hashCode() {
        return ((((((((((((((Arrays.hashCode(this.ciphertext) + 31) * 31) + Arrays.hashCode(this.encryptionSalt)) * 31) + Arrays.hashCode(this.hmac)) * 31) + Arrays.hashCode(this.hmacSalt)) * 31) + (this.isPasswordBased ? 1231 : 1237)) * 31) + Arrays.hashCode(this.f7858iv)) * 31) + this.options) * 31) + this.version;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AES256Ciphertext other = (AES256Ciphertext) obj;
        if (!Arrays.equals(this.ciphertext, other.ciphertext)) {
            return false;
        }
        if (!Arrays.equals(this.encryptionSalt, other.encryptionSalt)) {
            return false;
        }
        if (!Arrays.equals(this.hmac, other.hmac)) {
            return false;
        }
        if (!Arrays.equals(this.hmacSalt, other.hmacSalt)) {
            return false;
        }
        if (this.isPasswordBased != other.isPasswordBased) {
            return false;
        }
        if (!Arrays.equals(this.f7858iv, other.f7858iv)) {
            return false;
        }
        if (this.options != other.options) {
            return false;
        }
        if (this.version != other.version) {
            return false;
        }
        return true;
    }
}
