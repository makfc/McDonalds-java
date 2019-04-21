package org.apache.commons.cli;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CommandLine implements Serializable {
    private List args = new LinkedList();
    private List options = new ArrayList();

    CommandLine() {
    }

    public boolean hasOption(String opt) {
        return this.options.contains(resolveOption(opt));
    }

    private Option resolveOption(String opt) {
        opt = Util.stripLeadingHyphens(opt);
        for (Option option : this.options) {
            if (opt.equals(option.getOpt())) {
                return option;
            }
            if (opt.equals(option.getLongOpt())) {
                return option;
            }
        }
        return null;
    }

    public List getArgList() {
        return this.args;
    }

    /* Access modifiers changed, original: 0000 */
    public void addArg(String arg) {
        this.args.add(arg);
    }

    /* Access modifiers changed, original: 0000 */
    public void addOption(Option opt) {
        this.options.add(opt);
    }
}
