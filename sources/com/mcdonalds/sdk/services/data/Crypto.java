package com.mcdonalds.sdk.services.data;

import android.content.Context;
import android.util.Base64;
import com.mcdonalds.sdk.services.log.SafeLog;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Crypto {
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String ENCRYPTION = "AES";
    private static final String FILENAME = "fknjew.kdf";
    private static final int IV_LENGTH = 16;
    private static final int KEY_LENGTH = 256;
    private static final String LOG_TAG = Crypto.class.getSimpleName();
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private Context mContext;
    private SecretKey mSecretKey = getSecretKey();

    public Crypto(Context context) throws NoSuchAlgorithmException, IOException {
        this.mContext = context;
    }

    public String encrypt(String text) throws GeneralSecurityException {
        byte[] ciphertext;
        IvParameterSpec ivspec = new IvParameterSpec(generateIv());
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(1, this.mSecretKey, ivspec);
        try {
            ciphertext = cipher.doFinal(text.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            ciphertext = cipher.doFinal(text.getBytes());
        }
        return String.format("%s:%s", new Object[]{bytesToStorableString(iv), bytesToStorableString(ciphertext)});
    }

    public String decrypt(String encrypted) {
        try {
            return new String(decryptToBytes(this.mSecretKey, encrypted), "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private SecretKey getSecretKey() throws NoSuchAlgorithmException, IOException {
        if (this.mSecretKey == null) {
            try {
                this.mSecretKey = readSecretFromLocalStorage();
            } catch (FileNotFoundException e) {
                SafeLog.m7746e(LOG_TAG, "error retrieving secret key", e);
            }
            if (this.mSecretKey == null) {
                this.mSecretKey = generateNewKey();
                writeSecretToLocalStorage(this.mSecretKey);
            }
        }
        return this.mSecretKey;
    }

    private SecretKey generateNewKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ENCRYPTION);
        keyGenerator.init(256, SECURE_RANDOM);
        return keyGenerator.generateKey();
    }

    private void writeSecretToLocalStorage(SecretKey secretKey) throws IOException {
        FileOutputStream fos = this.mContext.openFileOutput(FILENAME, 0);
        fos.write(secretKey.getEncoded());
        fos.close();
    }

    private SecretKey readSecretFromLocalStorage() throws IOException {
        FileInputStream fis = this.mContext.openFileInput(FILENAME);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[8192];
        while (true) {
            int bytesRead = fis.read(buffer);
            if (bytesRead != -1) {
                baos.write(buffer, 0, bytesRead);
            } else {
                byte[] bytes = baos.toByteArray();
                baos.close();
                fis.close();
                return new SecretKeySpec(bytes, 0, bytes.length, ENCRYPTION);
            }
        }
    }

    private byte[] generateIv() {
        byte[] bytes = new byte[16];
        SECURE_RANDOM.nextBytes(bytes);
        return bytes;
    }

    private byte[] decryptToBytes(SecretKey key, String ciphertext) {
        try {
            String[] parts = ciphertext.split(":");
            String iv = parts[0];
            String ctext = parts[1];
            IvParameterSpec ivspec = new IvParameterSpec(bytesFromStorableString(iv));
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(2, key, ivspec);
            return cipher.doFinal(bytesFromStorableString(ctext));
        } catch (GeneralSecurityException e) {
            SafeLog.m7746e(LOG_TAG, String.format("Unable to decrypt data: %s : %s", new Object[]{bytesToStorableString(key.getEncoded()), ciphertext}), e);
            return new byte[0];
        } catch (RuntimeException e2) {
            return new byte[0];
        }
    }

    private byte[] bytesFromStorableString(String string) {
        return Base64.decode(string, 0);
    }

    private String bytesToStorableString(byte[] bytes) {
        return Base64.encodeToString(bytes, 0);
    }
}
