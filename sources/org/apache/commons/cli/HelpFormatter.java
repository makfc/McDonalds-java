package org.apache.commons.cli;

import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class HelpFormatter {
    public String defaultArgName = "arg";
    public int defaultDescPad = 3;
    public int defaultLeftPad = 1;
    public String defaultLongOptPrefix = "--";
    public String defaultNewLine = System.getProperty("line.separator");
    public String defaultOptPrefix = "-";
    public String defaultSyntaxPrefix = "usage: ";
    public int defaultWidth = 74;
    protected Comparator optionComparator = new OptionComparator(null);

    /* renamed from: org.apache.commons.cli.HelpFormatter$1 */
    static class C46331 {
    }

    private static class OptionComparator implements Comparator {
        private OptionComparator() {
        }

        OptionComparator(C46331 x0) {
            this();
        }

        public int compare(Object o1, Object o2) {
            return ((Option) o1).getKey().compareToIgnoreCase(((Option) o2).getKey());
        }
    }

    public int getWidth() {
        return this.defaultWidth;
    }

    public int getLeftPadding() {
        return this.defaultLeftPad;
    }

    public int getDescPadding() {
        return this.defaultDescPad;
    }

    public Comparator getOptionComparator() {
        return this.optionComparator;
    }

    public void printOptions(PrintWriter pw, int width, Options options, int leftPad, int descPad) {
        StringBuffer sb = new StringBuffer();
        renderOptions(sb, width, options, leftPad, descPad);
        pw.println(sb.toString());
    }

    /* Access modifiers changed, original: protected */
    public StringBuffer renderOptions(StringBuffer sb, int width, Options options, int leftPad, int descPad) {
        StringBuffer optBuf;
        Option option;
        String lpad = createPadding(leftPad);
        String dpad = createPadding(descPad);
        int max = 0;
        List prefixList = new ArrayList();
        List<Option> optList = options.helpOptions();
        Collections.sort(optList, getOptionComparator());
        for (Option option2 : optList) {
            optBuf = new StringBuffer(8);
            if (option2.getOpt() == null) {
                optBuf.append(lpad).append(new StringBuffer().append("   ").append(this.defaultLongOptPrefix).toString()).append(option2.getLongOpt());
            } else {
                optBuf.append(lpad).append(this.defaultOptPrefix).append(option2.getOpt());
                if (option2.hasLongOpt()) {
                    optBuf.append(',').append(this.defaultLongOptPrefix).append(option2.getLongOpt());
                }
            }
            if (option2.hasArg()) {
                if (option2.hasArgName()) {
                    optBuf.append(" <").append(option2.getArgName()).append(">");
                } else {
                    optBuf.append(SafeJsonPrimitive.NULL_CHAR);
                }
            }
            prefixList.add(optBuf);
            if (optBuf.length() > max) {
                max = optBuf.length();
            }
        }
        int x = 0;
        Iterator i = optList.iterator();
        while (i.hasNext()) {
            option2 = (Option) i.next();
            int x2 = x + 1;
            optBuf = new StringBuffer(prefixList.get(x).toString());
            if (optBuf.length() < max) {
                optBuf.append(createPadding(max - optBuf.length()));
            }
            optBuf.append(dpad);
            int nextLineTabStop = max + descPad;
            if (option2.getDescription() != null) {
                optBuf.append(option2.getDescription());
            }
            renderWrappedText(sb, width, nextLineTabStop, optBuf.toString());
            if (i.hasNext()) {
                sb.append(this.defaultNewLine);
            }
            x = x2;
        }
        return sb;
    }

    /* Access modifiers changed, original: protected */
    public StringBuffer renderWrappedText(StringBuffer sb, int width, int nextLineTabStop, String text) {
        int pos = findWrapPos(text, width, 0);
        if (pos == -1) {
            sb.append(rtrim(text));
        } else {
            sb.append(rtrim(text.substring(0, pos))).append(this.defaultNewLine);
            if (nextLineTabStop >= width) {
                nextLineTabStop = 1;
            }
            String padding = createPadding(nextLineTabStop);
            while (true) {
                text = new StringBuffer().append(padding).append(text.substring(pos).trim()).toString();
                pos = findWrapPos(text, width, 0);
                if (pos == -1) {
                    break;
                }
                if (text.length() > width && pos == nextLineTabStop - 1) {
                    pos = width;
                }
                sb.append(rtrim(text.substring(0, pos))).append(this.defaultNewLine);
            }
            sb.append(text);
        }
        return sb;
    }

    /* Access modifiers changed, original: protected */
    public int findWrapPos(String text, int width, int startPos) {
        int pos = text.indexOf(10, startPos);
        if (pos == -1 || pos > width) {
            pos = text.indexOf(9, startPos);
            if (pos == -1 || pos > width) {
                if (startPos + width >= text.length()) {
                    return -1;
                }
                char c;
                pos = startPos + width;
                while (pos >= startPos) {
                    c = text.charAt(pos);
                    if (c == SafeJsonPrimitive.NULL_CHAR || c == 10 || c == 13) {
                        break;
                    }
                    pos--;
                }
                if (pos > startPos) {
                    return pos;
                }
                pos = startPos + width;
                while (pos <= text.length()) {
                    c = text.charAt(pos);
                    if (c == SafeJsonPrimitive.NULL_CHAR || c == 10 || c == 13) {
                        break;
                    }
                    pos++;
                }
                if (pos != text.length()) {
                    return pos;
                }
                return -1;
            }
        }
        return pos + 1;
    }

    /* Access modifiers changed, original: protected */
    public String createPadding(int len) {
        StringBuffer sb = new StringBuffer(len);
        for (int i = 0; i < len; i++) {
            sb.append(SafeJsonPrimitive.NULL_CHAR);
        }
        return sb.toString();
    }

    /* Access modifiers changed, original: protected */
    public String rtrim(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int pos = s.length();
        while (pos > 0 && Character.isWhitespace(s.charAt(pos - 1))) {
            pos--;
        }
        return s.substring(0, pos);
    }
}
