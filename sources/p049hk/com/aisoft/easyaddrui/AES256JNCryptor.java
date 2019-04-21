package p049hk.com.aisoft.easyaddrui;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: hk.com.aisoft.easyaddrui.AES256JNCryptor */
public class AES256JNCryptor implements JNCryptor {
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private int iterations = 100;

    public SecretKey keyForPassword(char[] password, byte[] salt) throws CryptorException {
        boolean z;
        Validate.notNull(salt, "Salt value cannot be null.", new Object[0]);
        if (salt.length == 8) {
            z = true;
        } else {
            z = false;
        }
        Validate.isTrue(z, "Salt value must be %d bytes.", Integer.valueOf(8));
        Validate.notNull(password, "Password cannot be null.", new Object[0]);
        if (password.length > 0) {
            z = true;
        } else {
            z = false;
        }
        Validate.isTrue(z, "Password cannot be empty.", new Object[0]);
        try {
            return new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec(password, salt, getPBKDFIterations(), 256)).getEncoded(), "AES");
        } catch (GeneralSecurityException e) {
            throw new CryptorException(String.format("Failed to generate key from password using %s.", new Object[]{"PBKDF2WithHmacSHA1"}), e);
        }
    }

    public synchronized int getPBKDFIterations() {
        return this.iterations;
    }

    public byte[] encryptData(byte[] plaintext, char[] password, byte[] encryptionSalt, byte[] hmacSalt, byte[] iv) throws CryptorException {
        boolean z;
        Validate.notNull(plaintext, "Plaintext cannot be null.", new Object[0]);
        Validate.notNull(password, "Password cannot be null.", new Object[0]);
        if (password.length > 0) {
            z = true;
        } else {
            z = false;
        }
        Validate.isTrue(z, "Password cannot be empty.", new Object[0]);
        Validate.isCorrectLength(encryptionSalt, 8, "Encryption salt");
        Validate.isCorrectLength(hmacSalt, 8, "HMAC salt");
        Validate.isCorrectLength(iv, 16, "IV");
        return encryptData(plaintext, new PasswordKey(keyForPassword(password, encryptionSalt), encryptionSalt), new PasswordKey(keyForPassword(password, hmacSalt), hmacSalt), iv);
    }

    public byte[] encryptData(byte[] plaintext, char[] password) throws CryptorException {
        boolean z;
        Validate.notNull(plaintext, "Plaintext cannot be null.", new Object[0]);
        Validate.notNull(password, "Password cannot be null.", new Object[0]);
        if (password.length > 0) {
            z = true;
        } else {
            z = false;
        }
        Validate.isTrue(z, "Password cannot be empty.", new Object[0]);
        return encryptData(plaintext, password, AES256JNCryptor.getSecureRandomData(8), AES256JNCryptor.getSecureRandomData(8), AES256JNCryptor.getSecureRandomData(16));
    }

    static byte[] getSecureRandomData(int length) {
        byte[] result = new byte[length];
        SECURE_RANDOM.nextBytes(result);
        return result;
    }

    static boolean arraysEqual(byte[] array1, byte[] array2) {
        if (array1.length != array2.length) {
            return false;
        }
        boolean isEqual = true;
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) {
                isEqual = false;
            }
        }
        return isEqual;
    }

    /* Access modifiers changed, original: 0000 */
    public byte[] encryptData(byte[] plaintext, PasswordKey encryptionKey, PasswordKey hmacKey, byte[] iv) throws CryptorException {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(1, encryptionKey.getKey(), new IvParameterSpec(iv));
            AES256v3Ciphertext output = new AES256v3Ciphertext(encryptionKey.getSalt(), hmacKey.getSalt(), iv, cipher.doFinal(plaintext));
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(hmacKey.getKey());
            output.setHmac(mac.doFinal(output.getDataToHMAC()));
            return output.getRawData();
        } catch (InvalidKeyException e) {
            throw new CryptorException("Caught InvalidKeyException. Do you have unlimited strength jurisdiction files installed?", e);
        } catch (GeneralSecurityException e2) {
            throw new CryptorException("Failed to generate ciphertext.", e2);
        }
    }
}
