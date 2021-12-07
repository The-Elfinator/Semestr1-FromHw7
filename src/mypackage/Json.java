package mypackage;

public final class Json {
    public static Object parse(final String string) {
        return parse(new StringCharSource(string));
    }

    public static Object parse(final CharSource source) {
        return new JsonParser(source).parse();
    }

    private static class JsonParser extends BaseParser {

        public JsonParser(CharSource source) {
            super(source);
        }

        public Object parse() {
            skipWhitespace();
            final Object result = parseElement();
            skipWhitespace();
            if (!end()) {
                throw error("Expected end of input");
            }
            return result;
        }

        private void skipWhitespace() {
            while (take(' ') || take('\n') || take('\r') || take('\t')) {
                //Do nothing
            }
        }

        private Object parseElement() {
            if (take('n')) {
                expect("ull");
                return null;
            } else if (take('t')) {
                expect("rue");
                return true;
            } else if (take('f')) {
                expect("alse");
                return false;
            } else {
                throw error("null, true or false expected");
            }
        }
    }
}
