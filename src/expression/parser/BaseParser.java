package expression.parser;

public class BaseParser {
    private final char END = 0;
    private final CharSource source;
    private char ch;

    public BaseParser(CharSource source) {
        this.source = source;
        take();
    }

    // returns current symbol without reading next
    protected char getCurrent() {
        return this.ch;
    }

    // return 'true' if current symbol equals to expected, 'false' otherwise
    protected boolean test(final char expected) {
        return ch == expected;
    }

    // return current symbol and read next
    protected char take() {
        final char result = ch;
        ch = source.hasNext() ? source.next() : END;
        return result;
    }

    // return 'true' AND read the next symbol if current is equal to expected one, otherwise return 'false'
    protected boolean take(final char expected) {
        if (test(expected)) {
            take();
            return true;
        } else {
            return false;
        }
    }

    // throws error if current symbol is not equal to expected one
    protected void expect(final char expected) {
        if (!take(expected)) {
            throw error(String.format(
                    "Expected '%s', found '%s'",
                    expected, ch != END ? ch : "end-of-input"
            ));
            // message about error
        }
    }

    protected IllegalArgumentException error(String message) {
        return source.error(message);
    }

    // throws error if current String is not equal to expected one
    protected void expect(final String expected) {
        for (final char c : expected.toCharArray()) {
            expect(c);
        }
    }

    // return 'true' if current symbol is between min and max, otherwise return 'false'
    protected boolean between(final char min, final char max) {
        return min <= ch && ch <= max;
    }

    // return 'true' if there is end of input data, otherwise return 'false'
    protected boolean end() {
        return test(END);
    }
}
