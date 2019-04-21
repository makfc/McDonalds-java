package p049hk.com.aisoft.easyaddrui;

import javax.crypto.SecretKey;

/* renamed from: hk.com.aisoft.easyaddrui.JNCryptor */
public interface JNCryptor {
    byte[] encryptData(byte[] bArr, char[] cArr) throws CryptorException;

    SecretKey keyForPassword(char[] cArr, byte[] bArr) throws CryptorException;
}
