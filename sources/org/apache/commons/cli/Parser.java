package org.apache.commons.cli;

import com.facebook.internal.ServerProtocol;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

public abstract class Parser implements CommandLineParser {
    protected CommandLine cmd;
    private Options options;
    private List requiredOptions;

    public abstract String[] flatten(Options options, String[] strArr, boolean z);

    /* Access modifiers changed, original: protected */
    public void setOptions(Options options) {
        this.options = options;
        this.requiredOptions = new ArrayList(options.getRequiredOptions());
    }

    /* Access modifiers changed, original: protected */
    public Options getOptions() {
        return this.options;
    }

    /* Access modifiers changed, original: protected */
    public List getRequiredOptions() {
        return this.requiredOptions;
    }

    public CommandLine parse(Options options, String[] arguments, boolean stopAtNonOption) throws ParseException {
        return parse(options, arguments, null, stopAtNonOption);
    }

    public CommandLine parse(Options options, String[] arguments, Properties properties, boolean stopAtNonOption) throws ParseException {
        for (Option opt : options.helpOptions()) {
            opt.clearValues();
        }
        setOptions(options);
        this.cmd = new CommandLine();
        boolean eatTheRest = false;
        if (arguments == null) {
            arguments = new String[0];
        }
        ListIterator iterator = Arrays.asList(flatten(getOptions(), arguments, stopAtNonOption)).listIterator();
        while (iterator.hasNext()) {
            String t = (String) iterator.next();
            if ("--".equals(t)) {
                eatTheRest = true;
            } else if ("-".equals(t)) {
                if (stopAtNonOption) {
                    eatTheRest = true;
                } else {
                    this.cmd.addArg(t);
                }
            } else if (!t.startsWith("-")) {
                this.cmd.addArg(t);
                if (stopAtNonOption) {
                    eatTheRest = true;
                }
            } else if (!stopAtNonOption || getOptions().hasOption(t)) {
                processOption(t, iterator);
            } else {
                eatTheRest = true;
                this.cmd.addArg(t);
            }
            if (eatTheRest) {
                while (iterator.hasNext()) {
                    String str = (String) iterator.next();
                    if (!"--".equals(str)) {
                        this.cmd.addArg(str);
                    }
                }
            }
        }
        processProperties(properties);
        checkRequiredOptions();
        return this.cmd;
    }

    /* Access modifiers changed, original: protected */
    public void processProperties(Properties properties) {
        if (properties != null) {
            Enumeration e = properties.propertyNames();
            while (e.hasMoreElements()) {
                String option = e.nextElement().toString();
                if (!this.cmd.hasOption(option)) {
                    Option opt = getOptions().getOption(option);
                    String value = properties.getProperty(option);
                    if (opt.hasArg()) {
                        if (opt.getValues() == null || opt.getValues().length == 0) {
                            try {
                                opt.addValueForProcessing(value);
                            } catch (RuntimeException e2) {
                            }
                        }
                    } else if (!("yes".equalsIgnoreCase(value) || ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equalsIgnoreCase(value) || "1".equalsIgnoreCase(value))) {
                        return;
                    }
                    this.cmd.addOption(opt);
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void checkRequiredOptions() throws MissingOptionException {
        if (!getRequiredOptions().isEmpty()) {
            throw new MissingOptionException(getRequiredOptions());
        }
    }

    public void processArgs(Option opt, ListIterator iter) throws ParseException {
        while (iter.hasNext()) {
            String str = (String) iter.next();
            if (getOptions().hasOption(str) && str.startsWith("-")) {
                iter.previous();
                break;
            } else {
                try {
                    opt.addValueForProcessing(Util.stripLeadingAndTrailingQuotes(str));
                } catch (RuntimeException e) {
                    iter.previous();
                }
            }
        }
        if (opt.getValues() == null && !opt.hasOptionalArg()) {
            throw new MissingArgumentException(opt);
        }
    }

    /* Access modifiers changed, original: protected */
    public void processOption(String arg, ListIterator iter) throws ParseException {
        if (getOptions().hasOption(arg)) {
            Option opt = (Option) getOptions().getOption(arg).clone();
            if (opt.isRequired()) {
                getRequiredOptions().remove(opt.getKey());
            }
            if (getOptions().getOptionGroup(opt) != null) {
                OptionGroup group = getOptions().getOptionGroup(opt);
                if (group.isRequired()) {
                    getRequiredOptions().remove(group);
                }
                group.setSelected(opt);
            }
            if (opt.hasArg()) {
                processArgs(opt, iter);
            }
            this.cmd.addOption(opt);
            return;
        }
        throw new UnrecognizedOptionException(new StringBuffer().append("Unrecognized option: ").append(arg).toString(), arg);
    }
}
