package p049hk.com.aisoft.easyaddrui;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.Mac;

/* renamed from: hk.com.aisoft.easyaddrui.AES256JNCryptorOutputStream */
public class AES256JNCryptorOutputStream extends OutputStream {
    private CipherOutputStream cipherStream;
    private byte[] encryptionSalt;
    private byte[] hmacSalt;
    /* renamed from: iv */
    private byte[] f7860iv;
    private MacOutputStream macOutputStream;
    private final boolean passwordBased;
    private boolean writtenHeader;

    /* renamed from: hk.com.aisoft.easyaddrui.AES256JNCryptorOutputStream$MacOutputStream */
    private static class MacOutputStream extends FilterOutputStream {
        private final Mac mac;

        public void write(int b) throws IOException {
            this.mac.update((byte) b);
            this.out.write(b);
        }

        public void write(byte[] b, int off, int len) throws IOException {
            this.mac.update(b, off, len);
            this.out.write(b, off, len);
        }

        public void close() throws IOException {
            this.out.write(this.mac.doFinal());
            this.out.flush();
            this.out.close();
        }
    }

    private void writeHeader() throws IOException {
        if (this.passwordBased) {
            this.macOutputStream.write(3);
            this.macOutputStream.write(1);
            this.macOutputStream.write(this.encryptionSalt);
            this.macOutputStream.write(this.hmacSalt);
            this.macOutputStream.write(this.f7860iv);
            return;
        }
        this.macOutputStream.write(3);
        this.macOutputStream.write(0);
        this.macOutputStream.write(this.f7860iv);
    }

    public void write(int b) throws IOException {
        if (!this.writtenHeader) {
            writeHeader();
            this.writtenHeader = true;
        }
        this.cipherStream.write(b);
    }

    public void write(byte[] b, int off, int len) throws IOException {
        if (!this.writtenHeader) {
            writeHeader();
            this.writtenHeader = true;
        }
        this.cipherStream.write(b, off, len);
    }

    public void close() throws IOException {
        this.cipherStream.close();
    }
}
