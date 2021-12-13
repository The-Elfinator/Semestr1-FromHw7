package mypackage;

public class BaseParser {
    private final char END = 0;
    private final CharSource source;
    private char ch;

    public BaseParser(CharSource source) {
        this.source = source;
        take();
    }

    protected boolean test(final char expected) {
        return ch == expected;
    }

    protected char take() {
        final char result = ch;
        ch = source.hasNext() ? source.next() : END;
        return result;
    }

    protected boolean take(final char expected) {
        if (test(expected)) {
            take();
            return true;
        } else {
            return false;
        }
    }

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

    protected void expect(final String expected) {
        for (final char c : expected.toCharArray()) {
            expect(c);
        }
    }

    protected boolean between(final char min, final char max) {
        return min <= ch && ch <= max;
    }

    protected boolean end() {
        return test(END);
    }
}
