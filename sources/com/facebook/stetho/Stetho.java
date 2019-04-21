package com.facebook.stetho;

import android.app.Application;
import android.content.Context;
import android.os.Build.VERSION;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.Utf8Charset;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.dumpapp.Dumper;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.facebook.stetho.dumpapp.RawDumpappHandler;
import com.facebook.stetho.dumpapp.StreamingDumpappHandler;
import com.facebook.stetho.dumpapp.plugins.CrashDumperPlugin;
import com.facebook.stetho.dumpapp.plugins.FilesDumperPlugin;
import com.facebook.stetho.dumpapp.plugins.HprofDumperPlugin;
import com.facebook.stetho.dumpapp.plugins.SharedPreferencesDumperPlugin;
import com.facebook.stetho.inspector.ChromeDevtoolsServer;
import com.facebook.stetho.inspector.ChromeDiscoveryHandler;
import com.facebook.stetho.inspector.console.RuntimeReplFactory;
import com.facebook.stetho.inspector.database.DatabaseFilesProvider;
import com.facebook.stetho.inspector.database.DefaultDatabaseFilesProvider;
import com.facebook.stetho.inspector.elements.Document;
import com.facebook.stetho.inspector.elements.DocumentProviderFactory;
import com.facebook.stetho.inspector.elements.android.ActivityTracker;
import com.facebook.stetho.inspector.elements.android.AndroidDocumentProviderFactory;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.facebook.stetho.inspector.protocol.module.CSS;
import com.facebook.stetho.inspector.protocol.module.Console;
import com.facebook.stetho.inspector.protocol.module.DOM;
import com.facebook.stetho.inspector.protocol.module.DOMStorage;
import com.facebook.stetho.inspector.protocol.module.Database;
import com.facebook.stetho.inspector.protocol.module.Debugger;
import com.facebook.stetho.inspector.protocol.module.HeapProfiler;
import com.facebook.stetho.inspector.protocol.module.Inspector;
import com.facebook.stetho.inspector.protocol.module.Network;
import com.facebook.stetho.inspector.protocol.module.Page;
import com.facebook.stetho.inspector.protocol.module.Profiler;
import com.facebook.stetho.inspector.protocol.module.Runtime;
import com.facebook.stetho.inspector.protocol.module.Worker;
import com.facebook.stetho.inspector.runtime.RhinoDetectingRuntimeReplFactory;
import com.facebook.stetho.server.LocalSocketHttpServer;
import com.facebook.stetho.server.RegistryInitializer;
import com.facebook.stetho.websocket.WebSocketHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;
import org.apache.http.protocol.HttpRequestHandlerRegistry;

public class Stetho {
    private static final String LISTENER_THREAD_NAME = "Stetho-Listener";

    public static abstract class Initializer implements RegistryInitializer {
        private final Context mContext;

        private static class LoggingCatchAllHandler implements HttpRequestHandler {
            private LoggingCatchAllHandler() {
            }

            /* synthetic */ LoggingCatchAllHandler(C19661 x0) {
                this();
            }

            public void handle(HttpRequest request, HttpResponse response, HttpContext context) throws HttpException, IOException {
                LogUtil.m7459w("Unsupported request received: " + request.getRequestLine());
                response.setStatusCode(404);
                response.setReasonPhrase("Not Found");
                response.setEntity(new StringEntity("Endpoint not implemented\n", Utf8Charset.NAME));
            }
        }

        @Nullable
        public abstract Iterable<DumperPlugin> getDumperPlugins();

        @Nullable
        public abstract Iterable<ChromeDevtoolsDomain> getInspectorModules();

        protected Initializer(Context context) {
            this.mContext = context.getApplicationContext();
        }

        public final HttpRequestHandlerRegistry getRegistry() {
            HttpRequestHandlerRegistry registry = new HttpRequestHandlerRegistry();
            Iterable<DumperPlugin> dumperPlugins = getDumperPlugins();
            if (dumperPlugins != null) {
                Dumper dumper = new Dumper(dumperPlugins);
                registry.register("/dumpapp", new StreamingDumpappHandler(this.mContext, dumper));
                registry.register("/dumpapp-raw", new RawDumpappHandler(this.mContext, dumper));
            }
            Iterable<ChromeDevtoolsDomain> inspectorModules = getInspectorModules();
            if (inspectorModules != null) {
                new ChromeDiscoveryHandler(this.mContext, ChromeDevtoolsServer.PATH).register(registry);
                registry.register(ChromeDevtoolsServer.PATH, new WebSocketHandler(this.mContext, new ChromeDevtoolsServer(inspectorModules)));
            }
            addCustomEntries(registry);
            registry.register("/*", new LoggingCatchAllHandler());
            return registry;
        }

        /* Access modifiers changed, original: protected */
        public void addCustomEntries(HttpRequestHandlerRegistry registry) {
        }
    }

    private static class BuilderBasedInitializer extends Initializer {
        @Nullable
        private final DumperPluginsProvider mDumperPlugins;
        @Nullable
        private final InspectorModulesProvider mInspectorModules;

        /* synthetic */ BuilderBasedInitializer(InitializerBuilder x0, C19661 x1) {
            this(x0);
        }

        private BuilderBasedInitializer(InitializerBuilder b) {
            super(b.mContext);
            this.mDumperPlugins = b.mDumperPlugins;
            this.mInspectorModules = b.mInspectorModules;
        }

        /* Access modifiers changed, original: protected */
        @Nullable
        public Iterable<DumperPlugin> getDumperPlugins() {
            return this.mDumperPlugins != null ? this.mDumperPlugins.get() : null;
        }

        /* Access modifiers changed, original: protected */
        @Nullable
        public Iterable<ChromeDevtoolsDomain> getInspectorModules() {
            return this.mInspectorModules != null ? this.mInspectorModules.get() : null;
        }
    }

    public static final class DefaultDumperPluginsBuilder {
        private final Context mContext;
        private final PluginBuilder<DumperPlugin> mDelegate = new PluginBuilder();

        public DefaultDumperPluginsBuilder(Context context) {
            this.mContext = context;
        }

        public DefaultDumperPluginsBuilder provide(DumperPlugin plugin) {
            this.mDelegate.provide(plugin.getName(), plugin);
            return this;
        }

        private DefaultDumperPluginsBuilder provideIfDesired(DumperPlugin plugin) {
            this.mDelegate.provideIfDesired(plugin.getName(), plugin);
            return this;
        }

        public DefaultDumperPluginsBuilder remove(String pluginName) {
            this.mDelegate.remove(pluginName);
            return this;
        }

        public Iterable<DumperPlugin> finish() {
            provideIfDesired(new HprofDumperPlugin(this.mContext));
            provideIfDesired(new SharedPreferencesDumperPlugin(this.mContext));
            provideIfDesired(new CrashDumperPlugin());
            provideIfDesired(new FilesDumperPlugin(this.mContext));
            return this.mDelegate.finish();
        }
    }

    public static final class DefaultInspectorModulesBuilder {
        private final Application mContext;
        @Nullable
        private DatabaseFilesProvider mDatabaseFiles;
        private final PluginBuilder<ChromeDevtoolsDomain> mDelegate = new PluginBuilder();
        @Nullable
        private DocumentProviderFactory mDocumentProvider;
        @Nullable
        private RuntimeReplFactory mRuntimeRepl;

        public DefaultInspectorModulesBuilder(Context context) {
            this.mContext = (Application) context.getApplicationContext();
        }

        public DefaultInspectorModulesBuilder documentProvider(DocumentProviderFactory factory) {
            this.mDocumentProvider = factory;
            return this;
        }

        public DefaultInspectorModulesBuilder runtimeRepl(RuntimeReplFactory factory) {
            this.mRuntimeRepl = factory;
            return this;
        }

        public DefaultInspectorModulesBuilder databaseFiles(DatabaseFilesProvider provider) {
            this.mDatabaseFiles = provider;
            return this;
        }

        @Deprecated
        public DefaultInspectorModulesBuilder provide(ChromeDevtoolsDomain module) {
            this.mDelegate.provide(module.getClass().getName(), module);
            return this;
        }

        private DefaultInspectorModulesBuilder provideIfDesired(ChromeDevtoolsDomain module) {
            this.mDelegate.provideIfDesired(module.getClass().getName(), module);
            return this;
        }

        @Deprecated
        public DefaultInspectorModulesBuilder remove(String moduleName) {
            this.mDelegate.remove(moduleName);
            return this;
        }

        public Iterable<ChromeDevtoolsDomain> finish() {
            provideIfDesired(new Console());
            provideIfDesired(new Debugger());
            DocumentProviderFactory documentModel = resolveDocumentProvider();
            if (documentModel != null) {
                Document document = new Document(documentModel);
                provideIfDesired(new DOM(document));
                provideIfDesired(new CSS(document));
            }
            provideIfDesired(new DOMStorage(this.mContext));
            provideIfDesired(new HeapProfiler());
            provideIfDesired(new Inspector());
            provideIfDesired(new Network(this.mContext));
            provideIfDesired(new Page(this.mContext));
            provideIfDesired(new Profiler());
            provideIfDesired(new Runtime(this.mRuntimeRepl != null ? this.mRuntimeRepl : new RhinoDetectingRuntimeReplFactory(this.mContext)));
            provideIfDesired(new Worker());
            if (VERSION.SDK_INT >= 11) {
                provideIfDesired(new Database(this.mContext, this.mDatabaseFiles != null ? this.mDatabaseFiles : new DefaultDatabaseFilesProvider(this.mContext)));
            }
            return this.mDelegate.finish();
        }

        @Nullable
        private DocumentProviderFactory resolveDocumentProvider() {
            if (this.mDocumentProvider != null) {
                return this.mDocumentProvider;
            }
            if (VERSION.SDK_INT >= 14) {
                return new AndroidDocumentProviderFactory(this.mContext);
            }
            return null;
        }
    }

    public static class InitializerBuilder {
        final Context mContext;
        @Nullable
        DumperPluginsProvider mDumperPlugins;
        @Nullable
        InspectorModulesProvider mInspectorModules;

        /* synthetic */ InitializerBuilder(Context x0, C19661 x1) {
            this(x0);
        }

        private InitializerBuilder(Context context) {
            this.mContext = context.getApplicationContext();
        }

        public InitializerBuilder enableDumpapp(DumperPluginsProvider plugins) {
            this.mDumperPlugins = (DumperPluginsProvider) Util.throwIfNull(plugins);
            return this;
        }

        public InitializerBuilder enableWebKitInspector(InspectorModulesProvider modules) {
            this.mInspectorModules = modules;
            return this;
        }

        public Initializer build() {
            return new BuilderBasedInitializer(this, null);
        }
    }

    private static class PluginBuilder<T> {
        private boolean mFinished;
        private final ArrayList<T> mPlugins;
        private final Set<String> mProvidedNames;
        private final Set<String> mRemovedNames;

        private PluginBuilder() {
            this.mProvidedNames = new HashSet();
            this.mRemovedNames = new HashSet();
            this.mPlugins = new ArrayList();
        }

        /* synthetic */ PluginBuilder(C19661 x0) {
            this();
        }

        public void provide(String name, T plugin) {
            throwIfFinished();
            this.mPlugins.add(plugin);
            this.mProvidedNames.add(name);
        }

        public void provideIfDesired(String name, T plugin) {
            throwIfFinished();
            if (!this.mRemovedNames.contains(name) && this.mProvidedNames.add(name)) {
                this.mPlugins.add(plugin);
            }
        }

        public void remove(String pluginName) {
            throwIfFinished();
            this.mRemovedNames.remove(pluginName);
        }

        private void throwIfFinished() {
            if (this.mFinished) {
                throw new IllegalStateException("Must not continue to build after finish()");
            }
        }

        public Iterable<T> finish() {
            this.mFinished = true;
            return this.mPlugins;
        }
    }

    private Stetho() {
    }

    public static InitializerBuilder newInitializerBuilder(Context context) {
        return new InitializerBuilder(context, null);
    }

    public static void initializeWithDefaults(final Context context) {
        initialize(new Initializer(context) {
            /* Access modifiers changed, original: protected */
            public Iterable<DumperPlugin> getDumperPlugins() {
                return new DefaultDumperPluginsBuilder(context).finish();
            }

            /* Access modifiers changed, original: protected */
            public Iterable<ChromeDevtoolsDomain> getInspectorModules() {
                return new DefaultInspectorModulesBuilder(context).finish();
            }
        });
    }

    public static void initialize(final Initializer initializer) {
        if (!ActivityTracker.get().beginTrackingIfPossible((Application) initializer.mContext.getApplicationContext())) {
            LogUtil.m7459w("Automatic activity tracking not available on this API level, caller must invoke ActivityTracker methods manually!");
        }
        new Thread(LISTENER_THREAD_NAME) {
            public void run() {
                try {
                    new LocalSocketHttpServer(initializer).run();
                } catch (IOException e) {
                    LogUtil.m7449e(e, "Could not start Stetho");
                }
            }
        }.start();
    }

    public static DumperPluginsProvider defaultDumperPluginsProvider(final Context context) {
        return new DumperPluginsProvider() {
            public Iterable<DumperPlugin> get() {
                return new DefaultDumperPluginsBuilder(context).finish();
            }
        };
    }

    public static InspectorModulesProvider defaultInspectorModulesProvider(final Context context) {
        return new InspectorModulesProvider() {
            public Iterable<ChromeDevtoolsDomain> get() {
                return new DefaultInspectorModulesBuilder(context).finish();
            }
        };
    }
}
