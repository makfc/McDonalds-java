package org.apache.commons.cli;

public class MissingArgumentException extends ParseException {
    private Option option;

    public MissingArgumentException(String message) {
        super(message);
    }

    public MissingArgumentException(Option option) {
        this(new StringBuffer().append("Missing argument for option: ").append(option.getKey()).toString());
        this.option = option;
    }
}
