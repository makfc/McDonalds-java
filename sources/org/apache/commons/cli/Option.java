package org.apache.commons.cli;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Option implements Serializable, Cloneable {
    private String argName = "arg";
    private String description;
    private String longOpt;
    private int numberOfArgs = -1;
    private String opt;
    private boolean optionalArg;
    private boolean required;
    private Object type;
    private List values = new ArrayList();
    private char valuesep;

    public Option(String opt, String longOpt, boolean hasArg, String description) throws IllegalArgumentException {
        OptionValidator.validateOption(opt);
        this.opt = opt;
        this.longOpt = longOpt;
        if (hasArg) {
            this.numberOfArgs = 1;
        }
        this.description = description;
    }

    /* Access modifiers changed, original: 0000 */
    public String getKey() {
        if (this.opt == null) {
            return this.longOpt;
        }
        return this.opt;
    }

    public String getOpt() {
        return this.opt;
    }

    public String getLongOpt() {
        return this.longOpt;
    }

    public boolean hasOptionalArg() {
        return this.optionalArg;
    }

    public boolean hasLongOpt() {
        return this.longOpt != null;
    }

    public boolean hasArg() {
        return this.numberOfArgs > 0 || this.numberOfArgs == -2;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isRequired() {
        return this.required;
    }

    public String getArgName() {
        return this.argName;
    }

    public boolean hasArgName() {
        return this.argName != null && this.argName.length() > 0;
    }

    public boolean hasArgs() {
        return this.numberOfArgs > 1 || this.numberOfArgs == -2;
    }

    public char getValueSeparator() {
        return this.valuesep;
    }

    public boolean hasValueSeparator() {
        return this.valuesep > 0;
    }

    /* Access modifiers changed, original: 0000 */
    public void addValueForProcessing(String value) {
        switch (this.numberOfArgs) {
            case -1:
                throw new RuntimeException("NO_ARGS_ALLOWED");
            default:
                processValue(value);
                return;
        }
    }

    private void processValue(String value) {
        if (hasValueSeparator()) {
            char sep = getValueSeparator();
            int index = value.indexOf(sep);
            while (index != -1 && this.values.size() != this.numberOfArgs - 1) {
                add(value.substring(0, index));
                value = value.substring(index + 1);
                index = value.indexOf(sep);
            }
        }
        add(value);
    }

    private void add(String value) {
        if (this.numberOfArgs <= 0 || this.values.size() <= this.numberOfArgs - 1) {
            this.values.add(value);
            return;
        }
        throw new RuntimeException("Cannot add value, list full.");
    }

    public String[] getValues() {
        return hasNoValues() ? null : (String[]) this.values.toArray(new String[this.values.size()]);
    }

    public String toString() {
        StringBuffer buf = new StringBuffer().append("[ option: ");
        buf.append(this.opt);
        if (this.longOpt != null) {
            buf.append(" ").append(this.longOpt);
        }
        buf.append(" ");
        if (hasArgs()) {
            buf.append("[ARG...]");
        } else if (hasArg()) {
            buf.append(" [ARG]");
        }
        buf.append(" :: ").append(this.description);
        if (this.type != null) {
            buf.append(" :: ").append(this.type);
        }
        buf.append(" ]");
        return buf.toString();
    }

    private boolean hasNoValues() {
        return this.values.isEmpty();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Option option = (Option) o;
        if (this.opt == null ? option.opt != null : !this.opt.equals(option.opt)) {
            return false;
        }
        if (this.longOpt != null) {
            if (this.longOpt.equals(option.longOpt)) {
                return true;
            }
        } else if (option.longOpt == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result;
        int i = 0;
        if (this.opt != null) {
            result = this.opt.hashCode();
        } else {
            result = 0;
        }
        int i2 = result * 31;
        if (this.longOpt != null) {
            i = this.longOpt.hashCode();
        }
        return i2 + i;
    }

    public Object clone() {
        try {
            Option option = (Option) super.clone();
            option.values = new ArrayList(this.values);
            return option;
        } catch (CloneNotSupportedException cnse) {
            throw new RuntimeException(new StringBuffer().append("A CloneNotSupportedException was thrown: ").append(cnse.getMessage()).toString());
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void clearValues() {
        this.values.clear();
    }
}
