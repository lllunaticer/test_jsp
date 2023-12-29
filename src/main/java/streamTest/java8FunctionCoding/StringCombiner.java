package streamTest.java8FunctionCoding;

/**
 * @author Xingjian LONG
 * Created on 2021-08-26
 */
public class StringCombiner {
    private StringBuilder builder;
    private String prefix;
    private String suffix;
    private String delim;

    public StringCombiner(String prefix, String suffix, String delim) {
        this.builder = new StringBuilder();
        this.prefix = prefix;
        this.suffix = suffix;
        this.delim = delim;
    }

    public StringCombiner add(String element) {
        if (areAtStart()) {
            builder.append(prefix);
        } else {
            builder.append(delim);
        }
        builder.append(element);
        return this;
    }

    public StringCombiner merge(StringCombiner other) {
        builder.append(other.builder);
        return this;
    }

    public String toString() {
        builder.append(suffix);
        return builder.toString();
    }

    private boolean areAtStart() {
        return builder.length() <= 0;
    }
}
