package com.facebook.stetho.dumpapp;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;

public class Dumper {
    private final Map<String, DumperPlugin> mDumperPlugins;
    private final GlobalOptions mGlobalOptions;
    private final CommandLineParser mParser;

    public Dumper(Iterable<DumperPlugin> dumperPlugins) {
        this(dumperPlugins, new GnuParser());
    }

    public Dumper(Iterable<DumperPlugin> dumperPlugins, CommandLineParser parser) {
        this.mDumperPlugins = generatePluginMap(dumperPlugins);
        this.mParser = parser;
        this.mGlobalOptions = new GlobalOptions();
    }

    private static Map<String, DumperPlugin> generatePluginMap(Iterable<DumperPlugin> plugins) {
        Map<String, DumperPlugin> map = new HashMap();
        for (DumperPlugin plugin : plugins) {
            map.put(plugin.getName(), plugin);
        }
        return Collections.unmodifiableMap(map);
    }

    public int dump(InputStream input, PrintStream out, PrintStream err, String[] args) {
        int i = 1;
        try {
            return doDump(input, out, err, args);
        } catch (ParseException e) {
            err.println(e.getMessage());
            dumpUsage(err);
            return i;
        } catch (DumpException e2) {
            err.println(e2.getMessage());
            return i;
        } catch (DumpappOutputBrokenException e3) {
            throw e3;
        } catch (RuntimeException e4) {
            e4.printStackTrace(err);
            return i;
        }
    }

    private int doDump(InputStream input, PrintStream out, PrintStream err, String[] args) throws ParseException, DumpException {
        CommandLine parsedArgs = this.mParser.parse(this.mGlobalOptions.options, args, true);
        if (parsedArgs.hasOption(this.mGlobalOptions.optionHelp.getOpt())) {
            dumpUsage(out);
            return 0;
        } else if (parsedArgs.hasOption(this.mGlobalOptions.optionListPlugins.getOpt())) {
            dumpAvailablePlugins(out);
            return 0;
        } else if (parsedArgs.getArgList().isEmpty()) {
            dumpUsage(err);
            return 1;
        } else {
            dumpPluginOutput(input, out, err, parsedArgs);
            return 0;
        }
    }

    private void dumpAvailablePlugins(PrintStream output) {
        List<String> pluginNames = new ArrayList();
        for (DumperPlugin pluginToDump : this.mDumperPlugins.values()) {
            pluginNames.add(pluginToDump.getName());
        }
        Collections.sort(pluginNames);
        for (String pluginName : pluginNames) {
            output.println(pluginName);
        }
    }

    private void dumpPluginOutput(InputStream input, PrintStream out, PrintStream err, CommandLine parsedArgs) throws DumpException {
        List<String> args = new ArrayList(parsedArgs.getArgList());
        if (args.size() < 1) {
            throw new DumpException("Expected plugin argument");
        }
        String pluginName = (String) args.remove(0);
        DumperPlugin plugin = (DumperPlugin) this.mDumperPlugins.get(pluginName);
        if (plugin == null) {
            throw new DumpException("No plugin named '" + pluginName + "'");
        }
        plugin.dump(new DumperContext(input, out, err, this.mParser, args));
    }

    private void dumpUsage(PrintStream output) {
        String cmdName = "dumpapp";
        HelpFormatter formatter = new HelpFormatter();
        output.println("Usage: dumpapp [options] <plugin> [plugin-options]");
        PrintWriter writer = new PrintWriter(output);
        try {
            formatter.printOptions(writer, formatter.getWidth(), this.mGlobalOptions.options, formatter.getLeftPadding(), formatter.getDescPadding());
        } finally {
            writer.flush();
        }
    }
}
