package org.apache.commons.cli;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Options implements Serializable {
    private Map longOpts = new HashMap();
    private Map optionGroups = new HashMap();
    private List requiredOpts = new ArrayList();
    private Map shortOpts = new HashMap();

    public Options addOption(Option opt) {
        String key = opt.getKey();
        if (opt.hasLongOpt()) {
            this.longOpts.put(opt.getLongOpt(), opt);
        }
        if (opt.isRequired()) {
            if (this.requiredOpts.contains(key)) {
                this.requiredOpts.remove(this.requiredOpts.indexOf(key));
            }
            this.requiredOpts.add(key);
        }
        this.shortOpts.put(key, opt);
        return this;
    }

    /* Access modifiers changed, original: 0000 */
    public List helpOptions() {
        return new ArrayList(this.shortOpts.values());
    }

    public List getRequiredOptions() {
        return this.requiredOpts;
    }

    public Option getOption(String opt) {
        opt = Util.stripLeadingHyphens(opt);
        if (this.shortOpts.containsKey(opt)) {
            return (Option) this.shortOpts.get(opt);
        }
        return (Option) this.longOpts.get(opt);
    }

    public boolean hasOption(String opt) {
        opt = Util.stripLeadingHyphens(opt);
        return this.shortOpts.containsKey(opt) || this.longOpts.containsKey(opt);
    }

    public OptionGroup getOptionGroup(Option opt) {
        return (OptionGroup) this.optionGroups.get(opt.getKey());
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("[ Options: [ short ");
        buf.append(this.shortOpts.toString());
        buf.append(" ] [ long ");
        buf.append(this.longOpts);
        buf.append(" ]");
        return buf.toString();
    }
}
