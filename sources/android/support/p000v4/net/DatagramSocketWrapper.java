package android.support.p000v4.net;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketImpl;

/* renamed from: android.support.v4.net.DatagramSocketWrapper */
class DatagramSocketWrapper extends Socket {

    /* renamed from: android.support.v4.net.DatagramSocketWrapper$DatagramSocketImplWrapper */
    private static class DatagramSocketImplWrapper extends SocketImpl {
        public DatagramSocketImplWrapper(DatagramSocket socket, FileDescriptor fd) {
            this.localport = socket.getLocalPort();
            this.fd = fd;
        }

        /* Access modifiers changed, original: protected */
        public void accept(SocketImpl newSocket) throws IOException {
            throw new UnsupportedOperationException();
        }

        /* Access modifiers changed, original: protected */
        public int available() throws IOException {
            throw new UnsupportedOperationException();
        }

        /* Access modifiers changed, original: protected */
        public void bind(InetAddress address, int port) throws IOException {
            throw new UnsupportedOperationException();
        }

        /* Access modifiers changed, original: protected */
        public void close() throws IOException {
            throw new UnsupportedOperationException();
        }

        /* Access modifiers changed, original: protected */
        public void connect(String host, int port) throws IOException {
            throw new UnsupportedOperationException();
        }

        /* Access modifiers changed, original: protected */
        public void connect(InetAddress address, int port) throws IOException {
            throw new UnsupportedOperationException();
        }

        /* Access modifiers changed, original: protected */
        public void create(boolean isStreaming) throws IOException {
            throw new UnsupportedOperationException();
        }

        /* Access modifiers changed, original: protected */
        public InputStream getInputStream() throws IOException {
            throw new UnsupportedOperationException();
        }

        /* Access modifiers changed, original: protected */
        public OutputStream getOutputStream() throws IOException {
            throw new UnsupportedOperationException();
        }

        /* Access modifiers changed, original: protected */
        public void listen(int backlog) throws IOException {
            throw new UnsupportedOperationException();
        }

        /* Access modifiers changed, original: protected */
        public void connect(SocketAddress remoteAddr, int timeout) throws IOException {
            throw new UnsupportedOperationException();
        }

        /* Access modifiers changed, original: protected */
        public void sendUrgentData(int value) throws IOException {
            throw new UnsupportedOperationException();
        }

        public Object getOption(int optID) throws SocketException {
            throw new UnsupportedOperationException();
        }

        public void setOption(int optID, Object val) throws SocketException {
            throw new UnsupportedOperationException();
        }
    }

    public DatagramSocketWrapper(DatagramSocket socket, FileDescriptor fd) throws SocketException {
        super(new DatagramSocketImplWrapper(socket, fd));
    }
}
