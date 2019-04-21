package com.squareup.okhttp.internal;

import com.squareup.okhttp.Protocol;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.net.ssl.SSLSocket;
import okio.Buffer;

public class Platform {
    private static final Platform PLATFORM = findPlatform();

    private static class Android extends Platform {
        private final OptionalMethod<Socket> getAlpnSelectedProtocol;
        private final OptionalMethod<Socket> setAlpnProtocols;
        private final OptionalMethod<Socket> setHostname;
        private final OptionalMethod<Socket> setUseSessionTickets;
        private final Method trafficStatsTagSocket;
        private final Method trafficStatsUntagSocket;

        public Android(OptionalMethod<Socket> setUseSessionTickets, OptionalMethod<Socket> setHostname, Method trafficStatsTagSocket, Method trafficStatsUntagSocket, OptionalMethod<Socket> getAlpnSelectedProtocol, OptionalMethod<Socket> setAlpnProtocols) {
            this.setUseSessionTickets = setUseSessionTickets;
            this.setHostname = setHostname;
            this.trafficStatsTagSocket = trafficStatsTagSocket;
            this.trafficStatsUntagSocket = trafficStatsUntagSocket;
            this.getAlpnSelectedProtocol = getAlpnSelectedProtocol;
            this.setAlpnProtocols = setAlpnProtocols;
        }

        public void connectSocket(Socket socket, InetSocketAddress address, int connectTimeout) throws IOException {
            try {
                socket.connect(address, connectTimeout);
            } catch (AssertionError e) {
                if (Util.isAndroidGetsocknameError(e)) {
                    throw new IOException(e);
                }
                throw e;
            } catch (SecurityException e2) {
                IOException ioException = new IOException("Exception in connect");
                ioException.initCause(e2);
                throw ioException;
            }
        }

        public void configureTlsExtensions(SSLSocket sslSocket, String hostname, List<Protocol> protocols) {
            if (hostname != null) {
                this.setUseSessionTickets.invokeOptionalWithoutCheckedException(sslSocket, Boolean.valueOf(true));
                this.setHostname.invokeOptionalWithoutCheckedException(sslSocket, hostname);
            }
            if (this.setAlpnProtocols != null && this.setAlpnProtocols.isSupported(sslSocket)) {
                this.setAlpnProtocols.invokeWithoutCheckedException(sslSocket, Platform.concatLengthPrefixed(protocols));
            }
        }

        public String getSelectedProtocol(SSLSocket socket) {
            if (this.getAlpnSelectedProtocol == null || !this.getAlpnSelectedProtocol.isSupported(socket)) {
                return null;
            }
            byte[] alpnResult = (byte[]) this.getAlpnSelectedProtocol.invokeWithoutCheckedException(socket, new Object[0]);
            return alpnResult != null ? new String(alpnResult, Util.UTF_8) : null;
        }
    }

    private static class JdkWithJettyBootPlatform extends Platform {
        private final Class<?> clientProviderClass;
        private final Method getMethod;
        private final Method putMethod;
        private final Method removeMethod;
        private final Class<?> serverProviderClass;

        public JdkWithJettyBootPlatform(Method putMethod, Method getMethod, Method removeMethod, Class<?> clientProviderClass, Class<?> serverProviderClass) {
            this.putMethod = putMethod;
            this.getMethod = getMethod;
            this.removeMethod = removeMethod;
            this.clientProviderClass = clientProviderClass;
            this.serverProviderClass = serverProviderClass;
        }

        public void configureTlsExtensions(SSLSocket sslSocket, String hostname, List<Protocol> protocols) {
            List<String> names = new ArrayList(protocols.size());
            int size = protocols.size();
            for (int i = 0; i < size; i++) {
                Protocol protocol = (Protocol) protocols.get(i);
                if (protocol != Protocol.HTTP_1_0) {
                    names.add(protocol.toString());
                }
            }
            try {
                Object provider = Proxy.newProxyInstance(Platform.class.getClassLoader(), new Class[]{this.clientProviderClass, this.serverProviderClass}, new JettyNegoProvider(names));
                this.putMethod.invoke(null, new Object[]{sslSocket, provider});
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new AssertionError(e);
            }
        }

        public void afterHandshake(SSLSocket sslSocket) {
            try {
                this.removeMethod.invoke(null, new Object[]{sslSocket});
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new AssertionError();
            }
        }

        public String getSelectedProtocol(SSLSocket socket) {
            try {
                JettyNegoProvider provider = (JettyNegoProvider) Proxy.getInvocationHandler(this.getMethod.invoke(null, new Object[]{socket}));
                if (!provider.unsupported && provider.selected == null) {
                    Internal.logger.log(Level.INFO, "ALPN callback dropped: SPDY and HTTP/2 are disabled. Is alpn-boot on the boot class path?");
                    return null;
                } else if (provider.unsupported) {
                    return null;
                } else {
                    return provider.selected;
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new AssertionError();
            }
        }
    }

    private static class JettyNegoProvider implements InvocationHandler {
        private final List<String> protocols;
        private String selected;
        private boolean unsupported;

        public JettyNegoProvider(List<String> protocols) {
            this.protocols = protocols;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String methodName = method.getName();
            Class<?> returnType = method.getReturnType();
            if (args == null) {
                args = Util.EMPTY_STRING_ARRAY;
            }
            if (methodName.equals("supports") && Boolean.TYPE == returnType) {
                return Boolean.valueOf(true);
            }
            if (methodName.equals("unsupported") && Void.TYPE == returnType) {
                this.unsupported = true;
                return null;
            } else if (methodName.equals("protocols") && args.length == 0) {
                return this.protocols;
            } else {
                if ((methodName.equals("selectProtocol") || methodName.equals("select")) && String.class == returnType && args.length == 1 && (args[0] instanceof List)) {
                    String str;
                    List<String> peerProtocols = args[0];
                    int size = peerProtocols.size();
                    for (int i = 0; i < size; i++) {
                        if (this.protocols.contains(peerProtocols.get(i))) {
                            str = (String) peerProtocols.get(i);
                            this.selected = str;
                            return str;
                        }
                    }
                    str = (String) this.protocols.get(0);
                    this.selected = str;
                    return str;
                } else if ((!methodName.equals("protocolSelected") && !methodName.equals("selected")) || args.length != 1) {
                    return method.invoke(this, args);
                } else {
                    this.selected = (String) args[0];
                    return null;
                }
            }
        }
    }

    public static Platform get() {
        return PLATFORM;
    }

    public String getPrefix() {
        return "OkHttp";
    }

    public void logW(String warning) {
        System.out.println(warning);
    }

    public void configureTlsExtensions(SSLSocket sslSocket, String hostname, List<Protocol> list) {
    }

    public void afterHandshake(SSLSocket sslSocket) {
    }

    public String getSelectedProtocol(SSLSocket socket) {
        return null;
    }

    public void connectSocket(Socket socket, InetSocketAddress address, int connectTimeout) throws IOException {
        socket.connect(address, connectTimeout);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0145 A:{ExcHandler: ClassNotFoundException | NoSuchMethodException (e java.lang.Throwable), Splitter:B:5:0x0039, PHI: r5 } */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:18:?, code skipped:
            r18 = "org.eclipse.jetty.alpn.ALPN";
            r17 = java.lang.Class.forName(r18);
            r19 = java.lang.Class.forName(r18 + "$Provider");
            r13 = java.lang.Class.forName(r18 + "$ClientProvider");
            r14 = java.lang.Class.forName(r18 + "$ServerProvider");
            r0 = r17;
            r10 = r0.getMethod("put", new java.lang.Class[]{javax.net.ssl.SSLSocket.class, r19});
            r0 = r17;
     */
    /* JADX WARNING: Missing block: B:28:?, code skipped:
            return new com.squareup.okhttp.internal.Platform();
     */
    /* JADX WARNING: Missing block: B:29:?, code skipped:
            return new com.squareup.okhttp.internal.Platform.JdkWithJettyBootPlatform(r10, r0.getMethod("get", new java.lang.Class[]{javax.net.ssl.SSLSocket.class}), r17.getMethod("remove", new java.lang.Class[]{javax.net.ssl.SSLSocket.class}), r13, r14);
     */
    private static com.squareup.okhttp.internal.Platform findPlatform() {
        /*
        r2 = "com.android.org.conscrypt.OpenSSLSocketImpl";
        java.lang.Class.forName(r2);	 Catch:{ ClassNotFoundException -> 0x00a0 }
    L_0x0005:
        r3 = new com.squareup.okhttp.internal.OptionalMethod;	 Catch:{ ClassNotFoundException -> 0x00a8 }
        r2 = 0;
        r9 = "setUseSessionTickets";
        r22 = 1;
        r0 = r22;
        r0 = new java.lang.Class[r0];	 Catch:{ ClassNotFoundException -> 0x00a8 }
        r22 = r0;
        r23 = 0;
        r24 = java.lang.Boolean.TYPE;	 Catch:{ ClassNotFoundException -> 0x00a8 }
        r22[r23] = r24;	 Catch:{ ClassNotFoundException -> 0x00a8 }
        r0 = r22;
        r3.<init>(r2, r9, r0);	 Catch:{ ClassNotFoundException -> 0x00a8 }
        r4 = new com.squareup.okhttp.internal.OptionalMethod;	 Catch:{ ClassNotFoundException -> 0x00a8 }
        r2 = 0;
        r9 = "setHostname";
        r22 = 1;
        r0 = r22;
        r0 = new java.lang.Class[r0];	 Catch:{ ClassNotFoundException -> 0x00a8 }
        r22 = r0;
        r23 = 0;
        r24 = java.lang.String.class;
        r22[r23] = r24;	 Catch:{ ClassNotFoundException -> 0x00a8 }
        r0 = r22;
        r4.<init>(r2, r9, r0);	 Catch:{ ClassNotFoundException -> 0x00a8 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r2 = "android.net.TrafficStats";
        r21 = java.lang.Class.forName(r2);	 Catch:{ ClassNotFoundException -> 0x0142, ClassNotFoundException | NoSuchMethodException -> 0x0145 }
        r2 = "tagSocket";
        r9 = 1;
        r9 = new java.lang.Class[r9];	 Catch:{ ClassNotFoundException -> 0x0142, ClassNotFoundException | NoSuchMethodException -> 0x0145 }
        r22 = 0;
        r23 = java.net.Socket.class;
        r9[r22] = r23;	 Catch:{ ClassNotFoundException -> 0x0142, ClassNotFoundException | NoSuchMethodException -> 0x0145 }
        r0 = r21;
        r5 = r0.getMethod(r2, r9);	 Catch:{ ClassNotFoundException -> 0x0142, ClassNotFoundException | NoSuchMethodException -> 0x0145 }
        r2 = "untagSocket";
        r9 = 1;
        r9 = new java.lang.Class[r9];	 Catch:{ ClassNotFoundException -> 0x0142, ClassNotFoundException | NoSuchMethodException -> 0x0145 }
        r22 = 0;
        r23 = java.net.Socket.class;
        r9[r22] = r23;	 Catch:{ ClassNotFoundException -> 0x0142, ClassNotFoundException | NoSuchMethodException -> 0x0145 }
        r0 = r21;
        r6 = r0.getMethod(r2, r9);	 Catch:{ ClassNotFoundException -> 0x0142, ClassNotFoundException | NoSuchMethodException -> 0x0145 }
        r2 = "android.net.Network";
        java.lang.Class.forName(r2);	 Catch:{ ClassNotFoundException -> 0x014d, ClassNotFoundException | NoSuchMethodException -> 0x0145 }
        r16 = new com.squareup.okhttp.internal.OptionalMethod;	 Catch:{ ClassNotFoundException -> 0x014d, ClassNotFoundException | NoSuchMethodException -> 0x0145 }
        r2 = byte[].class;
        r9 = "getAlpnSelectedProtocol";
        r22 = 0;
        r0 = r22;
        r0 = new java.lang.Class[r0];	 Catch:{ ClassNotFoundException -> 0x014d, ClassNotFoundException | NoSuchMethodException -> 0x0145 }
        r22 = r0;
        r0 = r16;
        r1 = r22;
        r0.<init>(r2, r9, r1);	 Catch:{ ClassNotFoundException -> 0x014d, ClassNotFoundException | NoSuchMethodException -> 0x0145 }
        r20 = new com.squareup.okhttp.internal.OptionalMethod;	 Catch:{ ClassNotFoundException -> 0x0150, NoSuchMethodException -> 0x0148 }
        r2 = 0;
        r9 = "setAlpnProtocols";
        r22 = 1;
        r0 = r22;
        r0 = new java.lang.Class[r0];	 Catch:{ ClassNotFoundException -> 0x0150, NoSuchMethodException -> 0x0148 }
        r22 = r0;
        r23 = 0;
        r24 = byte[].class;
        r22[r23] = r24;	 Catch:{ ClassNotFoundException -> 0x0150, NoSuchMethodException -> 0x0148 }
        r0 = r20;
        r1 = r22;
        r0.<init>(r2, r9, r1);	 Catch:{ ClassNotFoundException -> 0x0150, NoSuchMethodException -> 0x0148 }
        r8 = r20;
        r7 = r16;
    L_0x0099:
        r2 = new com.squareup.okhttp.internal.Platform$Android;	 Catch:{ ClassNotFoundException -> 0x00a8 }
        r2.<init>(r3, r4, r5, r6, r7, r8);	 Catch:{ ClassNotFoundException -> 0x00a8 }
        r9 = r2;
    L_0x009f:
        return r9;
    L_0x00a0:
        r15 = move-exception;
        r2 = "org.apache.harmony.xnet.provider.jsse.OpenSSLSocketImpl";
        java.lang.Class.forName(r2);	 Catch:{ ClassNotFoundException -> 0x00a8 }
        goto L_0x0005;
    L_0x00a8:
        r2 = move-exception;
        r18 = "org.eclipse.jetty.alpn.ALPN";
        r17 = java.lang.Class.forName(r18);	 Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0138, NoSuchMethodException -> 0x0140 }
        r2 = new java.lang.StringBuilder;	 Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0138, NoSuchMethodException -> 0x0140 }
        r2.<init>();	 Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0138, NoSuchMethodException -> 0x0140 }
        r0 = r18;
        r2 = r2.append(r0);	 Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0138, NoSuchMethodException -> 0x0140 }
        r9 = "$Provider";
        r2 = r2.append(r9);	 Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0138, NoSuchMethodException -> 0x0140 }
        r2 = r2.toString();	 Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0138, NoSuchMethodException -> 0x0140 }
        r19 = java.lang.Class.forName(r2);	 Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0138, NoSuchMethodException -> 0x0140 }
        r2 = new java.lang.StringBuilder;	 Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0138, NoSuchMethodException -> 0x0140 }
        r2.<init>();	 Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0138, NoSuchMethodException -> 0x0140 }
        r0 = r18;
        r2 = r2.append(r0);	 Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0138, NoSuchMethodException -> 0x0140 }
        r9 = "$ClientProvider";
        r2 = r2.append(r9);	 Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0138, NoSuchMethodException -> 0x0140 }
        r2 = r2.toString();	 Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0138, NoSuchMethodException -> 0x0140 }
        r13 = java.lang.Class.forName(r2);	 Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0138, NoSuchMethodException -> 0x0140 }
        r2 = new java.lang.StringBuilder;	 Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0138, NoSuchMethodException -> 0x0140 }
        r2.<init>();	 Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0138, NoSuchMethodException -> 0x0140 }
        r0 = r18;
        r2 = r2.append(r0);	 Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0138, NoSuchMethodException -> 0x0140 }
        r9 = "$ServerProvider";
        r2 = r2.append(r9);	 Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0138, NoSuchMethodException -> 0x0140 }
        r2 = r2.toString();	 Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0138, NoSuchMethodException -> 0x0140 }
        r14 = java.lang.Class.forName(r2);	 Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0138, NoSuchMethodException -> 0x0140 }
        r2 = "put";
        r9 = 2;
        r9 = new java.lang.Class[r9];	 Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0138, NoSuchMethodException -> 0x0140 }
        r22 = 0;
        r23 = javax.net.ssl.SSLSocket.class;
        r9[r22] = r23;	 Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0138, NoSuchMethodException -> 0x0140 }
        r22 = 1;
        r9[r22] = r19;	 Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0138, NoSuchMethodException -> 0x0140 }
        r0 = r17;
        r10 = r0.getMethod(r2, r9);	 Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0138, NoSuchMethodException -> 0x0140 }
        r2 = "get";
        r9 = 1;
        r9 = new java.lang.Class[r9];	 Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0138, NoSuchMethodException -> 0x0140 }
        r22 = 0;
        r23 = javax.net.ssl.SSLSocket.class;
        r9[r22] = r23;	 Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0138, NoSuchMethodException -> 0x0140 }
        r0 = r17;
        r11 = r0.getMethod(r2, r9);	 Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0138, NoSuchMethodException -> 0x0140 }
        r2 = "remove";
        r9 = 1;
        r9 = new java.lang.Class[r9];	 Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0138, NoSuchMethodException -> 0x0140 }
        r22 = 0;
        r23 = javax.net.ssl.SSLSocket.class;
        r9[r22] = r23;	 Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0138, NoSuchMethodException -> 0x0140 }
        r0 = r17;
        r12 = r0.getMethod(r2, r9);	 Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0138, NoSuchMethodException -> 0x0140 }
        r9 = new com.squareup.okhttp.internal.Platform$JdkWithJettyBootPlatform;	 Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0138, NoSuchMethodException -> 0x0140 }
        r9.<init>(r10, r11, r12, r13, r14);	 Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0138, NoSuchMethodException -> 0x0140 }
        goto L_0x009f;
    L_0x0138:
        r2 = move-exception;
    L_0x0139:
        r9 = new com.squareup.okhttp.internal.Platform;
        r9.<init>();
        goto L_0x009f;
    L_0x0140:
        r2 = move-exception;
        goto L_0x0139;
    L_0x0142:
        r2 = move-exception;
        goto L_0x0099;
    L_0x0145:
        r2 = move-exception;
        goto L_0x0099;
    L_0x0148:
        r2 = move-exception;
        r7 = r16;
        goto L_0x0099;
    L_0x014d:
        r2 = move-exception;
        goto L_0x0099;
    L_0x0150:
        r2 = move-exception;
        r7 = r16;
        goto L_0x0099;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.Platform.findPlatform():com.squareup.okhttp.internal.Platform");
    }

    static byte[] concatLengthPrefixed(List<Protocol> protocols) {
        Buffer result = new Buffer();
        int size = protocols.size();
        for (int i = 0; i < size; i++) {
            Protocol protocol = (Protocol) protocols.get(i);
            if (protocol != Protocol.HTTP_1_0) {
                result.writeByte(protocol.toString().length());
                result.writeUtf8(protocol.toString());
            }
        }
        return result.readByteArray();
    }
}
