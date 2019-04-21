package com.google.api.client.http;

import com.google.api.client.util.Preconditions;
import java.util.HashMap;
import java.util.Map;

public class UriTemplate {
    static final Map<Character, CompositeOutput> COMPOSITE_PREFIXES = new HashMap();

    private enum CompositeOutput {
        PLUS(Character.valueOf('+'), "", ",", false, true),
        HASH(Character.valueOf('#'), "#", ",", false, true),
        DOT(Character.valueOf('.'), ".", ".", false, false),
        FORWARD_SLASH(Character.valueOf('/'), "/", "/", false, false),
        SEMI_COLON(Character.valueOf(';'), ";", ";", true, false),
        QUERY(Character.valueOf('?'), "?", "&", true, false),
        AMP(Character.valueOf('&'), "&", "&", true, false),
        SIMPLE(null, "", ",", false, false);
        
        private final String explodeJoiner;
        private final String outputPrefix;
        private final Character propertyPrefix;
        private final boolean requiresVarAssignment;
        private final boolean reservedExpansion;

        private CompositeOutput(Character propertyPrefix, String outputPrefix, String explodeJoiner, boolean requiresVarAssignment, boolean reservedExpansion) {
            this.propertyPrefix = propertyPrefix;
            this.outputPrefix = (String) Preconditions.checkNotNull(outputPrefix);
            this.explodeJoiner = (String) Preconditions.checkNotNull(explodeJoiner);
            this.requiresVarAssignment = requiresVarAssignment;
            this.reservedExpansion = reservedExpansion;
            if (propertyPrefix != null) {
                UriTemplate.COMPOSITE_PREFIXES.put(propertyPrefix, this);
            }
        }
    }

    static {
        CompositeOutput.values();
    }
}
