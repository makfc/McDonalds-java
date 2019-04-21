package com.facebook.stetho.dumpapp;

import com.mcdonalds.sdk.connectors.autonavi.AutoNavi.Methods;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class GlobalOptions {
    public final Option optionHelp = new Option("h", "help", false, "Print this help");
    public final Option optionListPlugins = new Option("l", Methods.LIST, false, "List available plugins");
    public final Option optionProcess = new Option("p", JiceArgs.EVENT_DASHBOARD_TRACK, true, "Specify target process");
    public final Options options = new Options();

    public GlobalOptions() {
        this.options.addOption(this.optionHelp);
        this.options.addOption(this.optionListPlugins);
        this.options.addOption(this.optionProcess);
    }
}
