package p049hk.com.aisoft.easyaddrui;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/* renamed from: hk.com.aisoft.easyaddrui.AES256JNCryptorInputStream */
public class AES256JNCryptorInputStream extends InputStream {
    private SecretKey decryptionKey;
    private boolean endOfStreamHandled;
    private SecretKey hmacKey;
    /* renamed from: in */
    private final InputStream f7859in;
    private final boolean isPasswordEncrypted;
    private Mac mac;
    private char[] password;
    private PushbackInputStream pushbackInputStream;
    private TrailerInputStream trailerIn;

    /* renamed from: hk.com.aisoft.easyaddrui.AES256JNCryptorInputStream$MacUpdateInputStream */
    private static class MacUpdateInputStream extends FilterInputStream {
        Mac mac;

        private MacUpdateInputStream(InputStream in, Mac mac) {
            super(in);
            this.mac = mac;
        }

        public int read() throws IOException {
            int b = super.read();
            if (b >= 0) {
                this.mac.update((byte) b);
            }
            return b;
        }

        public int read(byte[] b, int off, int len) throws IOException {
            int n = super.read(b, off, len);
            if (n > 0) {
                this.mac.update(b, off, n);
            }
            return n;
        }
    }

    public boolean markSupported() {
        return false;
    }

    private void initializeStream() throws IOException {
        int headerDataSize;
        if (this.isPasswordEncrypted) {
            headerDataSize = 34;
        } else {
            headerDataSize = 18;
        }
        byte[] headerData = new byte[headerDataSize];
        StreamUtils.readAllBytesOrFail(this.f7859in, headerData);
        int offset = 0 + 1;
        if (headerData[0] != (byte) 3) {
            throw new IOException(String.format("Expected version %d but found %d.", new Object[]{Integer.valueOf(3), Byte.valueOf(headerData[0])}));
        }
        int offset2 = offset + 1;
        byte options = headerData[offset];
        if (this.isPasswordEncrypted) {
            if (options != (byte) 1) {
                throw new IOException("Expected password flag missing.");
            }
            byte[] decryptionSalt = new byte[8];
            System.arraycopy(headerData, offset2, decryptionSalt, 0, decryptionSalt.length);
            offset2 = decryptionSalt.length + 2;
            byte[] hmacSalt = new byte[8];
            System.arraycopy(headerData, offset2, hmacSalt, 0, hmacSalt.length);
            offset2 += hmacSalt.length;
            JNCryptor cryptor = new AES256JNCryptor();
            try {
                this.decryptionKey = cryptor.keyForPassword(this.password, decryptionSalt);
                this.hmacKey = cryptor.keyForPassword(this.password, hmacSalt);
            } catch (CryptorException e) {
                throw new IOException("Failed to derive keys from password.", e);
            }
        } else if (options != (byte) 0) {
            throw new IOException("Expected options byte to be zero.");
        }
        byte[] iv = new byte[16];
        System.arraycopy(headerData, offset2, iv, 0, iv.length);
        this.trailerIn = new TrailerInputStream(this.f7859in, 32);
        try {
            Cipher decryptCipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            decryptCipher.init(2, this.decryptionKey, new IvParameterSpec(iv));
            this.mac = Mac.getInstance("HmacSHA256");
            this.mac.init(this.hmacKey);
            this.mac.update(headerData);
            this.pushbackInputStream = new PushbackInputStream(new CipherInputStream(new MacUpdateInputStream(this.trailerIn, this.mac), decryptCipher), 1);
        } catch (GeneralSecurityException e2) {
            throw new IOException("Failed to initiate cipher.", e2);
        }
    }

    public int read() throws IOException, StreamIntegrityException {
        if (this.trailerIn == null) {
            initializeStream();
        }
        return completeRead(this.pushbackInputStream.read());
    }

    public int read(byte[] b) throws IOException, StreamIntegrityException {
        Validate.notNull(b, "Array cannot be null.", new Object[0]);
        return read(b, 0, b.length);
    }

    public int read(byte[] b, int off, int len) throws IOException {
        boolean z;
        boolean z2 = true;
        Validate.notNull(b, "Byte array cannot be null.", new Object[0]);
        Validate.isTrue(off >= 0, "Offset cannot be negative.", new Object[0]);
        if (len >= 0) {
            z = true;
        } else {
            z = false;
        }
        Validate.isTrue(z, "Length cannot be negative.", new Object[0]);
        if (len + off > b.length) {
            z2 = false;
        }
        Validate.isTrue(z2, "Length plus offset cannot be longer than byte array.", new Object[0]);
        if (len == 0) {
            return 0;
        }
        if (this.trailerIn == null) {
            initializeStream();
        }
        return completeRead(this.pushbackInputStream.read(b, off, len));
    }

    private int completeRead(int b) throws IOException, StreamIntegrityException {
        if (b == -1) {
            handleEndOfStream();
        } else {
            int c = this.pushbackInputStream.read();
            if (c == -1) {
                handleEndOfStream();
            } else {
                this.pushbackInputStream.unread(c);
            }
        }
        return b;
    }

    private void handleEndOfStream() throws StreamIntegrityException {
        if (!this.endOfStreamHandled) {
            this.endOfStreamHandled = true;
            if (!AES256JNCryptor.arraysEqual(this.trailerIn.getTrailer(), this.mac.doFinal())) {
                throw new StreamIntegrityException("MAC validation failed.");
            }
        }
    }

    public void close() throws IOException {
        try {
            AES256JNCryptorInputStream.closeIfNotNull(this.pushbackInputStream);
        } finally {
            AES256JNCryptorInputStream.closeIfNotNull(this.trailerIn);
        }
    }

    private static void closeIfNotNull(InputStream in) throws IOException {
        if (in != null) {
            in.close();
        }
    }
}
