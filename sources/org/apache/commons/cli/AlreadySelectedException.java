package org.apache.commons.cli;

public class AlreadySelectedException extends ParseException {
    private OptionGroup group;
    private Option option;

    public AlreadySelectedException(String message) {
        super(message);
    }

    public AlreadySelectedException(OptionGroup group, Option option) {
        this(new StringBuffer().append("The option '").append(option.getKey()).append("' was specified but an option from this group ").append("has already been selected: '").append(group.getSelected()).append("'").toString());
        this.group = group;
        this.option = option;
    }
}
