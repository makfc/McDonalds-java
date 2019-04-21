package com.facebook.stetho.inspector.network;

import android.annotation.SuppressLint;
import java.util.ArrayList;
import javax.annotation.Nullable;

public class MimeMatcher<T> {
    private final ArrayList<MimeMatcherRule> mRuleMap = new ArrayList();

    @SuppressLint({"BadMethodUse-java.lang.String.length"})
    private class MimeMatcherRule {
        private final boolean mHasWildcard;
        private final String mMatchPrefix;
        private final T mResultIfMatched;

        public MimeMatcherRule(String ruleExpression, T resultIfMatched) {
            if (ruleExpression.endsWith("*")) {
                this.mHasWildcard = true;
                this.mMatchPrefix = ruleExpression.substring(0, ruleExpression.length() - 1);
            } else {
                this.mHasWildcard = false;
                this.mMatchPrefix = ruleExpression;
            }
            if (this.mMatchPrefix.contains("*")) {
                throw new IllegalArgumentException("Multiple wildcards present in rule expression " + ruleExpression);
            }
            this.mResultIfMatched = resultIfMatched;
        }

        public boolean match(String mimeType) {
            if (!mimeType.startsWith(this.mMatchPrefix)) {
                return false;
            }
            if (this.mHasWildcard || mimeType.length() == this.mMatchPrefix.length()) {
                return true;
            }
            return false;
        }

        public T getResultIfMatched() {
            return this.mResultIfMatched;
        }
    }

    public void addRule(String ruleExpression, T resultIfMatched) {
        this.mRuleMap.add(new MimeMatcherRule(ruleExpression, resultIfMatched));
    }

    public void clear() {
        this.mRuleMap.clear();
    }

    @Nullable
    public T match(String mimeT) {
        int ruleMapN = this.mRuleMap.size();
        for (int i = 0; i < ruleMapN; i++) {
            MimeMatcherRule rule = (MimeMatcherRule) this.mRuleMap.get(i);
            if (rule.match(mimeT)) {
                return rule.getResultIfMatched();
            }
        }
        return null;
    }
}
