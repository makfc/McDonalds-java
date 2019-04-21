package org.apache.commons.cli;

public class BasicParser extends Parser {
    /* Access modifiers changed, original: protected */
    public String[] flatten(Options options, String[] arguments, boolean stopAtNonOption) {
        return arguments;
    }
}
